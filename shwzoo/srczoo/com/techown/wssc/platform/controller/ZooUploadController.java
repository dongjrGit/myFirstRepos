package com.techown.wssc.platform.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.util.OssUtil;
import com.techown.util.SignatureUtil;
import com.yinlian.Enums.RelationTypeEnum;
import com.yinlian.wssc.web.util.ConfigUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/zoo/image")
public class ZooUploadController {

	private static Logger logger = LoggerFactory.getLogger(ZooUploadController.class);

	/**
	 * JSON 转换
	 */
	private static ObjectMapper MAPPER = new ObjectMapper();

	@RequestMapping(value = "/getSignature", produces = "text/html;charset=UTF-8")
	public  void upload(HttpServletRequest request, HttpServletResponse response) {
		String endpoint = SignatureUtil.endpoint;
		String accessId = SignatureUtil.accessId;
		String accessKey = SignatureUtil.accessKey;
		String bucket = SignatureUtil.bucket;
		String domainName = SignatureUtil.domainName;
		String type = request.getParameter("type");
		String uploadUrl=SignatureUtil.uploadUrl;
		String dir = null;
		
		if("50".equals(type)){
			 dir = "zoo/";
		}else{
			 dir = "platform/";
		}
		//暂时使用backet外网域名，使用cdn要切换到加速域名
		String host = "http://" + domainName;
		JSONObject ja1 = null;
		OSSClient client = new OSSClient(endpoint, accessId, accessKey);
		try {
			//token有效期半个小时
			long expireTime = 30*60;
			long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
			Date expiration = new Date(expireEndTime);
			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
			policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
			String postPolicy = client.generatePostPolicy(expiration, policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String encodedPolicy = BinaryUtil.toBase64String(binaryData);
			String postSignature = client.calculatePostSignature(postPolicy);
			Map<String, String> respMap = new LinkedHashMap<String, String>();
			respMap.put("accessid", accessId);
			respMap.put("policy", encodedPolicy);
			respMap.put("signature", postSignature);
			// respMap.put("expire", formatISO8601Date(expiration));
			respMap.put("dir", dir);
			respMap.put("host", host);
			respMap.put("expire", String.valueOf(expireEndTime / 1000));
			respMap.put("uploadUrl", uploadUrl);
			ja1 = JSONObject.fromObject(respMap);
			response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
            response(request, response, ja1.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void response(HttpServletRequest request, HttpServletResponse response, String results) throws IOException {
		String callbackFunName = request.getParameter("callback");
		if (callbackFunName==null || callbackFunName.equalsIgnoreCase(""))
			response.getWriter().println(results);
		else
			response.getWriter().println(callbackFunName + "( "+results+" )");
		response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();
	}

	@RequestMapping(value = "/upload", produces = "text/html;charset=UTF-8")
	public @ResponseBody String upload(MultipartHttpServletRequest multipartRequest,
			String relationid, String relationtype, String ch, String type, String iskdr,
			String token, HttpServletResponse response) throws JsonProcessingException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("error", 0);
		try {
			Integer irelationtype = null;
			if (StringUtils.isNotBlank(relationtype)) {
				irelationtype = Integer.parseInt(relationtype);
			} else {
				map.put("error", -100);
				map.put("Message","参数类型不正确！");
				return MAPPER.writeValueAsString(map);
			}
			boolean flag = false;
			RelationTypeEnum sEnum = RelationTypeEnum.其他;
			RelationTypeEnum[] relationTypeEnums = RelationTypeEnum.values();
			for (RelationTypeEnum iTypeEnum : relationTypeEnums) {
				if (iTypeEnum.getValue() == irelationtype) {
					flag = true;
					sEnum = iTypeEnum;
					break;
				}
			}
			if (!flag) {
				map.put("error", -100);
				map.put("Message","参数类型不正确！");
				return MAPPER.writeValueAsString(map);
			}
			List<String> paths = new ArrayList<String>();
			// 获取多个file
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile imgFile = multipartRequest.getFile(key);
				if (imgFile.getOriginalFilename().length() > 0) {
					String fileName = imgFile.getOriginalFilename();
					int Index = fileName.lastIndexOf(".");
					String ext = fileName.substring(Index + 1, fileName.length());
					String exts = ConfigUtil.get_instances().getuploadext();
					if (!exts.toLowerCase().contains(ext.toLowerCase())) {
						map.put("error", -101);
						map.put("Message", "上传文件类型不正确，只请允许上文件类型：" + exts + "！");
						return MAPPER.writeValueAsString(map);
					}
					InputStream in = imgFile.getInputStream();
					if (!savefile(fileName, in, ext, paths, sEnum, relationid,relationtype)) {
						logger.error("image upload fail");
						map.put("error", -900);
						map.put("Message", "系统错误!");
						return MAPPER.writeValueAsString(map);
					}
				}
			}
			map.put("url",paths.get(0));
		} catch (Exception e) {
			logger.error("upload exception", e);
			map.put("error", -900);
			map.put("Message", "系统错误!");
		}
		return MAPPER.writeValueAsString(map);
	}

	private Boolean savefile(String fileName, InputStream in, String ext, List<String> paths,
			RelationTypeEnum sEnum, String relationid,String relationtype) {
		String ossKey = "";
		if("50".equals(relationtype)){
			ossKey = "zoo/" + UUID.randomUUID().toString() + "." + ext;
		}else{
			ossKey = "platform/" + UUID.randomUUID().toString() + "." + ext;
		}
		String key = ossKey;
		String url = OssUtil.upload(null, in, key);
		if (null == url) {
			return false;
		}
		paths.add(url);
		/*ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		cachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					FileRecords imgrecords = new FileRecords();
					if (ext.toLowerCase().indexOf("jpg") != -1
							|| ext.toLowerCase().indexOf("jepg") != -1) {
						imgrecords.setFiletype(FileTypeEnum.jpg.getValue());
					}
					if (ext.toLowerCase().indexOf("png") != -1) {
						imgrecords.setFiletype(FileTypeEnum.png.getValue());
					}
					if (ext.toLowerCase().indexOf("gif") != -1) {
						imgrecords.setFiletype(FileTypeEnum.gif.getValue());
					}
					if (ext.toLowerCase().indexOf("bmp") != -1) {
						imgrecords.setFiletype(FileTypeEnum.bmp.getValue());
					}
					if (imgrecords.getFiletype() == null || imgrecords.getFiletype() == 0) {
						imgrecords.setFiletype(FileTypeEnum.other.getValue());
					}
					imgrecords.setCreatetime(new Date());
					imgrecords.setGroupname("oss");
					imgrecords.setServername(key);
					imgrecords.setLocalname(fileName);
					imgrecords.setRelationid(StringUtilsEX.ToIntNull(relationid));

					imgrecords.setRelationtype(sEnum.getValue());
					imgrecords.setUrl(url);
					imgrecords.setStatus(0);
					fileRecordsService.add(imgrecords);
				} catch (Exception e) {
					LogHandle.error(LogType.Other, "img:{0} ", e, "image/upload");
				}
			}
		});
		cachedThreadPool.shutdown();*/
		return true;
	}
}

package com.techown.wssc.platform.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.util.OssUtil;
import com.techown.wssc.web.po.ZooMap;
import com.techown.wssc.web.service.ZooMapService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.fastdfs.FastDFSFile;
import com.yinlian.wssc.web.fastdfs.FileManager;
import com.yinlian.wssc.web.util.ConfigUtil;
import com.yinlian.wssc.web.util.SessionState;

@Controller
@RequestMapping("/zoo/zooMap")
public class ZooMapController {
	private static Logger logger = LoggerFactory.getLogger(ZooMapController.class);
	@Autowired
	private ZooMapService zooMapService;

	/**
	 */
	private static ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 列表查询
	 * 
	 * @param type
	 *            类型
	 * @param state
	 *            状态
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public ReusltItem querylist(Integer type, Integer state) {
		logger.info("querylist ! type {} - state {} ", type, state);
		ReusltItem item = new ReusltItem();
		try {
			List<ZooMap> list = zooMapService.querylist(type, state);
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			logger.error("call querylist exception request type=" + type + "!state=" + state, e);
			item.setCode(-100);
		}
		return item;
	}

	/**
	 * 保存和编辑
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("/editlist")
	@ResponseBody
	public ReusltItem editslist(String data) {
		logger.info("editslist request data=" + data);
		ReusltItem item = new ReusltItem();
		ZooMap zooMap = null;
		try {
			zooMap = MAPPER.readValue(data, ZooMap.class);
			if (null == zooMap.getId()) {
				Date date = new Date();
				zooMap.setCreateTime(date);
				zooMap.setUpdateTime(date);
				zooMap.setOperator(SessionState.GetCurrentUser().getLoginName());
				zooMapService.addZooMap(zooMap);
			}
			item.setCode(0);
		} catch (Exception e) {
			logger.error("call editslist exception request data=" + data, e);
			item.setCode(100);
		}
		return item;
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 * @param srcState
	 * @param tgtState
	 * @return
	 */
	@RequestMapping("/dellist")
	@ResponseBody
	public ReusltItem dellist(Integer id, Integer srcState, Integer tgtState) {
		logger.info("dellist request id=" + id + "!srcState=" + srcState + "!tgtState=" + tgtState);
		ReusltItem item = new ReusltItem();
		try {
			zooMapService.updateState(id, srcState, tgtState, new Date());
			item.setCode(0);
			item.setDesc("删除成功");
		} catch (Exception e) {
			logger.error("call dellist exception request id=" + id + "!srcState=" + srcState + "!tgtState=" + tgtState, e);
			item.setCode(-900);
			item.setDesc("操作失败：" + e.getMessage());
		}
		return item;
	}

	/**
	 * 文件下载
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	public ResponseEntity<byte[]> download(String path) throws Exception {
		logger.info("download request path=" + path);
		try {
			byte[] bytes = OssUtil.download(null, path,"CDN");
			HttpHeaders headers = new HttpHeaders();
			// 获取图片后缀名
			String ext = path.substring(path.lastIndexOf("."));
			// String downloadFielName = "打开" + ext;
			// downloadFielName=new
			// String(downloadFielName.getBytes("UTF-8"),"iso-8859-1");
			// 通知浏览器以attachment（下载方式）打开图片
			headers.setContentDispositionFormData("attachment", "map" + ext);
			// application/octet-stream ： 二进制流数据（最常见的文件下载）。
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			
			return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("call download exception request path=" + path, e);
		}
		return null;
	}

	/**
	 * 保存和编辑
	 * 
	 * @param data
	 * @return
	 */
	/*@RequestMapping("/save")
	public String save(MultipartFile file, Integer type, String name, RedirectAttributes attr) {
		try {
			attr.addAttribute("type", type);
			attr.addAttribute("state",1);
			String fileName = file.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			ZooMap zooMap = new ZooMap();
			FastDFSFile files = new FastDFSFile(fileName, file.getBytes(), ext);
			String[] fileAbsolutePath = FileManager.upload(files);
			String url = fileAbsolutePath[7].replace("M00", "/data");
			Date date = new Date();
			zooMap.setCreateTime(date);
			zooMap.setUpdateTime(date);
			zooMap.setName(name);
			zooMap.setType(type);
			zooMap.setState(1);
			zooMap.setUrl(ConfigUtil.get_instances().fileUrl() + url);
			zooMap.setPath(fileAbsolutePath[7]);
			zooMap.setOperator(SessionState.GetCurrentUser().getLoginName());
			zooMapService.addZooMap(zooMap);
			return "redirect:/zoo/zooMap/list";
		} catch (Exception e) {
			logger.error("call editslist exception request data=", e);
			attr.addAttribute("msg","error");
		}
		return "redirect:/zoo/zooMap/editView";
	}*/
	@RequestMapping("/save")
	public String save(String path, Integer type,String url, String name, RedirectAttributes attr) {
		try {
			attr.addAttribute("type", type);
			attr.addAttribute("state",1);
			ZooMap zooMap = new ZooMap();
			Date date = new Date();
			zooMap.setCreateTime(date);
			zooMap.setUpdateTime(date);
			zooMap.setName(name);
			zooMap.setType(type);
			zooMap.setState(1);
			zooMap.setUrl(url);
			zooMap.setPath(path);
			zooMap.setOperator(SessionState.GetCurrentUser().getLoginName());
			zooMapService.addZooMap(zooMap);
			return "redirect:/zoo/zooMap/list";
		} catch (Exception e) {
			logger.error("call editslist exception request data=", e);
			attr.addAttribute("msg","error");
		}
		return "redirect:/zoo/zooMap/editView";
	}

}

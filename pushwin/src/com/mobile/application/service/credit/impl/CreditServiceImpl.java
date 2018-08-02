package com.mobile.application.service.credit.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.sysinit.SystemInfoInit;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.ICreditDao;
import com.mobile.application.entity.TBasisCredit;
import com.mobile.application.entity.TBasisCreditCheckrecord;
import com.mobile.application.entity.TBasisShootMater;
import com.mobile.application.service.credit.ICreditService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

@Service
public class CreditServiceImpl implements ICreditService{

	protected Logger logger = LoggerFactory.getLogger(SystemInfoInit.class);
	@Autowired
	private ICreditDao creditDao;
	@Autowired
	private ServletContext servletContext;
	
	@Transactional(readOnly = true)
	@Override
	public CommonVO qryCreditCheckList(int indexPage, int indexSize,String name,String creditKind, String status, String startTime, String endTime) {
		List<?> checkList = creditDao.qryCreditCheckList(indexPage,indexSize,name,creditKind,status,startTime,endTime);
		return new CommonVO(true, checkList, creditDao.qryCreditCheckCount(name,creditKind,status,startTime,endTime));
	}

	@Override
	@Transactional
	public Map<String, Object> qryCreditCheckDetail(String creditId) throws BusinessException {
		String rootPath = SpringProperty.props.getProperty("RootPath");
		String creditAppFolder = SpringProperty.props.getProperty("creditAppFolder");
		JSONArray modelJsonArray = (JSONArray) servletContext.getAttribute("creditModel");
		String txtFilePath = rootPath + creditAppFolder + "terminal/" + creditId + "/txt/info.txt";
		Map<String, String> appInfoMap;
		try {
			appInfoMap = resolveInfoTxt(txtFilePath);
		} catch (IOException e) {
			logger.error("[信用卡审核]读取txt文件出错。", e);
			throw new BusinessException("解析信用卡申请件文本信息出错");
		}
		
		Map<String, Map<String, String>> dictTypeMap = (Map<String, Map<String, String>>) servletContext.getAttribute("dictMap");
		for (int i = 0; i < modelJsonArray.size(); i++) {
			JSONObject columnJson = modelJsonArray.getJSONObject(i);
			if(StringUtils.isNotBlank(columnJson.getString("REMARK"))){
				Map<String, String> dictMap = dictTypeMap.get(columnJson.getString("REMARK"));
				columnJson.put("VALUE", dictMap.get(appInfoMap.get(columnJson.getString("EN_NAME"))));
			} else {
				columnJson.put("VALUE", appInfoMap.get(columnJson.getString("EN_NAME")));
			}
		}
		Map<String, Object> creditInfoMap = new HashMap<String, Object>();
		creditInfoMap.put("creditInfo", modelJsonArray);
		
		
		//开始读取图片信息
		String picFilePath = rootPath + creditAppFolder + "terminal/" + creditId + "/pic";
		File picForler = new File(picFilePath);
		
		FilenameFilter directoryFilter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String fileName) {
				if (dir.isDirectory()) {
					return true;
				} else {
					return false;
				}
			}
		};
		List<Map<String, Object>> oneDirectoryList = new ArrayList<Map<String,Object>>();
		File[] oneDirectorys =  picForler.listFiles(directoryFilter);
		for (File oneDirectory : oneDirectorys) {
			Map<String, Object> oneDirectoryDetail = new HashMap<String, Object>();
			TBasisShootMater tBasisShootMater1 = (TBasisShootMater) creditDao.getByID(TBasisShootMater.class,  oneDirectory.getName());
			if(tBasisShootMater1 != null){
				oneDirectoryDetail.put("oneDirectoryName", tBasisShootMater1.getMaterName());
			}else{
				oneDirectoryDetail.put("oneDirectoryName",oneDirectory.getName());
			}
			List<Map<String, Object>> twoDirectoryList = new ArrayList<Map<String,Object>>();
			if(oneDirectory.listFiles(directoryFilter).length > 0)
			for (File twoDirectory : oneDirectory.listFiles(directoryFilter)) {
				Map<String, Object> twoDirectoryDetail = new HashMap<String, Object>();
				TBasisShootMater tBasisShootMater = (TBasisShootMater) creditDao.getByID(TBasisShootMater.class,  twoDirectory.getName());
				if(tBasisShootMater != null){
					twoDirectoryDetail.put("twoDirectoryName", tBasisShootMater.getMaterName());
				}else{
					twoDirectoryDetail.put("twoDirectoryName", twoDirectory.getName());
				}
//				twoDirectoryDetail.put("twoDirectoryName", twoDirectory.getName());
				List<Map<String, String>> picList = new ArrayList<Map<String,String>>();
				if(twoDirectory.listFiles().length > 0)
				for (File picFile : twoDirectory.listFiles()) {
					Map<String, String> picFileDetail = new HashMap<String, String>();
					picFileDetail.put("picFilePath", picFile.getAbsolutePath().substring(rootPath.length()).replaceAll("\\\\", "/"));
					picFileDetail.put("picFileName", picFile.getName());
					picList.add(picFileDetail);
				}
				twoDirectoryDetail.put("picList", picList);
				twoDirectoryList.add(twoDirectoryDetail);
			}
			oneDirectoryDetail.put("twoDirectoryList", twoDirectoryList);
			oneDirectoryList.add(oneDirectoryDetail);
		}
		creditInfoMap.put("oneDirectoryList", oneDirectoryList);
		creditInfoMap.put("creditId", creditId);
		return creditInfoMap;
	}
	
	public Map<String, String> resolveInfoTxt(String txtPath)
			throws IOException {

		byte[] txtByte = FileUtils.readFileToByteArray(new File(txtPath));
		String jsonStr = new String(txtByte, "UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		Map<String, String> dataMap = new HashMap<String, String>();

//		if (jsonObject.keys().hasNext()) {
//			JSONObject obj = jsonObject.optJSONObject(String.valueOf(jsonObject.keys().next()));
			Iterator<?> it = jsonObject.keys();
			while (it.hasNext()) {
				String key = String.valueOf(it.next());
				String value = jsonObject.optString(key);
				dataMap.put(key, value);
			}
//		}
		return dataMap;
	}
	
	@Transactional(readOnly = true)
	@Override
	public CommonVO qrycreditCheckRecord(String creditId) {
		List<?> tBasisCreditCheckrecords = creditDao.qrycreditCheckRecord(creditId);
		return new CommonVO(true, tBasisCreditCheckrecords, null);
	}

	@Transactional
	@Override
	public CommonVO submitCheckResult(String creditId, String checkResult,
			String checkOpinion, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		TBasisCredit tBasisCredit = creditDao.get(TBasisCredit.class, creditId);
		tBasisCredit.setStatus(checkResult);
		TBasisCreditCheckrecord tBasisCreditCheckrecord = new TBasisCreditCheckrecord(tBasisCredit, checkResult, checkOpinion, sessionVO.getUserId(), DateUtil.format(new Date()));
		creditDao.save(tBasisCreditCheckrecord);
		return new CommonVO(true, "保存成功");
	}
}

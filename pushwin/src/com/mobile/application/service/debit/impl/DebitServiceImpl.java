package com.mobile.application.service.debit.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.IDebitDao;
import com.mobile.application.entity.TBasisDebit;
import com.mobile.application.entity.TBasisDebitCheckrecord;
import com.mobile.application.entity.TBasisShootMater;
import com.mobile.application.service.debit.IDebitService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;
@Service
public class DebitServiceImpl implements IDebitService {

	@Autowired
	private  IDebitDao  debitDao;
	@Autowired
	private ServletContext servletContext;
	
	@Transactional(readOnly = true)
	@Override
	public CommonVO qryDebitCheckList(int indexPage, int indexSize,String name,String status,String startTime,String endTime,String accountType) {
		List<?> checkList = debitDao.qryDebitCheckList(indexPage, indexSize, name,status,startTime,endTime,accountType);
		return new CommonVO(true, checkList, debitDao.qryDebitCheckCount(name,status,startTime,endTime,accountType));
	}
/////////////////////////////////////////////
	@Override
	@Transactional
	public Map<String, Object> qryDebitCheckDetail(String debitId) throws BusinessException{
		String rootPath = SpringProperty.props.getProperty("RootPath");
		String debitAppFolder = SpringProperty.props.getProperty("debitAppFolder");
		JSONArray modelJsonArray = (JSONArray) servletContext.getAttribute("debitModel");
		String txtFilePath = rootPath + debitAppFolder + "terminal/" + debitId + "/txt/info.txt";
		Map<String, String> appInfoMap;
		try {
			appInfoMap = resolveInfoTxt(txtFilePath);
		} catch (IOException e) {
//			logger.error("[信用卡审核]读取txt文件出错。");
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
		Map<String, Object> debitInfoMap = new HashMap<String, Object>();
		debitInfoMap.put("debitInfo", modelJsonArray);
		
		
		//开始读取图片信息
		String picFilePath = rootPath + debitAppFolder + "terminal/" + debitId + "/pic";
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
			TBasisShootMater tBasisShootMater1 = (TBasisShootMater) debitDao.getByID(TBasisShootMater.class,  oneDirectory.getName());
			if(tBasisShootMater1 != null){
				oneDirectoryDetail.put("oneDirectoryName", tBasisShootMater1.getMaterName());
			}else{
				oneDirectoryDetail.put("oneDirectoryName",oneDirectory.getName());
			}
//			oneDirectoryDetail.put("oneDirectoryName", oneDirectory.getName());
			List<Map<String, Object>> twoDirectoryList = new ArrayList<Map<String,Object>>();
			if(oneDirectory.listFiles(directoryFilter).length > 0)
			for (File twoDirectory : oneDirectory.listFiles(directoryFilter)) {
				Map<String, Object> twoDirectoryDetail = new HashMap<String, Object>();
				TBasisShootMater tBasisShootMater = (TBasisShootMater) debitDao.getByID(TBasisShootMater.class,  twoDirectory.getName());
				if(tBasisShootMater != null){
					twoDirectoryDetail.put("twoDirectoryName", tBasisShootMater.getMaterName());
				}else{
					twoDirectoryDetail.put("twoDirectoryName", twoDirectory.getName());
				}
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
		debitInfoMap.put("oneDirectoryList", oneDirectoryList);
		debitInfoMap.put("debitId", debitId);
		return debitInfoMap;
	}
	public Map<String, String> resolveInfoTxt(String txtPath)
	throws IOException {

byte[] txtByte = FileUtils.readFileToByteArray(new File(txtPath));
String jsonStr = new String(txtByte, "UTF-8");
JSONObject jsonObject = JSONObject.fromObject(jsonStr);
Map<String, String> dataMap = new HashMap<String, String>();

//if (jsonObject.keys().hasNext()) {
//	JSONObject obj = jsonObject.optJSONObject(String.valueOf(jsonObject.keys().next()));
	Iterator<?> it = jsonObject.keys();
	while (it.hasNext()) {
		String key = String.valueOf(it.next());
		String value = jsonObject.optString(key);
		dataMap.put(key, value);
	}
//}
return dataMap;
}
	
	/////////////////////////////////////////////////
	@Override
	@Transactional(readOnly = true)
	public CommonVO qryDebitCheckRecord(String debitId) {
		List<?> tBasisDebitCheckrecords = debitDao.qryDebitCheckRecord(debitId);
		return new CommonVO(true, tBasisDebitCheckrecords, null);
	}

	@Override
	@Transactional
	public CommonVO submitCheckResult(String debitId, String checkResult,
			String checkOpinion, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		TBasisDebit tBasisdebit = debitDao.get(TBasisDebit.class, debitId);
		tBasisdebit.setStatus(checkResult);
		TBasisDebitCheckrecord tBasisCreditCheckrecord = new TBasisDebitCheckrecord(tBasisdebit, checkResult, checkOpinion, sessionVO.getUserId(), DateUtil.format(new Date()));
		debitDao.save(tBasisCreditCheckrecord);
		return new CommonVO(true, "保存成功");
	}

}

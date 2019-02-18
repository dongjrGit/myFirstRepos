package com.pushwin.batchwork.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushwin.batchwork.common.SpringProperty;
import com.pushwin.batchwork.dao.IImportProInfoDao;
import com.pushwin.batchwork.model.TBasisiBusSpecialCostAdd;
import com.pushwin.batchwork.utils.DateUtil;


@Service("importProInfoService")
public class ImportProInfoService {
	@Autowired
	private IImportProInfoDao iimportProInfoDao;
	protected Logger logger = LoggerFactory.getLogger(ImportDebitServiceImpl.class);
	private String importProInfoFilePath = (String) SpringProperty.props.get("importProInfoFilePath");
	
	
	@Transactional
	public synchronized void importProInfo() throws IOException{
		String importProInfofilename = importProInfoFilePath + "/" + "property_"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importProInfofilename);
		File fileDes = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<TBasisiBusSpecialCostAdd> listProInfo = new ArrayList<TBasisiBusSpecialCostAdd>();
		Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");

		try {
			if (file.exists()) {
				iimportProInfoDao.deleteProInfo();
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					// content = new String(st.getBytes(),"utf-8");
					// content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] info = content.split("\\|");
					TBasisiBusSpecialCostAdd proInfo = new TBasisiBusSpecialCostAdd();
					proInfo.setBusInId(info[0].trim());
					proInfo.setBusInName(info[1].trim());
					proInfo.setBusInId_Bracnch(info[2].trim());
					proInfo.setBusInTypeId(info[3].trim());
					proInfo.setUpdateTime(info[4].trim());
					
					listProInfo.add(proInfo);
				}
				
				fileDes=new File(importProInfoFilePath + "/" +sdf.format(date));
				if(!fileDes .exists()  && !fileDes .isDirectory()){
					fileDes.mkdir();
				}else{
					
				}
				FileUtils.moveToDirectory(file,fileDes,true);
			}
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		iimportProInfoDao.SaveProInfo(listProInfo);

	}

	
}

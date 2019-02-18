package com.pushwin.batchwork.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushwin.batchwork.common.SpringProperty;
import com.pushwin.batchwork.dao.IImportOrgDao;
import com.pushwin.batchwork.model.TBasisBusBank;
import com.pushwin.batchwork.model.TBasisBusSubbank;
import com.pushwin.batchwork.model.TBasisOrg;
import com.pushwin.batchwork.model.TBasisOrgBatch;
import com.pushwin.batchwork.utils.DateUtil;

@Service("importOrgService")
public class ImportOrgServiceImpl {

	@Autowired
	private IImportOrgDao importOrgDao;
	protected Logger logger = LoggerFactory.getLogger(ImportDebitServiceImpl.class);
	private String importOrgFilePath = (String) SpringProperty.props.get("importOrgFilePath");
	
	@Transactional
	public synchronized void importOrg() throws IOException{
			
		int l = 0 ;
		try {
			if(importOrgDao.listTBasisBusBankFlag()){
				List<TBasisBusBank> listTBasisBusBank = importOrgDao.listTBasisBusBank();
			for (TBasisBusBank tBasisBusBank : listTBasisBusBank) {
				//String[] orgInfoArray = orgStrLine.split("\\|");
				List<TBasisOrgBatch> tBasisOrgs = (List<TBasisOrgBatch>) importOrgDao.get(TBasisOrgBatch.class, "orgId", tBasisBusBank.getOrgCode());
				TBasisOrgBatch tBasisOrg;
				if(tBasisOrgs.size() > 0){
					tBasisOrg = tBasisOrgs.get(0);
					tBasisOrg.setOrgName(tBasisBusBank.getOrgName());
//					tBasisOrg.setUpdateTime(orgInfoArray[3]);
				} else {
					tBasisOrg = new TBasisOrgBatch();
					tBasisOrg.setOrgCode(tBasisBusBank.getOrgCode());
					tBasisOrg.setOrgName(tBasisBusBank.getOrgName());
					tBasisOrg.setOrgId(tBasisBusBank.getOrgId());
//					tBasisOrg.setUpdateTime(orgInfoArray[3]);
					tBasisOrg.setOrgLevel("1");
					tBasisOrg.setOrgDesc(tBasisBusBank.getOrgCode());
					tBasisOrg.setOrgPid("xz");
				}
				l++;
				importOrgDao.saveOrUpdate(tBasisOrg);
				System.out.println(tBasisOrg.toString());
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//throw e;
		} 
		
		List<TBasisBusSubbank> listTBasisBusSubBank = importOrgDao.listTBasisBusSubbank();
		String subOrgStrLine = null;
		int j = 0 ;
		try {
			if(importOrgDao.listTBasisBusSubbankFlag()){
			for (TBasisBusSubbank tBasisBusSubbank : listTBasisBusSubBank) {
				//String[] orgInfoArray = subOrgStrLine.split("\\|");
				if("-1".equals(tBasisBusSubbank.getOrgId()))
					continue;
				List<TBasisOrg> tBasisOrgs = (List<TBasisOrg>) importOrgDao.get(TBasisOrg.class, "orgCode", tBasisBusSubbank.getOrgCode());
				TBasisOrg tBasisOrg;
				if(tBasisOrgs.size() > 0){
					tBasisOrg = tBasisOrgs.get(0);
					tBasisOrg.setOrgName(tBasisBusSubbank.getOrgName());
//					tBasisOrg.setUpdateTime(orgInfoArray[3]);
				} else {
					tBasisOrg = new TBasisOrg();
					tBasisOrg.setOrgCode(tBasisBusSubbank.getOrgCode());
					tBasisOrg.setOrgName(tBasisBusSubbank.getOrgName());
					//tBasisOrg.setOrgId(orgInfoArray[2]);
//					tBasisOrg.setUpdateTime(orgInfoArray[3]);
					tBasisOrg.setOrgLevel("2");
					tBasisOrg.setOrgDesc(tBasisBusSubbank.getOrgName());
					tBasisOrg.setOrgPid(tBasisBusSubbank.getOrgId());
				}
				j++;
				importOrgDao.saveOrUpdate(tBasisOrg);
			}
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Transactional
	public synchronized void importOrgOld() throws IOException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dateStr = dateFormat.format(new Date());
		String bankFilePath = importOrgFilePath + "/Banck" + DateUtil.batchTime() + ".txt";
		String subBankFilePath = importOrgFilePath + "/SubBank" + DateUtil.batchTime() + ".txt";
		File bankFile = new File(bankFilePath);
		FileReader fr;
		try {
			fr = new FileReader(bankFile);
		} catch (FileNotFoundException e) {
			logger.error("[机构跑批任务]一级机构文件不存在，退出批处理任务。");
			return;
		}
		BufferedReader br = new BufferedReader(fr);
		String orgStrLine = null;
		int l = 0 ;
		try {
			while((orgStrLine = br.readLine()) != null) {
				String[] orgInfoArray = orgStrLine.split("\\|");
				List<TBasisOrgBatch> tBasisOrgs = (List<TBasisOrgBatch>) importOrgDao.get(TBasisOrgBatch.class, "orgId", orgInfoArray[2]);
				TBasisOrgBatch tBasisOrg;
				if(tBasisOrgs.size() > 0){
					tBasisOrg = tBasisOrgs.get(0);
					tBasisOrg.setOrgName(orgInfoArray[1]);
//					tBasisOrg.setUpdateTime(orgInfoArray[3]);
				} else {
					tBasisOrg = new TBasisOrgBatch();
					tBasisOrg.setOrgCode(orgInfoArray[0]);
					tBasisOrg.setOrgName(orgInfoArray[1]);
					tBasisOrg.setOrgId(orgInfoArray[2]);
//					tBasisOrg.setUpdateTime(orgInfoArray[3]);
					tBasisOrg.setOrgLevel("1");
					tBasisOrg.setOrgDesc(orgInfoArray[1]);
					tBasisOrg.setOrgPid("xz");
				}
				l++;
				importOrgDao.saveOrUpdate(tBasisOrg);
				System.out.println(tBasisOrg.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
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
		
		File subBankFile = new File(subBankFilePath);
		try {
			fr = new FileReader(subBankFile);
		} catch (FileNotFoundException e) {
			logger.error("[机构跑批任务]一级机构文件不存在，退出批处理任务。");
			return;
		}
		br = new BufferedReader(fr);
		String subOrgStrLine = null;
		int j = 0 ;
		try {
			while((subOrgStrLine = br.readLine()) != null) {
				String[] orgInfoArray = subOrgStrLine.split("\\|");
				if("-1".equals(orgInfoArray[2]))
					continue;
				List<TBasisOrg> tBasisOrgs = (List<TBasisOrg>) importOrgDao.get(TBasisOrg.class, "orgCode", orgInfoArray[0]);
				TBasisOrg tBasisOrg;
				if(tBasisOrgs.size() > 0){
					tBasisOrg = tBasisOrgs.get(0);
					tBasisOrg.setOrgName(orgInfoArray[1]);
//					tBasisOrg.setUpdateTime(orgInfoArray[3]);
				} else {
					tBasisOrg = new TBasisOrg();
					tBasisOrg.setOrgCode(orgInfoArray[0]);
					tBasisOrg.setOrgName(orgInfoArray[1]);
					//tBasisOrg.setOrgId(orgInfoArray[2]);
//					tBasisOrg.setUpdateTime(orgInfoArray[3]);
					tBasisOrg.setOrgLevel("2");
					tBasisOrg.setOrgDesc(orgInfoArray[1]);
					tBasisOrg.setOrgPid(orgInfoArray[2]);
				}
				j++;
				importOrgDao.saveOrUpdate(tBasisOrg);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
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
	}
}

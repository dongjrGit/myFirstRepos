package com.pushwin.batchwork.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pushwin.batchwork.common.SpringProperty;
import com.pushwin.batchwork.dao.IImportDictDao;
import com.pushwin.batchwork.model.TBasisDict;
import com.pushwin.batchwork.model.TBasisDictBranch;
import com.pushwin.batchwork.model.TBasisDictBranchId;
import com.pushwin.batchwork.model.TBasisDictCompabymemo;
import com.pushwin.batchwork.model.TBasisDictCompabymemoId;
import com.pushwin.batchwork.model.TBasisDictId;
import com.pushwin.batchwork.model.TBasisDictIgroup;
import com.pushwin.batchwork.model.TBasisDictIgroupId;
import com.pushwin.batchwork.model.TBasisDictPlanstore;
import com.pushwin.batchwork.model.TBasisDictPlanstoreId;
import com.pushwin.batchwork.model.TBasisDictPlanstoregood;
import com.pushwin.batchwork.model.TBasisDictPlanstoregoodId;
import com.pushwin.batchwork.model.TBasisDictPlantype;
import com.pushwin.batchwork.model.TBasisDictPlantypeId;
import com.pushwin.batchwork.model.TBasisDictStoregoogs;
import com.pushwin.batchwork.model.TBasisDictStores;
import com.pushwin.batchwork.utils.DateUtil;
import com.pushwin.batchwork.utils.FileMv;

@Service("importDictService")
public class ImportDictValue {

	@Autowired
	private IImportDictDao iimportDictDao;

	protected Logger logger = LoggerFactory.getLogger(ImportDictValue.class);

	private String dictfilepath = (String) SpringProperty.props
			.get("dictfilepath");

	@Transactional
	public synchronized void importDictValueMany() {
		fileNaemeMatch(dictfilepath);
		/*
		 * importCompanyMemo(dictfilepath);
		 * importFavorleMessageab(dictfilepath);
		 * importPurchaseCode(dictfilepath); importJobMemo(dictfilepath);
		 * importJobTeacherLevel(dictfilepath); importCompanyType(dictfilepath);
		 * importJobLevel(dictfilepath); importMaritalStatus(dictfilepath);
		 * importEducationLevel(dictfilepath); importSpecialCost(dictfilepath);
		 * importBillAddress(dictfilepath); importPlanTerm(dictfilepath);
		 * importCustClass(dictfilepath); importStoreDistrict(dictfilepath);
		 * importStoreType(dictfilepath); importSale(dictfilepath);
		 * importCustClass(dictfilepath); importPlanType(dictfilepath);
		 * importPlanStore(dictfilepath); importStores(dictfilepath);
		 * importIgroup(dictfilepath); importStoreGoodS(dictfilepath);
		 * importplanStoreGood(dictfilepath);
		 */

	}

	/**
	 * 
	 * 优惠信息 文件名： FavorleMessageab 2013012.txt
	 * 
	 * @param dictPath
	 */
	public void importFavorleMessageab(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "FavorableMessage"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictBranch> listTBasisDict = new ArrayList<TBasisDictBranch>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					String orgCode = user[3];
					if ("302".equals(orgCode))
						continue;
					TBasisDictBranch tBasisDictBranch = new TBasisDictBranch();
					TBasisDictBranchId tBasisDictBranchId = new TBasisDictBranchId();
					// tBasisDictId.setBusinid(user[0]);
					tBasisDictBranch.setBusintypeid("FavorableMessage");
					tBasisDictBranchId.setBusinid(user[0]);
					tBasisDictBranchId.setBusinidBracnch(user[4]);
					tBasisDictBranch.setId(tBasisDictBranchId);
					if ("01".equals(user[4]))
						tBasisDictBranch.setDictremark("信用卡");
					if ("02".equals(user[4]))
						tBasisDictBranch.setDictremark("购易贷");
					if ("03".equals(user[4]))
						tBasisDictBranch.setDictremark("诚易贷");
					if ("04".equals(user[4]))
						tBasisDictBranch.setDictremark("购易贷卡");
					tBasisDictBranch.setBusinname(user[1]);
					tBasisDictBranch.setStatus("1");
					tBasisDictBranch.setUpdatetime(timeuser);
					tBasisDictBranch.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDictBranch);
				}
				iimportDictDao.saveTBasisDictBranch(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);

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

	}

	/**
	 * 2.3. 行业信息 1) 文件名： CompanyMemo2013012.txt
	 * 
	 * @param dictPath
	 */
	public void importCompanyMemo(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "CompanyMemo"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictCompabymemo> listTBasisDict = new ArrayList<TBasisDictCompabymemo>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("CompanyMemo");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict) String orgCode = user[3];
					 * if("302".equals(orgCode)) continue; ;
					 */
					TBasisDictCompabymemo tBasisDict = new TBasisDictCompabymemo();
					TBasisDictCompabymemoId tBasisDictId = new TBasisDictCompabymemoId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusinidBracnch(user[4]);
					// CompanyMemo
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setOrgArea(user[2]);
					tBasisDict.setBusintypeid("CompanyMemo");
					tBasisDict.setDictremark("行业信息");
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					// tBasisDict。setOrgArea(user[4]);
					listTBasisDict.add(tBasisDict);

				}
				iimportDictDao.saveListTBasisDictCompabymemo(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.2. 贷款用途 1) 文件名： PurchaseCode2013012.txt，
	 * 
	 * @param dictPath
	 */
	public void importPurchaseCode(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "PurchaseCode"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictBranch> listTBasisDict = new ArrayList<TBasisDictBranch>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("PurchaseCode");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					String orgCode = user[4];
					if ("302".equals(orgCode))
						continue;
					TBasisDictBranch tBasisDict = new TBasisDictBranch();
					TBasisDictBranchId tBasisDictId = new TBasisDictBranchId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusinidBracnch(user[1]);
					tBasisDict.setBusintypeid("PurchaseCode");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[2]);
					tBasisDict.setDictremark(user[5]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveTBasisDictBranch(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.4. 职位 1) 文件名： JobMemo2013012.txt
	 * 
	 * @param dictPath
	 */
	public void importJobMemo(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "JobMemo"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("JobMemo");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					String orgCode = user[3];
					if ("302".equals(orgCode))
						continue;
					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("JobMemo");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[1]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);

				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.5. 职位-教师级别 1) 文件名： JobTeacherLevel20130121.txt
	 * 
	 * 
	 * @param dictPath
	 */
	public void importJobTeacherLevel(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "JobTeacherLevel"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("JobTeacherLevel");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					String orgCode = user[3];
					if ("302".equals(orgCode))
						continue;
					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("JobTeacherLevel");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[1]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.6. 单位性质 1) 文件名： CompanyType2013012.txt
	 * 
	 * @param dictPath
	 */
	public void importCompanyType(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "CompanyType"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("CompanyType");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					String orgCode = user[3];
					if ("302".equals(orgCode))
						continue;
					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("CompanyType");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[1]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.7. 职务 1) 文件名： JobLevel2013012.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * @param dictPath
	 */
	public void importJobLevel(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "JobLevel"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("JobLevel");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					String orgCode = user[3];
					if ("302".equals(orgCode))
						continue;
					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("JobLevel");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[3]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.8. 婚姻状况 1) 文件名： MaritalStatus2013012.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * 
	 * @param dictPath
	 */
	public void importMaritalStatus(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "MaritalStatus"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("MaritalStatus");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					String orgCode = user[3];
					if ("302".equals(orgCode))
						continue;
					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("MaritalStatus");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[1]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.9. 教育程度 1) 文件名： EducationLevel2013012.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * @param dictPath
	 */
	public void importEducationLevel(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "EducationLevel"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("EducationLevel");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */

					String orgCode = user[3];
					if ("302".equals(orgCode))
						continue;
					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("EducationLevel");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[1]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.10. 客户特殊码 1) 文件名： SpecialCost2013012.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * @param dictPath
	 */
	public void importSpecialCost(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "SpecialCost"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictBranch> listTBasisDict = new ArrayList<TBasisDictBranch>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("SpecialCost");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					String orgCode = user[3];
					if ("302".equals(orgCode))
						continue;
					TBasisDictBranch tBasisDictBranch = new TBasisDictBranch();
					TBasisDictBranchId tBasisDictBranchId = new TBasisDictBranchId();
					// tBasisDictBranch.setBusintypeid("FavorableMessage");
					tBasisDictBranch.setBusintypeid("SpecialCost");
					tBasisDictBranchId.setBusinid(user[0]);
					tBasisDictBranchId.setBusinidBracnch(user[4]);
					tBasisDictBranch.setId(tBasisDictBranchId);
					tBasisDictBranch.setBusinname(user[1]);
					if ("01".equals(user[4]))
						tBasisDictBranch.setDictremark("信用卡");
					if ("02".equals(user[4]))
						tBasisDictBranch.setDictremark("购易贷");
					if ("03".equals(user[4]))
						tBasisDictBranch.setDictremark("诚易贷");
					if ("04".equals(user[4]))
						tBasisDictBranch.setDictremark("购易贷卡");

					tBasisDictBranch.setStatus("1");
					tBasisDictBranch.setUpdatetime(timeuser);
					tBasisDictBranch.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDictBranch);
				}
				iimportDictDao.saveTBasisDictBranch(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.15. 无卡支付账单寄送地址 7) 文件名： BillAddressYYYYMMDD.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * 
	 * @param dictPath
	 */
	public void importBillAddress(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "BillAddress"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("BillAddress");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */

					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("BillAddress");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[1]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.16. 无卡支付期数 13) 文件名： PlanTermYYYYMMDD.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * @param dictPath
	 */
	public void importPlanTerm(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "PlanTerm"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("PlanTerm");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */

					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("PlanTerm");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[1]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.17. 客户类别 19) 文件名： CustClassYYYYMMDD.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * @param dictPath
	 */
	public void importCustClass(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "CustClass"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictBranch> listTBasisDict = new ArrayList<TBasisDictBranch>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("CustClass");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					String orgCode = user[4];
					if ("302".equals(orgCode))
						continue;
					TBasisDictBranch tBasisDictBranch = new TBasisDictBranch();
					TBasisDictBranchId tBasisDictBranchId = new TBasisDictBranchId();
					tBasisDictBranchId.setBusinid(user[0]);
					tBasisDictBranchId.setBusinidBracnch(user[4]);
					tBasisDictBranch.setBusintypeid("CustClass");
					if ("01".equals(user[4]))
						tBasisDictBranch.setDictremark("信用卡");
					if ("02".equals(user[4]))
						tBasisDictBranch.setDictremark("购易贷");
					if ("03".equals(user[4]))
						tBasisDictBranch.setDictremark("诚易贷");
					if ("04".equals(user[4]))
						tBasisDictBranch.setDictremark("购易贷卡");
					tBasisDictBranch.setId(tBasisDictBranchId);
					tBasisDictBranch.setBusinname(user[1]);
					tBasisDictBranch.setDictremark(user[1]);
					tBasisDictBranch.setStatus("1");
					tBasisDictBranch.setUpdatetime(timeuser);
					tBasisDictBranch.setUpdateuser("SYSTEM");
					tBasisDictBranch.setProductCode(user[5]);
					listTBasisDict.add(tBasisDictBranch);
				}
				iimportDictDao.saveTBasisDictBranch(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.19.2.18. 商户所在地区城市 1) 文件名：
	 * StoreDistrict20130503.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * 
	 * @param dictPath
	 */
	public void importStoreDistrict(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "StoreDistrict"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("StoreDistrict");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("StoreDistrict");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[1]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.20.2.19. 商户类型 1) 文件名： StoreType20130503.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * 
	 * 
	 * @param dictPath
	 */
	public void importStoreType(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "StoreType"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("StoreType");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */

					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					tBasisDictId.setBusinid(user[0]);
					tBasisDictId.setBusintypeid("StoreType");
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setDictremark(user[1]);
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.20.2.19. 商户类型 1) 文件名： StoreType20130503.txt，其中YYYYMMDD可变，代表当天日期标识。
	 * 
	 * 
	 * 
	 * @param dictPath
	 */
	public void importSale(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "Sale"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDict> listTBasisDict = new ArrayList<TBasisDict>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("StoreType");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */

					TBasisDict tBasisDict = new TBasisDict();
					TBasisDictId tBasisDictId = new TBasisDictId();
					if ("301".equals(user[5])) {
						tBasisDictId.setBusinid(user[0]);
						tBasisDictId.setBusintypeid("SALE");
						tBasisDict.setId(tBasisDictId);
						tBasisDict.setBusinname(user[1]);
						tBasisDict.setDictremark(user[3]);
						tBasisDict.setStatus("1");
						tBasisDict.setUpdatetime(timeuser);
						tBasisDict.setUpdateuser("SYSTEM");
						listTBasisDict.add(tBasisDict);
					}
				}
				iimportDictDao.saveListDict(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 2.3. 行业信息 1) 文件名： PlanType2013012.txt
	 * 
	 * @param dictPath
	 */
	public void importPlanType(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "PlanType"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		int i = 0;
		// TBasisDict
		List<TBasisDictPlantype> listTBasisDict = new ArrayList<TBasisDictPlantype>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("CompanyMemo");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict) String orgCode = user[3];
					 * if("302".equals(orgCode)) continue; ;
					 */
					TBasisDictPlantype tBasisDict = new TBasisDictPlantype();
					TBasisDictPlantypeId tBasisDictId = new TBasisDictPlantypeId();
					tBasisDictId.setBusintypeid(user[0]);
					tBasisDictId.setBusinidbranch(user[4]);
					tBasisDictId.setOraarea(user[7]);
					tBasisDict.setBusinid(user[0]);
					// CompanyMemo
					tBasisDict.setId(tBasisDictId);
					tBasisDict.setBusinname(user[1]);
					tBasisDict.setLow(user[2]);
					tBasisDict.setFloorlow(user[3]);
					tBasisDict.setCusclas("PlanType");
					tBasisDict.setPanpro(user[5]);
					tBasisDict.setChild_product(user[8]);
					// tBasisDict.setBusintypeid("CompanyMemo");
					tBasisDict.setDictremark("汇率信息");
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					// tBasisDict。setOrgArea(user[4]);
					listTBasisDict.add(tBasisDict);
					i++;

				}
				iimportDictDao.saveListTBasisDictPlantype(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
			}
		} catch (Exception e) {
			logger.info("----------------" + i);
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

	}

	/**
	 * 利率商户 1) 文件名： PlanStore013012.txt，
	 * 
	 * @param dictPath
	 */
	public void importPlanStore(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "PlanStore"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictPlanstore> listTBasisDict = new ArrayList<TBasisDictPlanstore>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("PurchaseCode");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					TBasisDictPlanstore tBasisDict = new TBasisDictPlanstore();
					TBasisDictPlanstoreId tBasisDictPlanstoreId = new TBasisDictPlanstoreId();
					tBasisDictPlanstoreId.setBusinid(user[0]);
					tBasisDict.setBusiname("利率商户");
					tBasisDictPlanstoreId.setBusinidbranch(user[1]);
					tBasisDict.setId(tBasisDictPlanstoreId);
					tBasisDict.setOraarea(user[3]);
					tBasisDict.setDictremark("PlanStore");
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveListDictTBasisDictPlanstore(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 利率商户 1) 文件名： PlanStore013012.txt，
	 * 
	 * @param dictPath
	 */
	public void importStores(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "Stores"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictStores> listTBasisDict = new ArrayList<TBasisDictStores>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					/*
					 * TBasisDict TBasisDict = new TBasisDict();
					 * TBasisDict.setBusintypeid(user[0]);
					 * TBasisDict.setBusinremark(user[1]);
					 * TBasisDict.setBusintypename(user[1]);
					 * TBasisDict.setBusintypeid("PurchaseCode");
					 * TBasisDict.setStatus("1");
					 * TBasisDict.setUpdatetime(timeuser);
					 * TBasisDict.setUpdateuser("SYSTEM");
					 * listTBasisDict.add(TBasisDict);
					 */
					TBasisDictStores tBasisDict = new TBasisDictStores();

					tBasisDict.setBusinid(user[0]);
					tBasisDict.setBusiname("商户");
					tBasisDict.setBusinidbranch(user[2]);
					tBasisDict.setStoresdis(user[1]);
					tBasisDict.setStoretype(user[3]);
					tBasisDict.setOrapo(user[4]);
					tBasisDict.setOraarea(user[6]);
					tBasisDict.setStrooption(user[7]);
					/*
					 * tBasisDict.setBeizhu1(user[8]);
					 * tBasisDict.setBeizhu2(user[9]);
					 * tBasisDict.setBeizhu3(user[10]);
					 * tBasisDict.setBeizhu4(user[11]);
					 * tBasisDict.setBeizhu5(user[12]);
					 */
					tBasisDict.setStorepro("111");
					tBasisDict.setDictremark("Stores");
					tBasisDict.setStatus("1");
					tBasisDict.setUpdatetime(timeuser);
					tBasisDict.setUpdateuser("SYSTEM");
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveLisTBasisDictStores(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 利率商户 1) 文件名： GroupCustomer.txt，
	 * 
	 * @param dictPath
	 */
	public void importIgroup(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "GroupCustomer"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		int i = 0;
		// TBasisDict
		List<TBasisDictIgroup> listTBasisDict = new ArrayList<TBasisDictIgroup>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					TBasisDictIgroup tBasisDict = new TBasisDictIgroup();
					TBasisDictIgroupId tBasisDictid = new TBasisDictIgroupId();
					tBasisDict.setId(tBasisDictid);

					i++;
					tBasisDictid.setGroupNo(user[0]);
					tBasisDictid.setOrgCode(user[8]);
					tBasisDict.setGroupName(user[1].trim());
					tBasisDict.setGroupTuan(user[2]);
					tBasisDict.setGroupYou(user[3]);
					tBasisDict.setGroupDai(user[4]);
					tBasisDict.setGroupPro(user[5]);
					tBasisDict.setGroupDistrict(user[6]);
					// tBasisDict.setOrgCode(user[8]);
					tBasisDict.setUpdateTime(timeuser);
					tBasisDict.setCreateTime(timeuser);
					tBasisDict.setCreateuser("SYSTEM");
					logger.info("============" + user[0] + "----------"
							+ i);
					if (i > 834) {
						iimportDictDao.saveTBasisDictIgroup(tBasisDict);
						logger.info(tBasisDict.toString());
					} else {
						iimportDictDao.saveTBasisDictIgroup(tBasisDict);
					}

					listTBasisDict.add(tBasisDict);
				}
				FileMv.mvFileToDir(dictPath, file);
				String ss = "";
				logger.info(ss);
				// iimportDictDao.saveLisTBasisDictIgroup(listTBasisDict);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("gun");
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

	/**
	 * 商品利率) 文件名： planStoreGood2013012.txt，
	 * 
	 * @param dictPath
	 */
	public void importplanStoreGood(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "planStoreGood"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictPlanstoregood> listTBasisDict = new ArrayList<TBasisDictPlanstoregood>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					TBasisDictPlanstoregood tBasisDict = new TBasisDictPlanstoregood();
					TBasisDictPlanstoregoodId tBasisDictPlanstoregoodId = new TBasisDictPlanstoregoodId();
					tBasisDictPlanstoregoodId.setBusinid(user[0]);
					tBasisDictPlanstoregoodId.setStoretypeid(user[1]);
					tBasisDict.setId(tBasisDictPlanstoregoodId);
					tBasisDict.setArea(user[3]);
					tBasisDict.setDictremark("planStoreGood");
					tBasisDict.setUpdatetime(timeuser);
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveLisTplanStoreGood(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	/**
	 * 商品利率) 文件名： planStoreGood2013012.txt，
	 * 
	 * @param dictPath
	 */
	public void importStoreGoodS(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = dictfilepath + "/" + "storeGoods"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictStoregoogs> listTBasisDict = new ArrayList<TBasisDictStoregoogs>();
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					TBasisDictStoregoogs tBasisDict = new TBasisDictStoregoogs();
					tBasisDict.setBusinid(user[0]);
					tBasisDict.setStoretypeid(user[2]);
					tBasisDict.setStoreid(user[1]);
					tBasisDict.setArea(user[4]);
					tBasisDict.setDictremark("storeGoods");
					tBasisDict.setUpdatetime(timeuser);
					listTBasisDict.add(tBasisDict);
				}
				iimportDictDao.saveLisTBasisDictStoregoogs(listTBasisDict);
				FileMv.mvFileToDir(dictPath, file);
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

	}

	public static String replaceSS(String newStringSS) {
		String changeTOString = newStringSS.trim();// .replace("\t", "");
		return changeTOString;
	}

	public static void main(String[] args) {
		// importIgrouptoo("");
		String ss = "1\t";
		System.out.println(replaceSS(ss));
	}

	public static void importIgrouptoo(String dictPath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = "D://mywork//NJBANK//njcb//20160301" + "/"
				+ "GroupCustomer" + DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		// TBasisDict
		List<TBasisDictIgroup> listTBasisDict = new ArrayList<TBasisDictIgroup>();
		Map<String, String> map = new HashMap<String, String>();
		int i = 0;
		String steo = "";
		int l = 0;
		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {

					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					steo = user[0];
					l++;
					if (steo.equals(map.get(user[0]))) {
						i++;
						System.out.println("---------" + l);
						System.out.println("====" + user[0]);
					}
					TBasisDictIgroup tBasisDict = new TBasisDictIgroup();
					TBasisDictIgroupId tBasisDictid = new TBasisDictIgroupId();
					tBasisDictid.setGroupNo(user[0]);
					map.put(user[0], user[0]);
					tBasisDictid.setOrgCode(user[8]);
					tBasisDict.setId(tBasisDictid);
					tBasisDict.setGroupName(user[1]);
					tBasisDict.setGroupTuan(user[2]);
					tBasisDict.setGroupYou(user[3]);
					tBasisDict.setGroupDai(user[4]);
					tBasisDict.setGroupPro(user[5]);
					tBasisDict.setGroupDistrict(user[6]);
					// tBasisDict.setOrgCode(user[8]);
					tBasisDict.setUpdateTime(timeuser);
					tBasisDict.setCreateTime(timeuser);
					tBasisDict.setCreateuser("SYSTEM");

					listTBasisDict.add(tBasisDict);
					System.out.println(tBasisDict.toString());
				}
				System.out.println("sdsjkfdkjfkd" + i
						+ "0000000000000000000000-------" + l);
				// iimportDictDao.saveLisTBasisDictIgroup(listTBasisDict);
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

	}
	/**
	 * 通过不同文件名字执行 不同跑批
	 * @param filepath
	 */
	public synchronized  void fileNaemeMatch(String filepath){
		Map<String, String> map = new HashMap<String, String>();
		map.put("BillAddress" + DateUtil.batchTime() + ".txt", "1");
		map.put("CompanyMemo" + DateUtil.batchTime() + ".txt", "2");
		map.put("CompanyType" + DateUtil.batchTime() + ".txt", "3");
		map.put("CustClass" + DateUtil.batchTime() + ".txt", "4");
		map.put("EducationLevel" + DateUtil.batchTime() + ".txt", "5");
		map.put("FavorableMessage" + DateUtil.batchTime() + ".txt", "6");
		map.put("GroupCustomer" + DateUtil.batchTime() + ".txt", "7");
		map.put("JobLevel" + DateUtil.batchTime() + ".txt", "8");
		map.put("JobMemo" + DateUtil.batchTime() + ".txt", "9");
		map.put("JobTeacherLevel" + DateUtil.batchTime() + ".txt", "10");
		map.put("MaritalStatus" + DateUtil.batchTime() + ".txt", "11");
		map.put("PlanStore" + DateUtil.batchTime() + ".txt", "12");
		map.put("planStoreGood" + DateUtil.batchTime() + ".txt", "13");
		map.put("PlanTerm" + DateUtil.batchTime() + ".txt", "14");
		map.put("PlanType" + DateUtil.batchTime() + ".txt", "15");
		map.put("PurchaseCode" + DateUtil.batchTime() + ".txt", "16");
		map.put("Sale" + DateUtil.batchTime() + ".txt", "17");
		map.put("SpecialCost" + DateUtil.batchTime() + ".txt", "18");
		map.put("StoreDistrict" + DateUtil.batchTime() + ".txt", "19");
		map.put("storeGoods" + DateUtil.batchTime() + ".txt", "20");
		map.put("Stores" + DateUtil.batchTime() + ".txt", "21");
		map.put("StoreType" + DateUtil.batchTime() + ".txt", "22");
		File file = new File(filepath);
		File[] listfile = file.listFiles();
		for (File file2 : listfile) {
			if (file2.isFile()) {
				String filename = file2.getName();
				logger.info(filename+"============");
				if (map.containsKey(file2.getName())) {
					int l = Integer.parseInt(map.get(file2.getName())
							.toString());
					switch (l) {
					case 1:
						importBillAddress(dictfilepath);
						logger.info("执行了"+ "importBillAddress");
						break;
					case 2:
						importCompanyMemo(dictfilepath);
						logger.info("执行了"+ "importCompanyMemo");
						break;
					case 3:
						importCompanyType(dictfilepath);
						logger.info("执行了"+ "importCompanyType");
						break;
					case 4:
						importCustClass(dictfilepath);
						logger.info("执行了"+ "importCustClass");
						break;
					case 5:
						importEducationLevel(dictfilepath);
						logger.info("执行了"+ "importEducationLevel");
						break;
					case 6:
						importFavorleMessageab(dictfilepath);
						logger.info("执行了"+ "importFavorleMessageab");
						break;
					case 7:
						importIgroup(dictfilepath);
						logger.info("执行了"+ "importIgroup");
						break;
					case 8:
						importJobLevel(dictfilepath);
						logger.info("执行了"+ "importJobLevel");
						break;
					case 9:
						importJobMemo(dictfilepath);
						logger.info("执行了"+ "importJobMemo");
						break;
					case 10:
						importJobTeacherLevel(dictfilepath);
						logger.info("执行了"+ "importJobTeacherLevel");
						break;
					case 11:
						importMaritalStatus(dictfilepath);
						logger.info("执行了"+ "importMaritalStatus");
						break;
					case 12:
						importPlanStore(dictfilepath);
						logger.info("执行了"+ "importPlanStore");
						break;
					case 13:
						importplanStoreGood(dictfilepath);
						logger.info("执行了"+ "importplanStoreGood");
						break;
					case 14:
						importPlanTerm(dictfilepath);
						logger.info("执行了"+ "importPlanTerm");
						break;
					case 15:
						importPlanType(dictfilepath);
						logger.info("执行了"+ "importPlanType");
						break;
					case 16:
						importPurchaseCode(dictfilepath);
						logger.info("执行了"+ "importPurchaseCode");
						break;
					case 17:
						importSale(dictfilepath);
						logger.info("执行了"+ "importSale");
						break;
					case 18:
						importSpecialCost(dictfilepath);
						logger.info("执行了"+ "importSpecialCost");
						break;
					case 19:
						importStoreDistrict(dictfilepath);
						logger.info("执行了"+ "importStoreDistrict");
						break;
					case 20:
						importStoreGoodS(dictfilepath);
						logger.info("执行了"+ "importStoreGoodS");
						
						break;
					case 21:
						importStores(dictfilepath);
						logger.info("执行了"+ "importStores");
						break;
					case 22:
						importStoreType(dictfilepath);
						logger.info("执行了"+ "importStoreType");
						break;
					case 23:
						break;
					default:
						logger.info("执行了HELL WORlD!");
						break;
					}
				}
			}
		}
	}

}

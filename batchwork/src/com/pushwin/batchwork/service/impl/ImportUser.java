package com.pushwin.batchwork.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushwin.batchwork.common.SpringProperty;
import com.pushwin.batchwork.dao.IImportUser;
import com.pushwin.batchwork.model.TBasisBusUser;
import com.pushwin.batchwork.model.TBasisOrg;
import com.pushwin.batchwork.model.TBasisUser;
import com.pushwin.batchwork.utils.DateUtil;
import com.pushwin.batchwork.utils.MD5Util;

@Service("importUserService")
public class ImportUser {
	@Autowired
	private IImportUser iimportUser;

	protected Logger logger = LoggerFactory.getLogger(ImportUser.class);

	private String importUserFolder = (String) SpringProperty.props
			.get("importuserfile");

	private String initpwd = (String) SpringProperty.props
			.get("initpwd");

	
	@Transactional
	public synchronized  void importUser() {
	/*	String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = importUserFolder + "/" + "User" + DateUtil.batchTime()
				+ ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;*/
		List<TBasisUser> listuser = new ArrayList<TBasisUser>();
		System.err.println("-----------------------");
		TBasisOrg org = null;
		try {
			if (iimportUser.getUserFlag()) {
				Map<String,TBasisUser> mapTBasisUser= iimportUser.queryUserAll("");
				List<TBasisBusUser> listuserBasi = iimportUser.getUserAllBus();
				for (TBasisBusUser tBasisUser : listuserBasi) {
		
					//String[] user = content.split("\\|");
					TBasisUser baisuserc = mapTBasisUser.get(tBasisUser.getUserCode());
					if (null != baisuserc) {
						baisuserc.setUserCode(tBasisUser.getUserCode());
						baisuserc.setUserName(tBasisUser.getUserName());
						baisuserc.setUserPhone(tBasisUser.getUserPhone());
						baisuserc.setIsDisable("false");
						baisuserc.setCa(tBasisUser.getCa());
						baisuserc.setUserArea(tBasisUser.getUserArea());
						baisuserc.setUserCity(tBasisUser.getUserCity());
						
						if( "301".equals(tBasisUser.getUserbeizhu2()) && !"555".equals(tBasisUser.getUserArea()) ){
							baisuserc.setUserbeizhu1("NJCB");
						}else if( "301".equals(tBasisUser.getUserbeizhu2()) && "555".equals(tBasisUser.getUserArea()) ){
							baisuserc.setUserbeizhu1("KSLC");
						}else if( "302".equals(tBasisUser.getUserbeizhu2()) ){
							baisuserc.setUserbeizhu1("RZCB");
						}
						
//						org = baisuserc.getTBasisOrg(); 
						org = iimportUser.queryOrg(tBasisUser.getOrgId());
						if (null != org) {
							baisuserc.setTBasisOrg(org);
							listuser.add(baisuserc);
						} else {
							logger.info("非法倒入用户" + tBasisUser.getUserCode());
							continue;
						}

					} else {
						TBasisUser baisuser = new TBasisUser();
						baisuser.setUserCode(tBasisUser.getUserCode());
						baisuser.setUserName(tBasisUser.getUserName());
						baisuser.setUserPhone(tBasisUser.getUserPhone());
						baisuser.setIsDisable("false");
						baisuser.setUserPwd(MD5Util.md5(initpwd));
						baisuser.setCa(tBasisUser.getCa());
						baisuser.setUserArea(tBasisUser.getUserArea());
						baisuser.setUserCity(tBasisUser.getUserCity());
						
						if( "301".equals(tBasisUser.getUserbeizhu2()) && !"555".equals(tBasisUser.getUserArea()) ){
							baisuser.setUserbeizhu1("NJCB");
						}else if( "301".equals(tBasisUser.getUserbeizhu2()) && "555".equals(tBasisUser.getUserArea()) ){
							baisuser.setUserbeizhu1("KSLC");
						}else if( "302".equals(tBasisUser.getUserbeizhu2()) ){
							baisuser.setUserbeizhu1("RZCB");
						}
						
						org = iimportUser.queryOrg(tBasisUser.getOrgId());
						if (null != org) {
							baisuser.setTBasisOrg(org);
							listuser.add(baisuser);
						} else {
							logger.info("非法倒入用户" + tBasisUser.getUserCode());
							continue;
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		iimportUser.saveUser(listuser);
	}



	@Transactional
	public synchronized  void importUserNew() {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = importUserFolder + "/" + "User" + DateUtil.batchTime()
				+ ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		List<TBasisUser> listuser = new ArrayList<TBasisUser>();
		TBasisOrg org = null;
		try {
			if (file.exists()) {
              Map<String, TBasisUser> mapTBasisUser= iimportUser.queryUserAll("");
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";

				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					TBasisUser baisuserc = mapTBasisUser.get(user[0]);
					if (null != baisuserc) {
						baisuserc.setUserCode(user[0]);
						baisuserc.setUserName(user[1]);
						baisuserc.setUserPhone(user[2]);
						baisuserc.setIsDisable("false");
						baisuserc.setCa(user[3]);
						baisuserc.setUserArea(user[5]);
						baisuserc.setUserCity(user[6]);
						org = baisuserc.getTBasisOrg();
						if (null != org) {
							baisuserc.setTBasisOrg(org);
							listuser.add(baisuserc);
						} else {
							logger.info("非法倒入用户" + user[0]);
							continue;
						}

					} else {
						TBasisUser baisuser = new TBasisUser();
						baisuser.setUserCode(user[0]);
						baisuser.setUserName(user[1]);
						baisuser.setUserPhone(user[2]);
						baisuser.setIsDisable("false");
						baisuser.setUserPwd(MD5Util.md5(initpwd));
						baisuser.setCa(user[3]);
						baisuser.setUserArea(user[5]);
						baisuser.setUserCity(user[6]);
						org = iimportUser.queryOrg(user[7]);
						if (null != org) {
							baisuser.setTBasisOrg(org);
							listuser.add(baisuser);
						} else {
							logger.info("非法倒入用户" + user[0]);
							continue;
						}

					}
				}
				
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
		iimportUser.saveUser(listuser);
	}

	public static void main(String[] args) {
		String content = "0600601001|高丽霞|18668402920||SALES|513|301|21300|C001|2013-04-27 10:52:11";
		String[] user = content.split("\\|");
		for (String ss : user) {
			System.out.println(ss);

		}
		
		System.out.println(MD5Util.md5("123abc"));
	}
}

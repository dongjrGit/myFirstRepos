package com.pushwin.batchwork.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushwin.batchwork.common.SpringProperty;
import com.pushwin.batchwork.dao.IImportProvinceCityDistinctDao;
import com.pushwin.batchwork.model.TBasisCity;
import com.pushwin.batchwork.model.TBasisDistrict;
import com.pushwin.batchwork.model.TBasisProvinces;
import com.pushwin.batchwork.utils.DateUtil;

@Service("importProvinceCityDistinct")
public class ImportProvinceCityDistinct {

	@Autowired
	private IImportProvinceCityDistinctDao iimportProvinceCityDistinctDao;

	protected Logger logger = LoggerFactory
			.getLogger(ImportProvinceCityDistinct.class);

	private String importProvince = (String) SpringProperty.props
			.get("importProvince");

	@Transactional
	public void importProvince() {
		@SuppressWarnings("rawtypes")
		Map mapprovice = getProvice(importProvince);
		@SuppressWarnings("rawtypes")
		Map mapcitu = getTBasisCity(importProvince, mapprovice);
		getDistinct(importProvince, mapcitu);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getProvice(String importProvincepath) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = importProvince + "/" + "CityDistrict"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		List<TBasisProvinces> listprovince = new ArrayList<TBasisProvinces>();

		Map<String, TBasisProvinces> map = new HashedMap();
		try {
			if (file.exists()) {
				iimportProvinceCityDistinctDao.deleteProvinceCityDistinct();
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					// content = new String(st.getBytes(),"utf-8");
					// content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					TBasisProvinces provice = new TBasisProvinces();
					if ("-1".equals(user[2]) && "301".equals(user[4])) {
						// provice.setOrgId();
						provice.setProvinceId(user[0]);
						provice.setProvinceName(user[1]);
						provice.setOrgId(user[2]);
						Calendar cal = Calendar.getInstance();
						provice.setUpdateTime(new Timestamp(cal
								.getTimeInMillis()));
						provice.setUpdateUser("SYSTEM");
						listprovince.add(provice);
						map.put(user[0], provice);
					} else {
						continue;
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
		 iimportProvinceCityDistinctDao.SaveProvince(listprovince);
		return map;

	}

	@SuppressWarnings("rawtypes")
	public void getDistinct(String importProvincepath, Map map) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = importProvince + "/" + "CityDistrict"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;

		List<TBasisDistrict> listTBasisDistrict = new ArrayList<TBasisDistrict>();
		try {
			if (file.exists()) {
				// iimportProvinceCityDistinctDao.deleteProvinceCityDistinct();
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					content = br.readLine();
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					TBasisDistrict tbasisDistrict = new TBasisDistrict();
					if (4 == (user[2]).length() && "301".equals(user[4])) {
						// provice.setOrgId();
						tbasisDistrict.setDistrictId(user[0]);
						tbasisDistrict.setDistrictName(user[1]);
						TBasisCity distinct = (TBasisCity) map.get(user[2]);
						tbasisDistrict.setTBasisCity(distinct);
						Calendar cal = Calendar.getInstance();
						tbasisDistrict.setUpdateTime(new Timestamp(cal
								.getTimeInMillis()));
						tbasisDistrict.setUpdateUser("SYSTEM");
						listTBasisDistrict.add(tbasisDistrict);
					} else {
						continue;
					}
				}
			}
		} catch (Exception e) {

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
		iimportProvinceCityDistinctDao.SaveDistinct(listTBasisDistrict);
		// return listTBasisDistrict;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getTBasisCity(String importProvincepath, Map map) {
		String timeuser = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String importuserfilename = importProvince + "/" + "CityDistrict"
				+ DateUtil.batchTime() + ".txt";
		File file = new File(importuserfilename);
		FileReader fr = null;
		BufferedReader br = null;
		List<TBasisCity> listprovince = new ArrayList<TBasisCity>();
		Map<String, TBasisCity> mapcity = new HashedMap();
		try {
			if (file.exists()) {

				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String content = "";
				while (content != null) {
					String st = br.readLine();
					content = new String(st.getBytes(), "GBK");
					if ("".equals(content) || null == content)
						continue;
					String[] user = content.split("\\|");
					if (2 == (user[2]).length()&&false==("-1".equals(user[2])) && "301".equals(user[4])){
					TBasisCity tBasisCity = new TBasisCity();
					// provice.setOrgId();
					tBasisCity.setCityId(user[0]);
					tBasisCity.setCityName(user[1]);
					TBasisProvinces tBasisProvinces = (TBasisProvinces) map
							.get(user[2]);
					tBasisCity.setTBasisProvinces(tBasisProvinces);
					Calendar cal = Calendar.getInstance();
					tBasisCity.setUpdateTime(new Timestamp(cal
							.getTimeInMillis()));
					tBasisCity.setUpdateUser("SYSTEM");
					listprovince.add(tBasisCity);
					mapcity.put(user[0], tBasisCity);
					}else{
						continue;
					}
				}
			
			}
		} catch (Exception e) {

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
		iimportProvinceCityDistinctDao.SaveCity(listprovince);
		return mapcity;

	}

	/**
	 * 
	 * @param ss
	 *            待转换文本
	 * @param code
	 *            编码
	 * @return 转换后的文本
	 */
	static String changeToGBK(String ss, String code) {
		String temp = null;
		try {
			temp = new String(ss.getBytes(), code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	public static void main(String[] args) {
		String ss = "11|北京市|-1|2016-02-25 18:07:55|301";
		String[] user = ss.split("\\|");
		String sq = new String("GBK");
		for (String s1 : user) {
			System.out.println(s1);
		}
	}

}

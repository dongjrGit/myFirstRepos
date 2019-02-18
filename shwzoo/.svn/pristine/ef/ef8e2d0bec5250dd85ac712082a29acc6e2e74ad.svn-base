/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.CitySpDto;
import com.yinlian.wssc.web.po.City;


/**
 * 城市的业务类接口
 * 
 * @author Administrator
 * @version $Id: CityServcie.java, v 0.1 2016年3月10日 下午8:33:56 Administrator Exp
 *          $
 */
public interface CityServcie {

	/**
	 * 
	 * @param code
	 *            省的code
	 * @return
	 */
	List<City> selectByProvinceCode(String code) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */

	int deleteCityByCode(String id) throws Exception;

	int updateCityByCode(String name, String code) throws Exception;

	/**
	 * 根据code查询城市信息
	 * 
	 * @param code
	 * @return
	 */
	City queryByCode(String code) throws Exception;

	City selectByName(String name) throws Exception;

	/**
	 * 添加城市
	 * 
	 * @param name
	 * @param code
	 * @param fcode
	 * @return
	 * @throws Exception
	 */
	int add(String name, String code, String fcode) throws Exception;

	/**
	 * 查询所有城市
	 * 
	 * @return
	 * @throws Exception
	 */
	List<City> query() throws Exception;

	List selectCity(String parentCode) throws Exception;

	City selectCityByName(String name, String code) throws Exception;

	List<CitySpDto> getCityAllList() throws Exception;
}

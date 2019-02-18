/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.Api_ProvinceDto;
import com.yinlian.pc.dto.ProvinceDto;
import com.yinlian.pc.dto.TopicBig;
import com.yinlian.wssc.web.po.Province;

/**
 * 
 * @author Administrator
 * @version $Id: ProvinceServcice.java, v 0.1 2016年3月10日 下午8:31:26 Administrator Exp $
 */
public interface ProvinceServcice {

    /**
     * 
     * @return
     */
    List<Province> selectAll() throws Exception;

    /**
     * 查询所有的省包含下面的城市集合
     * @return
     * @throws Exception
     */
    List<Province> selectList() throws Exception;

    int deleteProvinceByCode(String id) throws Exception;

    int updateProvinceByCode(String name, String code) throws Exception;

    /**
     * 通过code 查询出省信息
     * @param code
     * @return
     */
    Province queryByCode(String code) throws Exception;
    /**
     * 根据code查询省
     * @param code
     * @return
     * @throws Exception
     */
	List<Province> selectProvinceByCode(String code) throws Exception;
	/**
	 * 根据名字查code
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Province selectProvinceByName(String name) throws Exception;

	/**
	 * 添加省
	 * 
	 * @param name
	 * @param code
	 * @return
	 * @throws Exception
	 */
	int add(String name, String code) throws Exception;
	/**
	 * 查询所有的省
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Province> query() throws Exception;

	List selectPro() throws Exception;

	/**
	 * 获取全部大区
	 * @return
	 * @throws Exception
	 */
	List<TopicBig> findBigAll() throws Exception;

	Province findById(Integer provinceid)throws Exception;
	
	
	List<ProvinceDto> findProBigAll()throws Exception;
	/**
     * 获取所有省市区信息 
     * @return
     */
	List<Api_ProvinceDto> getplaces() throws Exception;
}

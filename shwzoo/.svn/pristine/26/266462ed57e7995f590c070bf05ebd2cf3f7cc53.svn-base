package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaHotCity;
import com.yinlian.wssc.web.po.Hotcity;

public interface HotCityService {

	/**
	 * 获取热门城市列表
	 * 
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean getHotCity(CriteriaHotCity criteria, Integer index, Integer size) throws Exception;

	/**
	 * 添加热门城市
	 * 
	 * @param hotcity
	 * @return
	 * @throws Exception
	 */
	int insertHotCity(Hotcity hotcity) throws Exception;

	/**
	 * 修改热门城市
	 * 
	 * @param hotcity
	 * @return
	 * @throws Exception
	 */
	int updateHotCity(Hotcity hotcity) throws Exception;

	/**
	 * 删除热门城市
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteHotCity(Integer id) throws Exception;

	/**
	 * 根据id获取热门城市
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Hotcity selHotCityById(Integer id) throws Exception;

	List<Hotcity> getAllList() throws Exception;

	/**
	 * 根据城市code查询热门城市
	 * 
	 * @return
	 * @throws Exception
	 */
	Hotcity selCity(String code) throws Exception;

}

package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.GroupbuyClass;
import com.yinlian.wssc.web.util.CriteriaPage;

public interface GroupByClassService {

	/**
	 * 获取团购类型列表
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	PageBean selectByCriteria(CriteriaPage criteria, Integer pc, Integer ps) throws Exception;

	/**
	 * 删除团购类型
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delById(Integer id) throws Exception;

	/**
	 * 添加团购类型
	 * 
	 * @param gbc
	 * @return
	 * @throws Exception
	 */
	int addTglx(GroupbuyClass gbc) throws Exception;

	/**
	 * 根据id查询团购类型
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	GroupbuyClass selById(Integer id) throws Exception;

	/**
	 * 删除团购类型
	 * 
	 * @param gbc
	 * @return
	 * @throws Exception
	 */
	int updateById(GroupbuyClass gbc) throws Exception;

	List<GroupbuyClass> getAllList() throws Exception;

}

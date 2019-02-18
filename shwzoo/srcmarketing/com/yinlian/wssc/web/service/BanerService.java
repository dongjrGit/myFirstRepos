package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Baner;
import com.yinlian.wssc.web.po.CriteriaBaner;

public interface BanerService {

	/**
	 * 添加发现baner
	 * 
	 * @param baner
	 * @return
	 * @throws Exception
	 */
	int insertBaner(Baner baner) throws Exception;

	/**
	 * 修改发现baner
	 * 
	 * @param baner
	 * @return
	 * @throws Exception
	 */
	int updateBanerById(Baner baner) throws Exception;

	/**
	 * 根据id查询发现baner
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Baner selBanerById(Integer id) throws Exception;

	/**
	 * 获取发现baner列表
	 * 
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean selBaner(CriteriaBaner criteria, Integer index, Integer size) throws Exception;

	/**
	 * 删除发现baner
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delBaner(Integer id) throws Exception;

	List<Baner> getList()throws Exception;

}

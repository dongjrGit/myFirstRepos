package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaIntegra;
import com.yinlian.wssc.web.po.IntegraproductWithBLOBs;

public interface IntegraproductService {

	/**
	 * 添加积分商品
	 * 
	 * @param iwb
	 * @return
	 * @throws Exception
	 */
	int insertintepro(IntegraproductWithBLOBs iwb) throws Exception;

	/**
	 * 修改积分商品
	 * 
	 * @param iwb
	 * @return
	 * @throws Exception
	 */
	int updateintepro(IntegraproductWithBLOBs iwb) throws Exception;

	/**
	 * 根据id删除积分商品
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delById(Integer id) throws Exception;

	/**
	 * 获取积分商品列表
	 * 
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean getList(CriteriaIntegra criteria, Integer index, Integer size) throws Exception;

	/**
	 * 根据id获取积分商品
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	IntegraproductWithBLOBs getListById(Integer id) throws Exception;

}

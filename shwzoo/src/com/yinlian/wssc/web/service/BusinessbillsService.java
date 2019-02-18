/*
 * @(#) BusinessbillsService.java 2016年8月29日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.search.BusinessBillsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Businessbills;
import com.yinlian.wssc.web.po.OrderBills;
import com.yinlian.wssc.web.util.OrderCriteria;

/**
 * 对账单
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年8月29日
 */
public interface BusinessbillsService {

	
	/**
	 * 获取商户汇总对账单
	 * 
	 * @author kh.wang
	 * @since 2016年8月29日
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 */
	public PageBean getBusinessBills(BusinessBillsCriteria criteria,Integer page, Integer size)throws Exception;
	
	//获取对账单订单列表
	public PageBean getBillOrdersList(OrderCriteria criteria,Integer page, Integer size)throws Exception;
	
	public List<Businessbills> findAll(OrderCriteria criteria)throws Exception;;
	
	/// <summary>
    /// 生成商户对账单
    /// </summary>
	//public void generateBills() throws Exception;

	/**
	 * 根据ID查询
	 * 
	 * @author kh.wang
	 * @since 2016年11月1日
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Businessbills  findById(Integer id)throws Exception;;

	/**
	 * 修改
	 * 
	 * @author kh.wang
	 * @since 2016年11月1日
	 * @param businessbills
	 * @return
	 * @throws Exception
	 */
	public Integer updateById(Businessbills businessbills)throws Exception;
	
	/**
	 * 添加对账记录
	 * @return
	 * @throws Exception
	 */
	public Integer  addBusBill()throws Exception;

	public PageBean getOrderBills(BusinessBillsCriteria criteria, int page, int size)throws Exception;

	/**
	 * 对账订单列表
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public PageBean getBillsOrderList(BusinessBillsCriteria criteria, int page, int size)throws Exception;

	public OrderBills selectById(Integer id)throws Exception;

	public int updateBills(OrderBills bills)throws Exception;
    
}

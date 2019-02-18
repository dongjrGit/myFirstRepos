/*
 * @(#) BusinessbillsController.java 2016年8月29日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OrderDetailStatusEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.BusinessBillsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.interceptor.SameUrlData;
import com.yinlian.wssc.web.po.OrderBills;
import com.yinlian.wssc.web.service.BusinessbillsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/platform/businessbillsAjax")
public class BusinessbillsAjaxController {
	
	@Autowired
	BusinessbillsService businessbillsService;
	@Autowired
	OrderService orderService;
	
 
	/**
	 * 对账列表
	 * @param index
	 * @param size
	 * @param criteria
	 * @return
	 */
	@RequestMapping("/bulllist")
	public ReusltItem bulllist(String index,String size,String status,String begin,String end){
		ReusltItem item = new ReusltItem();
		try {
			int Page = StringUtilsEX.ToInt(index);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			BusinessBillsCriteria criteria=new BusinessBillsCriteria();
			if (StringUtilsEX.ToInt(status) >= 0) {
				criteria.setStatus(StringUtilsEX.ToInt(status));
			}
			criteria.setBillstart(StringUtilsEX.ToShortDate(begin));
			criteria.setBillsend(StringUtilsEX.ToShortDate(end));
			criteria.setOrderByClause("orderdate");
			criteria.setSort("desc");
			PageBean pageBean=businessbillsService.getOrderBills(criteria, Page, Size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Businessbills,"获取对账列表错误：", e,
					"/order/bulllist");
		}
		return item;
	}
	
	/**
	 * 修改对账单状态
	 * @param billId
	 * @return
	 */
	@SameUrlData
	@RequestMapping("/setBbillsStatus")
	public ReusltItem setBbillsStatus(String billId) {
		ReusltItem item = new ReusltItem();
		try {
			OrderBills bills=businessbillsService.selectById(StringUtilsEX.ToInt(billId));
			if(bills!=null){
				bills.setStatus(1);
			}
			int i=businessbillsService.updateBills(bills);
			if(i>0){
				item.setDesc("处理成功");
				item.setCode(0);
			}else{
				item.setDesc("处理失败");
				item.setCode(-200);
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Businessbills,"修改对账单状态错误：", e,
					"/order/setBbillsStatus");
		}
		return item;
	}
	
	/**
	 * 对账订单列表
	 * @param shopid
	 * @param orderdate
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getBbillsorderList")
	public ReusltItem getBbillsorderList(String shopid, String orderdate,
			String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			BusinessBillsCriteria criteria=new BusinessBillsCriteria();
			criteria.setShopid(StringUtilsEX.ToInt(shopid));
			criteria.setOrderdate(orderdate);
			criteria.setIsfree(0);
			criteria.setStatus(OrderDetailStatusEnum.已使用.getValue());
			
			criteria.setOrderByClause("o.AddOrderDate");
			criteria.setSort("desc");
			PageBean pageBean =businessbillsService.getBillsOrderList(criteria, Page, Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Businessbills,"获取对账单订单列表错误：", e,
					"/order/getBbillsorderList");
		}
		return item;
	}
	
}

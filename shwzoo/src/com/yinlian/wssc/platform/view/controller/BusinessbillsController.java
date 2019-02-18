/*
 * @(#) BusinessbillsController.java 2016年8月29日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.platform.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.service.BusinessbillsService;
import com.yinlian.wssc.web.service.InvoiceService;
import com.yinlian.wssc.web.service.OrderService;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/businessbills")
public class BusinessbillsController {

	@Autowired
	OrderService orderSerivce;
	
	@Autowired
	BusinessbillsService businessbillsService;
	@Autowired
	InvoiceService invoiceService;
	
	@RequestMapping("/index")
	public String index(Integer status){
		return "platform/ddgl/businessbills";
	}
	
	@RequestMapping("/orderlist")
	public String order(String status,Integer shopId,String dateb,String datee,Model model){
		model.addAttribute("shopId", shopId);
		model.addAttribute("status", status);
		model.addAttribute("dateb", dateb);
		model.addAttribute("datee", datee);
		return "platform/ddgl/bill_ddgl_dpdd";
	}
	
	
	@RequestMapping("/businessbillsedit")
	public String businessbillsedit(Integer id,Integer status,Model model){
		model.addAttribute("id", id);
		model.addAttribute("status", status);
		return "platform/ddgl/businessbillsedit";
	}
	@RequestMapping("/billddgldetail")
	public String billddgldetail(Integer shopId,Integer id,String status,String dateb,String datee,Model model){
		try {
			model.addAttribute("order", orderSerivce.getorderdetail(id));
			model.addAttribute("Invoice", invoiceService.selectByOrderId(id));
			model.addAttribute("shopId",shopId);
			model.addAttribute("status",status);
			model.addAttribute("dateb", dateb);
			model.addAttribute("datee",datee);
			return "platform/ddgl/bill_ddgl_detail";
		} catch (Exception e) {
			e.printStackTrace();
			 LogHandle.error(LogType.Login, "查询对账订单详情异常：" , e, "/platform/businessbills/orderDetail");

				return "platform/index";
		}
	}
	
	/**
	 * 对账列表页
	 * 
	 * @param status
	 * @return
	 */
	@RequestMapping("/billlist")
	public String billlist(Integer status,Model model){
		model.addAttribute("status", status);
		return "platform/ddgl/businessbills";
	}
	
	/**
	 * 对账订单列表
	 * @param model
	 * @param shopid
	 * @param orderdate
	 * @return
	 */
	@RequestMapping("/bbillsOrderList")
	public String bbillsOrderList(Model model,String shopid,String orderdate,String status){
		model.addAttribute("shopid", shopid);
		model.addAttribute("orderdate", orderdate);
		model.addAttribute("status", status);
		return "/platform/ddgl/bbillsOrderList";
	}
	
}

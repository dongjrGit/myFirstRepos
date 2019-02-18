/*
 * @(#) BusinessBillsCriteria.java 2016年8月29日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.search;

import java.util.Date;

import com.yinlian.wssc.web.util.Criteria;

public class BusinessBillsCriteria extends Criteria {

	/**
	 * 起始时间
	 */
	private String begin;
	
	/**
	 * 截至时间
	 */
	private String end;
	
	private Integer status;
	
	private  Date   billstart;
	
	private  Date   billsend;
	
	private  String  orderdate;
	
	private  Integer  shopid;
	
	private  Integer  isfree;
	
	public Integer isIsfree() {
		return isfree;
	}

	public void setIsfree(Integer isfree) {
		this.isfree = isfree;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public Date getBillstart() {
		return billstart;
	}

	public void setBillstart(Date billstart) {
		this.billstart = billstart;
	}

	public Date getBillsend() {
		return billsend;
	}

	public void setBillsend(Date billsend) {
		this.billsend = billsend;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}

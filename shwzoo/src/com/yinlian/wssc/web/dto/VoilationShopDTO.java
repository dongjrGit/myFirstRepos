/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.po.Shopviolation;
import com.yinlian.wssc.web.util.DateUtil;


public class VoilationShopDTO extends Shopviolation {
	
	private  String username;
	
	private  String  shopname;
	
	private  Integer type;
	
	private  String  description;
	
	private  String  result;
	
	private  Date    createtime;
	
	private  String    createtimetr;
	
	private  Integer id;
	
	private  Integer  userid;
	
	private  Integer  shopid;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatetimetr() {
		this.createtimetr = DateUtil.datePattren(createtime);
		return createtimetr;
	}

	public void setCreatetimetr(String createtimetr) {
		this.createtimetr = createtimetr;
	}
	
}

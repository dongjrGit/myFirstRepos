package com.yinlian.wssc.web.util;

import java.util.Date;


public class CriteriaSellerSh extends Criteria{

	private String code;
	
	private Date datef;  //开始时间
	
	private Date datet;  //结束时间
	
	private Integer type;
	
	private Integer shopid;
	
	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDatef() {
		return datef;
	}

	public void setDatef(Date datef) {
		this.datef = datef;
	}

	public Date getDatet() {
		return datet;
	}

	public void setDatet(Date datet) {
		this.datet = datet;
	}
}

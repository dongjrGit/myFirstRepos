package com.yinlian.wssc.web.util;

import java.util.Date;

public class CriteriaOrderDetails extends Criteria {
	
	private Integer shuid;
	/**
	 * 下单开始时间
	 */
	private Date addbegin;
	/**
	 * 下单结束时间
	 */
	private Date addend;
	
	public Integer getShuid() {
		return shuid;
	}
	public void setShuid(Integer shuid) {
		this.shuid = shuid;
	}
	public Date getAddbegin() {
		return addbegin;
	}
	public void setAddbegin(Date addbegin) {
		this.addbegin = addbegin;
	}
	public Date getAddend() {
		return addend;
	}
	public void setAddend(Date addend) {
		this.addend = addend;
	}
	
	
}

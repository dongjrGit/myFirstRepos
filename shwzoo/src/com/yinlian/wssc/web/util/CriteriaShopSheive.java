/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;


/**
 * 
 * @author Administrator
 * @version $Id: CriteriaShop.java, v 0.1 2016年3月10日 上午9:50:36 Administrator Exp
 *          $
 */
public class CriteriaShopSheive extends Criteria {

	private String startTime;
	
	private String endTime;
	
	private Integer shopid;
	
	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}

package com.yinlian.wssc.web.util;

import java.util.Date;

public class CriteriaCouponDetail extends Criteria {
	
	/**
	 * 优惠卷编号
	 */
	private String num;
	/**
	 * 下单开始时间
	 */
	private Date addbegin;
	/**
	 * 下单结束时间
	 */
	private Date addend;
	
	private Integer spikeType;
	
	private Integer activityType;

	
	public Integer getSpikeType() {
		return spikeType;
	}

	public void setSpikeType(Integer spikeType) {
		this.spikeType = spikeType;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
}

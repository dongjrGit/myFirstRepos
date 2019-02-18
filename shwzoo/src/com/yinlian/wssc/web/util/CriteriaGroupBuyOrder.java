package com.yinlian.wssc.web.util;

import java.util.Date;

public class CriteriaGroupBuyOrder extends Criteria{
	/**
	 * 状态	 
	 */
	private Integer status;
	/**
	 * 买家id
	 */
	private Integer buyid;
	/**
	 * 订单编号
	 */
	private String ordercode;
	/**
	 * 开始时间
	 */
	private Date begin;
	/**
	 * 结束时间
	 */
	private Date end;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getBuyid() {
		return buyid;
	}
	public void setBuyid(Integer buyid) {
		this.buyid = buyid;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
}

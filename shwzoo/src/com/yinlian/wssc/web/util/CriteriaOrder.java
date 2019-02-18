package com.yinlian.wssc.web.util;

import java.util.Date;

public class CriteriaOrder extends Criteria {
	/**
	 * 是否直营
	 */
	private int isowned;
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
	 * 下单开始时间
	 */
	private Date addbegin;
	/**
	 * 下单结束时间
	 */
	private Date addend;
	/**
	 * 物流编号
	 */
	private String logisticscode;
	/**
	 * 发货开始时间
	 */
	private Date sendbegin;
	/**
	 * 发货结束时间
	 */
	private Date sendend;
	
	/**
	 * 发票类型
	 */
	private Integer type;
	
	/**
	 * 状态集合
	 */
	private String statuss;
	
	/**
	 * 确认收货时间开始
	 */
	private String qrbegintime;
	
	/**
	 * 确认收货时间结束
	 */
	private String qrendtime;
	
	private Integer state;
	
	private Integer detailstatus;
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getDetailstatus() {
		return detailstatus;
	}
	public void setDetailstatus(Integer detailstatus) {
		this.detailstatus = detailstatus;
	}
	public String getLogisticscode() {
		return logisticscode;
	}
	public void setLogisticscode(String logisticscode) {
		this.logisticscode = logisticscode;
	}
	public Date getSendbegin() {
		return sendbegin;
	}
	public void setSendbegin(Date sendbegin) {
		this.sendbegin = sendbegin;
	}
	public Date getSendend() {
		return sendend;
	}
	public void setSendend(Date sendend) {
		this.sendend = sendend;
	}
	public int getIsowned() {
		return isowned;
	}
	public void setIsowned(int isowned) {
		this.isowned = isowned;
	}
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
	public String getStatuss() {
		return statuss;
	}
	public void setStatuss(String statuss) {
		this.statuss = statuss;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getQrbegintime() {
		return qrbegintime;
	}
	public void setQrbegintime(String qrbegintime) {
		this.qrbegintime = qrbegintime;
	}
	public String getQrendtime() {
		return qrendtime;
	}
	public void setQrendtime(String qrendtime) {
		this.qrendtime = qrendtime;
	}
	
	
}

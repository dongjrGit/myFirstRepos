package com.yinlian.pc.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.util.DateUtil;

/**
 * pc端会员中心订单信息
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("unused")
public class MemberOrderDto {

	//订单组编号
	private String groupcode;
	// 收货人
	private String consignee;
	// 收货人地址
	private String address;
	// 收货人电话
	private String telPhone;
	// 支付方式
	private Integer payType;
	// 订单总金额
	private BigDecimal price;
	// 订单创建时间
	private Date orderDate;
	// 支付时间
	private Date payDate;
	
	private String orderDateStr;
	private String payDateStr;
	private int isremind;
	private List<OrderListDto> orderlist;
	
	public String getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public List<OrderListDto> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List<OrderListDto> orderlist) {
		this.orderlist = orderlist;
	}
	public String getOrderDateStr() {
		return DateUtil.dateConvert(orderDate);
	}
	public void setOrderDateStr(String orderDateStr) {
		this.orderDateStr = orderDateStr;
	}
	public String getPayDateStr() {
		return DateUtil.dateConvert(payDate);
	}
	public void setPayDateStr(String payDateStr) {
		this.payDateStr = payDateStr;
	}
	public int getIsremind() {
		return isremind;
	}
	public void setIsremind(int isremind) {
		this.isremind = isremind;
	}
 
}

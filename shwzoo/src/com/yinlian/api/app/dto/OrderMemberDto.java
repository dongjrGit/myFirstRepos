package com.yinlian.api.app.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.util.DateUtil;

public class OrderMemberDto {

	//订单编号
		private Integer Id ;
		
		private String code;
		 private String groupcode;
		//订单状态
		private Integer status ;
		//收货人
		private String consignee;
		//收货人地址
		private String address;
	    //收货人电话
	    private String telPhone;
	    //支付方式
	    private Integer payType;
	    //发货时间
	    private Date deliverDate;
	    //订单总运费
	    private Float freight;
	    //订单总金额
	    private BigDecimal price;
	    //实际支付
	    private Double actualPay;
	    
	    //订单创建时间
	    private Date orderDate;
	    //支付时间
	    private Date payDate; 
	    private int iscomment;
	   
	    //订单创建时间
	    @SuppressWarnings("unused")
		private String orderDateStr;
	    //支付时间
	    @SuppressWarnings("unused")
	    private String payDateStr; 
	    
	    //订单取消或退款原因
	    private String reason;
	    
	    private String shopname;
	    
	    private String shopid;
	    
	    public String getShopid(){
	    	return shopid;
	    }
	    public void setShopid(String shopid)
	    {
	    	this.shopid = shopid;
	    }
	    public String getShopname(){
	    	return shopname;
	    }
	    public void setShopname(String shopname)
	    {
	    	this.shopname = shopname;
	    }
	    public String getGroupcode() {
			return groupcode;
		}
		public void setGroupcode(String groupcode) {
			this.groupcode = groupcode;
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
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
	
		
		private List<OrderdetailDto> children;
	    
		public List<OrderdetailDto> getChildren() {
			return children;
		}
		public void setChildren(List<OrderdetailDto> children) {
			this.children = children;
		}
		public Integer getId() {
			return Id;
		}
		public void setId(Integer id) {
			this.Id = id;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
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
		public Date getDeliverDate() {
			return deliverDate;
		}
		public void setDeliverDate(Date deliverDate) {
			this.deliverDate = deliverDate;
		}
		
		public Float getFreight() {
			return freight;
		}
		public void setFreight(Float freight) {
			this.freight = freight;
		}
	
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public Double getActualPay() {
			return actualPay;
		}
		public void setActualPay(Double actualPay) {
			this.actualPay = actualPay;
		}
		
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public Integer getIscomment() {
			return iscomment;
		}
		public void setIscomment(Integer iscomment) {
			this.iscomment = iscomment;
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
		
	}   



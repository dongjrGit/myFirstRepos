package com.yinlian.wssc.web.dto;

import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.util.DateUtil;

public class OrderDto {

    private Integer id ;
    private String code ;
    private String groupCode ;
    private Integer status ;
    private Integer payType ;
    private Float price ;
    private Float freight ;
    private Integer sellerID ;
    private Integer buyerID ;
    private Integer couponID ;
    private Integer isInvoice;
    private String logisticsCode ;
    private Date addOrderDate ;
    private Date payDate ;
    private Date deliverDate ;
    private Date deliverConfirmDate ;
    private boolean isOwned ;
    private String buyerName ;
    private String sellerName ;
    private String cancelReason ;
    private List<Orderdetail> children ;
    private Float actualPay ;
    private Float discount ;
    private Integer shopId ;
    private String shopName ;
    private String consignee;
    private String address;
    private String telphone;
    private String addOrderDatetr;

//    
//    public String getAddOrderDatetr() {
//    	this.addOrderDatetr=DateUtil.dateConvert(addOrderDate);
//		return addOrderDatetr;
//	}
//	public void setAddOrderDatetr(String addOrderDatetr) {
//		this.addOrderDatetr = addOrderDatetr;
//	}
	private Integer iscomment ;   //是否评价 0-未评价 1-已评价
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getFreight() {
		return freight;
	}
	public void setFreight(Float freight) {
		this.freight = freight;
	}
	public Integer getSellerID() {
		return sellerID;
	}
	public void setSellerID(Integer sellerID) {
		this.sellerID = sellerID;
	}
	public Integer getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(Integer buyerID) {
		this.buyerID = buyerID;
	}
	public Integer getCouponID() {
		return couponID;
	}
	public void setCouponID(Integer couponID) {
		this.couponID = couponID;
	}
	public String getLogisticsCode() {
		return logisticsCode;
	}
	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}
	public Date getAddOrderDate() {
		return addOrderDate;
	}
	public void setAddOrderDate(Date addOrderDate) {
		this.addOrderDate = addOrderDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public Date getDeliverConfirmDate() {
		return deliverConfirmDate;
	}
	public void setDeliverConfirmDate(Date deliverConfirmDate) {
		this.deliverConfirmDate = deliverConfirmDate;
	}
	public boolean getisOwned() {
		return isOwned;
	}
	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public List<Orderdetail> getChildren() {
		return children;
	}
	public void setChildren(List<Orderdetail> children) {
		this.children = children;
	}
	public Float getActualPay() {
		return actualPay;
	}
	public void setActualPay(Float actualPay) {
		this.actualPay = actualPay;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getAddOrderDatestr() {
		return DateUtil.dateConvert(addOrderDate);
	}
	public Integer getIscomment() {
		return iscomment;
	}
	public void setIscomment(Integer iscomment) {
		this.iscomment = iscomment;
	}
	public Integer getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
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
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}    
    
}

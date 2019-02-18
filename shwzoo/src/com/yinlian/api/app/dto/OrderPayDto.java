package com.yinlian.api.app.dto;

import java.util.List;

public class OrderPayDto {
private String OrderNo;
private String PayMoney;
private String BeanNumber;
private String VoucherCode;
private String UserName;
private String OrderType;
private List<OderPayMerchantInfoDto> MerchantList;
public String getOrderNo() {
	return OrderNo;
}
public void setOrderNo(String orderNo) {
	OrderNo = orderNo;
}
public String getPayMoney() {
	return PayMoney;
}
public void setPayMoney(String payMoney) {
	PayMoney = payMoney;
}
public String getBeanNumber() {
	return BeanNumber;
}
public void setBeanNumber(String beanNumber) {
	BeanNumber = beanNumber;
}
public String getVoucherCode() {
	return VoucherCode;
}
public void setVoucherCode(String voucherCode) {
	VoucherCode = voucherCode;
}
public String getUserName() {
	return UserName;
}
public void setUserName(String userName) {
	UserName = userName;
}
public String getOrderType() {
	return OrderType;
}
public void setOrderType(String orderType) {
	OrderType = orderType;
}
public List<OderPayMerchantInfoDto> getMerchantList() {
	return MerchantList;
}
public void setMerchantList(List<OderPayMerchantInfoDto> merchantList) {
	MerchantList = merchantList;
}
}

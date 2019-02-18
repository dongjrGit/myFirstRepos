package com.yinlian.api.app.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.util.DateUtil;

public class Api_ListOrderDto extends Api_OrderBaseDto{
	private int paytype;
	private BigDecimal price;
	private Date addorderdate;
	private String consignee;
    private String code ;
    private String addOrderDatetr;
    private boolean isremind;
    private String shopname;
    private String qrcode;
    private Date applydate;
    
    private Integer shoptype ;
    private String longitude;
    private String latitude;
    
    public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Date getApplydate() {
		return applydate;
	}
	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getAddOrderDatetr() {
   	this.addOrderDatetr=DateUtil.dateConvert(addorderdate);
		return addOrderDatetr;
	}
	public void setAddOrderDatetr(String addOrderDatetr) {
		this.addOrderDatetr = addOrderDatetr;
	}
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public Date getAddorderdate() {
		return addorderdate;
	}
	public void setAddorderdate(Date addorderdate) {
		this.addorderdate = addorderdate;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	private List<Api_OrderDetaiBaselDto> lsitdetais;

	public List<Api_OrderDetaiBaselDto> getLsitdetais() {
		return lsitdetais;
	}

	public void setLsitdetais(List<Api_OrderDetaiBaselDto> lsitdetais) {
		this.lsitdetais = lsitdetais;
	}
	public boolean isIsremind() {
		return isremind;
	}
	public void setIsremind(boolean isremind) {
		this.isremind = isremind;
	}
	public Integer getShoptype() {
		return shoptype;
	}
	public void setShoptype(Integer shoptype) {
		this.shoptype = shoptype;
	}
}

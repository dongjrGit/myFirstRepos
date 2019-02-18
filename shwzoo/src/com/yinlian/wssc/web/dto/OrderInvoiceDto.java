package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class OrderInvoiceDto {


    private Integer id;              //发票ID
    private Integer userid;          //买家ID
    private Integer type;            //发票类型
    private Integer titletype;       //发票抬头类型
    private String title;            //发票抬头
    private String content;          //发票内容
    private String mobile;           //手机号
    private String email;            //邮箱
    private Integer status;          //是否打印

    private Date printdate;          //打印时间
    private Integer orderid;         //订单ID
    private String ordercode;        //订单号
    private Integer sellerid;        //订单卖家ID
    private Integer shopid;          //店铺ID
    private Date addorderdate;       //下单时间
    private Date deliverdate;        //发货时间
    private Boolean isowned;         //是否直营     
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTitletype() {
		return titletype;
	}
	public void setTitletype(Integer titletype) {
		this.titletype = titletype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPrintdate() {
		return DateUtil.dateConvert(this.printdate);
	}
	public void setPrintdate(Date printdate) {
		this.printdate = printdate;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public String getAddorderdate() {
		return  DateUtil.dateConvert(this.addorderdate);
	}
	public void setAddorderdate(Date addorderdate) {
		this.addorderdate = addorderdate;
	}
	public String getDeliverdate() {
		return DateUtil.dateConvert(this.deliverdate);
	}
	public void setDeliverdate(Date deliverdate) {
		this.deliverdate = deliverdate;
	}
	public Boolean getIsowned() {
		return isowned;
	}
	public void setIsowned(Boolean isowned) {
		this.isowned = isowned;
	}
      
    
}

package com.yinlian.wssc.web.dto;

import java.util.Date;

public class CouponDetailDto {
	
	 private int    couponid;   //优惠卷id
	 
	 private String couponnum;  //优惠卷编号
	 
	 private String couponname;   //优惠卷名称
	
	 private int    couponCount;   //剩余数量
	 
	 private Float  faceValue;    //优惠卷面值
	 
	 private Date   provideTime;  //发放时间
	 
	 private Date   endTime;  //过期时间
	
	 private Date   outTime;  //用户领取后的过期时间
	 
	 private String    usercouponid; //用户领取id
	 
	 private Date usetime;  //使用时间+
	 
	 private int   orderid;  //订单id
	 
	 private  String ordercode;//订单编号
	 
	 private  Float  orderprice; //订单价格
	 
	 private   Float  activity_price;  //优惠价格
	 
	 private  int    shopid; //店铺id 
	 
	 private   String  activity_id; //使用优惠卷id 
	 
	 private  int isUse;
	 
	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public int getCouponid() {
		return couponid;
	}

	public void setCouponid(int couponid) {
		this.couponid = couponid;
	}

	public String getCouponnum() {
		return couponnum;
	}

	public void setCouponnum(String couponnum) {
		this.couponnum = couponnum;
	}

	public String getCouponname() {
		return couponname;
	}

	public void setCouponname(String couponname) {
		this.couponname = couponname;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	public Float getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(Float faceValue) {
		this.faceValue = faceValue;
	}

	public Date getProvideTime() {
		return provideTime;
	}

	public void setProvideTime(Date provideTime) {
		this.provideTime = provideTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getUsercouponid() {
		return usercouponid;
	}

	public void setUsercouponid(String usercouponid) {
		this.usercouponid = usercouponid;
	}

	public Date getUsetime() {
		return usetime;
	}

	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public Float getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(Float orderprice) {
		this.orderprice = orderprice;
	}

	public Float getActivity_price() {
		return activity_price;
	}

	public void setActivity_price(Float activity_price) {
		this.activity_price = activity_price;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}
	 
	 
	 
}

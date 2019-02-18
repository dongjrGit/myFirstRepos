package com.yinlian.wap.dto;

import java.util.Date;
import java.util.List;

import com.yinlian.api.app.dto.OrderCountDto;
import com.yinlian.api.app.dto.OrderdetailDto;
import com.yinlian.wssc.web.util.DateUtil;

public class UserInfoDto {
	/**
	 * 用户id
	 */
	public String userId;
	
	/**
	 * 用户头像
	 */
	public String imgUrl;
	/**
	 * 姓名
	 */
	public String realName;
	/**
	 * 年龄
	 */
	public Integer age;
	/**
	 * 出生日期
	 */
	public Date   birthday;
	
	private  String    birthdayer;
	/**
	 * 故乡
	 */
	public String hometown;
	/**
	 * 所在地
	 */
	public String location;
	/**
	 * 积分
	 */
	public Integer points;
	/**
	 * 未读消息数量
	 */
	public Integer messagecount;
	/**
	 * 未使用的优惠券数量
	 */
	public Integer couponcount;
	/**
	 * 订单数量
	 */
	public OrderCountDto children;
	/**
	 * 银行卡
	 */
	public Integer bankcard;
	
	/**
	 * 用户余额
	 */
	public Double  blance;
	
	/**
	 * 支付密码状态
	 */
	public Integer PayPassStatus;  
	
	
	public Double getBlance() {
		return blance;
	}
	public void setBlance(Double blance) {
		this.blance = blance;
	}
	public Integer getBankcard(){
		return bankcard;
	}
	public void setBankcard(Integer bankcards){
		bankcard = bankcards;
	}
	public Integer getPoints(){
		return points;
	}
	public void setPoints(Integer Points){
		points = Points;
	}
	public Integer getMessagecount(){
		return messagecount;
	}
	public void setMessagecount(Integer Messagecount){
		messagecount = Messagecount;
	}
	public Integer getCouponcount(){
		return couponcount;
	}
	public void setCouponcount(Integer Couponcount){
		couponcount = Couponcount;
	}
	public OrderCountDto getChildren() {
		return children;
	}
	public void setChildren(OrderCountDto children) {
		this.children = children;
	}

	public Integer getPayPassStatus() {
		return PayPassStatus;
	}
	public void setPayPassStatus(Integer payPassStatus) {
		PayPassStatus = payPassStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHometown() {
		return hometown;
	}
	
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getBirthdayer() {
		this.birthdayer = DateUtil.datePattren(birthday);
		return birthdayer;
	}
	public void setBirthdayer(String birthdayer) {
		this.birthdayer = birthdayer;
	}
	
}

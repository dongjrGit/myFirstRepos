package com.yinlian.api.app.dto;

import java.util.Date;

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
	 * 身份号码
	 */	
	
	public String idcard;

	/**
	 * 电话号码
	 */
	public String mobile;

	/**
	 * 出生日期
	 */
	public Date   birthday;
	
	private  String    birthdayer;
	/**
	 * 故乡
	 */
	//public String hometown;
	/**
	 * 所在地
	 */
	public String location;
	
	/**
	 * 支付密码状态
	 */
	
	public Integer PayPassStatus;

	private Integer sex;  
	
	private String provincecode;
	
	private String citycode;
	
	private String areacode;
	
	private String provincename;
	
	private String cityname;
	
	private String areaname;
	
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getProvincecode() {
		return provincecode;
	}
	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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

/*
	public String getHometown() {
		return hometown;
	}
	
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}*/
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
	public Integer getSex(){
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
		
	}
	
}

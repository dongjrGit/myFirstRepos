package com.yinlian.api.app.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

/**
 * api可领取优惠劵列表
 * @author Administrator
 *
 */
public class AvailableCouponDto {
	
	private Integer id;                //优惠卷ID
	private String couponnum;          //优惠卷编号
	private String couponname;         //优惠卷名称
	private Float facevalue;           //优惠卷面值
	private Integer couponusetype;     //优惠卷使用类型
	private Integer coupontype;        //优惠卷类型
	private Float fullreductionvalue;  //满额
	private Date providetime;          //使用开始时间
	private Date outtime;              //过期时间
	private String imgurlApp;		   //APP图片
	private String imgurl;             //图片
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Float getFacevalue() {
		return facevalue;
	}
	public void setFacevalue(Float facevalue) {
		this.facevalue = facevalue;
	}
	public Integer getCouponusetype() {
		return couponusetype;
	}
	public void setCouponusetype(Integer couponusetype) {
		this.couponusetype = couponusetype;
	}
	public Integer getCoupontype() {
		return coupontype;
	}
	public void setCoupontype(Integer coupontype) {
		this.coupontype = coupontype;
	}
	public Float getFullreductionvalue() {
		return fullreductionvalue;
	}
	public void setFullreductionvalue(Float fullreductionvalue) {
		this.fullreductionvalue = fullreductionvalue;
	}
	public String getProvidetime() {
		return DateUtil.dateConvert(this.providetime);
	}
	public void setProvidetime(Date providetime) {
		this.providetime = providetime;
	}
	public String getOuttime() {
		return DateUtil.dateConvert(this.outtime);
	}
	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}
	public String getImgurlApp() {
		return imgurlApp;
	}
	public void setImgurlApp(String imgurlApp) {
		this.imgurlApp = imgurlApp;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	
}

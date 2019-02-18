package com.yinlian.wssc.web.util;

public class CriteriaCoupon extends Criteria {
	
	private Integer status; //领用优惠卷状态  未使用,已使用,已过期,已删除
	private Integer userid; //领用人ID
	private Integer couponid; //优惠卷ID
	private String  name;    //名称
	private Integer ordertype; //排序类型
	private Integer useplatform; //使用平台
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getCouponid() {
		return couponid;
	}
	public void setCouponid(Integer couponid) {
		this.couponid = couponid;
	}
	public Integer getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUseplatform() {
		return useplatform;
	}
	public void setUseplatform(Integer useplatform) {
		this.useplatform = useplatform;
	}
	
}

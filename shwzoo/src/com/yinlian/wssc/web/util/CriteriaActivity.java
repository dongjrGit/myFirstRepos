package com.yinlian.wssc.web.util;

import java.util.Date;

/**
 * 活动，优惠卷 检索条件类
 * @author Administrator
 *
 */
public class CriteriaActivity extends Criteria {

	private String num;          //编号
	private String name;         //名称
	private Integer actType;     //活动类型
	private Integer couponType;  //优惠卷类型
	private Integer userType;    //优惠卷使用对象
	private Integer issueType;    //优惠卷发放类型 
	private Integer fromType;    //优惠卷来源
	private Date startFrom;      //开始时间  开始
	private Date startTo;        //开始时间  结束
	private Date endFrom;        //结束时间  开始
	private Date endTo;          //结束时间  结束
	private Integer spikeID;     //秒杀活动ID
	private Integer status;      //活动状态
	private Integer isout;       //活动是否过期 0=过期 1-未过期
	private Integer usesite;     //活动使用平台
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getActType() {
		return actType;
	}
	public void setActType(Integer actType) {
		this.actType = actType;
	}
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getFromType() {
		return fromType;
	}
	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}
	public Date getStartFrom() {
		return startFrom;
	}
	public void setStartFrom(Date startFrom) {
		this.startFrom = startFrom;
	}
	public Date getStartTo() {
		return startTo;
	}
	public void setStartTo(Date startTo) {
		this.startTo = startTo;
	}
	public Date getEndFrom() {
		return endFrom;
	}
	public void setEndFrom(Date endFrom) {
		this.endFrom = endFrom;
	}
	public Date getEndTo() {
		return endTo;
	}
	public void setEndTo(Date endTo) {
		this.endTo = endTo;
	}
	public Integer getSpikeID() {
		return spikeID;
	}
	public void setSpikeID(Integer spikeID) {
		this.spikeID = spikeID;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsout() {
		return isout;
	}
	public void setIsout(Integer isout) {
		this.isout = isout;
	}
	public Integer getIssueType() {
		return issueType;
	}
	public void setIssueType(Integer issueType) {
		this.issueType = issueType;
	}
	public Integer getUsesite() {
		return usesite;
	}
	public void setUsesite(Integer usesite) {
		this.usesite = usesite;
	}
	
	
}

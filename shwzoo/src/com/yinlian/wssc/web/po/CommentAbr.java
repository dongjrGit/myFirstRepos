package com.yinlian.wssc.web.po;

public class CommentAbr {
	/**
	 * 差评
	 */
	private Integer bad;
	/**
	 * 中评
	 */
	private Integer mr;
	/**
	 * 好评
	 */
	private Integer good;
	
	private Integer picCom;
	
	public Integer getBad() {
		return bad;
	}
	public void setBad(Integer bad) {
		this.bad = bad;
	}
	public Integer getMr() {
		return mr;
	}
	public void setMr(Integer mr) {
		this.mr = mr;
	}
	public Integer getGood() {
		return good;
	}
	public void setGood(Integer good) {
		this.good = good;
	}
	public Integer getPicCom() {
		return picCom;
	}
	public void setPicCom(Integer picCom) {
		this.picCom = picCom;
	}
}

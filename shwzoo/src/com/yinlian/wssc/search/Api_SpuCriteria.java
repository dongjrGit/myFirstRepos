package com.yinlian.wssc.search;

public class Api_SpuCriteria extends BaseCriteria{
	/**
	 * 品牌Id
	 */
	private Integer bid;
	
	/**
	 * 店铺id
	 */
	private Integer sid;
	
	private String keywords;
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}
}

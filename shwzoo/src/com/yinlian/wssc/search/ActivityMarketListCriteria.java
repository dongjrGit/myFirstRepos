package com.yinlian.wssc.search;

import java.util.Date;

public class ActivityMarketListCriteria implements ICriteria {

	// 排序条件
	private String orderByClause; // order by子句 如果不为空就排序 否则就不排序

	private String sort; // 排序方式 默认是升序

	/**
	 * 活动编号
	 */
	private String num;

	/**
	 * 活动名称
	 */
	private String name;

	/**
	 * 活动类型
	 */
	private Integer type;

	/**
	 * 所属店铺ID
	 */
	private Integer shopId;

	/**
	 * 审核状态
	 */
	private Boolean checkss;

	/**
	 * 活动明细类型
	 */
	private Integer detailtype;
	
	/**
	 * 开始时间 开始
	 */
	private Date s1;

	/**
	 * 开始时间 结束
	 */
	private Date s2;
	
	/**
	 * 结束时间 开始
	 */
	private Date e1;
	
	/**
	 * 结束时间 结束
	 */
	private Date e2;

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Boolean getCheckss() {
		return checkss;
	}

	public void setCheckss(Boolean checkss) {
		this.checkss = checkss;
	}

	public Integer getDetailtype() {
		return detailtype;
	}

	public void setDetailtype(Integer detailtype) {
		this.detailtype = detailtype;
	}

	public Date getS1() {
		return s1;
	}

	public void setS1(Date s1) {
		this.s1 = s1;
	}

	public Date getS2() {
		return s2;
	}

	public void setS2(Date s2) {
		this.s2 = s2;
	}

	public Date getE1() {
		return e1;
	}

	public void setE1(Date e1) {
		this.e1 = e1;
	}

	public Date getE2() {
		return e2;
	}

	public void setE2(Date e2) {
		this.e2 = e2;
	}

	
}

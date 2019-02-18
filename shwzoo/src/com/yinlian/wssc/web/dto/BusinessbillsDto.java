/*
 * @(#) BusinessbillsDto.java 2016年8月29日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.dto;

import java.util.List;

/**
 * 对账订单信息
 * @author Administrator
 *
 */
public class BusinessbillsDto {

	/**
	 * 店铺ID
	 */
	private Integer shopId;
	
	/**
	 * 持卡人姓名
	 */
	private String bankUserName;
	
	/**
	 * 开户行名称
	 */
	private String bankName;
	
	/**
	 * 银行卡卡号
	 */
	private String BankNum;
	
	/**
	 * 商户名称
	 */
	private String name;
	
	/**
	 * 结算类型
	 */
	private Integer settlementType;
	
	/**
	 * 分成百分比
	 */
	private String royaltyRate;
	
	/**
	 * 订单
	 */
	private List<OrderBillsDto> list;
	

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNum() {
		return BankNum;
	}

	public void setBankNum(String bankNum) {
		BankNum = bankNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}

	public String getRoyaltyRate() {
		return royaltyRate;
	}

	public void setRoyaltyRate(String royaltyRate) {
		this.royaltyRate = royaltyRate;
	}



	public List<OrderBillsDto> getList() {
		return list;
	}

	public void setList(List<OrderBillsDto> list) {
		this.list = list;
	}
}

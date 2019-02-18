package com.yinlian.wssc.web.dto;

import java.util.List;

/**
 * 销售统计
 * 
 * @author Administrator
 *
 */
public class SalesDetailsDto {

	// 订单数量
	private Integer ordercount;

	// 订单金额
	private Float ordermoney;

	// 订单客户数
	private Integer buyercount;

	// 订单商品件数
	private Integer skucount;


	// 销售明细（商品列表）
	private List<SkuSalesAnalysisDto> skuSalasDetails;


	public Integer getOrdercount() {
		return ordercount;
	}


	public void setOrdercount(Integer ordercount) {
		this.ordercount = ordercount;
	}


	public Float getOrdermoney() {
		return ordermoney;
	}


	public void setOrdermoney(Float ordermoney) {
		this.ordermoney = ordermoney;
	}


	public Integer getBuyercount() {
		return buyercount;
	}


	public void setBuyercount(Integer buyercount) {
		this.buyercount = buyercount;
	}


	public Integer getSkucount() {
		return skucount;
	}


	public void setSkucount(Integer skucount) {
		this.skucount = skucount;
	}


	public List<SkuSalesAnalysisDto> getSkuSalasDetails() {
		return skuSalasDetails;
	}


	public void setSkuSalasDetails(List<SkuSalesAnalysisDto> skuSalasDetails) {
		this.skuSalasDetails = skuSalasDetails;
	}
	
	
}

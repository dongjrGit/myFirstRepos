package com.yinlian.wssc.web.dto;

import java.util.List;

public class PackagesDto {
	private int actCount;
	
	private int skuCount;
	
	private int  orderCount;
	
	private Float orderMoney;
	
	private Float yhMoney;
	
	private List<PackageDetailDto> packageList;

	public int getActCount() {
		return actCount;
	}

	public void setActCount(int actCount) {
		this.actCount = actCount;
	}

	public int getSkuCount() {
		return skuCount;
	}

	public void setSkuCount(int skuCount) {
		this.skuCount = skuCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public Float getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Float orderMoney) {
		this.orderMoney = orderMoney;
	}
	
	public Float getYhMoney() {
		return yhMoney;
	}

	public void setYhMoney(Float yhMoney) {
		this.yhMoney = yhMoney;
	}

	public List<PackageDetailDto> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<PackageDetailDto> packageList) {
		this.packageList = packageList;
	}
	
	
}

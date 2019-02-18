package com.yinlian.wssc.web.dto;

import java.util.List;

public class PackageDto {
	  //组合商品ID
    private Integer PackageID;
    //组合商品编号
    private String PackageNum;
    //组合商品名称
    private String PackageName;

    //组合商品价格
    private Float SkuPackPrice;
    //组合商品数量
    private Integer Count;
    //组合商品（参与库存商品列表）
    private List<PackageSkuDto> skus;
    
	public Integer getPackageID() {
		return PackageID;
	}
	public void setPackageID(Integer packageID) {
		PackageID = packageID;
	}
	public String getPackageNum() {
		return PackageNum;
	}
	public void setPackageNum(String packageNum) {
		PackageNum = packageNum;
	}
	public String getPackageName() {
		return PackageName;
	}
	public void setPackageName(String packageName) {
		PackageName = packageName;
	}
	public Float getSkuPackPrice() {
		return SkuPackPrice;
	}
	public void setSkuPackPrice(Float skuPackPrice) {
		SkuPackPrice = skuPackPrice;
	}
	public Integer getCount() {
		return Count;
	}
	public void setCount(Integer count) {
		Count = count;
	}
	public List<PackageSkuDto> getSkus() {
		return skus;
	}
	public void setSkus(List<PackageSkuDto> skus) {
		this.skus = skus;
	}
    
    
}

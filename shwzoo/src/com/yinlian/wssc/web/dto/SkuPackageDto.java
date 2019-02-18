package com.yinlian.wssc.web.dto;

public class SkuPackageDto {
	//组合商品子表ID
    private Integer ID;
    //组合商品ID
    private Integer PackageID;
    //组合商品编号
    private String PackageNum;
    //组合商品名称
    private String PackageName;
    //库存商品ID
    private Integer SkuID;
    //优惠价格
    private Float SkuPackPrice;
    //库存商品编号
    private String SkuNum;
    //库存商品名称
    private String SkuName;
    //库存商品价格
    private Float SkuPrice;
    //库存商品图片地址
    private String ImgUrl;
    //组合商品数量
    private Integer Count;
    
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
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
	public Integer getSkuID() {
		return SkuID;
	}
	public void setSkuID(Integer skuID) {
		SkuID = skuID;
	}
	public Float getSkuPackPrice() {
		return SkuPackPrice;
	}
	public void setSkuPackPrice(Float skuPackPrice) {
		SkuPackPrice = skuPackPrice;
	}
	public String getSkuNum() {
		return SkuNum;
	}
	public void setSkuNum(String skuNum) {
		SkuNum = skuNum;
	}
	public String getSkuName() {
		return SkuName;
	}
	public void setSkuName(String skuName) {
		SkuName = skuName;
	}
	public Float getSkuPrice() {
		return SkuPrice;
	}
	public void setSkuPrice(Float skuPrice) {
		SkuPrice = skuPrice;
	}
	public String getImgUrl() {
		return ImgUrl;
	}
	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}
	public Integer getCount() {
		return Count;
	}
	public void setCount(Integer count) {
		Count = count;
	}
    
}

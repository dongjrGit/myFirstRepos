package com.yinlian.api.app.dto;

import java.math.BigDecimal;

public class CollectDto {
	
	private String imgurl;
	private String name;
	private BigDecimal price;
	private String bewrite;
	private String star;
	private String goodrate;
	private String marketingscope;
	private Integer procount;
	private Integer spuid;
	private Integer shopid;
	
	public Integer getSpuid() {
		return spuid;
	}
	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getBewrite() {
		return bewrite;
	}
	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getGoodrate() {
		return goodrate;
	}
	public void setGoodrate(String goodrate) {
		this.goodrate = goodrate;
	}
	public String getMarketingscope() {
		return marketingscope;
	}
	public void setMarketingscope(String marketingscope) {
		this.marketingscope = marketingscope;
	}
	public Integer getProcount() {
		return procount;
	}
	public void setProcount(Integer procount) {
		this.procount = procount;
	}
	
}

package com.yinlian.wssc.web.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品列表dto
 * 
 * @author mjx
 *
 */
public class ProductListDto {
	private int id;
	private String name;
	private int status;
	private String titleShort;
	private String proNum;
	private int classID;
	private int brandID;
	private Date createTime;
	private Boolean isOwned;
	private int shopID;
	private BigDecimal price;
	private BigDecimal appprice;
	private BigDecimal wapprice;
	private String shopName;
	private int userID;
	private String fullPath;
	private String fullPathName;
	private String imgApp;
	private String searchpath;
	private int isoffer;
	public String getImgApp() {
		return imgApp;
	}
	
	public void setImgApp(String imgApp) {
		this.imgApp = imgApp;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitleShort() {
		return titleShort;
	}

	public void setTitleShort(String titleShort) {
		this.titleShort = titleShort;
	}

	public String getProNum() {
		return proNum;
	}

	public void setProNum(String proNum) {
		this.proNum = proNum;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public int getBrandID() {
		return brandID;
	}

	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsOwned() {
		return isOwned;
	}

	public void setIsOwned(Boolean isOwned) {
		this.isOwned = isOwned;
	}

	public int getShopID() {
		return shopID;
	}

	public void setShopID(int shopID) {
		this.shopID = shopID;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getFullPathName() {
		return fullPathName;
	}

	public void setFullPathName(String fullPathName) {
		this.fullPathName = fullPathName;
	}

	public String getSearchpath() {
		return searchpath;
	}

	public void setSearchpath(String searchpath) {
		this.searchpath = searchpath;
	}

	public BigDecimal getAppprice() {
		return appprice;
	}

	public void setAppprice(BigDecimal appprice) {
		this.appprice = appprice;
	}

	public BigDecimal getWapprice() {
		return wapprice;
	}

	public void setWapprice(BigDecimal wapprice) {
		this.wapprice = wapprice;
	}

	public int getIsoffer() {
		return isoffer;
	}

	public void setIsoffer(int isoffer) {
		this.isoffer = isoffer;
	}
}

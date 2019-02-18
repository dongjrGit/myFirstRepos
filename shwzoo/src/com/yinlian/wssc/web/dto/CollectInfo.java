package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class CollectInfo {

	private int id;
	
	private String username;
	
	private String shopname;
	
	private int type;
	
	private String title;
	
	private String spuname;
	
	private String spuimgurl;
	
	private String shopnamespu;
	
	private Date createtime;
	
	private String createtimestr;//创建日期的字符串格式

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSpuname() {
		return spuname;
	}

	public void setSpuname(String spuname) {
		this.spuname = spuname;
	}

	public String getSpuimgurl() {
		return spuimgurl;
	}

	public void setSpuimgurl(String spuimgurl) {
		this.spuimgurl = spuimgurl;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatetimestr() {
		this.createtimestr = DateUtil.dateConvert(createtime);
		return createtimestr;
	}

	public void setCreatetimestr(String createtimestr) {
		this.createtimestr = createtimestr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShopnamespu() {
		return shopnamespu;
	}

	public void setShopnamespu(String shopnamespu) {
		this.shopnamespu = shopnamespu;
	}
	
	
}

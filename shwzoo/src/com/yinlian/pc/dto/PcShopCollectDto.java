package com.yinlian.pc.dto;

import java.util.Date;

import data.ParseUtil;

public class PcShopCollectDto {
	   
	private String name;
	
	private  Integer  sid;  //店铺id
	
	private  String   imgurl;
	
	private   Integer cid;  //collect id
	
	private  Date createtime;
	
	private String createtimetr;
	
	

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatetimetr() {
		this.createtimetr=ParseUtil.parseDateToString(createtime, "yyyy-MM-dd");
		return createtimetr;
	}

	public void setCreatetimetr(String createtimetr) {
		this.createtimetr = createtimetr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
    
}

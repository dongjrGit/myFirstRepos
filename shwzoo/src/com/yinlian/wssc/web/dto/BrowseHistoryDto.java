package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class BrowseHistoryDto {

	private Integer userid;
	
	private String username;
	
	private Integer channeltype;
	
	private String spuname;
	
	private String imgurl;
	
	private Date createtime;
	
	private String createtimestr;
	private Float price;
	 
	private Integer spuid;

	
	private Float spuprice;


	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getSpuid() {
		return spuid;
	}

	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getChanneltype() {
		return channeltype;
	}

	public void setChanneltype(Integer channeltype) {
		this.channeltype = channeltype;
	}

	public String getSpuname() {
		return spuname;
	}

	public void setSpuname(String spuname) {
		this.spuname = spuname;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
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

	public Float getSpuprice() {
		return spuprice;
	}

	public void setSpuprice(Float spuprice) {
		this.spuprice = spuprice;
	}
	
	
}

package com.techown.wssc.web.po;

import java.math.BigDecimal;
import java.util.Date;

import data.ParseUtil;

public class Scenic {
	private Integer id;

	private String type;

	private String scenicname;

	private String img;

	private Integer property;

	private String imageid;

	private Integer scenicarea;

	private String address;

	private Integer ischarge;

	private Integer state;

	private Integer shopid;

	private Integer topicid;

	private BigDecimal longitude;

	private BigDecimal latitude;
	
	private Date createtime;
	
	private Date updatetime;
	
	private String typeName;
	
	private String createtimestr;
	
	private String shopname;
	
	private String topicname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getScenicname() {
		return scenicname;
	}

	public void setScenicname(String scenicname) {
		this.scenicname = scenicname == null ? null : scenicname.trim();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public Integer getTopicid() {
		return topicid;
	}

	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}
	
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public Integer getScenicarea() {
		return scenicarea;
	}

	public void setScenicarea(Integer scenicarea) {
		this.scenicarea = scenicarea;
	}

	public Integer getIscharge() {
		return ischarge;
	}

	public void setIscharge(Integer ischarge) {
		this.ischarge = ischarge;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getCreatetimestr() {
		if(null!=createtime){
			this.createtimestr =ParseUtil.parseDateToString(createtime, "yyyy-MM-dd HH:mm:ss");	
		}
		return createtimestr;
	}

	public void setCreatetimestr(String createtimestr) {
		this.createtimestr = createtimestr;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getTopicname() {
		return topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}
}
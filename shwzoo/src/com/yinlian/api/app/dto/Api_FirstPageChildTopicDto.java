package com.yinlian.api.app.dto;

import java.util.List;

import data.ParseUtil;

public class Api_FirstPageChildTopicDto {
	private Integer topicid;
	private String imgurl;
	private  String url;
	private Integer sort;
	private Integer type;
	private String ids;
	private List<Integer> relatedid;
	private String name;
	private String title;
	
	private Integer floorType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Integer> getRelatedid() {
		return relatedid;
	}
	public void setRelatedid(List<Integer> relatedid) {
		this.relatedid = relatedid;
		this.ids=ParseUtil.parseArrayToString(relatedid.toArray(), ",");
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getTopicid() {
		return topicid;
	}
	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getIds() {
		return ids;
	}
	public Integer getFloorType() {
		return floorType;
	}
	public void setFloorType(Integer floorType) {
		this.floorType = floorType;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}

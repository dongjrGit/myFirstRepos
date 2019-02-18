package com.yinlian.wssc.web.dto;


public class FindTopicRelateDto {
	
	private  Integer  relationId;
	
	private  String   relationImg;
	
	private  Integer  sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public String getRelationImg() {
		return relationImg;
	}

	public void setRelationImg(String relationImg) {
		this.relationImg = relationImg;
	}
	
}

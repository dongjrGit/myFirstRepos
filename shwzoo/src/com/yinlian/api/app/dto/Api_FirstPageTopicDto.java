package com.yinlian.api.app.dto;

import java.util.List;



public class Api_FirstPageTopicDto {
	private Integer floorappid;
	private List<Api_FirstPageChildTopicDto> childs;
	public Integer getFloorappid() {
		return floorappid;
	}
	public void setFloorappid(Integer floorappid) {
		this.floorappid = floorappid;
	}
	public List<Api_FirstPageChildTopicDto> getChilds() {
		return childs;
	}
	public void setChilds(List<Api_FirstPageChildTopicDto> childs) {
		this.childs = childs;
	}
}

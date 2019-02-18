package com.techown.wssc.web.po;

import com.yinlian.wssc.web.util.Criteria;

public class CriteriaScenic extends Criteria {

	private String name;
	private String type;
	private Integer area;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}
}

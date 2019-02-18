package com.yinlian.wssc.web.po;

import com.yinlian.wssc.web.util.Criteria;

public class CriteriaVHpSku extends Criteria {

	private String name;
	private Integer type;
	
	
	/**
	 * 市
	 */
	private Integer citycode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

}

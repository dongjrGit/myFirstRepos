package com.yinlian.api.app.dto;

public class SeachAtrrValueDto {
	private String value;
	private Integer type;
	private Integer attrtype;	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAttrtype() {
		return attrtype;
	}

	public void setAttrtype(Integer attrtype) {
		this.attrtype = attrtype;
	}

	private String unit;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}

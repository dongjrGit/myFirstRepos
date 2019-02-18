package com.yinlian.api.app.dto;

public class Api_SeachAtrrValuesDto {
	private Integer type;
	private String disvalue;
	private String attrtype;
	public String getAttrtype() {
		return attrtype;
	}
	public void setAttrtype(String attrtype) {
		this.attrtype = attrtype;
	}
	private String minvalue;
	private String maxvalue;
	private Integer Id;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDisvalue() {
		return disvalue;
	}
	public void setDisvalue(String disvalue) {
		this.disvalue = disvalue;
	}
	public String getMinvalue() {
		return minvalue;
	}
	public void setMinvalue(String minvalue) {
		this.minvalue = minvalue;
	}
	public String getMaxvalue() {
		return maxvalue;
	}
	public void setMaxvalue(String maxvalue) {
		this.maxvalue = maxvalue;
	}
}

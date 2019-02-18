package com.yinlian.api.app.dto;

import java.util.List;

public class SpecsBaseDto {
	private Integer id;
	private String name;
	private List<IdValue> values;

	public List<IdValue> getValues() {
		return values;
	}

	public void setValues(List<IdValue> values) {
		this.values = values;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

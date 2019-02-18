package com.yinlian.wssc.web.dto;

import java.util.List;

import com.yinlian.wssc.web.po.Department;

public class DepartmentDto extends Department {
	
	private List<DepartmentDto> childrens;
	
	private String tiptitle;
	
	private String action;
	

	public List<DepartmentDto> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<DepartmentDto> childrens) {
		this.childrens = childrens;
	}

	public String getTiptitle() {
		return tiptitle;
	}

	public void setTiptitle(String tiptitle) {
		this.tiptitle = tiptitle;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	


}

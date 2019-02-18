package com.yinlian.pc.dto;

import java.util.List;

import com.yinlian.wssc.web.po.Province;

public class ProvinceDto {
	
	private String name;
	
	private List<Province> prolist;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Province> getProlist() {
		return prolist;
	}

	public void setProlist(List<Province> prolist) {
		this.prolist = prolist;
	}


}

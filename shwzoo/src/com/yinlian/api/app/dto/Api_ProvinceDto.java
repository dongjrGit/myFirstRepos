package com.yinlian.api.app.dto;

import java.util.List;

/**
 * 省市区dto
 */
public class Api_ProvinceDto {
	private int pid; // 省市区ID

	private String pname; // 省市区名称

	private String pcode; // 省市区code
	private List<Api_CityDto> citys;
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public List<Api_CityDto> getCitys() {
		return citys;
	}

	public void setCitys(List<Api_CityDto> citys) {
		this.citys = citys;
	}
		
}
 
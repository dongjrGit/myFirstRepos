package com.yinlian.wap.dto;

import java.util.ArrayList;
import java.util.List;

import com.yinlian.wssc.web.po.City;

/**
 * 区域选择
 * @author Administrator
 *
 */
public class CityDto {
	
	/**
	 * 首字母
	 */
	private String letter;

	private List<City> list;
	
	public CityDto() {
		list=new ArrayList<City>();
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public List<City> getList() {
		return list;
	}

	public void setList(List<City> list) {
		this.list = list;
	}
}

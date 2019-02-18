package com.yinlian.wssc.web.dto;

import java.util.List;

public class FindRelateShopDto {
	
	private Integer shopid;
	
	private String  shopname;
	
	private  String shopimg;
	
	private Integer findid;

	private List<FindSpuDto> spulist;
	
	public Integer getFindid() {
		return findid;
	}

	public void setFindid(Integer findid) {
		this.findid = findid;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getShopimg() {
		return shopimg;
	}

	public void setShopimg(String shopimg) {
		this.shopimg = shopimg;
	}

	public List<FindSpuDto> getSpulist() {
		return spulist;
	}

	public void setSpulist(List<FindSpuDto> spulist) {
		this.spulist = spulist;
	}
	
	
}

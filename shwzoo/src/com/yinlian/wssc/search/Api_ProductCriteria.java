package com.yinlian.wssc.search;

import java.util.List;

import com.yinlian.api.app.dto.Api_SeachAtrrDto;

public class Api_ProductCriteria extends BaseCriteria {
	/**
	 * 分类Id
	 */
	private Integer clsid;
	/**
	 * 关键字
	 */
	private String keyword;
	private List<Api_SeachAtrrDto> seachatrrdtos;
	private Double minprice;
	private Double maxprice;
	private String ispostage;
	private String fullpath;
	private String status;
	
	private Integer shopid;
	
	public String getIspostage() {
		return ispostage;
	}

	public void setIspostage(String ispostage) {
		this.ispostage = ispostage;
	}

	/**
	 * 关键字检索的商品ID集合
	 */
	private String idliststr;
	
	public Double getMinprice() {
		return minprice;
	}

	public void setMinprice(Double minprice) {
		this.minprice = minprice;
	}

	public Double getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(Double maxprice) {
		this.maxprice = maxprice;
	}

	public Integer getClsid() {
		return clsid;
	}

	public void setClsid(Integer clsid) {
		this.clsid = clsid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}	

	public List<Api_SeachAtrrDto> getSeachatrrdtos() {
		return seachatrrdtos;
	}

	public void setSeachatrrdtos(List<Api_SeachAtrrDto> seachatrrdtos) {
		this.seachatrrdtos = seachatrrdtos;
	}

	public String getIdliststr() {
		return idliststr;
	}

	public void setIdliststr(String idliststr) {
		this.idliststr = idliststr;
	}

	public String getFullpath() {
		return fullpath;
	}

	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
}

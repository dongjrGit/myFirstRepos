/*
 * @(#) SearchkeyDto.java 2016年6月23日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.dto;

public class SearchkeyDto {
	
	/**
	 * 编号
	 */
	private int id;

	/**
	 * 关键字
	 */
	private String keyWords;
	
	/**
	 * 排序
	 */
	private int orderBy;
	
	
	/**
	 * 使用站点
	 */
	private String useSites;
	
	private Integer pc;
	
	private Integer wap;
	
	private Integer app;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getKeyWords() {
		return keyWords;
	}


	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}


	public int getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}




	public Integer getPc() {
		return pc;
	}


	public void setPc(Integer pc) {
		this.pc = pc;
	}


	public Integer getWap() {
		return wap;
	}


	public void setWap(Integer wap) {
		this.wap = wap;
	}


	public Integer getApp() {
		return app;
	}


	public void setApp(Integer app) {
		this.app = app;
	}


	public String getUseSites() {
		return useSites;
	}


	public void setUseSites(String useSites) {
		this.useSites = useSites;
	}
	
	
}

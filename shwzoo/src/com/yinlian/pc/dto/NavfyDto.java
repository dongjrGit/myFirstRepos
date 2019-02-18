package com.yinlian.pc.dto;

import java.util.List;

import com.yinlian.pc.dto.ArticleDto;

public class NavfyDto {
	private int id;
	private String name;
	private int partid;
	private List<ArticleDto> artlist;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ArticleDto> getArtlist() {
		return artlist;
	}
	public void setArtlist(List<ArticleDto> artlist) {
		this.artlist = artlist;
	}
	public int getPartid() {
		return partid;
	}
	public void setPartid(int partid) {
		this.partid = partid;
	}		
}

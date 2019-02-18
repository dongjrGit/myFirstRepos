package com.yinlian.api.app.dto;

public class Api_NewsDto {
	private Integer id;
	
	private String  title;   //标题
	
	private String  diget;   //摘要

	private String  bytitle;  //副标题
	
	private String  content;  //内容
	
	private  String  imgurl;  //图片地址

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiget() {
		return diget;
	}

	public void setDiget(String diget) {
		this.diget = diget;
	}

	public String getBytitle() {
		return bytitle;
	}

	public void setBytitle(String bytitle) {
		this.bytitle = bytitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	
}

package com.yinlian.api.app.dto;

import java.util.List;

/**
 * 经彩活动详情DTO
 * @author Administrator
 *
 */
public class ExcitingActDetailDto {

	private Integer id;               //经彩活动ID
	private String detaildesc;        //详细介绍
	private String actcode;           //参与活动验证码
	private List<String> imglist;     //图片列表
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDetaildesc() {
		  if (detaildesc != null && detaildesc.indexOf("\"") > 0) {
              this.detaildesc = detaildesc.replace("\"", "'");
          }
    	  this.detaildesc = detaildesc.replace("<","&lt;");
    	  this.detaildesc = detaildesc.replace(">","&gt;");
		return detaildesc;
	}
	public void setDetaildesc(String detaildesc) {
		this.detaildesc = detaildesc;
	}
	public String getActcode() {
		return actcode;
	}
	public void setActcode(String actcode) {
		this.actcode = actcode;
	}
	public List<String> getImglist() {
		return imglist;
	}
	public void setImglist(List<String> imglist) {
		this.imglist = imglist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

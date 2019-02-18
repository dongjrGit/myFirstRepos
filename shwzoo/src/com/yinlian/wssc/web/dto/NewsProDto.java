package com.yinlian.wssc.web.dto;

/**
 * 新闻商品
 * @author Administrator
 *
 */
public class NewsProDto {
	
	/**
	 * 商品名称
	 */
	private String spuname;
	
	/**
	 * 商品ID
	 */
	private Integer spuid;
	
	/**
	 * 商品图片
	 */
	private String spuimg;
	
	/**
	 * ID
	 */
	private Integer id;

	public String getSpuname() {
		return spuname;
	}

	public void setSpuname(String spuname) {
		this.spuname = spuname;
	}

	public Integer getSpuid() {
		return spuid;
	}

	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}

	public String getSpuimg() {
		return spuimg;
	}

	public void setSpuimg(String spuimg) {
		this.spuimg = spuimg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

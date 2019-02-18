package com.yinlian.pc.dto;

import java.math.BigDecimal;

/**
 * 新闻商品
 * @author Administrator
 *
 */
public class NewsProDto {
	
	private String spuname;
	
	private BigDecimal spuprice;
	
	private String spuimg;
	
	private Integer spuid;
	
	private Integer id;

	public String getSpuname() {
		return spuname;
	}

	public void setSpuname(String spuname) {
		this.spuname = spuname;
	}


	public String getSpuimg() {
		return spuimg;
	}

	public void setSpuimg(String spuimg) {
		this.spuimg = spuimg;
	}

	public Integer getSpuid() {
		return spuid;
	}

	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSpuprice(BigDecimal spuprice) {
		this.spuprice = spuprice;
	}

	public BigDecimal getSpuprice() {
		return spuprice;
	}

}

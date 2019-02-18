package com.yinlian.pc.dto;

import java.math.BigDecimal;

/**
 * 猜你喜欢
 * @author Administrator
 *
 */
public class GuessLikeDto {

	/**
	 * spuID
	 */
	private Integer spuid;
	
	/**
	 * 商品名称
	 */
	private String spuname;
	
	/**
	 * 商品价格
	 */
	private BigDecimal spuprice;
	
	/**
	 * 商品图片
	 */
	private String spuimg;

	public Integer getSpuid() {
		return spuid;
	}

	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}

	public String getSpuname() {
		return spuname;
	}

	public void setSpuname(String spuname) {
		this.spuname = spuname;
	}

	public BigDecimal getSpuprice() {
		return spuprice;
	}

	public void setSpuprice(BigDecimal spuprice) {
		this.spuprice = spuprice;
	}

	public String getSpuimg() {
		return spuimg;
	}

	public void setSpuimg(String spuimg) {
		this.spuimg = spuimg;
	}
}

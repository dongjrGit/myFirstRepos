package com.yinlian.wssc.web.dto;

import java.math.BigDecimal;

/**
 * 添加商品 库存商品信息
 * @author Administrator
 *
 */
public class SkuTimeStockDto {
	
	private   Integer   id;
	
	private   Integer  showyear;
	
	private   Integer  showmonth;
	
	private   Integer  showdays;
	
	private   BigDecimal  price;
	
	private   Integer   stock;
	
	private   Integer   spuid;
	
	private   Integer  skuid;
	
	private   String    dateStr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShowyear() {
		return showyear;
	}

	public void setShowyear(Integer showyear) {
		this.showyear = showyear;
	}

	public Integer getShowmonth() {
		return showmonth;
	}

	public void setShowmonth(Integer showmonth) {
		this.showmonth = showmonth;
	}

	public Integer getShowdays() {
		return showdays;
	}

	public void setShowdays(Integer showdays) {
		this.showdays = showdays;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSpuid() {
		return spuid;
	}

	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}

	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

}

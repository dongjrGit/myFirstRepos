package com.yinlian.wap.dto;

import java.math.BigDecimal;

public class OrderdatailsDto {
	private String productnum;

    private String productimg;

    private String productname;

    private BigDecimal productprice;

    private Integer productcount;

	public String getProductnum() {
		return productnum;
	}

	public void setProductnum(String productnum) {
		this.productnum = productnum;
	}

	public String getProductimg() {
		return productimg;
	}

	public void setProductimg(String productimg) {
		this.productimg = productimg;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public BigDecimal getProductprice() {
		return productprice;
	}

	public void setProductprice(BigDecimal productprice) {
		this.productprice = productprice;
	}

	public Integer getProductcount() {
		return productcount;
	}

	public void setProductcount(Integer productcount) {
		this.productcount = productcount;
	}
}

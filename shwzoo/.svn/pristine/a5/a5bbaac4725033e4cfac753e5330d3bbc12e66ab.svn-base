package com.yinlian.api.app.dto;

import java.math.BigDecimal;
import java.util.Date;

import data.ParseUtil;

public class Api_OrderDetaiBaselDto {
	private int skuid;
	private int id;
	private int orderid;
	private String productimg;
	private String productname;
	private int spuid;
	private int status;
	private Date usetime;
	private String usetimetr;
	private Integer count;
	private BigDecimal price;
	private Integer iscomment;
	
	public Integer getIscomment() {
		return iscomment;
	}

	public void setIscomment(Integer iscomment) {
		this.iscomment = iscomment;
	}

	public String getUsetimetr() {
		this.usetimetr=ParseUtil.parseDateToString(usetime, "yyyy-MM-dd");
		return usetimetr;
	}

	public void setUsetimetr(String usetimetr) {
		this.usetimetr = usetimetr;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getUsetime() {
		return usetime;
	}

	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getSpuid() {
		return spuid;
	}

	public void setSpuid(int spuid) {
		this.spuid = spuid;
	}

	public int getSkuid() {
		return skuid;
	}

	public void setSkuid(int skuid) {
		this.skuid = skuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

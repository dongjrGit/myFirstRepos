package com.yinlian.pc.dto;

import java.util.Date;

import data.ParseUtil;

/**
 * 返修退换货信息
 * @author Administrator
 *
 */
public class AfterserviceDto {
	private Integer id;
	
	private String ordercode;
	
	private String productname;
	
	private Date  createtime;
	
	private String createtimetr;
	
	private Integer status;
	
	private Integer orderid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatetimetr() {
		this.createtimetr=ParseUtil.parseDateToString(createtime, "yyyy-MM-dd HH:mm:ss");
		return createtimetr;
	}

	public void setCreatetimetr(String createtimetr) {
		this.createtimetr = createtimetr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	
	
}

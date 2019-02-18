package com.yinlian.api.app.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

/**
 * 团购订单子表信息
 * @author Administrator
 *
 */
public class GroupOrderDetailDto {

	private Integer id;          //订单子表ID
	private Integer orderid;     //订单ID
	private String code;         //团购劵
	private Integer status;      //状态
	private Double price;         //价格
	private Date usetime;        //使用时间
	private Date systemtime;     //系统时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUsetime() {
		return DateUtil.dateConvert(usetime);
	}
	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}
	public String getSystemtime() {
		return DateUtil.dateConvert(systemtime);
	}
	public void setSystemtime(Date systemtime) {
		this.systemtime = systemtime;
	}
	
}

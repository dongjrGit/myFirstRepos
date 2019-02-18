package com.yinlian.wssc.search;

import java.util.Date;
import com.yinlian.wssc.web.util.Criteria;
public class OrderRemindCriteria extends Criteria {
	
	private Integer id;
	
	/**
	 * 订单编号
	 */
	private String ordernum;
	
	/**
	 * 订单id
	 */
	private Integer orderid;
	
	/**
	 * 订单下单时间
	 */
	private Date ordertime;
	
	/**
	 * 供应商id
	 */
	private Integer supplierid;
	
	/**
	 * 商户id
	 */
	private Integer buyerid;
	
	/**
	 * 商户名称
	 */
	private String buyername;
	
	/**
	 * 催单时间
	 */
	private Date createtime;
	
	/**
	 * 是否阅读处理
	 */
	private Boolean isread;
	
	/**
	 * 处理备注说明
	 */
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(Integer supplierid) {
		this.supplierid = supplierid;
	}

	public Integer getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Boolean getIsread() {
		return isread;
	}

	public void setIsread(Boolean isread) {
		this.isread = isread;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

package com.yinlian.wssc.web.dto;

import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.po.Grouporderdetail;
import com.yinlian.wssc.web.util.DateUtil;

/**
 * 团购订单列表
 * @author Administrator
 *
 */
public class OrderGroupByDto {

	private Integer id;
	private Integer buyerid; 
	private Integer shopid; 
	private String ordercode; 
	private Date createtime;	
	private Date starttime; 
	private Date endtime;
	private Integer status; 
	private Float orderprice; 
	private Integer count; 
	private Float payprice;
	private Float discount; 
	private String title;
	private String buyname;
	private String shopname;
	private List<Grouporderdetail> detaillist;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getCreatetime() {
		return DateUtil.dateConvert(this.createtime);
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getStarttime() {
		return DateUtil.dateConvert(this.starttime);
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return DateUtil.dateConvert(this.endtime);
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Float getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(Float orderprice) {
		this.orderprice = orderprice;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Float getPayprice() {
		return payprice;
	}
	public void setPayprice(Float payprice) {
		this.payprice = payprice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBuyname() {
		return buyname;
	}
	public void setBuyname(String buyname) {
		this.buyname = buyname;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public List<Grouporderdetail> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<Grouporderdetail> detaillist) {
		this.detaillist = detaillist;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
}

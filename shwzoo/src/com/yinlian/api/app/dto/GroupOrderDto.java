package com.yinlian.api.app.dto;

import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.util.DateUtil;


/**
 * 团购订单信息
 * @author Administrator
 *
 */
public class GroupOrderDto {

	private Integer id;                                   //订单ID
	private String ordercode;                             //订单编号
	private String title;                                 //团购劵名称
	private Integer ordercount;                           //团购劵数量
	private Double orderprice;                             //订单金额
	private Date startdate;                               //开始时间
	private Date enddate;                                 //结束时间
	private Date createdate;                              //创建时间
	private Integer shopid;                               //所属店铺
	private String shopname;                              //店铺名称
	private String img;                                   //图片
	private List<GroupOrderDetailDto> detaillist;         //子表信息
	private Integer ostatus;                               //订单状态
	private Integer groupbuyid;                           //团购id
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Double getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(Double orderprice) {
		this.orderprice = orderprice;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getStartdate() {
		return DateUtil.dateConvert(this.startdate);
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return DateUtil.dateConvert(this.enddate);
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getCreatedate() {
		return DateUtil.dateConvert(this.createdate);
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<GroupOrderDetailDto> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<GroupOrderDetailDto> detaillist) {
		this.detaillist = detaillist;
	}
	public Integer getOrdercount() {
		return ordercount;
	}
	public void setOrdercount(Integer ordercount) {
		this.ordercount = ordercount;
	}
	public Integer getOstatus() {
		return ostatus;
	}
	public void setOstatus(Integer ostatus) {
		this.ostatus = ostatus;
	}
	public Integer getGroupbuyid() {
		return groupbuyid;
	}
	public void setGroupbuyid(Integer groupbuyid) {
		this.groupbuyid = groupbuyid;
	}
	
}

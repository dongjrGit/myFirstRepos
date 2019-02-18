package com.yinlian.api.app.dto;

/**
 * API团购活动信息
 * @author Administrator
 *
 */
public class GroupBuyDto {

	private Integer id;         //团购ID
	private String title;       //标题
	private Double cprice;	    //现价
	private Double oprice;       //原价
	private String listdesc;    //简介
	private Integer shopid;     //店铺ID
	private String shopname;    //店铺名称
	private String img;         //展示图片
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getCprice() {
		return cprice;
	}
	public void setCprice(Double cprice) {
		this.cprice = cprice;
	}
	public Double getOprice() {
		return oprice;
	}
	public void setOprice(Double oprice) {
		this.oprice = oprice;
	}
	public String getListdesc() {
		return listdesc;
	}
	public void setListdesc(String listdesc) {
		this.listdesc = listdesc;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	
}

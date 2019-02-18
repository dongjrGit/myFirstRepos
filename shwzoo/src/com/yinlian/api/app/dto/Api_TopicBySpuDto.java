package com.yinlian.api.app.dto;

import java.util.List;

import com.yinlian.wssc.web.po.ProductImgs;

public class Api_TopicBySpuDto {
	private Integer topicid;
	private Integer spuid;
	private Integer skuid;
	private String name;
	private String img;
	private Double price;  //售价
	private Double oldPrice; //原价
	private String discount;
	private String shopname;
	
	/**
	 * 销量
	 */
	private Integer salesvolume;
	

	/**
	 * 店铺ID
	 */
	private Integer shopid;
	
	/**
	 * 总评论
	 */
	private Integer commentsum;

	/**
	 * 商品图片
	 */
	private List<ProductImgs> imglist;
	
	/**
	 * 评论数
	 */
	private Integer commentcount;
	
	private String titleshort;
	
	
	public String getTitleshort() {
		return titleshort;
	}

	public void setTitleshort(String titleshort) {
		this.titleshort = titleshort;
	}

	public Integer getSalesvolume() {
		return salesvolume;
	}

	public void setSalesvolume(Integer salesvolume) {
		this.salesvolume = salesvolume;
	}
	
	public Integer getCommentsum() {
		return commentsum;
	}

	public void setCommentsum(Integer commentsum) {
		this.commentsum = commentsum;
	}
	
	public List<ProductImgs> getImglist() {
		return imglist;
	}

	public void setImglist(List<ProductImgs> imglist) {
		this.imglist = imglist;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	
	public Integer getTopicid() {
		return topicid;
	}

	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}

	public Integer getSpuid() {
		return spuid;
	}

	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public String getDiscount() {
		return discount;
//		if (price!=null&&oldPrice!=null) {
//			DecimalFormat decimalFormat=new DecimalFormat(".0");
//			String p=decimalFormat.format(price/oldPrice*10);
//			this.discount=p;
//		}
//		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Integer getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(Integer commentcount) {
		this.commentcount = commentcount;
	}
 

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}
}

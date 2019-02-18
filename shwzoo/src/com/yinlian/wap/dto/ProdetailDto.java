package com.yinlian.wap.dto;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Object;

import com.yinlian.api.app.dto.CommentBaseDto;
import com.yinlian.api.app.dto.IdValue;

public class ProdetailDto {
	
	public  ProdetailDto() {
		imgs=new ArrayList<String>();
	}
	
	/**
	 * 标识Id(sku)
	 */
	private int id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 图片列表
	 */
	private List<String> imgs;

	/**
	 * 是否直营
	 */
	private Boolean isowned;

	/**
	 * 副标题
	 */
	private String subtitle;

	/**
	 * 库存
	 */
	private int stock;

	/**
	 * 闪购id
	 */
	private Integer skiid;
	
	/**
	 * 商品类型
	 */
	private Integer type;
	
	/**
	 * 闪购价格
	 */
	private Double skiprice;
	
	/**
	 * 闪购到期时间
	 */
	private String skitime;
	

	public Integer getSkiid() {
		return skiid;
	}

	public void setSkiid(Integer skiid) {
		this.skiid = skiid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getSkiprice() {
		return skiprice;
	}

	public void setSkiprice(Double skiprice) {
		this.skiprice = skiprice;
	}


	public String getSkitime() {
		return skitime;
	}

	public void setSkitime(String  skitime) {
		this.skitime = skitime;
	}

	/**
	 * 促销活动(备用)
	 */
	private List<Object> activity;

	/**
	 * 已选规格属性列表
	 */
	private List<IdValue> atrrs;

	/**
	 * 店铺Id
	 */
	private  Integer shopid;

	/**
	 * 评论列表（最新3条）
	 */
	private List<CommentBaseDto> cmts;
	/**
	 * 评论数
	 */
	private int cmtcount;
	
	/**
	 * 好评百分比
	 */
	private String cmtgood;
	
	/**
	 * 运费模板id
	 */
	private Integer freightid;
	
	/**
	 * 运费模板描述
	 */
	private String freightdesc;
	
	/**
	 * 是否有优惠卷
	 */
	private Boolean iscoupon;
	
	/**
	 * 是否有组合商品
	 */
	private Boolean  ispackage;
	
	/**
	 * 是否收藏
	 */
	private Boolean isfavorites;
	
	public Boolean getIsfavorites() {
		return isfavorites;
	}

	public void setIsfavorites(Boolean isfavorites) {
		this.isfavorites = isfavorites;
	}

	/**
	 * 分类id
	 */
	private Integer cid;
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Boolean getIspackage() {
		return ispackage;
	}

	public void setIspackage(Boolean ispackage) {
		this.ispackage = ispackage;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public Integer getFreightid() {
		return freightid;
	}

	public void setFreightid(Integer freightid) {
		this.freightid = freightid;
	}

	public String getFreightdesc() {
		return freightdesc;
	}

	public void setFreightdesc(String freightdesc) {
		this.freightdesc = freightdesc;
	}

	public Boolean getIscoupon() {
		return iscoupon;
	}

	public void setIscoupon(Boolean iscoupon) {
		this.iscoupon = iscoupon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public Boolean getIsowned() {
		return isowned;
	}

	public void setIsowned(Boolean isowned) {
		this.isowned = isowned;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Object> getActivity() {
		return activity;
	}

	public void setActivity(List<Object> activity) {
		this.activity = activity;
	}

	public List<IdValue> getAtrrs() {
		return atrrs;
	}

	public void setAtrrs(List<IdValue> atrrs) {
		this.atrrs = atrrs;
	}

	public List<CommentBaseDto> getCmts() {
		return cmts;
	}

	public void setCmts(List<CommentBaseDto> cmts) {
		this.cmts = cmts;
	}

	public int getCmtcount() {
		return cmtcount;
	}

	public void setCmtcount(int cmtcount) {
		this.cmtcount = cmtcount;
	}

	public String getCmtgood() {
		return cmtgood;
	}

	public void setCmtgood(String cmtgood) {
		this.cmtgood = cmtgood;
	}
}

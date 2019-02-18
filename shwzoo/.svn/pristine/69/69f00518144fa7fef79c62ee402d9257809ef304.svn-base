package com.yinlian.api.app.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.DocFlavor.STRING;

import org.omg.CORBA.Object;

import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.util.DateUtil;

public class ProductDetailedDto {

	public ProductDetailedDto() {
		imgs = new ArrayList<String>();
		// List<IdValue> l=new ArrayList<IdValue>();
		// List<IdValue>
		// s=l.stream().filter(x->x.getId()==1&&x.getId()==2).collect(Collectors.toList());
		// Map<Integer,List<IdValue> > sMap=(Map<Integer, List<IdValue>>)
		// atrrs.stream().collect(Collectors.groupingBy(x->x.getId()));
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
	 * 原价
	 */
	private BigDecimal price;
	/**
	 * 图片列表
	 */
	private List<String> imgs;


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

	/**
	 * 商品app图片
	 */
	private String appimg;

	/**
	 * 服务器时间
	 */
	@SuppressWarnings("unused")
	private String servicetime;

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
	private Integer shopid;

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
	private Boolean ispackage;

	/**
	 * 是否收藏
	 */
	private Boolean isfavorites;

	private Integer status;

	private Boolean isdel;

	/**
	 * 店铺名称
	 */
	private String shopname;

	/**
	 * 店铺头像
	 */
	private String shopimgurl;

	/**
	 * 店铺星数
	 */
	private String star;
	
	private Integer skuid;
	
	private List<SkuShowtimeDto> showtimeList;
	
	/**
	 * 店铺类型
	 */
	private Integer shoptype;
	
	private String marketingscope;
	
	private Boolean isnk;
	
	private  String  datestr;
	
	private BigDecimal oldprice;
	private Integer istoday;
	
    public String getDatestr() {
		return datestr;
	}

	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}

	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	public String getMarketingscope() {
		return marketingscope;
	}

	public void setMarketingscope(String marketingscope) {
		this.marketingscope = marketingscope;
	}


	public Integer getShoptype() {
		return shoptype;
	}

	public void setShoptype(Integer shoptype) {
		this.shoptype = shoptype;
	}

	
	public List<SkuShowtimeDto> getShowtimeList() {
		return showtimeList;
	}

	public void setShowtimeList(List<SkuShowtimeDto> showtimeList) {
		this.showtimeList = showtimeList;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getShopimgurl() {
		return shopimgurl;
	}

	public void setShopimgurl(String shopimgurl) {
		this.shopimgurl = shopimgurl;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getAppimg() {
		return appimg;
	}

	public void setAppimg(String appimg) {
		this.appimg = appimg;
	}

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

	public void setSkitime(String skitime) {
		this.skitime = skitime;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
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

	public String getServicetime() {
		return DateUtil.dateConvert(new Date());
	}

	public void setServicetime(String servicetime) {
		this.servicetime = servicetime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsdel() {
		return isdel;
	}

	public void setIsdel(Boolean isdel) {
		this.isdel = isdel;
	}

	public Boolean getIsnk() {
		return isnk;
	}

	public void setIsnk(Boolean isnk) {
		this.isnk = isnk;
	}

	public BigDecimal getOldprice() {
		return oldprice;
	}

	public void setOldprice(BigDecimal oldprice) {
		this.oldprice = oldprice;
	}

	public Integer getIstoday() {
		return istoday;
	}

	public void setIstoday(Integer istoday) {
		this.istoday = istoday;
	}
}

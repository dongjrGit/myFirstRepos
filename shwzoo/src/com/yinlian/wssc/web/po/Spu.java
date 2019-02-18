package com.yinlian.wssc.web.po;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class Spu {
    private Integer    id;

    @NotBlank(message = "商品的名字不能为空")
    private String     name;

    private String     pronum;

    private String     title;

    private String     titleShort;

    private Integer    classid;

    private Integer    userid;

    private String     imgurl;

    private String     imgurlApp;

    private String     imgsmall;

    private String     imgsmallApp;

    private Integer    status;

    private Integer    customcid;

    private String     tag;

    private String     producer;

    private Integer    brandid;

    private Integer    pack;

    private BigDecimal weight;

    private Integer    shelflife;

    private Integer    commentcount;

    private Integer    freightid;

    private Date       createtime;

    private Boolean    isdel;

    private Date       deltime;

    private Integer    deluserid;

    private Boolean    isowned;

    private Integer    shopid;

    private String     fullpath;

    private BigDecimal      price; //售价

    private Integer    praise;

    private Integer    mediumreview;

    private Integer    badreview;

    private String     fullpathname;  // 全路径的名字

    private Integer    salescount;
    
    private Date shelvetime;          //商品最近上架时间
     
    private BigDecimal      appprice;      //商品app价格
    private BigDecimal      wapprice;      //商品WAP价格
    private BigDecimal      wechatprice;   //商品微信端价格
    
    private String imgurlWap;           //wap图片
    private String imgurlWechat;        //微信图片
    private Boolean  ispostage;         //是否包邮
    private Integer isoffer;   //折扣
    
    private Integer  protype;
    
    private BigDecimal      oldprice; //原价
    
    private Integer  istoday;
    
    public BigDecimal getOldprice() {
		return oldprice;
	}

	public void setOldprice(BigDecimal oldprice) {
		this.oldprice = oldprice;
	}

	public String getImgurlWap() {
		return imgurlWap;
	}

	public void setImgurlWap(String imgurlWap) {
		this.imgurlWap = imgurlWap;
	}

	public String getImgurlWechat() {
		return imgurlWechat;
	}

	public void setImgurlWechat(String imgurlWechat) {
		this.imgurlWechat = imgurlWechat;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPronum() {
        return pronum;
    }

    public void setPronum(String pronum) {
        this.pronum = pronum == null ? null : pronum.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort == null ? null : titleShort.trim();
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getImgurlApp() {
        return imgurlApp;
    }

    public void setImgurlApp(String imgurlApp) {
        this.imgurlApp = imgurlApp == null ? null : imgurlApp.trim();
    }

    public String getImgsmall() {
        return imgsmall;
    }

    public void setImgsmall(String imgsmall) {
        this.imgsmall = imgsmall == null ? null : imgsmall.trim();
    }

    public String getImgsmallApp() {
        return imgsmallApp;
    }

    public void setImgsmallApp(String imgsmallApp) {
        this.imgsmallApp = imgsmallApp == null ? null : imgsmallApp.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCustomcid() {
        return customcid;
    }

    public void setCustomcid(Integer customcid) {
        this.customcid = customcid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getPack() {
        return pack;
    }

    public void setPack(Integer pack) {
        this.pack = pack;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getShelflife() {
        return shelflife;
    }

    public void setShelflife(Integer shelflife) {
        this.shelflife = shelflife;
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    public Integer getFreightid() {
        return freightid;
    }

    public void setFreightid(Integer freightid) {
        this.freightid = freightid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Date getDeltime() {
        return deltime;
    }

    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    public Integer getDeluserid() {
        return deluserid;
    }

    public void setDeluserid(Integer deluserid) {
        this.deluserid = deluserid;
    }

    public Boolean getIsowned() {
        return isowned;
    }

    public void setIsowned(Boolean isowned) {
        this.isowned = isowned;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath == null ? null : fullpath.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getMediumreview() {
        return mediumreview;
    }

    public void setMediumreview(Integer mediumreview) {
        this.mediumreview = mediumreview;
    }

    public Integer getBadreview() {
        return badreview;
    }

    public void setBadreview(Integer badreview) {
        this.badreview = badreview;
    }

    /**
     * Getter method for property <tt>fullpathname</tt>.
     * 
     * @return property value of fullpathname
     */
    public String getFullpathname() {
        return fullpathname;
    }

    /**
     * Setter method for property <tt>fullpathname</tt>.
     * 
     * @param fullpathname value to be assigned to property fullpathname
     */
    public void setFullpathname(String fullpathname) {
        this.fullpathname = fullpathname;
    }

    public Integer getSalescount() {
        return salescount;
    }

    public void setSalescount(Integer salescount) {
        this.salescount = salescount;
    }

	public Date getShelvetime() {
		return shelvetime;
	}

	public void setShelvetime(Date shelvetime) {
		this.shelvetime = shelvetime;
	}

	public BigDecimal getAppprice() {
		return appprice;
	}

	public void setAppprice(BigDecimal appprice) {
		this.appprice = appprice;
	}

	public BigDecimal getWapprice() {
		return wapprice;
	}

	public void setWapprice(BigDecimal wapprice) {
		this.wapprice = wapprice;
	}

	public BigDecimal getWechatprice() {
		return wechatprice;
	}

	public void setWechatprice(BigDecimal wechatprice) {
		this.wechatprice = wechatprice;
	}
	 
	public Boolean getIspostage() {
	    return ispostage;
	}

	public void setIspostage(Boolean ispostage) {
	    this.ispostage = ispostage;
	}

	public Integer getIsoffer() {
		return isoffer;
	}

	public void setIsoffer(Integer isoffer) {
		this.isoffer = isoffer;
	}

	public Integer getProtype() {
		return protype;
	}

	public void setProtype(Integer protype) {
		this.protype = protype;
	}

	public Integer getIstoday() {
		return istoday;
	}

	public void setIstoday(Integer istoday) {
		this.istoday = istoday;
	}
	
	
}
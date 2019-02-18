package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Coupon {
    private Integer id;

    private String couponnum;

    private String couponname;

    private Float facevalue;

    private Integer conponcount;

    private Integer couponissuetype;

    private Integer couponusetype;

    private Integer usetypeid;

    private Integer coupontype;

    private Float fullreductionvalue;

    private Integer createuserid;

    private Date createtime;

    private Date providetime;

    private Integer status;

    private Integer endday;

    private Date endtime;

    private Integer getuserlevel;

    private Boolean isdel;

    private Integer deluserid;

    private Date deltime;

    private Integer gettype;

    private Integer getcount;

    private Integer shopid;
    
    private Integer fromtype;
    
    private String groupcode;
    
    private String useplatform;

	private String providetimestr;
    
    private String endtimestr;
    private String shopname;

    private String imgurl;
    private String imgurlApp;
    
    private String spuname;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponnum() {
        return couponnum;
    }

    public void setCouponnum(String couponnum) {
        this.couponnum = couponnum == null ? null : couponnum.trim();
    }

    public String getCouponname() {
        return couponname;
    }

    public void setCouponname(String couponname) {
        this.couponname = couponname == null ? null : couponname.trim();
    }

    public Float getFacevalue() {
        return facevalue;
    }

    public void setFacevalue(Float facevalue) {
        this.facevalue = facevalue;
    }

    public Integer getConponcount() {
        return conponcount;
    }

    public void setConponcount(Integer conponcount) {
        this.conponcount = conponcount;
    }

    public Integer getCouponissuetype() {
        return couponissuetype;
    }

    public void setCouponissuetype(Integer couponissuetype) {
        this.couponissuetype = couponissuetype;
    }

    public Integer getCouponusetype() {
        return couponusetype;
    }

    public void setCouponusetype(Integer couponusetype) {
        this.couponusetype = couponusetype;
    }

    public Integer getUsetypeid() {
        return usetypeid;
    }

    public void setUsetypeid(Integer usetypeid) {
        this.usetypeid = usetypeid;
    }

    public Integer getCoupontype() {
        return coupontype;
    }

    public void setCoupontype(Integer coupontype) {
        this.coupontype = coupontype;
    }

    public Float getFullreductionvalue() {
        return fullreductionvalue;
    }

    public void setFullreductionvalue(Float fullreductionvalue) {
        this.fullreductionvalue = fullreductionvalue;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getProvidetime() {
        return providetime;
    }

    public void setProvidetime(Date providetime) {
        this.providetime = providetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEndday() {
        return endday;
    }

    public void setEndday(Integer endday) {
        this.endday = endday;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getGetuserlevel() {
        return getuserlevel;
    }

    public void setGetuserlevel(Integer getuserlevel) {
        this.getuserlevel = getuserlevel;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Integer getDeluserid() {
        return deluserid;
    }

    public void setDeluserid(Integer deluserid) {
        this.deluserid = deluserid;
    }

    public Date getDeltime() {
        return deltime;
    }

    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    public Integer getGettype() {
        return gettype;
    }

    public void setGettype(Integer gettype) {
        this.gettype = gettype;
    }

    public Integer getGetcount() {
        return getcount;
    }

    public void setGetcount(Integer getcount) {
        this.getcount = getcount;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

	public String getProvidetimestr() {
		 this.providetimestr = DateUtil.dateConvert(this.providetime);
	        return providetimestr;
	}

	public void setProvidetimestr(String providetimestr) {
		this.providetimestr = providetimestr;
	}

	public String getEndtimestr() {
		 this.endtimestr = DateUtil.dateConvert(this.endtime);
	        return endtimestr;
	}

	public void setEndtimestr(String endtimestr) {
		this.endtimestr = endtimestr;
	}

	public Integer getFromtype() {
		return fromtype;
	}

	public void setFromtype(Integer fromtype) {
		this.fromtype = fromtype;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
    public String getUseplatform() {
		return useplatform;
	}

	public void setUseplatform(String useplatform) {
		this.useplatform = useplatform;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getImgurlApp() {
		return imgurlApp;
	}

	public void setImgurlApp(String imgurlApp) {
		this.imgurlApp = imgurlApp;
	}

	public String getSpuname() {
		return spuname;
	}

	public void setSpuname(String spuname) {
		this.spuname = spuname;
	}

	 
	
}
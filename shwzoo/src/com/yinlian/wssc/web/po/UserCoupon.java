package com.yinlian.wssc.web.po;

import java.util.Date;

public class UserCoupon {
    private Integer id;

    private Integer couponid;

    private Integer userid;

    private Boolean isuser;

    private Date usetime;

    private String userdesc;

    private Date gettime;

    private Boolean isdel;

    private Date deltime;

    private Integer deluserid;

    private Date outtime;
    
    private Integer fromtype;
    private Integer voucherid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Boolean getIsuser() {
        return isuser;
    }

    public void setIsuser(Boolean isuser) {
        this.isuser = isuser;
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    public String getUserdesc() {
        return userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc == null ? null : userdesc.trim();
    }

    public Date getGettime() {
        return gettime;
    }

    public void setGettime(Date gettime) {
        this.gettime = gettime;
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

    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date endday) {
        this.outtime = endday;
    }

	public Integer getFromtype() {
		return fromtype;
	}

	public void setFromtype(Integer fromtype) {
		this.fromtype = fromtype;
	}

	public Integer getVoucherid() {
		return voucherid;
	}

	public void setVoucherid(Integer voucherid) {
		this.voucherid = voucherid;
	}
}
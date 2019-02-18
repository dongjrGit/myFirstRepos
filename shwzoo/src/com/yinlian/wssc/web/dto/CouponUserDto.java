package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class CouponUserDto {
	  private Integer id;                      //优惠卷ID
      private String couponnum;                //编号
      private String couponname;               //名称
      private Float facevalue;                 //面额
      private Integer conponcount;             //数量
      private Integer couponissuetype;         //发放平台
      private Integer couponusetype;           //使用对象
      private Integer usetypeid;               //使用对象ID
      private Integer coupontype;              //类型
      private Float fullreductionvalue;        //满减金额
      private Date providetime;                //开始时间
      private Integer status;                  //状态
      private Date endtime;                    //结束时间
      private Integer getuserlevel;            //用户等级限制
      private Integer endday;                  //有效期
      private Integer gettype;                 //获取类型
      private Integer getcount;                //获取数量
      private Integer shopid;                  //所属店铺id
      private Integer isget;                   //是否领取
      private Integer getcouponcount;          //领取数量
      private Date gettime;                    //领取时间
      private Integer isuse;                   //是否使用
      private Date usetime;                    //使用时间
      private Integer usecouponcount;          //使用数量
      
    //日期格式转换
  	private String providetimestr;  //开始时间
  	private String endtimestr;  //结束时间
  	private String gettimestr;  //领用时间
  	private String usetimestr;  //使用时间
      
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
		this.couponnum = couponnum;
	}
	public String getCouponname() {
		return couponname;
	}
	public void setCouponname(String couponname) {
		this.couponname = couponname;
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
	public Integer getEndday() {
		return endday;
	}
	public void setEndday(Integer endday) {
		this.endday = endday;
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
	public Integer getIsget() {
		return isget;
	}
	public void setIsget(Integer isget) {
		this.isget = isget;
	}
	public Integer getGetcouponcount() {
		return getcouponcount;
	}
	public void setGetcouponcount(Integer getcouponcount) {
		this.getcouponcount = getcouponcount;
	}
	public Date getGettime() {
		return gettime;
	}
	public void setGettime(Date gettime) {
		this.gettime = gettime;
	}
	public Integer getIsuse() {
		return isuse;
	}
	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}
	public Date getUsetime() {
		return usetime;
	}
	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}
	public Integer getUsecouponcount() {
		return usecouponcount;
	}
	public void setUsecouponcount(Integer usecouponcount) {
		this.usecouponcount = usecouponcount;
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
	public String getGettimestr() {
		 this.gettimestr = DateUtil.dateConvert(this.gettime);
	        return gettimestr;
	}
	public void setGettimestr(String gettimestr) {
		this.gettimestr = gettimestr;
	}
	public String getUsetimestr() {
		this.usetimestr = DateUtil.dateConvert(this.usetime);
       return usetimestr;
	}
	public void setUsetimestr(String usetimestr) {
		this.usetimestr = usetimestr;
	}
}

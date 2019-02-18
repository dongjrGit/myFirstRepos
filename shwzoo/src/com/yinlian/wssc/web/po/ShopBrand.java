package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class ShopBrand {
    private Integer id;

    private Integer shopid;

    private Integer brandid;

    private Integer checkstatus;

    private Integer checkuserid;

    private Date    checktime;

    private Date    createtime;

    private Boolean isdel;

    private Date    deltime;

    private Integer deluserid;

    private String  creattimestr; //创建时间

    private String  checktimestr; // 审核时间

    private String  shopname;    //店铺名称

    private Boolean flag;        //是否旗舰

    private String  brandname;   //品牌名称

    private String  description; //品牌的描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }

    public Integer getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(Integer checkuserid) {
        this.checkuserid = checkuserid;
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
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

    /**
     * Getter method for property <tt>creattimestr</tt>.
     * 
     * @return property value of creattimestr
     */
    public String getCreattimestr() {
        this.creattimestr = DateUtil.dateConvert(this.createtime);
        return creattimestr;
    }

    /**
     * Setter method for property <tt>creattimestr</tt>.
     * 
     * @param creattimestr value to be assigned to property creattimestr
     */
    public void setCreattimestr(String creattimestr) {
        this.creattimestr = creattimestr;
    }

    /**
     * Getter method for property <tt>checktimestr</tt>.
     * 
     * @return property value of checktimestr
     */
    public String getChecktimestr() {
        this.checktimestr = DateUtil.dateConvert(this.checktime);
        return checktimestr;
    }

    /**
     * Setter method for property <tt>checktimestr</tt>.
     * 
     * @param checktimestr value to be assigned to property checktimestr
     */
    public void setChecktimestr(String checktimestr) {
        this.checktimestr = checktimestr;
    }

    /**
     * Getter method for property <tt>shopname</tt>.
     * 
     * @return property value of shopname
     */
    public String getShopname() {
        return shopname;
    }

    /**
     * Setter method for property <tt>shopname</tt>.
     * 
     * @param shopname value to be assigned to property shopname
     */
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    /**
     * Getter method for property <tt>flag</tt>.
     * 
     * @return property value of flag
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * Setter method for property <tt>flag</tt>.
     * 
     * @param flag value to be assigned to property flag
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * Getter method for property <tt>brandname</tt>.
     * 
     * @return property value of brandname
     */
    public String getBrandname() {
        return brandname;
    }

    /**
     * Setter method for property <tt>brandname</tt>.
     * 
     * @param brandname value to be assigned to property brandname
     */
    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    /**
     * Getter method for property <tt>description</tt>.
     * 
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for property <tt>description</tt>.
     * 
     * @param description value to be assigned to property description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
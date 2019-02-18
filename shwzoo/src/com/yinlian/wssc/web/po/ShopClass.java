package com.yinlian.wssc.web.po;

import java.util.Date;

public class ShopClass {
    private Integer id;

    private Integer shopid;

    private Integer classid;

    private String classfullpath;

    private Date createtime;

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

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassfullpath() {
        return classfullpath;
    }

    public void setClassfullpath(String classfullpath) {
        this.classfullpath = classfullpath == null ? null : classfullpath.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
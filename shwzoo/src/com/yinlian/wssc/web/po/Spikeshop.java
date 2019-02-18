package com.yinlian.wssc.web.po;

import java.util.Date;

public class Spikeshop {
    private Integer id;

    private Integer spikeid;

    private Integer shopid;

    private Integer status;

    private Date createtime;

    private Integer createuserid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpikeid() {
        return spikeid;
    }

    public void setSpikeid(Integer spikeid) {
        this.spikeid = spikeid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }
}
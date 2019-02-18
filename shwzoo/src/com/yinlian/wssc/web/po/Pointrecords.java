package com.yinlian.wssc.web.po;

import java.util.Date;

public class Pointrecords {
    private Integer id;

    private Integer userid;

    private Integer points;

    private Integer tradetype;
    
    private Integer type;
    
    private Integer fromtype;

    private Integer beforepoints;

    private Date tradetime;

    private String description;

    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getFromType() {
        return fromtype;
    }

    public void setFromType(Integer fromtype) {
        this.fromtype = fromtype;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getTradetype() {
        return tradetype;
    }

    public void setTradetype(Integer tradetype) {
        this.tradetype = tradetype;
    }

    public Integer getBeforepoints() {
        return beforepoints;
    }

    public void setBeforepoints(Integer beforepoints) {
        this.beforepoints = beforepoints;
    }

    public Date getTradetime() {
        return tradetime;
    }

    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
package com.yinlian.wssc.web.po;

import java.util.Date;

public class Permission {
    private Integer id;

    private String code;

    private String name;

    private Integer level;

    private Integer fid;

    private Boolean isdel;

    private Integer shopid;

    private Integer type;

    private Integer status;

    private String urlCname;

    private String urlEname;

    private String description;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrlCname() {
        return urlCname;
    }

    public void setUrlCname(String urlCname) {
        this.urlCname = urlCname == null ? null : urlCname.trim();
    }

    public String getUrlEname() {
        return urlEname;
    }

    public void setUrlEname(String urlEname) {
        this.urlEname = urlEname == null ? null : urlEname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
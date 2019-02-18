/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.po;

import java.util.Date;

/**
 * Imgrecords.java
 * @author Administrator
 * @version $Id: Imgrecords.java, v 0.1 2016年3月23日 下午5:23:03 Administrator Exp $
 */
public class Imgrecords {

    private Integer id;

    private String  groupname;

    private String  servername;

    private String  localname;

    private Integer filetype;

    private Integer relationid;

    private Date    createtime;

    private Integer status;

    private Integer relationtype;

    private String  url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername == null ? null : servername.trim();
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname == null ? null : localname.trim();
    }

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public Integer getRelationid() {
        return relationid;
    }

    public void setRelationid(Integer relationid) {
        this.relationid = relationid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRelationtype() {
        return relationtype;
    }

    public void setRelationtype(Integer relationtype) {
        this.relationtype = relationtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}

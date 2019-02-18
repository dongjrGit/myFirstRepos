package com.yinlian.wssc.web.po;

import java.util.Date;

import data.ParseUtil;

public class Lottery {
    private Integer id;

    private Integer userid;

    private String mobile;

    private Integer mark;

    private String description;

    private Date creattime;

    private Boolean isdel;

    private Integer status;
    
    private String username;
    
    private  String createtimetr;
    
   	public String getCreatetimetr() {
   		this.createtimetr=ParseUtil.parseDateToString(creattime, "yyyy-MM-dd HH:mm:ss");
   		return createtimetr;
   	}

   	public void setCreatetimetr(String createtimetr) {
   		this.createtimetr = createtimetr;
   	}

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
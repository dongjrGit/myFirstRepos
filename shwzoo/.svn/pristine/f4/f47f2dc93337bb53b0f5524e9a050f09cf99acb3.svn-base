package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Role {
    private Integer id;

    private Integer shopid;

    private String name;

    private String description;

    private Date createtime;

    private Integer status;

    private Boolean isdel;

    private Boolean isbuiltin;
    
    private String  creattimestr;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Boolean getIsbuiltin() {
        return isbuiltin;
    }

    public void setIsbuiltin(Boolean isbuiltin) {
        this.isbuiltin = isbuiltin;
    }

	public String getCreattimestr() {
		 this.creattimestr = DateUtil.dateConvert(this.createtime);
	        return creattimestr;
	}

	public void setCreattimestr(String creattimestr) {
		this.creattimestr = creattimestr;
	}
}
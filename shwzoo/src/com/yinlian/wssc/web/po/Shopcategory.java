package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Shopcategory {
    private Integer id;

    private Integer shopid;

    private String name;

    private Date creattime;

    private Boolean isdel;
    
    private String shopname;
    
    private String creattimeStr;

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

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getCreattimeStr() {
		this.creattimeStr = DateUtil.dateConvert(creattime);
		return creattimeStr;
	}

	public void setCreattimeStr(String creattimeStr) {
		this.creattimeStr = creattimeStr;
	}
    
}
package com.yinlian.wssc.web.po;

import java.math.BigDecimal;
import java.util.Date;

public class Shopcartpros {
    private Integer id;

    private Integer shopcartid;

    private Integer type;

    private Integer proid;

    private String  proname;

    private Integer procount;

    private String  specsinfo;

    private Integer marketid;

    private String  packpros;

    private Integer spuid;

    private Integer userid;

    private Integer isselected;

    private Integer spikeid;

    private Integer shopid;
    
    private Date usetime;
    
    private  BigDecimal  price;
    
    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getUsetime() {
		return usetime;
	}

	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopcartid() {
        return shopcartid;
    }

    public void setShopcartid(Integer shopcartid) {
        this.shopcartid = shopcartid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    public Integer getProcount() {
        return procount;
    }

    public void setProcount(Integer procount) {
        this.procount = procount;
    }

    public String getSpecsinfo() {
        return specsinfo;
    }

    public void setSpecsinfo(String specsinfo) {
        this.specsinfo = specsinfo == null ? null : specsinfo.trim();
    }

    public Integer getMarketid() {
        return marketid;
    }

    public void setMarketid(Integer marketid) {
        this.marketid = marketid;
    }

    public String getPackpros() {
        return packpros;
    }

    public void setPackpros(String packpros) {
        this.packpros = packpros == null ? null : packpros.trim();
    }

    public Integer getSpuid() {
        return spuid;
    }

    public void setSpuid(Integer spuid) {
        this.spuid = spuid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getIsselected() {
        return isselected;
    }

    public void setIsselected(Integer isselected) {
        this.isselected = isselected;
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
}
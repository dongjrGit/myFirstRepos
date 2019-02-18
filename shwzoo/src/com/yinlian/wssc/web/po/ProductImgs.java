package com.yinlian.wssc.web.po;

import java.util.Date;

public class ProductImgs {
    private Integer id;

    private String  imgurl;

    private Integer status;

    private Integer orderby;

    private Date    addtime;

    private Integer skuId;

    private Integer spuId;

    private String imgsite;
    
    private String imgpc;
    private String imgapp;
    private String imgwap;
    private String imgwechat;    

    public String getImgpc() {
		return imgpc;
	}

	public void setImgpc(String imgpc) {
		this.imgpc = imgpc;
	}

	public String getImgapp() {
		return imgapp;
	}

	public void setImgapp(String imgapp) {
		this.imgapp = imgapp;
	}

	public String getImgwap() {
		return imgwap;
	}

	public void setImgwap(String imgwap) {
		this.imgwap = imgwap;
	}

	public String getImgwechat() {
		return imgwechat;
	}

	public void setImgwechat(String imgwechat) {
		this.imgwechat = imgwechat;
	}

	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }
	public String getImgsite() {
		return imgsite;
	}

	public void setImgsite(String imgsite) {
		this.imgsite = imgsite;
	}
}
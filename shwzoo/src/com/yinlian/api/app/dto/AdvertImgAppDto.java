/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.dto;

/**
 * AdvertImgAppDto.java
 * @author Liang.ma.s
 * @version $Id: AdvertImgAppDto.java, v 0.1 2016年4月12日 下午6:13:49 Administrator Exp $
 */
public class AdvertImgAppDto {

    private Integer id;
    private String  imgurl;
    private String  url;
    private Integer pagemark;
    private Integer position;
    private Integer sort;
    private Integer type;
    private Integer typeid;
    private String typename;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>imgurl</tt>.
     * 
     * @return property value of imgurl
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * Setter method for property <tt>imgurl</tt>.
     * 
     * @param imgurl value to be assigned to property imgurl
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * Getter method for property <tt>url</tt>.
     * 
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property <tt>url</tt>.
     * 
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter method for property <tt>position</tt>.
     * 
     * @return property value of position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * Setter method for property <tt>position</tt>.
     * 
     * @param position value to be assigned to property position
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getPagemark() {
		return pagemark;
	}

	public void setPagemark(Integer pagemark) {
		this.pagemark = pagemark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}

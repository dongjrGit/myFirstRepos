/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.dto;

/**
 * SpuDto.java
 * @author Liang.ma.s
 * @version $Id: SpuDto.java, v 0.1 2016年4月12日 下午4:35:00 Administrator Exp $
 */
public class SpuDto {

    private Integer id;       // 
    private Float   price;
    private String  name;
    private String  imgurlApp;
    private String  imgurl;

    public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

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
     * Getter method for property <tt>price</tt>.
     * 
     * @return property value of price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Setter method for property <tt>price</tt>.
     * 
     * @param price value to be assigned to property price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>imgurlApp</tt>.
     * 
     * @return property value of imgurlApp
     */
    public String getImgurlApp() {
        return imgurlApp;
    }

    /**
     * Setter method for property <tt>imgurlApp</tt>.
     * 
     * @param imgurlApp value to be assigned to property imgurlApp
     */
    public void setImgurlApp(String imgurlApp) {
        this.imgurlApp = imgurlApp;
    }

}

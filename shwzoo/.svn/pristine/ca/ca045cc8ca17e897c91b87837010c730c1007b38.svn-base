/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.dto;

import java.util.List;

import com.yinlian.wssc.web.po.Category;

/**
 * 
 * @author Administrator
 * @version $Id: ClassDTO.java, v 0.1 2016年2月26日 下午2:02:20 Administrator Exp $
 */
public class CategoryDTO extends Category {

    private List<CategoryDTO> children;
    //自定义分类全路径名称
    private String fullname;
   //自定义分类 所属店铺名称
    private String shopname;
    
    private String imageUrl;
    
    /**
     * Getter method for property <tt>children</tt>.
     * 
     * @return property value of children
     */
    public List<CategoryDTO> getChildren() {
        return children;
    }

    /**
     * Setter method for property <tt>children</tt>.
     * 
     * @param children value to be assigned to property children
     */
    public void setChildren(List<CategoryDTO> children) {
        this.children = children;
    }
    public String getImageUrl() {
		return imageUrl;
	}
    
    public void setImageUrl() {
		this.imageUrl = super.getImageurl();
	}
    	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

}

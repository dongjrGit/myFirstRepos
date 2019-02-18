/*
 * YinLian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.Enums;

/**
 * 服务反馈类型的枚举
 * FeedBackTypeEnum.java
 * @author Administrator
 * @version $Id: FeedBackTypeEnum.java, v 0.1 2016年4月1日 上午10:57:30 Administrator Exp $
 */
public enum FeedBackTypeEnum {

    功能意见(0);

    private int value;

    /**
     * @param value
     */
    private FeedBackTypeEnum(int value) {
        this.value = value;
    }

    /**
     * Getter method for property <tt>value</tt>.
     * 
     * @return property value of value
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter method for property <tt>value</tt>.
     * 
     * @param value value to be assigned to property value
     */
    public void setValue(int value) {
        this.value = value;
    }

}

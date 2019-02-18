/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.Enums;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年5月10日 下午4:09:05 Exp $
 */
public enum ShopSortEnum {

    距离("0"), 评价("1"), 人气("2"), 人均消费("3");

    private final String value;

    public String getValue() {
        return value;
    }

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ShopSortEnum(String value) {
        this.value = value;
    }
}

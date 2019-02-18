/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.Enums;

/**
 * 广告类型的枚举
 * AdvertImgTypeEnum.java
 * @author Liang.ma.s
 * @version $Id: AdvertImgTypeEnum.java, v 0.1 2016年4月9日 上午10:56:54 Administrator Exp $
 */
public enum AdvertImgTypeEnum {

    首页(1), 店铺(2), 团购(3), 分类(4),经彩活动(5);
    private final int value;

    public int getValue() {
        return value;
    }

    AdvertImgTypeEnum(int value) {
        this.value = value;
    }
}

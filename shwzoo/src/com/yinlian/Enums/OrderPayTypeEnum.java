/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.Enums;

/**
 * 订单支付的类型
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月29日 上午11:08:11 Exp $
 */
public enum OrderPayTypeEnum {

    支付(0), 充值(1);
    private final int value;

    public int getValue() {
        return value;
    }

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    OrderPayTypeEnum(int value) {
        this.value = value;
    }
}

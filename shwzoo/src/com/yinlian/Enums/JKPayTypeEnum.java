/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.Enums;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月29日 上午11:13:25 Exp $
 */
public enum JKPayTypeEnum {

    余额支付("01"), 快捷支付("02"), 浦发银行("03"), 支付宝("04"), 现金("05"), 电汇("06"), 支票("07"), 内部("08"), POS("09");
    private final String value;

    public String getValue() {
        return value;
    }

    JKPayTypeEnum(String value) {
        this.value = value;
    }
}

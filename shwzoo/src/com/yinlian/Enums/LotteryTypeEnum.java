/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.Enums;

/**
 * LotteryTypeEnum.java
 * @author Liang.ma.s
 * @version $Id: LotteryTypeEnum.java, v 0.1 2016年4月11日 上午11:40:24 Administrator Exp $
 */
public enum LotteryTypeEnum {

    满额送礼券("满额送礼券"), 满减优惠券("满减优惠券"), 积分("积分"), 不要灰心("不要灰心"), 神马也没有("神马也没有"), 祝您好运("祝您好运"), 再接再厉(
                                                                                               "再接再厉"), 运气先攒着(
                                                                                                              "运气先攒着"), 要加油哦(
                                                                                                                             "要加油哦"), 谢谢参与(
                                                                                                                                           "谢谢参与");
    private final String value;

    public String getValue() {
        return value;
    }

    LotteryTypeEnum(String value) {
        this.value = value;
    }
}

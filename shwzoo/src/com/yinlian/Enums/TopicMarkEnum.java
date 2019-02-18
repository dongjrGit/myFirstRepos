/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.Enums;

/**
 * TopicTypeEnum.java
 * @author Liang.ma.s
 * @version $Id: TopicTypeEnum.java, v 0.1 2016年4月7日 下午4:11:22 Administrator Exp $
 */
public enum TopicMarkEnum {

    所有(0),
    精彩专题(1), 
    发现好店(2), 
    新品尝鲜(3), 
    发现好货(4),
    精选推荐(5),
    超值特卖(6),
    爆款好货(7),
    天天优惠(8),
    猜你喜欢(9),
    优惠券热门推荐(10),
    会员推荐(11),
    地方馆(12),
    特色大全(13),
    限时抢购(14),
    每日鲜(15),
    包邮直送(16),
    品牌街(17),
    独家品牌(18),
    值得购(19),
    每日鲜商品推荐(20),
    包邮直送商品推荐(21),
    热卖推荐(22),
    今日特价(23);
    private final int value;

    public int getValue() {
        return value;
    }

    TopicMarkEnum(int value) {
        this.value = value;
    }
}

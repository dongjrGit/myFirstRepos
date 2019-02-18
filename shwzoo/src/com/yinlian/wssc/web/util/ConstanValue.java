/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

/**
 * 常量接口
 * @author Administrator
 * @version $Id: ConstanValue.java, v 0.1 2016年3月25日 下午3:07:55 Administrator Exp $
 */
public interface ConstanValue {

    /**
     * token key
     */
    public static final String TOKEN_KEY               = "_u_token";

    /**
     * 验证码key
     */
    public static final String VALIDATA_CODE           = "vCode";

    /**
     * 显示平台登录的地址
     */
    public static final String PLATFORM_LOGIN_URL      = "/platform/login";

    /**
     * 平台登录接口地址
     */
    public static final String PLATFORM_TOLOGIN_URL    = "/platform/user/toLogin";

    /**
     * 平台右边框架页
     */
    public static final String PLATFORM_INDEX_RIGTHURL = "/platform/menu/right";

    /**
     * 卖家显示登录的地址
     */
    public static final String SELLER_LOGIN_URL        = "/seller/login";

    /**
     * 显示注册地址
     */
    public static final String SELLER_REGISTER_URL     = "/seller/register";
    /**
     * 卖家注册的地址
     */
    public static final String SELLER_TOREGISTER_URL   = "/seller/user/toRegister";

    /**
     * 注册成功页
     */
    public static final String SELLER_REGSUCCESS_URL = "/seller/regsuccess";
    /**
     * 卖家登录接口地址
     */
    public static final String SELLER_TOLOGIN_URL      = "/seller/user/toLogin";

    public static final String SELLER_LEFT_URL         = "/seller/letf";

    /**
     * 卖家首页地址
     */
    public static final String SELLER_HOME_URL         = "/seller/home";

    /**
     * 平台标识
     */
    public static final String PLATFORM_MARK           = "platform_mark";

    /**
     * 卖家标识
     */
    public static final String SELLER_MARK             = "seller_mark";

    // redis 属性配置

    /**
     * 最大活动的对象个数
     */
    public static final int    MaxActive               = 500;

    /**
     * 对象最大空闲时间
     */
    public static final int    MaxIdle                 = 1000 * 60;

    /**
     * 获取对象时最大等待时间
     */
    public static final int    MaxWait                 = 1000 * 10;

    /**
     * redis 服务地址
     */
    public static final String REDIS_SERVER_HOST       = "192.168.1.53";

    /**
     * redis 服务端口
     */
    public static final int    PORT                    = 5379;

    /**
     * 普通的配置,计算评分的 星级的个数 
     */
    public static final int    COMMON_STAT             = 5;

    /**
     * 方圆10公里的范围
     */
    public static final String DISTANCE                = "10";

    /**
     * 一等奖唯一标识
     */
    public static final String FIRST_PRIZE             = "1";

    /**
     * 二等奖唯一标识
     */
    public static final String SECOND_PRIZE            = "2";

    /**
     * 三等奖唯一标识
     */
    public static final String THRID_PRIZE             = "3";

    /**
     * 没中奖的唯一标识
     */
    public static final String NO_PRIZE                = "10";

    /**
     * webservice 服务地址
     */
    public static final String WESERVICE_URL_SERVICE   = "http://impl.service.webservice.cheetah.com/";

    /**
     * 商户id
     */
    public static final String EBUSINESSID             = "1263906";

    /**
     * api key
     */
    public static final String APPKEY                  = "5e116ef7-a349-4730-bcf2-40bf1a1a80eb";

    /**
     * 快递100 查询地址
     */
    public static final String REQURL                  = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";

    /**
     * 物流轨迹查询的请求指令类型
     */
    public static final String REQUESTTYPE             = "1002";

    /**
     * 加密类型
     */
    public static final String MD5                     = "MD5";

    /**
     * 支付的地址
     */
    public static final String PAY_SERVICE_URL         = "http://58.30.232.151:8081/jkpay/orderToPay/toPay";

    /**
     * 支付请求的类型
     */
    public static final String PAY_REQUESTTYPE         = "P100001";

    /**
     * 回调地址
     */
    public static final String PAY_RETURN_URL          = "Http://192.168.1.52/app/api/pay/backurl";

    /**
     * token key
     */
    public static final String Shop_Status             = "0";

}

/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

/**
 * 支付的实体类
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月29日 上午10:21:40 Exp $
 */
public class PayEntity {

    private String  orderNo;     // 订单号:要支付订单的平台订单号
    private float   payMoney;    // 支付金额:买家实际支付的金额（不包括经彩豆和代金券的金额）
    private Integer beanNumber;  // 经彩豆数量:使用到时填写
    private String  voucherCode; // 代金券代码:可填写多条使用“,”分割
    private float   receivable;  // 卖家应收入总金额
    private String  merchantName; //卖家账户名
    private String  userName;    //买家账户名
    private String  payType;     // 支付方式：01-余额支付、02-快捷支付、03-浦发银行 、04-支付宝 、05-现金 、06-电汇、07-支票
                                  // 、08-内部账、09-POS刷卡

    private String  checkCode;   // 支票/电汇号码：如果支付类型为支票/电汇 必须填写该号码
    private Integer orderType;   // 订单类型：订单类型：0-支付、1-充值

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(float payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getBeanNumber() {
        return beanNumber;
    }

    public void setBeanNumber(Integer beanNumber) {
        this.beanNumber = beanNumber;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public float getReceivable() {
        return receivable;
    }

    public void setReceivable(float receivable) {
        this.receivable = receivable;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

}

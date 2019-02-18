package com.yinlian.Enums;

public enum MessagesTypeEnum {
	
    商品提醒(1),  
    物流通知(2) ,
    会员动态(3),
    订单提醒(4);
	
    private final int value; 
    public int getValue() { 
        return value; 
    } 
    //构造器默认也只能是private, 从而保证构造函数只能在内部使用 
    MessagesTypeEnum(int value) { 
        this.value = value; 
    } 
}

package com.yinlian.Enums;

public enum GroupOrderEnum {
	待付款(0), 待消费(1),已取消(2), 
	申请退款(3),已退款(4),待评价(5),已评价(6);
	
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	GroupOrderEnum(int value) {
	    		this.value = value;
	    	}
	public static GroupOrderEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
        case 0:
            return 待付款;
        case 1:
            return 待消费;
        case 2:
            return 已取消;
        case 3:
            return 申请退款;
        case 4:
            return 已退款;
        case 5:
            return 待评价;
        case 6:
            return 已评价;
        default:
            return null;
        }
    }
}

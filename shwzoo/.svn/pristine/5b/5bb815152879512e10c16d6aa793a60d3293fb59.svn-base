package com.yinlian.Enums;

public enum GroupOrderDetailEnum {
	
	未使用(0),已退款(1),已消费(2),申请退款(3);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	GroupOrderDetailEnum(int value) {
		    		this.value = value;
		    	}
		public static GroupOrderDetailEnum valueOf(int value) {    //    手写的从int到enum的转换函数
	        switch (value) {
	        case 0:
	            return 未使用;
	        case 1:
	            return 已退款;
	        case 2:
	            return 已消费;
	        case 3:
	            return 申请退款;
	        default:
	            return null;
	        }
	    }
}

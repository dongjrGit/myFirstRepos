package com.yinlian.Enums;

/**
 * 活动使用条件类型
 * 
 * @author mjx
 *
 */
public enum ActivityUseTypeEnum {
	针对金额(0), 针对商品(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ActivityUseTypeEnum(int value) {
		this.value = value;
	}
	  public static ActivityUseTypeEnum valueOf(int value) {    //    手写的从int到enum的转换函数
	        switch (value) {
	        case 0:
	            return 针对金额;
	        case 1:
	            return 针对商品;
	        default:
	            return null;
	        }
	    }
}

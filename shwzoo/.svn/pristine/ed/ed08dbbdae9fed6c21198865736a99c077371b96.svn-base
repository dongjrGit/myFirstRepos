package com.yinlian.Enums;

/**
 * 满赠活动赠品类型
 * 
 * @author mjx
 *
 */
public enum ManZengTypeEnum {
	优惠卷(0), 商品(1), 积分(2);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ManZengTypeEnum(int value) {
		this.value = value;
	}
	 public static ManZengTypeEnum valueOf(int value) {    //    手写的从int到enum的转换函数
	        switch (value) {
	        case 0:
	            return 优惠卷;
	        case 1:
	            return 商品;
	        case 2:
	            return 积分;
	        default:
	            return null;
	        }
	    }
}

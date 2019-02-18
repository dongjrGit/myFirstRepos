package com.yinlian.Enums;

/**
 * 活动记录子表 类型
 * 
 * @author mjx
 *
 */
public enum Och_Type {
	积分(0), 优惠券(1), 商品(2);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	Och_Type(int value) {
		this.value = value;
	}
	public static Och_Type valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
        case 0:
            return 积分;
        case 1:
            return 优惠券;
        case 2:
            return 商品;
        default:
            return null;
        }
    }
}

package com.yinlian.Enums;

/**
 * 订单时段
 * 
 * @author mjx
 *
 */
public enum OrderTimeBucketEnum {
	近三个月订单(1), 今年内订单(2), 前一年订单(3), 前二年当年订单(4), 前三年当年订单(5), 三年前所有订单(6);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	OrderTimeBucketEnum(int value) {
		this.value = value;
	}
}

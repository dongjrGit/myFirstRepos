package com.yinlian.Enums;

/**
 * 积分时段
 * 
 * @author mjx
 *
 */
public enum PointsTimeBucketEnum {
	最近三个月积分记录(1), 三个月前积分记录(2);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	PointsTimeBucketEnum(int value) {
		this.value = value;
	}
}

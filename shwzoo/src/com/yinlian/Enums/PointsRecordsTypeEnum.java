package com.yinlian.Enums;

/**
 * 积分记录类型
 * 
 * @author mjx
 *
 */
public enum PointsRecordsTypeEnum {
	增加(0), 消费(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	PointsRecordsTypeEnum(int value) {
		this.value = value;
	}
}

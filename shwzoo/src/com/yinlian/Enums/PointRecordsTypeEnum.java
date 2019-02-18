package com.yinlian.Enums;

public enum PointRecordsTypeEnum {
	获得积分(0), 消费积分(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	PointRecordsTypeEnum(int value) {
		this.value = value;
	}
}

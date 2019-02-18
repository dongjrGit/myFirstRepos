package com.yinlian.Enums;

/**
 *  状态历史类型
 */
public enum StatusHistoryTypeEnum {
	店铺(0), 买家(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	StatusHistoryTypeEnum(int value) {
		this.value = value;
	}
}

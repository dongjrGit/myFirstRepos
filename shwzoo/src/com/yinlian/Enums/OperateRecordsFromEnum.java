package com.yinlian.Enums;

/**
 * 操作记录来源
 */
public enum OperateRecordsFromEnum {

	系统后台(0), 卖家中心(1), 买家中心(2), web前台(3),pc前台(4);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	OperateRecordsFromEnum(int value) {
		this.value = value;
	}
}

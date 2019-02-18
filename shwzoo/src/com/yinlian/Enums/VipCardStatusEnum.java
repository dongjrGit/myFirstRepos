package com.yinlian.Enums;

public enum VipCardStatusEnum {
	申请中(0), 正在处理中(1), 审核通过(2), 审核未通过(3);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	VipCardStatusEnum(int value) {
		this.value = value;
	}
}

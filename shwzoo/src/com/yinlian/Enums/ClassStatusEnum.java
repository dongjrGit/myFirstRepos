package com.yinlian.Enums;

/** 商品类别状态 */
public enum ClassStatusEnum {
	未提交(0), 审核中(1), 审核通过(2), 审核未通过(3), 禁用(4);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ClassStatusEnum(int value) {
		this.value = value;
	}
}

package com.yinlian.Enums;
/** 证件类型 */
public enum CopeCardTypEnum {
	/** 身份证 */
	身份证(1),
	/** 学生证 */
	学生证(2);

	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	CopeCardTypEnum(int value) {
		this.value = value;
	}
}

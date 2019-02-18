package com.yinlian.Enums;

/** 性别 */

public enum CopySexEnum {
	/** 保密 */
	保密(0),
	/** 男 */
	男(1),
	/** 女 */
	女(2);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	CopySexEnum(int value) {
		this.value = value;
	}
}

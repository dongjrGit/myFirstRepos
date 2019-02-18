package com.yinlian.Enums;

/** 公司类型 */
public enum CompanyTypeEnum {
	/** 无 */
	None(-1),
	/** 民营 */
	Personal(0),
	/** 国企 */
	Country(1),
	/** 外企 */
	Foreign(2);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	CompanyTypeEnum(int value) {
		this.value = value;
	}
}

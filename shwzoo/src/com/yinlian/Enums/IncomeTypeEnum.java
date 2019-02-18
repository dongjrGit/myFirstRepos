package com.yinlian.Enums;

/** 收入水平类型 */
public enum IncomeTypeEnum {
	
	/** 2000元以下 */
	收入2000元以下(1),
	
	/** 2000-3999元 */
	收入2000至3999元 (2),
	
	/** 4000-5999元 */
	收入4000至5999元 (3),
	
	/** 6000-7999元 */
	收入6000至7999元(4),
	
	/** 8000元以上 */
	收入8000元以上(5);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	IncomeTypeEnum(int value) {
		this.value = value;
	}
}

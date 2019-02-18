package com.yinlian.Enums;

/**
 * 
 * @author mjx
 *
 */
public enum UserFinance_Type {
	未支付(0), 已支付(1);
	private Integer value;

	public Integer getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	UserFinance_Type(Integer value) {
		this.value = value;
	}
}

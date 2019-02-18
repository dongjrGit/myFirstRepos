package com.yinlian.Enums;

public enum PayConfigTypeEnum {

	支付宝(1),环迅支付(2);
	
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	PayConfigTypeEnum(int value) {
		this.value = value;
	}
}

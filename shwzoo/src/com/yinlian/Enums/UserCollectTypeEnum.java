package com.yinlian.Enums;

public enum UserCollectTypeEnum {
	商品(0), 店铺(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	UserCollectTypeEnum(int value) {
		this.value = value;
	}
}

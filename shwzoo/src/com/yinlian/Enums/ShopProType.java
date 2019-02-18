package com.yinlian.Enums;

/**
 * y店铺商品类型
 * @author mjx
 *
 */
public enum ShopProType {
	直营(1), 店铺(0);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ShopProType(int value) {
		this.value = value;
	}
}

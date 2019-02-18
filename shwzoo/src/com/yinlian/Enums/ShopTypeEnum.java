package com.yinlian.Enums;

/**
 * 店铺类型
 * @author Administrator
 *
 */
public enum ShopTypeEnum {

	门票(0), 马戏团票(1),游乐票(2),餐饮票(3),动物互动(4),主题商品(5);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ShopTypeEnum(int value) {
		this.value = value;
	}
}

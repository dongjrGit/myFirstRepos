package com.yinlian.Enums;

/**
 * 楼层商品类型
 * 
 * @author mjx
 *
 */
public enum FloorProTypeEnum {
	商品(0), 品牌(1), 分类(2), 专题(3);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	FloorProTypeEnum(int value) {
		this.value = value;
	}
}

package com.yinlian.Enums;

/**
 * 商品规格显示位置
 * 
 * @author mjx
 *
 */
public enum SpecsDisplayEnum {
	商品介绍(1), 商品详情(2), 规格参数(3),购物车(4);
	private final Integer value;

	public Integer getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	SpecsDisplayEnum(Integer value) {
		this.value = value;
	}
}

package com.yinlian.Enums;

/**
 * 品牌推荐首页显示位置
 * 
 * @author mjx
 *
 */
public enum Recommend_Brand_FristPage_Display {
	左(0), 中(1), 右(2);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	Recommend_Brand_FristPage_Display(int value) {
		this.value = value;
	}
}

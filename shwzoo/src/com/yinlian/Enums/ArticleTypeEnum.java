package com.yinlian.Enums;

/**
 * 文章类型（单页/列表）
 * 
 * @author mjx
 *
 */
public enum ArticleTypeEnum {
	单页(0), 列表(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ArticleTypeEnum(int value) {
		this.value = value;
	}
}

package com.yinlian.Enums;

/*
 * Web商品评论类型
 */
public enum WebCommentTypeEnum {
	好评(1), 中评(2), 差评(3), 有图片的评论(4);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	WebCommentTypeEnum(int value) {
		this.value = value;
	}
}

package com.yinlian.Enums;

/**
 * 优惠卷发放类型
 * 
 * @author mjx
 *
 */
public enum CouponIssueTypeEnum {
	店铺(0), 平台(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	CouponIssueTypeEnum(int value) {
		this.value = value;
	}
}

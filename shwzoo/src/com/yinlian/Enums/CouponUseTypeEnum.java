package com.yinlian.Enums;

/**
 * 优惠卷使用对象类型
 * 
 * @author mjx
 *
 */
public enum CouponUseTypeEnum {
	商品(0), 分类(1), 店铺(2), 通用(3);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	CouponUseTypeEnum(int value) {
		this.value = value;
	}
}

package com.yinlian.Enums;

/**
 * 会员优惠券排序状态
 * 
 * @author mjx
 *
 */
public enum MemberCouponOrderTypeEnum {
	过期时间(1), 到账时间(2), 优惠金额(3);
	private int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	MemberCouponOrderTypeEnum(int value) {
		this.value = value;
	}
}

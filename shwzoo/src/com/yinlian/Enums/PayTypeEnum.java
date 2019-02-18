package com.yinlian.Enums;

/**
 * 支付类型
 * 
 * @author mjx
 *
 */
public enum PayTypeEnum {
	在线支付(1), 余额支付(2), 优惠券支付(3), 混合支付(4), 货到付款(5),
	支付宝支付(6),微信支付(7),IPS支付(8);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	PayTypeEnum(int value) {
		this.value = value;
	}
}

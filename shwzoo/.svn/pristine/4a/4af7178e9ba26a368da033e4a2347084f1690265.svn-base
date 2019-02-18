package com.yinlian.Enums;

/** 验证类型 */
public enum AuTypeEnum {
	/** 无验证 */
	None(-1),
	/** 邮箱验证 */
	Email(0),
	/** 手机验证 */
	Mobile(1),
	/** 支付密码验证 */
	PayPwd(2);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	AuTypeEnum(int value) {
		this.value = value;
	}
}

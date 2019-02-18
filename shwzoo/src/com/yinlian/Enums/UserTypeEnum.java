package com.yinlian.Enums;

/** 用户类型 */
public enum UserTypeEnum {
	/** 无 */
	None(-1),
	/** 店铺员工 */
	Employee(1),
	/** 会员（买家） */
	Buyer(2), // 0
	/** 超级管理员 */
	SupAdmin(3),
	/** 平台管理员 */
	Admin(4), // 2
	/** 商家（卖家） */
	Seller(5);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	UserTypeEnum(int value) {
		this.value = value;
	}
}

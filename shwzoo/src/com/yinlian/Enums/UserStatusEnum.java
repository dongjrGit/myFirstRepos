package com.yinlian.Enums;

/**
 * 用户状态
 */
public enum UserStatusEnum {
	正常(0), 删除(1), 锁定(2);
	private final Integer value;

	public Integer getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	UserStatusEnum(Integer value) {
		this.value = value;
	}
}

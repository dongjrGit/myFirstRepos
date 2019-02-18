package com.yinlian.Enums;

/**
 * 活动审核
 * 
 * @author mjx
 *
 */
public enum ActivityCheckEnum {
	未提交(0), 提交审核中(1), 审核通过(2), 审核不通过(3);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ActivityCheckEnum(int value) {
		this.value = value;
	}

	public Boolean isExist(int val) {
		Boolean b = false;
		for (ActivityCheckEnum s : values()) {
			if (s.getValue() != val) {
				b = true;
			}
		}
		return b;
	}
}

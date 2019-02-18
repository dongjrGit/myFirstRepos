package com.yinlian.Enums;

/**
 * 完成状态
 * 
 * @author mjx
 *
 */
public enum CompleteStatus {
	已完成(0), 未支付(1), 已取消(2);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	CompleteStatus(int value) {
		this.value = value;
	}
}

package com.yinlian.Enums;

/**
 * 售后服务发货类型
 * 
 * @author mjx
 *
 */
public enum AfterServiceSender {
	买家(0), 商家(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	AfterServiceSender(int value) {
		this.value = value;
	}public Boolean isExist(int val) {
		Boolean b = false;
		for (AfterServiceSender s : values()) {
			if (s.getValue() != val) {
				b = true;
			}
		}
		return b;
	}
}

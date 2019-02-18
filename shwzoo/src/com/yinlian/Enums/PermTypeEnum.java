package com.yinlian.Enums;

/**
 * 二级菜单类型
 * 
 * @author mjx
 *
 */
public enum PermTypeEnum {
	菜单(0), 按钮(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	PermTypeEnum(int value) {
			this.value = value;
		}
}

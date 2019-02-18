package com.yinlian.Enums;

/**
 * 菜单
 * 
 * @author mjx
 *
 */
public enum MenusTypeEnum {

	系统后台(0), 卖家中心(1), 买家中心(2), web前台(3);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	MenusTypeEnum(int value) {
		this.value = value;
	}
}

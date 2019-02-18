package com.yinlian.Enums;

/**
 * @author mjx 
 * 数据库表操作类型
 */
public enum ChangeTableTypeEnum {
	新增(0), 删除(1), 修改(2), 查询(3);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ChangeTableTypeEnum(int value) {
		this.value = value;
	}
}

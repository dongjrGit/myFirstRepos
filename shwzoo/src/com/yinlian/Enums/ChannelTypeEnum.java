package com.yinlian.Enums;


/** 通道类型 */
public enum ChannelTypeEnum {
	/** 
	 * 无
	 *  */
	None(-1),
	/** 
	 * 电脑
	 */
	PC(0),
	/** 安卓 */
	Android(1),
	/** 苹果 */
	Ios(2),
	/**
	 * 
	 */
	wap(3),
	/**
	 * 第三方
	 */
	thirdparty(4);
	private final Integer value;

	public Integer getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ChannelTypeEnum(int value) {
		this.value = value;
	}
}

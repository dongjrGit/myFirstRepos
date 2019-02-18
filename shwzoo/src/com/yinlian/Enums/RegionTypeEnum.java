package com.yinlian.Enums;

/** 地域类型 */
public enum RegionTypeEnum {
	/** 无 */
	None(-1),
	/** 省 */
	Province(0),
	/** 市 */
	City(1),
	/** 区 */
	Area(2);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	RegionTypeEnum(int value) { 
        this.value = value; 
    }
}

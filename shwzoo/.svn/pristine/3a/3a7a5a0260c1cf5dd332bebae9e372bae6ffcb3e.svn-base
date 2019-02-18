package com.yinlian.Enums;

/**
 * 活动类型
 * 
 * @author mjx
 *
 */
public enum ActivityTypeEnum {
	满减(0), 满赠(1);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ActivityTypeEnum(int value) {
		this.value = value;
	}
	public static ActivityTypeEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
        case 0:
            return 满减;
        case 1:
            return 满赠;
        default:
            return null;
        }
    }

}
 
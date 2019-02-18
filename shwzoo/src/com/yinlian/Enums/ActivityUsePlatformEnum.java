package com.yinlian.Enums;

/**
 * 活动使用平台
 * @author Administrator
 *
 */
public enum ActivityUsePlatformEnum {
	pc(1), app(2),wap(3),wechat(4);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ActivityUsePlatformEnum(int value) {
		this.value = value;
	}
	public static ActivityUsePlatformEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
        case 1:
            return pc;
        case 2:
            return app;
        case 3:
            return wap;
        case 4:
            return wechat;
        default:
            return null;
        }
    }
}

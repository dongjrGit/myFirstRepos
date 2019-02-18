package com.yinlian.Enums;
//站点枚举
public enum WebSetEnum {
	pc(1), app(2),wap(3),wechat(4),thirdparty(5);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	WebSetEnum(int value) {
		this.value = value;
	}
	public static WebSetEnum valueOf(int value) {    //从int到enum的转换函数
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

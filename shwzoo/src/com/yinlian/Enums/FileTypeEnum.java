package com.yinlian.Enums;

public enum FileTypeEnum {
	 jpg(1),bmp (2) ,gif(3) , doc(4),png(5) ,other(6);
	private final Integer value;

	public Integer getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	FileTypeEnum(Integer value) {
		this.value = value;
	}

}

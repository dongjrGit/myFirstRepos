package com.yinlian.Enums;

/**
 * 图片类型
 * 
 * @author mjx
 *
 */
public enum ImageTypeEnum {
	库存图片(1), 买家申请退货图片(2), 卖家不同意退货图片(3), 买家申请换货图片(4), 卖家不同意换货图片(5), 
	买家评价图片(6), 买家申请维修图片(7), 买家申请退款图片(8), 卖家不同意退款图片(9);
	
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ImageTypeEnum(int value) {
		this.value = value;
	}
}

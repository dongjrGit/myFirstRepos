package com.yinlian.Enums;

/**
 * 模板内容类型
 * @author Administrator
 *
 */
public enum TemplateTagEnum {
	            下单成功(0),
		        支付成功(1),
		        支付失败(2),
		        卖家发货(3),
		        买家收货(4),
		        退换货申请(5),
		        卖家同意(6),
		        促销(7),
		        受理成功(8),
	            催单成功(9);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	TemplateTagEnum(int value) {
		this.value = value;
	}
}

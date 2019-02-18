package com.yinlian.Enums;

/**
 * 售后服务
 * 
 * @author mjx
 *
 */
public enum AfterServiceStatusEnum {
	申请售后(0), 商家不同意(-1),  售后完成(5); //商家已确认(1),商家待收货(2), 商家待发货(3), 买家待收货(4), (中绿售后流程调整，产品需要时请恢复注释)
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	AfterServiceStatusEnum(int value) {
		this.value = value;
	}
}

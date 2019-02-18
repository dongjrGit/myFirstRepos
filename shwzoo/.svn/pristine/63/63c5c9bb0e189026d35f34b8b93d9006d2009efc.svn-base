package com.yinlian.Enums;

/**
 * 订单状态
 */
public enum OrderStatusEnum {

	待付款(0), 出票中(1),待使用(2),审核中(3),已完结(9),
	已取消(4);
//	待发货(1), 待收货(2), 已确认收货(3), 未付款取消申请中(4),
//	未付款已取消(5), 已付款取消申请中(6), 已付款已取消(7), 已完成(8), 
//	申请退款退货中(9), 已退款(10);

	private final Integer value;

	public Integer getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	OrderStatusEnum(Integer value) {
		this.value = value;
	}
}

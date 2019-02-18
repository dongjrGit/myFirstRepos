package com.yinlian.Enums;

/**
 * 订单明细状态
 * 
 * @author mjx
 *
 */
public enum OrderDetailStatusEnum {
	
	订单未完成(0),
	
	待使用(10),已使用(19),
	//申请退货退款(50), 退货申请被拒绝(51), 退货退款成功(59), // 退货买家待发货(51), 退货商家待收货(52),

	申请退款(20), 退款失败(21), 审核中(22),退款成功(29),

	//申请换货(30), 换货申请被拒绝(31), 换货成功(39), // 换货买家待发货(31), 换货商家待收货(32), 换货商家待发货(33), 换货买家待收货(34), 

	//申请维修(40), 维修申请被拒绝(41), 维修成功(49), //维修买家待发货(41), 维修商家待收货(42), 维修商家待发货(43), 维修买家待收货(44),申请被拒绝(60),
	
	订单完成(99);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	OrderDetailStatusEnum(int value) {
		this.value = value;
	}
	public static OrderDetailStatusEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
        case 0:
        	return 订单未完成;
        case 10:
        	return 待使用;
        case 19:
        	return 已使用;
        case 20:
            return 申请退款;
        case 21:
            return 退款失败;
        case 22:
            return 审核中;
        case 29:
            return 退款成功;
        case 99:
            return 订单完成;
        default:
            return null;
        }
    }
}

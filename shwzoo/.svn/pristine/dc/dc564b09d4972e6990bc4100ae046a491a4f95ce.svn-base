package com.yinlian.Enums;

/**
 * 订单请求
 * 
 * @author mjx
 *
 */
public enum OrderApplyTypeEnum {
	订单未付款取消(0), 订单退货退款(1), 订单换货(2), 订单维修(3), 订单退款(4),订单已付款取消(5);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	OrderApplyTypeEnum(int value) {
		this.value = value;
	}
	public static OrderApplyTypeEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
        case 0:
            return 订单未付款取消;
        case 1:
            return 订单退货退款;
        case 2:
            return 订单换货;
        case 3:
            return 订单维修;
        case 4:
            return 订单退款;
        case 5:
            return 订单已付款取消;
        default:
            return null;
        }
	}
}

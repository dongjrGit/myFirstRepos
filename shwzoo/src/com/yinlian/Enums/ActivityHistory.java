package com.yinlian.Enums;

/**
 * 活动记录历史
 * 
 * @author mjx
 *
 */
public enum ActivityHistory {
	优惠卷(0), 满减(1), 满赠(2), 组合商品(3), 限时抢购(4), 秒杀(5), 闪购(6);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ActivityHistory(int value) {
		this.value = value;
	}
}

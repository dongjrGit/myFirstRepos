package com.yinlian.Enums;


/**
 * 购物车商品类型
 * 
 * @author mjx
 *
 */
public enum ShopCartProType {

	普通商品(0), 组合商品(1), 秒杀(2), 闪购(3), 赠品(4);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ShopCartProType(int value) {
		this.value = value;
	}
	  public static ShopCartProType valueOf(int value) {    //    手写的从int到enum的转换函数
	        switch (value) {
	        case 0:
	            return 普通商品;
	        case 1:
	            return 组合商品;
	        case 2:
	            return 秒杀;
	        case 3:
	            return 闪购;
	        case 4:
	            return 赠品;
	        default:
	            return null;
	        }
	    }

}

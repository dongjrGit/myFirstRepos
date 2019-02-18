/*
 * @(#) ClassifyPageType.java 2016年8月15日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.Enums;

public enum ClassifyPageType {
	 	首页(1),商品详细(2),会员中心(3),购物车(4),店铺详情(5),结算页面(6);
	    	
		
		private final int value;
		
		public int getValue() {
			return value;
		}
		
		public static ClassifyPageType valueOf(int value){
			switch (value) {
			case 1:
				return 首页;
			case 2:
				return 商品详细;
			case 3:
				return 会员中心;
			case 4:
				return 购物车;
			case 5:
				return 店铺详情;
			case 6:
				return 结算页面;
			}
			return null;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		ClassifyPageType(int value) {
			this.value = value;
		}

		public String getName() {
			return this.name();
		}
}

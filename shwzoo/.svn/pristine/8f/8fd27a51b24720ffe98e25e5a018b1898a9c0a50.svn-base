package com.yinlian.Enums;

/** 店铺违规 */
public enum ShopViolationTypeEnum {
	/** 轻度违规 */
	lvl1(1),
	/** 一般违规 */
	lvl2(2),
	/** 严重违规 */
	lvl3(3);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	private ShopViolationTypeEnum(int value) {
		this.value = value;
	}
	
	 public static String getName(int value) {
	        String name = "";
	        switch (value) {
	            case 0:
	                name = "轻度违规";
	                break;

	            case 1:
	                name = "一般违规";
	                break;
	            case 2:
	                name = "严重违规";
	                break;
	        }
	        return name;
	    }
}

package com.yinlian.Enums;
/**
 * 物流速度
 * @author admin
 *
 */
public enum LogisticsSpeed {
	很不满意(1),不满意(2),一般(3),满意(4),很满意(5);
	private final int value;
	
	public int getValue(){
		return value;
	}
	
	private LogisticsSpeed(int value) {
		this.value=value;
	}
}

package com.yinlian.Enums;
/**
 * 搜索属性状态
 * SearchattrStatusEnum.java
 * @author Administrator
 * @version $Id: SearchattrStatusEnum.java, v 0.1 2016年4月27日 下午3:37:34 Administrator Exp $
 */
public enum SearchattrStatusEnum {
	启用(0),禁用(1);
	private int value;
	public int getValue(){
		return value;
	}
	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	SearchattrStatusEnum(int value) {
		this.value = value;
	}
	
	}
	


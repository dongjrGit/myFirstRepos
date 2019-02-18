/*
 * @(#) TopicClassifyEnum.java 2016年10月31日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.Enums;

/**
 * 专题分类标识
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年10月31日
 */
public enum TopicClassifyEnum {
	今日爆款(0);
    
	private final int value;
	
	public int getValue() {
	return value;
	}
	
	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	TopicClassifyEnum(int value) {
	this.value = value;
	}
	
	public String getName(){
    	return this.name();
    }
}

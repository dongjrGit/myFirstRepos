/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.Enums;

/**
 * FinanceType.java
 * @author sssssssl.m
 * @version $Id: FinanceType.java, v 0.1 2016年4月26日 下午5:13:32 Administrator Exp $
 */
public enum FinanceType {

    金额(0),经采豆(1), 积分(2);

    private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	FinanceType(int value) {
		this.value = value;
	}
}

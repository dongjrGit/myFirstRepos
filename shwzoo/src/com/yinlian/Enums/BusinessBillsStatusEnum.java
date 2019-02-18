/*
 * @(#) BusinessBillsStatus.java 2016年8月29日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.Enums;

public enum BusinessBillsStatusEnum {
	未处理(0),已处理(1);
	
	private final int value;

	public int getValue() {
		return value;
	}

	BusinessBillsStatusEnum(int value) {
		this.value = value;
	}
}

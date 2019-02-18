package com.yinlian.app.tenpay;

import java.util.Random;

import com.yinlian.app.tenpay.MD5Util;

public class WXUtil {
	
	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "GBK");
	}

	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
}

package com.pushwin.batchwork.utils;

public class ParamCheck {
	public static boolean strEmptyCHK(String str){
		if(null == str || "".equals(str) || "".equals(str.trim())){
			return true;
		}
		return false;
	}
}

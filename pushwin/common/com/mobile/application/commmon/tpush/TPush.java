package com.mobile.application.commmon.tpush;

public class TPush {
	private static TPush tPush;
	private TPush(){}
	
	public static synchronized TPush getTPush(){
		if(null == tPush){
			return new TPush();
		}
		return tPush;
	}
}

package com.yinlian.wssc.web.util;

import java.lang.reflect.Field;

public class EnumUtils {
	public static <T extends Enum<T>> T getMyEnum(Class<T> enumType, Integer val) {
		return getMyEnum(enumType, val, null);
	}

	public static <T extends Enum<T>> T getMyEnum(Class<T> enumType, Integer val, T defaultT) {
		T ret = defaultT;
		try {
			T[] myEnums = enumType.getEnumConstants();			
			Field fl = enumType.getDeclaredField("value");
			fl.setAccessible(true);//修改访问权限
			if (myEnums != null && myEnums.length > 0) {
				for (T t : myEnums) {
					try {
						if (val == Integer.valueOf(fl.get(t).toString())) {
							ret = t;
							break;
						}
					} catch (IllegalArgumentException e) {
						 e.printStackTrace();
					} catch (IllegalAccessException e) {
						 e.printStackTrace();
					}
				}
			}
		} catch (NoSuchFieldException e) {
			// e.printStackTrace();
		} catch (NullPointerException e) {
			// e.printStackTrace();
		}
		return ret;
	}

}

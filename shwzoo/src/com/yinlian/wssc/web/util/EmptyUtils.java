package com.yinlian.wssc.web.util;

import java.util.List;

/**
 * 
 *  功能说明: 验证是否为空
 * 
 *  
 * 
 *
 */
public class EmptyUtils {
	
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list){
		return (list==null || list.size()==0);
	}
	
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list){
		return (list!=null && list.size()>0);
	}
	
	
	public static boolean isEmpty(String str){
		return (str==null || "".equals(str));
	}
	
	
	public static boolean isNotEmpty(String str){
		return (str!=null && !str.equals(""));
	}
	
	
	public static boolean isNotEmpty(Object obj){
		return obj!=null;
	}
	
	
	public static boolean isEmpty(Object obj){
		return obj==null;
	}
	
	
	public static boolean isEmpty(Object[] strings){
		return (strings==null || strings.length==0);
	}
	
	
	public static boolean isNotEmpty(Integer num){
		return num!=null;
	}
	
	
	public static boolean isEmpty(Integer num){
		return num==null;
	}
	
	
	public static boolean isNotEmpty(Object[] strings){
		return (strings!=null && strings.length>0);
	}
	
	


}

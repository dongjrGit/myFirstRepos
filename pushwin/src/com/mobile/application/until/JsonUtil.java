package com.mobile.application.until;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/** 
 * Copy Right Information   :Techown, 2016
 * Project                  : pushwin
 * Author					: luwh
 * JDK version used         : jdk1.6
 * Comments                 : 求对象JSON转换为String字符串写入日志中 
 * Version                  : 1.01
 * Modification history     : 2005.9.27
 * Sr Date   Modified By   Why & What is modified 
 **/
public class JsonUtil {
	/**
	 * 
	 * @param obj 需要转的对象
	 * @return
	 */
	public static String JSONTOString(Object obj){
		JSONObject jsontoString = JSONObject.fromObject(obj);
		return jsontoString.toString();
	}
}

/*
 *pushwin
 *com.mobile.application.until
 *2017-3-3
 *TODO
 *chenji
 *
 *
 *
 * 
 */
package com.mobile.application.until;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.dc.eai.data.Array;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

import antlr.collections.List;

/**
 * @TODO
 * @date 2017-3-3
 * @time 下午7:02:24
 * @author chenji
 */
public class SqlUtil {

	/**
	 * @description 工具方法  T_BASIS_BUS_SPECIALCOST_ADD  把businid和businname对应起来  
	 * @param list  表 T_BASIS_BUS_SPECIALCOST_ADD 查询结果
	 * @return  sqlMapList 
	 */
	public static ArrayList<HashMap<String, String>> getSqlList(
			ArrayList<HashMap<String, String>> list) {
		if (list == null) {
			return null;
		}
		ArrayList<HashMap<String, String>> sqlMapList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			String key = list.get(i).get("busInId").toString();
			String value = list.get(i).get("busInName").toString();
			tempMap.put(key, value);
			sqlMapList.add(tempMap);
		}

		return sqlMapList;
	}

}

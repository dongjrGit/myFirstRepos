package com.mobile.application.service.dectionary;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.mobile.application.vo.CommonVO;

public interface IDectService {
	public CommonVO dictlist(int indexPage,int indexSize,String businTypeName);

	public CommonVO listItem(int indexPage, int indexSize, String businTypeId);

	public CommonVO saveType(String dictNum, String dictName, String dictDesc);

	List<?> dialogType(String id);

	CommonVO updateType(String id, String dictName, String dictDesc);

	CommonVO deleteItem(String request);

	CommonVO dialogItem(String typeId, String itemId);

	public CommonVO updateItem(String dictDesc, String dictName,
			String dictNum, String dictsonId, String id);

    CommonVO deleteType(String roleId);

	public CommonVO addItem(HttpSession session,String dictDesc, String dictName, String dictNum,String dictId);

	Map<String, Map<String, String>> qryAllDict();

	Map<String,Object> qryDicts(Object obj);

	public Map<String, Object> qryCity(String lastUpdateTime,String bankFlag);

	List<?> qryDectByTypeWithDesc(String typeId);

	List<?> qryDectByType(String typeId);
}

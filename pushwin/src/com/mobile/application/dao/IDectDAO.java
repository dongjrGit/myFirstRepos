package com.mobile.application.dao;

import java.util.List;
import java.util.Map;

import com.mobile.application.entity.TBasisDict;
import com.mobile.application.entity.TBasisType;

public interface IDectDAO  extends IBaseDAO<TBasisType>{
	public List dictlist(int indexPage,int indexSize,String businTypeName);

	public List dictlistSize(String businTypeName);

	public List listItem(int indexPage, int indexSize, String businTypeId);

	public List dictItemSize(String businTypeId);

	public List qryDictByName(String dictName);

	public List qryDictByNO(String dictNum);

	public void saveType(TBasisType dict);

	List<?> dialogType(String id);

	TBasisType qryTypeById(String id);

	List<?> qryTypeByName(String dictTypeName);

	void deleteItem(String id, String dictId);

	List<?> dialogItem(String typeId, String itemId);

	public TBasisDict qryItemById(String id, String dictsonId);

	List<?> dictNameSonlist(String name, String id);

	public void updateItem(TBasisDict dict);

	public long queryDictUser(String roleId);
	
	void delType(String id);

	public List dictNumSonlist(String dictNum, String id);

	void saveItem(TBasisDict dictSon);

	List<TBasisDict> qryAllDict();

	Map<String,Object> qryDicts(Object obj);
	//List<?> qryDicts(String lastUpdateTime);

	public List<?> qryProvinces(String lastUpdateTime,String bankFlag);

	List<?> qryCity(String lastUpdateTime,String bankFlag);

	List<?> qryDistrict(String lastUpdateTime,String bankFlag);

	List<?> qryDectByTypeWithDesc(String typeId);

	List<?> qryDectByType(String typeId);
	
	public List<?> qryDectByTypeWithDescAllDesc(String typeId);
	
	public List<?> qryOrg(String typeId,String bankFlag) ;
}

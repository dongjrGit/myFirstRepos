package com.mobile.application.service.dectionary.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.dao.IDectDAO;
import com.mobile.application.entity.TBasisDict;
import com.mobile.application.entity.TBasisDictId;
import com.mobile.application.entity.TBasisType;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.service.dectionary.IDectService;
import com.mobile.application.vo.CommonVO;

@Service
public class DectServiceImpl implements IDectService{
	
	@Autowired
	private IDectDAO dectDAO;
	
	@Override
	@Transactional
	public CommonVO dictlist(int indexPage,int indexSize,String businTypeName)
	{
		List<?> data = dectDAO.dictlist(indexPage, indexSize, businTypeName);
		Integer size = dectDAO.dictlistSize(businTypeName).size();
		return new CommonVO(true, data, size.toString());
	}
	
	@Override
	@Transactional
	public CommonVO listItem(int indexPage,int indexSize,String businTypeId){
		List<?> data = dectDAO.listItem(indexPage, indexSize, businTypeId);
		Integer size = dectDAO.dictItemSize(businTypeId).size();
		return new CommonVO(true, data, size.toString());
	}

	@Override
	@Transactional
	public CommonVO saveType(String dictNum, String dictName, String dictDesc) {
		
		//判断字点名和字典编号是否重复
		List<?> dictByNameList = dectDAO.qryDictByName(dictName);
		List<?> dictByNOList = dectDAO.qryDictByNO(dictNum);
		if(dictByNameList.size() == 0 && dictByNOList.size()==0)
		{
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String time = dfs.format(new Date());//为获取当前系统时间
	        //获取当前登录人机构ID
			TBasisType dict = new TBasisType();
			dict.setBusintypeid(dictNum);
			dict.setBusintypename(dictName);
			dict.setBusinremark(dictDesc);
			dict.setUpdatetime(time);//添加时间
			dict.setUpdateuser("");//操作人
			dict.setStatus("1");//激活状态
			dectDAO.saveType(dict);
			return new CommonVO(true, "保存成功。");
		} else {
			return new CommonVO(false, "业务字典名称或业务字典编号重复。");
		}
	}
	
	@Override
	@Transactional
	public CommonVO deleteType(String roleId){
		long count = this.dectDAO.queryDictUser(roleId);
		if(count == 0){
			this.dectDAO.delType(roleId);
			return new CommonVO(true,"删除成功");
		}
		else{
			return new CommonVO(false,"请先清空字典下的数据");
		}
		
	}
	
	@Override
	@Transactional
	public List<?> dialogType(String id){
		return dectDAO.dialogType(id);
	}
	
	/**
	 * 字典编辑保存
	 * 姜瑞
	 * 2014-3-26
	 * @throws IOException 
	 * */
	@Override
	@Transactional
	public CommonVO updateType(String id, String dictName, String dictDesc)
	{
		//通过当前父级ID查询出实体类
		TBasisType dict = dectDAO.qryTypeById(id);
		
		//判断字点名和字典编号是否重复
		//判断字点名和字典编号是否重复
		List<?> dictByNameList = dectDAO.qryDictByName(dictName);
		if(dictByNameList.size() != 0 && !id.equals(((TBasisType)dictByNameList.get(0)).getBusintypeid()))
			return new CommonVO(false, "业务字典名称已存在");
		
		
		//获取当前时间
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = dfs.format(new Date());//为获取当前系统时间 
        //获取当前登录人机构ID
		dict.setBusinremark(dictDesc);
		dict.setBusintypename(dictName);
		dict.setUpdatetime(time);
		dectDAO.saveType(dict);
		return new CommonVO(true, "保存成功");
	}
	
	@Override
	@Transactional
	public CommonVO deleteItem(String deleteRows){
		//获取前台传入的JOSN
		JSONArray deleteRowJson = JSONArray.fromObject(deleteRows);
		int iSize = deleteRowJson.size();
		JSONObject jsonObj = null;
		String dictids = "";
		String dict = "";
		for(int i=0 ; i<iSize ; i++)
		{
			jsonObj = deleteRowJson.getJSONObject(i); 
			String dictid = jsonObj.getString("id");
			dict = jsonObj.getString("dictid");
			dictids += dictid+",";
		}
		dectDAO.deleteItem(dictids, dict);
		return new CommonVO(true, "删除成功");
	}
	
	@Override
	@Transactional
	public CommonVO dialogItem (String typeId, String itemId){
		List<?> roleList = dectDAO.dialogItem(typeId, itemId);
		return new CommonVO(true, roleList, null);
	}

	@Override
	@Transactional
	public CommonVO updateItem(String dictDesc, String dictName,
			String dictNum, String dictsonId, String id) {
		String num = "0";
		//根据ID查询出实体类
		TBasisDict dict = dectDAO.qryItemById(id,dictsonId);
		if(dict.getBusinname().equals(dictName) && !dict.getDictremark().equals(dictDesc))
		{
			//获取当前时间
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String time = dfs.format(new Date());//为获取当前系统时间
	        //获取当前登录人机构ID
			dict.setDictremark(dictDesc);
			dict.setUpdatetime(time);
			this.dectDAO.updateItem(dict);
		} else if (dict.getBusinname().equals(dictName) && dict.getDictremark().equals(dictDesc))
		{
			return new CommonVO(true, "保存成功");
		} else {
			//判断字典名称是否重复
			List<?> countNamelist = dectDAO.dictNameSonlist(dictName,id);
			if(countNamelist.size() == 0)
			{
				//获取当前时间
				SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String time = dfs.format(new Date());//为获取当前系统时间
		        //获取当前登录人机构ID
				dict.setBusinname(dictName);
				dict.setDictremark(dictDesc);
				dict.setUpdatetime(time);
				this.dectDAO.updateItem(dict);
			}else
			{
				return new CommonVO(false, "业务字典名称重复");
			}
		}
		return new CommonVO(true, "保存成功");
	}
	
	
	@Override
	@Transactional
	public CommonVO addItem(HttpSession session,String dictDesc, String dictName, String dictNum,String dictId){
		//判断字典名称,字典ID是否重复
		List dictNumlist =  this.dectDAO.dictNumSonlist(dictNum,dictId);
		List dictNamelist =  this.dectDAO.dictNameSonlist(dictName,dictId);
		if(dictNumlist.size() == 0 && dictNamelist.size() == 0)
		{
			
			//获取当前时间
			SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        Date date = new Date(); 
	        String time = formatTimestamp.format(date); 
	        //Timestamp time = Timestamp.valueOf(str); 
	        //获取当前登录人机构ID
			TBasisUser user = (TBasisUser) session.getAttribute("USER");
			
			TBasisDict dictSon = new TBasisDict();
			TBasisDictId  id = new TBasisDictId();
			id.setBusinid(dictNum);
			id.setBusintypeid(dictId);
			dictSon.setId(id);
			dictSon.setBusinname(dictName);
			dictSon.setDictremark(dictDesc);
			dictSon.setStatus("1");
			dictSon.setUpdatetime(time);
			dictSon.setUpdateuser("");
			this.dectDAO.saveItem(dictSon);
			//this.saveLog(SysConst.SYSTEM_DICT, "字典新增", SysConst.SAVE,"1");
			return new CommonVO(true,"添加成功");
		}
		else{
			return new CommonVO(false,"字典编号或名称重复");
		}
	}
	@Override
	@Transactional
	public Map<String, Map<String, String>> qryAllDict(){
		
		Map<String, Map<String, String>> dictTypeMap = new HashMap<String, Map<String,String>>();
		
		List<TBasisDict> dicts = this.dectDAO.qryAllDict();
		for(TBasisDict dict : dicts){
			if(!dictTypeMap.containsKey(dict.getId().getBusintypeid())){
				Map<String, String> dictMap = new HashMap<String, String>();
				dictMap.put(dict.getId().getBusinid(), dict.getBusinname());
				dictTypeMap.put(dict.getId().getBusintypeid(), dictMap);
			} else {
				dictTypeMap.get(dict.getId().getBusintypeid()).put(dict.getId().getBusinid(), dict.getBusinname());
			}
		}
		return dictTypeMap;
	}
	
	@Override
	@Transactional
	public Map<String,Object> qryDicts(Object obj){
	//public List<?> qryDicts(String lastUpdateTime){
		return this.dectDAO.qryDicts(obj);
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> qryCity(String lastUpdateTime,String bankFlag) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<?> provinces = dectDAO.qryProvinces(lastUpdateTime,bankFlag);
		List<?> citys = dectDAO.qryCity(lastUpdateTime,bankFlag);
		List<?> districts = dectDAO.qryDistrict(lastUpdateTime,bankFlag);
		List<?> org = dectDAO.qryOrg(lastUpdateTime,bankFlag);
		map.put("lastUpdateTime", DateUtil.format(new Date()));
		map.put("provinces", provinces);
		map.put("citys", citys);
		map.put("org", org);
		map.put("districts", districts);
		return map;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<?> qryDectByTypeWithDesc(String typeId) {
		return dectDAO.qryDectByTypeWithDesc(typeId);
	}
	@Override
	@Transactional(readOnly=true)
	public List<?> qryDectByType(String typeId) {
		return dectDAO.qryDectByType(typeId);
	}
}

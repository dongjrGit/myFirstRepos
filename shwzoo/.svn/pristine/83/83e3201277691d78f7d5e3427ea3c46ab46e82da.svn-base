package com.techown.wssc.web.service;

import java.util.List;
import java.util.Map;

import com.techown.wssc.web.po.CriteriaEditor;
import com.techown.wssc.web.po.ZooEditor;
import com.yinlian.wssc.web.interceptor.PageBean;

public interface ZooEditorService {
	
	/**
	 * 列表查询
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 */
	PageBean listByType(CriteriaEditor criteria, int index, int size);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	ZooEditor getById(Integer id);
	
	/**
	 * 根据ID查询未删除状态的数据
	 * 
	 * @param id
	 * @return
	 */
	ZooEditor getById(Integer id,Integer delstate);

	/**
	 * 保存
	 * 
	 * @param zooEditor
	 */
	void saveEditor(ZooEditor zooEditor);

	/**
	 * 逻辑删除
	 * @param type 
	 * 
	 * @param type
	 */
	Map<String,String> deleteById(Integer id,Integer type, String operator);

	/**
	 * 修改
	 * 
	 * @param zooEditor
	 */
	void updateById(ZooEditor zooEditor);

	/**
	 * 根据类型、名字进行查询
	 * 
	 * @param type 类型
	 * @param title 标题
	 * @return
	 */
	List<ZooEditor> listByTitle(Integer type, String title);
	/**
	 * 根据id和type校验title是否重复
	 * @param id
	 * @param type
	 * @param title 
	 * @return
	 */
	int countCheckTitle(Integer id, Integer type, String title);

}

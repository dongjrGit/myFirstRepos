package com.techown.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.techown.wssc.web.po.ZooEditor;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ZooEditorMapper {
	int deleteByPrimaryKey(Integer id);

	/**
	 * 保存
	 * 
	 * @param record
	 * @return
	 */
	int insert(ZooEditor record);

	int insertSelective(ZooEditor record);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	ZooEditor selectByPrimaryKey(@Param("id") Integer id, @Param("delstate") Integer delstate);

	/**
	 * 根据主键ID进行修改、属性如果为空，对应的字段不修改
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(ZooEditor record);

	int updateByPrimaryKeyWithBLOBs(ZooEditor record);

	int updateByPrimaryKey(ZooEditor record);

	/**
	 * 分页查询
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<ZooEditor> querylistPage(PageBeanUtil pageBeanUtil);

	/**
	 * 根据标题，查询相同标题的个数
	 * 
	 * @param id
	 * @param type
	 * @param title
	 * @return
	 */
	int countCheckTitle(@Param("id") Integer id, @Param("type") Integer type,
			@Param("title") String title);

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 * @param operator
	 */
	void deleteById(@Param("id") Integer id, @Param("operator") String operator);

	/**
	 * 根据类别和标题进行模糊查询
	 * 
	 * @param type
	 * @param title
	 * @return
	 */
	List<ZooEditor> listByTitle(@Param("type") Integer type, @Param("title") String title);
}
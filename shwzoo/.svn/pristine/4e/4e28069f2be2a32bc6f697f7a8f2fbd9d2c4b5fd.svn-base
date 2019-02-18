package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.ClassDto;
import com.yinlian.wssc.web.dto.TopicRelateInfo;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.CategoryExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface CategoryMapper {
    int countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
   List<Category> getAcByFatherId( @Param("fatherid")int fatherid) throws Exception;

	List<ClassDto> getRecommClass() throws Exception;
	/**
	 * 分頁查詢分類主題
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<TopicRelateInfo> selectTopicByPage(PageBeanUtil pageBeanUtil);
	
	List<Category> selectFirstClass() throws Exception;
	
	List<Category> selectChildrenClass(Integer fatherid) throws Exception;
	
	List<Category> getbyFullpath(@Param("fullpath") String fullpath) throws Exception;

	List<Category> queryAllList() throws Exception;

	List<Category> querySan();
	
	List<Category> getListbyids(List<Integer> idlist) throws Exception;
	}
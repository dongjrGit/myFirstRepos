package com.techown.wssc.web.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.techown.wssc.web.po.ZooNews;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ZooNewsMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(ZooNews record);

	int updateByPrimaryKeySelective(ZooNews record);

	int updateByPrimaryKey(ZooNews record);

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insert(ZooNews record);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	ZooNews selectByPrimaryKey(Integer id);

	/**
	 * 编辑
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeyWithBLOBs(ZooNews record);

	/**
	 * 列表查询
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<ZooNews> querylistPage(PageBeanUtil pageBeanUtil);
	/**
	 * 首页动物科普新闻
	 * @param state
	 * @return
	 */
	List<ZooNews> getNewsListHot(Integer state);
	
	/**
	 * 修改发布状态
	 * 
	 * @param id
	 * @param state
	 * @param updateTime
	 * @param operator
	 */
	void updateState(@Param("id") Integer id, @Param("state") Integer state, @Param("updateTime") Date updateTime, @Param("operator") String operator);

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 * @param updateTime
	 * @param operator
	 */
	void updateDelState(@Param("id") Integer id, @Param("updateTime") Date updateTime, @Param("operator") String operator);
	/**
	 * 获取更新时间最大，并且是发布状态的新闻
	 * @param state
	 * @return
	 */
	List<ZooNews> getLastNews(Integer state);
}
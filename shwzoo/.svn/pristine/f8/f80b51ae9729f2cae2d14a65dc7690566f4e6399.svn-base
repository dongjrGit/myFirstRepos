package com.techown.wssc.web.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.techown.wssc.web.po.ZooBanner;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ZooBannerMapper {
	
	int insertSelective(ZooBanner record);

	int updateByPrimaryKeySelective(ZooBanner record);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入
	 * 
	 * @param record
	 * @return
	 */
	int insert(ZooBanner record);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	ZooBanner selectByPrimaryKey(Integer id);

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(ZooBanner record);

	/**
	 * 分页查询
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<ZooBanner> querylistPage(PageBeanUtil pageBeanUtil);

	/**
	 * 修改启用/禁用状态
	 * 
	 * @param id
	 * @param status
	 * @param updateTime
	 * @param operator
	 */
	void updateStatus(@Param("id") Integer id, @Param("status") Integer status, @Param("updateTime") Date updateTime, @Param("operator") String operator);

	/**
	 * 查询启用状态的景点
	 * 
	 * @param t
	 * @param code
	 * @param i
	 * @return
	 */
	List<ZooBanner> selectScenic(@Param("type") Integer type, @Param("status") Integer status, @Param("typeId") Integer typeId);

	/**
	 * 根据状态查询banner表
	 * 
	 * @param status
	 *            状态 1启用 0禁用
	 * @return
	 */
	List<ZooBanner> getAppBanner(@Param("status") Integer status);
	
	/**
	 * 根据typeId进行查询
	 * 
	 * @param id
	 * @return
	 */
	List<ZooBanner> listByTypeId(@Param("typeId")Integer id);

}
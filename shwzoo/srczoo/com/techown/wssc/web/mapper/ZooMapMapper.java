package com.techown.wssc.web.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.techown.wssc.web.po.ZooMap;

public interface ZooMapMapper {
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入
	 * 
	 * @param zooMap
	 * @return
	 */
	int insert(ZooMap zooMap);

	int insertSelective(ZooMap record);

	ZooMap selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ZooMap record);

	int updateByPrimaryKey(ZooMap record);

	/**
	 * 列表查询
	 * 
	 * @param type
	 *            类型
	 * @param state
	 *            状态
	 * @return
	 */
	List<ZooMap> querylist(@Param("type") Integer type, @Param("state") Integer state);

	/**
	 * 修改状态
	 * 
	 * @param id
	 * @param srcState
	 * @param tgtState
	 * @param updateTime
	 */
	void updateState(@Param("id") Integer id, @Param("srcState") Integer srcState, @Param("tgtState") Integer tgtState, @Param("updateTime") Date updateTime);

	/**
	 * 查询每个类型的最大时间
	 * 
	 * @return
	 */
	List<ZooMap> queryMaxTime();

	/**
	 * 获取最新记录
	 * 
	 * @param type
	 *            类型
	 * @param state
	 *            状态
	 * @return
	 */
	HashMap<String, Object> queryLastMap(@Param("type") Integer type, @Param("state") Integer state);

}
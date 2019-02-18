package com.techown.wssc.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.techown.wssc.web.po.ScenicApp;
import com.techown.wssc.web.po.Scenic;
import com.techown.wssc.web.po.ScenicWithBLOBs;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ScenicMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ScenicWithBLOBs record);

	int insertSelective(ScenicWithBLOBs record);

	ScenicWithBLOBs selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ScenicWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(ScenicWithBLOBs record);

	int updateByPrimaryKey(Scenic record);

	List<Scenic> querylistPage(PageBeanUtil pageBeanUtil);

	ScenicWithBLOBs selectById(Integer id);

	void updateState(@Param("id") int id, @Param("state") int state);

	/**
	 * 获取景点列表
	 * 
	 * @param state
	 * @return
	 */
	List<Scenic> getScenicByState(Integer state);

	/**
	 * 通过ID查询景点接口(PAD)
	 * @param id
	 * @param state
	 * @return
	 */
	ScenicApp getAppScenicById(@Param("id")Integer id,@Param("state")Integer state);

	/**
	 * 根据类别code和景点名称查询景点名称列表
	 * 
	 * @param list
	 *            景点类比
	 * @param name
	 *            名称
	 * @param state
	 *            发布状态
	 * @return
	 */
	List<Scenic> getScenicStartWithName(@Param("list") List<String> list, @Param("name") String name, @Param("state") Integer state);

	/**
	 * 查询名字是否重复
	 * @param id
	 * @param scenicname
	 * @return
	 */
	List<Map<String, Object>> queryByName(@Param("id")Integer id, @Param("scenicname")String scenicname);

	/**
	 * 根据shopid查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Scenic getScenicByShopId(Integer id)throws Exception;

}
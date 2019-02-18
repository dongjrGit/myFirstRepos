package com.techown.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.techown.wssc.web.po.RcmdScenic;

public interface RcmdScenicMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RcmdScenic record);

	int insertSelective(RcmdScenic record);

	RcmdScenic selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RcmdScenic record);

	int updateByPrimaryKey(RcmdScenic record);

	List<RcmdScenic> querylist(@Param("name") String name, @Param("list") List<String> list,
			@Param("state") Integer state);

	List<RcmdScenic> selectByScenicId(int scenicid);

	RcmdScenic selectById(Integer id);

	int updateRcmdScenic(RcmdScenic rcmdScenic);

	/**
	 * 前端推荐景点接口
	 * 
	 * @param type
	 *            类型 1：剧场 ；2：动物 ；3：开心游乐
	 * @return
	 */
	List<RcmdScenic> getAppRcmdScenic(@Param("type") Integer type);
}
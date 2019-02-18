package com.techown.wssc.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.techown.wssc.web.po.ZooMap;

public interface ZooMapService {
	/**
	 * 列表查询
	 * @param type 类型
	 * @param state 状态
	 * @return
	 */
	List<ZooMap> querylist(Integer type, Integer state);

	/**
	 * 添加
	 * @param zooMap
	 */
	void addZooMap(ZooMap zooMap);
	/**
	 * 逻辑删除
	 * @param id
	 * @param srcState
	 * @param tgtState
	 * @param updateTime
	 */
	void updateState(Integer id, Integer srcState, Integer tgtState, Date updateTime);

	/**
	 * 查询每个类型的最大时间
	 * 
	 * @return
	 */
	List<ZooMap> queryMaxTime();
	/**
	 * 获取最新记录
	 * @param type 类型
	 * @param state 状态
	 * @return
	 */
	public HashMap<String,Object> queryLastMap(Integer type,Integer state);
}

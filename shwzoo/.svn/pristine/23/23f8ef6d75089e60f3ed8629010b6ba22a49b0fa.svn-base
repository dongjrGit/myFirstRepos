package com.techown.wssc.web.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.techown.wssc.web.po.ScenicApp;
import com.techown.wssc.web.po.CriteriaScenic;
import com.techown.wssc.web.po.Scenic;
import com.techown.wssc.web.po.ScenicType;
import com.techown.wssc.web.po.ScenicWithBLOBs;
import com.yinlian.wssc.web.interceptor.PageBean;

public interface ScenicService {
	/**
	 * 获取景点类别
	 * 
	 * @param type
	 * @return
	 */
	public List<ScenicType> queryScenicType(String type);

	/**
	 * 查询景点列表
	 * 
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 */
	public PageBean querylist(CriteriaScenic criteria, int index, int size);

	/**
	 * 添加景点
	 * 
	 * @param imagePaths
	 * @param scenic
	 */
	public int addScenic(ScenicWithBLOBs scenicWithBLOBs, String imagePaths);

	public ScenicWithBLOBs selectById(Integer id);

	public void updateScenic(ScenicWithBLOBs scenicWithBLOBs, String imagePaths);

	/**
	 * 删除景点设施
	 * 
	 * @param id
	 * @param imageid
	 */
	public void dellist(int id, String imageid);

	/**
	 * 修改发布状态
	 * 
	 * @param id
	 * @param state
	 */
	public Boolean updateState(int id, int state);

	/**
	 * 载入获取数据（景点类别，和景点信息）
	 * @param type 类别类型
	 * @return
	 */
	public ObjectNode getInitData(String type,Integer state);

	/**
	 * 通过ID查询景点信息（PAD）
	 * @param id
	 * @param state 
	 * @return
	 */
	public ScenicApp getAppScenicById(Integer id, Integer state);

	/**
	 * 根据类别code和景点名称查询景点名称列表
	 * @param type 景点类型
	 * @param name 景点名称
	 * @param state 发布状态
	 * @return
	 */
	public List<Scenic> getScenicStartWithName(String type, String name,Integer state);

	/**
	 * 验证是否重名
	 * @param id
	 * @param scenicname
	 * @return
	 */
	public boolean checkName(Integer id, String scenicname);
}

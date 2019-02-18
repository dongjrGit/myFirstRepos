package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.FloorDto;
import com.yinlian.wssc.web.dto.FloorProsDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Floor;
import com.yinlian.wssc.web.util.CriteriaFloor;

public interface FloorService {
	
	/**
	 * 查询所有的楼层
	 * @return
	 * @throws Exception
	 */
	List<Floor>  queryAll() throws Exception;
	
	/**
	 * 通过id查询楼层
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Floor querybyId(Integer id) throws Exception;
	/**
	 * 修改楼层信息
	 * @param floor
	 * @return
	 * @throws Exception
	 */
	int update(Floor floor) throws Exception;
	
	/**
	 * 插入一条信息
	 * @param floor
	 * @return
	 * @throws Exception
	 */
	int insert(Floor floor) throws Exception;
	
	/**
     * 分页查询子专题
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     * @throws Exception
     */
	public PageBean queryFloorListByCriteria(CriteriaFloor criteria,
			Integer pc, Integer ps) throws Exception;
	
	/**
	 * 删除楼层及楼层管理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delete(Integer id) throws Exception;
	/**
     * 根据站点获取楼层信息
     * 
     * @param webset
     * @return
     * @throws Exception
     */
	 public List<FloorDto> selectAllByWebSet(String webset)throws Exception;
}

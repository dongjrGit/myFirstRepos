package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Floorproduct;
import com.yinlian.wssc.web.util.CriteriaFloorproduct;

public interface FloorproductService {
	
	/**
	 * 插入一条记录
	 * @param floorproduct
	 * @return
	 * @throws Exception
	 */
	public int insert(Floorproduct   floorproduct) throws Exception;
	
	/**
	 * 修改楼层管理信息
	 * @param floorproduct
	 * @return
	 * @throws Exception
	 */
	public int update(Floorproduct   floorproduct) throws Exception;
	
	/**
	 * 删除一条楼层管理信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delect(Integer id) throws Exception;
	
	/**
	 * 根据id查询楼层关联信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Floorproduct  queryById(Integer id) throws Exception;
	
	/**
     * 分页查询楼层的关联信息
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     * @throws Exception
     */
	public PageBean queryFloorListByCriteria(CriteriaFloorproduct criteria,
			Integer pc, Integer ps) throws Exception;
}

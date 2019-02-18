/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.config.Criteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Specstype;

public interface SpecstypeService {

	/**
	 * 根据ClassID获取规格类型
	 * @param classid
	 * @return
	 * @throws Exception
	 */
	public List<Specstype> getByClassID(Integer classid) throws Exception;
	/**
	 * 根据ClassID获取规格包括上级规格类型
	 * @param classid
	 * @return
	 * @throws Exception
	 */
	public List<Specstype> getBySuperiorClassID(com.yinlian.wssc.web.util.Criteria criteria) throws Exception;
	 
	 /**
	  * 添加规格类型
	  * @param stype
	  * @return
	  * @throws Exception
	  */
	public int insert(Specstype stype) throws Exception;
	 
	 /**
	  * 根据规格类型ID修改
	  * @param stype
	  * @return
	  * @throws Exception
	  */
	public int update(Specstype stype) throws Exception;
	 
	 /**
	  * 根据规格类型ID删除
	  * @param id
	  * @return
	  * @throws Exception
	  */
	public int del(Integer id) throws Exception;
	
	 /**
	  * 根据规格类型ID集合批量删除
	  * @param ids
	  * @return
	  * @throws Exception
	  */
	public int delList(List<Integer> ids) throws Exception;
	 /**
	  * 根据规格类型ID获取规格类型信息
	  * @param id
	  * @return
	  * @throws Exception
	  */
	public Specstype getByID(Integer id) throws Exception;
	 
	 /**
	  * 修改规格类型状态 0-启用 1-禁用
	  * @param status
	  * @param id
	  * @return
	  * @throws Exception
	  */
	public int updateStatus(Integer status,Integer id)throws Exception;
	 
	 /**
	  * 修改规格类型排序
	  * @param orderby
	  * @param id
	  * @return
	  * @throws Exception
	  */
	public int updateOrder(Integer orderby,Integer id)throws Exception;
	 
	 /**
	  * 批量修改规格类型排序
	  * @param orderbys
	  * @param ids
	  * @return
	  * @throws Exception
	  */
	public int updateOrderList(List<Integer> orderbys,List<Integer> ids)throws Exception;
	 
	 /**
	  * 查询规格类型数据（分页）
	  * @param classid
	  * @param pindex
	  * @param psize
	  * @return
	  * @throws Exception
	  */
	public PageBean getList(Integer classid,Integer pindex,Integer psize)throws Exception;
	 
	/**
	  * 查询规格类型数据（分页）
	  * @param classid
	  * @param pindex
	  * @param psize
	  * @return
	  * @throws Exception
	  */
	public PageBean getSonList(Integer classid,Integer classid2,Integer classid3,Integer pindex,Integer psize)throws Exception;
	
}

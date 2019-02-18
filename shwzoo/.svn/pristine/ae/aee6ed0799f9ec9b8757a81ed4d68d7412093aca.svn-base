package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.FindAppDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Findrecord;
import com.yinlian.wssc.web.util.CriteriaFind;

public interface FindRecordService {

	/**
	 * 查询所有图片
	 * 
	 */
	public List<Findrecord> selectimg() throws Exception;
	
	
	List<Findrecord> queryAllrecord() throws Exception;
	
	
	int add(Findrecord record) throws Exception;
	
	 PageBean selectFindrecordListByPage(CriteriaFind criteria, Integer pc, Integer ps)
             throws Exception;
	 
	 
	 /**
	     * 根据id删除
	     * @param toInt
	     * @return
	     */
	    int deleteById(Integer id) throws Exception;
	    
	    
	   Findrecord selectFind(Integer id) throws Exception;
	   
	   
	   int updateById(Findrecord record) throws Exception;
	   
	   /**
		  * 修改发现状态 1-启用 0-禁用
		  * @param status
		  * @param id
		  * @return
		  * @throws Exception
		  */
		public int updateStatus(Integer status,Integer id)throws Exception;
		 
		 /**
		  * 修改发现排序
		  * @param orderby
		  * @param id
		  * @return
		  * @throws Exception
		  */
		public int updateOrder(Integer sort,Integer id)throws Exception;
		 
		 /**
		  * 批量修改发现排序
		  * @param orderbys
		  * @param ids
		  * @return
		  * @throws Exception
		  */
		public int updateOrderList(List<Integer> sorts,List<Integer> ids)throws Exception;
		
		public List<FindAppDto> getListByType(Integer type) throws Exception;


		public List<Findrecord> queryFind()throws Exception;
	   
 }


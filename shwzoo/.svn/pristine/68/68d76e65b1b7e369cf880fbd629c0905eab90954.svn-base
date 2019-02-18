package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Announcement;
import com.yinlian.wssc.web.util.Criteriaannouncement;




public interface AnnouncementService {
	int add(Announcement announcement) throws Exception;
	
	List<Announcement> queryAllrecord() throws Exception;
	
	PageBean selectAnnouncementByPage(Criteriaannouncement criteria, Integer pc, Integer ps)
            throws Exception;
	
	int delete(Integer id) throws Exception;
	
	
	Announcement selectByid(Integer id) throws Exception;
	
	int updateAnnouncement(Announcement announcement) throws Exception;
	
	/**
	  * 修改广告排序
	  * @param sort
	  * @param id
	  * @return
	  * @throws Exception
	  */
	public int updateOrder(Integer sort,Integer id)throws Exception;
	 
	 /**
	  * 批量修改广告排序
	  * @param sorts
	  * @param ids
	  * @return
	  * @throws Exception
	  */
	public int updateOrderList(List<Integer> sorts,List<Integer> ids)throws Exception;
}

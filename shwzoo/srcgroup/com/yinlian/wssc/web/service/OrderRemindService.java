package com.yinlian.wssc.web.service;


import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.OrderRemind;

public interface OrderRemindService {
	OrderRemind selectByExample(OrderRemind remind)throws Exception;;
	
	OrderRemind selectByPrimaryKey(int id)throws Exception;;
	
	int deleteByPrimaryKey(int id)throws Exception;;
	
	int insert(OrderRemind remind)throws Exception;;
	
	int updateByPrimaryKeySelective(OrderRemind remind)throws Exception;;
	
	public PageBean selectOrderRemindPage(Criteria criteria, Integer pc, Integer ps) throws Exception;
	
	OrderRemind getDescOrderRemindByid(Integer ordernum) throws Exception;
	
	public PageBean OrderRemindListbyclerkid(Criteria criteria, Integer pc, Integer ps)throws Exception;
}

package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.orderremindMapper;
import com.yinlian.wssc.web.po.OrderRemind;
import com.yinlian.wssc.web.service.OrderRemindService;
import com.yinlian.wssc.web.util.PageBeanUtil;
@Component("OrderRemindService")
public class OrderRemindimpl implements OrderRemindService {

	@Autowired
	protected orderremindMapper orderremindmapper;
	@Override
	public OrderRemind selectByExample(OrderRemind remind)throws Exception {
		return null;
	}  

	@Override
	public OrderRemind selectByPrimaryKey(int id)throws Exception {
		return orderremindmapper.selectByPrimaryKey(id);
	}  

	@Override
	public int deleteByPrimaryKey(int id)throws Exception {
		return orderremindmapper.deleteByPrimaryKey(id);
	} 

	@Override
	public int insert(OrderRemind remind) throws Exception {
		return orderremindmapper.insert(remind);
	} 

	@Override
	public int updateByPrimaryKeySelective(OrderRemind remind)throws Exception {
		return orderremindmapper.updateByPrimaryKeySelective(remind);
	} 

	@Override
	public PageBean selectOrderRemindPage(Criteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil util=new PageBeanUtil(criteria, pc, ps);
		PageBean pageBean=util.getPage();
		List<OrderRemind> list=orderremindmapper.seleOrderRemindPage(util);
		pageBean.setBeanList(list);
		return pageBean;
	}

	@Override
	public OrderRemind getDescOrderRemindByid(Integer ordernum) throws Exception {
		return orderremindmapper.getDescOrderRemindByid(ordernum);
	}

	@Override
	public PageBean OrderRemindListbyclerkid(Criteria criteria, Integer pc,
			Integer ps) throws Exception {
		PageBeanUtil util=new PageBeanUtil(criteria, pc, ps);
		PageBean pageBean=util.getPage();
		List<OrderRemind> list=orderremindmapper.OrderRemindListbyclerkid(util);
		pageBean.setBeanList(list);
		return pageBean;
	}
	
	
}

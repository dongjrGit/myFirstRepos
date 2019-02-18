package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.wssc.web.dto.ThOrder;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ApplyforcancelorderMapper;
import com.yinlian.wssc.web.mapper.OrdersMapper;
import com.yinlian.wssc.web.po.Applyforcancelorder;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.ApplyforcancelorderService;
import com.yinlian.wssc.web.util.CriteriaSellerSh;
import com.yinlian.wssc.web.util.CriteriaSh;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("applyforcancelorderService")
public class ApplyforcancelorderServiceImpl implements ApplyforcancelorderService {
	@Autowired
	private ApplyforcancelorderMapper applyforcancelorderMapper;
	@Autowired
	private OrdersMapper orderedMapper;

	@Override
	public int add(Applyforcancelorder afco) throws Exception {
		return applyforcancelorderMapper.insert(afco);
	}

	public Applyforcancelorder getbyOrderAndType(int orderid, int type) throws Exception {
		return applyforcancelorderMapper.getbyOrderAndType(orderid, type);
	}
    public Applyforcancelorder  getbyOrderAndStatus(int orderid, int status) throws Exception {
		return applyforcancelorderMapper.getbyOrderAndStatus(orderid, status);
	}
	@Override
	public PageBean selectThListByPage(CriteriaSh criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<ThOrder> beanList = applyforcancelorderMapper.getThListByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public int del(Integer id) throws Exception {

		return applyforcancelorderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageBean selectSellerThListByPage(CriteriaSellerSh criteria, Integer pc, Integer ps) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<ThOrder> beanList = applyforcancelorderMapper.getSellerThListByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public int editNote(Integer id, String note) throws Exception {
		Applyforcancelorder record = applyforcancelorderMapper.selectByPrimaryKey(id);
		record.setNote(note);
		return applyforcancelorderMapper.updateByPrimaryKey(record);
	}

	@Override
	public String getNote(Integer id) throws Exception {
		Applyforcancelorder record = applyforcancelorderMapper.selectByPrimaryKey(id);
		return record.getNote();
	}

	@Override
	public int updatestatus(Integer id) throws Exception {
		Applyforcancelorder record = applyforcancelorderMapper.selectByPrimaryKey(id);
		record.setStatus(OrderStatusEnum.已取消.getValue());
		applyforcancelorderMapper.updateByPrimaryKey(record);
		Orders orders = orderedMapper.selectByPrimaryKey(record.getOrderid());
		orders.setStatus(OrderStatusEnum.已取消.getValue());
		return orderedMapper.updateByPrimaryKey(orders);
	}

	@Override
	public Applyforcancelorder getbyOrderAndUser(int orderid, int userid)
			throws Exception {	
		return applyforcancelorderMapper.getbyOrderAndUser(orderid, userid);
	}
	@Override
	public List<Applyforcancelorder>getApplyList(int orderid, int userid)
			throws Exception {	
		return applyforcancelorderMapper.getApplyList(orderid, userid);
	}
	@Override
	public List<Applyforcancelorder>getApplyListByOid(int orderid)
			throws Exception {	
		return applyforcancelorderMapper.getApplyListByOid(orderid);
	}
}

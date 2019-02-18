package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.GroupbuyClassMapper;
import com.yinlian.wssc.web.po.GroupbuyClass;
import com.yinlian.wssc.web.service.GroupByClassService;
import com.yinlian.wssc.web.util.CriteriaPage;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("groupByClassService")
public class GroupByClassServiceImpl implements GroupByClassService{
	
	@Autowired
	private GroupbuyClassMapper groupbuyClassMapper;
	
	@Override
	public PageBean selectByCriteria(CriteriaPage criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																       // 设置其他的参数
																       // 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<GroupbuyClass> beanList = groupbuyClassMapper.selectByCriteriaPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public int delById(Integer id) throws Exception {
		return groupbuyClassMapper.delById(id);
	}

	@Override
	public int addTglx(GroupbuyClass gbc) throws Exception {
		return groupbuyClassMapper.insertSelective(gbc);
	}

	@Override
	public GroupbuyClass selById(Integer id) throws Exception {
		return groupbuyClassMapper.selectById(id);
	}

	@Override
	public int updateById(GroupbuyClass gbc) throws Exception {
		return groupbuyClassMapper.updateById(gbc);
	}

	@Override
	public List<GroupbuyClass> getAllList() throws Exception {
	return groupbuyClassMapper.getAllList();
	}

	
	
}

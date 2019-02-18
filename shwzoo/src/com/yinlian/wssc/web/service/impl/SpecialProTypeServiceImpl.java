package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.search.CriteriaSpecialProType;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SpecialProtypeMapper;
import com.yinlian.wssc.web.po.SpecialProtype;
import com.yinlian.wssc.web.po.SpecialProtypeExample;
import com.yinlian.wssc.web.service.SpecialProTypeService;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.TopicCriteria;

@Component("SpecialProTypeService")
public class SpecialProTypeServiceImpl implements SpecialProTypeService{
	
	@Autowired
	private SpecialProtypeMapper specialProtypeMapper;

	/**
	 * 获取专题商品分类列表
	 */
	@Override
	public PageBean getSpecialProtypeList(CriteriaSpecialProType criteria,Integer page,Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);// 还可以设置其他的参数
		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<SpecialProtype> beanList = specialProtypeMapper.selectBySpecialProtypePage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * 获取专题商品分类
	 */
	@Override
	public SpecialProtype getSpecialProtype(Integer id) throws Exception {
		return specialProtypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 编辑专题商品分类
	 */
	@Override
	public int updateSpecialProtype(SpecialProtype specialProtype) throws Exception {
		return specialProtypeMapper.updateByPrimaryKeySelective(specialProtype);
	}

	/**
	 * 根据id删除专题商品分类
	 */
	@Override
	public int delSpecialProtype(Integer id) throws Exception {
		return specialProtypeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 添加专题商品分类
	 */
	@Override
	public int insertSpecialProtype(SpecialProtype specialProtype) throws Exception {
		return specialProtypeMapper.insertSelective(specialProtype);
	}

	@Override
	public List<SpecialProtype> queryAll(Integer id) throws Exception{
		SpecialProtypeExample ex=new SpecialProtypeExample();
		SpecialProtypeExample.Criteria criteria=ex.createCriteria();
		criteria.andSpecialidEqualTo(id);
		return specialProtypeMapper.selectByExample(ex);
	}

	@Override
	public List<SpecialProtype> queryAll(String name, Integer id) throws Exception {
		SpecialProtypeExample ex=new SpecialProtypeExample();
		SpecialProtypeExample.Criteria criteria=ex.createCriteria();
		criteria.andSpecialidEqualTo(id);
		criteria.andShownameLike(name);
		return specialProtypeMapper.selectByExample(ex);
	}

	@Override
	public List<SpecialProtype> findByTopicCruteria(TopicCriteria criteria) throws Exception {
		return specialProtypeMapper.selectByTopicCruteria(criteria);
	}


}

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SpecstypeMapper;
import com.yinlian.wssc.web.mapper.SpecstypeMapperCustom;
import com.yinlian.wssc.web.po.Specstype;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.SpecstypeService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 商品规格类型业务类
 * 
 * @author Administrator
 *
 */
public class SpecstypeServiceImpl implements SpecstypeService {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(SpecsvaluesServiceImpl.class);

	@Autowired
	private SpecstypeMapperCustom specstypeMapperCustom;

	@Autowired
	private SpecstypeMapper specstypeMapper;
	
	@Autowired
	private  CategoryService categoryService;

	/**
	 * 根据ClassID获取规格类型（不分页）
	 * 
	 * @param classid
	 * @return
	 */
	public List<Specstype> getByClassID(Integer classid) throws Exception {
		if (classid == null || classid <= 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException(
						"The parameter classid is null!");
			}
		}
		return specstypeMapperCustom.getByClassID(classid);
	}
	
	/**
	 * 根据ClassID获取规格包括上级规格类型
	 * @param classid
	 * @return
	 * @throws Exception
	 */
	public List<Specstype> getBySuperiorClassID(Criteria criteria){
		return specstypeMapperCustom.getBySuperiorClassID(criteria);
	}

	/**
	 * 根据ID获取规格类型
	 * 
	 * @param id
	 * @return
	 */
	public Specstype getByID(Integer id) throws Exception {
		if (id == null || id <= 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException("The parameter id is null!");
			}
		}
		return specstypeMapperCustom.getByID(id);
	}

	/**
	 * 添加
	 * 
	 * @param stype
	 * @return
	 */
	public int insert(Specstype stype) throws Exception {
		return specstypeMapper.insert(stype);
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 */
	public int update(Specstype stype) throws Exception {
		return specstypeMapper.updateByPrimaryKey(stype);
	}

	/**
	 * 刪除
	 * 
	 * @param id
	 * @return
	 */
	public int del(Integer id) throws Exception {
		return specstypeMapper.deleteByPrimaryKey(id);
	}
	
	/** 批量删除
	 * @param ids
	 * @return
	 */
	public int delList(List<Integer> ids) throws Exception{
		return specstypeMapperCustom.deleteList(ids);
	}
	/**
	 * 禁用 或启用
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public int updateStatus(Integer status, Integer id) throws Exception {

		return specstypeMapperCustom.updateStatus(status, id);
	}

	/**
	 * 修改排序
	 * 
	 * @param orderby
	 * @param id
	 */
	public int updateOrder(Integer orderby, Integer id) throws Exception {
		return specstypeMapperCustom.updateOrder(orderby, id);
	}

	/**
	 * 批量修改排序
	 * 
	 * @param orderbys
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int updateOrderList(List<Integer> orderbys, List<Integer> ids)
			throws Exception {
		Integer orderby = 0, id = 0;
		List<Specstype> list = new ArrayList<Specstype>();
		for (int i = 0; i < orderbys.size(); i++) {
			Specstype stype = new Specstype();
			orderby = orderbys.get(i);
			id = ids.get(i);
			stype = specstypeMapperCustom.getByID(id);
			stype.setOrderby(orderby);
			list.add(stype);
		}

		return specstypeMapperCustom.updateOrderList(list);
		// 百度搜索 mybatis中最快的更新 有待研究
		// SqlSession sqlSession =
		// sqlSessionFactory.getObject().openSession(ExecutorType.BATCH);
		// try {
		// sqlSession.insert("com.emg.trans.mapper.batchMapper.batchInsert",
		// list);
		// sqlSession.commit();
		// } finally {
		// sqlSession.close();
		// }
	}

	public PageBean getList(Integer classid, Integer pindex, Integer psize)
			throws Exception {

		Criteria criteria = new Criteria();
		if(classid!=null && classid>0){
			criteria.setClassid(classid);
		}
		criteria.setOrderByClause("orderBy");
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pindex, psize);// 还可以
																				// 设置其他的参数
																				// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Specstype> beanList = specstypeMapperCustom.selectSpecsPage(pageBeanUtil);
				
		pageBean.setBeanList(beanList);

		return pageBean;
	}
	
	public PageBean getSonList(Integer classid,Integer classid2,Integer classid3, Integer pindex, Integer psize)
			throws Exception {

		Criteria criteria = new Criteria();
		if(classid!=null && classid>0){
			criteria.setClassid(classid);
		}
		if (classid2!=null && classid2>0) {
			criteria.setClassid2(classid2);
		}
		if (classid3!=null && classid3>0) {
			criteria.setClassid3(classid3);
		}	
		criteria.setOrderByClause("orderBy");
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pindex, psize);// 还可以
																				// 设置其他的参数
																				// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Specstype> beanList = specstypeMapperCustom.selectSonPage(pageBeanUtil);
		for (Specstype s : beanList) {
			s.setFullpathName(categoryService.GetFullNamePath(s.getFullpath()));
		}
		pageBean.setBeanList(beanList);

		return pageBean;
	}

}

package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.search.CriteriaSpecialProType;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.SpecialProtype;
import com.yinlian.wssc.web.util.TopicCriteria;

public interface SpecialProTypeService {
	
	/**
	 * 获取专题商品分类列表
	 * @param specialid
	 * @param name
	 * @return
	 * @throws Exception
	 */
	PageBean getSpecialProtypeList(CriteriaSpecialProType criteria,Integer page,Integer size)throws Exception;
	
	/**
	 * 获取专题商品分类
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SpecialProtype getSpecialProtype(Integer id)throws Exception;
	
	/**
	 * 编辑专题商品分类
	 * @param specialProtype
	 * @return
	 * @throws Exception
	 */
	int updateSpecialProtype(SpecialProtype specialProtype)throws Exception;
	
	/**
	 * 根据id删除专题商品分类
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delSpecialProtype(Integer id)throws Exception;
	
	/**
	 * 添加专题商品分类
	 * @param specialProtype
	 * @return
	 * @throws Exception
	 */
	int insertSpecialProtype(SpecialProtype specialProtype)throws Exception;

	/**
	 * 更具专题ID获取分类
	 * 
	 * @author kh.wang
	 * @since 2016年10月21日
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<SpecialProtype> queryAll(Integer id)throws Exception;
	/**
	 * 更具专题ID获取分类 和名称模糊查询
	 * 
	 * @author kh.wang
	 * @since 2016年10月21日
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<SpecialProtype> queryAll(String name, Integer id)throws Exception;

	/**
	 * 获取专题分类
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	List<SpecialProtype> findByTopicCruteria(TopicCriteria criteria)throws Exception;
	
}

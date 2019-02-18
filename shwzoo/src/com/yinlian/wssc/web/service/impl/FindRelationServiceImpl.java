package com.yinlian.wssc.web.service.impl;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.search.Api_FindCriteria;
import com.yinlian.wssc.web.dto.FindRelateArticlesDto;
import com.yinlian.wssc.web.dto.FindRelateShopDto;
import com.yinlian.wssc.web.dto.FindRelateTopicDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.FindRelationMapper;
import com.yinlian.wssc.web.po.FindRelation;
import com.yinlian.wssc.web.po.Floor;
import com.yinlian.wssc.web.service.FindRelationService;
import com.yinlian.wssc.web.util.PageBeanUtil;
@Component("findRelationService")
public class FindRelationServiceImpl implements FindRelationService {
	
	private static final Logger  logger = LoggerFactory.getLogger(FindRelationServiceImpl.class);
	
	@Autowired
	private FindRelationMapper   findRelationMapper;

	@Override
	public FindRelation queryInfoById(Integer id) throws Exception {

		return findRelationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertFindRelation(FindRelation findRelation) throws Exception {
		
		return findRelationMapper.insertSelective(findRelation);
	}

	@Override
	public int deleteFindRelation(Integer id) throws Exception {
		
		return findRelationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateFindRelation(FindRelation findRelation) throws Exception {
		
		return findRelationMapper.updateByPrimaryKeySelective(findRelation);
	}

	@Override
	public PageBean selectFindspuList(Api_FindCriteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<FindRelateShopDto> beanList = findRelationMapper.selectFindShopByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public PageBean selectFindTopicList(Api_FindCriteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<FindRelateTopicDto> beanList = findRelationMapper.selectFindTopicByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public PageBean selectFindArtList(Api_FindCriteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<FindRelateArticlesDto> beanList = findRelationMapper.selectFindArtByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	
	
	/*@Override
	public PageBean queryFindRelationByCriteria(CriteriaFindRelate criteria, Integer pc, Integer ps)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<TopicRelate> beanList = topicRelateMapper.selectTopicRelateByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
	}*/

	
}

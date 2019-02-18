package com.yinlian.wssc.web.service;


import java.util.List;

import com.yinlian.wssc.search.Api_FindCriteria;
import com.yinlian.wssc.web.dto.FindRelateArticlesDto;
import com.yinlian.wssc.web.dto.FindRelateShopDto;
import com.yinlian.wssc.web.dto.FindRelateTopicDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.FindRelation;

public interface FindRelationService {

	/**
	 * 获取发现关联信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	FindRelation queryInfoById(Integer id) throws Exception;
	
	
	/**
	 * 添加发现关联信息
	 * @param findRelation
	 * @return
	 * @throws Exception
	 */
	int insertFindRelation(FindRelation findRelation) throws Exception;


	int deleteFindRelation(Integer id) throws Exception;


	int updateFindRelation(FindRelation findRelation) throws Exception;


	PageBean selectFindspuList(Api_FindCriteria criteria, Integer pc, Integer ps) throws Exception;


	PageBean selectFindTopicList(Api_FindCriteria criteria, Integer pc, Integer ps)throws Exception;


	PageBean selectFindArtList(Api_FindCriteria criteria, Integer pc, Integer ps)throws Exception;


	   
 }


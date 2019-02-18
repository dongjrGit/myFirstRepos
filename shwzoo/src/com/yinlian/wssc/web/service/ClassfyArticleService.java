/*
 * @(#) ClassfyArticleService.java 2016年8月15日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.po.ClassfyArticle;
import com.yinlian.wssc.web.util.CriteriaClassfyArticlePage;

public interface ClassfyArticleService {
	
	
	int add(ClassfyArticle article);

	PageBean queryCriteriaPage(CriteriaClassfyArticlePage criteria, Integer page,Integer size);

	/**
	 * 删除
	 * 
	 * @author kh.wang
	 * @since 2016年8月16日
	 * @param id
	 * @return
	 */
	int del(Integer id);

	/**
	 * 判断 类型id 于文章id是否存在
	 * 
	 * @author kh.wang
	 * @since 2016年8月16日
	 * @param articleId
	 * @param classfyId
	 * @return
	 */
	int classfyAndArticleIs(Integer articleId, Integer classfyId);
	
	
	/**
	 * 获取指定类型的文章
	 * 
	 * @author kh.wang
	 * @since 2016年8月16日
	 * @param ids
	 * @return
	 */
	List<Articles> queryByClassfyId(Integer ids);

}

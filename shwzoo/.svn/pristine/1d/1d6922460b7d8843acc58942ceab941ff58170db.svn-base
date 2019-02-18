/*
 * @(#) ClassfyArticleServiceImpl.java 2016年8月15日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ClassfyArticleMapper;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.po.ClassfyArticle;
import com.yinlian.wssc.web.service.ClassfyArticleService;
import com.yinlian.wssc.web.util.CriteriaClassfyArticlePage;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("classfyArticleService")
public class ClassfyArticleServiceImpl implements ClassfyArticleService {
	@Autowired
	private ClassfyArticleMapper classfyarticlemapper;

	@Override
	public int add(ClassfyArticle article) {
		// TODO Auto-generated method stub
		return classfyarticlemapper.insert(article);
	}

	@Override
	public PageBean queryCriteriaPage(CriteriaClassfyArticlePage criteria,
			Integer page, Integer size) {
		PageBeanUtil beanUtil=new PageBeanUtil(criteria, page, size);
		PageBean bean=beanUtil.getPage();
		List<ClassfyArticle> articles=classfyarticlemapper.selectCriteriaPage(beanUtil);
		bean.setBeanList(articles);
		return bean;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return classfyarticlemapper.delete(id);
	}

	@Override
	public int classfyAndArticleIs(Integer articleId, Integer classfyId) {
		return classfyarticlemapper.classfyAndArticleIs(articleId,classfyId);
	}

	@Override
	public List<Articles> queryByClassfyId(Integer id) {
		return classfyarticlemapper.queryByClassfyId(id);
	}


}

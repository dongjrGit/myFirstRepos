/*
 * @(#) ClassfyArticleMapper.java 2016年8月15日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.po.ClassfyArticle;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ClassfyArticleMapper {
	int insert(ClassfyArticle classfyArticle);

	List<ClassfyArticle> selectCriteriaPage(PageBeanUtil beanUtil);

	int delete(Integer id);

	int classfyAndArticleIs(Integer articleId, Integer classfyId);

	List<Articles> queryByClassfyId(Integer id);

	List<Articles> findByClassId(Integer id);
}

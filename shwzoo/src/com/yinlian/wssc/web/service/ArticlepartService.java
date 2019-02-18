package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.Enums.ArticleNavType;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.ArticlePart;
import com.yinlian.wssc.web.po.ArticlePartExample;
import com.yinlian.wssc.web.po.Articles;

public interface ArticlepartService {

	    boolean deleteByID(int id)throws Exception;

	    boolean insert(ArticlePart record)throws Exception;

	    List<ArticlePart> selectByExample(ArticlePartExample example)throws Exception;

	    ArticlePart selectByID(Integer id)throws Exception;

	    int update(ArticlePart record)throws Exception;
	    
	    PageBean getListByPage(int page, int size) throws Exception;
	    
	    ArticlePart selectByName(String name) throws Exception;
}

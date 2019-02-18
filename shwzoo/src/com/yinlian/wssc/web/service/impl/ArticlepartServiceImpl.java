package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.ArticleNavType;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ArticlePartMapper;
import com.yinlian.wssc.web.po.ArticlePart;
import com.yinlian.wssc.web.po.ArticlePartExample;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.service.ArticlepartService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("articlepartService")
public class ArticlepartServiceImpl implements ArticlepartService {

	@Autowired 
	private ArticlePartMapper articlepartMapper;
	@Override
	public boolean deleteByID(int id) throws Exception {

		return articlepartMapper.deleteByPrimaryKey(id)>0;
	}

	@Override
	public boolean insert(ArticlePart record) throws Exception {

		return articlepartMapper.insert(record)>0;
	}

	@Override
	public List<ArticlePart> selectByExample(ArticlePartExample example)
			throws Exception {

		return articlepartMapper.selectByExample(example);
	}

	@Override
	public ArticlePart selectByID(Integer id) throws Exception {

		return articlepartMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(ArticlePart record) throws Exception {

		return articlepartMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageBean getListByPage(int page, int size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<ArticlePart> beanList=articlepartMapper.getListByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public ArticlePart selectByName(String name) throws Exception {
		return articlepartMapper.selectByName(name);
	}

}





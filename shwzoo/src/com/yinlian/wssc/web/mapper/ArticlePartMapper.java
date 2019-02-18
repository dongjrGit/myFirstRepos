package com.yinlian.wssc.web.mapper;

import com.yinlian.wssc.web.po.ArticlePart;
import com.yinlian.wssc.web.po.ArticlePartExample;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.util.PageBeanUtil;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ArticlePartMapper {
   
    int countByExample(ArticlePartExample example)throws Exception;

    int deleteByExample(ArticlePartExample example)throws Exception;

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(ArticlePart record)throws Exception;

    int insertSelective(ArticlePart record)throws Exception;

    List<ArticlePart> selectByExample(ArticlePartExample example)throws Exception;

    ArticlePart selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") ArticlePart record, @Param("example") ArticlePartExample example);

    int updateByExample(@Param("record") ArticlePart record, @Param("example") ArticlePartExample example);

    int updateByPrimaryKeySelective(ArticlePart record)throws Exception;

    int updateByPrimaryKey(ArticlePart record)throws Exception;
    
    List<ArticlePart> getListByPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    ArticlePart selectByName(String name)throws Exception;

	List<Articles> selectByPartid(int value);
    
}





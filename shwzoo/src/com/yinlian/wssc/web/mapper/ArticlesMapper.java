package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.Api_ArticlesDto;
import com.yinlian.api.app.dto.Api_NewsDto;
import com.yinlian.pc.dto.NavfyDto;
import com.yinlian.pc.dto.ArticleDto;
import com.yinlian.wssc.web.dto.ArticlesDto;
import com.yinlian.wssc.web.dto.FindRelationDto;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.po.ArticlesExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ArticlesMapper {
    int countByExample(ArticlesExample example);

    int deleteByExample(ArticlesExample example);

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(Articles record)throws Exception;

    int insertSelective(Articles record)throws Exception;

    List<Articles> selectByExampleWithBLOBs(ArticlesExample example);

    List<Articles> selectByExample(ArticlesExample example)throws Exception;

    Articles selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") Articles record, @Param("example") ArticlesExample example);

    int updateByExampleWithBLOBs(@Param("record") Articles record, @Param("example") ArticlesExample example);

    int updateByExample(@Param("record") Articles record, @Param("example") ArticlesExample example);

    int updateByPrimaryKeySelective(Articles record);

    int updateByPrimaryKeyWithBLOBs(Articles record)throws Exception;

    int updateByPrimaryKey(Articles record)throws Exception;
    
    Articles selectByclassfyid(Integer classfyid)throws Exception;
    
    /**
     * 查询网站新闻列表
     * @param value
     * @return
     */
	List<Api_ArticlesDto> selectNews(int value)throws Exception;
	
	/**
	 * 根据id查询网站新闻详细信息
	 * @param id
	 * @return
	 */
	Api_NewsDto selectNewsInfo(Integer id)throws Exception;
	/**
	 * 分页查询文章管理
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<ArticlesDto> selectArticlesManageByPage(PageBeanUtil pageBeanUtil)throws Exception;
	
	/**
	 * 分页查询新闻
	 * @param pageBeanUtil
	 * @return
	 */
	List<Articles> selectNewsByPage(PageBeanUtil pageBeanUtil)throws Exception;
    
    Articles selectByclassfy(String classfy)throws Exception;
    
    List<ArticlesDto> getArticlesByPage(PageBeanUtil pageBeanUtil)throws Exception;
    
    int updateClickNum(int id)throws Exception;
    
    List<NavfyDto> getNavfyDto(@Param("partid") int partid)throws Exception;
    
    List<ArticleDto> getArticleDtoByPart(int partid)throws Exception;

	//Articles selectByclassfyid(String title) throws Exception;

	Articles selectByTitle(String title) throws Exception;

	List<Articles> selectByPartid(int value) throws Exception;

	List<Articles> selectAll() throws Exception;

	List<FindRelationDto> seleteFindRelationByPage(PageBeanUtil pageBeanUtil)  throws Exception;

	List<Articles> getSpuStartWithNames(String name) throws Exception;

}
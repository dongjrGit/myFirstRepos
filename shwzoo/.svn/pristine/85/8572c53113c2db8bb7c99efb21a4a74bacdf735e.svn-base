package com.yinlian.wssc.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.Enums.ArticleNavType;
import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.api.app.dto.Api_ArticlesDto;
import com.yinlian.api.app.dto.Api_NewsDto;
import com.yinlian.pc.dto.ArticleDto;
import com.yinlian.pc.dto.NavfyDto;
import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.util.CriteriaFindRelate;
import com.yinlian.wssc.web.util.CriteriaPage;

public interface ArticlesService {
	
	/**
	 * 通过导航分类ID查询文章信息
	 * @param classfyid
	 * @return
	 * @throws Exception
	 */
	public Articles selectByClassfyId(Integer classfyid) throws Exception;
	
	/**
	 * 查询网站的新闻
	 * @param value
	 * @return
	 */
	public List<Api_ArticlesDto> selectNews(int value)throws Exception;;
	
	/**
	 * 新闻详细信息
	 * @param toInt
	 * @return
	 */
	public Api_NewsDto selectNewsInfo(Integer id)throws Exception;;
	/**
	 * 分页查询文章管理
	 * 
	 * @param criteria
	 * @param toInt
	 * @param toInt2
	 * @return
	 * @throws Exception
	 */
	public PageBean queryArticlesManagerByPage(CriteriaPage criteria,
			Integer pc, Integer ps) throws Exception;
	/**
	 * 根据id查询内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Articles queryarticlebyid(Integer id) throws Exception;
	/**
	 * 根据id删除
	 * 
	 * @param toInt
	 * @return
	 * @throws Exception
	 */

	public Integer delArticles(Integer toInt) throws Exception;
	
	/**
	 * 分页查询新闻
	 * @param criteria
	 * @param toInt
	 * @param toInt2
	 * @return
	 */
	public PageBean queryNewsByPage(CriteriaPage criteria, Integer pc,
			Integer ps) throws Exception;
	
	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	
	public Articles selectByID(Integer id)throws Exception;; 
	/**
	 * 添加文章内容
	 * 
	 * @param articles
	 * @return
	 * @throws Exception
	 */
	public Integer addNavClass(Articles articles) throws Exception;
	/**
	 * 修改文章内容
	 * 
	 * @param articles
	 * @return
	 * @throws Exception
	 */
	public Integer editArticle(Articles articles) throws Exception;

	/**
	 * 通过导航分类查询文章信息
	 * @param classfy
	 * @return
	 * @throws Exception
	 */
	public Articles selectByClassfy(String classfy) throws Exception;
	
	public PageBean getArticlesByPage(CriteriaPage criteria, Integer page,
			Integer size) throws Exception;
	
	int updateClickNum(int id)throws Exception;
	
    List<NavfyDto> getNavfyDto(@Param("partid") int partid)throws Exception;
    
    List<ArticleDto> getArticleDtoByPart(int partid)throws Exception;

	public Articles selectByName(String title) throws Exception;

	public List<Articles> findByPartid(ArticleNavType valueOf) throws Exception;
	
	
	public List<Articles> findAll() throws Exception;
	
	
	List<NavclassfyDto> findByAssign(ClassifyPageType navType,WebSetEnum webSetEnum, String name) throws Exception;

	public PageBean queryFindRelationByCriteria(CriteriaFindRelate criteria, Integer pc, Integer ps)throws Exception;

	public List<Articles> getArticleStartWithNames(String name) throws Exception;

	

}

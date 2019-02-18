package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.jpush.api.utils.StringUtils;

import com.yinlian.Enums.ArticleNavType;
import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.api.app.dto.Api_ArticlesDto;
import com.yinlian.api.app.dto.Api_NewsDto;
import com.yinlian.pc.dto.ArticleDto;
import com.yinlian.pc.dto.NavfyDto;
import com.yinlian.wssc.web.dto.ArticlesDto;
import com.yinlian.wssc.web.dto.FindRelationDto;
import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ArticlesMapper;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.ClassfyArticleService;
import com.yinlian.wssc.web.service.NavclassfyService;
import com.yinlian.wssc.web.util.CriteriaFindRelate;
import com.yinlian.wssc.web.util.CriteriaPage;
import com.yinlian.wssc.web.util.PageBeanUtil;

import data.StringUtil;

@Component("articlesService")
public class ArticlesServiceImpl implements ArticlesService{

	private static final Logger  logger = LoggerFactory.getLogger(ArticlesServiceImpl.class);
	
	@Autowired
	private ArticlesMapper       articlesMapper;
	@Autowired
	private NavclassfyService navclassfyService;
	@Autowired
	private ClassfyArticleService classfyArticleService;
	
	@Override
	public Articles selectByClassfyId(Integer classfyid) throws Exception {
		
		return articlesMapper.selectByclassfyid(classfyid);
	}
	
	/**
	 * 查询网站新闻列表
	 */
	@Override
	public List<Api_ArticlesDto> selectNews(int value)throws Exception{
		return articlesMapper.selectNews(value);
	}
	
	/**
	 * 网站新闻详细信息
	 */
	@Override
	public Api_NewsDto selectNewsInfo(Integer id)throws Exception {
		Articles  articles=articlesMapper.selectByPrimaryKey(id);
		if(articles!=null){
			Integer clickNum=articles.getClicknum();
			articles.setClicknum(clickNum+1);
			articlesMapper.updateByPrimaryKeySelective(articles);
		}
		return articlesMapper.selectNewsInfo(id);
	}
	/**
	 *分页查询文章管理
	 * @see com.yinlian.wssc.web.service.ArticlesService#queryArticlesManagerByPage(com.yinlian.wssc.web.util.CriteriaPage, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryArticlesManagerByPage(CriteriaPage criteria,
			Integer pc, Integer ps) throws Exception {
		  PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
		                                                                // 设置其他的参数
		                                                               // 多条件查询
          PageBean pageBean = pageBeanUtil.getPage();
          List<ArticlesDto> beanList = articlesMapper.selectArticlesManageByPage(pageBeanUtil);
          pageBean.setBeanList(beanList);
          return pageBean;
	}
	/**
	 * 根据id查询内容
	 * @see com.yinlian.wssc.web.service.ArticlesService#queryarticlebyid(java.lang.Integer)
	 */
	@Override
	public Articles queryarticlebyid(Integer id) throws Exception {
		
		return articlesMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据id删除
	 * @see com.yinlian.wssc.web.service.ArticlesService#delArticles(java.lang.Integer)
	 */
	@Override
	public Integer delArticles(Integer toInt) throws Exception {
		
		return articlesMapper.deleteByPrimaryKey(toInt);
	}
	/**
	 * 添加文章内容
	 * @see com.yinlian.wssc.web.service.ArticlesService#addNavClass(com.yinlian.wssc.web.po.Articles)
	 */
	@Override
	public Integer addNavClass(Articles articles) throws Exception {
		
		return articlesMapper.insert(articles);
	}
	/**
	 * 修改文章内容
	 * @see com.yinlian.wssc.web.service.ArticlesService#editArticle(com.yinlian.wssc.web.po.Articles)
	 */
	@Override
	public Integer editArticle(Articles articles) throws Exception {
		// TODO Auto-generated method stub
		return articlesMapper.updateByPrimaryKey(articles);
	}
	
	
	@Override
	public PageBean queryNewsByPage(CriteriaPage criteria, Integer pc,
			Integer ps) throws Exception{
		
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																        // 设置其他的参数
																       // 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Articles> beanList = articlesMapper.selectNewsByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public Articles selectByID(Integer id)throws Exception {
		
		return articlesMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Articles selectByClassfy(String classfy) throws Exception {
		
		return articlesMapper.selectByclassfy(classfy);
	}

	@Override
	public PageBean getArticlesByPage(CriteriaPage criteria, Integer page,
			Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
        PageBean pageBean = pageBeanUtil.getPage();
        
        List<ArticlesDto> beanList = articlesMapper.getArticlesByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public int updateClickNum(int id) throws Exception {

		return articlesMapper.updateClickNum(id);
	}

	@Override
	public List<NavfyDto> getNavfyDto(int partid) throws Exception {

		return articlesMapper.getNavfyDto(partid);
	}

	@Override
	public List<ArticleDto> getArticleDtoByPart(int partid) throws Exception {

		return articlesMapper.getArticleDtoByPart(partid);
	}

	@Override
	public Articles selectByName(String title) throws Exception {
		
		return articlesMapper.selectByTitle(title);
	}

	@Override
	public List<Articles> findByPartid(ArticleNavType valueOf)throws Exception {
		return articlesMapper.selectByPartid(valueOf.getValue());
	}

	@Override
	public List<Articles> findAll()throws Exception {
		return articlesMapper.selectAll();
	}
	
	List<NavclassfyDto> navclassfylist;
	List<Articles> articleslist;

	@Override
	public List<NavclassfyDto> findByAssign(ClassifyPageType navType,WebSetEnum webSetEnum,String name) {
		navclassfylist=navclassfyService.queryRelevance(navType.getValue(),webSetEnum.getValue());
		List<NavclassfyDto> newnav=new ArrayList<NavclassfyDto>();
		
		newnav=navclassfylist.stream().filter(n->n.getParentid()==0
				&&n.getPageType()==navType.getValue()
				&&n.getWebSet().indexOf(webSetEnum.getValue()+"")>=0
				&&n.getRelevance().equals("0")
				&&n.getClassname().matches(name)).collect(Collectors.toList());
		navclassfylistSon(newnav, navType, webSetEnum);
		return newnav;
	}
	
	private List<NavclassfyDto> navclassfylistSon(List<NavclassfyDto> newnav,ClassifyPageType navType,WebSetEnum webSetEnum){
		for (NavclassfyDto navclassfyDto : newnav) {
			navclassfyDto.setArticles(classfyArticleService.queryByClassfyId(navclassfyDto.getId()));
			navclassfyDto.setList(navclassfylist.stream().filter(n->n.getParentid().equals(navclassfyDto.getId())
					&&n.getPageType()==navType.getValue()
					&&n.getWebSet().matches(webSetEnum.getValue()+"")).collect(Collectors.toList()));
			if (navclassfyDto.getList()!=null&&navclassfyDto.getList().size()>0) {
				navclassfylistSon(navclassfyDto.getList(), navType, webSetEnum);
			}else{
				continue;
			}
		}
		return newnav;
	}



	@Override
	public PageBean queryFindRelationByCriteria(CriteriaFindRelate criteria, Integer pc, Integer ps) throws Exception {
		 PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																         // 设置其他的参数
																        // 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<FindRelationDto> beanList = articlesMapper.seleteFindRelationByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<Articles> getArticleStartWithNames(String name) throws Exception {
		
		return articlesMapper.getSpuStartWithNames(name);
	}


}

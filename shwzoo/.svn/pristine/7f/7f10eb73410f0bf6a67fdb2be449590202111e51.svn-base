package com.yinlian.wssc.platform.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ArticleNavType;
import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.wssc.platform.vo.MemberVo;
import com.yinlian.wssc.web.po.ArticlePart;
import com.yinlian.wssc.web.po.ArticlePartExample;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.po.Navclassfy;
import com.yinlian.wssc.web.service.ArticlepartService;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.NavclassfyService;
import com.yinlian.wssc.web.util.CriteriaPage;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 页面管理的控制层 PageViewController.java
 * 
 * @author Administrator
 * @version $Id: PageViewController.java, v 0.1 2016年5月18日 下午2:34:00
 *          Administrator Exp $
 */
@Controller
@RequestMapping("/platform/zd")
public class PageViewController {

	@Autowired
	private ArticlesService articlesService;

	@Autowired
	private ArticlepartService articlepartService;
	@Autowired
	private NavclassfyService navclassfyService;

//	/**
//	 * 显示文章管理页面
//	 * 
//	 * @return
//	 */
//	@RequestMapping("/showArticleManager")
//	public String showArticle() {
//
//		return "platform/zd/Articles";
//	}

	/**
	 * 显示文章管理添加/编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/showArticleManagerAdd")
	public String showArticleAdd(String id, HttpServletRequest request) {
		Articles articles =new Articles();
		String action="addArticle";
		Integer fid=0;
		try{
			if(StringUtilsEX.ToInt(id)>0){
				articles = articlesService.selectByID(StringUtilsEX.ToInt(id));
				action="editArticle";
				if(articles.getClassfyid()!=null && articles.getClassfyid()>0){
					Navclassfy nav=navclassfyService.queryArticleClassById(articles.getClassfyid());
					if(nav!=null){
						/*if(nav.getDepth()>1){
							Navclassfy navf=navclassfyService.queryArticleClassById(nav.getParentid());
							if(navf!=null){
								fid=navf.getId();
							}
						}*/
					}
				}
				
			}		
			//request.setAttribute("suparticle", articlesService.findByPartid(ArticleNavType.valueOf(partid)));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("fid", fid);
		request.setAttribute("action", action);
		request.setAttribute("art", articles);
		return "platform/zd/AddArticle";
	}

	/**
	 * 显示文章分类页面
	 * 
	 * @return
	 */
	@RequestMapping("/showArticleClass")
	public String showArticleClass() {
		return "platform/zd/NavClass";
	}
	
	/**
	 * 文章管理分类
	 * 
	 * @author kh.wang
	 * @since 2016年8月15日
	 * @return
	 */
	@RequestMapping("/articleAndClassify")
	public String articleAndClassify(Integer id,String title,Model model){
		model.addAttribute("id", id);
		model.addAttribute("title", title);
		model.addAttribute("fl", navclassfyService.queryAll());
		return "platform/zd/article_classify";
	}
	
	/**
	 * 分类管理文章
	 * 
	 * @author kh.wang
	 * @since 2016年8月15日
	 * @return
	 */
	@RequestMapping("/classifyAndarticle")
	public String ClassifyAndarticle(Integer id,String name,Model model) throws Exception{
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("wz", articlesService.findAll());
		return "platform/zd/classify_article";
	}

	/**
	 * 显示文章分类添加/编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/showAddNavClass")
	public String showAddNavClass(Model model,Integer id) {
		model.addAttribute("list", ClassifyPageType.values());
		model.addAttribute("fl",navclassfyService.queryStair());
		if(id!=null)
		model.addAttribute("vo", navclassfyService.queryById(id));
		return "platform/zd/AddNavClass";
	}

	/**
	 * 显示网站新闻管理
	 * 
	 * @return
	 */
	@RequestMapping("/ArticleNews")
	public String ArticleNews(HttpServletRequest request) {
		request.setAttribute("moduleid", 5);
		return "platform/zd/ArticleNews";
	}

//	/**
//	 * 显示添加文章
//	 * 
//	 * @return
//	 */
//	@RequestMapping("/AddNews")
//	public String AddArticle() {
//
//		return "platform/zd/AddNews";
//	}

	/**
	 * 显示添加文章
	 * 
	 * @return
	 */
	@RequestMapping("/editArticle")
	public String editArticle(Integer id, HttpServletRequest request) {
		try{
			Articles articles = articlesService.selectByID(id);
			request.setAttribute("topic", articles);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
		return "platform/zd/AddArticle";
	}

	/**
	 * 显示客户服务文章管理
	 * 
	 * @return
	 */
	@RequestMapping("/khfw")
	public String khfw(HttpServletRequest request) {
		request.setAttribute("moduleid", 3);
		return "platform/zd/khfw_list";
	}

	/**
	 * 显示网站导航文章管理
	 * 
	 * @return
	 */
	@RequestMapping("/wzdh")
	public String wzdh(HttpServletRequest request) {
		request.setAttribute("moduleid", 4);
		return "platform/zd/wzdh_list";
	}

	/**
	 * 显示网站服务文章管理
	 * 
	 * @return
	 */
	@RequestMapping("/wzfw")
	public String wzfw(HttpServletRequest request) {
		request.setAttribute("moduleid", 1);
		return "platform/zd/wzfw_list";
	}

	/**
	 * 显示网站资讯文章管理
	 * 
	 * @return
	 */
	@RequestMapping("/wzzx")
	public String wzzx(HttpServletRequest request) {
		request.setAttribute("moduleid", 2);
		return "platform/zd/wzzx_list";
	}
	
	/**
	 * 显示个人中心文章管理
	 * 
	 * @return
	 */
	@RequestMapping("/grzx")
	public String grzx(HttpServletRequest request) {
		request.setAttribute("moduleid", 6);
		return "platform/zd/grzx_list";
	}
	
}

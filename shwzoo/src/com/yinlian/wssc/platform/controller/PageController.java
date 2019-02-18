package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.po.ClassfyArticle;
import com.yinlian.wssc.web.po.Navclassfy;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.ClassfyArticleService;
import com.yinlian.wssc.web.service.NavclassfyService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaClassfyArticlePage;
import com.yinlian.wssc.web.util.CriteriaPage;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import data.ParseUtil;
import data.StringUtil;

/**
 * 页面管理的控制类
 * PageController.java
 * @author Administrator
 * @version $Id: PageController.java, v 0.1 2016年5月18日 下午2:56:19 Administrator Exp $
 */
@Controller
@RequestMapping("/platform/page")
public class PageController {
	
	@Autowired
	private ArticlesService articlesService;
	@Autowired
	private NavclassfyService navclassfyService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
	@Autowired
	private ClassfyArticleService classfyArticleService;
	
	/**
	 * 分页查询文章管理
	 * 
	 * @param page
	 * @param size
	 * @param title
	 * @return
	 */
	@RequestMapping("/queryArticlesManager")
	public @ResponseBody ReusltItem queryArticlesManager(String page, String size,String title){
		ReusltItem item = new ReusltItem();
        try {
	        if (StringUtilsEX.ToInt(page) <= 0) {
	            item.setCode(-101);
	            item.setDesc("分页参数错误，pageindex：" + page);
	            return item;
	        }
	        if (StringUtilsEX.ToInt(size) <= 0) {
	            item.setCode(-102);
	            item.setDesc("分页参数错误，pageindex：" + size);
	            return item;
	        }
	        PageBean pageBean;
	        CriteriaPage criteria = new CriteriaPage();
	        criteria.setTitle(title);
            pageBean = articlesService.queryArticlesManagerByPage(criteria, StringUtilsEX.ToInt(page),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询文章管理信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			item.setDesc("分页查询文章管理信息出错：" + e.getMessage());
			LogHandle.error(LogType.pag,"分页查询文章管理出错! 异常信息:{0}",
					e, "/platform/page/queryArticles");
        }
		return item;
	}
	/**
	 * 添加文章内容
	 * 
	 * @return
	 */
	@RequestMapping("/addArticle")
	public @ResponseBody ReusltItem addArticle(String title,String digest, String bytitle,String content, String imgurl){
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			Articles articles = new Articles();
			if(StringUtilsEX.IsNullOrWhiteSpace(title)){
				item.setCode(-101);
				item.setDesc("文章标题不能为空");
				return item;
			}
			articles.setTitle(title);
			articles.setDigest(digest);
			articles.setBytitle(bytitle);
			articles.setArtcontent(content);
			articles.setImgurl(imgurl);
			
			articles.setSendid(user.getUserId());
			articles.setSendtime(new Date());
			articles.setClicknum(0);
			Integer result = articlesService.addNavClass(articles);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("添加成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "AddArticle.jsp", "/platform/page/addArticle", "添加文章内容");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加文章内容操作记录出错! 异常信息:",
    								e, "/platform/page/addArticle");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加文章内容信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag,"添加文章内容出错! 异常信息:{0}",
					e, "/platform/page/addArticle");
		}
		return item;
	}
	/**
	 * 编辑文章内容
	 * 
	 * @return
	 */
	@RequestMapping("/editArticle")
	public @ResponseBody ReusltItem editArticle(Integer id,String title,String digest, String bytitle,String content, String imgurl){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			Articles articles = articlesService.queryarticlebyid(id);
			if(articles==null){
				item.setCode(-101);
				item.setDesc("文章id参数错误");
				return item;
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(title)){
				item.setCode(-102);
				item.setDesc("文章标题不能为空");
				return item;
			}
			articles.setTitle(title);
			articles.setDigest(digest);
			articles.setBytitle(bytitle);
			
			if(StringUtilsEX.IsNullOrWhiteSpace(content)){
				item.setCode(-103);
				item.setDesc("文章内容不能为空");
				return item;
			}
			articles.setArtcontent(content);
			articles.setImgurl(imgurl);
//			if(StringUtilsEX.ToInt(partid)>0){
//				articles.setPartid(StringUtilsEX.ToInt(partid));
//			}
			Integer result = articlesService.editArticle(articles);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "AddArticle.jsp", "/platform/page/editArticle", "修改文章内容");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改文章内容操作记录出错! 异常信息:",
    								e, "/platform/page/editArticle");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("修改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑文章内容出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, "编辑文章内容出错",e, "/platform/page/editArticle");
		}
		return item;
	}
	
	@RequestMapping("/articleAndClassfyAdd")
	public @ResponseBody ReusltItem articleAndClassfyAdd(Integer articleId,String articleTitle,Integer classfyId,String classfyName){
		ReusltItem item = new ReusltItem();
		try {
			
			if (classfyArticleService.classfyAndArticleIs(articleId,classfyId)>0) {
				item.setCode(-101);
				item.setDesc("已有相同类型存在无法添加");
				return item;
			}
			
			ClassfyArticle article=new ClassfyArticle();
			
			article.setArticleId(articleId);
			article.setArticleTitle(articleTitle);
			article.setClassfyId(classfyId);
			article.setClassfyName(classfyName);
			
			if(classfyArticleService.add(article)>0){
			//异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "AddArticle.jsp", "/platform/page/articleAndClassfyAdd", "添加文章分类");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加文章分类记录出错! 异常信息:",
								e, "/platform/page/articleAndClassfyAdd");
					}
					
				}
			});
			}else{
				item.setCode(-200);
				item.setDesc("添加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加文章分类信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, "添加文章分类出错! 异常信息:{0}",
					e, "/platform/page/articleAndClassfyAdd");
		}
		return item;
	}
	@RequestMapping("/articleAndClassfyDel")
	public @ResponseBody ReusltItem articleAndClassfyDel(Integer id){
		ReusltItem item = new ReusltItem();
		try {
			
			if(classfyArticleService.del(id)>0){
			//异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "AddArticle.jsp", "/platform/page/articleAndClassfyDel", "删除文章分类");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"删除文章分类记录出错! 异常信息:",
								e, "/platform/page/articleAndClassfyDel");
					}
					
				}
			});
			item.setDesc("删除成功");
			}else{
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除文章分类信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("添加文章分类出错! 异常信息:{0}",
					e.getMessage()), "/platform/page/articleAndClassfyDel");
		}
		return item;
	}
	
	@RequestMapping("/articleAndClassfy")
	public @ResponseBody ReusltItem articleAndClassfy(String page, String size,Integer articleId,Integer classifyId){
		ReusltItem item = new ReusltItem();
		try {
	        if (StringUtilsEX.ToInt(page) <= 0) {
	            item.setCode(-101);
	            item.setDesc("分页参数错误，pageindex：" + page);
	            return item;
	        }
	        if (StringUtilsEX.ToInt(size) <= 0) {
	            item.setCode(-102);
	            item.setDesc("分页参数错误，pageindex：" + size);
	            return item;
	        }
	        PageBean pageBean;
	        CriteriaClassfyArticlePage criteria = new CriteriaClassfyArticlePage();
	        criteria.setArticleId(articleId);
	        criteria.setClassifyId(classifyId);
	        
            pageBean = classfyArticleService.queryCriteriaPage(criteria, StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("文章分类列表出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag,"文章分类列表出错! 异常信息:{0}",
					e, "/platform/page/articleAndClassfy");
		}
		return item;
	}
	/**
	 * 获取文章类目一级分类
	 * 
	 * @return
	 */
	@RequestMapping("/queryfirstclass")
	public @ResponseBody ReusltItem queryfirstclass(){
		ReusltItem item = new ReusltItem();
		try {
			List<Navclassfy> list = navclassfyService.queryfirstClass();
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取文章类目一级分类信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("获取文章类目一级分类出错! 异常信息:{0}",
					e.getMessage()), "page/queryfirstclass");
		}
		return item;
	}
	
	@RequestMapping("/queryfclassbypart")
	public @ResponseBody ReusltItem queryfirstclassByPart(String partid){
		ReusltItem item = new ReusltItem();
		try {
			if(StringUtilsEX.ToInt(partid)<=0){
				item.setCode(-101);
				item.setDesc("所属模块参数错误");
				return item;
			}
			List<Navclassfy> list = navclassfyService.queryfirstClassbyPart(StringUtilsEX.ToInt(partid));
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取文章类目一级分类信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("获取文章类目一级分类出错! 异常信息:{0}",
					e.getMessage()), "/platform/page/queryfirstclass");
		}
		return item;
	}
	/**
	 * 根据首项获取分类子项
	 * 
	 * @return
	 */
	@RequestMapping("/querychildbyfid")
	public @ResponseBody ReusltItem querychildbyfid(String fatherid){
		ReusltItem item = new ReusltItem();
		try {
			List<Navclassfy> list = navclassfyService.querychildbyfid(StringUtilsEX.ToInt(fatherid));
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据首项获取分类子项信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("根据首项获取分类子项出错! 异常信息:{0}",
					e.getMessage()), "page/querychildbyfid");
		}
		return item;
	}
	/**
	 * 根据id获取内容
	 * 
	 * @return
	 */
	@RequestMapping("/queryarticlebyid")
	public @ResponseBody ReusltItem queryarticlebyid(String id){
		ReusltItem item = new ReusltItem();
		try {
			Articles articles = articlesService.queryarticlebyid(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(articles);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id获取内容信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("根据id获取内容出错! 异常信息:{0}",
					e.getMessage()), "page/queryarticlebyid");
		}
		return item;
	}
	/**
	 * 根据id删除
	 * 
	 * @return
	 */
	@RequestMapping("/delArticles")
	public @ResponseBody ReusltItem delArticles(String id){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			int result = articlesService.delArticles(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Articles.jsp", "/platform/page/delArticles", "根据id删除");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除操作记录出错! 异常信息:",
    								e, "/platform/page/delArticles");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id删除信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag,"根据id删除出错! 异常信息:{0}",
					e, "/platform/page/delArticles");
		}
		return item;
	}
	/**
	 * 批量删除
	 * 
	 * @return
	 */
	@RequestMapping("/delArticlesList")
	public @ResponseBody ReusltItem delArticlesList(String idList){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			int result = 0;
			String[] ids = idList.split(",");
			for (int i = 0; i < ids.length; i++) {
				
				result = articlesService.delArticles(StringUtilsEX.ToInt(ids[i]));
			}
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Articles.jsp", "/platform/page/delArticles", "根据id批量删除");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id批量删除操作记录出错! 异常信息:",
    								e, "/platform/page/delArticles");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id删除信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("根据id删除出错! 异常信息:{0}",
					e.getMessage()), "/platform/page/delArticles");
		}
		return item;
	}
	/**
	 * 查询分类信息
	 * 
	 * @return
	 */
	@RequestMapping("/queryClassTree")
	public @ResponseBody ReusltItem queryClassTree(){
		ReusltItem item = new ReusltItem();
		try {
				
				List<NavclassfyDto> list = navclassfyService.queryClassTree(0);
				item.setCode(0);
				item.setData(list);
				
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询分类信息信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("查询分类信息出错! 异常信息:{0}",
					e.getMessage()), "/platform/page/queryClassTree");
		}
		return item;
	}
	
	@RequestMapping("/queryClassSub")
	public @ResponseBody ReusltItem queryClassSub(Integer id){
		ReusltItem item = new ReusltItem();
		try {
				if (id==null) {
					item.setCode(101);
					item.setDesc("父id不能为空");
					return item;
				}
				List<NavclassfyDto> list = navclassfyService.querySon(id);
				item.setCode(0);
				item.setData(list);
				
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询子集信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("查询子集信息出错! 异常信息:{0}",
					e.getMessage()), "/platform/page/queryClassSub");
		}
		return item;
	}
	/**
	 * 根据id删除类型
	 * 
	 * @return
	 */
	@RequestMapping("/delNavClassById")
	public @ResponseBody ReusltItem delNavClassById(String id){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			Integer result = navclassfyService.delNavClassById(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "NavClass.jsp", "/platform/page/delArticles", "根据id删除类型");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除类型操作记录出错! 异常信息:",
    								e, "/platform/page/delArticles");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id删除信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag,"根据id删除出错! 异常信息:{0}",
					e, "/platform/page/delArticles");
		}
		return item;
	}

	
	/**
	 * 分页查询文章管理
	 * 
	 * @param page
	 * @param size
	 * @param title
	 * @return
	 */
	@RequestMapping("/queryNewsList")
	public @ResponseBody ReusltItem queryNewsList(String page, String size,String title){
		ReusltItem item = new ReusltItem();
        try {
	        if (StringUtilsEX.ToInt(page) <= 0) {
	            item.setCode(-101);
	            item.setDesc("分页参数错误，pageindex：" + page);
	            return item;
	        }
	        if (StringUtilsEX.ToInt(size) <= 0) {
	            item.setCode(-102);
	            item.setDesc("分页参数错误，pageindex：" + size);
	            return item;
	        }
	        PageBean pageBean;
	        CriteriaPage criteria = new CriteriaPage();
	        criteria.setTitle(title);
            pageBean = articlesService.queryNewsByPage(criteria, StringUtilsEX.ToInt(page),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询文章管理信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("分页查询文章管理出错! 异常信息:{0}",
					e.getMessage()), "/platform/page/queryArticles");
        }
		return item;
	}

	/**
	 * 获取分类表单
	 * 
	 * @return
	 */
	@RequestMapping("/queryArticleClassById")
	public @ResponseBody ReusltItem queryArticleClassById(String id){
		ReusltItem item = new ReusltItem();
		try {
			Navclassfy navclassfy = navclassfyService.queryArticleClassById(StringUtilsEX.ToInt(id));
				item.setCode(0);
				item.setData(navclassfy);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取分类表单信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("获取分类表单出错! 异常信息:{0}",
					e.getMessage()), "/platform/page/queryArticleClassById");
		}
		return item;
	}
	/**
	 * 添加分类
	 * 
	 * @return
	 */
	@RequestMapping("/addNavClass")
	public @ResponseBody ReusltItem addNavClass(String className,Integer pageType , String webSet ,@RequestParam("parentId")Integer [] parentId,Integer sort ){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (className == null || "".equals(className)	) {
				item.setCode(-101);
				item.setDesc("分类名称不能为空");
				return item;
			}
			if(StringUtil.isNull(webSet)){
				item.setCode(-101);
				item.setDesc("站点不能为空");
				return item;
			}
			List<Integer> par=new ArrayList<Integer>();
			for (int i = 0; i < parentId.length; i++) {
				if (parentId[i]!=0) {
					par.add(parentId[i]);
				}
			}
			
			Navclassfy navclassfy = new Navclassfy();
			navclassfy.setClassname(className);
			navclassfy.setRelevance(org.apache.commons.lang.StringUtils.join(par,","));
			navclassfy.setParentid(par.get(par.size()-1));
			navclassfy.setPageType(pageType);
			navclassfy.setWebSet(webSet);
			navclassfy.setAddTime(new Date());
			if (sort==null) {
				navclassfy.setSort(0);		
			}else{
				navclassfy.setSort(sort);		
			}
			Integer result = navclassfyService.addNavClass(navclassfy);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("添加成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "AddNavClass.jsp", "/platform/page/addNavClass", "添加分类");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加分类操作记录出错! 异常信息:",
    								e, "/platform/page/addNavClass");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取分类表单信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			item.setDesc("添加分类信息出错：" + e.getMessage());
			LogHandle.error(LogType.pag, MessageFormat.format("添加分类出错! 异常信息:{0}",
					e.getMessage()), "/platform/page/addNavClass");
		}
		return item;
	}
	/**
	 * 编辑分类
	 * 
	 * @return
	 */
	@RequestMapping("/editNavClass")
	public @ResponseBody ReusltItem editNavClass(String className,Integer pageType , String webSet ,Integer parentID1,Integer parentID2,Integer sort,String id){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (className == null || "".equals(className)) {
				item.setCode(-101);
				item.setDesc("分类名称不能为空");
			}
			Navclassfy navclassfy = navclassfyService.queryArticleClassById(StringUtilsEX.ToInt(id));
			navclassfy.setClassname(className);
			if(parentID2!=null&&parentID2!=-1){
				String p=parentID1+","+parentID2;
				navclassfy.setRelevance(p);
			}else{
				navclassfy.setRelevance(parentID1+"");
			}
			if (parentID2!=null&&parentID2!=-1) {
				parentID1=parentID2;
			}
			navclassfy.setParentid(parentID1);
			navclassfy.setSort(sort);
			navclassfy.setWebSet(webSet);
			Integer result = navclassfyService.editNavClass(navclassfy);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "AddNavClass.jsp", "/platform/page/editNavClass", "修改分类");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改分类操作记录出错! 异常信息:",
    								e, "/platform/page/editNavClass");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("修改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑分类出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, "编辑分类出错! 异常信息:{0}",
					e, "/platform/page/editNavClass");
		}
		return item;
	}
	/**
	 * 根据子类id查询父类id
	 * 
	 * @return
	 */
	@RequestMapping("/getFirstId")
	public @ResponseBody ReusltItem getFirstId(String childid ){
		ReusltItem item = new ReusltItem();
		try {
			Navclassfy navclassfy = navclassfyService.getFirstId(StringUtilsEX.ToInt(childid));
				item.setCode(0);
				item.setData(navclassfy.getParentid());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑分类出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag, MessageFormat.format("编辑分类出错! 异常信息:{0}",
					e.getMessage()), "/platform/page/editNavClass");
		}
		return item;
	}
	
	/**
	 * 根据模块ID获取文章列表
	 * @param title
	 * @param partid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getArticleList")
	public @ResponseBody ReusltItem getArticleList(String title,String partid,String page,String size){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0) {
	            item.setCode(-101);
	            item.setDesc("分页参数错误，pageindex：" + page);
	            return item;
	        }
	        if (StringUtilsEX.ToInt(size) <= 0) {
	            item.setCode(-102);
	            item.setDesc("分页参数错误，pageindex：" + size);
	            return item;
	        }
	        if(StringUtilsEX.ToInt(partid) < 0){
	        	 item.setCode(-103);
		         item.setDesc("文章所属模块参数错误");
		          return item;
	        }
	        PageBean pageBean;
	        CriteriaPage criteria = new CriteriaPage();
	        criteria.setPartid(StringUtilsEX.ToInt(partid));
	        if(!StringUtilsEX.IsNullOrWhiteSpace(title)){
	        criteria.setTitle(title);
	        }
	        criteria.setOrderByClause("sendTime");
	        criteria.setSort("desc");
            pageBean = articlesService.getArticlesByPage(criteria, StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取文章列表出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pag,"获取文章列表出错!",e, "/platform/page/getArticleList");
		}
		return item;
	}
	
}





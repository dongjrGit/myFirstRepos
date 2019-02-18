package com.yinlian.api.app.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_ArticlesDto;
import com.yinlian.api.app.dto.Api_NewsDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;
 

@Controller
@RequestMapping("/api/app/news")
public class ArticlesController {
	
	@Autowired
	private   ArticlesService     articlesService;
	
	@Autowired
	private NewsService newsService;
	
	
	/**
	 * 通过导航分类Id查询文章信息
	 * @param classfyid
	 * @return
	 */
	@RequestMapping(value = "/showarticles", produces = "text/html;charset=UTF-8")
	public @ResponseBody String showarticles(String classfyid,String ch){
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(classfyid)) {
				item.setCode(-101);
				item.setDesc("导航分类id(classfyid)不能为空！");
				return item.toJson();
			}
			
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Articles  articles=articlesService.selectByClassfyId(StringUtilsEX.ToInt(classfyid));
			item.setCode(0);
			item.setData(articles);
			item.setDesc("查询成功");
			
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"查询文章信息异常! 异常信息:{0}",
					e, "news/showarticles");
		}
		return item.toJson();
	}
	
	
	
	/**
	 * 查询网站新闻
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/shownews", produces = "text/html;charset=UTF-8")
	public @ResponseBody String shownews(String ch){
		BaseResult item = new BaseResult();
		try {
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			List<Api_ArticlesDto> dto= new ArrayList<Api_ArticlesDto>();
			List<NavclassfyDto> articles=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.app, "网站新闻");
			if (articles.size()>0) {
				articles.get(0).getArticles().forEach(a->{
					Api_ArticlesDto dtos=new Api_ArticlesDto();
					dtos.setId(a.getId());
					dtos.setTitle(a.getTitle());
					dto.add(dtos);
				});
			}
			item.setData(dto);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"查询网站新闻异常! 异常信息:{0}",
					e, "news/shownews");
		}
		
		return item.toJson();
	}
	
	/**
	 * 查询网站新闻
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/newInfo", produces = "text/html;charset=UTF-8")
	public @ResponseBody String newInfo(String id,String ch){
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-101);
				item.setDesc("新闻id不能为空！");
				return item.toJson();
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Api_NewsDto dto=articlesService.selectNewsInfo(StringUtilsEX.ToInt(id));
			item.setData(dto);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "查询新闻详细信息出错! 异常信息:{0}",
					e, "news/newInfo");
		}
		return item.toJson();
	}
	
	
	/**
	 * 最新消息
	 * @param ch
	 * @param size 大小
	 * @return
	 */
	@RequestMapping(value = "/newestmsg", produces = "text/html;charset=UTF-8")
	public @ResponseBody String newestmsg(String ch,String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(size)<=0) {
				size="1";
			}
			AppNewsCriteria criteria=new AppNewsCriteria();
			criteria.setType(1);
			criteria.setCtype(-1);
			criteria.setWebset(WebSetEnum.app.getValue()+"");
			PageBean listBean=newsService.getApplist(criteria, 1, StringUtilsEX.ToInt(size));
  			item.setData(listBean.getBeanList());
  			item.setPage(listBean.getTp());
  			item.setMaxRow(listBean.getTr());
  			item.setPageIndex(1);
  			item.setPageSize(StringUtilsEX.ToInt(size));
  			item.setPage(listBean.getTp());
  			return item.toJson();
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取最新消息异常!", e,
					"userinfo/newestmsg");
		}
		return item.toJson();
	}
}

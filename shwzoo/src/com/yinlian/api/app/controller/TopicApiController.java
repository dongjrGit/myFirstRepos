/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_TopicBrandDto;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 手机端专题的控制类 TopicApiController.java
 * 
 * @author Liang.ma.s
 * @version $Id: TopicApiController.java, v 0.1 2016年4月7日 下午4:05:54
 *          Administrator Exp $
 */
@RestController
@RequestMapping("/api/app/topic")
public class TopicApiController {

	@Autowired
	private TopicService topicService;
	@Autowired
	private NewsService newsService;

	
	/**
	 * 发现好店
	 * @param ch 通道
	 * @param page 当前页
	 * @param size 页大小
	 * @return
	 */
	@RequestMapping(value = "/findfxhd", produces = "text/html;charset=UTF-8")
  	public String findfxhh(String ch, String page,String size) {
  		ReusltItem item = new ReusltItem();
  		try {
  			if (!StringUtilsEX.isChannelTypeExist(ch)) {
  				item.setCode(-108);
  				item.setDesc("登录通道参数错误");
  				return item.toJson();
  			}
  			Integer ipage = StringUtilsEX.ToIntNull(page);
  			Integer isize = StringUtilsEX.ToIntNull(size);
  			if (ipage == null || ipage <= 0) {
  				ipage = 1;
  			}
  			if (isize == null || isize <= 0) {
  				isize = 10;
  			}
  			
				PageBean listBean = topicService.getTopicByShop(ipage, isize);
	  			item.setData(listBean.getBeanList());
	  			item.setPage(listBean.getTp());
	  			item.setMaxRow(listBean.getTr());
	  			item.setPageIndex(ipage);
	  			item.setPageSize(isize);
	  			item.setPage(listBean.getTp());
	  			return item.toJson();
  		} catch (Exception e) {
  			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取发现好店错误：{0}", e,"/topic/findfxhd");
  		}
  		return item.toJson();
  	}
	/**
	 * 获取专题
	 * @param ch 通道
	 * @param page 当前页
	 * @param size 页大小
	 * @param mark 专题标识 1发现好货 2.新品尝鲜
	 */
	@RequestMapping(value = "/findtopic", produces = "text/html;charset=UTF-8")
  	public String findtopic(String ch, String page,String size,String mark) {
  		ReusltItem item = new ReusltItem();
  		try {
  			if (!StringUtilsEX.isChannelTypeExist(ch)) {
  				item.setCode(-108);
  				item.setDesc("登录通道参数错误");
  				return item.toJson();
  			}
  			Integer marks=StringUtilsEX.ToInt(mark);
  			if (marks <= 0) {
				item.setCode(-109);
				item.setDesc("专题标识参数错误");
				return item.toJson();
			}
  			Integer ipage = StringUtilsEX.ToIntNull(page);
  			Integer isize = StringUtilsEX.ToIntNull(size);
  			if (ipage == null || ipage <= 0) {
  				ipage = 1;
  			}
  			if (isize == null || isize <= 0) {
  				isize = 10;
  			}
  			Api_TopicBySpuCriteria atc = new Api_TopicBySpuCriteria();
  			switch (marks) {
			case 1:
				atc.setMark(TopicMarkEnum.发现好货.getValue());
				atc.setIssys(false);
				break;
			case 2:
				atc.setMark(TopicMarkEnum.新品尝鲜.getValue());
				atc.setIssys(true);
				break;
			default:
				item.setCode(-109);
				item.setDesc("专题标识参数错误");
				return item.toJson();
			}
  			atc.setWebset(Integer.toString(WebSetEnum.app.getValue()));
  			PageBean listBean = topicService.getTopicBySpu(atc, ipage, isize);
  			item.setData(listBean.getBeanList());
  			item.setPage(listBean.getTp());
  			item.setMaxRow(listBean.getTr());
  			item.setPageIndex(ipage);
  			item.setPageSize(isize);
  			item.setPage(listBean.getTp());
  			return item.toJson();
  		} catch (Exception e) {
  			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取专题错误：{0}", e,"/topic/findtopic");
  		}
  		return item.toJson();
  	}

	
	/**
	 * 获取专题品牌列表(品牌街)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gettopicbrand", produces = "text/html;charset=UTF-8")
	public String getTopicBrand(String size) {
		ReusltItem item = new ReusltItem();
		try {
			Integer sizei=6;
			if (!StringUtilsEX.IsNullOrWhiteSpace(size)) {
				sizei=StringUtilsEX.ToInt(size);
			}
			List<Api_TopicBrandDto> topicbrands = topicService.getTopicBrand(PageMarkType.首页.getValue(),TopicMarkEnum.品牌街.getValue(), 			
					TopicTypeEnum.品牌.getValue(),Integer.toString(WebSetEnum.app.getValue()),sizei,false);
			item.setData(topicbrands);
			item.setCode(0);
			return item.toJson();
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取专题品牌列表错误：{0}", e,
					"/topic/gettopicbrand");
		}
		return item.toJson();
	}
	/**
	 * 用户协议
	 * @param ctype 213：用户协议
	 * @return
	 */
	@RequestMapping(value = "/getnewslist", produces = "text/html;charset=UTF-8")
	public String getnewslist(Integer ctype){
		ReusltItem item = new ReusltItem();
		try {
			List<SnewsWithBLOBs> newslist=newsService.findByCType(ctype);
			item.setData(newslist);
			item.setCode(0);
		}catch(Exception e){
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "用户协议出错：{0}", e,
					"/topic/getnewslist");
		}
		return item.toJson();
	}
	
	/**
	 * 获取专题商品列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gettopicmark", produces = "text/html;charset=UTF-8")
	public String gettopicmark(String size,String mark) {
		ReusltItem item = new ReusltItem();
		try {
			Integer sizei=6;
			if (!StringUtilsEX.IsNullOrWhiteSpace(size)) {
				sizei=StringUtilsEX.ToInt(size);
			}
			if (StringUtilsEX.ToInt(mark)<=0) {
				item.setCode(-101);
				item.setDesc("专题标识错误");
				return item.toJson();
			}
			Date date=new Date();
			List<Api_TopicBySpuDto> topicpros = topicService.getApiTopicSpu(PageMarkType.首页.getValue(),StringUtilsEX.ToInt(mark),
					Integer.toString(WebSetEnum.app.getValue()),sizei,date);
			item.setData(topicpros);
			item.setCode(0);
			return item.toJson();
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取专题商品列表错误：{0}", e,
					"/topic/gettopicmark");
		}
		return item.toJson();
	}
	

	/**
	 * 获取专题商品列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gettopicid", produces = "text/html;charset=UTF-8")
	public String gettopicid(String id,String keys,String page,String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id)<=0) {
				item.setCode(-101);
				item.setDesc("专题ID错误");
				return item.toJson();
			}
			int page1=1;
			int size1=10;
			if(StringUtilsEX.ToInt(page)>0)page1=StringUtilsEX.ToInt(page);
			if(StringUtilsEX.ToInt(size)>0)size1=StringUtilsEX.ToInt(size);
			
			CriteriaTopic criteria=new CriteriaTopic();
			criteria.setTopicid(StringUtilsEX.ToInt(id));
			criteria.setTitle(keys);
			criteria.setOrderByClause("r.sort");
			criteria.setNewdate(new Date());
			PageBean listBean = topicService.getApiTopicSpuPage(criteria,page1,size1);
  			item.setData(listBean.getBeanList());
  			item.setPage(listBean.getTp());
  			item.setMaxRow(listBean.getTr());
  			item.setPageIndex(page1);
  			item.setPageSize(size1);
  			item.setPage(listBean.getTp());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取专题商品列表错误：{0}", e,
					"/topic/gettopicid");
		}
		return item.toJson();
	}
}

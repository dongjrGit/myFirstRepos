/*
 * @(#) GreenChinaVoewController.java 2016年11月7日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.pc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Pc_TopicCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaSnews;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;


@RestController
@RequestMapping("/pc/lszg")
public class PCGreenChinaController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private TopicService topicService;
	
	
	/**
	 * 绿色故事
	 * 
	 * @author kh.wang
	 * @since 2016年11月7日
	 * @param page 页
	 * @param pagesize 页大小
	 * @param ctype 
	 * @return
	 */
	@RequestMapping("/querylist")
	public ReusltItem querylist(Integer page, Integer pagesize) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaSnews criteria = new CriteriaSnews();
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
			criteria.setCtype(5);
			criteria.setState(0);
			PageBean pageBean = newsService.querySlist(criteria, page, pagesize);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.pc,"获取绿色故事异常", e,
					"/lszg/querylist");
		}
		return item;
	}
	

	/**
	 * 绿色品牌
	 * 
	 * @author kh.wang
	 * @since 2016年11月7日
	 * @param page
	 * @param pagesize
	 * @return
	 */
	@RequestMapping("/querynewsimg")
	public ReusltItem queryNewsImg(Integer page, Integer pagesize) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaSnews criteria = new CriteriaSnews();
			criteria.setState(0);
			criteria.setCtype(2);
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
			PageBean pageBean = newsService.queryNewsImg(criteria, page, pagesize);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setPage(pageBean.getTp());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.pc,"获取绿色品牌异常", e,
					"/lszg/querynewsimg");
		}
		return item;
	}
	
	/**
	 * 获取专题商品
	 * @param page
	 * @param pagesize
	 * @param topicid
	 * @return
	 */
	@RequestMapping("/querytopicspu")
	public ReusltItem querytopicspu(Integer page, Integer pagesize,Integer topicid){

		ReusltItem item = new ReusltItem();
		try {
			Pc_TopicCriteria criteria=new Pc_TopicCriteria();
			criteria.setTopicid(topicid);
			PageBean list=topicService.getPcTopByPage(criteria, page, pagesize);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPageIndex(list.getPc());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.pc," 获取专题商品异常",e,
					"/lszg/querylist");
		}
		return item;
	}
	
	/**
	 * 特色大全
	 * @param page
	 * @param pagesize
	 * @param fl 分类ID
	 * @param min 最小价格
	 * @param max 最大价格
	 * @return
	 */
	@RequestMapping("/querytsdqspu")
	public ReusltItem querytsdqspu(Integer page, Integer pagesize,Integer fl,String min,String max,String orderByClause,String sort){
		ReusltItem item = new ReusltItem();
		try {
			Pc_TopicCriteria criteria=new Pc_TopicCriteria();
			criteria.setMark(TopicMarkEnum.特色大全.getValue());
			criteria.setType(TopicTypeEnum.专题分类.getValue());
			criteria.setTypeid(fl);
			criteria.setMin(min);
			criteria.setMax(max);
			if (!StringUtilsEX.IsNullOrWhiteSpace(orderByClause)&&!StringUtilsEX.IsNullOrWhiteSpace(sort)) {
				criteria.setOrderByClause(orderByClause);
				criteria.setSort(sort);
			}
			PageBean list=topicService.getPcTopByPage(criteria, page, pagesize);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPageIndex(list.getPc());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.pc,"特色大全异常",e,"/lszg/querytsdqspu");
		}
		return item;
	}
	
}

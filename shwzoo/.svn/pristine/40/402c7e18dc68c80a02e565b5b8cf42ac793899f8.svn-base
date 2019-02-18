/*
 * @(#) WapQiangController.java 2016年7月6日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.api.wap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 抢购
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年7月6日
 */
@RestController
@RequestMapping("/api/wap/qiang")
public class WapQiangController {
	
	@Autowired
	private SpikeActivityService spikeActivityService;

	/**
	 * 抢购列表
	 * 
	 * @author kh.wang
	 * @since 2016年7月6日
	 * @param page
	 * @param size
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public ReusltItem index(String page, String size,String ch,Model model){
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item;
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSort("desc");
			criteria.setOrderByClause("b.orderby");
			criteria.setUsesite(ActivityUsePlatformEnum.wap.getValue());
			PageBean pBean = spikeActivityService.getSgSpuByPage(criteria,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setMaxRow(pBean.getPs());
			item.setPage(pBean.getTp());
			item.setCode(0);
			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"抢购列表异常! 异常信息:{0}", e, "/qiang/index");
		}
		return item;
	}
	
	/**
	 * 抢购列表
	 * 
	 * @author kh.wang
	 * @since 2016年7月6日
	 * @param page
	 * @param size
	 * @param model
	 * @return
	 */
	@RequestMapping("/app/index")
	public ReusltItem appindex(String page, String size,String ch,Model model){
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item;
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSort("desc");
			criteria.setOrderByClause("b.orderby");
			criteria.setUsesite(ActivityUsePlatformEnum.app.getValue());
			PageBean pBean = spikeActivityService.getSgSpuByPage(criteria,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setMaxRow(pBean.getPs());
			item.setPage(pBean.getTp());
			item.setCode(0);
			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"抢购列表异常! 异常信息:{0}", e, "/qiang/index");
		}
		return item;
	}
	
	
	
	
	/**
	 * 获取秒杀活动
	 * 
	 * @return
	 */
	@RequestMapping("/getSpike")
	public ReusltItem getSpike(String ch, String page, String size,Model model) {
		ReusltItem item = new ReusltItem();
		String logpath = "qiang/" + ch + "/getSpike";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item;
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setUsesite(ActivityUsePlatformEnum.wap.getValue());
			item.setData(spikeActivityService.getmsActivityList(criteria,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size), item));
			item.setCode(0);
			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取秒杀活动异常! 异常信息:{0}", e, logpath);
		}
		return item;
	}
	
	/**
	 * 获取秒杀活动
	 * 
	 * @return
	 */
	@RequestMapping("/app/getSpike")
	public ReusltItem appgetSpike(String ch, String page, String size,Model model) {
		ReusltItem item = new ReusltItem();
		String logpath = "qiang/" + ch + "/getSpike";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item;
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setUsesite(ActivityUsePlatformEnum.app.getValue());
			item.setData(spikeActivityService.getmsActivityList(criteria,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size), item));
			item.setCode(0);
			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取秒杀活动异常! 异常信息:{0}", e, logpath);
		}
		return item;
	}
	
	/**
	 * 获取秒杀活动
	 * 
	 * @return
	 */
	@RequestMapping(value = "/seckill", produces = "text/html;charset=UTF-8")
	public ReusltItem getSpike(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		String logpath = "qiang/" + ch + "/getSpike";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item;
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSort("desc");
			criteria.setOrderByClause("a.orderby");
			criteria.setUsesite(ActivityUsePlatformEnum.wap.getValue());
			int pages=StringUtilsEX.ToInt(page);
			int sizes=StringUtilsEX.ToInt(size);
			item.setData(spikeActivityService.getmsActivity(criteria,
					pages,sizes, item));
			item.setCode(0);
			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取秒杀活动异常! 异常信息:{0}", e, logpath);
		}
		return item;
	}
}

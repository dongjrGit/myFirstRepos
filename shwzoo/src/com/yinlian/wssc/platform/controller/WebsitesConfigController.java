/*
 * @(#) WebsitesConfig.java 2016年6月23日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.platform.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.WebsitesConfigDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.WebsitesConfig;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.WebsitesConfigService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import data.ObtainUtil;

/**
 * 网站配置
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年6月23日
 */
@Controller
@RequestMapping("/platform/websitesConfig")
public class WebsitesConfigController {
	@Autowired
	WebsitesConfigService websitesConfigService;
	
	SessionUser user = null;
    @Autowired
    private OperaterecordsService operaterecordsService ;
	
	/**
	 * 列表
	 * 
	 * @author kh.wang
	 * @since 2016年6月23日
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/index")
	public @ResponseBody ReusltItem index(String page, String size){
		 ReusltItem item = new ReusltItem();
		try {
			Integer ipage = StringUtilsEX.ToIntNull(page);
			Integer isize = StringUtilsEX.ToIntNull(size);
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
		        PageBean listBean = websitesConfigService.getListByPage(ipage, isize);
				System.out.println(((WebsitesConfigDto)listBean.getBeanList().get(0)).getCompanyName());
		        item.setData(listBean.getBeanList());
				item.setPage(listBean.getTp());
				item.setMaxRow(listBean.getTr());
				item.setPageIndex(ipage);
				item.setPageSize(isize);
			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
	    			item.setDesc("网站配置列表异常：" + e.getMessage());
	           } else {
	               item.setDesc("系统错误！");
	           }
	           item.setCode(-900);
				LogHandle.error(LogType.Sys, "网络配置列表错误",e,"/platform/websitesConfig/index");
			}
		return item;
	}
	
	
	
	@RequestMapping("/modify")
	public @ResponseBody ReusltItem modify(HttpServletRequest request,String name,String url,String tel,String email,String key,String desc,String forRecord,String address,String id){
		 ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			item.setCode(-1);//验证
			
			WebsitesConfig dto=new WebsitesConfig();
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setDesc("公司名称不能为空");
				return item;
			}
			dto.setCompanyName(name);
			if (StringUtilsEX.IsNullOrWhiteSpace(url)) {
				item.setDesc("公司网站不能为空");
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(tel)) {
				item.setDesc("联系电话不能为空");
				return item;
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(id)) {
				dto.setId(StringUtilsEX.ToInt(id));
			}
			item.setCode(0);//验证
			dto.setCompanyUrl(url);
			dto.setForRecord(forRecord);
			dto.setCompanyAddr(address);
			dto.setCompanyTel(tel);
			dto.setCompanyEmail(email);
			dto.setCompanyKey(key);
			dto.setDesc(desc);
			dto.setCreatetime(ObtainUtil.dateTime());
			dto.setCreateuserid(SessionUtil.getSessionUserId(request));
			if (dto.getId()!=null&&dto.getId()>0) {
				if (websitesConfigService.updateById(dto)>0) {
					item.setDesc("修改成功");
					//异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "websitesConfig_modify.jsp", "/platform/websitesConfig/modify", "修改网络配置");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"修改网络配置操作记录出错! 异常信息:",
	    								e, "/platform/websitesConfig/modify");
	    					}
	    					
	    				}
	    			});
				}
			}else{
				if (websitesConfigService.insertAdd(dto)>0) {
					item.setDesc("添加成功");
					//异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "websitesConfig_modify.jsp", "/platform/websitesConfig/modify", "添加网络配置");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"添加网络配置操作记录出错! 异常信息:",
	    								e, "/platform/websitesConfig/modify");
	    					}
	    					
	    				}
	    			});
				}
			}
			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
	    			item.setDesc("网络配置修改或添加异常：" + e.getMessage());
	           } else {
	               item.setDesc("系统错误！");
	           }
	           item.setCode(-900);
				 LogHandle.error(LogType.Sys, "网络配置修改或添加错误",e,"/platform/websitesConfig/modify");
			}
		return item;
	}
	
	
	/**
	 * 删除
	 * 
	 * @author kh.wang
	 * @since 2016年6月23日
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody ReusltItem delete(String id){
		 ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-1);
				item.setDesc("id不能为空");
				return item;
			}
			if (websitesConfigService.deltel(StringUtilsEX.ToInt(id))>0) {
				item.setDesc("删除成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "websitesConfig_list.jsp", "/platform/SpikeActivity/delete", "删除网络配置");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除网络配置操作记录出错! 异常信息:",
    								e, "/platform/SpikeActivity/delete");
    					}
    					
    				}
    			});
			}
			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
	    			item.setDesc("网络配置删除异常：" + e.getMessage());
	           } else {
	               item.setDesc("系统错误！");
	           }
	           item.setCode(-900);
				 LogHandle.error(LogType.Sys, "网络配置删除",e,"/platform/websitesConfig/delete");
			}
		return item;
	}
}

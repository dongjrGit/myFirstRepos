/*
 * @(#) SearchKeyViewController.java 2016年6月23日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.platform.controller;

import java.util.Date;
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
import com.yinlian.wssc.web.dto.SearchkeyDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Searchkey;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.SearchkeyService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 关键字
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年6月23日
 */
@Controller
@RequestMapping("/platform/searchkey")
public class SearchKeyController {

	@Autowired
	SearchkeyService searchkeyService;
	
	SessionUser user=null;
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
		        PageBean listBean = searchkeyService.getListByPage(ipage, isize);
				item.setData(listBean.getBeanList());
				item.setPage(listBean.getTp());
				item.setMaxRow(listBean.getTr());
				item.setPageIndex(ipage);
				item.setPageSize(isize);
			} catch (Exception e) {
				item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("关键字列表错误：" + e.getMessage());
				} else {
						item.setDesc("系统错误！");
				}
				LogHandle.error(LogType.searchPro, "关键字列表错误",e,"/platform/searchkey/index");
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
			if (searchkeyService.deltel(StringUtilsEX.ToInt(id))) {
				item.setDesc("删除成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "searchkey_list.jsp", "/platform/searchkey/delete", "删除关键字");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除关键字操作记录出错! 异常信息:",
    								e, "/platform/searchkey/delete");
    					}
    					
    				}
    			});
			}
			} catch (Exception e) {
				item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("关键字删除错误：" + e.getMessage());
				} else {
						item.setDesc("系统错误！");
				}
				LogHandle.error(LogType.searchPro, "关键字删除错误",e,"/platform/searchkey/delete");
			}
		return item;
	}
	
	@RequestMapping("/orderByUpd")
	public @ResponseBody ReusltItem orderByUpd(String id,String orderBy){
		 ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-1);
				item.setDesc("id不能为空");
				return item;
			}
			SearchkeyDto dto=new SearchkeyDto();
			dto.setId(StringUtilsEX.ToInt(id));
			dto.setOrderBy(StringUtilsEX.ToInt(orderBy));
			if (searchkeyService.orderByUpd(dto)) {
				item.setDesc("修改成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "searchkey_list.jsp", "/platform/searchkey/orderByUpd", "修改关键字");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改关键字操作记录出错! 异常信息:",
    								e, "/platform/searchkey/orderByUpd");
    					}
    					
    				}
    			});
			}
			} catch (Exception e) {
				item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("关键字修改错误：" + e.getMessage());
				} else {
						item.setDesc("系统错误！");
				}
				LogHandle.error(LogType.searchPro, "关键字修改错误",e,"/platform/searchkey/orderByUpd");
			}
		return item;
	}
	
	/**
	 * 添加
	 * 
	 * @author kh.wang
	 * @since 2016年6月23日
	 * @param keyword
	 * @param orderby
	 * @return
	 */
	@RequestMapping("/insert")
	public @ResponseBody ReusltItem insert(HttpServletRequest request,String keyword,String orderby,String userSites){
		 ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			Searchkey dto=new Searchkey();
			dto.setKeywords(keyword);
			dto.setOrderby(StringUtilsEX.ToInt(orderby));
			dto.setCreateuserid(SessionUtil.getSessionUserId(request));
			dto.setUsesites("2");
			
			dto.setCreatetime(new Date());
			if (searchkeyService.insert(dto)) {
				item.setDesc("添加成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "searchkey_list.jsp", "/platform/searchkey/insert", "添加关键字");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加关键字操作记录出错! 异常信息:",
    								e, "/platform/searchkey/insert");
    					}
    					
    				}
    			});
			}
			} catch (Exception e) {
				item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("关键字添加错误：" + e.getMessage());
				} else {
						item.setDesc("系统错误！");
				}
				LogHandle.error(LogType.searchPro, "关键字添加错误",e,"/platform/searchkey/insert");
			}
		return item;
	}
}

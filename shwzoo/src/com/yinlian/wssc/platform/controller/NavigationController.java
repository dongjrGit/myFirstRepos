/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Navigation;
import com.yinlian.wssc.web.service.NavigationService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaNavigation;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * NavigationController.java
 * @author sssssssl.m
 * @version $Id: NavigationController.java, v 0.1 2016年4月15日 上午10:12:05 Administrator Exp $
 */
@RestController
@RequestMapping("/platform/navigation")
public class NavigationController {

    @Autowired
    private NavigationService navigationService;
    
    SessionUser                user = null;
	@Autowired
    private OperaterecordsService operaterecordsService ;

    /**
     * 获取导航菜单
     * 
     * @param title
     * @param status
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryListByCriteria")
    public ReusltItem queryListByCriteria(String title, String status, String page, String size) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(page) < 0 || StringUtilsEX.ToInt(size) < 0) {
                item.setCode(-101);
                item.setDesc("分页参数错误,page:" + page + ",size:" + size);
                return item;
            }
            CriteriaNavigation criteria = new CriteriaNavigation();
            if (StringUtils.isNotNull(title)) {
                criteria.setTitle(title);
            }
            if (StringUtils.isNotNull(status)) {
                criteria.setStatus(StringUtilsEX.ToInt(status));
            }
            criteria.setOrderByClause("sort");
            PageBean pageBean = navigationService.queryByCriteria(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取导航菜单信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
                MessageFormat.format("获取导航菜单信息出错! 异常信息:{0}", e.getMessage()),
                "navigation/queryListByCriteria");
        }
        return item;
    }

    /**
     * 编辑导航
     * 
     * @param title
     * @param img
     * @param url
     * @param sort
     * @param status
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public ReusltItem edit(String title, String img, String url, String sort, String status,
                           String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user= SessionState.GetCurrentUser();
            if (StringUtils.isBlanck(title)) {
                item.setCode(-101);
                item.setDesc("请填写标题");
                return item;
            }
            if (StringUtils.isBlanck(img)) {
                item.setCode(-102);
                item.setDesc("请上传图片");
                return item;
            }
            if (StringUtils.isBlanck(url)) {
                item.setCode(-103);
                item.setDesc("请填写链接");
                return item;
            }
            if (StringUtilsEX.ToInt(sort) < 0) {
                item.setCode(-104);
                item.setDesc("请填写排序");
                return item;
            }
            if (StringUtilsEX.ToInt(status) < 0) {
                item.setCode(-105);
                item.setDesc("请选择状态");
                return item;
            }
            Navigation record = new Navigation();
            record.setTitle(title);
            record.setImg(img);
            record.setUrl(url);
            record.setSort(StringUtilsEX.ToInt(sort));
            record.setStatus(StringUtilsEX.ToInt(status));
            if (StringUtils.isBlanck(id)) {
                int result = navigationService.insert(record);
                if (result > 0) {
                    item.setCode(0);
                    item.setDesc("操作成功");
                  //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "navigation_edit.jsp", "/platform/navigation/edit", "添加导航菜单");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加导航菜单操作记录出错! 异常信息:",
        								e, "/platform/navigation/edit");
        					}
        					
        				}
        			});
                } else {
                    item.setCode(-200);
                    item.setDesc("操作失败");
                    LogHandle.error(LogType.User,
                        MessageFormat.format("编辑导航菜单信息失败! 错误信息:{0}", record.toString()),
                        "navigation/edit");
                }
            } else {
                record.setId(StringUtilsEX.ToInt(id));
                int result = navigationService.update(record);
                if (result > 0) {
                    item.setCode(0);
                    item.setDesc("操作成功");
                  //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "navigation_edit.jsp", "/platform/navigation/edit", "修改导航菜单");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改导航菜单操作记录出错! 异常信息:",
        								e, "/platform/navigation/edit");
        					}
        					
        				}
        			});
                } else {
                    item.setCode(-200);
                    item.setDesc("操作失败");
                    LogHandle.error(LogType.User,
                        MessageFormat.format("编辑导航菜单信息失败! 错误信息:{0}", record.toString()),
                        "navigation/edit");
                }
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑导航菜单信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,"编辑导航菜单信息出错! 异常信息:{0}", e, "/platform/navigation/edit");
        }
        return item;
    }

    /**
     * 修改状态
     * 
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus")
    public ReusltItem updateStatus(String id, String status) {
        ReusltItem item = new ReusltItem();
        try {
        	user= SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("参数id错误,id" + id);
                return item;
            }
            if (StringUtilsEX.ToInt(status) < 0) {
                item.setCode(-102);
                item.setDesc("请选择状态");
                return item;
            }
            int result = navigationService.updateStatus(StringUtilsEX.ToInt(id),
                StringUtilsEX.ToInt(status));
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
                            		user.getUserId(), user.getLoginName(), "navigation_list.jsp", "/platform/navigation/updateStatus", "修改状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改状态操作记录出错! 异常信息:",
    								e, "/platform/navigation/updateStatus");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
                LogHandle.error(LogType.User,
                    MessageFormat.format("编辑导航菜单信息失败! 错误信息id:{0},状态{1}", id, status),
                    "navigation/updateStatus");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改导航菜单状态信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,"修改导航菜单状态信息出错! 异常信息:{0}", e,
                "/platform/navigation/updateStatus");
        }
        return item;
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public ReusltItem delete(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user= SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("参数id错误,id" + id);
                return item;
            }
            int result = navigationService.deleteById(StringUtilsEX.ToInt(id));
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
                            		user.getUserId(), user.getLoginName(), "navigation_list.jsp", "/platform/navigation/delete", "删除导航菜单");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除导航菜单操作记录出错! 异常信息:",
    								e, "/platform/navigation/delete");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
                LogHandle.error(LogType.User, MessageFormat.format("编辑导航菜单信息失败! 错误信息id:{0}", id),
                    "navigation/delete");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除导航菜单信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,"删除导航菜单信息出错! 异常信息:{0}", e, "/platform/navigation/delete");
        }
        return item;
    }
}

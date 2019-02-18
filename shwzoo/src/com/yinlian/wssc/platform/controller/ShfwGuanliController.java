/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.ApplyforcancelorderService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaSh;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * The class is for Account Service. 管理员信息的控制类
 * 
 * @author Administrator
 * @version $Id: AccountsController.java, v 0.1 2016年2月25日 下午1:10:31
 *          Administrator Exp $
 */
@Controller
@RequestMapping("/platform/sh")
public class ShfwGuanliController {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory.getLogger(ShfwGuanliController.class);

	@Autowired
	private ApplyforcancelorderService applyforcancelorderService;
	
	SessionUser user=null;
    @Autowired
    private OperaterecordsService operaterecordsService ;

	/**
	 * 得到退款换货列表
	 * 
	 * @param order
	 * @param time
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getReturnTradeList")
	public @ResponseBody ReusltItem getReturnTradeList(String order, String time, String page, String size) {
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
			if (order.equals("请输入订单号")) {
				order = "";
			}
			CriteriaSh criteria = new CriteriaSh();
			criteria.setCode(order);
			String datef = "";
			String datet = "";
			if (time != "") {
				datef = time + " 00:00:00";
				datet = time + " 23:59:59";
			}

			criteria.setDatef(StringUtilsEX.ToDate(datef));
			criteria.setDatet(StringUtilsEX.ToDate(datet));
			criteria.setType(3);
			PageBean pageBean = applyforcancelorderService.selectThListByPage(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取退款和退货列表异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order, MessageFormat.format("获取退款和退货列表异常! 异常信息:{0}", e.getMessage()),
					"/platform/sh/getReturnTradeList");
		}
		return item;
	}

	/**
	 * 删除退款退货记录
	 * 
	 * @param orderid
	 * @return
	 */
	@RequestMapping("/delReturnTrade")
	public @ResponseBody ReusltItem delReturnTrade(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-101);
				item.setDesc("id不能为空");
				return item;
			}
			if (applyforcancelorderService.del(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("删除退款退货记录成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除退款退货记录页面", "/platform/sh/delReturnTrade", "删除退款退货记录");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除退款退货记录操作记录出错! 异常信息:",
    								e, "/platform/sh/delReturnTrade");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("删除退款退货记录失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除退款退货记录异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order, "删除退款退货记录异常! 异常信息:{0}", e,
					"/platform/sh/delReturnTrade");
		}
		return item;
	}

	/**
	 * 添加备注
	 * 
	 * @param id
	 * @param note
	 * @return
	 */
	@RequestMapping("/editnote")
	public @ResponseBody ReusltItem editNote(String id, String note) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-101);
				item.setDesc("id不能为空");
				return item;
			}
			if (applyforcancelorderService.editNote(StringUtilsEX.ToInt(id), note) > 0) {
				item.setCode(0);
				item.setDesc("保存成功!");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "添加备注页面", "/platform/sh/editnote", "添加备注");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加备注操作记录出错! 异常信息:",
    								e, "/platform/sh/editnote");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("保存失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加备注异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"添加备注异常! 异常信息:{0}", e, "/platform/sh/editnote");
		}
		return item;
	}

	/**
	 * 查看 备注
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getnote")
	public @ResponseBody ReusltItem getNote(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-101);
				item.setDesc("id不能为空");
				return item;
			}
			item.setCode(0);
			item.setData(applyforcancelorderService.getNote(StringUtilsEX.ToInt(id)));

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(" 查看 备注异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order," 查看 备注异常! 异常信息:{0}", e, "/platform/sh/getnote");
		}
		return item;
	}

	/**
	 * 更新已退款
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/updatestatus")
	public @ResponseBody ReusltItem updateStatus(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-101);
				item.setDesc("id不能为空");
				return item;
			}
			if (applyforcancelorderService.updatestatus(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("保存成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "更新已退款页面", "/platform/sh/updatestatus", "更新已退款");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"更新已退款操作记录出错! 异常信息:",
    								e, "/platform/sh/updatestatus");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("保存失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("更新已退款异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order, "更新已退款异常! 异常信息:{0}", e,
					"/platform/sh/updatestatus");
		}
		return item;
	}

}

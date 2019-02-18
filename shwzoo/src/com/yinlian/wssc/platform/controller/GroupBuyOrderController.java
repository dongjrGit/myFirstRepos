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
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaGroupBuyOrder;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/platform/grouporder")
public class GroupBuyOrderController {

	@Autowired
	private GroupBuyOrderService groupBuyOrderService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
	/**
	 * 查询团购订单列表
	 * @param num
	 * @param status
	 * @param buyerid
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getGroupOrderList")
	public ReusltItem getGroupOrderList(String num, String status, String buyerid,
			String start, String end, String page, String size,String shopid) {
		ReusltItem item = new ReusltItem();
		try {
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaGroupBuyOrder cOrder = new CriteriaGroupBuyOrder();
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cOrder.setOrdercode(num.trim());
			}
			if (StringUtilsEX.ToInt(status) >= 0) {
				cOrder.setStatus(StringUtilsEX.ToInt(status));
			}
			if (StringUtilsEX.ToInt(buyerid) > 0) {
				cOrder.setBuyid(StringUtilsEX.ToInt(buyerid));
			}
			if (StringUtilsEX.ToInt(shopid) > 0) {
				cOrder.setShopid(StringUtilsEX.ToInt(shopid));
			}
			cOrder.setBegin(StringUtilsEX.ToShortDate(start));
			cOrder.setEnd(StringUtilsEX.ToShortDate(end));
			cOrder.setOrderByClause("a.createtime");
			cOrder.setSort("desc");

			PageBean pageBean = groupBuyOrderService.getGroupOrderByPage(cOrder, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取团购订单列表错误：", e,
					"/platform/grouporder/getGroupOrderList");
		}
		return item;
	}
	/**
	 * 删除团购订单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delGroupOrder")
	public ReusltItem delGroupOrder(String id){
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("参数错误：id"+id);
				return item;
			}
			int result = groupBuyOrderService.delGroupOrder(StringUtilsEX.ToInt(id));
			if (result > 0 ) {
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
                            		user.getUserId(), user.getLoginName(), "grouporder_list.jsp", "/platform/grouporder/getGroupOrderList", "删除团购订单");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除团购订单操作记录出错! 异常信息:",
    								e, "/platform/grouporder/getGroupOrderList");
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
				item.setDesc("获取删除团购订单的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取删除团购订单的信息错误：", e,
					"/platform/grouporder/getGroupOrderList");
		}
		return item;
	}
	
	/**
	 * 使用团购劵
	 * @param id
	 * @return
	 */
	@RequestMapping("/useGroupOrder")
	public ReusltItem useGroupOrder(String code,String id){
		ReusltItem item = new ReusltItem();
		try{
			 user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("参数错误：id"+id);
				return item;
			}
			if (groupBuyOrderService.updateUse(StringUtilsEX.ToInt(id),item) > 0) {
				item.setCode(0);
				item.setDesc("使用团购码成功！");
				LogHandle.info(LogType.Order, MessageFormat.format(
						"使用团购码成功! 团购码:{0}", code),
						"platform/grouporder/useGroupOrder");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "grouporder_list.jsp", "/platform/grouporder/useGroupOrder", "使用团购劵");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"使用团购劵操作记录出错! 异常信息:",
    								e, "/platform/grouporder/useGroupOrder");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				if(StringUtilsEX.IsNullOrWhiteSpace(item.getDesc())){
					item.setDesc("使用团购码失败！");
				}
				LogHandle.info(LogType.Order, MessageFormat.format(
						"使用团购码失败! 团购码:{0}", code),
						"platform/grouporder/useGroupOrder");
			}
		}
		catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("使用团购码出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"使用团购码错误：", e,
					"/platform/grouporder/useGroupOrder");
		}
		return item;
	}
	/**
	 * 退款审核
	 * @param id
	 * @return
	 */
	@RequestMapping("/confirmReturnOrder")
	public ReusltItem confirmReturnOrder(String id){
		ReusltItem item = new ReusltItem();
		try{
			 user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("参数错误：id"+id);
				return item;
			}
			if (groupBuyOrderService.updateTKSH(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("退款审核成功");
				LogHandle.info(LogType.Order, MessageFormat.format(
						"退款审核成功! 订单详情id:{0}", id),
						"platform/grouporder/confirmReturnOrder");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "grouporder_list.jsp", "/platform/grouporder/confirmReturnOrder", "退款审核");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"退款审核操作记录出错! 异常信息:",
    								e, "/platform/grouporder/confirmReturnOrder");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				if(StringUtilsEX.IsNullOrWhiteSpace(item.getDesc())){
					item.setDesc("退款审核失败！");
				}
				LogHandle.info(LogType.Order, MessageFormat.format(
						"退款审核失败! 订单详情id:{0}", id),
						"platform/grouporder/confirmReturnOrder");
			}
		}
		catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("退款审核出错：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"退款审核错误：", e,
					"/platform/grouporder/confirmReturnOrder");
		}
		return item;
	}
}

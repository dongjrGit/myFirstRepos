/*
 * @(#) WapActivityController.java 2016年7月11日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.GroupOrderDetailEnum;
import com.yinlian.Enums.GroupOrderEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.ShopStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Grouporderdetail;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.GroupBuyService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaGroupBuy;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/activity")
public class WapActivityController {
	

	@Autowired
	private GroupBuyService groupBuyService;

	@Autowired
	private GroupBuyOrderService groupBuyOrderService;
	

	

    @Autowired
    private OperaterecordsService operaterecordsService ;
	/**
	 * 团购信息
	 * 
	 * @author kh.wang
	 * @since 2016年7月11日
	 * @param page
	 * @param size
	 * @param model
	 * @return
	 */
	@RequestMapping("/tuan")
	public String tuan(String page,String size,Model model){
		ReusltItem item = new ReusltItem();
		try {
			Integer pindex = StringUtilsEX.ToIntNull(page);
			if (pindex == null || pindex <= 0) {
				pindex = 1;
			}
			Integer psize = StringUtilsEX.ToIntNull(size);
			if (psize == null || psize <= 0) {
				psize = 4;
			}
			CriteriaGroupBuy cGroupBuy = new CriteriaGroupBuy();
			// 正在营业中的店铺
			cGroupBuy.setStatus(ShopStatusEnum.Open.getValue());
			PageBean pageBean = groupBuyService.getGroupByPage(cGroupBuy,
					pindex, psize);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setMaxRow(pageBean.getPs());
			item.setPage(pageBean.getTp());
			item.setCode(0);
			model.addAttribute("list", item);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle
			.error(LogType.wap,"获取商品评论错误：{0}", e,
					"/activity/tuan");
		}
		return "/template/wap/activity/tuan_list";
	}
	
	
	/**
	 * 生成团购订单
	 * 
	 * @param ch
	 * @param token
	 * @param gid
	 * @param count
	 * @param beans
	 * @return
	 */
	@RequestMapping("/addOrder")
	public @ResponseBody String addOrder( String gid, String count,String beans,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionUtil.getSessionUser(request);
			int userid = user.getUserId();
			// int userid = 56;
		
			if (StringUtilsEX.ToInt(gid) <= 0) {
				item.setCode(-103);
				item.setDesc("团购劵ID参数错误！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(count) <= 0) {
				item.setCode(-104);
				item.setDesc("团购劵数量参数错误！");
				return item.toJson();
			}
			float disc=0.0f;
//			if(StringUtilsEX.ToFloat(discount)>=0.0f){
//				 disc=StringUtilsEX.ToFloat(discount);
//			}
			if (groupBuyOrderService.insert(userid, StringUtilsEX.ToInt(gid),
					StringUtilsEX.ToInt(count),disc,StringUtilsEX.ToIntNull(beans),user.getLoginName(), item) > 0) {
				item.setCode(0);
//				item.setData("生成团购订单成功!");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"生成团购订单成功! 团购劵ID:{0},userID:{1}", gid, userid),
						"api/groupbuy/addOrder");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "groupbuyDetail.html", "/api/wap/activity/addOrder", "生成团购订单成功");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"生成团购订单成功 异常信息:",
    								e, "/api/wap/activity/addOrder");
						}
					}
				});
			} else {
				if (item.getCode() >= 0) {
					item.setCode(-200);
					item.setDesc("生成订单失败！");
				}
				LogHandle.info(LogType.Api, MessageFormat.format(
						"生成团购订单失败! 团购劵ID:{0},userID:{1}", gid, userid),
						"api/groupbuy/addOrder");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"团购订单生成异常! 异常信息:{0}", e,
					"/activity/addOrder");
		}
		return item.toJson();
	}
	
	
	/**
	 * 付款
	 * 
	 * @param token
	 * @param ch
	 * @param id
	 * @return
	 */
	
	@RequestMapping("/payOrder")
	public @ResponseBody String payOrder( String ordercode,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			
			SessionUser user = SessionUtil.getSessionUser(request);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}

			if (StringUtilsEX.IsNullOrWhiteSpace(ordercode)) {
				item.setCode(-103);
				item.setDesc("团购订单编号参数错误！");
				return item.toJson();
			}
			if (groupBuyOrderService.savePayOrder(ordercode, user.getUserId(),
					user.getLoginName(),item) > 0) {
				item.setCode(0);
				item.setData("付款成功!");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"付款成功! 团购订单号:{0},userID:{1}", ordercode, user.getUserId()),
						"api/groupbuy/payOrder");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "orderPay.html", "/api/wap/activity/payOrder", "付款");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"付款 异常信息:",
    								e, "/api/wap/activity/payOrder");
						}
					}
				});
			}
			else {
				item.setCode(-200);
				item.setDesc("付款失败！");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"付款失败! 团购订单号:{0},userID:{1}", ordercode, user.getUserId()),
						"/activity/payOrder");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"团购订单付款异常! 异常信息:{0}", e,
					"/activity/payOrder");
		}
		return item.toJson();
	}

	
	@RequestMapping("/getOrderList")
	public String getOrderList(String page, String size,Model model, String status,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionUtil.getSessionUser(request);
			if(user.getCode()!= 0){
				return "/template/wap/userinfo/login";
			}else{
				CriteriaGroupBuy cGroupBuy = new CriteriaGroupBuy();
				if (StringUtilsEX.ToInt(status) >= GroupOrderEnum.待付款.getValue()
						|| StringUtilsEX.ToInt(status) <= GroupOrderEnum.已评价
								.getValue()) {
					cGroupBuy.setStatus(StringUtilsEX.ToInt(status));
				}
				cGroupBuy.setUserid(user.getUserId());
				cGroupBuy.setOrderByClause("a.CreateTime");
				cGroupBuy.setSort("desc");
				PageBean pageBean = groupBuyOrderService.getGroupByPage(cGroupBuy,
						StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
				item.setData(pageBean.getBeanList());
				item.setMaxRow(pageBean.getTr());
				item.setPageIndex(pageBean.getPc());
				item.setMaxRow(pageBean.getPs());
				item.setPage(pageBean.getTp());
				item.setCode(0);
				model.addAttribute("pageData",item);
			}
			
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取团购订单异常! 异常信息:{0}", e,
					"/activity/getOrderList");
		}
		return "/template/wap/activity/tuangou_order_list";
	}
	
	
	
	/**
	 * 取消团购订单
	 * 
	 * @param ch
	 * @param token
	 * @param id
	 * @return
	 */

	@RequestMapping("/cancelOrder")
	public  @ResponseBody String cancelOrder(String id,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			
			Integer userid=SessionUtil.getSessionUserId(request);
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-103);
				item.setDesc("团购订单ID参数错误！");
				return item.toJson();
			}
			if (groupBuyOrderService.updateCancel(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("取消团购订单成功！");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"取消团购成功! 团购ID:{0},userID:{1}", id, userid),
						"api/groupbuy/cancelOrder");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "OrderAll.html", "/api/wap/activity/cancelOrder", "取消团购订单");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"取消团购订单 异常信息:",
    								e, "/api/wap/activity/cancelOrder");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("取消团购失败！");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"取消团购失败! 团购ID:{0},userID:{1}", id, userid),
						"/activity/cancelOrder");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"取消团购异常! 异常信息:{0}", e,
					"/activity/cancelOrder");
		}
		return item.toJson();
	}
	
	/**
	 * 申请退款
	 * 
	 * @param ch
	 * @param token
	 * @param id
	 * @return
	 */
	@RequestMapping("/returnOrder")
	public @ResponseBody String returnOrder(String id,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			Integer userid=SessionUtil.getSessionUserId(request);
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-103);
				item.setDesc("团购子表ID参数错误！");
				return item.toJson();
			}
			Grouporderdetail detail = groupBuyOrderService
					.getDetailByID(StringUtilsEX.ToInt(id));
			if (detail == null) {
				item.setCode(-104);
				item.setDesc("根据团购子表ID未能找到团购信息！");
				return item.toJson();
			} else {
				if (detail.getUsetime() != null) {
					item.setCode(-105);
					item.setDesc("团购码已使用！");
					return item.toJson();
				}
				if(detail.getStatus()!=GroupOrderDetailEnum.未使用.getValue()){
					item.setCode(-107);
					item.setDesc("团购码状态 不是未使用，不能进行退款操作");
					return item.toJson();
				}
			}
			if (groupBuyOrderService.updateTK(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("团购订单申请退款成功！");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"团购订单申请退款成功! 团购ID:{0},userID:{1}", id, userid),
						"api/groupbuy/returnOrder");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "OrderAll.html", "/api/wap/activity/returnOrder", "团购订单申请退款");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"团购订单申请退款 异常信息:",
    								e, "/api/wap/activity/returnOrder");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("团购订单申请退款失败！");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"团购订单申请退款失败! 团购ID:{0},userID:{1}", id, userid),
						"activity/returnOrder");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"团购订单申请退款异常! 异常信息:{0}", e,
					"/activity/returnOrder");
		}
		return item.toJson();
	}

	
	

	/**
	 * 删除订单
	 * @param ch
	 * @param token
	 * @param id
	 * @return
	 */

	@RequestMapping("/delOrder")
	public @ResponseBody String delOrder(String id,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			List<Integer> idlist = new ArrayList<Integer>();
			
			Integer userid=SessionUtil.getSessionUserId(request);
			
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-103);
				item.setDesc("订单ID不能为空！");
				return item.toJson();
			}
			for (String oid : id.split(",")) {
				if (StringUtilsEX.ToInt(oid) <= 0) {
					item.setCode(-104);
					item.setDesc("订单ID参数错误！");
					return item.toJson();
				}
				idlist.add(StringUtilsEX.ToInt(oid));
			}
			if (idlist.size() == 1) {
				if (groupBuyOrderService.delOrder(idlist.get(0)) > 0) {
					item.setCode(0);
					item.setDesc("删除订单成功！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"删除订单成功! 订单ID:{0},userID:{1}", id, userid),
							"api/groupbuy/delOrder");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user=SessionState.GetCurrentUser();
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "OrderAll.html", "/api/wap/activity/delOrder", "删除订单");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"删除订单 异常信息:",
	    								e, "/api/wap/activity/delOrder");
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("删除订单失败！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"删除订单失败! 订单ID:{0},userID:{1}", id, userid),
							"/activity/delOrder");
				}
			} else {
				if (groupBuyOrderService.delOrderList(idlist) > 0) {
					item.setCode(0);
					item.setDesc("删除订单成功！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"删除订单成功! 订单ID:{0},userID:{1}", id, userid),
							"api/groupbuy/delOrder");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user=SessionState.GetCurrentUser();
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "删除订单", "/api/wap/activity/delOrder", "操作说明（例：删除订单）");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"删除订单 异常信息:",
	    								e, "/api/wap/activity/delOrder");
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("删除订单失败！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"删除订单失败! 订单ID:{0},userID:{1}", id, userid),
							"activity/delOrder");
				}
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"删除团购订单异常! 异常信息:{0}", e,
					"/activity/delOrder");
		}
		return item.toJson();
	}
}

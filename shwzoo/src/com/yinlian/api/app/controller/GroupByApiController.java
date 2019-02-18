/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.GroupOrderDetailEnum;
import com.yinlian.Enums.GroupOrderEnum;
import com.yinlian.Enums.ShopStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Grouporderdetail;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.GroupBuyService;
import com.yinlian.wssc.web.util.CriteriaGroupBuy;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * GroupByApiController.java
 * 
 * @author Liang.ma.s
 * @version $Id: GroupByApiController.java, v 0.1 2016年4月11日 下午12:02:58
 *          Administrator Exp $
 */
@RestController
@RequestMapping("/api/app/groupby")
public class GroupByApiController {

	@Autowired
	private GroupBuyService groupBuyService;
	@Autowired
	private GroupBuyOrderService groupBuyOrderService;

	/**
	 * 查询团购信息
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "list", produces = "text/html;charset=UTF-8")
	public String list(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			CriteriaGroupBuy cGroupBuy = new CriteriaGroupBuy();
			// 正在营业中的店铺
			cGroupBuy.setStatus(ShopStatusEnum.Open.getValue());
			PageBean pageBean = groupBuyService.getGroupByPage(cGroupBuy,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取团购列表异常! 异常信息:{0}", e,
					"api/groupbuy/list");
		}
		return item.toJson();
	}

	/**
	 * 获取团购详情
	 * 
	 * @param ch
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getDetail", produces = "text/html;charset=UTF-8")
	public String getDetail(String ch, String id) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();

			}
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-102);
				item.setDesc("团购ID参数错误！");
				return item.toJson();
			}
			item.setData(groupBuyService.getDetail(StringUtilsEX.ToInt(id)));
			item.setCode(0);

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取团购详情异常! 异常信息:{0}", e,
					"api/groupbuy/getDetail");
		}
		return item.toJson();
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
	@RequestMapping(value = "addOrder", produces = "text/html;charset=UTF-8")
	public String addOrder(String ch, String token, String gid, String count,String beans) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			int userid = user.getUserId();
			// int userid = 56;
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
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
			LogHandle.error(LogType.Api,"团购订单生成异常! 异常信息:{0}", e,
					"/groupbuy/addOrder");
		}
		return item.toJson();
	}

	/**
	 * 取消团购订单
	 * 
	 * @param ch
	 * @param token
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "cancelOrder", produces = "text/html;charset=UTF-8")
	public String cancelOrder(String ch, String token, String id) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			int userid = user.getUserId();
			// int userid=56;
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
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
			} else {
				item.setCode(-200);
				item.setDesc("取消团购失败！");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"取消团购失败! 团购ID:{0},userID:{1}", id, userid),
						"api/groupbuy/cancelOrder");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"取消团购异常! 异常信息:{0}", e,
					"/groupbuy/cancelOrder");
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
	@RequestMapping(value = "returnOrder", produces = "text/html;charset=UTF-8")
	public String returnOrder(String ch, String token, String id) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			int userid = user.getUserId();
			// int userid=56;
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
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
			} else {
				item.setCode(-200);
				item.setDesc("团购订单申请退款失败！");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"团购订单申请退款失败! 团购ID:{0},userID:{1}", id, userid),
						"api/groupbuy/returnOrder");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"团购订单申请退款异常! 异常信息:{0}", e,
					"/groupbuy/returnOrder");
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
	@RequestMapping(value = "payOrder", produces = "text/html;charset=UTF-8")
	public String payOrder(String token, String ch, String ordercode) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}

			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
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
			}
			else {
				item.setCode(-200);
				item.setDesc("付款失败！");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"付款失败! 团购订单号:{0},userID:{1}", ordercode, user.getUserId()),
						"api/groupbuy/payOrder");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"团购订单付款异常! 异常信息:{0}", e,
					"/groupbuy/payOrder");
		}
		return item.toJson();
	}

	/**
	 * 使用团购劵
	 * 
	 * @param ch
	 * @param token
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "useGroupBuy", produces = "text/html;charset=UTF-8")
	public String useGroupBuy(String ch, String token, String code) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			int userid = user.getUserId();
			// int userid=56;
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(code)) {
				item.setCode(-103);
				item.setDesc("团购码不能为空！");
				return item.toJson();
			}
			List<Integer> codelist = new ArrayList<Integer>();
			for (String icode : code.split(",")) {
				Grouporderdetail detail = groupBuyOrderService.getByCode(icode);
				if (detail == null) {
					item.setCode(-104);
					item.setDesc("根据团购码未能找到订单信息！");
					return item.toJson();
				} else {
					if (detail.getUsetime() != null) {
						item.setCode(-105);
						item.setDesc("团购码已使用！");
						return item.toJson();
					}
				}
				codelist.add(detail.getId());
			}
			if (codelist.size() == 1) {
				if (groupBuyOrderService.updateUse(codelist.get(0),item) > 0) {
					item.setCode(0);
					item.setDesc("使用团购码成功！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"使用团购码成功! 团购码:{0},userID:{1}", code, userid),
							"api/groupbuy/useGroupBuy");
				} else {
					item.setCode(-200);
					item.setDesc("使用团购码失败！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"使用团购码失败! 团购码:{0},userID:{1}", code, userid),
							"api/groupbuy/useGroupBuy");
				}

			} else if (codelist.size() > 1) {
				if (groupBuyOrderService.updateUseList(codelist) > 0) {
					item.setCode(0);
					item.setDesc("使用团购码成功！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"使用团购码成功! 团购码:{0},userID:{1}", code, userid),
							"api/groupbuy/useGroupBuy");
				} else {
					item.setCode(-200);
					item.setDesc("使用团购码失败！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"使用团购码失败! 团购码:{0},userID:{1}", code, userid),
							"api/groupbuy/useGroupBuy");
				}
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"使用团购码异常! 异常信息:{0}", e,
					"/groupbuy/useGroupBuy");
		}
		return item.toJson();
	}

	/**
	 * 获取团购订单列表
	 * 
	 * @param ch
	 * @param token
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "getOrderList", produces = "text/html;charset=UTF-8")
	public String getOrderList(String ch, String token, String page,
			String size, String status) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			int userid = user.getUserId();
//			 int userid=56;
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-103);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			CriteriaGroupBuy cGroupBuy = new CriteriaGroupBuy();
			if (StringUtilsEX.ToInt(status) >= GroupOrderEnum.待付款.getValue()
					|| StringUtilsEX.ToInt(status) <= GroupOrderEnum.已评价
							.getValue()) {
				cGroupBuy.setStatus(StringUtilsEX.ToInt(status));
				if(StringUtilsEX.ToInt(status)==GroupOrderEnum.待消费.getValue()){
					cGroupBuy.setDetailstatus(GroupOrderDetailEnum.未使用.getValue());
				}
				if(StringUtilsEX.ToInt(status)==GroupOrderEnum.申请退款.getValue()){
					cGroupBuy.setDetailstatus(GroupOrderDetailEnum.申请退款.getValue());
				}
			}
			cGroupBuy.setUserid(userid);
			cGroupBuy.setOrderByClause("a.CreateTime");
			cGroupBuy.setSort("desc");
			PageBean pageBean = groupBuyOrderService.getGroupByPage(cGroupBuy,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取团购订单异常! 异常信息:{0}", e,
					"/groupbuy/getOrderList");
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
	@RequestMapping(value = "delOrder", produces = "text/html;charset=UTF-8")
	public String delOrder(String ch, String token, String id) {
		ReusltItem item = new ReusltItem();
		try {
			List<Integer> idlist = new ArrayList<Integer>();
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			int userid = user.getUserId();
//			 int userid=56;
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
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
				} else {
					item.setCode(-200);
					item.setDesc("删除订单失败！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"删除订单失败! 订单ID:{0},userID:{1}", id, userid),
							"api/groupbuy/delOrder");
				}
			} else {
				if (groupBuyOrderService.delOrderList(idlist) > 0) {
					item.setCode(0);
					item.setDesc("删除订单成功！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"删除订单成功! 订单ID:{0},userID:{1}", id, userid),
							"api/groupbuy/delOrder");
				} else {
					item.setCode(-200);
					item.setDesc("删除订单失败！");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"删除订单失败! 订单ID:{0},userID:{1}", id, userid),
							"api/groupbuy/delOrder");
				}
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"删除团购订单异常! 异常信息:{0}", e,
					"/groupbuy/delOrder");
		}
		return item.toJson();
	}
}

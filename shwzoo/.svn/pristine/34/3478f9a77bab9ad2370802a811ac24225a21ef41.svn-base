/*
 * @(#) PcPayViewController.java 2016年7月18日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.view.pc.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wab/member/order/pay")
public class PcPayViewController {


	@Autowired
	private GroupBuyOrderService  groupBuyOrderService;
	

	@Autowired
	private OrderStatusService orderStatusService;
	
	/**
	 * 余额支付
	 * 
	 * @param token
	 * @param ch
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/payOrder", produces = "text/html;charset=UTF-8")
	public  String payOrder(String ordercode,HttpServletRequest request,String orderId) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionUtil.getSessionUser(request);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return "redirect:/member/user/showlogin";
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(ordercode)) {
				item.setCode(-103);
				item.setDesc("团购订单编号参数错误！");

				return "/template/error/index";
			}
			if (groupBuyOrderService.savePayOrder(ordercode, user.getUserId(),
					user.getLoginName(),item) > 0) {
				item.setCode(0);
				item.setData("付款成功!");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"付款成功! 团购订单号:{0},userID:{1}", ordercode, user.getUserId()),
						"api/groupbuy/payOrder");
				return "redirect:/wab/member/order/orderPaySuccess.html?orderId="+orderId;
			}
			else {
				item.setCode(-200);
				item.setDesc("付款失败！");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"付款失败! 团购订单号:{0},userID:{1}", ordercode, user.getUserId()),
						"api/groupbuy/payOrder");
				return "redirect:/wab/member/order/orderPayError.html?orderId="+orderId;
			}
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("团购订单付款异常：" + e.toString());
			LogHandle.error(LogType.Api,
					MessageFormat.format("团购订单付款异常! 异常信息:{0}", e),
					"api/groupbuy/payOrder");
			return "/template/error/index";
		}
	}
	
}

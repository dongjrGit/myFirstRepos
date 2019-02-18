/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.controller;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.app.alipay.AppAlipayNotify;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.PayconfigService;
import com.yinlian.wssc.web.util.PayEntity;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * api支付接口
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月29日 上午10:50:19 Exp $
 */
@Controller
@RequestMapping("/api/app/pay")
public class PayApiController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private GroupBuyOrderService  groupBuyOrderService;
    
	@Autowired
	private PayconfigService payconfigService;
    /**
     * 支付
     *@param backurl
     *@param orderNo
     *@param orderType
     *@param merchantName
     *@param userName
     *@param payType
     *@param payMoney
     *@param beanNumber
     *@param receivable
     *@param voucherCode
     *@return
     */
    @RequestMapping("/pay")
    public String pay(String backurl, String orderNo, Integer orderType, String merchantName,
                      String userName, String payType, Float payMoney, Integer beanNumber,
                      Float receivable, String voucherCode) {
        PayEntity entity = new PayEntity();
        entity.setOrderNo(orderNo);
        entity.setOrderType(orderType);
        entity.setBeanNumber(beanNumber);
        entity.setMerchantName(merchantName);
        entity.setUserName(userName);
        entity.setPayType(payType);
        entity.setPayMoney(payMoney);
        entity.setReceivable(receivable);
        entity.setVoucherCode(voucherCode);
     // TODO： 原支撑系统 要修改
       // String result = PayUtil.pay(backurl, entity);
        return "";
    }

    /**
     * 异步回调地址
     *@return
     */
    @RequestMapping(value = "/backurl", produces = "text/html;charset=UTF-8")
    public String backurl(HttpServletRequest request) {
        try {
            StringBuilder str = new StringBuilder();
            str.append("{");
            Map<String, String[]> params = request.getParameterMap(); 
            for (Entry<String, String[]> s : params.entrySet()) {
                str.append("\"" + s.getKey() + "\":");
                str.append("\"" + s.getValue()[0].replace("\"", "\\\"") + "\",");
            }
            LogHandle.error(LogType.Api, MessageFormat.format("异步回调地址参数：{0}", str.toString()),
                "pay/backurl");
            //TODO 如果code ="0000"; 更新订单状态
            String code = request.getParameter("code");
            System.out.println(code);
            LogHandle.info(LogType.Api, MessageFormat.format("异步回调地址返回代码：{0}", code), "/order/backurl");
            String msg = new String(request.getParameter("msg").getBytes("GBK"), "UTF-8");
            System.out.println(msg);
            String state = request.getParameter("state");
            System.out.println(state);
            System.out.println(request.getParameter("time"));
            System.out.println(request.getParameter("money"));
            String orderGroup = request.getParameter("businessOrderId");
            System.out.println(orderGroup);
            if ("0000".equals(code)) {
                orderService.updateByGroupCode(orderGroup,request.getParameter("money"));
            }
            request.setAttribute("msg", msg);           
        } catch (Exception e) {
            LogHandle.error(LogType.Api, "异步回调地址错误：{0}", e, "/pay/backurl");
        }
        return "returnUrl";
    }
    
    /**
     * 异步回调地址
     *@return
     */
    @RequestMapping(value = "/gbackurl", produces = "text/html;charset=UTF-8")
    public String gbackurl(HttpServletRequest request) {
        try {
        	//TODO 
            StringBuilder str = new StringBuilder();
            str.append("{");
            Map<String, String[]> params = request.getParameterMap();
            for (Entry<String, String[]> s : params.entrySet()) {
                str.append("\"" + s.getKey() + "\":");
                str.append("\"" + s.getValue()[0].replace("\"", "\\\"") + "\",");
            }
            LogHandle.error(LogType.Api, MessageFormat.format("异步回调地址参数：{0}", str.toString()),
                "pay/backurl");
            //TODO 如果code ="0000"; 更新订单状态
            String code = request.getParameter("code");
            System.out.println(code);
            String msg = new String(request.getParameter("msg").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(msg);
            String state = request.getParameter("state");
            System.out.println(state);
            System.out.println(request.getParameter("time"));
            System.out.println(request.getParameter("money"));
            String ordercode = request.getParameter("businessOrderId");
            System.out.println(ordercode);
          //  String money=request.getParameter("money");
            if ("0000".equals(code)) {
//            	groupBuyOrderService.updateFK(ordercode,Float.parseFloat(money) );
//                orderService.updateByGroupCode(orderGroup);
            }
            request.setAttribute("msg", msg);
        } catch (Exception e) {
            LogHandle.error(LogType.Api, MessageFormat.format("异步回调地址错误：{0}", e.toString()), "");
        }
        return "returnUrl";
    }
    /**
	 * 团购订单支付宝支付异步回调地址
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/notifyurl")
	public @ResponseBody String notify_url(HttpServletRequest request,
			HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号

			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号

			String trade_no = new String(request.getParameter("trade_no")
					.getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter(
					"trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// 订单总金额
			String total_fee = new String(request.getParameter("total_fee")
					.getBytes("ISO-8859-1"), "UTF-8");
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (AppAlipayNotify.verify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if (trade_status.equals("TRADE_FINISHED")
						|| trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					int commitre = groupBuyOrderService.updateFK(
							out_trade_no, Double.valueOf(total_fee),trade_no);
					if (commitre == 1) {
						item.setCode(0);
						item.setDesc("验证成功");
					}
					// 注意：
					// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				}
				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				item.setCode(-201);
				item.setDesc("支付宝验证失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("支付宝支付处理订单错误：" + e.toString());
			LogHandle.error(LogType.Api, "支付宝支付处理订单异常! 异常信息:{0}", e,
					"groupby/notifyurl");
		}
		return item.toJson();
	}
	@RequestMapping(value = "/getalipayconfig", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getalipayconfig(String token, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			item.setData(payconfigService.getAppConfig());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取支付宝配置错误：" + e.toString());
			LogHandle.error(LogType.Api, "获取支付宝配置异常! 异常信息:{0}", e,
					"order/getalipayconfig");
		}
		return item.toJson();
	}

	@RequestMapping(value = "/getipsconfig", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getipsconfig(String token, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			item.setData(payconfigService.getIpsConfig());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取支付宝配置错误：" + e.toString());
			LogHandle.error(LogType.Api, "获取支付宝配置异常! 异常信息:{0}", e,
					"order/getipsconfig");
		}
		return item.toJson();
	}

}

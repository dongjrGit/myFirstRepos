/*
 * @(#) PayController.java 2016年8月5日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.FinanceType;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.Extended.LogType;
import com.yinlian.alipay.AlipayConfig;
import com.yinlian.alipay.AlipayService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.service.UserFinanceService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionState;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pay")
public class PayController {
		
	
	@Autowired
	private UserFinanceService userFinanceService;
	@Autowired
	private UsercapitalService usercapitalService;
	@Autowired
	private OrderStatusService orderStatusService;

	@RequestMapping(value = "/paybygroupnum", produces = "text/html;charset=UTF-8")
	public  String paybygroupnum(String price,HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		String sHtmlText = "";
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        	String proNum = formatter.format(calendar.getTime());
	        long rand =15 - proNum.length();
	        proNum += (rand == -1 ?"": Long.toString(rand));
			String groupnum = proNum;
			//String gropname = "因联电商平台在线充值";
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			//插入用户充值记录
			Userfinance userfinance=new Userfinance();
			userfinance.setCreatetime(new Date());
			userfinance.setFinancetype(FinanceType.金额.getValue());
			userfinance.setMoney(Double.valueOf(price));
			userfinance.setNumber(groupnum);
			userfinance.setDescription("因联电商平台会员在线充值");
			userfinance.setStatus(UserFinance_Type.未支付.getValue());
			userfinance.setType(CapitalChange_Type.保证金充值.getValue());
			userfinance.setUserid(sessionUser.getUserId());
			userfinance.setUserip(GetIpAddresss.getIp());
		    int ret=userFinanceService.insert(userfinance);
		    if(ret==1){
				// 商户订单号
				String out_trade_no = new String(groupnum.getBytes("ISO-8859-1"), "UTF-8");
				// 商户网站订单系统中唯一订单号，必填

				// 订单名称
				String subject = new String(groupnum.getBytes("ISO-8859-1"),"UTF-8");
				// 必填

				// 付款金额
				String total_fee = new String(price.toString().getBytes("ISO-8859-1"), "UTF-8");
				// 必填

				// 订单描述
				String body = "11111";
//				String descString = "订单" + groupnum + "余额支付".getBytes("ISO-8859-1");
//				String body = new String(descString.getBytes("ISO-8859-1"),"UTF-8");
				// 商品展示地址
				// String show_url = new
				// String("http://yinlink.f3322.net:9001/platform/login".getBytes("ISO-8859-1"),"UTF-8");
				// 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

				// ////////////////////////////////////////////////////////////////////////////////

				// 把请求参数打包成数组
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("service", "create_direct_pay_by_user");
				sParaTemp.put("partner", AlipayConfig.getAlipayConfig().partner);
				sParaTemp.put("seller_id", AlipayConfig.getAlipayConfig().seller_email);
				sParaTemp.put("_input_charset", AlipayConfig.getAlipayConfig().input_charset);
				sParaTemp.put("payment_type", AlipayConfig.getAlipayConfig().payment_type);
				sParaTemp.put("notify_url", AlipayConfig.getAlipayConfig().alipay_notify_url_cz);
				sParaTemp.put("return_url","");
				sParaTemp.put("out_trade_no", out_trade_no);
				sParaTemp.put("subject", subject);
				sParaTemp.put("total_fee", total_fee);
				sParaTemp.put("body", body);
				sParaTemp.put("show_url", "");
				sParaTemp.put("anti_phishing_key", AlipayConfig.getAlipayConfig().anti_phishing_key);
				sParaTemp.put("exter_invoke_ip", AlipayConfig.getAlipayConfig().exter_invoke_ip);

				// 建立请求
				sHtmlText = AlipayService.buildRequest(sParaTemp, "get", "确认");
				response.getWriter().write(sHtmlText);
		    }
		} catch (Exception e) {
			 item.setCode(-900);
	            if (DebugConfig.BLUETOOTH_DEBUG) {
	            	item.setDesc("金额充值异常："+e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Pay,
					"余额充值异常! 异常信息:{0}", e,
					"/seller/order/paybygroupnum");
		}
		return item.toJson();
	}
	
	@RequestMapping("/balanceTurnMargin")
	public ReusltItem balanceTurnMargin(Float price){
		ReusltItem item=new ReusltItem();
		try {
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item;
			}
			Usercapital usercap=usercapitalService.queryByUserId(sessionUser.getUserId());
			if (usercap.getBalance()>=price) {
				orderStatusService.addBalancetoBond(price, sessionUser.getId(), item);
				item.setDesc("金额转保证金成功");
			}else{
				item.setCode(105);
				item.setDesc("余额不足");
				return item;
			}
			
		} catch (Exception e) {
			 item.setCode(-900);
	            if (DebugConfig.BLUETOOTH_DEBUG) {
	            	item.setDesc("余额转保证金异常：:"+e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.SellerShopManagement,
					"余额转保证金异常! 异常信息:{0}", e,
					"order/paybygroupnum");
		}
		return item;
	}
	
}

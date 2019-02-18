/*
 * @(#) PcAlipayController.java 2016年7月15日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.pc.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Enums.PayTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.alipay.AlipayConfig;
import com.yinlian.alipay.AlipayNotify;
import com.yinlian.alipay.AlipayService;
import com.yinlian.api.wap.controller.IpsConfig;
import com.yinlian.tenpay.MatrixToImageWriter;
import com.yinlian.tenpay.PaymentUtil;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.XmlUtils;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年7月15日
 */
@RestController
@RequestMapping("/pc/pay")
public class PcPayController {

	@Autowired
	private GroupBuyOrderService  groupBuyOrderService;
	@Autowired 
	private OrderService orderService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;
	

	@Autowired
	private OrderStatusService orderStatusService;
	
	@RequestMapping(value = "/alipypay", produces = "text/html;charset=UTF-8")
	public void alipayByGroupOrder(String groupnum, String ch,
			HttpServletResponse response) {
		String sHtmlText = "";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				sHtmlText += "<div><b>支付请求错误!</b><br/>";
				sHtmlText += "<b>错误码:108</b><br/>";
				sHtmlText += "<b>说明:登录通道参数错误</b></div>";
				response.getWriter().write(sHtmlText);
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(groupnum)) {
				sHtmlText += "<div><b>支付请求错误!</b><br/>";
				sHtmlText += "<b>错误码:101</b><br/>";
				sHtmlText += "<b>说明:订单组编号参数错误</b></div>";
				response.getWriter().write(sHtmlText);
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser.getCode() != 0) {
				sHtmlText += "<div><b>支付请求错误!</b><br/>";
				sHtmlText += "<b>错误码:401</b><br/>";
				sHtmlText += "<b>说明:用戶未登陆</b></div>";
				response.getWriter().write(sHtmlText);
			}
			List<Orders> orderlist = orderService.getOrderByGroupCode(groupnum);
			if (orderlist == null) {
				LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
				sHtmlText += "<div><b>支付请求错误!</b><br/>";
				sHtmlText += "<b>错误码:403</b><br/>";
				sHtmlText += "<b>说明:组订单号错误</b></div>";
				response.getWriter().write(sHtmlText);
			}
			if (orderlist != null && orderlist.size() > 0) {
				if(orderlist.get(0).getStatus()!=OrderStatusEnum.待付款.getValue()){
					sHtmlText += "<div><b>支付请求错误!</b><br/>";
					sHtmlText += "<b>错误码:103</b><br/>";
					sHtmlText += "<b>说明:订单状态不是待付款，不能进行付款</b></div>";
					response.getWriter().write(sHtmlText);
				}
				Double ss = orderlist.stream()
						.mapToDouble(x -> x.getActualpay().doubleValue()).sum();
				
				// 商户订单号
				String out_trade_no = new String(groupnum.getBytes("ISO-8859-1"), "UTF-8");
				// 商户网站订单系统中唯一订单号，必填

				// 订单名称
				String subject = new String(groupnum.getBytes("ISO-8859-1"),"UTF-8");
				// 必填

				// 付款金额
				String total_fee = new String(ss.toString().getBytes("ISO-8859-1"), "UTF-8");
				// 必填

				// 订单描述
				String descString = "订单" + groupnum + "在线支付".getBytes("ISO-8859-1");
				String body = new String(descString.getBytes("ISO-8859-1"),"UTF-8");
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
				sParaTemp.put("notify_url", AlipayConfig.getAlipayConfig().notify_url);
				sParaTemp.put("return_url", AlipayConfig.getAlipayConfig().return_url);
				sParaTemp.put("out_trade_no", out_trade_no);
				sParaTemp.put("subject", subject);
				sParaTemp.put("total_fee", total_fee);
				sParaTemp.put("body", body);
				sParaTemp.put("show_url", "");
				sParaTemp.put("anti_phishing_key", AlipayConfig.getAlipayConfig().anti_phishing_key);
				sParaTemp.put("exter_invoke_ip", AlipayConfig.getAlipayConfig().exter_invoke_ip);

				// 建立请求
				sHtmlText = AlipayService.buildRequest(sParaTemp, "get", "确认");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);
			}

		} catch (Exception e) {
			LogHandle.debug(LogType.pc,
					MessageFormat.format("支付订单异常! 异常信息:{0}", e.toString()),
					"/pc/pay/alipypay");
		}
	}	
	
	/**
	 * 支付
	 * 
	 * @param ids
	 * @param status
	 * @param userid
	 * @param totalMoney
	 * @param userip
	 * @return
	 */
	@RequestMapping(value = "/balancepay", produces = "text/html;charset=UTF-8")
	public String pay(String groupnum, String ch,HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtils.isBlanck(groupnum)) {
				item.setCode(-101);
				item.setDesc("订单组单号不能为空");
				return item.toJson();
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			String userip = GetIpAddresss.getIp();
			int userid = sessionUser.getUserId();
			List<Orders> orders = orderService.getOrderByGroupCode(groupnum);
			if (orders == null || orders.size()==0) {
				item.setCode(-403);
				item.setDesc("组订单号错误");
				LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
				return item.toJson();
			}
			if (orders.size()>0 && orders.get(0).getStatus().equals(OrderStatusEnum.待付款.getValue())) {
				Double ss = orders.stream()
						.mapToDouble(x -> x.getActualpay().doubleValue()).sum();
				int result = orderStatusService.updatePayforBalanceCode(
						groupnum, userid, userip, item);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("付款成功");
					response.sendRedirect("/member/order/orderPaySuccess.html?out_trade_no="+groupnum+"&total_fee="+ss);
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {							
							try {								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), sessionUser.getId(), sessionUser.getLoginName(), "order_pay.html", "/pc/order/balancepay", "余额支付");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"余额支付记录 异常信息:",
	    								e, "/pc/pay/balancepay");
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("付款失败");
					LogHandle.debug(LogType.Api, MessageFormat.format(
							"付款失败! 参数信息订单组编号:{0}", groupnum), "order/pay");
					response.sendRedirect("/member/order/orderPayError.html");
				}
			} else {
				item.setCode(-103);
				item.setDesc("订单状态不是待付款，不能进行付款");
				response.sendRedirect("/member/order/orderPayError.html");
			}

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("支付订单异常：" + e.getMessage());
			LogHandle.debug(LogType.Api,
					MessageFormat.format("支付订单异常! 异常信息:{0}", e.toString()),
					"/pc/pay/balancepay");
		}
		return item.toJson();
	}
	/**
	 * 环迅支付
	 * @param groupnum
	 * @param token
	 * @param ch
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ipspay", produces = "text/html;charset=UTF-8")
	public String ipspay(String groupnum, String ch,
			HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(groupnum)) {
				item.setCode(-101);
				item.setDesc("订单组编号参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			List<Orders> orderlist = orderService.getOrderByGroupCode(groupnum);
			if (orderlist == null) {
				item.setCode(-403);
				item.setDesc("组订单号错误");
				LogHandle.error(LogType.Order,
						MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
				return item.toJson();
			}
			
			if (orderlist != null && orderlist.size() > 0) {
				if(orderlist.get(0).getStatus()!=OrderStatusEnum.待付款.getValue()){
					item.setCode(-103);
					item.setDesc("订单状态不是待付款，不能进行付款");
					return item.toJson();
				}
				Double ss = orderlist.stream()
						.mapToDouble(x -> x.getActualpay().doubleValue()).sum();

				// 订单的MD5摘要明文(报文主体-body)
				String gateWayReqBody = "<body>";
				// 订单号
				gateWayReqBody += "<MerBillNo>"+groupnum+"</MerBillNo>";
				// 订单金额
				gateWayReqBody += "<Amount>"+ss+"</Amount>";
				// 订单日期
				gateWayReqBody += "<Date>"
						+ new java.text.SimpleDateFormat("yyyyMMdd")
								.format(orderlist.get(0).getAddorderdate()) + "</Date>";
				// 币种（人民币 156）
				gateWayReqBody += "<CurrencyType>" + IpsConfig.getIpsConfig().currency
						+ "</CurrencyType>";
				// 支付方式 默认借记卡01 信用卡02 IPS账号支付03
				gateWayReqBody += "<GatewayType>" + IpsConfig.getIpsConfig().gateway
						+ "</GatewayType>";
				// 语言
				gateWayReqBody += "<Lang>"+IpsConfig.getIpsConfig().lang+"</Lang>";
				// 支付结果成功返回的商户URL
				gateWayReqBody += "<Merchanturl>"+IpsConfig.getIpsConfig().merchanturl+"</Merchanturl>";
				// 支付结果失败返回的商户URL
				gateWayReqBody += "<FailUrl>"+IpsConfig.getIpsConfig().failUrl+"</FailUrl>";
				// 商户数据包（存放商户自己的信息，支付成功后原封不动的返回给商户）
				gateWayReqBody += "<Attach>qwe</Attach>";
				// 订单支付接口加密方式 MD5
				gateWayReqBody += "<OrderEncodeType>"+IpsConfig.getIpsConfig().orderEncode+"</OrderEncodeType>";
				// 交易返回接口加密方式
				gateWayReqBody += "<RetEncodeType>"+IpsConfig.getIpsConfig().retEncode+"</RetEncodeType>";
				// 返回方式 S2S
				gateWayReqBody += "<RetType>"+IpsConfig.getIpsConfig().retType+"</RetType>";
				// 异步S2S返回
				gateWayReqBody += "<ServerUrl>"+IpsConfig.getIpsConfig().serverUrl+"</ServerUrl>";
				// 订单有效期 以小时为单位，必须是整数
				gateWayReqBody += "<BillEXP>"+IpsConfig.getIpsConfig().billEXP+"</BillEXP>";
				// 商品名称
				gateWayReqBody += "<GoodsName>环迅在线支付</GoodsName>";
				// 直连方式
				gateWayReqBody += "<IsCredit>"+IpsConfig.getIpsConfig().isCredit+"</IsCredit>";
				// 银行号
				gateWayReqBody += "<BankCode>"+IpsConfig.getIpsConfig().bankCode+"</BankCode>";
				// 产品类型 1-个人网银 2-企业网银
				gateWayReqBody += "<ProductType>"+IpsConfig.getIpsConfig().productType+"</ProductType>";
				gateWayReqBody += "</body>";
				String strMd5 = IPSCrypto.Security.MD5(gateWayReqBody
						+ IpsConfig.getIpsConfig().merCode + IpsConfig.getIpsConfig().md5key);
				// 组织提交表单
				String GateWayReq = "<Ips><GateWayReq><head>";
				// 版本号
				GateWayReq += "<Version>" + IpsConfig.getIpsConfig().version + "</Version>";
				// 商户号
				GateWayReq += "<MerCode>" + IpsConfig.getIpsConfig().merCode + "</MerCode>";
				// 商户名称
				GateWayReq += "<MerName>" + IpsConfig.getIpsConfig().merName + "</MerName>";
				// 账户号
				GateWayReq += "<Account>" + IpsConfig.getIpsConfig().account + "</Account>";
				// 交易编号
				GateWayReq += "<MsgId>" + IpsConfig.getIpsConfig().msgId + "</MsgId>";
				// 商户请求时间
				GateWayReq += "<ReqDate>"
						+ new java.text.SimpleDateFormat("yyyyMMddHHmmss")
								.format(new java.util.Date()) + "</ReqDate>";
				// 数字签名
				GateWayReq += "<Signature>" + strMd5 + "</Signature>";
				GateWayReq += "</head>";
				GateWayReq += gateWayReqBody;
				GateWayReq += "</GateWayReq></Ips>";

				String postForm = "<form method=\"post\" action=\""
						+ IpsConfig.getIpsConfig().actionUrl + "\">";
				postForm += "<input type=\"text\" value=\"" + GateWayReq
						+ "\" name=\"pGateWayReq\"></input>";								
				 postForm +="<input type=\"submit\" value=\"点击支付\" style=\"display:none;\" ></input>";
				 postForm += "</form>";
//				 System.out.println(postForm);
				// 自动提交该表单
//				postForm += "<script>document.forms[0].submit();</script>";
				postForm += "<script type=\"text/javascript\" language=\"javascript\">function submitForm(){document.forms[0].submit();} setTimeout(submitForm,0);</script>";
				response.getWriter().write(postForm);
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("IPS支付请求错误：" + e.toString());
			LogHandle.error(LogType.pc, "IPS支付请求异常! 异常信息:{0}", e,
					"/pc/pay/ipspay");
		}
		return item.toJson();
	}
	
	/**
	 * 支付宝异步回调
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/notifyurl", produces = "text/html;charset=UTF-8")
	public String notify_url(HttpServletRequest request) {
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

			if (AlipayNotify.verify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if (trade_status.equals("TRADE_FINISHED")
						|| trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					String userip = GetIpAddresss.getIp();
					int commitre = orderStatusService.updatePayforAlipayCode(
							out_trade_no, userip, trade_no, total_fee,PayTypeEnum.支付宝支付.getValue(),item);
					if (commitre == 1) {
						item.setCode(0);
						item.setDesc("验证成功");
						ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {
									SessionUser user=SessionState.GetCurrentUser();
									if(user!=null&&user.getId()>0){
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "支付宝异步回调", "/pc/order/notifyurl", "操作说明（例：支付宝异步回调）");
									}
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"支付宝异步回调 异常信息:",
		    								e, "/pc/pay/notifyurl");
								}
							}
						});
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
			LogHandle.error(LogType.pc, "支付宝支付处理订单异常! 异常信息:{0}", e,
					"/pc/pay/notifyurl");
		}
		return item.toJson();
	}
	
	/**
	 * 环迅支付成功异步回调
	 * @param request
	 * @return
	 */
	@RequestMapping("/ipsnotifyurl")
	public String ipsreturn(HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			String returnXml = request.getParameter("paymentResult");
			Map<String, Object> map = XmlUtils.parserValueForXml(returnXml);
			if (map.get("RspCode").equals("000000")) {
				Matcher m = Pattern.compile("<body>[\\s\\S]+</body>").matcher(
						returnXml);
				String bodystr = null;
				while (m.find()) {
					String g = m.group();
					// System.out.println(g);
					bodystr = g;
				}
				if(!StringUtilsEX.IsNullOrWhiteSpace(bodystr)){
					String strMd5 = IPSCrypto.Security.MD5(bodystr
							+ IpsConfig.getIpsConfig().merCode + IpsConfig.getIpsConfig().md5key);
					if(!map.get("Signature").equals(strMd5)){
						item.setDesc("数字签名验证失败");
					}
				}
				//订单号
				String merBillNo=map.get("MerBillNo").toString(); 
				System.out.println(merBillNo);
				//IPS交易号
				String IpsTradeNo=map.get("IpsTradeNo").toString(); 
				System.out.println(IpsTradeNo);
				//订单金额
				String Amount=map.get("Amount").toString(); 
				System.out.println(Amount);
				String userip = GetIpAddresss.getIp();
				int commitre = orderStatusService.updatePayforAlipayCode(
						merBillNo, userip, IpsTradeNo,Amount,PayTypeEnum.IPS支付.getValue(), item);
				if (commitre == 1) {
					item.setCode(0);
					item.setDesc("验证成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user=SessionState.GetCurrentUser();
								if(user!=null&&user.getId()>0){
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "order_pay.html", "/pc/order/ipsnotifyurl", "环迅支付成功异步回调");

								}
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"环迅支付成功异步回调:",
	    								e, "/pc/pay/ipsnotifyurl");
							} 
						}
					});
				}
				
			} else {
				item.setCode(-200);
				item.setDesc(map.get("RspMsg").toString());

			}
			// System.out.println(returnXml);

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("ips支付处理订单错误：" + e.toString());
			LogHandle.error(LogType.pc, "ips支付处理订单异常! 异常信息:{0}", e,
					"/pc/pay/ipsnotifyurl");
		}
		return item.toJson();
	}
	
	 /**
     * 环迅支付成功同步通知
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/paysuccess")
    public String paysuccess(HttpServletRequest request,HttpServletResponse response) {
    	String returnurl="";
    	try{
    		String returnXml = request.getParameter("paymentResult");
			Map<String, Object> map = XmlUtils.parserValueForXml(returnXml);
			if (map.get("RspCode").equals("000000")) {
				//订单金额
				String orderprice=map.get("Amount").toString(); 
				String groupnum=map.get("MerBillNo").toString(); 
				response.sendRedirect("/member/order/orderPaySuccess.html?out_trade_no="+groupnum+"&total_fee="+orderprice);
			}
    		
    	} catch (Exception e) {
			LogHandle.error(LogType.pc, "ips支付处理订单异常! 异常信息:{0}", e,
					"/pc/pay/paysuccess");
		}
    	
        return returnurl;
    }
	
	/**
	 * 创建二维码
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/createQRCode",produces = "text/html;charset=UTF-8")
	public String createQRCode(HttpServletResponse response,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			String ch=request.getParameter("ch");
			String groupnum=request.getParameter("groupnum");
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(groupnum)) {
				item.setCode(-101);
				item.setDesc("订单组编号参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			List<Orders> orderlist = orderService.getOrderByGroupCode(groupnum);
			if (orderlist == null) {
				item.setCode(-403);
				item.setDesc("组订单号错误");
				LogHandle.error(LogType.Order,
						MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
				return item.toJson();
			}
			Double ss=0.00;
			if (orderlist != null && orderlist.size() > 0) {
				 ss = orderlist.stream().mapToDouble(x -> x.getActualpay().doubleValue()).sum();
				}
			else{
				item.setCode(-404);
				item.setDesc("根据组订单号未能检索到数据");
				return item.toJson();
			}
			String userip = GetIpAddresss.getIp();
			String [] errordesc=new String [1];
			// 调统一下单API
			String code_url = PaymentUtil.getCodeurl(groupnum, ss.toString(), userip, "订单在线支付",errordesc);
			if(code_url==""){
				item.setCode(-405);
				item.setDesc("获取二维码错误："+errordesc[0]);
				return item.toJson();
			}
			// 将返回预支付交易链接（code_url）生成二维码图片
			int width = 200;
			int height = 200;
			String format = "png";
			Hashtable hints = new Hashtable();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(code_url,
					BarcodeFormat.QR_CODE, width, height, hints);
			OutputStream out = null;
			out = response.getOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, format, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取微信支付二维码错误：" + e.toString());
			LogHandle.error(LogType.pc, "获取微信支付二维码异常! 异常信息:{0}", e,
					"/pc/pay/createQRCode");
		}
		return item.toJson();
	}

	/**
	 * 微信支付回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/wechatnotify")
	public String wechatPayNotify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReusltItem item = new ReusltItem();
		try {
			// 解析结果存储在HashMap
			Map<String, String> map = new HashMap<String, String>();
			InputStream inputStream = request.getInputStream();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();

			// 遍历所有子节点
			for (Element e : elementList)
				map.put(e.getName(), e.getText());

			// 释放资源
			inputStream.close();
			inputStream = null;

			String returnCode = map.get("return_code");
			if ("SUCCESS".equals(returnCode)) {
				String resultCode = map.get("result_code");
				if ("SUCCESS".equals(resultCode)) {
					//下面为业务逻辑处理：如果支付成功，则修改该用户的付费状态，更新付费时间。
					String out_trade_no = map.get("out_trade_no");
					String total_fee = map.get("total_fee");
					String transaction_id = map.get("transaction_id");
					if(!StringUtilsEX.IsNullOrWhiteSpace(total_fee)){
						total_fee=BigDecimal.valueOf(Long.valueOf(total_fee)).divide(new BigDecimal(100)).toString();
					}
					String userip = GetIpAddresss.getIp();
					int commitre = orderStatusService.updatePayforAlipayCode(
							out_trade_no, userip, transaction_id, total_fee,
							PayTypeEnum.微信支付.getValue(),item);
					if (commitre == 1) {
						item.setCode(0);
						item.setDesc("订单支付处理成功");
						ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {
									SessionUser user=SessionState.GetCurrentUser();
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "order_pay.html", "/pc/order/wechatnotify", "微信支付回调");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"微信支付回调:",
		    								e, "/pc/pay/wechatnotify");
								}
							}
						});
					}
				} else {
					item.setCode(-200);
					item.setDesc("支付失败");
				}
			} else {
				item.setCode(-201);
				String returnMsg = map.get("return_msg");
				item.setDesc("支付通信失败！" + returnMsg);
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("支付失败");
			LogHandle.error(LogType.pc, "微信支付处理订单异常! 异常信息:{0}", e,
					"/pc/pay/wechatnotify");
		}
		return item.toJson();
	}
}

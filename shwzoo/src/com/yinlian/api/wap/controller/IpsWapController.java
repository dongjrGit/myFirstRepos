package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PayTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.XmlUtils;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/wap/IpsWap")
public class IpsWapController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	private GroupBuyOrderService groupBuyOrderService;
	

    @Autowired
    private OperaterecordsService operaterecordsService ;


//	@RequestMapping(value = "/ipspay", produces = "text/html;charset=UTF-8")
//	public String ipspay(String groupnum, String token, String ch,
//			HttpServletResponse response) {
//		ReusltItem item = new ReusltItem();
//		try {
//			if (!StringUtilsEX.isChannelTypeExist(ch)) {
//				item.setCode(-108);
//				item.setDesc("登录通道参数错误");
//				return item.toJson();
//			}
//			if (StringUtilsEX.IsNullOrWhiteSpace(groupnum)) {
//				item.setCode(-101);
//				item.setDesc("订单组编号参数错误");
//				return item.toJson();
//			}
//			SessionUser sessionUser = SessionState.GetCurrentUser(token);
//			if (sessionUser.getCode() != 0) {
//				item.setCode(-401);
//				item.setDesc("用戶未登陆！");
//				return item.toJson();
//			}
//			List<Orders> orderlist = orderService.getOrderByGroupCode(groupnum);
//			if (orderlist == null) {
//				item.setCode(-403);
//				item.setDesc("组订单号错误");
//				LogHandle.error(LogType.Order,
//						MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
//				return item.toJson();
//			}
//			if (orderlist != null && orderlist.size() > 0) {
//				Double ss = orderlist.stream()
//						.mapToDouble(x -> x.getActualpay().doubleValue()).sum();
//
//				// 测试商户号
//				String merCode = "120101";
//
//				// 120101测试商户的md5的证书
//				String md5key = "loykhyDlfi8rlWB5CT5UUuwsi8puD9Aykru5MCok438CHS4DjiioZDnQSgb9jt858jhTCgJaEuz1LJBqeCk40vz6d3CFAFbkUcd7KAadyiAFnMei4L56idemKBQj3dzZ";
//
//				// 120101测试商户的rsa公钥
//				String rsakey = "<RSAKeyValue><Modulus>tfq42SqsoDuzqLUAz7iqe1z1CTaAuFqu3ZMdhDHsuLw3bqAJ4gTCITIg+71pkhY6GxUUPw4LNP6Y2KjdtLOrAfRxqqYmB6FOlQir15Y3zQUtG8kMvu1XHzDNZZ8h4l8oxgz8sBMHDAPqepQZOS46zXyd65mUd8c8TZoZTFtXKR8=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
//
//				// 测试环境提交地址
//				String actionUrl = "http://paynew.ips.net.cn/B2C/Distribute/Pay.aspx";
//
//				String pIsDistribute = "Y";
//				String pWhoCall = "IPSCALL";
//
//				String RuleDetails = "";
//				// 判断分帐明细的加密方式
//				String pruleString = "120101,60,0,1|alashy@163.com,40,100,0";
//				String pDisEncodeType = "1";
//				String pRuleType = "ALT";
//				String pOrderEncodeType = "2";
//				// if (pDisEncodeType.equalsIgnoreCase("1")) {
//				// BASE64编码
//				sun.misc.BASE64Encoder enc64 = new sun.misc.BASE64Encoder();
//				RuleDetails = enc64.encodeBuffer(pruleString.getBytes("utf-8"))
//						.trim();
//				// }
//				String DetailsSign = "";
//				// if (pIsDistribute.equalsIgnoreCase("Y")
//				// && pRuleType.equalsIgnoreCase("ALT")
//				// && pDisEncodeType.equalsIgnoreCase("1")) {
//				DetailsSign = IPSCrypto.Security.MD5(RuleDetails + md5key);
//				// }
//
//				// 订单的MD5摘要明文
//				String strMd5Text = groupnum + ss + "20160615" + "RMB" + md5key;
//
//				// 进行MD5摘要操作
//				String strMd5 = "";
//				// if (pOrderEncodeType.equalsIgnoreCase("2")) {
//				// // MD5加密提交
//
//				strMd5 = IPSCrypto.Security.MD5(strMd5Text);
//				// } else {
//				// // 明文提交
//				//
//				// strMd5 = strMd5Text;
//				// }
//				String successURL = "http://paynew.ips.net.cn/Result/DisPayResult.aspx";
//				String failureUrl = "http://paynew.ips.net.cn/Result/DisPayResult.aspx";
//				String errorURL = "http://paynew.ips.net.cn/Error.aspx";
//				// 组织提交表单
//				String postForm = "<form name=\"B2CPay\" id=\"B2CPay\" method=\"post\" action=\""
//						+ actionUrl + "\">";
//				// 订单号
//				postForm += "<input type=\"hidden\" name=\"pBillNo\" value=\""
//						+ groupnum + "\" />";
//				// 商户号
//				postForm += "<input type=\"hidden\" name=\"pMerCode\" value=\""
//						+ merCode + "\" />";
//				// 订单金额
//				postForm += "<input type=\"hidden\" name=\"pAmount\" value=\""
//						+ ss + "\" />";
//				// 订单日期
//				postForm += "<input type=\"hidden\" name=\"pDate\" value=\"20160615\" />";
//				// 币种
//				postForm += "<input type=\"hidden\" name=\"pCurrency\" value=\"RMB\" />";
//				// 语言
//				postForm += "<input type=\"hidden\" name=\"pLang\" value=\"GB\" />";
//				// 支付结果成功返回的商户URL
//				postForm += "<input type=\"hidden\" name=\"pSuccessUrl\" value=\""
//						+ successURL + "\" />";
//				// 支付结果失败返回的商户URL
//				postForm += "<input type=\"hidden\" name=\"pFailureUrl\" value=\""
//						+ failureUrl + "\" />";
//				// 支付异常返回的商户URL
//				postForm += "<input type=\"hidden\" name=\"pErrorUrl\" value=\""
//						+ errorURL + "\" />";
//				// // 商户附加信息
//				postForm += "<input type=\"hidden\" name=\"pAttach\" value=\"测试 \" />";
//				// 订单支付接口加密方式（MD5）
//				postForm += "<input type=\"hidden\" name=\"pOrderEncodeType\" value=\"2\" />";
//				// 交易返回接口加密方式(11-MD5WITHRSA的签名认证 12-MD5的摘要认证)
//				postForm += "<input type=\"hidden\" name=\"pRetEncodeType\" value=\"11\" />";
//				// 是否采用S2S返回 1-是 0-否
//				postForm += "<input type=\"hidden\" name=\"pRetType\" value=\"1\" />";
//				// S2S返回地址
//				postForm += "<input type=\"hidden\" name=\"pIsDistribute\" value=\""
//						+ pIsDistribute + "\" />";
//				// 分账规则类型
//				postForm += "<input type=\"hidden\" name=\"pRuleType\" value=\"ALT\" />";
//				// 分账金额的类型
//				postForm += "<input type=\"hidden\" name=\"pAmtType\" value=\"0\" />";
//				// 分账手续费类型
//				postForm += "<input type=\"hidden\" name=\"pFeeType\" value=\"0\" />";
//				// 分账手续费计算的基数
//				postForm += "<input type=\"hidden\" name=\"pFeeBase\" value=\"1\" />";
//				// 分账指令
//				postForm += "<input type=\"hidden\" name=\"pInstruction\" value=\"1\" />";
//				//
//				postForm += "<input type=\"hidden\" name=\"pWhoCall\" value=\""
//						+ pWhoCall + "\" />";
//				// 分账明细加密方式
//				postForm += "<input type=\"hidden\" name=\"pDisEncodeType\" value=\"1\" />";
//				// 分账规则明细
//				postForm += "<input type=\"hidden\" name=\"pRuleDetails\" value=\""
//						+ RuleDetails + "\" />";
//				//
//				postForm += "<input type=\"hidden\" name=\"pDetailsSign\" value=\""
//						+ DetailsSign + "\" />";
//				//
//				postForm += "<input type=\"hidden\" name=\"pSignMD5\" value=\""
//						+ strMd5 + "\" />";
//				postForm += "</form>";
//				// 自动提交该表单到测试网关
//				postForm += "<script type=\"text/javascript\" language=\"javascript\">function submitForm(){document.forms[0].submit();} setTimeout(submitForm,250);</script>";
//				response.getWriter().write(postForm);
//				// response.getWriter().close();
//			}
//		} catch (Exception e) {
//
//		}
//		return item.toJson();
//	}

	/**
	 * 环迅支付
	 * @param groupnum
	 * @param token
	 * @param ch
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ipspay", produces = "text/html;charset=UTF-8")
	public String ipsceshi(String groupnum, String ch,
			HttpServletResponse response, HttpServletRequest request) {
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
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			// String token="377de9a6-6694-45cc-bad9-14ba71cf8e06";
			sessionUser = SessionState.GetCurrentUser(token);
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
				gateWayReqBody += "<Merchanturl>"+IpsConfig.getIpsConfig().wapmerchanturl+"</Merchanturl>";
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
				gateWayReqBody += "<ServerUrl>"+IpsConfig.getIpsConfig().wapserverUrl+"</ServerUrl>";
				// 订单有效期 以小时为单位，必须是整数
				gateWayReqBody += "<BillEXP>"+IpsConfig.getIpsConfig().billEXP+"</BillEXP>";
				// 商品名称
				gateWayReqBody += "<GoodsName>iphone6s</GoodsName>";
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
				 System.out.println(postForm);
				// 自动提交该表单
				postForm += "<script>document.forms[0].submit();</script>";
				//postForm += "<script type=\"text/javascript\" language=\"javascript\">function submitForm(){document.forms[0].submit();} setTimeout(submitForm,250);</script>";
				response.getWriter().write(postForm);
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "IPS支付请求异常! 异常信息:{0}", e,
					"IpsWap/ipspay");
		}
		return item.toJson();
	}

	@RequestMapping("/paytest")
	public ModelAndView paytest() {
		return new ModelAndView("wap/ipstest/B2CPay");
	}

	// @RequestMapping("/payreturn")
	// public ModelAndView payreturn() {
	// return new ModelAndView("wap/ipstest/OrderReturn");
	// }
	/**
	 * 环迅支付成功异步回调
	 * @param request
	 * @return
	 */
	@RequestMapping("/payreturn")
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
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "orderPay.html", "/api/wap/IpsWap/payreturn", "：环迅支付成功异步回调");
								}
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"环迅支付成功异步回调 异常信息:",
	    								e, "/api/wap/IpsWap/payreturn");
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
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "ips支付处理订单异常! 异常信息:{0}", e,
					"IpsWap/payreturn");
		}
		return item.toJson();
	}
	
	@RequestMapping(value = "/groupipspay", produces = "text/html;charset=UTF-8")
	public String groupipspay(String groupnum, String ch,
			HttpServletResponse response, HttpServletRequest request) {
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
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			// String token="377de9a6-6694-45cc-bad9-14ba71cf8e06";
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			Groupbuyorder orderlist=groupBuyOrderService.getByOrderCode(groupnum);
			if (orderlist == null) {
				item.setCode(-403);
				item.setDesc("组订单号错误");
				LogHandle.error(LogType.Order,
						MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
				return item.toJson();
			}else{
				Double ss =orderlist.getPayprice().doubleValue(); 

				// 订单的MD5摘要明文(报文主体-body)
				String gateWayReqBody = "<body>";
				// 订单号
				gateWayReqBody += "<MerBillNo>"+groupnum+"</MerBillNo>";
				// 订单金额
				gateWayReqBody += "<Amount>"+ss+"</Amount>";
				// 订单日期
				gateWayReqBody += "<Date>"
						+ new java.text.SimpleDateFormat("yyyyMMdd")
								.format(orderlist.getCreatetime()) + "</Date>";
				// 币种（人民币 156）
				gateWayReqBody += "<CurrencyType>" + IpsConfig.getIpsConfig().currency
						+ "</CurrencyType>";
				// 支付方式 默认借记卡01 信用卡02 IPS账号支付03
				gateWayReqBody += "<GatewayType>" + IpsConfig.getIpsConfig().gateway
						+ "</GatewayType>";
				// 语言
				gateWayReqBody += "<Lang>"+IpsConfig.getIpsConfig().lang+"</Lang>";
				// 支付结果成功返回的商户URL
				gateWayReqBody += "<Merchanturl>"+IpsConfig.getIpsConfig().wapmerchanturl+"</Merchanturl>";
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
				gateWayReqBody += "<ServerUrl>"+IpsConfig.getIpsConfig().wapserverUrl_tg+"</ServerUrl>";
				// 订单有效期 以小时为单位，必须是整数
				gateWayReqBody += "<BillEXP>"+IpsConfig.getIpsConfig().billEXP+"</BillEXP>";
				// 商品名称
				gateWayReqBody += "<GoodsName>iphone6s</GoodsName>";
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
				 System.out.println(postForm);
				// 自动提交该表单
				postForm += "<script>document.forms[0].submit();</script>";
				//postForm += "<script type=\"text/javascript\" language=\"javascript\">function submitForm(){document.forms[0].submit();} setTimeout(submitForm,250);</script>";
				response.getWriter().write(postForm);
			}
			
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "IPS支付请求异常! 异常信息:{0}", e,
					"IpsWap/ipspay");
		}
		return item.toJson();
	}

	
	/**
	 * 环迅支付成功异步回调
	 * @param request
	 * @return
	 */
	@RequestMapping("/paygroupreturn")
	public String paygroupreturn(HttpServletRequest request) {
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
				//String userip = GetIpAddresss.getIp();
				int commitre=groupBuyOrderService.updateFK(merBillNo, StringUtilsEX.ToDouble(Amount), IpsTradeNo);
				if (commitre == 1) {
					item.setCode(0);
					item.setDesc("验证成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user=SessionState.GetCurrentUser();
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "orderPay.html", "/api/wap/IpsWap/paygroupreturn", "环迅支付成功异步回调");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"环迅支付成功异步回调 异常信息:",
	    								e, "/api/wap/IpsWap/paygroupreturn");
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
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "ips支付处理订单异常! 异常信息:{0}", e,
					"IpsWap/payreturn");
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
    public ModelAndView paysuccess(HttpServletRequest request) {
    	String orderprice="";
    	try{
    		String returnXml = request.getParameter("paymentResult");
			Map<String, Object> map = XmlUtils.parserValueForXml(returnXml);
			if (map.get("RspCode").equals("000000")) {
				//订单金额
				orderprice=map.get("Amount").toString(); 
				System.out.println(orderprice);
			}
    		
    	} catch (Exception e) {
			LogHandle.error(LogType.wap, "ips支付处理订单异常! 异常信息:{0}", e,
					"IpsWap/payreturn");
		}
    	ModelAndView view = new ModelAndView();
    	 view.addObject("price",orderprice);  
    	view.setViewName("wap/Order/OrderSuccess");
        return view;
    }
}

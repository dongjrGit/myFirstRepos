package com.yinlian.api.wap.controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PayTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.alipay.AlipayConfig;
import com.yinlian.alipay.AlipayNotify;
import com.yinlian.alipay.AlipayService;
import com.yinlian.app.tenpay.ClientResponseHandler;
import com.yinlian.app.tenpay.ConstantUtil;
import com.yinlian.app.tenpay.RequestHandler;
import com.yinlian.app.tenpay.ResponseHandler;
import com.yinlian.app.tenpay.TenpayHttpClient;
import com.yinlian.tenpay.MatrixToImageWriter;
import com.yinlian.tenpay.PaymentUtil;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/wap/alipay")
public class AlipayWapController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private GroupBuyOrderService  groupBuyOrderService;
	@Autowired
	private OrderStatusService orderStatusService;
    @Autowired
    private OperaterecordsService operaterecordsService ;

    
	@RequestMapping(value = "/paybygroupnum", produces = "text/html;charset=UTF-8")
	public @ResponseBody String alipayByGroupOrder(String groupnum, String ch,
			HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		String sHtmlText = "";
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
				LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
				return item.toJson();
			}
			if (orderlist != null && orderlist.size() > 0) {
				Double ss = orderlist.stream()
						.mapToDouble(x -> x.getActualpay().doubleValue()).sum();
				// // 服务器异步通知页面路径
				// String notify_url =
				// "http://yinlink.f3322.net:9001/wap/alipay/notifyurl";
				// // 需http://格式的完整路径，不能加?id=123这类自定义参数
				//
				// // 页面跳转同步通知页面路径
				// String return_url =
				// "http://yinlink.f3322.net:9001/wap/IpsWap/paysuccess";
				// // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

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
				//String descString = "订单" + groupnum + "在线支付".getBytes("ISO-8859-1");
				//String body = new String(descString.getBytes("ISO-8859-1"),"UTF-8");
				// 商品展示地址
				// String show_url = new
				// String("http://yinlink.f3322.net:9001/platform/login".getBytes("ISO-8859-1"),"UTF-8");
				// 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

				// ////////////////////////////////////////////////////////////////////////////////

				// 把请求参数打包成数组
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
				sParaTemp.put("partner", AlipayConfig.getAlipayConfig().partner);
				sParaTemp.put("seller_id", AlipayConfig.getAlipayConfig().seller_email);
				sParaTemp.put("_input_charset", AlipayConfig.getAlipayConfig().input_charset);
				sParaTemp.put("payment_type", AlipayConfig.getAlipayConfig().payment_type);
				sParaTemp.put("notify_url", AlipayConfig.getAlipayConfig().wapnotify_url);
				sParaTemp.put("return_url", AlipayConfig.getAlipayConfig().wapreturn_url);
				sParaTemp.put("out_trade_no", out_trade_no);
				sParaTemp.put("subject", subject);
				sParaTemp.put("total_fee", total_fee);
				sParaTemp.put("show_url", "");

				// 建立请求
				sHtmlText = AlipayService.buildRequest(sParaTemp, "get", "确认");
				response.getWriter().write(sHtmlText);
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.debug(LogType.wap,
					MessageFormat.format("支付订单异常! 异常信息:{0}", e.toString()),
					"order/alipayByGroupOrder");
		}
		return item.toJson();
	}

	
	
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
							out_trade_no, userip, trade_no, total_fee,PayTypeEnum.支付宝支付.getValue(), item);
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
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "orderPay.html", "/api/wap/alipay/notifyurl", "支付宝支付");
									}
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"支付宝支付 异常信息:",
		    								e, "/api/wap/alipay/notifyurl");
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
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "支付宝支付处理订单异常! 异常信息:{0}", e,
					"order/notifyurl");
		}
		return item.toJson();
	}
	
	@RequestMapping(value = "/grouppaybygroupnum", produces = "text/html;charset=UTF-8")
	public @ResponseBody String grouppaybygroupnum(String groupnum, String ch,
			HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		String sHtmlText = "";
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
			//List<Orders> orderlist = orderService.getOrderByGroupCode(groupnum);
			Groupbuyorder orderlist=groupBuyOrderService.getByOrderCode(groupnum);
			if (orderlist == null) {
				item.setCode(-403);
				item.setDesc("组订单号错误");
				LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
				return item.toJson();
			}else{
				Double ss =orderlist.getPayprice().doubleValue(); 
				// // 服务器异步通知页面路径
				// String notify_url =
				// "http://yinlink.f3322.net:9001/wap/alipay/notifyurl";
				// // 需http://格式的完整路径，不能加?id=123这类自定义参数
				//
				// // 页面跳转同步通知页面路径
				// String return_url =
				// "http://yinlink.f3322.net:9001/wap/IpsWap/paysuccess";
				// // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

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
				sParaTemp.put("notify_url", AlipayConfig.getAlipayConfig().wapnotify_url_tg);
				sParaTemp.put("return_url", AlipayConfig.getAlipayConfig().wapreturn_url_tg);
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
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.debug(LogType.wap,
					MessageFormat.format("支付订单异常! 异常信息:{0}", e.toString()),
					"order/alipayByGroupOrder");
		}
		return item.toJson();
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/notifyurl_tg", produces = "text/html;charset=UTF-8")
	public String notifyurltg(HttpServletRequest request) {
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
					//String userip = GetIpAddresss.getIp();
					/*int commitre = orderStatusService.updatePayforAlipayCode(
							out_trade_no, userip, trade_no, total_fee, item);*/
					int commitre=groupBuyOrderService.updateFK(out_trade_no, StringUtilsEX.ToDouble(total_fee), trade_no);
					if (commitre == 1) {
						item.setCode(0);
						item.setDesc("验证成功");
						ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {
									SessionUser user=SessionState.GetCurrentUser();
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "orderPay.html", "/api/wap/alipay/notifyurl_tg", "支付宝验证");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"支付宝验证 异常信息:",
		    								e, "/api/wap/alipay/notifyurl_tg");
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
			LogHandle.error(LogType.wap, "支付宝支付处理订单异常! 异常信息:{0}", e,
					"order/notifyurl_tg");
		}
		return item.toJson();
	}

	@RequestMapping("/wxindex")
	public ModelAndView wxindex() {
		return new ModelAndView("wap/wxpay/index");
	}

	@RequestMapping("/wxjsapi")
	public ModelAndView wxjsapi() {
		return new ModelAndView("wap/wxpay/jsapi");
	}

	@RequestMapping("/wxnative")
	public ModelAndView wxnative() {
		// return new ModelAndView("wap/wxpay/native");
		return new ModelAndView("wap/wxpay/nativenew");
	}

	@RequestMapping("/wxnativecall")
	public ModelAndView wxnativecall() {
		return new ModelAndView("wap/wxpay/nativecall");
	}

	/**
	 * 微信支付异步回调地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/wxnotifyurl", produces = "text/html;charset=UTF-8")
	public String wechat_notifyurl(HttpServletRequest request,
			HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		try {
			// ---------------------------------------------------------
			// 财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
			// ---------------------------------------------------------
			// 商户号
			String partner = ConstantUtil.PARTNER; // "1900000109";

			// 密钥
			String key = ConstantUtil.PARTNER_KEY; // "8934e7d15453e97507ef794cf7b0519d";

			// 创建支付应答对象
			ResponseHandler resHandler = new ResponseHandler(request, response);
			resHandler.setKey(key);

			// 判断签名
			if (resHandler.isTenpaySign()) {

				// 通知id
				String notify_id = resHandler.getParameter("notify_id");

				// 创建请求对象
				RequestHandler queryReq = new RequestHandler(null, null);
				// 通信对象
				TenpayHttpClient httpClient = new TenpayHttpClient();
				// 应答对象
				ClientResponseHandler queryRes = new ClientResponseHandler();

				// 通过通知ID查询，确保通知来至财付通
				queryReq.init();
				queryReq.setKey(key);
				queryReq.setGateUrl(ConstantUtil.GATEURL);
				queryReq.setParameter("partner", partner);
				queryReq.setParameter("notify_id", notify_id);

				// 通信对象
				httpClient.setTimeOut(5);
				// 设置请求内容
				httpClient.setReqContent(queryReq.getRequestURL());
				System.out.println("queryReq:" + queryReq.getRequestURL());
				// 后台调用
				if (httpClient.call()) {
					// 设置结果参数
					queryRes.setContent(httpClient.getResContent());
					System.out
							.println("queryRes:" + httpClient.getResContent());
					queryRes.setKey(key);

					// 获取返回参数
					String retcode = queryRes.getParameter("retcode");
					String trade_state = queryRes.getParameter("trade_state");

					String trade_mode = queryRes.getParameter("trade_mode");

					// 判断签名及结果
					if (queryRes.isTenpaySign() && "0".equals(retcode)
							&& "0".equals(trade_state)
							&& "1".equals(trade_mode)) {
						System.out.println("订单查询成功");
						// 取结果参数做业务处理
						String out_trade_no = queryRes
								.getParameter("out_trade_no");

						System.out.println("out_trade_no:"
								+ queryRes.getParameter("out_trade_no")
								+ " transaction_id:"
								+ queryRes.getParameter("transaction_id"));
						System.out.println("trade_state:"
								+ queryRes.getParameter("trade_state")
								+ " total_fee:"
								+ queryRes.getParameter("total_fee"));
						System.out.println("transaction_id:"
								+ queryRes.getParameter("transaction_id"));
						String trade_no = queryRes
								.getParameter("transaction_id");
						String total_fee = queryRes.getParameter("total_fee");
						// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
						System.out.println("discount:"
								+ queryRes.getParameter("discount")
								+ " time_end:"
								+ queryRes.getParameter("time_end"));
						// ------------------------------
						// 处理业务开始
						// ------------------------------
						String userip = GetIpAddresss.getIp();
						int commitre = orderStatusService
								.updatePayforAlipayCode(out_trade_no, userip,
										trade_no, total_fee,PayTypeEnum.微信支付.getValue(), item);
						if (commitre == 1) {
							item.setCode(0);
							item.setDesc("验证成功");
							ExecutorService executorService=Executors.newCachedThreadPool();
							executorService.execute(new Runnable() {
								@Override
								public void run() {
									try {
										SessionUser user=SessionState.GetCurrentUser();
										operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "orderPay.html", "/api/wap/alipay/wxnotifyurl", "微信验证");
									} catch (Exception e) {
										LogHandle.error(LogType.OperateRecords,"微信支付 异常信息:",
			    								e, "/api/wap/alipay/wxnotifyurl");
									}
								}
							});
						} else {
							item.setCode(-200);
							item.setDesc("验证失败");
						}
						// 处理数据库逻辑
						// 注意交易单不要重复处理
						// 注意判断返回金额

						// ------------------------------
						// 处理业务完毕
						// ------------------------------
						resHandler.sendToCFT("Success");
					} else {
						item.setCode(-201);
						item.setDesc("查询验证签名失败或业务错误,retcode:"
								+ queryRes.getParameter("retcode") + " retmsg:"
								+ queryRes.getParameter("retmsg"));
						// 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
						System.out.println("查询验证签名失败或业务错误");
						System.out.println("retcode:"
								+ queryRes.getParameter("retcode") + " retmsg:"
								+ queryRes.getParameter("retmsg"));
					}

				} else {
					item.setCode(-202);
					item.setDesc("后台调用通信失败,错误代码："
							+ httpClient.getResponseCode() + ",错误信息："
							+ httpClient.getErrInfo());
					System.out.println("后台调用通信失败");
					System.out.println(httpClient.getResponseCode());
					System.out.println(httpClient.getErrInfo());
					// 有可能因为网络原因，请求已经处理，但未收到应答。
				}
			} else {
				item.setCode(-203);
				item.setDesc("通知签名验证失败");
				System.out.println("通知签名验证失败");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "微信支付处理订单异常! 异常信息:{0}", e,
					"order/wxnotifyurl");
		}
		return item.toJson();
	}

	/**
	 * 创建二维码
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/createQRCode",produces = "text/html;charset=UTF-8")
	public String createQRCode(String groupnum,String ch,String token, HttpServletResponse response) {
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
			SessionUser sessionUser = SessionState.GetCurrentUser(token);
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
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取微信支付二维码异常! 异常信息:{0}", e,
					"order/createQRCode");
		}
		return item.toJson();
	}

	@SuppressWarnings("unused")
	@RequestMapping("/wechatPayNotify")
	public void wechatPayNotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String /*ret = "",*/returnurl="";
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

			Map<String, String> retMap = new HashMap<String, String>();

			String returnCode = map.get("return_code");
			if ("SUCCESS".equals(returnCode)) {
				String resultCode = map.get("result_code");
				if ("SUCCESS".equals(resultCode)) {
					// //下面为业务逻辑处理：如果支付成功，则修改该用户的付费状态，更新付费时间。
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
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "orderPay.html", "/api/wap/alipay/wechatPayNotify", "订单支付处理");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"订单支付处理 异常信息:",
		    								e, "/api/wap/alipay/wechatPayNotify");
								}
							}
						});
					}
//					retMap.put("return_code", "SUCCESS");
//					retMap.put("return_msg", "OK");

				} else {
					item.setCode(-200);
					item.setDesc("支付失败");
					//String errCode = map.get("err_code");
//					retMap.put("return_code", returnCode);
//					retMap.put("return_msg", resultCode);
				}
			} else {
				item.setCode(-201);
				String returnMsg = map.get("return_msg");
				item.setDesc("支付通信失败！" + returnMsg);
				retMap.put("return_code", returnCode);
				retMap.put("return_msg", returnMsg);
				returnurl="wap/wxpay/wx_fail";
			}
//			ret = XmlUtils.mapToXml(retMap);
//			response.getWriter().print(ret);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "微信支付异常! 异常信息:{0}", e,
					"order/wechatPayNotify");
		}
	}
}

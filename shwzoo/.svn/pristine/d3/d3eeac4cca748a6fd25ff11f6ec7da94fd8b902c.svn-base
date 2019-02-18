package com.yinlian.api.app.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.PayTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.alipay.AlipayConfig;
import com.yinlian.alipay.AlipayService;
import com.yinlian.app.alipay.AppAlipayConfig;
import com.yinlian.tenpay.PaymentUtil;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.XmlUtils;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/app/onlinepay")
public class AlipayController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderStatusService orderStatusService;

	@Autowired
	private GroupBuyOrderService  groupBuyOrderService;
	
	@RequestMapping(value = "/paybygroupnum", produces = "text/html;charset=UTF-8")
	public String alipayByGroupOrder(String groupnum,String token, String ch,HttpServletResponse response ) {
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
		            LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
		            return item.toJson();
		        }	       
			   if (orderlist != null && orderlist.size()>0) {		
				   Double ss = orderlist.stream().mapToDouble(x -> x.getActualpay().doubleValue()).sum();
//				// 服务器异步通知页面路径
//				String notify_url = "http://yinlink.f3322.net:9001/app/api/onlinepay/notify_url";
//				// 需http://格式的完整路径，不能加?id=123这类自定义参数
//
//				// 页面跳转同步通知页面路径
//				String return_url = sucesssUrl;
//				// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

				// 商户订单号
				String out_trade_no = new String(groupnum
						.getBytes("ISO-8859-1"), "UTF-8");
				// 商户网站订单系统中唯一订单号，必填

				// 订单名称
				String subject = new String(groupnum.getBytes("ISO-8859-1"),
						"UTF-8");
				// 必填

				// 付款金额
				String total_fee = new String(ss.toString()
						.getBytes("ISO-8859-1"), "UTF-8");
				// 必填

				// 订单描述
				String descString = "订单" + groupnum
						+ "在线支付".getBytes("ISO-8859-1");
				String body = new String(descString.getBytes("ISO-8859-1"), "UTF-8");
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
				response.getWriter().write(sHtmlText);
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.debug(LogType.Api,
					MessageFormat.format("支付订单异常! 异常信息:{0}", e.toString()),
					"order/alipayByGroupOrder");
		}
		 return item.toJson();
	}
	
	@RequestMapping("/payindex")
	public ModelAndView payindex() {
		return new ModelAndView("app/alipay/payindex");
	}
	@RequestMapping("/alipayapi")
	public ModelAndView alipayapi() {
		return new ModelAndView("app/alipay/alipayapi");
	}
	
	@RequestMapping("/wxindex")
	public ModelAndView wxindex() {
		return new ModelAndView("app/wx/index");
	}
	@RequestMapping("/wxreq")
	public ModelAndView payRequest() {
		return new ModelAndView("app/wx/payRequest");
	}
	@RequestMapping("/wxnotify")
	public ModelAndView payNotifyUrl() {
		return new ModelAndView("app/wx/payNotifyUrl");
	}
	/**
	 * app微信支付请求接口
	 */
	@RequestMapping(value="/wechatpay",produces = "text/html;charset=UTF-8")
	public String wechatpay(String groupnum,String ch,String token,String type, HttpServletResponse response) {
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
			
			Double ss=0.00;
			int ordertype=0;
			if(StringUtilsEX.ToInt(type)>0){
				ordertype=1;
			Groupbuyorder gorder=groupBuyOrderService.getByOrderCode(groupnum);
			if (gorder == null) {
				item.setCode(-403);
				item.setDesc("团购订单号错误");
				LogHandle.error(LogType.Order,
						MessageFormat.format("团购订单号错误，订单号：{0}", groupnum));
				return item.toJson();
			}
			ss=BigDecimal.valueOf(gorder.getPayprice().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}else{
				List<Orders> orderlist = orderService.getOrderByGroupCode(groupnum);
				if (orderlist == null) {
					item.setCode(-403);
					item.setDesc("组订单号错误");
					LogHandle.error(LogType.Order,
							MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
					return item.toJson();
				}
				if (orderlist != null && orderlist.size() > 0) {
					 ss = BigDecimal.valueOf(orderlist.stream().mapToDouble(x -> x.getActualpay()
							 .doubleValue()).sum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					}
				else{
					item.setCode(-404);
					item.setDesc("根据组订单号未能检索到数据");
					return item.toJson();
				}
			}
			String userip = GetIpAddresss.getIp();
			String [] errordesc=new String [1];
			// 调统一下单API
			String packString = PaymentUtil.getPackage(groupnum, ss.toString(), userip, "订单在线支付",ordertype,errordesc);
			if(packString==""){
				item.setCode(-405);
				item.setDesc("获取微信支付参数错误："+errordesc[0]);
				return item.toJson();
			}
			item.setData("{"+packString+"}");
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
	/**
	 * 微信支付回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/wechatPayNotify")
	public void wechatPayNotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ret = "";
		
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
							out_trade_no, userip, transaction_id, total_fee,PayTypeEnum.微信支付.getValue(),
							item);
					
					if (commitre == 1) {
						item.setCode(0);
						item.setDesc("订单支付处理成功");
					}
					retMap.put("return_code", "SUCCESS");
					retMap.put("return_msg", "OK");
					wxindex();
					
					// log.info("支付成功！out_trade_no:" + outTradeNo +
					// ", result_code:" + resultCode);
				} else {
					item.setCode(-200);
					item.setDesc("支付失败");
					//String errCode = map.get("err_code");
					// log.error("支付失败！out_trade_no:" + outTradeNo +
					// ",result_code:" + resultCode + ", err_code:" + errCode);
					retMap.put("return_code", returnCode);
					retMap.put("return_msg", resultCode);
				}
			} else {
				item.setCode(-201);
				String returnMsg = map.get("return_msg");
				item.setDesc("支付通信失败！" + returnMsg);
				retMap.put("return_code", returnCode);
				retMap.put("return_msg", returnMsg);

			}
			ret = XmlUtils.mapToXml(retMap);
			response.getWriter().print(ret);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "微信支付回调异常! 异常信息:{0}", e,
					"order/wechatPayNotify");
		}

	}
	/**
	 * 微信支付回调 -团购订单
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/wechatGroupPayNotify")
	public void wechatPayNotify_tg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ret = "";
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
					//String userip = GetIpAddresss.getIp();
					int commitre = groupBuyOrderService.updateFK(
							out_trade_no, Double.valueOf(total_fee),transaction_id);
					if (commitre == 1) {
						item.setCode(0);
						item.setDesc("订单支付处理成功");
					}
					retMap.put("return_code", "SUCCESS");
					retMap.put("return_msg", "OK");
					wxindex();
					// log.info("支付成功！out_trade_no:" + outTradeNo +
					// ", result_code:" + resultCode);
				} else {
					item.setCode(-200);
					item.setDesc("支付失败");
					//String errCode = map.get("err_code");
					// log.error("支付失败！out_trade_no:" + outTradeNo +
					// ",result_code:" + resultCode + ", err_code:" + errCode);
					retMap.put("return_code", returnCode);
					retMap.put("return_msg", resultCode);
				}
			} else {
				item.setCode(-201);
				String returnMsg = map.get("return_msg");
				item.setDesc("支付通信失败！" + returnMsg);
				retMap.put("return_code", returnCode);
				retMap.put("return_msg", returnMsg);

			}
			ret = XmlUtils.mapToXml(retMap);
			response.getWriter().print(ret);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "微信支付回调 -团购订单异常! 异常信息:{0}", e,
					"order/wechatGroupPayNotify");
		}

	}
	
	@RequestMapping(value = "/alipayreturn", produces = "text/html;charset=UTF-8")
	public String alipayreturn(String tradeno, String ss,HttpServletResponse response ) {
		ReusltItem item = new ReusltItem();
		String sHtmlText = "";
		try {
				// 付款金额
				String total_fee = new String(ss.toString()
						.getBytes("ISO-8859-1"), "UTF-8");
				// 必填
				
				String reson = new String("协商退款".getBytes("ISO-8859-1"), "UTF-8");
				// 订单描述
				String refunddata = tradeno+"^" + total_fee
						+ "^"+reson;
				//refunddata = new String(refunddata.getBytes("ISO-8859-1"), "UTF-8");

				// ////////////////////////////////////////////////////////////////////////////////

				// 把请求参数打包成数组
				Map<String, String> sParaTemp = new HashMap<String, String>();
//				sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
//				sParaTemp.put("partner", AppAlipayConfig.getAlipayConfig().partner);
//				sParaTemp.put("_input_charset", AppAlipayConfig.getAlipayConfig().input_charset);
//				sParaTemp.put("sign_type", AppAlipayConfig.getAlipayConfig().alipay_websign_type);
//				sParaTemp.put("notify_url", AlipayConfig.getAlipayConfig().notify_url);
//				sParaTemp.put("return_url", AlipayConfig.getAlipayConfig().return_url);
//				sParaTemp.put("seller_email", AppAlipayConfig.getAlipayConfig().partner);
//				sParaTemp.put("seller_user_id", AppAlipayConfig.getAlipayConfig().partner);
//				sParaTemp.put("refund_date", DateUtil.dateConvert(new Date()));
//				sParaTemp.put("batch_no", ProductNumUtil.getBatchNum());
//				sParaTemp.put("batch_num", "1");
//				sParaTemp.put("detail_data", refunddata);
				sParaTemp.put("service", "refund_fastpay_by_platform_nopwd");
				sParaTemp.put("partner", AppAlipayConfig.getAlipayConfig().partner);
				sParaTemp.put("_input_charset", AppAlipayConfig.getAlipayConfig().input_charset);
				sParaTemp.put("sign_type", AppAlipayConfig.getAlipayConfig().alipay_websign_type);
				sParaTemp.put("notify_url", AlipayConfig.getAlipayConfig().notify_url);
				sParaTemp.put("dback_notify_url", AlipayConfig.getAlipayConfig().return_url);
				sParaTemp.put("refund_date", DateUtil.dateConvert(new Date()));
				sParaTemp.put("batch_no", ProductNumUtil.getBatchNum());
				sParaTemp.put("batch_num", "1");
				sParaTemp.put("detail_data", refunddata);
				// 建立请求
			    sHtmlText = AlipayService.buildRequest(sParaTemp, "get", "确认");
				response.getWriter().write(sHtmlText);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.debug(LogType.Api,
					MessageFormat.format("支付订单异常! 异常信息:{0}", e.toString()),
					"order/alipayByGroupOrder");
		}
		 return item.toJson();
	}
	
//	@RequestMapping(value = "/wxreturn", produces = "text/html;charset=UTF-8")
//	public String wxreturn(String groupcode,String tradeno, String ss, String rs) {
//		ReusltItem item = new ReusltItem();
//		try {
//			 String  refund=PaymentUtil.refund(groupcode,ss,rs,tradeno);
//			 item.setData(refund);
//
//		} catch (Exception e) {
//			if (DebugConfig.BLUETOOTH_DEBUG) {
//				item.setDesc(e.toString());
//			} else {
//				item.setDesc("系统错误！");
//			}
//			item.setCode(-900);
//			LogHandle.debug(LogType.Api,
//					MessageFormat.format("支付订单异常! 异常信息:{0}", e.toString()),
//					"order/wxreturn");
//		}
//		 return item.toJson();
//	}
	
}

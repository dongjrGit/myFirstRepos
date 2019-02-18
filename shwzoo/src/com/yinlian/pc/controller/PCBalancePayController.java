package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.FinanceType;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.Extended.LogType;
import com.yinlian.alipay.AlipayConfig;
import com.yinlian.alipay.AlipayNotify;
import com.yinlian.alipay.AlipayService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.GroupBuyService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.service.UserFinanceService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionState;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/balancepay")
public class PCBalancePayController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private GroupBuyService  groupBuyService;
	
	@Autowired
	private GroupBuyOrderService  groupBuyOrderService;

	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	private OperaterecordsService operaterecordsService; 
	
	@Autowired
	private UserFinanceService userFinanceService;
	
	

	@RequestMapping(value = "/paybygroupnum", produces = "text/html;charset=UTF-8")
	public String paybygroupnum(String price,
			HttpServletResponse response) {
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
			userfinance.setType(CapitalChange_Type.充值.getValue());
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
			LogHandle.debug(LogType.pc,
					MessageFormat.format("余额充值异常! 异常信息:{0}", e.toString()),
					"order/paybygroupnum");
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
					int commitre = orderStatusService.addBalance(out_trade_no,trade_no, total_fee, item);
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
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("支付宝支付处理订单错误：" + e.toString());
			}else {
				item.setDesc("支付宝支付处理订单错误");
			}			
			LogHandle.error(LogType.pc, "支付宝支付处理订单异常! 异常信息:{0}", e,
					"/balance/notifyurl");
		}
		return item.toJson();
	}
	
}

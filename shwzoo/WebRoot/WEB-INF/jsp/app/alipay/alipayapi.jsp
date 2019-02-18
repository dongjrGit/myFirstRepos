<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yinlian.alipay.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝即时到账交易接口</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////

		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = "http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp";
		//需http://格式的完整路径，不能加?id=123这类自定义参数

		//页面跳转同步通知页面路径
		String return_url = "http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		//商户订单号
		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//商户网站订单系统中唯一订单号，必填

		//订单名称
		String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		//必填

		//付款金额
		String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
		//必填

		//订单描述

		String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		//商品展示地址
		String show_url = new String(request.getParameter("WIDshow_url").getBytes("ISO-8859-1"),"UTF-8");
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数

		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.getAlipayConfig().partner);
        sParaTemp.put("seller_id", AlipayConfig.getAlipayConfig().seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.getAlipayConfig().input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		
		//建立请求
		String sHtmlText = AlipayService.buildRequest(sParaTemp,"get","确认");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
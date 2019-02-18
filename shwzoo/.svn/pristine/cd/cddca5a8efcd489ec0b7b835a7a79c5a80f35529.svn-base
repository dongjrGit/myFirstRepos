<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.concurrent.ExecutionException"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="org.apache.commons.httpclient.DefaultHttpMethodRetryHandler"%>
<%@ page import="org.apache.commons.httpclient.HttpClient"%>
<%@ page import="org.apache.commons.httpclient.HttpStatus"%>
<%@ page import="org.apache.commons.httpclient.MultiThreadedHttpConnectionManager"%>
<%@ page import="org.apache.commons.httpclient.cookie.CookiePolicy"%>
<%@ page import="org.apache.commons.httpclient.methods.PostMethod"%>
<%@ page import="org.apache.commons.httpclient.methods.StringRequestEntity"%>
<%@ page import="org.apache.commons.httpclient.params.HttpClientParams"%>
<%@ page import="org.apache.commons.httpclient.params.HttpMethodParams"%>
<%@ page import="org.springframework.stereotype.Controller"%>
<%@ page import="org.springframework.web.bind.annotation.RequestMapping"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.yinlian.wssc.web.util.DEndecryptUtil"%>
<%
	String requestType = request.getParameter("requestType");
	String uUID = request.getParameter("uUID");
	String comId = request.getParameter("comId");
	String comIP = request.getParameter("comIP");
	String from = request.getParameter("from");
	String systemName = request.getParameter("systemName");
	String systemPassword = request.getParameter("systemPassword");
	String sendTime = request.getParameter("sendTime");
	String asyn = request.getParameter("asyn");
	String returnUrl = request.getParameter("returnUrl");
	String signed = request.getParameter("signed");
	String itSystem = request.getParameter("itSystem");
	// body
	String orderNo = request.getParameter("orderNo");
	String payMoney = request.getParameter("payMoney");
	String beanNumber = request.getParameter("beanNumber");
	String voucherCode = request.getParameter("voucherCode");
	String receivable = request.getParameter("receivable");
	String merchantName = request.getParameter("merchantName");
	String userName = request.getParameter("userName");
	String payType = request.getParameter("payType");
	String checkCode = request.getParameter("checkCode");
	String orderType = request.getParameter("orderType");
	// 地址为外网测试地址，生产地址待提供
	String url = "http://58.30.232.151:8081/jkpay/orderToPay/toPay";
	StringBuffer xml = new StringBuffer();
	xml.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?><Package><Header>");
	// 交易类型:支付(P100001)
	xml.append("<RequestType>" + requestType + "</RequestType>");
	// 唯一编码:能够唯一标识一次交互，返回报文中需要携带。主要用于双方调试跟踪，随机生成，不进行任何保存和验证
	xml.append("<UUID>" + uUID + "</UUID>");
	// 经开平台代码:经开平台接入业务系统的id代码
	xml.append("<ComId>" + comId + "</ComId>");
	// 经开平台台接入业务系统的ip地址:经开平台接入业务系统的主机ip地址
	xml.append("<ComIP>" + comIP + "</ComIP>");
	// 发送方编号:支撑系统:ufc 经开平台:同ComId
	xml.append("<From>" + from + "</From>");
	// 业务系统用户名:每个对接业务系统都会分配一个对接用户，这里是分配的用户名
	xml.append("<SystemName>" + systemName + "</SystemName>");
	// 业务系统客户密码:每个对接业务系统都会分配一个对接用户，这里是分配的用户密码
	xml.append("<SystemPassword>" + systemPassword + "</SystemPassword>");
	// 发送时间:yyyy-MM-dd HH:mm:ss
	xml.append("<SendTime>" + sendTime + "</SendTime>");
	// 是否异步接口:缺省为异步
	xml.append("<Asyn>" + asyn + "</Asyn>");
	// 回调Url:对于异步返回接口，经开平台需填写此地址接收异步回调信号
	xml.append("<ReturnUrl>" + returnUrl + "</ReturnUrl>");
	// 签名串:暂不使用
	xml.append("<Signed>" + signed + "</Signed>");
	// 所属系统:0-Pc端、1-移动端、10-ios系统、11-Android系统、101-ios手机端系统、102-ios
	// pad端系统、111-Android手机端系统、112-Anrdoid pad端系统
	xml.append("<ItSystem>" + itSystem + "</ItSystem>");
	xml.append("</Header><Body><PayInfo>");
	// 订单号:要支付订单的平台订单号
	xml.append("<OrderNo>" + orderNo + "</OrderNo>");
	// 支付金额:买家实际支付的金额（不包括经彩豆和代金券的金额）
	xml.append("<PayMoney>" + payMoney + "</PayMoney>");
	// 经彩豆数量:使用到时填写
	xml.append("<BeanNumber>" + beanNumber + "</BeanNumber>");
	// 代金券代码:可填写多条使用“,”分割
	xml.append("<VoucherCode>" + voucherCode + "</VoucherCode>");
	// 卖家应收入总金额
	xml.append("<Receivable>" + receivable + "</Receivable>");
	// 卖家用户名
	xml.append("<MerchantName>" + merchantName + "</MerchantName>");
	// 买家用户名
	xml.append("<UserName>" + userName + "</UserName>");
	// 支付方式：01-余额支付、02-快捷支付、03-浦发银行 、04-支付宝 、05-现金 、06-电汇、07-支票
	// 、08-内部账、09-POS刷卡
	xml.append("<PayType>" + payType + "</PayType>");
	// 支票/电汇号码：如果支付类型为支票/电汇 必须填写该号码
	xml.append("<CheckCode>" + checkCode + "</CheckCode>");
	// 订单类型：订单类型：0-支付、1-充值
	xml.append("<OrderType>" + orderType + "</OrderType>");
	xml.append("</PayInfo></Body></Package>");
	
	// 加密工具类
	DEndecryptUtil dUtil = DEndecryptUtil.get_instances();
	// 将报文进行加密
	String deXml = dUtil.encrypt(xml.toString());
	// post调用service接口
	String postwebservice = dUtil.post(url, deXml);

	out.print(postwebservice);
%>
</body>
</html>
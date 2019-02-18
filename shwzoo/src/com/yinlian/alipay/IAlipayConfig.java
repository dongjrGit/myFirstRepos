package com.yinlian.alipay;

public interface IAlipayConfig {
	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		// 合作身份者ID，以2088开头由16位纯数字组成的字符串
		  String partner = "2088121066720423";;

		// 收款支付宝账号
		  String seller_email = partner;
		// 商户的私钥
		  String key = "jstnsatn5ty8pi0r4fetvy720lv6brd9";

		// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

		// 字符编码格式 目前支持 gbk 或 utf-8
		  String input_charset = "utf-8";

		// 签名方式 不需修改
		  String sign_type = "MD5";
		// 支付类型 必填，不能修改
		  String payment_type = "1";

		// 防钓鱼时间戳
		  String anti_phishing_key = "";
		// 若要使用请调用类文件submit中的query_timestamp函数

		// 客户端的IP地址
		  String exter_invoke_ip = "";
		// 非局域网的外网IP地址，如：221.0.0.1

		// 服务器异步通知页面路径
		  String notify_url = "http://yinlink.f3322.net:9001/wap/alipay/notifyurl";
		// 页面跳转同步通知页面路径
		  String return_url = "http://yinlink.f3322.net:9001/wap/IpsWap/paysuccess";
		  void init();
}

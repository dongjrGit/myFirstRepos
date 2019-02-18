package com.yinlian.app.alipay;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppAlipayConfig {
	
	 Properties pro = new Properties();  
	 
	 private static AppAlipayConfig _AppAlipayConfig = null;// 声明一个AlipayConfig类的引用
	 public static AppAlipayConfig getAlipayConfig() {// 实例化引用
	        if (_AppAlipayConfig == null) {
	        	_AppAlipayConfig = new AppAlipayConfig();
	        }
	        return _AppAlipayConfig;
	    }
	 public AppAlipayConfig(){
		 try {
			    InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("alipay.properties");
				pro.load(inStream);				
				partner=pro.getProperty("alipay_partner"); 
				private_key=pro.getProperty("alipay_private_key");
				ali_public_key=pro.getProperty("alipay_ali_public_key");
				input_charset=pro.getProperty("alipay_charset");
				sign_type=pro.getProperty("alipay_appsign_type");
				appid=pro.getProperty("alipay_appid");
				notifyurl=pro.getProperty("alipay_appnotify_url");
				public_key=pro.getProperty("alipay_public_key");
				notifyurl_tg=pro.getProperty("alipay_appnotify_url_tg");
				alipay_key=pro.getProperty("alipay_key");
				alipay_websign_type=pro.getProperty("alipay_websign_type");
				alipay_app_public_key=pro.getProperty("alipay_app_public_key");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     
	}
	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public String partner = "";
	// 商户的私钥
	public String private_key = "";

	// 支付宝的公钥，无需修改该值
	public String ali_public_key = "";
	public String public_key = "";
	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 字符编码格式 目前支持 gbk 或 utf-8
	public String input_charset = "";

	// 签名方式 不需修改
	public String sign_type = "";
	
	public String appid = "";
	
	public String notifyurl="";
	public String notifyurl_tg="";
	public String alipay_key="";
	public String alipay_websign_type="";
	
	public String alipay_app_public_key="";
	
}

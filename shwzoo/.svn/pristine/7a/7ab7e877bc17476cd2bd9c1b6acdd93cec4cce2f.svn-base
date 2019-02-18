package com.yinlian.tenpay;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

	Properties pro = new Properties();  
	 
	 private static ConfigUtil _ConfigUtil = null;// 声明一个ConfigUtil类的引用
	 public static ConfigUtil getConfigUtil() {// 实例化引用
	        if (_ConfigUtil == null) {
	        	_ConfigUtil = new ConfigUtil();
	        }
	        return _ConfigUtil;
	    }
	 public ConfigUtil(){
		 try {
			    InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("alipay.properties");
				pro.load(inStream);				
				appid=pro.getProperty("wechat_appid");
				appsecret=pro.getProperty("wechat_appsecret");
				partner=pro.getProperty("wechat_partner");
				partnerkey=pro.getProperty("wechat_partnerkey");
				openId=pro.getProperty("wechat_openId");
				notifyurl=pro.getProperty("wechat_notifyurl");
				appnotifyurl=pro.getProperty("wechat_appnotifyurl");
				appnotifyurl_tg=pro.getProperty("wechat_appnotifyurl_tg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     
	}
	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
		public String appid = "";
		public String appsecret = "";
		public String partner = "";
		//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
		public String partnerkey = "";
		//openId 是微信用户针对公众号的标识，授权的部分这里不解释
		public String openId = "";
		//微信支付成功后通知地址 必须要求80端口并且地址不能带参数
		public String notifyurl = "";	
		public String appnotifyurl = "";	
		public String appnotifyurl_tg= "";	
}

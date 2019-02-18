package com.yinlian.alipay;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */
public class AlipayConfig  {

	  Properties pro = new Properties();  
	  private static AlipayConfig _AlipayConfig = null;// 声明一个AlipayConfig类的引用
	   
	    public static AlipayConfig getAlipayConfig() {// 实例化引用
	        if (_AlipayConfig == null) {
	        	_AlipayConfig = new AlipayConfig();
	        }
	        return _AlipayConfig;
	    }
	  public AlipayConfig(){
	  try {
		    InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("alipay.properties");
			pro.load(inStream);
			
			partner=pro.getProperty("alipay_partner"); 
			seller_email=pro.getProperty("alipay_sellerid");
			key=pro.getProperty("alipay_key");
			input_charset=pro.getProperty("alipay_charset");
			sign_type=pro.getProperty("alipay_websign_type");
			payment_type=pro.getProperty("alipay_payment_type");
			anti_phishing_key=pro.getProperty("alipay_anti_phishing_key");
			exter_invoke_ip=pro.getProperty("alipay_exter_invoke_ip");
			notify_url=pro.getProperty("alipay_notify_url");
			return_url=pro.getProperty("alipay_return_url");
			fail_url=pro.getProperty("alipay_fail_url");
			wapnotify_url=pro.getProperty("alipay_wapnotify_url");
			wapreturn_url=pro.getProperty("alipay_wapreturn_url");
			wapnotify_url_tg=pro.getProperty("alipay_wapnotify_url_tg");
			wapreturn_url_tg=pro.getProperty("alipay_wapreturn_url_tg");
			alipay_notify_url_cz=pro.getProperty("alipay_notify_url_cz");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	  }
      

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public  String partner = "";

	// 收款支付宝账号
	public  String seller_email = "";
	// 商户的私钥
	public  String key = "";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 字符编码格式 目前支持 gbk 或 utf-8
	public  String input_charset = "";

	// 签名方式 不需修改
	public  String sign_type = "";
	// 支付类型 必填，不能修改
	public  String payment_type = "";

	// 防钓鱼时间戳
	public  String anti_phishing_key = "";
	// 若要使用请调用类文件submit中的query_timestamp函数

	// 客户端的IP地址
	public   String exter_invoke_ip = "";
	// 非局域网的外网IP地址，如：221.0.0.1

	// 服务器异步通知页面路径
	public   String notify_url = "";
	// 页面跳转同步通知页面路径
	public   String return_url = "";
	
	public   String fail_url = "";
	
	// 服务器异步通知页面路径
	public   String wapnotify_url = "";
		// 页面跳转同步通知页面路径
	public   String wapreturn_url = "";
	// 服务器异步通知页面路径
	public   String wapnotify_url_tg = "";
			// 页面跳转同步通知页面路径
	public  String wapreturn_url_tg = "";
	
	public  String alipay_notify_url_cz = "";
}

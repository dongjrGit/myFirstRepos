package com.yinlian.api.wap.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IpsConfig {

	Properties pro = new Properties();  
	  private static IpsConfig _IpsConfig = null;// 声明一个AlipayConfig类的引用
	   
	    public static IpsConfig getIpsConfig() {// 实例化引用
	        if (_IpsConfig == null) {
	        	_IpsConfig = new IpsConfig();
	        }
	        return _IpsConfig;
	    }
	  public IpsConfig(){
	  try {
		    InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("alipay.properties");
			pro.load(inStream);
			
			merCode=pro.getProperty("ips_merCode"); 
			account=pro.getProperty("ips_account");
			merName=pro.getProperty("ips_merName");
			md5key=pro.getProperty("ips_md5key");
			rsakey=pro.getProperty("ips_rsakey");
			actionUrl=pro.getProperty("ips_actionUrl");
			currency=pro.getProperty("ips_currency");
			gateway=pro.getProperty("ips_gateway");
			lang=pro.getProperty("ips_lang");
			orderEncode=pro.getProperty("ips_orderEncode");
			retEncode=pro.getProperty("ips_retEncode");
			merchanturl=pro.getProperty("ips_merchanturl");
			serverUrl=pro.getProperty("ips_serverUrl");
			retType=pro.getProperty("ips_retType");
			isCredit=pro.getProperty("ips_isCredit");
			bankCode=pro.getProperty("ips_bankCode");
			productType=pro.getProperty("ips_productType");
			version=pro.getProperty("ips_version");
			billEXP=pro.getProperty("ips_billEXP");
			msgId=pro.getProperty("ips_msgId");
			appmerchanturl=pro.getProperty("ips_appmerchanturl");
			appserverUrl=pro.getProperty("ips_appserverUrl");
			wapmerchanturl=pro.getProperty("ips_wapmerchanturl");
			wapserverUrl=pro.getProperty("ips_wapserverUrl");
			wapserverUrl_tg=pro.getProperty("ips_wapserverUrl_tg");
			pcordersuccessurl=pro.getProperty("ips_pcordersuccessurl");
			pcordererrorurl=pro.getProperty("ips_pcordererrorurl");
			failUrl="";
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	  }
    
	// 商户号
	public String merCode = "";

	public String account = "";
	
	public String merName = "";
	// 商户的md5的证书
	public String md5key = "";

	// 商户的rsa公钥（暂时用不到）
	public String rsakey = "";

	// 正式环境提交地址
	public String actionUrl = "";
	//币种 
	public String currency="";    //人民币
	
	// 支付方式 默认借记卡01 信用卡02 IPS账号支付03
	public String gateway="";
	
	// 语言
	public String lang="";
	
	// 订单支付接口加密方式 MD5
	public String orderEncode="";
	
	// 交易返回接口加密方式 md5
	public String retEncode="";
	
	public String merchanturl="";
	public String failUrl="";
	public String serverUrl="";
	
	public String appmerchanturl="";
	public String appfailUrl="";
	public String appserverUrl="";
	
	public String wapmerchanturl="";
	public String wapfailUrl="";
	public String wapserverUrl="";
	public String wapserverUrl_tg="";
	public String pcordersuccessurl="";
	public String pcordererrorurl="";
	
	public String retType="";
	public String isCredit="";
	public String bankCode="";
	public String productType="";
	public String version="";
	public String billEXP="";
	public String msgId="";
}

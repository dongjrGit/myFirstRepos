package com.yinlian.tenpay;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.yinlian.Extended.LogType;
import com.yinlian.alipay.AlipayConfig;
import com.yinlian.tenpay.GetWxOrderno;
import com.yinlian.tenpay.RequestHandler;
import com.yinlian.tenpay.Sha1Util;
import com.yinlian.tenpay.TenpayUtil;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

public class PaymentUtil {

	/**
	 * 获取微信扫码支付二维码连接
	 */
	public static String getCodeurl(String ordercode,String totalmoney,String ipaddress,String desc,String[] errordesc){
		
		// 1 参数
		// 订单号
		String orderId = ordercode;
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(totalmoney);
		
		// 订单生成的机器 IP
		String spbill_create_ip = ipaddress;
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = ConfigUtil.getConfigUtil().appnotifyurl;
		String trade_type = "NATIVE";

		//公众号
		String app_id =ConfigUtil.getConfigUtil().appid;
		// 商户号
		String mch_id =ConfigUtil.getConfigUtil().partner;
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body = desc;

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", app_id);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(app_id, ConfigUtil.getConfigUtil().appsecret,
				ConfigUtil.getConfigUtil().partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + app_id + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "</xml>";
		String code_url = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		errordesc[0]="";
		code_url = new GetWxOrderno().getCodeUrl(createOrderURL, xml,errordesc);
		System.out.println("code_url----------------"+code_url);
		return code_url;
	}
	
	
//	/**
//	 * 获取请求预支付id报文
//	 * @return
//	 */
//	@SuppressWarnings("static-access")
//	public static String getPackage(WxPayDto tpWxPayDto) {
//		
////		String openId = tpWxPayDto.getOpenId();
//		// 1 参数
//		// 订单号
//		String orderId = tpWxPayDto.getOrderId();
//		// 附加数据 原样返回
//		String attach = "";
//		// 总金额以分为单位，不带小数点
//		String totalFee = getMoney(tpWxPayDto.getTotalFee());
//		
//		// 订单生成的机器 IP
//		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
//		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
//		String notify_url = notifyurl;
//		String trade_type = "APP";
//
//		// ---必须参数
//		// 商户号
//		String mch_id = partner;
//		// 随机字符串
//		String nonce_str = getNonceStr();
//
//		// 商品描述根据情况修改
//		String body = tpWxPayDto.getBody();
//
//		// 商户订单号
//		String out_trade_no = orderId;
//
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("appid", appid);
//		packageParams.put("mch_id", mch_id);
//		packageParams.put("nonce_str", nonce_str);
//		packageParams.put("body", body);
//		packageParams.put("attach", attach);
//		packageParams.put("out_trade_no", out_trade_no);
//
//		// 这里写的金额为1 分到时修改
//		packageParams.put("total_fee", totalFee);
//		packageParams.put("spbill_create_ip", spbill_create_ip);
//		packageParams.put("notify_url", notify_url);
//
//		packageParams.put("trade_type", trade_type);
////		packageParams.put("openid", openId);
//
//		RequestHandler reqHandler = new RequestHandler(null, null);
//		reqHandler.init(appid, appsecret, partnerkey);
//
//		String sign = reqHandler.createSign(packageParams);
//		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
//				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
//				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
//				+ "<body><![CDATA[" + body + "]]></body>" 
//				+ "<out_trade_no>" + out_trade_no
//				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
//				+ "<total_fee>" + totalFee + "</total_fee>"
//				+ "<spbill_create_ip>" + spbill_create_ip
//				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
//				+ "</notify_url>" + "<trade_type>" + trade_type
//				+ "</trade_type>" + "<openid>" + openId + "</openid>"
//				+ "</xml>";
//		String prepay_id = "";
//		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//		
//		
//		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
//
//		System.out.println("获取到的预支付ID：" + prepay_id);
//		
//		
//		//获取prepay_id后，拼接最后请求支付所需要的package
//		
//		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
//		String timestamp = Sha1Util.getTimeStamp();
//		String packages = "prepay_id="+prepay_id;
//		finalpackage.put("appId", appid);  
//		finalpackage.put("timeStamp", timestamp);  
//		finalpackage.put("nonceStr", nonce_str);  
//		finalpackage.put("package", packages);  
//		finalpackage.put("signType", "MD5");
//		//要签名
//		String finalsign = reqHandler.createSign(finalpackage);
//		
//		String finaPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timestamp
//		+ "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
//		+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
//		+ finalsign + "\"";
//
//		System.out.println("V3 jsApi package:"+finaPackage);
//		return finaPackage;
//	}

	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}

	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString(); 
	}

	/**
	 * 获取请求预支付id报文
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getPackage(String ordercode,String totalmoney,String ipaddress,
			String desc,Integer type,String[] errordesc) {
		
		// 订单号
		String orderId = ordercode;
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(totalmoney);
		
		// 订单生成的机器 IP
		String spbill_create_ip = ipaddress;
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = ConfigUtil.getConfigUtil().appnotifyurl;
		if(type==1){
			notify_url= ConfigUtil.getConfigUtil().appnotifyurl_tg;
		}
		String trade_type = "APP";

		// ---必须参数
		// 公众号
		String app_id = ConfigUtil.getConfigUtil().appid;
		// 商户号
		String mch_id = ConfigUtil.getConfigUtil().partner;
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body = desc;

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", app_id);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(app_id, ConfigUtil.getConfigUtil().appsecret, ConfigUtil.getConfigUtil().partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + app_id + "</appid>" 
				+ "<attach>" + attach + "</attach>"
				+ "<body><![CDATA[" + body + "]]></body>" 
	        	+ "<mch_id>"+ mch_id + "</mch_id>" 
				+ "<nonce_str>" + nonce_str+ "</nonce_str>" 
				+ "<notify_url>" + notify_url+ "</notify_url>" 
				+ "<out_trade_no>" + out_trade_no+ "</out_trade_no>" 
				+ "<spbill_create_ip>" + spbill_create_ip+ "</spbill_create_ip>" 			
				+ "<total_fee>" + totalFee + "</total_fee>"				
				+ "<trade_type>" + trade_type+ "</trade_type>" 
				+ "<sign>" + sign + "</sign>"
				+ "</xml>";	
		String prepay_id = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		errordesc[0]="";
		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml,errordesc);

		System.out.println("获取到的预支付ID：" + prepay_id);
		String finaPackage ="";
		if(prepay_id==""){
			return finaPackage;
		}
		
		//获取prepay_id后，拼接最后请求支付所需要的package
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		finalpackage.put("appid", app_id);  
		finalpackage.put("timestamp", timestamp);  
		finalpackage.put("noncestr", nonce_str);  
		finalpackage.put("package", "Sign=WXPay");  
		finalpackage.put("partnerid", mch_id);
		finalpackage.put("prepayid", prepay_id);
		//要签名
		String finalsign = reqHandler.createSign(finalpackage);
		
	    finaPackage = "\"appid\":\"" + app_id 
		+ "\",\"timestamp\":\"" + timestamp
		+ "\",\"noncestr\":\"" + nonce_str 
		+ "\",\"package\":\"Sign=WXPay"
		+ "\",\"prepayid\" : \"" + prepay_id 
		+ "\",\"partnerid\":\""+ mch_id
		+ "\",\"sign\":\""+ finalsign + "\"";

		System.out.println("App package:"+finaPackage);
		return finaPackage;
	}
	
	public static void main(String[] args) {
		String[] strings = null;
		PaymentUtil.refund("172972875851062", "30", "30", "4200000027201710240059065065", strings);
	}
	
	public static int refund(String ordercode,String totalmoney,String retmoney,
			String tradeno,String[] errordesc){
		
		 int status = -1;
		// ---必须参数
		// 公众号
		String app_id = ConfigUtil.getConfigUtil().appid;
		// 商户号
		String mch_id = ConfigUtil.getConfigUtil().partner;
		// 随机字符串
		String nonce_str = getNonceStr();
	    SortedMap<String, String> packageParams = new TreeMap<String, String>();
	    packageParams.put("appid", app_id);//应用id
	    packageParams.put("mch_id", mch_id);//商户号
	    packageParams.put("nonce_str",  nonce_str);//随机字符串
	    packageParams.put("out_trade_no", ordercode);//订单号
	    packageParams.put("transaction_id", tradeno);//订单号
	    packageParams.put("out_refund_no", ProductNumUtil.getBatchNum());//退款单号
	    packageParams.put("total_fee",getMoney(totalmoney));//订单总金额
	    packageParams.put("refund_fee",getMoney(retmoney));//退款总金额
	    packageParams.put("op_user_id", mch_id);//商户号
	    
	    RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(app_id, ConfigUtil.getConfigUtil().appsecret, ConfigUtil.getConfigUtil().partnerkey);
	    String sign = reqHandler.createSign(packageParams);
	    String result = "FAIL";
	    String msg = "";
	    System.out.println("--sign--="+sign);

	    String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	    String xml = null;
	    try {
	        xml = ClientCustomSSL.createXML(packageParams,sign.toUpperCase());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    System.out.println("--xml--="+xml);
	    String retur=null;
	    try {
	        retur = ClientCustomSSL.doRefund(createOrderURL, xml);
	        System.out.print(retur);
	        LogHandle.info(LogType.Order, "微信退款请求:" + retur, "/OrderTK");
	    if (!StringUtilsEX.isEmpty(retur)) {
	        Map map = GetWxOrderno.doXMLParse(retur);

	        String returnCode = (String) map.get("return_code");
	        if(returnCode.equals("SUCCESS")){
	            result = "SUCCESS";
	            msg = "OK";
	           
	            String resultCode = (String)map.get("result_code");
	            if(resultCode.equals("SUCCESS")){
	                status = 1;
	            }else{
	            	errordesc[0]=(String)map.get("result_code");
	            }
//	            if(status == 1) {
//	                String outtradeno = (String) map.get("out_trade_no"); // 订单号
//
//	//业务操作
//	            }
	        }  if (result.equals("FAIL")) {
	            msg = (String)map.get("return_msg");
	            errordesc[0]=msg;
	            //logger.info(" 微信退款失败 refundfail msg="+msg);
	        }
	    }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return status;
	}
	
	public static boolean checkSign(Map<String, String> map) {

        try {

        	 String signFromAPIResponse = map.get("sign").toString();

 	        if (signFromAPIResponse == "" || signFromAPIResponse == null) {

 	            //System.out.println("API返回的数据签名数据不存在，有可能被第三方篡改!!!");

 	            return false;

 	        }
 	        //System.out.println("服务器回包里面的签名是:" + signFromAPIResponse);

 	        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名

 	        map.put("sign", "");

 	        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较

 	        String signForAPIResponse = getSign(map);

 	        if (!signForAPIResponse.equals(signFromAPIResponse)) {

 	            //签名验不过，表示这个API返回的数据有可能已经被篡改了

 	            //System.out.println("API返回的数据签名验证不通过，有可能被第三方篡改!!! signForAPIResponse生成的签名为" + signForAPIResponse);
 	           LogHandle.info 

(LogType.Order, "微信签名验证:" + signForAPIResponse+"," +signFromAPIResponse, "/OrderTK");
 	            return false;

 	        }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        //System.out.println("恭喜，API返回的数据签名验证通过!!!");

        return true;

    }



    public static String getSign(Map<String, String> map) {
    	// 公众号
		String app_id = ConfigUtil.getConfigUtil().appid;
		
        SortedMap<String, String> signParams = new TreeMap<String, String>();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            signParams.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        signParams.remove("sign");
    	RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(app_id, ConfigUtil.getConfigUtil().appsecret, ConfigUtil.getConfigUtil().partnerkey);

		String sign = reqHandler.createSign(signParams);
        return sign;
    }
	
//    public static void main(String[] args) {
//    	String[] errordesc=new String[1];
//    	refund("172926866981891","180.02","180.02",
//    			"4200000025201710198953773324",errordesc);
//    }
}

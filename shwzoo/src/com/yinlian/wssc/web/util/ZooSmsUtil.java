package com.yinlian.wssc.web.util;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.fluent.Request;

import com.alibaba.fastjson.JSON;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.dto.SmsResultDto;
import com.yl.soft.log.LogHandle;

public class ZooSmsUtil {
	
	
	/**
	 * 发送短息
	 * 
	 * @param phone
	 * @param message
	 */
	public static String sendMessage(String phone, String message) {
		String info = "-1";
		try {
			String username =ConfigUtil.get_instances().getSmsName();
			String password = ConfigUtil.get_instances().getSmsPwd();
			String url = ConfigUtil.get_instances().getSmsUrl();
			
			// 短信相关的必须参数
			String mobile = phone;
			String extendCode = "";
			//String message = "测试明码验证，不安全【XX公司】";
			long externalId = 0l; // 自定义消息编码，可以忽略
			int dataCoding = 8; // UNICODE 编码
			int transferEncoding = 3; // URLEncode+UTF8
			int responseFormat = 2; // 返回格式为 Json 格式

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String timestamp = sdf.format(new Date() );		
			// 计算密码摘要
			String timestamppwd = sdf.format(new Date() );
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update( username.getBytes("utf-8") );
			md5.update( password.getBytes("utf-8") );
			md5.update( timestamppwd.getBytes("utf-8") );
			md5.update( message.getBytes("utf-8") );
			//password = Base64.encodeBase64String( md5.digest() );
			password = Base64.encodeBase64String( md5.digest() ).replaceAll("\r\n", "");
			
			StringBuilder sb = new StringBuilder();
			sb.append(url);
			sb.append("un=").append( username );
			sb.append("&pw=").append( URLEncoder.encode(password,"utf-8") );
			sb.append("&ts=").append( timestamp );
			sb.append("&da=").append( mobile );
			sb.append("&sa=").append( extendCode );
			sb.append("&ex=").append( externalId );
			sb.append("&dc=").append( dataCoding );
			sb.append("&tf=").append( transferEncoding );
			sb.append("&rf=").append( responseFormat );
			sb.append("&sm=").append( URLEncoder.encode(message, "utf-8") );
			String req = sb.toString();
			System.out.println("request: " + req);
			// 发送短信
			String result = Request.Get( req ).connectTimeout(60000).socketTimeout(60000)
			.execute().returnContent().asString();
			System.out.println( "response: " + result );
			SmsResultDto dto=JSON.parseObject(result,SmsResultDto.class);
			if (dto!=null && dto.getSuccess().equals("true")) {
				info = "1";
				System.out.println("发送短信成功");
			} else {
				System.out.println("发送短信失败");
			}

		} catch (Exception e) {
			LogHandle.error(LogType.Sys, e, "/sendMessage");
		}
		return info.trim();
	}
public static void main(String[] args) throws Exception {
		
	    String mobile = "13965086942";
	    String message = "这是一条测试短信,返回 Json";
    	//sendMessage(mobile,message);
    	System.out.println(sendMessage(mobile,message));
		
	}
}

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test post message to phone.
 * 
 * @author Administrator
 * @version $Id: SmsUtil.java, v 0.1 2016年2月23日 下午2:23:00 Administrator Exp $
 */
public class SmsUtil {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String phone = "15656912120";
		String message = "【经开商城】：您好，你本次的验证码：" + ProductNumUtil.getRandNum();

		sendMessage(phone, message);

	}

	/**
	 * 发送短息
	 * 
	 * @param phone
	 * @param message
	 */
	public static String sendMessage(String phone, String message) {
		String info = "-1";
		try {
			// Properties properties = PropertiesUtil
			// .getProperties("cfg.properties");
			// String webservice_path =
			// properties.getProperty("webservice_path");
			// String namespace_URI = properties.getProperty("namespace_URI");
			// String localPart = properties.getProperty("localPart");
			// String loginId = properties.getProperty("loginId");
			// String passWord = properties.getProperty("passWord");
			// TODO： 原支撑系统 要修改
//			message="【因联商城】您好,您的本次验证码为"+message;
			// 发送短信
			info = doGet(ConfigUtil.get_instances().getSmsUrl() + "&phones=" + phone + "&msg="
					+ URLEncoder.encode(message, "gbk"), "gbk", null);
			if (info.trim().equals("0")) {
				System.out.println("发送短信成功");
			} else {
				System.out.println("发送短信失败");
			}

		} catch (Exception e) {
			logger.error("发送短息异常e：", e.getMessage());
		}
		return info.trim();
	}

	public static String register(String xml) {

		return "";
	}

	/**
	 * 通过get来访问url
	 * 
	 * @param src
	 *            需要访问的地址
	 * @param outputencode
	 *            获取输出时的编码
	 * @param headers
	 *            需要添加的访问头信息
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String src, String outputencode, HashMap<String, String> headers) throws Exception {
		StringBuffer result = new StringBuffer();
		URL url = new URL(src);
		URLConnection connection = url.openConnection();
		BufferedReader in = null;
		try {
			if (headers != null) {
				Iterator<String> iterators = headers.keySet().iterator();
				while (iterators.hasNext()) {
					String key = iterators.next();
					connection.setRequestProperty(key, headers.get(key));
				}
			}
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), outputencode));
			String line;
			while ((line = in.readLine()) != null) {
				result.append("\n" + line);
			}
			return result.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (in != null) {
				in.close();
			}
		}

	}
}

package com.yinlian.tenpay;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.yinlian.wssc.web.util.PropertiesUtil;

public class ClientCustomSSL {

	public static String doRefund(String url, String data) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		Properties properties = PropertiesUtil.getProperties("cfg.properties");
		FileInputStream is = new FileInputStream(new File(
				properties.getProperty("certificatepath")));
		try {
			keyStore.load(is, ConfigUtil.getConfigUtil().partner.toCharArray());// 这里写密码..默认是你的MCHID
		} finally {
			is.close();
		}

		// Trust own CA and all self-signed certs
		@SuppressWarnings("deprecation")
		SSLContext sslcontext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, ConfigUtil.getConfigUtil().partner.toCharArray())// 这里也是写密码的
				.build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		
		try {
			HttpPost httpost = new HttpPost(url); // 设置响应头信息
			httpost.addHeader("Connection", "keep-alive");
			httpost.addHeader("Accept", "*/*");
			httpost.addHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			httpost.addHeader("Host", "api.mch.weixin.qq.com");
			httpost.addHeader("X-Requested-With", "XMLHttpRequest");
			httpost.addHeader("Cache-Control", "max-age=0");
			httpost.addHeader("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			httpost.setEntity(new StringEntity(data, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpost);
			try {
				HttpEntity entity = response.getEntity();

				String jsonStr = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				EntityUtils.consume(entity);
				return jsonStr;
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

	public static String getMoney(String amount) {
		if (amount == null) {
			return "";
		}
		// 金额转化为分为单位
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "") + "00");
		}
		return amLong.toString();
	}

	public static String signMd5_2(Map<String, String> packageParams,
			String merchantKey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + merchantKey);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}

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

	public static String createXML(Map<String, String> map, String s)
			throws Exception {
		StringBuilder xml = new StringBuilder("<xml>");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();

			xml.append("<").append(key).append(">").append(val).append("</")
					.append(key).append(">");
		}
		xml.append("<").append("sign").append(">").append(s).append("</")
				.append("sign").append(">");
		xml.append("</xml>");
		String xml1 = xml.toString();
		return xml1;
	}
}

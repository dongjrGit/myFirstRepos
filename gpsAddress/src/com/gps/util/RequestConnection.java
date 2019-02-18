package com.gps.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.gps.servlet.ServletGetAddress;

public class RequestConnection {
	private static Logger logger = Logger.getLogger(ServletGetAddress.class);
	/**
	 * 发送请求
	 */
	public static String sendGet(String url) {
		String responseText = "";
		BufferedReader read = null;
		HttpURLConnection connection = null;
		try {
			String line;
			URL realurl = new URL(url);

			connection = (HttpURLConnection) realurl.openConnection();
			connection.setConnectTimeout(20 * 1000);
			connection.setReadTimeout(30 * 1000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.connect();

			read = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));

			StringBuffer result = new StringBuffer();
			while ((line = read.readLine()) != null) {
				result.append(line);
				result.append("\n");
			}
			responseText = result.toString();

			read.close();
			
			connection.disconnect();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			Constants.resultError = "网络超时";
			System.out.println("网络超时");
			logger.error("网络超时:" + url);
		} catch (IOException e) {
			e.printStackTrace();
			if (read == null) {
				Constants.resultError = "没返回任何数据";
				System.out.println("没返回任何数据。");
			}
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return responseText;
	}
}

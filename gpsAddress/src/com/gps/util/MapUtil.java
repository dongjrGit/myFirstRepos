package com.gps.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class MapUtil {
	private static Logger logger = Logger.getLogger(MapUtil.class);

	/**
	 * 获取所有外访人员到目的地的距离
	 * 
	 * @param from
	 * @param toJson
	 * @return
	 */
	public static String getTrueDistance(String from, JSONObject toJson) {
		// 获取目的地经纬度
		String location = toJson.optString("result");
		toJson = JSONObject.fromObject(location);
		location = toJson.optString("location");
		toJson = JSONObject.fromObject(location);
		logger.info(toJson.optString("lat") + "---" + toJson.optString("lng"));
		String baiduUrl = "http://api.map.baidu.com/routematrix/v2/driving?output=json"
				+ "&origins="
				+ from
				+ "&destinations="
				+ toJson.optString("lat")
				+ ","
				+ toJson.optString("lng")
				+ "&ak=giMx1O1rvK23iqmtv24Ozjm3A8DOk9es";
		return MapUtil.connectUrl(baiduUrl);
	}

	/**
	 * 根据位置获取经纬度
	 * 
	 * @param to
	 * @return
	 */
	public static JSONObject getLatLonByAddressName(String to) {
		String destinationName = "http://api.map.baidu.com/geocoder/v2/?address="
				+ to + "&output=json&ak=giMx1O1rvK23iqmtv24Ozjm3A8DOk9es";
		return JSONObject.fromObject(MapUtil.connectUrl(destinationName));
	}

	/**
	 * 访问百度接口
	 * 
	 * @param aimUrl
	 * @return
	 * @throws IOException
	 */
	public static String connectUrl(String aimUrl) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			URL url = new URL(aimUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setConnectTimeout(10 * 1000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) "
							+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			conn.connect();
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			logger.info("访问百度接口失败",e);
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 获取外访人员中距离最近的人员
	 * 
	 * @param minDistance
	 * @return
	 */
	public static String getMinDistance(Map<String, Double> minDistance) {
		List<Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(
				minDistance.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
					Map.Entry<String, Double> o2) {
				return (int) (o1.getValue() - o2.getValue());
			}
		});
		return list.get(0).getKey();
	}
}

package com.gps.servlet;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.gps.VO.GeograComp;
import com.gps.VO.GeograHome;
import com.gps.VO.Latlons;
import com.gps.entity.Address;
import com.gps.util.MapUtil;

public class DistanceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(DistanceServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		InputStream in = null;
		ByteArrayOutputStream bos = null;
		try {
			request.setCharacterEncoding("UTF-8");
			in = request.getInputStream();
			bos = new ByteArrayOutputStream();
			int len = 0;
			while ((len = in.read()) != -1) {
				bos.write(len);
			}
			byte[] re = bos.toByteArray();
			String requestData = new String(re, "UTF-8").trim();

			logger.info(requestData);

			if (null != requestData && !"".equals(requestData)) {
				JSONObject jsonObject = JSONObject.fromObject(requestData);
				Map<String, Object> resultMap = doLatAddChagnge(jsonObject);
				if (resultMap != null) {
					JSONObject latObject = JSONObject.fromObject(resultMap);
					result = latObject.toString();
				}
				logger.info(result);
			}
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("服务异常");
		}
	}

	public Map<String, Object> doLatAddChagnge(JSONObject objJson) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Address> address = null;
		GeograComp comp = null;
		GeograHome home = null;
		long startTime = System.currentTimeMillis();
		try {
			if (null != objJson && objJson.size() > 0) {
				// 获取请求中包含的所有外访人员经纬度
				JSONObject requestjsonObject = objJson.optJSONObject("GeographicDtos");
				JSONArray workers = requestjsonObject.getJSONArray("workers");
				int workerCount = workers.size();
				StringBuffer fromlatlng = new StringBuffer();// 所有外访员工位置串
				if (workerCount > 1) {
					fromlatlng.append(workers.getJSONObject(0).getString("latlng"));
					for (int i = 1; i < workers.size(); i++) {
						JSONObject worker = workers.getJSONObject(i);
						fromlatlng.append("|").append(worker.getString("latlng"));
					}
				} else if (workerCount == 1) {
					fromlatlng.append(workers.getJSONObject(0).getString("latlng"));
				} else {
					map.put("success", "0");
					map.put("info", "外访人员参数有误");
					return map;
				}

				JSONObject jsonObject = objJson.optJSONObject("GeographicDtos");
				if (jsonObject != null && jsonObject.size() > 0) {
					JSONObject geographicComp = jsonObject.getJSONObject("geographicComp");
					JSONObject geographicHome = jsonObject.getJSONObject("geographicHome");
					String compAddress = geographicComp.getString("address");
					String homeAddress = geographicHome.getString("address");
					Latlons workerLatLon = new Latlons();// 外访人员位置经纬度
					address = new ArrayList<Address>();
					// 单位访
					if (!"".equals(compAddress) && null != compAddress) {
						Map<String, Double> minDistance = new TreeMap<String, Double>();
						JSONObject latlonJson = MapUtil.getLatLonByAddressName(compAddress);
						// 单位位置经纬度
						Latlons unitLatLon = new Latlons(latlonJson
								.getJSONObject("result")
								.getJSONObject("location").getString("lat"),
								latlonJson.getJSONObject("result")
										.getJSONObject("location")
										.getString("lng"));
						// 获取每个外访人员单位访距离
						String resultStr = MapUtil.getTrueDistance(fromlatlng.toString(), latlonJson);
						System.out.println(resultStr);
						JSONObject resultJson = JSONObject.fromObject(resultStr);
						JSONArray resultArray = resultJson.getJSONArray("result");
						for (int j = 0; j < workers.size(); j++) {
							minDistance.put(workers.getJSONObject(j).toString(),
									Double.parseDouble(resultArray
											.getJSONObject(j)
											.getJSONObject("distance")
											.getString("value")));
						}
						// 获取离得最近的外访人员位置
						String workerInfo = MapUtil.getMinDistance(minDistance);
						JSONObject latlng = JSONObject.fromObject(workerInfo);
						String[] latlngs = latlng.getString("latlng").split(",");
						// 获取离得最近的外访人员的经纬度
						workerLatLon = new Latlons(latlngs[0], latlngs[1]);
						comp = new GeograComp(latlng.getString("userAccessId"),
								unitLatLon, minDistance.get(workerInfo)
										.toString(), workerLatLon, "C",
								compAddress);
						address.add(comp);
					}
					// 家访
					if (!"".equals(homeAddress) && null != homeAddress) {
						Map<String, Double> minDistance = new TreeMap<String, Double>();
						JSONObject latlonJson = MapUtil.getLatLonByAddressName(homeAddress);
						// 单位位置经纬度
						Latlons unitLatLon = new Latlons(latlonJson
								.getJSONObject("result")
								.getJSONObject("location").getString("lat"),
								latlonJson.getJSONObject("result")
										.getJSONObject("location")
										.getString("lng"));
						// 获取每个外访人员单位访距离
						String resultStr = MapUtil.getTrueDistance(fromlatlng.toString(), latlonJson);
						System.out.println(resultStr);
						JSONObject resultJson = JSONObject.fromObject(resultStr);
						JSONArray resultArray = resultJson.getJSONArray("result");
						for (int j = 0; j < workers.size(); j++) {
							minDistance.put(workers.getJSONObject(j).toString(),
									Double.parseDouble(resultArray
											.getJSONObject(j)
											.getJSONObject("distance")
											.getString("value")));
						}
						// 获取离得最近的外访人员位置
						String workerInfo = MapUtil.getMinDistance(minDistance);
						JSONObject latlng = JSONObject.fromObject(workerInfo);
						String[] latlngs = latlng.getString("latlng").split(",");
						// 获取离得最近的外访人员的经纬度
						workerLatLon = new Latlons(latlngs[0], latlngs[1]);
						home = new GeograHome(JSONObject.fromObject(workerInfo)
								.getString("userAccessId"), unitLatLon,
								minDistance.get(workerInfo).toString(),
								workerLatLon, "H", homeAddress);
						address.add(home);
					}
				}
				long endTime = System.currentTimeMillis();
				map = new HashMap<String, Object>();
				map.put("GeographicDtos", address);
				map.put("responseTime", endTime - startTime);
				map.put("success", "1");
			}
		} catch (Exception e) {
			logger.info("异常信息：" + e.getMessage());
			e.printStackTrace();
			map.put("success", "0");
			map.put("info", "数据解析异常");
			return map;
		}
		return map;
	}

}

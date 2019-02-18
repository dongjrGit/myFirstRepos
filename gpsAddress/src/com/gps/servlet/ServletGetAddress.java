package com.gps.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.gps.entity.Address;
import com.gps.entity.Cells;
import com.gps.entity.GeographicComp;
import com.gps.entity.GeographicHome;
import com.gps.entity.Home;
import com.gps.entity.LonLat;
import com.gps.entity.Unit;
import com.gps.entity.XmlVO;
import com.gps.util.Constants;
import com.gps.util.RequestConnection;

public class ServletGetAddress extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8503944303057892329L;
	private static final String SETTING_XML = "WEB-INF" + File.separator
			+ "classes" + File.separator + "setting.xml";
	private static Logger logger = Logger.getLogger(ServletGetAddress.class);
	private String convp = null;
	private String mapu = null;
	private String basep = null;

	private String convuri = null; // ������γ��ת���ɰٶȾ�γ�ȵ�URL
	private String mapuri = null; // �Ѿ�γ��ת���ɵ�ַ��Ϣ��URL
	private String baseuri = null; // �ѻ�վ��Ϣת���ɰٶȾ�γ��

	private String home_address = null; // �ҷõ�ַ
	private String unit_address = null; // ��λ�õ�ַ
	private String requestTime; // ����ʱ��
	private String responseTime;// ��Ӧʱ��

	private LonLat lonLat = null;
	private Map<String, Object> map = null;
	private List<XmlVO> xmlList = null;
	private List<LonLat> lonlats = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		InputStream in = request.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int i = 0;
		while ((i = in.read()) != -1) {
			bos.write(i);
		}

		byte[] re = bos.toByteArray();
		String reply = new String(re, "UTF-8").trim(); 
		if (reply != null && !"".equals(reply)) {
			JSONObject objJson = JSONObject.fromObject(reply);
			if (objJson != null && objJson.size() > 0) {
				JSONArray lonlatsArray = objJson.optJSONArray("lonlats");				
				if (lonlatsArray != null && lonlatsArray.size() > 0) {
					doPost(request, response);
				}else{
					doLatAddChagnge(objJson);
				}
			}
		}

		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		lonlats = new ArrayList<LonLat>();

		// ����gps��λ��γ��ת��
		InputStream in = req.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int i = 0;
		while ((i = in.read()) != -1) {
			bos.write(i);
		}

		byte[] re = bos.toByteArray();
		String reply = new String(re, "UTF-8").trim(); // �õ���������
		System.out.println("reply��" + reply);
		logger.info("接收到的请求数据:" + reply);

		// �õ���λ�û��߼ҷõľ�γ����Ϣ
		if (reply != null && !"".equals(reply)) {
			JSONObject objJson = JSONObject.fromObject(reply);
			if (objJson != null && objJson.size() > 0) {
				JSONArray lonlatsArray = objJson.optJSONArray("lonlats");
				// JSONArray baseArray = objJson.optJSONArray("base");
				if (lonlatsArray != null && lonlatsArray.size() > 0) {

					for (int j = 0; j < lonlatsArray.size(); j++) {
						JSONObject opt = (JSONObject) lonlatsArray.opt(j);
						String c = opt.getString("c"); // ��λ��
						String h = opt.getString("h"); // �ҷ�
						String id = opt.getString("id");// ��Ȩ��
						System.out.println("id:" + id + ",c:" + c + ",h:" + h);

						parserXML(req); // ����XML
						String lon = null;
						String lat = null;
						String location = null;
						// ��վ���
						String mcc = null;
						String mnc = null;
						String lac = null;
						String cellid = null;

						Unit unit = null;
						Home home = null;

						if (c != "null" && !"".equals(c)) {
							JSONObject jc = JSONObject.fromObject(c);
							lon = jc.optString("lon"); // ����
							lat = jc.optString("lat"); // γ��
							mcc = jc.optString("mcc");
							mnc = jc.optString("mnc");
							lac = jc.optString("lac");
							cellid = jc.optString("cellid");

							if (!"".equals(lon) && !"".equals(lat)) {
								// ������γ��
								location = lon + "," + lat; // ��γ��

								requestTime = getCurrTime(); // ����ʱ��
								String baiduLongLat = getBaiduLongLat(location); // �õ��ٶȾ�γ��
								if (baiduLongLat != null
										&& !"".equals(baiduLongLat)) {
									unit_address = getAddressByLongLat(baiduLongLat); // ��ݾ�γ�ȵõ���ַ
								}
								responseTime = getCurrTime(); // ��Ӧʱ��

								unit_address = formatAddress(unit_address);
								System.out.println("��γ��-��λ�õ�ַ��"
										+ unit_address);

								String[] xy = baiduLongLat.split(",");
								lat = xy[0]; // �ٶ�γ�����
								lon = xy[1]; // �ٶȾ������
								unit = new Unit(lon, lat, unit_address,
										requestTime, responseTime,
										Constants.resultError);
								logger.info("单位访:授权号：" + id + "--" + lon + ","
										+ lat + "--" + this.unit_address + "--"
										+ "返回错误码:" + Constants.resultError);
							} else if (!"".equals(mcc) && !"".equals(mnc)
									&& !"".equals(lac) && !"".equals(cellid)) {

								Cells cells = new Cells(mcc, mnc, lac, cellid,
										"0");

								requestTime = getCurrTime(); // ����ʱ��
								String latlon = getBaseAddress(cells); // �õ��ٶ����
								String baseAddress = "";
								if (latlon != null && !"0.0,0.0".equals(latlon)) {
									baseAddress = getAddressByLongLat(latlon); // ��ݾ�γ�ȵõ���ַ
								} else if ("0.0,0.0".equals(latlon)) {
									latlon = "";
									System.out.println("��ȡ��γ��Ϊ��,��վ��γ��:"
											+ latlon);
								}
								responseTime = getCurrTime(); // ��Ӧʱ��
								unit_address = formatAddress(baseAddress);
								System.out.println("��վ-��λ�õ�ַ��"
										+ unit_address);

								if (latlon != null && !"".equals(latlon)) {
									String[] split = latlon.split(",");
									lat = split[0];
									lon = split[1];
								}
								unit = new Unit(lon, lat, unit_address,
										requestTime, responseTime,
										Constants.resultError);
								logger.info("基站-单位访:授权号：" + id + "--" + lon
										+ "," + lat + "--" + this.unit_address
										+ "--" + "返回错误码:"
										+ Constants.resultError);
							}

						}

						if (h != "null" && !"".equals(h)) {
							JSONObject jh = JSONObject.fromObject(h);
							lon = jh.optString("lon"); // ����
							lat = jh.optString("lat"); // γ��
							mcc = jh.optString("mcc");
							mnc = jh.optString("mnc");
							lac = jh.optString("lac");
							cellid = jh.optString("cellid");

							if (!"".equals(lon) && !"".equals(lat)) {
								location = lon + "," + lat; // ��γ��

								requestTime = getCurrTime(); // ����ʱ��
								String baiduLongLat = getBaiduLongLat(location); // ��ݻ�վ�õ��ٶ����
								if (baiduLongLat != null
										&& !"".equals(baiduLongLat)) {
									home_address = getAddressByLongLat(baiduLongLat); // ��ݾ�γ�ȵõ���ַ
								}
								responseTime = getCurrTime(); // ��Ӧʱ��

								home_address = formatAddress(home_address);
								System.out.println("��γ��-�ҷõ�ַ��"
										+ home_address);

								String[] xy = baiduLongLat.split(",");
								lat = xy[0]; // �ٶ�γ�����
								lon = xy[1]; // �ٶȾ������
								home = new Home(lon, lat, home_address,
										requestTime, responseTime,
										Constants.resultError);
								logger.info("家访:授权号：" + id + "--" + lon + ","
										+ lat + "--" + this.home_address + "--"
										+ "返回错误码:" + Constants.resultError);
							} else if (!"".equals(mcc) && !"".equals(mnc)
									&& !"".equals(lac) && !"".equals(cellid)) {

								Cells cells = new Cells(mcc, mnc, lac, cellid,
										"0");
								requestTime = getCurrTime(); // ����ʱ��
								String latlon = getBaseAddress(cells); // ��ݻ�վ�õ��ٶ����
								String baseAddress = "";
								if (latlon != null && !"0.0,0.0".equals(latlon)) {
									baseAddress = getAddressByLongLat(latlon); // ��ݾ�γ�ȵõ���ַ
								} else if ("0.0,0.0".equals(latlon)) {
									latlon = "";
									System.out.println("��ȡ��γ��Ϊ��,��վ��γ��:"
											+ latlon);
								}
								responseTime = getCurrTime(); // ��Ӧʱ��
								home_address = formatAddress(baseAddress);
								System.out
										.println("��վ-�ҷõ�ַ��" + home_address);

								if (latlon != null && !"".equals(latlon)) {
									String[] split = latlon.split(",");
									lat = split[0];
									lon = split[1];
								}
								home = new Home(lon, lat, home_address,
										requestTime, responseTime,
										Constants.resultError);
								logger.info("基站-家访:授权号：" + id + "--" + lon
										+ "," + lat + "--" + this.home_address
										+ "--" + "返回错误码:"
										+ Constants.resultError);
							}

						}
						lonLat = new LonLat(id, home, unit);
						lonlats.add(lonLat);
					}
					map = new HashMap<String, Object>();
					map.put("lonlats", lonlats);

				}else{
					doLatAddChagnge(objJson);
				}
			}
		}

		JSONObject latObject = JSONObject.fromObject(map);
		String lalon = latObject.toString();
		System.out.println(lalon);
		logger.info("返回数据:" + lalon);

		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(lalon);
		resp.getWriter().flush();
		resp.getWriter().close();
	}

	private List<Address> address = null;
	private GeographicComp comp = null;
	private GeographicHome home = null;

	public void doLatAddChagnge(JSONObject objJson)
			throws ServletException, IOException {

		try {

//			req.setCharacterEncoding("UTF-8");
//			lonlats = new ArrayList<LonLat>();
//
//			InputStream in = req.getInputStream();
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			int i = 0;
//			while ((i = in.read()) != -1) {
//				bos.write(i);
//			}
//			byte[] re = bos.toByteArray();
//			String reply = new String(re, "UTF-8").trim(); // �õ���������
//			logger.info("接收到的===>请求数据::" + reply);
//			if (reply != null && !"".equals(reply)) {
//				JSONObject objJson = JSONObject.fromObject(reply);
				if (objJson != null && objJson.size() > 0) {
					JSONArray lonlatsArray = objJson
							.optJSONArray("GeographicDtos");
					if (lonlatsArray != null && lonlatsArray.size() > 0) {
						for (int j = 0; j < lonlatsArray.size(); j++) {
							JSONObject opt = (JSONObject) lonlatsArray.opt(j);

							JSONObject geographicComp = opt
									.getJSONObject("geographicComp"); // ��λ��
							JSONObject geographicHome = opt
									.getJSONObject("geographicHome"); // �ҷ�
							String compAdreess = geographicComp
									.getString("address");

							String homeAdreess = geographicHome
									.getString("address");
							address = new ArrayList<Address>();
							if (!"".equals(compAdreess)) {

								comp = new GeographicComp(compAdreess,
										doConnect(compAdreess)
												.getJSONObject("result")
												.getJSONObject("location")
												.getString("lat"), doConnect(
												compAdreess)
												.getJSONObject("result")
												.getJSONObject("location")
												.getString("lng"), "C",
												doConnect(compAdreess)
												.getJSONObject("result")
												.getString("precise"));
								address.add(comp);
							}
							if (!"".equals(homeAdreess)) {
								home = new GeographicHome(homeAdreess,
										doConnect(homeAdreess)
												.getJSONObject("result")
												.getJSONObject("location")
												.getString("lat"), doConnect(
												homeAdreess)
												.getJSONObject("result")
												.getJSONObject("location")
												.getString("lng"), "H",
												doConnect(homeAdreess)
												.getJSONObject("result")
												.getString("precise"));
								address.add(home);
							}

						}
						map = new HashMap<String, Object>();
						map.put("GeographicDtos", address);
					}
				}
//			}

//			JSONObject latObject = JSONObject.fromObject(map);
//			String lalon = latObject.toString();
//			System.out.println(lalon);
//			logger.info("返回信息:" + lalon);
//
//			resp.setContentType("text/html;charset=UTF-8");
//			resp.setCharacterEncoding("UTF-8");
//			resp.getWriter().print(lalon);
//			resp.getWriter().flush();
//			resp.getWriter().close();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("异常信息：" + e.getMessage());
			e.printStackTrace();

		}
	}

	//
	public JSONObject doConnect(String address) {
		String urlString = "http://api.map.baidu.com/geocoder/v2/?address="
				+ address + "&output=json&ak=CaUFINd5T3CPEC8jWtVxHW3l";

		// start
		/*JSONObject demo = new JSONObject();
		JSONObject result = new JSONObject();
		JSONObject location = new JSONObject();
		location.put("lng", "121.47489238123245");
		location.put("lat", "31.213439116817655");
		result.put("location", location);
		demo.put("result", result);*/
		// end
		 return JSONObject.fromObject(RequestConnection.sendGet(urlString));

//		logger.info("demo数据：" + demo.toString());
//		return demo;
	}

	/**
	 * ���JSON��ݵõ������ַ
	 * 
	 * @param address
	 * @return
	 */
	public String formatAddress(String address) {
		if (address != null && !"".equals(address)) {
			JSONObject jaddress = JSONObject.fromObject(address);
			Constants.resultError = jaddress.getString("status");
			String resultaddress = jaddress.getString("result");
			JSONObject fobj = JSONObject.fromObject(resultaddress);
			String formatted_address = fobj.getString("formatted_address");

			return formatted_address;
		}

		return "";
	}

	/**
	 * ������ð��������ת���ɰٶȾ�γ��,Ĭ��GPS���
	 * 
	 * @location ��ʽ�����ȣ�γ��
	 * @return
	 */
	public String getBaiduLongLat(String location) {
		// ���������ת���ɰٶȾ�γ�� URL
		String convurl = convuri + "coords=" + location + "&"
				+ new String(convp).substring(0, convp.length() - 1);
		logger.debug("���������ת���ɰٶȾ�γ�� URL: " + convurl);
		String convjso = null;
		try {
			convjso = RequestConnection.sendGet(convurl);
			if (convjso != null) {
				JSONObject objson = JSONObject.fromObject(convjso);
				JSONArray jaArray = objson.optJSONArray("result");
				if (jaArray != null && jaArray.size() > 0) {
					for (int j = 0; j < jaArray.size(); j++) {
						JSONObject jlist = (JSONObject) jaArray.opt(j);
						String x = jlist.getString("x");
						String y = jlist.getString("y");

						location = y + "," + x; // γ�ȣ�����
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ת���ٶȾ�γ��ʧ��:" + convjso);
			logger.error("ת���ٶȾ�γ��ʧ��:" + "url:" + convurl + "--�����룺"
					+ Constants.resultError);
		}

		return location;
	}

	/**
	 * ��ݾ�γ�ȵõ���ַ
	 * 
	 * @location ��ʽ��γ�ȣ�����
	 * @return
	 */
	public String getAddressByLongLat(String location) {
		// �ٶȾ�γ��ת����ϸ��ַ
		String url = mapuri + "?" + mapu + "location=" + location;
		logger.debug("�ٶȾ�γ��ת����ϸ��ַ URL: " + url);
		String address = null;
		try {
			address = RequestConnection.sendGet(url);
			if (address != null) {
				return address;
			}
		} catch (Exception e) {
			e.printStackTrace();
			address = "";
			System.out.println("��γ�ȵõ���ַʧ��:" + address);
			logger.error("��γ�ȵõ���ַʧ��:" + "url:" + url + "--�����룺"
					+ Constants.resultError);
		}

		return address;
	}

	/**
	 * ��ݻ�վ�õ����
	 */
	public String getBaseAddress(Cells cells) {
		String x = getXParameter(cells); // �õ�16���ƻ�վ��Ϣ
		String url = null;
		if (x != null) {
			url = baseuri + "&" + basep + "x=" + x;
			logger.debug("��ݻ�վ�õ���� URL: " + url);
		}

		String minigps = RequestConnection.sendGet(url);
		String latlon = null;
		if (minigps != null && !"".equals(minigps)) {
			JSONObject jObject = JSONObject.fromObject(minigps);
			Double lat = jObject.optDouble("lat");
			Double lon = jObject.optDouble("lon");
			Constants.resultError = "��վ���أ�" + jObject.optString("cause");
			// latlon = String.format("%f,%f", lat, lon);
			latlon = lat + "," + lon;
		} else {
			System.out.println("��վ��Ϣ����ʧ��:" + Constants.resultError);
			logger.error("��վ��Ϣ����ʧ��:" + "url:" + url + "--�����룺"
					+ Constants.resultError);
		}

		return latlon;
	}

	/**
	 * ת��16����
	 */
	private String getXParameter(Cells c) {
		String x = null;
		if (c != null) {
			// mcc-mnc-lac-cellid-sdb(�źţ�Ĭ��0)
			x = "%x-%x-%x-%x-%x";
			x = String.format(x, (int) parseInt(c.getMcc()),
					(int) parseInt(c.getMnc()), (int) parseInt(c.getLac()),
					(int) parseInt(c.getCellid()), (int) parseInt(c.getSdb()));
		}
		return x;
	}

	/**
	 * ת��int��
	 * 
	 * @param p
	 * @return
	 */
	private int parseInt(String p) {
		return Integer.parseInt(p);
	}

	/**
	 * ��ȡ��ǰʱ��
	 * 
	 * @return
	 */
	private String getCurrTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(new Date());

		return time;
	}

	/**
	 * ���� setting.xml ����
	 * 
	 * @param req
	 */
	public void parserXML(HttpServletRequest req) {
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder db = factory.newDocumentBuilder();
			String serverPath = req.getSession().getServletContext()
					.getRealPath(File.separator);
			String relativePath = SETTING_XML;
			String filePath = serverPath + relativePath;
			System.out.println("xml�ļ�·���� " + filePath);
			logger.debug("xml�ļ�·���� " + filePath);

			Document document = db.parse(new File(filePath));
			NodeList node = document.getElementsByTagName("config");
			xmlList = new ArrayList();
			xmlList.clear();
			for (int j = 0; j < node.getLength(); ++j) {
				Element element = (Element) node.item(j);
				XmlVO con = new XmlVO();

				String name = element.getAttribute("name");
				String value = element.getAttribute("value");
				con.setName(name);
				con.setValue(value);
				xmlList.add(j, con);
				System.out.println("name��" + name + ",value:" + value);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		convp = "";
		mapu = "";
		basep = "";
		// logger.debug("xmlList Count:" + xmlList.size());
		for (int j = 0; j < xmlList.size(); ++j) {

			// ��ȡxml ƴ�� �ǰٶȵ�ͼ������ת������ URL
			if (((XmlVO) xmlList.get(j)).getName().equals("convurl")) {
				convuri = ((XmlVO) xmlList.get(j)).getValue();
			} else if (((XmlVO) xmlList.get(j)).getName().equals("from")
					|| ((XmlVO) xmlList.get(j)).getName().equals("to")
					|| ((XmlVO) xmlList.get(j)).getName().equals("ak")) {
				convp = convp + ((XmlVO) xmlList.get(j)).getName() + "="
						+ ((XmlVO) xmlList.get(j)).getValue() + "&";
			}

			// ��ȡxml ƴ�� �ٶȾ�γ��ת��ַURL
			if (((XmlVO) xmlList.get(j)).getName().equals("url")) {
				mapuri = ((XmlVO) xmlList.get(j)).getValue();
			} else if (((XmlVO) xmlList.get(j)).getName().equals("ak")
					// || ((xmlVO) list.get(j)).getName().equals("callback")
					|| ((XmlVO) xmlList.get(j)).getName().equals("output")
					|| ((XmlVO) xmlList.get(j)).getName().equals("pois")) {
				mapu = mapu + ((XmlVO) xmlList.get(j)).getName() + "="
						+ ((XmlVO) xmlList.get(j)).getValue() + "&";
			}

			// ��ȡxml ƴ�ӻ�վ��Ϣת���ٶȾ�γ�� URL minigps
			if (((XmlVO) xmlList.get(j)).getName().equals("baseUrl")) {
				baseuri = ((XmlVO) xmlList.get(j)).getValue();
			} else if (((XmlVO) xmlList.get(j)).getName().equals("needaddress")
					|| ((XmlVO) xmlList.get(j)).getName().equals("mt")) {
				basep = basep + ((XmlVO) xmlList.get(j)).getName() + "="
						+ ((XmlVO) xmlList.get(j)).getValue() + "&";
			}

		}
	}
}

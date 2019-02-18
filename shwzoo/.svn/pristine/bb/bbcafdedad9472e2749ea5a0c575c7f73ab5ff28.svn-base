package com.yinlian.wssc.web.util;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class XmlUtils {

	public static void main(String[] arg) {
		Pattern p = Pattern.compile("<body>[\\s\\S]+</body>");
		Matcher m = p
				.matcher("<Ips><GateWayRsp><head><ReferenceID></ReferenceID><RspCode>000000</RspCode><RspMsg><![CDATA[交易成功！]]></RspMsg><ReqDate>20160617143850</ReqDate><RspDate>20160617144012</RspDate><Signature>b6c9ddc7981bb626d38ed24ff5fa4b5d</Signature></head><body><MerBillNo>20160524094823163</MerBillNo><CurrencyType>156</CurrencyType><Amount>0.01</Amount><Date>20160617</Date><Status>Y</Status><Msg><![CDATA[支付成功！]]></Msg><Attach><![CDATA[qwe]]></Attach><IpsBillNo>BO20160617131629054695</IpsBillNo><IpsTradeNo>2016061702065026225</IpsTradeNo><RetEncodeType>17</RetEncodeType><BankBillNo>7108334891</BankBillNo><ResultType>0</ResultType><IpsBillTime>20160617144006</IpsBillTime></body></GateWayRsp></Ips>");
	String string=null;
		 while(m.find()) {
	            String g = m.group();
	           // System.out.println(g);
	            string = g;
	        }
		
	        System.out.println(string);

		/*
		 * Map<String, Object> map=parserValueForXml(
		 * "<Ips><GateWayRsp><head><ReferenceID></ReferenceID><RspCode>000000</RspCode><RspMsg><![CDATA[交易成功！]]></RspMsg><ReqDate>20160617143850</ReqDate><RspDate>20160617144012</RspDate><Signature>b6c9ddc7981bb626d38ed24ff5fa4b5d</Signature></head><body><MerBillNo>20160524094823163</MerBillNo><CurrencyType>156</CurrencyType><Amount>0.01</Amount><Date>20160617</Date><Status>Y</Status><Msg><![CDATA[支付成功！]]></Msg><Attach><![CDATA[qwe]]></Attach><IpsBillNo>BO20160617131629054695</IpsBillNo><IpsTradeNo>2016061702065026225</IpsTradeNo><RetEncodeType>17</RetEncodeType><BankBillNo>7108334891</BankBillNo><ResultType>0</ResultType><IpsBillTime>20160617144006</IpsBillTime></body></GateWayRsp></Ips>"
		 * ); System.out.println(map.get("Date"));
		 * System.out.println(map.get("RspCode"));
		 */
	}

	/**
	 * 解析xml的value值
	 * 
	 * @param xml
	 * @return
	 */
	public static Map<String, Object> parserValueForXml(String xml) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotNull(xml)) {
			StringReader read = new StringReader(
					"<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + xml + "");
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder saxbBuilder = new SAXBuilder();
			try {
				// 通过输入源构造一个Document
				Document doc = saxbBuilder.build(source);
				// 取的根元素
				Element root = doc.getRootElement();
				System.out.println(root.getName());
				List<?> node = root.getChildren(); // 一级
				for (int i = 0; i < node.size(); i++) {
					Element element = (Element) node.get(i);
					if (element != null && !element.getName().equals("head")) {
						String key1 = element.getName();
						String value1 = element.getValue();
						System.out.println(key1 + "-->一级标签下的:" + value1);
					}
					List<?> subNode = element.getChildren(); // 二级
					String key2 = "";
					if (subNode != null && subNode.size() > 0) {
						for (int j = 0; j < subNode.size(); j++) {
							Element subElement = (Element) subNode.get(j);
							key2 = subElement.getName();
							String value2 = subElement.getValue();
							System.out.println(key2 + "-->二级标签下的:" + value2);
							if (key2 != null) {
								map.put(key2, value2);
							}
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							List<?> tNode = subElement.getChildren(); // 三级
							if (tNode != null && tNode.size() > 0) {
								for (int k = 0; k < tNode.size(); k++) {
									Element tElement = (Element) tNode.get(k);
									String key3 = tElement.getName();
									String value3 = tElement.getValue();
									System.out.println(tElement.getName()
											+ "-->三级标签下的:" + value3);
									if (key3 != null) {
										map.put(key3, value3);
									}
									Map<String, Object> map2 = new HashMap<String, Object>();
									List<?> fNode = tElement.getChildren();// 四级
									if (fNode != null && fNode.size() > 0) {

										for (int h = 0; h < fNode.size(); h++) {
											Element fElement = (Element) fNode
													.get(h);
											String key4 = fElement.getName();
											String value4 = fElement.getValue();
											System.out.println(fElement
													.getName()
													+ "-->四级标签下的:"
													+ value4);
											map2.put(key4, value4);
										}
									}
									list.add(map2);
								}
							}
							map.put(key2, list);
						}
					}
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 解析xml的属性值
	 * 
	 * @param xml
	 *            目标xml
	 */
	public static Map<String, Object> parserAttributes(String xml) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotNull(xml)) {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder saxbBuilder = new SAXBuilder();
			try {
				// 通过输入源构造一个Document
				Document doc = saxbBuilder.build(source);
				// 取的根元素
				Element root = doc.getRootElement();
				System.out.println(root.getName());
				List<?> node = root.getChildren(); // 一级
				for (int i = 0; i < node.size(); i++) {
					Element element = (Element) node.get(i);
					System.out.println(element.getName());
					List<Attribute> list = element.getAttributes();
					if (list != null && list.size() > 0) {
						for (Attribute attribute : list) {
							System.out.println(attribute.getName() + ":"
									+ attribute.getValue());
							map.put(attribute.getName(), attribute.getValue());
						}
					}

					List<?> subNode = element.getChildren(); // 二级
					if (subNode != null && subNode.size() > 0) {
						for (int j = 0; j < subNode.size(); j++) {
							Element subElement = (Element) subNode.get(j);
							List<Attribute> list2 = subElement.getAttributes();
							if (list2 != null && list2.size() > 0) {
								for (Attribute attribute : list2) {
									System.out.println(attribute.getName()
											+ "-->二级标签下的:"
											+ attribute.getValue());
									map.put(attribute.getName(),
											attribute.getValue());
								}
							}

							List<?> tNode = subElement.getChildren(); // 三级
							if (tNode != null && tNode.size() > 0) {
								for (int k = 0; k < tNode.size(); k++) {
									Element tElement = (Element) tNode.get(j);
									List<Attribute> list3 = tElement
											.getAttributes();
									for (Attribute attribute : list3) {
										System.out.println(attribute.getName()
												+ "-->三级级标签下的:"
												+ attribute.getValue());
										map.put(attribute.getName(),
												attribute.getValue());
									}
								}
							}
						}
					}
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	 public static String mapToXml(Map<String, String> dataMap)
	    {
	        synchronized (XmlUtils.class)
	        {
	            StringBuilder strBuilder = new StringBuilder();
	            strBuilder.append("<xmlroot>");
	            Set<String> objSet = dataMap.keySet();
	            for (Object key : objSet)
	            {
	                if (key == null)
	                {
	                    continue;
	                }
	                strBuilder.append("\n");
	                strBuilder.append("<").append(key.toString()).append(">\n");
	                Object value = dataMap.get(key);
	                strBuilder.append(coverter(value));
	                strBuilder.append("</").append(key.toString()).append(">\n");
	            }
	            strBuilder.append("</xmlroot>");
	            return strBuilder.toString();
	        }
	    }
	    /**
	     * Coverter.
	     *
	     * @param object the object
	     * @return the string
	     */
	    public static String coverter(Object object)
	    {
	        if (object instanceof Object[])
	        {
	            return coverter((Object[]) object);
	        }
	        if (object instanceof Collection)
	        {
	            return coverter((Collection<?>) object);
	        }
	        StringBuilder strBuilder = new StringBuilder();
	        if (isObject(object))
	        {
	            Class<? extends Object> clz = object.getClass();
	            Field[] fields = clz.getDeclaredFields();

	            for (Field field : fields)
	            {
	                field.setAccessible(true);
	                if (field == null)
	                {
	                    continue;
	                }
	                String fieldName = field.getName();
	                Object value = null;
	                try
	                {
	                    value = field.get(object);
	                }
	                catch (IllegalArgumentException e)
	                {
	                    continue;
	                }
	                catch (IllegalAccessException e)
	                {
	                    continue;
	                }
	                strBuilder.append("<").append(fieldName)
	                        .append(" className=\"").append(
	                                value.getClass().getName()).append("\">\n");
	                if (isObject(value))
	                {
	                    strBuilder.append(coverter(value));
	                }
	                else if (value == null)
	                {
	                    strBuilder.append("null\n");
	                }
	                else
	                {
	                    strBuilder.append(value.toString() + "\n");
	                }
	                strBuilder.append("</").append(fieldName).append(">\n");
	            }
	        }
	        else if (object == null)
	        {
	            strBuilder.append("null\n");
	        }
	        else
	        {
	            strBuilder.append(object.toString() + "\n");
	        }
	        return strBuilder.toString();
	    }
	    
	    /**
	     * Checks if is object.
	     *
	     * @param obj the obj
	     *
	     * @return true, if is object
	     */
	    private static boolean isObject(Object obj)
	    {
	        if (obj == null)
	        {
	            return false;
	        }
	        if (obj instanceof String)
	        {
	            return false;
	        }
	        if (obj instanceof Integer)
	        {
	            return false;
	        }
	        if (obj instanceof Double)
	        {
	            return false;
	        }
	        if (obj instanceof Float)
	        {
	            return false;
	        }
	        if (obj instanceof Byte)
	        {
	            return false;
	        }
	        if (obj instanceof Long)
	        {
	            return false;
	        }
	        if (obj instanceof Character)
	        {
	            return false;
	        }
	        if (obj instanceof Short)
	        {
	            return false;
	        }
	        if (obj instanceof Boolean)
	        {
	            return false;
	        }
	        return true;
	    }
}

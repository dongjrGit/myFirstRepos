/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.yinlian.Extended.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 返回的结果的父类
 * 
 * @author Administrator
 * @version $Id: ResultItem.java, v 0.1 2016年2月25日 下午1:51:33 Administrator Exp $
 * @param <T>
 */
public class BaseResult {

	private String startTime;
	private int code;
	private String desc;
	private Object data;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BaseResult() {
		data = "";
		desc = "";
		code = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		startTime = df.format(new Date());// new Date()为获取当前系统时间
	}

	/**
	 * Getter method for property <tt>data</tt>.
	 * 
	 * @return property value of data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Setter method for property <tt>data</tt>.
	 * 
	 * @param data
	 *            value to be assigned to property data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Getter method for property <tt>startTime</tt>.
	 * 
	 * @return property value of startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Setter method for property <tt>startTime</tt>.
	 * 
	 * @param startTime
	 *            value to be assigned to property startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Getter method for property <tt>code</tt>.
	 * 
	 * @return property value of code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Setter method for property <tt>code</tt>.
	 * 
	 * @param code
	 *            value to be assigned to property code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Getter method for property <tt>desc</tt>.
	 * 
	 * @return property value of desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Setter method for property <tt>desc</tt>.
	 * 
	 * @param desc
	 *            value to be assigned to property desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String toJson() {
		JSONObject json;
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));  
	
		json = JSONObject.fromObject(this,config);
		return json.toString();
	}
	
	public String toJson(String dataFromat) {
		JSONObject json;
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(dataFromat));  
	
		json = JSONObject.fromObject(this,config);
		return json.toString();
	}
	public String toJsonEx() {
			return JSON.toJSONString(this);
	}
}

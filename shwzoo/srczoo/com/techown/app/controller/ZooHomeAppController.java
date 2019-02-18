package com.techown.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;

@RestController
@RequestMapping("/api/app/zooHome")
public class ZooHomeAppController {
	private static Logger logger = LoggerFactory.getLogger(ZooHomeAppController.class);

	private static ObjectMapper MAPPER = new ObjectMapper();
	@Value("${root_address}")
	private String rootAddr;

	@Value("${time_address}")
	private String timeAddr;

	@Value("${instruction_address}")
	private String instructionAddr;

	@Value("${introduce_address}")
	private String introduceAddr;

	@Value("${ticknotice_address}")
	private String ticknoticeAddr;

	@Value("${gui_services_address}")
	private String guiServicesAddr;
	
	@Value("${redisExpireTime}")
	private int redisExpireTime;

	/**
	 * 表演时间
	 * 
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/time", produces = "text/html;charset=UTF-8")
	public String getTime(String ch) {
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			String redistData = null;
			if(StringUtils.isNotBlank(redistData = RedisUserInfo.Get("app_zooHome_getTime"))){
				item.setData(redistData);
				return  MAPPER.writeValueAsString(item);
			}
			HttpClient client = new HttpClient();
			String response = null;
			PostMethod postMethod = new PostMethod(timeAddr);
			postMethod.setRequestHeader("content-type", "text/html;charset=UTF-8");
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
				response = replaceUrl(response);
				Document doc = Jsoup.parse(response);
				doc.getElementsByClass("H50").remove();
				response = doc.toString();
				RedisUserInfo.Set("app_zooHome_getTime", response, redisExpireTime);
				item.setData(response);
			} else {
				item.setCode(status);
				item.setDesc("系统维护中");
				logger.error("app-zooHome getTime httpStatus {}", status);
			}
			result = MAPPER.writeValueAsString(item);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error("call app-zooHome getTime req ch {} !\r\n resp {} !\r\n exception", ch,
					result, e);
		}
		return result;
	}

	/**
	 * 游园须知
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/instruction", produces = "text/html;charset=UTF-8")
	public String getInstruction(String ch) {
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			String redistData = null;
			if(StringUtils.isNotBlank(redistData = RedisUserInfo.Get("app_zooHome_getInstruction"))){
				item.setData(redistData);
				return  MAPPER.writeValueAsString(item);
			}
			HttpClient client = new HttpClient();
			String response = null;
			PostMethod postMethod = new PostMethod(instructionAddr);
			postMethod.setRequestHeader("content-type", "text/html;charset=UTF-8");
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
				response = replaceUrl(response);
				Document doc = Jsoup.parse(response);
				doc.getElementsByClass("H50").remove();
				Elements aelements = doc.getElementsByClass("instr_li").get(0)
						.getElementsByTag("a");
				for (Element element : aelements) {
					element.removeAttr("href");
				}
				response = doc.toString();
				RedisUserInfo.Set("app_zooHome_getInstruction", response, redisExpireTime);
				item.setData(response);
			} else {
				item.setCode(status);
				item.setDesc("系统维护中");
				logger.error("app-zooHome getInstruction httpStatus {}", status);
			}
			result = MAPPER.writeValueAsString(item);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error(
					"call app-zooHome getInstruction req ch {} !\r\n resp {} !\r\n exception",
					ch, result, e);
		}
		return result;
	}

	/**
	 * 园区介绍
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/introduce", produces = "text/html;charset=UTF-8")
	public String getIntroduce(String ch) {
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			String redistData = null;
			if(StringUtils.isNotBlank(redistData = RedisUserInfo.Get("app_zooHome_getIntroduce"))){
				item.setData(redistData);
				return  MAPPER.writeValueAsString(item);
			}
			HttpClient client = new HttpClient();
			String response = null;
			PostMethod postMethod = new PostMethod(introduceAddr);
			postMethod.setRequestHeader("content-type", "text/html;charset=UTF-8");
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
				response = replaceUrl(response);
				Document doc = Jsoup.parse(response);
				doc.getElementsByClass("vidio").remove();

				doc.getElementsByClass("H50").remove();
				response = doc.toString();
				RedisUserInfo.Set("app_zooHome_getIntroduce", response, redisExpireTime);
				item.setData(response);
			} else {
				item.setCode(status);
				item.setDesc("系统维护中");
				logger.error("app-zooHome getIntroduce httpStatus {}", status);
			}
			result = MAPPER.writeValueAsString(item);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error("call app-zooHome getIntroduce req ch {} !\r\n resp {} !\r\n exception",
					ch, result, e);
		}
		return result;
	}

	/**
	 * 购票须知
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/ticknotice", produces = "text/html;charset=UTF-8")
	public String getTicknotice(String ch) {
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			String redistData = null;
			if(StringUtils.isNotBlank(redistData = RedisUserInfo.Get("app_zooHome_getTicknotice"))){
				item.setData(redistData);
				return  MAPPER.writeValueAsString(item);
			}
			HttpClient client = new HttpClient();
			String response = null;
			PostMethod postMethod = new PostMethod(ticknoticeAddr);
			postMethod.setRequestHeader("content-type", "text/html;charset=UTF-8");
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
				response = replaceUrl(response);
				Document doc = Jsoup.parse(response);
				doc.getElementsByClass("H50").remove();
				response = doc.toString();
				RedisUserInfo.Set("app_zooHome_getTicknotice", response, redisExpireTime);
				item.setData(response);
			} else {
				item.setCode(status);
				item.setDesc("系统维护中");
				logger.error("app-zooHome getTicknotice httpStatus {}", status);
			}
			result = MAPPER.writeValueAsString(item);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error(
					"call app-zooHome getTicknotice req ch {} !\r\n resp {} !\r\n exception", ch,
					result, e);
		}
		return result;
	}

	/**
	 * 便捷服务
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/guiService", produces = "text/html;charset=UTF-8")
	public String getGui(String ch, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			String redistData = null;
			if(StringUtils.isNotBlank(redistData = RedisUserInfo.Get("app_zooHome_getGui"))){
				item.setData(redistData);
				return  MAPPER.writeValueAsString(item);
			}
			HttpClient client = new HttpClient();
			String response = null;
			PostMethod postMethod = new PostMethod(guiServicesAddr);
			postMethod.setRequestHeader("content-type", "text/html;charset=UTF-8");
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
				response = replaceUrl(response);
				Document doc = Jsoup.parse(response);
				doc.getElementsByClass("dining_head").remove();
				doc.getElementsByClass("H50").remove();
				response = doc.toString();
				// Elements srcs = doc.select("[src]");
				// for (Element element : srcs) {
				// if (!element.attr("src").startsWith("http:")) {
				// element.attr("src", rootAddr + element.attr("src"));
				// }
				// }
				// Elements links = doc.select("link[href]");
				// for (Element element : links) {
				// if (!element.attr("href").startsWith("http:")) {
				// element.attr("href", rootAddr + element.attr("href"));
				// }
				// }
				RedisUserInfo.Set("app_zooHome_getGui", response, redisExpireTime);
				item.setData(response);
			} else {
				item.setCode(status);
				item.setDesc("系统维护中");
				logger.error("app-zooHome getGui httpStatus {}", status);
			}
			result = MAPPER.writeValueAsString(item);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error("call app-zooHome getGui req ch {} !\r\n resp {} !\r\n exception", ch,
					result, e);
		}
		return result;
	}

	public String replaceUrl(String url) {
		return url.replaceAll("src\\s*=\\s*\"(?!http:)(?!https:)", "src=\"" + rootAddr)
				.replaceAll("src\\s*=\\s*'(?!http:)(?!https:)", "src='" + rootAddr)
				.replaceAll("href\\s*=\\s*\"(?!http:)(?!https:)", "href=\"" + rootAddr)
				.replaceAll("href\\s*=\\s*'(?!http:)(?!https:)", "href='" + rootAddr);
	}
}

package com.yinlian.app.jpush;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.util.PropertiesUtil;
import com.yl.soft.log.LogHandle;

public class PushService {

	/**Android
	 * 根据应用程序注册ID发送推送消息
	 * @param title
	 * @param content
	 * @param regid
	 * @throws Exception
	 */
	public void sendPush(String title, String content, String regid)
			throws Exception {
		String masterSecret = GetProperty().getProperty("mastersecret");
		String appKey = GetProperty().getProperty("appkey");
		String maxRetryTimes = GetProperty().getProperty("offlineday");
		// 创建激光推送客户端对象
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				Integer.parseInt(maxRetryTimes));
		PushPayload payload;
		payload = JpushService.buildAndroidWinphonePayloadwithRegID(regid,
				content, title);
		try {
			PushResult pushResult = jpushClient.sendPush(payload);// 实际上是这句发送出错，被catch到APIRequestException错误
			LogHandle.info(LogType.jpush, "Got result - " + pushResult);
		} catch (APIConnectionException e) {
			LogHandle.error(LogType.jpush,
					"Connection error. Should retry later. " + e.toString());
		} catch (APIRequestException e) {
			LogHandle.error(LogType.jpush,
					"Error response from JPush server. Should review and fix it. "
							+ e.toString());
			LogHandle.error(LogType.jpush, "HTTP Status: " + e.getStatus());
			LogHandle.error(LogType.jpush, "Error Code: " + e.getErrorCode());
			LogHandle.error(LogType.jpush,
					"Error Message: " + e.getErrorMessage());
			LogHandle.error(LogType.jpush, "Msg ID: " + e.getMsgId());
		}
	}
	/**IOS
	 * 根据应用程序注册ID发送推送消息
	 * @param title
	 * @param content
	 * @param regid
	 * @throws Exception
	 */
	public void sendPushIOS(String title, String content, String regid)
			throws Exception {
		String masterSecret = GetProperty().getProperty("iosmastersecret");
		String appKey = GetProperty().getProperty("iosappkey");
		String maxRetryTimes = GetProperty().getProperty("iosofflineday");
		// 创建激光推送客户端对象
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				Integer.parseInt(maxRetryTimes));
		PushPayload payload;
		payload = JPushIOS_Service.buildIOSPayloadwithRegID(regid, content,
				title);
		try {
			PushResult pushResult = jpushClient.sendPush(payload);// 实际上是这句发送出错，被catch到APIRequestException错误
			LogHandle.info(LogType.jpush, "Got result - " + pushResult);
		} catch (APIConnectionException e) {
			LogHandle.error(LogType.jpush,
					"Connection error. Should retry later. " + e.toString());
		} catch (APIRequestException e) {
			LogHandle.error(LogType.jpush,
					"Error response from JPush server. Should review and fix it. "
							+ e.toString());
			LogHandle.error(LogType.jpush, "HTTP Status: " + e.getStatus());
			LogHandle.error(LogType.jpush, "Error Code: " + e.getErrorCode());
			LogHandle.error(LogType.jpush,
					"Error Message: " + e.getErrorMessage());
			LogHandle.error(LogType.jpush, "Msg ID: " + e.getMsgId());
		}
	}
	/**Android
	 * 根据应用程序别名（用户ID）发送推送消息
	 * @param title
	 * @param content
	 * @param regid
	 * @throws Exception
	 */
	public static void sendPushAlias(String title, String content, String userid)
			throws Exception {
		String masterSecret = GetProperty().getProperty("mastersecret");
		String appKey = GetProperty().getProperty("appkey");
		String maxRetryTimes = GetProperty().getProperty("offlineday");
		// 创建激光推送客户端对象
		List<String> alias = new ArrayList<String>();
		alias.add(userid);
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				Integer.parseInt(maxRetryTimes));
		PushPayload payload;

		payload = JpushService.buildAndroidWinphonePayloadwithAlias(alias,
				content, title);
		try {
			PushResult pushResult = jpushClient.sendPush(payload);// 实际上是这句发送出错，被catch到APIRequestException错误
			LogHandle.info(LogType.jpush, "Got result - " + pushResult);
		} catch (APIConnectionException e) {
			LogHandle.error(LogType.jpush,
					"Connection error. Should retry later. " + e.toString());
		} catch (APIRequestException e) {
			LogHandle.error(LogType.jpush,
					"Error response from JPush server. Should review and fix it. "
							+ e.toString());
			LogHandle.error(LogType.jpush, "HTTP Status: " + e.getStatus());
			LogHandle.error(LogType.jpush, "Error Code: " + e.getErrorCode());
			LogHandle.error(LogType.jpush,
					"Error Message: " + e.getErrorMessage());
			LogHandle.error(LogType.jpush, "Msg ID: " + e.getMsgId());
		}
	}
	
	/**
	 * 根据应用程序别名（用户ID）批量发送推送消息
	 * @param title
	 * @param content
	 * @param userids
	 * @throws Exception
	 */
	public static void sendPushAliass(String title, String content, List<String> userids)
			throws Exception {
		String masterSecret = GetProperty().getProperty("mastersecret");
		String appKey = GetProperty().getProperty("appkey");
		String maxRetryTimes = GetProperty().getProperty("offlineday");
		// 创建激光推送客户端对象
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				Integer.parseInt(maxRetryTimes));
		PushPayload payload;

		payload = JpushService.buildAndroidWinphonePayloadwithAlias(userids,
				content, title);
		try {
			PushResult pushResult = jpushClient.sendPush(payload);// 实际上是这句发送出错，被catch到APIRequestException错误
			LogHandle.info(LogType.jpush, "Got result - " + pushResult);
		} catch (APIConnectionException e) {
			LogHandle.error(LogType.jpush,
					"Connection error. Should retry later. " + e.toString());
		} catch (APIRequestException e) {
			LogHandle.error(LogType.jpush,
					"Error response from JPush server. Should review and fix it. "
							+ e.toString());
			LogHandle.error(LogType.jpush, "HTTP Status: " + e.getStatus());
			LogHandle.error(LogType.jpush, "Error Code: " + e.getErrorCode());
			LogHandle.error(LogType.jpush,
					"Error Message: " + e.getErrorMessage());
			LogHandle.error(LogType.jpush, "Msg ID: " + e.getMsgId());
		}
	}
	
	/**IOS
	 * 根据应用程序别名（用户ID）发送推送消息
	 * @param title
	 * @param content
	 * @param regid
	 * @throws Exception
	 */
	public void sendPushAliasIOS(String title, String content, String userid)
			throws Exception {
		String masterSecret = GetProperty().getProperty("iosmastersecret");
		String appKey = GetProperty().getProperty("iosappkey");
		String maxRetryTimes = GetProperty().getProperty("iosofflineday");
		List<String> alias = new ArrayList<String>();
		alias.add(userid);
		// 创建激光推送客户端对象
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				Integer.parseInt(maxRetryTimes));
		PushPayload payload;
		payload = JPushIOS_Service.buildIOSPayloadwithAlias(alias, content,
				title);
		try {
			PushResult pushResult = jpushClient.sendPush(payload);// 实际上是这句发送出错，被catch到APIRequestException错误
			LogHandle.info(LogType.jpush, "Got result - " + pushResult);
		} catch (APIConnectionException e) {
			LogHandle.error(LogType.jpush,
					"Connection error. Should retry later. " + e.toString());
		} catch (APIRequestException e) {
			LogHandle.error(LogType.jpush,
					"Error response from JPush server. Should review and fix it. "
							+ e.toString());
			LogHandle.error(LogType.jpush, "HTTP Status: " + e.getStatus());
			LogHandle.error(LogType.jpush, "Error Code: " + e.getErrorCode());
			LogHandle.error(LogType.jpush,
					"Error Message: " + e.getErrorMessage());
			LogHandle.error(LogType.jpush, "Msg ID: " + e.getMsgId());
		}
	}

	private static Properties GetProperty() throws IOException {
		// 读取配置文件
		 Properties pro = PropertiesUtil.getProperties("cfg.properties");
		return pro;
	}
}

package com.yinlian.app.jpush;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.yinlian.Extended.LogType;
import com.yl.soft.log.LogHandle;

public class JpushService {

	public void sendPush(String titile,String content)throws Exception {
		
		String masterSecret=GetProperty().getProperty("mastersecret");
		String appKey=GetProperty().getProperty("appkey");
		String maxRetryTimes= GetProperty().getProperty("offlineday");
        //创建激光推送客户端对象
        JPushClient jpushClient = new JPushClient(masterSecret, appKey,Integer.parseInt(maxRetryTimes));
        
        PushPayload payload =buildAndroidWinphonePayload(content,titile);
        
        try {
            PushResult pushResult = jpushClient.sendPush(payload);//实际上是这句发送出错，被catch到APIRequestException错误
            LogHandle.info(LogType.jpush,"Got result - " + pushResult);
        } catch (APIConnectionException e) {
        	LogHandle.error(LogType.jpush,"Connection error. Should retry later. "+e.toString());
        } catch (APIRequestException e) {
        	LogHandle.error(LogType.jpush,"Error response from JPush server. Should review and fix it. "+e.toString());
            LogHandle.error(LogType.jpush,"HTTP Status: " + e.getStatus());
            LogHandle.error(LogType.jpush,"Error Code: " + e.getErrorCode());
            LogHandle.error(LogType.jpush,"Error Message: " + e.getErrorMessage());
            LogHandle.error(LogType.jpush,"Msg ID: " + e.getMsgId());
        }
    }
	//先构建一个推送所有平台、所有设备的推送对象
	public static PushPayload buildPushAllPayload(String alert) {
	        return PushPayload
	        		.alertAll(alert);
	}
	//根据别名、内容、标题，构建一个推送Android和windows phone平台的推送对象	
	//根据注册ID点对点推送消息 单个
	public static PushPayload buildAndroidWinphonePayloadwithRegID(String registrationId,
		    String alert,String title) {
		        return PushPayload.newBuilder()
		                .setPlatform(Platform.android())
		                .setAudience(Audience.registrationId(registrationId))
		                .setNotification(Notification.newBuilder()
		                .setAlert(alert)
		                .addPlatformNotification(AndroidNotification.newBuilder()
		                .setTitle(title).build())
		                .build())
		                 .build();
		    }
	//根据注册ID点对点推送消息 批量
	public static PushPayload buildAndroidWinphonePayloadwithRegID(List<String> registrationIds,
		    String alert,String title) {
		        return PushPayload.newBuilder()
		                .setPlatform(Platform.android())
		                .setAudience(Audience.registrationId(registrationIds))
		                .setNotification(Notification.newBuilder()
		                .setAlert(alert)
		                .addPlatformNotification(AndroidNotification.newBuilder()
		                .setTitle(title).build())
		                .build())
		                 .build();
		    }
	//根据别名集合批量推送消息
	public static PushPayload buildAndroidWinphonePayloadwithAlias(List<String> alias,
		    String alert,String title) {
		        return PushPayload.newBuilder()
		                .setPlatform(Platform.android())
		                .setAudience(Audience.alias(alias))
		                .setNotification(Notification.newBuilder()
		                .setAlert(alert)
		                .addPlatformNotification(AndroidNotification.newBuilder()
		                .setTitle(title).build())
		                .build())
		                 .build();
		    }
	//根据别名集合批量推送消息
	public static PushPayload buildAndroidWinphonePayloadwithTag(List<String> tags,
		    String alert,String title) {
		        return PushPayload.newBuilder()
		                .setPlatform(Platform.android())
		                .setAudience(Audience.tag(tags))
		                .setNotification(Notification.newBuilder()
		                .setAlert(alert)
		                .addPlatformNotification(AndroidNotification.newBuilder()
		                .setTitle(title).build())
		                .build())
		                 .build();
		    }
	//根据别名集合批量推送消息
		public static PushPayload buildAndroidWinphonePayloadwithTagsAnd(List<String> tags,
			    String alert,String title) {
			        return PushPayload.newBuilder()
			                .setPlatform(Platform.android())
			                .setAudience(Audience.tag_and(tags))
			                .setNotification(Notification.newBuilder()
			                .setAlert(alert)
			                .addPlatformNotification(AndroidNotification.newBuilder()
			                .setTitle(title).build())
			                .build())
			                 .build();
			    }
	//推送广播消息
	public static PushPayload buildAndroidWinphonePayload(
		    String alert,String title) {
		        return PushPayload.newBuilder()
		                .setPlatform(Platform.android())
		                .setAudience(Audience.all())
		                .setNotification(Notification.newBuilder()
		                .setAlert(alert)
		                .addPlatformNotification(AndroidNotification.newBuilder()
		                .setTitle(title).build())
		                .build())
		                 .build();
		    }
	private Properties GetProperty() throws IOException{
	 	Properties pro = new Properties();  
		 //读取配置文件
		InputStream inStream =this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
	    pro.load(inStream); 	    
	    return pro;
	}	
}

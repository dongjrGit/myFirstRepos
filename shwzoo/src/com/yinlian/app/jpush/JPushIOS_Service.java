package com.yinlian.app.jpush;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.yinlian.Extended.LogType;
import com.yl.soft.log.LogHandle;

public class JPushIOS_Service {
	
	
public void sendPush(String title,String content,String regid)throws Exception {		
		String masterSecret=GetProperty().getProperty("iosmastersecret");
		String appKey=GetProperty().getProperty("iosappkey");
		String maxRetryTimes= GetProperty().getProperty("iosofflineday");
        //创建激光推送客户端对象
        JPushClient jpushClient = new JPushClient(masterSecret, appKey,Integer.parseInt(maxRetryTimes));   
        PushPayload payload=buildIOSPayloadwithRegID(regid, content, title);
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
	//根据别名、内容、标题，构建一个推送IOS平台的推送对象
		//setPlatform 推送平台设置 ios,android,winphone
		//setAudience 推送设备指定
		//setNotification 通知内容体。是被推送到客户端的内容。与 message 一起二者必须有其一，可以二者并存
		public static PushPayload buildIOSPayload(String alert,String title) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.all())
	                .setNotification((Notification.newBuilder()
	                		.setAlert(alert)
	                		.addPlatformNotification(IosNotification.newBuilder()
	                				.setAlert(title).build()))
	                		.build())
	                 .build();
	    }
		//别名推送
		public static PushPayload buildIOSPayloadwithAlias(List<String> alias,String alert,String title) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.alias(alias))
	                .setNotification((Notification.newBuilder()
	                		.setAlert(alert)
	                		.addPlatformNotification(IosNotification.newBuilder()
	                				.setAlert(title).build()))
	                		.build())
	                 .setOptions(Options.newBuilder().setApnsProduction(false).build())                  
	                 .build();
	    }
		//注册ID 点对点推送
		public static PushPayload buildIOSPayloadwithRegID(String registrationId,String alert,String title) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.registrationId(registrationId))
	                .setNotification((Notification.newBuilder()
	                		.setAlert(alert)
	                		.addPlatformNotification(IosNotification.newBuilder()
	                				.setAlert(title).build()))
	                		.build())
	                 .build();
	    }
		//注册ID集合 批量推送
		public static PushPayload buildIOSPayloadwithRegID(List<String> registrationId,String alert,String title) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.registrationId(registrationId))
	                 .setNotification((Notification.newBuilder()
	                		.setAlert(alert)
	                		.addPlatformNotification(IosNotification.newBuilder()
	                				.setAlert(title).build()))
	                		.build())
	                 .build();
	    }
		
		//标签 or批量推送
		public static PushPayload buildIOSPayloadwithTags(List<String> tags,String alert,String title) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.tag(tags))
	                .setNotification((Notification.newBuilder()
	                		.setAlert(alert)
	                		.addPlatformNotification(IosNotification.newBuilder()
	                				.setAlert(title).build()))
	                		.build())
	                 .build();
	    }
		//标签 and批量推送
		public static PushPayload buildIOSPayloadwithTagsAnd(List<String> tags,String alert,String title) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.tag_and(tags))
	                .setNotification((Notification.newBuilder()
	                		.setAlert(alert)
	                		.addPlatformNotification(IosNotification.newBuilder()
	                				.setAlert(title).build()))
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

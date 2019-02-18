package com.yinlian.wssc.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.redis.RedisUserInfo;

public class SessionState {

	/**
	 * 获取当前用户信息
	 * 
	 * @param token
	 * @return code 0 获取成功 -1 未登陆或登陆超时
	 */
	public static SessionUser GetCurrentUser(String token) {
		if(token==null)
			token="";
		String userInfo = RedisUserInfo.Get(token);
		SessionUser sessionUser;
		if (userInfo == null) {
			sessionUser = new SessionUser();
			sessionUser.setCode(-1);
		}
		sessionUser = (SessionUser) JsonUtil.getObjectFromJsonString(userInfo, SessionUser.class);
		if (sessionUser != null) {
			return sessionUser;
		} else {
			sessionUser = new SessionUser();
			sessionUser.setCode(-1);
			return sessionUser;
		}
	}
	/*
	 * public static void SetSessionUser(String token, SessionUser user) {
	 * ShardedJedis_User.Set(token, JsonUtil.getJsonStringFromObject(user)); }
	 */

	/**
	 * 获取当前用户信息
	 * 
	 * @return code 0 获取成功 -1 未登陆或登陆超时
	 */
	public static SessionUser GetCurrentUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Cookie[] cookies = request.getCookies();
		SessionUser sessionUser;
		String token = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("_u_token")) {
					token = cookie.getValue();
					break;
				}
			}
			String userInfo = RedisUserInfo.Get(token);
			if (StringUtilsEX.IsNullOrWhiteSpace(userInfo)) {
				sessionUser = new SessionUser();
				sessionUser.setCode(-1);
			}else {
				sessionUser = (SessionUser) JsonUtil.getObjectFromJsonString(userInfo, SessionUser.class);
			}			
		} else {
			sessionUser = new SessionUser();
			sessionUser.setCode(-1);
		}
		return sessionUser;
	}

	/**
	 * 保存 登陆用户信息
	 * 
	 * @param token
	 * @param user
	 */
	public static void SetSessionUser(String token, SessionUser user) {
		RedisUserInfo.Set(token, JsonUtil.getJsonStringFromObject(user));
	}

	/**
	 * 延迟Token过期时间
	 * 
	 * @param token
	 * @param user
	 */
	public static int DelayTokenTimeOut(String token) {
		String val = RedisUserInfo.Get(token);
		if(StringUtilsEX.IsNullOrWhiteSpace(val)){
			return -1;
		}
		RedisUserInfo.Set(token, val, 1 * 5 * 60);
		return 0;
	}

	/**
	 * 延迟Token过期时间
	 * 
	 * @param token
	 * @param user
	 */
	public static int DelayTokenTimeOut() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Cookie[] cookies = request.getCookies();
		String token = "";
		int res=0;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("_u_token") ) {
					token = cookie.getValue();
					break;
				}
			}
			String val = RedisUserInfo.Get(token);
			if(!StringUtilsEX.IsNullOrWhiteSpace(val)){
				RedisUserInfo.Set(token, val, 1 * 5 * 60);
			}
			else{
				res=-1;
			}
		}else {
			res=-1;
		}
		return res;
	}

}

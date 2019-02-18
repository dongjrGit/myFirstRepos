/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.techown.wssc.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.SessionUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @author lenovo
 *
 */
public class LoginPlatformInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LoginPlatformInterceptor.class);
	@Autowired
	private AccountsService accountsService;

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		logger.debug("The Method is 'afterCompletion' of [ " + getClass().getSimpleName() + " ]");
	}

	/**
	 * 在业务处理器处理完请求后，但是DispatcherServlet向客户端返回请求前被调用
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		logger.debug("The Method is 'postHandle' of [ " + getClass().getSimpleName() + " ]");
	}

	/**
	 * 业务处理器处理请求之前被调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 后台登录拦截器
		SessionUser sessionUser = SessionUtil.getSessionUser(request);
		if (sessionUser != null && sessionUser.getCode() == 0) {
			Integer userid = sessionUser.getUserId();
			if (userid != null) {
				Integer usertype = accountsService.queryByuserid(userid).getUsertype();
				if (usertype != null) {
					if (SessionUtil.checkUserType(request, ConstanValue.PLATFORM_MARK, usertype)) {
						return true;
					}
				}
			}
		} else {
			if ((request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) {
				response.setHeader("sessionstatus", "timeout");
				PrintWriter pw = null;
				try {
					pw = response.getWriter();
					JSONObject commonJson = JSONObject.fromObject("{\"msg\":\"系统登录超时，请重新登录\",\"success\":\"true\"}");
					response.setContentType("text/html");
					response.setCharacterEncoding("UTF-8");
					pw.print(commonJson);
					pw.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("ajax session exception"+request.getRequestURI(), e);;
				} finally {
					if (null != pw) {
						pw.close();
					}
				}
				return false;
			}
		}
		response.sendRedirect("/platform/login");
		return false;
	}
}

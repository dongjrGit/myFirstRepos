/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.interceptor;

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

/**
 * The Class is for Interceptor Login Platform This
 * @author Administrator
 * @version $Id: LoginInterceptor.java, v 0.1 2016年2月22日 下午4:00:42 Administrator Exp $
 */
public class LoginPlatformInterceptor implements HandlerInterceptor {

    /**
     * This is Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginPlatformInterceptor.class);
    @Autowired
    private AccountsService     accountsService;

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
                                Exception arg3) throws Exception {
        logger.debug("The Method is 'afterCompletion' of [ " + getClass().getSimpleName() + " ]");
    }

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
                           ModelAndView arg3) throws Exception {
        logger.debug("The Method is 'postHandle' of [ " + getClass().getSimpleName() + " ]");
    }

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        //后台登录拦截器
        String url = request.getRequestURI();
        if (url.indexOf(ConstanValue.PLATFORM_LOGIN_URL) >= 0
            || url.indexOf(ConstanValue.PLATFORM_TOLOGIN_URL) > 0
            || url.indexOf(ConstanValue.PLATFORM_INDEX_RIGTHURL) > 0) {
            return true;
        }
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
        }
        //转发到登陆页面
        response.sendRedirect("/platform/login");
        return false;
    }
}

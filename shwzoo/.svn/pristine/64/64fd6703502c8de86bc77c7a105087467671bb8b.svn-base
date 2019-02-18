/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import javax.servlet.http.HttpServletRequest;

import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.wssc.web.dto.SessionUser;

/**
 * SessionUtil.java
 * @author Administrator
 * @version $Id: SessionUtil.java, v 0.1 2016年3月25日 下午4:42:56 Administrator Exp $
 */
public class SessionUtil {

    /**
     * 先获取token再获取SessionUser
     * 
     * @param request
     * @return
     * @throws Exception 
     */
    public static SessionUser getSessionUser(HttpServletRequest request) throws Exception {
        SessionUser sessionUser = new SessionUser();
        String token = CookieUtils.getTokenFromCookie(request);
        if (StringUtils.isNotNull(token)) {
            sessionUser = SessionState.GetCurrentUser(token);
        } else {
            sessionUser.setCode(-1);
        }
        return sessionUser;
    }

    /**
     * 获取到当前用户的userid
     * 
     * @param request
     * @return
     * @throws Exception 
     */
    public static Integer getSessionUserId(HttpServletRequest request) throws Exception {
        SessionUser sessionUser = null;
        String token = CookieUtils.getTokenFromCookie(request);
        if (StringUtils.isNotNull(token)) {
            sessionUser = SessionState.GetCurrentUser(token);
            if (sessionUser != null) {
                return sessionUser.getUserId();
            }
        }
        return null;
    }

    /**
     * 获取当前用户的shopid集合
     * 
     * @param request
     * @return
     * @throws Exception 
     */
    public static Integer getSessionUserShopId(HttpServletRequest request) throws Exception {
        SessionUser sessionUser = null;
        String token = CookieUtils.getTokenFromCookie(request);
        if (StringUtils.isNotNull(token)) {
            sessionUser = SessionState.GetCurrentUser(token);
            if (sessionUser != null) {
                return sessionUser.getShopid();
            }
        }
        return null;
    }

    /**
     * 验证用户类型
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public static boolean checkUserType(HttpServletRequest request, String mark, Integer usertype) {
        boolean flag = false;
        if (usertype != null) {
            switch (mark) {
                case ConstanValue.PLATFORM_MARK:
                    if (usertype.equals(UserTypeEnum.Admin.getValue())
                        || usertype.equals(UserTypeEnum.SupAdmin.getValue())) {
                        return true;
                    }
                    break;
                case ConstanValue.SELLER_MARK:
                    if (usertype.equals(UserTypeEnum.Seller.getValue())
                        || usertype.equals(UserTypeEnum.Employee.getValue())) {
                        flag = true;
                    }
                    break;
            }
        }
        return flag;
    }
}

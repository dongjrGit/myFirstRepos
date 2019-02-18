/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * CookieUtils.java
 * @author Administrator
 * @version $Id: CookieUtils.java, v 0.1 2016年3月26日 上午10:13:24 Administrator Exp $
 */
public class CookieUtils {

    /**
     * 获取cookie中的token
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public static String getTokenFromCookie(HttpServletRequest request) throws Exception {
        String token = "";
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            System.out.println("Cookie长度:" + cookies.length); //读取本机共存在多少COOKIE
            for (int i = 0; i < cookies.length; i++) {
                if (ConstanValue.TOKEN_KEY.equals(cookies[i].getName())) {
                    token = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                    System.out.println("For 内部Cookie : " + token);
                    break;
                }
            }
        } else {
            System.out.println("没有Cookie");
        }

        return token;
    }

    /**
     * 获取验证码
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public static String getVCodeFromCookie(HttpServletRequest request) {
        String vCode = "";
        try {
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                System.out.println("Cookie长度:" + cookies.length); //读取本机共存在多少COOKIE
                for (int i = 0; i < cookies.length; i++) {
                    if (ConstanValue.VALIDATA_CODE.equals(cookies[i].getName())) {
                        vCode = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                        System.out.println("For 内部Cookie : " + vCode);
                        break;
                    }
                }
            } else {
                System.out.println("没有Cookie");
            }
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
        return vCode;
    }
}

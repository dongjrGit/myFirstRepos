package com.yinlian.wssc.web.util;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class GetCookie {

	public static String getcookies(String cookiename )throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Cookie[] cookies = request.getCookies();
		String cookiesvalue = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookiename)) {
					cookiesvalue=URLDecoder.decode(cookie.getValue(), "UTF-8");  
					break;
				}
			}
		}
		return cookiesvalue;
	}
}

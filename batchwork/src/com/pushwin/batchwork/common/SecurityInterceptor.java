package com.pushwin.batchwork.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityInterceptor implements HandlerInterceptor {
	
	//private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);
	
	private List<String> excludeUrls;// 不需要拦截的资源
	public static Map<String, String> map = new HashMap<String, String>();
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		map.remove(arg0.getParameter("deviceNumber"));
		System.out.println("over");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		
		if(map.size() > 2 && !map.containsKey(request.getParameter("deviceNumber"))){
			response.getWriter().write("<b>Only " + map.size() + " device allowed!!</b>");
			response.setCharacterEncoding("GBK");
			return false;
		}
		
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put(request.getParameter("deviceNumber"), dateFormater.format(new Date()));
		return true;
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

}

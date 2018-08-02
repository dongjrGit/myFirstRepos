package com.mobile.application.commmon.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.mobile.application.vo.session.IpInfoVO;
import com.mobile.application.vo.session.SessionVO;

public class SessionListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		Map<String, IpInfoVO> onlineUser = (Map<String, IpInfoVO>) servletContext.getAttribute("onlineUser");
		String userCode = ((SessionVO)sessionEvent.getSession().getAttribute("sessionVO")).getUserCode();
		if(onlineUser.containsKey(userCode))onlineUser.remove(userCode);
	}
	
}

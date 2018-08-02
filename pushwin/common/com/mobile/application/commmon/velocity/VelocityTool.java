package com.mobile.application.commmon.velocity;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class VelocityTool {
	
	
	public ServletContext getApplication() {
		//获取ServletContext
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        ServletContext servletContext = webApplicationContext.getServletContext(); 
        return servletContext;
	}
}

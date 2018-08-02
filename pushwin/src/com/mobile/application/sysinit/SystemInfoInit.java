package com.mobile.application.sysinit;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ServletContextAware;

public class SystemInfoInit  implements InitializingBean,ServletContextAware{

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setAttribute("sysLogo", "111111");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}

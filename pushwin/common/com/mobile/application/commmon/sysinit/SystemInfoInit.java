package com.mobile.application.commmon.sysinit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.service.credit.util.CreditExcelModel;
import com.mobile.application.service.debit.util.DebitExcelModel;
import com.mobile.application.service.dectionary.IDectService;
import com.mobile.application.service.log.ILogService;
import com.mobile.application.service.sys.ISysService;
import com.mobile.application.vo.session.IpInfoVO;
import com.techown.tpush.http.BaseURL;

public class SystemInfoInit  implements InitializingBean,ServletContextAware{
	
	protected Logger logger = LoggerFactory.getLogger(SystemInfoInit.class);
	
	@Autowired
	IDectService dectService;
	@Autowired
	ILogService logService;
	@Autowired
	ISysService sysService;
	@Autowired
	CreditExcelModel creditExcelModel;
	@Autowired
	DebitExcelModel debitExcelModel;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		//加载数据字典
		servletContext.setAttribute("dictMap", dectService.qryAllDict());
		//加载系统日志
		servletContext.setAttribute("logMap", logService.qryLogTypeMap());
		//加载系统设置
		servletContext.setAttribute("sysMap", sysService.qrySysSetting());
		//加载信用卡审核模版
		try {
			servletContext.setAttribute("creditModel", creditExcelModel.readExcelModel());
		} catch (IOException e) {
			logger.error("严重错误：加载信用卡审核模版失败！", e);
		}
		try {
			servletContext.setAttribute("debitModel", debitExcelModel.readDebitExcelModel());
		} catch (IOException e) {
			logger.error("严重错误：加载借记卡审核模版失败！", e);
		}
		//在线用户初始化
		Map<String, IpInfoVO> onlineUser = new HashMap<String, IpInfoVO>();
		servletContext.setAttribute("onlineUser", onlineUser);
		String rootPath = (String) SpringProperty.props.get("RootPath");
		File rootDir = new File(rootPath);
		if(!rootDir.exists()){
			rootDir.mkdirs();
		}
		//设置TPush请求地址
		BaseURL.HOST_NAME_SSL= (String) SpringProperty.props.get("HOST_NAME_SSL");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

}

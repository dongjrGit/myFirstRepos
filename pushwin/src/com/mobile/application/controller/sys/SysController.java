package com.mobile.application.controller.sys;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.entity.TBasisSystem;
import com.mobile.application.service.sys.ISysService;

@Controller
@RequestMapping("/sys")
public class SysController {
	
	@Autowired
	private ISysService sysService;
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/setting")
	public ModelAndView settingInit(ModelAndView mw){
		Map<String, String> map = sysService.qrySysSetting();
		return new ModelAndView("sys/sysSetting", map);
	}
	
	@RequestMapping("/qry")
	public Map<String, String> qrySysSetting() {
		return sysService.qrySysSetting();
	}
	
	@RequestMapping("/save")
	public ModelAndView saveSysSetting(@RequestParam(value = "logoImgFile", required = false)MultipartFile file, TBasisSystem tBasisSystem, HttpSession session) throws BusinessException, IOException {
		sysService.saveSysSetting(file, tBasisSystem);
		servletContext.setAttribute("sysMap", sysService.qrySysSetting());
		Map<String, String> map = sysService.qrySysSetting();
		return new ModelAndView("sys/sysSetting", map);
	}
}

package com.mobile.application.controller.checkcard;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.service.checkcard.ICheckCardService;
import com.mobile.application.vo.CommonVO;

@Controller
@RequestMapping("/checkcard")
public class CheckCardController {
	@Autowired
	private ICheckCardService checkCardService;
	/*
	 * 进入复核产品参数模板配置页面
	 * */
	@RequestMapping("/configCheckCard")
	public String init() throws IOException{
		return "checkcard/configCheckCard";
	}
	/*
	 * 导出复核产品参数excel表
	 * */
	@RequestMapping("/exportmodle")
	public void exportModle(HttpServletResponse response,String modleCode) throws IOException, BusinessException{
		 this.checkCardService.exportModle(response,modleCode);
	}
	/*
	 * 导入复核产品参数excel表
	 * */
	@RequestMapping("/importmodle")
	@ResponseBody
	public CommonVO  importmodle(@RequestParam(value = "excelfile", required = false)MultipartFile file, HttpSession session,HttpServletRequest request,String modleCode) throws BusinessException, IOException{
		return this.checkCardService.importmodle(file,session,request,modleCode);
	}
	/*
	 * 从数据库查询复核产品参数excel表信息
	 * */
	@RequestMapping("/qrymodle")
	@ResponseBody
	public List<?> qrymodle(HttpServletResponse response) throws IOException{
		return this.checkCardService.qrymodle();
	}
}

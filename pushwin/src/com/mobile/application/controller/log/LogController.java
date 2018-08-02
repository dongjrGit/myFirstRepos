package com.mobile.application.controller.log;


import java.util.Map;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.service.log.ILogService;
import com.mobile.application.vo.CommonVO;

/** 
 * Copy Right Information   :Techown, 2015
 * Project                  : pushwin
 * Author					: 姜瑞
 * JDK version used         : jdk1.6
 * Comments                 : 操作日志配置，通用功能 
 * Version                  : 1.01
 * Modification history     : 2005.9.27
 * Sr Date   Modified By   Why & What is modified
 * 1. 2005.9.27 姜瑞     新建 
 **/
@Controller
@RequestMapping("log")
public class LogController {
	
	@Autowired
	private ILogService logService;
	@Autowired
	private ServletContext servletContext;
	
	/**
	 * Description : 操作日志配置，初始化页面
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param N/A
	 * @return 操作日志配置界面
	 * @exception BusinessException
	 */
	@RequestMapping("init")
	public String init(){
		return "log/log_type";
	}
	
	/**
	 * Description : 获取操作日志类型列表
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param N/A
	 * @return 查询结果集
	 * @exception BusinessException
	 */
	@RequestMapping("qryLogType")
	@ResponseBody
	public CommonVO qryLogType(String modelName){
		CommonVO commonVO = logService.qryLogType(modelName);
		return commonVO;
	}
	
	/**
	 * Description :保存操作日志类型
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param url : 操作请求相对路径
	 * @param modelName : 模块名称
	 * @param operaName : 操作名称
	 * @param description : 操作描述
	 * @param option : 新增、修改
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	@RequestMapping("saveLogType")
	@ResponseBody
	public CommonVO saveLogType(String url, String modelName, String operaName, String description, String option) throws BusinessException{
		
		try{
			return logService.saveLogType(url, modelName, operaName, description, option);
		} catch(DataIntegrityViolationException e) {
		        throw new BusinessException("请求路径重复");
		}
	}
	
	/**
	 * Description :删除操作日志类型
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param String uri : 操作请求相对路径
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	@RequestMapping("removeLogType")
	@ResponseBody
	public CommonVO removeLogType(String uri){
		return logService.removeLogType(uri);
	}
	
	/**
	 * Description :操作日志类型立即生效
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param N/A
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	@RequestMapping("activeLogType")
	@ResponseBody
	public CommonVO activeLogType() throws BusinessException{
		//加载系统日志
		try{
			servletContext.setAttribute("logMap", logService.qryLogTypeMap());
			return new CommonVO(true, "操作成功");
		} catch (Exception e) {
			throw new BusinessException("操作失败");
		}
	}
	
	/**
	 * Description : 显示后台操作日志页面
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param N/A
	 * @return 后台操作日志界面
	 * @exception BusinessException
	 */
	@RequestMapping("/detailInit")
	public String detailInit(){
		return "log/log_detail";
	}
	
	/**
	 * Description : 获取后台操作日志列表
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param int pageIndex : 分页
	 * @param int pageSize : 每页大小
	 * @param String modelName : 模块名称
	 * @param String userCode : 操作人工号
	 * @param String userName ：操作人姓名
	 * @return 查询结果集
	 * @exception BusinessException
	 */
	@RequestMapping("/qryLogDetail")
	@ResponseBody
	public Map<String, Object> qryLogDetail(int pageIndex,int pageSize,String modelName,String userCode,String startTime,String endTime){
		return this.logService.qryLogDetail(pageIndex,pageSize,modelName,userCode,startTime,endTime);
	}
	
	/**
	 * Description : 后台操作日志导出
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param response
	 * @param modelname
	 * @param usercode
	 * @param starttime
	 * @param endtime
	 * @exception
	 */
	@RequestMapping("/exportLogReportExcel")
	@ResponseBody
	public void exportLogReportExcel(HttpServletResponse response,String modelname,String usercode,String starttime,String endtime){
		this.logService.exportLogReportExcel(response,modelname,usercode,starttime,endtime);
	}
}

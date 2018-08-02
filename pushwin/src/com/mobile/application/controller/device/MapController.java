package com.mobile.application.controller.device;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WriteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.service.device.IMapService;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	IMapService mapService;
	
	/**
	 * Description : 进入设备轨迹地图页面
     * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 设备轨迹地图页面
	 * @exception  
	 */
	@RequestMapping("/init")
	public String deviceAllotInit(HttpSession session, HttpServletRequest request) {
		return "position/device_position";
	}
	
	/**
	 * 设备轨迹查询
	 * @param userId
	 * @param updateTime
	 * @return
	 */
	@RequestMapping("/qryDevicePosition")
	@ResponseBody
	public List<?> qryDevicePosition(String userId,String updateTime){
		return mapService.qryDevicePosition(userId, updateTime);
	}
	
	/**
	 * 导出设备轨迹
	 * @param response
	 * @param excelData
	 * @throws WriteException
	 * @throws IOException
	 */
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletResponse response,String excelData) throws WriteException, IOException{
		mapService.exportExcel(response,excelData);
	}
	
	/**
	 * 进入实时轨迹页面
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/realTimePosition")
	public String realTimePosition(HttpSession session, HttpServletRequest request) {
		return "position/realTime_position";
	}
	
	/**
	 * 查询实时设备轨迹
	 * @param session
	 * @param request
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/qryRealTimePosition")
	@ResponseBody
	public List<?> qryRealTimePosition(HttpSession session, HttpServletRequest request,String orgId){
		return mapService.qryRealTimePosition(session,request,orgId);
	}
}

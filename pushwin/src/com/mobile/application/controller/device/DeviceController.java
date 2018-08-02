package com.mobile.application.controller.device;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.service.device.IDeviceService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.device.DeviceInfoVO;

/** 
 * Copy Right Information   :Techown, 2015
 * Project                  : pushwin
 * Author					: 罗杨
 * JDK version used         : jdk1.6
 * Comments                 : 设备管理
 * Version                  : 1.01
 * Modification history     : 2015.9.27
 * Sr Date   Modified By   Why & What is modified
 * 1. 2015.9.27 罗杨     新建 
 **/
@Controller
@RequestMapping("/device")
public class DeviceController {
	
	@Autowired
	private IDeviceService deviceService;
	
	/**
	 * Description : 进入设备调拨页面
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 设备调拨页面
	 * @exception  
	 */
	@RequestMapping("/deviceAllotInit")
	public String deviceAllotInit(HttpSession session, HttpServletRequest request) {
		return "device/deviceAllot";
	}
	
	/**
	 * Description : 设备列表查询
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param pageIndex ： 分页
	 * @param pageSize ： 分页大小
	 * @param deptId ： 机构id
	 * @param userCode :  员工号
	 * @param pinCode ：  设备pin号
	 * @param simCode ：  设备sim号
	 * @param userName ： 员工姓名
	 * @param startTime ： 开始时间
	 * @param endTime ： 结束时间
	 * @param session 
	 * @return 查询结果集
	 * @throws ParseException
	 */
	@RequestMapping("/deviceList")
	@ResponseBody
	public Map<String, Object> deviceList(int pageIndex,int pageSize,String deptId,String userCode,String pinCode,String simCode,String userName,String startTime,String endTime,HttpSession session) throws ParseException {
		return deviceService.deviceList(pageIndex,pageSize,deptId,userCode,pinCode,simCode,userName,startTime,endTime,session);
		
	}
	
	/**
	 * Description : 弹出设备调拨机构选择树
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 设备调拨机构选择树
	 */
	@RequestMapping("/selectOrgTreeWindow")
	public String selectOrgTreeWindow(HttpSession session, HttpServletRequest request){
		return "device/selectOrgTreeWindow";
	}
	
	
	/**
	 * Description : 机构选择树数据加载
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 查询结果集
	 * @exception
	 */
	@RequestMapping("/showOrgTreeAjax")
	@ResponseBody
	public List<?> showOrgTreeAjax(HttpSession session){
		return deviceService.showOrgTreeAjax(session);
	}
	
	
	/**
	 * Description : 设备调拨人员选择列表
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 人员选择列表
	 */
	@RequestMapping("/selectUserTreeWindow")
	public String selectUserTreeWindow(HttpSession session, HttpServletRequest request){
		return "device/selectUserTreeWindow";
	}
	
	
	/**
	 * Description : 人员选择数据加载
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param pageIndex ： 分页
	 * @param pageSize ： 分页大小
	 * @param orgId ： 机构号
	 * @return 查询结果集
	 * @exception
	 */
	@RequestMapping("/showUserTreeAjax")
	@ResponseBody
	public Map<?,?> showUserTreeAjax(HttpSession session, int pageIndex,int pageSize,String orgId,String key){
		return deviceService.showUserTreeAjax(session,pageIndex,pageSize,orgId,key);
	}
	
    /**
     * Description : 设备回收,查询出当前登录人的机构和工号返回到机构和人员选择框中
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
     * @param session
     * @return 查询结果集
     * @exception
     */
	@RequestMapping("/reclaimDevice")
	@ResponseBody
	public Map<?,?> reclaimDevice(HttpSession session){
		return deviceService.reclaimDevice(session);
	}

	/**
	 * Description : 进入设备出库入库页面
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @param response
	 * @return 设备出库入库页面
	 * @exception
	 */
	@RequestMapping("/deviceManage")
	public String device(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		//response.setHeader("wms-token", "user");
		request.setAttribute("log-token", "user");
		return "device/device";
	}
    
	
	/**
	 * Description : 设备回收信息保存
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param deviceType
	 * @param orgId : 机构id
	 * @param combolData ： 回收员工的员工id
	 * @param deviceId ： 设备id
	 * @return 操作执行结果
	 * @exception
	 */
	@RequestMapping("/reclaimSave")
	@ResponseBody
	public CommonVO reclaimSave(HttpSession session,String deviceType,String orgId,String combolData,String deviceId){
		return deviceService.reclaimSave(session,deviceType,orgId,combolData,deviceId);
	}


	/**
	 * Description : 设备出库
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param userCode ： 员工号
	 * @param orgId ： 机构id
	 * @param password ： 密码
	 * @param deviceId ： 设备id
	 * @param request
	 * @return 操作执行结果
	 * @exception
	 */
	@RequestMapping("/deviceOutbound")
	@ResponseBody
	public CommonVO deviceOutbound(HttpSession session,String userCode,String orgId,String password,String deviceId, HttpServletRequest request){
		return deviceService.deviceOutbound(session,userCode ,orgId,password,deviceId,request);
	}
	
	/**
	 * Description : 设备入库
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param deviceId ： 设备id
	 * @param request
	 * @return 操作执行结果
	 * @exception
	 */
	@RequestMapping("/saveDeviceStorage")
	@ResponseBody
	public CommonVO saveDeviceStorage(HttpSession session,String deviceId, HttpServletRequest request){
		return deviceService.saveDeviceStorage(session,deviceId,request);
	}
    
	
	/**
	 * Description : 设备调拨信息保存
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param deviceType ： 操作类型
	 * @param orgId ： 机构id
	 * @param combolData ： 接收员工id
	 * @param deviceId ： 设备id
	 * @param userCode ： 授权页面中的员工号
	 * @param userPwd ：授权页面中的密码
	 * @return 操作执行结果
	 * @exception
	 */
	@RequestMapping("/allotSave")
	@ResponseBody
	public CommonVO allotSave(HttpSession session,String deviceType,String orgId,String combolData,String deviceId,String userCode,String userPwd){
		return deviceService.allotSave(session,deviceType,orgId,combolData,deviceId,userCode,userPwd);
	}
	
	/**
	 * Description : 检查设备调拨机构是否为子机构
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param deviceId ： 设备id
	 * @param orgId ： 机构id
	 * @return 操作执行结果
	 * @exception
	 */
	@RequestMapping("/checkSub")
	@ResponseBody
	public CommonVO checkSub(HttpSession session,String deviceId,String orgId){
		return deviceService.checkSub(session,deviceId,orgId);
	}
	
	
	/**
	 * Description : 弹出设备调拨历史记录页面
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 历史记录页面
	 * @exception
	 */
    @RequestMapping("/deviceRecordWindow")
    public String deviceRecordWindow(HttpSession session, HttpServletRequest request){
    	return "device/deviceRecordWindow";
    }
    
    
    /**
     * Description : 加载设备调拨历史记录数据
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
     * @param pageIndex ： 分页
     * @param pageSize ： 分页大小
     * @param pinId ： 设备pin号
     * @return 查询结果集
     * @exception
     */
    @RequestMapping("/findRecord")
    @ResponseBody
    public Map<String, Object> findRecord(int pageIndex,int pageSize,String pinId){
		return deviceService.findRecord(pageIndex,pageSize,pinId);
    	
    }
    
    /**
     * Description : 进入设备轨迹页面
     * @author 
	 * @version 1.01
	 * @see N/A
     * @param session
     * @param request
     * @return 设备轨迹页面
     * @exception
     */
    @RequestMapping("/deviceTrack")
    public String deviceTrack(HttpSession session, HttpServletRequest request){
    	return "device/deviceTrack";
    }
    
    /**
     * Description : 保存设备信息
     * @author 
	 * @version 1.01
	 * @see N/A
     * @param session
     * @param request
     * @return 
     * @exception
     */
    @RequestMapping("/saveDevice")
    @ResponseBody
    public CommonVO saveDevice(@RequestBody DeviceInfoVO deviceInfoVO){
    	CommonVO commonVO = deviceService.saveDevice(deviceInfoVO);
    	return commonVO;
    }
    
    /**
     * Description : 保存设备信息
     * @author 
	 * @version 1.01
	 * @see N/A
     * @param session
     * @param request
     * @return 
     * @exception
     */
    @RequestMapping("/removeDevice")
    @ResponseBody
    public CommonVO removeDevice(String reId){
    	CommonVO commonVO = deviceService.removeDevice(reId);
    	return commonVO;
    }
}

package com.mobile.application.service.device;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.device.DeviceInfoVO;



public interface IDeviceService {

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
	Map<String, Object> deviceList(int pageIndex, int pageSize, String deptId,
			String userCode, String pinCode, String simCode, String userName,
			String startTime, String endTime, HttpSession session) throws ParseException;

	/**
	 * Description : 机构选择树数据加载
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 查询结果集
	 * @exception
	 */
	List<?> showOrgTreeAjax(HttpSession session);

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
	Map<?,?> showUserTreeAjax(HttpSession session, int pageIndex, int pageSize, String orgId, String key);

    /**
     * Description : 设备回收,查询出当前登录人的机构和工号返回到机构和人员选择框中
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
     * @param session
     * @return 查询结果集
     * @exception
     */
	Map<?,?> reclaimDevice(HttpSession session);

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
	CommonVO deviceOutbound(HttpSession session, String userCode, String orgId, String password,String deviceId, HttpServletRequest request);

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
	CommonVO saveDeviceStorage(HttpSession session, String deviceId, HttpServletRequest request);

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
	CommonVO reclaimSave(HttpSession session, String deviceType, String orgId,
			String combolData, String deviceId);

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
	CommonVO allotSave(HttpSession session, String deviceType, String orgId,
			String combolData, String deviceId, String userCode, String userPwd);
	
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
	CommonVO checkSub(HttpSession session,String deviceId, String orgId);

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
	Map<String, Object> findRecord(int pageIndex, int pageSize, String pinId);

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
	CommonVO saveDevice(DeviceInfoVO deviceInfoVO);

	CommonVO removeDevice(String reId);

}

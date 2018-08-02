package com.mobile.application.service.device.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mobile.application.commmon.tools.IpUtil;
import com.mobile.application.commmon.tools.MD5Util;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.dao.IDeviceDao;
import com.mobile.application.entity.LogModuleOperation;
import com.mobile.application.entity.TBasisDevice;
import com.mobile.application.entity.TBasisDeviceInfo;
import com.mobile.application.entity.TBasisDeviceTransferLog;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.service.device.IDeviceService;
import com.mobile.application.service.org.IOrgService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.device.DeviceInfoVO;
import com.mobile.application.vo.session.SessionVO;

@Service
public class DeviceServiceImpl implements IDeviceService{
	
	@Autowired
	private IDeviceDao deviceDao;
	@Autowired
	private IOrgService orgService;
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#deviceList(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpSession)
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> deviceList(int pageIndex, int pageSize, String deptId,String userCode, String pinCode, String simCode, String userName,String startTime, String endTime, HttpSession session) throws ParseException{
		// 根据机构ID查询出相应设备
		List<?> list = this.deviceDao.findDevice(deptId, pageIndex,pageSize, userCode, pinCode, simCode,userName,startTime,endTime);
		List<?> listSize = this.deviceDao.findDevice(deptId, userCode, pinCode, simCode,userName,startTime,endTime);
		//this.saveLog(SysConst.DEVICE_SBTB, "进入设备调拨列表", SysConst.LIST,"1");
		// 将集合转换成json数组
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("total", listSize);
		return map;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#showOrgTreeAjax(javax.servlet.http.HttpSession)
	 */
	@Transactional
	public List<?> showOrgTreeAjax(HttpSession session){
		SessionVO user = (SessionVO)session.getAttribute("sessionVO");
		String orgId = null;
		if(!"1".equals(user.getIsAdmin())){
			List<?> list = this.deviceDao.findOrgByUser(user.getUserId());
			orgId = list.get(0).toString();
		}
		List<?> treeList = this.deviceDao.findOrgTree(user,orgId);
		return treeList;
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#showUserTreeAjax(javax.servlet.http.HttpSession, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Map<?,?> showUserTreeAjax(HttpSession session,int pageIndex, int pageSize, String orgId,String key) {
		List<?> list = this.deviceDao.findUser(orgId, pageIndex,pageSize,key);
		List<?> listSize = this.deviceDao.findUser(orgId,key);
		// 将集合转换成json数组
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("total", listSize);
		return map;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#reclaimDevice(javax.servlet.http.HttpSession)
	 */
	@Transactional(readOnly = true)
	public Map<String ,Object> reclaimDevice(HttpSession session){
		SessionVO user = (SessionVO)session.getAttribute("sessionVO");
		Map<String ,Object> map = new HashMap<String ,Object>();
		if(!"1".equals(user.getIsAdmin())){
			List<?> list = this.deviceDao.findOrgByUser(user.getUserId());
			JSONArray json = JSONArray.fromObject(list);
			JSONObject obj = json.getJSONObject(0);
			String orgId = obj.getString("orgId");
			String orgName = obj.getString("orgName");
			map.put("orgId", orgId);
			map.put("orgName", orgName);
			map.put("userId", user.getUserId());
			map.put("userName", user.getUserName());
		}
		return map;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#reclaimSave(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public synchronized CommonVO reclaimSave(HttpSession session, String deviceType, String orgId,String combolData, String deviceId){
		SessionVO sessionvo = (SessionVO)session.getAttribute("sessionVO");
		TBasisDevice device = (TBasisDevice) this.deviceDao.getByID(TBasisDevice.class,deviceId);
		TBasisOrg org = (TBasisOrg) this.deviceDao.getByID(TBasisOrg.class,orgId);
		TBasisUser user = (TBasisUser) this.deviceDao.getByID(TBasisUser.class,combolData);
		TBasisUser userOperate = (TBasisUser) this.deviceDao.getByID(TBasisUser.class,sessionvo.getUserId());//当前登录用户
		// 获取系统时间
		Date date = new Date();
		List<?> list = this.orgService.qryOrgParentTree(orgId);
		if(!"1".equals(userOperate.getIsAdmin())){
			if(list.contains(org.getOrgId())){
				if(user.getUserId().equals(device.gettBasisUser().getUserId())){
					return new CommonVO(false,"设备已属于该员工");
				}else{
				device.setPadStatus("0");
				device.settBasisOrg(org);
				device.settBasisUser(user);
				device.setRecipientTime(DateUtil.format(date));
				device.setUpdateTime(DateUtil.format(date));
				this.deviceDao.save(device);
				
				// 保存系统日志
				LogModuleOperation log = new LogModuleOperation();
				log.setModuleId("DEVICE");
				log.setOperateCode(userOperate.getUserCode());
				log.setOperateOrg(userOperate.getTBasisOrg());
				log.setOperateTime(date);
				log.setOperateType("添加");
				log.setDescription("设备回收");
				log.setOperateUser(userOperate);
				this.deviceDao.save(LogModuleOperation.class,log);
//				this.saveLog(SysConst.DEVICE_SBGL, "保存调度设备信息 ", SysConst.SAVE,"1");
				
				// 保存历史记录
				TBasisDeviceTransferLog deviceLog = new TBasisDeviceTransferLog();
				deviceLog.setOperatorUser(userOperate);
				deviceLog.setOperatorTime(date);
				deviceLog.setOperatorType("回收");
				deviceLog.setPinId(device.getPinId());
				deviceLog.setSimId(device.getSimId());
				deviceLog.setReceiveUser(user);
				deviceLog.setUpdateTime(date);
				deviceLog.setUpdateUserId(sessionvo.getUserId());
				this.deviceDao.save(TBasisDeviceTransferLog.class,deviceLog);
				
				return new CommonVO(true,"回收成功");
				
				}
			}
			
			return new CommonVO(false,"不能回收至该机构");
			
		}
		return new CommonVO(false,"管理员无法进行操作");
		
		
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#checkSub(javax.servlet.http.HttpSession, java.lang.String, java.lang.String)
	 */
	@Transactional
	public CommonVO checkSub(HttpSession session,String deviceId,String orgId){
		SessionVO user = (SessionVO)session.getAttribute("sessionVO");
		TBasisOrg org = (TBasisOrg) this.deviceDao.getByID(TBasisOrg.class,orgId);
		List<?> list = this.orgService.qryOrgChildrenTree(orgId);
		if(!"1".equals(user.getIsAdmin())){
			if(list.contains(org.getOrgId())){
				return new CommonVO(true,"");
			}
			return new CommonVO(false,"不能调度至该机构");
			
		}

		return new CommonVO(false,"管理员无法进行操作");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#allotSave(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public CommonVO allotSave(HttpSession session, String deviceType, String orgId,String combolData, String deviceId, String userCode, String userPwd){
		String pwd = MD5Util.md5(userPwd);
		SessionVO sessionvo = (SessionVO)session.getAttribute("sessionVO");
		TBasisDevice device = (TBasisDevice) this.deviceDao.getByID(TBasisDevice.class,deviceId);
		if(!"0".equals(device.getPadStatus())){
			return new CommonVO(false,"当前设备状态非入库状态，无法调拨"); 
		}
		TBasisOrg org = (TBasisOrg) this.deviceDao.getByID(TBasisOrg.class,orgId);
		TBasisUser user = (TBasisUser) this.deviceDao.getByID(TBasisUser.class,combolData);
		TBasisUser userOperate = (TBasisUser) this.deviceDao.getByID(TBasisUser.class,sessionvo.getUserId());//当前登录用户
		Date date = new Date();
		CommonVO cv ;
//		if(userCode.equals(user.getUserCode()) && pwd.equals(user.getUserPwd()))
//		{
		// 更新设备状态
		device.setPadStatus("0");
		device.settBasisOrg(org);
		device.settBasisUser(user);
		device.setRecipientTime(DateUtil.format(date));
		device.setUpdateTime(DateUtil.format(date));
		this.deviceDao.save(device);
		
		// 保存系统日志
		LogModuleOperation log = new LogModuleOperation();
		log.setModuleId("DEVICE");
		log.setOperateCode(userOperate.getUserCode());
		log.setOperateOrg(userOperate.getTBasisOrg());
		log.setOperateTime(date);
		log.setOperateType("添加");
		if(combolData == ""){
			log.setDescription("设备回收");
		}else {
			log.setDescription("设备调拨");
		}
		log.setOperateUser(userOperate);
		this.deviceDao.save(LogModuleOperation.class,log);
//		this.saveLog(SysConst.DEVICE_SBGL, "保存调度设备信息 ", SysConst.SAVE,"1");
		
		// 保存历史记录
		TBasisDeviceTransferLog deviceLog = new TBasisDeviceTransferLog();
		deviceLog.setOperatorUser(userOperate);
		deviceLog.setOperatorTime(date);
		if(combolData == ""){
			deviceLog.setOperatorType("回收");
		}else {
			deviceLog.setOperatorType("调拨");
		}
		deviceLog.setPinId(device.getPinId());
		deviceLog.setSimId(device.getSimId());
		deviceLog.setReceiveUser(user);
		deviceLog.setUpdateTime(date);
		deviceLog.setUpdateUserId(sessionvo.getUserId());
		this.deviceDao.save(TBasisDeviceTransferLog.class,deviceLog);
		
		if(combolData == ""){
			cv = new CommonVO(true,"回收成功");
		}else {
			cv = new CommonVO(true,"调拨成功");
		}
//		}else{ 
//			cv = new CommonVO(false,"员工号或密码错误！"); 
//		} 
		return cv;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#deviceOutbound(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional
	public CommonVO deviceOutbound(HttpSession session, String userCode, String orgId,String password,String deviceId,HttpServletRequest request) {
		try {
//			// 获取当前登录人Session
			SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
			TBasisUser tbasisUser = this.deviceDao.getUserByCode(sessionVO.getUserCode());
			TBasisUser user = this.deviceDao.getUserById(userCode,password);
			// 校验此人名下是否有设备、没人只可领取一台设备

			if (user != null ) {
				List<?> list = this.deviceDao.qryDeviceUser(user.getUserId());
				if (user.getTBasisOrg().getOrgId().equals(orgId)) {
					if (list.size() == 0) {
						TBasisDevice pad = (TBasisDevice) this.deviceDao.getByID(TBasisDevice.class,deviceId);
						// 获取系统时间
						Date date = new Date();
						pad.setPadStatus("1");
					//	pad.settBasisOrg(org);
						pad.settBasisUser(user);
						pad.setUpdateTime(DateUtil.format(date));
						pad.setRecipientTime(DateUtil.format(date));
						this.deviceDao.save1(pad);
//						系统日记saveLog("设备管理", "设备出库", "更新","1");
//
//						// 保存日志到数据表中
						TBasisDeviceTransferLog padLog = new TBasisDeviceTransferLog();
						padLog.setPinId(pad.getPinId());
						padLog.setSimId(pad.getSimId());
						padLog.setOperatorUser(tbasisUser);
						padLog.setOperatorType("出库");
						padLog.setOperatorTime(date);
						padLog.setReceiveUser(user);
						padLog.setUpdateTime(date);
						this.deviceDao.save(TBasisDeviceTransferLog.class,padLog);
//
//						TPadOperatorLog operLog = new TPadOperatorLog();
//						operLog.setManagerOrg(org);
//						operLog.setOperatorOrg(org);
//						operLog.setOperatorTime(date);
//						operLog.setOperatorType("出库");
//						operLog.setOperatorUser(tbasisUser);
//						operLog.setPinId(pad.getPinId());
//						operLog.setSimId(pad.getSimId());
//						operLog.setReceiveUser(user);
//						this.deviceDao.save(operLog);
//
						// 保存日志到系统表中
						LogModuleOperation log = new LogModuleOperation();
						log.setModuleId("设备管理");
						log.setOperateCode(tbasisUser.getUserCode());
						log.setOperateOrg(tbasisUser.getTBasisOrg());
						log.setOperateTime(date);
						log.setOperateType("添加");
						log.setDescription("设备出库");
						log.setOperateUser(tbasisUser);

						this.deviceDao.save(LogModuleOperation.class,log);

						log.setOperateIp(IpUtil.getIpAddr(request));
						this.deviceDao.save(LogModuleOperation.class,log);

//						this.saveLog(SysConst.DEVICE_SBGL, "设备出库", SysConst.SAVE,"1");
					} else {
						return new CommonVO(false, "此人名下已有未归还设备");
					}
				} else {
					return new CommonVO(false, "您的权限不足,请联系管理员");
				}
			} else {
				return new CommonVO(false, "员工号或密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonVO(false, "系统异常");
		}
		return new CommonVO(true, "出库成功");
	}

	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#saveDeviceStorage(javax.servlet.http.HttpSession, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional
	public CommonVO saveDeviceStorage(HttpSession session, String deviceId,HttpServletRequest request) {
		try {
			// 获取当前登录人Session
			SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
			TBasisUser tbasisUser = this.deviceDao.getUserByCode(sessionVO.getUserCode());
			TBasisUser user = this.deviceDao.getUserByCode(sessionVO.getUserCode());
			TBasisDevice pad = this.deviceDao.getDeviceById(deviceId);
		if(pad.gettBasisOrg().equals(tbasisUser.getTBasisOrg())){
			Date date = new Date();
			pad.setPadStatus("0");// 修改状态为入库
			pad.settBasisUser(user);
			pad.setUpdateTime(DateUtil.format(date));
			this.deviceDao.save(pad);
			TBasisDeviceTransferLog padLog = new TBasisDeviceTransferLog();
			padLog.setPinId(pad.getPinId());
			padLog.setSimId(pad.getSimId());
			padLog.setOperatorUser(tbasisUser);
			padLog.setOperatorType("入库");
			padLog.setOperatorTime(date);
			padLog.setReceiveUser(tbasisUser);
			padLog.setUpdateTime(date);
			this.deviceDao.save(TBasisDeviceTransferLog.class,padLog);

			// 保存日志到系统表中
			LogModuleOperation log = new LogModuleOperation();
			log.setModuleId("设备管理");
			log.setOperateCode(tbasisUser.getUserCode());
			log.setOperateOrg(tbasisUser.getTBasisOrg());
			log.setOperateTime(date);
			log.setOperateType("添加");
			log.setDescription("设备入库");
			log.setOperateUser(tbasisUser);

			this.deviceDao.save(LogModuleOperation.class,log);

			log.setOperateIp(IpUtil.getIpAddr(request));
			this.deviceDao.save(LogModuleOperation.class,log);

//			this.saveLog(SysConst.DEVICE_SBGL, "保存入库", SysConst.EDIT,"1");
		}else{
				return new CommonVO(false, "操作人权限不足");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonVO(false, "系统异常");
		}
		return new CommonVO(true, "入库成功");
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.device.IDeviceService#findRecord(int, int, java.lang.String)
	 */
	@Transactional
	public Map<String, Object> findRecord(int pageIndex, int pageSize, String pinId){
		List<?> list = this.deviceDao.findRecord(pinId, pageIndex,pageSize);
		List<?> listSize = this.deviceDao.findRecord(pinId);
		JSONArray json = JSONArray.fromObject(list);
		JSONObject obj = null;
		JSONObject jsonTime = null;
		for (int i = 0; i < json.size(); i++) {
			obj = json.getJSONObject(i);
			String operatorTime = obj.getString("operatorTime");
			jsonTime = JSONObject.fromObject(operatorTime);
			operatorTime = jsonTime.getString("time");
			Timestamp t = new Timestamp(new Long(operatorTime));
			obj.put("operatorTime",DateUtil.format(t,"yyyy-MM-dd HH:mm:ss"));
		}

		// 将集合转换成json数组
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", json);
		map.put("total", listSize);
		return map;
	}

	@Override
	@Transactional
	public CommonVO saveDevice(DeviceInfoVO deviceInfoVO) {
		List<TBasisDevice> tBasisDevices = (List<TBasisDevice>) deviceDao.get(TBasisDevice.class, "pinId", deviceInfoVO.getPinId());
		if(tBasisDevices.size() > 0)
			return new CommonVO(false, "设备已存在，请检查设备PIN码。");
//		TBasisOrg tBasisOrg = new TBasisOrg();
//		tBasisOrg.setOrgId(deviceInfoVO.getOrgId());
//		TBasisUser tu = new TBasisUser();
//		tu.setUserId("1e8168a452a0fa350152a105f2030001");
		TBasisDeviceInfo tBasisDeviceInfo = new TBasisDeviceInfo(null, deviceInfoVO.getPinId(), deviceInfoVO.getSimId(), null, deviceInfoVO.getOrgId(), null, "0", "normal", DateUtil.format(new Date()));
		deviceDao.save(tBasisDeviceInfo);
		return new CommonVO(true, "保存成功。");
	}

	@Override
	@Transactional
	public CommonVO removeDevice(String reId) {
		TBasisDevice tBasisDevice = new TBasisDevice();
		tBasisDevice.setReId(reId);
		deviceDao.delete(tBasisDevice);
		return new CommonVO(true, "删除成功。");
	}
}

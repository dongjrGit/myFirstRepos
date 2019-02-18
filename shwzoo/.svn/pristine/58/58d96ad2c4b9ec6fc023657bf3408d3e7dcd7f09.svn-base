package com.yinlian.wssc.platform.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/platform/memberlevel")
public class MemberLevelController {

	@Autowired
	private UserslevelService userslevelService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
	/**
	 * 查询所有会员等级列表
	 * @return
	 */
	@RequestMapping("/queryAll")
	public ReusltItem queryAll(){
		ReusltItem item = new ReusltItem();
		try {
			List<Userslevel> list = userslevelService.queryAllLevel();
			if (list.size()>0) {
				item.setCode(0);
				item.setData(list);
				item.setDesc("查询失败");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询所有会员等级列表信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "查询所有会员等级列表信息出错! 异常信息:",
					e, "/platform/memberlevel/queryAll");
		}
		
		return item;
	}
	/**
	 * 根据id编辑会员等级信息
	 * @param id
	 * @param name
	 * @param level
	 * @param cycle
	 * @param cycleunit
	 * @param leveldown
	 * @param pointdown
	 * @param upstandard
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateMemberLevelById")
	public ReusltItem updateMemberLevelById(String id,String name,String level,String cycle,
			String cycleunit,String leveldown,String pointdown,String upstandard,String status){
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			Userslevel userslevel = new Userslevel();
			userslevel.setId(StringUtilsEX.ToInt(id));
			userslevel.setName(name);
			userslevel.setLevel(StringUtilsEX.ToInt(level));
			userslevel.setCycle(StringUtilsEX.ToInt(cycle));
			userslevel.setCycleunit(cycleunit);
			userslevel.setLeveldown(StringUtilsEX.ToInt(leveldown));
			userslevel.setPointdown(StringUtilsEX.ToInt(pointdown));
			userslevel.setUpstandard(StringUtilsEX.ToInt(upstandard));
			userslevel.setStatus(StringUtilsEX.ToInt(status));
			int result = userslevelService.updateMemberLevelById(userslevel);
			if (result>0) {
				item.setCode(0);
				item.setDesc("编辑成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "level_update.jsp", "/platform/memberlevel/updateMemberLevelById", "根据id编辑会员等级信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id编辑会员等级信息操作记录出错! 异常信息:",
    								e, "/platform/memberlevel/updateMemberLevelById");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("编辑失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id编辑会员等级信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "根据id编辑会员等级信息出错! 异常信息:",
					e, "/platform/memberlevel/updateMemberLevelById");
		}
		
		return item;
	}
	/**
	 * 添加等级
	 * @param name
	 * @param level
	 * @param cycle
	 * @param cycleunit
	 * @param leveldown
	 * @param pointdown
	 * @param upstandard
	 * @param status
	 * @return
	 */
	@RequestMapping("/addMemberLevel")
	public ReusltItem addMemberLevel(String name,String level,String cycle,
			String cycleunit,String leveldown,String pointdown,String upstandard,String status){
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			Userslevel userslevel = new Userslevel();
			userslevel.setName(name);
			userslevel.setLevel(StringUtilsEX.ToInt(level));
			userslevel.setCycle(StringUtilsEX.ToInt(cycle));
			userslevel.setCycleunit(cycleunit);
			userslevel.setLeveldown(StringUtilsEX.ToInt(leveldown));
			userslevel.setPointdown(StringUtilsEX.ToInt(pointdown));
			userslevel.setUpstandard(StringUtilsEX.ToInt(upstandard));
			userslevel.setStatus(StringUtilsEX.ToInt(status));		
			int result = userslevelService.addMemberLevel(userslevel);
			if (result>0) {
				item.setCode(0);
				item.setDesc("添加成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "level_update.jsp", "/platform/memberlevel/addMemberLevel", "添加等级");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加等级操作记录出错! 异常信息:",
    								e, "/platform/memberlevel/addMemberLevel");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加等级信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "添加等级信息出错! 异常信息:",
					e, "/platform/memberlevel/addMemberLevel");
		}
		return item;
	}
	/**
	 * 根据id删除等级信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteById")
	public ReusltItem deleteById(String id){
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			int result = userslevelService.deleteById(StringUtilsEX.ToInt(id));
			if (result>0) {
				item.setCode(0);
				item.setDesc("删除成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "member_level.jsp", "/platform/memberlevel/deleteById", "根据id删除等级信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除等级信息操作记录出错! 异常信息:",
    								e, "/platform/memberlevel/deleteById");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id删除等级信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "根据id删除等级信息出错! 异常信息:",
					e, "/platform/memberlevel/deleteById");
		}
		return item;
	}
	
	/**
	 * 获取会员等级下拉框
	 * @return
	 */
	@RequestMapping("/getLevelList")
	public ReusltItem getLevelList(){
		ReusltItem item = new ReusltItem();
		try {
			List<Userslevel> list = userslevelService.getlevelList();
			if (list.size()>0) {
				item.setCode(0);
				item.setData(list);
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取会员等级下拉框信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "获取会员等级下拉框信息出错! 异常信息:",
					e, "/platform/memberlevel/getLevelList");
		}
		
		return item;
	}
}
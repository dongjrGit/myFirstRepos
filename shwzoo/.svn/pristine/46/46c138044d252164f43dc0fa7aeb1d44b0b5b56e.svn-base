package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.AccountsVo;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.AccountsDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/platform/operator")
public class OperatorController {

	@Autowired
	private AccountsService accountsService;

	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
	/**
	 * 添加操作管理员
	 * 
	 * @param loginname
	 * @param password
	 * @param email
	 * @param nickname
	 * @param realname
	 * @param roleid
	 * @param departid
	 * @return
	 */
	@RequestMapping("/addOperator")
	public ReusltItem addOperator(String loginname,
			String password, String email, String nickname, String realname,
			String roleid, String departid) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			AccountsVo vo = new AccountsVo();
			vo = checkParam(loginname, password, email, nickname, realname,
					roleid, departid, "0", item);
			if (item.getCode() < 0) {
				return item;
			}
			CriteriaAccounts cAccounts=new CriteriaAccounts();
			cAccounts.setLoginname(loginname);
			if(accountsService.isExistAcc(cAccounts)>0){
				item.setCode(-201);
				item.setDesc("该账号已存在!");
				return item;
			}
			if (accountsService.addOperator(vo) > 0) {
				item.setCode(0);
				item.setDesc("添加操作管理员成功");
				LogHandle.info(LogType.PlatformAdmin, 
						MessageFormat.format("添加操作管理员成功，账号:{0},操作人ID:{1}", loginname,user.getUserId()),
						"/platform/operator/addOperator");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "operator_edit.jsp", "/platform/operator/addOperator", "添加操作管理员");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加操作管理员操作记录出错! 异常信息:",
    								e, "/platform/operator/addOperator");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("添加操作管理员失败");
				LogHandle.info(LogType.PlatformAdmin, 
						MessageFormat.format("添加操作管理员失败，账号:{0},操作人ID:{1}", loginname,user.getUserId()),
						"/platform/operator/addOperator");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加操作管理员异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformAdmin,"添加操作管理员异常:"+e.getMessage(), "/platform/operator/addOperator");
		}

		return item;
	}

	/**
	 * 编辑操作管理员
	 * 
	 * @param id
	 * @param loginname
	 * @param email
	 * @param nickname
	 * @param realname
	 * @param roleid
	 * @param departid
	 * @return
	 */
	@RequestMapping("/updateOperator")
	public ReusltItem updateOperator(String id, String loginname,
			String email, String nickname, String realname, String roleid,
			String departid) throws Exception {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			AccountsVo vo = new AccountsVo();
			vo = checkParam(loginname, "", email, nickname, realname, roleid,
					departid, id, item);
			if (item.getCode() < 0) {
				return item;
			}
			CriteriaAccounts cAccounts=new CriteriaAccounts();
			cAccounts.setLoginname(loginname);
			cAccounts.setId(vo.getID());
			if(accountsService.isExistAcc(cAccounts)>0){
				item.setCode(-201);
				item.setDesc("该账号已存在!");
				return item;
			}
			if (accountsService.updateOperator(vo) > 0) {
				item.setCode(0);
				item.setDesc("编辑操作管理员成功");
				LogHandle.info(LogType.PlatformAdmin, 
						MessageFormat.format("编辑操作管理员成功，id:{0},账号:{1},操作人ID:{2}",id, loginname,user.getUserId()),
						"/platform/operator/updateOperator");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "operator_edit.jsp", "/platform/operator/updateOperator", "修改操作管理员");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改操作管理员操作记录出错! 异常信息:",
    								e, "/platform/operator/updateOperator");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("编辑操作管理员失败");
				LogHandle.info(LogType.PlatformAdmin, 
						MessageFormat.format("编辑操作管理员失败，id:{0},账号:{1},操作人ID:{2}",id, loginname,user.getUserId()),
						"/platform/operator/updateOperator");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑操作管理员异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformAdmin,"编辑操作管理员异常:"+e.getMessage(),
					"/platform/operator/updateOperator");
		}

		return item;
	}

	private AccountsVo checkParam(String loginname, String password,
			String email, String nickname, String realname, String roleid,
			String departid, String id, ReusltItem item) throws Exception {
		AccountsVo vo = new AccountsVo();
		Integer ID = StringUtilsEX.ToInt(id);
		if (ID < 0) {
			item.setCode(-101);
			item.setDesc("操作员ID参数错误，id:" + id);
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(loginname)) {
			item.setCode(-102);
			item.setDesc("操作员登录账号不能为空");
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(realname)) {
			item.setCode(-103);
			item.setDesc("操作员真实姓名不能为空");
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(email)) {
			item.setCode(-104);
			item.setDesc("操作员邮箱不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(roleid) <= 0) {
			item.setCode(-106);
			item.setDesc("操作员管理角色错误，roleid:" + roleid);
			return null;
		}
		if (ID == 0) {
			if (StringUtilsEX.IsNullOrWhiteSpace(password)) {
				item.setCode(-105);
				item.setDesc("操作员密码不能为空");
				return null;
			}
			vo.setPassword(password);
		} else {
			AccountsDTO dto = accountsService.selectByID(ID);
			vo.setID(dto.getId());
			vo.setDepartID(dto.getDepartmentid());
			vo.setEmail(dto.getEmail());
			vo.setMobile(dto.getPhone());
			vo.setNickName(dto.getNickname());
			vo.setRealName(dto.getRealname());
			vo.setRoleID(dto.getRoleid());
			vo.setUserName(dto.getLoginname());
		}
		vo.setUserName(loginname.trim());
		vo.setEmail(email);
		vo.setNickName(nickname);
		vo.setRealName(realname.trim());
		vo.setRoleID(StringUtilsEX.ToInt(roleid));

		if (StringUtilsEX.ToInt(departid) > 0) {
			vo.setDepartID(StringUtilsEX.ToInt(departid));
		}
		return vo;
	}

	/**
	 * 删除操作管理员
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteOperator")
	public ReusltItem deleteOperator(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("操作员ID参数错误，id:" + id);
				return item;
			}
			if (accountsService.deleteOperator(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("删除操作管理员成功");
				LogHandle.info(LogType.PlatformAdmin, 
						MessageFormat.format("编辑操作管理员成功，id:{0},操作人ID:{1}",id,user.getUserId()),
						"/platform/operator/deleteOperator");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "operator_list.jsp", "/platform/operator/deleteOperator", "删除操作管理员");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除操作管理员操作记录出错! 异常信息:",
    								e, "/platform/operator/deleteOperator");
    					}
    					
    				}
    			});
			} else {
				item.setCode(0);
				item.setDesc("删除操作管理员失败");
				LogHandle.info(LogType.PlatformAdmin, 
						MessageFormat.format("编辑操作管理员失败，id:{0},操作人ID:{1}",id,user.getUserId()),
						"/platform/operator/deleteOperator");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑操作管理员异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformAdmin,"删除操作管理员异常:",e ,
					"/platform/operator/deleteOperator");
		}

		return item;
	}

	/**
	 * 获取操作管理员列表
	 * @param name
	 * @param departid
	 * @param roleid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getList")
	public ReusltItem getList(String name, String departid,
			String roleid, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			
			if(StringUtilsEX.ToInt(page) <= 0 || StringUtilsEX.ToInt(size) <= 0){
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex:" + page+",pagesize:"+size);
				return item;
			}
			CriteriaAccounts cAccounts = new CriteriaAccounts();
			if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
				cAccounts.setLoginname(name);
			}
			if (StringUtilsEX.ToInt(departid) > 0) {
				cAccounts.setDepartid(StringUtilsEX.ToInt(departid));
			}
			if (StringUtilsEX.ToInt(roleid) > 0) {
				cAccounts.setRoleid(StringUtilsEX.ToInt(roleid));
			}
			cAccounts.setUsertype(UserTypeEnum.Admin.getValue());
			PageBean pBean=accountsService.getAccountsList(cAccounts, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取操作管理员列表异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformAdmin,"获取操作管理员列表异常:",e,
					"/platform/operator/getList");
		}

		return item;
	}
	
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping("/updatePass")
	public  ReusltItem updatePass(String id,String password){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("操作员ID参数错误，id:" + id);
				return item;
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(password)){
				item.setCode(-102);
				item.setDesc("操作员密码不能为空");
				return item;
			}
			if(accountsService.updatePassword(StringUtilsEX.ToInt(id), password.trim())>0){
				item.setCode(0);
				item.setDesc("修改操作管理员密码成功");
				LogHandle.info(LogType.PlatformAdmin,MessageFormat.format("修改操作管理员密码成功，id:{0},操作人ID:{1}",
						id,user.getUserId()),"/platform/operator/updatePass");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "operator_list.jsp", "/platform/operator/updatePass", "修改操作管理员密码");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改操作管理员密码操作记录出错! 异常信息:",
    								e, "/platform/operator/updatePass");
    					}
    					
    				}
    			});
			}
			else
			{
				item.setCode(-200);
				item.setDesc("修改操作管理员密码失败");
				LogHandle.info(LogType.PlatformAdmin,MessageFormat.format("修改操作管理员密码失败，id:{0},操作人ID:{1}",
						id,user.getUserId()),"/platform/operator/updatePass");
			}
		}
		catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改操作管理员密码异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformAdmin,"修改操作管理员密码异常:",e,
					"/platform/operator/updatePass");
		}

		return item;
	}
}

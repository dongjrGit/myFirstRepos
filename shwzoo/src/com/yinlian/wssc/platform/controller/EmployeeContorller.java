/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Employee;
import com.yinlian.wssc.web.service.EmployeeService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.RoleService;
import com.yinlian.wssc.web.util.CriteriaEmployee;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 员工的控制类
 * @author Administrator
 * @version $Id: EmployeeContorller.java, v 0.1 2016年3月14日 上午10:53:37 Administrator Exp $
 */
@Controller
@RequestMapping("/platform/employee")
public class EmployeeContorller {

    /**
     * 输出的控制类
     */

    @Autowired
    private EmployeeService     employeeService;
    @Autowired
    private RoleService         roleService;
    
    SessionUser                 user   = null;
    @Autowired
    private OperaterecordsService operaterecordsService ;

    /**
     * 查询店铺下面的员工分页的列表
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     */
    @RequestMapping("/queryEmployeeByShopidCriteria")
    public @ResponseBody ReusltItem queryEmployeeByShopidCriteria(String username, String shopid,
                                                                  String employeename,
                                                                  @RequestParam("page") String pc,
                                                                  @RequestParam("size") String ps) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(pc) < 0) {
                item.setCode(-102);
                item.setDesc("分页参数错误，pageindex：" + pc);
                return item;
            }
            if (StringUtilsEX.ToInt(ps) < 0) {
                item.setCode(-102);
                item.setDesc("分页参数错误，pageindex：" + ps);
                return item;
            }
            CriteriaEmployee criteria = new CriteriaEmployee();
            criteria.setUsername(username);
            criteria.setEmployeename(employeename);
            if (StringUtilsEX.ToInt(shopid) > 0) {
                criteria.setShopid(StringUtilsEX.ToInt(shopid));
            }
            PageBean pageBean = employeeService.selectEmployeeByShopidPage(criteria,
                StringUtilsEX.ToInt(pc), StringUtilsEX.ToInt(ps));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询店铺员工信息的异常{0}：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.ShopEmployee,
                "查询店铺员工信息的异常! 异常信息:", e,
                "/platform/employee/queryEmployeeByShopidCriteria");
        }

        return item;
    }

    /**
     * 删除员工
     * 
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public @ResponseBody ReusltItem delete(String id) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("会员的id不能为空,id" + id);
                return item;
            }
            SessionUser sessionUser = SessionState.GetCurrentUser();
            int result = employeeService.deleteById(StringUtilsEX.ToInt(id),
                sessionUser.getUserId());
            if (result > 0) {
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
                            		sessionUser.getUserId(), sessionUser.getLoginName(), "shop_employee.jsp", "/platform/employee/delete", "删除员工");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除员工操作记录出错! 异常信息:",
    								e, "/platform/employee/delete");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("删除店铺员工信息的异常{0}：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.ShopEmployee,
                "删除店铺员工信息的异常! 异常信息:", e,
                "/platform/employee/delete");
        }
        return item;
    }

    /**
     * 解锁和加锁
     * 
     * @param id
     * @param islock
     * @return
     */
    @RequestMapping("/lock")
    public @ResponseBody ReusltItem lock(String id, String islock,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("会员的id不能为空,id" + id);
                return item;
            }
            if (!StringUtils.isNotNull(islock)) {
                item.setCode(-102);
                item.setDesc("锁定的状态不能为空");
            }

            int result = employeeService.updateIsLock(StringUtilsEX.ToInt(id), islock);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("操作成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "shop_employee.jsp", "/platform/employee/lock", "修改解锁加锁状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改解锁加锁状态操作记录出错! 异常信息:",
    								e, "/platform/employee/lock");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("操作失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("解锁加锁店铺员工信息的异常{0}：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.ShopEmployee,
                "解锁加锁店铺员工信息的异常! 异常信息:", e,
                "/platform/employee/lock");
        }
        return item;
    }

    /**
     * 添加一个员工
     * 
     * @param username
     * @param password
     * @param empnum
     * @param realname
     * @param mobile
     * @param roleid
     * @param shopid
     * @return
     */
    @RequestMapping("/add")
    public @ResponseBody ReusltItem add(String username, String password, String empnum,
                                        String realname, String mobile, String roleid, String shopid,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
            if (!StringUtils.isNotNull(username)) {
                item.setCode(-101);
                item.setDesc("员工的账户不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(password)) {
                item.setCode(-102);
                item.setDesc("员工的密码不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(empnum)) {
                item.setCode(-103);
                item.setDesc("员工的编号不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(realname)) {
                item.setCode(-104);
                item.setDesc("员工的名字不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(mobile)) {
                item.setCode(-105);
                item.setDesc("员工的电话不能为空");
                return item;
            }
            if (StringUtilsEX.ToInt(roleid) < 0) {
                item.setCode(-106);
                item.setDesc("员工的角色不能为空");
                return item;
            }
            if (StringUtilsEX.ToInt(shopid) < 0) {
                item.setCode(-107);
                item.setDesc("员工的店铺不能为空");
                return item;
            }

            Employee employee = new Employee();
            employee.setEmpnum(empnum);
            employee.setMobile(mobile);
            employee.setShopid(StringUtilsEX.ToInt(shopid));
            employee.setUsername(username);
            employee.setPassword(password);
            employee.setRoleid(StringUtilsEX.ToInt(roleid));
            employee.setRealname(realname);
            employee.setIsdel(false);
            employee.setIslock(false);
            employee.setStatus(0);

            int result = employeeService.insert(employee);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("操作成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "shop_employee_add.jsp", "/platform/employee/add", "添加员工");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加员工操作记录出错! 异常信息:",
    								e, "/platform/employee/add");
    					}
    					
    				}
    			});
            } else if (result == -2) {
                item.setCode(-200);
                item.setDesc("员工已满");
            } else if (result == -3) {
                item.setCode(-202);
                item.setDesc("用户名已被注册");

            } else {
                item.setCode(-201);
                item.setDesc("操作失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("添加店铺员工信息的异常{0}：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.ShopEmployee,
                "添加铺员工信息的异常! 异常信息:", e,
                "/platform/employee/add");
        }
        return item;
    }

    /**
     * 更新员工信息
     * 
     * @param id
     * @param username
     * @param empnum
     * @param realname
     * @param mobile
     * @param roleid
     * @param shopid
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ReusltItem update(String id, String username, String empnum,
                                           String realname, String mobile, String roleid,
                                           String shopid,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-102);
                item.setDesc("员工的id不能为空,id" + id);
                return item;
            }
            if (!StringUtils.isNotNull(username)) {
                item.setCode(-101);
                item.setDesc("员工的账户不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(empnum)) {
                item.setCode(-103);
                item.setDesc("员工的编号不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(realname)) {
                item.setCode(-104);
                item.setDesc("员工的名字不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(mobile)) {
                item.setCode(-105);
                item.setDesc("员工的电话不能为空");
                return item;
            }
            if (StringUtilsEX.ToInt(roleid) < 0) {
                item.setCode(-106);
                item.setDesc("员工的角色不能为空");
                return item;
            }
            if (StringUtilsEX.ToInt(shopid) < 0) {
                item.setCode(-106);
                item.setDesc("员工的店铺不能为空");
                return item;
            }

            int result = employeeService.update(StringUtilsEX.ToInt(id), username, empnum,
                realname, mobile, StringUtilsEX.ToInt(roleid), StringUtilsEX.ToInt(shopid));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("操作成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "shop_employee_update.jsp", "/platform/employee/update", "修改员工信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改员工信息操作记录出错! 异常信息:",
    								e, "/platform/employee/update");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("操作失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("更新店铺员工信息的异常{0}：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.ShopEmployee,
                "更新铺员工信息的异常! 异常信息:", e,
                "/platform/employee/update");
        }
        return item;
    }

    /**
     * 更改 密码
     * 
     * @param id
     * @param newpassword
     * @return
     */
    @RequestMapping("/upatePassword")
    public @ResponseBody ReusltItem updatePassword(String id, String newpassword,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("员工id不能为空,id" + id);
                return item;
            }
            if (!StringUtils.isNotNull(newpassword)) {
                item.setCode(-102);
                item.setDesc("新密码不能为空");
            }
            int result = employeeService.updatePassword(StringUtilsEX.ToInt(id), newpassword);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("操作成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "shop_employee_pwd_update.jsp", "/platform/employee/upatePassword", "修改密码");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改密码操作记录出错! 异常信息:",
    								e, "/platform/employee/upatePassword");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("操作失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("更新店铺员工密码的异常{0}：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
           LogHandle.error(LogType.ShopEmployee,
                "更新铺员工密码的异常! 异常信息:", e,
                "/platform/employee/upatePassword");
        }
        return item;
    }

}

package com.yinlian.wssc.platform.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.DepartmentDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Department;
import com.yinlian.wssc.web.service.DepartmentService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 部门管理的控制类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/department")
public class DepartmentController {

    @Autowired
    private DepartmentService   departmentService;
    
    SessionUser                 user   = null;
    @Autowired
    private OperaterecordsService operaterecordsService ;

    /**
     * 查询所有的部门
     * @return
     */
    @RequestMapping("/queryDeparAll")
    public @ResponseBody ReusltItem queryDeparAll() {
    	ReusltItem item = new ReusltItem();
        try {
            List<Department> list = departmentService.queryAll();
            item.setCode(0);
            item.setData(list);
            item.setDesc("获取所有部门");
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取所有部门异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(
					LogType.department,"获取所有部门异常， 异常信息",
							e, "/platform/department/queryDeparAll");
        }
        return item;
    }

    /**
     * 查询所有的一级部门，根据关键字查询
     * @return
     */
    @RequestMapping("/queryDepartList")
    public @ResponseBody ReusltItem queryDepartList(String keyWords) {
        ReusltItem item = new ReusltItem();

        try {
            if (keyWords != null && keyWords != "") {
                List<Department> list = departmentService.queryDepartByName(keyWords);
                item.setCode(0);
                item.setData(list);
                item.setDesc("查询成功");
            } else {
                List<DepartmentDto> list = departmentService.queryAll(0);
                item.setCode(0);
                item.setData(list);
                item.setDesc("获取部门一级列表");
            }

        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取部门一级列表异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(
					LogType.department,"获取部门一级列表异常， 异常信息",
							e, "/platform/department/queryDepartList");
        }

        return item;
    }

    /**
     * 根据name查询部门
     * @return
     */
    @RequestMapping("/queryDepartByName")
    public @ResponseBody ReusltItem queryDepartByName(String keyWords) {
        ReusltItem item = new ReusltItem();

        try {
            List<Department> list = departmentService.queryDepartByName(keyWords);
            item.setCode(0);
            item.setData(list);
            item.setDesc("查询成功");
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据名称查询部门异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(
					LogType.department,"根据名称查询部门异常， 异常信息",
							e, "/platform/department/queryDepartByName");
        }

        return item;
    }

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public @ResponseBody ReusltItem deleteById(String id,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
            int result = departmentService.deleteById(id);
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
                            		user.getUserId(), user.getLoginName(), "Department_list.jsp", "/platform/department/deleteById", "根据id删除部门");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除部门操作记录出错! 异常信息:",
    								e, "/platform/department/deleteById");
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
				item.setDesc("删除部门异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(
					LogType.department,"删除部门异常， 异常信息",
							e, "/platform/department/deleteById");
        }
        return item;
    }

    /**
     * 根据id修改状态 0启用 1禁用
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatusById")
    public @ResponseBody ReusltItem updateStatusById(String id, String status,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
            int result = departmentService.updateStatusById(id, status);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Department_list.jsp", "/platform/department/updateStatusById", "根据id修改状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id修改状态操作记录出错! 异常信息:",
    								e, "/platform/department/updateStatusById");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改部门状态异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(
					LogType.department,"修改部门状态异常， 异常信息",
							e, "/platform/department/updateStatusById");
        }

        return item;
    }

    /**
     * 添加部门
     * @param Id
     * @param num
     * @param name
     * @param FatherID
     * @param FullPath
     * @param status
     * @return
     */
    @RequestMapping("/addDepart")
    public @ResponseBody ReusltItem addDepart(String id, String num, String name, String fatherid,
                                              String fullpath, String status,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtils.decide(num)) {
                item.setCode(-101);
                item.setDesc("部门编号不能有中文");
                return item;
            }
            Department department = new Department();
            department.setNum(num);
            department.setName(name);
            department.setCreatetime(new Date());
            if(fatherid == "" || fatherid ==null){
            	department.setFatherid(0);
            }else{
            	department.setFatherid(StringUtilsEX.ToInt(fatherid));
            }
            if (fullpath != "" || fullpath == null) {
                department.setFullpath(fullpath);
            }
            department.setStatus(StringUtilsEX.ToInt(status));

            int result = departmentService.addDepart(department);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
                user=SessionUtil.getSessionUser(request);
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Department_Save.jsp", "/platform/department/addDepart", "添加部门");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加部门操作记录出错! 异常信息:",
    								e, "/platform/department/addDepart");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("添加失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加部门异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(
					LogType.department,"添加部门异常， 异常信息",
							e, "/platform/department/addDepart");
        }
        return item;
    }

    /**
     * 根据id修改部门
     * @param Id
     * @param num
     * @param name
     * @param FatherID
     * @param FullPath
     * @param status
     * @return
     */
    @RequestMapping("/updateDepartById")
    public @ResponseBody ReusltItem updateDepartById(String id, String num, String name,
                                                     String fatherid, String fullpath, String status,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
            int resule = departmentService.updateDepartById(id, num, name, fatherid, fullpath,
                status);
            if (resule > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Department_Save.jsp", "/platform/department/updateDepartById", "根据id修改部门");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id修改部门操作记录出错! 异常信息:",
    								e, "/platform/department/updateDepartById");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改部门异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(
					LogType.department,"修改部门异常， 异常信息",
							e, "/platform/department/updateDepartById");
        }
        return item;
    }

    /**
     * 根据父id获取子部门信息
     * @param fatherid
     * @return
     */
    @RequestMapping("/getByFatherID")
    public @ResponseBody ReusltItem getByFatherID(String fatherid) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(fatherid) < 0) {
                item.setCode(-101);
                item.setDesc("父ID参数错误");
            }
            item.setData(departmentService.getByFatherID(StringUtilsEX.ToInt(fatherid)));
            item.setCode(0);

        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据父id获取子部门信息异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(
					LogType.department,"根据父id获取子部门信息异常， 异常信息",
							e, "/platform/department/getByFatherID");
        }
        return item;
    }
}

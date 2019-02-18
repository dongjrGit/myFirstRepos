/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.CopeCardTypEnum;
import com.yinlian.Enums.CopySexEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.MemberVo;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.UsersInfoDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Employee;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Role;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.EmployeeService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.RoleService;
import com.yinlian.wssc.web.service.UserAttrService;
import com.yinlian.wssc.web.service.UserFinanceService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.CriteriaEmployee;
import com.yinlian.wssc.web.util.CriteriaFinance;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 账号管理的控制层
 * ZhglShopController.java
 * @author Administrator
 * @version $Id: ZhglShopController.java, v 0.1 2016年3月23日 下午5:46:54 Administrator Exp $
 */
@RestController
@RequestMapping("/seller/zhglshop")
public class ZhglShopController {

    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(ZhglShopController.class);
    @Autowired
    private EmployeeService     employeeService;
    @Autowired
    private RoleService         roleService;
    @Autowired
    private ProvinceServcice    provinceServcice;
    @Autowired
    private CityServcie         cityService;
    @Autowired
    private AreaService         areaService;
    @Autowired
    private AccountsService     accountsService;
    @Autowired
    private UserService         userService;
    @Autowired
    private UserAttrService     userAttrService;
    @Autowired
    private UserFinanceService  userFinanceService;
    @Autowired
	private OperaterecordsService   operaterecordsService ;
    /**
     * 根据shopid分页查询员工列表
     * 
     * @param username
     * @param realname
     * @param roleid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/qeuryAccountsListByCriteria")
    public ReusltItem qeuryAccountsListByCriteria(String username, String realname, Integer roleid,
                                                  Integer shopid, String page, String size) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(page) <= 0) {
                item.setCode(-102);
                item.setDesc("分页参数错误，pageindex：" + page);
                return item;
            }
            if (StringUtilsEX.ToInt(size) <= 0) {
                item.setCode(-103);
                item.setDesc("分页参数错误，pageindex：" + size);
                return item;
            }
            PageBean pageBean;

            CriteriaEmployee criteria = new CriteriaEmployee();
            criteria.setUsername(username);
            criteria.setEmployeename(realname);
            criteria.setRoleid(roleid);
            criteria.setShopid(shopid);
            pageBean = employeeService.selectEmployeeByShopidPage(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("询员工列表异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,"询员工列表异常! 异常信息:{0}", e,
                "shop/qeuryAccountsListByCriteria");
        }
        return item;
    }

    /**
     * 添加员工
     * 
     * @param username
     * @param empnum
     * @param realname
     * @param mobile
     * @param roleid
     * @param email
     * @param tel
     * @param mark
     * @param shopid
     * @param password
     * @return
     */
    @RequestMapping("/addAccount")
    public ReusltItem addAccount(String username, String empnum, String realname, String mobile,
                                 String roleid, String email, String tel, String mark,
                                 String shopid, String password) {
        ReusltItem item = new ReusltItem();
        try {
            checkParam(username, empnum, realname, mobile, roleid, shopid, item);
            if (item.getCode() < 0) {
                return item;
            }
            if (!StringUtils.isNotNull(password)) {
                item.setCode(-105);
                item.setDesc("员工密码不能为空");
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
            employee.setEmail(email);
            employee.setMark(mark);
            employee.setTel(tel);
            employee.setIsdel(false);
            employee.setIslock(false);
            employee.setStatus(0);
            int result = employeeService.insert(employee);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("操作成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "添加员工页面", "/seller/zhglshop/addAccount", "添加员工");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加添加员工操作记录出错! 异常信息:",
    								e, "/seller/zhglshop/addAccount");
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
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("添加员工失败! 参数信息:{0}", employee.toString()),
                    "shop/addAccount");
            }
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("添加员工异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "添加员工异常! 异常信息:{0}", e, "shop/addAccount");
        }
        return item;
    }

    @RequestMapping("/editAccount")
    public ReusltItem editAccount(String id, String username, String empnum, String realname,
                                  String mobile, String roleid, String email, String tel,
                                  String mark, String shopid, String password) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("员工id错误");
                return item;
            }
            checkParam(username, empnum, realname, mobile, roleid, shopid, item);
            if (item.getCode() < 0) {
                return item;
            }
            Employee employee = new Employee();
            employee.setEmpnum(empnum);
            employee.setMobile(mobile);
            employee.setShopid(StringUtilsEX.ToInt(shopid));
            employee.setEmail(email);
            employee.setUsername(username);
            employee.setPassword(password);
            employee.setRoleid(StringUtilsEX.ToInt(roleid));
            employee.setRealname(realname);
            employee.setMark(mark);
            employee.setTel(tel);
            employee.setId(StringUtilsEX.ToInt(id));

            int result = employeeService.updateEmployee(employee);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("操作成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改员工页面", "/seller/zhglshop/editAccount", "修改员工");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改员工操作记录出错! 异常信息:",
    								e, "/seller/zhglshop/editAccount");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("操作失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("编辑员工失败! 参数信息:{id}", id), "shop/editAccount");
            }
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("编辑员工异常：" + e.getMessage());
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("编辑员工异常! 异常信息:{0}", e.toString()), "shop/editAccount");
        }
        return item;
    }

    /**
     * 根据id查询员工
     * 
     * @param id
     * @return
     */
    @RequestMapping("/queryEmployeeById")
    public ReusltItem queryEmployeeById(Integer id) {
        ReusltItem item = new ReusltItem();
        try {
            Employee employee = employeeService.selectById(id);
            item.setCode(0);
            item.setData(employee);
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("查询员工异常：" + e.getMessage());
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("查询员工异常! 异常信息:{0}", e.toString()), "shop/queryEmployeeById");
        }
        return item;
    }

    /**
     * 删除员工
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteAccount")
    public ReusltItem deleteAccount(String id) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("员工id错误");
                return item;
            }
            SessionUser sessionUser = SessionState.GetCurrentUser();
            int result = employeeService.deleteById(StringUtilsEX.ToInt(id),
                sessionUser.getUserId());
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除员工页面", "/seller/zhglshop/deleteAccount", "删除员工");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加删除员工操作记录出错! 异常信息:",
    								e, "/seller/zhglshop/deleteAccount");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("删除员工失败! 参数信息:{id}", id), "shop/deleteAccount");
            }
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("删除员工异常：" + e.getMessage());
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("删除员工异常! 异常信息:{0}", e.toString()), "shop/deleteAccount");
        }
        return item;
    }

    /**
     * 根据shopid查询角色列表
     * 
     * @param shopid
     * @return
     */
    @RequestMapping("/queryRoleListByShopId")
    public ReusltItem queryRoleListByShopId(Integer shopid) {
        ReusltItem item = new ReusltItem();
        try {
            List<Role> list = roleService.selectShopRole(shopid);
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("查询角色集合异常：" + e.getMessage());
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("查询角色集合异常! 异常信息:{0}", e.toString()),
                "shop/queryRoleListByShopId");
        }
        return item;
    }

    /**
     * 根据名字和shopid模糊查询角色集合
     * 
     * @param name
     * @param shopid
     * @return
     */
    @RequestMapping("/queryRoleLikeName")
    public ReusltItem queryRoleLikeName(String name, Integer shopid) {
        ReusltItem item = new ReusltItem();
        try {
            List<Role> list = roleService.getRoleStartWithName(name, shopid);
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("查询角色集合异常：" + e.getMessage());
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("查询角色集合异常! 异常信息:{0}", e.toString()), "shop/queryRoleLikeName");
        }
        return item;
    }

    /**
     * 修改员工密码
     * 
     * @param id
     * @param password
     * @return
     */
    @RequestMapping("/updateEmployeePassword")
    public ReusltItem updateEmployeePassword(Integer id, String password) {
        ReusltItem item = new ReusltItem();
        try {
            int result = employeeService.updatePassword(id, password);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改员工密码页面", "/seller/zhglshop/updateEmployeePassword", "修改员工密码");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改员工密码操作记录出错! 异常信息:",
    								e, "/seller/zhglshop/updateEmployeePassword");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("修改员工密码异常! 参数信息:{id}", id), "shop/updateEmployeePassword");
            }
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("修改员工密码异常：" + e.getMessage());
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("修改员工密码异常! 异常信息:{0}", e.toString()),
                "shop/updateEmployeePassword");
        }
        return item;
    }

    /**
     * 解锁和锁定功能
     * 
     * @param id
     * @param islock
     * @return
     */
    @RequestMapping("/updateEmployeeLock")
    public ReusltItem updateEmployeeLock(Integer id, String islock) {
        ReusltItem item = new ReusltItem();
        try {
            int result = employeeService.updateIsLock(id, islock);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改员工状态 解锁和锁定 页面", "/seller/zhglshop/updateEmployeeLock", "修改员工状态 解锁和锁定");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改员工状态 解锁和锁定操作记录出错! 异常信息:",
    								e, "/seller/zhglshop/updateEmployeeLock");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("修改失败! 参数信息id:{0}", id), "shop/updateEmployeeLock");
            }
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("解锁和锁定异常：" + e.getMessage());
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("解锁和锁定异常! 异常信息:{0}", e.toString()), "shop/updateEmployeeLock");
        }
        return item;
    }

    /**
     * 根据code查询所有的省Provice
     * @return
     */
    @RequestMapping("/queryRegion")
    public @ResponseBody ReusltItem queryRegion(String type, String code) {
        ReusltItem item = new ReusltItem();
        try {
            switch (StringUtilsEX.ToInt(type)) {
                case 0:
                    //查询 省
                    List<Province> list = provinceServcice.selectAll();
                    item.setCode(0);
                    item.setData(list);
                    break;

                case 1:
                    // 查询市
                    List<City> cities = cityService.selectByProvinceCode(code);
                    item.setCode(0);
                    item.setData(cities);
                    break;
                case 2:
                    //查询区
                    List<Area> areas = areaService.selectByCityCode(code);
                    item.setCode(0);
                    item.setData(areas);
                    break;
            }
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("查询区域异常：" + e.getMessage());
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("查询区域异常! 异常信息:{0}", e.toString()), "shop/queryRegion");
        }
        return item;
    }

    /**
     * 绑定收入水平
     * @return
     */
    @RequestMapping("/queryInComeMonth")
    public @ResponseBody ReusltItem queryInComeMonth() {
        ReusltItem item = new ReusltItem();
        List<MemberVo> list = new ArrayList<MemberVo>();

        for (int i = 1; i < 6; i++) {
            MemberVo cardtypeVo = new MemberVo();
            cardtypeVo.setCode(i);
            switch (i) {
                case 1:
                    cardtypeVo.setName("2000以下");
                    break;
                case 2:
                    cardtypeVo.setName("2000-3999元");
                    break;
                case 3:
                    cardtypeVo.setName("4000-5999元");
                    break;
                case 4:
                    cardtypeVo.setName("6000-7999元");
                    break;
                case 5:
                    cardtypeVo.setName("8000元以上");
                    break;
            }
            list.add(cardtypeVo);
        }
        item.setCode(0);
        item.setDesc("绑定收入水平");
        item.setData(list);
        return item;
    }

    /**
     * 绑定证件类型
     * @return
     */
    @RequestMapping("/queryCardType")
    public @ResponseBody ReusltItem queryCardType() {
        ReusltItem item = new ReusltItem();
        List<MemberVo> list = new ArrayList<MemberVo>();
        for (int i = 0; i < CopeCardTypEnum.values().length; i++) {
            MemberVo cardtypeVo = new MemberVo();
            cardtypeVo.setCode(CopeCardTypEnum.values()[i].getValue());
            cardtypeVo.setName(CopeCardTypEnum.values()[i].name());
            list.add(cardtypeVo);
        }
        item.setCode(0);
        item.setDesc("绑定证件类型");
        item.setData(list);
        return item;
    }

    /**
     * 绑定性别Sex
     * @return
     */
    @RequestMapping("/querySexAll")
    public @ResponseBody ReusltItem querySexAll() {
        ReusltItem item = new ReusltItem();
        List<MemberVo> list = new ArrayList<MemberVo>();
        for (int i = 0; i < CopySexEnum.values().length; i++) {
            MemberVo sexVo = new MemberVo();
            sexVo.setCode(CopySexEnum.values()[i].getValue());
            sexVo.setName(CopySexEnum.values()[i].name());
            list.add(sexVo);
        }
        item.setCode(0);
        item.setDesc("初始化性别");
        item.setData(list);
        return item;
    }

    /**
     * 卖家账号数据绑定
     * @return
     */
    @RequestMapping("/queryMemberById")
    public @ResponseBody ReusltItem queryMemberById(String userid) {
        ReusltItem item = new ReusltItem();

        try {
            UsersInfoDto usersInfoDto = new UsersInfoDto();
            usersInfoDto = accountsService.querySellerById(StringUtilsEX.ToInt(userid));
            if (usersInfoDto != null) {
                usersInfoDto.setYear(DateUtil.getYear(usersInfoDto.getBirthday()));
                usersInfoDto.setMonth(DateUtil.getMonth(usersInfoDto.getBirthday()));
                usersInfoDto.setDay(DateUtil.getDay(usersInfoDto.getBirthday()));
            }
            item.setCode(0);
            item.setData(usersInfoDto);
            item.setDesc("会员数据绑定");
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("卖家账号数据绑定异常：" + e.getMessage());
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("卖家账号数据绑定异常! 异常信息:{0}", e.toString()), "shop/queryMemberById");
        }

        return item;
    }

    /**
     * 修改账号信息
     * 
     * @param userattrid
     * @param userid
     * @param nickname
     * @param realname
     * @param sex
     * @param birthyear
     * @param birthmonth
     * @param birthday
     * @param phone
     * @param provincecode
     * @param provincename
     * @param citycode
     * @param cityname
     * @param areacode
     * @param areaname
     * @param address
     * @param postcode
     * @param idcardtype
     * @param idcard
     * @param incomemonth
     * @return
     */
    @RequestMapping("/updateSellerById")
    public @ResponseBody ReusltItem updateSellerById(String userattrid, String userid,
                                                     String nickname, String realname, String sex,
                                                     String birthyear, String birthmonth,
                                                     String birthday, String phone,
                                                     String provincecode, String provincename,
                                                     String citycode, String cityname,
                                                     String areacode, String areaname,
                                                     String address, String postcode,
                                                     String idcardtype, String idcard,
                                                     String incomemonth) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(userattrid) < 0) {
                item.setCode(-101);
                item.setDesc("账号属性表id不能为");
                return item;
            }
            if (StringUtilsEX.ToInt(userid) < 0) {
                item.setCode(-102);
                item.setDesc("账号user表id不能为");
                return item;
            }
            checkParamSeller(nickname, realname, sex, birthyear, birthmonth, birthday, phone,
                provincename, cityname, areaname, address, postcode, idcardtype, idcard,
                incomemonth, item);
            if (item.getCode() < 0) {
                return item;
            }

            Users users = new Users();
            users.setId(StringUtilsEX.ToInt(userid));
            users.setNickname(nickname);
            users.setRealname(realname);
            users.setIdcardtype(StringUtilsEX.ToInt(idcardtype));
            users.setIdcard(idcard);

            UserAttr userAttr = new UserAttr();
            userAttr.setId(StringUtilsEX.ToInt(userattrid));
            userAttr.setSex(StringUtilsEX.ToInt(sex));
            userAttr.setAddress(address);
            userAttr.setAreacode(areacode);
            userAttr.setAreaname(areaname);
            userAttr.setPhone(phone);
            userAttr.setProvincecode(provincecode);
            userAttr.setProvincename(provincename);
            userAttr.setPostcode(postcode);
            userAttr.setCitycode(citycode);
            userAttr.setCityname(cityname);
            userAttr.setIncomemonth(StringUtilsEX.ToInt(incomemonth));
            String date = birthyear + "-" + birthmonth + "-" + birthday;
            userAttr.setBirthday(StringUtilsEX.ToShortDate(date));

            int result = userService.updateSeller(users, userAttr);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("更改成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改账号信息 页面", "/seller/zhglshop/updateSellerById", "修改账号信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改账号信息操作记录出错! 异常信息:",
    								e, "/seller/zhglshop/updateSellerById");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更改失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("修改账号信息失败! 失败信息:{0}", userid), "shop/updateSellerById");
            }
        } catch (Exception e) {
            item.setDesc("修改账号信息异常：" + e.getMessage());
            item.setCode(-900);
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("修改账号信息异常! 异常信息:{0}", e.toString()), "shop/updateSellerById");
        }

        return item;
    }

    /**
     * 校验卖家账号修改的参数
     * @param nickname
     * @param realname
     * @param sex
     * @param birthyear
     * @param birthmonth
     * @param birthday
     * @param phone
     * @param provincename
     * @param cityname
     * @param areaname
     * @param address
     * @param postcode
     * @param idcardtype
     * @param idcard
     * @param incomemonth
     * @param item 
     */
    private void checkParamSeller(String nickname, String realname, String sex, String birthyear,
                                  String birthmonth, String birthday, String phone,
                                  String provincename, String cityname, String areaname,
                                  String address, String postcode, String idcardtype,
                                  String idcard, String incomemonth, ReusltItem item) {

        if (!StringUtils.isNotNull(nickname)) {
            item.setCode(-103);
            item.setDesc("昵称不能为空");
        }
        if (!StringUtils.isNotNull(realname)) {
            item.setCode(-104);
            item.setDesc("真实名不能为空");
        }
        /*if (StringUtilsEX.ToInt(sex) < 0) {
            item.setCode(-105);
            item.setDesc("性别");
        }
        if (!StringUtils.isNotNull(birthyear)) {
            item.setCode(-106);
            item.setDesc("生日年不能为空");
        }
        if (!StringUtils.isNotNull(birthmonth)) {
            item.setCode(-107);
            item.setDesc("生日月不能为空");
        }
        if (!StringUtils.isNotNull(birthday)) {
            item.setCode(-108);
            item.setDesc("生日天不能为空");
        }*/
        if (!StringUtils.isNotNull(phone)) {
            item.setCode(-109);
            item.setDesc("电话不能为空");
        }
        if (!StringUtils.isNotNull(provincename)) {
            item.setCode(-110);
            item.setDesc("省不能为空");
        }
        if (!StringUtils.isNotNull(cityname)) {
            item.setCode(-111);
            item.setDesc("市不能为空");
        }
        if (!StringUtils.isNotNull(areaname)) {
            item.setCode(-112);
            item.setDesc("区不能为空");
        }
        if (!StringUtils.isNotNull(address)) {
            item.setCode(-113);
            item.setDesc("地址不能为空");
        }
        /*if (!StringUtils.isNotNull(postcode)) {
            item.setCode(-114);
            item.setDesc("邮编不能为空");
        }
        if (StringUtilsEX.ToInt(idcardtype) < 0) {
            item.setCode(-115);
            item.setDesc("证件类型不能为空");
        }
        if (!StringUtils.isNotNull(idcard)) {
            item.setCode(-116);
            item.setDesc("证件号不能为空");
        }
        if (StringUtilsEX.ToInt(incomemonth) < 0) {
            item.setCode(-117);
            item.setDesc("收入类型不能为空");
        }*/
    }

    /**
     * 参数校验
     * @param username
     * @param empnum
     * @param realname
     * @param mobile
     * @param roleid
     * @param shopid
     */
    private void checkParam(String username, String empnum, String realname, String mobile,
                            String roleid, String shopid, ReusltItem item) {
        if (!StringUtils.isNotNull(username)) {
            item.setCode(-101);
            item.setDesc("员工用户名不能为空");
        }
        if (!StringUtils.isNotNull(empnum)) {
            item.setCode(-102);
            item.setDesc("员工编号不能为空");
        }
        if (!StringUtils.isNotNull(realname)) {
            item.setCode(-103);
            item.setDesc("员工姓名不能为空");
        }
        if (!StringUtils.isNotNull(mobile)) {
            item.setCode(-104);
            item.setDesc("员工手机号不能为空");
        }

        if (StringUtilsEX.ToInt(roleid) < 0) {
            item.setCode(-106);
            item.setDesc("员工角色不能为空");
        }
        if (StringUtilsEX.ToInt(shopid) < 0) {
            item.setCode(-107);
            item.setDesc("店铺id不能为空");
        }
    }

    /**
     * 查询卖家的财务信息
     * 
     * @param type
     * @param number
     * @param paynum
     * @param datef
     * @param datet
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryFinanceList")
    public @ResponseBody ReusltItem queryFinanceList(String type, String number, String paynum,
                                                     String datef, String datet, String page,
                                                     String size) {
        ReusltItem item = new ReusltItem();
        try {
            Integer paInteger = StringUtilsEX.ToInt(page);
            Integer sInteger = StringUtilsEX.ToInt(size);
            if (paInteger <= 0) {
                item.setCode(-102);
                item.setDesc("分页参数错误，pageindex：" + page);
                return item;
            }
            if (sInteger <= 0) {
                item.setCode(-103);
                item.setDesc("分页参数错误，pageindex：" + size);
                return item;
            }
            SessionUser sessionUser = SessionState.GetCurrentUser();
            CriteriaFinance criteria = new CriteriaFinance();
            if (sessionUser.getCode() == 0) {
                criteria.setUserid(sessionUser.getUserId());
            }
            criteria.setType(StringUtilsEX.ToInt(type));
            if (StringUtils.isNotNull(number)) {
                criteria.setNumber(number);
            }
            if (StringUtils.isNotNull(paynum)) {
                criteria.setPaynum(paynum);
            }
            if (StringUtils.isNotNull(datef)) {
                criteria.setStart(DateUtil.stringConvert(datef));
            }
            if (StringUtils.isNotNull(datet)) {
                criteria.setEnd(DateUtil.stringConvert(datet));
            }
            PageBean pageBean = userFinanceService.selectPage(criteria, paInteger, sInteger);
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
            item.setDesc("查询卖家的财务信息异常：" + e.getMessage());
            item.setCode(-900);
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("查询卖家的财务信息异常! 异常信息:{0}", e.toString()),
                "shop/queryFinanceList");
        }
        return item;
    }
}

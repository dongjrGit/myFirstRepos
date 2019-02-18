/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Employee;
import com.yinlian.wssc.web.util.CriteriaEmployee;

/**
 * 会员业务类的接口
 * @author Administrator
 * @version $Id: EmployeeService.java, v 0.1 2016年3月14日 上午11:05:00 Administrator Exp $
 */
public interface EmployeeService {

    /**
     * 查询出店铺下所有员工信息
     * @param criteria
     * @param pc
     * @param ps
     * @return
     */
    PageBean selectEmployeeByShopidPage(CriteriaEmployee criteria, Integer pc, Integer ps)
                                                                                          throws Exception;

    /**
     * 根据主键删除
     * @param id
     * @param userid 
     * @return
     */
    int deleteById(Integer id, Integer userid) throws Exception;

    /**
     * 根据逐渐查询
     * @param id
     * @return
     * @throws Exception
     */
    Employee selectById(Integer id) throws Exception;

    /**
     * 操作锁定的状态
     * @param id
     * @param islock
     * @return
     */
    int updateIsLock(Integer id, String islock) throws Exception;

    /**
     * 增加一个员工
     * @param employee
     * @return
     */
    int insert(Employee employee) throws Exception;

    /**
     * 
     * @param toInt
     * @param newpassword
     * @return
     */
    int updatePassword(Integer id, String newpassword) throws Exception;

    /**
     * 修改员工信息 (平台)
     * @param id
     * @param username
     * @param empnum
     * @param realname
     * @param mobile
     * @param roleid
     * @param shopid
     * @return
     */
    int update(Integer id, String username, String empnum, String realname, String mobile,
               Integer roleid, Integer shopid) throws Exception;

    /**
     * 更新员工对象 (卖家)
     * @param employee
     * @return
     */
    int updateEmployee(Employee employee) throws Exception;

    /**
     * 
     * @param userName
     * @param pwd
     * @return
     */
    Employee queryByNameAndPassword(String userName, String pwd) throws Exception;

}

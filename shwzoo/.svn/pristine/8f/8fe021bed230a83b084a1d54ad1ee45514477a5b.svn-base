/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.UserStatusEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.AccountsMapper;
import com.yinlian.wssc.web.mapper.ConfigdictionaryMapper;
import com.yinlian.wssc.web.mapper.EmployeeMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Employee;
import com.yinlian.wssc.web.po.EmployeeExample;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.EmployeeService;
import com.yinlian.wssc.web.util.CriteriaEmployee;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 会员的业务了类
 * @author Administrator
 * @version $Id: EmployeeServiceImpl.java, v 0.1 2016年3月14日 上午11:05:17 Administrator Exp $
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger    logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeMapper         employeeMapper;
    @Autowired
    private AccountsMapper         accountsMapper;
    @Autowired
    private ConfigdictionaryMapper configdictionaryMapper;
    @Autowired
    private UsersMapper            usersMapper;

    /** 
     * @see com.yinlian.wssc.web.service.EmployeeService#selectEmployeeByShopidPage(com.yinlian.wssc.web.util.CriteriaEmployee, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectEmployeeByShopidPage(CriteriaEmployee criteria, Integer pc, Integer ps)
                                                                                                 throws Exception {
        if (criteria == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Criteria is null!");
            }
        }
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Employee> beanList = employeeMapper.selectEmployeeByShopidPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /** 
     * @see com.yinlian.wssc.web.service.EmployeeService#deleteById(java.lang.String)
     */
    @Override
    public int deleteById(Integer id, Integer userid) throws Exception {
        Employee record = employeeMapper.selectByPrimaryKey(id);
        record.setIsdel(true);
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", record.getUsername());
        map.put("password", record.getPassword());
        Accounts accounts = accountsMapper.queryByUserNameAndPassword(map);
        accounts.setIsdel(true);
        accounts.setDeluserid(userid);
        accounts.setDeltime(new Date());
        accountsMapper.updateByPrimaryKey(accounts);
        return employeeMapper.updateByPrimaryKey(record);
    }

    /** 
     * @see com.yinlian.wssc.web.service.EmployeeService#updateIsLock(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public int updateIsLock(Integer id, String islock) throws Exception {
        Employee record = employeeMapper.selectByPrimaryKey(id);
        if ("true".endsWith(islock)) {
            record.setIslock(false);
        } else {
            record.setIslock(true);
        }
        return employeeMapper.updateByPrimaryKey(record);
    }

    /** 
     * @see com.yinlian.wssc.web.service.EmployeeService#insert(com.yinlian.wssc.web.po.Employee)
     */
    @Override
    public int insert(Employee employee) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        employee
            .setPassword(DEndecryptUtil.get_instances().passwordEncrypt(employee.getPassword()));
        map.put("username", employee.getUsername());
        map.put("password", employee.getPassword());
        Accounts _accounts = accountsMapper.queryByUserNameAndPassword(map);
        if (_accounts != null) {
            LogHandle.error(LogType.Messages, MessageFormat.format("添加员工信息错误", "用户名已被注册"),
                "EmployeeServiceImpl/insert");
            return -3;
        }
        Accounts record = new Accounts();
        record.setUsertype(UserTypeEnum.Employee.getValue()); // 员工
        record.setLoginname(employee.getUsername());
        record.setPassword(employee.getPassword());
        record.setPhone(employee.getMobile());
        record.setIsdel(false);
        record.setStatus(UserStatusEnum.正常.getValue()); //正常
        record.setRoleid(employee.getRoleid());
        record.setCreatetime(new Date());
        record.setEmail(employee.getEmail());

        Users users = new Users();
        users.setUsername(employee.getUsername());
        users.setPassword(employee.getPassword());
        users.setMobile(employee.getMobile());
        users.setStatus(UserStatusEnum.正常.getValue());
        users.setRealname(employee.getRealname());
        users.setEmail(employee.getEmail());
        usersMapper.insertUsers(users);
        //record.setUserid(users.getId());

//        ConfigdictionaryExample example = new ConfigdictionaryExample();
//        ConfigdictionaryExample.Criteria criteria = example.createCriteria();
//        criteria.andTypeEqualTo(ConfigSetTypeEnum.员工个数.getValue());
//        
        Integer value =0;
        Configdictionary configdictionary = configdictionaryMapper.selectByPrimaryType(ConfigSetTypeEnum.员工个数.getValue());
        if(configdictionary!=null){
        	value = StringUtilsEX.ToInt(configdictionary.getValue());
        }

        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria2 = employeeExample.createCriteria();
        criteria2.andShopidEqualTo(employee.getShopid());
        criteria2.andIsdelEqualTo(false);
        List<Employee> list = employeeMapper.selectByExample(employeeExample);
        if(list!=null){
        	 if (list.size() < value) { //当店铺员工数小于配置的最大员工数 才插入
                 employeeMapper.insertForId(employee);
                 record.setUserid(employee.getId());
                 return accountsMapper.insert(record);
             } else {
                 return -2;
             }
        }
        return 1;
    }

    /** 
     * @see com.yinlian.wssc.web.service.EmployeeService#updatePassword(java.lang.Integer, java.lang.String)
     */
    @Override
    public int updatePassword(Integer id, String newpassword) throws Exception {
        Employee record = employeeMapper.selectByPrimaryKey(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", record.getUsername());
        map.put("password", record.getPassword());
        Accounts accounts = accountsMapper.queryByUserNameAndPassword(map);
        Integer userid = accounts.getUserid();
        Users users = usersMapper.selectByPrimaryKey(userid);
        if (users != null) {
            users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(newpassword));
        }
        usersMapper.updateByPrimaryKey(users);

        accounts.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(newpassword));
        accountsMapper.updateByPrimaryKey(accounts);

        record.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(newpassword));
        return employeeMapper.updateByPrimaryKey(record);
    }

    /** 
     * @see com.yinlian.wssc.web.service.EmployeeService#update(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public int update(Integer id, String username, String empnum, String realname, String mobile,
                      Integer roleid, Integer shopid) throws Exception {

        Employee record = employeeMapper.selectByPrimaryKey(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", record.getUsername());
        map.put("password", record.getPassword());
        Accounts accounts = accountsMapper.queryByUserNameAndPassword(map);
        accounts.setLoginname(username);
        accountsMapper.updateByPrimaryKey(accounts);

        record.setEmpnum(empnum);
        record.setUsername(username);
        record.setRealname(realname);
        record.setMobile(mobile);
        record.setShopid(shopid);
        record.setRoleid(roleid);

        return employeeMapper.updateByPrimaryKey(record);
    }

    @Override
    public Employee selectById(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * @see com.yinlian.wssc.web.service.EmployeeService#updateEmployee(com.yinlian.wssc.web.po.Employee)
     */
    @Override
    public int updateEmployee(Employee employee) throws Exception {
        Employee record = employeeMapper.selectByPrimaryKey(employee.getId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", record.getUsername());
        map.put("password", record.getPassword());
        Accounts accounts = accountsMapper.queryByUserNameAndPassword(map);
        accounts.setLoginname(employee.getUsername());
        accounts.setPhone(employee.getMobile());
        accounts.setRoleid(employee.getRoleid());
        accountsMapper.updateByPrimaryKey(accounts);

        Integer userid = accounts.getUserid();
        Users users = usersMapper.selectByPrimaryKey(userid);
        if (users != null) {
            users.setUsername(employee.getUsername());
            users.setEmail(employee.getEmail());
            users.setRealname(employee.getRealname());
        }
        usersMapper.updateByPrimaryKey(users);

        BeanUtils.copyProperties(employee, record);
        return employeeMapper.updateByPrimaryKey(record);
    }

    /**
     * @see com.yinlian.wssc.web.service.EmployeeService#queryByNameAndPassword(java.lang.String, java.lang.String)
     */
    @Override
    public Employee queryByNameAndPassword(String userName, String pwd) throws Exception {
        Employee employee = new Employee();
        pwd = DEndecryptUtil.get_instances().passwordEncrypt(pwd);
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        criteria.andPasswordEqualTo(pwd);
        List<Employee> list = employeeMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            employee = list.get(0);
        }
        return employee;
    }

}

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.math.BigDecimal;
import java.util.List;

import com.yinlian.Enums.PointsRecordsFromTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.api.app.dto.UserDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Financerecords;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.UserLoginSession;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.util.CriteriaUser;

/**
 * 
 * @author Administrator
 * @version $Id: UserService.java, v 0.1 2016年3月9日 下午8:35:28 Administrator Exp $
 */
public interface UserService {

    /**
     * 通过名字模糊查询用户的列表
     * @param name
     * @param usertype 用户的类型 5表示卖家
     * @return
     */
    List<Users> selectByLikeName(String name, Integer usertype) throws Exception;

    /**
     * 通过用户名和密码查询用户信息
     * @param name
     * @param pwd
     * @return
     * @throws Exception
     */
    Accounts selectByNamaPwd(String name, String pwd) throws Exception;

    /**
     * 
     * @param userid
     * @return
     */
    Users queryById(Integer userid) throws Exception;

    /**
     * 用户身份登录
     * @param name
     * @param pwd
     * @return
     * @throws Exception
     */
    Object[] login(String name, String pwd,Integer usertype) throws Exception;

    Users selectId(String name, String pwd) throws Exception;

    /**
     * 添加用户
     * @param users
     * @return
     * @throws Exception
     */
    public int addUser(Users users) throws Exception;

    /**
     * 修改用户密码
     * @param id
     * @param pwd
     * @throws Exception
     */
    public void updPwd(Integer id, String pwd) throws Exception;

    /**
     * 修改联系人信息
     * @param toInt
     * @param name
     * @param mobile
     * @param email
     * @return
     */
    int update(Integer userid, String name, String mobile, String email) throws Exception;

    /**
     * 修改用户信息
     * @param user
     * @return
     * @throws Exception
     */
    int updateInfo(Users user) throws Exception;

    /**
     * 通过手机账号修改密码
     * @param map
     * @throws Exception
     */
    public int updPwdByPhone(String phone, String password) throws Exception;

    /**
     * 修改用户信息
     * @param user
     * @param userattr
     * @return
     * @throws Exception
     */
    void updatUserInfo(Users user, UserAttr userattr) throws Exception;

    /**
     * 将登录成功的信息插入到User_login_Session
     * @param record
     * @return
     * @throws Exception
     */
    public int insertUserLogin(UserLoginSession record) throws Exception;

    /**
     * 分页查询 会员信息列表
     * @param criteria
     * @param pc 当前页
     * @param ps 每页大小
     * @return
     */
    PageBean selectMemberListByPage(CriteriaUser criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 根据id修改用户信息
     * @param record
     * @return
     */
    int updateById(Users record) throws Exception;

    /**
     * 根据id查询users信息
     * @param id
     * @return
     */
    Users selectByPrimaryKey(Integer id) throws Exception;

    /**
     * 添加用户信息并返回主键
     * @param users
     * @return
     */
    int addUsers(Users users) throws Exception;

    /**
     * 更新用户积分
     * @param userid 用户ID
     * @param points 积分
     * @param Type 类型
     * @param FromType 来源类型
     */
    int updatePoint(Integer userid, int points, PointsRecordsTypeEnum Type,
                    PointsRecordsFromTypeEnum FromType) throws Exception;

    /**
     * 获取用户余额并锁定行（只有余额变更的时候 才能用）
     * @param userid 用户Id
     * @return
     */
    Users getRowLockById(int userid) throws Exception;

    /**
    * 获取用户信息并锁定行（只有余额变更的时候 才能用）
    * @param userid 用户Id
    * @return
    */
    Usercapital getBalanceRowLockById(int userid) throws Exception;

    /**
     * 更新用户财务信息
     * @param uc
     * @return
     * @throws Exception
     */
    int UsercapitalById(Usercapital uc) throws Exception;

    /**
     * 添加用户财务记录
     * @param uf
     * @return
     * @throws Exception
     */
    int addUserFinance(Userfinance uf) throws Exception;

    /**
     * 添加用户变更财务记录
     * @param urs
     * @return
     */
    int addFinancerecords(Financerecords record) throws Exception;

    /**
     * 根据手机号查询用户信息
     * @param mobile
     * @return
     * @throws Exception
     */
    List<Users> findInfo(String mobile) throws Exception;

    /**
     * 根据用户Id 获取最后一条 财务 记录
     * @param userID 用户Id 
     * @return
     */
    Userfinance getLastUserFinance(int userID) throws Exception;

    /**
     * 根据id删除会员的用户信息
     * @param id
     * @return
     */
    int deleteMemberById(Integer id) throws Exception;

    /**
     * 修改卖家的账号信息
     * @param users
     * @param userAttr
     * @return
     */
    int updateSeller(Users users, UserAttr userAttr) throws Exception;

    /**
     * 插入会员信息
     * @param users
     * @param typevalue
     * @param username
     * @param pwd
     * @param rpwd
     * @param ch 
     * @return
     */
    int insert(Users users, String typevalue, String username, String pwd, String rpwd, int channelType);

    /**
     * 设置支付密码
     * @param id
     * @param repwd
     * @return
     */
    int updPayPwd(Integer id, String repwd) throws Exception;

    /**
     * 修改会员支付密码
     * 
     * @param id
     * @param paypwd
     * @return
     * @throws Exception
     */
    int updatePayPasswordByUserId(Integer id, String paypwd) throws Exception;

    /**
     * 根据手机号查询用户信息
     * @param name
     * @return
     */
    List<Users> findInfobyName(String username) throws Exception;

    /**
     * 根据usernmae查询用户
     * 
     * @param username
     * @return
     * @throws Exception
     */
    List<Users> queryByUsernme(String username) throws Exception;

	Users findaccountInfo(String mobile, int value) throws Exception;
	
	int getByPayPwd(Integer userid, String paypwd) throws Exception;

	int updatePhone(Users users, Accounts accounts, UserAttr userAttr) throws Exception;

	int updatePwd(Users users, Accounts accounts) throws Exception;
	
	int updateuserlevel(Users users) throws Exception;
	
	int updateConsumeAmount(Integer amount, Integer id)throws Exception;
	
	int updPayPwdByPhone(String phone, String password)throws Exception;
	
	public Integer changePoints(int userid, String pointRuleEnum, BigDecimal money);

	/**
	 * 获取用户
	 * @param phone
	 * @param channelType
	 * @return
	 * @throws Exception
	 */
	List<UserDto> queryByPhone(String phone,int channelType)throws Exception;
	/**
	 * 平台修改密码
	 * @param userid
	 * @param newPwd
	 * @throws Exception
	 */
	void updatePlatPwd(int userid, String newPwd ) throws Exception ;

	List<Users> getUsersListByIds(String ids)throws Exception;
}

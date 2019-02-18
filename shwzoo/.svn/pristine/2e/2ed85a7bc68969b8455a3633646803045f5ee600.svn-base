/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.wssc.platform.vo.AccountsVo;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.AccountsDTO;
import com.yinlian.wssc.web.dto.UsersInfoDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.CriteriaUser;

/**
 * 
 * @author Administrator
 * @version $Id: AccountsService.java, v 0.1 2016年2月25日 下午4:48:21 Administrator
 *          Exp $
 * 
 */
public interface AccountsService {

	public List<AccountsDTO> queryAllAccounts(AccountsVo vo) throws Exception;

	/**
	 * 根据登录名和密码查询
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public Accounts queryByNameAndPassword(String name, String password)
			throws Exception;

	/**
	 * 根据会员名，密码，userType查询
	 * 
	 * @param name
	 * @param password
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public Accounts queryOperator(String name, String password, Integer userType)
			throws Exception;

	// public Accounts queryLogin(String name, String password, Integer[] array)
	// throws Exception;

	public Object[] queryLogin(String name, String password, Integer[] array)
			throws Exception;

	/**
	 * 手机号员名，密码，userType查询
	 * 
	 * @param phone
	 * @param password
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public Accounts queryByPhone(String phone, String password, Integer userType)
			throws Exception;

	/**
	 * 根据邮箱，密码，userType查询
	 * 
	 * @param email
	 * @param pasword
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public Accounts queryByEmail(String email, String password, Integer userType)
			throws Exception;

	/**
	 * 分页查询会员信息
	 * 
	 * @param criteria
	 * @param pc
	 *            当前页
	 * @param ps
	 *            每页大小
	 * @return
	 * @throws Exception
	 */
	public PageBean queryMemberByCriteria(CriteriaUser criteria, Integer pc,
			Integer ps) throws Exception;

	public Accounts queryByuserid(Integer id) throws Exception;

	public int addOperator(AccountsVo vo) throws Exception;

	public int updateOperator(AccountsVo vo) throws Exception;

	public int deleteOperator(Integer id) throws Exception;

	public PageBean getAccountsList(Criteria a, Integer page, Integer size)
			throws Exception;

	public Accounts getByRoleID(Integer roleid) throws Exception;

	AccountsDTO selectByID(Integer id) throws Exception;

	int updatePassword(Integer id, String pass) throws Exception;

	public int isExistAcc(CriteriaAccounts cAccounts) throws Exception;

    /**
     * 根据userid修改密码
     * @param userid
     * @param newpwd
     */
    public int updatePasswordByUserId(Integer userid, String newpwd ) throws Exception;

	public void updPwd(Integer uerid, String newpwd) throws Exception;

	public void updPwdByPhone(String phone, String pasword) throws Exception;

	/**
	 * 会员数据绑定
	 * 
	 * @param memberid
	 * @return
	 * @throws Exception
	 */
	public UsersInfoDto selectMemberById(Integer memberid) throws Exception;

	/**
	 * 添加会员的账户信息
	 * 
	 * @param accounts
	 * @return
	 * @throws Exception
	 */
	public int addAccounts(Accounts accounts) throws Exception;

	/**
	 * 验证手机号
	 * 
	 * @param usertype
	 * @param mobile
	 * @return
	 */
	public List<Accounts> queryByMobile(AccountsExample example)
			throws Exception;

	/**
	 * 根据userid删除账号信息
	 * 
	 * @param memberid
	 * @return
	 * @throws Exception
	 */
	public int deleteMemberByUserId(Integer memberid) throws Exception;

	/**
	 * 根据userid查询 卖家信息
	 * 
	 * @param userid
	 * @return
	 */
	public UsersInfoDto querySellerById(Integer userid) throws Exception;

	/**
	 * 根据userid查询会员信息
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<Accounts> queryMember(Integer userid) throws Exception;

	/**
	 * 根据userid修改会员
	 * 
	 * @param accounts
	 * @return
	 * @throws Exception
	 */
	public int updateMemberByUserId(Accounts accounts) throws Exception;

	/**
	 * 根据账号查询
	 * 
	 * @param userkey
	 * @param usertype
	 */
	public List<Accounts> selectByName(String userkey, Integer usertype)
			throws Exception;

	/**
	 * 根据手机号查询
	 * 
	 * @param userkey
	 * @param toInt
	 * @return
	 */
	public List<Accounts> selectByMobile(String userkey, Integer usertype)
			throws Exception;

	/**
	 * 根据用户名和用户类型模糊查询
	 * 
	 * @param usertype
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List<Accounts> getUserStartWithName(int usertype, String name)
			throws Exception;

	/**
	 * 批量添加站内信
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertBacth(List<Messages> list) throws Exception;

	/**
	 * 添加一条站内信
	 * 
	 * @param messages
	 * @return
	 * @throws Exception
	 */
	public int insertMessage(Messages messages) throws Exception;

	/**
	 * 冻结用户
	 * 
	 * @param toInt
	 * @param toInt2
	 * @return
	 */
	public int updatStatus(Integer id, Integer status) throws Exception;

	/**
	 * 
	 * 根据id查询用户
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Accounts queryById(Integer id) throws Exception;

	Accounts getSuperadmin() throws Exception;

	/**
	 * 添加新会员
	 * 
	 * @param usertype
	 *            用户类型
	 * @param username
	 *            用户名
	 * @param nickname
	 *            昵称
	 * @param realname
	 *            真实姓名
	 * @param pwd
	 *            密码
	 * @param mobile
	 *            手机
	 * @param email
	 * @param levelid
	 *            会员等级
	 * @param sex
	 * 			性别
	 * @param birthdayyear
	 * 			出生年
	 * @param birthmonth
	 * @param birthday
	 * @param provincecode
	 * @param provincename
	 * @param citycode
	 * @param cityname
	 * @param areacode
	 * @param areaname
	 * @param address
	 * 
	 * @param idcard
	 *            省份证号
	 * @return
	 */
	public int addMenber(Integer usertype, String imgurl, String username,
			String nickname, String realname, String pwd, String mobile,
			String email, Integer levelid, String idcard,String sex, String birthyear,
			String birthmonth, String birthday,
			String provincecode, String provincename, String citycode,
			String cityname, String areacode, String areaname, String address) throws Exception;

	/**
	 * 单点登录 通过username判断该用户是否可以登陆
	 * 
	 * @param username
	 * @return
	 */
	Object[] querybyName(String username, Integer[] array) throws Exception;

	public List<Accounts> queryUsers(Integer[] array, String name)
			throws Exception;

	public List<Accounts> selectName(String seachName) throws Exception;

	public List<Accounts> queryAccounts(Platfrom_SYCriteria criteria)
			throws Exception;

	public Accounts queryAccouts(String accName, String userType)
			throws Exception;

	public Accounts querySeller(String accName) throws Exception;

	public List<Accounts> getUserName(String name) throws Exception;

	/**
	 * 根据userid查询登录密码
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public String getPwdByUserid(Integer userid, Integer type) throws Exception;

	public Integer queryAccountsCount(Platfrom_SYCriteria criteria)throws Exception;
	
	public int isExistAccByPhone(CriteriaAccounts cAccounts) throws Exception;
	
}

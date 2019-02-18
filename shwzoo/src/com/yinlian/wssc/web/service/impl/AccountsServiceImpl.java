/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.Enums.CardTypEnum;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.Enums.UserStatusEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.wssc.platform.vo.AccountsVo;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.AccountsDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.UsersInfoDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.AccountsMapper;
import com.yinlian.wssc.web.mapper.AccountsMapperCustom;
import com.yinlian.wssc.web.mapper.CouponMapper;
import com.yinlian.wssc.web.mapper.CouponMapperCustom;
import com.yinlian.wssc.web.mapper.EmployeeMapper;
import com.yinlian.wssc.web.mapper.MessagesMapper;
import com.yinlian.wssc.web.mapper.RoleMenusMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.UserAttrMapper;
import com.yinlian.wssc.web.mapper.UserCouponMapper;
import com.yinlian.wssc.web.mapper.UserDepartMapper;
import com.yinlian.wssc.web.mapper.UsercapitalMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.mapper.UserslevelMapper;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.Employee;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.po.RoleMenus;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.UserCoupon;
import com.yinlian.wssc.web.po.UserDepart;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.CriteriaUser;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 
 * @author Administrator
 * @version $Id: AccountsServiceImpl.java, v 0.1 2016年2月26日 下午1:27:03
 *          Administrator Exp $
 */
public class AccountsServiceImpl implements AccountsService {

    private static final Logger  logger = LoggerFactory.getLogger(AccountsServiceImpl.class);
    @Autowired
    private AccountsMapperCustom accountsMapperCustom;
    @Autowired
    private AccountsMapper       accountsMapper;
    @Autowired
    private UsersMapper          userMapper;
    @Autowired
    private UserDepartMapper     userDepartMapper;
    @Autowired
    private MessagesMapper       messagesMapper;
    @Autowired
    private UserslevelMapper     userslevelMapper;
    @Autowired
    private UsersMapper          usersMapper;
    @Autowired
    private UserAttrMapper       userAttrMapper;
    @Autowired
    private UsercapitalMapper    usercapitalMapper;
    @Autowired
    private RoleMenusMapper      roleMenusMapper;
    @Autowired
    private ShopMapper           shopMapper;
    @Autowired
    private EmployeeMapper       employeeMapper;
    @Autowired
    private CouponMapperCustom   couponMapperCustom;
	@Autowired
	private CouponMapper         couponMapper;
	@Autowired
	private UserCouponMapper     userCouponMapper;
    /**
     * @see com.yinlian.wssc.web.service.AccountsService#queryAllAccounts(com.yinlian.wssc.platform.vo.AccountsVo)
     */
    @Override
    public List<AccountsDTO> queryAllAccounts(AccountsVo vo) throws Exception {
        if (vo == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数对象AccountsVo 为空!");
            }
            throw new IllegalArgumentException("The object class [" + vo.getClass() + "] is null");

        }
        return accountsMapperCustom.selectAllAccounts(vo);
    }

    /**
     * @see com.yinlian.wssc.web.service.AccountsService#queryByNameAndPassword(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Accounts queryByNameAndPassword(String name, String password) throws Exception {
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(name);
        criteria.andPasswordEqualTo(password);
        List<Accounts> list = accountsMapper.selectByExample(example);
        Accounts accounts = list.get(0);
        return accounts;
    }


    /**
     * @see com.yinlian.wssc.web.service.AccountsService#queryByuserid(java.lang.Integer)
     */
    @Override
    public Accounts queryByuserid(Integer id) throws Exception {
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(id);
        Accounts accounts = new Accounts();
        List<Accounts> list = accountsMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            accounts = list.get(0);
        }
        return accounts;
    }

    /**
     * 添加平台管理员
     */
    public int addOperator(AccountsVo vo) throws Exception {
        int ret = 0;
        Accounts accounts = new Accounts();
        vo.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(vo.getPassword()));
        Users users = new Users();
        // 添加users表并返回ID主键
        users.setUsername(vo.getUserName());
        users.setPassword(vo.getPassword());
        users.setNickname(vo.getNickName());
        
        users.setRealname(vo.getRealName());
        users.setMobile("");
        users.setEmail(vo.getEmail());
        users.setIsmobilecheck(false);
        users.setIsemailcheck(false);
        users.setStatus(0);
        users.setPoints(0);
        users.setPaypassstatus(0);
        users.setIdcard("");
        users.setImgurl("");
        users.setPaypass("");
        ret = userMapper.insertUsers(users);
        // 添加账户表信息
        accounts.setUserid(users.getId());
        
        accounts.setLoginname(vo.getUserName());
        accounts.setPassword(vo.getPassword());
        accounts.setUsertype(UserTypeEnum.Admin.getValue());
        accounts.setEmail(vo.getEmail());
        accounts.setCreatetime(new Date());
        accounts.setIsdel(false);
        accounts.setRoleid(vo.getRoleID());
        accounts.setStatus(0);
        ret = accountsMapper.insert(accounts);

        // 添加用户关联部门表信息
        if (vo.getDepartID() > 0) {
            UserDepart udDepart = new UserDepart();
            udDepart.setDepartId(vo.getDepartID());
            udDepart.setUserId(users.getId());
            ret = userDepartMapper.insert(udDepart);
        }

        return ret;
    }

    /**
     * 编辑平台管理员
     */
    public int updateOperator(AccountsVo vo) throws Exception {
        int ret = 0;
        Accounts accounts = accountsMapper.selectByPrimaryKey(vo.getID());

        Users users = userMapper.selectByPrimaryKey(accounts.getUserid());
        // 修改users表
        users.setUsername(vo.getUserName());
        users.setNickname(vo.getNickName());
        users.setRealname(vo.getRealName());
        users.setEmail(vo.getEmail());

        ret = userMapper.updateByPrimaryKey(users);
        // 修改账户表信息
        accounts.setUserid(users.getId());
        accounts.setLoginname(vo.getUserName());
        accounts.setEmail(vo.getEmail());
        accounts.setRoleid(vo.getRoleID());
        ret = accountsMapper.updateByPrimaryKey(accounts);

        // 修改用户关联部门表信息
        if (vo.getDepartID() > 0) {
            ret = userDepartMapper.deleteByUser(users.getId());
            UserDepart udDepart = new UserDepart();
            udDepart.setDepartId(vo.getDepartID());
            udDepart.setUserId(users.getId());
            ret = userDepartMapper.insert(udDepart);
        }

        return ret;
    }

    /**
     * 删除平台管理员
     */
    public int deleteOperator(Integer id) throws Exception {
        Accounts accounts = accountsMapper.selectByPrimaryKey(id);
        userMapper.deleteByPrimaryKey(accounts.getUserid());
        userDepartMapper.deleteByUser(accounts.getUserid());
        accountsMapper.deleteByPrimaryKey(id);
        return 1;
    }

    /**
     * 获取平台管理员列表
     */
    public PageBean getAccountsList(Criteria a, Integer page, Integer size) throws Exception {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(a, page, size);
        PageBean pageBean = pageBeanUtil.getPage();

        List<AccountsDTO> beanList = accountsMapperCustom.getAccountsByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);

        return pageBean;
    }

    /**
     * 根据角色ID获取账号
     * @param roleid
     * @return
     * @throws Exception
     */
    public Accounts getByRoleID(Integer roleid) throws Exception {
        return accountsMapper.getByRoleID(roleid);
    }

    public AccountsDTO selectByID(Integer id) throws Exception {
        return accountsMapperCustom.selectByID(id);
    }

    /**
     * 修改操作员密码
     * @param id
     * @param pass
     * @return
     * @throws Exception
     */
    public int updatePassword(Integer id, String pass) throws Exception {
        String oldpwd = "";
        Accounts accounts = new Accounts();
        accounts.setId(id);
        oldpwd = accounts.getPassword();
        accounts.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(pass));
    
        return accountsMapper.updatePassword(accounts);
    }

    /**
     * 判断用户名是否重复
     */
    public int isExistAcc(CriteriaAccounts cAccounts) throws Exception {
        return accountsMapper.isExistAcc(cAccounts);
    }
    public int isExistAccByPhone(CriteriaAccounts cAccounts) throws Exception {
        return accountsMapper.isExistAccByPhone(cAccounts);
    }
    /**
     * 
     * @see com.yinlian.wssc.web.service.AccountsService#queryMemberByCriteria(com.yinlian.wssc.web.util.CriteriaUser, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean queryMemberByCriteria(CriteriaUser criteria, Integer pc, Integer ps)
                                                                                        throws Exception {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<UsersInfoDto> beanList = accountsMapper.selectMemberByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;

    }

    /** 
     * @see com.yinlian.wssc.web.service.AccountsService#selectMemberById(java.lang.String)
     */
    @Override
    public UsersInfoDto selectMemberById(Integer memberid) throws Exception {
        UsersInfoDto usersInfoDto = accountsMapper.selectMemberById(memberid);
        return usersInfoDto;
    }

    /**
     * 添加会员的账户信息
     * @see com.yinlian.wssc.web.service.AccountsService#addAccounts(com.yinlian.wssc.web.po.Accounts)
     */
    @Override
    public int addAccounts(Accounts accounts) throws Exception {
        accounts
            .setPassword(DEndecryptUtil.get_instances().passwordEncrypt(accounts.getPassword()));
      
        return accountsMapper.insert(accounts);
    }

    /**
     * 验证手机号
     * @see com.yinlian.wssc.web.service.AccountsService#queryByMobile(java.lang.String, java.lang.String)
     */
    @Override
    public List<Accounts> queryByMobile(AccountsExample example) throws Exception {

        return accountsMapper.selectByExample(example);
    }
    @Override
    public Accounts queryOperator(String name, String password, Integer userType) throws Exception {

        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(name);
        criteria.andPasswordEqualTo(DEndecryptUtil.get_instances().passwordEncrypt(password));
        criteria.andUsertypeEqualTo(userType);
        List<Accounts> list = accountsMapper.selectByExample(example);
        if (list.size() > 0) {
            Accounts accounts = list.get(0);
            return accounts;
        } else {
            return null;
        }
    }

    @Override
    public Accounts queryByPhone(String phone, String password, Integer userType) throws Exception {
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        criteria.andPasswordEqualTo(password);
        criteria.andUsertypeEqualTo(userType);
        List<Accounts> list = accountsMapper.selectByExample(example);
        if (list.size() > 0) {
            Accounts accounts = list.get(0);
            return accounts;
        } else {
            return null;
        }
    }

    @Override
    public Accounts queryByEmail(String email, String password, Integer userType) throws Exception {
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        criteria.andPasswordEqualTo(password);
        criteria.andUsertypeEqualTo(userType);
        List<Accounts> list = accountsMapper.selectByExample(example);
        if (list.size() > 0) {
            Accounts accounts = list.get(0);
            return accounts;
        } else {
            return null;
        }
    }

    /**
     * 根据userid删除账号信息
     * @see com.yinlian.wssc.web.service.AccountsService#deleteMemberByUserId(java.lang.String)
     */
    @Override
    public int deleteMemberByUserId(Integer memberid) throws Exception {
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(memberid);
        return accountsMapper.deleteByExample(example);
    }

    /**
     * @see com.yinlian.wssc.web.service.AccountsService#updatePasswordByUserId(java.lang.Integer, java.lang.String)
     */
    @Override
    public int updatePasswordByUserId(Integer userid, String newpwd) throws Exception {
    	usersMapper.updatePwd(userid, DEndecryptUtil.get_instances().passwordEncrypt(newpwd));
		accountsMapper.updPwd(userid, DEndecryptUtil.get_instances().passwordEncrypt(newpwd));
		return 1;
    }


    @Override
    public void updPwdByPhone(String phone, String pasword) throws Exception {
//        String username = "";
//        String oldpwd = "";
//        AccountsExample example = new AccountsExample();
//        AccountsExample.Criteria criteria = example.createCriteria();
//        criteria.andPhoneEqualTo(phone);
//        List<Accounts> list = accountsMapper.selectByExample(example);
//        if (list != null && list.size() > 0) {
//            Accounts accounts = list.get(0);
//            username = accounts.getLoginname();
//            oldpwd = accounts.getPassword();
//        }
//      
        accountsMapper
            .updPwdByPhone(phone, DEndecryptUtil.get_instances().passwordEncrypt(pasword));
    }

    @Override
    public UsersInfoDto querySellerById(Integer userid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", userid);
        map.put("usertype", 5);
        return accountsMapperCustom.querySellerById(map);
    }

    @Override
    public Object[] queryLogin(String name, String password, Integer[] array) throws Exception {
    	/*
		 * Map<String, Object> map = new HashMap<String, Object>(); password =
		 * DEndecryptUtil.get_instances().passwordEncrypt(password);
		 * map.put("name", name); map.put("password", password);
		 * map.put("array", array); List<Accounts> list =
		 * accountsMapper.queryLogin(map); if (list.size() > 0) { Accounts
		 * accounts = list.get(0); return accounts; } else { return null; }
		 */

		Map<String, Object> map = new HashMap<String, Object>();
		password = DEndecryptUtil.get_instances().passwordEncrypt(password);
		map.put("name", name);
		map.put("password", password);
		map.put("array", array);
		List<Accounts> list = accountsMapper.queryLogin(map);
		Object[] rsl = new Object[2];
		rsl[0] = 0;
		if (list == null || list.size() <= 0) {
			rsl[0] = -1;
			return rsl;
		}
		if (list.get(0).getStatus() == UserStatusEnum.锁定.getValue()) {
			rsl[0] = -2;
			return rsl;
		}
		if (list.get(0).getStatus() != UserStatusEnum.正常.getValue()) {
			rsl[0] = -3;
			return rsl;
		}
		List<Integer> rights = new ArrayList<Integer>();
		List<RoleMenus> roleMenus = roleMenusMapper.selectByRoleID(list.get(0)
				.getRoleid());
		if (roleMenus != null && roleMenus.size() > 0) {
			for (int i = 0; i < roleMenus.size(); i++) {
				rights.add(roleMenus.get(i).getMenusid());
			}
		} else {
			rights = null;
		}
		Integer userid = list.get(0).getUserid();
		Integer shopId = 0;
		SessionUser su = new SessionUser();
		su.setCode(0);
		su.setId(userid);
		su.setUserId(userid);
		su.setLoginName(name);
		su.setRights(rights);
		su.setUtype(list.get(0).getUsertype());
		if (list.get(0).getUsertype() == UserTypeEnum.Employee.getValue()) {
			Employee employee = employeeMapper.selectByPrimaryStatus(list.get(0)
					.getUserid());
			if (employee != null) {
				shopId = employee.getShopid();
			}

			if (employee == null) {
				rsl[0] = -2;
				return rsl;
			}
			String realName = employee.getRealname();
			su.setName(realName);
			su.setShopid(shopId);
			rsl[1] = su;
		} else {
			Shop shop = shopMapper.selectByUserid(userid);
			if (shop != null) {
				shopId = shop.getId();
			}
			Users users = usersMapper.selectByPrimaryKey(list.get(0)
					.getUserid());
			if (users != null) {
				su.setName(users.getRealname());
			}
			su.setShopid(shopId);
			rsl[1] = su;
		}

		return rsl;
    }

    /**
     * 根据userid查询买家信息
     * @see com.yinlian.wssc.web.service.AccountsService#queryMember(java.lang.Integer)
     */
    @Override
    public List<Accounts> queryMember(Integer userid) throws Exception {
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        return accountsMapper.selectByExample(example);
    }

    /**
     * 根据userid修改会员
     * @see com.yinlian.wssc.web.service.AccountsService#updateMemberByUserId(com.yinlian.wssc.web.po.Accounts)
     */
    @Override
    public int updateMemberByUserId(Accounts accounts) throws Exception {
      
        return accountsMapper.updateByPrimaryKey(accounts);
    }

    /**
     * @see com.yinlian.wssc.web.service.AccountsService#selectByName(java.lang.String)
     */
    @Override
    public List<Accounts> selectByName(String userkey, Integer usertype) throws Exception {
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(userkey);
        criteria.andUsertypeEqualTo(usertype);
        criteria.andIsdelEqualTo(false);
        return accountsMapper.selectByExample(example);
    }

    /**
     * @see com.yinlian.wssc.web.service.AccountsService#selectByMobile(java.lang.String, java.lang.Integer)
     */
    @Override
    public List<Accounts> selectByMobile(String userkey, Integer usertype) throws Exception {
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(userkey);
        criteria.andUsertypeEqualTo(usertype);
        return accountsMapper.selectByExample(example);
    }

    public List<Accounts> getUserStartWithName(int usertype, String name) throws Exception {
        return accountsMapperCustom.getUserStartWithName(usertype, name);
    }

    /**
     * 批量添加站内信
     * @see com.yinlian.wssc.web.service.AccountsService#insertBacth(java.util.List)
     */
    @Override
    public int insertBacth(List<Messages> list) throws Exception {
        return accountsMapper.insertBacth(list);
    }

    /**
     * 添加一条站内信
     * @see com.yinlian.wssc.web.service.AccountsService#insertMessage(com.yinlian.wssc.web.po.Messages)
     */
    @Override
    public int insertMessage(Messages messages) throws Exception {
        return messagesMapper.insert(messages);
    }

    /**
     * 会员冻结
     * @see com.yinlian.wssc.web.service.AccountsService#updatStatus(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public int updatStatus(Integer id, Integer status) throws Exception {
        Accounts accounts = accountsMapper.selectByPrimaryKey(id);
        accounts.setStatus(status);
        return accountsMapper.updateByPrimaryKey(accounts);
    }

    /**
     * 根据id查询用户
     * @see com.yinlian.wssc.web.service.AccountsService#queryById(java.lang.Integer)
     */
    @Override
    public Accounts queryById(Integer id) throws Exception {
        return accountsMapper.selectByPrimaryKey(id);
    }

    public Accounts getSuperadmin() throws Exception {
        return accountsMapper.getSuperadmin();
    }

    /**
     * 添加新会员
     * @see com.yinlian.wssc.web.service.AccountsService#addMenber(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int addMenber(Integer usertype, String imgurl, String username, String nickname, String realname,
                         String pwd,String mobile, String email, 
                         Integer levelid,String idcard,String sex, String birthyear,
             			String birthmonth, String birthday,
            			String provincecode, String provincename, String citycode,
            			String cityname, String areacode, String areaname, String address) throws Exception {
    	
    	AccountsExample example = new AccountsExample();
		AccountsExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(mobile);
		criteria.andIsdelEqualTo(false);
		List<Accounts> result = queryByMobile(example);
		if (result.size() > 0) {
			return -1;
		}
    	//添加用户表并返回主键
        Users users = new Users();
        users.setImgurl(imgurl);
        users.setUsername(username);
        users.setNickname(nickname);
        users.setRealname(realname);
        users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(pwd));
        users.setMobile(mobile);
        users.setEmail(email);
        users.setTotalpoints(0);
        users.setPoints(0);
        users.setTotalbeans(0);
        //验证身份证号码是否为空，不为空的话把身份证的枚举带入
        if(!StringUtilsEX.IsNullOrWhiteSpace(idcard)){
        	 users.setIdcardtype(CardTypEnum.lvl1.getValue());
        	 users.setIdcard(idcard);
        }
       
        users.setAvailablebeans(0);
        users.setStatus(0);
        if (levelid == -1) {
            Userslevel userlevel = userslevelMapper.levelOrder();
            if(userlevel!=null&&userlevel.getId()>0){
            	users.setLevelid(userlevel.getId());
            }else{
            	return -200;
            }
        } else {
            users.setLevelid(levelid);
        }
        usersMapper.insertUsers(users);
        // 添加账户表信息
        Accounts accounts = new Accounts();
        accounts.setUsertype(usertype);
       // accounts.setImgurl(imgurl);
        accounts.setUserid(users.getId());
        accounts.setCreatetime(new Date());
        accounts.setLoginname(users.getUsername());
        accounts.setPassword(users.getPassword());
        accounts.setPhone(users.getMobile());
        accounts.setEmail(users.getEmail());
        accounts.setStatus(0);
        accounts.setIsdel(false);
        accountsMapper.insert(accounts);
        //添加用户关联表User_attr
        UserAttr userAttr = new UserAttr();
        userAttr.setUserid(users.getId());
        userAttr.setSex(StringUtilsEX.ToInt(sex));
        userAttr.setPhone(users.getMobile());
        userAttr.setTotalpoints(0);
        userAttr.setAddress(address);
        userAttr.setAreacode(areacode);
        userAttr.setAreaname(areaname);
        userAttr.setBirthday(StringUtilsEX.ToShortDate(birthday));
        userAttr.setCitycode(citycode);
        userAttr.setCityname(cityname);
        userAttr.setProvincename(provincename);
        userAttr.setProvincecode(provincecode);
        
        userAttrMapper.insert(userAttr);
        //添加用户关联表用户资金
        Usercapital usercapital = new Usercapital();
        usercapital.setUserid(users.getId());
        usercapital.setBalance(StringUtilsEX.ToDouble("0"));
        usercapital.setBond(StringUtilsEX.ToDouble("0"));
        usercapital.setFreezemoney(StringUtilsEX.ToDouble("0"));
//        usercapital.setStatus(1);
        usercapital.setStatus(UserFinance_Type.未支付.getValue());
        usercapitalMapper.insert(usercapital);
        
        //获取会员注册优惠券列表
        List<Coupon> clist=couponMapperCustom.getRegistergiftCoupon();
        if(clist!=null && clist.size()>0){
        	for (Coupon coupon : clist) {
        		Date dt = coupon.getEndtime();
        		if (coupon.getEndday() > 0) {
    				dt = DateUtil.addDays(coupon.getProvidetime(), coupon.getEndday());
    			}
        		int pcount=coupon.getGetcount();
        		if (coupon.getConponcount() <coupon.getGetcount()) {
        			pcount=coupon.getConponcount();
        		}
        		UserCoupon uc=null;
        		List<UserCoupon> ucList=new ArrayList<UserCoupon>();
        		for (int i = 1; i <= pcount; i++) {
        			uc = new UserCoupon();
        			uc.setFromtype(coupon.getFromtype());
        			uc.setCouponid(coupon.getId());
        			uc.setUserid(users.getId());
        			uc.setIsuser(false);
        			uc.setGettime(new Date());
        			uc.setIsdel(false);
        			uc.setOuttime(dt);
        			ucList.add(uc);
        		}
        		// 添加用户关联优惠券表信息
        		userCouponMapper.insertList(ucList);
        		// 减少优惠券数量
        		coupon.setConponcount(coupon.getConponcount() - pcount);
        		couponMapper.updateCount(coupon);
			}
        }
        
       return users.getId();
    }

	@Override
	 public Object[]  querybyName(String username,Integer[] array) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", username);
        map.put("array", array);
        List<Accounts> list = accountsMapper.selectByUsername(map);
        Object[] rsl = new Object[2];
        rsl[0] = 0;
        if (list == null || list.size() <= 0) {
            rsl[0] = -1;
            return rsl;
        }
        if (list.get(0).getStatus() == UserStatusEnum.锁定.getValue()) {
            rsl[0] = -2;
            return rsl;
        }
        if (list.get(0).getStatus() != UserStatusEnum.正常.getValue()) {
            rsl[0] = -3;
            return rsl;
        }
        List<Integer> rights = new ArrayList<Integer>();
        List<RoleMenus> roleMenus = roleMenusMapper.selectByRoleID(list.get(0).getRoleid());
        if (roleMenus != null && roleMenus.size() > 0) {
            for (int i = 0; i < roleMenus.size(); i++) {
                rights.add(roleMenus.get(i).getMenusid());
            }
        } else {
            rights = null;
        }
        Integer userid = list.get(0).getUserid();
        Integer shopId = 0;
        SessionUser su = new SessionUser();
        su.setCode(0);
        su.setId(userid);
        su.setUserId(userid);
        su.setLoginName(username);
        su.setRights(rights);
        if (list.get(0).getUsertype() == UserTypeEnum.Employee.getValue()) {
            Employee employee = employeeMapper.selectByPrimaryKey(list.get(0).getUserid());
            if (employee != null) {
                shopId = employee.getShopid();
            }
            String realName = employee.getRealname();
            su.setName(realName);
            su.setShopid(shopId);
            rsl[1] = su;
        } else {
            Shop shop = shopMapper.selectByUserid(userid);
            if (shop != null) {
                shopId = shop.getId();
            }
            Users users = usersMapper.selectByPrimaryKey(list.get(0).getUserid());
            if (users != null) {
                su.setName(users.getRealname());
            }
            su.setShopid(shopId);
            rsl[1] = su;
        }

        return rsl;
	}

	@Override
	public List<Accounts> queryUsers(Integer[] array, String name)
			throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("name", name);
	        map.put("array", array);
	        List<Accounts> list = accountsMapper.selectByLikeName(map);
		return list;
	}

	@Override
	public List<Accounts> selectName(String name) throws Exception {
		
		List<Accounts> list=new ArrayList<Accounts>();
		try {
			list = accountsMapper.selectname(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Accounts> queryAccounts(Platfrom_SYCriteria criteria) throws Exception {
		
		return accountsMapper.selectAccounts(criteria);
	}

	@Override
	public Accounts queryAccouts(String accName,String userType) throws Exception {
		
		return accountsMapper.queryAccounts(accName,userType);
	}

	@Override
	public Accounts querySeller(String accName) throws Exception {
		
		return accountsMapper.querySeller(accName);
	}

	@Override
	public List<Accounts> getUserName(String name) throws Exception {
		return accountsMapperCustom.getUserName(name);
	}

	/**
	 * 根据userid查询登录密码
	 */
	@Override
	public String getPwdByUserid(Integer userid,Integer usertype) throws Exception {
		return accountsMapper.getPwdByUserid(userid,usertype);
	}

	@Override
    public void updPwd(Integer uerid, String newpwd) throws Exception {
        String username = "";
        String oldpwd = "";
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(uerid);
        List<Accounts> list = accountsMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            Accounts accounts = list.get(0);
            username = accounts.getLoginname();
            oldpwd = accounts.getPassword();
        }
        accountsMapper.updPwd(uerid, DEndecryptUtil.get_instances().passwordEncrypt(newpwd));

    }

	@Override
	public Integer queryAccountsCount(Platfrom_SYCriteria criteria)
			throws Exception {
		return accountsMapper.queryAccountsCount(criteria);
	}
	
}

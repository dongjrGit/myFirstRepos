/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.PointsRecordsFromTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.Enums.UserStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.UserDto;
import com.yinlian.wssc.web.dto.Feature;
import com.yinlian.wssc.web.dto.MemberInfo;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.AccountsMapper;
import com.yinlian.wssc.web.mapper.FinancerecordsMapper;
import com.yinlian.wssc.web.mapper.UserAttrMapper;
import com.yinlian.wssc.web.mapper.UserLoginSessionMapper;
import com.yinlian.wssc.web.mapper.UsercapitalMapper;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Financerecords;
import com.yinlian.wssc.web.po.Pointsrule;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.UserLoginSession;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.UsersExample;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.PointRecordService;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yinlian.wssc.web.service.PointsruleService;
import com.yinlian.wssc.web.service.UserAttrService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.CriteriaUser;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.UserXml;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 * @version $Id: UserServiceImpl.java, v 0.1 2016年3月9日 下午8:35:38 Administrator
 *          Exp $
 */
public class UserServiceImpl implements UserService {

    /**
     * 日志输出的类
     */
    private static final Logger    logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UsersMapper            usersMapper;
    @Autowired
    private AccountsMapper         accountsMapper;
    @Autowired
    private UsercapitalMapper      usercapitalMapper;
    @Autowired
    private UserfinanceMapper      userfinanceMapper;
    @Autowired
    private FinancerecordsMapper   financerecordsMapper;

    @Autowired
    private UserAttrMapper         userAttrMapper;

    @Autowired
    private UserLoginSessionMapper userloginSessionMapper;

    @Autowired
    private PointRecordService     pointRecordService;

    @Autowired
    private AccountsService        accountsService;

    @Autowired
    private UserAttrService        userAttrService;

    @Autowired
    private UsercapitalService     usercapitalService;
    
	@Autowired
	private PointsruleService pointRuleService;
	
	@Autowired
	private PointsRecordService pointsRecordService;

    /**
     * @see com.yinlian.wssc.web.service.UserService#selectByLikeName(java.lang.String)
     */
    @Override
    public List<Users> selectByLikeName(String name, Integer usertype) throws Exception {
        List<Accounts> accounts = selectByUserType(usertype);
        Feature feature = new Feature();
        feature.setList(accounts);
        feature.setUsername(name);
        List<Users> list = usersMapper.selectByFeature(feature);
        return list;
    }

	@Override
	public void updatePlatPwd(int userid, String newPwd) throws Exception {
		usersMapper.updatePwd(userid, DEndecryptUtil.get_instances().passwordEncrypt(newPwd));
		accountsMapper.updPwd(userid, DEndecryptUtil.get_instances().passwordEncrypt(newPwd));
	}
	

    private List<Accounts> selectByUserType(Integer usertype) {
        AccountsExample example = new AccountsExample();
        AccountsExample.Criteria criteria = example.createCriteria();
        if (usertype != -1) {
            criteria.andUsertypeEqualTo(usertype);
        }
        return accountsMapper.selectByExample(example);
    }

    /**
     * @see com.yinlian.wssc.web.service.UserService#queryById(java.lang.Integer)
     */
    @Override
    public Users queryById(Integer userid) throws Exception {

        return usersMapper.selectByPrimaryKey(userid);
    }

    /**
     * @see com.yinlian.wssc.web.service.UserService#update(java.lang.Integer,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int update(Integer userid, String name, String mobile, String email) throws Exception {
        Users users = usersMapper.selectByPrimaryKey(userid);
        users.setRealname(name);
        users.setMobile(mobile);
        users.setEmail(email);
        UserXml userXml = new UserXml();
        userXml.setUserName(users.getUsername());
        userXml.setRealName(name);
        userXml.setMobile(mobile);
        userXml.setEmail(email);
        try {
        	//TODO： 原支撑系统 要修改
           /* Map<String, Object> map = WebserviceClient.editInformation(
                WebServiceTypeEnum.用户修改.getValue(), userXml);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice账户信息修改! 提交参数信息:{0},Webservice账户注册返回信息:{0},{1}", userXml.toString(),
                    map.get("ResultCode"), map.get("ResultMsg")), "userServiceImpl/update");
            } else {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice账户信息修改错误! 提交参数信息:{0},{1},Webservice账户注册返回信息:{0},{1}",
                    userXml.toString(), map.get("ResultCode"), map.get("ResultMsg")),
                    "userServiceImpl/update");
            }*/
        } catch (Exception e) {
            LogHandle.error(LogType.WEBSEVICE,
                MessageFormat.format("Webservice账户修改异常! 异常信息:{0}", e.getMessage()),
                "userServiceImpl/update");
        }
        return usersMapper.updateByPrimaryKey(users);
    }

    /**
     * @see com.yinlian.wssc.web.service.UserService#login(java.lang.String)
     */

    @Override
    public Object[] login(String name, String pwd, Integer usertype) throws Exception {
        /*
         * Accounts accountss = new Accounts(); accountss.setLoginname(name);
         * accountss
         * .setPassword(DEndecryptUtil.get_instances().passwordEncrypt(pwd));
         */
        Accounts accounts = accountsMapper.Login(name, DEndecryptUtil.get_instances()
            .passwordEncrypt(pwd));
        Object[] rsl = new Object[2];
        rsl[0] = 0;
        if (accounts == null) {
            rsl[0] = -1;
            return rsl;
        }
        if (accounts.getStatus() == UserStatusEnum.锁定.getValue()) {
            rsl[0] = -2;
            return rsl;
        }
        if (accounts.getStatus() != UserStatusEnum.正常.getValue()) {
            rsl[0] = -3;
            return rsl;
        }
    
        //Users users = usersMapper.getNameByID(accounts.getUserid());
        //Users users2 = users.get(0);
        SessionUser su = new SessionUser();
        su.setCode(0);
        su.setName(usersMapper.getNameByID(accounts.getUserid()));
//        if(users!=null){
//            su.setName(users.getUsername());
//        }
    	su.setId(accounts.getUserid());
        su.setUserId(accounts.getUserid());
        su.setLoginName(accounts.getLoginname());
        rsl[1] = su;
        return rsl;
    }

    @Override
    public Users selectId(String name, String pwd) throws Exception {

        Users users = usersMapper.selectId(name, DEndecryptUtil.get_instances()
            .passwordEncrypt(pwd));
        return users;
    }

    @Override
    public int addUser(Users users) throws Exception {
        users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(users.getPassword()));
        UserXml userXml = new UserXml();
        userXml.setUserName(users.getUsername());
        userXml.setNickName(users.getNickname());
        userXml.setRealName(users.getRealname());
        userXml.setMobile(users.getMobile());
        userXml.setPassword(users.getPassword());
        userXml.setPayword(DEndecryptUtil.get_instances().passwordEncrypt(users.getPaypass()));
        try {
        	//TODO： 原支撑系统 要修改
           /* Map<String, Object> map = WebserviceClient.register(WebServiceTypeEnum.用户注册.getValue(),
                userXml);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice账户注册! 提交参数信息:{0},Webservice账户注册返回信息:{0},{1}", userXml.toString(),
                    map.get("ResultCode"), map.get("ResultMsg")), "userServiceImpl/addUser");
            } else {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice账户注册错误! 提交参数信息:{0},{1},Webservice账户注册返回信息:{0},{1}",
                    userXml.toString(), map.get("ResultCode"), map.get("ResultMsg")),
                    "userServiceImpl/addUser");
            }*/
        } catch (Exception e) {
            LogHandle.error(LogType.WEBSEVICE,
                MessageFormat.format("Webservice账户注册异常! 异常信息:{0}", e.getMessage()),
                "userServiceImpl/addUser");
        }
        return usersMapper.insert(users);
    }

    @Override
    public void updPwd(Integer id, String pwd) throws Exception {
        String username = "";
        String oldpwd = "";
        Users users = usersMapper.selectByPrimaryKey(id);
        if (users != null) {
            username = users.getUsername();
            oldpwd = users.getPassword();
        }
        try {//TODO： 原支撑系统 要修改
          /*  Map<String, Object> map = WebserviceClient.changePassWordService(username, oldpwd,
                DEndecryptUtil.get_instances().passwordEncrypt(pwd), WebServiceTypeEnum.登录密码修改);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.debug(
                    LogType.WEBSEVICE,
                    MessageFormat.format("WebService修改店铺的密码成功! 参数信息:{0},{1},返回信息:{3}", pwd,
                        map.get("ResultMsg")), "userServiceImpl/updPwd");
            } else {
                LogHandle.debug(
                    LogType.WEBSEVICE,
                    MessageFormat.format("WebService修改店铺的密码失败! 参数信息:{0},{1},返回信息:{3}", pwd,
                        map.get("ResultMsg")), "userServiceImpl/updPwd");
            }*/
        } catch (Exception e) {
            LogHandle.debug(
                LogType.WEBSEVICE,
                MessageFormat.format("WebService修改店铺的密码失败! 参数信息:{0},{1},返回信息:{3}", pwd,
                    e.toString()), "userServiceImpl/updPwd");
        }
        usersMapper.updatePwd(id, DEndecryptUtil.get_instances().passwordEncrypt(pwd));
    }

    @Override
    public int updateInfo(Users user) throws Exception {

        Users users = usersMapper.selectByPrimaryKey(user.getId());
        users.setEmail(user.getEmail());
        users.setIdcard(user.getIdcard());
        users.setUsername(user.getUsername());
        users.setPassword(user.getPassword());
        users.setNickname(user.getNickname());
        users.setRealname(user.getRealname());
        users.setMobile(user.getMobile());
        users.setEmail(user.getEmail());
        users.setIsmobilecheck(user.getIsmobilecheck());
        users.setIsemailcheck(user.getIsemailcheck());
        users.setIdcardtype(user.getIdcardtype());
        users.setIdcard(user.getIdcard());
        users.setStatus(user.getStatus());
        users.setImgurl(user.getImgurl());
        users.setLevel(user.getLevel());
        users.setLevelid(user.getLevelid());
        users.setPoints(user.getPoints());
        users.setTotalpoints(user.getTotalpoints());
        users.setPaypass(user.getPaypass());
        users.setPaypassstatus(user.getPaypassstatus());
        UserXml userXml = new UserXml();
        userXml.setUserName(users.getUsername());
        userXml.setRealName(user.getRealname());
        userXml.setNickName(user.getNickname());
        userXml.setMobile(user.getMobile());
        userXml.setEmail(user.getEmail());
        try {//TODO： 原支撑系统 要修改
          /*  Map<String, Object> map = WebserviceClient.editInformation(
                WebServiceTypeEnum.用户修改.getValue(), userXml);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice账户信息修改! 提交参数信息:{0},Webservice账户注册返回信息:{0},{1}", userXml.toString(),
                    map.get("ResultCode"), map.get("ResultMsg")), "userServiceImpl/update");
            } else {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice账户信息修改错误! 提交参数信息:{0},{1},Webservice账户注册返回信息:{0},{1}",
                    userXml.toString(), map.get("ResultCode"), map.get("ResultMsg")),
                    "userServiceImpl/update");
            }*/
        } catch (Exception e) {
            LogHandle.error(LogType.WEBSEVICE,
                MessageFormat.format("Webservice账户修改异常! 异常信息:{0}", e.getMessage()),
                "userServiceImpl/update");
        }

        return usersMapper.updateByPrimaryKey(users);
    }

    @Override
    public int updPwdByPhone(String phone, String password) throws Exception {
        String username = phone;
        String oldpwd = "";
        List<Users> list = usersMapper.findInfo(phone);
        if (list != null && list.size() > 0) {
            Users users = list.get(0);
            username = users.getUsername();
            oldpwd = users.getPassword();
        }
        try {//TODO： 原支撑系统 要修改
           /* Map<String, Object> map = WebserviceClient
                .changePassWordService(username, oldpwd, DEndecryptUtil.get_instances()
                    .passwordEncrypt(password), WebServiceTypeEnum.登录密码修改);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.debug(
                    LogType.WEBSEVICE,
                    MessageFormat.format("WebService修改密码成功! 参数信息:{0},{1},返回信息:{3}", password,
                        map.get("ResultMsg")), "userServiceImpl/updPwdByPhone");
            } else {
                LogHandle.debug(
                    LogType.WEBSEVICE,
                    MessageFormat.format("WebService修改密码失败! 参数信息:{0},{1},返回信息:{3}", password,
                        map.get("ResultMsg")), "userServiceImpl/updPwdByPhone");
            }*/
        } catch (Exception e) {
            LogHandle.debug(
                LogType.WEBSEVICE,
                MessageFormat.format("WebService修改密码失败! 参数信息:{0},{1},返回信息:{3}", password,
                    e.toString()), "userServiceImpl/updPwdByPhone");
        }
        return usersMapper.updateByPhone(phone,
            DEndecryptUtil.get_instances().passwordEncrypt(password));
    }

    @Override
    public void updatUserInfo(Users user, UserAttr userattr) throws Exception {
        try {
            UserXml userXml = new UserXml();
            userXml.setUserName(user.getUsername());
            userXml.setMobile(user.getMobile());
            userXml.setEmail(user.getEmail());
            userXml.setGender(userattr.getSex() + "");
        
        } catch (Exception e) {
            LogHandle.debug(LogType.WEBSEVICE, MessageFormat.format(
                "WebService修改用户信息失败! 参数信息:{0},{1},返回信息:{3}", user.toString(), e.toString()),
                "userServiceImpl/updatUserInfo");
        }
        usersMapper.updateByPrimaryKey(user);
        userAttrMapper.updateByPrimaryKey(userattr);
    }

    @Override
    public int insertUserLogin(UserLoginSession record) throws Exception {

        return userloginSessionMapper.insertSelective(record);
    }

    @Override
    public Accounts selectByNamaPwd(String name, String pwd) throws Exception {

        return accountsMapper.Login(name, pwd);
    }

    @Override
    public int updatePoint(Integer userid, int points, PointsRecordsTypeEnum Type,
                           PointsRecordsFromTypeEnum FromType) throws Exception {
        Users usermodel = queryById(userid);
        if (usermodel == null) {
            return -1;
        }
        switch (Type) {
            case 消费:
                usermodel.setPoints(usermodel.getPoints() - points);
                usermodel.setTotalpoints(usermodel.getTotalpoints() - points);
                break;
            case 增加:
                usermodel.setPoints(usermodel.getPoints() + points);
                usermodel.setTotalpoints(usermodel.getTotalpoints() + points);
                break;
        }
        if (usersMapper.updateByPrimaryKey(usermodel) > 0) {
            // 积分记录
            pointRecordService.add(points, Type, FromType, userid);
            return 0;
        } else {
            return -1;
        }
    }
    
    /**
	 * 按积分规则修改用户积分
	 * @param userid 用户id
	 * @param pointRuleEnum 积分规则名称
	 * @param money 订单金额  ★只有在用户支付订单成功给用户增加相应积分时（积分规则名称为消费时）需要，其他情况一律传0
	 * @return 1:正常修改积分完成    -1:userid错误   -2:根据用户等级和积分名称未查询到相应积分规则信息    -3:积分规则名称错误    -9系统异常
	 */
	@Override
	public Integer changePoints(int userid, String pointRuleEnum, BigDecimal money) {
		try {
			Users user = usersMapper.selectByPrimaryKey(userid);
			if (user == null) {
				LogHandle.error(LogType.User, MessageFormat.format("修改用户积分错误，未查询到用户信息！userid:{0}", userid), "userService/changePoints");
				return -1;
			}
			int userlevel = user.getLevelid() == null ? 1 : user.getLevelid();//levelid
			List<Pointsrule> rules = pointRuleService.querPointsruleByUserLevelAndName(userlevel, pointRuleEnum);
//			if (rules == null || rules.isEmpty()) {
//				LogHandle.error(LogType.User, MessageFormat.format("修改用户积分错误，未查询到平台设置积分规则信息！userLevel:{0},ruleName:{1}",
//						userlevel, pointRuleEnum), "userService/changePoints");
//				return -2;
//			}
			if(rules.size()>0 || !rules.isEmpty()){
				Pointsrule rule = rules.get(0);
				switch (pointRuleEnum) {
				case "消费":
					int point = (int)Math.floor(money.divide(new BigDecimal(rule.getValue())).doubleValue()) * rule.getPoints();
					if(point > 0){
						pointsRecordService.updateUserPoint(point, userid, PointsRecordsFromTypeEnum.订单消费.getValue(), PointsRecordsTypeEnum.增加.getValue());
					}
					break;
				case "签到":
					pointsRecordService.updateUserPoint(rule.getPoints(), userid, PointsRecordsFromTypeEnum.签到.getValue(), PointsRecordsTypeEnum.增加.getValue());
					break;
				case "好评":
				case "中评":
				case "差评":
					pointsRecordService.updateUserPoint(rule.getPoints(), userid, PointsRecordsFromTypeEnum.订单评价.getValue(), PointsRecordsTypeEnum.增加.getValue());
					break;
				default:
					LogHandle.error(LogType.User, MessageFormat.format("修改用户积分错误，无效积分规则名称：{0}！", pointRuleEnum), "userService/changePoints");
					return -3;
				}
			}
		} catch (Exception e) {
			LogHandle.error(LogType.User, MessageFormat.format("修改用户积分异常！异常信息：{0}", e.getMessage()), "userService/changePoints");
			return -9;
		}
		return 1;
	}
    
    @Override
    public Usercapital getBalanceRowLockById(int userid) throws Exception {
        return usercapitalMapper.getBalanceRowLockById(userid);
    }

    @Override
    public int UsercapitalById(Usercapital uc) throws Exception {
        return usercapitalMapper.updateByPrimaryKey(uc);
    }

    @Override
    public int addUserFinance(Userfinance uf) throws Exception {
        return userfinanceMapper.insert(uf);
    }

    @Override
    public int addFinancerecords(Financerecords record) throws Exception {
        return financerecordsMapper.insert(record);
    }

    /**
     * @see com.yinlian.wssc.web.service.UserService#selectMemberListByPage(com.yinlian.wssc.web.util.CriteriaUser,
     *      java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectMemberListByPage(CriteriaUser criteria, Integer pc, Integer ps)
                                                                                         throws Exception {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
                                                                       // 设置其他的参数
                                                                       // 多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<MemberInfo> beanList = usersMapper.selectMemberListByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /**
     * 根据id修改用户信息
     * 
     * @see com.yinlian.wssc.web.service.UserService#updateById(com.yinlian.wssc.web.po.Users)
     */
    @Override
    public int updateById(Users record) throws Exception {
        try {
            UserXml userXml = new UserXml();
            userXml.setUserName(record.getUsername());
            userXml.setMobile(record.getMobile());
            userXml.setEmail(record.getEmail());
          //TODO： 原支撑系统 要修改
          /*  Map<String, Object> map = WebserviceClient.editInformation(
                WebServiceTypeEnum.用户修改.getValue(), userXml);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.debug(
                    LogType.WEBSEVICE,
                    MessageFormat.format("WebService修改用户信息成功! 参数信息:{0},{1},返回信息:{3}",
                        record.toString(), map.get("ResultMsg")), "userServiceImpl/updateById");
            } else {
                LogHandle.debug(
                    LogType.WEBSEVICE,
                    MessageFormat.format("WebService修改用户信息失败! 参数信息:{0},{1},返回信息:{3}",
                        record.toString(), map.get("ResultMsg")), "userServiceImpl/updateById");
            }*/
        } catch (Exception e) {
            LogHandle.debug(LogType.WEBSEVICE, MessageFormat.format(
                "WebService修改用户信息失败! 参数信息:{0},{1},返回信息:{3}", record.toString(), e.toString()),
                "userServiceImpl/updateById");
        }

        return usersMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据id查询用户信息
     * 
     * @see com.yinlian.wssc.web.service.UserService#selectByPrimaryKey(java.lang.Integer)
     */
    @Override
    public Users selectByPrimaryKey(Integer id) throws Exception {

        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addUsers(Users users) throws Exception {
        users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(users.getPassword()));
        UserXml userXml = new UserXml();
        userXml.setUserName(users.getUsername());
        userXml.setNickName(users.getNickname());
        userXml.setRealName(users.getRealname());
        userXml.setMobile(users.getMobile());
        userXml.setPassword(users.getPassword());
        userXml.setPayword(DEndecryptUtil.get_instances().passwordEncrypt(users.getPaypass()));
        try {//TODO： 原支撑系统 要修改
          /*  Map<String, Object> map = WebserviceClient.register(WebServiceTypeEnum.用户注册.getValue(),
                userXml);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice账户注册! 提交参数信息:{0},Webservice账户注册返回信息:{0},{1}", userXml.toString(),
                    map.get("ResultCode"), map.get("ResultMsg")), "userServiceImpl/addUsers");
            } else {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice账户注册错误! 提交参数信息:{0},{1},Webservice账户注册返回信息:{0},{1}",
                    userXml.toString(), map.get("ResultCode"), map.get("ResultMsg")),
                    "userServiceImpl/addUsers");
            }*/
        } catch (Exception e) {
            LogHandle.error(LogType.WEBSEVICE,
                MessageFormat.format("Webservice账户注册异常! 异常信息:{0}", e.getMessage()),
                "userServiceImpl/addUsers");
        }
        return usersMapper.insertUsers(users);
    }

    @Override
    public Userfinance getLastUserFinance(int userID) throws Exception {
        return userfinanceMapper.getLastUserFinance(userID);
    }

    @Override
    public int deleteMemberById(Integer id) throws Exception {
        return usersMapper.deleteByPrimaryKey(id);
    }

    /**
     * @see com.yinlian.wssc.web.service.UserService#updateSeller(com.yinlian.wssc.web.po.Users,
     *      com.yinlian.wssc.web.po.UserAttr)
     */
    @Override
    public int updateSeller(Users users, UserAttr userAttr) throws Exception {
        Users recordUsers = usersMapper.selectByPrimaryKey(users.getId());
        BeanUtils.copyProperties(users, recordUsers);
        usersMapper.updateByPrimaryKey(recordUsers);
        UserAttr recordAttr = userAttrMapper.selectByPrimaryKey(userAttr.getId());
        BeanUtils.copyProperties(userAttr, recordAttr);
        return userAttrMapper.updateByPrimaryKey(recordAttr);
    }

    @Override
    public List<Users> findInfo(String mobile) throws Exception {

        List<Users> users = new ArrayList<Users>();
        users = usersMapper.findInfo(mobile);
        return users;
    }

    @Override
    public Users getRowLockById(int userid) throws Exception {
        return usersMapper.getRowLockById(userid);
    }

    @Override
    public int insert(Users users, String typevalue, String username, String pwd, String rpwd,int channelType) {

        try {
            users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(users.getPassword()));
            int result = usersMapper.insertUsers(users);

            Accounts accounts = new Accounts();
            accounts.setPhone(typevalue);
            accounts.setLoginname(username);
            accounts.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(pwd));
            accounts.setStatus(0);
            accounts.setUsertype(2);
            accounts.setUserid(users.getId());
            accounts.setCreatetime(new Date());
            accounts.setIsdel(false);
            accounts.setChannelType(channelType);

            int result1 = accountsService.addAccounts(accounts);

            UserAttr userAttr = new UserAttr();
            userAttr.setUserid(users.getId());
            userAttr.setPhone(typevalue);
            userAttr.setTotalpoints(0);

            int result2 = userAttrService.addUserAttr(userAttr);

            // 添加用户关联表用户资金
            Usercapital usercapital = new Usercapital();
            usercapital.setUserid(users.getId());
            usercapital.setBalance(0.0d);
            usercapital.setBond(0.0d);
            usercapital.setFreezemoney(0.0d);
            usercapital.setStatus(1);
            int result3 = usercapitalService.addUsercapital(usercapital);
            if ((result > 0) && (result1 > 0) && (result2 > 0) && (result3 > 0)) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    public int updPayPwd(Integer id, String repwd) throws Exception {
        String username = "";
        String oldpwd = "";
        Users users = usersMapper.selectByPrimaryKey(id);
        if (users != null) {
            username = users.getUsername();
            oldpwd = users.getPaypass();
        }
        try {//TODO： 原支撑系统 要修改
          /*  Map<String, Object> map = WebserviceClient.changePayWordService(username, oldpwd,
                DEndecryptUtil.get_instances().passwordEncrypt(repwd), WebServiceTypeEnum.支付密码修改);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.debug(
                    LogType.UserCenterManage,
                    MessageFormat.format("WebService修改支付密码成功! 参数信息:{0},{1},返回信息:{3}", repwd,
                        map.get("ResultMsg")), "userServiceImpl/updatePayPwd");
            } else {
                LogHandle.debug(
                    LogType.UserCenterManage,
                    MessageFormat.format("WebService修改支付密码失败! 参数信息:{0},{1},返回信息:{3}", repwd,
                        map.get("ResultMsg")), "userServiceImpl/updatePayPwd");
            }*/
        } catch (Exception e) {
            LogHandle.debug(
                LogType.UserCenterManage,
                MessageFormat.format("WebService修改支付密码异常! 参数信息:{0},{1},返回信息:{3}", repwd,
                    e.toString()), "userServiceImpl/updatePayPwd");
        }
        return usersMapper.updatePayPwd(id, DEndecryptUtil.get_instances().passwordEncrypt(repwd));
    }

    /**
     * 修改支付密码
     * 
     * @see com.yinlian.wssc.web.service.UserService#updatePayPasswordByUserId(java.lang.Integer,
     *      java.lang.String)
     */
    @Override
    public int updatePayPasswordByUserId(Integer id, String paypwd) throws Exception {
        Users users = usersMapper.selectByPrimaryKey(id);
        String oldpwd = users.getPaypass();
        try {
        	//TODO： 原支撑系统 要修改
           /* Map<String, Object> map = WebserviceClient.changePayWordService(users.getUsername(),
                oldpwd, DEndecryptUtil.get_instances().passwordEncrypt(paypwd),
                WebServiceTypeEnum.支付密码修改);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.debug(
                    LogType.UserCenterManage,
                    MessageFormat.format("WebService修改支付密码成功! 参数信息:{0},{1},返回信息:{3}", paypwd,
                        map.get("ResultMsg")), "userServiceImpl/updatePayPasswordByUserId");
            } else {
                LogHandle.debug(
                    LogType.UserCenterManage,
                    MessageFormat.format("WebService修改支付密码失败! 参数信息:{0},{1},返回信息:{3}", paypwd,
                        map.get("ResultMsg")), "userServiceImpl/updatePayPasswordByUserId");
            }*/
        } catch (Exception e) {
            LogHandle.debug(
                LogType.UserCenterManage,
                MessageFormat.format("WebService修改支付密码异常! 参数信息:{0},{1},返回信息:{3}", paypwd,
                    e.toString()), "userServiceImpl/updatePayPasswordByUserId");
        }
        users.setPaypass(DEndecryptUtil.get_instances().passwordEncrypt(paypwd));
        return usersMapper.updateByPrimaryKey(users);
    }

    /**
     * 根据username查询用户
     * 
     * @see com.yinlian.wssc.web.service.UserService#queryByUsernme(java.lang.String)
     */
    @Override
    public List<Users> queryByUsernme(String username) throws Exception {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        return usersMapper.selectByExample(example);
    }

    @Override
    public List<Users> findInfobyName(String username) throws Exception{
        List<Users> users = new ArrayList<Users>();
        users = usersMapper.findInfoByname(username);
        return users;
    }

	@Override
	public Users findaccountInfo(String mobile, int value) throws Exception {
		Accounts accounts=accountsMapper.findAccount(mobile,value);
		if(accounts!=null){
			Users user= usersMapper.selectByPrimaryKey(accounts.getUserid());
			if(user!=null){
				return user;
			}
		}
		
		return null;
	}

	@Override
	public int getByPayPwd(Integer userid, String paypwd) throws Exception {
		int ret=0;
		Users user= usersMapper.selectByPrimaryKey(userid);
		if(user==null){
			ret= -1;         //用户不存在
		}else{
			if(user.getPaypass()==null){
				ret= -2;         //未设置支付密码
			}else{
				if(user.getPaypass().equals(DEndecryptUtil.get_instances().passwordEncrypt(paypwd))){
					ret= 1;       //验证成功
				}else{
					ret=-3;       //密码错误
				}
			}
		}
		return ret;
	}

	@Override
	public int updatePhone(Users users, Accounts accounts, UserAttr userAttr)
			throws Exception {
			usersMapper.updateByPrimaryKeySelective(users);
			accountsMapper.updateByPrimaryKeySelective(accounts);
			int i=userAttrMapper.updateByPrimaryKeySelective(userAttr);
		return i;
	}

	@Override
	public int updatePwd(Users users, Accounts accounts) throws Exception {
		
		usersMapper.updateByPrimaryKeySelective(users);
		int i=accountsMapper.updateByPrimaryKeySelective(accounts);
		return i;
	}

	@Override
	public int updateuserlevel(Users users) throws Exception {
		
		return usersMapper.updateuserlevel(users);
	}

	@Override
	public int updateConsumeAmount(Integer amount, Integer id) throws Exception {		
		return usersMapper.updateConsumeAmount(amount, id);
	}

	@Override
	public int updPayPwdByPhone(String phone, String password) throws Exception {
		return usersMapper.updPayPwdByPhone(phone,
            DEndecryptUtil.get_instances().passwordEncrypt(password));
	}

	@Override
	public List<UserDto> queryByPhone(String phone, int channelType) throws Exception {
		return usersMapper.selectByPhone(phone,channelType);
	}

	@Override
	public List<Users> getUsersListByIds(String ids) throws Exception {
		
		return usersMapper.getUsersListByIds(ids);
	}

}

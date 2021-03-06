package com.yinlian.api.app.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PointRecordsTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.UserInfoDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.search.Pc_PointsCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.BankCard;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Pointrecords;
import com.yinlian.wssc.web.po.Pointsrecords;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.po.WebsitesConfig;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AcctountNoService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.BankCardService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.PointRecordService;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.UserAttrService;
import com.yinlian.wssc.web.service.UserFinanceService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.service.WebsitesConfigService;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.CriteriaFinance;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.JsonUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/app/userinfo")
public class UserInfoController {

	@Autowired
	private UserService userService;


	@Autowired
	private AccountsService accountsService;

	@Autowired
	private UserAttrService userAttrService;

	@Autowired
	private ProvinceServcice provinceService;

	@Autowired
	private CityServcie cityService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private PointRecordService pointRecordService;

	@Autowired
	private BankCardService bankCardService;

	@Autowired
	private UserslevelService userslevelService;
	@Autowired
	private AcctountNoService acctountNoService;

	@Autowired
	private UsercapitalService usercapitalService;
	
	@Autowired
	private  OperaterecordsService  operaterecordsService;
	
	@Autowired
	private PointsRecordService pointsRecordService;
	
	@Autowired
	private NewsService newService;
    @Autowired private WebsitesConfigService websitesConfigService;
    @Autowired
	private UserFinanceService userfinanceService;

	/**
	 * 会员登录
	 * 
	 * @param mobile
	 * @param pwd
	 * @param ch
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	public @ResponseBody String Login(String mobile, String pwd, String ch,
			String code) {
		BaseResult item = new BaseResult();
		try {
			item.setCode(0);
			UUID token = UUID.randomUUID();
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-101);
				item.setDesc("手机号码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(pwd)) {
				item.setCode(-103);
				item.setDesc("密码不能为空！");
				return item.toJson();
			}

			String userInfo = "";
			Object[] rls = userService.login(mobile, pwd,
					UserTypeEnum.Buyer.getValue());
			switch (Integer.parseInt(rls[0].toString())) {
			case 0:
				userInfo = JsonUtil.getJsonStringFromObject(rls[1]);
				break;
			case -1:
				item.setCode(-104);
				item.setDesc("登录失败,手机号码或密码错误！");
				return item.toJson();
			case -2:
				item.setCode(-105);
				item.setDesc("登录失败,账户被锁定！");
				return item.toJson();
			case -3:
				item.setCode(-106);
				item.setDesc("登录失败,账户异常！");
				return item.toJson();
			default:
				item.setCode(-104);
				item.setDesc("登录失败,手机号码或密码错误！");
				return item.toJson();
			}
			String key = DEndecryptUtil.get_instances().passwordEncrypt(
					"T" + ch + token);
			item.setToken(key);
			SessionState.SetSessionUser(key, (SessionUser) rls[1]);
			item.setData(userInfo);
		
			return item.toJson();
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"会员登录异常! 异常信息:{0}", e,
					"userinfo/login");
		}
		return item.toJson();
	}
	/**
	 * 验证注册
	 * 
	 * @param mobile
	 * @param sms
	 * @param password
	 * @return
	 */
	
	@RequestMapping(value = "/checkregister", produces = "text/html;charset=UTF-8")
	public @ResponseBody String checkregister(String password,String mobile,String sms,
			 String ch) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-101);
				item.setDesc("手机号不能为空！");
				return item.toJson();
			}
		
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
		
			if (StringUtilsEX.IsNullOrWhiteSpace(sms)) {
				item.setCode(-102);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(password)) {
				item.setCode(-103);
				item.setDesc("密码不能为空！");
				return item.toJson();
			}

			if (password.trim().length() < 6 || password.trim().length() > 32) {
				item.setCode(-109);
				item.setDesc("密码长度不符");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
		
			List<Users> user1 = userService.findInfobyName(mobile);
			if(user1.size() > 0) 
				{
				item.setCode(-107);
				item.setDesc("该手机号(mobile)已被注册"); 
				return item.toJson(); 
				}
			 
			AccountsExample example = new AccountsExample();
			AccountsExample.Criteria criteria = example.createCriteria();
			criteria.andPhoneEqualTo(mobile);
			criteria.andIsdelEqualTo(false);
			List<Accounts> result = accountsService.queryByMobile(example);
			if (result.size() > 0) {
				item.setCode(-106);
				item.setDesc("该手机号已被注册，你可以找会密码");
				return item.toJson();
			}

			String smss = RedisUserInfo.Get("I" + mobile.trim());

			if (!sms.equals(smss)) {
				item.setCode(-104);
				item.setDesc("验证码有误");
				return item.toJson();
			}
			
			/*Userslevel userlevel = userslevelService.getlowerLevel();

			if (userlevel == null) {
				item.setCode(-105);
				item.setDesc("用户等级不存在！");
				return item.toJson();
			}
			String no = acctountNoService.getNo();
			int temp = accountsService
					.addMenber(UserTypeEnum.Buyer.getValue(),"","1" + no,"","",password, mobile, email,userlevel.getId(),"");
			if (temp > 0) {
				item.setData(temp);
				item.setCode(0);
				item.setDesc("用户注册成功");
			}else{
				item.setCode(-200);
				if(temp==-200){
					item.setDesc("系统未设置用户等级");
				}else{				
				item.setDesc("账号注册失败");}
			}*/

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"账号注册异常! 异常信息:{0}", e,
					"userinfo/checkregister");
		}

		return item.toJson();
	}

	
	
	/**
	 * 账号注册
	 * 
	 * @param mobile
	 * @param sms
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/register", produces = "text/html;charset=UTF-8")
	public @ResponseBody String register( String imgurl,String nickName,String realName,String
			password,String mobile,String email,String sms,
			 String ch,String idcard,String sex, String birthyear,
				String birthmonth, String birthday,
				String provincecode, String provincename, String citycode,
				String cityname, String areacode, String areaname, String address) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-101);
				item.setDesc("手机号不能为空！");
				return item.toJson();
			}
		
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			
			if (StringUtilsEX.IsNullOrWhiteSpace(sms)) {
				item.setCode(-102);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(password)) {
				item.setCode(-103);
				item.setDesc("密码不能为空！");
				return item.toJson();
			}

			if (password.trim().length() < 6 || password.trim().length() > 32) {
				item.setCode(-109);
				item.setDesc("密码长度不符");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			//身份证非空的时候，验证位数
			if(!StringUtilsEX.IsNullOrWhiteSpace(idcard)){
				if (!idcard.matches("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$")){
					item.setCode(-182);
					item.setDesc("身份证号码格式不正确！");
					return item.toJson();
				}
			}
				
			CriteriaAccounts cAccounts=new CriteriaAccounts();
			cAccounts.setMobile(mobile);
			if(accountsService.isExistAccByPhone(cAccounts) > 0) 
				{
				item.setCode(-107);
				item.setDesc("该手机号(mobile)已被注册"); 
				return item.toJson(); 
				}
			AccountsExample example = new AccountsExample();
			AccountsExample.Criteria criteria = example.createCriteria();
			// criteria.andUsertypeEqualTo(UserTypeEnum.Buyer.getValue());
			criteria.andPhoneEqualTo(mobile);
			criteria.andIsdelEqualTo(false);
			List<Accounts> result = accountsService.queryByMobile(example);
			if (result.size() > 0) {
				item.setCode(-106);
				item.setDesc("该手机号已被注册，你可以找回密码");
				return item.toJson();
			}

			String smss = RedisUserInfo.Get("I" + mobile.trim());

			if (!sms.equals(smss)) {
				item.setCode(-104);
				item.setDesc("验证码有误");
				return item.toJson();
			}
			Userslevel userlevel = userslevelService.getlowerLevel();

			if (userlevel == null) {
				item.setCode(-105);
				item.setDesc("用户等级不存在！");
				return item.toJson();
			}
			String no = acctountNoService.getNo();
			int temp = accountsService
					.addMenber(UserTypeEnum.Buyer.getValue(), imgurl,"1" + no, nickName,
							realName, password, mobile, email,userlevel.getId(),idcard, sex, birthyear,
							birthmonth, birthday,provincecode,  provincename, citycode,
							cityname, areacode, areaname, address);
			if (temp > 0) {
				item.setData(temp);
				item.setCode(0);
				item.setDesc("用户注册成功");
			}else{
				item.setCode(-200);
				if(temp==-200){
					item.setDesc("系统未设置用户等级");
				}else{				
				item.setDesc("账号注册失败");}
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"账号注册异常! 异常信息:{0}", e,
					"userinfo/register");
		}

		return item.toJson();
	}

	/**
	 * 找回密码(通过手机验证码找回密码)
	 * 
	 * @param mobile
	 * @param sms
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPwd", produces = "text/html;charset=UTF-8")
	public @ResponseBody String findPwd(String mobile, String sms,
			String password,String ch) throws Exception {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-101);
				item.setDesc("手机号不能为空！");
				return item.toJson();
			}
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(sms)) {
				item.setCode(-102);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(password)) {
				item.setCode(-103);
				item.setDesc("密码不能为空！");
				return item.toJson();
			}
			/*if (StringUtilsEX.IsNullOrWhiteSpace(passwordagin)) {
				item.setCode(-106);
				item.setDesc("确认密码不能为空！");
				return item.toJson();
			}
			if (!password.equals(passwordagin)) {
				item.setCode(-107);
				item.setDesc("两次密码不一致");
				return item.toJson();
			}
			*/
			
			if (password.trim().length() < 6 || password.trim().length() > 32) {
				item.setCode(-104);
				item.setDesc("密码长度不符");
				return item.toJson();
			}
		
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			String smss = RedisUserInfo.Get("I" + mobile.trim());
			if (!sms.equals(smss)) {
				item.setCode(-105);
				item.setDesc("验证码有误");
				return item.toJson();
			}

			Users users = userService.findaccountInfo(mobile,
					UserTypeEnum.Buyer.getValue());
			
			if (users == null) {
				item.setCode(-201);
				item.setData("该用户不存在");
				return item.toJson();
			}
			/*
			 * Map<String,Object> map=new HashMap<String,Object>();
			 * map.put("mobile", mobile);
			 * map.put("password",DEndecryptUtil.get_instances().passwordEncrypt
			 * (password));
			 */
			accountsService.updPwdByPhone(mobile, password);
			int code = userService.updPwdByPhone(mobile, password);
			if (code > 0) {
				item.setCode(0);
				item.setDesc("密码修改成功");
			} else {
				item.setCode(-200);
				item.setDesc("找回密码失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"找回密码异常! 异常信息:{0}", e,
					"userinfo/findPwd");
		}
		return item.toJson();
	}

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param oldpwd
	 * @param newPwd
	 * @param renewPwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatPwd", produces = "text/html;charset=UTF-8")
	public @ResponseBody String updatPwd(String token, String oldpwd,
			String newPwd,String ch) throws Exception {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(oldpwd)) {
				item.setCode(-101);
				item.setDesc("当前密码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(newPwd)) {
				item.setCode(-102);
				item.setDesc("新密码不能为空！");
				return item.toJson();
			}
			/*if (StringUtilsEX.IsNullOrWhiteSpace(renewPwd)) {
				item.setCode(-103);
				item.setDesc("密码不能为空！");
				return item.toJson();
			}*/
			if (newPwd.trim().length() < 6 || newPwd.trim().length() > 32) {
				item.setCode(-104);
				item.setDesc("密码长度不符");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int buyerId = sessionUser.getUserId();
				String pwd = userService.queryById(buyerId).getPassword();

				if (!pwd.equals(DEndecryptUtil.get_instances().passwordEncrypt(
						oldpwd))) {
					item.setCode(-106);
					item.setDesc("密码填写有误");
					return item.toJson();
				}
				/*if (!newPwd.equals(renewPwd)) {
					item.setCode(-105);
					item.setDesc("两次密码不一致");
					return item.toJson();
				}*/
				if (newPwd.equals(oldpwd)) {
					item.setCode(-105);
					item.setDesc("两次密码不能相同，请重新输入！");
					return item.toJson();
				}

				/*
				 * Map<String,Object> map=new HashMap<String,Object>();
				 * map.put("id", id); map.put("pwd",
				 * DEndecryptUtil.get_instances().passwordEncrypt(newPwd));
				 */
				userService.updPwd(buyerId, newPwd);
				// accountsService.updatePasswordByUserId(StringUtilsEX.ToInt(id),
				// DEndecryptUtil.get_instances().passwordEncrypt(newPwd));

				accountsService.updPwd(buyerId, newPwd);

				item.setCode(0);
				item.setDesc("密码修改成功");

			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"修改密码异常! 异常信息:{0}", e,
					"userinfo/updatPwd");
		}

		return item.toJson();
	}

	/**
	 * 修改用户信息
	 * 
	 * @param id
	 * @param imgUrl
	 * @param name
	 * @param age
	 * @param birthday
	 * @param location
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updUserInfo", produces = "text/html;charset=UTF-8")
	public @ResponseBody String updUserInfo(String token, String imgUrl,
			String name, String mobile, String birthday,String sex,
			String location, String idcard,String address1,String ch) throws Exception {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser= SessionState.GetCurrentUser(token);
			
			if (null == sessionUser||sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			}
			
			int buyerId = sessionUser.getUserId();
			Users user = new Users();
			user = userService.queryById(buyerId);	
			if (!StringUtilsEX.IsNullOrWhiteSpace(imgUrl)) {
				user.setImgurl(imgUrl);
			}
			if	(!StringUtilsEX.IsNullOrWhiteSpace(mobile)){
				if (!mobile.matches("1\\d{10}")) {
					item.setCode(-118);
					item.setDesc("手机号格式错误！");
				}else{
					user.setMobile(mobile);
				}
			}
			user.setRealname(name);
			UserAttr userAttr = new UserAttr();
			List<UserAttr> list = userAttrService.selectById(buyerId);
			if (list != null && list.size() > 0) {
				userAttr = list.get(0);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(sex)) {
				if(!sex.matches("1|0")){
					item.setCode(-192);
					item.setDesc("性别判断错误！");
					return item.toJson();
				}else{
					userAttr.setSex(StringUtilsEX.ToInt(sex));
					
				}
			}
			if(!StringUtilsEX.IsNullOrWhiteSpace(idcard)){
				if (!idcard.matches("^(\\d{6})(19|20)(\\d{2})"
						+ "(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]"
						+ "|3[0-1])(\\d{3})(\\d|X|x)?$")){
					item.setCode(-182);
					item.setDesc("身份证号码格式不正确！");
					return item.toJson();
				}else{
					user.setIdcard(idcard);
				}
			}
			
			/*if (StringUtilsEX.ToInt(age)>=0) {
			
			}*/
			if (!StringUtilsEX.IsNullOrWhiteSpace(birthday)) {
				userAttr.setBirthday(StringUtilsEX.ToDate(birthday.substring(0,
						4)
						+ "-"
						+ birthday.substring(5, 7)
						+ "-"
						+ birthday.substring(8, 10) + " 00:00:00"));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(location)) {
				String[] address = location.split("-");
				userAttr.setProvincename(address[0]);
				Province province = null;
				province = provinceService.selectProvinceByName(address[0]);
				String procode=null;
				if (province != null) {
					procode=province.getCode();
					userAttr.setProvincecode(procode);					
				}
				userAttr.setCityname(address[1]);
				City city = null;
				city = cityService.selectCityByName(address[1],procode);
				String cityCode=null;
				if (city != null) {
					cityCode=city.getCode();
					userAttr.setCitycode(cityCode);
				}
				userAttr.setAreaname(address[2]);
				Area area = null;
				area = areaService.selectAreaByName(address[2],cityCode);
				if (area != null) {
					userAttr.setAreacode(area.getCode());
				}
			}
			/*if (!StringUtilsEX.IsNullOrWhiteSpace(location)) {
				userAttr.setAddress(location);
			}*/
			// userAttr.setAddress(address[3]);

				userAttr.setAddress(address1);
				userService.updatUserInfo(user, userAttr);
				item.setCode(0);
				item.setDesc("信息修改成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("修改用户信息异常：" + e.getMessage());
			}else {
				item.setDesc("修改用户信息异常");
			}			
			LogHandle.error(LogType.Api,"修改用户信息异常! 异常信息:{0}", e,
					"userinfo/updUserInfo");
		}

		return item.toJson();
	}

	/**
	 * 显示我的信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectInfo", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectInfo(String token, String ch)
			throws Exception {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			} else {
				int userId = sessionUser.getUserId();
				UserInfoDto userInfo = new UserInfoDto();
				userInfo.setUserId(String.valueOf(userId));
				Users users = new Users();
				users = userService.queryById(userId);
				if (users == null) {
					item.setCode(-102);
					item.setDesc("该用户不存在");
					
					return item.toJson();

				} else {
					userInfo.setImgUrl(users.getImgurl());
					userInfo.setRealName(users.getRealname());
					userInfo.setPayPassStatus(users.getPaypassstatus());
					userInfo.setMobile(users.getMobile());
					userInfo.setIdcard(users.getIdcard());
					
					List<UserAttr> list = new ArrayList<UserAttr>();
					list = userAttrService.selectById(userId);
					if (list != null & list.size() > 0) {
						UserAttr userattr = new UserAttr();
						userattr = list.get(0);
						Date date = userattr.getBirthday();
//						if (date != null) {
//							Date today = new Date();
//							userInfo.setAge(DateUtil.getYear(today)
//									- DateUtil.getYear(date));
//							userInfo.setBirthday(date);
//						} else {
//							userInfo.setAge(0);
//							// userInfo.setBirthday("");
//						}
						Integer sex =userattr.getSex();
						if(sex !=null){
							userInfo.setSex(sex);
						}
						String provincename = userattr.getProvincename();
						String cityname = userattr.getCityname();
						String areaname = userattr.getAreaname();
						String provincecode = userattr.getProvincecode();
						String address = userattr.getAddress();
						String citycode = userattr.getCitycode();
						String areacode = userattr.getAreacode();
						userInfo.setBirthday(userattr.getBirthday());
						userInfo.setProvincecode(provincecode);
						userInfo.setCitycode(citycode);
						userInfo.setAreacode(areacode);
						userInfo.setProvincename(provincename);
						userInfo.setCityname(cityname);
						userInfo.setAreaname(areaname);
					//String hometown = "";
						String location = "";
						if (provincename != null && cityname != null
								& areaname != null && address != null) {
							/*location = provincename + "-" + cityname + "-"
									+ areaname;*/
							 location = provincename + "-" + cityname + "-" +
							 areaname;

						}
						//userInfo.setHometown(hometown);
						userInfo.setLocation(address);

						item.setCode(0);
						item.setData(userInfo);

					} else {
						item.setCode(-103);
						item.setDesc("该用户尚未添加属性");

					}
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"显示我的信息异常! 异常信息:{0}", e,
					"userinfo/selectInfo");
		}

		return item.toJson();
	}

	/**
	 * 查询用户的当前积分
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/totalPoints", produces = "text/html;charset=UTF-8")
	public @ResponseBody String totalPoints(String token, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int userId = sessionUser.getUserId();
				int currentPoints = 0;

				Users users = userService.queryById(userId);
				if (users != null && users.getPoints() != null) {
					currentPoints = users.getPoints();
				}
				item.setCode(0);
				item.setData(currentPoints);
				item.setDesc("查询积分成功");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(
					LogType.Api,"查询用户的当前积分异常! 异常信息:{0}",
							e, "userinfo/totalPoints");
		}

		return item.toJson();
		/*
		 * Pointrecords pointrecords=new Pointrecords(); try {
		 * pointrecords=pointRecordService.selectByUserId(userId);
		 * if(pointrecords==null){ item.setCode(0); item.setData(currentPoints);
		 * item.setDesc("你当前还没有积分"); return item.toJson(); }else{ int
		 * beforePoints=pointrecords.getBeforepoints(); int
		 * points=pointrecords.getPoints();
		 * if(pointrecords.getTradetype()==PointRecordsTypeEnum.获得积分.getValue())
		 * { currentPoints=beforePoints+points; }else
		 * if(pointrecords.getTradetype()==PointRecordsTypeEnum.消费积分.getValue())
		 * { currentPoints=beforePoints-points; } item.setCode(0);
		 * item.setData(currentPoints); return item.toJson(); } } catch
		 * (Exception e) { item.setCode(-900); item.setDesc("查询用户的当前积分异常：" +
		 * e.getMessage()); LogHandle.error(LogType.Api, MessageFormat.format(
		 * "查询用户的当前积分异常! 异常信息:{0}", e.getMessage()), "userinfo/totalPoints"); }
		 */

	}

	/**
	 * 兑换积分
	 * 
	 * @param userId
	 * @param points
	 * @param fromType
	 * @param beforePoints
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/exchangPoints", produces = "text/html;charset=UTF-8")
	public @ResponseBody String exchangPoints(String token, String points,
			String fromType, String beforePoints, String description, String ch) {
		BaseResult item = new BaseResult();
		try {

			if (StringUtilsEX.IsNullOrWhiteSpace(points)
					|| StringUtilsEX.ToInt(points) < 0) {
				item.setCode(-102);
				item.setDesc("积分值参数错误！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(beforePoints)
					|| StringUtilsEX.ToInt(beforePoints) < 0) {
				item.setCode(-103);
				item.setDesc("当前积分值参数错误！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(fromType)) {
				item.setCode(-104);
				item.setDesc("来源不能为空！");
				return item.toJson();
			}

			if (StringUtilsEX.ToInt(fromType) < 0
					|| StringUtilsEX.ToInt(fromType) > 5) {
				item.setCode(-100);
				item.setData("fromType参数错误");
			}
			if (StringUtilsEX.ToInt(points) > StringUtilsEX.ToInt(beforePoints)) {
				item.setCode(-111);
				item.setDesc("兑换积分超于实际积分数");
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int userId = sessionUser.getUserId();
				Pointrecords pointrecords = new Pointrecords();
				pointrecords.setUserid(userId);
				pointrecords.setPoints(StringUtilsEX.ToInt(points));
				pointrecords.setTradetype(PointRecordsTypeEnum.消费积分.getValue());
				pointrecords.setTradetime(new Date());
				pointrecords.setDescription(description);

				Pointsrecords pointsrecords = new Pointsrecords();
				pointsrecords.setPoints(StringUtilsEX.ToInt(points));
				pointsrecords.setType(PointsRecordsTypeEnum.消费.getValue());
				pointsrecords.setFromtype(StringUtilsEX.ToInt(fromType));
				pointsrecords.setCreatetime(new Date());
				pointsrecords.setUserid(userId);

				Users users = userService.queryById(userId);
				if (users != null) {
					users.setTotalpoints(users.getTotalpoints()
							- StringUtilsEX.ToInt(points));
					userService.updateInfo(users);
				}
				List<UserAttr> list = userAttrService.selectById(userId);
				if (list != null && list.size() > 0) {
					UserAttr userAttr = list.get(0);
					userAttr.setTotalpoints(userAttr.getTotalpoints()
							- StringUtilsEX.ToInt(points));
					userAttrService.updateById(userAttr);
				}

				int record = pointRecordService.insertPoint(pointrecords,
						pointsrecords);
				if (record == 0) {
					item.setCode(-200);
					item.setDesc("兑换积分失败");
					return item.toJson();
				}
				item.setCode(0);
				item.setDesc("积分兑换成功");
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"兑换积分异常! 异常信息:{0}", e,
					"userinfo/exchangPoints");
		}

		return item.toJson();
	}

	/*
	 * @RequestMapping(value = "/updBirth",method=RequestMethod.GET,produces =
	 * "text/html;charset=UTF-8") public @ResponseBody String updBirth(String
	 * userId,String birthday) throws Exception{ BaseResult item = new
	 * BaseResult(); List<UserAttr> list=new ArrayList<UserAttr>();
	 * list=userAttrService.selectById(StringUtilsEX.ToInt(userId)); UserAttr
	 * userattr=new UserAttr(); userattr=list.get(0);
	 * userattr.setBirthday(StringUtilsEX.ToDate(birthday)); try {
	 * userAttrService.updateById(userattr); item.setCode(0);
	 * item.setDesc("信息修改成功"); } catch (Exception e) { item.setCode(-900);
	 * userAttrService.updateById(userattr); item.setCode(0);
	 * item.setDesc("信息修改成功"); } return item.toJson(); }
	 */

	/**
	 * 心跳api
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/delayheartbeat", produces = "text/html;charset=UTF-8")
	public @ResponseBody String DelayHeartbeat(String token,HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(SessionState.DelayTokenTimeOut());
			} else {
				item.setCode(SessionState.DelayTokenTimeOut(token));
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG)
				item.setDesc(e.getMessage());
			else {
				item.setDesc("系统错误!");
			}
			LogHandle.error(LogType.Api,"心跳错误：{0}", e,
					"userinfo/DelayHeartbeat");
		}
		return item.toJson();
	}

	/**
	 * 会员添加银行卡
	 * 
	 * @param token
	 * @param openbank
	 * @param subbank
	 * @param name
	 * @param number
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/addCard", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addCard(String token, String openbank,
			String subbank, String name, String number, String phone, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(openbank)) {
				item.setCode(-102);
				item.setDesc("开户行不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(subbank)) {
				item.setCode(-103);
				item.setDesc("支行不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-104);
				item.setDesc("持卡人姓名不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(number)) {
				item.setCode(-105);
				item.setDesc("卡号不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(phone)) {
				item.setCode(-106);
				item.setDesc("手机号不能为空！");
				return item.toJson();
			}
			if (!phone.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-112);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int buyerId = sessionUser.getUserId();
				BankCard bankCard = new BankCard();
				bankCard.setUserid(buyerId);
				bankCard.setOpenbank(openbank);
				bankCard.setSubbank(subbank);
				bankCard.setName(name);
				bankCard.setNumber(number);
				bankCard.setPhone(phone);

				BankCard bc = new BankCard();
				bc = bankCardService.query(buyerId, number);
				if (bc != null) {
					item.setCode(-108);
					item.setDesc("该银行卡已添加");
				} else {
					int temp = bankCardService.insert(bankCard);
					if (temp == 0) {
						item.setCode(-109);
						item.setDesc("添加银行卡失败");
					} else {
						item.setCode(0);
						item.setDesc("添加银行卡成功");
					}
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"添加银行卡异常! 异常信息:{0}", e,
					"userinfo/addCard");
		}

		return item.toJson();
	}

	/**
	 * 会员银行卡列表
	 * 
	 * @param token
	 * @param openbank
	 * @param subbank
	 * @param name
	 * @param number
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/cardlist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String cardlist(String token, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-112);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int userId = sessionUser.getUserId();
				List<BankCard> list = new ArrayList<BankCard>();
				list = bankCardService.queryBrandCardByUserId(userId);
				item.setCode(0);
				item.setData(list);
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle
					.error(LogType.Api,"查询银行卡列表异常! 异常信息:{0}", "userinfo/cardlist");
		}

		return item.toJson();
	}
	
	
	/**
	 * 
	 * 解绑银行卡
	 * @param ch
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deletecard", produces = "text/html;charset=UTF-8")
	public  @ResponseBody String deletecard(String token,String id,String ch){
		BaseResult item = new BaseResult();
		try{
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-112);
				item.setDesc("通道错误");
				return item.toJson();
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(id)){
				item.setCode(-104);
				item.setDesc("id不能为空");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				SessionUser user=SessionState.GetCurrentUser();
				int cardid =Integer.parseInt(id);
				int i=bankCardService.deleteById(cardid);
				if(i>0){
					item.setCode(0);
					item.setDesc("解绑成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "BankCard.html", "/api/wap/userinfo/addBankcard", "操作说明（例：解绑银行卡）");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"解绑银行卡 异常信息:",
										e, "/app/api/userinfo/deletecard");
							}
						}
					});
				}
					
			}
		}
		catch(Exception e){
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"解绑银行卡信息! 异常信息:{0}", e,
					"userinfo/deletecard");
		}
		return item.toJson();
	}

	/**
	 * 添加支付密码
	 * 
	 * @param token
	 * @param pwd
	 * @param repwd
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/setpayPwd", produces = "text/html;charset=UTF-8")
	public @ResponseBody String setpayPwd(String token, String pwd,
			String repwd, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(pwd)) {
				item.setCode(-101);
				item.setDesc("支付密码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(repwd)) {
				item.setCode(-102);
				item.setDesc("确认密码不能为空！");
				return item.toJson();
			}
			if (pwd.trim().length() < 6 || pwd.trim().length() > 32) {
				item.setCode(-104);
				item.setDesc("密码长度不符");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (!pwd.equals(repwd)) {
				item.setCode(-105);
				item.setDesc("两次密码不一致");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				Integer id = sessionUser.getUserId();
				/*
				 * Users users=userService.queryById(id); users.setPaypass(pwd);
				 */
				int temp = userService.updPayPwd(id, repwd);
				if (temp > 0) {
					item.setCode(0);
					item.setDesc("设置支付密码成功");
				} else {
					item.setCode(-200);
					item.setDesc("设置支付密码失败");
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"添加支付密码异常! 异常信息:{0}", e,
					"userinfo/setpayPwd");
		}

		return item.toJson();
	}
	
	/**
	 * 找回支付密码(通过手机验证码找回密码)
	 * @param mobile
	 * @param sms
	 * @param pwd
	 * @param repwd
	 * @param ch
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPayPwd", produces = "text/html;charset=UTF-8")
	public @ResponseBody String findPayPwd(String mobile, String sms,
			String pwd, String repwd, String ch) throws Exception {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-101);
				item.setDesc("手机号不能为空！");
				return item.toJson();
			}
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(sms)) {
				item.setCode(-102);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(pwd)) {
				item.setCode(-103);
				item.setDesc("密码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(repwd)) {
				item.setCode(-106);
				item.setDesc("确认密码不能为空！");
				return item.toJson();
			}
			if (!pwd.equals(repwd)) {
				item.setCode(-107);
				item.setDesc("两次密码不一致");
				return item.toJson();
			}
			if (pwd.trim().length() < 6 || pwd.trim().length() > 32) {
				item.setCode(-104);
				item.setDesc("密码长度不符");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			String smss = RedisUserInfo.Get("I" + mobile.trim());
			if (!sms.equals(smss)) {
				item.setCode(-105);
				item.setDesc("验证码有误");
				return item.toJson();
			}

			Users users = userService.findaccountInfo(mobile,
					UserTypeEnum.Buyer.getValue());
			if (users == null) {
				item.setCode(-201);
				item.setData("该用户不存在");
				return item.toJson();
			}
			/*
			 * Map<String,Object> map=new HashMap<String,Object>();
			 * map.put("mobile", mobile);
			 * map.put("password",DEndecryptUtil.get_instances().passwordEncrypt
			 * (password));
			 */
			int code = userService.updPayPwdByPhone(mobile, pwd);
			if (code > 0) {
				item.setCode(0);
				item.setDesc("支付密码成功找回");
			} else {
				item.setCode(-200);
				item.setDesc("找回支付密码失败");
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"找回支付密码异常! 异常信息:{0}", e,
					"userinfo/findPayPwd");
		}
		return item.toJson();
	}

	/**
	 * 修改支付密码
	 * 
	 * @param token
	 * @param pwd
	 * @param repwd
	 * @param ch
	 * @return
	 */
	/*@RequestMapping(value = "/updpayPwd", produces = "text/html;charset=UTF-8")
	public @ResponseBody String updpayPwd(String token, String oldpwd,
			String pwd, String repwd, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(oldpwd)) {
				item.setCode(-103);
				item.setDesc("旧的支付密码(oldpwd)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(pwd)) {
				item.setCode(-101);
				item.setDesc("支付密码(pwd)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(repwd)) {
				item.setCode(-102);
				item.setDesc("确认密码(repwd)不能为空！");
				return item.toJson();
			}

			if (pwd.trim().length() < 6 || pwd.trim().length() > 32) {
				item.setCode(-104);
				item.setDesc("密码(pwd)长度不符");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (!pwd.equals(repwd)) {
				item.setCode(-105);
				item.setDesc("两次密码不一致");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				Integer id = sessionUser.getUserId();
				Users users = userService.queryById(id);
				String _pwd = users.getPaypass();
				if (!_pwd.equals(DEndecryptUtil.get_instances()
						.passwordEncrypt(oldpwd))) {
					item.setCode(-106);
					item.setDesc("支付密码(oldpwd)填写有误");
					return item.toJson();
				}
				// users.setPaypass(pwd);
				int temp = userService.updPayPwd(id, pwd);
				if (temp > 0) {
					item.setCode(0);
					item.setDesc("修改支付密码成功");
				} else {
					item.setCode(-200);
					item.setDesc("修改支付密码失败");
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"修改支付密码异常! 异常信息:{0}", e,
					"userinfo/updpayPwd");
		}
		return item.toJson();
	}
*/
	/**
	 * 实名认证接口
	 * 
	 * @param token
	 * @param relname
	 *            真实姓名
	 * @param cardno
	 *            身份证号
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/certification", produces = "text/html;charset=UTF-8")
	public String certification(String token, String relname, String cardno,
			String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			}
			// TODO： 原支撑系统 要修改
			/*
			 * Map<String, Object> rsl =
			 * WebserviceClient.certification(sessionUser.getLoginName(),
			 * relname, cardno); if (!rsl.get("ResultCode").equals("1")) {
			 * item.setCode(-100); item.setDesc("支撑系统出错：" +
			 * rsl.get("ResultMsg").toString()); return item.toJson(); }
			 */
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"实名认证异常! 异常信息:{0}", e,
					"userinfo/updpayPwd");
		}
		return item.toJson();
	}

	/**
	 * 用户资产查询
	 * 
	 * @param token
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/queryassets", produces = "text/html;charset=UTF-8")
	public String queryassets(String token, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			}
			Usercapital usercapital = usercapitalService
					.queryByUserId(sessionUser.getUserId());
			if (usercapital != null) {
				BigDecimal db = new BigDecimal(usercapital.getBalance().doubleValue());
				DecimalFormat myformat=new java.text.DecimalFormat("0.00");
				String str = myformat.format(db);  
				item.setData(str);
			} else {
				item.setData(0.00);
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"查询用户资产异常! 异常信息:{0}", e,
					"userinfo/queryassets");
		}
		return item.toJson();
	}

	/**
	 * 验证会员支付密码
	 * 
	 * @param ch
	 * @param pass
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/checkpaypass", produces = "text/html;charset=UTF-8")
	public String checkpaypass(String ch, String pass, String token) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(pass)) {
				item.setCode(-101);
				item.setDesc("支付密码不能为空！");
				return item.toJson();
			}
			int code = userService.getByPayPwd(sessionUser.getUserId(),
					pass.trim());
			switch (code) {
			case 1:
				item.setDesc("验证支付密码成功");
				break;
			case -1:
				item.setCode(-201);
				item.setDesc("用户不存在");
				break;
			case -2:
				item.setCode(-202);
				item.setDesc("未设置支付密码");
				break;
			case -3:
				item.setCode(-203);
				item.setDesc("支付密码错误");
				break;
			default:
				break;
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "验证用户支付密码异常!", e,
					"userinfo/checkpaypass");
		}
		return item.toJson();
	}
	
	/**
	 * 最新消息
	 * @param ch
	 * @param size 大小
	 * @return
	 */
	@RequestMapping(value = "/newestmsg", produces = "text/html;charset=UTF-8")
	public String newestmsg(String ch,String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(size)<=0) {
				size="1";
			}
			AppNewsCriteria criteria=new AppNewsCriteria();
			criteria.setType(1);
			criteria.setCtype(-1);
			criteria.setWebset(WebSetEnum.app.getValue()+"");
			PageBean listBean=newService.getApplist(criteria, 1, StringUtilsEX.ToInt(size));
  			item.setData(listBean.getBeanList());
  			item.setPage(listBean.getTp());
  			item.setMaxRow(listBean.getTr());
  			item.setPageIndex(1);
  			item.setPageSize(StringUtilsEX.ToInt(size));
  			item.setPage(listBean.getTp());
  			return item.toJson();
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取最新消息异常!", e,
					"userinfo/newestmsg");
		}
		return item.toJson();
	}
	/**
	 * 获取网站联系电话
	 * @return
	 */
	@RequestMapping(value = "/getnetcontact", produces = "text/html;charset=UTF-8")
	public String getnetcontact() {
		ReusltItem item = new ReusltItem();
		try {
			WebsitesConfig wConfig=websitesConfigService.selectConfig();
			if(wConfig!=null&&wConfig.getId()>0){
				item.setCode(0);
				item.setData(wConfig.getCompanyTel());
			}else {
				item.setCode(-200);
				item.setDesc("未获取网站联系电话");
			}
  			return item.toJson();
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取网站联系电话：" + e.getMessage());
			}else {
				item.setDesc("获取网站联系电话");
			}
			LogHandle.error(LogType.Api, "获取网站联系电话!", e,
					"userinfo/getnetcontact");
		}
		return item.toJson();
	}
	
	
	/**
	 * 积分列表
	 * @param page 当前页
	 * @param size 页大小
	 * @param ch 通道
	 * @param token 用户登录唯一标识
	 * @return
	 */
	@RequestMapping(value = "/pointslist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String  pointslist(String page,String size,String ch, String token){
		ReusltItem item = new ReusltItem();
	    try{
			    	if (!StringUtilsEX.isChannelTypeExist(ch)) {
						item.setCode(-108);
						item.setDesc("登录通道参数错误");
						return item.toJson();
					}
		    		SessionUser sessionUser = new SessionUser();
					sessionUser = SessionState.GetCurrentUser(token);
					if (sessionUser.getCode() != 0) {
						item.setCode(-401);
						item.setDesc("请先登陆！");
						return item.toJson();
					}
					Integer page1 = StringUtilsEX.ToIntNull(page);
					Integer size11 = StringUtilsEX.ToIntNull(size);
					if (page1 == null || page1 <= 0) {
						page1 = 1;
					}
					if (size11 == null || size11 <= 0) {
						size11 = 10;
					}
					Pc_PointsCriteria criteria=new Pc_PointsCriteria();
					criteria.setUserid(sessionUser.getUserId());
					criteria.setOrderByClause("CreateTime");
					criteria.setSort("desc");
	                PageBean list = pointsRecordService.pointListByPage(criteria,page1,size11);
	    			item.setData(list.getBeanList());
	    			item.setMaxRow(list.getTr());
	    			item.setPageIndex(list.getPc());
	    			item.setPageSize(list.getPs());
	    			item.setPage(list.getTp());
	    			item.setDesc("查询成功");
	    }catch(Exception e){
	    		item.setCode(-900);
				if(DebugConfig.BLUETOOTH_DEBUG){
					item.setDesc("查询积分异常：" + e.getMessage());
				}else {
					item.setDesc("查询积分异常");
				}
				LogHandle.error(LogType.Api, "查询订单异常! 异常信息:{0}", e,
						"userinfo/getpoints");
	   }
	    return item.toJson();
	}
	/**
	 * 资金记录查询
	 * @param token
	 * @param ch
	 * @param page
	 * @param size
	 * @param financetype 资金类型  金额(0), 积分(2);
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getbalancerecord", produces = "text/html;charset=UTF-8")
	public String getbalancerecord(String token,String ch, String page, String size,String financetype,
		 HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		ReusltItem item = new ReusltItem();
		try {

			SessionUser sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
			}
			int index = StringUtilsEX.ToInt(page);
			int psize = StringUtilsEX.ToInt(size);
			if (index <= 0) {
				index = 1;
			}
			if (psize <= 0) {
				psize = 10;
			}
			CriteriaFinance criteria = new CriteriaFinance();
			criteria.setOrderByClause("CreateTime");
			criteria.setSort("desc");
			criteria.setUserid(sessionUser.getUserId());
			if(StringUtilsEX.ToInt(financetype)>=0){
				criteria.setFinancetype(StringUtilsEX.ToInt(financetype));
			}
			PageBean balance = userfinanceService.selectPage(criteria, index,
					psize);
			item.setCode(0);
			item.setMaxRow(balance.getTr());
			item.setPageIndex(balance.getPc());
			item.setPageSize(balance.getPs());
			item.setData(balance.getBeanList());
			item.setDesc("余额查询成功");

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("资金记录查询异常" + e.toString());
			} else {
				item.setDesc("资金记录查询异常");
			}			
			LogHandle.debug(LogType.pc,
					MessageFormat.format(" 积分查询异常! 异常信息:{0}", e.toString()),
					"coupon/getbalancerecord");

		}
		return item.toJson();
	}
	
	/**
	 * 测试注册赠送优惠券
	 * @return
	 *//*
	@RequestMapping(value = "/test", produces = "text/html;charset=UTF-8")
	public String test(){
		try {
			for (Integer i=1;i<21;i++) {
			int no=200+i;
			String username=String.format("%07d", no);
			String mobile="178696"+String.format("%05d", i);
			accountsService.addMenber(UserTypeEnum.Buyer.getValue(), "", "5" + username, "", "",
			        "123456", mobile,"", 0,"","", "", "", "", "", "", "", "", "", "", "");
		   }
		} catch (Exception e) {
			// TODO: handle exception
		}
        return "";  
	}*/
}

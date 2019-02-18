package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
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
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.BankCard;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Pointrecords;
import com.yinlian.wssc.web.po.Pointsrecords;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AcctountNoService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.BankCardService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.PointRecordService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.SmsService;
import com.yinlian.wssc.web.service.UserAttrService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.JsonUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/wap/userinfo")
public class WapUserInfoController {
	@Autowired
	private UserService userService;

	@Autowired
	private SmsService smsservice;
	
	@Autowired
	private AccountsService accountsService;

	@Autowired
	private UserAttrService userAttrService;

	@Autowired
	private ProvinceServcice provinceService;

	@Autowired
	private BankCardService bankcardService;

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
	private OrderService orderService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private CouponService couponService;
	    
	@Autowired
	private OperaterecordsService operaterecordsService;  
	   
	/**
	 * 会员登录
	 * 
	 * @param name
	 * @param pwd
	 * @param ch
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	public String Login(String name, String pwd, String ch, String code,
			HttpServletResponse response) {
		BaseResult item = new BaseResult();
		try {
			item.setCode(0);
			String token = UUID.randomUUID().toString();
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-102);
				item.setDesc("登录名不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(pwd)) {
				item.setCode(-103);
				item.setDesc("密码不能为空！");
				return item.toJson();
			}

			String userInfo = "";
			Object[] rls = userService.login(name, pwd,
					UserTypeEnum.Buyer.getValue());
			switch (Integer.parseInt(rls[0].toString())) {
			case 0:
				userInfo = JsonUtil.getJsonStringFromObject(rls[1]);
				break;
			case -1:
				item.setCode(-104);
				item.setDesc("账号名或密码不正确！");
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
				item.setDesc("登录失败,用户名或密码错误！");
				return item.toJson();
			}
			SessionUser sessionUser = (SessionUser) rls[1];
			SessionState.SetSessionUser(token, sessionUser);
			System.out.println(SessionState.GetCurrentUser(token));
			Users users = userService.queryById(sessionUser.getUserId());

			/*
			 * Cookie cookie1=new Cookie("_u_token",token);
			 * cookie1.setPath("/"); Cookie cookie2=new Cookie("_u_channel",ch);
			 * Cookie cookie3=new
			 * Cookie("_uid",sessionUser.getUserId().toString()); Cookie
			 * cookie4=new Cookie("_u_name",sessionUser.getLoginName()); Cookie
			 * cookie5=new Cookie("_u_mobile",users.getMobile()); Cookie
			 * cookie6=new Cookie("_u_email",users.getEmail());
			 * 
			 * response.addCookie(cookie1); response.addCookie(cookie2);
			 * response.addCookie(cookie3); response.addCookie(cookie4);
			 * response.addCookie(cookie5); response.addCookie(cookie6);
			 */

			// item.setToken(token);
			item.setCode(0);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Token", token);
			map.put("Channel", ch);
			map.put("sessionUser", sessionUser);
			map.put("UserID", sessionUser.getUserId());
			map.put("UserName", sessionUser.getLoginName());
			map.put("Mobile", users.getMobile());
			map.put("Email", users.getEmail());
			item.setData(JsonUtil.getJsonStringFromMap(map));
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("登录异常！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("会员登录异常! 异常信息:{0}", e.getMessage()),
					"userinfo/login");
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
	public String register(String imgurl,String nickName,String realName,String email,
			String mobile, String sms, String password, String ch,String idcard,String sex, String birthyear,
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
			if (!mobile.matches("^[1][3,5,7,8][0-9]{9}$")) {//1\\d{10}
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			/*
			 * if (!username.matches("^[a-zA-Z][a-zA-Z0-9_]{2,16}$")) {
			 * item.setCode(-118); item.setDesc("用户名格式错误 26个大小写字母，数字或‘_’不能以数字开头"
			 * ); return item.toJson(); }
			 */
			/*
			 * if (!StringUtilsEX.checkUserName(username)) { item.setCode(-111);
			 * item.setDesc("用户名不正确，请输入3~16个字符，26个大小写字、数字或“_-”（不能以数字开头）");
			 * return item.toJson(); }
			 */
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
				item.setDesc("密码(password)长度不符");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}

			/*
			 * List<Users> user1 = userService.findInfobyName(username); if
			 * (user1.size() > 0) { item.setCode(-107);
			 * item.setDesc("该用户名(username)已被注册"); return item.toJson(); }
			 */
			AccountsExample example = new AccountsExample();
			AccountsExample.Criteria criteria = example.createCriteria();
			// criteria.andUsertypeEqualTo(UserTypeEnum.Buyer.getValue());
			criteria.andPhoneEqualTo(mobile);
			criteria.andIsdelEqualTo(false);
			List<Accounts> result = accountsService.queryByMobile(example);
			if (result.size() > 0) {
				item.setCode(-106);
				item.setDesc("该手机号(mobile)已被注册，你可以找回密码");
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
					.addMenber(UserTypeEnum.Buyer.getValue(), imgurl,"zl_" + no, nickName,
							realName, password, mobile, email, userlevel.getId(),idcard,sex,birthyear,
							birthmonth, birthday,
							provincecode, provincename, citycode,
							cityname,areacode,areaname, address);
			if (temp > 0) {
				item.setData(temp);
				item.setCode(0);
				item.setDesc("用户注册成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "register.html", "/api/wap/userinfo/register", "操作说明（例：用户注册）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"用户注册 异常信息:",
									e, "/api/wap/userinfo/register");
						}
					}
				});
			}else {
				item.setCode(-200);
				if(temp==-200){
					item.setDesc("系统未设置用户等级");
				}else{				
				item.setDesc("用户注册失败");}
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("注册失败！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("账号注册异常! 异常信息:{0}", e.getMessage()),
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
	public String findPwd(String mobile, String sms, String password, String ch)
			throws Exception {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-101);
				item.setDesc("手机号(mobile)不能为空！");
				return item.toJson();
			}
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号(mobile)格式错误！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(sms)) {
				item.setCode(-102);
				item.setDesc("验证码(sms)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(password)) {
				item.setCode(-103);
				item.setDesc("密码(password)不能为空！");
				return item.toJson();
			}
			if (password.trim().length() < 6 || password.trim().length() > 32) {
				item.setCode(-104);
				item.setDesc("密码(password)长度不符");
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
				item.setDesc("密码成功找回");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "findPwd.html", "/api/wap/userinfo/findPwd", "操作说明（例：密码找回）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"密码找回 异常信息:",
									e, "/api/wap/userinfo/findPwd");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("找回密码失败");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("找回密码异常！");
			}			
			LogHandle.error(LogType.wap,
					MessageFormat.format("找回密码异常! 异常信息:{0}", e.getMessage()),
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
	public String updatPwd(String token, String oldpwd, String newPwd,
			String renewPwd, String ch) throws Exception {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(oldpwd)) {
				item.setCode(-101);
				item.setDesc("当前密码(oldpwd)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(newPwd)) {
				item.setCode(-102);
				item.setDesc("新密码(newPwd)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(renewPwd)) {
				item.setCode(-103);
				item.setDesc("密码(renewPwd)不能为空！");
				return item.toJson();
			}
			if (newPwd.trim().length() < 6 || newPwd.trim().length() > 32) {
				item.setCode(-104);
				item.setDesc("密码(password)长度不符");
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
					item.setDesc("密码(oldpwd)填写有误");
					return item.toJson();
				}
				if (!newPwd.equals(renewPwd)) {
					item.setCode(-105);
					item.setDesc("两次密码不一致");
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

				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "updpwd.html", "/api/wap/userinfo/updatPwd", "密码修改");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"密码修改 异常信息:",
									e, "/api/wap/userinfo/updatPwd");
						}
					}
				});
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("修改密码异常！");
			}			
			LogHandle.error(LogType.wap,
					MessageFormat.format("修改密码异常! 异常信息:{0}", e.getMessage()),
					"userinfo/updatPwd");
		}

		return item.toJson();
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
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "", "/api/wap/userinfo/exchangPoints", "积分兑换");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"积分兑换 异常信息:",
									e, "/api/wap/userinfo/exchangPoints");
						}
					}
				});
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("兑换积分异常！");
			}
			
			LogHandle.error(LogType.wap,
					MessageFormat.format("兑换积分异常! 异常信息:{0}", e.getMessage()),
					"userinfo/exchangPoints");
		}

		return item.toJson();
	}
/**
 * 退出登录
 * @param ch
 * @param request
 * @return
 */
	@RequestMapping(value = "/exit", produces = "text/html;charset=UTF-8")
	public  @ResponseBody String exit(String ch,HttpServletRequest request){
		BaseResult item = new BaseResult();
		try{
			String token = CookieUtils.getTokenFromCookie(request);
			RedisUserInfo.DetToken(token);
			item.setCode(0);
			item.setDesc("退出登录成功");
			
			
		}catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("退出登录异常！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("退出登录异常! 异常信息:{0}", e.getMessage()),
					"userinfo/exchangPoints");
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
	 * @param hometown
	 * @param location
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updUserInfo", produces = "text/html;charset=UTF-8")
	public @ResponseBody String updUserInfo(String imgUrl, String name,
			String age, String birthday, String hometown, String location,
			String ch, HttpServletRequest request) throws Exception {
		ch = "3";
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int buyerId = sessionUser.getUserId();
				// int buyerId =492;
				Users user = new Users();
				user = userService.queryById(buyerId);
				// user.setId(StringUtilsEX.ToInt(id));
				if (!StringUtilsEX.IsNullOrWhiteSpace(imgUrl)) {
					user.setImgurl(imgUrl);
				}
				if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
					user.setRealname(name);
				}

				UserAttr userAttr = new UserAttr();
				List<UserAttr> list = userAttrService.selectById(buyerId);
				if (list != null && list.size() > 0) {
					userAttr = list.get(0);
				}
				// userAttr.setId(StringUtilsEX.ToInt(id));
				if (!StringUtilsEX.IsNullOrWhiteSpace(birthday)) {
					userAttr.setBirthday(StringUtilsEX.ToDate(birthday
							.substring(0, 4)
							+ "-"
							+ birthday.substring(5, 7)
							+ "-" + birthday.substring(8, 10) + " 00:00:00"));
				}
				
				if (!StringUtilsEX.IsNullOrWhiteSpace(hometown)) {
					String[] address = hometown.split("-");
					userAttr.setProvincename(address[0]);
					Province province = null;
					province = provinceService.selectProvinceByName(address[0]);
					if (province != null) {
						userAttr.setProvincecode(province.getCode());
					}

					userAttr.setCityname(address[1]);
					City city = null;
					city = cityService.selectByName(address[1]);
					if (city != null) {
						userAttr.setCitycode(city.getCode());
					}
                   
                    	userAttr.setAreaname(address[2]);
    					Area area = null;
    					area = areaService.selectByName(address[2]);
    					if (area != null) {
    						userAttr.setAreacode(area.getCode());
    					 }
          
					
				}

				if (!StringUtilsEX.IsNullOrWhiteSpace(location)) {
					userAttr.setAddress(location);
				}

				userService.updatUserInfo(user, userAttr);
				item.setCode(0);
				item.setDesc("信息修改成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "Userinfo.html", "/api/wap/userinfo/updUserInfo", "修改用户信息");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"修改用户信息 异常信息:",
									e, "/api/wap/userinfo/updUserInfo");
						}
					}
				});
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("修改用户信息异常！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("修改用户信息异常! 异常信息:{0}", e.getMessage()),
					"userinfo/updUserInfo");
		}

		return item.toJson();
	}
	
	
	/**
	 * 添加银行卡信息
	 * @param ch                通道
	 * @param openBank          开户行
	 * @param subBank           支行
	 * @param name              持卡人姓名
	 * @param number            卡号
	 * @param phone             手机号
	 * @param userId            会员ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addBankcard", produces = "text/html;charset=UTF-8")
	public  @ResponseBody String addBankcard(String ch,String openBank,String subBank,String name,String number,String phone,HttpServletRequest request){
		BaseResult item = new BaseResult();
		try{
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("通道错误");
				return item.toJson();
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(openBank)){
				item.setCode(-103);
				item.setDesc("开户行不能为空");
				return item.toJson();
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(subBank)){
				item.setCode(-104);
				item.setDesc("支行不能为空");
				return item.toJson();
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(name)){
				item.setCode(-105);
				item.setDesc("持卡人姓名不能为空");
				return item.toJson();
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(number)){
				item.setCode(-106);
				item.setDesc("卡号不能为空");
				return item.toJson();
			}
			String carnumber =number.replaceAll(" ", "");
		   if (!carnumber.matches("(\\d{16}|\\d{19})")) {
				item.setCode(-119);
				item.setDesc("银行卡号格式错误！");
				return item.toJson();
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(phone)){
				item.setCode(-107);
				item.setDesc("电话号码不能为空");
				return item.toJson();
			}
			if (!phone.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			} else {
				BankCard bankCard = new BankCard();
				bankCard.setName(name);
				bankCard.setNumber(number);
				bankCard.setOpenbank(openBank);
				bankCard.setPhone(phone);
				bankCard.setSubbank(subBank);
				bankCard.setUserid(sessionUser.getUserId());
				bankcardService.insert(bankCard);
				item.setCode(0);
				item.setDesc("添加银行卡信息成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "BankCard.html", "/api/wap/userinfo/addBankcard", "添加银行卡信息");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"添加银行卡信息 异常信息:",
									e, "/api/wap/userinfo/addBankcard");
						}
					}
				});
				return item.toJson();
			}
			
		}catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("查询银行卡信息! 异常信息:{0}", e.getMessage()),
					"userinfo/updUserInfo");
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
	public  @ResponseBody String deletecard(String ch,String id,HttpServletRequest request){
		BaseResult item = new BaseResult();
		try{
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("通道错误");
				return item.toJson();
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(id)){
				item.setCode(-104);
				item.setDesc("id不能为空");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			} else {
				int cardid =Integer.parseInt(id);
				int daleteis=bankcardService.deleteById(cardid);
					item.setCode(0);
					item.setDesc("解绑成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user=SessionState.GetCurrentUser();
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "BankCard.html", "/api/wap/userinfo/addBankcard", "操作说明（例：解绑银行卡）");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"解绑银行卡 异常信息:",
										e, "/api/wap/userinfo/deletecard");
							}
						}
					});
					return item.toJson();
				
			}
			}
		catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("解绑银行卡信息! 异常信息:{0}", e.getMessage()),
					"userinfo/deletecard");
		}
		return item.toJson();
	}
}

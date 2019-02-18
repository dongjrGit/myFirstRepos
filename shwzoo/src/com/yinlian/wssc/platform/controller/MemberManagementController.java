package com.yinlian.wssc.platform.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.CopeCardTypEnum;
import com.yinlian.Enums.CopySexEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.MemberVo;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.UsersInfoDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.BankCard;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.BankCardService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.MessageRecordService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.UserAttrService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.CommonUtils;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.CriteriaUser;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.ZooSmsUtil;
import com.yl.soft.log.LogHandle;

/**
 * 会员管理的控制类
 * 
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/platform/membermanagement")
public class MemberManagementController {

	@Autowired
	private AccountsService accountsService;
	@Autowired
	private ProvinceServcice provinceServcice;
	@Autowired
	private CityServcie cityService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserAttrService userAttrService;
	@Autowired
	private UsercapitalService usercapitalService;
	@Autowired
	private MessageRecordService messageRecordService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private BankCardService bankCardService;
	@Autowired
	private UserslevelService userslevelService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;

	/**
	 * 绑定性别Sex
	 * 
	 * @return
	 */
	@RequestMapping("/querySexAll")
	public ReusltItem querySexAll() {
		ReusltItem item = new ReusltItem();
		try {
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
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("绑定会员性别信息出错：" + e.getMessage());
				
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "绑定会员性别信息出错! 异常信息:", e,
					"/platform/membermanagement/querySexAll");
		}

		return item;
	}

	/**
	 * 绑定证件类型
	 * 
	 * @return
	 */
	@RequestMapping("/queryCardType")
	public ReusltItem queryCardType() {
		ReusltItem item = new ReusltItem();
		try {
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
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("绑定会员证件类型出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "绑定会员证件类型信息出错! 异常信息:",
					e, "/platform/membermanagement/queryCardType");
		}

		return item;
	}

	/**
	 * 绑定收入水平
	 * 
	 * @return
	 */
	@RequestMapping("/queryInComeMonth")
	public ReusltItem queryInComeMonth() {
		ReusltItem item = new ReusltItem();
		List<MemberVo> list = new ArrayList<MemberVo>();
		try {
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
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("绑定会员收入水平出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "绑定会员收入水平信息出错! 异常信息:",
					e, "/platform/membermanagement/queryInComeMonth");
		}

		return item;
	}

	/**
	 * 根据code查询所有的省Provice
	 * 
	 * @return
	 */
	@RequestMapping("/queryRegion")
	public ReusltItem queryRegion(String type, String code) {
		ReusltItem item = new ReusltItem();
		try {
			switch (StringUtilsEX.ToInt(type)) {
			case 0:
				// 查询 省
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
				// 查询区
				List<Area> areas = areaService.selectByCityCode(code);
				item.setCode(0);
				item.setData(areas);
				break;
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据code查询所有的省Provice的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, 
					"根据code查询所有的省Provice的信息出错! 异常信息:", e,
					"/platform/membermanagement/queryRegion");
		}
		return item;
	}

	/**
	 * 根据条件分页查询会员用户
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param name
	 * @param mobile
	 * @param email
	 * @param sex
	 * @param begin
	 * @param end
	 * @param usertype
	 * @return
	 */
	@RequestMapping("/queryMemberList")
	public ReusltItem queryMemberList(String pageindex,
			String pagesize, String name, String mobile, String email,
			String sex, String begin, String end, Integer usertype) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaUser criteria = new CriteriaUser();
			criteria.setUsername(name);
			criteria.setMobile(mobile);
			criteria.setEmail(email);
			criteria.setSex(StringUtilsEX.ToInt(sex));
			criteria.setBegin(StringUtilsEX.ToShortDate(begin));
			criteria.setEnd(StringUtilsEX.ToShortDate(end));
			criteria.setUsertype(2);
			criteria.setOrderByClause("createTime");
			criteria.setSort("desc");
			PageBean pageBean = accountsService.queryMemberByCriteria(criteria,
					StringUtilsEX.ToInt(pageindex),
					StringUtilsEX.ToInt(pagesize));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据条件分页查询会员用户的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, 
							"根据条件分页查询会员用户的信息出错! 异常信息:", e,
							"/platform/membermanagement/queryMemberList");
		}
		return item;

	}

	/**
	 * 会员数据绑定
	 * 
	 * @return
	 */
	@RequestMapping("/queryMemberById")
	public ReusltItem queryMemberById(String memberid) {
		ReusltItem item = new ReusltItem();

		try {
			UsersInfoDto usersInfoDto = new UsersInfoDto();
			usersInfoDto = accountsService.selectMemberById(StringUtilsEX
					.ToInt(memberid));
			usersInfoDto.setYear(DateUtil.getYear(usersInfoDto.getBirthday()));
			usersInfoDto
					.setMonth(DateUtil.getMonth(usersInfoDto.getBirthday()));
			usersInfoDto.setDay(DateUtil.getDay(usersInfoDto.getBirthday()));
			item.setCode(0);
			item.setData(usersInfoDto);
			item.setDesc("会员数据绑定");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("会员数据绑定的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
							"会员数据绑定的信息出错! 异常信息:",e,
							"/platform/membermanagement/queryMemberById");
		}

		return item;
	}

	/**
	 * 根据id修改会员信息
	 * 
	 * @param usertype
	 * @param memberid
	 * @param username
	 * @param nickname
	 * @param realname
	 * @param mobile
	 * @param email
	 * @param phone
	 * @param sex
	 * @param birthyear
	 * @param birthmonth
	 * @param birthday
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
	 * @param levelid
	 * @return
	 */
	@RequestMapping("/updateMenberById")
	public ReusltItem updateMenberById(String usertype,
			String memberid, String pwd, String username, String imgurl,String nickname,
			String realname, String mobile, String email, String phone,
			String sex, String birthyear, String birthmonth, String birthday,
			String provincecode, String provincename, String citycode,
			String cityname, String areacode, String areaname, String address,
			String postcode, String idcardtype, String idcard,
			String incomemonth, String levelid, String birth) {
		ReusltItem item = new ReusltItem();

		int result = 0;
		try {
			user= SessionState.GetCurrentUser();
			String  regx="\\w{3,16}";
	    	Pattern	p = Pattern.compile(regx); 
	    	
			if(!StringUtilsEX.IsNullOrWhiteSpace(idcard)){
				if (!idcard.matches("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$")){
					item.setCode(-182);
					item.setDesc("身份证号码格式不正确！");
					return item;
				}
			}
			
			if (!p.matcher(username).matches()) {
				item.setCode(-111);
				item.setDesc("用户名不正确，请输入3~16个字符");
				return item;
			}
			CriteriaAccounts cAccounts=new CriteriaAccounts();
			cAccounts.setLoginname(username);
			cAccounts.setUsertype(UserTypeEnum.Buyer.getValue());
			
			// 修改accounts表
			Accounts accounts = accountsService.queryByuserid(StringUtilsEX
					.ToInt(memberid));
			
			cAccounts.setId(accounts.getId());		
			if(accountsService.isExistAcc(cAccounts)>0){
				item.setCode(-201);
				item.setDesc("该账号已存在!");
				return item;
			}
			accounts.setLoginname(username);
			accounts.setEmail(email);
			accounts.setPhone(mobile);
			
			result = accountsService.updateMemberByUserId(accounts);
			// 修改users表
			Users record = userService.selectByPrimaryKey(StringUtilsEX
					.ToInt(memberid));
			Users users = new Users();
			users.setUsername(username);
			users.setImgurl(imgurl);
			users.setIdcard(idcard);
			users.setNickname(nickname);
			users.setRealname(realname);
			users.setMobile(mobile);
			users.setEmail(email);
			users.setIdcardtype(StringUtilsEX.ToInt(idcardtype));
			users.setLevelid(StringUtilsEX.ToInt(levelid));
			BeanUtils.copyProperties(users, record);
			result = userService.updateById(record);
			// 修改UserAttr表
			List<UserAttr> list = userAttrService.selectById(StringUtilsEX
					.ToInt(memberid));
			UserAttr record1 = list.get(0);
			UserAttr userAttr = new UserAttr();
			userAttr.setId(record1.getId());
			userAttr.setPhone(phone);
			userAttr.setSex(StringUtilsEX.ToInt(sex));
			/* String date = birthyear+"-"+birthmonth+"-"+birthday; */
			userAttr.setBirthday(StringUtilsEX.ToShortDate(birth));
			userAttr.setProvincecode(provincecode);
			userAttr.setProvincename(provincename);
			if (provincename.equals("请选择")) {
				userAttr.setProvincename("");
			}
			userAttr.setCitycode(citycode);
			userAttr.setCityname(cityname);
			if (cityname.equals("请选择")) {
				userAttr.setCityname("");
			}
			userAttr.setAreacode(areacode);
			userAttr.setAreaname(areaname);
			if (areaname.equals("请选择")) {
				userAttr.setAreaname("");
			}
			userAttr.setAddress(address);
			userAttr.setPostcode(postcode);
			userAttr.setIncomemonth(StringUtilsEX.ToInt(incomemonth));
			BeanUtils.copyProperties(userAttr, record1);
			result = userAttrService.updateById(record1);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("更改成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "member_update.jsp", "/platform/membermanagement/updateMenberById", "根据id修改会员信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id修改会员信息操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/updateMenberById");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("更改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id修改会员信息的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"根据id修改会员信息的信息出错! 异常信息:",e,
					"/platform/membermanagement/updateMenberById");

		}

		return item;
	}

	/**
	 * 添加新会员
	 * 
	 * @param usertype
	 * @param username
	 * @param nickname
	 * @param realname
	 * @param pwd
	 * @param mobile
	 * @param email
	 * @param levelid
	 * @param sex
	 * @param birthyear
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
	 * @param incomemonth
	 * @return
	 */
	@RequestMapping("/addMenber")
	public ReusltItem addMenber(String usertype, String imgurl,String username,
			String nickname, String realname, String pwd, String mobile,
			String email, String levelid,String idcard,String sex, String birthyear,
			String birthmonth, String birthday,
			String provincecode, String provincename, String citycode,
			String cityname, String areacode, String areaname, String address ) {
		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
//			List<Users> list = userService.queryByUsernme(username);
//			if (list.size() > 0) {
//				item.setCode(-101);
//				item.setDesc("用户名已被使用");
//				return item;
//			}
			CriteriaAccounts cAccounts=new CriteriaAccounts();
			cAccounts.setLoginname(username);
			if(accountsService.isExistAcc(cAccounts)>0){
				item.setCode(-201);
				item.setDesc("该账号已存在!");
				return item;
			}
			if (!StringUtilsEX.checkUserName(username)) {
				item.setCode(-111);
				item.setDesc("用户名不正确，请输入3~16个字符，26个大小写字、数字或“_-”（不能以数字开头）");
				return item;
			}
			
			if(!StringUtilsEX.IsNullOrWhiteSpace(idcard)){
				if (!idcard.matches("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$")){
					item.setCode(-182);
					item.setDesc("身份证号码格式不正确！");
					return item;
				}
			}
			if ("".equals(mobile) || mobile == null) {
				item.setCode(-102);
				item.setDesc("手机号不能为空");
				return item;
			}
			int results = accountsService.addMenber(
					StringUtilsEX.ToInt(usertype),imgurl, username, nickname,
					realname, pwd, mobile, email,StringUtilsEX.ToInt(levelid),
					idcard,sex,birthyear,birthmonth,birthday,provincecode,
					provincename,citycode,cityname,areacode,areaname,address);
			
			if (results > 0) {
				item.setCode(0);
				item.setDesc("添加成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "member_add.jsp", "/platform/membermanagement/addMenber", "添加新会员");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加新会员操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/addMenber");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				if(results==-200){
					item.setDesc("系统未设置用户等级");
				}else{				
				item.setDesc("添加失败");}
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加新会员的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"添加新会员的信息出错! 异常信息:",
							e, "/platform/membermanagement/addMenber");
		}

		return item;
	}

	/**
	 * 修改会员密码
	 * 
	 * @param usertype
	 * @param memberid
	 * @param loginpwd
	 * @return
	 */
	@RequestMapping("/updateMemberPwd")
	public ReusltItem updateMemberPwd(String usertype,
			String memberid, String loginpwd) {
		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
			int result = accountsService.updatePasswordByUserId(
					StringUtilsEX.ToInt(memberid), loginpwd);
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
                            		user.getUserId(), user.getLoginName(), "memberpwd_update.jsp", "/platform/membermanagement/updateMemberPwd", "修改会员密码");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改会员密码操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/updateMemberPwd");
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
				item.setDesc("修改会员密码的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"修改会员密码的信息出错! 异常信息:",e,
							"/platform/membermanagement/updateMemberPwd");
		}
		return item;
	}

	/**
	 * 修改会员支付密码
	 * 
	 * @param usertype
	 * @param memberid
	 * @param loginpwd
	 * @return
	 */
	@RequestMapping("/updateMemberPayPwd")
	public ReusltItem updateMemberPayPwd(String usertype,
			String memberid, String paypwd) {
		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
			int result = userService.updatePayPasswordByUserId(
					StringUtilsEX.ToInt(memberid), paypwd);
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
                            		user.getUserId(), user.getLoginName(), "memberpaypwd_update.jsp", "/platform/membermanagement/updateMemberPayPwd", "修改会员支付密码");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改会员支付密码操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/updateMemberPayPwd");
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
				item.setDesc("修改会员密码的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"修改会员密码的信息出错! 异常信息:",e,
					"/platform/membermanagement/updateMemberPayPwd");
		}
		return item;
	}

	/**
	 * 会员账户充值
	 * 
	 * @param memberid
	 * @param money
	 * @param type
	 *            充值类型 0 是账户余额 1是保证金
	 * @return
	 */
	@RequestMapping("/rechargeMember")
	public ReusltItem rechargeMember(String memberid,
			String money1, String type) {
		ReusltItem item = new ReusltItem();
		String userip = GetIpAddresss.getIp();
		try {
			if(StringUtilsEX.ToDouble(money1)<=0){
				item.setCode(-101);
				item.setDesc("充值充值金额参数错误");
				return item;
			}
			user= SessionState.GetCurrentUser();
			int result = usercapitalService.recharge(
					StringUtilsEX.ToDouble(money1), type,
					StringUtilsEX.ToInt(memberid), userip);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("充值成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "member_recharge.jsp", "/platform/membermanagement/rechargeMember", "会员账户充值");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"会员账户充值操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/rechargeMember");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("充值失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("会员账户充值的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"会员账户充值的信息出错! 异常信息:",e, 
					"/platform/membermanagement/rechargeMember");
		}
		return item;
	}

	/**
	 * 根据用户查询会员银行卡
	 * 
	 * @param memberid
	 * @return
	 */
	@RequestMapping("/queryBrandCard")
	public ReusltItem queryBrandCard(String memberid) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(memberid) <= 0) {
				item.setCode(-101);
				item.setDesc("参数错误memberid" + memberid);
			}
			List<BankCard> list = bankCardService
					.queryBrandCardByUserId(StringUtilsEX.ToInt(memberid));
			if (list.size() > 0) {
				item.setCode(0);
				item.setData(list);
				item.setDesc("查询成功");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据用户查询会员银行卡的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"根据用户查询会员银行卡的信息出错! 异常信息:",e, 
							"/platform/membermanagement/queryBrandCard");
		}
		return item;
	}

	/**
	 * 删除会员
	 * 
	 * @param memberid
	 * @return
	 */
	@RequestMapping("/deleteMember")
	public ReusltItem deleteMember(String usertype,
			String memberid, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
			List<Accounts> list = accountsService.queryMember(StringUtilsEX
					.ToInt(memberid));
			Accounts accounts = list.get(0);
			accounts.setUsertype(StringUtilsEX.ToInt(usertype));
			accounts.setIsdel(true);
			accounts.setStatus(1);
			accounts.setDeltime(new Date());
			accounts.setDeluserid(SessionUtil.getSessionUserId(request));
			int result = accountsService.updateMemberByUserId(accounts);
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
                            		user.getUserId(), user.getLoginName(), "membermanagement_list.jsp", "/platform/membermanagement/deleteMember", "删除会员");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除会员操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/deleteMember");
    					}
    					
    				}
    			});
			} else {
				item.setCode(0);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除会员的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"删除会员的信息出错! 异常信息:",e,
					 "/platform/membermanagement/deleteMember");
		}
		return item;

	}

	/**
	 * 发送一个邮件
	 * 
	 * @param email
	 * @param title
	 * @param content
	 * @return
	 */
	@RequestMapping("/sendEmailOne")
	public ReusltItem sendEmailOne(String email, String title,
			String content) {
		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
			if (!StringUtils.isNotNull(email)) {
				item.setCode(-101);
				item.setDesc("邮件号不能为空");
				return item;
			}
			boolean istrue = CommonUtils.sendEmail(email, title, content);
			if (istrue == true) {
				item.setCode(0);
				item.setDesc("发送成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "send_email.jsp", "/platform/membermanagement/sendEmailOne", "发送一个邮件");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"发送一个邮件操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/sendEmailOne");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("发送失败");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("发送一个邮件的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"发送一个邮件的信息出错! 异常信息:",
							e, "/platform/membermanagement/sendEmailOne");
		}
		return item;
	}

	/**
	 * 批量发送邮件
	 * 
	 * @param memberemailstr
	 * @return
	 */
	@RequestMapping("/sendEmail")
	public ReusltItem sendEmail(String emails, String title,
			String content) {
		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
			if (!StringUtils.isNotNull(emails)) {
				item.setCode(-101);
				item.setDesc("邮件号不能为空");
				return item;
			}
			// 发送邮件
			String[] array = emails.split(",");
			for (String email : array) {
				CommonUtils.sendEmail(email, title, content);
			}
			item.setCode(0);
			item.setDesc("发送成功");
			//异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "send_email.jsp", "/platform/membermanagement/sendEmail", "批量发送邮件");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"批量发送邮件操作记录出错! 异常信息:",
								e, "/platform/membermanagement/sendEmail");
					}
					
				}
			});
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("批量发送邮件的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"批量发送邮件的信息出错! 异常信息:",
					e, "/platform/membermanagement/sendEmail");
		}

		return item;
	}

	/**
	 * 发送一条短信
	 * 
	 * @param type
	 * @param mobiles
	 * @param content
	 * @param ids
	 * @return
	 */
	@RequestMapping("/addMessageMobileRecord")
	public ReusltItem addMessageMobileRecord(String mobile,
			String content) {

		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
			if (!StringUtils.isNotNull(mobile)) {
				item.setCode(-101);
				item.setDesc("手机号不能为空");
				return item;
			}
			if (!StringUtils.isNotNull(content)) {
				item.setCode(-102);
				item.setDesc("短息内容不能为空");
				return item;
			}
			ZooSmsUtil.sendMessage(mobile, content); // 发送短息 上线使用
			item.setCode(0);
			item.setDesc("发送成功");
			//异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "send_mobilemessage.jsp", "/platform/membermanagement/addMessageMobileRecord", "发送一条短信");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"发送一条短信操作记录出错! 异常信息:",
								e, "/platform/membermanagement/addMessageMobileRecord");
					}
					
				}
			});
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("发送一条短信的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"发送一条短信的信息出错! 异常信息:",e,
					"/platform/membermanagement/addMessageMobileRecord");
		}
		return item;
	}

	/**
	 * 批量发送站内信
	 * 
	 * @param receiveid
	 * @param title
	 * @param content
	 * @return
	 */
	@RequestMapping("/addMessageList")
	public ReusltItem addMessageList(String receiveid,
			String title, String content, String type,
			HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
			if (!StringUtils.isNotNull(receiveid)) {
				item.setCode(-101);
				item.setDesc("会员id错误");
				return item;
			}
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			int userid = sessionUser.getUserId();
			String[] array = receiveid.split(",");
			List<Messages> list = new ArrayList<Messages>();
			for (String id : array) {
				Users users = userService.selectByPrimaryKey(userid);
				Messages messages = new Messages();
				messages.setUserid(StringUtilsEX.ToInt(id));
				messages.setTitle(title);
				messages.setContent(content);
				messages.setSendtime(new Date());
				messages.setSenduserid(userid); // 登录者发送的id
				messages.setStatus(1); // 表示未读的
				messages.setSendusername(users.getUsername());
				messages.setType(StringUtilsEX.ToInt(type));
				list.add(messages);
			}
			int result = accountsService.insertBacth(list);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("发送成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "send_systemmessage.jsp", "/platform/membermanagement/addMessageList", "批量发送站内信");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量发送站内信操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/addMessageList");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("发送失败");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("批量发送站内信的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"批量发送站内信的信息出错! 异常信息:",
							e, "/platform/membermanagement/addMessageList");
		}
		return item;
	}

	/**
	 * 发送一条站内信
	 * 
	 * @param receiveid
	 * @param title
	 * @param content
	 * @param type
	 * @param token
	 * @return
	 */
	@RequestMapping("/addMessage")
	public ReusltItem addMessage(String receiveid, String title,
			String content, String type, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
			if (!StringUtils.isNotNull(receiveid)) {
				item.setCode(-101);
				item.setDesc("会员id错误");
				return item;
			}
			SessionUser sessionUser = SessionUtil.getSessionUser(request);

			int userid = sessionUser.getUserId();
			Users users = userService.selectByPrimaryKey(userid);
			Messages messages = new Messages();
			messages.setUserid(StringUtilsEX.ToInt(receiveid));
			messages.setTitle(title);
			messages.setContent(content);
			messages.setSendtime(new Date());
			messages.setSenduserid(userid); // 登录者发送的id
			messages.setStatus(1); // 表示未读的
			messages.setSendusername(users.getUsername());
			messages.setType(StringUtilsEX.ToInt(type));

			int result = accountsService.insertMessage(messages);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("发送成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "send_systemmessage.jsp", "/platform/membermanagement/addMessageList", "发送一条站内信");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"发送一条站内信操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/addMessageList");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("发送失败");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("发送站内信的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"发送站内信的信息出错! 异常信息:{0}",e, 
					"/platform/membermanagement/addMessageList");
		}
		return item;
	}

	/**
	 * 账户冻结的更改
	 * 
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public ReusltItem updateStatus(String id, String status) {
		ReusltItem item = new ReusltItem();
		try {
			user= SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数错误：id" + id);
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-102);
				item.setDesc("参数错误：status" + status);
			}
			int result = accountsService.updatStatus(StringUtilsEX.ToInt(id),
					StringUtilsEX.ToInt(status));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("冻结成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "membermanagement_list.jsp", "/platform/membermanagement/updateStatus", "账户冻结的更改");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"账户冻结的更改操作记录出错! 异常信息:",
    								e, "/platform/membermanagement/updateStatus");
    					}
    					
    				}
    			});
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("账户冻结的更改的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"账户冻结的更改的信息出错! 异常信息:",
							e, "/platform/membermanagement/updateStatus");
		}
		return item;
	}

	/**
	 * 根据id查询邮箱
	 * 
	 * @return
	 */
	@RequestMapping("/queryEmail")
	public ReusltItem queryEmail(String idList) {
		ReusltItem item = new ReusltItem();
		try {
			String[] ids = idList.split(",");
			String emails = "";
			for (String id : ids) {
				if (StringUtilsEX.ToInt(id) > 0) {
					Accounts accounts = accountsService.queryById(StringUtilsEX
							.ToInt(id));
					String email = accounts.getEmail();
					if (!"".equals(email) && email != null) {
						emails += email + ",";
					}

				}

			}
			item.setCode(0);
			item.setData(emails);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id查询邮箱的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"根据id查询邮箱的信息异常:{0}", e,
					"/platform/membermanagement/queryEmail");
		}
		return item;
	}

	/**
	 * 根据id查询手机号
	 * 
	 * @return
	 */
	@RequestMapping("/queryMobilenum")
	public ReusltItem queryMobilenum(String idList) {
		ReusltItem item = new ReusltItem();
		
		try {
			String[] ids = idList.split(",");
			String mobilenum = "";
			String usernames = "";
			for (String id : ids) {
				if (StringUtilsEX.ToInt(id) > 0) {
					Accounts accounts = accountsService.queryById(StringUtilsEX
							.ToInt(id));
					String mobile = accounts.getPhone();
					String username = Integer.toString(accounts.getUserid());
					if (!"".equals(mobile) && mobile != null) {
						usernames += username + ",";
						mobilenum += mobile + ",";
					}

				}

			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mobilenum", mobilenum);
			map.put("usernames", usernames);
			item.setCode(0);
			item.setData(map);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id查询手机号的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"根据id查询手机号的信息异常:", e,
					"/platform/membermanagement/queryMobilenum");
		}
		return item;
	}

	/**
	 * 根据id查询userid
	 * 
	 * @return
	 */
	@RequestMapping("/queryUserIds")
	public @ResponseBody ReusltItem queryUserIds(String idList) {
		ReusltItem item = new ReusltItem();
		try {
			String[] ids = idList.split(",");
			String userids = "";
			for (String id : ids) {
				if (StringUtilsEX.ToInt(id) > 0) {
					Accounts accounts = accountsService.queryById(StringUtilsEX
							.ToInt(id));
					String userid = Integer.toString(accounts.getUserid());
					if (!"".equals(userid) && userid != null) {
						userids += userid + ",";
					}

				}

			}
			item.setCode(0);
			item.setData(userids);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id查询userid的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,
					"根据id查询userid的信息异常:", e,
					"/platform/membermanagement/queryUserIds");
		}
		return item;
	}
}
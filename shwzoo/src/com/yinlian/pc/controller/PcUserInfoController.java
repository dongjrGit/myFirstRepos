/*
 * @(#) PcActivityViewController.java 2016年7月12日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.CardTypEnum;
import com.yinlian.Enums.IncomeTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.pc.dto.InComeDto;
import com.yinlian.pc.dto.UserAttrInfoDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Receiveaddress;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ReceiveAddressService;
import com.yinlian.wssc.web.service.SmsService;
import com.yinlian.wssc.web.service.UserAttrService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.giftcardService;
import com.yinlian.wssc.web.util.CommonUtils;
import com.yinlian.wssc.web.util.ConfigUtil;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import data.ParseUtil;

@RestController
@RequestMapping("/pc/user")
public class PcUserInfoController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountsService accountsService;

	@Autowired
	private UserAttrService userAttrService;


	@Autowired
	private CityServcie cityService;

	@Autowired
	private AreaService areaService;

	 @Autowired
    private ProvinceServcice    provinceServcice;
	 
	 @Autowired
	 private ReceiveAddressService  receiveAddressService;
	 @Autowired
	 private giftcardService  giftcardService;
	 
	 @Autowired
	 private OperaterecordsService operaterecordsService;
	 @Autowired
	 private SmsService          smsService;
	    
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
	@RequestMapping(value = "/updBaseUserInfo", produces = "text/html;charset=UTF-8")
	public  String updUserInfo( String NickName,
			String RealName, String Sex, String BirthYear, String BirthMonth,
			String BirthDay, String ProvinceName, String ProvinceCode, String CityName,
			String CityCode,String AreaName, String AreaCode, String Address,String ch,HttpServletRequest request) throws Exception {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(NickName)) {
				item.setCode(-101);
				item.setDesc("昵称不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(RealName)) {
				item.setCode(-102);
				item.setDesc("真实姓名不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(Sex)) {
				item.setCode(-103);
				item.setDesc("性别不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionUtil.getSessionUser(request);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			} else {
				int buyerId = sessionUser.getUserId();

				Users users = new Users();
				users = userService.queryById(buyerId);
				users.setNickname(NickName);
				users.setRealname(RealName);
				
				// user.setId(StringUtilsEX.ToInt(id));

				UserAttr userAttr = new UserAttr();
				List<UserAttr> list = userAttrService.selectById(buyerId);
				if (list != null && list.size() > 0) {
					userAttr = list.get(0);
				}
				// userAttr.setId(StringUtilsEX.ToInt(id));
				userAttr.setBirthday(ParseUtil.parseStringToDate(BirthYear+"-"+BirthMonth+"-"+BirthDay+" 00:00:00"));
				
				
				userAttr.setProvincename(ProvinceName);
				userAttr.setProvincecode(ProvinceCode);
				userAttr.setCitycode(CityCode);
				userAttr.setCityname(CityName);
				userAttr.setAreacode(AreaCode);
				userAttr.setAreaname(AreaName);
				userAttr.setSex(StringUtilsEX.ToInt(Sex));
				
				// userAttr.setAddress(address[3]);
				userAttr.setAddress(Address);
				userService.updatUserInfo(users, userAttr);
				item.setCode(0);
				item.setDesc("信息修改成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				SessionUser user=SessionState.GetCurrentUser();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "personalinformation.html", "/pc/user/updBaseUserInfo", "修改用户信息");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"修改用户信息记录:",
    								e, "/pc/user/updBaseUserInfo");
						}
					}
				});
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("修改用户信息异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("修改用户信息异常! 异常信息:{0}", e.getMessage()),
					"user/updUserInfo");
		}

		return item.toJson();
	}

	/**
	 * 修改头像
	 * @param ImgUrl
	 * @param ch
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updUserImg", produces = "text/html;charset=UTF-8")
	public  String updUserImg(String ImgUrl,String ch,HttpServletRequest request) throws Exception {
		BaseResult item = new BaseResult();
		try {
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser();
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				return "member/user/showlogin";
			} else {
				int userid = sessionUser.getUserId();
				Users user=userService.queryById(userid);
				user.setImgurl(ImgUrl);
				int i=userService.updateInfo(user);
				if(i>0){
					item.setCode(0);
					item.setDesc("信息修改成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					SessionUser users=SessionState.GetCurrentUser();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), users.getId(), users.getLoginName(), "personalinformation.html", "/pc/user/updUserImg", "修改头像");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"修改头像记录:",
	    								e, "/pc/user/updUserImg");
							}
						}
					});
				}
			
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("修改用户信息异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("修改用户信息异常! 异常信息:{0}", e.getMessage()),
					"user/updUserImg");
		}

		return item.toJson();
	}

	
	/**
	 * 修改更多信息
	 * @param MaritalStatus
	 * @param IDCard
	 * @param IncomeMonth
	 * @param ch
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updMoreInfo", produces = "text/html;charset=UTF-8")
	public  String updMoreInfo(String MaritalStatus,String IDCard,String IncomeMonth,String ch,HttpServletRequest request) throws Exception {
		BaseResult item = new BaseResult();
		try {
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionUtil.getSessionUser(request);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				return "member/user/showlogin";
			} else {
				int userid = sessionUser.getUserId();
				Users user=userService.queryById(userid);
				user.setIdcard(IDCard);
				user.setIdcardtype(CardTypEnum.lvl1.getValue());
				
				UserAttr userAttr=new UserAttr();
				List<UserAttr> list = userAttrService.selectById(userid);
				if (list != null && list.size() > 0) {
					userAttr = list.get(0);
				}
				userAttr.setMaritalstatus(StringUtilsEX.ToInt(MaritalStatus));
				userAttr.setIncomemonth(StringUtilsEX.ToInt(IncomeMonth));
				userService.updatUserInfo(user, userAttr);
				ExecutorService executorService=Executors.newCachedThreadPool();
				SessionUser users=SessionState.GetCurrentUser();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), users.getId(), users.getLoginName(), "personalinformation.html", "/pc/user/updMoreInfo", "修改更多信息");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"修改更多信息记录:",
    								e, "/pc/user/updMoreInfo");
						}
					}
				});
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("修改用户信息异常");
			}			
			LogHandle.error(LogType.pc,
					MessageFormat.format("修改用户信息异常! 异常信息:{0}", e.getMessage()),
					"user/updMoreInfo");
		}
		return item.toJson();
	}
	
	
	@RequestMapping("/getIncome")
    public String  getIncome() {
		BaseResult item = new BaseResult();
        try {
        	IncomeTypeEnum[] array = IncomeTypeEnum.values();

            List<InComeDto> list = new ArrayList<InComeDto>();
            for (int i = 0; i < array.length; i++) {
            	InComeDto dTo = new InComeDto();
            	dTo.setCode(IncomeTypeEnum.values()[i].getValue());
            	dTo.setName(IncomeTypeEnum.values()[i].name());
                list.add(dTo);
            }
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
            item.setCode(-900);
            if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("获取收入水平类型异常");
			}
            LogHandle
                .error(LogType.pc, MessageFormat.format("获取收入水平类型! 异常信息:{0}", e.getMessage()),
                    "user/getIncome");
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
	public @ResponseBody String selectInfo(String ch,HttpServletRequest request)
			throws Exception {
		BaseResult item = new BaseResult();
		try {
			
			SessionUser sessionUser =SessionUtil.getSessionUser(request);
			UserAttrInfoDto dto=new UserAttrInfoDto();
			if(sessionUser.getCode()==0){
				int userId = sessionUser.getUserId();
				
				
				Users users = userService.queryById(userId);
				if(users!=null){
					dto.setUsername(users.getUsername());
					dto.setNickname(users.getNickname());
					dto.setRealname(users.getRealname());
					dto.setPoint(users.getPoints());
					dto.setIdcard(users.getIdcard());
					dto.setIDCardType(users.getIdcardtype());
					dto.setImgurl(users.getImgurl());
					dto.setEmail(users.getEmail());
					dto.setIsEmailCheck(users.getIsemailcheck());
					dto.setIsMobileCheck(users.getIsmobilecheck());
				}
				
				UserAttr userAttr=new UserAttr();
				List<UserAttr> list = userAttrService.selectById(userId);
				if (list != null && list.size() > 0) {
					userAttr = list.get(0);
					dto.setSex(userAttr.getSex());
					Date brithday=userAttr.getBirthday();
					if(brithday!=null){
						Calendar cal=Calendar.getInstance();
						cal.setTime(brithday);
						
						Integer b_year=cal.get(Calendar.YEAR);
						Integer b_month=cal.get(Calendar.MONTH)+1;
						Integer b_day=cal.get(Calendar.DATE);
						
						dto.setB_year(b_year);
						dto.setB_month(b_month);
						dto.setB_day(b_day);
					}
					
					dto.setProvinceCode(userAttr.getProvincecode());
					dto.setAreaCode(userAttr.getAreacode());
					dto.setCityCode(userAttr.getCitycode());
					dto.setAddress(userAttr.getAddress());
				    dto.setMaritalStatus(userAttr.getMaritalstatus());
				    dto.setIncomeMonth(userAttr.getIncomemonth());
				}
				
			}else{
				
			}
			
			item.setData(dto);
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("显示我的信息异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("显示我的信息异常! 异常信息:{0}", e.getMessage()),
					"user/selectInfo");
		}

		return item.toJson();
	}
	
	
	
	/**
	 * 查询地区
	 * @param RegionType
	 * @param ParentCode
	 * @param ch
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/selectAreas",produces = "text/html;charset=UTF-8")
	public  String selectAreas(String RegionType,String ParentCode,String ch){
		BaseResult item=new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(RegionType)) {
				item.setCode(-101);
				item.setDesc("查询类型不能为空");
				return item.toJson();
			}
			int type=StringUtilsEX.ToInt(RegionType);
			List list=new ArrayList();
			if(type==0){
				list=provinceServcice.selectPro();
			}else if(type==1){
				list=cityService.selectCity(ParentCode);
			}else if(type==2){
				list=areaService.selectArea(ParentCode);
			}
			item.setData(list);
			item.setCode(0);
			item.setDesc("查询全部省成功");
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("查询地区异常");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("查询异常! 异常信息:{0}",
					e.getMessage()), "user/selectAreas");
		}
		return item.toJson();
	}
	
	
	/**
	 * 删除地址
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delReceiverAddr",produces = "text/html;charset=UTF-8")
	public  String delReceiverAddr(String id,String ch){
		BaseResult item=new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<=0){
				item.setCode(-401);
				item.setDesc("用户未登录");
			}
			if(StringUtilsEX.ToInt(id)<0){
				item.setCode(-101);
				item.setDesc("id不能为空");
			}
			int result=receiveAddressService.delByID(id);
			if(result>0){
				item.setCode(0);
				item.setDesc("删除成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "receiveAddress.html", "/pc/user/delReceiverAddr", "删除地址");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"删除地址记录异常:",
    								e, "/pc/user/delReceiverAddr");
						}
					}
				});
			}else{
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("删除地址异常");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("删除地址异常! 异常信息:{0}",
					e.toString()), "receiver/delReceiverAddr");
		}
		
		return item.toJson();
	}
	
	/**
	 * 查询每个顾客的收货人地址
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/selectAddrByUserId",produces = "text/html;charset=UTF-8")
	public  String selectAddrByUserId(String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		ch="1";
		try {
			
			SessionUser sessionUser=new SessionUser();
			sessionUser=SessionUtil.getSessionUser(request);
			if(sessionUser.getCode()==0){
				String  userid=sessionUser.getUserId().toString();
				List<Receiveaddress> list = receiveAddressService.queryAllByUserId(userid);
				item.setCode(0);
				item.setData(list);
				//item.setDesc("查询成功");
			}
		
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("查询收货人地址异常");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("查询每个顾客的收货人地址异常! 异常信息:{0}",
					e.toString()), "user/selectAddrByUserId");
		}
		
		return item.toJson();
	}
	
	
	
	/**
	 * 设置默认地址
	 * @param ch
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/setDefault",produces = "text/html;charset=UTF-8")
	public  String setDefault(String ch,String id,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<=0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			Receiveaddress  receiveaddress=receiveAddressService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			receiveaddress.setIsdefault(1);
			receiveAddressService.updateReceiver(receiveaddress);
			ExecutorService executorService=Executors.newCachedThreadPool();
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {						
						operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "receiveAddress.html", "/pc/user/setDefault", "设置默认地址");
					} catch (Exception e) {
						LogHandle.error(LogType.OperateRecords,"设置默认地址记录异常:",
								e, "/pc/user/setDefault");
					}
				}
			});
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("设置默认地址异常");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("设置默认地址异常! 异常信息:{0}",
					e.toString()), "user/setDefault");
		}
		return item.toJson();
	}
	
	/**
	 * 添加收货地址
	 * @param name
	 * @param provinceCode
	 * @param provinceName
	 * @param cityCode
	 * @param cityName
	 * @param areaCode
	 * @param areaName
	 * @param address
	 * @param isHome
	 * @param mobile
	 * @param isDefault
	 * @param telArea
	 * @param telNum
	 * @param telExt
	 * @param isReceive
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addReceiverAddr",produces = "text/html;charset=UTF-8")
	public  String addReceiverAddr(String name, String provinceCode, String provinceName,
			String cityCode, String cityName, String areaCode, String areaName, String address,
			String isHome, String mobile, String isDefault, String telArea,String telNum,
			String telExt,String isReceive,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (user==null || user.getId() <= 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-101);
				item.setDesc("收货人姓名(name)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-102);
				item.setDesc("收货人手机号码(mobile)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(address)) {
				item.setCode(-103);
				item.setDesc("详细地址(address)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(provinceCode)) {
				item.setCode(-106);
				item.setDesc("省编号(provinceCode)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(cityCode)) {
				item.setCode(-107);
				item.setDesc("市编号(cityCode)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(areaCode)) {
				item.setCode(-108);
				item.setDesc("区编号(areaCode)不能为空！");
				return item.toJson();
			}
			
			if (StringUtilsEX.IsNullOrWhiteSpace(address)) {
				item.setCode(-104);
				item.setDesc("收货地址不能为空！");
				return item.toJson();
			}
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号(mobile)格式错误！");
				return item.toJson();
			}
			
			Integer userid=SessionUtil.getSessionUserId(request);
			
			Receiveaddress receiveaddress=new Receiveaddress();
			receiveaddress.setUserid(userid);
			receiveaddress.setName(name);
			receiveaddress.setProvincecode(provinceCode);
			receiveaddress.setProvincename(provinceName);
			receiveaddress.setCitycode(cityCode);
			receiveaddress.setCityname(cityName);
			receiveaddress.setAreacode(areaCode);
			receiveaddress.setAreaname(areaName);
			receiveaddress.setAddress(address);
			receiveaddress.setIshome(StringUtilsEX.ToInt(isHome));
			receiveaddress.setMobile(mobile);
			receiveaddress.setIsdefault(StringUtilsEX.ToInt(isDefault));
			receiveaddress.setTelAreacode(telArea);
			receiveaddress.setTelNumber(telNum);
			receiveaddress.setTelExtension(telExt);
			receiveaddress.setIsreceive(StringUtilsEX.ToBoolean(isReceive));			
			int i=receiveAddressService.inserAddress(receiveaddress);
			
			if(i>0){
				item.setDesc("添加收货地址成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "receiveAddress.html", "/pc/user/addReceiverAddr", "添加收货地址");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"添加收货地址记录异常:",
									e, "/pc/user/addReceiverAddr");
						}
					}
				});
			}else{
				item.setCode(-110);
				item.setDesc("添加收货地址失败");
			}
			
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("添加收货地址异常");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("添加收货地址异常! 异常信息:{0}",
					e.toString()), "user/addReceiverAddr");
		}
		
		return item.toJson();
	}
	
	
	/**
	 * 修改收货地址
	 * @param name
	 * @param provinceCode
	 * @param provinceName
	 * @param cityCode
	 * @param cityName
	 * @param areaCode
	 * @param areaName
	 * @param address
	 * @param isHome
	 * @param mobile
	 * @param isDefault
	 * @param telArea
	 * @param telNum
	 * @param telExt
	 * @param isReceive
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updReceiverAddr",produces = "text/html;charset=UTF-8")
	public  String updReceiverAddr(String adID,String name, String provinceCode, String provinceName,
			String cityCode, String cityName, String areaCode, String areaName, String address,
			String isHome, String mobile, String isDefault, String telArea,String telNum,
			String telExt,String isReceive,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (user==null || user.getId() <= 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-101);
				item.setDesc("收货人姓名(name)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-102);
				item.setDesc("收货人手机号码(mobile)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(address)) {
				item.setCode(-103);
				item.setDesc("详细地址(address)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(provinceCode)) {
				item.setCode(-106);
				item.setDesc("省编号(provinceCode)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(cityCode)) {
				item.setCode(-107);
				item.setDesc("市编号(cityCode)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(areaCode)) {
				item.setCode(-108);
				item.setDesc("区编号(areaCode)不能为空！");
				return item.toJson();
			}
			
			if (StringUtilsEX.IsNullOrWhiteSpace(address)) {
				item.setCode(-104);
				item.setDesc("收货地址不能为空！");
				return item.toJson();
			}
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号(mobile)格式错误！");
				return item.toJson();
			}
			
			Integer userid=SessionUtil.getSessionUserId(request);
			
			Receiveaddress  receiveaddress=receiveAddressService.selectByPrimaryKey(StringUtilsEX.ToInt(adID));
			receiveaddress.setUserid(userid);
			receiveaddress.setName(name);
			receiveaddress.setProvincecode(provinceCode);
			receiveaddress.setProvincename(provinceName);
			receiveaddress.setCitycode(cityCode);
			receiveaddress.setCityname(cityName);
			receiveaddress.setAreacode(areaCode);
			receiveaddress.setAreaname(areaName);
			receiveaddress.setAddress(address);
			receiveaddress.setIshome(StringUtilsEX.ToInt(isHome));
			receiveaddress.setMobile(mobile);
			receiveaddress.setIsdefault(StringUtilsEX.ToInt(isDefault));
			receiveaddress.setTelAreacode(telArea);
			receiveaddress.setTelNumber(telNum);
			receiveaddress.setTelExtension(telExt);
			receiveaddress.setIsreceive(StringUtilsEX.ToBoolean(isReceive));
			
			receiveAddressService.updateReceiver(receiveaddress);
			ExecutorService executorService=Executors.newCachedThreadPool();
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "receiveAddress.html", "/pc/user/updReceiverAddr", "修改收货地址");
					} catch (Exception e) {
						LogHandle.error(LogType.OperateRecords,"修改收货地址记录异常:",
								e, "/pc/user/updReceiverAddr");
					}
				}
			});
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("修改收货地址异常");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("修改收货地址异常! 异常信息:{0}",
					e.toString()), "user/updReceiverAddr");
		}
		
		return item.toJson();
	}
	
	/**
	 * 查询单个收货地址详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/selectAddrInfo",produces = "text/html;charset=UTF-8")
	public  String selectAddrInfo(String adID,String ch){
		BaseResult item=new BaseResult();
		try {
			if(StringUtilsEX.ToInt(adID)<0){
				item.setCode(-101);
				item.setDesc("收货地址id不能为空");
				return item.toJson();
			}
			
			Receiveaddress receiveaddress=receiveAddressService.selectByPrimaryKey(StringUtilsEX.ToInt(adID));
			item.setData(receiveaddress);
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("查询地址异常");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("查询收货地址详情异常! 异常信息:{0}",
					e.toString()), "receiver/selectAddrInfo");
		}
		
		return item.toJson();
	}
	

	

	/**
	 * 修改电话
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updPhone",produces = "text/html;charset=UTF-8")
	public  String updPhone(String Mobile,String MobileCode,String ImgCode,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (user==null || user.getId() <= 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(Mobile)) {
				item.setCode(-101);
				item.setDesc("手机号不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(MobileCode)) {
				item.setCode(-102);
				item.setDesc("手机验证码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(ImgCode)) {
				item.setCode(-103);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			
			String smss = RedisUserInfo.Get("I" + Mobile);
            if (!MobileCode.equals(smss)) {
                item.setCode(-106);
                item.setDesc("短信验证码错误");
                return item.toJson();
            }
            
           String code= RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
           
           if (!ImgCode.equals(code)) {
               item.setCode(-106);
               item.setDesc("短信验证码错误");
               return item.toJson();
           }
           
           Integer userid=SessionUtil.getSessionUserId(request);
           Users users=userService.queryById(userid);
           users.setMobile(Mobile);
           users.setIsmobilecheck(true);
           
           Accounts accounts=accountsService.queryByuserid(userid);
           accounts.setPhone(Mobile);
           
	       	UserAttr userAttr=new UserAttr();
			List<UserAttr> list = userAttrService.selectById(userid);
			if (list != null && list.size() > 0) {
				userAttr=list.get(0);
				userAttr.setPhone(Mobile); 
			}
	       
		   int temp=userService.updatePhone(users,accounts,userAttr);
	       if(temp>0){
	    	   item.setCode(0);
	    	   item.setDesc("修改成功");
	    	   ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "receiveAddress.html", "/pc/user/updPhone", "修改电话");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"修改电话:",
									e, "/pc/user/updPhone");
						}
					}
				});
	    	   return item.toJson();
	       }else{
	    	   item.setCode(-109);
	    	   item.setDesc("修改失败");
	       }
         
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改电话异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("修改电话异常! 异常信息:{0}",
					e.toString()), "user/updPhone");
		}
		
		return item.toJson();
	}
	
	@RequestMapping(value = "/checkMobile",produces = "text/html;charset=UTF-8")
	public  String checkMobile(String MobileCode,String ImgCode,String mobile,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			
			if (StringUtilsEX.IsNullOrWhiteSpace(MobileCode)) {
				item.setCode(-102);
				item.setDesc("手机验证码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(ImgCode)) {
				item.setCode(-103);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			
			String smss = RedisUserInfo.Get("I" + mobile);
            if (!MobileCode.equals(smss)) {
                item.setCode(-106);
                item.setDesc("短信验证码错误");
                return item.toJson();
            }
            
           String code= RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
           
           if (!ImgCode.equals(code)) {
               item.setCode(-106);
               item.setDesc("图片验证码错误");
               return item.toJson();
           }
           
          
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("验证手机异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("验证手机异常! 异常信息:{0}",
					e.toString()), "user/checkMobile");
		}
		
		return item.toJson();
	}
	
	
	@RequestMapping(value = "/checkPassPwd",produces = "text/html;charset=UTF-8")
	public  String checkPassPwd(String ImgCode,String PayPwd,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			
			if (StringUtilsEX.IsNullOrWhiteSpace(ImgCode)) {
				item.setCode(-103);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
            
           String code= RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
           
           if (!ImgCode.toLowerCase().equals(code.toLowerCase())) {
               item.setCode(-106);
               item.setDesc("图片验证码错误");
               return item.toJson();
           }
           
           Integer userid=SessionUtil.getSessionUserId(request);
           Users users=userService.selectByPrimaryKey(userid);
           if(users!=null){
        	   String paypwd=users.getPaypass();
        	   if(DEndecryptUtil.get_instances().passwordEncrypt(
        			   PayPwd).equals(paypwd)){
        		   item.setCode(0);
        		   return item.toJson();
        	   }else{
        		   item.setCode(-101);
        		   item.setDesc("支付密码错误");
        		   return item.toJson();
        	   }
           }
          
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("验证支付密码异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("验证支付密码异常! 异常信息:{0}",
					e.toString()), "user/checkPassPwd");
		}
		
		return item.toJson();
	}

	
	@RequestMapping(value = "/sendEmailForPwd",produces = "text/html;charset=UTF-8")
	public  String sendEmailForPwd(String imgCode,String SkipUrl,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			
			if (StringUtilsEX.IsNullOrWhiteSpace(imgCode)) {
				item.setCode(-103);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
            
           String code= RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
           
           if (!imgCode.equals(code)) {
               item.setCode(-106);
               item.setDesc("图片验证码错误");
               return item.toJson();
           }
           
           Integer userid=SessionUtil.getSessionUserId(request);
           Users users=userService.queryById(userid);
           if(users!=null){
        	   String email=users.getEmail();
        	   CommonUtils.sendEmail(email,"【中绿生活】", "http://localhost/member/userInfo/changeLoginPwd.html?type=1&setup=2");
           }
           
          
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("验证邮箱异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("验证支付密码异常! 异常信息:{0}",
					e.toString()), "user/sendEmailForPwd");
		}
		
		return item.toJson();
	}


	/**
	 * 修改密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updPwd",produces = "text/html;charset=UTF-8")
	public  String updPwd(String NewPwd,String NewPwdAgain,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (user==null || user.getId() <= 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(NewPwd)) {
				item.setCode(-101);
				item.setDesc("新密码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(NewPwdAgain)) {
				item.setCode(-102);
				item.setDesc("确认新密码不能为空！");
				return item.toJson();
			}
			if(!NewPwd.equals(NewPwdAgain)){
				item.setCode(-103);
				item.setDesc("两次密码不一致");
				return item.toJson();
			}
			
			Integer userid=SessionUtil.getSessionUserId(request);
			if(userid!=null){
				Users users=userService.queryById(userid);
				users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
						NewPwd));
				
				
				Accounts accounts=accountsService.queryByuserid(userid);
				accounts.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
						NewPwd));
	        
			   int temp=userService.updatePwd(users,accounts);
		       if(temp>0){
		    	   item.setCode(0);
		    	   item.setDesc("修改成功");
		    	   ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "findPwd.html", "/pc/user/updPwd", "修改密码");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"修改密码:",
										e, "/pc/user/updPwd");
							}
						}
					});
		    	  
		       }else{
		    	   item.setCode(-109);
		    	   item.setDesc("修改失败");
		    	 
		       }
			}			
         
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改密码异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("修改密码异常! 异常信息:{0}",
					e.toString()), "user/updPwd");
		}
		  return item.toJson();
		
	}
	
	
	/**
	 * 找回密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findPwd",produces = "text/html;charset=UTF-8")
	public  String findPwd(String userid,String NewPwd,String NewPwdAgain,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(NewPwd)) {
				item.setCode(-101);
				item.setDesc("新密码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(NewPwdAgain)) {
				item.setCode(-102);
				item.setDesc("确认新密码不能为空！");
				return item.toJson();
			}
			if(!NewPwd.equals(NewPwdAgain)){
				item.setCode(-103);
				item.setDesc("两次密码不一致");
				return item.toJson();
			}
			
		
			Users users=userService.queryById(StringUtilsEX.ToInt(userid));
			users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
					NewPwd));

			Accounts accounts=accountsService.queryByuserid(StringUtilsEX.ToInt(userid));
			accounts.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
					NewPwd));
	        
		   int temp=userService.updatePwd(users,accounts);
	       if(temp>0){
	    	   item.setCode(0);
	    	   item.setDesc("修改成功");
	    	   ExecutorService executorService=Executors.newCachedThreadPool();
	    	   SessionUser user=SessionState.GetCurrentUser();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "findPwd.html", "/pc/user/findPwd", "找回密码");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"找回密码:",
									e, "/pc/user/findPwd");
						}
					}
				});
	    	  
	       }else{
	    	   item.setCode(-109);
	    	   item.setDesc("修改失败");
	    	 
	       }
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("找回密码异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("修改密码异常! 异常信息:{0}",
					e.toString()), "user/findPwd");
		}
		  return item.toJson();
		
	}
	
	
	

	/**
	 * 绑定邮箱
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/bindEmail",produces = "text/html;charset=UTF-8")
	public  String BindEmail(String email,String mailcode,String imgcode,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (user==null || user.getId() <= 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(email)) {
				item.setCode(-101);
				item.setDesc("邮箱不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mailcode)) {
				item.setCode(-102);
				item.setDesc("邮箱验证码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(imgcode)) {
				item.setCode(-103);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			
			String smss = RedisUserInfo.Get("I" + email);
            if (!mailcode.equals(smss)) {
                item.setCode(-106);
                item.setDesc("邮箱验证码错误");
            }
            
           String code= RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
           
           if (!imgcode.equals(code)) {
               item.setCode(-106);
               item.setDesc("验证码错误");
               return item.toJson();
           }
           
           Integer userid=SessionUtil.getSessionUserId(request);
           Users users=userService.queryById(userid);
           users.setEmail(email);
           users.setIsemailcheck(true);
           
           Accounts accounts=accountsService.queryByuserid(userid);
           accounts.setEmail(email);
	       
		   int temp=userService.updatePwd(users, accounts);
	       if(temp>0){
	    	   item.setCode(0);
	    	   item.setDesc("修改成功");
	    	   ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "bindingemail.html", "/pc/user/bindEmail", "绑定邮箱");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"绑定邮箱:",
									e, "/pc/user/bindEmail");
						}
					}
				});
	       }else{
	    	   item.setCode(-109);
	    	   item.setDesc("修改失败");
	       }
         
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("绑定邮箱异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("绑定邮箱异常! 异常信息:{0}",
					e.toString()), "user/bindEmail");
		}
		
		return item.toJson();
	}


	/**
	 * 验证账户
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/checkAccount",produces = "text/html;charset=UTF-8")
	public  String checkAccount(String accName,String ImgCode,String userType,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(accName)) {
				item.setCode(-101);
				item.setDesc("账号名不能为空！");
				return item.toJson();
			}
			
			if (StringUtilsEX.IsNullOrWhiteSpace(ImgCode)) {
				item.setCode(-103);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			 String code= RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
	           
           if (!ImgCode.equals(code)) {
               item.setCode(-106);
               item.setDesc("图片验证码错误");
               return item.toJson();
           }
			
           Accounts accounts=accountsService.queryAccouts(accName,userType);
           if(accounts!=null){
        	   item.setCode(0);
        	   item.setData(accounts.getUserid());
           }else{
        	   item.setCode(-101);
        	   item.setDesc("该用户不存在");
           }
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("绑定邮箱异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("绑定邮箱异常! 异常信息:{0}",
					e.toString()), "user/bindEmail");
		}
		
		return item.toJson();
	}

	
	@RequestMapping(value = "/checkMobileCode",produces = "text/html;charset=UTF-8")
	public  String checkMobileCode(String MobileCode,String mobile,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			
			
			if (StringUtilsEX.IsNullOrWhiteSpace(MobileCode)) {
				item.setCode(-103);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			
			String smss = RedisUserInfo.Get("I" + mobile);
            if (!MobileCode.equals(smss)) {
                item.setCode(-106);
                item.setDesc("短信验证码错误");
                return item.toJson();
            }
            
          /*  Accounts accounts=accountsService.queryAccouts(MobileCode);
            if(accounts!=null){
         	   item.setCode(0);
         	   item.setData(accounts.getUserid());
            }
          */
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("验证手机异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("验证手机异常! 异常信息:{0}",
					e.toString()), "user/checkMobileCode");
		}
		
		return item.toJson();
	}
	
	@RequestMapping(value = "/checkEmailCode",produces = "text/html;charset=UTF-8")
	public  String checkEmailCode(String EmailCode,String email,String ch,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			
			
			if (StringUtilsEX.IsNullOrWhiteSpace(EmailCode)) {
				item.setCode(-103);
				item.setDesc("验证码不能为空！");
				return item.toJson();
			}
			
			String smss = RedisUserInfo.Get("I" + email);
            if (!EmailCode.equals(smss)) {
                item.setCode(-106);
                item.setDesc("邮箱验证码错误");
                return item.toJson();
            }
            
          /*  Accounts accounts=accountsService.queryAccouts(email);
            if(accounts!=null){
         	   item.setCode(0);
         	   item.setData(accounts.getUserid());
            }
          */
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("验证手机异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("验证手机异常! 异常信息:{0}",
					e.toString()), "user/checkEmailCode");
		}
		
		return item.toJson();
	}
	
	@RequestMapping(value = "/sendemail", produces = "text/html;charset=UTF-8")
    public  String sendEmail(String eamil,String ch) {
        BaseResult item = new BaseResult();
        try {
        	if (StringUtilsEX.IsNullOrWhiteSpace(eamil)) {
                item.setCode(-101);
                item.setDesc("邮箱不能为空！");
                return item.toJson();
            }
//           System.out.println(eamil);
            if(!StringUtilsEX.isChannelTypeExist(ch)){
    			item.setCode(-108);
    			item.setDesc("登录通道参数错误");
    			return item.toJson();
    		}
            String val = ProductNumUtil.getRandNum();
            String content="【因联商城】您好,您的本次验证码为"+val;
          
            boolean i=CommonUtils.sendEmail(eamil,"因联商城欢迎您，邀请您验证邮箱", content);
            if(i==true){
            	 RedisUserInfo.Set("I" + eamil, val, ConfigUtil.get_instances().getSmsCodeTmeOut());
//                 item.setData(val);
                 item.setCode(0);
                 
                 item.setDesc("获取验证码成功");
                 return item.toJson();
            }else{
            	item.setCode(-101);
            	item.setDesc("获取验证码失败");
            }
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取验证码异常：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("获取验证码异常! 异常信息:{0}",
					e.toString()), "user/sendEmail");
		}
        
        return item.toJson();
    }

/**
 * 礼品卡充值
 * @param cardnum  礼品卡号
 * @param cardpwd 礼品卡密码
 * @param phonecode 手机验证码
 * @param mobile 手机号
 * @param request
 * @return
 */
	@RequestMapping(value = "/chargecard",produces = "text/html;charset=UTF-8")
	public  String chargeCard(String cardnum,String cardpwd,String phonecode,String mobile,HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (user==null || user.getId() <= 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(cardnum)) {
				item.setCode(-101);
				item.setDesc("充值卡号不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(cardpwd)) {
				item.setCode(-102);
				item.setDesc("充值卡密码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(phonecode)) {
				item.setCode(-103);
				item.setDesc("手机验证码不能为空！");
				return item.toJson();
			}
			
			String smss = RedisUserInfo.Get("I" + mobile);
            if (!phonecode.equals(smss)) {
                item.setCode(-104);
                item.setDesc("短信验证码错误");
                return item.toJson();
            }
            
           Integer userid=user.getUserId();
		   int temp=giftcardService.giftcardRecharge(cardnum.trim(), cardpwd, userid, mobile);
		   if(temp==-1){
			   item.setCode(-105);
                item.setDesc("礼品卡错误");
                return item.toJson();
		   }
		   if(temp==-2){
			   item.setCode(-106);
                item.setDesc("礼品卡密码错误");
                return item.toJson();
		   }
		   if(temp==-3){
			   item.setCode(-107);
                item.setDesc("礼品卡已使用");
                return item.toJson();
		   }
	       if(temp>0){
	    	   item.setCode(0);
	    	   item.setDesc("充值成功");
	    	   ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cardcharge.html", "/user/chargecard", "礼品卡充值");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"礼品卡充值记录异常:",
									e, "/user/chargecard");
						}
					}
				});
	       }else{
	    	   item.setCode(-107);
	    	   item.setDesc("礼品卡充值失败");
	       }
         
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("系统错误");
			LogHandle.error(LogType.pc, MessageFormat.format("礼品卡充值异常! 异常信息:{0}",
					e.toString()), "user/chargecard");
		}
		
		return item.toJson();
	}
}

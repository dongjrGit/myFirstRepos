package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Receiveaddress;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ReceiveAddressService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/receiveraddress")
public class WapReceiverAddressController {

	
	@Autowired
	private ReceiveAddressService receiveAddressService;
	
	@Autowired
	private AreaService areaservice;
	
	@Autowired
	private CityServcie cityServcie;
	
	@Autowired
	private ProvinceServcice provinceServcice;
	
	@Autowired
	private OperaterecordsService operaterecordsService;
	
	/**
	 * 添加新的收货人地址
	 * 
	 * @param userId
	 * @param name
	 * @param mobile
	 * @param provinceCode
	 * @param CityCode
	 * @param AreaCode
	 * @param address
	 * @param isDefault
	 * @return
	 */
	@RequestMapping(value = "/addReceiverAddr", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addReceiverAddr(String name, String mobile,
			String provinceCode, String cityCode, String areaCode,String provinceName, String cityName, String areaName,
			String address, Integer isDefault, String ch,
			HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-101);
				item.setDesc("收货人姓名不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-102);
				item.setDesc("收货人手机号码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(address)) {
				item.setCode(-103);
				item.setDesc("详细地址不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(provinceCode)) {
				item.setCode(-106);
				item.setDesc("省编号不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(cityCode)) {
				item.setCode(-107);
				item.setDesc("市编号不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(areaCode)) {
				item.setCode(-108);
				item.setDesc("区编号不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-105);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(cityCode)
					|| StringUtilsEX.IsNullOrWhiteSpace(areaCode)
					|| StringUtilsEX.IsNullOrWhiteSpace(provinceCode)) {
				item.setCode(-104);
				item.setDesc("收货地址不能为空！");
				return item.toJson();
			}
			if (!mobile.matches("1\\d{10}")) {
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
			} else {
				String userId = sessionUser.getUserId().toString();				
				
				receiveAddressService.insert(userId, name, mobile,
						provinceCode, provinceName, cityCode, cityName,
						areaCode, areaName, address, isDefault);

				item.setCode(0);
				item.setDesc("添加地址成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "Address.html", "/api/wap/receiveraddress/addReceiverAddr", "添加新的收货人地址");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"添加新的收货人地址 异常信息:",
    								e, "/api/wap/receiveraddress/addReceiverAddr");
						}
					}
				});
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("添加收货地址异常！");
			}
			LogHandle
					.error(LogType.wap,
							MessageFormat.format("添加新的收货人地址异常! 异常信息:{0}",
									e.toString()), "receiver/addReceiverAddr");
		}

		return item.toJson();
	}

	/**
	 * 修改收货人地址
	 * 
	 * @param id
	 * @param name
	 * @param mobile
	 * @param provinceCode
	 * @param cityCode
	 * @param areaCode
	 * @param address
	 * @param isDefault
	 * @return
	 */
	@RequestMapping(value = "/updAddr", produces = "text/html;charset=UTF-8")
	public @ResponseBody String updAddr(String id, String name, String mobile,
			String province, String city, String area,String provincecode, String citycode, String areacode,
			String address, String isDefault, String ch,
			HttpServletRequest request ) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-105);
				item.setDesc("收件人id不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-101);
				item.setDesc("收货人姓名不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-102);
				item.setDesc("收货人手机号码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(province)) {
				item.setCode(-103);
				item.setDesc("省不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(city)) {
				item.setCode(-104);
				item.setDesc("市不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(area)) {
				item.setCode(-106);
				item.setDesc("区不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(address)) {
				item.setCode(-107);
				item.setDesc("详细地址不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(provincecode)) {
				item.setCode(-108);
				item.setDesc("省编码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(citycode)) {
				item.setCode(-109);
				item.setDesc("市编码不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(areacode)) {
				item.setCode(-110);
				item.setDesc("区编码不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-111);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			Receiveaddress receiveaddress = null;

			receiveaddress = receiveAddressService
					.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			receiveaddress.setIsdefault(StringUtilsEX.ToInt(isDefault));
			receiveaddress.setName(name);
			receiveaddress.setMobile(mobile);
			receiveaddress.setAddress(address);

			receiveaddress.setAreacode(areacode);
			receiveaddress.setAreaname(area);
			receiveaddress.setProvincecode(provincecode);
			receiveaddress.setProvincename(province);
			receiveaddress.setCitycode(citycode);
			receiveaddress.setCityname(city);
			receiveAddressService.updateReceiver(receiveaddress);
			item.setCode(0);
			item.setDesc("修改信息成功");
			ExecutorService executorService=Executors.newCachedThreadPool();
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						SessionUser user=SessionState.GetCurrentUser();
						operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "Address.html", "/api/wap/receiveraddress/updAddr", "修改收货人地址");
					} catch (Exception e) {
						LogHandle.error(LogType.OperateRecords,"修改收货人地址 异常信息:",
								e, "/api/wap/receiveraddress/updAddr");
					}
				}
			});
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("修改收货地址异常！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("修改收货人地址异常! 异常信息:{0}", e.toString()),
					"receiver/updAddr");
		}

		return item.toJson();
	}
	/**
	 * 设为默认地址
	 * 
	 * @param id 地址id
	 * @return
	 */
	@RequestMapping(value = "/updateDeAddr", produces = "text/html;charset=UTF-8")
	public @ResponseBody String updateDeAddr(String id,	HttpServletRequest request ) {
		BaseResult item = new BaseResult();
		try {
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser();
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} 
			if (StringUtilsEX.ToInt(id)<=0) {
				item.setCode(-101);
				item.setDesc("地址id参数错误 ！");
				return item.toJson();
			}
			
			Receiveaddress receiveaddress = new Receiveaddress();

			receiveaddress = receiveAddressService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			receiveaddress.setIsdefault(1);

			receiveAddressService.updateReceiver(receiveaddress);
			item.setCode(0);
			item.setDesc("修改成功");
			ExecutorService executorService=Executors.newCachedThreadPool();
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						SessionUser user=SessionState.GetCurrentUser();
						operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.买家中心.getValue(), user.getId(), user.getLoginName(), "Address.html", "/api/wap/receiveraddress/updateDeAddr", "修改收货地址");
					} catch (Exception e) {
						LogHandle.error(LogType.OperateRecords,"修改收货地址记录异常:",
								e, "/api/wap/receiveraddress/updateDeAddr");
					}
				}
			});
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("修改收货地址异常！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("修改收货人地址异常! 异常信息:{0}", e.toString()),
					"receiver/updateDeAddr");
		}

		return item.toJson();
	}

	/**
	 * 删除收货地址
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delReceiverAddr", produces = "text/html;charset=UTF-8")
	public @ResponseBody String delReceiverAddr(String id, String ch ) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("id不能为空");
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}

			int result = receiveAddressService.delByID(id);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
			} else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("删除收货地址异常！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("删除不需要的地址异常! 异常信息:{0}", e.toString()),
					"receiver/delReceiverAddr");
		}

		return item.toJson();
	}
}

package com.yinlian.api.app.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Receiveaddress;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ReceiveAddressService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/app/receiver")
public class ReceiverController {
	/**
	 * 日志输出的类
	 */
	//private static final Logger logger = LoggerFactory.getLogger(ReceiverController.class);

	@Autowired
	private ReceiveAddressService receiveAddressService;

	@Autowired
	private AreaService areaservice;

	@Autowired
	private CityServcie cityServcie;

	@Autowired
	private ProvinceServcice provinceServcice;

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
	public @ResponseBody String addReceiverAddr(String token, String name,
			String mobile, String provinceCode, String cityCode,
			String areaCode, String address, Integer isDefault, String ch,HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
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
				item.setDesc("手机号(mobile)格式错误！");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				String userId = sessionUser.getUserId().toString();

				Province province = provinceServcice.queryByCode(provinceCode);
				String provinceName = "";
				if (province != null) {
					provinceName = province.getName();
				} else {
					item.setCode(-111);
					item.setData("省编号不存在");
					return item.toJson();
				}

				City city = cityServcie.queryByCode(cityCode);
				String cityName = "";
				if (city != null) {
					cityName = city.getName();
				} else {
					item.setCode(-110);
					item.setData("市编号不存在");
					return item.toJson();
				}

				Area area = areaservice.queryByCode(areaCode);
				String areaName = "";
				if (area != null) {
					areaName = area.getName();
				} else {
					item.setCode(-109);
					item.setData("区编号不存在");
					return item.toJson();
				}

				receiveAddressService.insert(userId, name, mobile,
						provinceCode, provinceName, cityCode, cityName,
						areaCode, areaName, address, isDefault);

				item.setCode(0);
				item.setDesc("添加地址成功");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle
					.error(LogType.Api,"添加新的收货人地址异常! 异常信息:{0}",
									e, "receiver/addReceiverAddr");
		}

		return item.toJson();
	}

	/**
	 * 删除不需要的地址
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delReceiverAddr", produces = "text/html;charset=UTF-8")
	public @ResponseBody String delReceiverAddr(String id, String ch,HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
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
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"删除不需要的地址异常! 异常信息:{0}", e,
					"receiver/delReceiverAddr");
		}

		return item.toJson();
	}

	/**
	 * 查询每个顾客的收货人地址
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/selectAddrByUserId", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectAddrByUserId(String token, String ch,HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				String userid = sessionUser.getUserId().toString();
				List<Receiveaddress> list = null;

				list = receiveAddressService.queryAllByUserId(userid);

				item.setCode(0);
				item.setData(list);
				item.setDesc("查询成功");

			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(
					LogType.Api,"查询每个顾客的收货人地址异常! 异常信息:{0}",
							e, "receiver/selectAddrByUserId");
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
			String provinceCode, String cityCode, String areaCode,
			String address, String isDefault, String ch,HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-105);
				item.setDesc("收件人id(id)不能为空！");
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
			if (StringUtilsEX.IsNullOrWhiteSpace(provinceCode)) {
				item.setCode(-103);
				item.setDesc("详细地址(address)不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-112);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(cityCode)
					|| StringUtilsEX.IsNullOrWhiteSpace(areaCode)
					|| StringUtilsEX.IsNullOrWhiteSpace(address)) {
				item.setCode(-104);
				item.setDesc("收货地址不能为空！");
				return item.toJson();
			}
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号(mobile)格式错误！");
				return item.toJson();
			}
			Receiveaddress receiveaddress = null;

			receiveaddress = receiveAddressService
					.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			receiveaddress.setIsdefault(StringUtilsEX.ToInt(isDefault));
			receiveaddress.setName(name);
			receiveaddress.setMobile(mobile);
			receiveaddress.setAddress(address);
			String areaName = areaservice.queryByCode(areaCode).getName();
			String cityName = cityServcie.queryByCode(cityCode).getName();
			String provinceName = provinceServcice.queryByCode(provinceCode)
					.getName();
			receiveaddress.setAreacode(areaCode);
			receiveaddress.setAreaname(areaName);
			receiveaddress.setProvincecode(provinceCode);
			receiveaddress.setProvincename(provinceName);
			receiveaddress.setCitycode(cityCode);
			receiveaddress.setCityname(cityName);
			receiveAddressService.updateReceiver(receiveaddress);
			item.setCode(0);
			item.setDesc("修改信息成功");
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"修改收货人地址异常! 异常信息:{0}", e,
					"receiver/updAddr");
		}

		return item.toJson();
	}

	/**
	 * 修改收货人默认地址
	 * 
	 * @param token
	 * @param id
	 * @param isDefault
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/updateAddrDefault", produces = "text/html;charset=UTF-8")
	public @ResponseBody String updateAddrDefault(String token, String id,
			String ch, HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			}
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("收件人地址id参数错误！");
				return item.toJson();
			}

			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Receiveaddress receiveaddress = new Receiveaddress();
			receiveaddress = receiveAddressService
					.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			if (receiveaddress != null && receiveaddress.getId() > 0) {
				receiveaddress.setIsdefault(1);

				receiveAddressService.updateReceiver(receiveaddress);
				item.setCode(0);
				item.setDesc("修改默认地址成功");
			} else {
				item.setCode(-200);
				item.setDesc("修改默认地址失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改默认地址异常：" + e.getMessage());
			} else {
				item.setDesc("系统异常！");
			}
			LogHandle.error(LogType.Api,
					MessageFormat.format("修改默认地址异常! 异常信息:{0}", e),
					"/receiver/updateAddrDefault");
		}
		return item.toJson();
	}
}

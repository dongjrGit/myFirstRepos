package com.yinlian.api.wap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/getaddress")
public class WapGetAddressController {


	@Autowired
	private AreaService areaService;

	@Autowired
	private CityServcie cityService;

	@Autowired
	private ProvinceServcice provinceServcice;

	/**
	 * 查询全部的省
	 * 
	 * @return
	 */
	/**
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/selectAllProvice", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectAllProvice(String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			List<Province> list = provinceServcice.selectAll();
			item.setData(list);
			item.setCode(0);
			item.setDesc("查询全部省成功");
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"查询全部的省异常! 异常信息:{0}", e,
					"addr/selectAllProvice");
		}
		return item.toJson();
	}

	/**
	 * 查询每个省对应的所有市
	 * 
	 * @param proviceCode
	 * @return
	 */
	@RequestMapping(value = "/selectAllCity", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectAllCity(String proviceCode, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(proviceCode)) {
				item.setCode(-101);
				item.setDesc("省的编号不能为空");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			List<City> list = cityService.selectByProvinceCode(proviceCode);
			item.setData(list);
			item.setCode(0);
			item.setDesc("查询全部市成功");
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(
					LogType.wap,"查询每个省对应的市异常! 异常信息:{0}",
							e, "addr/selectAllCity");
		}
		return item.toJson();
	}

	/**
	 * 查询每个市对应的区
	 * 
	 * @param cityCode
	 * @return
	 */
	@RequestMapping(value = "/selectAllArea", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectAllArea(String cityCode, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(cityCode)) {
				item.setCode(-101);
				item.setDesc("市的编号(cityCode)不能为空");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			List<Area> list = areaService.selectByCityCode(cityCode);
			item.setData(list);
			item.setCode(0);
			item.setDesc("查询全部区成功");
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(
					LogType.wap,"查询每个市对应的区异常! 异常信息:{0}",
							e, "addr/selectAllArea");
		}
		return item.toJson();
	}
}

package com.yinlian.wssc.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.CriteriaHotCity;
import com.yinlian.wssc.web.po.Hotcity;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.HotCityService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/hotcity")
public class HotCityController {

	@Autowired
	private ProvinceServcice provinceServcice;

	@Autowired
	private CityServcie cityServcie;

	@Autowired
	private HotCityService hotCityService;

	/**
	 * 获取热门城市列表
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param province
	 * @param city
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public ReusltItem getList(String pageindex, String pagesize, String province, String city) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaHotCity criteria = new CriteriaHotCity();
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			if (!province.equals("-1")) {
				criteria.setPcode(province);
			}
			if (!city.equals("-1")) {
				criteria.setCode(city);
			}
			criteria.setOrderByClause("sort");
			PageBean pageBean = hotCityService.getHotCity(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("获取热门城市列表异常：" + e.getMessage());
			LogHandle.error(LogType.group, "获取热门城市列表异常{0}!", e, "/platform/hotcity/getList");
		}
		return item;
	}

	/**
	 * 删除热门城市
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delById")
	@ResponseBody
	public ReusltItem delById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			hotCityService.deleteHotCity(StringUtilsEX.ToInt(id));
			item.setCode(0);
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("删除热门城市异常：" + e.getMessage());
			LogHandle.error(LogType.group, "删除热门城市异常{0}!", e, "/platform/hotcity/delById");
		}
		return item;
	}

	/**
	 * 编辑热门城市
	 * 
	 * @param id
	 * @param province
	 * @param city
	 * @param sort
	 * @param name
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public ReusltItem edit(String id, String province, String city, String sort, String name,String imgurl) {
		ReusltItem item = new ReusltItem();
		try {
			Hotcity hotcity = new Hotcity();
			if (province != null || province != "") {
				hotcity.setPcode(province);
			}
			if (city != null || city != "") {
				if (hotCityService.selCity(city) != null) {
					item.setCode(-100);
					item.setDesc("该热门城市已存在");
					return item;
				}
				hotcity.setCode(city);
			}
			if (sort == null || sort == "") {
				hotcity.setSort(0);
			} else {
				hotcity.setSort(StringUtilsEX.ToInt(sort));
			}
			if (name != null || name != "") {
				hotcity.setName(name);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(imgurl)) {
				hotcity.setImgurl(imgurl);
			}
			if (StringUtilsEX.ToInt(id) > 0) {
				hotcity.setId(StringUtilsEX.ToInt(id));
				hotCityService.updateHotCity(hotcity);
				item.setCode(0);
			} else {
				hotCityService.insertHotCity(hotcity);
				item.setCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("编辑热门城市异常：" + e.getMessage());
			LogHandle.error(LogType.group, "编辑热门城市异常{0}!", e, "/platform/hotcity/edit");
		}
		return item;
	}

	/**
	 * 获取城市列表
	 * 
	 * @param type
	 * @param code
	 * @return
	 */
	@RequestMapping("/queryRegion")
	public @ResponseBody ReusltItem queryRegion(String type, String code) {
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
				List<City> cities = cityServcie.selectByProvinceCode(code);
				item.setCode(0);
				item.setData(cities);
				break;
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("异常信息：e" + e.getMessage());
		}
		return item;
	}

}

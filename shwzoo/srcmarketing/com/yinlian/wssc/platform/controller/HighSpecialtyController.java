package com.yinlian.wssc.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.CriteriaVHpSku;
import com.yinlian.wssc.web.po.Highspecialty;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.HighSpecialtyService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/hispe")
public class HighSpecialtyController {

	@Autowired
	private ProvinceServcice provinceServcice;

	@Autowired
	private CityServcie cityServcie;

	@Autowired
	private SpuService spuService;

	@Autowired
	private HighSpecialtyService highSpecialtyService;

	/**
	 * 获取优质特产列表
	 * 
	 * @param title
	 * @param pageindex
	 * @param pagesize
	 * @param type
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public ReusltItem getList(String title, String pageindex, String pagesize, String type) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaVHpSku criteria = new CriteriaVHpSku();
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			if (!StringUtilsEX.IsNullOrWhiteSpace(title)) {
				criteria.setName(title);
			}
			if (StringUtilsEX.ToInt(type) >= 0) {
				criteria.setType(StringUtilsEX.ToInt(type));
			}
			criteria.setOrderByClause("sort");
			PageBean pageBean = highSpecialtyService.getList(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("获取优质特产列表异常：" + e.getMessage());
			LogHandle.error(LogType.group, "获取优质特产列表异常{0}!", e, "/platform/hispe/getList");
		}
		return item;
	}

	/**
	 * 删除优质特产
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delById")
	@ResponseBody
	public ReusltItem delById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			highSpecialtyService.deleteHiSpeById(StringUtilsEX.ToInt(id));
			item.setCode(0);
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("删除优质特产异常：" + e.getMessage());
			LogHandle.error(LogType.group, "删除优质特产异常{0}!", e, "/platform/hispe/delById");
		}
		return item;
	}

	/**
	 * 编辑优质特产
	 * 
	 * @param id
	 * @param title
	 * @param imgurl
	 * @param sort
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public ReusltItem edit(String id, String title, String imgurl, String sort, String type, String kid,
			String province, String city) {
		ReusltItem item = new ReusltItem();
		try {
			Highspecialty highspecialty = new Highspecialty();
			if (!StringUtilsEX.IsNullOrWhiteSpace(title)) {
				highspecialty.setTitle(title);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(imgurl)) {
				highspecialty.setImgurl(imgurl);
			}
			if (sort == null || sort == "") {
				highspecialty.setSort(0);
			} else {
				highspecialty.setSort(StringUtilsEX.ToInt(sort));
			}
			if (StringUtilsEX.ToInt(type) >= 0) {
				highspecialty.setType(StringUtilsEX.ToInt(type));
			}
			if (StringUtilsEX.ToInt(kid) >= 0) {
				highspecialty.setKid(StringUtilsEX.ToInt(kid));
			}
			if (province != null || province != "") {
				highspecialty.setProvince(province);
			}
			if (city != null || city != "") {
				highspecialty.setCity(city);
			}
			if (StringUtilsEX.ToInt(type) >= 0) {
				highspecialty.setType(StringUtilsEX.ToInt(type));
			}
			if (StringUtilsEX.ToInt(id) > 0) {
				highspecialty.setId(StringUtilsEX.ToInt(id));
				highSpecialtyService.updateHiSpe(highspecialty);
				item.setCode(0);
			} else {
				highSpecialtyService.insertHiSpe(highspecialty);
				item.setCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("编辑优质特产异常：" + e.getMessage());
			LogHandle.error(LogType.group, "编辑优质特产异常{0}!", e, "/platform/hispe/edit");
		}
		return item;
	}

	/**
	 * 编辑优质特产状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	@RequestMapping("/setstate")
	@ResponseBody
	public ReusltItem setstate(String id, String state) {
		ReusltItem item = new ReusltItem();
		try {
			highSpecialtyService.setType(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(state));
			item.setCode(0);
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("编辑优质特产状态异常：" + e.getMessage());
			LogHandle.error(LogType.group, "编辑优质特产状态异常{0}!", e, "/platform/hispe/delById");
		}
		return item;
	}

	/**
	 * 模糊检索sku商品bySKU 名称
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/getBySkuName")
	public @ResponseBody BaseResult getBySkuName(String name) {
		BaseResult item = new BaseResult();
		try {
			item.setCode(0);
			item.setData(spuService.getBySkuName(name));
		} catch (Exception e) {
			LogHandle.error(LogType.Product, "模糊检索sku商品bySKU 名称异常:" + e.getMessage(), "platform/Spu/getBySkuName");
			item.setCode(-900);
			item.setDesc("异常：" + e.getMessage());
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

package com.yinlian.wssc.platform.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.P_Groupbuyinfo_Criteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Groupbuyinfo;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.service.GroupbuyinfoService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/dgroup")
public class GroupbuyinfoController {

	@Autowired
	private GroupbuyinfoService groupbuyinfoService;

	@Autowired
	private ShopService shopService;

	/**
	 * 编辑团购商品
	 * 
	 * @param id
	 * @param title
	 * @param imgurl
	 * @param cid
	 * @param price
	 * @param num
	 * @param state
	 * @param depict
	 * @param starttime
	 * @param endtime
	 * @param yprice
	 * @return
	 */
	@RequestMapping("/save")
	public @ResponseBody BaseResult save(String id, String title, String imgurl, String cid, String price, String num,
			String state, String depict, String starttime, String endtime, String yprice) {
		BaseResult item = new BaseResult();
		Groupbuyinfo po = new Groupbuyinfo();
		try {
			if (StringUtilsEX.isNotBlank(id))
				po.setId(StringUtilsEX.ToInt(id));
			if (StringUtilsEX.isNotBlank(cid))
				po.setCid(StringUtilsEX.ToInt(cid));
			po.setTitle(title);
			po.setDepict(depict);
			if (StringUtilsEX.isNotBlank(endtime))
				po.setEndtime(StringUtilsEX.ToDate(endtime));
			if (StringUtilsEX.isNotBlank(starttime))
				po.setStarttime(StringUtilsEX.ToDate(starttime));
			if (StringUtilsEX.isNotBlank(state))
				po.setState(StringUtilsEX.ToInt(state));
			po.setImgurl(imgurl);
			if (StringUtilsEX.isNotBlank(price))
				po.setPrice(StringUtilsEX.ToDouble(price));
			if (StringUtilsEX.isNotBlank(num))
				po.setNum(StringUtilsEX.ToInt(num));
			po.setAuditing(0);
			po.setIsower(0);
			if (StringUtilsEX.isNotBlank(yprice))
				po.setYprice(StringUtilsEX.ToDouble(yprice));
			if (po.getId() != null && po.getId() > 0) {
				groupbuyinfoService.update(po);
			} else {
				po.setCreatetime(new Date());
				Shop shop = shopService.getOwnedShop();
				if (shop == null) {
					item.setCode(-1);
					item.setDesc("请添加直营店铺！");
					return item;
				}
				po.setSid(shop.getId());
				groupbuyinfoService.add(po);
			}
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			e.printStackTrace();
			item.setDesc("保存团购异常：" + e.getMessage());
			LogHandle.error(LogType.group, "保存团购异常{0}!", e, "/platform/dgroup/save");
		}

		return item;
	}

	/**
	 * 获取团购商品列表
	 * 
	 * @param pagesize
	 * @param pageindex
	 * @param title
	 * @param cid
	 * @param startf
	 * @param startt
	 * @param endf
	 * @param endt
	 * @param state
	 * @param auditing
	 * @param sname
	 * @param isower
	 * @return
	 */
	@RequestMapping("/glist")
	@ResponseBody
	public ReusltItem glist(String pagesize, String pageindex, String title, String cid, String startf, String startt,
			String endf, String endt, String state, String auditing, String sname, Integer isower) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index <= 0 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			P_Groupbuyinfo_Criteria criteria = new P_Groupbuyinfo_Criteria();
			criteria.setIsower(isower);
			if (!StringUtilsEX.IsNullOrWhiteSpace(title)) {
				criteria.setTitle(title);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(startf)) {
				criteria.setStartFrom(StringUtilsEX.ToShortDate(startf));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(startt)) {
				criteria.setStartTo(StringUtilsEX.ToShortDate(startt));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endf)) {
				criteria.setEndFrom(StringUtilsEX.ToShortDate(endf));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endt)) {
				criteria.setEndTo(StringUtilsEX.ToShortDate(endt));
			}
			if (StringUtilsEX.ToInt(cid) > 0) {
				criteria.setCid(StringUtilsEX.ToInt(cid));
			}
			if (StringUtilsEX.ToInt(auditing) >= 0) {
				criteria.setAuditing(StringUtilsEX.ToInt(auditing));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(sname)) {
				criteria.setSname(sname);
			}
			criteria.setState(StringUtilsEX.ToInt(state));
			criteria.setOrderByClause("endtime");
			PageBean list = groupbuyinfoService.getList(criteria, index, size);
			item.setCode(0);
			item.setData(list.getBeanList());
			if (list.getTr() == null) {
				item.setMaxRow(0);
			} else {
				item.setMaxRow(list.getTr());
			}
			item.setPageIndex(list.getPc());
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("获取团购列表异常：" + e.getMessage());
			LogHandle.error(LogType.group, "获取团购列表异常{0}!", e, "/platform/dgroup/glist");
		}
		return item;
	}

	/**
	 * 更改上下架状态
	 * 
	 * @param id
	 * @param val
	 * @return
	 */
	@RequestMapping("/cstate")
	public @ResponseBody BaseResult cstate(Integer id, Integer state) {
		BaseResult item = new BaseResult();
		try {
			groupbuyinfoService.changestate(id, state);
		} catch (Exception e) {
			item.setCode(-900);
			e.printStackTrace();
			item.setDesc("状态改变异常：" + e.getMessage());
			LogHandle.error(LogType.group, "状态改变异常{0}!", e, "/platform/dgroup/cstate");
		}
		return item;
	}

	/**
	 * 删除团购
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/del")
	public @ResponseBody BaseResult del(Integer id) {
		BaseResult item = new BaseResult();
		try {
			groupbuyinfoService.del(id);
		} catch (Exception e) {
			item.setCode(-900);
			e.printStackTrace();
			item.setDesc("删除团购异常：" + e.getMessage());
			LogHandle.error(LogType.group, "删除团购异常{0}!", e, "/platform/dgroup/del");
		}
		return item;
	}

	/**
	 * 团购审核操作
	 * 
	 * @param id
	 * @param auditing
	 * @return
	 */
	@RequestMapping("/editaudit")
	@ResponseBody
	public ReusltItem editaudit(String id, String auditing) {
		ReusltItem item = new ReusltItem();
		try {
			groupbuyinfoService.editAudit(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(auditing));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			e.printStackTrace();
			item.setDesc("团购审核操作异常：" + e.getMessage());
			LogHandle.error(LogType.group, "团购审核操作异常{0}!", e, "/platform/dgroup/editaudit");
		}
		return item;
	}

}

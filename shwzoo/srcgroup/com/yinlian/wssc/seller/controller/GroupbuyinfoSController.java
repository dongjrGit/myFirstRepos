package com.yinlian.wssc.seller.controller;

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
import com.yinlian.wssc.web.service.GroupbuyinfoService;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/seller/dgroup")
public class GroupbuyinfoSController {
	@Autowired
	private GroupbuyinfoService groupbuyinfoService;

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
			po.setAuditing(1);
			po.setIsower(1);
			if (StringUtilsEX.isNotBlank(yprice))
				po.setYprice(StringUtilsEX.ToDouble(yprice));
			if (po.getId() != null && po.getId() > 0) {
				groupbuyinfoService.update(po);
			} else {
				po.setCreatetime(new Date());
				po.setSid(SessionState.GetCurrentUser().getShopid());
				groupbuyinfoService.add(po);
			}
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			e.printStackTrace();
			item.setDesc("保存团购异常：" + e.getMessage());
			LogHandle.error(LogType.group, "保存团购异常{0}!", e, "/seller/dgroup/save");
		}

		return item;
	}
	
	@RequestMapping("/glist")
	@ResponseBody
	public ReusltItem glist(String pagesize, String pageindex, String title, String cid, String startf, String startt,
			String endf, String endt) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index <= 0? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			P_Groupbuyinfo_Criteria criteria = new P_Groupbuyinfo_Criteria();
			criteria.setIsower(1);
			criteria.setShopid(SessionState.GetCurrentUser().getShopid());
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
	 * 更改状态
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

}

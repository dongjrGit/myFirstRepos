package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaIntegra;
import com.yinlian.wssc.web.po.IntegraproductWithBLOBs;
import com.yinlian.wssc.web.service.IntegraproductService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/integra")
public class IntegraproductController {

	@Autowired
	private IntegraproductService integraproductService;

	/**
	 * 编辑积分商品
	 * 
	 * @param id
	 * @param name
	 * @param sname
	 * @param price
	 * @param integral
	 * @param select_level
	 * @param packing
	 * @param stock
	 * @param count
	 * @param count1
	 * @param start
	 * @param end
	 * @param detaildesc
	 * @return
	 */
	@RequestMapping("/editjfsp")
	@ResponseBody
	public ReusltItem editjfsp(String id, String name, String sname, String price, String integral, String select_level,
			String packing, String stock, String count, String count1, String start, String end, String detaildesc) {
		ReusltItem item = new ReusltItem();
		try {
			IntegraproductWithBLOBs iwb = new IntegraproductWithBLOBs();
			iwb.setName(name);
			iwb.setSname(sname);
			if (!StringUtilsEX.IsNullOrWhiteSpace(price)) {
				iwb.setPrice(StringUtilsEX.ToDouble(price));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(integral)) {
				iwb.setIntegral(StringUtilsEX.ToInt(integral));
			}
			iwb.setUserlevel(StringUtilsEX.ToInt(select_level));
			iwb.setPacking(packing);
			if (!StringUtilsEX.IsNullOrWhiteSpace(stock)) {
				iwb.setStock(StringUtilsEX.ToInt(stock));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(count)) {
				iwb.setCount(StringUtilsEX.ToInt(count));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(count1)) {
				iwb.setCount1(StringUtilsEX.ToInt(count1));
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(start)) {
				item.setCode(-100);
				item.setDesc("请输入开始时间");
				return item;
			}
			iwb.setEndtime(StringUtilsEX.ToShortDate(start));
			if (StringUtilsEX.IsNullOrWhiteSpace(end)) {
				item.setCode(-101);
				item.setDesc("请输入结束时间");
				return item;
			}
			iwb.setStarttime(StringUtilsEX.ToShortDate(end));
			iwb.setContent(detaildesc);
			iwb.setCreatetime(new Date());
			if (StringUtilsEX.ToInt(id) >= 0) {
				iwb.setId(StringUtilsEX.ToInt(id));
				integraproductService.updateintepro(iwb);
				item.setCode(0);
			} else {
				integraproductService.insertintepro(iwb);
				item.setCode(0);
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加积分商品异常：" + e.getMessage());
			LogHandle.error(LogType.Product, MessageFormat.format("添加积分商品异常! 异常信息:{0}", e.getMessage()),
					"/platform/integra/editjfsp");
		}
		return item;
	}

	/**
	 * 删除积分商品
	 * 
	 * @return
	 */
	@RequestMapping("/deljfsp")
	@ResponseBody
	public ReusltItem deljfsp(String id) {
		ReusltItem item = new ReusltItem();
		try {
			integraproductService.delById(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setDesc("删除成功");
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除积分商品异常：" + e.getMessage());
			LogHandle.error(LogType.Product, MessageFormat.format("删除积分商品异常! 异常信息:{0}", e.getMessage()),
					"/platform/integra/deljfsp");
		}
		return item;
	}

	/**
	 * 获取积分商品
	 * 
	 * @param name
	 * @param start1
	 * @param end1
	 * @param start2
	 * @param end2
	 * @param pageindex
	 * @param pagesize
	 * @return
	 */
	@RequestMapping("/seljfsp")
	@ResponseBody
	public ReusltItem seljfsp(String name, String start1, String end1, String start2, String end2, String pageindex,
			String pagesize) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaIntegra criteria = new CriteriaIntegra();
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
				criteria.setName(name);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(start1)) {
				criteria.setStartFrom(StringUtilsEX.ToShortDate(start1));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(end1)) {
				criteria.setStartTo(StringUtilsEX.ToShortDate(end1));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(start2)) {
				criteria.setEndFrom(StringUtilsEX.ToShortDate(start2));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(end2)) {
				criteria.setEndTo(StringUtilsEX.ToShortDate(end2));
			}
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
			PageBean pageBean = integraproductService.getList(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取积分商品列表异常：" + e.getMessage());
			LogHandle.error(LogType.Product, MessageFormat.format("获取积分商品列表异常! 异常信息:{0}", e.getMessage()),
					"/platform/integra/seljfsp");
		}
		return item;
	}

}

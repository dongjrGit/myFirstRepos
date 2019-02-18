package com.yinlian.wssc.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.UserFinanceService;
import com.yinlian.wssc.web.util.CriteriaFinance;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/memberfinance")
public class MemberFinanceController {
	@Autowired
	private UserFinanceService userFinanceService;

	@RequestMapping("/getFinanceList")
	public ReusltItem getFinanceList(String type, String username, String starttime, String endtime, String pageindex,
			String pagesize, String buyername, String paytype, String number, String paynum, String moneybegin,
			String moneyend) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaFinance criteria = new CriteriaFinance();
			String typeslist = "";
			typeslist += CapitalChange_Type.支出.getValue() + ",";
			typeslist += CapitalChange_Type.收入.getValue() + ",";
			typeslist += CapitalChange_Type.充值.getValue() + ",";
			typeslist += CapitalChange_Type.退款返还.getValue() + ",";
			typeslist += CapitalChange_Type.后台管理添加.getValue();
			criteria.setTypes(typeslist.trim());
			if (!StringUtilsEX.IsNullOrWhiteSpace(buyername)) {
				criteria.setBuyername(buyername);
			}
			/*
			 * if(StringUtilsEX.ToInt(paytype)>0){
			 * criteria.setPaytype(StringUtilsEX.ToInt(paytype)); }
			 */
			if (!StringUtilsEX.IsNullOrWhiteSpace(paytype)) {
				paytype = paytype.substring(0, paytype.length() - 1);
				criteria.setPaytypes(paytype);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(number)) {
				criteria.setNumber(number);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(paynum)) {
				criteria.setPaynum(paynum);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(username)) {
				criteria.setUsername(username);
			}
			if (StringUtilsEX.ToInt(type) >= 0) {
				criteria.setType(StringUtilsEX.ToInt(type));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(starttime)) {
				criteria.setStart(StringUtilsEX.ToShortDate(starttime));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endtime)) {
				criteria.setEnd(StringUtilsEX.ToShortDate(endtime));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(moneybegin)) {
				criteria.setMoneybegin(StringUtilsEX.ToDecimal(moneybegin));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(moneyend)) {
				criteria.setMoneyend(StringUtilsEX.ToDecimal(moneyend));
			}
			criteria.setSort("desc");
			criteria.setOrderByClause("createtime");

			int index = 1;
			int size = 10;
			if (StringUtilsEX.ToInt(pageindex) > 0) {
				index = StringUtilsEX.ToInt(pageindex);
			}
			if (StringUtilsEX.ToInt(pagesize) > 0) {
				size = StringUtilsEX.ToInt(pagesize);
			}
			PageBean pageBean = userFinanceService.selectPage(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取消费列表错误：" + e.getMessage());
			} else {
				item.setDesc("系统异常！");
			}
			LogHandle.error(LogType.MemberCenterManage, "获取消费列表错误，错误详情：", e, "/memberfinance/getFinanceList");
		}

		return item;
	}
}

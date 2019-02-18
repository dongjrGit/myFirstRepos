package com.yinlian.wssc.platform.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.util.CriteriaGroupBuy;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/groupcount")
public class GroupCountController {
	
	@Autowired
	private GroupBuyOrderService groupBuyOrderService;
	
	/**
     * 分页查询团购订单统计
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryGroupOrder")
    public @ResponseBody ReusltItem queryGroupOrder(String page, String size, String shopid,
                                                String StartFrom, String StartTo,String stype) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(page) <= 0) {
            item.setCode(-101);
            item.setDesc("分页参数错误，pageindex：" + page);
            return item;
        }
        if (StringUtilsEX.ToInt(size) <= 0) {
            item.setCode(-102);
            item.setDesc("分页参数错误，pageindex：" + size);
            return item;
        }
       /* if (StringUtilsEX.ToInt(shopid) <= 0) {
             item.setCode(-103);
             item.setDesc("参数type错误，type：" + type);
             return item;
         }
         if (!StringUtilsEX.IsNullOrWhiteSpace(position)&&StringUtilsEX.ToInt(position)<0) {
             item.setCode(-105);
             item.setDesc("参数position错误，position：" + position);
             return item;
         }
         if (StringUtilsEX.ToInt(status) < 0) {
             item.setCode(-105);
             item.setDesc("参数status错误，status：" + status);
             return item;
         }*/
        try {
            //Criteria criteria = new Criteria();
        	CriteriaGroupBuy criteria=new CriteriaGroupBuy();
        	if(StringUtilsEX.ToInt(stype)==1){
	        	criteria.setStartFrom(StringUtilsEX.ToShortDate(StartFrom));
	        	criteria.setStartTo(StringUtilsEX.ToShortDate(StartTo));
        	}else if(StringUtilsEX.ToInt(stype)==2){
        		criteria.setEndFrom(new Date());
        	}else if(StringUtilsEX.ToInt(stype)==3){
        		criteria.setTime(StartFrom);
        	}else if(StringUtilsEX.ToInt(stype)==4){
        		String StartFromsub=StartFrom.substring(StartFrom.length()-1,StartFrom.length());
        		String StartFromyear=StartFrom.substring(0,StartFrom.length()-2);
        		switch (StartFromsub) {
				case "1":
					StartFrom=StartFromyear+"-01";
					StartTo=StartFromyear+"-03";
					break;
				case "2":
					StartFrom=StartFromyear+"-04";
					StartTo=StartFromyear+"-06";
					break;
				case "3":
					StartFrom=StartFromyear+"-07";
					StartTo=StartFromyear+"-09";
					break;
				case "4":
					StartFrom=StartFromyear+"-10";
					StartTo=StartFromyear+"-12";
					break;
				default:
					break;
				}
        		SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM");
        		criteria.setStartFrom(dataformat.parse(StartFrom));
	        	criteria.setStartTo(dataformat.parse(StartTo));
        	}else if(StringUtilsEX.ToInt(stype)==5){
        		criteria.setTime(StartFrom);
        	}
        	criteria.setShopid(StringUtilsEX.ToInt(shopid));
            PageBean pageBean = groupBuyOrderService.getGroupOrderByToTalPage(criteria,StringUtilsEX.ToInt(page),
            		StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询订单统计出现的异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Order, "分页查询团购卷统计出现的异常! 异常信息:",
					e, "/platform/groupcount/queryGroupOrder");
        }
        return item;
    }
    
    
    
    
    
    /**
     * 分页查询团购卷统计
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryGroupOrderDetail")
    public @ResponseBody ReusltItem queryGroupOrderDetail(String page, String size, String shopid,
                                                String StartFrom, String StartTo,String stype) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(page) <= 0) {
            item.setCode(-101);
            item.setDesc("分页参数错误，pageindex：" + page);
            return item;
        }
        if (StringUtilsEX.ToInt(size) <= 0) {
            item.setCode(-102);
            item.setDesc("分页参数错误，pageindex：" + size);
            return item;
        }
       /* if (StringUtilsEX.ToInt(shopid) <= 0) {
             item.setCode(-103);
             item.setDesc("参数type错误，type：" + type);
             return item;
         }
         if (!StringUtilsEX.IsNullOrWhiteSpace(position)&&StringUtilsEX.ToInt(position)<0) {
             item.setCode(-105);
             item.setDesc("参数position错误，position：" + position);
             return item;
         }
         if (StringUtilsEX.ToInt(status) < 0) {
             item.setCode(-105);
             item.setDesc("参数status错误，status：" + status);
             return item;
         }*/
        try {
            //Criteria criteria = new Criteria();
        	CriteriaGroupBuy criteria=new CriteriaGroupBuy();
        	if(StringUtilsEX.ToInt(stype)==1){
	        	criteria.setStartFrom(StringUtilsEX.ToShortDate(StartFrom));
	        	criteria.setStartTo(StringUtilsEX.ToShortDate(StartTo));
        	}else if(StringUtilsEX.ToInt(stype)==2){
        		criteria.setEndFrom(new Date());
        	}else if(StringUtilsEX.ToInt(stype)==3){
        		criteria.setTime(StartFrom);
        	}else if(StringUtilsEX.ToInt(stype)==4){
        		String StartFromsub=StartFrom.substring(StartFrom.length()-1,StartFrom.length());
        		String StartFromyear=StartFrom.substring(0,StartFrom.length()-2);
        		switch (StartFromsub) {
				case "1":
					StartFrom=StartFromyear+"-01";
					StartTo=StartFromyear+"-03";
					break;
				case "2":
					StartFrom=StartFromyear+"-04";
					StartTo=StartFromyear+"-06";
					break;
				case "3":
					StartFrom=StartFromyear+"-07";
					StartTo=StartFromyear+"-09";
					break;
				case "4":
					StartFrom=StartFromyear+"-10";
					StartTo=StartFromyear+"-12";
					break;
				default:
					break;
				}
        		SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM");
        		criteria.setStartFrom(dataformat.parse(StartFrom));
	        	criteria.setStartTo(dataformat.parse(StartTo));
        	}else if(StringUtilsEX.ToInt(stype)==5){
        		criteria.setTime(StartFrom);
        	}
        	criteria.setShopid(StringUtilsEX.ToInt(shopid));
            PageBean pageBean = groupBuyOrderService.getGroupOrderByPage(criteria,StringUtilsEX.ToInt(page),
            		StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询团购卷统计出现的异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "分页查询团购卷统计出现的异常! 异常信息:",
					e, "/platform/groupcount/queryGroupOrderDetail");
        }
        return item;
    }

	
	
}

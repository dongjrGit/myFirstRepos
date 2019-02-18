/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.seller.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.util.CriteriaDdtj;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 卖家店铺销量统计
 * @author admin
 *
 */
@RestController
@RequestMapping("/seller/tj")
public class DdXlTjController {

    
    @Autowired
    private OrderService         orderService;
    
    /**
     * 订单日统计
     * @param page
     * @param size
     * @param sellerid
     * @param type
     * @param zy
     * @param datef
     * @param datet
     * @return
     */
    @RequestMapping("/getddtjDay")
    public @ResponseBody ReusltItem getzyddtjDay(String page, String size, String sellerid,String type,String zy,String datef,String datet) {
        ReusltItem item = new ReusltItem();
        try {
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
  	      SessionUser user = SessionState.GetCurrentUser();

  	       CriteriaDdtj criteria=new CriteriaDdtj();
  	       criteria.setId(user.getShopid());
	       criteria.setType(type);
	       criteria.setZy(zy);
	       criteria.setDatef(StringUtilsEX.ToShortDate(datef));
	       criteria.setDatet(StringUtilsEX.ToShortDate(datet));
	       PageBean  pageBean=orderService.selectDpOrderTjByPage(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
	      item.setCode(0);
          item.setData(pageBean.getBeanList());
          item.setMaxRow(pageBean.getTr());
          item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("订单日统计异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
	       LogHandle.error(LogType.Order,"订单日统计异常! 异常信息:{0}", e,
	                "/seller/tj/getddtjDay");
		}
        return item;
    }
    
    /**
     * 订单周统计
     * @param page
     * @param size
     * @param sellerid
     * @param type
     * @param zy
     * @return
     */
    @RequestMapping("/getddtjWeek")
    public @ResponseBody ReusltItem getddtjWeek(String page, String size, String sellerid,String type,String datef,String datet) {
        ReusltItem item = new ReusltItem();
        try {
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
  	      SessionUser user = SessionState.GetCurrentUser();

  	       CriteriaDdtj criteria=new CriteriaDdtj();
  	       criteria.setId(user.getShopid());
	       criteria.setType(type);
	       criteria.setDatef(StringUtilsEX.ToShortDate(datef));
	       criteria.setDatet(StringUtilsEX.ToShortDate(datet));
	    	PageBean  pageBean=orderService.selectDpOrderTjByPage(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
	 	    item.setCode(0);
	        item.setData(pageBean.getBeanList());
	        item.setMaxRow(pageBean.getTr());
	        item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("订单周统计异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
	         LogHandle.error(LogType.Order,"订单周统计异常! 异常信息:{0}", e,
	                "/seller/tj/getddtjWeek");
		}
        
        return item;
    }
    
    /**
     * 按月,季度,年获取订单统计
     * @param page
     * @param size
     * @param sellerid
     * @param type
     * @param zy
     * @param datef
     * @return
     */
    @RequestMapping("/getddtj")
    public @ResponseBody ReusltItem getzyddtj(String page, String size, String sellerid,String type,String zy,String datef) {
        ReusltItem item = new ReusltItem();
        try {
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
   	 	SessionUser user = SessionState.GetCurrentUser();

   	       CriteriaDdtj criteria=new CriteriaDdtj();
   	       criteria.setId(user.getShopid());
 	       criteria.setType(type);
 	       criteria.setZy(zy);
 	       String datet=null;
 	       String _datef=null;
 	       if(type.equals("3")){
 	    	  _datef=datef+"-01";
 	    	   int month=Integer.parseInt(new SimpleDateFormat("MM").format(StringUtilsEX.ToShortDate(_datef)));
 	    	   int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(StringUtilsEX.ToShortDate(_datef)));
 	    	   if(month==4||month==6||month==9||month==11){
 		    	    datet=datef+"-30";
	    	   }else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
	    		    datet=datef+"-31";   
	    	   }else if(month==2){
	    	      if(year%400==0||(year %4==0&&year%100!=0))
	    	    	  datet=datef+"-29";
	    	      else 
	    	    	  datet=datef+"-28";
	    	   }     
 	       }else if(type.equals("4")){
 	    	   String year=datef.substring(0,4); 
 	    	   String t=datef.substring(5, 6);
 	    	   if(t.equals("1")){
 	    		   
 	    		  _datef=year+"-01"+"-01";
 	    		  datet=year+"-03"+"-31";
 	    		  
 	    	   }else if(t.equals("2")){
 	    		   
 	    		  _datef=year+"-04"+"-01";
 	    		 datet=year+"-06"+"-30";
 	    		 
 	    	   }else if(t.equals("3")){
 	    		   
 	    		  _datef=year+"-07"+"-01";
 	    		 datet=year+"-09"+"-30";
 	    		 
 	    	   }else if(t.equals("4")){
 	    		   
 	    		  _datef=year+"-10"+"-01";
 	    		 datet=year+"-12"+"-31";
 	    		 
 	    	   }
 	    		   
 	       }else if(type.equals("5")){
 	    	   _datef=datef+"-01"+"-01";
 	    	   datet=datef+"-12"+"-31";
 	       }
 	       criteria.setDatef(StringUtilsEX.ToShortDate(_datef));
 	       criteria.setDatet(StringUtilsEX.ToShortDate(datet));
 	       criteria.setDatem(datef);
 	       PageBean  pageBean=orderService.selectDpOrderTjByPage(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
	 	   item.setCode(0);
	       item.setData(pageBean.getBeanList());
	       item.setMaxRow(pageBean.getTr());
	       item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("订单统计异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
	        LogHandle.error(LogType.Order,"订单统计异常! 异常信息:{0}", e,
	                "/seller/tj/getddtj");
		}
        return item;
    }
    
}

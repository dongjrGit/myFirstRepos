package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.SyShopDto;
import com.yinlian.wssc.web.dto.SySpuDto;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 卖家店铺首页销量统计
 * @author admin
 *
 */
@RestController
@RequestMapping("/seller/createChart")
public class SellerSyTjController {
	

	@Autowired
	private  OrderdetailService    orderdetailService;
	


	/**
	 * 卖家商品统计
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/getSaleSKU")
	public ReusltItem getSaleSKUZ(String page,String size,String type,HttpServletRequest request){
		ReusltItem item=new ReusltItem();
		try{
			Integer userid=SessionUtil.getSessionUserId(request);
			Platfrom_SYCriteria criteria=new Platfrom_SYCriteria();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	SimpleDateFormat shortformatter = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtilsEX.ToInt(type)==2){
				Calendar calendar = Calendar.getInstance();
		    	String end = formatter.format(calendar.getTime());
		    	calendar.add(Calendar.DAY_OF_MONTH, -6);
		    	String start = shortformatter.format(calendar.getTime());
		    	criteria.setBegintime(start+" 00:00:00");
		    	criteria.setEndtime(end);
		    	criteria.setSellerid(userid);
			}else if(StringUtilsEX.ToInt(type)==3){
				
				Calendar calendar = Calendar.getInstance();
		    	String end = formatter.format(calendar.getTime());
		    	calendar.add(Calendar.MONTH, 0);
		    	calendar.set(Calendar.DAY_OF_MONTH,1);
		    	String start = shortformatter.format(calendar.getTime());
		    	criteria.setBegintime(start+" 00:00:00");
		    	criteria.setEndtime(end);
		    	criteria.setSellerid(userid); 	
		    	
			}
			
			List<SySpuDto>  list=orderdetailService.selectSellerSaleasCount(criteria);
	    	 item.setData(list);
			
	    	
		}	
		catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询商品销售统计异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product,
					"查询商品销售概况统计异常! 异常信息:{0}", e,
					"/seller/syCreateCharts/getSaleSKU");
		}
		return item;
		
	}
	
	
	/**
	 * 卖家订单销售统计
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/getSaleOrder")
	public ReusltItem getSaleOrderZ(HttpServletRequest request){
		ReusltItem item=new ReusltItem();
		try{
			List<SyShopDto> list=new ArrayList<SyShopDto>();
			SessionUser  user=SessionUtil.getSessionUser(request);
			Integer shopid=user.getShopid();
			Platfrom_SYCriteria criteria=new Platfrom_SYCriteria();
			criteria.setShopid(shopid);	
			
			Calendar calendar = Calendar.getInstance();
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	SimpleDateFormat shortformatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String start=shortformatter.format(calendar.getTime());
	    	criteria.setBegintime(start);
	    	SyShopDto dto=orderdetailService.selectSellerCountDay(criteria);
	    	if(dto.getOrderacount()==null){
	    		dto.setOrderacount(0.0f);
	    	}
	    	if(dto.getOrdercount()==null){
	    		dto.setOrdercount(0);
	    	}
	    	list.add(dto);
	    
	    	
	    	String end1 = formatter.format(calendar.getTime());
	    	calendar.add(Calendar.DAY_OF_MONTH, -6);
	    	String start1 = shortformatter.format(calendar.getTime());
	    	criteria.setBegintime(start1+" 00:00:00");
	    	criteria.setEndtime(end1);
			SyShopDto dto1=orderdetailService.selectSellerCount(criteria);
			if(dto1.getOrderacount()==null){
				dto1.setOrderacount(0.0f);
	    	}
	    	if(dto1.getOrdercount()==null){
	    		dto1.setOrdercount(0);
	    	}
	    	list.add(dto1);
	    	
	    	
	    	String end2 = formatter.format(calendar.getTime());
	    	calendar.add(Calendar.MONTH, 0);
	    	calendar.set(Calendar.DAY_OF_MONTH,1);
	    	String start2 = shortformatter.format(calendar.getTime());
	    	criteria.setBegintime(start2+" 00:00:00");
	    	criteria.setEndtime(end2);
	    	SyShopDto dto2=orderdetailService.selectSellerCount(criteria);
	    	if(dto2.getOrderacount()==null){
	    		dto2.setOrderacount(0.0f);
	    	}
	    	if(dto2.getOrdercount()==null){
	    		dto2.setOrdercount(0);
	    	}
	    	list.add(dto2);
	    	
	    	item.setData(list);
		}
		catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询订单销售概况统计异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"查询订单销售概况统计异常! 异常信息:{0}", e,
					"/seller/createChart/getSaleOrder");
		}
		return item;
		
	}

}

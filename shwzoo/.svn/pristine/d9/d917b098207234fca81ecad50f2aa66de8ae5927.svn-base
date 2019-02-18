package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.SyShopDto;
import com.yinlian.wssc.web.dto.SySpuDto;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/platform/syCreateCharts")
public class SyTjController {

	
	@Autowired
	private  OrderdetailService    orderdetailService;
	


	/**
	 * 平台商品统计
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/getSaleSKUZ")
	public ReusltItem getSaleSKUZ(String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			Calendar calendar = Calendar.getInstance();
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	SimpleDateFormat shortformatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String end = formatter.format(calendar.getTime());
	    	calendar.add(Calendar.DAY_OF_MONTH, -6);
	    	String start = shortformatter.format(calendar.getTime());
	    	
	    	Platfrom_SYCriteria criteria=new Platfrom_SYCriteria();
	    	criteria.setBegintime(start+" 00:00:00");
	    	criteria.setEndtime(end);
			
	    	List<SySpuDto>  list=orderdetailService.selectSaleasCount(criteria);
	    	 item.setData(list);
	    	
		}	

		catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询商品销售统计出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product,"查询商品销售概况统计异常! 异常信息:{0}", e,
					"/platform/syCreateCharts/getSaleSKU");
		}
		return item;
		
	}
	
	
	/**
	 * 平台商品统计
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/getSaleSKUM")
	public ReusltItem getSaleSKUM(String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			Calendar calendar = Calendar.getInstance();
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	SimpleDateFormat shortformatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String end = formatter.format(calendar.getTime());
	    	calendar.add(Calendar.MONTH, 0);
	    	calendar.set(Calendar.DAY_OF_MONTH,1);
	    	String start = shortformatter.format(calendar.getTime());
	    	
	    	Platfrom_SYCriteria criteria=new Platfrom_SYCriteria();
	    	criteria.setBegintime(start+" 00:00:00");
	    	criteria.setEndtime(end);
			
	    	List<SySpuDto>  list=orderdetailService.selectSaleasCount(criteria);
	    	 item.setData(list);
		}	

		catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询商品销售概况统计出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product,"查询商品销售概况统计异常! 异常信息:{0}", e,
					"/platform/syCreateCharts/getSaleSKUM");
		}
		return item;
		
	}
	
	/**
	 * 平台销售概况统计
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/getSaleOrderZ")
	public ReusltItem getSaleOrderZ(String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			Calendar calendar = Calendar.getInstance();
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	SimpleDateFormat shortformatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String end = formatter.format(calendar.getTime());
	    	calendar.add(Calendar.DAY_OF_MONTH, -6);
	    	String start = shortformatter.format(calendar.getTime());
	    	
	    	Platfrom_SYCriteria criteria=new Platfrom_SYCriteria();
	    	criteria.setBegintime(start+" 00:00:00");
	    	criteria.setEndtime(end);
			
	    	List<SyShopDto>  list=orderdetailService.selectShopSaleasCount(criteria);
	    	 item.setData(list);
		}
		catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询订单销售概况统计出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"查询订单销售概况统计异常! 异常信息:{0}", e,
					"/platform/syCreateCharts/getSaleOrder");
		}
		return item;
		
	}
	
	/**
	 * 平台销售概况统计
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/getSaleOrderM")
	public ReusltItem getSaleOrderM(String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			Calendar calendar = Calendar.getInstance();
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	SimpleDateFormat shortformatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String end = formatter.format(calendar.getTime());
	    	calendar.add(Calendar.MONTH, 0);
	    	calendar.set(Calendar.DAY_OF_MONTH,1);
	    	String start = shortformatter.format(calendar.getTime());
	    	
	    	Platfrom_SYCriteria criteria=new Platfrom_SYCriteria();
	    	criteria.setBegintime(start+" 00:00:00");
	    	criteria.setEndtime(end);
			
	    	List<SyShopDto>  list=orderdetailService.selectShopSaleasCount(criteria);
	    	 item.setData(list);
		}
		catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询订单销售概况统计出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"查询订单销售概况统计异常! 异常信息:{0}", e,
					"/platform/syCreateCharts/getSaleOrder");
		}
		return item;
		
	}
	
	
}

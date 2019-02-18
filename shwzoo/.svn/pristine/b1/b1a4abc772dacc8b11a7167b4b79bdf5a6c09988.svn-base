package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityHistory;
import com.yinlian.Enums.PackageTypeEnum;
import com.yinlian.Enums.SpikeTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.service.AnalysisReportService;
import com.yinlian.wssc.web.util.CriteriaCouponDetail;
import com.yinlian.wssc.web.util.CriteriaOrder;
import com.yinlian.wssc.web.util.CriteriaOrderDetails;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/platform/analysis")
public class AnalysisController {

	@Autowired
	private AnalysisReportService analysisReportService;
	
	/**
	 * 平台销售概况统计
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/getSalesAnalysis")
	public ReusltItem getSalesAnalysis(String starttime,String endtime,String shopid){
		ReusltItem item=new ReusltItem();
		try{
			CriteriaOrder criteria=new CriteriaOrder();
			if(StringUtilsEX.ToInt(shopid)>0){
				criteria.setShopid(StringUtilsEX.ToInt(shopid));
			}
			criteria.setAddbegin(StringUtilsEX.ToShortDate(starttime));
			criteria.setAddend(StringUtilsEX.ToShortDate(endtime));			
			item.setData(analysisReportService.getSalesAnalysis(criteria));
		}
		catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询销售概况统计出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.analysis,"查询销售概况统计异常! 异常信息:{0}", e,
					"/platform/analysis/getSalesAnalysis");
		}
		return item;
		
	}
	
	/**
	 * 销售明细
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @param skuid
	 * @return
	 */
	@RequestMapping("/SalesDetails")
	public ReusltItem SalesDetails(String starttime,String endtime,String shopid,
			String skuid,String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			CriteriaOrderDetails criteria=new CriteriaOrderDetails();
			if(StringUtilsEX.ToInt(shopid)>0){
				criteria.setShopid(StringUtilsEX.ToInt(shopid));
			}
			criteria.setAddbegin(StringUtilsEX.ToShortDate(starttime));
			criteria.setAddend(StringUtilsEX.ToShortDate(endtime));	
			if(StringUtilsEX.ToInt(skuid)>0){
				criteria.setShuid(StringUtilsEX.ToInt(skuid));
			}
			if(StringUtilsEX.ToInt(page)<=0 || StringUtilsEX.ToInt(size)<=0){
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			item.setData(analysisReportService.getSalesDetails(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size),item));
		}
		catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询销售明细异常出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.analysis,"查询销售明细异常! 异常信息:{0}", e,
					"/platform/analysis/SalesDetails");
		}
		return item;
		
	}
	
	/**
	 * 客户区域分析
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @param skuid
	 * @return
	 */
	@RequestMapping("/getbuyerArea")
	public ReusltItem getbuyerArea(String starttime,String endtime,String shopid,
			String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			CriteriaOrder criteria=new CriteriaOrder();
			if(StringUtilsEX.ToInt(shopid)>0){
				criteria.setShopid(StringUtilsEX.ToInt(shopid));
			}
			criteria.setAddbegin(StringUtilsEX.ToShortDate(starttime));
			criteria.setAddend(StringUtilsEX.ToShortDate(endtime));	
			if(StringUtilsEX.ToInt(page)<=0 || StringUtilsEX.ToInt(size)<=0){
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			item.setData(analysisReportService.getbuyerArea(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size),item));
		}
		catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询 客户区域分析异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.analysis,
					MessageFormat.format("查询 客户区域分析异常! 异常信息:{0}", e.toString()),
					"/platform/analysis/getbuyerArea");
		}
		return item;
		
	}
	
	/**
	 * 客户明细
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @param skuid
	 * @return
	 */
	@RequestMapping("/getbuyerDetail")
	public ReusltItem getbuyerDetail(String starttime,String endtime,String shopid,
			String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			if(StringUtilsEX.ToInt(page)<=0 || StringUtilsEX.ToInt(size)<=0){
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaOrder criteria=new CriteriaOrder();
			if(StringUtilsEX.ToInt(shopid)>0){
				criteria.setShopid(StringUtilsEX.ToInt(shopid));
			}
			criteria.setAddbegin(StringUtilsEX.ToShortDate(starttime));
			criteria.setAddend(StringUtilsEX.ToShortDate(endtime));	
			item.setData(analysisReportService.getbuyerDetail(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size),item));
		}
		catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询客户明细异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.analysis,
					MessageFormat.format("查询客户明细异常! 异常信息:{0}", e.toString()),
					"/platform/analysis/getbuyerDetail");
		}
		return item;
		
	}
	
	/**
	 * 优惠卷数据分析
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @param skuid
	 * @return
	 */
	@RequestMapping("/getCouponDetail")
	public ReusltItem getCouponDetail(String starttime,String endtime,String shopid,String num,
			String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			if(StringUtilsEX.ToInt(page)<=0 || StringUtilsEX.ToInt(size)<=0){
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaCouponDetail criteria=new CriteriaCouponDetail();
			if(StringUtilsEX.ToInt(shopid)>0){
				criteria.setShopid(StringUtilsEX.ToInt(shopid));
			}
			criteria.setAddbegin(StringUtilsEX.ToShortDate(starttime));
			criteria.setAddend(StringUtilsEX.ToShortDate(endtime));	
			criteria.setNum(num);
			criteria.setActivityType(ActivityHistory.优惠卷.getValue());
			item.setData(analysisReportService.getCouponDetail(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size),item));
		}
		catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询优惠卷数据异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.analysis,
					MessageFormat.format("查询优惠卷数据异常! 异常信息:{0}", e.toString()),
					"/platform/analysis/getCouponDetail");
		}
		return item;
		
	}
	
	/**
	 * 组合商品数据分析
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @param skuid
	 * @return
	 */
	@RequestMapping("/getPackageDetail")
	public ReusltItem getPackageDetail(String starttime,String endtime,String shopid,String num,
			String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			if(StringUtilsEX.ToInt(page)<=0 || StringUtilsEX.ToInt(size)<=0){
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaCouponDetail criteria=new CriteriaCouponDetail();
			if(StringUtilsEX.ToInt(shopid)>0){
				criteria.setShopid(StringUtilsEX.ToInt(shopid));
			}
			criteria.setAddbegin(StringUtilsEX.ToShortDate(starttime));
			criteria.setAddend(StringUtilsEX.ToShortDate(endtime));	
			criteria.setNum(num);
			criteria.setActivityType(PackageTypeEnum.组合商品.getValue());
			item.setData(analysisReportService.getPackageDetail(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size),item));
		}
		catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询组合商品数据异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.analysis,"查询组合商品数据异常! 异常信息:{0}", e,
					"/platform/analysis/getPackageDetail");
		}
		return item;
		
	}
	
	
	
	/**
	 * 闪购数据分析
	 * @param starttime
	 * @param endtime
	 * @param shopid
	 * @param skuid
	 * @return
	 */
	@RequestMapping("/getspikeSG")
	public ReusltItem getspikeSG(String starttime,String endtime,String shopid,String num,
			String page,String size){
		ReusltItem item=new ReusltItem();
		try{
			if(StringUtilsEX.ToInt(page)<=0 || StringUtilsEX.ToInt(size)<=0){
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaCouponDetail criteria=new CriteriaCouponDetail();
			if(StringUtilsEX.ToInt(shopid)>0){
				criteria.setShopid(StringUtilsEX.ToInt(shopid));
			}
			criteria.setAddbegin(StringUtilsEX.ToShortDate(starttime));
			criteria.setAddend(StringUtilsEX.ToShortDate(endtime));	
			criteria.setNum(num);
			criteria.setSpikeType(SpikeTypeEnum.闪购.getValue());
			criteria.setActivityType(ActivityHistory.闪购.getValue());
			item.setData(analysisReportService.getSpikeSG(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size),item));
		}
		catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询闪购数据异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.analysis,"查询闪购数据异常! 异常信息:{0}", e,
					"/platform/analysis/getspikeSG");
		}
		return item;
		
	}
}

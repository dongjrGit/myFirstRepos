package com.yinlian.wssc.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yinlian.wssc.web.util.CriteriaMemberPoints;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/memberPoints")
public class MemberPointsController {
	
	@Autowired
	private  PointsRecordService    pointsRecordService;
	 
	 /**
     * 积分查询
     * @param request
     * @return
     */
    @RequestMapping("/getpointsList")
    public ReusltItem  points(String pageindex,String pagesize,String buyerid,String type,String starttime,String endtime){
    	
    	ReusltItem item = new ReusltItem();
    	try{
			Integer page1 = StringUtilsEX.ToIntNull(pageindex);
			Integer size11 = StringUtilsEX.ToIntNull(pagesize);
			if (page1 == null || page1 <= 0) {
				page1 = 1;
			}
			if (size11 == null || size11 <= 0) {
				size11 = 10;
			}
			
			CriteriaMemberPoints criteria=new CriteriaMemberPoints();
			criteria.setType(StringUtilsEX.ToInt(type));
			
			criteria.setStarttime(StringUtilsEX.ToShortDate(starttime));
			criteria.setEndtime(StringUtilsEX.ToShortDate(endtime));
			criteria.setBuyerid(StringUtilsEX.ToInt(buyerid));
			criteria.setOrderByClause("CreateTime");
			criteria.setSort("desc");
			PageBean list = pointsRecordService.PlatformpointListByPage(criteria, page1, size11);
			item.setData(list.getBeanList());
			if (list.getTr() == null) {
				item.setMaxRow(0);
			} else {
				item.setMaxRow(list.getTr());
			}
			item.setPageIndex(list.getPc());
			item.setPageSize(list.getPs());
			
    	}catch(Exception e){
    		item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询积分异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pc, "查询订单异常! 异常信息:{0}", e,
					"point/getpoints");
    	}
    	return item;
    }
}

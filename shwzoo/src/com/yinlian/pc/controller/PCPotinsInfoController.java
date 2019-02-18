package com.yinlian.pc.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Pc_PointsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 积分
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/pc/point")
public class PCPotinsInfoController {

	
	@Autowired
	private  PointsRecordService    pointsRecordService;
	 
	@Autowired
	private UserService userService;
	
	
	 /**
     * 积分查询
     * @param request
     * @return
     */
    @RequestMapping("/getpoints")
    public @ResponseBody  String  points(String type,String time,String page,String size,String ch,HttpServletRequest request){
    	
    	ReusltItem item = new ReusltItem();
    	
    	try{
    		
			Integer userid=SessionUtil.getSessionUserId(request);
			if(userid!=null){
				Integer page1 = StringUtilsEX.ToIntNull(page);
				Integer size11 = StringUtilsEX.ToIntNull(size);
				if (page1 == null || page1 <= 0) {
					page1 = 1;
				}
				if (size11 == null || size11 <= 0) {
					size11 = 10;
				}
				
				Pc_PointsCriteria criteria=new Pc_PointsCriteria();
				criteria.setUserid(userid);
				criteria.setType(type);
				criteria.setOrderByClause("CreateTime");
				criteria.setSort("desc");
				
				
				if(StringUtilsEX.ToInt(time)==1){
					
        			//这三个月记录
        			Calendar calendar = Calendar.getInstance();
                	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	String end = formatter.format(calendar.getTime());
                	calendar.add(Calendar.MONTH, -3);
                	String start = formatter.format(calendar.getTime());
                	criteria.setBegintime(start);
                	criteria.setEndtime(end);
                	PageBean list = pointsRecordService.pointListByPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
    				item.setData(list.getBeanList());
    				if (list.getTr() == null) {
    					item.setMaxRow(0);
    				} else {
    					item.setMaxRow(list.getTr());
    				}
    				item.setPageIndex(list.getPc());
    				item.setPageSize(list.getPs());
    				item.setDesc("查询成功");
        		}else{
        			//三个月之前记录
        			Calendar calendar = Calendar.getInstance();
                	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	calendar.add(Calendar.MONTH, -3);
                	String end = formatter.format(calendar.getTime());
                	criteria.setEndtime(end);
                	PageBean list = pointsRecordService.pointListByPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
        			
    				item.setData(list.getBeanList());
    				if (list.getTr() == null) {
    					item.setMaxRow(0);
    				} else {
    					item.setMaxRow(list.getTr());
    				}
    				item.setPageIndex(list.getPc());
    				item.setPageSize(list.getPs());
    				item.setDesc("查询成功");
        		}
			}

    	}catch(Exception e){
    		item.setCode(-900);
			item.setDesc("查询积分异常：" + e.getMessage());
			LogHandle.error(LogType.pc, "查询订单异常! 异常信息:{0}", e,
					"point/getpoints");
    	}
    	return item.toJson();
    }
    
}

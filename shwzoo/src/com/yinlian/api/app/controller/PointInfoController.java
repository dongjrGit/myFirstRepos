package com.yinlian.api.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yinlian.wssc.web.util.CriteriaMemberPoints;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/app/point")
public class PointInfoController {

	@Autowired
	private  PointsRecordService    pointsRecordService;
	 @Autowired
	private  ConfigSetService    configSetService;
	 
	
	
	 /**
     * 积分查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/getpointsList", produces = "text/html;charset=UTF-8")
    public String getpointsList(String token,String page,String size,String type,String starttime,String endtime,String ch){
    	
    	ReusltItem item = new ReusltItem();
    	try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			
			if (StringUtilsEX.ToInt(type) < 0
					|| StringUtilsEX.ToInt(type) >= 2
							) {
				item.setCode(-104);
				item.setDesc("积分类型错误");
				return item.toJson();
			}
			
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-103);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			
			CriteriaMemberPoints criteria=new CriteriaMemberPoints();
			criteria.setType(StringUtilsEX.ToInt(type));
			
			criteria.setStarttime(StringUtilsEX.ToShortDate(starttime));
			criteria.setEndtime(StringUtilsEX.ToShortDate(endtime));
			criteria.setBuyerid(user.getUserId());
			criteria.setOrderByClause("CreateTime");
			criteria.setSort("desc");
			
			PageBean list = pointsRecordService.PlatformpointListByPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(list.getBeanList());
			if (list.getTr() == null) {
				item.setMaxRow(0);
			} else {
				item.setMaxRow(list.getTr());
			}
			item.setPageIndex(list.getPc());
			item.setPageSize(list.getPs());
			item.setCode(0);

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取当前用户的积分消费情况异常，信息：" + e,
					"point/getpointsList");
		}
		return item.toJson();
    }
    

	 /**
    * 查询积分兑换比例
    * @param request
    * @return
    */
   @RequestMapping(value = "/getpointsratio", produces = "text/html;charset=UTF-8")
   public String getpointsratio(String token,String ch){
   	
   	ReusltItem item = new ReusltItem();
   	try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			item.setCode(0);
			Configdictionary configdictionary = configSetService
					.showConfigSetByType(ConfigSetTypeEnum.积分兑换人民币.getValue());
			if(configdictionary==null||StringUtilsEX.IsNullOrWhiteSpace(configdictionary.getValue())){
				item.setData("0.01");
			}else{
				item.setData(configdictionary.getValue());
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取积分结算兑换比例异常！"+e.toString());
			}else {
				item.setDesc("获取积分结算兑换比例异常！");
			}
			LogHandle.error(LogType.Api, "获取当前用户的积分消费情况异常，信息：" + e,
					"point/getpointsratio");
		}
		return item.toJson();
   }
    
	
}

package com.yinlian.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author wl
 * 
 *
 */
@RestController
@RequestMapping("/api/app/advert")
public class AdvertApiController {

    @Autowired
    AdvertImgService            advertImgService;
    
    @Autowired
   private  AdverisingService adverisingService;

    /**
     * 查询广告
     * 
     * @param ch
     * @return
     */

    @RequestMapping(value = "/queryadvert", produces = "text/html;charset=UTF-8")
    public String queryadvert(String page, String position, String ch) {
        ReusltItem item = new ReusltItem();
        try {

            Integer pagee = StringUtilsEX.ToInt(page);
            if (pagee < 0) {
                item.setCode(-102);
                item.setDesc("参数page错误：" + page);
                return item.toJson();
            }
            Integer chi = StringUtilsEX.ToInt(ch);
            if (chi < 0 || chi > 3) {
                item.setCode(-104);
                item.setDesc("登录通道参数错误");
                return item.toJson();
            }
            if(StringUtilsEX.ToInt(position)>=0){
            	 item.setData(adverisingService.getListByTypeAndDisplayApi(pagee, StringUtilsEX.ToInt(position),ActivityUsePlatformEnum.app.getValue()));
            }else{
            	item.setData(adverisingService.getListByTypeApi(pagee,ActivityUsePlatformEnum.app.getValue()));
            }
            item.setCode(0);
            item.setDesc("查询成功");
        } catch (Exception e) {
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
            LogHandle.error(LogType.Api, "查询广告信息异常! 异常信息:{0}", e, "advert/queryadvert");
        }
        return item.toJson();
    }
    
    @RequestMapping(value = "/queryact", produces = "text/html;charset=UTF-8")
    public String queryadvert(String ch) {
    	 ReusltItem item = new ReusltItem();
    	try {
    		item.setData(adverisingService.getByAdvertact(PageMarkType.抢购活动.getValue(), 1));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
            LogHandle.error(LogType.Api, "获取首页活动异常! 异常信息:{0}", e, "advert/queryact");
		}
    	 return item.toJson();
    }
    
}

package com.yinlian.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.NavigationService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;
@RestController
@RequestMapping("/api/app/navigation")
public class NavigationApiController {
	@Autowired
	private NavigationService navigationService;
	/**
	 * 查询导航信息
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @return
	 */
	 @RequestMapping(value = "/querynavigation", produces = "text/html;charset=UTF-8")
	    public String querynavigation( String page, String size, String ch) {
	        ReusltItem item = new ReusltItem();
	        try {
	        	if(!StringUtilsEX.isChannelTypeExist(ch)){
					item.setCode(-108);
					item.setDesc("登录通道参数错误");
					return item.toJson();
				}
	            if (StringUtilsEX.ToInt(page) < 0) {
	                item.setCode(-102);
	                item.setDesc("分页参数page错误:" + page);
	                return item.toJson();
	            }
	            if (StringUtilsEX.ToInt(size) < 0) {
	                item.setCode(-103);
	                item.setDesc("分页参数size错误:" + size);
	                return item.toJson();
	            }
	            if (!StringUtilsEX.isChannelTypeExist(ch)) {
	                item.setCode(-104);
	                item.setDesc("登录通道参数错误");
	                return item.toJson();
	            }

	            PageBean pageBean = navigationService.selectNavigationPage( StringUtilsEX.ToInt(page),
	                StringUtilsEX.ToInt(size));
	            item.setData(pageBean.getBeanList());
	            item.setMaxRow(pageBean.getTr());
	            item.setPageIndex(StringUtilsEX.ToInt(page));
	        } catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(e.toString());
				} else {
					item.setDesc("系统错误!");
				}
				item.setCode(-900);
	            LogHandle.error(LogType.Api,"查询导航信息异常! 异常信息:{0}", e,
	                "navigation/querynavigation");
	        }
	        return item.toJson();
	    }
}

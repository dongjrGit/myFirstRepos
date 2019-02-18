/*
 * @(#) WapSpuController.java 2016年6月20日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.api.wap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yl.soft.log.LogHandle;

/**
 * 商品
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年6月20日
 */
@Controller
@RequestMapping("/wap/spu")
public class WapSpuController {

	
	public String spuList(Model model){
		ReusltItem item=new ReusltItem();
		try {
			
			
			
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取精选推荐列表异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Data,"获取物流公司列表异常! 异常信息:", e,"/platform/logistics/pagelist");
		}
		model.addAttribute("item", item);
		return "";
	}
	
}

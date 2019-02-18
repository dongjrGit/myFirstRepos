package com.yinlian.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.CircleService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 活动列表
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/app/circle")
public class CircleController {

	@Autowired
	private CircleService     circleService;
	
	/**
	 * 查询所有的商圈
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/allCircle", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addcard(String ch,String page,String size){
			
		ReusltItem item = new ReusltItem();
		try {
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Integer ipage = StringUtilsEX.ToIntNull(page);
			Integer isize = StringUtilsEX.ToIntNull(size);
			if (ipage == null || ipage <= 0) {
				ipage = 1;
			}
			if (isize == null || isize <= 0) {
				isize = 10;
			}
			Criteria criteria=new Criteria();
			PageBean listBean=circleService.queryAll(criteria,ipage, isize);
			item.setData(listBean.getBeanList());
			item.setPageIndex(ipage);
			item.setPageSize(isize);
			item.setPage(listBean.getTp());
			item.setMaxRow(listBean.getTr());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"查询商圈异常! 异常信息:{0}",
					e, "circle/allCircle");
		}
		return item.toJson();
	}
}

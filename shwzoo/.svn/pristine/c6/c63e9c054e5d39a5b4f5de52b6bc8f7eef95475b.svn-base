package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/address")
public class PCAddressController {
	
	@Autowired
	private ProvinceServcice provinceServcice;
	
	/**
	 * 查询全部省列表信息
	 * @return
	 */
	@RequestMapping("/getallprovince")
	public ReusltItem selectAllProvice(){
		ReusltItem item=new ReusltItem();
		try {
			List<Province> list=provinceServcice.selectAll();
			item.setData(list);
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){				
			item.setDesc("查询全部省列表信息异常：" + e.getMessage());
			}else {
				item.setDesc("查询全部省列表信息异常");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("查询全部省列表信息异常! 异常信息:{0}",
					e.getMessage()), "address/getallprovince");
		}
		return item;
	}
}

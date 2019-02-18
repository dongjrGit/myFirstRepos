package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yinlian.Extended.LogType;
import com.yinlian.pc.dto.FreightParamDto;
import com.yinlian.pc.dto.FreightReturnDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/freight")
public class PCFreightController {
	@Autowired
	private   FreightService     freightService;
	
	@Autowired
	private   OrderService       orderService;
	
	@RequestMapping(value = "/getFreightByShop", produces = "text/html;charset=UTF-8")
	public String getFreightByShop(String ch,String shopfreight,String province,String spuids){
		BaseResult item=new BaseResult();
		
		try{
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(shopfreight)) {
	            item.setCode(-101);
	            item.setDesc("店铺id(shopid)不能为空！");
	            return item.toJson();
	        }
			if (StringUtilsEX.IsNullOrWhiteSpace(province)) {
	            item.setCode(-102);
	            item.setDesc("省名称(provinceName)不能为空！");
	            return item.toJson();
	        }
			if (StringUtilsEX.IsNullOrWhiteSpace(spuids)) {
	            item.setCode(-103);
	            item.setDesc("获取运费错误");
	            return item.toJson();
	        }
			List<Integer> spids=new ArrayList<Integer>();
			String ids[]=spuids.split(",");
			if(ids.length>1){				
				for (int i = 0; i < ids.length; i++) {
					spids.add(StringUtilsEX.ToInt(ids[i]));					
				}
			}else{
				spids.add(StringUtilsEX.ToInt(spuids));
			}
			List<FreightParamDto> listdto=new ArrayList<FreightParamDto>();
			if (!StringUtilsEX.IsNullOrWhiteSpace(shopfreight)) {					
				listdto=JSON.parseArray(shopfreight, FreightParamDto.class);
			}
			
			SessionUser user = SessionState.GetCurrentUser();
			if(user.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆");
				return item.toJson();
			}
			if(province.contains("省")||province.contains("市")){
				province=province.substring(0, province.length()-1);
			}
			
			List<FreightReturnDto> list=freightService.getFreightPriceNew(listdto, province, spids);
			if(list!=null && list.size()==listdto.size()){
				item.setCode(0);
				item.setData(list);
			}else{
				item.setCode(-200);
				item.setDesc("有的店铺没有模板信息");
			}
			
		}catch(Exception e){
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取运费模板异常：" + e.getMessage());
			}else {
				item.setDesc("获取运费模板异常");
			}
			
			LogHandle.error(LogType.pc, MessageFormat.format("获取运费模板异常! 异常信息:{0}",
					e.getMessage()), "/1/freight/getFreightByShop");
		}
		
		 return item.toJson();
	}
	
}

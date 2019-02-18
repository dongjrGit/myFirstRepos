package com.yinlian.api.app.controller;

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
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.V_Freights;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 活动列表
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/app/freight")
public class FreightController {

	@Autowired
	private   FreightService     freightService;
	
	/**
	 * 获取运费模板（组合商品用，其他商品用下面的）
	 * @param ch
	 * @param token
	 * @param shopid
	 * @param proNo
	 * @return
	 */
	@RequestMapping(value = "/getFreight", produces = "text/html;charset=UTF-8")
	public String getFreight(String token,String shopid,String provinceName,String ch) {
		ReusltItem item = new ReusltItem();
		try {
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(shopid)) {
                item.setCode(-101);
                item.setDesc("店铺id(shopid)不能为空！");
                return item.toJson();
            }
			if (StringUtilsEX.IsNullOrWhiteSpace(provinceName)) {
                item.setCode(-102);
                item.setDesc("省名称(provinceName)不能为空！");
                return item.toJson();
            }
			
			SessionUser user = SessionState.GetCurrentUser(token);
			if(user.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆");
				return item.toJson();
			} 
			
			List<Integer> shopids=new ArrayList<Integer>();
			if(shopid.contains(",")){
				String[] a=shopid.split(",");
				for (int i = 0; i < a.length; i++) {
					shopids.add(StringUtilsEX.ToInt(a[i]));
				}
			}else{
				shopids.add(StringUtilsEX.ToInt(shopid));
			}
			if(provinceName.contains("省")||provinceName.contains("市")){
				provinceName=provinceName.replace("省", "")
						.replace("市", "");
			}
			List<V_Freights> list=freightService.getFreightPrice(shopids,provinceName);
			if(list==null){
				item.setCode(-200);
				item.setDesc("有的店铺没有模板信息");
			}else{
				item.setData(list);
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取运费模板异常! 异常信息:{0}",
					e, "freight/getFreight");
		}
		return item.toJson();
	}
	/**
	 * 新获取运费（有包邮商品的用此接口，组合商品用上面接口） 
	 * @param ch
	 * @param shopfreight 店铺运费字符串 格式：[{shopid:1,procount:2,proprice:0},...]	shopid-店铺ID,procount-商品总数量 ,proprice-商品总价格
	 * @param province 省名称
	 * @param spuids spuid集合
	 * @return
	 */
	@RequestMapping(value = "/getFreightByShop", produces = "text/html;charset=UTF-8")
	public String getFreightByShop(String ch,String shopfreight,String province,String spuids,String token){
		BaseResult item=new BaseResult();
		
		try{
			SessionUser user = SessionState.GetCurrentUser(token);
			if(user==null||user.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆");
				return item.toJson();
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(shopfreight)) {
	            item.setCode(-101);
	            item.setDesc("店铺运费参数不能为空！");
	            return item.toJson();
	        }
			if (StringUtilsEX.IsNullOrWhiteSpace(province)) {
	            item.setCode(-102);
	            item.setDesc("省名称不能为空！");
	            return item.toJson();
	        }
			if (StringUtilsEX.IsNullOrWhiteSpace(spuids)) {
	            item.setCode(-103);
	            item.setDesc("商品id参数错误");
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
			
			if(province.contains("省")||province.contains("市")){
				province=province.substring(0, province.length()-1);
			}
			List<FreightReturnDto> list=freightService.getFreightPriceNew(listdto, province, spids);
			if(list!=null && list.size()==listdto.size()){
				item.setCode(0);
				item.setData(list);
			}else{
				item.setCode(-200);
				item.setDesc("店铺没有模板信息");
			}
			
		}catch(Exception e){
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取运费模板异常! 异常信息:{0}",
					e, "/freight/getFreightByShop");
		}
		
		 return item.toJson();
	}
}

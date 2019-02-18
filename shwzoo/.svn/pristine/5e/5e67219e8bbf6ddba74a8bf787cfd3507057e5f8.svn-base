package com.yinlian.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.VipCardStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.Card;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.VipCardService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/app/card")
public class CardController {

	
	@Autowired
	private ProvinceServcice       provinceService;
	
	@Autowired
	private CityServcie            cityService;
	
	@Autowired
	private AreaService            areaService;
	
	@Autowired
	private VipCardService         vipCardService;
	/**
	 * 办理会员卡
	 * @param token
	 * @param name
	 * @param phone
	 * @param provincecode
	 * @param citycode
	 * @param areacode
	 * @param addr
	 * @param company
	 * @param idcard
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/addcard", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addcard(String token,String name,String mobile,String provincecode,String citycode,String areacode,String addr,String ch,String company,String idcard){
		BaseResult item = new BaseResult();
		try {
			
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-102);
				item.setDesc("姓名(name)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-103);
				item.setDesc("电话(mobile)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(provincecode)) {
				item.setCode(-104);
				item.setDesc("省编号(provincecode)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(citycode)) {
				item.setCode(-105);
				item.setDesc("市编号(citycode)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(areacode)) {
				item.setCode(-106);
				item.setDesc("区编号(areacode)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(addr)) {
				item.setCode(-107);
				item.setDesc("详细地址(addr)不能为空！");
				return item.toJson();
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号(mobile)格式错误！");
				return item.toJson();
			}
			//.matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")
			if (!idcard.matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")) {
				item.setCode(-119);
				item.setDesc("身份证(idcard)格式错误！");
				return item.toJson();
			}
			
			Province province=provinceService.queryByCode(provincecode);
			String provinceName="";
			if(province!=null){
				provinceName=province.getName();
			}else{
				item.setCode(-111);
				item.setData("省编号不存在");
				return item.toJson();
			}
			
			City city=cityService.queryByCode(citycode);
			String cityName="";
			if(city!=null){
				cityName=city.getName();
			}else{
				item.setCode(-110);
				item.setData("市编号不存在");
				return item.toJson();
			}
			
			Area area=areaService.queryByCode(areacode);
			String areaName="";
			if(area!=null){
				 areaName=area.getName();
			}else{
				item.setCode(-109);
				item.setData("区编号不存在");
				return item.toJson();
			}
			
			SessionUser user = SessionState.GetCurrentUser(token);
			if(user.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆");
				return item.toJson();
			} 
			int  userid=user.getUserId();
			Card c1=new Card();
			c1=vipCardService.quertByUserId(userid);
			if(c1==null||c1.getStauts()==VipCardStatusEnum.审核未通过.getValue()){
				Card card=new Card();
				card.setUserid(userid);
				card.setUsername(name);
				card.setMobile(mobile);
				card.setProvincecode(provincecode);
				card.setProvincename(provinceName);
				card.setAreacode(areacode);
				card.setAreaname(areaName);
				card.setCitycode(citycode);
				card.setCityname(cityName);
				card.setAddress(addr);
				card.setCompanyname(company);
				card.setIdcard(idcard);
				card.setStauts(VipCardStatusEnum.申请中.getValue());
				int temp=vipCardService.insert(card);
				if(temp==0){
					item.setCode(-200);
					item.setDesc("办卡失败");
				}else{
					item.setCode(0);
					item.setDesc("办卡成功");
				}
			}else{
				item.setCode(-110);
				item.setDesc("你已经办卡");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"办理会员卡异常! 异常信息:{0}",
					e, "/card/addcard");
		}
		return item.toJson();
	}
}

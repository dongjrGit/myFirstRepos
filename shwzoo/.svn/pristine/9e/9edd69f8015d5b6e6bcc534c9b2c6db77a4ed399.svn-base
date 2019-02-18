package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.UserCollectTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;


	@Controller
	@RequestMapping("/wap/concern")
	public class WapConcernViewController {
		@Autowired
		private   UsercollectService        usercollectService;
		
		@Autowired
		private   ShopService               shopService;
		
		@Autowired
		private   SpuService                spuService;
		
		/**
		 * 查询收藏的店铺或商品
		 * @param token
		 * @param spuId
		 * @return
		 */
		@RequestMapping(value = "/selectCollect", produces = "text/html;charset=UTF-8")
		public ModelAndView selectCollect(String type,HttpServletRequest request) {
			BaseResult item = new BaseResult();
			ModelAndView view = new ModelAndView();
			try {
				if (StringUtilsEX.IsNullOrWhiteSpace(type)) {
					view.setViewName("/template/reeor/index.html");
					return view;
					
				}
				String ch="3";
				if(!StringUtilsEX.isChannelTypeExist(ch)){
					view.setViewName("/template/error/index.html");
					return view;
					
				}
				int collectType=StringUtilsEX.ToInt(type);
				if(collectType!=UserCollectTypeEnum.商品.getValue()&&collectType!=UserCollectTypeEnum.店铺.getValue()){
					view.setViewName("/template/error/index.html");
					return view;
				}
				SessionUser sessionUser=new SessionUser();
				String token = CookieUtils.getTokenFromCookie(request);
				sessionUser=SessionState.GetCurrentUser(token);
				if(sessionUser.getCode()!=0){
					view.setViewName("/template/wap/userinfo/login");
					return view;
				}else{
					int buyerId=sessionUser.getUserId();
				//int buyerId=492;
					List<Usercollect> list=usercollectService.select(buyerId, collectType);
					if(list!=null&&list.size()<1){
						item.setCode(0);
						
					}else{
						if(collectType==UserCollectTypeEnum.商品.getValue()){
							List<Spu> spulist=new ArrayList<Spu>();
							for (int i = 0; i < list.size(); i++) {
								Spu spu=spuService.queryById(list.get(i).getSpuid());
								if(spu!=null){
									spulist.add(spu);
								}
							}
							item.setCode(0);
							item.setDesc("查询成功");
							item.setData(spulist);
							request.setAttribute("spu",spulist);
							view.setViewName("/template/wap/concern/Concernspu");
							return view;
						}else{
							List<Shop> shoplist=new ArrayList<Shop>();
							for (int i = 0; i <list.size(); i++) {
								Shop shop=shopService.queryById(list.get(i).getShopid());
								if(shop!=null){
									shoplist.add(shop);
								}
							}
							item.setCode(0);
							item.setDesc("查询成功");
							item.setData(shoplist);
							request.setAttribute("shop",shoplist);
							view.setViewName("/template/wap/concern/Concernshop");
							return view;
						}	
					}
					
				}
				//return ErrorRedirect.getInstance().wapRedirectErrorModel();
				if(collectType==UserCollectTypeEnum.商品.getValue())
					{
					view.setViewName("/template/wap/concern/Concernspu");
					return view;
					}
				else {
					view.setViewName("/template/wap/concern/Concernshop");
				return view;
				}
			} catch (Exception e) {
				item.setCode(-900);
				item.setDesc("查询收藏的店铺或商品：" + e.getMessage());
				LogHandle.error(LogType.Api, MessageFormat.format("查询收藏的店铺或商品! 异常信息:{0}",
						e.toString()), "concern/selectCollect");
				view.setViewName("/template/error/index.html");
				return view;
			}
			
			
		}
	}


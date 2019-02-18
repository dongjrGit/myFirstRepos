package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.SiteType;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ApiShopAboutInfoBaseDto;
import com.yinlian.api.app.dto.ShopAppDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.PageInfo;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/shop")
public class ShopIndexController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private SpuService spuService;
    @Autowired UsercollectService usercollectService;
    @Autowired
    private AdverisingService adverisingService;
	@RequestMapping("index.html")
	public String index(String id,String href,Model model) {
		try {
			int shopid = StringUtilsEX.ToInt(id);
			/*
			 * Shop shop = shopService.getShopBySpuID(shopid);
			 */
			// collection rate
			// 获取是否收藏
			/*
			 * Integer userId = SessionState.GetCurrentUser(token).getUserId();
			 * Boolean bool = usercollectService.IsCollectShop(userId,
			 * StringUtilsEX.ToInt(id)); shopAppDto.setIskeep(bool);
			 */
//			final ShopAppDto shopAppDto = shopService.queryByIdForApp(shopid, SiteType.wap);
//			if (shopAppDto.getId() == null || shopAppDto.getId() <= 0) {
//				return ErrorRedirect.getInstance().wapRedirect("店铺不存在");
//			}
//			// 获取是否收藏	
//			SessionUser  user= SessionState.GetCurrentUser();
//			if(user==null||user.getCode()<0){
//				shopAppDto.setIskeep(false);
//			}else{
//				Boolean bool = usercollectService.IsCollectShop(user.getUserId(),shopid);
//				shopAppDto.setIskeep(bool);
//			}						
			List<Advertising>  advertising= adverisingService.getListByType(2,3);
			ApiShopAboutInfoBaseDto apishopdto = shopService.getApiShopAboutInfo(shopid);
			model.addAttribute("shopinfotop", apishopdto);
			model.addAttribute("ggimg", advertising);
//			model.addAttribute("title", shopAppDto.getName());
//			model.addAttribute("shopinfo", shopAppDto);
			final	CriteriaShop criteria = new CriteriaShop();
			criteria.setShopid(shopid);
			criteria.setSiteType(SiteType.wap.getValue());
			criteria.setStatus(ProStatusEnum.上架.getValue());
			criteria.setOrderByClause("WapPrice");
			criteria.setSort("DESC");
			PageBean pageBean = spuService.queryByAppCriteria(criteria, 1, 10);
			model.addAttribute("spuinfo", pageBean.getBeanList());
			PageInfo pageinfo = new PageInfo();
			pageinfo.setCount(pageBean.getTr());
			pageinfo.setIndex(pageBean.getPc());
			pageinfo.setMaxpage(pageBean.getTp());
			pageinfo.setSize(10);
			model.addAttribute("href", href);
			model.addAttribute("page", pageinfo);
		} catch (Exception ex) {

			LogHandle.error(LogType.wap,
					MessageFormat.format("店铺页面异常! 异常信息:{0}", ex),
					"/wap/shop/index");
			 return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/shop/index";
	}
}

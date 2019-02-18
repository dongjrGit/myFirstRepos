package com.yinlian.api.wap.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.SiteType;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ShopAppDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.PageInfo;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/wap/shop")
public class ShopWapController {

	@Autowired
	private SpuService spuService;

	@Autowired
	private ShopService shopService;
	@Autowired
	private UsercollectService usercollectService;

	/**
	 * 获取店铺的详细信息
	 * 
	 * @param id
	 *            店铺id
	 * @return
	 */
	@RequestMapping(value = "/queryshopinfo", produces = "text/html;charset=UTF-8")
	public String queryShopInfo(String id, String ch, String token) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数店铺id错误:" + id);
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-104);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}

			ShopAppDto shopAppDto = shopService.queryByIdForApp(StringUtilsEX.ToInt(id), SiteType.wap,item);
			if (StringUtils.isNotNull(token)) {
				// 获取是否收藏
				Integer userId = SessionState.GetCurrentUser(token).getUserId();
				Boolean bool = usercollectService.IsCollectShop(userId, StringUtilsEX.ToInt(id));
				shopAppDto.setIskeep(bool);
			} else {
				shopAppDto.setIskeep(false);
			}
			item.setCode(0);
			item.setData(shopAppDto);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询商家信息异常：" + e.getMessage());
			LogHandle.error(LogType.wap, MessageFormat.format("查询商家信息异常! 异常信息:{0}", e.getMessage()).toString(),
					"shop/queryShopInfo");
		}
		return item.toJson();
	}

	/**
	 * 获取商品详细 页 店铺信息
	 * 
	 * @param ch
	 * @param sid
	 * @return
	 */
	@RequestMapping(value = "/getprodshop", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailedShopInfo(String ch, String sid) {
		BaseResult item = new BaseResult();
		String logpath = "product/" + ch + "/" + "getProductDetailedShopInfo";
		try {

			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer shopid = StringUtilsEX.ToIntNull(sid);
			if (shopid == null || shopid <= 0) {
				item.setCode(-102);
				item.setDesc("shopid参数不正确！");
				return item.toJson();
			}
			item.setData(shopService.getApiShopAboutInfo(shopid));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, MessageFormat.format("获取商品详细（店铺店铺）错误：{0}", e.toString()), logpath);
		}
		return item.toJson();
	}

	@RequestMapping(value = "/getprolist", produces = "text/html;charset=UTF-8")
	public String getproducts(String ch,String id, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			int shopid = StringUtilsEX.ToInt(id);
			PageInfo pageinfo = new PageInfo();
			pageinfo.setIndex(1);
			pageinfo.setSize(10);
			if (StringUtils.isNotNull(page)) {
				pageinfo.setIndex(StringUtilsEX.ToInt(page));
			}
			if (StringUtils.isNotNull(size)) {
				pageinfo.setSize(StringUtilsEX.ToInt(size));
			}
			if (pageinfo.getIndex() <= 0)
				pageinfo.setIndex(1);
			if (pageinfo.getSize() <= 0) {
				pageinfo.setSize(10);
			}
			CriteriaShop criteria = new CriteriaShop();
			criteria.setShopid(shopid);
			criteria.setSiteType(SiteType.wap.getValue());
			criteria.setStatus(ProStatusEnum.上架.getValue());
			criteria.setOrderByClause("WapPrice");
			criteria.setSort("DESC");
			PageBean pageBean = spuService.queryByAppCriteria(criteria, pageinfo.getIndex(), pageinfo.getSize());
			item.setData(pageBean.getBeanList());		
			pageinfo.setCount(pageBean.getTr());
			pageinfo.setIndex(pageBean.getPc());
			pageinfo.setMaxpage(pageBean.getTp());
			item.setDesc(pageinfo.tojson());
		} catch (Exception ex) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "获取商品详细（店铺店铺）错误：{0}", ex, "/api/wap/shop/getprolist");
		}
		return item.toJson();
	}
}

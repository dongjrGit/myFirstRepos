/*
 * YinLian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.ShopSortEnum;
import com.yinlian.Enums.ShopTypeEnum;
import com.yinlian.Enums.SiteType;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ShopAppDto;
import com.yinlian.api.app.dto.ShopCategoryDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Applyshop;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.ApplyshopService;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.ShopcategoryService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.MapDistance;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 商家的api控制器 ShopApiController.java
 * 
 * @author Administrator
 * @version $Id: ShopApiController.java, v 0.1 2016年4月1日 上午11:14:39
 *          Administrator Exp $
 */
@RestController
@RequestMapping("/api/app/shop")
public class ShopApiController {

	@Autowired
	private SpuService spuService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ConfigSetService configSetService;
	@Autowired
	private ShopcategoryService shopcategoryService;
	@Autowired
	private UsercollectService usercollectService;
	@Autowired
	private ApplyshopService applyshopService;
	@Autowired
	private AdvertImgService advertImgService;

	/**
	 * 根据店铺id查询spu 分页信息
	 * 
	 * @param shopid
	 * @return
	 */
	@RequestMapping(value = "/queryspu", produces = "text/html;charset=UTF-8")
	public String querySpuByCriteria(String shopid, String page, String size, String ch, HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("参数店铺id错误:" + shopid);
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

			CriteriaShop criteria = new CriteriaShop();
			criteria.setShopid(StringUtilsEX.ToInt(shopid));
			criteria.setStatus(ProStatusEnum.上架.getValue());
			criteria.setOrderByClause("price");
			criteria.setSort("DESC");
			PageBean pageBean = spuService.queryByAppCriteria(criteria, StringUtilsEX.ToInt(page),
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
			LogHandle.error(LogType.Api, "查询商家商品信息异常! 异常信息:{0}", e, "shop/querySpuByCriteria");
		}
		return item.toJson();
	}

	/**
	 * 获取店铺的详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryshopinfo", produces = "text/html;charset=UTF-8")
	public String queryShopInfo(String id, String ch, String token, HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
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

			ShopAppDto shopAppDto = shopService.queryByIdForApp(StringUtilsEX.ToInt(id), SiteType.app,item);
			if(item.getCode()>=0){
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
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "查询商家信息异常! 异常信息:{0}", e, "shop/queryShopInfo");
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
			LogHandle.error(LogType.Api, "获取商品详细（店铺店铺）错误：{0}", e, logpath);
		}
		return item.toJson();
	}

	/**
	 * 申请加盟
	 * 
	 * @param token
	 * @param mobile
	 * @param shopname
	 * @param name
	 * @param privincecode
	 * @param citycode
	 * @param areacode
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/join", produces = "text/html;charset=UTF-8")
	public @ResponseBody String join(String mobile, String shopname, String contactsname, String provincecode,
			String citycode, String areacode, String address, String iscontact, String ch) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if ("".equals(mobile) || mobile == null) {
				item.setCode(-102);
				item.setDesc("请填您的电话");
				return item.toJson();
			}
			Applyshop applyshop = new Applyshop();
			applyshop.setAcode(areacode);
			applyshop.setAddress(address);
			applyshop.setCcode(citycode);
			applyshop.setContactsname(contactsname);
			applyshop.setIscontact(false);
			applyshop.setMobile(mobile);
			applyshop.setPcode(provincecode);
			applyshop.setShopname(shopname);
			int result = applyshopService.inset(applyshop);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("操作成功");
			} else {
				item.setCode(-200);
				item.setDesc("操作失败");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "申请商家信息异常! 异常信息:{0}", e, "shop/join");
		}
		return item.toJson();
	}

	/**
	 * 
	 * @param mobile
	 * @param shopname
	 * @param name
	 * @param privincecode
	 * @param citycode
	 * @param areacode
	 * @param address
	 * @param ch
	 * @param paypassword
	 * @param password
	 * @param item
	 */
	/*
	 * private void checkParameters(String mobile, String shopname, String
	 * username, String provincecode, String citycode, String areacode, String
	 * address, String ch, String password, String paypassword, String
	 * principalname, ReusltItem item) { if
	 * (!StringUtilsEX.isChannelTypeExist(ch)) { item.setCode(-101);
	 * item.setDesc("登录通道参数错误"); return; } if (StringUtils.isBlanck(shopname)) {
	 * item.setCode(-102); item.setDesc("请填写您的店铺名称"); return; } if
	 * (StringUtils.isBlanck(mobile)) { item.setCode(-103);
	 * item.setDesc("请填您的电话"); return; } if (StringUtils.isBlanck(username)) {
	 * item.setCode(-104); item.setDesc("请填用户名"); return; } if
	 * (!StringUtilsEX.checkUserName(username)) { item.setCode(-109);
	 * item.setDesc("用户名不正确，请输入3~16个字符，26个大小写字、数字或“_-”（不能以数字开头）"); return; } if
	 * (StringUtils.isBlanck(provincecode) || StringUtils.isBlanck(citycode) ||
	 * StringUtils.isBlanck(areacode)) { item.setCode(-105);
	 * item.setDesc("请选择省市区"); return; } if (StringUtils.isBlanck(address)) {
	 * item.setCode(-106); item.setDesc("请填写详细地址"); return; } if
	 * (StringUtils.isBlanck(password)) { item.setCode(-107);
	 * item.setDesc("请填写登录密码"); return; } if (StringUtils.isBlanck(paypassword))
	 * { item.setCode(-108); item.setDesc("请填写支付密码"); return; } }
	 */

	/**
	 * 查询经营范围
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qeuryscop", produces = "text/html;charset=UTF-8")
	public String qeuryBusinessScop(String ch) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}

			Configdictionary configdictionary = configSetService.showConfigSetByType(ConfigSetTypeEnum.经营范围.getValue());
			if (configdictionary != null) {
				item.setData(configdictionary.getValue());
			}
			item.setCode(0);

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "查询经营范围异常! 异常信息:{0}", e, "shop/qeuryscop");
		}
		return item.toJson();
	}

	/**
	 * 附近商盟
	 * 
	 * @param ch
	 *            必填
	 * @param longitude
	 *            必填
	 * @param latitude
	 *            必填
	 * @param isjck
	 *            非
	 * @param isfree
	 *            非
	 * @param issupport
	 *            非
	 * @param classid
	 *            非
	 * @param shopcircle
	 *            非
	 * @param page
	 *            必填
	 * @param size
	 *            必填
	 * @return
	 */
	@RequestMapping(value = "/queryscopshop", produces = "text/html;charset=UTF-8")
	public String queryScope(String ch, String longitude, String latitude, String isjck, String isfree,
			String issupport, String classid, String circleid, String issort, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtils.isBlanck(longitude) || StringUtils.isBlanck(latitude)) {
				item.setCode(-102);
				item.setDesc("经纬度不能为空");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) < 0) {
				item.setCode(-103);
				item.setDesc("分页参数page错误:" + page);
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(size) < 0) {
				item.setCode(-104);
				item.setDesc("分页参数size错误:" + size);
				return item.toJson();
			}
			Map<String, String> map = MapDistance.getSquarePoint(longitude, latitude, ConstanValue.DISTANCE);
			CriteriaShop criteria = new CriteriaShop();
			criteria.setLongmax(map.get("longmax"));
			criteria.setLongmin(map.get("longmin"));
			criteria.setLatmax(map.get("latmax"));
			criteria.setLatmin(map.get("latmin"));
			if (StringUtils.isNotNull(isjck)) {
				criteria.setIsjck(StringUtilsEX.toBooleanForOS(isjck));
			}
			if (StringUtils.isNotNull(isfree)) {
				criteria.setIsfree(StringUtilsEX.toBooleanForOS(isfree));
			}
			if (StringUtils.isNotNull(issupport)) {
				criteria.setIssupport(StringUtilsEX.toBooleanForOS(issupport));

			}
			if (StringUtils.isNotNull(classid)) {
				criteria.setClassid(StringUtilsEX.ToInt(classid));
			}
			if (StringUtils.isNotNull(circleid)) {
				criteria.setCircleid(StringUtilsEX.ToInt(circleid));
			}
			criteria.setLongitude(longitude);
			criteria.setLatitude(latitude);
			if (StringUtils.isNotNull(issort) && issort.equals(ShopSortEnum.距离.getValue())) {
				criteria.setOrderByClause("distances");
			}
			if (StringUtils.isNotNull(issort) && issort.equals(ShopSortEnum.评价.getValue())) {
				criteria.setOrderByClause("gooddescription");
				criteria.setSort("DESC");
			}
			if (StringUtils.isNotNull(issort) && issort.equals(ShopSortEnum.人气.getValue())) {
				criteria.setOrderByClause("popularity");
				criteria.setSort("DESC");
			}
			if (StringUtils.isNotNull(issort) && issort.equals(ShopSortEnum.人均消费.getValue())) {
				criteria.setOrderByClause("consumption");
			}
			PageBean pageBean = shopService.queryBylatAndlogitCriteria(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
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
			LogHandle.error(LogType.Api, "查询附近商盟异常! 异常信息:{0}", e, "shop/queryscopshop");
		}
		return item.toJson();
	}

	/**
	 * 查询商盟分类
	 * 
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/querycategory", produces = "text/html;charset=UTF-8")
	public String querycategory(String ch) {
		BaseResult item = new BaseResult();
		try {
			List<ShopCategoryDto> list = shopcategoryService.getAllList();
			item.setData(list);
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "查询商盟分类异常! 异常信息:{0}", e, "shop/queryscopshop");
		}
		return item.toJson();
	}

	/**
	 * 查询餐饮店铺列表
	 * 
	 * @param page
	 * @param size
	 * @param ch
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getRestaurantShopList", produces = "text/html;charset=UTF-8")
	public String getRestaurantShopList(String page, String size, String ch,String shopname, HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Integer pageindex = StringUtilsEX.ToInt(page) <= 0 ? 1 : StringUtilsEX.ToInt(page);
			Integer pagesize = StringUtilsEX.ToInt(size) <= 0 ? 10 : StringUtilsEX.ToInt(size);
			CriteriaShop criteria = new CriteriaShop();
			criteria.setShoptype(ShopTypeEnum.餐饮票.getValue());
			criteria.setName(shopname);
			PageBean pageBean = shopService.getRestaurantShopList(criteria, pageindex, pagesize);
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
			LogHandle.error(LogType.Api, "查询餐饮店铺列表异常! 异常信息:{0}", e, "shop/getRestaurantShopList");
		}
		return item.toJson();
	}

	/**
	 * 获取店铺店内图片列表
	 * 
	 * @param id
	 * @param ch
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getShopImgList", produces = "text/html;charset=UTF-8")
	public String getShopImgList(String id, String ch, HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-102);
				item.setDesc("店铺id参数错误");
				return item.toJson();
			}
			List<AdvertImg> advertImgs = advertImgService.getShopImgList(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(advertImgs);
			item.setDesc("查询成功");
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取店铺店内图片列表异常! 异常信息:{0}", e, "shop/getShopImgList");
		}
		return item.toJson();
	}
	
	/**
	 * 获取票务中心列表
	 * @param ch
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getticketcenter", produces = "text/html;charset=UTF-8")
	public String getTicketCenter(String ch, HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			List<Shop> shops = shopService.getTicketCenter();
			item.setCode(0);
			item.setData(shops);
			item.setDesc("查询成功");
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取票务中心列表异常! 异常信息:{0}", e, "shop/getticketcenter");
		}
		return item.toJson();
	}
	
	/**
	 * 根据店铺名称模糊查询店铺列表
	 * @param page
	 * @param size
	 * @param ch
	 * @param response
	 * @param shopname
	 * @return
	 */
	@RequestMapping(value = "/getshopbyname", produces = "text/html;charset=UTF-8")
	public String getShopByName(String page, String size, String ch, HttpServletResponse response,
			String shopname) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Integer pageindex = StringUtilsEX.ToInt(page) <= 0 ? 1 : StringUtilsEX.ToInt(page);
			Integer pagesize = StringUtilsEX.ToInt(size) <= 0 ? 10 : StringUtilsEX.ToInt(size);
			CriteriaShop criteria = new CriteriaShop();
			if(!StringUtilsEX.IsNullOrWhiteSpace(shopname)){
				criteria.setName(shopname.trim());
			}
			PageBean pageBean = shopService.getShopByName(criteria, pageindex, pagesize);
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
			LogHandle.error(LogType.Api, "根据店铺名称模糊查询店铺列表异常! 异常信息:{0}", e, "shop/getshopbyname");
		}
		return item.toJson();
	}
	
	@RequestMapping(value = "/gettopicshop", produces = "text/html;charset=UTF-8")
	public String gettopicshop(String ch, HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Shop shops = shopService.gettopicshop(ShopTypeEnum.主题商品.getValue());
			if(shops!=null){
				item.setCode(0);
				item.setData(shops.getId());
			}else{
				item.setCode(-100);
				item.setData(0);
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取票务中心列表异常! 异常信息:{0}", e, "shop/gettopicshop");
		}
		return item.toJson();
	}
}

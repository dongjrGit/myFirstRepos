package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.AddCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CookieDto;
import com.yinlian.api.app.dto.V_ShopCart;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.CartService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.PackageService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/api/wap/shopcart")
public class ShopCartWapController {
	@Autowired
	private CartService cartService;
	@Autowired
	private PackageService packageService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private SpuService spuService;
    @Autowired
    private OperaterecordsService operaterecordsService ;

	/**
	 * 添加购物车
	 * 
	 * @param sid
	 *            shopid
	 * @param proid
	 *            skuid
	 * @param procount
	 *            商品数量
	 * @param ispack
	 *            是否是商品组合
	 * @param type
	 *            商品类型 0普通商品；1组合商品2秒杀 3闪购
	 * @param spikeid
	 *            闪购/秒杀活动id
	 * @param ch
	 *            通道wap:3
	 * @return
	 */
	@RequestMapping(value = "/addshopCart", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addCart(String sid, String proid,
			String procount, String type, String spikeid, String ch) {
		ReusltItem item = new ReusltItem();
		try {			
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			int shopid = StringUtilsEX.ToInt(sid);
			int ProID = StringUtilsEX.ToInt(proid);
			int Procount = StringUtilsEX.ToInt(procount);
			int Protype = StringUtilsEX.ToInt(type);
			// Integer SpuID = StringUtilsEX.ToIntNull(spuid);
			Integer SpikeID = StringUtilsEX.ToIntNull(spikeid);
			Integer SpuID = null;
			String goodsName = "";
			if (shopid <= 0) {
				item.setCode(-103);
				item.setDesc("店铺ID参数错误");
				return item.toJson();
			}
			if (ProID <= 0) {
				item.setCode(-104);
				item.setDesc("商品ID参数错误");
				return item.toJson();
			}
			if (Procount <= 0) {
				item.setCode(-105);
				item.setDesc("商品数量参数错误");
				return item.toJson();
			}
			if (Protype < 0) {
				item.setCode(-106);
				item.setDesc("商品类型参数错误");
				return item.toJson();
			}
			switch (Protype) {
			case 1:
				// 组合商品
				Package pkage = packageService.getByID(ProID);
				if (pkage == null) {
					item.setCode(-107);
					item.setDesc("组合商品ID错误，无此组合商品信息");
					return item.toJson();
				}
				goodsName = pkage.getName();
				break;
			default:
				// 普通商品
				Sku sku = skuService.selectByID(ProID);
				if (sku == null) {
					item.setCode(-108);
					item.setDesc("库存商品不存在");
					return item.toJson();
				}
				goodsName = sku.getName();
				SpuWithBLOBs spu = spuService.queryById(sku.getSpuId());
				if (spu == null) {
					item.setCode(-109);
					item.setDesc("库存商品ID错误，无此库存商品所对应的标准商品");
					return item.toJson();
				}
				SpuID = spu.getId();
				// if (SpuID != spu.getId())
				// {
				// item.setCode(-110);
				// item.setDesc("商品SPUID错误");
				// return item.toJson();
				// }
				break;
			}
			V_ShopCart vc = new V_ShopCart();
			int userid = user.getUserId();
			// int userid =1;
			vc.setUserID(userid);
			vc.setIsSelected(1);
			vc.setProCount(Procount);
			vc.setProName(goodsName);
			vc.setProID(ProID);
			vc.setShopID(shopid);
			vc.setType(Protype);
			vc.setSpuID(SpuID);
			vc.setSpikeID(SpikeID);

			if (cartService.addCart(vc) > 0) {
				item.setCode(0);
				item.setSum(cartService.getCountByUser(userid));
				item.setDesc("商品加入购物车成功!");
				LogHandle.info(LogType.wap, MessageFormat.format(
						"商品加入购物车成功! ProID:{0},userID:{1}", ProID, userid),
						"cart/addCart");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "shopcart.html", "/api/wap/shopcart/addshopCart", "商品加入购物车");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"加入购物车操作记录出错! 异常信息:",
    								e, "/api/wap/shopcart/addshopCart");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("商品加入购物车失败!");
				LogHandle.info(LogType.wap, MessageFormat.format(
						"商品加入购物车失败! ProID:{0},userID:{1}", ProID, userid),
						"cart/addCart");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("商品加入购物车异常：" + e.toString());
			LogHandle.error(LogType.wap,
					MessageFormat.format("商品加入购物车异常! 异常信息:{0}", e),
					"cart/addCart");
		}
		return item.toJson();
	}

	/**
	 * 会员登录批量上传购物车数据到数据库
	 * 
	 * @param ch
	 * @param request
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addcookieCarts", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addCarts(String ch, String cartstr) {
		BaseResult item = new BaseResult();
		try {
			
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			List<AddCartDto> cartDtos = new ArrayList<AddCartDto>();
			if (!StringUtilsEX.IsNullOrWhiteSpace(cartstr)) {
				JSONArray json = JSONArray.fromObject(cartstr);
				cartDtos = (List<AddCartDto>) JSONArray.toCollection(json,
						AddCartDto.class);
			} else {
				cartDtos = new ArrayList<AddCartDto>();
			}
			int userid = user.getUserId();
			// int userid=1;
			if (cartDtos != null && cartDtos.size() > 0) {
				List<V_ShopCart> vscList = new ArrayList<V_ShopCart>();
				V_ShopCart vsCart = null;
				for (AddCartDto dto : cartDtos) {
					vsCart = new V_ShopCart();
					vsCart = checkParam(dto, userid, item);
					if (item.getCode() < 0) {
						return item.toJson();
					}
					vscList.add(vsCart);
				}
				if (cartService.addCarts(vscList) > 0) {
					item.setData(cartService.getByUser(userid,ActivityUsePlatformEnum.wap.getValue()));
					item.setCode(0);
					item.setDesc("同步购物车数据成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "shopcart.html", "/api/wap/shopcart/addcookieCarts", "会员登录批量上传购物车数据到数据库");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"同步购物车数据! 异常信息:",
	    								e, "/api/wap/shopcart/addcookieCarts");
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("同步购物车数据失败!");
					LogHandle.info(LogType.wap, MessageFormat.format(
							"同步购物车数据失败! cartstr:{0},userID:{1}", cartstr,
							userid), "cart/addCarts");
				}
			} else {
				item.setData(cartService.getByUser(userid,ActivityUsePlatformEnum.wap.getValue()));
				item.setCode(0);
				item.setDesc("无购物车数据同步");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"同步更新购物车数据异常! 异常信息:{0}", e,
					"cart/addCarts");
		}
		return item.toJson();
	}

	/**
	 * 获取购物车数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCartList", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getCartList(String ch) {
		BaseResult item = new BaseResult();
		try {
			
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			int userid = user.getUserId();
			// int userid =1;
			item.setData(cartService.getByUser(userid,ActivityUsePlatformEnum.wap.getValue()));
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取购物车数据异常! 异常信息:{0}", e,
					"cart/getCartList");
		}
		return item.toJson();
	}

	/**
	 * 获取cookie购物车数据
	 * 
	 * @param cartstr 购物车缓存数据
	 * @return
	 */
	@RequestMapping(value="/getCookieCarts", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getCookieCartList(String cartstr, String ch) {
		BaseResult item = new BaseResult();
		// cartstr="[{CartType:0,GoodsCount:1,GoodsID:65,IsSelected:true,ShopActId:0,shopcartproid:0,ShopId:380,SpikeID:0,SpuActId:0,SpuId:82}]";
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(cartstr)) {
				item.setCode(-101);
				item.setDesc("购物车cookie不能为空");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}

			// List<CookieCartDto> cartlist = new ArrayList<CookieCartDto>();
			List<CookieDto> cartsDto = new ArrayList<ShoppingNewCartDto.CookieDto>();
			if (!StringUtilsEX.IsNullOrWhiteSpace(cartstr)) {
				JSONArray json = JSONArray.fromObject(cartstr);
				// cartlist = (List<CookieCartDto>) JSONArray.toCollection(json,
				// CookieCartDto.class);
				if (json != null && json.size() > 0) {					
					for (Integer i = 0; i < json.size(); i++) {
						JSONObject object = (JSONObject) json.get(i);
						CookieDto c = new CookieDto();
						c.setSpuId(object.getInt("SpuId"));
						c.setCartType(object.getInt("CartType"));
						c.setGoodsCount(object.getInt("GoodsCount"));
						c.setGoodsID(object.getInt("GoodsID"));
						c.setIsSelected(object.getBoolean("IsSelected"));
						c.setShopActId(object.getInt("ShopActId"));
						c.setShopcartproid(object.getInt("shopcartproid"));
						c.setShopId(object.getInt("ShopId"));
						c.setSpikeID(object.getInt("SpikeID"));
						c.setSpuActId(object.getInt("SpuActId"));
						cartsDto.add(c);
					}
				}
			}
             CartDto cartdts=cartService.getByCookieCart(cartsDto,ActivityUsePlatformEnum.wap.getValue());
			 item.setData(cartdts);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取购物车数据异常! 异常信息:{0}", e,
					"shopcart/getCookieCartList");
		}
		return item.toJson();
	}

	/**
	 * 获取购物车商品数量
	 * 
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/getCarGoodCount", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getCount(String ch) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			int userid = user.getUserId();
			item.setSum(cartService.getCountByUser(userid));
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取购物车商品数量异常! 异常信息:{0}", e,
					"cart/getCount");
		}

		return item.toJson();
	}

	/**
	 * 编辑商品数量
	 * 
	 * @param id
	 * @param count	
	 * @return
	 */
	@RequestMapping(value = "/changeCount", produces = "text/html;charset=UTF-8")
	public @ResponseBody String changeCount(String id, String count, String ch) {
		BaseResult item = new BaseResult();
		try {

			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			int ID = StringUtilsEX.ToInt(id);
			int Procount = StringUtilsEX.ToInt(count);
			if (ID <= 0) {
				item.setCode(-103);
				item.setDesc("ID参数错误");
				return item.toJson();
			}

			if (Procount < 0) {
				item.setCode(-104);
				item.setDesc("商品数量参数错误");
				return item.toJson();
			}
			
			
			
			if (cartService.updateCount(Procount, ID) > 0) {
				int userid = user.getUserId();
				
				item.setData(cartService.getByUser(userid,ActivityUsePlatformEnum.wap.getValue()));
				item.setCode(0);
				LogHandle.info(LogType.wap,
						MessageFormat.format("更新购物车商品数量成功! ProID:{0}", ID),
						"cart/changeCount");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "shopcart.html", "/api/wap/shopcart/changeCount", "更新购物车商品数量");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"更新购物车商品数量! 异常信息:",
    								e, "/api/wap/shopcart/changeCount");
						}
					}
				});
			} else {
				item.setCode(-200);
				LogHandle.info(LogType.wap,
						MessageFormat.format("更新购物车商品数量失败! ProID:{0}", ID),
						"cart/changeCount");
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"编辑商品数量异常! 异常信息:{0}", e,
					"cart/changeCount");
		}
		return item.toJson();
	}

	/**
	 * 清空购物车
	 * 
	 * @return
	 */
	@RequestMapping(value = "/clearCart", produces = "text/html;charset=UTF-8")
	public @ResponseBody String clearCart(String ch) {
		BaseResult item = new BaseResult();
		try {			
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}

			int userid = user.getUserId();
			
			if (cartService.deleteByUser(userid) > 0) {
				item.setCode(0);
				item.setDesc("清空购物车成功!");
				LogHandle.info(LogType.wap,
						MessageFormat.format("清空购物车成功! userID:{0}", userid),
						"shopcart/clearCart");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "shopcart.html", "/api/wap/shopcart/changeCount", "清空购物车");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"清空购物车! 异常信息:",
    								e, "/api/wap/shopcart/changeCount");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("清空购物车失败!");
				LogHandle.info(LogType.wap,
						MessageFormat.format("清空购物车失败! userID:{0}", userid),
						"shopcart/clearCart");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"清空购物车异常! 异常信息:{0}", e,
					"shopcart/clearCart");
		}
		return item.toJson();
	}
	/**
	 * 删除购物车某个商品
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleCartSpu", produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleCartSpu(String id, String ch) {
		BaseResult item = new BaseResult();
		try {			
			SessionUser user = SessionState.GetCurrentUser();
			if (user!=null&&user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}

			int ID = StringUtilsEX.ToInt(id);
			if (ID <= 0) {
				item.setCode(-103);
				item.setDesc("ID参数错误");
				return item.toJson();
			}
			if (cartService.deleteByID(ID) > 0) {
				int userid = user.getUserId();				
				item.setData(cartService.getByUser(userid,ActivityUsePlatformEnum.wap.getValue()));
				item.setCode(0);
				LogHandle.info(LogType.wap,
						MessageFormat.format("删除购物车商品成功! ID:{0}", ID),
						"shopcart/deleCartSpu");
			} else {
				item.setCode(-200);
				LogHandle.info(LogType.wap,
						MessageFormat.format("删除购物车商品失败! ID:{0}", ID),
						"shopcart/deleCartSpu");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "shopcart.html", "/api/wap/shopcart/changeCount", "删除购物车某个商品");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"删除购物车某个商品! 异常信息:",
    								e, "/api/wap/shopcart/changeCount");
						}
					}
				});
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"删除购物车商品异常! 异常信息:{0}", e,
					"shopcart/deleCartSpu");
		}
		return item.toJson();
	}
	
	/**
	 * 批量删除购物车商品
	 * 
	 * @param ids 用","隔开
	 * @return
	 */
	@RequestMapping(value = "/deleCartSpuList", produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleCartSpuList(String ids, String ch) {
		BaseResult item = new BaseResult();
		try {			
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			List<Integer> idlList = new ArrayList<Integer>();
			for (String id : ids.split(",")) {
				if (StringUtilsEX.ToInt(id) <= 0) {
					item.setCode(-103);
					item.setDesc("ID参数错误");
					return item.toJson();
				}
				idlList.add(StringUtilsEX.ToInt(id));
			}
			if (idlList.size() == 0) {
				item.setCode(-201);
				item.setDesc("未选中商品");
				return item.toJson();
			} else {
				if (cartService.deleteByIds(idlList) > 0) {
					int userid = user.getUserId();					
					item.setData(cartService.getByUser(userid,ActivityUsePlatformEnum.wap.getValue()));
					item.setCode(0);
					LogHandle.info(LogType.wap,
							MessageFormat.format("批量删除购物车商品成功! IDs:{0}", ids),
							"cart/deleCartSpuList");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "shopcart.html", "/api/wap/shopcart/deleCartSpuList", "操作说明（例：批量删除购物车商品）");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"批量删除购物车商品! 异常信息:",
	    								e, "/api/wap/shopcart/deleCartSpuList");
							}
						}
					});
				} else {
					item.setCode(-200);
					LogHandle.info(LogType.wap,
							MessageFormat.format("批量删除购物车商品失败! IDs:{0}", ids),
							"cart/deleCartSpuList");
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"批量删除购物车商品异常! 异常信息:{0}", e,
					"cart/deleCartSpuList");
		}
		return item.toJson();
	}

	/**
	 * 购物车选中或取消选中商品
	 * 
	 * @param ch
	 * @param id  购物车子表id
	 * @param sel
	 *            0-取消 1-选中
	 * @return
	 */
	@RequestMapping(value = "/selectCart", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selCart(String ch, String id, String sel) {
		BaseResult item = new BaseResult();
		try {			
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-103);
				item.setDesc("ID参数错误！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(sel) < 0 || StringUtilsEX.ToInt(sel) > 1) {
				item.setCode(-104);
				item.setDesc("选择商品是否选择参数错误！");
				return item.toJson();
			}

			if (cartService.updateSelect(StringUtilsEX.ToInt(id),
					StringUtilsEX.ToInt(sel)) > 0) {				
				item.setDesc("更新成功");
				item.setCode(0);
				LogHandle.info(LogType.wap,
						MessageFormat.format("购物车选中或取消选中商品成功! ID:{0}", id),
						"shopcart/selCart");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "shopcart.html", "/api/wap/shopcart/selectCart", "购物车选中或取消选中商品");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"购物车选中或取消选中商品! 异常信息:",
    								e, "/api/wap/shopcart/selectCart");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("购物车选中或取消选中商品失败");
				LogHandle.info(LogType.wap,
						MessageFormat.format("购物车选中或取消选中商品失败! ID:{0}", id),
						"shopcart/selCart");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,
					MessageFormat.format("购物车选中或取消选中商品异常! 异常信息:{0}", e),
					"shopcart/selCart");
		}
		return item.toJson();
	}
	
	/**
	 * 购物车批量更改商品选中状态
	 * 
	 * @param ch
	 * @param ids 购物车子表id集合用，隔开
	 * @param sels
	 *            0-取消 1-选中
	 * @return
	 */
	@RequestMapping(value = "/selectCartList", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selCartList(String ch, String ids, String sels) {
		BaseResult item = new BaseResult();
		try {			
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(ids)) {
				item.setCode(-103);
				item.setDesc("ID集合不能为空！");
				return item.toJson();
			}

			List<Integer> idlist = new ArrayList<Integer>();
			List<Integer> sellist = new ArrayList<Integer>();
			for (String id : ids.split(",")) {
				if (StringUtilsEX.ToInt(id) <= 0) {
					item.setCode(-104);
					item.setDesc("ID参数错误！");
					return item.toJson();
				}
				idlist.add(StringUtilsEX.ToInt(id));
			}
			for (String sel : sels.split(",")) {
				if (StringUtilsEX.ToInt(sel) < 0
						|| StringUtilsEX.ToInt(sel) > 1) {
					item.setCode(-105);
					item.setDesc("选择商品参数错误！");
					return item.toJson();
				}
				sellist.add(StringUtilsEX.ToInt(sel));
			}
			if (idlist.size() != sellist.size()) {
				item.setCode(-106);
				item.setDesc("ID参数集合与选中参数集合不匹配！");
				return item.toJson();
			}
			if (cartService.updateSelectList(idlist, sellist) > 0) {
				item.setCode(0);
				item.setDesc("更新成功");
				LogHandle.info(LogType.wap,
						MessageFormat.format("购物车批量更改商品选中状态成功! ID集合:{0}", ids),
						"shopcart/selCartList");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "shopcart.html", "/api/wap/shopcart/selectCartList", "购物车批量更改商品选中状态");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"购物车批量更改商品选中状态! 异常信息:",
    								e, "/api/wap/shopcart/selectCartList");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("购物车选中或取消选中商品失败");
				LogHandle.info(LogType.Api,
						MessageFormat.format("购物车批量更改商品选中状态失败! ID集合:{0}", ids),
						"shopcart/selCartList");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"购物车批量更改商品选中状态异常! 异常信息:{0}", e,
					"shopcart/selCartList");
		}
		return item.toJson();
	}
	/**
	 * 验证购物车参数
	 * 
	 * @param dto
	 * @param user
	 * @param item
	 * @return
	 * @throws Exception
	 */
	private V_ShopCart checkParam(AddCartDto dto, int userid, BaseResult item)
			throws Exception {
		Integer shopid = StringUtilsEX.ToInt(dto.getSid());
		Integer ProID = StringUtilsEX.ToInt(dto.getProid());
		Integer Procount = StringUtilsEX.ToInt(dto.getProcount());
		Integer Protype = StringUtilsEX.ToInt(dto.getType());
		Integer SpikeID = StringUtilsEX.ToIntNull(dto.getSpikeid());
		Integer isSelect = StringUtilsEX.ToInt(dto.getIssel());
		Integer SpuID = null;
		String goodsName = "";
		if (shopid <= 0) {
			item.setCode(-103);
			item.setDesc("店铺ID参数错误");
			return null;
		}
		if (ProID <= 0) {
			item.setCode(-104);
			item.setDesc("商品ID参数错误");
			return null;
		}
		if (Procount <= 0) {
			item.setCode(-105);
			item.setDesc("商品数量参数错误");
			return null;
		}
		if (Protype < 0) {
			item.setCode(-106);
			item.setDesc("商品类型参数错误");
			return null;
		}
		switch (Protype) {
		case 1:
			// 组合商品
			Package pkage = packageService.getByID(ProID);
			if (pkage == null) {
				item.setCode(-107);
				item.setDesc("组合商品ID错误，无此组合商品信息");
				return null;
			}
			goodsName = pkage.getName();
			break;
		default:
			// 普通商品
			Sku sku = skuService.selectByID(ProID);
			if (sku == null) {
				item.setCode(-108);
				item.setDesc("库存商品不存在");
				return null;
			}
			goodsName = sku.getName();
			SpuWithBLOBs spu = spuService.queryById(sku.getSpuId());
			if (spu == null) {
				item.setCode(-109);
				item.setDesc("库存商品ID错误，无此库存商品所对应的标准商品");
				return null;
			} else {
				if (spu.getStatus() != ProStatusEnum.上架.getValue()) {
					item.setCode(-110);
					item.setDesc("该商品已下架，不能添加购物车");
					return null;
				}
			}
			SpuID = spu.getId();
			break;
		}
		V_ShopCart vc = new V_ShopCart();
		vc.setUserID(userid);
		vc.setIsSelected(isSelect);
		vc.setProCount(Procount);
		vc.setProName(goodsName);
		vc.setProID(ProID);
		vc.setShopID(shopid);
		vc.setType(Protype);
		vc.setSpuID(SpuID);
		vc.setSpikeID(SpikeID);
		return vc;
	}

}

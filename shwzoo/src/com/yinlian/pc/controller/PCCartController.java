package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.AddCartDto;
import com.yinlian.api.app.dto.V_ShopCart;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.CartService;
import com.yinlian.wssc.web.service.InvoiceService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.PackageService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/cart")
public class PCCartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private PackageService packageService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private SkuService skuService;

	@Autowired
	private SpuService spuService;

	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private OperaterecordsService operaterecordsService;

	/**
	 * 添加购物车
	 * 
	 * @param sid
	 * @param proid
	 * @param procount
	 * @param ispack
	 * @param spikeid
	 * @param ch
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/addCart", produces = "text/html;charset=UTF-8")
	public String addCart(String sid, String proid, String procount,
			String type, String spikeid, String ch) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
			    SpikeID =0;
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
				LogHandle.info(LogType.pc, MessageFormat.format(
						"商品加入购物车成功! ProID:{0},userID:{1}", ProID, userid),
						"cart/addCart");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "prodetail.html", "/pc/cart/addCart", "商品加入购物车");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"商品加入购物车记录 异常:",
    								e, "/pc/cart/addCart");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("商品加入购物车失败!");
				LogHandle.info(LogType.pc, MessageFormat.format(
						"商品加入购物车失败! ProID:{0},userID:{1}", ProID, userid),
						"cart/addCart");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else{
				item.setDesc("商品加入购物车异常");
			}			
			LogHandle.error(LogType.pc,
					MessageFormat.format("商品加入购物车异常! 异常信息:{0}", e),
					"/cart/addCart");
		}
		return item.toJson();
	}

	/**
	 * 清空购物车
	 * 
	 * @return
	 */
	@RequestMapping(value = "/clearCart", produces = "text/html;charset=UTF-8")
	public String clearCart(String ch) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
			if (cartService.deleteByUser(userid) > 0) {
				item.setCode(0);
				item.setDesc("清空购物车成功!");
				LogHandle.info(LogType.pc,
						MessageFormat.format("清空购物车成功! userID:{0}", userid),
						"cart/clearCart");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cartlist.html", "/pc/cart/clearCart", "清空购物车");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"清空购物车记录 异常信息:",
    								e, "/pc/cart/clearCart");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("清空购物车失败!");
				LogHandle.info(LogType.pc,
						MessageFormat.format("清空购物车失败! userID:{0}", userid),
						"cart/clearCart");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else{
				item.setDesc("清空购物车异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("清空购物车异常! 异常信息:{0}", e),
					"/1/cart/clearCart");
		}
		return item.toJson();
	}

	/**
	 * 获取购物车数据
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getCartList", produces = "text/html;charset=UTF-8")
	public String getCartList(String ch) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
			item.setData(cartService.getByUser(userid,ActivityUsePlatformEnum.pc.getValue()));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else{
				item.setDesc("获取购物车数据异常");
			}	
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取购物车数据异常! 异常信息:{0}", e),
					"/cart/getCartList");
		}
		return item.toJson();
	}

	/**
	 * 编辑商品数量
	 * 
	 * @param id
	 * @param count
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/changeCount", produces = "text/html;charset=UTF-8")
	public String changeCount(String id, String count, String ch) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
				// int userid = user.getUserId();
				// // int userid =1;
				// item.setData(cartService.getByUser(userid));
				item.setCode(0);
				LogHandle.info(LogType.pc,
						MessageFormat.format("更新购物车商品数量成功! ProID:{0}", ID),
						"cart/changeCount");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cartlist.html", "/pc/cart/changeCount", "更新购物车商品数量");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"更新购物车商品数量记录 异常:",
    								e, "/pc/cart/changeCount");
						}
					}
				});
			} else {
				item.setCode(-200);
				LogHandle.info(LogType.pc,
						MessageFormat.format("更新购物车商品数量失败! ProID:{0}", ID),
						"cart/changeCount");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else{
				item.setDesc("编辑商品数量异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("编辑商品数量异常! 异常信息:{0}", e),
					"/cart/changeCount");
		}
		return item.toJson();
	}

	/**
	 * 获取购物车商品数量
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getCount", produces = "text/html;charset=UTF-8")
	public String getCount(String ch) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else{
				item.setDesc("获取购物车商品数量异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取购物车商品数量异常! 异常信息:{0}", e),
					"/cart/getCount");
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
	public String deleCartSpu(String id, String ch) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
				item.setCode(0);
				LogHandle.info(LogType.pc,
						MessageFormat.format("删除购物车商品成功! ID:{0}", ID),
						"cart/deleCartSpu");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cartlist.html", "/pc/cart/deleCartSpu", "操作说明（例：删除购物车商品）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"删除购物车商品记录 异常:",
    								e, "/pc/cart/deleCartSpu");
						}
					}
				});
			} else {
				item.setCode(-200);
				LogHandle.info(LogType.Api,
						MessageFormat.format("删除购物车商品失败! ID:{0}", ID),
						"cart/deleCartSpu");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else{
				item.setDesc("删除购物车商品异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("删除购物车商品异常! 异常信息:{0}", e),
					"/cart/deleCartSpu");
		}
		return item.toJson();
	}

	/**
	 * 批量删除购物车商品
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleCartSpuList", produces = "text/html;charset=UTF-8")
	public String deleCartSpuList(String ids, String ch) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
					item.setCode(0);
					LogHandle.info(LogType.pc,
							MessageFormat.format("批量删除购物车商品成功! IDs:{0}", ids),
							"cart/deleCartSpuList");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cartlist.html", "/pc/cart/deleCartSpuList", "批量删除购物车商品");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"批量删除购物车商品记录 异常:",
	    								e, "/pc/cart/deleCartSpuList");
							}
						}
					});
				} else {
					item.setCode(-200);
					LogHandle.info(LogType.pc,
							MessageFormat.format("批量删除购物车商品失败! IDs:{0}", ids),
							"cart/deleCartSpuList");
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("批量删除购物车商品异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("批量删除购物车商品异常! 异常信息:{0}", e),
					"/cart/deleCartSpuList");
		}
		return item.toJson();
	}

	/**
	 * 购物车选中或取消选中商品
	 * 
	 * @param token
	 * @param ch
	 * @param id
	 * @param sel
	 *            0-取消 1-选中
	 * @return
	 */
	@RequestMapping(value = "/selCart", produces = "text/html;charset=UTF-8")
	public String selCart(String ch, String id, String sel) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
				item.setDesc("选择商品参数错误！");
				return item.toJson();
			}

			if (cartService.updateSelect(StringUtilsEX.ToInt(id),
					StringUtilsEX.ToInt(sel)) > 0) {
				// int userid = user.getUserId();
				// // int userid=1;
				// item.setData(cartService.getByUser(userid));
				item.setDesc("更新成功");
				item.setCode(0);
				LogHandle.info(LogType.Api,
						MessageFormat.format("购物车选中或取消选中商品成功! ID:{0}", id),
						"cart/selCart");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cartlist.html", "/pc/cart/selCart", "购物车选中或取消选中商品");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"购物车选中或取消选中商品记录 异常:",
    								e, "/pc/cart/selCart");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("购物车选中或取消选中商品失败");
				LogHandle.info(LogType.pc,
						MessageFormat.format("购物车选中或取消选中商品失败! ID:{0}", id),
						"cart/selCart");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("购物车选中或取消选中商品异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("购物车选中或取消选中商品异常! 异常信息:{0}", e),
					"/cart/selCart");
		}
		return item.toJson();
	}

	/**
	 * 获取发票抬头信息
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getInvoiceTitle", produces = "text/html;charset=UTF-8")
	public String getInvoiceTitle(String ch) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
			// int userid=1;
			item.setData(invoiceService.getByUserId(userid));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("获取发票抬头信息异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取发票抬头信息异常! 异常信息:{0}", e),
					"/cart/getInvoiceTitle");
		}
		return item.toJson();
	}

	/**
	 * 会员登录批量上传购物车数据到数据库
	 * 
	 * @param token
	 * @param ch
	 * @param request
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addCarts", produces = "text/html;charset=UTF-8")
	public String addCarts(String ch, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			String cartstr = request.getParameter("cartstr");
			List<AddCartDto> cartDtos = new ArrayList<AddCartDto>();
			if (!StringUtilsEX.IsNullOrWhiteSpace(cartstr)) {
				JSONArray json = JSONArray.fromObject(cartstr);
				cartDtos = (List<AddCartDto>) JSONArray.toCollection(json,
						AddCartDto.class);
			} else {
				cartDtos = new ArrayList<AddCartDto>();
			}
			int userid = user.getUserId();
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
					item.setCode(0);
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cartlist.html", "/pc/cart/addCarts", "同步购物车数据失败");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"同步购物车数据失败记录 异常:",
	    								e, "/pc/cart/addCarts");
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("同步购物车数据失败!");
					LogHandle.info(LogType.Api, MessageFormat.format(
							"同步购物车数据失败! cartstr:{0},userID:{1}", cartstr,
							userid), "cart/addCarts");
				}
			} 
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("同步更新购物车数据异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("同步更新购物车数据异常! 异常信息:{0}", e),
					"/cart/addCarts");
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
	private V_ShopCart checkParam(AddCartDto dto, Integer userid,
			BaseResult item) throws Exception {
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
			SpikeID=0;
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

	/**
	 * 订单再次购买验证
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	private boolean checkProStatus(AddCartDto dto) throws Exception {
		Integer Protype = StringUtilsEX.ToInt(dto.getType());
		if (Protype < 0) {
			return false;
		}
		Integer ProID = StringUtilsEX.ToInt(dto.getProid());
		if (ProID <= 0) {
			return false;
		}
		switch (Protype) {
		case 1:
			// 组合商品
			Package pkage = packageService.getByID(ProID);
			if (pkage == null) {
				return false;
			}
			if (pkage.getEndtime().getTime() <= new Date().getTime()) {
				return false;
			}
			break;
		default:
			// 普通商品
			Sku sku = skuService.selectByID(ProID);
			if (sku == null) {
				return false;
			}
			SpuWithBLOBs spu = spuService.queryById(sku.getSpuId());
			if (spu == null) {
				return false;
			} else {
				if (spu.getStatus() != ProStatusEnum.上架.getValue()) {
					return false;
				}
			}
			break;
		}
		return true;
	}

	/**
	 * 购物车批量更改商品选中状态
	 * 
	 * @param token
	 * @param ch
	 * @param ids
	 * @param sels
	 *            0-取消 1-选中
	 * @return
	 */
	@RequestMapping(value = "/selCartList", produces = "text/html;charset=UTF-8")
	public String selCartList(String ch, String ids, String sels) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
				// int userid = user.getUserId();
				// // int userid=1;
				// item.setData(cartService.getByUser(userid));
				item.setCode(0);
				item.setDesc("更新成功");
				LogHandle.info(LogType.pc,
						MessageFormat.format("购物车批量更改商品选中状态成功! ID集合:{0}", ids),
						"cart/selCartList");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cartlist.html", "/pc/cart/selCartList", "购物车批量更改商品选中状态");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"购物车批量更改商品选中状态记录 异常:",
    								e, "/pc/cart/selCartList");
						}
					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("购物车选中或取消选中商品失败");
				LogHandle.info(LogType.pc,
						MessageFormat.format("购物车批量更改商品选中状态失败! ID集合:{0}", ids),
						"cart/selCartList");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("购物车批量更改商品选中状态异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("购物车批量更改商品选中状态异常! 异常信息:{0}", e),
					"/cart/selCartList");
		}
		return item.toJson();
	}

	/**
	 * 订单再次购买添加购物车
	 * 
	 * @param token
	 * @param ch
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/buyAgain", produces = "text/html;charset=UTF-8")
	public String buyAgain(String ch, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			String cartstr = request.getParameter("cartstr");
			if (StringUtilsEX.IsNullOrWhiteSpace(cartstr)) {
				item.setCode(-103);
				item.setDesc("商品数据不能为空！");
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
			List<V_ShopCart> vscList = new ArrayList<V_ShopCart>();
			V_ShopCart vsCart = null;
			for (AddCartDto dto : cartDtos) {
				vsCart = new V_ShopCart();
				// 验证商品是否下架或者过期（组合商品）
				if (checkProStatus(dto) == false) {
					continue;
				}
				vsCart = checkParam(dto, userid, item);
				if (item.getCode() < 0) {
					return item.toJson();
				}
				vscList.add(vsCart);
			}
			if (vscList.size() > 0) {
				if (cartService.addCarts(vscList) > 0) {
					item.setData(cartService.getByUser(userid,ActivityUsePlatformEnum.pc.getValue()));
					item.setCode(0);
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cartlist.html", "/pc/cart/buyAgain", "订单再次购买添加购物车");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"订单再次购买添加购物车记录 异常信息:",
	    								e, "/pc/cart/buyAgain");
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("再次购买失败!");
					LogHandle.info(LogType.pc, MessageFormat.format(
							"再次购买失败! cartstr:{0},userID:{1}", cartstr, userid),
							"cart/buyAgain");
				}
			} else {
				item.setData(cartService.getByUser(userid,ActivityUsePlatformEnum.pc.getValue()));
				item.setCode(0);
			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("再次购买异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("再次购买异常! 异常信息:{0}", e),
					"/cart/buyAgain");
		}
		return item.toJson();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getCookieCarts", produces = "text/html;charset=UTF-8")
	public String getCookieCarts(String ch, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			String cartstr = request.getParameter("cartstr");
			List<AddCartDto> cartDtos = new ArrayList<AddCartDto>();
			if (!StringUtilsEX.IsNullOrWhiteSpace(cartstr)) {
				JSONArray json = JSONArray.fromObject(cartstr);
				cartDtos = (List<AddCartDto>) JSONArray.toCollection(json,
						AddCartDto.class);
			} else {
				cartDtos = new ArrayList<AddCartDto>();
			}
			List<V_ShopCart> vscList = new ArrayList<V_ShopCart>();
			if (cartDtos != null && cartDtos.size() > 0) {
				V_ShopCart vsCart = null;
				for (AddCartDto dto : cartDtos) {
					vsCart = new V_ShopCart();
					vsCart = checkParam(dto, 0, item);
					if (item.getCode() < 0) {
						return item.toJson();
					}
					vscList.add(vsCart);
				}
			}
			if (vscList == null || vscList.size() == 0) {
				item.setCode(-200);
				item.setDesc("未检索到购物车数据！");
				return item.toJson();
			} else {
				item.setCode(0);
				item.setData(cartService.getByCookie(vscList,ActivityUsePlatformEnum.pc.getValue(),0));
			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("未登录状态获取购物车数据异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("未登录状态获取购物车数据异常! 异常信息:{0}", e),
					"/cart/getCookieCarts");
		}
		return item.toJson();
	}

	@RequestMapping(value = "/selShopCart", produces = "text/html;charset=UTF-8")
	public String selShopCart(String ch, String shopids, String sel) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(shopids)) {
				item.setCode(-103);
				item.setDesc("店铺ID参数不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(sel) < 0 || StringUtilsEX.ToInt(sel) > 1) {
				item.setCode(-104);
				item.setDesc("选择商品参数错误！");
				return item.toJson();
			}
			List<Integer> shopidList=new ArrayList<Integer>();
			
			for (String ids : shopids.split(",")) {
				if(StringUtilsEX.ToInt(ids)<=0){
					item.setCode(-104);
					item.setDesc("店铺ID参数错误！");
					return item.toJson();
				}else{
					shopidList.add(StringUtilsEX.ToInt(ids));
				}
			}
			if(shopidList.size()>0){
				if(cartService.updateSelectShop(shopidList,user.getUserId(), StringUtilsEX.ToInt(sel))>0){
					item.setCode(0);
					item.setDesc("更新成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cartlist.html", "/pc/cart/selShopCart", "更新店铺选择框");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"更新店铺选择框 异常信息:",
	    								e, "/pc/cart/selShopCart");
							}
						}
					});
				}else {
					item.setCode(-200);
					item.setDesc("更新店铺选择框异常");
				}
				
			}else{
				item.setCode(-201);
				item.setDesc("店铺ID为空");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.toString());
			}else {
				item.setDesc("购物车店铺选择异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("购物车店铺选择异常! 异常信息:{0}", e),
					"/cart/selShopCart");
		}
		return item.toJson();
	}
}

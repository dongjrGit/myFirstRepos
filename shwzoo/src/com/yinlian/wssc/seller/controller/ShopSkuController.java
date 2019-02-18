package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PriceTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.controller.CouponController;
import com.yinlian.wssc.platform.controller.SkuController;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.PriceTypeDTo;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.SkuValuesDto;
import com.yinlian.wssc.web.dto.SpecsValueDto;
import com.yinlian.wssc.web.po.ProductImgs;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuSpecsv;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductSpecsService;
import com.yinlian.wssc.web.service.ProductUpdateRecordsService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/seller/shopsku")
public class ShopSkuController {

	@Autowired
	private SkuService skuService;

	@Autowired
	private SpuService spuService;

	@Autowired
	private ProductSpecsService productSpecsService;

	@Autowired
	private ProductImgsService productImgsService;
	@Autowired
	private OperaterecordsService operaterecordsService ;
	
	SessionUser user = null;
	
	@Autowired
    private ProductUpdateRecordsService productUpdateRecordsService;
	
	
//
//	/**
//	 * 添加店铺库存商品
//	 * 
//	 * @param name
//	 * @param price
//	 * @param oldprice
//	 * @param appprice
//	 * @param spuid
//	 * @param stock
//	 * @param warnnum
//	 * @param remark
//	 * @return
//	 */
//	@RequestMapping("/addSku")
//	public @ResponseBody ReusltItem addSku(String name, String subtitle,
//			String price, String oldprice, String appprice, String spuid,
//			String stock, String wapprice, String wechatprice, String warnnum,
//			String remark, String img, HttpServletRequest request) {
//		ReusltItem item = new ReusltItem();
//		try {
//			Sku sku = new Sku();
//			sku = checkParam(name, subtitle, price, oldprice, appprice,
//					wapprice, wechatprice, spuid, stock, warnnum, remark, img,
//					"0", item);
//			if (item.getCode() < 0) {
//				return item;
//			}
//			// 规格值调整 不可输入的规格值 （新增） 库存关联规格值表（新增）
//			List<Specsvalues> sv_addlist = new ArrayList<Specsvalues>();
//			List<SkuSpecsv> ssv_addlist = new ArrayList<SkuSpecsv>();
//			Specsvalues sValue = null;
//			SkuSpecsv ssValues = null;
//			SpuWithBLOBs spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
//			List<SpecsValueDto> list = productSpecsService.getByClassId(spu
//					.getClassid());
//			for (SpecsValueDto dto : list) {
//				String value = request
//						.getParameter("specs_" + dto.getspecsID());
//				if (StringUtilsEX.isNotBlank(value)) {
//					if (dto.getisEntry() == true) {
//						Optional<Specsvalues> sv = dto.getvaluelist().stream()
//								.filter(x -> x.getValue().equals(value))
//								.findFirst();
//						if (sv.isPresent()) {
//							// 新增规格值关联
//							ssValues = new SkuSpecsv();
//							ssValues.setSpecsvId(sv.get().getId());
//							ssv_addlist.add(ssValues);
//						} else {
//							// 新增规格值
//							sValue = new Specsvalues();
//							sValue.setSpecsid(dto.getspecsID());
//							sValue.setStatus(0);
//							sValue.setValue(value);
//							sv_addlist.add(sValue);
//						}
//					} else {
//						if (dto.getIsmust() == 1
//								&& StringUtilsEX.ToInt(value) == 0) {
//							item.setCode(-111);
//							item.setDesc("规格名称：" + dto.getspecsName()
//									+ " 不能为空!");
//							return item;
//						}
//						int svid = StringUtilsEX.ToInt(value);
//						Optional<Specsvalues> sv = dto.getvaluelist().stream()
//								.filter(x -> x.getId() == svid).findFirst();
//						if (sv.isPresent()) {
//							// 新增规格值关联
//							ssValues = new SkuSpecsv();
//							ssValues.setSpecsvId(sv.get().getId());
//							ssv_addlist.add(ssValues);
//						}
//					}
//				} else {
//					if (dto.getIsmust() == 1) {
//						item.setCode(-111);
//						item.setDesc("规格名称：" + dto.getspecsName() + " 不能为空!");
//						return item;
//					}
//				}
//			}
//			int result=skuService.addSku(sku, sv_addlist, ssv_addlist);
//			if ( result> 0) {
//				logger.info(MessageFormat.format(
//						"添加库存商品成功,名称:{0},所属SpuID:{1},价格:{2},库存:{3}", name,
//						spuid, price, stock));
//				item.setCode(0);
//				item.setDesc("添加库存商品成功");
//				SessionUser user=SessionState.GetCurrentUser();
//                //异步操作 不影响正常流程
//                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//    			cachedThreadPool.execute(new Runnable() {
//    				@Override
//    				public void run() {
//    					try{
//    						operaterecordsService.insertOperaterecords(
//                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
//                            		user.getUserId(), user.getLoginName(), "添加库存商品页面", "/seller/shopsku/addSku", "添加库存商品");
//    					}
//    					catch(Exception e){
//    						LogHandle.error(LogType.OperateRecords,"添加添加库存商品操作记录出错! 异常信息:",
//    								e, "/seller/shopsku/addSku");
//    					}
//    					
//    				}
//    			});
//			} else {
//				item.setCode(-200);
//				item.setDesc("添加库存商品失败");
//				if(result==-2){
//					item.setDesc("此规格商品已存在");
//				}
//				logger.info(MessageFormat.format(
//						"添加库存商品失败,名称:{0},所属SpuID:{1},价格:{2},库存:{3}", name,
//						spuid, price, stock));
//
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error(SkuController.class + "添加库存商品异常", e);
//			item.setCode(-900);
//			if(DebugConfig.BLUETOOTH_DEBUG){
//				item.setDesc("添加库存商品失败异常：" + e.getMessage());
//			}else {
//				item.setDesc("添加库存商品失败");
//			}
//			
//		}
//
//		return item;
//	}
//
//	/**
//	 * 编辑库存商品
//	 * 
//	 * @param id
//	 * @param name
//	 * @param price
//	 * @param oldprice
//	 * @param appprice
//	 * @param spuid
//	 * @param stock
//	 * @param warnnum
//	 * @param remark
//	 * @return
//	 */
//	@RequestMapping("/updateSku")
//	public @ResponseBody ReusltItem updateSku(String id, String name,
//			String subtitle, String price, String oldprice, String appprice,
//			String wapprice, String wechatprice, String spuid, String stock,
//			String warnnum, String remark, String img,
//			HttpServletRequest request) {
//		ReusltItem item = new ReusltItem();
//		try {
//			Sku sku = new Sku();
//			sku = checkParam(name, subtitle, price, oldprice, appprice,wapprice,wechatprice, spuid,
//					stock, warnnum, remark, img, id, item);
//			if (item.getCode() < 0) {
//				return item;
//			}
//			// 规格值调整 不可输入的规格值 （新增,删除） 库存关联规格值表（新增，删除）
//			List<Integer> sv_dellist = new ArrayList<Integer>();
//			List<Specsvalues> sv_addlist = new ArrayList<Specsvalues>();
//			List<SkuSpecsv> ssv_addlist = new ArrayList<SkuSpecsv>();
//			Specsvalues sValue = null;
//			SkuSpecsv ssValues = null;
//			List<SkuValuesDto> listspvalueDtos = skuService.getValueBySkuID(sku
//					.getId());
//			SpuWithBLOBs spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
//			List<SpecsValueDto> list = productSpecsService.getByClassId(spu
//					.getClassid());
//			for (SpecsValueDto dto : list) {
//				String value = request
//						.getParameter("specs_" + dto.getspecsID());
//				if (StringUtilsEX.isNotBlank(value)) {
//					if (dto.getisEntry() == true) {
//						// 判断库存商品是否关联规格值
//						Optional<SkuValuesDto> svd = listspvalueDtos
//								.stream()
//								.filter(x -> x.getSpecsid() == dto.getspecsID())
//								.findFirst();
//						if (svd.isPresent()) {
//							if (svd.get().getValue().equals(value)) {
//								// 新增规格值关联
//								ssValues = new SkuSpecsv();
//								ssValues.setSpecsvId(svd.get().getValueid());
//								ssValues.setSkuId(sku.getId());
//								ssv_addlist.add(ssValues);
//							} else {
//								sv_dellist.add(svd.get().getSkuvalueid());
//								// 新增规格值
//								sValue = new Specsvalues();
//								sValue.setSpecsid(dto.getspecsID());
//								sValue.setStatus(0);
//								sValue.setValue(value);
//								sv_addlist.add(sValue);
//							}
//						} else {
//							Optional<Specsvalues> sv = dto.getvaluelist()
//									.stream()
//									.filter(x -> x.getValue().equals(value))
//									.findFirst();
//							if (sv.isPresent()) {
//								// 新增规格值关联
//								ssValues = new SkuSpecsv();
//								ssValues.setSpecsvId(sv.get().getId());
//								ssValues.setSkuId(sku.getId());
//								ssv_addlist.add(ssValues);
//							} else {
//								// 新增规格值
//								sValue = new Specsvalues();
//								sValue.setSpecsid(dto.getspecsID());
//								sValue.setStatus(0);
//								sValue.setValue(value);
//								sv_addlist.add(sValue);
//							}
//						}
//
//					} else {
//						if (dto.getIsmust() == 1
//								&& StringUtilsEX.ToInt(value) == 0) {
//							item.setCode(-111);
//							item.setDesc("规格名称：" + dto.getspecsName()
//									+ " 不能为空!");
//							return item;
//						}
//						int svid = StringUtilsEX.ToInt(value);
//						Optional<Specsvalues> sv = dto.getvaluelist().stream()
//								.filter(x -> x.getId() == svid).findFirst();
//						if (sv.isPresent()) {
//							// 新增规格值关联
//							ssValues = new SkuSpecsv();
//							ssValues.setSpecsvId(sv.get().getId());
//							ssValues.setSkuId(sku.getId());
//							ssv_addlist.add(ssValues);
//						}
//					}
//				} else {
//					if (dto.getIsmust() == 1) {
//						item.setCode(-111);
//						item.setDesc("规格名称：" + dto.getspecsName() + " 不能为空!");
//						return item;
//					}
//				}
//			}
//			int result=skuService.editSku(sku, sv_dellist, sv_addlist, ssv_addlist);
//			if ( result> 0) {
//				logger.info(MessageFormat.format(
//						"编辑库存商品成功,ID:{0},名称:{1},所属SpuID:{2},价格:{3},库存:{4}", id,
//						name, spuid, price, stock));
//				item.setCode(0);
//				item.setDesc("编辑库存商品成功");
//				SessionUser user=SessionState.GetCurrentUser();
//                //异步操作 不影响正常流程
//                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//    			cachedThreadPool.execute(new Runnable() {
//    				@Override
//    				public void run() {
//    					try{
//    						operaterecordsService.insertOperaterecords(
//                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
//                            		user.getUserId(), user.getLoginName(), "编辑库存商品页面", "/seller/shopsku/updateSku", "编辑库存商品");
//    					}
//    					catch(Exception e){
//    						LogHandle.error(LogType.OperateRecords,"添加编辑库存商品操作记录出错! 异常信息:",
//    								e, "/seller/shopsku/updateSku");
//    					}
//    					
//    				}
//    			});
//			} else {
//				logger.info(MessageFormat.format(
//						"编辑库存商品失败,ID:{0},名称:{1},所属SpuID:{2},价格:{3},库存:{4}", id,
//						name, spuid, price, stock));
//				item.setCode(-200);
//				item.setDesc("编辑库存商品失败");
//				if(result==-2){
//					item.setDesc("此规格商品已存在");
//				}
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error(SkuController.class + "编辑库存商品异常", e);
//			item.setCode(-900);
//			if(DebugConfig.BLUETOOTH_DEBUG){
//				item.setDesc("编辑库存商品失败异常：" + e.getMessage());
//			}else {
//				item.setDesc("编辑库存商品失败");
//			}
//			
//		}
//
//		return item;
//	}
//
//	/**
//	 * 参数验证
//	 * 
//	 * @param name
//	 * @param price
//	 * @param oldprice
//	 * @param appprice
//	 * @param spuid
//	 * @param stock
//	 * @param warnnum
//	 * @param remark
//	 * @param id
//	 * @param item
//	 * @return
//	 * @throws Exception
//	 */
//	private Sku checkParam(String name, String subtitle, String price,
//			String oldprice, String appprice, String wapprice,
//			String wechatprice, String spuid, String stock, String warnnum,
//			String remark, String img, String id, ReusltItem item)
//			throws Exception {
//		Sku sku = new Sku();
//		Integer ID = StringUtilsEX.ToInt(id);
//		if (ID < 0) {
//			item.setCode(-101);
//			item.setDesc("库存商品ID参数错误,id:" + id);
//			return null;
//		}
//
//		if (StringUtilsEX.isBlank(name)) {
//			item.setCode(-102);
//			item.setDesc("库存商品名称不能为空");
//			return null;
//		}
//		if (StringUtilsEX.ToInt(spuid) <= 0) {
//			item.setCode(-103);
//			item.setDesc("spuid参数错误，spuid：" + spuid);
//			return null;
//		}
//		if (spuService.queryById(StringUtilsEX.ToInt(spuid)) == null) {
//			item.setCode(-104);
//			item.setDesc("根据spuid未能获取到商品数据，spuid：" + spuid);
//			return null;
//		}
//
//		if (StringUtilsEX.ToInt(stock) < 0) {
//			item.setCode(-105);
//			item.setDesc("库存商品库存参数错误，stock：" + stock);
//			return null;
//		}
//		if (StringUtilsEX.ToInt(warnnum) < 0) {
//			item.setCode(-106);
//			item.setDesc("库存商品预警数量参数错误，warnnum：" + warnnum);
//			return null;
//		}
//		if (StringUtilsEX.ToFloat(price) < 0.00) {
//			item.setCode(-107);
//			item.setDesc("库存商品价格参数错误，price：" + price);
//			return null;
//		}
//		if (StringUtilsEX.ToFloat(oldprice) < 0.00) {
//			item.setCode(-108);
//			item.setDesc("库存商品原价参数错误，oldprice：" + oldprice);
//			return null;
//		}
//		if (StringUtilsEX.ToFloat(appprice) < 0.00) {
//			item.setCode(-109);
//			item.setDesc("库存商品App价格参数错误，appprice：" + appprice);
//			return null;
//		}
//		if (StringUtilsEX.ToFloat(wapprice) < 0.00) {
//			item.setCode(-110);
//			item.setDesc("库存商品Wap价格参数错误，wapprice：" + wapprice);
//			return null;
//		}
//		if (StringUtilsEX.ToFloat(wechatprice) < 0.00) {
//			item.setCode(-111);
//			item.setDesc("库存商品微信价格参数错误，微信价格：" + wechatprice);
//			return null;
//		}
//		if (ID > 0) {
//			sku = skuService.selectByID(ID);
//		} else {
//			sku.setSpuId(StringUtilsEX.ToInt(spuid));
//			sku.setNum(ProductNumUtil.getSkuNum());
//		}
//		 if(!StringUtilsEX.IsNullOrWhiteSpace(img)){
//	         	String [] imgstr=img.split(",");
//	         	if(imgstr.length>0){
//	         		if(imgstr.length==1){
//	         			sku.setImgurl(img);
//	         		}else{
//	         			sku.setImgurl(imgstr[1]);
//	         			sku.setImgurlApp(imgstr[0]);
//	         			sku.setImgurlWap(imgstr[0]);
//	         			sku.setImgurlWechat(imgstr[0]);
//	         			sku.setImgurlCart(imgstr[0]);
//	                    sku.setImgurlOrderdetail(imgstr[0]);
//	                    sku.setImgurlOrderlist(imgstr[0]);
//	         		}
//	         		
//	         	}
//	         }
//		sku.setName(name);
//		sku.setStock(StringUtilsEX.ToInt(stock));
//		sku.setWarnnum(StringUtilsEX.ToInt(warnnum));
//		sku.setPrice(StringUtilsEX.ToFloat(price));
//		sku.setOldprice(StringUtilsEX.ToFloat(oldprice));
//		sku.setAppprice(StringUtilsEX.ToFloat(appprice));
//		sku.setWapprice(StringUtilsEX.ToFloat(wapprice));
//		sku.setWechatprice(StringUtilsEX.ToFloat(wechatprice));
//		sku.setRemark(remark);
//		sku.setSubtitle(subtitle);
////		sku.setImgurlApp(img);
//		sku.setSalescount(0);
//		return sku;
//	}
//
//	/**
//	 * 删除库存商品 暂时不做（前台没有对应操作按钮）
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/delSku")
//	public @ResponseBody ReusltItem delSku(String id) {
//		ReusltItem item = new ReusltItem();
//
//		return item;
//	}
//
//	@RequestMapping("/getPriceType")
//	public @ResponseBody ReusltItem getPriceType() {
//		ReusltItem item = new ReusltItem();
//		try {
//			PriceTypeEnum[] array = PriceTypeEnum.values();
//
//			List<PriceTypeDTo> list = new ArrayList<PriceTypeDTo>();
//			for (int i = 0; i < array.length; i++) {
//				PriceTypeDTo dTo = new PriceTypeDTo();
//				dTo.setCode(PriceTypeEnum.values()[i].getValue());
//				dTo.setName(PriceTypeEnum.values()[i].name());
//				list.add(dTo);
//			}
//			item.setCode(0);
//			item.setData(list);
//		} catch (Exception e) {
//			item.setCode(-900);
//			item.setDesc("获取所有的价格类型异常：" + e.getMessage());
//			LogHandle.error(
//					LogType.userrecords,
//					MessageFormat.format("获取所有的价格类型异常! 异常信息:{0}",
//							e.getMessage()), "Sku/getPriceType");
//		}
//		return item;
//	}
//
//	/**
//	 * 库存商品调价
//	 * 
//	 * @param id
//	 *            库存商品ID
//	 * @param pricetype
//	 *            调价类型 0-售价 1-原价 2-APP价格
//	 * @param price
//	 * @return
//	 */
//	@RequestMapping("/updatePrice")
//	public @ResponseBody ReusltItem updatePrice(String id, String pricetype,
//			String price,String oldprice) {
//		ReusltItem item = new ReusltItem();
//		try {
//			user=SessionState.GetCurrentUser();
//			String ip = GetIpAddresss.getIpAddr();
//			if (StringUtilsEX.ToInt(id) <= 0) {
//				item.setCode(-101);
//				item.setDesc("库存商品ID参数错误,id:" + id);
//				return item;
//			}
//			if (StringUtilsEX.ToInt(pricetype) < 0
//					|| StringUtilsEX.ToInt(pricetype) > 4) {
//				item.setCode(-102);
//				item.setDesc("调价类型参数错误,pricetype:" + pricetype);
//				return item;
//			}
//			String priceType = "";
//			switch (StringUtilsEX.ToInt(pricetype)) {
//			case 0:
//				priceType = "售价";
//				break;
//			case 1:
//				priceType = "原价";
//				break;
//			case 2:
//				priceType = "APP价";
//				break;
//			case 3:
//				priceType = "Wap价";
//				break;
//			case 4:
//				priceType = "微信价";
//				break;
//			default:
//				break;
//			}
//			if (StringUtilsEX.ToFloat(price) <= 0.00f) {
//				item.setCode(-103);
//				item.setDesc("库存商品价格参数错误,price:" + price);
//				return item;
//			}
//			String newPrice=price+".00";
//			if (skuService.updatePrice(StringUtilsEX.ToInt(pricetype),
//					StringUtilsEX.ToFloat(price), StringUtilsEX.ToInt(id)) > 0) {
//				logger.info(MessageFormat.format(
//						"库存商品调价成功,ID:{0},价格:{1},类型:{2}", id, price, priceType));
//				item.setCode(0);
//				item.setDesc("库存商品调价成功");
//				SessionUser user=SessionState.GetCurrentUser();
//                //异步操作 不影响正常流程
//                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//    			cachedThreadPool.execute(new Runnable() {
//    				@Override
//    				public void run() {
//    					try{
//    						operaterecordsService.insertOperaterecords(
//                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
//                            		user.getUserId(), user.getLoginName(), "库存商品调价页面", "/seller/shopsku/updatePrice", "库存商品调价");
//    					}
//    					catch(Exception e){
//    						LogHandle.error(LogType.OperateRecords,"添加库存商品调价操作记录出错! 异常信息:",
//    								e, "/seller/shopsku/updatePrice");
//    					}
//    					
//    				}
//    			});
//    			//异步操作 不影响正常流程
//                ExecutorService cachedThreadPool1 = Executors.newCachedThreadPool();
//    			cachedThreadPool1.execute(new Runnable() {
//    				@Override
//    				public void run() {
//    					try{
//    						if(StringUtilsEX.ToInt(pricetype)==0){//PC售价
//    							productUpdateRecordsService.insertProTJ("Price", oldprice, newPrice, 
//    									StringUtilsEX.ToInt(id), user.getUserId(), user.getName(), ip);
//    						}else if(StringUtilsEX.ToInt(pricetype)==2){//APP售价
//    							productUpdateRecordsService.insertProTJ("AppPrice", oldprice, newPrice, 
//    									StringUtilsEX.ToInt(id), user.getUserId(), user.getName(), ip);
//    						}else if(StringUtilsEX.ToInt(pricetype)==3){//Wap售价
//    							productUpdateRecordsService.insertProTJ("WapPrice", oldprice, newPrice, 
//    									StringUtilsEX.ToInt(id), user.getUserId(), user.getName(), ip);
//    						}else if(StringUtilsEX.ToInt(pricetype)==4){//微信售价
//    							productUpdateRecordsService.insertProTJ("WeChatPrice", oldprice, newPrice, 
//    									StringUtilsEX.ToInt(id), user.getUserId(), user.getName(), ip);
//    						}
//    					}
//    					catch(Exception e){
//    						LogHandle.error(LogType.OperateRecords,"直营库存商品调价操作记录出错! 异常信息:",
//    								e, "/platform/Sku/updatePrice");
//    					}
//    					
//    				}
//    			});
//				
//			} else {
//				logger.info(MessageFormat.format(
//						"库存商品调价失败,ID:{0},价格:{1},类型:{2}", id, price, priceType));
//				item.setCode(-200);
//				item.setDesc("库存商品调价失败");
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error(SkuController.class + "编辑库存商品价格异常", e);
//			item.setCode(-900);
//			item.setDesc("异常：" + e.getMessage());
//		}
//
//		return item;
//	}
//
//	/**
//	 * 根据分类ID查询规格及规格值信息
//	 * 
//	 * @param classid
//	 * @return
//	 */
//	@RequestMapping("/getSpecsByClassID")
//	public @ResponseBody ReusltItem getSpecsByClassID(String classid) {
//		ReusltItem item = new ReusltItem();
//		try {
//			if (StringUtilsEX.ToInt(classid) <= 0) {
//				item.setCode(-101);
//				item.setDesc("分类ID参数错误,classid:" + classid);
//				return item;
//			}
//			item.setData(productSpecsService.getByFatherClassId(StringUtilsEX
//					.ToInt(classid)));
//			item.setCode(0);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error(SkuController.class + "根据分类ID查询规格及规格值信息异常", e);
//			item.setCode(-900);
//			item.setDesc("异常：" + e.getMessage());
//		}
//
//		return item;
//	}
//
//	/**
//	 * 根据库存商品ID获取关联规格值信息
//	 * 
//	 * @param skuid
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/getValueBySkuID")
//	public @ResponseBody ReusltItem getValueBySkuID(String skuid) {
//		ReusltItem item = new ReusltItem();
//		try {
//			if (StringUtilsEX.ToInt(skuid) <= 0) {
//				item.setCode(-101);
//				item.setDesc("库存商品ID参数错误,classid:" + skuid);
//				return item;
//			}
//			item.setData(skuService.getValueBySkuID(StringUtilsEX.ToInt(skuid)));
//			item.setCode(0);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error(SkuController.class + "根据分类ID查询规格及规格值信息异常", e);
//			item.setCode(-900);
//			item.setDesc("异常：" + e.getMessage());
//		}
//
//		return item;
//	}
//
//	/**
//	 * 添加库存商品图片
//	 * 
//	 * @param imgurl
//	 * @param orderby
//	 * @param skuid
//	 * @return
//	 */
//	@RequestMapping("/addSkuImage")
//	public @ResponseBody ReusltItem addSkuImage(String img, String orderby,
//			String skuid, String imgsite) {
//		ReusltItem item = new ReusltItem();
//		try {
//			if (StringUtilsEX.isBlank(img)) {
//				item.setCode(-101);
//				item.setDesc("图片不能为空");
//				return item;
//			}
//			if (StringUtilsEX.ToInt(orderby) < 0) {
//				item.setCode(-102);
//				item.setDesc("图片排序参数错误，orderby：" + orderby);
//				return item;
//			}
//			if (StringUtilsEX.ToInt(skuid) < 0) {
//				item.setCode(-103);
//				item.setDesc("库存ID参数错误，skuid：" + skuid);
//				return item;
//			}
//			if (StringUtilsEX.IsNullOrWhiteSpace(imgsite)) {
//				item.setCode(-104);
//				item.setDesc("图片使用站点不能为空");
//				return item;
//			}
//			ProductImgs imgs = new ProductImgs();
////			imgs.setImgurl(img);
//			if(!StringUtilsEX.IsNullOrWhiteSpace(img)){
//	         	String [] imgstr=img.split(",");
//	         	if(imgstr.length>0){
//	         		if(imgstr.length==1){
//	         			imgs.setImgurl(img);
//	         		}else{
//	         			imgs.setImgurl(imgstr[1]);
//	         			imgs.setImgpc(imgstr[1]);
//	         			imgs.setImgapp(imgstr[0]);
//	         			imgs.setImgwap(imgstr[0]);
//	         			imgs.setImgwechat(imgstr[0]);
//	         		}
//	         		
//	         	}
//	         }
//			imgs.setOrderby(StringUtilsEX.ToInt(orderby));
//			imgs.setSkuId(StringUtilsEX.ToInt(skuid));
//			imgs.setStatus(0);
//			imgs.setImgsite(imgsite);
//			imgs.setAddtime(new Date());
//
//			if (productImgsService.insert(imgs) > 0) {
//				logger.info(MessageFormat.format("添加库存商品图片成功,图片路径:{0}", img));
//				item.setCode(0);
//				item.setDesc("添加库存商品图片成功");
//				SessionUser user=SessionState.GetCurrentUser();
//                //异步操作 不影响正常流程
//                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//    			cachedThreadPool.execute(new Runnable() {
//    				@Override
//    				public void run() {
//    					try{
//    						operaterecordsService.insertOperaterecords(
//                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
//                            		user.getUserId(), user.getLoginName(), "添加库存商品图片页面", "/seller/shopsku/addSkuImage", "添加库存商品图片");
//    					}
//    					catch(Exception e){
//    						LogHandle.error(LogType.OperateRecords,"添加添加库存商品图片操作记录出错! 异常信息:",
//    								e, "/seller/shopsku/addSkuImage");
//    					}
//    					
//    				}
//    			});
//			} else {
//				logger.info(MessageFormat.format("添加库存商品图片失败,图片路径:{0}", img));
//				item.setCode(-200);
//				item.setDesc("添加库存商品图片失败");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error(SkuController.class + "添加库存商品图片异常", e);
//			item.setCode(-900);
//			item.setDesc("异常：" + e.getMessage());
//		}
//
//		return item;
//	}
//
//	/**
//	 * 编辑库存商品图片
//	 * 
//	 * @param id
//	 * @param imgurl
//	 * @param orderby
//	 * @param skuid
//	 * @return
//	 */
//	@RequestMapping("/editSkuImage")
//	public @ResponseBody ReusltItem editSkuImage(String id, String img,
//			String orderby, String skuid, String imgsite) {
//		ReusltItem item = new ReusltItem();
//		try {
//			if (StringUtilsEX.isBlank(img)) {
//				item.setCode(-101);
//				item.setDesc("图片不能为空");
//				return item;
//			}
//			if (StringUtilsEX.ToInt(orderby) < 0) {
//				item.setCode(-102);
//				item.setDesc("图片排序参数错误，orderby：" + orderby);
//				return item;
//			}
//			if (StringUtilsEX.ToInt(skuid) < 0) {
//				item.setCode(-103);
//				item.setDesc("库存ID参数错误，skuid：" + skuid);
//				return item;
//			}
//			if (StringUtilsEX.ToInt(id) < 0) {
//				item.setCode(-104);
//				item.setDesc("ID参数错误，id：" + id);
//				return item;
//			}
//			if (StringUtilsEX.IsNullOrWhiteSpace(imgsite)) {
//				item.setCode(-105);
//				item.setDesc("图片使用站点不能为空");
//				return item;
//			}
//			ProductImgs imgs = productImgsService
//					.selectByPrimaryKey(StringUtilsEX.ToInt(id));
////			imgs.setImgurl(img);
//			if(!StringUtilsEX.IsNullOrWhiteSpace(img)){
//	         	String [] imgstr=img.split(",");
//	         	if(imgstr.length>0){
//	         		if(imgstr.length==1){
//	         			imgs.setImgurl(img);
//	         		}else{
//	         			imgs.setImgurl(imgstr[1]);
//	         			imgs.setImgpc(imgstr[1]);
//	         			imgs.setImgapp(imgstr[0]);
//	         			imgs.setImgwap(imgstr[0]);
//	         			imgs.setImgwechat(imgstr[0]);
//	         		}
//	         		
//	         	}
//	         }
//			imgs.setOrderby(StringUtilsEX.ToInt(orderby));
//			imgs.setImgsite(imgsite);
//
//			if (productImgsService.updateByPrimaryKey(imgs) > 0) {
//				logger.info(MessageFormat.format("编辑库存商品图片成功,图片路径:{0},id:{1}",
//						img, id));
//				item.setCode(0);
//				item.setDesc("编辑库存商品图片成功");
//				SessionUser user=SessionState.GetCurrentUser();
//                //异步操作 不影响正常流程
//                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//    			cachedThreadPool.execute(new Runnable() {
//    				@Override
//    				public void run() {
//    					try{
//    						operaterecordsService.insertOperaterecords(
//                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
//                            		user.getUserId(), user.getLoginName(), "编辑库存商品图片页面", "/seller/shopsku/editSkuImage", "编辑库存商品图片");
//    					}
//    					catch(Exception e){
//    						LogHandle.error(LogType.OperateRecords,"添加编辑库存商品图片操作记录出错! 异常信息:",
//    								e, "/seller/shopsku/editSkuImage");
//    					}
//    					
//    				}
//    			});
//			} else {
//				logger.info(MessageFormat.format("编辑库存商品图片失败,图片路径:{0},id:{1}",
//						img, id));
//				item.setCode(-200);
//				item.setDesc("编辑库存商品图片失败");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error(SkuController.class + "编辑库存商品图片异常", e);
//			item.setCode(-900);
//			item.setDesc("异常：" + e.getMessage());
//		}
//
//		return item;
//	}
//
//	/**
//	 * 删除库存商品图片
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/delSkuImage")
//	public @ResponseBody ReusltItem delSkuImage(String id) {
//		ReusltItem item = new ReusltItem();
//		try {
//			if (StringUtilsEX.ToInt(id) < 0) {
//				item.setCode(-101);
//				item.setDesc("ID参数错误，id：" + id);
//				return item;
//			}
//			if (productImgsService.deleteByPrimaryKey(StringUtilsEX.ToInt(id)) > 0) {
//				logger.info(MessageFormat.format("删除库存商品图片成功,id:{0}", id));
//				item.setCode(0);
//				item.setDesc("删除库存商品图片成功");
//				SessionUser user=SessionState.GetCurrentUser();
//                //异步操作 不影响正常流程
//                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//    			cachedThreadPool.execute(new Runnable() {
//    				@Override
//    				public void run() {
//    					try{
//    						operaterecordsService.insertOperaterecords(
//                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
//                            		user.getUserId(), user.getLoginName(), "删除库存商品图片页面", "/seller/shopsku/delSkuImage", "删除库存商品图片");
//    					}
//    					catch(Exception e){
//    						LogHandle.error(LogType.OperateRecords,"添加删除库存商品图片操作记录出错! 异常信息:",
//    								e, "/seller/shopsku/delSkuImage");
//    					}
//    					
//    				}
//    			});
//			} else {
//				logger.info(MessageFormat.format("删除库存商品图片失败,id:{0}", id));
//				item.setCode(-200);
//				item.setDesc("删除库存商品图片失败");
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error(SkuController.class + "删除库存商品图片异常", e);
//			item.setCode(-900);
//			item.setDesc("异常：" + e.getMessage());
//		}
//
//		return item;
//	}
//
//	/**
//	 * 根据skuid获取库存商品图片
//	 * 
//	 * @param skuid
//	 * @return
//	 */
//	@RequestMapping("/getImageBySku")
//	public @ResponseBody ReusltItem getImageBySku(String skuid) {
//		ReusltItem item = new ReusltItem();
//		try {
//			if (StringUtilsEX.ToInt(skuid) < 0) {
//				item.setCode(-101);
//				item.setDesc("库存ID参数错误，skuid：" + skuid);
//				return item;
//			}
//			item.setData(productImgsService.selectBySku(StringUtilsEX
//					.ToInt(skuid)));
//			item.setCode(0);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error(SkuController.class + "根据skuid获取库存商品图片异常", e);
//			item.setCode(-900);
//			item.setDesc("异常：" + e.getMessage());
//		}
//
//		return item;
//	}
//
//	/**
//	 * 模糊检索库存商品
//	 * 
//	 * @param name
//	 * @return
//	 */
//	@RequestMapping("/getSkuStartWithName")
//	public @ResponseBody ReusltItem getSkuStartWithName(String name) {
//		ReusltItem item = new ReusltItem();
//		try {
//			int shopid = 1; // 所属店铺ID 默认为1
//			item.setData(skuService.getSkuStartWithName(shopid, name));
//			item.setCode(0);
//		} catch (Exception e) {
//			item.setCode(-900);
//			item.setDesc("模糊检索库存商品异常：" + e.getMessage());
//			logger.error(
//					CouponController.class + "模糊检索库存商品异常:" + e.getMessage(), e);
//		}
//		return item;
//	}
//
//	/**
//	 * 营销活动获取库存商品列表
//	 * 
//	 * @param name
//	 * @return
//	 */
//	@RequestMapping("/getactskubyname")
//	public @ResponseBody ReusltItem getActSkuStartWithName(String name) {
//		ReusltItem item = new ReusltItem();
//		try {
//			SessionUser user = SessionState.GetCurrentUser();
//			item.setData(skuService.getActSkuStartWithName(user.getShopid(),
//					name));
//			item.setCode(0);
//		} catch (Exception e) {
//			item.setCode(-900);
//			item.setDesc("模糊检索库存商品异常：" + e.getMessage());
//			LogHandle.error(LogType.Product, "模糊检索库存商品异常：" + e.toString(),
//					"platform/Sku/getactskubyname");
//		}
//		return item;
//	}
}

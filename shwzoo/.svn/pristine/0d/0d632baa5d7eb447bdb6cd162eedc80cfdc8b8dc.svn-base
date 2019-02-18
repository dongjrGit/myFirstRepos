package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.ImageTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.UserCollectTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_Detail_Specs;
import com.yinlian.api.app.dto.Api_SeachAtrrDto;
import com.yinlian.api.app.dto.CommentProDetailListDto;
import com.yinlian.pc.dto.InComeDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_ProductCriteria;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.search.Pc_TopicCriteria;
import com.yinlian.wssc.search.Wap_CommentCriteria;
import com.yinlian.wssc.web.dto.PackageDto;
import com.yinlian.wssc.web.dto.ProLuceneDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Goodconsult;
import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.po.ProductImgs;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.GoodConsultService;
import com.yinlian.wssc.web.service.ImagesService;
import com.yinlian.wssc.web.service.LuceneSearchService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuPackageService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.CriteriaGoodConsult;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import data.ParseUtil;

@Controller
@RequestMapping("/pc/products")
public class PCProductsController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UsercollectService usercollectService;
	@Autowired
	private ProductImgsService pimgSevice;
	@Autowired
	private SkuService skuService;
	@Autowired
	private SkuPackageService skuPackageService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private GoodConsultService goodconsultService;

	@Autowired
	private LuceneSearchService luceneSearchService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;
	@Autowired
	private ImagesService   imagesService;
    @Autowired
	private TopicService   topicService;
	/**
	 * 获取商品详细 页 评论信息
	 * 
	 * @param ch
	 * @param sid
	 * @param page
	 * @param size
	 * @param star
	 *            评论类型 2好评，1中评，0差评
	 * @return
	 */
	@RequestMapping(value = "/procomlist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductComment(String sid, String page,
			String size, String star) {
		ReusltItem item = new ReusltItem();
		String logpath = "products/1/" + "proComment";
		try {
			Integer spuid = StringUtilsEX.ToIntNull(sid);
			if (spuid == null || spuid <= 0) {
				item.setCode(-101);
				item.setDesc("spuid参数不正确！");
				return item.toJson();
			}
			Integer pindex = StringUtilsEX.ToIntNull(page);
			if (pindex == null || pindex <= 0) {
				pindex = 1;
			}
			Integer psize = StringUtilsEX.ToIntNull(size);
			if (psize == null || psize <= 0) {
				psize = 8;
			}
			Wap_CommentCriteria acc = new Wap_CommentCriteria();
			acc.setStar(StringUtilsEX.ToIntNull(star));
			if (acc.getStar() == null || acc.getStar() < 0) {
				acc.setStar(null);
			}
			acc.setSpuid(spuid);
			acc.setOrderByClause("date");
			acc.setSort("desc");
			PageBean dto = commentService.getProductDetailedCommentList(acc,
					pindex, psize);
			CommentProDetailListDto list=(CommentProDetailListDto) dto.getData();
			
			list.getList().forEach(f->{
				try {
					List<Images> imgLists=imagesService.queryImages(f.getId(),ImageTypeEnum.买家评价图片.getValue());
					f.setShowImgList(imgLists);
				} catch (Exception e) {
					if (DebugConfig.BLUETOOTH_DEBUG) {
						item.setDesc(e.toString());
					} else {
						item.setDesc("系统错误！");
					}
					item.setCode(-900);
					LogHandle
							.error(LogType.pc,
									MessageFormat.format("获取商品评论错误：{0}", e.toString()),
									logpath);
				}
			});
			if(star.equals("4")){
				list.setList(list.getList().stream().filter(a->a.getShowImgList()!=null && a.getShowImgList().size()>0 ).collect(Collectors.toList()));
			}	
			item.setPage(dto.getTp());
			item.setMaxRow(dto.getTr());
			item.setPageIndex(dto.getPc());
			item.setPageSize(dto.getPs());
			item.setData(list);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle
					.error(LogType.pc,
							MessageFormat.format("获取商品评论错误：{0}", e.toString()),
							logpath);
		}
		return item.toJson();
	}

	/**
	 * 收藏商品
	 * 
	 * @param token
	 * @param skuId
	 * @return
	 */
	@RequestMapping(value = "/collectSpu", produces = "text/html;charset=UTF-8")
	public @ResponseBody String collectSpu(String skuid, String ch,
			HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(skuid)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Integer userid = SessionUtil.getSessionUserId(request);
			Sku sku = skuService.selectByID(StringUtilsEX.ToInt(skuid));
			Integer spuid = 0;
			if (sku != null) {
				spuid = sku.getSpuId();

			}
			Usercollect usercollect = new Usercollect();
			usercollect.setUserid(userid);
			usercollect.setSpuid(spuid);
			usercollect.setType(UserCollectTypeEnum.商品.getValue());
			usercollect.setVaildflag(0);
			usercollect.setCreatetime(new Date());
			List<Usercollect> list = usercollectService
					.selectSpu(userid, spuid);
			if (list != null && list.size() >= 1) {
				item.setCode(-104);
				item.setDesc("你已收藏该商品了");
			} else {
				int temp = usercollectService.add(usercollect);
				if (temp == 0) {
					item.setCode(-104);
					item.setDesc("收藏商品失败");
				} else {
					item.setCode(0);
					item.setDesc("收藏商品成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "concern.html", "/pc/products/collectSpu", "收藏商品");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"收藏商品:",
	    								e, "/pc/products/collectSpu");
							}
						}
					});
				}
			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("收藏商品异常：" + e.getMessage());
			}else {
				item.setDesc("收藏商品异常");
			}			
			LogHandle.error(LogType.pc,
					MessageFormat.format("收藏商品异常! 异常信息:{0}", e.toString()),
					"products/collectSpu");
		}

		return item.toJson();
	}
	
	/**
	 * 收藏商品
	 * 
	 * @param token
	 * @param skuId
	 * @return
	 */
	@RequestMapping(value = "/collectbuySpu", produces = "text/html;charset=UTF-8")
	public @ResponseBody String collectbuySpu(String spuid, String ch,
			HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(spuid)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Integer userid = SessionUtil.getSessionUserId(request);
		
			Usercollect usercollect = new Usercollect();
			usercollect.setUserid(userid);
			usercollect.setSpuid(StringUtilsEX.ToInt(spuid));
			usercollect.setType(UserCollectTypeEnum.商品.getValue());
			usercollect.setVaildflag(0);
			usercollect.setCreatetime(new Date());
			List<Usercollect> list = usercollectService
					.selectSpu(userid, StringUtilsEX.ToInt(spuid));
			if (list != null && list.size() >= 1) {
				item.setCode(-104);
				item.setDesc("你已收藏该商品了");
			} else {
				int temp = usercollectService.add(usercollect);
				if (temp == 0) {
					item.setCode(-104);
					item.setDesc("收藏商品失败");
				} else {
					item.setCode(0);
					item.setDesc("收藏商品成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "concern.html", "/pc/products/collectSpu", "操作说明（例：收藏商品）");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"收藏商品:",
	    								e, "/pc/products/collectSpu");
							}
						}
					});
				}
			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("收藏商品异常：" + e.getMessage());
			}else {
				item.setDesc("收藏商品异常");
			}
			
			LogHandle.error(LogType.pc,
					MessageFormat.format("收藏商品异常! 异常信息:{0}", e.toString()),
					"products/collectSpu");
		}

		return item.toJson();
	}

	/**
	 * 收藏店铺
	 * 
	 * @param token
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/collectShop", produces = "text/html;charset=UTF-8")
	public @ResponseBody String collectShop(String shopId, String ch,
			HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(shopId)) {
				item.setCode(-102);
				item.setDesc("店铺id不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Integer userid = SessionUtil.getSessionUserId(request);
			Usercollect usercollect = new Usercollect();
			usercollect.setUserid(userid);
			usercollect.setShopid(StringUtilsEX.ToInt(shopId));
			usercollect.setType(UserCollectTypeEnum.店铺.getValue());
			usercollect.setVaildflag(0);
			usercollect.setCreatetime(new Date());

			List<Usercollect> list = usercollectService.selectShop(userid,
					StringUtilsEX.ToInt(shopId));
			if (list != null && list.size() >= 1) {
				item.setCode(-104);
				item.setDesc("你已收藏该店铺了");
			} else {
				int temp = usercollectService.add(usercollect);
				if (temp == 0) {
					item.setCode(-105);
					item.setDesc("收藏店铺失败");
				} else {
					item.setCode(0);
					item.setDesc("收藏店铺成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "concernslist.html", "/pc/products/collectShop", "操作说明（例：收藏店铺）");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"收藏店铺:",
	    								e, "/pc/products/collectShop");
							}
						}
					});
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("收藏店铺异常：" + e.getMessage());
			}else {
				item.setDesc("收藏店铺异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("收藏店铺异常! 异常信息:{0}", e.toString()),
					"products/collectShop");
		}
		return item.toJson();
	}

	/**
	 * 批量收藏商品
	 * 
	 * @param token
	 * @param skuId
	 * @return
	 */
	@RequestMapping(value = "/collectMoreSpu", produces = "text/html;charset=UTF-8")
	public @ResponseBody String collectSpus(String skuids, String ch,
			HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(skuids)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			boolean falg = true;
			Integer userid = SessionUtil.getSessionUserId(request);
			String[] ids = skuids.split(",");
			Usercollect usercollect = new Usercollect();
			usercollect.setUserid(userid);
			usercollect.setType(UserCollectTypeEnum.商品.getValue());
			usercollect.setVaildflag(0);
			usercollect.setCreatetime(new Date());
			for (int i = 0; i < ids.length; i++) {
				Sku sku = skuService.selectByID(StringUtilsEX.ToInt(ids[i]));
				Integer spuid = 0;
				if (sku != null) {
					spuid = sku.getSpuId();
				}
				usercollect.setSpuid(spuid);
				List<Usercollect> list = usercollectService.selectSpu(userid,
						spuid);

				if (list != null && list.size() >= 1) {
					item.setCode(-104);
					item.setDesc(sku.getName() + "你已收藏该商品了");
					return item.toJson();
				} else {
					int temp = usercollectService.add(usercollect);
					if (temp == 0) {
						falg = false;
						break;
					}
				}

			}
			if (falg == false) {
				item.setCode(-104);
				item.setDesc("收藏商品失败");
			} else {
				item.setCode(0);
				item.setDesc("收藏商品成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "concernslist.html", "/pc/products/collectMoreSpu", "操作说明（例：批量收藏商品）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"批量收藏商品:",
    								e, "/pc/products/collectMoreSpu");
						}
					}
				});
			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("批量收藏商品异常：" + e.getMessage());
			}else {
				item.setDesc("批量收藏商品异常");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("批量收藏商品异常! 异常信息:{0}", e.toString()),
					"products/collectMoreSpu");
		}

		return item.toJson();
	}

	/**
	 * 获取sku商品信息
	 * 
	 * @param skuId
	 * @return sku List<ProductImgs> selectBySku
	 */
	@RequestMapping(value = "/getskubyid", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getskubyid(String skuid) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(skuid) > 0) {
				Sku sku = skuService.selectByID(StringUtilsEX.ToInt(skuid));
				if (sku != null) {
					item.setCode(0);
					item.setData(sku);
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询sku详情异常：" + e.getMessage());
			}else {
				item.setDesc("查询商品详情异常");
			}
			
			LogHandle.error(LogType.pc, "查询sku详情异常! 异常信息:{0}", e,
					"products/getskubyid");
		}
		return item.toJson();
	}

	/**
	 * 获取sku商品图片
	 * 
	 * @param skuId
	 * @return List<ProductImgs>
	 */
	@RequestMapping(value = "/getimgsbyskuid", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getimgsbyskuid(String skuid) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(skuid) > 0) {
				List<ProductImgs> skuimgs = pimgSevice
						.selectBySku(StringUtilsEX.ToInt(skuid));
				if (skuimgs != null) {
					item.setCode(0);
					item.setData(skuimgs);
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询sku图片异常：" + e.getMessage());
			}else {
				item.setDesc("图片异常");
			}
			
			LogHandle.error(LogType.pc, "查询sku图片异常! 异常信息:{0}", e,
					"products/getimgsbyskuid");
		}
		return item.toJson();
	}

	@RequestMapping(value = "/getspecsbyskuid", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getspecsbyskuid(String skuid, String mark) {
		ReusltItem item = new ReusltItem();
		try {
			Integer skid = StringUtilsEX.ToInt(skuid);
			if (skid < 0) {
				item.setCode(-101);
				item.setDesc("skuid参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mark)) {
				item.setCode(-102);
				item.setDesc("专题标识/位置参数错误");
				return item.toJson();
			}

			List<Api_Detail_Specs> infospeces = productService.getProductSpecs(
					skid, mark);
			item.setCode(0);
			item.setData(infospeces);

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询sku规格异常：" + e.getMessage());
			}else {
				item.setDesc("查询sku规格异常");
			}			
			LogHandle.error(LogType.pc, "查询sku规格异常! 异常信息:{0}", e,
					"products/getspecsbyskuid");
		}
		return item.toJson();
	}

	/**
	 * 咨询
	 * 
	 * @author kh.wang
	 * @since 2016年7月25日
	 * @return
	 */
	@RequestMapping("/addConsult")
	public @ResponseBody BaseResult addConsult(String spuId, String shopId,
			String consultType, String consultContent,
			HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			Goodconsult goodconsult = new Goodconsult();
			goodconsult.setReplydate(new Date());
			goodconsult.setStatus(0);
			goodconsult.setSpuid(ParseUtil.toInteger(spuId));
			SessionUser user = SessionUtil.getSessionUser(request);
			if (user == null) {
				item.setCode(201);
				item.setDesc("用户未登录");
				return item;
			}
			goodconsult.setConsultid(user.getUserId());
			goodconsult.setSellerid(ParseUtil.toInteger(shopId));
			goodconsult.setConsulttype(ParseUtil.toInteger(consultType));
			goodconsult.setVaildflag(0);
			goodconsult.setConsultcontent(consultContent);
			goodconsult.setConsultdate(new Date());
			if (goodconsultService.addConsult(goodconsult) > 0) {
				item.setDesc("提交成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "prodetail_tap.html", "/pc/products/addConsult", "操作说明（例：咨询）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"咨询:",
    								e, "/pc/products/addConsult");
						}
					}
				});
			} else {
				item.setCode(500);
				item.setDesc("咨询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("咨询异常：" + e.getMessage());
			}else {
				item.setDesc("咨询异常");
			}			
			LogHandle.error(LogType.pc,
					MessageFormat.format("咨询异常! 异常信息:{0}", e.toString()),
					"products/addConsult");
		}
		return item;
	}

	@RequestMapping("/getConsultPage")
	public @ResponseBody ReusltItem getConsultPage(String page, String size,
			String spuId) {
		ReusltItem item = new ReusltItem();
		try {

			if (StringUtilsEX.IsNullOrWhiteSpace(spuId)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item;
			}
			CriteriaGoodConsult consult = new CriteriaGoodConsult();
			consult.setSpuId(spuId);
			PageBean pageBean = goodconsultService.queryPage(consult,
					ParseUtil.toInteger(page), ParseUtil.toInteger(size));
			item.setData(pageBean.getData());
			item.setPage(pageBean.getTp());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setPageSize(pageBean.getPs());
			item.setData(pageBean.getBeanList());
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("咨询列表异常:"+e.toString());
			}else {
				item.setDesc("咨询列表异常！");
			}
			LogHandle.error(LogType.pc,
					MessageFormat.format("咨询列表异常", e.getMessage()),
					"/pc/products/getConsultPage");
		}
		return item;
	}

	/**
	 * 查询组合商品
	 * 
	 * @param skuid
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/selectPackage", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectPackage(String skuid) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.ToInt(skuid) < 0) {
				item.setCode(-101);
				item.setDesc("库存商品id参数错误！");
				return item.toJson();
			}
			List<PackageDto> pklist = skuPackageService.queryBySkuId(
					StringUtilsEX.ToInt(skuid),
					ActivityUsePlatformEnum.pc.getValue());
			item.setCode(0);
			item.setData(pklist);
			item.setDesc("查询组合商品列表成功");
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询组合商品异常：" + e.getMessage());
			}else{
				item.setDesc("查询组合商品异常");
			}
			
			LogHandle.error(LogType.pc,
					MessageFormat.format("查询组合商品异常! 异常信息:{0}", e.toString()),
					"products/selectPackage");
		}
		return item.toJson();
	}

	@RequestMapping(value = "/getprolist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProList(String cid, String page,
			String size, String searchstr, String orderby, String ordertype,
			String minprice, String maxprice, String keywords, String ch) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer page1 = StringUtilsEX.ToIntNull(page);
			Integer size11 = StringUtilsEX.ToIntNull(size);
			if (page1 == null || page1 <= 0) {
				page1 = 1;
			}
			if (size11 == null || size11 <= 0) {
				size11 = 20;
			}
			Api_ProductCriteria criteria = new Api_ProductCriteria();
			if (!StringUtilsEX.IsNullOrWhiteSpace(searchstr)) {
				List<Api_SeachAtrrDto> list = JSON.parseArray(searchstr,
						Api_SeachAtrrDto.class);
				if (list != null) {
					criteria.setSeachatrrdtos(list);
				}
			}
			criteria.setMaxprice(StringUtilsEX.ToDoubleNull(maxprice));
			criteria.setMinprice(StringUtilsEX.ToDoubleNull(minprice));
			// criteria.setClsid(clsid1);
			if (!StringUtilsEX.IsNullOrWhiteSpace(keywords)) {
				//criteria.setKeyword(keywords.trim());
				List<ProLuceneDto> list = luceneSearchService.getProidList(keywords.trim());
				String ids = "";					
				if (list != null && list.size() > 0) {
					for (ProLuceneDto proLuceneDto : list) {
						if (ids == "") {
							ids += proLuceneDto.getId();
						} else {
							ids += "," + proLuceneDto.getId();
						}
					}
					criteria.setIdliststr(ids);
				}else{
					criteria.setIdliststr("0");
				}
			}
			int classid = StringUtilsEX.ToInt(cid);
			if (classid > 0) {
				criteria.setFullpath(cid);
			}
			Integer s = StringUtilsEX.ToIntNull(orderby);
			Integer s1 = StringUtilsEX.ToIntNull(ordertype);
			s = s == null ? 0 : s;
			s1 = s1 == null ? 0 : s1;
			switch (s) {
			case 1:// 价格
				criteria.setOrderByClause("p.price");
				break;
			case 2:// 评论数
				criteria.setOrderByClause("p.CommentCount");
				break;
			default:// 销量
				criteria.setOrderByClause("SalesCount");
				break;
			}
			switch (s1) {
			case 1:// 升序
				criteria.setSort("asc");
				break;
			case 0:// 降序
			default:
				criteria.setSort("desc");
				break;
			}
			PageBean list = productService.getSerachProductListPage_pc(
					criteria, page1, size11);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPageIndex(page1);

		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询商品列表异常：" + e.getMessage());
			}else {
				item.setDesc("查询商品列表异常");
			}
			
			LogHandle.error(LogType.pc, "查询商品列表异常!", e, "products/getProList");
		}
		return item.toJson();
	}


	@RequestMapping(value = "/getpostlist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getpostlist(String cid, String page,
			String size, String searchstr, String orderby, String ordertype,
			String minprice, String maxprice, String keywords, String ch) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			
			Integer page1 = StringUtilsEX.ToIntNull(page);
			Integer size11 = StringUtilsEX.ToIntNull(size);
			if (page1 == null || page1 <= 0) {
				page1 = 1;
			}
			if (size11 == null || size11 <= 0) {
				size11 = 20;
			}
			Api_ProductCriteria criteria = new Api_ProductCriteria();
			if (!StringUtilsEX.IsNullOrWhiteSpace(searchstr)) {
				List<Api_SeachAtrrDto> list = JSON.parseArray(searchstr,
						Api_SeachAtrrDto.class);
				if (list != null) {
					criteria.setSeachatrrdtos(list);
				}
			}

			criteria.setIspostage("1");
			criteria.setMaxprice(StringUtilsEX.ToDoubleNull(maxprice));
			criteria.setMinprice(StringUtilsEX.ToDoubleNull(minprice));

			if (!StringUtilsEX.IsNullOrWhiteSpace(keywords)) {
				criteria.setKeyword(keywords.trim());
			}
			Integer s = StringUtilsEX.ToIntNull(orderby);
			Integer s1 = StringUtilsEX.ToIntNull(ordertype);
			s = s == null ? 0 : s;
			s1 = s1 == null ? 0 : s1;
			switch (s) {
			case 1:// 价格
				criteria.setOrderByClause("p.price");
				break;
			case 2:// 评论数
				criteria.setOrderByClause("p.CommentCount");
				break;
			default:// 销量
				criteria.setOrderByClause("SalesCount");
				break;
			}
			switch (s1) {
			case 1:// 升序
				criteria.setSort("asc");
				break;
			case 0:// 降序
			default:
				criteria.setSort("desc");
				break;
			}
			PageBean list = productService.getSerachProductListPage_pc(
					criteria, page1, size11);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPageIndex(page1);

		} catch (Exception e) {	
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询商品列表异常：" + e.getMessage());
			}else {
				item.setDesc("查询商品列表异常");
			}			
			LogHandle.error(LogType.pc, "查询商品列表异常!", e, "products/getpostlist");
		}
		return item.toJson();
	}
	/**
	 * 根据专题id 获取专题商品列表
	 * @param topicid 专题id
	 * @param page
	 * @param size
	 * @param orderby 排序类型 1价格，2评论，3销量
	 * @param ordertype 排序顺序 1升序，0降序
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/gettopicprolist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String gettopicprolist(String topicid, String page,
			String size, String orderby, String ordertype,
			  String ch) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(topicid)<=0) {
				item.setCode(-102);
				item.setDesc("专题id参数错误！");
				return item.toJson();
			}
			Integer index = StringUtilsEX.ToInt(page);
			Integer psize = StringUtilsEX.ToInt(size);
			if (index <= 0) {
				index = 1;
			}
			if (psize <= 0) {
				psize = 20;
			}
			Api_TopicBySpuCriteria criteria = new Api_TopicBySpuCriteria();
			criteria.setTopicid(StringUtilsEX.ToInt(topicid));
			Integer s = StringUtilsEX.ToIntNull(orderby);
			Integer s1 = StringUtilsEX.ToIntNull(ordertype);
			s = s == null ? 0 : s;
			s1 = s1 == null ? 0 : s1;
			switch (s) {
			case 1:// 价格
				criteria.setOrderByClause("price");
				break;
			case 2:// 评论数
				criteria.setOrderByClause("commentcount");
				break;
			default:// 销量
				criteria.setOrderByClause("salesvolume");
				break;
			}
			switch (s1) {
			case 1:// 升序
				criteria.setSort("asc");
				break;
			case 0:// 降序
			default:
				criteria.setSort("desc");
				break;
			}
			PageBean pageBean =topicService.getTopicBySpu(criteria, index, psize);
					
			item.setData(pageBean.getBeanList());
			item.setPage(pageBean.getTp());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setPageSize(pageBean.getPs());

		} catch (Exception e) {	
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询商品列表异常：" + e.getMessage());
			}else {
				item.setDesc("查询商品列表异常");
			}			
			LogHandle.error(LogType.pc, "查询商品列表异常!", e, "/products/gettopicprolist");
		}
		return item.toJson();
	}
	
	@RequestMapping(value = "/getsearchattrlist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getsearchAttrList(String claid, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(claid) <= 0) {
				item.setCode(-101);
				item.setDesc("分类ID参数错误！");
				return item.toJson();
			}
			List<Api_SeachAtrrDto> list = productService.getApiSeachAtrrList(
					StringUtilsEX.ToInt(claid),
					ActivityUsePlatformEnum.pc.getValue());
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("根据分类ID查询商品搜索属性异常：" + e.getMessage());
			}else {
				item.setDesc("查询商品搜索属性异常");
			}			
			LogHandle.error(LogType.pc, "根据分类ID查询商品搜索属性异常!", e,
					"products/getsearchattrlist");
		}
		return item.toJson();
	}

	@RequestMapping(value = "/getclasslist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getclassList(String keywords, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(keywords)) {
				item.setCode(-101);
				item.setDesc("关键字不能为空！");
				return item.toJson();
			}
			List<InComeDto> classlist = new ArrayList<InComeDto>();
			List<ProLuceneDto> list = luceneSearchService.getProidList(keywords
					.trim());
			if (list != null && list.size() > 0) {
				InComeDto claDto = null;
				Map<Integer, List<ProLuceneDto>> classgroup = list
						.stream()
						.collect(
								Collectors.groupingBy(ProLuceneDto::getClassid));
				for (Map.Entry<Integer, List<ProLuceneDto>> entry : classgroup
						.entrySet()) {
					claDto = new InComeDto();
					claDto.setCode(entry.getKey());
					claDto.setName(entry.getValue().get(0).getClassname());
					classlist.add(claDto);
				}
			}
			item.setCode(0);
			item.setData(classlist);
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("根据关键字获取分类列表异常：" + e.getMessage());
			}else {
				item.setDesc("根据关键字获取分类列表异常");
			}
			
			LogHandle.error(LogType.pc, "根据关键字获取分类列表异常!", e,
					"products/getclasslist");
		}
		return item.toJson();
	}
	//获取分页专题
	@RequestMapping(value = "/gettopiclist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String gettopiclist(String index, String size) {
		ReusltItem item = new ReusltItem();
		try {
			int  pindex=1;
			int psize=3;
			if (StringUtilsEX.ToInt(index) >0&&StringUtilsEX.ToInt(size)>0) {
				pindex=StringUtilsEX.ToInt(index);
				psize=StringUtilsEX.ToInt(size);
			}
			
			Pc_TopicCriteria criteria = new Pc_TopicCriteria();
			criteria.setMark(TopicMarkEnum.猜你喜欢.getValue());
			criteria.setType(TopicTypeEnum.商品.getValue());
			criteria.setWebset(Integer.toString(WebSetEnum.pc.getValue()));
			criteria.setPagetag(PageMarkType.商品详情页.getValue());
			PageBean pageBean = topicService.getPcTopByPage(criteria, pindex, psize);
			
			item.setPage(pageBean.getTp());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setPageSize(pageBean.getPs());
			item.setData(pageBean.getBeanList());
			
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("系统错误");
			LogHandle.error(LogType.pc, "查询商品专题分页异常!", e,
					"products/gettopiclist");
		}
		return item.toJson();
	}

}

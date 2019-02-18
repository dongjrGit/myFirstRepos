package com.yinlian.api.wap.controller;

import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ProductDetailedDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_ProductCriteria;
import com.yinlian.wssc.search.Wap_CommentCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Browsehistory;
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/wap/products")
public class ProductsWapController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UsercollectService usercollectService;
	@Autowired
	private BrowsehistoryService browsehistoryService;
	@Autowired
	private ProductImgsService productImgService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private MessageService messageService;

	@RequestMapping("/getProducts")
	public @ResponseBody ReusltItem getProducts(String index, String size,
			String classID, String orderBy, String orderType) {
		ReusltItem item = new ReusltItem();
		try {
			Integer pageIndex = StringUtilsEX.ToIntNull(index);
			Integer pageSize = StringUtilsEX.ToIntNull(size);
			if (pageIndex == null || pageIndex <= 0) {
				pageIndex = 1;
			}
			if (pageSize == null || pageSize <= 0) {
				pageSize = 2;
			}
		    Api_ProductCriteria pc = new Api_ProductCriteria();
			pc.setClsid(StringUtilsEX.ToInt(classID));
			pc.setOrderByClause(orderBy);
			pc.setSort(orderType);
			PageBean bean = productService.getWapProductList(pc, pageIndex, pageSize);
			item.setData(bean.getBeanList());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取商品列表数据错误：{0}", e, "Wap/Product");
		}
		return item;
	}

	/**
	 * 商品详细
	 * 
	 * @param ch
	 *            通道 3wap
	 * @param sid
	 *            spuId
	 * @param type
	 *            商品类型 1闪购
	 * @return
	 */
	@RequestMapping(value = "/getprodetail", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailed(String ch, String sid,
			String type) {
		BaseResult item = new BaseResult();
		String logpath = "products/" + ch + "/" + "getprodetail";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer itype = StringUtilsEX.ToInt(type) >= 0 ? StringUtilsEX
					.ToInt(type) : 0;
			Integer spuid = StringUtilsEX.ToIntNull(sid);
			if (spuid == null || spuid <= 0) {
				item.setCode(-102);
				item.setDesc("spuid参数不正确！");
				return item.toJson();
			}
			ProductDetailedDto dto = productService.getProductDetailed(spuid,
					itype, ActivityUsePlatformEnum.wap.getValue());
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() >= 0) {

				dto.setIsfavorites(usercollectService.IsCollectSpu(
						user.getUserId(), spuid));
			}
			item.setData(dto);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "获取商品详细错误：{0}", e, logpath);
		}
		return item.toJson();
	}

	/**
	 * 根据skuid商品详细
	 * 
	 * @param ch
	 * @param sid
	 *            spuId
	 * @param kid
	 *            SkuId
	 * @param type
	 *            商品类型 1闪购
	 * @return
	 */
	@RequestMapping(value = "/getprodetailedbyskuid", produces = "text/html;charset=UTF-8")
	public String getProductDetailedBySkuid(String ch, String sid, String kid,
			String type) {
		BaseResult item = new BaseResult();
		String logpath = "product/" + ch + "/" + "getprodetailedbyskuid";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer itype = StringUtilsEX.ToInt(type) >= 0 ? StringUtilsEX.ToInt(type) : 0;

			Integer skuid = StringUtilsEX.ToIntNull(kid);
			if (skuid == null || skuid <= 0) {
				item.setCode(-103);
				item.setDesc("skuid参数不正确！");
				return item.toJson();
			}
			Integer spuid = StringUtilsEX.ToIntNull(sid);
			if (spuid == null || spuid <= 0) {
				item.setCode(-102);
				item.setDesc("spuid参数不正确！");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser();
			ProductDetailedDto dto = productService
					.getProductDetailedBySkuId(spuid, skuid, itype,
							ActivityUsePlatformEnum.wap.getValue());
			if (user.getCode() >= 0) {
				dto.setIsfavorites(usercollectService.IsCollectSpu(
						user.getUserId(), spuid));
				Browsehistory browsehistory = new Browsehistory();
				browsehistory.setUserid(user.getUserId());
				browsehistory.setSpuid(spuid);
				browsehistory.setChanneltype(StringUtilsEX.ToInt(ch));
				browsehistory.setBrowsetime(new Date());
				browsehistory.setCreatetime(new Date());
				browsehistory.setVaildflag(0);
				browsehistoryService.insert(browsehistory);
			}
			item.setData(dto);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "获取商品详细错误：{0}", e, logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取商品详细页详细售后服务
	 * 
	 * @param ch
	 * @param sid
	 * @return
	 */
	@RequestMapping(value = "/getprodass", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailedAfterSaleService(String ch,
			String sid) {
		BaseResult item = new BaseResult();
		String logpath = "products/" + ch + "/" + "getprodass";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer spuid = StringUtilsEX.ToIntNull(sid);
			if (spuid == null || spuid <= 0) {
				item.setCode(-103);
				item.setDesc("spuid参数不正确！");
				return item.toJson();
			}
			// URLEncoder.encode(productService.getApigetProductDetailedAfterSaleService(spuid),
			// "UTF-8")
			item.setData(productService
					.getApigetProductDetailedAfterSaleService(spuid));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取商品详细（店铺店铺）错误：{0}", e,
					logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取商品详细 页 评论信息
	 * 
	 * @param ch
	 * @param sid
	 * @param page
	 * @param size
	 * @param star
	 *            评论类型
	 * @return
	 */
	@RequestMapping(value = "/proCommentList", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductComment(String ch, String sid,
			String page, String size, String star) {
		ReusltItem item = new ReusltItem();
		String logpath = "products/" + ch + "/" + "proComment";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer spuid = StringUtilsEX.ToIntNull(sid);
			if (spuid == null || spuid <= 0) {
				item.setCode(-102);
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
			item.setPage(dto.getTp());
			item.setMaxRow(dto.getTr());
			item.setPageIndex(dto.getPc());
			item.setPageSize(dto.getPs());
			item.setData(dto.getData());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取商品详细（店铺店铺）错误：{0}", e,
					logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取商品详细页中详细 商品描述
	 * 
	 * @param ch
	 * @param sid
	 *            spuid
	 * @return
	 */
	@RequestMapping(value = "/getproddesc", produces = "text/html;charset=UTF-8")
	public String getProductDetailedDesc(String ch, String sid) {
		BaseResult item = new BaseResult();
		String logpath = "products/" + ch + "/" + "getproddesc";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}

			Integer spuid = StringUtilsEX.ToIntNull(sid);
			if (spuid == null || spuid <= 0) {
				item.setCode(-103);
				item.setDesc("skuid参数不正确！");
				return item.toJson();
			}
			item.setData(URLEncoder.encode(
					productService.getApiProDetailDesc(spuid), "UTF-8"));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取商品详细（店铺店铺）错误：{0}", e,
					logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取商品详细中详细商品规格
	 * 
	 * @param ch
	 * @param kid
	 * @return
	 */
	@RequestMapping(value = "/getprodspecinfo", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailedSpecsInfo(String ch,
			String kid) {
		BaseResult item = new BaseResult();
		String logpath = "products/" + ch + "/" + "getprodspec";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer skuid = StringUtilsEX.ToIntNull(kid);
			if (skuid == null || skuid <= 0) {
				item.setCode(-103);
				item.setDesc("skuid参数不正确！");
				return item.toJson();
			}
			item.setData(productService.getProductDetailedSpecsInfo(skuid));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取商品详细（店铺店铺）错误：{0}", e,
					logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取商品详细页中 选择规格
	 * 
	 * @param ch
	 * @param sid
	 * @param kid
	 * @return
	 */
	@RequestMapping(value = "/getprodspecs", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailedSpecs(String ch, String sid) {
		BaseResult item = new BaseResult();
		String logpath = "product/" + ch + "/" + "getprodspecs";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer spuid = StringUtilsEX.ToIntNull(sid);
			if (spuid == null || spuid <= 0) {
				item.setCode(-103);
				item.setDesc("spuid参数不正确！");
				return item.toJson();
			}
			item.setData(productService.getProductDetailedSpecs(spuid));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取商品详细（店铺店铺）错误：{0}", e,
					logpath);
		}
		return item.toJson();
	}

	/**
	 * 根据分类id获取最新商品
	 * 
	 * @param ch
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/getshelvesnewspro", produces = "text/html;charset=UTF-8")
	public String getNewsPro(String ch, String cid) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer classid = StringUtilsEX.ToIntNull(cid);
			if (classid == null || classid <= 0) {
				item.setCode(-102);
				item.setDesc("cid参数不正确！");
				return item.toJson();
			}
			item.setData(productService.getgetNewsShelvesProByCid(classid,WebSetEnum.wap.getValue()));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"根据分类id获取最新商品 错误：{0}", e,
					"products/getshelvesnewspro");
		}
		return item.toJson();
	}

	/**
	 * 根据分类id获取热销商品
	 * 
	 * @param ch
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/getsellingnewspro", produces = "text/html;charset=UTF-8")
	public String getNewsSellingPro(String ch, String cid) {
		String logpath = "products/" + ch + "/" + "getNewsSellingPro";
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer classid = StringUtilsEX.ToIntNull(cid);
			if (classid == null || classid <= 0) {
				item.setCode(-102);
				item.setDesc("cid参数不正确！");
				return item.toJson();
			}
			item.setData(productService.getgetNewsSellingProByCid(classid,WebSetEnum.wap.getValue()));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle
					.error(LogType.wap,"获取热销商品错误：{0}", e,
							logpath);
		}
		return item.toJson();
	}

	/**
	 * 根据skuid获取商品信息
	 * 
	 * @param ch
	 * @param skuid
	 * @return
	 */
	@RequestMapping(value = "/getSkuByID", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getSkuByID(String ch, String skuid) {
		String logpath = "products/" + ch + "/" + "getSkuByID";
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer skid = StringUtilsEX.ToIntNull(skuid);
			if (skid == null || skid <= 0) {
				item.setCode(-102);
				item.setDesc("skuid参数不正确！");
				return item.toJson();
			}
			item.setData(skuService.selectByID(skid));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle
					.error(LogType.wap,"获取商品信息错误：{0}", e,
							logpath);
		}
		return item.toJson();
	}

	/**
	 * 根据skuid获取商品信息图片
	 * 
	 * @param ch
	 * @param skuid
	 * @return
	 */
	@RequestMapping(value = "/getSkuImgsByID", produces = "text/html;charset=UTF-8")
	public  String getSkuImgsByID(String ch, String skuid) {
		String logpath = "products/" + ch + "/" + "getSkuImgsByID";
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer skid = StringUtilsEX.ToIntNull(skuid);
			if (skid == null || skid <= 0) {
				item.setCode(-102);
				item.setDesc("skuid参数不正确！");
				return item.toJson();
			}
			item.setData(productImgService.selectBySku(skid));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取商品图片错误：{0}", e,logpath);
		}
		return item.toJson();
	}
	
	
	@RequestMapping("/keywordQuery")
	public ReusltItem keywordQuery(String keyword,Integer page,Integer size,Integer classID,String orderBy,String orderType){
		ReusltItem item = new ReusltItem();
		try {
			Api_ProductCriteria criteria=new Api_ProductCriteria();
			criteria.setKeyword(keyword);
			criteria.setClsid(classID);
			criteria.setOrderByClause(orderBy);
			criteria.setSort(orderType);
			PageBean ben=productService.getSerachProductListPage_pc(criteria, page,size);
			item.setPage(ben.getTp());
			item.setMaxRow(ben.getTr());
			item.setPageIndex(ben.getPc());
			item.setPageSize(ben.getTp());
			item.setData(ben.getBeanList());
			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle
			.error(LogType.wap,"关键字搜索错误：{0}", e,"/products/keywordQuery");
		}
		return item;
	}
	
	
	@RequestMapping("/keywordlist.html")
	public ModelAndView keywordlist(HttpServletRequest request,Integer classID,String keyword){
		try {
			ModelAndView model=new ModelAndView("/template/wap/products/keywordList");
			
			int messagecount=0;
			Integer userid=SessionUtil.getSessionUserId(request);			
			if(userid!=null){
				messagecount=messageService.getCount(userid);				
			}		
			model.addObject("messagecount", messagecount);
			model.addObject("classID", classID);
			model.addObject("keyword",keyword);
			Api_ProductCriteria criteria=new Api_ProductCriteria();
			criteria.setKeyword(keyword);
			criteria.setClsid(classID);
			PageBean ben=productService.getSerachProductListPage_pc(criteria, 1,10);
			model.addObject("proBean", ben);
			return model;
		} catch (Exception e) {
			LogHandle
			.error(LogType.wap,
					MessageFormat.format("关键字搜索错误：{0}", e.toString()),"/wap/products/keywordlist");
			return ErrorRedirect.getInstance().wapRedirectModel("关键字搜索错误");
		}
	}
}

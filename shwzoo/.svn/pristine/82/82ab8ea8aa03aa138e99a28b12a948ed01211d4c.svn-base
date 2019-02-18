package com.yinlian.api.app.controller;

import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_SeachAtrrDto;
import com.yinlian.api.app.dto.Api_SeachAtrrValuesDto;
import com.yinlian.api.app.dto.ProductDetailedDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_CommentCriteria;
import com.yinlian.wssc.search.Api_ProductCriteria;
import com.yinlian.wssc.search.Api_SpuCriteria;
import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.dto.ProLuceneDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Browsehistory;
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.LuceneSearchService;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.service.SearchkeyService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/api/app/product")
public class ProductApiController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private LuceneSearchService luceneSearchService;
	@Autowired
	private UsercollectService usercollectService;
	@Autowired
	private BrowsehistoryService browsehistoryService;
	@Autowired
	private SearchkeyService SearchkeyService;
	@Autowired
	private SpuService spuService;

	/**
	 * 获取分类列表
	 * 
	 * @param ch
	 *            通道 IOS 、android
	 * @param fid
	 *            一级分类（如果为空，获取是推荐分类）
	 * @return
	 */
	@RequestMapping(value = "/getclslist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getClassList(String ch, String fid) {

		String logpath = "product/" + ch + "/" + "getclslist";
		BaseResult item = new BaseResult();
		if (!StringUtilsEX.isChannelTypeExist(ch)) {
			item.setCode(-101);
			item.setDesc("通道(ch)不正确！");
			return item.toJson();
		}
		try {
			Integer fid1 = StringUtilsEX.ToIntNull(fid);
			item.setData(categoryService.getApiList(fid1));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, MessageFormat.format("获取分类表列错误：{0}", e.toString()), logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取商品列表
	 * 
	 * @param ch
	 *            通道
	 * @param page
	 *            当前页
	 * @param size
	 *            页大小
	 * @param keyword
	 *            String keyword,
	 * @param clsid
	 *            分类Id
	 * @param sortfield
	 *            排序 字段 0销量 1价格 2评论数
	 * @param sorttype
	 *            排序类型 0降序 1升序
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getprolist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductList(String ch, String page, String size, String clsid, String sortfield,
			String sorttype, String minprice, String maxprice, String searchval, String keyword) {

		String logpath = "product/" + ch + "/" + "getprolist";
		ReusltItem item = new ReusltItem();
		if (!StringUtilsEX.isChannelTypeExist(ch)) {
			item.setCode(-101);
			item.setDesc("通道(ch)不正确！");
			return item.toJson();
		}
		try {
			Integer clsid1 = StringUtilsEX.ToIntNull(clsid);
			Integer page1 = StringUtilsEX.ToIntNull(page);
			Integer size11 = StringUtilsEX.ToIntNull(size);
			if (page1 == null || page1 <= 0) {
				page1 = 1;
			}
			if (size11 == null || size11 <= 0) {
				size11 = 10;
			}
			Api_ProductCriteria criteria = new Api_ProductCriteria();
			if (!StringUtilsEX.IsNullOrWhiteSpace(searchval)) {
				JSONArray jsonArray = JSONArray.fromObject(searchval);
				List<Api_SeachAtrrDto> list = (List<Api_SeachAtrrDto>) JSONArray.toCollection(jsonArray,
						Api_SeachAtrrDto.class);
				list.forEach(x -> {
					x.setValues((List<Api_SeachAtrrValuesDto>) JSONArray
							.toCollection(JSONArray.fromObject(x.getValues()), Api_SeachAtrrValuesDto.class));
				});
				criteria.setSeachatrrdtos(list);
			}
			// criteria.setSeachatrrdtos(null);
			criteria.setMaxprice(StringUtilsEX.ToDoubleNull(maxprice));
			criteria.setMinprice(StringUtilsEX.ToDoubleNull(minprice));
			criteria.setClsid(clsid1);
			if (!StringUtilsEX.IsNullOrWhiteSpace(keyword)) {
				criteria.setKeyword(keyword.trim());
			}
			Integer s = StringUtilsEX.ToIntNull(sortfield);
			Integer s1 = StringUtilsEX.ToIntNull(sorttype);
			s = s == null ? 0 : s;
			s1 = s1 == null ? 0 : s1;
			switch (s) {
			case 1:// 价格
				criteria.setOrderByClause("price");
				break;
			case 2:// 评论数
				criteria.setOrderByClause("p.CommentCount");
				break;
			case 0:// 销量
			default:
				criteria.setOrderByClause("count");
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
			PageBean list = productService.getApiProductList(criteria, page1, size11);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPage(list.getTp());
			item.setPageIndex(page1);
			item.setPageSize(size11);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, MessageFormat.format("获取商品列表错误：{0}", e.toString()), logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取商品列表搜索显示
	 * 
	 * @param ch
	 *            通道
	 * @param cid
	 *            分类id
	 * @return
	 */
	@RequestMapping(value = "/getsatrrlist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getSeachAtrrList(String ch, String cid) {
		BaseResult item = new BaseResult();
		String logpath = "product/" + ch + "/" + "getsatrrlist";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer clsid = StringUtilsEX.ToIntNull(cid);
			if (clsid == null || clsid <= 0) {
				item.setCode(-102);
				item.setDesc("分类参数不正确！");
				return item.toJson();
			}
			item.setData(productService.getApiSeachAtrrList(clsid, ActivityUsePlatformEnum.app.getValue()));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, MessageFormat.format("获取商品列表搜索错误：{0}", e.toString()), logpath);
		}
		return item.toJson();
	}

	/**
	 * 商品详细
	 * 
	 * @param ch
	 * @param sid
	 *            spuid
	 * @param type
	 * @param token
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getprodetailed", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailed(String ch, String sid, String type, String token,
			HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		String logpath = "product/" + ch + "/" + "getprodetailed";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer itype = StringUtilsEX.ToInt(type) >= 0 ? StringUtilsEX.ToInt(type) : 0;
			Integer spuid = StringUtilsEX.ToIntNull(sid);
			if (spuid == null || spuid <= 0) {
				item.setCode(-102);
				item.setDesc("商品id参数不正确！");
				return item.toJson();
			}
			Integer usesite = ActivityUsePlatformEnum.app.getValue();
			ProductDetailedDto dto = productService.getProductDetailed(spuid, itype, usesite);
			SessionUser user = SessionState.GetCurrentUser(token);
			if (dto != null && user.getCode() >= 0) {
				dto.setIsfavorites(usercollectService.IsCollectSpu(user.getUserId(), spuid));
				Browsehistory browsehistory = new Browsehistory();
				browsehistory.setUserid(user.getUserId());
				browsehistory.setSpuid(spuid);
				browsehistory.setChanneltype(StringUtilsEX.ToInt(ch));
				browsehistory.setBrowsetime(new Date());
				browsehistory.setCreatetime(new Date());
				browsehistory.setVaildflag(0);
				browsehistoryService.insert(browsehistory);
			}
			if(dto.getStatus() == ProStatusEnum.下架.getValue()){
				item.setCode(-201);
				item.setDesc("该商品已下架");
			} else if (dto == null || dto.getId() <= 0) {
				item.setCode(-200);
				item.setDesc("未获取商品信息");
			} else {
				item.setData(dto);
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取商品详细错误：{0}", e, logpath);
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
	 * @return
	 */
	@RequestMapping(value = "/getprodetailedbyskuid", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailedBySkuid(String ch, String sid, String kid, String type, String token,
			HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
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
			SessionUser user = SessionState.GetCurrentUser(token);
			ProductDetailedDto dto = productService.getProductDetailedBySkuId(spuid, skuid, itype,
					ActivityUsePlatformEnum.app.getValue());
			if (dto != null && user.getCode() >= 0) {
				dto.setIsfavorites(usercollectService.IsCollectSpu(user.getUserId(), spuid));
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
			LogHandle.error(LogType.Api, "获取商品详细错误：{0}", e, logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取商品详细页中详细 商品描述
	 * 
	 * @param ch
	 * @param kid
	 * @return
	 */
	@RequestMapping(value = "/getproddesc", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailedDesc(String ch, String sid, HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		String logpath = "product/" + ch + "/" + "getproddesc";
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
			item.setData(URLEncoder.encode(productService.getApiProDetailDesc(spuid), "UTF-8"));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, MessageFormat.format("获取商品详细（店铺店铺）错误：{0}", e.toString()), logpath);
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
	@RequestMapping(value = "/getprodspec", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailedSpecsInfo(String ch, String kid) {
		BaseResult item = new BaseResult();
		String logpath = "product/" + ch + "/" + "getprodspec";
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
			LogHandle.error(LogType.Api, MessageFormat.format("获取商品详细（店铺店铺）错误：{0}", e.toString()), logpath);
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
			LogHandle.error(LogType.Api, MessageFormat.format("获取商品详细（店铺店铺）错误：{0}", e.toString()), logpath);
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
	public @ResponseBody String getProductDetailedAfterSaleService(String ch, String sid,
			HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		String logpath = "product/" + ch + "/" + "getprodass";
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
			item.setData(URLEncoder.encode(productService.getApigetProductDetailedAfterSaleService(spuid), "UTF-8"));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, MessageFormat.format("获取商品详细（店铺店铺）错误：{0}", e.toString()), logpath);
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
	 * @return
	 */
	@RequestMapping(value = "/getprodcmt", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProductDetailedComment(String ch, String sid, String page, String size, String star,
			HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		String logpath = "product/" + ch + "/" + "getprodcmt";
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
				psize = 10;
			}
			Api_CommentCriteria acc = new Api_CommentCriteria();
			acc.setStar(StringUtilsEX.ToIntNull(star));
			if (acc.getStar() == null || acc.getStar() < 0) {
				acc.setStar(null);
			}
			acc.setSpuid(spuid);
			acc.setOrderByClause("date");
			acc.setSort("desc");
			PageBean dto = commentService.getProductDetailedCommentList(acc, pindex, psize);
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
			LogHandle.error(LogType.Api, "获取商品详细（店铺店铺）错误：{0}", e, logpath);
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
	public String getNewsPro(String ch, String cid, HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
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
			item.setData(productService.getgetNewsShelvesProByCid(classid, WebSetEnum.app.getValue()));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "根据分类id获取最新商品 错误：{0}", e, "/product/getshelvesnewspro");

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
	public String getNewsSellingPro(String ch, String cid, HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
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
			item.setData(productService.getgetNewsSellingProByCid(classid, WebSetEnum.app.getValue()));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "根据分类id获取热销商品", e, "/product/getsellingnewspro");

		}
		return item.toJson();
	}

	@RequestMapping(value = "/getprobykeys", produces = "text/html;charset=UTF-8")
	public String getProbyKeys(String ch, String keyw) {
		ReusltItem item = new ReusltItem();
		String logpath = "product/" + ch + "/" + "getprobykeys";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(keyw)) {
				item.setCode(-102);
				item.setDesc("关键字不能为空！");
				return item.toJson();
			}
			Object[] objects = new Object[2];
			// objects[0]=
			List<ProLuceneDto> list = luceneSearchService.getProidList(keyw.trim());
			Api_ProductCriteria criteria = new Api_ProductCriteria();
			String ids = "";
			if (list != null && list.size() > 0) {
				objects[0] = list.get(0).getClassid();
				criteria.setClsid(list.get(0).getClassid());
				for (ProLuceneDto proLuceneDto : list) {
					if (ids == "") {
						ids += proLuceneDto.getId();
					} else {
						ids += "," + proLuceneDto.getId();
					}
				}
				criteria.setIdliststr(ids);

				criteria.setOrderByClause("count");
				criteria.setSort("desc");

				PageBean prolist = productService.getApiProductList(criteria, 1, 10);
				item.setPage(prolist.getTp());
				item.setMaxRow(prolist.getTr());
				item.setPageIndex(prolist.getPc());
				item.setPageSize(prolist.getPs());
				objects[1] = prolist.getBeanList();
				item.setData(objects);
			}
			// item.setData(luceneSearchService.getProidList(keyw.trim()));
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);

			LogHandle.error(LogType.Api, "根据关键字获取商品错误：{0}", e, logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取热门关键字
	 * 
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/getsearchkeys", produces = "text/html;charset=UTF-8")
	public String getsearchkeys(String ch) {
		BaseResult item = new BaseResult();
		String logpath = "product/" + ch + "/" + "getsearchkeys";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			item.setData(SearchkeyService.getSearchkeys(ActivityUsePlatformEnum.app.getValue()));

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取热门关键字错误：{0}", e, logpath);
		}
		return item.toJson();
	}

	/**
	 * 根据品牌id获取商品列表
	 * 
	 * @param ch
	 *            通道
	 * @param page
	 *            当前页
	 * @param size
	 *            页大小
	 * @param bid
	 *            品牌Id
	 * @param sortfield
	 *            排序 字段 0销量 1价格
	 * @param sorttype
	 *            排序类型 0降序 1升序
	 * @return
	 */
	@RequestMapping(value = "/getprobybid", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProByBid(String ch, String page, String size, String bid, String sortfield,
			String sorttype) {
		String logpath = "product/getprobybid";
		ReusltItem item = new ReusltItem();
		try {
			Integer branid = StringUtilsEX.ToInt(bid);
			Integer page1 = StringUtilsEX.ToInt(page);
			Integer size11 = StringUtilsEX.ToInt(size);
			if (page1 <= 0) {
				page1 = 1;
			}
			if (size11 <= 0) {
				size11 = 10;
			}
			Api_SpuCriteria criteria = new Api_SpuCriteria();
			criteria.setBid(branid);
			Integer s = StringUtilsEX.ToIntNull(sortfield);
			Integer s1 = StringUtilsEX.ToIntNull(sorttype);
			s = s == null ? 0 : s;
			s1 = s1 == null ? 0 : s1;
			switch (s) {
			case 1:// 价格
				criteria.setOrderByClause("price");
				break;
			case 0:// 销量
			default:
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
			PageBean list = spuService.getSpuByBidPage(criteria, page1, size11);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPage(list.getTp());
			item.setPageIndex(page1);
			item.setPageSize(size11);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取商品列表错误：{0}", e, logpath);
		}
		return item.toJson();
	}

	/**
	 * 根据店铺id获取商品列表
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @param sid
	 *            店铺id
	 * @param sortfield
	 *            0 : 上架时间 1 : 商品价格
	 * @param sorttype
	 *            0 : 降序 1 : 升序
	 * @return
	 */
	@RequestMapping(value = "/getprobyshopid", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProByShopId(String ch, String page, String size, String sid, String sortfield,
			String sorttype,String keywords) {
		String logpath = "product/getprobyshopid";
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(sid)) {
				item.setCode(-102);
				item.setDesc("店铺id参数错误！");
				return item.toJson();
			}
			Integer shopid = StringUtilsEX.ToInt(sid);
			Integer page1 = StringUtilsEX.ToInt(page);
			Integer size11 = StringUtilsEX.ToInt(size);
			if (page1 <= 0) {
				page1 = 1;
			}
			if (size11 <= 0) {
				size11 = 10;
			}
			Api_SpuCriteria criteria = new Api_SpuCriteria();
			criteria.setSid(shopid);
			if(!StringUtilsEX.IsNullOrWhiteSpace(keywords)){
				criteria.setKeywords(keywords);
			}
			Integer s = StringUtilsEX.ToIntNull(sortfield);
			Integer s1 = StringUtilsEX.ToIntNull(sorttype);
			s = s == null ? 0 : s;
			s1 = s1 == null ? 0 : s1;
			switch (s) {
			case 1:// 商品价格
				criteria.setOrderByClause("AppPrice");
				break;
			case 0:// 上架时间
			default:
				criteria.setOrderByClause("ShelveTime");
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
			spuService.getProByShopIdNews(criteria, page1, size11,item);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "根据店铺id获取商品列表错误：{0}", e, logpath);
		}
		return item.toJson();
	}
	
	/**
	 * 根据商品名称模糊查询商品
	 * @param ch
	 * @param page
	 * @param size
	 * @param proname
	 * @return
	 */
	@RequestMapping(value = "/getprobyname", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProByName(String ch, String page, String size, String proname) {
		String logpath = "product/getprobyname";
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			Integer page1 = StringUtilsEX.ToInt(page);
			Integer size11 = StringUtilsEX.ToInt(size);
			if (page1 <= 0) {
				page1 = 1;
			}
			if (size11 <= 0) {
				size11 = 10;
			}
			ProductCriteria criteria = new ProductCriteria();
			if(!StringUtilsEX.IsNullOrWhiteSpace(proname)){
				criteria.setName(proname.trim());
			}
			PageBean list = spuService.getProByName(criteria, page1, size11);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPage(list.getTp());
			item.setPageIndex(page1);
			item.setPageSize(size11);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "根据商品名称模糊查询商品错误：{0}", e, logpath);
		}
		return item.toJson();
	}

}

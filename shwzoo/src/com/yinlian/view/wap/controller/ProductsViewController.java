package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.SiteType;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ApiShopAboutInfoBaseDto;
import com.yinlian.api.app.dto.Api_Detail_Specs;
import com.yinlian.api.app.dto.CommentBaseDto;
import com.yinlian.api.app.dto.IdValue;
import com.yinlian.api.app.dto.ProductDetailedDto;
import com.yinlian.api.app.dto.ProductDto;
import com.yinlian.api.app.dto.ShopAppDto;
import com.yinlian.api.app.dto.SpecsBaseDto;
import com.yinlian.wssc.search.Api_ProductCriteria;
import com.yinlian.wssc.search.Wap_CommentCriteria;
import com.yinlian.wssc.web.dto.PackageDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuPackageService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/products")
public class ProductsViewController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UsercollectService usercollectService;
	@Autowired
	private ProductImgsService productImgService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SkuPackageService	skuPackageService;
	@Autowired
	private MessageService  messageService;
	
	@RequestMapping("/proList.html")
	public ModelAndView proList(String classID,HttpServletRequest request) {
		ModelAndView model = new ModelAndView("/template/wap/products/proList");
		try {
			Api_ProductCriteria pc = new Api_ProductCriteria();
			pc.setClsid(StringUtilsEX.ToInt(classID));
			pc.setOrderByClause("count");
			pc.setSort("desc");
			PageBean bean = productService.getWapProductList(pc, 1, 10);
			model.addObject("proBean", bean);
			model.addObject("cid", classID);
			int messagecount=0;
			Integer userid=SessionUtil.getSessionUserId(request);			
			if(userid!=null){
				messagecount=messageService.getCount(userid);				
			}		
			model.addObject("messagecount", messagecount);
		} catch (Exception e) {
			e.printStackTrace();                                      
			LogHandle.error(LogType.wap, "访问商品列表错误：{0}", e, "/wap/products/proList");				
		}
		return model;
		//return ErrorRedirect.getInstance().wapRedirectErrorModel();
	}

	/**
	 * 显示商品信息
	 * @param type 商品类型：0普通商品，1闪购商品，2秒杀商品
	 * @param cartType 购物车的商品类型：0普通商品，2闪购商品，3秒杀商品
	 */
	@RequestMapping("/ProDetail.html")
	public  String showDetail(String spuid,String skuid, String type,String carttype,
			HttpServletRequest request) {		
		String ch = "3";
		//秒杀传秒杀商品剩余数量 __调整后台获取
//		String spikcon=request.getParameter("spikcon");		
//		if(StringUtilsEX.ToInt(spikcon)>0){
//			request.setAttribute("spikcon", spikcon);
//		}else{
//			request.setAttribute("spikcon", -1);
//		}
        //商品详情和购物车的对应商品类型不一样！
		if(StringUtilsEX.ToInt(type)<0){
			if(StringUtilsEX.ToInt(carttype)<0){
				type="0";
			}else{
				if(carttype.equals("2")){
				    type="1";				
			    }
				if(carttype.equals("3")){
					type="2";
				}
			}						
		}
    	
		Integer spid = StringUtilsEX.ToInt(spuid);
		Integer skid = StringUtilsEX.ToInt(skuid);

		String logpath = "products/" + ch + "/" + "getprodetail";
		try {
			Boolean hasch=StringUtilsEX.isChannelTypeExist(ch);
			if (!hasch) {				
			  return ErrorRedirect.getInstance().wapRedirect("商品信息错误");
			}
			Integer itype = StringUtilsEX.ToInt(type) >= 0 ? StringUtilsEX
					.ToInt(type) : 0;
			if (spid <= 0&&skid <= 0) {				
				return ErrorRedirect.getInstance().wapRedirect("商品信息错误");
			}
			if (spid <= 0) {				
				spid=skuService.selectByID(skid).getSpuId();
			}
			ProductDetailedDto dto = productService.getProductDetailed(spid,
					itype, ActivityUsePlatformEnum.wap.getValue());
            //隐藏评论用户名称
		    List<CommentBaseDto> co=dto.getCmts();
		    List<CommentBaseDto> consetname=new ArrayList<CommentBaseDto>();
		    for (CommentBaseDto coDto : co) {
		    	String na=coDto.getUsername();
				if(na.length()>=3)
				na=na.substring(0, 1)+"**"+na.substring(na.length()-2, na.length()-1);
				coDto.setUsername(na);
				consetname.add(coDto);
			}
			dto.setCmts(consetname);
			// 店铺信息
			//final ShopAppDto shopAppDto = shopService.queryByIdForApp(dto.getShopid(), SiteType.wap);
			ApiShopAboutInfoBaseDto apishopdto = shopService.getApiShopAboutInfo(dto.getShopid());
			
			// 商品规格选择
			List<SpecsBaseDto> speces = productService
					.getProductDetailedSpecs(spid);
			String value = "", spname = "", samevalue = "";
			List<SpecsBaseDto> selectspece = new ArrayList<SpecsBaseDto>();
			for (SpecsBaseDto sbDto : speces) {
				SpecsBaseDto newsbDto = new SpecsBaseDto();
				newsbDto.setId(sbDto.getId());
				newsbDto.setName(sbDto.getName());
				List<IdValue> newIdValues = new ArrayList<IdValue>();
				for (IdValue idv : sbDto.getValues()) {
					IdValue newidv = new IdValue();

					spname = (String) idv.getValue();
					samevalue = Integer.toString(idv.getId());
					value = Integer.toString(idv.getId());
					for (IdValue sidv : sbDto.getValues()) {
						if (spname.equals((String) sidv.getValue())) {
							if (!value.equals(Integer.toString(sidv.getId()))) {
								String temp = Integer.toString(sidv.getId());
								samevalue = samevalue + ";" + temp;								
							}

						}
					}
					newidv.setValue(idv.getValue());
					newidv.setName(samevalue);
					newidv.setId(idv.getId());
					
					if (newidv.getId() > 0) {
						if (newIdValues.size() > 0) {
							boolean has = false;
							for (IdValue iv : newIdValues) {
								String skus = iv.getName();
								if (skus.contains(Integer.toString(newidv.getId()))) {
									has = true;
								}
							}
							if (!has) {
								newIdValues.add(newidv);
							}
						} else {
							newIdValues.add(newidv);
						}
					}

				}
				if (newIdValues.size() > 0)
					newsbDto.setValues(newIdValues);
				if (newsbDto.getValues() != null)
					selectspece.add(newsbDto);
			}

			// 最新商品
			List<ProductDto> newproduct = productService
					.getgetNewsShelvesProByCid(dto.getCid(),WebSetEnum.wap.getValue());
			// 热销商品
			List<ProductDto> sellproduct = productService
					.getgetNewsSellingProByCid(dto.getCid(),WebSetEnum.wap.getValue());
			
			if (skid < 0) {
				skid = dto.getId();
			}
			
			request.setAttribute("skuid", skid);
			request.setAttribute("shopinfotop", apishopdto);
			request.setAttribute("speces", selectspece);
			request.setAttribute("spuinfo", dto);
			//request.setAttribute("shopinfo", shopAppDto);
			request.setAttribute("newproduct", newproduct);
			request.setAttribute("sellproduct", sellproduct);
			request.setAttribute("spuid", spid);

			SessionUser user = SessionState.GetCurrentUser();
			if (user != null && user.getCode() >= 0) {
				dto.setIsfavorites(usercollectService.IsCollectSpu(
						user.getUserId(), spid));
			} else {
				dto.setIsfavorites(false);
			}
			String phref=request.getParameter("href");
			if(StringUtilsEX.IsNullOrWhiteSpace(phref)){
				phref="/wap/index.html";
			}
			
			String ids=request.getParameter("ids");
			if(!StringUtilsEX.IsNullOrWhiteSpace(ids)){
				phref=phref+"&ids="+ids;
			}
			request.setAttribute("href", phref);
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.wap, "获取商品详细错误：{0}", e, logpath);
			 return ErrorRedirect.getInstance().wapRedirect("商品信息错误");		
		}
		return "/template/wap/products/proDetail";
		//return ErrorRedirect.getInstance().wapRedirectError();
	}

	/**
	 * 显示商品介绍
	 * 
	 */
	@RequestMapping("/showProInfo.html")
	public String showProInfo(String spuid,HttpServletRequest request) {
		String ch = "3";		
		try {
			Integer spid = StringUtilsEX.ToIntNull(spuid);
			if (spuid == null || spid <= 0) {
				return ErrorRedirect.getInstance().wapRedirect("商品详情错误");	
			}
			// 商品详情
			String spudetail = HtmlUtils.htmlUnescape(productService
					.getApiProDetailDesc(spid));
			request.setAttribute("spudetail", spudetail);
			request.setAttribute("spuid", spid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/template/app/product/productInfo";
	}

	/**
	 * 显示商品评论
	 * 
	 */
	@RequestMapping("/showProCommen.html")
	public String showProCommen(String sid, String page,
			String size, String star, HttpServletRequest request) {
		sid = request.getParameter("spuid");
		String shopid=request.getParameter("shopid");
		String logpath = "products/3/proComment";
		try {			
			Integer spuid = StringUtilsEX.ToIntNull(sid);
			if (spuid == null || spuid <= 0) {
				return ErrorRedirect.getInstance().wapRedirect("商品评论错误");
			}
			Integer pindex = StringUtilsEX.ToIntNull(page);
			if (pindex == null || pindex <= 0) {
				pindex = 1;
			}
			Integer psize = StringUtilsEX.ToIntNull(size);
			if (psize == null || psize <= 0) {
				psize = 6;
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
			request.setAttribute("spucomms", dto.getData());
			request.setAttribute("spuid", spuid);
			String skuid = request.getParameter("skuid").toString();
			request.setAttribute("skuid", skuid);
			request.setAttribute("shopid", shopid);
			request.setAttribute("href", "/wap/products/ProDetail.html?spuid="+spuid+"&skuid="+skuid);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,MessageFormat.format("获取商品评论错误：{0}", e.toString()),logpath);
			return ErrorRedirect.getInstance().wapRedirect("商品评论错误");
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/products/proComment";
	}
	/**
	 * 显示商品组合
	 * 
	 */
	@RequestMapping("/showprogroups.html")
	public String showprogroups(String skid,String spid,String shopid,String isfavorites, HttpServletRequest request) {
		String logpath = "products/3/proComment";
		try {
			Integer spuid = StringUtilsEX.ToIntNull(spid);
			Integer sid = StringUtilsEX.ToIntNull(shopid);
			Integer skuid = StringUtilsEX.ToIntNull(skid);
			Boolean iscon=StringUtilsEX.ToBoolean(isfavorites);
			if (skuid == null || skuid <= 0) {
				return ErrorRedirect.getInstance().wapRedirect("商品库存id错误");
			}
			//组合商品
			List<PackageDto>  pklist=skuPackageService.queryBySkuId(skuid,ActivityUsePlatformEnum.wap.getValue());
			request.setAttribute("pklist",pklist);
            request.setAttribute("skuid", skuid);
            request.setAttribute("spuid", spuid); 
            request.setAttribute("shopid", sid);            
            request.setAttribute("isfavorites", iscon);
 			request.setAttribute("href", "/wap/products/showprogroups.html?skid="+skuid);
		} catch (Exception e) {
			LogHandle
					.error(LogType.wap,
							MessageFormat.format("获取商品组合错误：{0}", e.toString()),logpath);
			return ErrorRedirect.getInstance().wapRedirect("商品组合错误");
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/products/progroups";
	}
}

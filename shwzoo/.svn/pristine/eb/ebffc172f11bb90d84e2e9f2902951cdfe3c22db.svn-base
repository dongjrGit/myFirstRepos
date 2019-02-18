package com.yinlian.view.pc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.ImageTypeEnum;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.SpecsDisplayEnum;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ApiShopAboutInfoBaseDto;
import com.yinlian.api.app.dto.Api_Detail_Specs;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.api.app.dto.CommentProDetailListDto;
import com.yinlian.api.app.dto.IdValue;
import com.yinlian.api.app.dto.ProductDetailedDto;
import com.yinlian.api.app.dto.SpecsBaseDto;
import com.yinlian.pc.dto.InComeDto;
import com.yinlian.wssc.search.Api_ProductCriteria;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.search.Wap_CommentCriteria;
import com.yinlian.wssc.web.dto.BrowseHistoryDto;
import com.yinlian.wssc.web.dto.PackageDto;
import com.yinlian.wssc.web.dto.ProLuceneDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.Browsehistory;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.GoodConsultService;
import com.yinlian.wssc.web.service.ImagesService;
import com.yinlian.wssc.web.service.LuceneSearchService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuPackageService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaGoodConsult;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/web/products")
public class PCProductsViewController {
	@Autowired 
	private ProductService productService;
	@Autowired 
	private CategoryService classService;
	@Autowired 
	private AdverisingService adservice;
	@Autowired 
	private ShopService shopService;
	@Autowired 
	private CommentService commentService;
	@Autowired 
	private BrowsehistoryService hisService;
	@Autowired 
	private TopicService topservice;
	@Autowired 
	private SkuService skuService;
    @Autowired 
    private SkuPackageService skuPackageService;   
	@Autowired
	private LuceneSearchService luceneSearchService;
	@Autowired
	private GoodConsultService goodconsultService;
	@Autowired
	private ImagesService imagesService;
	@Autowired
	private ProductImgsService proimgService;
    /**
     * 根据分类ID获取商品列表
     * @param cid 三级分类
     * @param fid 二级分类
     * @param tid 一级分类
     * @return
     */
	@RequestMapping("/proList.html")
	public ModelAndView proList(String cid,String fid,String tid,String classid){
		ModelAndView model = new ModelAndView("/template/pc/pro/proList");
		try {
			Api_ProductCriteria criteria=new Api_ProductCriteria();
			if(StringUtilsEX.IsNullOrWhiteSpace(classid)){
				criteria.setFullpath(cid);
				classid=cid;
			}else{
				criteria.setFullpath(classid);
			}
			criteria.setFullpath(classid);
			criteria.setStatus(ProStatusEnum.上架.getValue().toString());
			criteria.setOrderByClause("SalesCount");
			criteria.setSort("desc");
			PageBean bean = productService.getSerachProductListPage_pc(criteria, 1, 20);
			model.addObject("proBean", bean);		
			model.addObject("classid", classid);
			Category category = new Category();
			if (StringUtilsEX.ToInt(classid) > 0) {
				category = classService.selectByPrimaryKey(StringUtilsEX.ToInt(classid));
				if(category != null && category.getId() > 0 && category.getClasslever() > 1){
					Category tClass = classService.selectByPrimaryKey(category.getFatherid());
					if (tClass != null && tClass.getId() > 0 && tClass.getFatherid() > 0) {
						Category fClass = classService.selectByPrimaryKey(tClass.getFatherid());
						model.addObject("fClass", fClass);
					}
					model.addObject("tClass", tClass);
				}
			}
			model.addObject("selectClass", category);
			model.addObject("searchdata", productService.getApiSeachAtrrList(StringUtilsEX.ToInt(classid),ActivityUsePlatformEnum.pc.getValue()));
			
			List<BrowseHistoryDto> hisList = new ArrayList<BrowseHistoryDto>();
			SessionUser user = SessionState.GetCurrentUser();
			if (user != null && user.getId() > 0) {
				hisList = hisService.queryDetailByUserId(user.getUserId());
				if(hisList!=null && hisList.size()>10 ){
					hisList=hisList.stream().limit(10).collect(Collectors.toList());
				}
			}
			model.addObject("history", hisList); 
			List<Api_TopicBySpuDto> hotpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.热卖推荐.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			List<Api_TopicBySpuDto> likedpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			List<Api_TopicBySpuDto> recommpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.精选推荐.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),9);
			model.addObject("likedpros", likedpros);
			model.addObject("recommpros", recommpros);
			model.addObject("hotpros", hotpros);
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.pc, "访问商品列表错误：{0}", e, "web/products/proList");
			model.setViewName("redirect:/404.html");
			return model;
		}
		return model;
	}
	  
	/**
	 * 包邮
	 * @param cid
	 * @return
	 */
		@RequestMapping("/postlist.html")
		public ModelAndView postlist(){
			ModelAndView model = new ModelAndView("/template/pc/pro/postlist");
			try {
				ProductCriteria pc = new ProductCriteria();
				pc.setStatus(ProStatusEnum.上架.getValue().toString());
				pc.setOrderByClause("a.SalesCount");
				pc.setSort("desc");
				pc.setIsposttag("1");
				PageBean bean = productService.getProListPage_pc(pc, 1, 20);
				model.addObject("proBean", bean);
				List<BrowseHistoryDto> hisList = new ArrayList<BrowseHistoryDto>();
				SessionUser user = SessionState.GetCurrentUser();
				if (user != null && user.getId() > 0) {
					hisList = hisService.queryDetailByUserId(user.getUserId());
					if(hisList!=null && hisList.size()>10 ){
						hisList=hisList.stream().limit(10).collect(Collectors.toList());
					}
				}
				model.addObject("history", hisList);
				List<Api_TopicBySpuDto> hotpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.热卖推荐.getValue(), 			
						TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
				List<Api_TopicBySpuDto> likedpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
						TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
				List<Api_TopicBySpuDto> recommpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.精选推荐.getValue(), 			
						TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),9);
				model.addObject("likedpros", likedpros);
				model.addObject("recommpros", recommpros);
				model.addObject("hotpros", hotpros);
			} catch (Exception e) {
				e.printStackTrace();
				LogHandle.error(LogType.pc, "访问包邮商品列表错误：{0}", e, "web/products/postlist");
				model.setViewName("redirect:/404.html");
				return model;
			}
			return model;
		}
        //专题商品 
		//topicid 专题id
		@RequestMapping("/topicprolist.html")
		public ModelAndView topicprolist(String topicid){
			ModelAndView model = new ModelAndView("/template/pc/pro/topicprolist");
			try {
				Api_TopicBySpuCriteria atc = new Api_TopicBySpuCriteria();
				atc.setTopicid(StringUtilsEX.ToInt(topicid));
				PageBean listBean = topservice.getTopicBySpu(atc, 1, 10);			
				model.addObject("proBean", listBean);
				model.addObject("topicid",topicid);
				List<BrowseHistoryDto> hisList = new ArrayList<BrowseHistoryDto>();
				SessionUser user = SessionState.GetCurrentUser();
				if (user != null && user.getId() > 0) {
					hisList = hisService.queryDetailByUserId(user.getUserId());
					if(hisList!=null && hisList.size()>10 ){
						hisList=hisList.stream().limit(10).collect(Collectors.toList());
					}
				}
				model.addObject("history", hisList);
				List<Api_TopicBySpuDto> hotpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.热卖推荐.getValue(), 			
						TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
				List<Api_TopicBySpuDto> likedpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
						TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
				List<Api_TopicBySpuDto> recommpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.精选推荐.getValue(), 			
						TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),9);
				model.addObject("likedpros", likedpros);
				model.addObject("recommpros", recommpros);
				model.addObject("hotpros", hotpros);
			} catch (Exception e) {
				e.printStackTrace();
				LogHandle.error(LogType.pc, "访问专题商品列表错误：{0}", e, "web/products/topicprolist");
				model.setViewName("redirect:/404.html");
				return model;
			}
			return model;
		}

	/**
	 * 商品详情页面
	 * @param spuid 标准商品id（如果不传值则必须传skuid）
	 * @param skuid 库存商品id（如果不传值则必须传spuid）
	 * @param protype 商品类型 0普通商品，1闪购
	 * */
	@RequestMapping("/proinfo.html")
	public ModelAndView proInfo(String spuid,String skuid,String protype){
		ModelAndView model = new ModelAndView("/template/pc/pro/prodetail");
		try {
			//获取广告
			List<Advertising> adlist=adservice.getListByTypeAndDisplay(PageMarkType.商品详情页.getValue(), 1, WebSetEnum.pc.getValue());
			
			int itype= StringUtilsEX.ToInt(protype) >= 0 ? StringUtilsEX
					.ToInt(protype) : 0;
			Integer sid=StringUtilsEX.ToInt(spuid);
			Integer skid=StringUtilsEX.ToInt(skuid);

			if(sid<0){
				if(skid<0){
					 model.setViewName("redirect:/404.html");	
					 return model;
				}else{
					sid=skuService.selectByID(skid).getSpuId();					
				}
			}
			model.addObject("skuid", skid);
			model.addObject("spuid",sid);
			model.addObject("itype",itype);
			ProductDetailedDto dto =productService.getProductDetaileds(sid,itype, ActivityUsePlatformEnum.pc.getValue());
			model.addObject("isdel", 0);
			if(dto.getStatus()!=3||dto.getIsdel()==true){
				model.addObject("isdel", 1);
				model.addObject("skuid", skid);
				model.addObject("spuinfo", dto);
				return model;
			}
				
			// 店铺信息
			ApiShopAboutInfoBaseDto shopAppDto = new ApiShopAboutInfoBaseDto();
			if (dto.getShopid() > 0) {
				shopAppDto = shopService.getApiShopAboutInfo(dto.getShopid());
			}
			//分类信息
			int classid = dto.getCid();
			Category category = new Category();
			if (classid > 0) {
				category = classService.selectByPrimaryKey(classid);
				if(category != null && category.getId() > 0 && category.getClasslever() > 1){
					Category tClass = classService.selectByPrimaryKey(category.getFatherid());
					if (tClass != null && tClass.getId() > 0 && tClass.getFatherid() > 0) {
						Category fClass = classService.selectByPrimaryKey(tClass.getFatherid());
						model.addObject("fClass", fClass);
					}
					model.addObject("tClass", tClass);
				}
			}
			model.addObject("selectClass", category);
			// 商品规格选择
			List<SpecsBaseDto> speces = productService
					.getProductDetailedSpecs(sid);
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
								if (skus.contains(Integer.toString(newidv
										.getId()))) {
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
				selectspece.add(newsbDto);
					
			}
			
			if (skid < 0) {
				skid = dto.getId();
				model.addObject("skuid", skid);
			}
			// 商品详情
			String spudetail = HtmlUtils.htmlUnescape(productService.getPcProductDetailedDesc(sid));
			// 规格参数
			List<Api_Detail_Specs> specesinfo = productService.getProductSpecs(skid, SpecsDisplayEnum.规格参数.getValue().toString());
			// 商品介绍规格参数
			List<Api_Detail_Specs> infospeces = productService.getProductSpecs(skid, SpecsDisplayEnum.商品介绍.getValue().toString());
			//售后服务
			String spuafter = HtmlUtils.htmlUnescape(productService.getApigetProductDetailedAfterSaleService(sid));
			Wap_CommentCriteria acc = new Wap_CommentCriteria();			
			acc.setStar(null);			
			acc.setSpuid(sid);
			acc.setOrderByClause("date");
			acc.setSort("desc");
			PageBean comms = commentService.getProductDetailedCommentList(acc,1, 10);
			
			CommentProDetailListDto list=(CommentProDetailListDto) comms.getData();
			
			list.getList().forEach(f->{
				try {
					List<Images> imgLists=imagesService.queryImages(f.getId(),ImageTypeEnum.买家评价图片.getValue());
					f.setShowImgList(imgLists);
				} catch (Exception e) {
					LogHandle.error(LogType.pc, "访问商品列表错误：{0}", e, "web/products/proinfo");
					model.setViewName("redirect:/404.html");					
				}
			});
			Integer picSize=list.getList().stream().filter(a->a.getShowImgList()!=null && a.getShowImgList().size()>0 ).collect(Collectors.toList()).size();
			list.setPicCom(picSize);
			model.addObject("spucomms", list);
			model.addObject("spuinfo", dto);
			model.addObject("speces", selectspece);
			model.addObject("shopinfo", shopAppDto);
			model.addObject("adlist", adlist);
			model.addObject("spudetail",spudetail);
			model.addObject("specesinfo",specesinfo);
			model.addObject("infospeces",infospeces);
			model.addObject("spuafter",spuafter);
			model.addObject("maxrow",comms.getTr());
			model.addObject("pindex",comms.getPc());
			
			CriteriaGoodConsult consult = new CriteriaGoodConsult();
			consult.setSpuId(spuid);
			PageBean pageBean = goodconsultService.queryPage(consult,1, 10);
			model.addObject("consults", pageBean.getBeanList());
			model.addObject("consultindex", pageBean.getPc());
			model.addObject("consultmaxrow", pageBean.getTr());
			
			//组合商品
			List<PackageDto>  pklist=skuPackageService.queryBySkuId(skid,ActivityUsePlatformEnum.pc.getValue());
			model.addObject("pklist",pklist);
			//获取主题
			List<Api_TopicBySpuDto> topsputj= topservice.getIndexTopic(PageMarkType.商品详情页.getValue(),TopicMarkEnum.天天优惠.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			//看了还看（猜你喜欢）
			List<Api_TopicBySpuDto> topspukk=new ArrayList<Api_TopicBySpuDto>();
			 topspukk= topservice.getIndexTopic(PageMarkType.商品详情页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			
			List<BrowseHistoryDto> hisList = new ArrayList<BrowseHistoryDto>();
			SessionUser user = SessionState.GetCurrentUser();
			if (user != null && user.getId() > 0) {
				hisList = hisService.queryDetailByUserId(user.getUserId());
				if(hisList!=null && hisList.size()>10){
					hisList=hisList.stream().limit(10).collect(Collectors.toList());
				}
			}
			model.addObject("hisList", hisList);
			model.addObject("topsputj", topsputj);
			model.addObject("topspukk", topspukk);//看了还看（猜你喜欢）
			//浏览历史
			if (user != null && user.getId() > 0) {
				Browsehistory browsehistory=new Browsehistory();
				browsehistory.setUserid(user.getUserId());
				browsehistory.setSpuid(sid);
				browsehistory.setChanneltype(1);
				browsehistory.setBrowsetime(new Date());
				browsehistory.setCreatetime(new Date());
				browsehistory.setVaildflag(0);
				hisService.insert(browsehistory);
			}
		
		} catch (Exception e) {
			e.printStackTrace();                                      
			LogHandle.error(LogType.pc, "访问商品详情错误：{0}", e, "web/products/proinfo");
			model.setViewName("redirect:/404.html");
			return model;
		}
		return model;
	}
	
	@RequestMapping(value = "/searchList.html", produces = "text/html;charset=UTF-8")
	public ModelAndView searchList(String keywords){
		ModelAndView model = new ModelAndView("/template/pc/pro/searchlist");
		try {
			List<InComeDto> classlist=new ArrayList<InComeDto>();
			List<ProLuceneDto> list = luceneSearchService.getProidList(keywords.trim());
			Api_ProductCriteria criteria = new Api_ProductCriteria();
			String ids = "";
			Integer classid=0;
			InComeDto claDto=null;
			model.addObject("keywords", keywords);
			if (list != null && list.size() > 0) {
				Map<Integer, List<ProLuceneDto>> classgroup=list.stream().collect(Collectors.groupingBy(ProLuceneDto::getClassid));
				for (Map.Entry<Integer, List<ProLuceneDto>> entry : classgroup
						.entrySet()) {
					claDto=new InComeDto();
					claDto.setCode(entry.getKey());
					claDto.setName(entry.getValue().get(0).getClassname());
					classlist.add(claDto);
				}
				model.addObject("classlist", classlist);

				for (ProLuceneDto proLuceneDto : list) {
					if (ids == "") {
						ids += proLuceneDto.getId();
					} else {
						ids += "," + proLuceneDto.getId();
					}
				}			
				classid=classlist.get(0).getCode();
				criteria.setIdliststr(ids);
			}else{
				criteria.setIdliststr("0");
			}
			criteria.setOrderByClause("SalesCount");
			criteria.setSort("desc");
			PageBean bean = productService.getSerachProductListPage_pc(criteria, 1, 20);
			model.addObject("proBean", bean);
			model.addObject("cid", classid);
			model.addObject("searchdata", productService.getApiSeachAtrrList(classid,ActivityUsePlatformEnum.pc.getValue()));
			List<BrowseHistoryDto> hisList = new ArrayList<BrowseHistoryDto>();
			SessionUser user = SessionState.GetCurrentUser();
			if (user != null && user.getId() > 0) {
				hisList = hisService.queryDetailByUserId(user.getUserId());
				if(hisList!=null && hisList.size()>10 ){
					hisList=hisList.stream().limit(10).collect(Collectors.toList());
				}
			}
			model.addObject("history", hisList);
			List<Api_TopicBySpuDto> hotpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.热卖推荐.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			List<Api_TopicBySpuDto> likedpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			List<Api_TopicBySpuDto> recommpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.精选推荐.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),9);
			model.addObject("likedpros", likedpros);
			model.addObject("recommpros", recommpros);
			model.addObject("hotpros", hotpros);
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.pc, "访问商品搜索列表错误：{0}", e, "web/products/searchList");
			model.setViewName("redirect:/404.html");
			return model;
		}
		return model;
	} 
}


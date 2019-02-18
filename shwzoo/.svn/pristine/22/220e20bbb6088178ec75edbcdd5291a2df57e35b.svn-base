package com.yinlian.wssc.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.ShopTypeEnum;
import com.yinlian.Enums.SpecsDisplayEnum;
import com.yinlian.api.app.dto.Api_Detail_Specs;
import com.yinlian.api.app.dto.Api_SeachAtrrDto;
import com.yinlian.api.app.dto.Api_SeachAtrrValuesDto;
import com.yinlian.api.app.dto.CommentBaseDto;
import com.yinlian.api.app.dto.IdValue;
import com.yinlian.api.app.dto.NameValue;
import com.yinlian.api.app.dto.ProductDetailedDto;
import com.yinlian.api.app.dto.ProductDto;
import com.yinlian.api.app.dto.SatisfactionGoodCountDto;
import com.yinlian.api.app.dto.SeachAtrrDto;
import com.yinlian.api.app.dto.SeachAtrrValueDto;
import com.yinlian.api.app.dto.ShopAppDto;
import com.yinlian.api.app.dto.ShowtimeDto;
import com.yinlian.api.app.dto.SkuShowtimeDto;
import com.yinlian.api.app.dto.SpecsBaseDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CookieDto;
import com.yinlian.pc.dto.PCProListDto;
import com.yinlian.wssc.search.Api_CommentCriteria;
import com.yinlian.wssc.search.Api_ProductCriteria;
import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.dto.ProLuceneDto;
import com.yinlian.wssc.web.dto.ProductListDto;
import com.yinlian.wssc.web.dto.V_SearchProducts;
import com.yinlian.wssc.web.dto.sgSpuDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.CommentMapper;
import com.yinlian.wssc.web.mapper.CouponMapper;
import com.yinlian.wssc.web.mapper.FreightMapper;
import com.yinlian.wssc.web.mapper.ProductImgsMapper;
import com.yinlian.wssc.web.mapper.ProductMapper;
import com.yinlian.wssc.web.mapper.SearchattrMapper;
import com.yinlian.wssc.web.mapper.ShopAuthenticationMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.SkuMapper;
import com.yinlian.wssc.web.mapper.SkuPackageMapper;
import com.yinlian.wssc.web.mapper.SkuShowtimeMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.mapper.VSpecsinfoMapper;
import com.yinlian.wssc.web.po.CommentAbr;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.ProductImgs;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.VSpecsinfo;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.LuceneSearchService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.uitl.DateUtil;

public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private SkuPackageMapper skuPackageMapper;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductImgsMapper productImgsMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private VSpecsinfoMapper vSpecsinfoMapper;
	@Autowired
	private FreightMapper freightMapper;
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private SearchattrMapper searchattrMapper;
	@Autowired
	private LuceneSearchService luceneSearchService;
	@Autowired
	private SpikeActivityService spikeActivityService;
	@Autowired
	private FreightService freightService;
	@Autowired
	private SpuMapper spuMapper;
	@Autowired
	private ProductImgsService proimgService;
	@Autowired
	private SkuMapper skuMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private SkuShowtimeMapper skuShowtimeMapper;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ShopAuthenticationMapper shopAuthenticationMapper;

	@Override
	public PageBean getList(ProductCriteria criteria, Integer pageIndex, Integer pageSize) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pageIndex, pageSize);
		PageBean pageBean = pageBeanUtil.getPage();
		List<ProductListDto> beanList = productMapper.getListPage(pageBeanUtil);
		// for (ProductListDto s : beanList) {
		// s.setFullPathName(categoryService.GetFullNamePath(s.getFullPath()));
		// }
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public PageBean getPcProList(ProductCriteria cria, int index, int size) throws Exception {
		PageBeanUtil util = new PageBeanUtil(cria, index, size);
		PageBean bean = util.getPage();
		List<V_SearchProducts> pros = productMapper.getPcProListPage(util);
		List<V_SearchProducts> resList = new ArrayList<V_SearchProducts>();
		for (V_SearchProducts pro : pros) {
			boolean isexist = false;
			for (V_SearchProducts sp : resList) {
				if (pro.getSpuid() == sp.getSpuid()) {
					isexist = true;
				}
			}
			if (!isexist) {
				resList.add(pro);
			}
		}
		bean.setBeanList(resList);
		return bean;
	}

	/**
	 * 根据名称模糊检索商品列表
	 */
	public List<ProductListDto> getSpuStartWithName(Integer shopid, String name) throws Exception {
		return productMapper.getSpuStartWithName(shopid, name);
	}

	@Override
	public PageBean getApiProductList(Api_ProductCriteria criteria, Integer page, Integer size) throws Exception {
		// 判断是否有关键字
		if (!StringUtilsEX.IsNullOrWhiteSpace(criteria.getKeyword())) {
			List<ProLuceneDto> list = luceneSearchService.getProidList(criteria.getKeyword().trim());
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
			} else {
				criteria.setIdliststr("0");
			}
		}
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<ProductDto> beanList = productMapper.getApiProductListPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<Api_SeachAtrrDto> getApiSeachAtrrList(Integer clsid, Integer usesites) throws Exception {
		List<SeachAtrrDto> list = searchattrMapper.getAllSearchattrValues(clsid, usesites);
		List<Api_SeachAtrrDto> dtos = new ArrayList<Api_SeachAtrrDto>();
		Api_SeachAtrrDto dto;
		Api_SeachAtrrValuesDto vdto;
		for (SeachAtrrDto item : list) {
			dto = new Api_SeachAtrrDto();
			dto.setValues(new ArrayList<Api_SeachAtrrValuesDto>());
			dto.setIscheckbox(item.getIscheckbox());
			dto.setName(item.getName());
			dto.setType(item.getAttrtype());
			dto.setTypeid(item.getTypeid());
			for (SeachAtrrValueDto val : item.getValues()) {
				vdto = new Api_SeachAtrrValuesDto();
				vdto.setType(val.getType());
				if (item.getAttrtype() == 2) { // 品牌
					String spval = val.getValue();
					int index = spval.indexOf("-");
					String[] vals = new String[2];
					vals[0] = spval.substring(0, index);
					vals[1] = spval.substring(index + 1);
					vdto.setMinvalue(vals[0]);
					vdto.setDisvalue(vals[1]);
					dto.getValues().add(vdto);
					continue;
				}
				if (item.getAttrtype() == 3) { // 价格
					int v_type = val.getType(); // 值类型，1区间值 0固定值
					String spval = val.getValue();
					if (v_type == 1) {
						int index = spval.indexOf("-");
						String[] vals = new String[2];
						vals[0] = spval.substring(0, index);
						vals[1] = spval.substring(index + 1);
						vdto.setMinvalue(vals[0]);
						vdto.setMaxvalue(vals[1]);
					} else {
						vdto.setMinvalue(spval);
					}
					vdto.setDisvalue(spval + val.getUnit());
					dto.getValues().add(vdto);
					continue;
				}
				if (val.getType() == 0) {
					vdto.setMinvalue(val.getValue());
					vdto.setDisvalue(val.getValue() + val.getUnit());
					dto.getValues().add(vdto);
					continue;
				}
				String spval = val.getValue();
				int index = spval.indexOf("-");
				String[] vals = new String[2];
				vals[0] = spval.substring(0, index);
				vals[1] = spval.substring(index + 1);
				if (StringUtilsEX.IsNullOrWhiteSpace(vals[0])) {
					vdto.setDisvalue("小于" + vals[1] + val.getUnit());
				} else if (StringUtilsEX.IsNullOrWhiteSpace(vals[1])) {
					vdto.setDisvalue("大于" + vals[0] + val.getUnit());
				} else {
					vdto.setDisvalue(vals[0] + val.getUnit() + "-" + vals[1] + val.getUnit());
				}
				vdto.setMinvalue(vals[0]);
				vdto.setMaxvalue(vals[1]);
				dto.getValues().add(vdto);
			}
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public ProductDetailedDto getProductDetailedBySkuId(Integer spuid, Integer skuid, Integer itype, Integer usesite)
			throws Exception {
		ProductDetailedDto dto = productMapper.getProductDetailedBySkuId(skuid);
		getProDetailed(dto, spuid, itype, usesite);
		return dto;
	}

	// Integer spuid,
	@SuppressWarnings("unchecked")
	@Override
	public ProductDetailedDto getProductDetailed(Integer spuid, Integer itype, Integer usesite) throws Exception {
		ProductDetailedDto dto = new ProductDetailedDto();
		Spu spu = spuMapper.selectByPrimaryKey(spuid);
		if (spu != null) {
			if (spu.getIsdel())return dto;
			if(spu.getStatus() == ProStatusEnum.下架.getValue()){
				dto.setStatus(spu.getStatus());
				return dto;
			}
			dto.setId(spuid);
			dto.setTitle(spu.getName());
			dto.setPrice(spu.getPrice());
			dto.setIsnk(false);
			dto.setIstoday(0);
			dto.setOldprice(spu.getPrice());
			if(spu.getIstoday()!=null && spu.getIstoday()==1){
				dto.setIstoday(1);
			}
			if(spu.getProtype()!=null && spu.getProtype()==1){
				dto.setIsnk(true);
			}
			Sku sku = skuMapper.getBySpuId(spuid);
			if(sku!=null){
				dto.setSkuid(sku.getId());
			}
			List<ProductImgs> productImgs = productImgsMapper.selectBySpu(spuid);
			dto.setImgs(productImgs.stream().map((x) -> {
				return x.getImgurl();
			}).collect(Collectors.toList()));
			Shop shop = shopMapper.selectByPrimaryKey(spu.getShopid());
			dto.setShopname(shop.getName());
			dto.setShopid(shop.getId());
			dto.setShopimgurl(shop.getImgurl());
			dto.setShoptype(shop.getShoptype());
			ShopAuthentication authentication = shopAuthenticationMapper.selectByPrimaryByShopId(shop.getId());
			dto.setMarketingscope(authentication.getMarketingscope());
			
			
			final Integer year=DateUtil.getYear(new Date());
			final Integer month=DateUtil.getMonth(new Date());
			final Integer day=DateUtil.getDay(new Date());
			
			List<SkuShowtimeDto> showtimes=skuShowtimeMapper.getShowTimeDtoBySpuid(spuid);
			List<SkuShowtimeDto> lastlist=new ArrayList<SkuShowtimeDto>();
			List<SkuShowtimeDto> list=new ArrayList<SkuShowtimeDto>();
			if(showtimes!=null&&showtimes.size()>0){
				lastlist=showtimes.stream().filter(a->a.getShowyear().equals(year) && a.getShowmonth().equals(month)).collect(Collectors.toList());
			}
			list.addAll(lastlist);
			
			Calendar calendar = Calendar.getInstance();
	    	calendar.add(Calendar.MONTH, 1);
	    	final Integer nyear=DateUtil.getYear(calendar.getTime());
	    	final Integer nmonth=DateUtil.getMonth(calendar.getTime());
			if(showtimes!=null&&showtimes.size()>0){
				lastlist=showtimes.stream().filter(a->a.getShowyear().equals(nyear) && a.getShowmonth().equals(nmonth)).collect(Collectors.toList());
			}
			list.addAll(lastlist);
			dto.setShowtimeList(list);
			
			if(showtimes!=null&&showtimes.size()>0){
				Optional<SkuShowtimeDto> showtimeDto =showtimes.stream().filter(a->a.getShowyear().equals(year) && a.getShowmonth().equals(month) && a.getShowdays().equals(day)).findFirst();
				if (showtimeDto.isPresent()) {
					dto.setPrice(showtimeDto.get().getPrice());
					dto.setDatestr(year+"-"+month+"-"+day);
				}
			}
			
	    	
			List<SatisfactionGoodCountDto> dtos = shopMapper.getSfGoodCoutByShopId(shop.getId());
			if (dtos != null && dtos.size() > 0) {
				int commentSize = dtos.size();
				int stars = 0;
				for (SatisfactionGoodCountDto comm : dtos) {
					stars += comm.getAttitude();
				}
				double starr = Math.ceil(stars / commentSize);
				dto.setStar(starr + "");
			} else {
				dto.setStar("5");
			}
			Api_CommentCriteria criteria = new Api_CommentCriteria();
			criteria.setStatus(1);
			criteria.setSpuid(spuid);
			criteria.setOrderByClause("c.Date");
			criteria.setSort("desc");
			PageBean pageBean = commentService.getCommentBySpuId(criteria,1,3);
			dto.setCmts((List<CommentBaseDto>) pageBean.getBeanList());
			CommentAbr abr = commentMapper.getCommentAbr(spuid);
			dto.setCmtcount(abr.getBad() + abr.getGood() + abr.getMr());
			if (dto.getCmtcount() > 0){
				dto.setCmtgood(Math.ceil((abr.getGood() * 100.00) / dto.getCmtcount() * 100.00) / 100 + "%");
			}
		}
		return dto;
	}

	private void getProDetailed(ProductDetailedDto dto, Integer spuid, Integer itype, Integer usesite)
			throws Exception {
		if (dto == null)
			return;

		List<ProductImgs> listImgs = productImgsMapper.selectBySku(dto.getId());
		// 根据站点取对应图片
		if (listImgs != null) {
			switch (usesite) {
			case 1:
				dto.setImgs(listImgs.stream().filter(x -> x.getImgsite().contains(usesite.toString())).map((x) -> {
					return x.getImgpc();
				}).collect(Collectors.toList()));
				break;
			case 2:
				dto.setImgs(listImgs.stream().filter(x -> x.getImgsite().contains(usesite.toString())).map((x) -> {
					return x.getImgapp();
				}).collect(Collectors.toList()));
				break;
			case 3:
				dto.setImgs(listImgs.stream().filter(x -> x.getImgsite().contains(usesite.toString())).map((x) -> {
					return x.getImgwap();
				}).collect(Collectors.toList()));
				break;
			case 4:
				dto.setImgs(listImgs.stream().filter(x -> x.getImgsite().contains(usesite.toString())).map((x) -> {
					return x.getImgwechat();
				}).collect(Collectors.toList()));
				break;
			default:
				break;
			}
		}
		if (dto.getImgs().size() == 0) {
			List<String> imgs = new ArrayList<String>();
			imgs.add(spuMapper.selectByPrimaryKey(spuid).getImgurl());
			dto.setImgs(imgs);
		}
		if (skuPackageMapper.getCoutBySkuId(dto.getId(), usesite) > 0) {
			dto.setIspackage(true);
		} else {
			dto.setIspackage(false);
		}
		CommentAbr abr = commentMapper.getCommentAbr(spuid);
		dto.setCmtcount(abr.getBad() + abr.getGood() + abr.getMr());
		if (dto.getCmtcount() > 0)
			dto.setCmtgood(Math.ceil((abr.getGood() * 100.00) / dto.getCmtcount() * 100.00) / 100 + "%");
		PageBeanUtil pageBeanUtil = new PageBeanUtil();
		PageBean pageBean = new PageBean();
		pageBean.setPc(1);
		pageBean.setPs(3);
		pageBeanUtil.setPage(pageBean);
		Api_CommentCriteria acc = new Api_CommentCriteria();
		acc.setSpuid(spuid);
		pageBeanUtil.setCriteria(acc);
		dto.setCmts(commentMapper.getApiCmtListPage(pageBeanUtil));
		dto.setAtrrs(
				vSpecsinfoMapper.getBySkuId(dto.getId()).stream()
						.filter(x -> x.getDisplaylocation() != null
								&& x.getDisplaylocation().contains(SpecsDisplayEnum.商品详情.getValue().toString()))
						.map(x -> {
							IdValue value = new IdValue();
							value.setId(x.getSpecsid());
							value.setValue(x.getValue());
							value.setName(x.getName());
							return value;
						}).collect(Collectors.toList()));
		Coupon coupon = couponMapper.getAvailableFirst(dto.getShopid());
		if (coupon != null)
			dto.setIscoupon(true);
		if (itype == 1) {
			sgSpuDto ski = spikeActivityService.getActBySpuid(spuid, 1);
			if (ski != null && ski.getId() > 0) {
				dto.setSkiid(ski.getId());
				dto.setSkiprice(ski.getPrice().doubleValue());
				dto.setSkitime(ski.getEndTime());
				dto.setStock(ski.getSpucount());
				dto.setType(itype + 1);
			} else {
				dto.setType(itype);
			}
		} else if (itype == 2) {
			sgSpuDto ski = spikeActivityService.getActBySpuid(spuid, 0);
			if (ski != null && ski.getId() > 0) {
				dto.setSkiid(ski.getId());
				dto.setSkiprice(ski.getPrice().doubleValue());
				dto.setSkitime(ski.getEndTime());
				dto.setStock(ski.getSpucount());
				dto.setType(itype + 1);
			} else {
				dto.setType(itype);
			}
		} else {
			dto.setType(itype);
		}

		// V_Freights vFreights = freightService
		// .getByShopID(dto.getShopid(), "中国");
		// if (vFreights == null) {
		dto.setFreightdesc("");
		// } else {
		// Freight freight = freightMapper.selectByPrimaryKey(vFreights
		// .getFreightID());
		// if (freight != null)
		// dto.setFreightdesc(freight.getDescription());
		// else
		// dto.setFreightdesc("");
		// }
	}

	@Override
	public String getPcProductDetailedDesc(Integer spuid) throws Exception {

		return productMapper.getPCProductDetailedDesc(spuid);
	}

	@Override
	public String getApiProDetailDesc(Integer spuid) throws Exception {
		return productMapper.getApiProDetailDesc(spuid);
	}

	@Override
	public String getApigetProductDetailedAfterSaleService(Integer spuid) throws Exception {

		return productMapper.getApigetProductDetailedAfterSaleService(spuid);
	}

	@Override
	public List<SpecsBaseDto> getProductDetailedSpecs(Integer spuid) throws Exception {
		List<VSpecsinfo> list = vSpecsinfoMapper.getBySpuId(spuid);
		List<VSpecsinfo> fList = list.stream()
				.filter(x -> x.getDisplaylocation() != null
						&& x.getDisplaylocation().indexOf(SpecsDisplayEnum.商品详情.getValue().toString()) != -1
						&& (x.getSpecsstatus() == null || x.getSpecsstatus() == 0)
						&& (x.getValuestatus() == null || x.getValuestatus() == 0)
						&& (x.getTypestatus() == null || x.getTypestatus() == 0))
				.collect(Collectors.toList());
		if (fList == null)
			fList = new ArrayList<VSpecsinfo>();
		Map<Integer, List<VSpecsinfo>> specsgroup = fList.stream().collect(Collectors.groupingBy(x -> x.getSpecsid()));
		List<SpecsBaseDto> specsBaseDto = new ArrayList<SpecsBaseDto>();
		SpecsBaseDto b = null;

		for (Map.Entry<Integer, List<VSpecsinfo>> entry : specsgroup.entrySet()) {
			b = new SpecsBaseDto();
			b.setId(entry.getKey());
			b.setName(entry.getValue().get(0).getName());
			b.setValues(entry.getValue().stream().map(x -> {
				IdValue value = new IdValue();
				value.setId(x.getSkuid());
				value.setValue(x.getValue());
				return value;
			}).collect(Collectors.toList()));
			specsBaseDto.add(b);
		}

		return specsBaseDto;
	}

	@Override
	public List<Api_Detail_Specs> getProductDetailedSpecsInfo(Integer skuid) throws Exception {
		List<VSpecsinfo> list = vSpecsinfoMapper.getBySkuId(skuid);
		List<Api_Detail_Specs> dto = new ArrayList<Api_Detail_Specs>();
		Map<String, List<VSpecsinfo>> specsgroup = list.stream()
				.filter(x -> (x.getDisplaylocation() != null
						&& x.getDisplaylocation().contains(SpecsDisplayEnum.商品详情.getValue().toString()))
						&& (x.getSpecsstatus() == null || x.getSpecsstatus() == 0)
						&& (x.getValuestatus() == null || x.getValuestatus() == 0)
						&& (x.getTypestatus() == null || x.getTypestatus() == 0) && x.getTypename() != null)
				.collect(Collectors.groupingBy(x -> x.getTypename()));
		Api_Detail_Specs nValue = null;
		for (Map.Entry<String, List<VSpecsinfo>> entry : specsgroup.entrySet()) {
			nValue = new Api_Detail_Specs();
			nValue.setName(entry.getKey());
			Map<String, List<VSpecsinfo>> vchild = entry.getValue().stream()
					.collect(Collectors.groupingBy(x -> x.getName()));
			for (Map.Entry<String, List<VSpecsinfo>> entry1 : vchild.entrySet()) {
				NameValue nValue1 = new NameValue();
				nValue1.setName(entry1.getKey());
				nValue1.setValue(entry1.getValue().stream().findFirst().get().getValue());
				nValue.getChilds().add(nValue1);
			}
			dto.add(nValue);
		}
		return dto;
	}

	@Override
	public List<Api_Detail_Specs> getProductSpecs(Integer skuid, String display) throws Exception {
		List<VSpecsinfo> list = vSpecsinfoMapper.getBySkuId(skuid);
		List<Api_Detail_Specs> dto = new ArrayList<Api_Detail_Specs>();
		Map<String, List<VSpecsinfo>> specsgroup = list.stream()
				.filter(x -> (x.getDisplaylocation() != null && x.getDisplaylocation().contains(display))
						&& (x.getSpecsstatus() == null || x.getSpecsstatus() == 0)
						&& (x.getValuestatus() == null || x.getValuestatus() == 0)
						&& (x.getTypestatus() == null || x.getTypestatus() == 0))
				.collect(Collectors.groupingBy(x -> x.getTypename() == null ? "" : x.getTypename()));
		;

		Api_Detail_Specs nValue = null;
		for (Map.Entry<String, List<VSpecsinfo>> entry : specsgroup.entrySet()) {
			nValue = new Api_Detail_Specs();
			nValue.setName(entry.getKey());
			Map<String, List<VSpecsinfo>> vchild = entry.getValue().stream()
					.collect(Collectors.groupingBy(x -> x.getName()));
			for (Map.Entry<String, List<VSpecsinfo>> entry1 : vchild.entrySet()) {
				NameValue nValue1 = new NameValue();
				nValue1.setName(entry1.getKey());
				nValue1.setValue(entry1.getValue().stream().findFirst().get().getValue());
				nValue.getChilds().add(nValue1);
			}
			dto.add(nValue);
		}
		for (int i = 0; i < dto.size(); i++) {
			Api_Detail_Specs obj = new Api_Detail_Specs();
			if (dto.get(i).getName() == "") {
				obj = dto.get(i);
				dto.remove(i);
				dto.add(obj);
			}
		}
		return dto;
	}

	@Override
	public List<ProductDto> getgetNewsShelvesProByCid(Integer classid, int sites) throws Exception {
		List<ProductDto> list = productMapper.getgetNewsShelvesProByCid(classid, sites);
		// list.forEach(x -> x.setImg(ConfigUtil.get_instances().fileUrl() +
		// x.getImg()));
		return list;//
	}

	@Override
	public List<ProductDto> getgetNewsSellingProByCid(Integer classid, int sites) throws Exception {
		List<ProductDto> list = productMapper.getgetNewsSellingProByCid(classid, sites);
		// list.forEach(x -> x.setImg(ConfigUtil.get_instances().fileUrl() +
		// x.getImg()));
		return list;//
	}

	@Override
	public ProductDto selectInfoById(Integer id, int webset) throws Exception {

		return productMapper.queryInfoById(id, webset);
	}

	@Override
	public PageBean getProListPage_pc(ProductCriteria cria, int index, int size) throws Exception {
		PageBeanUtil util = new PageBeanUtil(cria, index, size);
		PageBean bean = util.getPage();
		List<PCProListDto> list = productMapper.getProListPage(util);
		for (PCProListDto pcProListDto : list) {
			List<ProductImgs> img = proimgService.selectBySpu(pcProListDto.getSpuid());
			if (img.size() > 4) {
				img = img.subList(0, 4);
			}
			if (img.size() > 0) {
				pcProListDto.setImages(img);
			} else {
				ProductImgs im = new ProductImgs();
				im.setImgurl(pcProListDto.getImgUrl());
				img.add(im);
				pcProListDto.setImages(img);
			}

		}
		bean.setBeanList(list);
		return bean;
	}

	@Override
	public PageBean getSerachProductListPage_pc(Api_ProductCriteria criteria, Integer page, Integer size)
			throws Exception {
		// 判断是否有关键字
		if (!StringUtilsEX.IsNullOrWhiteSpace(criteria.getKeyword())) {
			if (StringUtilsEX.IsNullOrWhiteSpace(criteria.getIdliststr())) {
				List<ProLuceneDto> list = luceneSearchService.getProidList(criteria.getKeyword().trim());
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
				}
			}
		}
		PageBeanUtil util = new PageBeanUtil(criteria, page, size);
		PageBean bean = util.getPage();
		List<PCProListDto> list = productMapper.getSerachProductListPage(util);
		for (PCProListDto pcProListDto : list) {
			List<ProductImgs> img = proimgService.selectBySpu(pcProListDto.getSpuid());
			if (img.size() > 0) {
				pcProListDto.setImages(img);
			} else {
				ProductImgs im = new ProductImgs();
				im.setImgurl(pcProListDto.getImgUrl());
				img.add(im);
				pcProListDto.setImages(img);
			}
			if (img.size() > 4) {
				pcProListDto.setImages(img.subList(0, 4));
			}
		}
		bean.setBeanList(list);

		return bean;
	}

	@Override
	public PageBean getWapProductList(Api_ProductCriteria criteria, Integer page, Integer size) throws Exception {

		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<ProductDto> beanList = productMapper.getWapProductListPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public ProductDetailedDto getProductDetaileds(Integer spuid, Integer itype, Integer usesite) throws Exception {
		ProductDetailedDto dto = productMapper.getProductDetaileds(spuid, usesite);
		getProDetailed(dto, spuid, itype, usesite);
		return dto;
	}

}

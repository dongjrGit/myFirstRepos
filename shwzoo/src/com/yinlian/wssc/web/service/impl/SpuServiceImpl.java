/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.SiteType;
import com.yinlian.api.app.dto.Api_ProductsDto;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.api.app.dto.SpuDto;
import com.yinlian.pc.dto.GuessLikeDto;
import com.yinlian.pc.dto.PcSpuDto;
import com.yinlian.wap.dto.ShevelSpuDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_SpuCriteria;
import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.dto.FindRelationDto;
import com.yinlian.wssc.web.dto.SkuTimeStockDto;
import com.yinlian.wssc.web.dto.TopicRelateInfo;
import com.yinlian.wssc.web.dto.VSkuShopName;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.CategoryMapper;
import com.yinlian.wssc.web.mapper.ShopcartprosMapper;
import com.yinlian.wssc.web.mapper.SkuMapper;
import com.yinlian.wssc.web.mapper.SkuShowtimeMapper;
import com.yinlian.wssc.web.mapper.SkuSpecsvMapper;
import com.yinlian.wssc.web.mapper.SpecsvaluesMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.SkuSpecsv;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.SpuUpdateStatus;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaFindRelate;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.CriteriaShopSheive;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.EnumUtils;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yl.soft.uitl.DateUtil;

/**
 * spu的业务实现类
 * 
 * @author Administrator
 * @version $Id: SpuServiceImpl.java, v 0.1 2016年3月7日 下午1:38:15 Administrator
 *          Exp $
 */
public class SpuServiceImpl implements SpuService {

	@Autowired
	private SpuMapper spuMapper;

	@Autowired
	private SkuMapper skuMapper;
	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private SpecsvaluesMapper valuesMapper;

	@Autowired
	private SkuSpecsvMapper specsvMapper;

	@Autowired
	private ShopcartprosMapper shopcartprosMapper;
	@Autowired
	private SkuShowtimeMapper     skuShowtimeMapper;
	/**
	 * @see com.yinlian.wssc.web.service.SpuService#queryByCriteria(com.yinlian.wssc.web.util.Criteria,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean queryByCriteria(Criteria criteria, Integer pc, Integer ps)
			throws Exception {

		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<SpuWithBLOBs> beanList = spuMapper.selectDirectPage(pageBeanUtil);
//		for (SpuWithBLOBs spu : beanList) {
//			String fullpath = spu.getFullpath();
//			String fullpathname = "";
//			if (fullpath != null && !"".equals(fullpath)) {
//				String[] array = fullpath.split(",");
//				for (int i = 0; i < array.length; i++) {
//					String cfid = array[i];
//					if (StringUtils.isNotNull(cfid)) {
//						Category category = categoryMapper
//								.selectByPrimaryKey(Integer.valueOf(cfid));
//						fullpathname = fullpathname + "-" + category.getName();
//					}
//				}
//			}
//			fullpathname = fullpathname
//					.substring(fullpathname.indexOf("-") + 1);
//			spu.setFullpathname(fullpathname);
//		}
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * 根据品牌id分页查询 商品
	 * 
	 * @param criteria
	 *            查询条件
	 * @param pc
	 *            当前页
	 * @param ps
	 *            每页大小
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean getSpuByBidPage(Api_SpuCriteria criteria, Integer pc,
			Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//多条件查询

		PageBean pageBean = pageBeanUtil.getPage();
		List<Api_ProductsDto> beanList = spuMapper.getSpuByBidPage(pageBeanUtil);
		
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * @see com.yinlian.wssc.web.service.SpuService#update(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) throws Exception {

		SpuWithBLOBs bloBs = spuMapper.selectByPrimaryKey(id);
		bloBs.setIsdel(true);
		bloBs.setDeltime(new Date());
		spuMapper.updateByPrimaryKey(bloBs);
		skuMapper.deleteSkuBySpuID(bloBs.getId());
		return 1;
	}

	/**
	 * @see com.yinlian.wssc.web.service.SpuService#queryById(java.lang.Integer)
	 */
	@Override
	public SpuWithBLOBs queryById(Integer id) throws Exception {

		return spuMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSpu(SpuWithBLOBs spuWithBLOBs, List<Sku> skulist,SkuShowtime showtime)
			throws Exception {
		
		if (spuWithBLOBs == null) {
			return 0;
		}
		if (skulist == null || skulist.size()==0) {
			return 0;
		}
		double minprice=skulist.get(0).getPrice().doubleValue();
		double maxprice=skulist.get(0).getPrice().doubleValue();
		for (Sku sku : skulist) {
			if(sku.getAppprice().doubleValue()>maxprice){
				maxprice=sku.getPrice().doubleValue();
			}
			if(minprice>sku.getAppprice().doubleValue()){
				minprice=sku.getPrice().doubleValue();
			}
		}
		// 添加SPU
		spuWithBLOBs.setWapprice(BigDecimal.valueOf(maxprice));
		spuWithBLOBs.setAppprice(BigDecimal.valueOf(minprice));
		spuMapper.insertSpu(spuWithBLOBs);
		// 添加SKU
		for (Sku sku : skulist) {
			sku.setSpuId(spuWithBLOBs.getId());
			skuMapper.addSku(sku);
			if(showtime!=null){
				showtime.setSpuid(spuWithBLOBs.getId());
				showtime.setSkuid(sku.getId());
				skuShowtimeMapper.insert(showtime);
			}
		}
		
		return 1;
	}
	public int updateSpu(SpuWithBLOBs spuWithBLOBs, List<Sku> skulist
			,SkuShowtime showtime) throws Exception{
		
		if (spuWithBLOBs == null) {
			return 0;
		}
		if (skulist == null || skulist.size()==0) {
			return 0;
		}
		double minprice=skulist.get(0).getPrice().doubleValue();
		double maxprice=skulist.get(0).getPrice().doubleValue();
		for (Sku sku : skulist) {
			if(sku.getAppprice().doubleValue()>maxprice){
				maxprice=sku.getPrice().doubleValue();
			}
			if(minprice>sku.getAppprice().doubleValue()){
				minprice=sku.getPrice().doubleValue();
			}
		}
		spuWithBLOBs.setWapprice(BigDecimal.valueOf(maxprice));
		spuWithBLOBs.setAppprice(BigDecimal.valueOf(minprice));
		spuMapper.updateByPrimaryKeyWithBLOBs(spuWithBLOBs);
		
		for (Sku sku : skulist) {
			skuMapper.updateByPrimaryKey(sku);
			List<SkuShowtime> showtimes=skuShowtimeMapper.getBySkuid(sku.getId());
			if(showtimes!=null && showtimes.size()>0){
				Optional<SkuShowtime> optional=showtimes.stream().filter(x->
				x.getShowyear().equals(showtime.getShowyear()) 
				&& x.getShowmonth().equals(showtime.getShowmonth())).findFirst();
				if(optional.isPresent()){
					showtime.setId(optional.get().getId());
					skuShowtimeMapper.updateByPrimaryKey(showtime);
				}else{
					skuShowtimeMapper.insert(showtime);
				}
			}
		}
		
		return 1;
	}
	@Override
	public int updateBatchStatus(SpuUpdateStatus sus) throws Exception {
		return spuMapper.updateBatchStatus(sus);
	}

	/**
	 * 模糊检索标准商品列表
	 */
	public List<Spu> getSpuStartWithName(Integer shopid, String name,
			Integer classid) throws Exception {
		Spu spu = new Spu();
		spu.setName(name);
		spu.setShopid(shopid);
		spu.setClassid(classid);
		return spuMapper.getSpuStartWithName(spu);
	}

	@Override
	public int updateCommentCount(Integer spuid) throws Exception {

		return spuMapper.updateById(spuid);
	}

	/**
	 * 分頁查詢商品主題
	 * 
	 * @see com.yinlian.wssc.web.service.SpuService#queryTopicRelateListByCriteria(com.yinlian.wssc.web.util.CriteriaTopic,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<TopicRelateInfo> beanList = spuMapper.selectTopicByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<Spu> queryAll(Integer status) throws Exception {

		return spuMapper.selectAll(status);
	}

	/**
	 * @see com.yinlian.wssc.web.service.SpuService#queryByAppCriteria(com.yinlian.wssc.web.util.CriteriaShop,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryByAppCriteria(CriteriaShop criteria, Integer pc,
			Integer ps) throws Exception {

		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<SpuDto> list = new ArrayList<SpuDto>();
		List<Spu> beanList = spuMapper.selectDirectAppPage(pageBeanUtil);
		if (beanList != null && beanList.size() > 0) {
			for (Spu spu : beanList) {
				SpuDto dto = new SpuDto();
				BeanUtils.copyProperties(spu, dto);
				switch (EnumUtils.getMyEnum(SiteType.class,
						criteria.getSiteType(), SiteType.pc)) {
				case wap:
					if (spu.getWapprice() == null) {
						spu.setWapprice(spu.getPrice());
					}
					dto.setPrice(spu.getWapprice().floatValue());
					dto.setImgurl(spu.getImgurlApp());
					break;
				case pc:
					dto.setPrice(spu.getPrice().floatValue());
					dto.setImgurl(spu.getImgurlApp());
					break;
				case app:
					dto.setPrice(spu.getAppprice().floatValue());
					dto.setImgurl(spu.getImgurlApp());
					break;
				case wechart:
					dto.setPrice(spu.getWechatprice().floatValue());
					dto.setImgurl(spu.getImgurlApp());
					break;
				default:
					dto.setPrice(spu.getPrice().floatValue());
					break;
				}
				list.add(dto);
			}
		}
		pageBean.setBeanList(list);
		return pageBean;
	}

	public int updateShelves(SpuUpdateStatus sus) throws Exception {
		return spuMapper.updateShelves(sus);
	}

	public int updateShelve(int status, int id) throws Exception {
		return spuMapper.updateShelve(status, id);
	}

	@Override
	public List<Spu> getSpuStartWithNames(String name) {

		return spuMapper.getSpuStartWithNames(name);
	}

	@Override
	public PageBean queryByPageShop(CriteriaShop criteria, Integer page,
			Integer pagesize) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, pagesize);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Spu> list = spuMapper.selectDirectAppPage(pageBeanUtil);
		pageBean.setBeanList(list);
		return pageBean;
	}

	/**
	 * 获取店铺商品列表
	 * 
	 * @param criteria
	 * @return
	 */
	@Override
	public PageBean getShopSpuPage(CriteriaShop criteria, Integer page,
			Integer pagesize) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, pagesize);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Spu> list = spuMapper.selectShopSpuPage(pageBeanUtil);
		pageBean.setBeanList(list);
		return pageBean;
	}

	@Override
	public List<PcSpuDto> querySpuList(Integer shopid) throws Exception {

		return spuMapper.querySpuList(shopid);
	}

	@Override
	public List<ShevelSpuDto> queryShevelSpu(CriteriaShopSheive aoc)
			throws Exception {

		return spuMapper.queryShevelSpuList(aoc);
	}

	@Override
	public PageBean queryShevelSpuPage(CriteriaShopSheive aoc, Integer pc,
			Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(aoc, pc, ps);
		PageBean pageBean = pageBeanUtil.getPage();
		List<ShevelSpuDto> list = spuMapper.getShevelSpuPage(pageBeanUtil);
		pageBean.setBeanList(list);
		return pageBean;
	}

	@Override
	public PageBean queryFindRelationByCriteria(CriteriaFindRelate criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<FindRelationDto> beanList = spuMapper
				.seleteFindRelationByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	public List<VSkuShopName> getBySkuName(String name) throws Exception {
		return spuMapper.getBySkuName(name);

	}

	@Override
	public VSkuShopName getBySkuId(Integer id) throws Exception {
		return spuMapper.getBySkuId(id);
	}

	@Override
	public List<Spu> getSpuStartWithNamesIsby(String name) throws Exception {
		return spuMapper.getSpuStartWithNamesIsby(name);
	}

	@Override
	public List<GuessLikeDto> findGuessLikeByShopCartID(Integer userid,
			Integer size) throws Exception {
		return shopcartprosMapper.selectByShopCartID(userid, size);
	}

	@Override
	public List<Spu> platgetSspuStartWithName(String name) throws Exception {
		return spuMapper.platgetSspuStartWithName(name,null);
	}

	@Override
	public List<Spu> platgetSspuStartWithName(String name,Integer shopid) throws Exception {
		return spuMapper.platgetSspuStartWithName(name,shopid);
	}

	@Override
	public int update(SpuWithBLOBs record) throws Exception {
	
		return spuMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public Spu getByName(String name, Integer id) throws Exception {
		
		return spuMapper.getByName(id, name);
	}

	@Override
	public List<SkuShowtime> getBySkuid(Integer skuid) throws Exception {
		
		return skuShowtimeMapper.getBySkuid(skuid);
	}

	@Override
	public SkuShowtime getBySkuidLast(Integer skuid) throws Exception {

		return skuShowtimeMapper.getBySkuidLast(skuid);
	}

	@Override
	public List<Spu> getSpuStartWithNamesIszk(String name) throws Exception {
		return spuMapper.getSpuStartWithNamesIszk(name);
	}

	@Override
	public PageBean getProByShopId(Api_SpuCriteria criteria, Integer page1, Integer size11) throws Exception {
		PageBeanUtil beanUtil = new PageBeanUtil(criteria, page1, size11);
		PageBean pageBean = beanUtil.getPage();
		List<Spu> spus = spuMapper.getProByShopIdByPage(beanUtil);
		pageBean.setBeanList(spus);
		return pageBean;
	}

	@Override
	public PageBean getProByName(ProductCriteria criteria, Integer page1, Integer size11) throws Exception {
		PageBeanUtil beanUtil = new PageBeanUtil(criteria, page1, size11);
		PageBean pageBean = beanUtil.getPage();
		List<Spu> spus = spuMapper.getProByNameByPage(beanUtil);
		Integer year=DateUtil.getYear(new Date());
		Integer month=DateUtil.getMonth(new Date());
		Integer day=DateUtil.getDay(new Date());
		for (Spu spu : spus) {
			spu.setOldprice(spu.getPrice());
			spu.setPrice(new BigDecimal(0));
			SkuShowtime  skutime=getSkuTime(spu.getId(), year, month, day);
			if(skutime!=null){
				spu.setPrice(skutime.getPrice());
			}
			
		}
		pageBean.setBeanList(spus);
		return pageBean;
	}

	@Override
	public SkuShowtime getBySkuidAndMonth(Integer skuid, Integer year,
			Integer month) throws Exception {
		
		return skuShowtimeMapper.getBySkuidAndMonth(skuid, year, month);
	}

	@Override
	public int insertSpuNew(SpuWithBLOBs record, Sku sku, List<SkuShowtime> showtimes) throws Exception {
		if (record == null) {
			return 0;
		}
		if (sku == null) {
			return 0;
		}
		spuMapper.insertSpu(record);
		
		sku.setSpuId(record.getId());
		skuMapper.addSku(sku);
		
		for (SkuShowtime showtime : showtimes) {
			showtime.setSpuid(record.getId());
			showtime.setSkuid(sku.getId());
			skuShowtimeMapper.insert(showtime);
		}
		return 1;
	}

	
	@Override
	public List<SkuShowtime> getListBySkuidAndMonth(Integer skuid, Integer spuid,Integer year,
			Integer month) throws Exception {
		
		return skuShowtimeMapper.getListBySkuidAndMonth(skuid,spuid, year, month);
	}

	@Override
	public int updateSpuNew(SpuWithBLOBs record, Sku sku, List<SkuShowtime> showtimes, Integer showy, Integer showm)
			throws Exception {
		if (record == null) {
			return 0;
		}
		if (sku == null) {
			return 0;
		}
		spuMapper.updateByPrimaryKeyWithBLOBs(record);
		
		skuMapper.updateByPrimaryKey(sku);
		
		//删除当月的库存价格，重新添加
		skuShowtimeMapper.delListBySkuidAndMonth(sku.getId(),record.getId(),showy,showm);
		
		for (SkuShowtime showtime : showtimes) {
			showtime.setSpuid(record.getId());
			showtime.setSkuid(sku.getId());
			skuShowtimeMapper.insert(showtime);
		}
		return 1;
	}

	@Override
	public PageBean getSpuStockPage(ProductCriteria criteria, Integer page, Integer size) throws Exception {
		PageBeanUtil beanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = beanUtil.getPage();
		List<SkuTimeStockDto> spus = skuShowtimeMapper.getSpuStockPage(beanUtil);
		for (SkuTimeStockDto dto : spus) {
			if(dto.getShowmonth()<10 && dto.getShowdays()<10){
				
				dto.setDateStr(dto.getShowyear()+"-0"+dto.getShowmonth()+"-0"+dto.getShowdays());
				
			}else if(dto.getShowmonth()<10 && dto.getShowdays()>=10){
			
				dto.setDateStr(dto.getShowyear()+"-0"+dto.getShowmonth()+"-"+dto.getShowdays());
				
			}else if(dto.getShowmonth()>=10 && dto.getShowdays()<10){
				
				dto.setDateStr(dto.getShowyear()+"-"+dto.getShowmonth()+"-0"+dto.getShowdays());
				
			}else {
				dto.setDateStr(dto.getShowyear()+"-"+dto.getShowmonth()+"-"+dto.getShowdays());
			}
		}
		pageBean.setBeanList(spus);
		return pageBean;
	}

	@Override
	public SkuShowtime getSkuShowTimeById(Integer id) throws Exception {
		
		return skuShowtimeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateSkuShowTime(SkuShowtime skuShowtime) throws Exception {
		
		return skuShowtimeMapper.updateByPrimaryKey(skuShowtime);
	}

	@Override
	public int delSpuTimeStock(Integer id) throws Exception {
		
		return skuShowtimeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int delTimeStockList(List<Integer> idlist) throws Exception {
	
		return skuShowtimeMapper.deleteList(idlist);
	}

	@Override
	public int insertTimeStockList(List<SkuShowtime> showtimes) throws Exception {
		
		return skuShowtimeMapper.insertList(showtimes);
	}

	@Override
	public SkuShowtime getSkuTimeByDay(Integer skuid, Integer spuid, Integer showyear, Integer showmonth,
			Integer showdays) throws Exception {
		
		return skuShowtimeMapper.getSkuTimeByDay(skuid,spuid,showyear,showmonth,showdays);
	}

	@Override
	public SkuShowtime getSkuTime(Integer spuid, Integer year, Integer month, Integer day) throws Exception {
		
		return skuShowtimeMapper.getSkuTime(spuid,year,month,day);
	}

	@Override
	public void getProByShopIdNews(Api_SpuCriteria criteria, Integer page, Integer size, ReusltItem item)
			throws Exception {
		List<Spu> beanlist=spuMapper.getProByShopId(criteria);
		if(beanlist==null || beanlist.size()==0){
			item.setData(new ArrayList<Spu>());
			item.setPageIndex(page);
			item.setPageSize(size);
			item.setMaxRow(0);
			return;
	    }
		
		Integer year=DateUtil.getYear(new Date());
		Integer month=DateUtil.getMonth(new Date());
		Integer day=DateUtil.getDay(new Date());
		for (Spu spu : beanlist) {
			spu.setOldprice(spu.getPrice());
			spu.setAppprice(spu.getPrice());
			spu.setPrice(new BigDecimal(0));
			SkuShowtime  skutime=getSkuTime(spu.getId(), year, month, day);
			if(skutime!=null){
				spu.setPrice(skutime.getPrice());
				spu.setAppprice(skutime.getPrice());
			}
			
		}
		
		if(criteria.getOrderByClause().equals("AppPrice") && criteria.getSort().equals("desc")){
			
			beanlist=beanlist.stream().sorted((p1, p2) -> p2.getAppprice().
					compareTo(p1.getAppprice())).collect(Collectors.toList());
			
		}else if(criteria.getOrderByClause().equals("AppPrice") && criteria.getSort().equals("asc")){
			
			beanlist=beanlist.stream().sorted((p1, p2) -> p1.getAppprice().
					compareTo(p2.getAppprice())).collect(Collectors.toList());
			
		}else if(criteria.getOrderByClause().equals("ShelveTime") && criteria.getSort().equals("desc")){
			
			beanlist=beanlist.stream().sorted((p1, p2) -> p2.getShelvetime().
					compareTo(p1.getShelvetime())).collect(Collectors.toList());
			
		}else if(criteria.getOrderByClause().equals("ShelveTime") && criteria.getSort().equals("asc" )){
			
			beanlist=beanlist.stream().sorted((p1, p2) -> p1.getShelvetime().
					compareTo(p2.getShelvetime())).collect(Collectors.toList());
		}
		
		 int totalcount=beanlist.size(); //列表总数
	     int totalpage=totalcount%size==0?totalcount/size:(totalcount/size)+1; //总页数
	     if(page==totalpage){
	    	 if(totalcount%size==0){
	    		 beanlist=beanlist.stream().skip((page-1)*size).limit(size).collect(Collectors.toList());
	    	 }else{
	    		 beanlist=beanlist.stream().skip((page-1)*size).limit(totalcount%size).collect(Collectors.toList());
	    	 }
	     }else{
	    	 beanlist=beanlist.stream().skip((page-1)*size).limit(size).collect(Collectors.toList());
	     }
		item.setData(beanlist);
		item.setMaxRow(totalcount);
		item.setPageIndex(page);
		
	}
}

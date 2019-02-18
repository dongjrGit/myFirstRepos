/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.pc.dto.GuessLikeDto;
import com.yinlian.pc.dto.PcSpuDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_SpuCriteria;
import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.dto.VSkuShopName;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.SkuSpecsv;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.SpuUpdateStatus;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaFindRelate;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.CriteriaShopSheive;
import com.yinlian.wssc.web.util.CriteriaTopic;

/**
 * spu接口类
 * 
 * @author Administrator
 * @version $Id: SpuService.java, v 0.1 2016年3月7日 下午1:36:49 Administrator Exp $
 */
public interface SpuService {

	/**
	 * 分页查询 直营商品的信息
	 * 
	 * @param criteria
	 * @param pc
	 *            当前页
	 * @param ps
	 *            每页大小
	 */
	PageBean queryByCriteria(Criteria criteria, Integer pc, Integer ps)
			throws Exception;

	/**
	 * 根据品牌id分页查询 商品 
	 * @param criteria 查询条件
	 * @param pc
	 *            当前页
	 * @param ps
	 *            每页大小
	 * @return
	 * @throws Exception
	 */
	PageBean getSpuByBidPage(Api_SpuCriteria criteria, Integer pc, Integer ps)
			throws Exception;

	/**
	 * 
	 * @param string2Integer
	 * @return
	 */
	int delete(Integer id) throws Exception;

	/**
	 * 根据id查询一个spu
	 * 
	 * @param id
	 * @return
	 */
	SpuWithBLOBs queryById(Integer id) throws Exception;

	/**
	 * 添加spu，添加一个默认的SKU和规格关联
	 * 
	 * @param spuWithBLOBs
	 * @param skus
	 * @return
	 * @throws Exception
	 */
	int insertSpu(SpuWithBLOBs spuWithBLOBs, List<Sku> skus,SkuShowtime showtime) throws Exception;
	
	int updateSpu(SpuWithBLOBs spuWithBLOBs, List<Sku> skus,SkuShowtime showtime) throws Exception;
	
	int updateBatchStatus(SpuUpdateStatus sus) throws Exception;

	List<Spu> getSpuStartWithName(Integer shopid, String name, Integer classid)
			throws Exception;

	int updateCommentCount(Integer spuid) throws Exception;

	/**
	 * 分頁查詢商品主題
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria, Integer pc,
			Integer ps) throws Exception;

	public List<Spu> queryAll(Integer status) throws Exception;

	/**
	 * 
	 * @param criteria
	 * @param toInt
	 * @param toInt2
	 * @return
	 */
	PageBean queryByAppCriteria(CriteriaShop criteria, Integer pc, Integer ps)
			throws Exception;

	/**
	 * 
	 * @param criteria
	 * @param toInt
	 * @param toInt2
	 * @return
	 */
	PageBean queryByPageShop(CriteriaShop criteria, Integer page,
			Integer pagesize) throws Exception;

	/**
	 * 获取店铺商品列表
	 * 
	 * @param criteria
	 * @return
	 */
	PageBean getShopSpuPage(CriteriaShop criteria, Integer page,
			Integer pagesize) throws Exception;

	/**
	 * 商品上架 更新最近上架时间
	 * 
	 * @param sus
	 * @return
	 * @throws Exception
	 */
	int updateShelves(SpuUpdateStatus sus) throws Exception;

	int updateShelve(int status, int id) throws Exception;

	/**
	 * 根据名称检索商品
	 * 
	 * @param name
	 * @return
	 */
	List<Spu> getSpuStartWithNames(String name);

	List<PcSpuDto> querySpuList(Integer shopid) throws Exception;

	List<com.yinlian.wap.dto.ShevelSpuDto> queryShevelSpu(CriteriaShopSheive aoc)
			throws Exception;

	PageBean queryShevelSpuPage(CriteriaShopSheive aoc, Integer pc, Integer ps)
			throws Exception;

	PageBean queryFindRelationByCriteria(CriteriaFindRelate criteria,
			Integer pc, Integer ps) throws Exception;

	List<Spu> getSpuStartWithNamesIsby(String name) throws Exception;

	List<VSkuShopName> getBySkuName(String name) throws Exception;

	VSkuShopName getBySkuId(Integer id) throws Exception;

	/**
	 * 猜你喜欢
	 * 
	 * @param userid
	 *            购物车ID
	 * @param size
	 *            数量
	 * @return
	 * @throws Exception
	 */
	List<GuessLikeDto> findGuessLikeByShopCartID(Integer userid, Integer size)
			throws Exception;

	/**
	 * 平台拉所有供应商的sspu
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List<Spu> platgetSspuStartWithName(String name) throws Exception;

	/**
	 * 卖家供应商的sspu
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List<Spu> platgetSspuStartWithName(String name,Integer shopid)throws Exception;

	int update(SpuWithBLOBs record)throws Exception;
	
	/**
	 * 检查商品名称是否重复
	 * @param name
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Spu getByName(String name,Integer id)throws Exception;
	
	List<SkuShowtime> getBySkuid(Integer skuid)throws Exception;
	
	SkuShowtime getBySkuidLast(Integer skuid)throws Exception;
	
	SkuShowtime getBySkuidAndMonth(Integer skuid,Integer year,Integer month)throws Exception;
	/**
	 * 获取折扣商品列表
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List<Spu> getSpuStartWithNamesIszk(String name)throws Exception;

	/**
	 * 根据店铺id获取商品列表
	 * @param criteria
	 * @param page1
	 * @param size11
	 * @return
	 * @throws Exception
	 */
	PageBean getProByShopId(Api_SpuCriteria criteria, Integer page1, Integer size11)throws Exception;

	/**
	 * 根据商品名称模糊查询商品
	 * @param criteria
	 * @param page1
	 * @param size11
	 * @return
	 * @throws Exception
	 */
	PageBean getProByName(ProductCriteria criteria, Integer page1, Integer size11)throws Exception;

	int insertSpuNew(SpuWithBLOBs record, Sku sku, List<SkuShowtime> showtimes)throws Exception;

	List<SkuShowtime> getListBySkuidAndMonth(Integer skuid,Integer spuid,Integer year,Integer month)throws Exception;

	int updateSpuNew(SpuWithBLOBs record, Sku sku, List<SkuShowtime> showtimes, Integer showy, Integer showm)throws Exception;

	PageBean getSpuStockPage(ProductCriteria criteria, Integer page, Integer size)throws Exception;

	SkuShowtime getSkuShowTimeById(Integer id)throws Exception;

	int updateSkuShowTime(SkuShowtime skuShowtime)throws Exception;

	int delSpuTimeStock(Integer id)throws Exception;

	int delTimeStockList(List<Integer> idlist)throws Exception;

	int insertTimeStockList(List<SkuShowtime> showtimes)throws Exception;

	SkuShowtime getSkuTimeByDay(Integer skuid, Integer spuid, 
			Integer showyear, Integer showmonth, Integer showdays)throws Exception;

	SkuShowtime getSkuTime(Integer spuid, Integer year, Integer month, Integer day)throws Exception;

	void getProByShopIdNews(Api_SpuCriteria criteria, Integer page1, Integer size11, ReusltItem item)throws Exception;
	
}

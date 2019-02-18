package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.dto.SkuValuesDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.SkuSpecsv;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaSku;

/**
 * 库存商品管理
 * 
 * @author Administrator
 *
 */
public interface SkuService {

	int insert(Sku sku) throws Exception;

	int updateSku(Sku sku) throws Exception;

	Sku selectByID(Integer id) throws Exception;

	PageBean getList(Criteria criteria, Integer pc, Integer ps) throws Exception;

	int updatePrice(Integer settype, Double price, Integer id) throws Exception;

	List<Sku> getListBySpuID(Integer spuid) throws Exception;

	int addSku(Sku sku, List<Specsvalues> sv_addlist, List<SkuSpecsv> ssv_addlist) throws Exception;

	int editSku(Sku sku, List<Integer> sv_dellist, List<Specsvalues> sv_addlist, List<SkuSpecsv> ssv_addlist)
			throws Exception;

	List<SkuValuesDto> getValueBySkuID(Integer skuid) throws Exception;

	List<Sku> getListByIds(List<Integer> ids) throws Exception;

	int updateStockById(Integer stock, Integer skuId) throws Exception;
	/**
	 *平台设置库存
	 *@param stock
	 *@param skuId  
	 **/
	int updateSetStockById(Integer stock, Integer skuId) throws Exception;
	
    List<Sku> getSkuStartWithName(Integer shopid,String name) throws Exception;
    /**
     * 营销活动获取库存商品下拉框
     * @param shopid
     * @param name
     * @return
     * @throws Exception
     */
    List<Sku> getActSkuStartWithName(Integer shopid, String name) throws Exception;

	PageBean getUserListOrderSpu(Integer pc, Integer ps,
			CriteriaSku aoc) throws Exception;

	PageBean selectWanrSkuPage(ProductCriteria criteria, Integer pc, Integer ps) throws Exception;
	
	/**
	 * 检查商品ID是否重复
	 * @param tnum
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Sku getByTicketnum(String tnum,Integer id) throws Exception;

	Sku getByShopTicketnum(String tnum, Integer shopid,Integer id)throws Exception;

	Sku getBySpuID(Integer id)throws Exception;

}

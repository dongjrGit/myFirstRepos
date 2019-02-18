package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Shopormemberstatushistory;
import com.yinlian.wssc.web.po.Shopviolation;
import com.yinlian.wssc.web.util.CriteriaVoilationShop;

public interface ShopviolationService {
	/**
	 * 插入一条违规店铺信息
	 * @param shop
	 * @param shopstatus
	 * @return
	 */
	public int insertviolation(Shop shop,Shopormemberstatushistory shopstatus,Shopviolation shopviolation) throws Exception;
	
	/**
	 * 删除店铺违规
	 * @param violationId
	 * @return
	 * @throws Exception
	 */
	public int delete(Integer violationId ) throws Exception;
	
	/**
	 * 根据id查询违规店铺信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Shopviolation queryBykey(Integer id) throws Exception;

	/**
	 * 删除违规店铺信息
	 * @param shopstatus
	 * @param shop
	 * @param shopviolation
	 * @return
	 */
	public int delviolation(Shopormemberstatushistory shopstatus, Shop shop,
			Shopviolation shopviolation);
	
	/**
	 * 查询违规电铺信息
	 * @param criteria
	 * @param toInt
	 * @param toInt2
	 * @return
	 */
	public PageBean queryShopVoilationListByCriteria(
			CriteriaVoilationShop criteria, Integer pc, Integer ps);
	
	/**
	 * 查询历史违规店铺信息
	 * @param criteria
	 * @param toInt
	 * @param toInt2
	 * @return
	 */
	public PageBean queryShopHistoryVoilationListByCriteria(
			CriteriaVoilationShop criteria, Integer pc, Integer ps);
}

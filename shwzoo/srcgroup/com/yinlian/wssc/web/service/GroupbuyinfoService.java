package com.yinlian.wssc.web.service;

import com.yinlian.wssc.search.Groupbuyinfo_Api_Criteria;
import com.yinlian.wssc.search.P_Groupbuyinfo_Criteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Groupbuyinfo;
import com.yinlian.wssc.web.po.VGroupShop;

public interface GroupbuyinfoService {
	/**
	 * 添加团购商品
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	int add(Groupbuyinfo po) throws Exception;

	/**
	 * 修改团购商品
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	int update(Groupbuyinfo po) throws Exception;

	/**
	 * 删除团购商品
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int del(int id) throws Exception;

	/**
	 * 根据id查询团购商品
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Groupbuyinfo getById(int id) throws Exception;

	/**
	 * 获取团购商品
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	PageBean getList(P_Groupbuyinfo_Criteria criteria, Integer pc, Integer ps) throws Exception;

	PageBean getApiList(Groupbuyinfo_Api_Criteria criteria, Integer pc, Integer ps) throws Exception;

	PageBean getV_G_SkuList(P_Groupbuyinfo_Criteria criteria, Integer pc, Integer ps) throws Exception;

	/**
	 * 更新上下架状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 * @throws Exception
	 */
	Integer changestate(Integer id, Integer state) throws Exception;

	/**
	 * 更新团购审核
	 * 
	 * @param id
	 * @param auditing
	 * @return
	 * @throws Exception
	 */
	int editAudit(Integer id, Integer auditing) throws Exception;

	/**
	 * 根据id 查询 视图 v_group_shop 团购信息
	 * 
	 * @param id
	 *            团购 id
	 * @return
	 * @throws Exception
	 */
	VGroupShop getV_G_ShopById(int id) throws Exception;
}

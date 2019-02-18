package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Applyshop;
import com.yinlian.wssc.web.util.CriteriaApplyShop;

/**
 * 申请加盟的业务类
 * ApplyshopService.java
 * @author Administrator
 * @version $Id: ApplyshopService.java, v 0.1 2016年5月13日 下午6:19:06 Administrator Exp $
 */
public interface ApplyshopService {
	/**
	 * 申请加盟
	 * 
	 * @param applyshop
	 * @return
	 */
	int inset(Applyshop applyshop) throws Exception;
	/**
	 * 分页查询申请店铺
	 * 
	 * @param criteria
	 * @param toInt
	 * @param toInt2
	 * @return
	 * @throws Exception
	 */
	PageBean queryApplyShopList(CriteriaApplyShop criteria, Integer pc,
			Integer ps) throws Exception;
	/**
	 * 删除申请店铺
	 * 
	 * @param toInt
	 * @return
	 * @throws Exception
	 */
	int delApplyShop(Integer id) throws Exception;
	/**
	 * 修改申请店铺状态
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int updateApplyShop(Integer id) throws Exception;

}

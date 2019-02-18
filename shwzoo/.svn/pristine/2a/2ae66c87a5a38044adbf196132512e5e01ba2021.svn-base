package com.yinlian.wssc.web.service;

import com.yinlian.wssc.search.Pc_GoodConsultCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Goodconsult;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaGoodConsult;

/**
 * 咨询列表的业务类
 * @author Administrator
 *
 */
public interface GoodConsultService {

	/**
	 * 分页查询咨询列表
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	PageBean queryGoodConsultByCriteria(Criteria criteria,
			Integer pc, Integer ps) throws Exception;

	/**
	 * 根据id查询商品咨询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Goodconsult selectGoodConsultById(Integer id) throws Exception;

	/**
	 * 根据id修改商品咨询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int updateById(Goodconsult goodconsult) throws Exception;

	/**
	 * 分页查询每个会员咨询列表
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	PageBean queryUserGoodConsultByCriteria(Pc_GoodConsultCriteria criteria,
			Integer pc, Integer ps) throws Exception;
	
	int addConsult(Goodconsult goodconsult) throws Exception;

	PageBean queryPage(CriteriaGoodConsult consult, Integer integer,
			Integer integer2);

}

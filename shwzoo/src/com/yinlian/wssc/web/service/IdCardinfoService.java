package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.search.IdCardCriteria;
import com.yinlian.wssc.web.po.Idcardinfo;

public interface IdCardinfoService {

	/**
	 * 新增
	 * 
	 * @param idcardinfo
	 * @return
	 * @throws Exception
	 */
	public int insert(Idcardinfo idcardinfo) throws Exception;

	/**
	 * 查询
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	List<Idcardinfo> quertByUserId(int userid) throws Exception;

	/**
	 * 购买门票时判断身份信息
	 * 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public Integer checkcardinfo(IdCardCriteria criteria) throws Exception;
	
	/**
	 * 根据组订单号查询
	 * @param groupcode
	 * @return
	 * @throws Exception
	 */
	Idcardinfo getByGroupCode(String groupcode)throws Exception;

	/**
	 * 删除身份信息
	 * @param idcardinfo
	 * @return
	 * @throws Exception
	 */
	public int delCardInfo(Idcardinfo idcardinfo)throws Exception;

}

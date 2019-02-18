package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.Payset;

public interface PaySetService {
	
	/**
	 * 查询所有支付方式
	 * @return
	 * @throws Exception
	 */
	public List<Payset> queryPaySetList() throws Exception;

	/**
	 * 根据id删除支付方式
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	public int deletePaySetById(Integer id) throws Exception;

	
	/**
	 * 修改支付方式
	 * @param payset
	 * @return
	 * @throws Exception
	 */
	public int updatePaySetById(Payset payset) throws Exception;

	/**
	 * 根据id查询支付方式
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Payset selectPaySetById(String id) throws Exception;

	/**
	 * 添加支付方式
	 * @param payset
	 * @return
	 * @throws Exception
	 */
	public int addPaySet(Payset payset) throws Exception;

}

package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Applyforcancelorder;
import com.yinlian.wssc.web.util.CriteriaSellerSh;
import com.yinlian.wssc.web.util.CriteriaSh;

public interface ApplyforcancelorderService {

	int add(Applyforcancelorder afco) throws Exception;
	
	/**
	 * 根据订单ID或者订单detailID和申请类型获取申请记录
	 * @param orderid
	 * @param type
	 * @return
	 * @throws Exception
	 */
	 Applyforcancelorder getbyOrderAndType(int orderid, int type) throws Exception;
    /**
	 * 根据订单ID或者订单detailID和订单状态获取申请记录
	 * @param orderid 订单id
	 * @param status 订单状态
	 * @return
	 * @throws Exception
	 */
	 Applyforcancelorder getbyOrderAndStatus(int orderid, int status) throws Exception;
	 /**
	  * 得到退款退货列表
	  * @param criteria
	  * @param toInt
	  * @param toInt2
	  * @return
	  */
	PageBean selectThListByPage(CriteriaSh criteria, Integer pc,
			Integer ps) throws Exception;
	
	/**
	 * 删除退款退货记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int del(Integer id) throws Exception;
	
	/**
	 * 得到卖家退款退货列表
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 */
	PageBean selectSellerThListByPage(CriteriaSellerSh criteria,Integer  pc,
			Integer ps) throws Exception;

	/**
	 * 编辑备注
	 * @param toInt
	 * @param note
	 * @return
	 * @throws Exception
	 */
	int editNote(Integer toInt, String note) throws Exception;

	/**
	 * 查看备注
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String getNote(Integer id) throws Exception;

	/**
	 * 更新已退款状态
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int updatestatus(Integer id) throws Exception;
	
	Applyforcancelorder getbyOrderAndUser(int orderid, int userid) throws Exception;
	/**
	 * 获取多条申请记录
	 * @param orderid 订单id
	 * @param userid 用户id
	 */
    List<Applyforcancelorder>getApplyList(int orderid, int userid) throws Exception;
    /**
	 * 根据订单id获取多条申请记录
	 * @param orderid 订单id
	 */
    List<Applyforcancelorder>getApplyListByOid(int orderid) throws Exception;
}

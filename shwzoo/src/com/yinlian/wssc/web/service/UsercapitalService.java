/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.po.Usercapital;

/**
 * 用户的账户余额的业务类接口
 * @author Administrator
 * @version $Id: UsercapitalService.java, v 0.1 2016年3月12日 下午2:03:45 Administrator Exp $
 */
public interface UsercapitalService {

    /**
     * 根据userid查询信息
     * @param userid
     * @return
     */
    Usercapital queryByUserId(int userid) throws Exception;

    /**
     * 账户充值 会员
     * @param id 主键
     * @param money  充值金额
     * @param type 充值类型 0 是账户余额  1是保证金
     * @return
     */
    int recharge(Double money, String type,Integer userid,String userip) throws Exception;

    /**
     * 添加会员的账户余额
     * @param usercapital
     * @return
     */
	int addUsercapital(Usercapital usercapital) throws Exception;

	/**
	 * 根据userid删除会员的账户表
	 * @param toInt
	 * @return
	 */
	int deleteMemberByUserId(Integer userid) throws Exception;

	/**
	 * 根据id查询消息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Usercapital queryById(Integer id) throws Exception;

	/**
	 * 修改账户冻结状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	int updatStatus(Integer id, Integer status) throws Exception;
    

}

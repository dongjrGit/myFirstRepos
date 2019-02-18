/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaMessage;

/**
 * 站内信的业务接口类
 * MessageService.java
 * @author Administrator
 * @version $Id: MessageService.java, v 0.1 2016年3月21日 下午8:03:11 Administrator Exp $
 */
public interface MessageService {

    /**
     * 根据userid和状态获取分页信息
     * @param criteria
     * @param pc 当前页
     * @param ps 每页大小
     * @return
     */
    PageBean selectMessagesByUserIdPage(CriteriaMessage criteria, Integer pc, Integer ps)
                                                                                         throws Exception;

    /**
     * 根据主键删除
     * @param id  主键
     * @return
     */
    int deleteById(Integer id) throws Exception;

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteByIds(String ids) throws Exception;

    /**
     * 批量更新
     * @param ids
     * @return
     */
    int updateByIds(String ids) throws Exception;

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Messages queryById(Integer id) throws Exception;

    /**
     * 向messages表插入数据
     * @param array
     * @param title
     * @param content
     * @return
     */
    int insertBacth(String[] array, String title, String content) throws Exception;

    /**
     * 根据userid获取消息
     * @param userid
     * @return
     * @throws Exception
     */
	List<Messages> queryByUserId(Integer userid) throws Exception;

	/**
	 * 根据id更改站内信
	 * @param messages
	 * @return
	 * @throws Exception
	 */
	int updateById(Messages messages) throws Exception;
	
	/**
	 * 用户未读消息数量
	 * @param userid
	 * @return
	 */
	int getCount(int userid) throws Exception;
	
	int deleteByUserid(int userid) throws Exception;
	
	PageBean getMessagesByUser(Criteria criteria,Integer page, Integer size) throws Exception;
	/**
	 * 根据用户id设置站内信已读
	 * @param uid 用户id
	 * @return
	 * @throws Exception
	 */
	int updateSetMes(Integer uid) throws Exception;
}

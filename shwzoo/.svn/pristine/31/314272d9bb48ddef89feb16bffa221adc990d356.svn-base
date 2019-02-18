/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.CriteriaMessage;

/**
 * 短信记录表接口
 * MessageRecordService.java
 * @author Administrator
 * @version $Id: MessageRecordService.java, v 0.1 2016年3月22日 下午7:52:56 Administrator Exp $
 */
public interface MessageRecordService {

    /**
     * 批量插入站内信
     * @param array
     * @param title
     * @param content
     * @return
     */
    int insertBacth(Integer type,SessionUser	sessionUser,String[] array, String title, String content) throws Exception;

    /**
     * 查询已发送的站内信
     * @param criteria
     * @param pc
     * @param ps
     * @return
     */
    PageBean selectMessagesListByPage(CriteriaMessage criteria, Integer pc, Integer ps)
                                                                                       throws Exception;

    /**
     * 根据id删除
     * @param toInt
     * @return
     */
    int deleteById(Integer id) throws Exception;

    /**
     * 批量删除
     * @param array
     * @return
     */
    int deleteBacth(String[] array) throws Exception;

    /**
     * 批量发送短息
     * @param type
     * @param moblieArray
     * @param content
     * @param idArray
     * @param title 
     * @return
     */
    int insertList(SessionUser	sessionUser,Integer type, String[] moblieArray, String content, String[] idArray,
                   String title) throws Exception;

    /**
     * 发送一条短息
     * @param type
     * @param mobile
     * @param content
     * @param userid
     * @param title 
     * @return
     */
    int insertOne(SessionUser	sessionUser,Integer type, String mobile, String content, Integer userid, String title)
                                                                                            throws Exception;

    /**
     * 查询已发送短信
     * 
     * @param criteria
     * @param toInt
     * @param toInt2
     * @return
     */
	PageBean selectMessagescordsListByPage(CriteriaMessage criteria,
			Integer pc, Integer ps)  throws Exception;

}

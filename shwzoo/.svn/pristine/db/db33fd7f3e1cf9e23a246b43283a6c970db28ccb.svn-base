/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.Date;

import com.yinlian.wssc.web.po.Lotteryrecord;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年5月11日 上午10:16:37 Exp $
 */
public interface LotteryrecordService {

    /**
     * 根据userid查询 当天的抽奖记录
     *@param userid
     *@param date
     * @param date2 
     *@return
     */
    Lotteryrecord selectByUserIdCurrentDay(Integer userid, Date staDate, Date endDate)
                                                                                      throws Exception;

    /**
     *@param lotteryrecord
     */
    void update(Lotteryrecord lotteryrecord) throws Exception;

    /**
     *@param userid
     *@param mobile
     *@param username
     *@param i
     */
    void insert(Integer userid, String mobile, String username, int count) throws Exception;

}

/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.Date;
import java.util.List;

import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.search.ICriteria;
import com.yinlian.wssc.web.dto.LotteryDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Lottery;
import com.yinlian.wssc.web.po.LotteryDictionary;

/**
 * LotteryService.java
 * @author Liang.ma.s
 * @version $Id: LotteryService.java, v 0.1 2016年4月11日 上午11:09:00 Administrator Exp $
 */
public interface LotteryService {

    /**
     * 增加一条抽奖信息
     * @param userid
     * @param mobile
     * @param description
     * @param dto
     * @param valueparam  奖项设置的 表中的
     * @param value       抽奖字典表中的
     */
    void insert(Integer userid,String usename, String mobile,  LotteryDictionary dto, List<LotteryDictionary> rlist,BaseResult item)
                                                                                                throws Exception;

    /**
     * 分页查询中奖名单
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     */
    PageBean queryLotteryAllByCriteria(ICriteria criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 根据id删除中奖人
     * 
     * @param id
     * @return
     */
    int delLottery(Integer id) throws Exception;

    /**
     *@param userid
     *@param startTime
     *@param endTime
     *@return
     */
    List<Lottery> selectByUserIdCurrentDay(Integer userid, Date startTime, Date endTime)
                                                                                        throws Exception;
    /**
     * 查询最新的20位中奖信息
     * @param c
     * @return
     */
    List<LotteryDto> queryNewLottery() throws Exception;

}

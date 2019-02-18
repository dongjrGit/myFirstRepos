/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.LotteryrecordMapper;
import com.yinlian.wssc.web.po.Lotteryrecord;
import com.yinlian.wssc.web.service.LotteryrecordService;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年5月11日 上午10:16:56 Exp $
 */
@Component("lotteryrecordService")
public class LotteryrecordServiceImpl implements LotteryrecordService {

    @Autowired
    private LotteryrecordMapper lotteryrecordMapper;

    /**
     * @see com.yinlian.wssc.web.service.LotteryrecordService#selectByUserIdCurrentDay(java.lang.Integer, java.util.Date)
     */
    @Override
    public Lotteryrecord selectByUserIdCurrentDay(Integer userid, Date staDate, Date endDate)
                                                                                             throws Exception {

        return lotteryrecordMapper.selectByUserIdCurrentDay(userid, staDate, endDate);
    }

    /**
     * @see com.yinlian.wssc.web.service.LotteryrecordService#update(com.yinlian.wssc.web.po.Lotteryrecord)
     */
    @Override
    public void update(Lotteryrecord lotteryrecord) throws Exception {
        lotteryrecordMapper.updateByPrimaryKey(lotteryrecord);
    }

    /**
     * @see com.yinlian.wssc.web.service.LotteryrecordService#insert(java.lang.Integer, java.lang.String, java.lang.String, int)
     */
    @Override
    public void insert(Integer userid, String mobile, String username, int count) throws Exception {

        Lotteryrecord record = new Lotteryrecord();
        record.setUserid(userid);
        record.setMobile(mobile);
        record.setUsername(username);
        record.setCount(count);
        record.setCreattime(new Date());
        lotteryrecordMapper.insert(record);
    }

}

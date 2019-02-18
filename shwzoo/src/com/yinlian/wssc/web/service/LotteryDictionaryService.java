/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;
import java.util.Map;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.LotteryDictionary;
import com.yinlian.wssc.web.util.Criteria;

/**
 * LotteryDictionaryService.java
 * @author Liang.ma.s
 * @version $Id: LotteryDictionaryService.java, v 0.1 2016年4月12日 上午11:16:32 Administrator Exp $
 */
public interface LotteryDictionaryService {

    /**
     * 获取概率的map集合
     * @return
     */
    Map<String, Double> getMap() throws Exception;

    /**
     * 查询所有的 奖项字典信息
     *@return
     */
    List<LotteryDictionary> qeury1() throws Exception;
    /**
     * 查询所有的 奖项字典信息
     *@return
     */
    Map<String, Object> qeury() throws Exception;

    /**
     * 查询所有的
     * 
     * @return
     */
    PageBean queryAllByCriteria(Criteria criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 根据id查询
     * 
     * @param id
     * @return
     */
    LotteryDictionary queryById(Integer id) throws Exception;

    /**
     * 根据id修改规则
     * 
     * @param toInt
     * @param name
     * @param description
     * @param probability
     * @param value 
     * @return
     */
    int updatLotteryRuleById(Integer id, String name, String description, String probability,
                             String value,String mark) throws Exception;

    /**
     * 添加一条规则
     * 
     * @param toInt
     * @param name
     * @param description
     * @param probability
     * @param value 
     * @return
     */
    int addLotteryRule(String name, String description, String probability, String value,String mark)
                                                                                         throws Exception;

    /**
     *@return
     */
    List<LotteryDictionary> selectAll() throws Exception;
    
    /**
     * 删除抽奖规则
     * @param id
     * @return
     * @throws Exception
     */
	int deleteById(Integer id) throws Exception;

}

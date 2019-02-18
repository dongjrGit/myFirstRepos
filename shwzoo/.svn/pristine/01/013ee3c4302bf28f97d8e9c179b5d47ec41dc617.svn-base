/*
 * @(#) BusinessbillsMapper.java 2016年8月29日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.BusinessbillsDto;
import com.yinlian.wssc.web.po.Businessbills;
import com.yinlian.wssc.web.util.OrderCriteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface BusinessbillsMapper {

	List<Businessbills> selectBusinessByPage(PageBeanUtil pageBeanUtil);

	List<BusinessbillsDto> generateBills(@Param("now")String now);

	Integer insertAll(List<Businessbills> list);

	List<Businessbills> selectAll(OrderCriteria criteria);


	int deleteByPrimaryKey(Integer id);

    int insert(Businessbills record);

    int insertSelective(Businessbills record);

    Businessbills selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Businessbills record);

    int updateByPrimaryKey(Businessbills record);

    /**
     * 批量添加对账
     * @param bill
     */
    int insertList(@Param("bill")List<Businessbills> bill);
}

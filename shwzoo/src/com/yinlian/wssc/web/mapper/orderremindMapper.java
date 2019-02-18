package com.yinlian.wssc.web.mapper;

import java.util.List;

import com.yinlian.wssc.web.po.OrderRemind;
import com.yinlian.wssc.web.util.PageBeanUtil;


public interface orderremindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRemind record);

    int insertSelective(OrderRemind record);

    OrderRemind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRemind record);

    int updateByPrimaryKey(OrderRemind record);
    
    List<OrderRemind> seleOrderRemindPage(PageBeanUtil util) throws Exception;
    
    OrderRemind getDescOrderRemindByid(Integer ordernum);
    
    List<OrderRemind> OrderRemindListbyclerkid(PageBeanUtil util)throws Exception;
}
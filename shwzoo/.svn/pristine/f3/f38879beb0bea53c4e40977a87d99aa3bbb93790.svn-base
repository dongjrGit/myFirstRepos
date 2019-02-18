package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.OrderactivityHistory;
import com.yinlian.wssc.web.po.OrderactivityHistoryExample;

public interface OrderactivityHistoryMapper {
    int countByExample(OrderactivityHistoryExample example)throws Exception;

    int deleteByExample(OrderactivityHistoryExample example)throws Exception;

    int deleteByPrimaryKey(String id)throws Exception;

    int insert(OrderactivityHistory record)throws Exception;

    int insertSelective(OrderactivityHistory record)throws Exception;

    List<OrderactivityHistory> selectByExample(OrderactivityHistoryExample example)throws Exception;

    OrderactivityHistory selectByPrimaryKey(String id)throws Exception;

    int updateByExampleSelective(@Param("record") OrderactivityHistory record, @Param("example") OrderactivityHistoryExample example);

    int updateByExample(@Param("record") OrderactivityHistory record, @Param("example") OrderactivityHistoryExample example);

    int updateByPrimaryKeySelective(OrderactivityHistory record)throws Exception;

    int updateByPrimaryKey(OrderactivityHistory record)throws Exception;

    List<OrderactivityHistory> getbyOrderid(int orderid)throws Exception;

	List<OrderactivityHistory> getbyOrdercode(String ordercode)throws Exception;
}
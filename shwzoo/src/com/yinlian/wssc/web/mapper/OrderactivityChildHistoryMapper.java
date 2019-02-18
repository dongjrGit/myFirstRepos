package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.OrderactivityChildHistory;
import com.yinlian.wssc.web.po.OrderactivityChildHistoryExample;

public interface OrderactivityChildHistoryMapper {
    int countByExample(OrderactivityChildHistoryExample example);

    int deleteByExample(OrderactivityChildHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderactivityChildHistory record);

    int insertSelective(OrderactivityChildHistory record);

    List<OrderactivityChildHistory> selectByExample(OrderactivityChildHistoryExample example);

    OrderactivityChildHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderactivityChildHistory record, @Param("example") OrderactivityChildHistoryExample example);

    int updateByExample(@Param("record") OrderactivityChildHistory record, @Param("example") OrderactivityChildHistoryExample example);

    int updateByPrimaryKeySelective(OrderactivityChildHistory record);

    int updateByPrimaryKey(OrderactivityChildHistory record);

	List<OrderactivityChildHistory> getListByOrderCode(String code) throws Exception;

	int updateStatusById(Integer id, Integer status) throws Exception;

	int updatePayMoney(String orderNum, int orderid, float payMoney,int status)throws Exception;
}
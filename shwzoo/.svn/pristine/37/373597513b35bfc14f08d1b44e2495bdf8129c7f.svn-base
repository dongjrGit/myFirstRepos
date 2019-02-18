package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.GroupOrderStateDto;
import com.yinlian.wssc.web.dto.GroupByorderDto;
import com.yinlian.wssc.web.dto.OrderGroupByDto;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.po.GroupbuyorderExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface GroupbuyorderMapper {
    int countByExample(GroupbuyorderExample example) throws Exception;

    int deleteByExample(GroupbuyorderExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Groupbuyorder record) throws Exception;

    int insertSelective(Groupbuyorder record) throws Exception;

    List<Groupbuyorder> selectByExample(GroupbuyorderExample example) throws Exception;

    Groupbuyorder selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Groupbuyorder record,
                                 @Param("example") GroupbuyorderExample example) throws Exception;

    int updateByExample(@Param("record") Groupbuyorder record,
                        @Param("example") GroupbuyorderExample example) throws Exception;

    int updateByPrimaryKeySelective(Groupbuyorder record) throws Exception;

    int updateByPrimaryKey(Groupbuyorder record) throws Exception;
    
    int delOrder(int id) throws Exception;
    
    int updateStatus(int status,int id) throws Exception;
    
    int updateFK(Groupbuyorder record) throws Exception;
    
    int delOrderList(List<Integer> list) throws Exception;
    
    List<GroupByorderDto> getListOrderByPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    List<OrderGroupByDto> getGroupOrderByPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    Groupbuyorder getByOrderCode(String ordercode) throws Exception;
    
    GroupOrderStateDto getCount(Integer userid) throws Exception;
    
    List<Groupbuyorder> getUnpaidGroupOrders()throws Exception;

}
package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.GroupbuyorderdetailDto;
import com.yinlian.wssc.web.po.Grouporderdetail;
import com.yinlian.wssc.web.po.GrouporderdetailExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface GrouporderdetailMapper {
    int countByExample(GrouporderdetailExample example) throws Exception;

    int deleteByExample(GrouporderdetailExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Grouporderdetail record) throws Exception;

    int insertSelective(Grouporderdetail record) throws Exception;

    List<Grouporderdetail> selectByExample(GrouporderdetailExample example) throws Exception;

    Grouporderdetail selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Grouporderdetail record,
                                 @Param("example") GrouporderdetailExample example)
                                                                                   throws Exception;

    int updateByExample(@Param("record") Grouporderdetail record,
                        @Param("example") GrouporderdetailExample example) throws Exception;

    int updateByPrimaryKeySelective(Grouporderdetail record) throws Exception;

    int updateByPrimaryKey(Grouporderdetail record) throws Exception;
    
    int insertList(List<Grouporderdetail> list) throws Exception;
    
    List<Grouporderdetail> getByOrderID(Integer orderid) throws Exception;
    
    int updateUse(int status,int id) throws Exception;
    
    int updateUseList(List<Grouporderdetail> list) throws Exception;
    
    int updateTK(int status,int id) throws Exception;
    
    Grouporderdetail getByCode(String code) throws Exception;
    
    List<GroupbuyorderdetailDto> getGroupOrderByToTalPage(PageBeanUtil pageBeanUtil) throws Exception;
    
}
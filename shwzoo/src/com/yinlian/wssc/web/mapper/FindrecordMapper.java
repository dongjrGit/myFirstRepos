package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.FindAppDto;
import com.yinlian.wssc.web.po.Findrecord;
import com.yinlian.wssc.web.po.FindrecordExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface FindrecordMapper {
    int countByExample(FindrecordExample example);

    int deleteByExample(FindrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Findrecord record);

    int insertSelective(Findrecord record);

    List<Findrecord> selectByExample(FindrecordExample example);

    Findrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Findrecord record, @Param("example") FindrecordExample example);

    int updateByExample(@Param("record") Findrecord record, @Param("example") FindrecordExample example);

    int updateByPrimaryKeySelective(Findrecord record);

    int updateByPrimaryKey(Findrecord record);
    
    List<Findrecord> selectimg();
    
    List<Findrecord> selectFindRecordPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    int updateStatus(Integer status,Integer id) throws Exception;
    
    int updateOrder(Integer sort,Integer id) throws Exception;
    
    int updateOrderList(List<Findrecord> list) throws Exception;
    
    List<FindAppDto> getListByType(Integer type) throws Exception;

	List<Findrecord> selectTop() throws Exception;
}
package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.RecommendClassDto;
import com.yinlian.wssc.web.po.Recommclass;
import com.yinlian.wssc.web.po.RecommclassExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface RecommclassMapper {
    int countByExample(RecommclassExample example);

    int deleteByExample(RecommclassExample example);

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(Recommclass record)throws Exception;

    int insertSelective(Recommclass record);

    List<Recommclass> selectByExample(RecommclassExample example);

    Recommclass selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") Recommclass record, @Param("example") RecommclassExample example);

    int updateByExample(@Param("record") Recommclass record, @Param("example") RecommclassExample example);

    int updateByPrimaryKeySelective(Recommclass record);

    int updateByPrimaryKey(Recommclass record)throws Exception;
    
    List<RecommendClassDto> getListByPage(PageBeanUtil pageBeanUtil)throws Exception;
    
    int updateOrder(int orderby,int id)throws Exception;
    
    int updateOrderList(List<Recommclass> list)throws Exception;
}
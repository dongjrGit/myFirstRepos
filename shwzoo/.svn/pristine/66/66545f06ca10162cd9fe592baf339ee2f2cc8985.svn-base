package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.VSpecsinfo;
import com.yinlian.wssc.web.po.VSpecsinfoExample;

public interface VSpecsinfoMapper {
    int countByExample(VSpecsinfoExample example);

    int deleteByExample(VSpecsinfoExample example);

    int insert(VSpecsinfo record);

    int insertSelective(VSpecsinfo record);

    List<VSpecsinfo> selectByExample(VSpecsinfoExample example);

    int updateByExampleSelective(@Param("record") VSpecsinfo record, @Param("example") VSpecsinfoExample example);

    int updateByExample(@Param("record") VSpecsinfo record, @Param("example") VSpecsinfoExample example);
    List<VSpecsinfo> getBySkuId(int skuid) throws Exception;

    List<VSpecsinfo> getBySpuId(int spuid) throws Exception;
    
    List<VSpecsinfo> getcartspecsBySkuid(int skuid) throws Exception;
    
    List<VSpecsinfo> getcartspecslist(List<Integer> list) throws Exception;

}
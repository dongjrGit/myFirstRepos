package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.po.SpecsvaluesExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface SpecsvaluesMapper {
    int countByExample(SpecsvaluesExample example);

    int deleteByExample(SpecsvaluesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Specsvalues record);

    int insertSelective(Specsvalues record);

    List<Specsvalues> selectByExample(SpecsvaluesExample example);

    Specsvalues selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Specsvalues record,
                                 @Param("example") SpecsvaluesExample example);

    int updateByExample(@Param("record") Specsvalues record,
                        @Param("example") SpecsvaluesExample example);

    int updateByPrimaryKeySelective(Specsvalues record);

    int updateByPrimaryKey(Specsvalues record);

    List<Specsvalues> selectSpecsValuesBySpecsIdPage(PageBeanUtil pageBeanUtil);
    
    int updateStatus(Integer status,Integer id);
    
    List<Specsvalues> getBySpecsID(Integer specsid);
    
    int insertList(List<Specsvalues> list);
    
    int deleteList(List<Integer> ids);
    
    int insertSpecsvalues(Specsvalues record);

}
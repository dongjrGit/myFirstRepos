package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ProThemeClass;
import com.yinlian.wssc.web.po.ProThemeClassExample;

public interface ProThemeClassMapper {
    int countByExample(ProThemeClassExample example);

    int deleteByExample(ProThemeClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProThemeClass record);

    int insertSelective(ProThemeClass record);

    List<ProThemeClass> selectByExample(ProThemeClassExample example);

    ProThemeClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProThemeClass record, @Param("example") ProThemeClassExample example);

    int updateByExample(@Param("record") ProThemeClass record, @Param("example") ProThemeClassExample example);

    int updateByPrimaryKeySelective(ProThemeClass record);

    int updateByPrimaryKey(ProThemeClass record);
}
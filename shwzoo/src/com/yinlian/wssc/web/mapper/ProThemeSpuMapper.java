package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ProThemeSpu;
import com.yinlian.wssc.web.po.ProThemeSpuExample;

public interface ProThemeSpuMapper {
    int countByExample(ProThemeSpuExample example);

    int deleteByExample(ProThemeSpuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProThemeSpu record);

    int insertSelective(ProThemeSpu record);

    List<ProThemeSpu> selectByExample(ProThemeSpuExample example);

    ProThemeSpu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProThemeSpu record, @Param("example") ProThemeSpuExample example);

    int updateByExample(@Param("record") ProThemeSpu record, @Param("example") ProThemeSpuExample example);

    int updateByPrimaryKeySelective(ProThemeSpu record);

    int updateByPrimaryKey(ProThemeSpu record);
}
package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.AreaExample;

public interface AreaMapper {

    int countByExample(AreaExample example);

    int deleteByExample(AreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    List<Area> selectByExample(AreaExample example);

    Area selectByPrimaryKey(Integer id);

    Area selectByName(String name);

    int updateByExampleSelective(@Param("record") Area record, @Param("example") AreaExample example);

    int updateByExample(@Param("record") Area record, @Param("example") AreaExample example);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    /**
     * 
     * @param companyarea
     * @return
     */
    String selectNameByCode(String code) throws Exception;

	List queryArea(@Param("parentCode")String parentCode) throws Exception;

	Area selectCodeByName(String name, String code) throws Exception ;

}
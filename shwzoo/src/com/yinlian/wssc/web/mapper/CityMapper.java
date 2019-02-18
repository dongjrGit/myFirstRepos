package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.CitySpDto;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.CityExample;

public interface CityMapper {
    int countByExample(CityExample example);

    int deleteByExample(CityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    List<City> selectByExample(CityExample example);

    City selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") City record, @Param("example") CityExample example);

    int updateByExample(@Param("record") City record, @Param("example") CityExample example);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    City selectByName(String name);

    /**
     * 
     * @param companycity
     * @return
     */
    String selectNameByCode(String code) throws Exception;

	List queryCity(@Param("parentCode")String parentCode) throws Exception;

	City queryCityCode(String name, String code) throws Exception;

	List<CitySpDto> getCityAllList()throws Exception;
}
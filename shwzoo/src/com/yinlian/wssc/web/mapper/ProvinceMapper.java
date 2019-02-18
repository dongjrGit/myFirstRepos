package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.Api_ProvinceDto;
import com.yinlian.pc.dto.ProvinceDto;
import com.yinlian.pc.dto.TopicBig;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.ProvinceExample;

public interface ProvinceMapper {
    int countByExample(ProvinceExample example);

    int deleteByExample(ProvinceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Province record);

    int insertSelective(Province record);

    List<Province> selectByExample(ProvinceExample example);

    Province selectByPrimaryKey(Integer id);

    Province selectByName(String name);

    int updateByExampleSelective(@Param("record") Province record,
                                 @Param("example") ProvinceExample example);

    int updateByExample(@Param("record") Province record, @Param("example") ProvinceExample example);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);

    /**
     * 
     * @param companyprovince
     * @return
     */
    String selectNameByCode(String code) throws Exception;

    /**
     * 获取全部大区
     * @return
     * @throws Exception
     */
	List<TopicBig> selectBigAll()throws Exception;

	List<ProvinceDto> selectProBigAll()throws Exception;
	/**
     * 获取所有省市区信息 
     * @return
     */
    List<Api_ProvinceDto> getplaces() throws Exception;
}
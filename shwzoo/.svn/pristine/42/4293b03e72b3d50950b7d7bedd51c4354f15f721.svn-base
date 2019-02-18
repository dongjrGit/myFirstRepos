package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.VoilationShopDTO;
import com.yinlian.wssc.web.po.Shopviolation;
import com.yinlian.wssc.web.po.ShopviolationExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ShopviolationMapper {
    int countByExample(ShopviolationExample example);

    int deleteByExample(ShopviolationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Shopviolation record);

    int insertSelective(Shopviolation record);

    List<Shopviolation> selectByExample(ShopviolationExample example);

    Shopviolation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Shopviolation record, @Param("example") ShopviolationExample example);

    int updateByExample(@Param("record") Shopviolation record, @Param("example") ShopviolationExample example);

    int updateByPrimaryKeySelective(Shopviolation record);

    int updateByPrimaryKey(Shopviolation record);
    
	List<VoilationShopDTO> selectVoilationShopByPage(PageBeanUtil pageBeanUtil);

	List<VoilationShopDTO> selectHistoryVoilationShopByPage(
			PageBeanUtil pageBeanUtil);
}
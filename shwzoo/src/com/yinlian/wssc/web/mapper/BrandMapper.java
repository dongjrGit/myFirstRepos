package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.TopicRelateInfo;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.BrandExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface BrandMapper {
    int countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExampleWithBLOBs(BrandExample example);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record,
                                 @Param("example") BrandExample example);

    int updateByExampleWithBLOBs(@Param("record") Brand record,
                                 @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKeyWithBLOBs(Brand record);

    int updateByPrimaryKey(Brand record);

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Brand> selectBrandPage(PageBeanUtil pageBeanUtil) throws Exception;

    List<Brand> selectBrandByShop(Integer shopid) throws Exception;

    List<Brand> getBrandByShopPage(PageBeanUtil pageBeanUtil) throws Exception;

    List<Brand> getBrandWithNameShop(Integer shopid, @Param("name")String name) throws Exception;

    int insertBrandGetId(Brand record) throws Exception;
    
    List<Brand> getBrandWithName(@Param("name")String name) throws Exception;
    
    List<Brand> queryAll() throws Exception;

	List<TopicRelateInfo> selectTopicByPage(PageBeanUtil pageBeanUtil);
	
	int deleteBrand(Brand record)throws Exception;
	
	Brand getbyName(String name)throws Exception;
}
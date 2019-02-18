package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ShopBrand;
import com.yinlian.wssc.web.po.ShopBrandExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ShopBrandMapper {
    int countByExample(ShopBrandExample example);

    int deleteByExample(ShopBrandExample example);

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(ShopBrand record)throws Exception;

    int insertSelective(ShopBrand record)throws Exception;

    List<ShopBrand> selectByExample(ShopBrandExample example)throws Exception;

    ShopBrand selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") ShopBrand record,
                                 @Param("example") ShopBrandExample example)throws Exception;

    int updateByExample(@Param("record") ShopBrand record,
                        @Param("example") ShopBrandExample example)throws Exception;

    int updateByPrimaryKeySelective(ShopBrand record)throws Exception;

    int updateByPrimaryKey(ShopBrand record)throws Exception;

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<ShopBrand> selectShopBrandPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<ShopBrand> selectPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    int deleteByBrandid(Integer bid)throws Exception;
    
    ShopBrand getbySidAndBid(Integer sid,Integer bid)throws Exception;
    
}
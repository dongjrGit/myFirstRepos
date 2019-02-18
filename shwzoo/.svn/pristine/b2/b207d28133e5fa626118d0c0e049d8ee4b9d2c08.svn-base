package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.V_ShopCart;
import com.yinlian.wssc.web.po.Shopcart;
import com.yinlian.wssc.web.po.ShopcartExample;

public interface ShopcartMapper {
    int countByExample(ShopcartExample example);

    int deleteByExample(ShopcartExample example);

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(Shopcart record)throws Exception;

    int insertSelective(Shopcart record);

    List<Shopcart> selectByExample(ShopcartExample example);

    Shopcart selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") Shopcart record, @Param("example") ShopcartExample example);

    int updateByExample(@Param("record") Shopcart record, @Param("example") ShopcartExample example);

    int updateByPrimaryKeySelective(Shopcart record);

    int updateByPrimaryKey(Shopcart record)throws Exception;
    
    int insertCart(Shopcart record)throws Exception;
    
    int deleteByUser(int userid)throws Exception;
    
    int deleteByShop(int userid)throws Exception;
    
    Shopcart getByUserAndShop(int userid,int shopid)throws Exception;
    
    List<Shopcart> getByUser(int userid)throws Exception;
    
    List<V_ShopCart> getV_ShopCartByUser(int userid)throws Exception;
    List<V_ShopCart> getBuyCartByUser(int userid)throws Exception;
}
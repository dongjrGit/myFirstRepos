package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.ShopAuthenticationExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ShopAuthenticationMapper {
    int countByExample(ShopAuthenticationExample example);

    int deleteByExample(ShopAuthenticationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopAuthentication record);

    int insertSelective(ShopAuthentication record);

    List<ShopAuthentication> selectByExample(ShopAuthenticationExample example);

    ShopAuthentication selectByPrimaryKey(Integer id);   
    ShopAuthentication selectByPrimaryByShopId(Integer shopid)throws Exception;

    int updateByExampleSelective(@Param("record") ShopAuthentication record,
                                 @Param("example") ShopAuthenticationExample example);

    int updateByExample(@Param("record") ShopAuthentication record,
                        @Param("example") ShopAuthenticationExample example);

    int updateByPrimaryKeySelective(ShopAuthentication record);

    int updateByPrimaryKey(ShopAuthentication record);

    /**
     * 分页查询
     * @param pageBeanUtil
     * @return
     */
    List<ShopAuthentication> selectPage(PageBeanUtil pageBeanUtil) throws Exception;
}
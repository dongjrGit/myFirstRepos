package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Shopnotice;
import com.yinlian.wssc.web.po.ShopnoticeExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ShopnoticeMapper {
    int countByExample(ShopnoticeExample example);

    int deleteByExample(ShopnoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Shopnotice record);

    int insertSelective(Shopnotice record);

    List<Shopnotice> selectByExample(ShopnoticeExample example);

    Shopnotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Shopnotice record,
                                 @Param("example") ShopnoticeExample example);

    int updateByExample(@Param("record") Shopnotice record,
                        @Param("example") ShopnoticeExample example);

    int updateByPrimaryKeySelective(Shopnotice record);

    int updateByPrimaryKey(Shopnotice record);

    /**
     * 分页查询
     * @param pageBeanUtil
     * @return
     */
    List<Shopnotice> selectShopNoticesByPage(PageBeanUtil pageBeanUtil);
}
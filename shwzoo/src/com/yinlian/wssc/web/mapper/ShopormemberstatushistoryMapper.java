package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Shopormemberstatushistory;
import com.yinlian.wssc.web.po.ShopormemberstatushistoryExample;

public interface ShopormemberstatushistoryMapper {
    int countByExample(ShopormemberstatushistoryExample example);

    int deleteByExample(ShopormemberstatushistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Shopormemberstatushistory record);

    int insertSelective(Shopormemberstatushistory record);

    List<Shopormemberstatushistory> selectByExample(ShopormemberstatushistoryExample example);

    Shopormemberstatushistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Shopormemberstatushistory record, @Param("example") ShopormemberstatushistoryExample example);

    int updateByExample(@Param("record") Shopormemberstatushistory record, @Param("example") ShopormemberstatushistoryExample example);

    int updateByPrimaryKeySelective(Shopormemberstatushistory record);

    int updateByPrimaryKey(Shopormemberstatushistory record);

	Shopormemberstatushistory selectNewStatus(Integer objid);
}
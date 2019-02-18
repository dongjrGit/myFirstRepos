package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Applyshop;
import com.yinlian.wssc.web.po.ApplyshopExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ApplyshopMapper {
    int countByExample(ApplyshopExample example);

    int deleteByExample(ApplyshopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Applyshop record);

    int insertSelective(Applyshop record);

    List<Applyshop> selectByExample(ApplyshopExample example);

    Applyshop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Applyshop record, @Param("example") ApplyshopExample example);

    int updateByExample(@Param("record") Applyshop record, @Param("example") ApplyshopExample example);

    int updateByPrimaryKeySelective(Applyshop record);

    int updateByPrimaryKey(Applyshop record);
    /**
     * 分页查询申请店铺
     * 
     * @param pageBeanUtil
     * @return
     * @throws Exception
     */
	List<Applyshop> selectApplyShopByPage(PageBeanUtil pageBeanUtil) throws Exception;
}
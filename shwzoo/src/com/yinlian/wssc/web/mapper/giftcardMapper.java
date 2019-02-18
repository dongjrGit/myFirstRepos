package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.giftcard;
import com.yinlian.wssc.web.po.giftcardExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface giftcardMapper {
    int countByExample(giftcardExample example);

    int deleteByExample(giftcardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(giftcard record);

    int insertSelective(giftcard record);

    List<giftcard> selectByExample(giftcardExample example);
    
    public List<giftcard> getgiftcardList(PageBeanUtil bean);

    giftcard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") giftcard record, @Param("example") giftcardExample example);

    int updateByExample(@Param("record") giftcard record, @Param("example") giftcardExample example);

    int updateByPrimaryKeySelective(giftcard record);

    int updateByPrimaryKey(giftcard record);
    
    public giftcard getByCode(@Param("code")String code) throws Exception; 
}
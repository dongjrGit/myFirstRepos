package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.po.FreightAttr;
import com.yinlian.wssc.web.po.FreightAttrExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface FreightAttrMapper {
    int countByExample(FreightAttrExample example)throws Exception;

    int deleteByExample(FreightAttrExample example)throws Exception;

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(FreightAttr record)throws Exception;

    int insertSelective(FreightAttr record)throws Exception;

    List<FreightAttr> selectByExample(FreightAttrExample example)throws Exception;

    FreightAttr selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") FreightAttr record, @Param("example") FreightAttrExample example)throws Exception;

    int updateByExample(@Param("record") FreightAttr record, @Param("example") FreightAttrExample example)throws Exception;

    int updateByPrimaryKeySelective(FreightAttr record)throws Exception;

    int updateByPrimaryKey(FreightAttr record)throws Exception;
    
    int deleteByFreightID(Integer freightid)throws Exception;

	List<FreightAttr> selectByFreightIdByPage(PageBeanUtil pageBeanUtil) throws Exception;
}
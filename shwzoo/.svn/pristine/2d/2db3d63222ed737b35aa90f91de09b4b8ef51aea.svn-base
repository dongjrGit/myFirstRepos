package com.yinlian.wssc.web.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Operaterecords;
import com.yinlian.wssc.web.po.OperaterecordsExample;
import com.yinlian.wssc.web.util.CriteriaOperater;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface OperaterecordsMapper {
    int countByExample(OperaterecordsExample example);

    int deleteByExample(OperaterecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Operaterecords record);

    int insertSelective(Operaterecords record);

    List<Operaterecords> selectByExample(OperaterecordsExample example);

    Operaterecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Operaterecords record, @Param("example") OperaterecordsExample example);

    int updateByExample(@Param("record") Operaterecords record, @Param("example") OperaterecordsExample example);

    int updateByPrimaryKeySelective(Operaterecords record);

    int updateByPrimaryKey(Operaterecords record);

	List<Operaterecords> getRecordsByPage(PageBeanUtil pageBeanUtil) throws Exception;

	int deleteAll(CriteriaOperater criteria) throws Exception;

	
}
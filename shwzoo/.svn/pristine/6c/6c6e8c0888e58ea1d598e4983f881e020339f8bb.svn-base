package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Historybeans;
import com.yinlian.wssc.web.po.HistorybeansExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface HistorybeansMapper {
    int countByExample(HistorybeansExample example);

    int deleteByExample(HistorybeansExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Historybeans record);

    int insertSelective(Historybeans record);

    List<Historybeans> selectByExample(HistorybeansExample example);

    Historybeans selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Historybeans record,
                                 @Param("example") HistorybeansExample example);

    int updateByExample(@Param("record") Historybeans record,
                        @Param("example") HistorybeansExample example);

    int updateByPrimaryKeySelective(Historybeans record);

    int updateByPrimaryKey(Historybeans record);

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Historybeans> selectByPage(PageBeanUtil pageBeanUtil) throws Exception;
}
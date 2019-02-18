package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Searchvalue;
import com.yinlian.wssc.web.po.SearchvalueExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface SearchvalueMapper {
    int countByExample(SearchvalueExample example);

    int deleteByExample(SearchvalueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Searchvalue record);

    int insertSelective(Searchvalue record);

    List<Searchvalue> selectByExample(SearchvalueExample example);

    Searchvalue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Searchvalue record,
                                 @Param("example") SearchvalueExample example);

    int updateByExample(@Param("record") Searchvalue record,
                        @Param("example") SearchvalueExample example);

    int updateByPrimaryKeySelective(Searchvalue record);

    int updateByPrimaryKey(Searchvalue record);

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Searchvalue> selectSearchvalueByAttrIdPage(PageBeanUtil pageBeanUtil) throws Exception;
}
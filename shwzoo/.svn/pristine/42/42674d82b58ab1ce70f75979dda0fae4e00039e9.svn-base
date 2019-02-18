package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.SeachAtrrDto;
import com.yinlian.wssc.web.po.Searchattr;
import com.yinlian.wssc.web.po.SearchattrExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface SearchattrMapper {
    int countByExample(SearchattrExample example);

    int deleteByExample(SearchattrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Searchattr record);

    int insertSelective(Searchattr record);

    List<Searchattr> selectByExample(SearchattrExample example);

    Searchattr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Searchattr record,
                                 @Param("example") SearchattrExample example);

    int updateByExample(@Param("record") Searchattr record,
                        @Param("example") SearchattrExample example);

    int updateByPrimaryKeySelective(Searchattr record);

    int updateByPrimaryKey(Searchattr record);

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Searchattr> selectSearchattrPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    List<SeachAtrrDto> getAllSearchattrValues(int cid,Integer usesites)throws Exception;
}
package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.NavigationAppDto;
import com.yinlian.wssc.web.po.Navigation;
import com.yinlian.wssc.web.po.NavigationExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface NavigationMapper {
    int countByExample(NavigationExample example) throws Exception;

    int deleteByExample(NavigationExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Navigation record) throws Exception;

    int insertSelective(Navigation record) throws Exception;

    List<Navigation> selectByExample(NavigationExample example) throws Exception;

    Navigation selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Navigation record,
                                 @Param("example") NavigationExample example) throws Exception;

    int updateByExample(@Param("record") Navigation record,
                        @Param("example") NavigationExample example) throws Exception;

    int updateByPrimaryKeySelective(Navigation record) throws Exception;

    int updateByPrimaryKey(Navigation record) throws Exception;

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Navigation> selectByPage(PageBeanUtil pageBeanUtil) throws Exception;

    List<NavigationAppDto> selectNavigationPage(PageBeanUtil pageBeanUtil) throws Exception;

}
package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Announcement;
import com.yinlian.wssc.web.po.AnnouncementExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface AnnouncementMapper {
    int countByExample(AnnouncementExample example);

    int deleteByExample(AnnouncementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    List<Announcement> selectByExample(AnnouncementExample example);

    Announcement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByExample(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);
    
    List<Announcement> selectannouncementPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    int updateOrder(Integer sort,Integer id);
    
    int updateOrderList(List<Announcement> list);
}
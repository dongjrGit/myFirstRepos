package com.yinlian.wssc.web.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.pc.dto.PcPointsDto;
import com.yinlian.wssc.web.po.Pointsrecords;
import com.yinlian.wssc.web.po.PointsrecordsExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface PointsrecordsMapper {
    int countByExample(PointsrecordsExample example);

    int deleteByExample(PointsrecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pointsrecords record);

    int insertSelective(Pointsrecords record);

    List<Pointsrecords> selectByExample(PointsrecordsExample example);

    Pointsrecords selectByPrimaryKey(Integer id);
    
    List<Pointsrecords> selectpoints(Integer userid,Date time,Integer type,Integer fromtype);

    int updateByExampleSelective(@Param("record") Pointsrecords record, @Param("example") PointsrecordsExample example);

    int updateByExample(@Param("record") Pointsrecords record, @Param("example") PointsrecordsExample example);

    int updateByPrimaryKeySelective(Pointsrecords record);

    int updateByPrimaryKey(Pointsrecords record);
    
    List<Pointsrecords> selectByuserid(Integer userid);
    
    List<Pointsrecords> selectByuser(@Param(value="userid") Integer userid,@Param(value="createtime") String createtime);

	List<PcPointsDto> selectPointByPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<PcPointsDto> selectPlatformPointByPage(PageBeanUtil pageBeanUtil) throws Exception;
}
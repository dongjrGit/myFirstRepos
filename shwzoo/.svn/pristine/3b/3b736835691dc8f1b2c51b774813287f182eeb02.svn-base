package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.pc.dto.AfterserviceDto;
import com.yinlian.pc.dto.PcPointsDto;
import com.yinlian.wssc.web.po.Pointrecords;
import com.yinlian.wssc.web.po.PointrecordsExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface PointrecordsMapper {
    int countByExample(PointrecordsExample example);

    int deleteByExample(PointrecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pointrecords record);

    int insertSelective(Pointrecords record);

    List<Pointrecords> selectByExampleWithBLOBs(PointrecordsExample example);

    List<Pointrecords> selectByExample(PointrecordsExample example);

    Pointrecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pointrecords record,
                                 @Param("example") PointrecordsExample example);

    int updateByExampleWithBLOBs(@Param("record") Pointrecords record,
                                 @Param("example") PointrecordsExample example);

    int updateByExample(@Param("record") Pointrecords record,
                        @Param("example") PointrecordsExample example);

    int updateByPrimaryKeySelective(Pointrecords record);

    int updateByPrimaryKeyWithBLOBs(Pointrecords record);

    int updateByPrimaryKey(Pointrecords record);

    List<PcPointsDto> selectByUserid(Integer userid) throws Exception;
    
    List<PcPointsDto> selectByType(Integer userid,Integer type) throws Exception;
    
    List<PcPointsDto> selectByTime(Integer userid,String start,String end) throws Exception;
    
    List<PcPointsDto> selectByTimes(Integer userid,String start) throws Exception;

}
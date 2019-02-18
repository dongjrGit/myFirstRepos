package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.web.dto.FullGiftDto;
import com.yinlian.wssc.web.po.Fullgift;
import com.yinlian.wssc.web.po.FullgiftExample;

public interface FullgiftMapper {

    int deleteByExample(FullgiftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Fullgift record);

    int insertSelective(Fullgift record);

    List<Fullgift> selectByExample(FullgiftExample example);

    Fullgift selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Fullgift record, @Param("example") FullgiftExample example);

    int updateByExample(@Param("record") Fullgift record, @Param("example") FullgiftExample example);

    int updateByPrimaryKeySelective(Fullgift record);

    int updateByPrimaryKey(Fullgift record);

    List<Fullgift>  selectByActId(int acid,@Param("count")int count) throws Exception;
    
    List<Fullgift>  selectByActIds(List<Integer> actids) throws Exception;
    
    List<FullGiftDto>  selectGiffandCoupon(int actid)throws Exception;

    List<FullGiftDto>  selectGiffandSku(int actid)throws Exception;

    List<FullGiftDto>  selectGiffByActId(int actid)throws Exception;
    
    int updateChangePoint(Integer id,int point) throws Exception;
    
    int updateChangeCount(Integer id,int count) throws Exception;

	List<Fullgift> getByIds(List<Integer> ids);

	int updateFullGiftCountByIds(List<UpDateActDto> ugiftdtos);
	
	Fullgift getbyTypeAndGiftid(int gtype,int gid) throws Exception;
}
package com.yinlian.wssc.web.mapper;

import com.yinlian.api.app.dto.SkuShowtimeDto;
import com.yinlian.wssc.web.dto.SkuTimeStockDto;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.SkuShowtimeExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuShowtimeMapper {
    int countByExample(SkuShowtimeExample example)throws Exception;

    int deleteByExample(SkuShowtimeExample example)throws Exception;

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(SkuShowtime record)throws Exception;

    int insertSelective(SkuShowtime record)throws Exception;

    List<SkuShowtime> selectByExample(SkuShowtimeExample example)throws Exception;

    SkuShowtime selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") SkuShowtime record, 
    		@Param("example") SkuShowtimeExample example)throws Exception;

    int updateByExample(@Param("record") SkuShowtime record, 
    		@Param("example") SkuShowtimeExample example)throws Exception;

    int updateByPrimaryKeySelective(SkuShowtime record)throws Exception;

    int updateByPrimaryKey(SkuShowtime record)throws Exception;
    
    List<SkuShowtime> getBySkuid(Integer skuid)throws Exception;
    
    List<SkuShowtime> getBySpuid(Integer spuid)throws Exception;
    
    SkuShowtime getBySkuidLast(Integer skuid)throws Exception;
    
    SkuShowtime getBySpuidLast(Integer spuid)throws Exception;
    
    SkuShowtime getBySkuidAndMonth(Integer skuid,Integer year,Integer month)throws Exception;
    
    List<SkuShowtime> getListBySkuidAndMonth(Integer skuid,Integer spuid,Integer year,Integer month)throws Exception;

	void delListBySkuidAndMonth(Integer skuid, Integer spuid, Integer showy, Integer showm)throws Exception;

	List<SkuTimeStockDto> getSpuStockPage(PageBeanUtil beanUtil)throws Exception;

	int deleteList(List<Integer> ids)throws Exception;

	int insertList(List<SkuShowtime> showtimes)throws Exception;

	SkuShowtime getSkuTimeByDay(Integer skuid, Integer spuid, Integer showyear,
			Integer showmonth, Integer showdays)throws Exception;

	SkuShowtime getSkuTime(Integer spuid, Integer year, Integer month, Integer day)throws Exception;
	
	SkuShowtime getSkuTimeBySkuid(Integer skuid, Integer year, Integer month, Integer day)throws Exception;

	List<SkuShowtimeDto> getShowTimeDtoBySpuid(Integer spuid)throws Exception;
}
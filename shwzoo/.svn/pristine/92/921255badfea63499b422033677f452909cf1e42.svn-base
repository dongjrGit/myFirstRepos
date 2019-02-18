package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.SpSpuDto;
import com.yinlian.wssc.web.dto.V_SpikeActivity;
import com.yinlian.wssc.web.dto.sgSpuDto;
import com.yinlian.wssc.web.po.Spikeactivity;
import com.yinlian.wssc.web.po.SpikeactivityExample;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface SpikeactivityMapper {
	
	    int countByExample(SpikeactivityExample example);

	    int deleteByExample(SpikeactivityExample example);

	    int deleteByPrimaryKey(Integer id);

	    int insert(Spikeactivity record) throws Exception;

	    int insertSelective(Spikeactivity record);

	    List<Spikeactivity> selectByExample(SpikeactivityExample example);

	    Spikeactivity selectByPrimaryKey(Integer id)throws Exception;

	    int updateByExampleSelective(@Param("record") Spikeactivity record, @Param("example") SpikeactivityExample example);

	    int updateByExample(@Param("record") Spikeactivity record, @Param("example") SpikeactivityExample example);

	    int updateByPrimaryKeySelective(Spikeactivity record);

	    int updateByPrimaryKey(Spikeactivity record)throws Exception;
	    
	    List<Spikeactivity> getListPage(PageBeanUtil pageBeanUtil)throws Exception;
	    
	    int changeStatus(int status,int id)throws Exception;
	    
	    int deleteSpike(Spikeactivity record)throws Exception;
	    
	    List<V_SpikeActivity> GetApplyBySpikeIDPage(PageBeanUtil pageBeanUtil)throws Exception;
	    
	    List<V_SpikeActivity> GetShopApplyByPage(PageBeanUtil pageBeanUtil)throws Exception;
	    
	    List<SpSpuDto> getSpuBySpikeIDPage(PageBeanUtil pageBeanUtil)throws Exception;
	    
	    List<Spikeactivity> getByDate(Criteria criteria)throws Exception;
	    
	    List<Spu> getSpuStartwithName(int shopid,int spikeid,@Param("name")String name)throws Exception;
	    
	    SpSpuDto getSpikeBySpikeIDAndSpuID(int spikeid,int spuid)throws Exception;
	    
	    List<Spikeactivity> getExcitListPage(PageBeanUtil pageBeanUtil)throws Exception;
	    
	    sgSpuDto getActBySpuid(int spuid,@Param("acttype") int acttype)throws Exception;
	   
	    List<Spikeactivity> getActListBySpu(@Param("spuid")int spuid,@Param("acttype") int acttype)throws Exception;

	    int getSpuPriceStartwithName(int shopid,int spikeid,String name)throws Exception;
	    
	    List<Spikeactivity> getByEndDate(Criteria criteria)throws Exception;
	    
}

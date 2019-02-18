package com.yinlian.wssc.web.mapper;

import java.util.List;

import com.yinlian.api.app.dto.ExcitingActDetailDto;
import com.yinlian.api.app.dto.ExcitingActDto;
import com.yinlian.api.app.dto.PromotionDto;
import com.yinlian.api.app.dto.SpikeDto;
import com.yinlian.api.app.dto.SpikeSpuDto;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface SpikeactivityMapperCustom {

	SpikeDto getmsActivity(int usersite)throws Exception;
	
	List<SpikeSpuDto> getSpuListByActivityIDPage(PageBeanUtil pBeanUtil)throws Exception;
	
	List<SpikeSpuDto> getSgSpuByPage(PageBeanUtil pBeanUtil)throws Exception;
	List<SpikeSpuDto> gettgSpuByPage(PageBeanUtil pBeanUtil)throws Exception;
	
	List<PromotionDto> getcxActivity()throws Exception;
	
	List<SpikeSpuDto> getcxSpuByPage(PageBeanUtil pBeanUtil)throws Exception;
	
	SpikeDto getSpikeActivity()throws Exception;
	
	List<ExcitingActDto> getExcitingByPage(PageBeanUtil pBeanUtil)throws Exception;
	
	ExcitingActDetailDto getBySpikeid(Integer id)throws Exception;

	int seekSeckillCount();

	List<SpikeDto> getmsActivityList(Integer usesite);
}

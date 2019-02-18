package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.web.dto.SpSpuDto;
import com.yinlian.wssc.web.po.SpikeSpu;
import com.yinlian.wssc.web.po.SpikeSpuExample;

public interface SpikeSpuMapper {
    int countByExample(SpikeSpuExample example);

    int deleteByExample(SpikeSpuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpikeSpu record);

    int insertSelective(SpikeSpu record);

    List<SpikeSpu> selectByExample(SpikeSpuExample example);

    SpikeSpu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpikeSpu record, @Param("example") SpikeSpuExample example);

    int updateByExample(@Param("record") SpikeSpu record, @Param("example") SpikeSpuExample example);

    int updateByPrimaryKeySelective(SpikeSpu record);

    int updateByPrimaryKey(SpikeSpu record);

	int updateSpikeSpuCountByIds(List<UpDateActDto> spiIds) throws Exception;

	SpSpuDto getSpSpuDtoBySpikeID(@Param("spikeid") int spikeid,@Param("spuid") int spuid);
	
	int deleteByShopID(int shopid,int spikeid)throws Exception;
	
	int updateIsPhone(int isphone,int id)throws Exception;
}
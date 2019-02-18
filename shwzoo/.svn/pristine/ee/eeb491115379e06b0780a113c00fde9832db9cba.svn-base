package com.yinlian.wssc.web.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.LotteryDto;
import com.yinlian.wssc.web.po.Lottery;
import com.yinlian.wssc.web.po.LotteryExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface LotteryMapper {
    int countByExample(LotteryExample example);

    int deleteByExample(LotteryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Lottery record);

    int insertSelective(Lottery record);

    List<Lottery> selectByExample(LotteryExample example);

    Lottery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Lottery record,
                                 @Param("example") LotteryExample example);

    int updateByExample(@Param("record") Lottery record, @Param("example") LotteryExample example);

    int updateByPrimaryKeySelective(Lottery record);

    int updateByPrimaryKey(Lottery record);

    /**
     * 分页查询中奖名单
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Lottery> selectLotteryAllByPage(PageBeanUtil pageBeanUtil);

    /**
     *@param userid
     *@param startTime
     *@param endTime
     *@return
     */
    List<Lottery> selectByUserIdCurrentDay(Integer userid, Date startTime, Date endTime)
                                                                                        throws Exception;
    /**
     * 查询最新的20位中奖信息
     * @param c
     * @return
     */
	List<LotteryDto> selectNewLottery() throws Exception;
}
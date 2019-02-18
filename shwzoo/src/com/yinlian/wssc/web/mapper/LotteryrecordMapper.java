package com.yinlian.wssc.web.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Lotteryrecord;
import com.yinlian.wssc.web.po.LotteryrecordExample;

public interface LotteryrecordMapper {
    int countByExample(LotteryrecordExample example);

    int deleteByExample(LotteryrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Lotteryrecord record);

    int insertSelective(Lotteryrecord record);

    List<Lotteryrecord> selectByExample(LotteryrecordExample example);

    Lotteryrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Lotteryrecord record,
                                 @Param("example") LotteryrecordExample example);

    int updateByExample(@Param("record") Lotteryrecord record,
                        @Param("example") LotteryrecordExample example);

    int updateByPrimaryKeySelective(Lotteryrecord record);

    int updateByPrimaryKey(Lotteryrecord record);

    /**
     *@param userid
     *@param date
     * @param endDate 
     *@return
     */
    Lotteryrecord selectByUserIdCurrentDay(Integer userid, Date staDate, Date endDate)
                                                                                      throws Exception;
}
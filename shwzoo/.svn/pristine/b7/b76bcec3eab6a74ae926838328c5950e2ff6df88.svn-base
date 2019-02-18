package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.LotteryDictionary;
import com.yinlian.wssc.web.po.LotteryDictionaryExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface LotteryDictionaryMapper {
    int countByExample(LotteryDictionaryExample example);

    int deleteByExample(LotteryDictionaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LotteryDictionary record);

    int insertSelective(LotteryDictionary record);

    List<LotteryDictionary> selectByExample(LotteryDictionaryExample example);

    LotteryDictionary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LotteryDictionary record,
                                 @Param("example") LotteryDictionaryExample example);

    int updateByExample(@Param("record") LotteryDictionary record,
                        @Param("example") LotteryDictionaryExample example);

    int updateByPrimaryKeySelective(LotteryDictionary record);

    int updateByPrimaryKey(LotteryDictionary record);

    /**
     * 根据唯一标识查询奖项信息
     * @param firstPrize
     * @return
     */
    LotteryDictionary queryByMark(int firstPrize) throws Exception;

    /**
     * 分页查询所有
     * 
     * @param pageBeanUtil
     * @return
     */
    List<LotteryDictionary> selectAllByPage(PageBeanUtil pageBeanUtil) throws Exception;
}
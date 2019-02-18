package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.LotteryParam;
import com.yinlian.wssc.web.po.LotteryParamExample;

public interface LotteryParamMapper {
    int countByExample(LotteryParamExample example);

    int deleteByExample(LotteryParamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LotteryParam record);

    int insertSelective(LotteryParam record);

    List<LotteryParam> selectByExample(LotteryParamExample example);

    LotteryParam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LotteryParam record,
                                 @Param("example") LotteryParamExample example);

    int updateByExample(@Param("record") LotteryParam record,
                        @Param("example") LotteryParamExample example);

    int updateByPrimaryKeySelective(LotteryParam record);

    int updateByPrimaryKey(LotteryParam record);

    /**
     *@param value
     *@return
     */
    String selectNameByType(int type) throws Exception;

    /**
     *@param value
     *@return
     */
    int selectValueByType(int type) throws Exception;

    /**
     *@param mark
     *@param vcount
     */
    void updateByType(int vcount, Integer mark) throws Exception;

	LotteryParam getByLId(Integer ruleid) throws Exception;
}
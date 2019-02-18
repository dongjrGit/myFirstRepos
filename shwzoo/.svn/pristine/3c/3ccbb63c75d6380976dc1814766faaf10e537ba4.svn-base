package com.yinlian.wssc.web.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.UserfinanceDto;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.UserfinanceExample;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaFinance;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface UserfinanceMapper {
    int countByExample(UserfinanceExample example);

    int deleteByExample(UserfinanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userfinance record);

    int insertSelective(Userfinance record);

    List<Userfinance> selectByExample(UserfinanceExample example);

    Userfinance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userfinance record,
                                 @Param("example") UserfinanceExample example);

    int updateByExample(@Param("record") Userfinance record,
                        @Param("example") UserfinanceExample example);

    int updateByPrimaryKeySelective(Userfinance record);

    int updateByPrimaryKey(Userfinance record);

    Userfinance getLastUserFinance(int userID) throws Exception;

    List<Userfinance> getListByNumber(String code, int status) throws Exception;

    Userfinance getUserFinance(int id, Integer status) throws Exception;

    Userfinance getListByNumberAndType(String code, int type) throws Exception;

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Userfinance> selectByPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    List<Userfinance> getUseridFinance(int userID) throws Exception;
    
    List<Userfinance> getUseridandTimeFinance(int userID,String start,String end) throws Exception;
    
    List<Userfinance> getUseridandMonthFinance(int userID,String time) throws Exception;

	List<UserfinanceDto> selectPlatformByPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<UserfinanceDto> selectPlatformList(@Param("criteria")CriteriaFinance criteria)throws Exception;

	List<UserfinanceDto> selectShopFinanceByPage(PageBeanUtil pageBeanUtil)throws Exception;

	List<UserfinanceDto> selectShopFinanceList(@Param("criteria")CriteriaFinance criteria)throws Exception;
    
    
}
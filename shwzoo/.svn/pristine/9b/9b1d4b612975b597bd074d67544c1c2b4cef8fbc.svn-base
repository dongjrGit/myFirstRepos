package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.UsercapitalExample;

public interface UsercapitalMapper {
    int countByExample(UsercapitalExample example);

    int deleteByExample(UsercapitalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Usercapital record);

    int insertSelective(Usercapital record);

    List<Usercapital> selectByExample(UsercapitalExample example);

    Usercapital selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Usercapital record,
                                 @Param("example") UsercapitalExample example);

    int updateByExample(@Param("record") Usercapital record,
                        @Param("example") UsercapitalExample example);

    int updateByPrimaryKeySelective(Usercapital record);

    int updateByPrimaryKey(Usercapital record);

    Usercapital getBalanceRowLockById(int userid) throws Exception;

    /**
     * 修改账户冻结状态
     * 
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Integer id, Integer status);

    /**
     * 
     * @param record
     */
    void insertForPrinaryKey(Usercapital record) throws Exception;
}
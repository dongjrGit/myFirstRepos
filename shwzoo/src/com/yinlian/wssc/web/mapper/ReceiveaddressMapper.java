package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Receiveaddress;
import com.yinlian.wssc.web.po.ReceiveaddressExample;

public interface ReceiveaddressMapper {
    int countByExample(ReceiveaddressExample example);

    int deleteByExample(ReceiveaddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Receiveaddress record);

    int insertSelective(Receiveaddress record);

    List<Receiveaddress> selectByExample(ReceiveaddressExample example);

    Receiveaddress selectByPrimaryKey(Integer id);
    
    List<Receiveaddress> selectByUserId(Integer userid);

    int updateByExampleSelective(@Param("record") Receiveaddress record, @Param("example") ReceiveaddressExample example);

    int updateByExample(@Param("record") Receiveaddress record, @Param("example") ReceiveaddressExample example);

    int updateByPrimaryKeySelective(Receiveaddress record);

    int updateByPrimaryKey(Receiveaddress record);
    /**
     * 设置用户默认地址
     * @param uid
     * @param adrid
     * @return
     */
    int updateDefault(@Param("uid") Integer uid,@Param("adrid") Integer adrid);
}
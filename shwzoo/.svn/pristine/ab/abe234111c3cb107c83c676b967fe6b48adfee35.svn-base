package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.VoucherDto;
import com.yinlian.wssc.web.po.Voucher;
import com.yinlian.wssc.web.po.VoucherExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface VoucherMapper {
    int countByExample(VoucherExample example) throws Exception;

    int deleteByExample(VoucherExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Voucher record) throws Exception;

    int insertSelective(Voucher record) throws Exception;

    List<Voucher> selectByExample(VoucherExample example) throws Exception;

    Voucher selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Voucher record,
                                 @Param("example") VoucherExample example) throws Exception;

    int updateByExample(@Param("record") Voucher record, @Param("example") VoucherExample example)
                                                                                                  throws Exception;

    int updateByPrimaryKeySelective(Voucher record) throws Exception;

    int updateByPrimaryKey(Voucher record) throws Exception;
    
    List<VoucherDto> getlistByPage(PageBeanUtil pBeanUtil) throws Exception;
    
    VoucherDto getByGroupCode(String code) throws Exception;
    
    int updateStatus(int status,int id) throws Exception;
    
    int updateStatusList(List<Integer> idlist) throws Exception;
    
    String getVCodebyCoupon(int couponid,int userid) throws Exception;
}
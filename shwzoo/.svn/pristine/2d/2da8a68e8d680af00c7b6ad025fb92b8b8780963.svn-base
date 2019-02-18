package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.UserCoupon;
import com.yinlian.wssc.web.po.UserCouponExample;

public interface UserCouponMapper {
    int countByExample(UserCouponExample example);

    int deleteByExample(UserCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCoupon record)throws Exception;

    int insertSelective(UserCoupon record);

    List<UserCoupon> selectByExample(UserCouponExample example);

    UserCoupon selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") UserCoupon record, @Param("example") UserCouponExample example);

    int updateByExample(@Param("record") UserCoupon record, @Param("example") UserCouponExample example);

    int updateByPrimaryKeySelective(UserCoupon record);

    int updateByPrimaryKey(UserCoupon record)throws Exception;
    
    UserCoupon getByUserAndCoupon(Integer couponid,Integer userid)throws Exception;
    
    List<UserCoupon> selectCount(Integer couponid,Integer userid) throws Exception;
    
    int insertList(List<UserCoupon> list)throws Exception;
    
    int updateOrcancelUse(UserCoupon record)throws Exception;
    
    int deleteOrcancelCoupon(UserCoupon record)throws Exception;
    
    int cancelUseList(List<UserCoupon> list)throws Exception;
    
    List<UserCoupon> getUserCouponList(String ids,Integer userid)throws Exception;
    
    List<UserCoupon> selectByUserId(Integer userid) throws Exception;
}
package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.AvailableCouponDto;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.CouponExample;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface CouponMapper {
    int countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
      
    int updateStatus(Coupon record)throws Exception;
    
    List<Coupon> queryCoupon(Criteria a)throws Exception;
    
    List<Coupon> getCouponByPage(PageBeanUtil pBeanUtil)throws Exception;
    
    int deleteCoupon(Coupon record)throws Exception;
    
    int updateCount(Coupon record)throws Exception;
    
    List<Coupon> getAvailableCoupon(@Param("shopid") Integer shopid)throws Exception;
    Coupon getAvailableFirst(@Param("shopid")Integer shopid)throws Exception;
    
    List<Coupon> getCouponStartwithName(Integer shopid,String name)throws Exception;

	List<Coupon> getListByIds(List<Integer> ids) throws Exception;
	
	List<Coupon> getShopAvailableCoupon(@Param("shopid") Integer shopid)throws Exception;
	
	List<AvailableCouponDto> getAvailableCouponByPage(PageBeanUtil pBeanUtil)throws Exception;
	
	Coupon getbyGroupcodeAndUsetype(String code,int usetype)throws Exception;
	
	List<Coupon> getbyGroupcode(String code)throws Exception;
	
	int selectCount(int userid) throws Exception;

	List<Coupon> getShopCoupon(int usesite);
	
	Coupon getByIDandUsesite(int id,int usesite)throws Exception;
	
	List<Coupon> getShopCouponApi(int shopid,int usesite)throws Exception;


	List<Coupon> queryByShopId()throws Exception;

	int queryCouponCount(@Param("userId")int userId,@Param("webSet") int value) throws Exception;
}
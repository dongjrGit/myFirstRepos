package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.CouponDto;
import com.yinlian.api.app.dto.CouponShopDto;
import com.yinlian.api.app.dto.SearchCouponDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CouponShopCartDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.UserCoupon;
import com.yinlian.wssc.web.util.Criteria;

public interface CouponService {

	public int insert(Coupon record) throws Exception;

	public int updateByID(Coupon record) throws Exception;

	public int deleteByID(Integer id) throws Exception;

	public Coupon getByID(Integer id) throws Exception;

	List<Coupon> queryByCoupon(Criteria criteria) throws Exception;

	PageBean getList(Criteria criteria, Integer page, Integer size)throws Exception;

	int updateStatus(Integer status, Integer id) throws Exception;

	int deleteCoupon(Coupon record) throws Exception;

	int updateCount(Coupon record) throws Exception;

	List<Coupon> getAvailableCoupon(Integer shopid) throws Exception;

	List<Coupon> getCouponStartwithName(Integer shopid, String name)throws Exception;
	
	public PageBean getByUserID(Criteria criteria,Integer page,Integer size)throws Exception;
	
	public List<Coupon> getShopCoupon(int webset)throws Exception;
    
    public int addUserCoupon(Integer couponid,Integer userid,String userName,BaseResult item) throws Exception;

	public Float UseCoupon(Integer couponid,Integer userid,Float price,String desc) throws Exception;

	public int deleteUCoupon(Integer id,Integer userid) throws Exception;
	
	public int updateUCouponCancel(List<Integer> couponids,Integer userid) throws Exception;

	public UserCoupon getUCByID(Integer id) throws Exception;
	
	public int addUserCouponList(List<Integer> couponids,Integer userid) throws Exception;
	
	public Boolean IsExistByShopID(Integer shopid)throws Exception;

	/**
	 * 根据ids查询列表
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<Coupon> getListByIds(List<Integer> ids) throws Exception;
	
	PageBean getMenberListPage(Criteria criteria,Integer page,Integer size)throws Exception;

	public int addUCouponList(List<Integer> userids,Integer couponid) throws Exception;
	
	List<Coupon> getShopAvailableCoupon(Integer shopid) throws Exception;
	
	PageBean getByCouponIDPage(Criteria criteria,Integer page,Integer size)throws Exception;
	
	public PageBean getUserCouponList(Criteria criteria,Integer page,Integer size)throws Exception;
	
	List<CouponDto> getOrderCoupon(List<SearchCouponDto> dtos,Integer userid,Integer useplatform)throws Exception;

	public List<Shop> getShopStartWithName(String name)throws Exception;

	public int insertAssign(Coupon coupon)throws Exception;

	public List<Spu> getSpuStartWithName(String name, Integer toInt)throws Exception;
	
	List<Coupon> getbyGroupcode(String code)throws Exception;
	
	CouponShopDto getLotteryCoupon()throws Exception;
	
	List<CouponShopDto> getLotteryCouponList()throws Exception;
	
	int getCount(int userid) throws Exception;

	Coupon getByIDandUsesite(int id,int usesite)throws Exception;
	
	List<CouponShopCartDto> getShopCouponApi(int shopid,int usesite,int userid)throws Exception;

	
	public PageBean getByUserIDorderby(Criteria criteria,Integer page,Integer size)throws Exception;

	
	public PageBean getUserCouponListPC(Criteria criteria, Integer page, Integer size) throws Exception;

	List<Coupon> findByShopId(int shopId) throws Exception;

	public int getWapCouponCount(int userId, int value) throws Exception;
}

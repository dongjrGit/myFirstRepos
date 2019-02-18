package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.dto.VoucherDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Voucher;
import com.yinlian.wssc.web.util.Criteria;

public interface VoucherService {
public int insert(Voucher vchr)throws Exception; 

PageBean getlistByPage(Criteria criteria,int page,int size) throws Exception;

VoucherDto getByGroupCode(String code) throws Exception;

/**
 * 根据优惠卷ID和用户ID获取代金券编号
 * @param couponid
 * @param userid
 * @return
 * @throws Exception
 */
String getVCodebyCoupon(int couponid,int userid) throws Exception;
}

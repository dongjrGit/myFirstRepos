package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.VoucherDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.VoucherMapper;
import com.yinlian.wssc.web.po.Voucher;
import com.yinlian.wssc.web.service.VoucherService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("voucherService")
public class VoucherServiceImpl implements VoucherService {
	@Autowired
	private VoucherMapper voucherMapper;
	
	@Override
	public int insert(Voucher vchr) throws Exception {
		return voucherMapper.insert(vchr);
	}

	public PageBean getlistByPage(Criteria criteria,int page,int size) throws Exception{
		PageBeanUtil pBeanUtil=new PageBeanUtil(criteria,page,size);
		PageBean pageBean=pBeanUtil.getPage();
		List<VoucherDto> list=voucherMapper.getlistByPage(pBeanUtil);
		for (VoucherDto dto : list) {
			if(dto.getEndtime().getTime()>new Date().getTime()){
				dto.setIsout(1);
			}
			else{
				dto.setIsout(0);
			}
			dto.setName("满"+dto.getQuota()+"减"+dto.getValue());
		}
		pageBean.setBeanList(list);
		return pageBean;
		
	}
	
	public VoucherDto getByGroupCode(String code) throws Exception{
		return voucherMapper.getByGroupCode(code);
	}

	/**
	 * 根据优惠卷ID和用户ID获取代金券编号
	 */
	@Override
	public String getVCodebyCoupon(int couponid, int userid) throws Exception {
		
		return  voucherMapper.getVCodebyCoupon(couponid, userid);
	}

}

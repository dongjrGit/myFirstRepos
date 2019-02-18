package com.yinlian.wssc.web.mapper;

import java.util.List;

import com.yinlian.wssc.web.dto.BuyerdetailDto;
import com.yinlian.wssc.web.dto.CouponDetailDto;
import com.yinlian.wssc.web.dto.ProOrderdetailDto;
import com.yinlian.wssc.web.po.OrderactivityHistory;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.SkuPackage;
import com.yinlian.wssc.web.po.SpikeSpu;
import com.yinlian.wssc.web.po.Spikeactivity;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface OrderMapperAnalysisReport {

	List<Orderdetail> getSalesAnalysis(PageBeanUtil pageBeanUtil)throws Exception;
	
	/**
	 * 得到销售明细
	 * @param pBeanUtil
	 * @return
	 */
	List<Orderdetail> getSalesDeatils(PageBeanUtil pBeanUtil) throws Exception;
	
	/**
	 * 区域客户分析
	 * @param pBeanUtil
	 * @return
	 */
	List<ProOrderdetailDto> getbuyerArea(PageBeanUtil pBeanUtil) throws Exception;
	
	/**
	 * 客户明细
	 * @param pageBeanUtil
	 * @return
	 */
	List<BuyerdetailDto> getbuyerDetail(PageBeanUtil pageBeanUtil) throws Exception;
	
	/**
	 * 
	 * @param pBeanUtil
	 * @return
	 * @throws Exception
	 */
	List<CouponDetailDto> getCouponDetail(PageBeanUtil pBeanUtil) throws Exception;
	
	
	List<Package> getPackage(PageBeanUtil pBeanUtil) throws Exception;
	
	List<SkuPackage> selectSkuPackage(PageBeanUtil pBeanUtil) throws Exception;
	
	List<OrderactivityHistory> selectActivityPackage(PageBeanUtil pBeanUtil) throws Exception;
	
    List<Spikeactivity> getSpike(PageBeanUtil pBeanUtil) throws Exception;
	
	List<SpikeSpu> selectSpikeSpu(PageBeanUtil pBeanUtil) throws Exception;
	
	List<OrderactivityHistory> selectActivitySpike(PageBeanUtil pBeanUtil) throws Exception;
}

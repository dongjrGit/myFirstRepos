package com.yinlian.wssc.web.service;


import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.CouponsDto;
import com.yinlian.wssc.web.dto.PackagesDto;
import com.yinlian.wssc.web.dto.SalesAnalysisDto;
import com.yinlian.wssc.web.dto.SalesDetailsDto;
import com.yinlian.wssc.web.dto.UserAreaDto;
import com.yinlian.wssc.web.dto.UserDetailDto;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaCouponDetail;

public interface AnalysisReportService {

	/**
	 * 销售概况统计
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	SalesAnalysisDto getSalesAnalysis(Criteria criteria)throws Exception;
	/**
	 * 销售明细统计
	 * @param criteria
	 * @return
	 */
	SalesDetailsDto getSalesDetails(Criteria criteria,Integer page,Integer size,ReusltItem item)throws Exception;
	
	/**
	 * 客户区域分析
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	UserAreaDto getbuyerArea(Criteria criteria,Integer page,Integer size,ReusltItem item) throws Exception;
	
	/**
	 * 客户明细
	 * @param _index
	 * @param pageRow
	 * @param criteria
	 * @return
	 */
	UserDetailDto getbuyerDetail(Criteria criteria,Integer page,Integer size,ReusltItem item)  throws Exception;

	CouponsDto getCouponDetail(CriteriaCouponDetail criteria, Integer page,
			Integer size, ReusltItem item) throws Exception;
	
	/**
	 * 组合商品
	 * @param criteria
	 * @param toInt
	 * @param toInt2
	 * @param item
	 * @return
	 * @throws Exception
	 */
	PackagesDto getPackageDetail(CriteriaCouponDetail criteria, Integer page,
			Integer size, ReusltItem item) throws Exception;
	
	/**
	 * 闪购
	 * @param criteria
	 * @param page
	 * @param size
	 * @param item
	 * @return
	 * @throws Exception
	 */
	Object getSpikeSG(CriteriaCouponDetail criteria, Integer page,
			Integer size, ReusltItem item) throws Exception;
}

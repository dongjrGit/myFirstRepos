/*
 * @(#) BusinessbillsServiceImpl.java 2016年8月29日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.BusinessBillsStatusEnum;
import com.yinlian.Enums.CouponUseTypeEnum;
import com.yinlian.Enums.OrderDetailStatusEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Enums.ShopSettlementTypeEnum;
import com.yinlian.wssc.search.BillsOrderCriteria;
import com.yinlian.wssc.search.BusinessBillsCriteria;
import com.yinlian.wssc.web.dto.BillsOrderDto;
import com.yinlian.wssc.web.dto.BusinessbillsDto;
import com.yinlian.wssc.web.dto.OrderDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.BusinessbillsMapper;
import com.yinlian.wssc.web.mapper.OrderBillsMapper;
import com.yinlian.wssc.web.mapper.OrderdetailMapper;
import com.yinlian.wssc.web.mapper.OrdersMapper;
import com.yinlian.wssc.web.po.Businessbills;
import com.yinlian.wssc.web.po.OrderBills;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.BusinessbillsService;
import com.yinlian.wssc.web.util.OrderCriteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

import data.ParseUtil;

@Component("businessbillsService")
public class BusinessbillsServiceImpl implements BusinessbillsService {
	
	@Autowired
	BusinessbillsMapper businessbillsMapper;
	
	@Autowired
	OrdersMapper ordersMapper;
	
	@Autowired
	private  OrderBillsMapper   orderBillsMapper;
	
	@Autowired
	OrderdetailMapper orderdetailMapper;

	@Override
	public PageBean getBusinessBills(BusinessBillsCriteria criteria,
			Integer page, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil=new PageBeanUtil(criteria, page, size);
		
		PageBean pageBean=pageBeanUtil.getPage();
		
		List<Businessbills> beanList=businessbillsMapper.selectBusinessByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	
	@Override
	public PageBean getBillOrdersList(OrderCriteria criteria,Integer page, Integer size)  throws Exception {
		PageBeanUtil pageBeanUtil=new PageBeanUtil(criteria, page, size);
		PageBean pageBean=pageBeanUtil.getPage();
		//List<OrderDto> beanList=ordersMapper.selectOrdersDetailsByPage(pageBeanUtil);
		//pageBean.setBeanList(beanList);
		return pageBean;
	}


	/// <summary>
    /// 生成商户对账汇总账单
    /// </summary>
	private List<Businessbills> setSummaryBills()  throws Exception{

        List<Businessbills> bills = new ArrayList<Businessbills>();
        OrderCriteria orderCriteria=new OrderCriteria();
        Date start;
		Calendar last=Calendar.getInstance();
		last.setTime(new Date());
		last.set(Calendar.DATE,last.get(Calendar.DATE)-8);

		//当月第一天
		if (last.get(Calendar.DATE)==1) {
			last.set(Calendar.MONTH,last.get(Calendar.MONTH)-1);
			start=last.getTime();
			orderCriteria.setBegintime(start);
			orderCriteria.setSettlementType(ShopSettlementTypeEnum.月.getValue());
			orderCriteria.setStatus(BusinessBillsStatusEnum.未处理.getValue());
			orderCriteria.setOrderByClause("BeginDate");
			orderCriteria.setSort("asc");
			findAll(orderCriteria).forEach(o->{
				bills.add(o);
			});
			last.setTime(new Date());
			last.set(Calendar.DATE,last.get(Calendar.DATE)-8);
		}
		
		//当年总天数的第一天
		if (last.get(Calendar.DAY_OF_YEAR)==1) {
			last.set(Calendar.YEAR,last.get(Calendar.YEAR)-1);
			start=last.getTime();
			orderCriteria.setBegintime(start);
			orderCriteria.setSettlementType(ShopSettlementTypeEnum.年.getValue());
			orderCriteria.setStatus(BusinessBillsStatusEnum.未处理.getValue());
			orderCriteria.setOrderByClause("BeginDate");
			orderCriteria.setSort("asc");
			bills.addAll(bills);
			findAll(orderCriteria).forEach(o->{
				bills.add(o);
			});
			last.setTime(new Date());
			last.set(Calendar.DATE,last.get(Calendar.DATE)-8);
		}
		

		//当前月第一天 并且 第一个月 或 第四个月 或 第七个月 或 弟十个月
		if (last.get(Calendar.DATE)==1 && (last.get(Calendar.MONTH)==1||last.get(Calendar.MONTH)==4||last.get(Calendar.MONTH)==7||last.get(Calendar.MONTH)==10)) {
			last.set(Calendar.MONTH,last.get(Calendar.MONTH)-3);
			start=last.getTime();
			
			orderCriteria.setBegintime(start);
			orderCriteria.setSettlementType(ShopSettlementTypeEnum.季度.getValue());
			orderCriteria.setStatus(BusinessBillsStatusEnum.未处理.getValue());
			orderCriteria.setOrderByClause("BeginDate");
			orderCriteria.setSort("asc");
			findAll(orderCriteria).forEach(o->{
				bills.add(o);
			});
			last.setTime(new Date());
			
			last.set(Calendar.DATE,last.get(Calendar.DATE)-8);
		}
		if (last.get(Calendar.DAY_OF_WEEK)==DayOfWeek.MONDAY.getValue()) {
			last.set(Calendar.MONTH,last.get(Calendar.MONTH)-7);
			start=last.getTime();
			orderCriteria.setBegintime(start);
			orderCriteria.setSettlementType(ShopSettlementTypeEnum.周.getValue());
			orderCriteria.setStatus(BusinessBillsStatusEnum.未处理.getValue());
			orderCriteria.setOrderByClause("BeginDate");
			orderCriteria.setSort("asc");
			findAll(orderCriteria).forEach(o->{
				bills.add(o);
			});
			last.setTime(new Date());
			last.set(Calendar.DATE,last.get(Calendar.DATE)-8);
		}
		return bills;
	}

	

//	@Override
//	public void generateBills() throws Exception{
//		Calendar last=Calendar.getInstance();
//		last.setTime(new Date());
//
//		last.set(Calendar.DATE,last.get(Calendar.DATE)-8);
//		String now = ParseUtil.parseDateToString(last.getTime(), "yyyy-MM-dd");
//		List<Businessbills> bills = new ArrayList<Businessbills>();
//		BillsOrderCriteria criteria=new BillsOrderCriteria();
//		criteria.setDeliverConfirmDate(now);
//		criteria.setOrderstatus(OrderStatusEnum.已确认收货.getValue()+"");
//		List<BusinessbillsDto> buslist=businessbillsMapper.generateBills(criteria);
//		if (buslist!=null&&buslist.size()>0) {
//			HashMap<Integer, BigDecimal> rates= new HashMap<Integer,BigDecimal>();
//			buslist.forEach(b->{
//				Businessbills bb;
//				
//				if (bills.stream().filter(bl->bl.getShopid().equals(b.getShopId())).count()>0) {
//					bb=bills.stream().filter(bl->bl.getShopid().equals(b.getShopId())).collect(Collectors.toList()).get(0);
//					bb.setTurnover(bb.getTurnover().add(b.getPrice()));
//					bb.setDiscountmoney(bb.getDiscountmoney().add(b.getDiscount()));
//					bb.setActualpay(bb.getActualpay().add(b.getActualPay()));
//					if (b.getCouponUseType()!=null&&CouponUseTypeEnum.通用.getValue()==b.getCouponUseType()) {
//						bb.setVoucher(bb.getVoucher().add(b.getFaceValue()));
//					}
//				}else{
//					bb=new  Businessbills();
//					bb.setShopid(b.getShopId());
//					bb.setShopname(b.getName());
//					java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
//					try {
//						bb.setBegindate(formatter.parse(now));
//						bb.setEnddate(formatter.parse(now));
//					} catch (Exception e) {
//					}
//					bb.setTurnover(b.getPrice());
//					bb.setActualpay(b.getActualPay());
//					bb.setBankname(b.getBankName());
//					bb.setBanknum(b.getBankNum());
//					bb.setBankusername(b.getBankUserName());
//					bb.setStatus(0);
//					bb.setDatatype(0);
//					bb.setDiscountmoney(b.getDiscount());
//					if (b.getCouponUseType()!=null&&CouponUseTypeEnum.通用.getValue()==b.getCouponUseType()) {
//						bb.setVoucher(b.getFaceValue());
//					}else{
//						bb.setVoucher(new BigDecimal(0));
//					}
//					bills.add(bb);
//					rates.put(b.getShopId(),b.getRoyaltyRate()==null||b.getRoyaltyRate() == ""?new BigDecimal(0):new BigDecimal(b.getRoyaltyRate()));
//				}
//				bills.forEach(w -> {
//					w.setCommission(w.getActualpay().multiply(rates.get(w.getShopid())).divide(new BigDecimal(100)));
//				});	
//				bills.forEach(w -> {
//					w.setSettlement(w.getActualpay().add(w.getVoucher()).subtract(w.getCommission()));
//				});
//			});
//		}
//		
//		
//		List<Businessbills> sBills=setSummaryBills();
//		if (sBills!=null&&sBills.size()>0)
//			bills.addAll(sBills);
//		if(bills.size()>0)
//			businessbillsMapper.insertAll(bills);
//	}

	@Override
	public List<Businessbills> findAll(OrderCriteria criteria)  throws Exception{
		return businessbillsMapper.selectAll(criteria);
	}


	@Override
	public Businessbills findById(Integer id)  throws Exception{
		return businessbillsMapper.selectByPrimaryKey(id);
	}


	@Override
	public Integer updateById(Businessbills businessbills)  throws Exception{
		return businessbillsMapper.updateByPrimaryKeySelective(businessbills);
	}


	@Override
	public Integer addBusBill() throws Exception {
		
		BillsOrderCriteria criteria=new BillsOrderCriteria();
//		Calendar calendar = Calendar.getInstance();
//    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	calendar.add(Calendar.DATE, -1);
//    	String end = formatter.format(calendar.getTime());
//    	criteria.setDeliverConfirmDate(end);
    	criteria.setOrderstatu(OrderDetailStatusEnum.已使用.getValue());
		
    	List<OrderBills> billsList=new ArrayList<OrderBills>();
    	billsList=orderBillsMapper.selectBills(criteria);
    	
    	if(billsList==null||billsList.size()==0){
    		return 0;
    	}
    	for (OrderBills ordersBillsDto : billsList) {
    		ordersBillsDto.setStatus(0);
    		ordersBillsDto.setCreatedate(new Date());
		}
    	//将已加入对账的订单的是否对账字段设为true 已对账
    	List<Orderdetail> orderdetails=orderdetailMapper.selectNoBills(criteria);
    	for (Orderdetail orderdetail : orderdetails) {
    		orderdetail.setIsaddbill(true);
			orderdetailMapper.updateByPrimaryKey(orderdetail);
		}
		return orderBillsMapper.inserList(billsList);
	}
	
	
	@Override
	public PageBean getOrderBills(BusinessBillsCriteria criteria, int page, int size) throws Exception {
		PageBeanUtil pageBeanUtil=new PageBeanUtil(criteria, page, size);
		PageBean pageBean=pageBeanUtil.getPage();
		List<OrderBills> beanList=orderBillsMapper.selectOrderBillsByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}


	@Override
	public PageBean getBillsOrderList(BusinessBillsCriteria criteria, int page, int size) throws Exception {
		PageBeanUtil pageBeanUtil=new PageBeanUtil(criteria, page, size);
		PageBean pageBean=pageBeanUtil.getPage();
		List<BillsOrderDto> beanList=orderBillsMapper.selectBillsOrderByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}


	@Override
	public OrderBills selectById(Integer id) throws Exception {
		
		return orderBillsMapper.selectByPrimaryKey(id);
	}


	@Override
	public int updateBills(OrderBills bills) throws Exception {
		
		return orderBillsMapper.updateByPrimaryKey(bills);
	}
	
	
}

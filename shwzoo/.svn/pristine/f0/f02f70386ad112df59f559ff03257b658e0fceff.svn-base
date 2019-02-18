package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.ActivityHistory;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.BuyerSalesAnalysisDto;
import com.yinlian.wssc.web.dto.BuyerdetailDto;
import com.yinlian.wssc.web.dto.CouponDetailDto;
import com.yinlian.wssc.web.dto.CouponsDto;
import com.yinlian.wssc.web.dto.PackageDetailDto;
import com.yinlian.wssc.web.dto.PackagesDto;
import com.yinlian.wssc.web.dto.ProOrderdetailDto;
import com.yinlian.wssc.web.dto.ProSalesAnalysisDto;
import com.yinlian.wssc.web.dto.SalesAnalysisDto;
import com.yinlian.wssc.web.dto.SalesDetailsDto;
import com.yinlian.wssc.web.dto.SkuSalesAnalysisDto;
import com.yinlian.wssc.web.dto.SpikesDto;
import com.yinlian.wssc.web.dto.UserAreaDto;
import com.yinlian.wssc.web.dto.UserCouponDetailDto;
import com.yinlian.wssc.web.dto.UserDetailDto;
import com.yinlian.wssc.web.mapper.OrderMapperAnalysisReport;
import com.yinlian.wssc.web.po.OrderactivityHistory;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.SkuPackage;
import com.yinlian.wssc.web.po.SpikeSpu;
import com.yinlian.wssc.web.po.Spikeactivity;
import com.yinlian.wssc.web.service.AnalysisReportService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaCouponDetail;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 统计报表业务层
 * @author Administrator
 *
 */
@Component("analysisReportService")
public class AnalysisReportServiceImpl implements AnalysisReportService {

	@Autowired
	private OrderMapperAnalysisReport orderMapperAnalysisReport ;
	
	/**
	 * 销售概况统计
	 */
	@Override
	public SalesAnalysisDto getSalesAnalysis(Criteria criteria)
			throws Exception {
		
		 PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, null, null);
		 //获取基础数据
	     List<Orderdetail> list = orderMapperAnalysisReport.getSalesAnalysis(pBeanUtil);
	        
	     SalesAnalysisDto dto=new SalesAnalysisDto();
	     if(list==null || list.size()==0){
	    	 return dto;
	     }
	     //根据订单ID分组
	     Map<Integer, List<Orderdetail>> groupOrderList = list.stream().collect(
					Collectors.groupingBy(Orderdetail::getOrderid));
	     //根据订单买家ID分组
	     Map<Integer, List<Orderdetail>> groupBuyerList = list.stream().collect(
					Collectors.groupingBy(Orderdetail::getBuyerid));
	     //筛选取消订单，根据订单ID分组
	     Map<Integer, List<Orderdetail>> groupCancelOrderList = list.stream()
	    		 .filter(a->a.getStatus()==OrderStatusEnum.已取消.getValue()).collect(
					Collectors.groupingBy(Orderdetail::getOrderid));
	     Float totalmoney=0.0f,cancelmoney=0.0f;
	     Integer skucount=0;
	     //遍历获取下单量，下单金额
	     for (Map.Entry<Integer, List<Orderdetail>> entry : groupOrderList
					.entrySet()) {
	    	 List<Orderdetail> detailist=entry.getValue();
	    	for (Orderdetail detail : detailist) {
	    		if(detail.getProductcount()!=null&&detail.getProductprice()!=null){
	    			totalmoney+=detail.getProductcount()*detail.getProductprice().floatValue();
		    		skucount+=detail.getProductcount();
	    		}
	    		
			}
		}
	   //获取取消订单数,取消订单金额
	     for (Map.Entry<Integer, List<Orderdetail>> entrycancel : groupCancelOrderList
					.entrySet()) {
	    	 List<Orderdetail> detailist=entrycancel.getValue();
	    	for (Orderdetail detail : detailist) {
	    		if(detail.getProductcount()!=null&&detail.getProductprice()!=null){
	    			cancelmoney+=detail.getProductcount()*detail.getProductprice().floatValue();
	    		}
			}
		}
	     //数据汇总 获取下单客户数，订单数，取消订单数，取消订单金额，订单总金额，订单商品件数
	     dto.setBuyercount(groupBuyerList.size());
	     dto.setOrdercount(groupOrderList.size());
	     dto.setCancelcount(groupCancelOrderList.size());
	     dto.setCancelmoney(cancelmoney);
	     dto.setOrdermoney(totalmoney);
	     dto.setSkucount(skucount);
	     dto.setCancelpercent(dto.getCancelcount()/dto.getOrdercount().floatValue()*100);
	     dto.setBuyerbycount(dto.getOrdercount().floatValue()/dto.getBuyercount());
	     dto.setBuyerbymoney(dto.getOrdermoney()/dto.getBuyercount());
	     
	     //根据库存商品分组 获取下单量，下单金额，下单客户数
	     Map<Integer, List<Orderdetail>> groupSkuList = list.stream().collect(
					Collectors.groupingBy(Orderdetail::getSkuId));
	     //下单客户数列表
	     List<SkuSalesAnalysisDto> buyerList=new ArrayList<SkuSalesAnalysisDto>();
	     //下单数列表
	     List<SkuSalesAnalysisDto> orderList=new ArrayList<SkuSalesAnalysisDto>();
	     //下单金额列表
	     List<SkuSalesAnalysisDto> moneyList=new ArrayList<SkuSalesAnalysisDto>();

	     SkuSalesAnalysisDto buyerdto=null;
	     for (Map.Entry<Integer, List<Orderdetail>> entrysku : groupSkuList
					.entrySet()) {
	    	 List<Orderdetail> detailist=entrysku.getValue();
	    	 if (detailist==null) {
				continue;
			}
	    	 Map<BigDecimal, List<Orderdetail>> groupPriceList = detailist.stream().collect(
						Collectors.groupingBy(Orderdetail::getProductprice));
	    	 for (Map.Entry<BigDecimal, List<Orderdetail>> entrysku1 : groupPriceList
						.entrySet()) {
	    		 buyerdto=new SkuSalesAnalysisDto();
		    	 buyerdto.setSkuid(entrysku.getKey());
	    		 buyerdto.setSkuprice(entrysku1.getKey());
	    		 //订单客户数
		    	 buyerdto.setSkuname(detailist.get(0).getProductname());
		    	 buyerdto.setSkunum(detailist.get(0).getProductnum());
		    	 
	    		 List<Orderdetail>  pricedetailist=entrysku.getValue();
	    		 buyerdto.setBuyercount(pricedetailist.stream().collect(Collectors.groupingBy(Orderdetail::getBuyerid)).size());
		    	 buyerList.add(buyerdto);
		    	 
		    	 //下单量
		    	 buyerdto.setOrdercount(pricedetailist.stream().collect(Collectors.groupingBy(Orderdetail::getOrdercode)).size());
		    	 orderList.add(buyerdto);
		    	 
		    	 //下单金额
		    	 Double tmoneyDouble=detailist.stream().filter(a->a.getProductcount()!=null && a.getProductprice()!=null).mapToDouble(a->a.getProductcount()*a.getProductprice().doubleValue()).sum();
		    	 buyerdto.setOrdermoney(tmoneyDouble.floatValue());
		    	 moneyList.add(buyerdto);
	    	 }
	    	/* 
	    	 buyerdto.setSkuprice(detailist.get(0).getProductprice().floatValue());
	    	 buyerdto.setBuyercount(detailist.stream().collect(Collectors.groupingBy(Orderdetail::getBuyerid)).size());
	    	 buyerList.add(buyerdto);
	    	 //下单量
	    	 buyerdto.setOrdercount(detailist.stream().collect(Collectors.groupingBy(Orderdetail::getOrdercode)).size());
	    	 orderList.add(buyerdto);
	    	 //下单金额
	    	 
	    	 Double tmoneyDouble=detailist.stream().filter(a->a.getProductcount()!=null && a.getProductprice()!=null).mapToDouble(a->a.getProductcount()*a.getProductprice().doubleValue()).sum();
	    	 buyerdto.setOrdermoney(tmoneyDouble.floatValue());
	    	 moneyList.add(buyerdto);*/
		}
	     //根据下单客户数排序获取前20条数据
	     if(buyerList.size()>20){
	    	 buyerList=buyerList.stream().sorted((p1,p2)->p2.getBuyercount().compareTo(p1.getBuyercount())).limit(20).collect(Collectors.toList());
	     }else{
	    	 buyerList=buyerList.stream().sorted((p1,p2)->p2.getBuyercount().compareTo(p1.getBuyercount())).collect(Collectors.toList());
	     }
	     dto.setSkubybuyer(buyerList);
	     //根据下单数排序获取前20条数据
	     if(orderList.size()>20){
	    	 orderList=orderList.stream().sorted((p1,p2)->p2.getOrdercount().compareTo(p1.getOrdercount())).limit(20).collect(Collectors.toList());
	     }else{
	    	 orderList=orderList.stream().sorted((p1,p2)->p2.getOrdercount().compareTo(p1.getOrdercount())).collect(Collectors.toList());
	     }
	     dto.setSkubyorder(orderList);
	     //根据下单金额排序获取前20条数据
	     if(moneyList.size()>20){
	    	 moneyList=moneyList.stream().sorted((p1,p2)->p2.getOrdermoney().compareTo(p1.getOrdermoney())).limit(20).collect(Collectors.toList());
	     }else{
	    	 moneyList=moneyList.stream().sorted((p1,p2)->p2.getOrdermoney().compareTo(p1.getOrdermoney())).collect(Collectors.toList());
	     }
	     dto.setSkubymoney(moneyList);
	     
		return dto;
	}
	
	@Override
	public SalesDetailsDto getSalesDetails(Criteria criteria,Integer page,Integer size,ReusltItem item) throws Exception {

		 PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, null, null);
		 //获取基础数据
	     List<Orderdetail> list = orderMapperAnalysisReport.getSalesDeatils(pBeanUtil);
	     
	     SalesDetailsDto dto=new SalesDetailsDto();
	     if(list==null || list.size()==0){
	    	 return dto;
	     }
	     
	     //根据订单ID分组
	     Map<Integer, List<Orderdetail>> groupOrderList = list.stream().collect(
					Collectors.groupingBy(Orderdetail::getOrderid));
	     
	    //根据订单买家ID分组
	     Map<Integer, List<Orderdetail>> groupBuyerList = list.stream().collect(
					Collectors.groupingBy(Orderdetail::getBuyerid));

	     
	     Float totalmoney=0.0f;
	     Integer skucount=0;
	     //遍历获取下单量，下单金额
	     for (Map.Entry<Integer, List<Orderdetail>> entry : groupOrderList
					.entrySet()) {
	    	 List<Orderdetail> detailist=entry.getValue();
	    	for (Orderdetail detail : detailist) {
	    		if(detail.getProductcount()!=null && detail.getProductprice()!=null){
	    			totalmoney+=detail.getProductcount()*detail.getProductprice().floatValue();
		    		skucount+=detail.getProductcount();
	    		}	
			}
		}
	     //数据汇总 获取下单客户数，订单数，取消订单数，取消订单金额，订单总金额，订单商品件数
	     dto.setBuyercount(groupBuyerList.size());
	     dto.setOrdercount(groupOrderList.size());
	     dto.setOrdermoney(totalmoney);
	     dto.setSkucount(skucount);
	     
	     //根据库存商品分组 获取下单量，下单金额，下单客户数
	     Map<Integer, List<Orderdetail>> groupSkuList = list.stream().collect(
					Collectors.groupingBy(Orderdetail::getSkuId));
	    
	     List<SkuSalesAnalysisDto> salesList=new ArrayList<SkuSalesAnalysisDto>();

	     SkuSalesAnalysisDto buyerdto=null;
	     for (Map.Entry<Integer, List<Orderdetail>> entrysku : groupSkuList
					.entrySet()) {
	    	 List<Orderdetail> detailist=entrysku.getValue();
	    	 if (detailist==null) {
					continue;
				 }
	    	 Map<BigDecimal, List<Orderdetail>> groupPriceList = detailist.stream().collect(
						Collectors.groupingBy(Orderdetail::getProductprice));
	    	 
	    	 for (Map.Entry<BigDecimal, List<Orderdetail>> entrysku1 : groupPriceList
						.entrySet()) {
	    		 buyerdto=new SkuSalesAnalysisDto();
		    	 buyerdto.setSkuid(entrysku.getKey());
		    	 //订单客户数
		    	 buyerdto.setSkuname(detailist.get(0).getProductname());
		    	 buyerdto.setSkunum(detailist.get(0).getProductnum());
		    	 
	    		 buyerdto.setSkuprice(entrysku1.getKey());
	    		 List<Orderdetail> pricedetailist=entrysku1.getValue();
		    	 if (pricedetailist==null) {
					continue;
				 }
		    	 buyerdto.setBuyercount(pricedetailist.stream().collect(Collectors.groupingBy(Orderdetail::getBuyerid)).size());
		    	 //下单量
		    	 buyerdto.setOrdercount(pricedetailist.stream().collect(Collectors.groupingBy(Orderdetail::getOrderid)).size());
		    	 //下单金额
		    	 Double tmoneyDouble=pricedetailist.stream().filter(a->a.getProductcount()!=null && a.getProductprice()!=null).mapToDouble(a->a.getProductcount()*a.getProductprice().doubleValue()).sum();
		    	 buyerdto.setOrdermoney(tmoneyDouble.floatValue());
		    	 
		    	 //下单商品件数
		    	 buyerdto.setSkucount(pricedetailist.stream().filter(a->a.getProductcount()!=null).mapToInt(a->a.getProductcount()).sum());
		    	 salesList.add(buyerdto);
	    	 }
		 }
	     //按下单量排序 倒叙
	     salesList=salesList.stream().sorted((p1,p2)->p2.getOrdercount().compareTo(p1.getOrdercount())).collect(Collectors.toList());
	     int totalcount=salesList.size(); //列表总数
	     int totalpage=totalcount%size==0?totalcount/size:(totalcount/size)+1; //总页数
	     if(page==totalpage){
	    	 salesList=salesList.stream().skip((page-1)*size).limit(totalcount%size).collect(Collectors.toList());
	     }else{
	    	 salesList=salesList.stream().skip((page-1)*size).limit(size).collect(Collectors.toList());
	     }
	     dto.setSkuSalasDetails(salesList);
	     //返回当前页数和列表总数
	     item.setPageIndex(page);
	     item.setMaxRow(totalcount);
	     return dto;
	    
	}
	
	/**
	 * 客户区域分析
	 */
	@Override
	public UserAreaDto getbuyerArea(Criteria criteria,Integer page,Integer size,ReusltItem item) throws Exception {
		 
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, null, null);
		 //获取基础数据
	     List<ProOrderdetailDto> list = orderMapperAnalysisReport.getbuyerArea(pBeanUtil);
	     
	     UserAreaDto dto=new UserAreaDto();
	     if(list==null || list.size()==0){
	    	 return dto;
	     }
	     //根据省份进行分组
	     Map<String, List<ProOrderdetailDto>> groupProList=list.stream().filter(a->a.getProvinceName()!=null).collect(
	    		 Collectors.groupingBy(ProOrderdetailDto::getProvinceName));
	    
	     List<ProSalesAnalysisDto> proList=new ArrayList<ProSalesAnalysisDto>();
	     
	     ProSalesAnalysisDto buyerdto=null;
	     for (Map.Entry<String, List<ProOrderdetailDto>> entrysku : groupProList
					.entrySet()) {
	    	 buyerdto=new ProSalesAnalysisDto();
	    	
	    	 List<ProOrderdetailDto> detailist=entrysku.getValue();
	    	 if (detailist==null) {
				continue;
			}
	    	 buyerdto.setProName(detailist.get(0).getProvinceName());;
	    	 //订单客户数
	    	 buyerdto.setBuyercount(detailist.stream().collect(Collectors.groupingBy(Orderdetail::getBuyerid)).size());
	    	 //下单量
	    	 buyerdto.setOrdercount(detailist.stream().collect(Collectors.groupingBy(Orderdetail::getOrdercode)).size());
	    	 //下单金额
	    	 Double tmoneyDouble=detailist.stream().filter(a->a.getProductcount()!=null&&a.getProductprice()!=null).mapToDouble(a->a.getProductcount()*a.getProductprice().doubleValue()).sum();
	    	 buyerdto.setOrdermoney(tmoneyDouble.floatValue());
	    	 
	    	 //下单商品件数
	    	 buyerdto.setSkucount(detailist.stream().filter(a->a.getProductcount()!=null).mapToInt(a->a.getProductcount()).sum());
	    	 proList.add(buyerdto);
		 }
	     proList=proList.stream().sorted((p1,p2)->p2.getOrdercount().compareTo(p1.getOrdercount())).collect(Collectors.toList());
	     int totalcount=proList.size(); //列表总数
	     int totalpage=totalcount%size==0?totalcount/size:(totalcount/size)+1; //总页数
	     if(page==totalpage){
	    	 proList=proList.stream().skip((page-1)*size).limit(totalcount%size).collect(Collectors.toList());
	     }else{
	    	 proList=proList.stream().skip((page-1)*size).limit(size).collect(Collectors.toList());
	     }
	
	     dto.setProDetail(proList);
		    
	     //返回当前页数和列表总数
	     item.setPageIndex(page);
	     item.setMaxRow(totalcount);
	     return dto;
	}
	
	/**
	 * 客户明细
	 */
	@Override
	public UserDetailDto getbuyerDetail(Criteria criteria,Integer page,Integer size,ReusltItem item)
			throws Exception {
		 PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,null, null);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		//PageBean pageBean = pageBeanUtil.getPage();
		 List<BuyerdetailDto> list = orderMapperAnalysisReport.getbuyerDetail(pageBeanUtil);
		 
		 UserDetailDto dto=new UserDetailDto();
	     if(list==null || list.size()==0){
	    	 return dto;
	     }
	     Map<Integer, List<BuyerdetailDto>> grouporderList=list.stream().collect(
	    		 Collectors.groupingBy(BuyerdetailDto::getOrderid));
	     
	     Map<Integer, List<BuyerdetailDto>> groupbuyList=list.stream().collect(
	    		 Collectors.groupingBy(BuyerdetailDto::getBuyerid));
	     
	     Float totalmonery=0.0f; //下单金额
	     Integer skuCount=0;   //下单量
	     for(Map.Entry<Integer, List<BuyerdetailDto>> entry:grouporderList.entrySet()){
	    	 List<BuyerdetailDto> detailist=entry.getValue();
	    	 for(BuyerdetailDto detail:detailist){
	    		 if(detail.getProductcount()!=null&&detail.getProductprice()!=null){
	    			 totalmonery+=detail.getProductcount()*detail.getProductprice().floatValue();
		    		 skuCount+=detail.getProductcount();
	    		 }
	    		
	    	 }
	     }
	     Map<Integer,List<BuyerdetailDto>> groupbuyerList=list.stream().collect(
	    		 Collectors.groupingBy(BuyerdetailDto::getBuyerid));
	     //下单客户数
	     dto.setBuyercount(groupbuyerList.size());
	     //下单量
	     dto.setOrdercount(grouporderList.size());
	     //下单商品件数
	     dto.setSkucount(skuCount);
	     //下单金额
	     dto.setOrdermoney(totalmonery);
	     //客单量
	     dto.setBuyerbycount(dto.getOrdercount().floatValue()/dto.getBuyercount());
	     //客单价
	     dto.setBuyerbymoney(dto.getOrdermoney()/dto.getBuyercount());
	     
	     List<BuyerSalesAnalysisDto> buyerlist=new ArrayList<BuyerSalesAnalysisDto>();
	     
	     BuyerSalesAnalysisDto buyerdto=null;
	     for (Map.Entry<Integer, List<BuyerdetailDto>> entrysku : groupbuyList
					.entrySet()) {
	    	 buyerdto=new BuyerSalesAnalysisDto();
	    	
	    	 List<BuyerdetailDto> detailist=entrysku.getValue();
	    	 if (detailist==null) {
				continue;
			}
	    	// buyerdto.setProName(detailist.get(0).getProvinceName());;
	    	 buyerdto.setUsername(detailist.get(0).getUsername());
	    	 //下单量
	    	 Integer orderCount=detailist.stream().collect(Collectors.groupingBy(Orderdetail::getOrdercode)).size();
	    	 buyerdto.setOrdercount(orderCount);
	    	 //下单金额
	    	 Double tmoneyDouble=detailist.stream().filter(a->a.getProductcount()!=null && a.getProductprice()!=null).mapToDouble(a->a.getProductcount()*a.getProductprice().doubleValue()).sum();
	    	 buyerdto.setOrdermoney(tmoneyDouble.floatValue());
	    	 //下单商品件数
	    	 buyerdto.setSkucount(detailist.stream().filter(a->a.getProductcount()!=null).mapToInt(a->a.getProductcount()).sum());
	    	 //客单价
	    	 buyerdto.setBuyerbymoney(tmoneyDouble.floatValue()/orderCount);
	    	 buyerlist.add(buyerdto);
		 }
	     buyerlist=buyerlist.stream().sorted((p1,p2)->p2.getOrdercount().compareTo(p1.getOrdercount())).collect(Collectors.toList());
	     int totalcount=buyerlist.size(); //列表总数
	     int totalpage=totalcount%size==0?totalcount/size:(totalcount/size)+1; //总页数
	     if(page==totalpage){
	    	 buyerlist=buyerlist.stream().skip((page-1)*size).limit(totalcount%size).collect(Collectors.toList());
	     }else{
	    	 buyerlist=buyerlist.stream().skip((page-1)*size).limit(size).collect(Collectors.toList());
	     }
	
	     dto.setProDetail(buyerlist);
		    
	     //返回当前页数和列表总数
	     item.setPageIndex(page);
	     item.setMaxRow(totalcount);
		return dto;
	    
	}
	
	/**
	 * 优惠卷数据分析
	 */
	@Override
	public CouponsDto getCouponDetail(CriteriaCouponDetail criteria, Integer page,
			Integer size, ReusltItem item) throws Exception {
		
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, null, null);
		 //获取基础数据
	     List<CouponDetailDto> list = orderMapperAnalysisReport.getCouponDetail(pBeanUtil);
	     
	     CouponsDto dto=new CouponsDto();
	     if(list==null || list.size()==0){
	    	 return dto;
	     }
	     /**
	      * 根据优惠卷分组
	      */
	     Map<Integer, List<CouponDetailDto>> groupCouponIdList=list.stream().collect(
	    		 Collectors.groupingBy(CouponDetailDto::getCouponid));
	     
	     Float couponMoney=0.0f; //发放金额
	     int couponCount=0;   //发放数量
	     Float getMoney=0.0f; //领取金额
	     int getCount=0;  //领取数量
	     Integer useCount=0;  //使用用户卷数量
	     Float   useMoney=0.0f;  //使用金额
	     Integer outCount=0;     //过期数量
	     Float   outMoney=0.0f;  //过期金额
	     int  orderCount=0;   //涉及订单数量
	     //涉及订单金额
	     Float  orderMoney=0.0f;
	     //优惠金额
	     Float  activity_price=0.0f;
	     for(Map.Entry<Integer, List<CouponDetailDto>> entry:groupCouponIdList.entrySet()){
	    	 List<CouponDetailDto> detailist=entry.getValue();
	    	 getCount +=detailist.size();
	    	 couponCount +=detailist.get(0).getCouponCount()+detailist.size();
	    	 
	    	 getMoney+=detailist.size()*detailist.get(0).getFaceValue();
	    	 couponMoney+=(detailist.get(0).getCouponCount()+detailist.size())*detailist.get(0).getFaceValue();
	    	 
	    	 List<CouponDetailDto> usedetailist=detailist.stream().filter(a->a.getIsUse()==1).collect(Collectors.toList());
	    	 if(usedetailist!=null&&usedetailist.size()>0){
	    		 useCount+=usedetailist.size();
		    	 useMoney+=usedetailist.size()*detailist.get(0).getFaceValue();
	    	 }
	    	 
	    	 if(detailist.get(0).getEndTime().getTime()<new Date().getTime()){
	    		 outCount+=detailist.get(0).getCouponCount();
	    		 outMoney+=detailist.get(0).getCouponCount()*detailist.get(0).getFaceValue();
	    	 }
	    	 
	    	 List<CouponDetailDto> nousedetailist=detailist.stream().filter(a->a.getIsUse()==0 && a.getOutTime()!=null &&
	    			 a.getOutTime().getTime()<new Date().getTime()).collect(Collectors.toList());
	    	 if(nousedetailist!=null&&nousedetailist.size()>0){
	    		 outCount+=detailist.size();
		    	 outMoney+=detailist.size()*detailist.get(0).getFaceValue();
	    	 } 
	    	 
	    	 orderCount+=usedetailist.size();
	    	 Float orderm=0.0f;
	    	 Float yhm=0.0f;
	    	 for (CouponDetailDto couponDetailDto : usedetailist) {
	    		 orderm+=couponDetailDto.getOrderprice();
	    		 yhm+=couponDetailDto.getActivity_price();
			 }
	    	 orderMoney+=orderm;
	    	 activity_price+=yhm;
	     }
	     //领取数量
	     dto.setGetCount(getCount);
	     //领取金额
	     dto.setGetMoney(getMoney.floatValue());
	     //发放数量
	     dto.setCouponCount(couponCount);
	     //发放金额
	     dto.setCouponMoney(couponMoney.floatValue());
	     //领取率
	     dto.setGetpercent(dto.getGetCount()/dto.getCouponCount().floatValue());
	     
	     dto.setUseCount(useCount);
	     dto.setUseMoney(useMoney);
	     dto.setUserpercent(dto.getUseCount()/dto.getGetCount().floatValue());
	     
	     dto.setOutCount(outCount);
	     dto.setOutMoney(outMoney);
	     dto.setOutpercent(dto.getOutCount()/dto.getCouponCount().floatValue());
	     
	     dto.setOrderCount(useCount);
	     dto.setOrderMoney(orderMoney);
	     dto.setYhMoney(activity_price);
	   
	     List<UserCouponDetailDto> usercouponlist=new ArrayList<UserCouponDetailDto>();
	     UserCouponDetailDto  couponDto=null;
	     
	     for(Map.Entry<Integer, List<CouponDetailDto>> entry:groupCouponIdList.entrySet()){
	    	couponDto=new UserCouponDetailDto();
	    	List<CouponDetailDto> detailist=entry.getValue();
	    	if(detailist==null){
	    		continue;
	    	}
	    	couponDto.setCouponNum(detailist.get(0).getCouponnum());
	    	couponDto.setCouponName(detailist.get(0).getCouponname());
	    	
	    	couponDto.setCouponCount(detailist.get(0).getCouponCount()+detailist.size());
	    	couponDto.setCouponMoney((detailist.get(0).getCouponCount()+detailist.size())*detailist.get(0).getFaceValue().floatValue());
	    	
	    	couponDto.setGetCount(detailist.size());
	    	couponDto.setGetMoney(detailist.size()*detailist.get(0).getFaceValue());
	    	couponDto.setGetpercent(couponDto.getGetCount()/couponDto.getCouponCount().floatValue());
	    	
	    	List<CouponDetailDto> usedetailist=detailist.stream().filter(a->a.getIsUse()==1).collect(Collectors.toList());
	    	couponDto.setUseCount(usedetailist.size());
	    	couponDto.setUseMoney(usedetailist.size()*detailist.get(0).getFaceValue().floatValue());
	    	couponDto.setUserpercent(couponDto.getUseCount()/couponDto.getGetCount().floatValue());
	    	
	    	couponDto.setOrderCount(usedetailist.size());
	    	Float ordermoneys=0.0f;
	    	Float yhmoney=0.0f;
	    	for (CouponDetailDto couponDetailDto : usedetailist) {
	    		ordermoneys+=couponDetailDto.getOrderprice();
	    		yhmoney+=couponDetailDto.getActivity_price();
			}
	    	couponDto.setOrderMoney(ordermoneys.floatValue());
	    	couponDto.setYhMoney(yhmoney);
	    	
	    	usercouponlist.add(couponDto);
	     }
	     
	     int totalcount=groupCouponIdList.size(); //列表总数
	     int totalpage=totalcount%size==0?totalcount/size:(totalcount/size)+1; //总页数
	     if(page==totalpage){
	    	 usercouponlist=usercouponlist.stream().skip((page-1)*size).limit(totalcount%size).collect(Collectors.toList());
	     }else{
	    	 usercouponlist=usercouponlist.stream().skip((page-1)*size).limit(size).collect(Collectors.toList());
	     }
	     dto.setList(usercouponlist);
		    
	     //返回当前页数和列表总数
	     item.setPageIndex(page);
	     item.setMaxRow(totalcount);
	     return dto;
		
	}
	
	/**
	 * 组合商品
	 */
	@Override
	public PackagesDto getPackageDetail(CriteriaCouponDetail criteria,
			Integer page, Integer size, ReusltItem item) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, null, null);
		 
		List<com.yinlian.wssc.web.po.Package> list=orderMapperAnalysisReport.getPackage(pBeanUtil);
	
		PackagesDto dto=new PackagesDto();
	     if(list==null || list.size()==0){
	    	 return dto;
	     }
	 	List<SkuPackage> skuPackages=orderMapperAnalysisReport.selectSkuPackage(pBeanUtil);
		List<OrderactivityHistory> activitylist=orderMapperAnalysisReport.selectActivityPackage(pBeanUtil);
		 
	     //组合商品活动数
	     dto.setActCount(list.size());
	     int skuCount=0;
	     int orderCount=0;
	     Float orderMoney=0.0f;
	     Float yhMoney=0.0f;
	     
	     List<PackageDetailDto> packageList=new ArrayList<PackageDetailDto>();
	     PackageDetailDto packageDto=null;
	     for (com.yinlian.wssc.web.po.Package package1 : list) {
			packageDto=new PackageDetailDto();
			//组合商品编号
			packageDto.setActivityName(package1.getName());
			packageDto.setActivityNum(package1.getNum());
			packageDto.setActivityType(package1.getPacktype());
			
			List<SkuPackage> subSkuPackages=skuPackages.stream().filter(x->x.getPackageid()==package1.getId()).collect(Collectors.toList());
			packageDto.setSkuCount(subSkuPackages.size());
			
			skuCount+=subSkuPackages.size();
			List<OrderactivityHistory> orderlist=activitylist.stream().filter(x->x.getActivityId()==package1.getId()).collect(Collectors.toList());
			packageDto.setOrderCount(orderlist.size());
			Float money=0.0f;
			Float yhmoney=0.0f;
			for (OrderactivityHistory order : orderlist) {
				money+=order.getOrderprice();
				yhmoney+=order.getActivityPrice();
			}
			packageDto.setOrderMoney(money.floatValue());
			packageDto.setYhMoney(yhmoney.floatValue());
			
			orderCount+=orderlist.size();
			orderMoney+=money;
			yhMoney+=yhmoney;
			
			packageList.add(packageDto);
		}
	     dto.setOrderCount(orderCount);
	     dto.setOrderMoney(orderMoney);
	     dto.setYhMoney(yhMoney);
	     dto.setSkuCount(skuCount);
	   
	     
	     int totalcount=packageList.size(); //列表总数
	     int totalpage=totalcount%size==0?totalcount/size:(totalcount/size)+1; //总页数
	     if(page==totalpage){
	    	 packageList=packageList.stream().skip((page-1)*size).limit(totalcount%size).collect(Collectors.toList());
	     }else{
	    	 packageList=packageList.stream().skip((page-1)*size).limit(size).collect(Collectors.toList());
	     }
	     dto.setPackageList(packageList);
		    
	     //返回当前页数和列表总数
	     item.setPageIndex(page);
	     item.setMaxRow(totalcount);
		return dto;
	}
	
	/**
	 * 闪购
	 */
	@Override
	public Object getSpikeSG(CriteriaCouponDetail criteria, Integer page,
			Integer size, ReusltItem item) throws Exception {
		
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, null, null);
		
		List<Spikeactivity> spikelist=orderMapperAnalysisReport.getSpike(pBeanUtil);
	
		SpikesDto dto=new SpikesDto();
	     if(spikelist==null || spikelist.size()==0){
	    	 return dto;
	     }
	 	List<SpikeSpu> spikeSpus=orderMapperAnalysisReport.selectSpikeSpu(pBeanUtil);
		List<OrderactivityHistory> activitylist=orderMapperAnalysisReport.selectActivitySpike(pBeanUtil);
		
	     //组合商品活动数
	     dto.setActCount(spikelist.size());
	     int skuCount=0;
	     int orderCount=0;
	     Float orderMoney=0.0f;
	     Float yhMoney=0.0f;
	     
	     List<PackageDetailDto> packageList=new ArrayList<PackageDetailDto>();
	     PackageDetailDto packageDto=null;
	     for (Spikeactivity spike : spikelist) {
	    	packageDto=new PackageDetailDto();
	    	packageDto.setActivityNum(spike.getSpikenum());
	    	packageDto.setActivityName(spike.getSpikename());
	    	
	    	List<SpikeSpu> subSpikeSpus=spikeSpus.stream().filter(x->x.getSpikeid()==spike.getId()).collect(Collectors.toList());
	    	skuCount+=subSpikeSpus.size();
	    	
	    	List<OrderactivityHistory> subactivitylist=activitylist.stream().filter(x->x.getActivityId()==spike.getId()).collect(Collectors.toList());
	    	packageDto.setOrderCount(subactivitylist.size());
			Float money=0.0f;
			Float yhmoney=0.0f;
			for (OrderactivityHistory order : subactivitylist) {
				money+=order.getOrderprice();
				yhmoney+=order.getActivityPrice();
			}
			packageDto.setOrderMoney(money.floatValue());
			packageDto.setYhMoney(yhmoney.floatValue());
			
			orderCount+=subactivitylist.size();
			orderMoney+=money;
			yhMoney+=yhmoney;
			
			packageList.add(packageDto);

		 }
	     dto.setOrderCount(orderCount);
	     dto.setOrderMoney(orderMoney);
	     dto.setYhMoney(yhMoney);
	     dto.setSkuCount(skuCount);
	   
	     int totalcount=packageList.size(); //列表总数
	     int totalpage=totalcount%size==0?totalcount/size:(totalcount/size)+1; //总页数
	     if(page==totalpage){
	    	 packageList=packageList.stream().skip((page-1)*size).limit(totalcount%size).collect(Collectors.toList());
	     }else{
	    	 packageList=packageList.stream().skip((page-1)*size).limit(size).collect(Collectors.toList());
	     }
	     dto.setPackageList(packageList);
		    
	     //返回当前页数和列表总数
	     item.setPageIndex(page);
	     item.setMaxRow(totalcount);
		 return dto;
	}
}

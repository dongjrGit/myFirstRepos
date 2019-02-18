package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.OrderDetailStatusEnum;
import com.yinlian.api.app.dto.OrderdetailDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.AfterServiceOrderDto;
import com.yinlian.wssc.web.dto.SyShopDto;
import com.yinlian.wssc.web.dto.SySpuDto;
import com.yinlian.wssc.web.dto.V_saleproducts;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.OrderdetailMapper;
import com.yinlian.wssc.web.mapper.OrdersMapper;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.util.CriteriaDdtj;
import com.yinlian.wssc.web.util.CriteriaSaleProduct;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("orderdetailService")
public class OrderdetailServiceImpl implements OrderdetailService {

	@Autowired
	private OrderdetailMapper orderdetailMapper;

	@Autowired
	private ShopService shopService;
	
	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public Orderdetail queryByID(Integer id) throws Exception {

		return orderdetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(Orderdetail orderdetail) throws Exception {

		return orderdetailMapper.updateByPrimaryKeySelective(orderdetail);
	}

	@Override
	public List<OrderdetailDto> selectByOrderid(Integer orderid)
			throws Exception {
		List<OrderdetailDto> orderdetails = orderdetailMapper.queryByDtoOrderid(orderid);
		return orderdetails;
	}

	@Override
	public List<SySpuDto> selectSaleasCount(Platfrom_SYCriteria criteria)
			throws Exception {

		return orderdetailMapper.querySales(criteria);
	}

	@Override
	public List<SyShopDto> selectShopSaleasCount(Platfrom_SYCriteria criteria)
			throws Exception {

		return orderdetailMapper.queryShopSales(criteria);
	}

	@Override
	public List<SySpuDto> selectSellerSaleasCount(Platfrom_SYCriteria criteria)
			throws Exception {

		return orderdetailMapper.querySellerSales(criteria);
	}

	@Override
	public SyShopDto selectSellerCount(Platfrom_SYCriteria criteria)
			throws Exception {

		return orderdetailMapper.querySellerCount(criteria);
	}

	@Override
	public SyShopDto selectSellerCountDay(Platfrom_SYCriteria criteria)
			throws Exception {

		return orderdetailMapper.querySellerCountDay(criteria);
	}

	// 卖家售后订单统计
	@Override
	public AfterServiceOrderDto getDetailsAnalysisByShop(CriteriaDdtj criteria)
			throws Exception {
		PageBeanUtil uBeanUtil = new PageBeanUtil(criteria, null, null);
		AfterServiceOrderDto dto = new AfterServiceOrderDto();
		List<Orderdetail> detaiList = orderdetailMapper
				.getDetailsAnalysis(uBeanUtil);
		if (detaiList != null && detaiList.size() > 0) {
			// 退款申请
			List<Orderdetail> list_TKSQ = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请退款
							.getValue()).collect(Collectors.toList());

			if (list_TKSQ == null || list_TKSQ.size() == 0) {
				dto.setCount_TKSQ(0);
				dto.setMoney_TKSQ(0d);
			} else {
				dto.setCount_TKSQ(list_TKSQ
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_TKSQ(list_TKSQ.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 已退款
			List<Orderdetail> list_TK = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.退款成功
							.getValue()).collect(Collectors.toList());
			if (list_TK == null || list_TK.size() == 0) {
				dto.setCount_TK(0);
				dto.setMoney_TK(0d);
			} else {
				dto.setCount_TK(list_TK
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_TK(list_TK.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 退货申请
			/*List<Orderdetail> list_THSQ = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请退款
							.getValue()).collect(Collectors.toList());
			if (list_THSQ == null || list_THSQ.size() == 0) {
				dto.setCount_THSQ(0);
				dto.setMoney_THSQ(0d);
			} else {
				dto.setCount_THSQ(list_THSQ
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_TKSQ(list_THSQ.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
			// 退货买家发货(中绿售后流程调整，产品需要时请恢复注释)
			/*List<Orderdetail> list_THFH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.退货买家待发货
							.getValue()).collect(Collectors.toList());
			if (list_THFH == null || list_THFH.size() == 0) {
				dto.setCount_THFH(0);
				dto.setMoney_THFH(0d);
			} else {
				dto.setCount_THFH(list_THFH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_THFH(list_THFH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 退货卖家收货
			List<Orderdetail> list_THSH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.退货商家待收货
							.getValue()).collect(Collectors.toList());
			if (list_THSH == null || list_THSH.size() == 0) {
				dto.setCount_THSH(0);
				dto.setMoney_THSH(0d);
			} else {
				dto.setCount_THSH(list_THSH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_THSH(list_THSH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
			// 已退货退款
			/*List<Orderdetail> list_TH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.退款成功
							.getValue()).collect(Collectors.toList());
			if (list_TH == null || list_TH.size() == 0) {
				dto.setCount_TH(0);
				dto.setMoney_TH(0d);
			} else {
				dto.setCount_TH(list_TH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_TH(list_TH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
			// 换货申请
			/*List<Orderdetail> list_HHSQ = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请换货
							.getValue()).collect(Collectors.toList());
			if (list_HHSQ == null || list_HHSQ.size() == 0) {
				dto.setCount_HHSQ(0);
				dto.setMoney_HHSQ(0d);
			} else {
				dto.setCount_HHSQ(list_HHSQ
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_HHSQ(list_HHSQ.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
			// 换货买家发货(中绿售后流程调整，产品需要时请恢复注释)
			/*List<Orderdetail> list_BuyFH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货买家待发货
							.getValue()).collect(Collectors.toList());
			if (list_BuyFH == null || list_BuyFH.size() == 0) {
				dto.setCount_BuyFH(0);
				dto.setMoney_BuyFH(0d);
			} else {
				dto.setCount_BuyFH(list_BuyFH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_BuyFH(list_BuyFH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 换货卖家收货
			List<Orderdetail> list_MJSH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货商家待收货
							.getValue()).collect(Collectors.toList());
			if (list_MJSH == null || list_MJSH.size() == 0) {
				dto.setCount_MJSH(0);
				dto.setMoney_MJSH(0d);
			} else {
				dto.setCount_MJSH(list_MJSH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_MJSH(list_MJSH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 换货卖家发货
			List<Orderdetail> list_MJFH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货商家待发货
							.getValue()).collect(Collectors.toList());
			if (list_MJFH == null || list_MJFH.size() == 0) {
				dto.setCount_MJFH(0);
				dto.setMoney_MJFH(0d);
			} else {
				dto.setCount_MJFH(list_MJFH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_MJFH(list_MJFH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 换货买家收货
			List<Orderdetail> list_BuySH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货买家待收货
							.getValue()).collect(Collectors.toList());
			if (list_BuySH == null || list_BuySH.size() == 0) {
				dto.setCount_BuySH(0);
				dto.setMoney_BuySH(0d);
			} else {
				dto.setCount_BuySH(list_BuySH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_BuySH(list_BuySH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
			// 已换货
			/*List<Orderdetail> list_HH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货成功
							.getValue()).collect(Collectors.toList());
			if (list_HH == null || list_HH.size() == 0) {
				dto.setCount_HH(0);
				dto.setMoney_HH(0d);
			} else {
				dto.setCount_HH(list_HH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_HH(list_HH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
		}

		return dto;
	}

	@Override
	public List<AfterServiceOrderDto> getDetailsAnalysis(CriteriaDdtj criteria,
			Integer page, Integer size, ReusltItem item) throws Exception {
		List<AfterServiceOrderDto> dtoList = new ArrayList<AfterServiceOrderDto>();
		PageBeanUtil uBeanUtil = new PageBeanUtil(criteria, null, null);
		List<Orderdetail> detaiList = orderdetailMapper
				.getDetailsAnalysis(uBeanUtil);
		AfterServiceOrderDto dto = null;
		if (detaiList != null && detaiList.size() > 0) {
			// 根据shopid分组
			Map<Integer, List<Orderdetail>> shopGroupList = detaiList.stream()
					.collect(Collectors.groupingBy(Orderdetail::getSellerid));
			for (Map.Entry<Integer, List<Orderdetail>> entry : shopGroupList
					.entrySet()) {

				List<Orderdetail> detailshopgroup = entry.getValue();
				// 按天统计
				if (criteria.getTjtype() == 1) {
					Map<Date, List<Orderdetail>> detaildatelist = detailshopgroup
							.stream()
							.collect(
									Collectors
											.groupingBy(Orderdetail::getApplytime));
					for (Map.Entry<Date, List<Orderdetail>> entrydate : detaildatelist
							.entrySet()) {
						dto = new AfterServiceOrderDto();
						List<Orderdetail> detaildategroup = entrydate
								.getValue();
						dto.setOrderdate(entrydate.getKey());
						dto.setName(shopService.queryByUserId(entry.getKey())
								.getName());

						switch (criteria.getAftertype()) {
						case 1: // 退款退货
							List<Orderdetail> listReturn = detaildategroup
									.stream()
									.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请退款
											.getValue()
											|| x.getStatus() == OrderDetailStatusEnum.退款成功
													.getValue()
											/*|| x.getStatus() == OrderDetailStatusEnum.申请退货退款
													.getValue()*/
											/*|| x.getStatus() == OrderDetailStatusEnum.退货买家待发货  (中绿售后流程调整，产品需要时请恢复注释)
													.getValue()
											|| x.getStatus() == OrderDetailStatusEnum.退货商家待收货
													.getValue()*/
											/*|| x.getStatus() == OrderDetailStatusEnum.退货退款成功
													.getValue()*/)
									.collect(Collectors.toList());
							dto.setTotalcount(listReturn
									.stream()
									.collect(
											Collectors
													.groupingBy(Orderdetail::getOrderid))
									.size());
							dto.setTotalmoney(listReturn
									.stream()
									.mapToDouble(
											x -> x.getProductprice()
													.doubleValue()).sum());
							getRetrunDto(listReturn, dto);
							break;
						case 2: // 换货
							/*List<Orderdetail> listExchange = detaildategroup
									.stream()
									.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请换货
											.getValue()
											|| x.getStatus() == OrderDetailStatusEnum.换货买家待发货  (中绿售后流程调整，产品需要时请恢复注释)
													.getValue()
											|| x.getStatus() == OrderDetailStatusEnum.换货商家待收货
													.getValue()
											|| x.getStatus() == OrderDetailStatusEnum.换货商家待发货
													.getValue()
											|| x.getStatus() == OrderDetailStatusEnum.换货买家待收货
													.getValue()
											|| x.getStatus() == OrderDetailStatusEnum.换货成功
													.getValue())
									.collect(Collectors.toList());
							dto.setTotalcount(listExchange
									.stream()
									.collect(
											Collectors
													.groupingBy(Orderdetail::getOrderid))
									.size());
							dto.setTotalmoney(listExchange
									.stream()
									.mapToDouble(
											x -> x.getProductprice()
													.doubleValue()).sum());
							getExchangeDto(listExchange, dto);
							break;*/
						case 3: // 维修

							break;
						default:
							break;
						}
						if (dto.getTotalcount() > 0) {
							dtoList.add(dto);
						}
					}
				} else {
					dto = new AfterServiceOrderDto();
					dto.setName(shopService.queryByUserId(entry.getKey())
							.getName());
					switch (criteria.getAftertype()) {
					case 1: // 退款退货
						List<Orderdetail> listReturn = detailshopgroup
								.stream()
								.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请退款
										.getValue()
										|| x.getStatus() == OrderDetailStatusEnum.退款成功
												.getValue()
										/*|| x.getStatus() == OrderDetailStatusEnum.申请退货退款
												.getValue()*/
/*										|| x.getStatus() == OrderDetailStatusEnum.退货买家待发货  (中绿售后流程调整，产品需要时请恢复注释)
												.getValue()
										|| x.getStatus() == OrderDetailStatusEnum.退货商家待收货
												.getValue()*/
										/*|| x.getStatus() == OrderDetailStatusEnum.退货退款成功
												.getValue()*/)
								.collect(Collectors.toList());
						dto.setTotalcount(listReturn
								.stream()
								.collect(
										Collectors
												.groupingBy(Orderdetail::getOrderid))
								.size());
						dto.setTotalmoney(listReturn
								.stream()
								.mapToDouble(
										x -> x.getProductprice().doubleValue())
								.sum());
						getRetrunDto(listReturn, dto);
						break;
					case 2: // 换货
						/*List<Orderdetail> listExchange = detailshopgroup
								.stream()
								.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请换货
										.getValue()
										|| x.getStatus() == OrderDetailStatusEnum.换货买家待发货    (中绿售后流程调整，产品需要时请恢复注释)
												.getValue()
										|| x.getStatus() == OrderDetailStatusEnum.换货商家待收货
												.getValue()
										|| x.getStatus() == OrderDetailStatusEnum.换货商家待发货
												.getValue()
										|| x.getStatus() == OrderDetailStatusEnum.换货买家待收货
												.getValue()
										|| x.getStatus() == OrderDetailStatusEnum.换货成功
												.getValue())
								.collect(Collectors.toList());
						dto.setTotalcount(listExchange
								.stream()
								.collect(
										Collectors
												.groupingBy(Orderdetail::getOrderid))
								.size());
						dto.setTotalmoney(listExchange
								.stream()
								.mapToDouble(
										x -> x.getProductprice().doubleValue())
								.sum());
						getExchangeDto(listExchange, dto);
						break;*/
					case 3: // 维修

						break;
					default:
						break;
					}
					if (dto.getTotalcount() > 0) {
						dtoList.add(dto);
					}
				}
			}
		}
		if (criteria.getTjtype() == 1) {
			dtoList = dtoList
					.stream()
					.sorted((p1, p2) -> p2.getOrderdate().compareTo(
							p1.getOrderdate())).collect(Collectors.toList());
		}
		int totalcount = dtoList.size(); // 列表总数
		int totalpage = totalcount % size == 0 ? totalcount / size
				: (totalcount / size) + 1; // 总页数
		if (page == totalpage) {
			dtoList = dtoList.stream().skip((page - 1) * size)
					.limit(totalcount % size).collect(Collectors.toList());
		} else {
			dtoList = dtoList.stream().skip((page - 1) * size).limit(size)
					.collect(Collectors.toList());
		}
		item.setPageIndex(page);
		item.setMaxRow(totalcount);
		return dtoList;
	}

	private AfterServiceOrderDto getRetrunDto(List<Orderdetail> detaiList,
			AfterServiceOrderDto dto) {
		if (detaiList != null && detaiList.size() > 0) {
			// 退款申请
			List<Orderdetail> list_TKSQ = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请退款
							.getValue()).collect(Collectors.toList());

			if (list_TKSQ == null || list_TKSQ.size() == 0) {
				dto.setCount_TKSQ(0);
				dto.setMoney_TKSQ(0d);
			} else {
				dto.setCount_TKSQ(list_TKSQ
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_TKSQ(list_TKSQ.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 已退款
			List<Orderdetail> list_TK = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.退款成功
							.getValue()).collect(Collectors.toList());
			if (list_TK == null || list_TK.size() == 0) {
				dto.setCount_TK(0);
				dto.setMoney_TK(0d);
			} else {
				dto.setCount_TK(list_TK
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_TK(list_TK.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 退货申请
			/*List<Orderdetail> list_THSQ = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请退货退款
							.getValue()).collect(Collectors.toList());
			if (list_THSQ == null || list_THSQ.size() == 0) {
				dto.setCount_THSQ(0);
				dto.setMoney_THSQ(0d);
			} else {
				dto.setCount_THSQ(list_THSQ
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_THSQ(list_THSQ.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
			// 退货买家发货  (中绿售后流程调整，产品需要时请恢复注释)
			/*List<Orderdetail> list_THFH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.退货买家待发货
							.getValue()).collect(Collectors.toList());
			if (list_THFH == null || list_THFH.size() == 0) {
				dto.setCount_THFH(0);
				dto.setMoney_THFH(0d);
			} else {
				dto.setCount_THFH(list_THFH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_THFH(list_THFH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 退货卖家收货
			List<Orderdetail> list_THSH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.退货商家待收货
							.getValue()).collect(Collectors.toList());
			if (list_THSH == null || list_THSH.size() == 0) {
				dto.setCount_THSH(0);
				dto.setMoney_THSH(0d);
			} else {
				dto.setCount_THSH(list_THSH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_THSH(list_THSH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
			// 已退货退款
			/*List<Orderdetail> list_TH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.退货退款成功
							.getValue()).collect(Collectors.toList());
			if (list_TH == null || list_TH.size() == 0) {
				dto.setCount_TH(0);
				dto.setMoney_TH(0d);
			} else {
				dto.setCount_TH(list_TH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_TH(list_TH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/

		} else {
			dto.setCount_TH(0);
			dto.setMoney_TH(0d);
			dto.setCount_THFH(0);
			dto.setMoney_THFH(0d);
			dto.setCount_THSH(0);
			dto.setMoney_THFH(0d);
			dto.setCount_THSQ(0);
			dto.setMoney_THSQ(0d);
			dto.setCount_TK(0);
			dto.setMoney_TK(0d);
			dto.setCount_TKSQ(0);
			dto.setMoney_TKSQ(0d);
		}
		return dto;
	}

	private AfterServiceOrderDto getExchangeDto(List<Orderdetail> detaiList,
			AfterServiceOrderDto dto) {
		if (detaiList != null && detaiList.size() > 0) {
			// 换货申请
			/*List<Orderdetail> list_HHSQ = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.申请换货
							.getValue()).collect(Collectors.toList());
			if (list_HHSQ == null || list_HHSQ.size() == 0) {
				dto.setCount_HHSQ(0);
				dto.setMoney_HHSQ(0d);
			} else {
				dto.setCount_HHSQ(list_HHSQ
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_HHSQ(list_HHSQ.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
			// 换货买家发货  (中绿售后流程调整，产品需要时请恢复注释)
			/*List<Orderdetail> list_BuyFH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货买家待发货
							.getValue()).collect(Collectors.toList());
			if (list_BuyFH == null || list_BuyFH.size() == 0) {
				dto.setCount_BuyFH(0);
				dto.setMoney_BuyFH(0d);
			} else {
				dto.setCount_BuyFH(list_BuyFH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_BuyFH(list_BuyFH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 换货卖家收货
			List<Orderdetail> list_MJSH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货商家待收货
							.getValue()).collect(Collectors.toList());
			if (list_MJSH == null || list_MJSH.size() == 0) {
				dto.setCount_MJSH(0);
				dto.setMoney_MJSH(0d);
			} else {
				dto.setCount_MJSH(list_MJSH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_MJSH(list_MJSH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 换货卖家发货
			List<Orderdetail> list_MJFH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货商家待发货
							.getValue()).collect(Collectors.toList());
			if (list_MJFH == null || list_MJFH.size() == 0) {
				dto.setCount_MJFH(0);
				dto.setMoney_MJFH(0d);
			} else {
				dto.setCount_MJFH(list_MJFH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_MJFH(list_MJFH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}
			// 换货买家收货
			List<Orderdetail> list_BuySH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货买家待收货
							.getValue()).collect(Collectors.toList());
			if (list_BuySH == null || list_BuySH.size() == 0) {
				dto.setCount_BuySH(0);
				dto.setMoney_BuySH(0d);
			} else {
				dto.setCount_BuySH(list_BuySH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_BuySH(list_BuySH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
			// 已换货
			/*List<Orderdetail> list_HH = detaiList
					.stream()
					.filter(x -> x.getStatus() == OrderDetailStatusEnum.换货成功
							.getValue()).collect(Collectors.toList());
			if (list_HH == null || list_HH.size() == 0) {
				dto.setCount_HH(0);
				dto.setMoney_HH(0d);
			} else {
				dto.setCount_HH(list_HH
						.stream()
						.collect(Collectors.groupingBy(Orderdetail::getOrderid))
						.size());
				dto.setMoney_HH(list_HH.stream()
						.mapToDouble(x -> x.getProductprice().doubleValue())
						.sum());
			}*/
		} else {
			dto.setCount_HH(0);
			dto.setMoney_HH(0d);
			dto.setCount_MJFH(0);
			dto.setMoney_MJFH(0d);
			dto.setCount_MJSH(0);
			dto.setMoney_MJSH(0d);
			dto.setCount_HHSQ(0);
			dto.setMoney_HHSQ(0d);
			dto.setCount_BuyFH(0);
			dto.setMoney_BuyFH(0d);
			dto.setCount_BuySH(0);
			dto.setMoney_BuySH(0d);
		}
		return dto;
	}

	@Override
	public PageBean getSaleProsPage(CriteriaSaleProduct criteria, Integer page,
			Integer size) throws Exception {
		PageBean pageBean = new PageBean();
		List<V_saleproducts> list = new ArrayList<V_saleproducts>();
		V_saleproducts vSale = null;
		PageBeanUtil uBeanUtil = new PageBeanUtil(criteria, null, null);
		List<V_saleproducts> listAll = orderdetailMapper.getSalePros(uBeanUtil);
		if (listAll != null && listAll.size() > 0) {		
			Map<String, List<V_saleproducts>> numList = listAll.stream()
					.collect(Collectors.groupingBy(V_saleproducts::getProductnum));
			if(criteria.getSites()!=null && criteria.getSites()>0){
				// 按天统计
				if (criteria.getTjtype() ==1) {
					for (Map.Entry<String, List<V_saleproducts>> entry : numList
							.entrySet()) {
						List<V_saleproducts> progroup = entry.getValue();
					// 根据天统计
					Map<Date, List<V_saleproducts>> dateList = progroup.stream()
							.collect(Collectors.groupingBy(V_saleproducts::getOrderdate));
					for (Map.Entry<Date, List<V_saleproducts>> entrydate : dateList
							.entrySet()) {
						List<V_saleproducts> detaildategroup = entry.getValue();
						// 根据站点统计
						Map<Integer, List<V_saleproducts>> siteList = detaildategroup.stream()
								.collect(Collectors.groupingBy(V_saleproducts::getWebsites));
						for (Map.Entry<Integer, List<V_saleproducts>> entrysite : siteList
								.entrySet()) {
							List<V_saleproducts> detailsitegroup = entrydate.getValue();
							vSale=new V_saleproducts();
							vSale.setOrderdate(entrydate.getKey());
							vSale.setProductnum(entry.getKey());
							vSale.setWebsites(entrysite.getKey());
							vSale.setProductname(progroup.get(0).getProductname());
							vSale.setProductnum(progroup.get(0).getProductnum());
							vSale.setProductprice(detailsitegroup.get(0).getProductprice());
//							vSale.setShopname(progroup.get(0).getShopname());
//							vSale.setShopid(progroup.get(0).getShopid());
							if(detailsitegroup==null || detailsitegroup.size()==0){
								break;
							}else{
								vSale.setTotalcount(detailsitegroup.stream().mapToInt(x->x.getTotalcount()).sum());
								vSale.setTotalmoney(detailsitegroup.stream().mapToDouble(x->x.getTotalmoney()).sum());
							}
							list.add(vSale);
						}
						
					}
					}
				}else{
					for (Map.Entry<String, List<V_saleproducts>> entry : numList
							.entrySet()) {
						List<V_saleproducts> detailprogroup = entry.getValue();
						
						if(detailprogroup==null || detailprogroup.size()==0){
							break;
						}else{
							// 根据站点统计
							Map<Integer, List<V_saleproducts>> siteList = detailprogroup.stream()
									.collect(Collectors.groupingBy(V_saleproducts::getWebsites));
							for (Map.Entry<Integer, List<V_saleproducts>> entrysite : siteList
									.entrySet()) {
								List<V_saleproducts> detailsitegroup = entrysite.getValue();
								vSale=new V_saleproducts();
							vSale.setProductnum(entry.getKey());
							vSale.setWebsites(entrysite.getKey());
							vSale.setProductname(detailprogroup.get(0).getProductname());
							vSale.setProductprice(detailsitegroup.get(0).getProductprice());
//							vSale.setShopname(detailprogroup.get(0).getShopname());
//							vSale.setShopid(detailprogroup.get(0).getShopid());
							vSale.setTotalcount(detailsitegroup.stream().mapToInt(x->x.getTotalcount()).sum());
							vSale.setTotalmoney(detailsitegroup.stream().mapToDouble(x->x.getTotalmoney()).sum());
							}
						}
						list.add(vSale);
					}
				}
			}
			else{
				for (Map.Entry<String, List<V_saleproducts>> entry : numList
					.entrySet()) {
					List<V_saleproducts> progroup = entry.getValue();
					if (criteria.getTjtype() ==1) {
						Map<Date, List<V_saleproducts>> dateList = progroup.stream()
								.collect(Collectors.groupingBy(V_saleproducts::getOrderdate));
						for (Map.Entry<Date, List<V_saleproducts>> entrydate : dateList
								.entrySet()) {
							List<V_saleproducts> dategroup = entrydate.getValue();
							vSale=new V_saleproducts();
							vSale.setProductnum(entry.getKey());
							vSale.setOrderdate(entrydate.getKey());
							vSale.setProductname(progroup.get(0).getProductname());
							vSale.setTotalcount(dategroup.stream().mapToInt(x->x.getTotalcount()).sum());
							vSale.setTotalmoney(dategroup.stream().mapToDouble(x->x.getTotalmoney()).sum());
							list.add(vSale);
						}
					}else{
						vSale=new V_saleproducts();
						vSale.setProductnum(entry.getKey());
						vSale.setProductname(progroup.get(0).getProductname());
						vSale.setTotalcount(progroup.stream().mapToInt(x->x.getTotalcount()).sum());
						vSale.setTotalmoney(progroup.stream().mapToDouble(x->x.getTotalmoney()).sum());
						list.add(vSale);
					}
					
				}				
			}
				if (criteria.getTjtype() == 1) {
					list = list
							.stream()
							.sorted((p1, p2) -> p2.getOrderdate().compareTo(
									p1.getOrderdate())).collect(Collectors.toList());
				}
				int totalcount = list.size(); // 列表总数
				int totalpage = totalcount % size == 0 ? totalcount / size
						: (totalcount / size) + 1; // 总页数
				if (page == totalpage) {
					list = list.stream().skip((page - 1) * size)
							.limit(totalcount % size).collect(Collectors.toList());
				} else {
					list = list.stream().skip((page - 1) * size).limit(size)
							.collect(Collectors.toList());
				}
				pageBean.setTr(totalcount);
		}else{
			pageBean.setTr(0);
		}
		pageBean.setBeanList(list);
		pageBean.setPc(page);
		return pageBean;
	}

	@Override
	public String getProCode(String groupcode) throws Exception {
		String str = "";
		List<Orders> orderList = ordersMapper.getOrderByGroupCode(groupcode);
		if(orderList != null && !orderList.isEmpty()){
			for(Orders order : orderList){
				List<Orderdetail> orderdetails = orderdetailMapper.getDetailsByOrderID(order.getId());
				if(orderdetails != null && !orderdetails.isEmpty()){
					for(Orderdetail orderdetail : orderdetails){
						str += orderdetail.getProductname()+":"+orderdetail.getProcode()+",";
					}
				}
			}
		}
		return str;
	}

	@Override
	public int delOrderDetail(Integer _orderid, Integer userid)
			throws Exception {
		Orderdetail od= orderdetailMapper.selectByPrimaryKey(_orderid);
		if(od==null)return 0;
		List<Orderdetail> odlist=orderdetailMapper.getDetailsByOrderID(od.getOrderid());
		if(odlist!=null && odlist.size()==1){
			Orders o=ordersMapper.getOrderByID(odlist.get(0).getOrderid());
			if(o!=null){
				o.setUserdeltime(new Date());
				o.setUserisdel(1);
				ordersMapper.updateByPrimaryKey(o);
			}
		}
		od.setVaildflag(1);
		od.setDeluserid(userid);
		od.setDeldate(new Date());
		return orderdetailMapper.updateByPrimaryKey(od);
	}
}

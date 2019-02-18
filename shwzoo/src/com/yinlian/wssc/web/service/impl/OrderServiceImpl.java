package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.ChangeTableTypeEnum;
import com.yinlian.Enums.Och_Status;
import com.yinlian.Enums.Och_Type;
import com.yinlian.Enums.OrderDetailStatusEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Enums.PayTypeEnum;
import com.yinlian.Enums.PointsRecordsFromTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.api.app.dto.Api_GroupOrderDto;
import com.yinlian.api.app.dto.Api_ListOrderDto;
import com.yinlian.api.app.dto.Api_OrderDetaiBaselDto;
import com.yinlian.api.app.dto.ApplyAfterDto;
import com.yinlian.api.app.dto.OderPayMerchantInfoDto;
import com.yinlian.api.app.dto.OrderPayDto;
import com.yinlian.wssc.search.Api_OrderCriteria;
import com.yinlian.wssc.search.P_OrderListCriteria;
import com.yinlian.wssc.web.dto.OrderDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.FinancerecordsMapper;
import com.yinlian.wssc.web.mapper.GroupbuyorderMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.SkuMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.mapper.UsercapitalMapper;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Financerecords;
import com.yinlian.wssc.web.po.OrderUpdaterecords;
import com.yinlian.wssc.web.po.OrderactivityChildHistory;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.OrdersExample;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaOrder;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;

public class OrderServiceImpl extends OrderServiceBaseImpl {
	@Autowired
	private UsercapitalMapper usercapitalMapper;

	@Autowired
	private UserfinanceMapper userfinanceMapper;

	@Autowired
	private FinancerecordsMapper financerecordsMapper;

	@Autowired
	private GroupbuyorderMapper groupbuyorderMapper;

	@Autowired
	private ShopMapper shopMapper;

	@Autowired
	private SkuMapper skuMapper;

	@Autowired
	private SpuMapper spuMapper;

	@Override
	public int delbyId(int orderID, int userid) throws Exception {

		Orders order = ordersMapper.getOrderByID(orderID);
		List<Orderdetail> details = orderdetailMapper
				.getDetailsByOrderID(orderID);
		if (order != null && details != null) {
			order.setValidflag(1);
			order.setDeldate(new Date());
			order.setDeluserid(userid);
			ordersMapper.updateByPrimaryKey(order);
			for (Orderdetail record : details) {
				record.setVaildflag(1);
				record.setDeldate(new Date());
				record.setDeluserid(userid);
				orderdetailMapper.updateByPrimaryKey(record);
			}
			return 1;
		} else {
			throw new Exception("空指针异常，未能根据orderID获取到对象！");
		}
	}

	@Override
	public Orders getOrderByID(int orderID) throws Exception {
		return ordersMapper.getOrderByID(orderID);
	}

	@Override
	public List<Orderdetail> getDetailsByOrderID(int orderID) throws Exception {
		List<Orderdetail> orderdetails = orderdetailMapper
				.getDetailsByOrderID(orderID);
		for (Orderdetail orderdetail : orderdetails) {
			Sku sku = skuMapper.selectByPrimaryKey(orderdetail.getSkuId());
			if (sku != null) {
				Spu spu = spuMapper.selectByPrimaryKey(sku.getSpuId());
				if (spu != null) {
					orderdetail.setProductimg(spu.getImgurl());
				}
			}
		}
		return orderdetails;
	}

	@Override
	public List<Orders> getOrderByGroupCode(String groupCode) throws Exception {
		return ordersMapper.getOrderByGroupCode(groupCode);
	}

	@Override
	public Orderdetail getDetailByID(int detailID) throws Exception {
		return orderdetailMapper.selectByPrimaryKey(detailID);
	}

	@Override
	public boolean updatePayForBalance(String groupCode, Double totalMoney,
			String ip, SessionUser user, String[] desc) throws Exception {

		desc[0] = "";
		List<Orders> orders = getOrderByGroupCode(groupCode);
		if (orders == null | orders.size() == 0
				| orders.get(0).getStatus() != OrderStatusEnum.待付款.getValue()) {
			desc[0] = "订单信息或订单组编号错误";
			return false;
		}
		double[] allmoney = new double[1];
		orders.forEach(x -> allmoney[0] = x.getPrice().doubleValue()
				- x.getDiscount().doubleValue() + x.getFreight().doubleValue());
		if (totalMoney != allmoney[0]) {
			desc[0] = "订单支付金额错误";
			return false;
		}
		String infoStr = "";
		List<Object> odrernummoney = new ArrayList<Object>();
		double payMoney = 0;
		for (Orders order : orders) {
			payMoney = order.getPrice().doubleValue()
					- order.getDiscount().doubleValue()
					+ order.getFreight().doubleValue();
			infoStr += "sellerid:" + order.getSellerid() + ",money:" + payMoney
					+ ",num:" + order.getCode() + ";";
			// 修改订单信息
			order.setActualpay(BigDecimal.valueOf(payMoney));
			order.setStatus(OrderStatusEnum.待使用.getValue());
			order.setPaytype(PayTypeEnum.余额支付.getValue());
			order.setPaydate(new Date());
			odrernummoney.add(new Object[] { order.getCode(), order.getId(),
					order.getActualpay() });
			// 修改商品信息
			List<Orderdetail> details = getDetailsByOrderID(order.getId());
			List<Integer> ids = new ArrayList<Integer>();
			boolean isMarket = false;
			Hashtable<Integer, Integer> Orderdetaildic = new Hashtable<Integer, Integer>();
			for (Orderdetail x : details) {
				if (x.getMarketid() != null && x.getMarketid() > 0) {
					isMarket = true;
				}
				ids.add(x.getSkuId());
				Orderdetaildic.put(x.getSkuId(), x.getProductcount());
			}
			List<Sku> skus = skuService.getListByIds(ids);
			for (Sku sku : skus) {
				if (!Orderdetaildic.containsKey(sku.getId())) {
					continue;
				}
				sku.setSalescount(sku.getSalescount()
						+ Orderdetaildic.get(sku.getId()));
				skuService.updateSku(sku);
			}
			// 订单活动处理
			if ((order.getActivityid() != null && order.getActivityid() > 0)
					|| isMarket) {
				List<OrderactivityChildHistory> hises = orderactivityChildHistoryService
						.getListByOrderCode(order.getCode());
				if (hises != null && hises.size() > 0) {

					int points = 0;
					ids.clear();
					for (OrderactivityChildHistory his : hises) {
						switch (Och_Type.values()[his.getType()]) {
						case 优惠券:
							ids.add(his.getCouponid());
							his.setStatus(Och_Status.已增送.getValue());
							break;
						case 积分:
							points += his.getPoint();
							his.setStatus(Och_Status.已增送.getValue());
							break;
						default:
							break;
						}
						orderactivityChildHistoryService.updateStatusById(
								his.getId(), his.getStatus());
						if (ids.size() > 0) {
							couponService.addUserCouponList(ids,
									order.getBuyerid());
							// 异步批量赠送优惠券
						}
						if (points > 0) {
							// 异步处理积分
							userService.updatePoint(order.getBuyerid(), points,
									PointsRecordsTypeEnum.增加,
									PointsRecordsFromTypeEnum.活动增送);
						}
					}
				}
			}
		}
		infoStr = infoStr.substring(0, infoStr.length() - 1);
		int code = PayForOrderByBalance(infoStr, orders.get(0).getBuyerid(),
				totalMoney, groupCode, ip, desc);
		if (code < 0) {
			return false;
		} else {
			desc[0] = "卖家/买家资金财务处理成功!";
			// 订单状态修改添加记录 付款
			addOrderRecords("Status",
					String.valueOf(OrderStatusEnum.待付款.getValue()),
					String.valueOf(OrderStatusEnum.待使用.getValue()), orders,
					null, user, ip);
			for (int i = 0; i < odrernummoney.size(); i++) {
				Object[] obj = (Object[]) odrernummoney.get(i);
				orderactivityChildHistoryService.updatePayMoney(
						String.valueOf(obj[0]), (int) obj[1], (float) obj[2]);// 更新活动优惠记录表
			}
			return true;
		}
	}

	@Override
	public int addOrderRecords(String Filed, String OldValue, String NewValue,
			List<Orders> orders, Orders order, SessionUser user, String userip)
			throws Exception {
		OrderUpdaterecords ou = null;
		// 批量修改状态 暂时只用在订单结算付款的操作
		String strOldValue = "";
		switch (OldValue) {
		case "0":
			strOldValue = String.valueOf(OrderStatusEnum.待付款.getValue());
			break;
		case "1":
			strOldValue = String.valueOf(OrderStatusEnum.出票中.getValue());
			break;
		case "2":
			strOldValue = String.valueOf(OrderStatusEnum.待使用.getValue());
			break;
		case "3":
			strOldValue = String.valueOf(OrderStatusEnum.审核中.getValue());
			break;
		case "4":
			strOldValue = String.valueOf(OrderStatusEnum.已取消.getValue());
			break;
		default:
			strOldValue = OldValue;
			break;
		}
		if (orders != null) {
			for (Orders od : orders) {
				ou = new OrderUpdaterecords();
				ou.setField(Filed); // 更新字段
				ou.setOldvalue(strOldValue); // 原始值
				ou.setNewvalue(NewValue); // 更新值
				ou.setOrderid(od.getId()); // 订单ID
				ou.setCreateuserid(user.getId()); // 操作人ID
				ou.setCreateusername(user.getName()); // 操作人用户名
				ou.setCreatetime(new Date());
				ou.setIp(userip); // 操作人IP
				if (orderUpdaterecordsService.addOrderRecords(ou) > 0) {
					logger.info(MessageFormat.format("订单状态修改成功,由" + OldValue
							+ "到" + NewValue + ",订单号：{0},操作人：{1},操作时间：{2}",
							od.getCode(), user.getId(), new Date()));
				} else {
					logger.warn(MessageFormat.format("订单状态修改失败,由" + OldValue
							+ "到" + NewValue + ",订单号：{0},操作人：{1},操作时间：{2}",
							od.getCode(), user.getId(), new Date()));
				}
			}
		}
		// 单个修改状态
		if (order != null) {
			ou = new OrderUpdaterecords();
			ou.setField(Filed); // 更新字段
			ou.setOldvalue(strOldValue); // 原始值
			ou.setNewvalue(NewValue); // 更新值
			ou.setOrderid(order.getId()); // 订单ID
			ou.setCreateuserid(user.getId()); // 操作人ID
			ou.setCreateusername(user.getName()); // 操作人用户名
			ou.setCreatetime(new Date());
			ou.setIp(userip); // 操作人IP
			if (orderUpdaterecordsService.addOrderRecords(ou) > 0) {
				logger.info(MessageFormat.format("订单状态修改成功,由" + OldValue + "到"
						+ NewValue + ",订单号：{0},操作人：{1},操作时间：{2}",
						order.getCode(), user.getId(), new Date()));
			} else {
				logger.warn(MessageFormat.format("订单状态修改失败,由" + OldValue + "到"
						+ NewValue + ",订单号：{0},操作人：{1},操作时间：{2}",
						order.getCode(), user.getId(), new Date()));
			}
		}
		return 0;
	}

	/**
	 * 订单余额支付买家卖家资金财务处理
	 * 
	 * @param orderinfo
	 *            示例:"sellerid:x1,money:x2,num:x3;sellerid:y1,money:y2,num:y3"
	 * @param buyerid
	 *            买家id
	 * @param totalMoney
	 *            支付总金额
	 * @param groupNum
	 *            订单组编号
	 * @param desc
	 * @return
	 * @throws Exception
	 */
	int PayForOrderByBalance(String orderinfo, int userid, Double totalMoney,
			String groupNum, String userip, String[] desc) throws Exception {

		Usercapital uc = userService.getBalanceRowLockById(userid);
		if (uc == null) {
			desc[0] = "用户余额不足";
			logger.error(MessageFormat.format("订单余额支付错误，错误详情：{0}", desc[0]));
			return -401;
		}
		double buyer_balance = uc.getBalance();
		if (buyer_balance < totalMoney) {
			desc[0] = "用户余额不足";
			logger.error(MessageFormat.format("订单余额支付错误，错误详情：{0}", desc[0]));
			return -401;
		}
		buyer_balance -= totalMoney;
		uc.setBalance(buyer_balance);
		userService.UsercapitalById(uc);
		Userfinance uf = new Userfinance();
		uf.setBalance(buyer_balance);
		uf.setDescription("订单余额支付买家扣款");
		uf.setUserid(userid);
		uf.setPaynum("");
		uf.setCreatetime(new Date());
		uf.setType(CapitalChange_Type.支出.getValue());
		uf.setNumber(groupNum);
		uf.setStatus(UserFinance_Type.已支付.getValue());
		uf.setUserip(userip);
		uf.setMoney(totalMoney);
		userService.addUserFinance(uf);

		Financerecords urs = new Financerecords();
		urs.setCreatetime(new Date());
		urs.setStatus(0);
		urs.setType(ChangeTableTypeEnum.修改.getValue());
		urs.setUsercapitalid(uc.getId());
		urs.setUserid(userid);
		urs.setUserip(userip);
		userService.addFinancerecords(urs);

		// #region 卖家资金财务处理
		String[] info = orderinfo.split(";");
		for (int i = 0; i < info.length; i++) {
			int sellerid = Integer
					.parseInt(info[i].split(",")[0].split(":")[1]);
			double money = Double.valueOf(info[i].split(",")[1].split(":")[1]);
			String num = info[i].split(",")[2].split(":")[1];
			uc = userService.getBalanceRowLockById(sellerid);
			if (uc == null) {
				desc[0] = "未查询到卖家用户资金数据";
				logger.error(MessageFormat.format("订单付款未查询到卖家资金数据，错误详情：{0}",
						desc[0]));
				return -402;
			}
			double freeze = uc.getFreezemoney();
			double balance = uc.getBalance();
			freeze += money;
			uc.setFreezemoney(freeze);
			userService.UsercapitalById(uc);

			uf = new Userfinance();
			uf.setBalance(balance);
			uf.setDescription("订单余额支付卖家冻结金额增加");
			uf.setUserid(sellerid);
			uf.setPaynum("");
			uf.setCreatetime(new Date());
			uf.setType(CapitalChange_Type.冻结金额增加.getValue());
			uf.setNumber(num);
			uf.setStatus(UserFinance_Type.已支付.getValue());
			uf.setUserip(userip);
			uf.setMoney(money);
			userService.addUserFinance(uf);

			urs = new Financerecords();
			urs.setCreatetime(new Date());
			urs.setStatus(0);
			urs.setType(ChangeTableTypeEnum.修改.getValue());
			urs.setUsercapitalid(uc.getId());
			urs.setUserid(sellerid);
			urs.setUserip(userip);
			userService.addFinancerecords(urs);
		}
		// #endregion
		logger.info("订单余额支付买家卖家财务处理成功");
		return 0;
	}

	/**
	 * 修改用户冻结金额
	 * 
	 * @param userid
	 *            用户ID
	 * @param money
	 *            金额
	 * @param num
	 *            订单编号
	 * @param isAdd
	 *            是否添加冻结金额
	 * @param userip
	 *            用户IP
	 * @return
	 * @throws Exception
	 */
	int ChangeFreezeMoney(int userid, double money, String num, Boolean isAdd,
			String userip, String[] desc) throws Exception {
		Usercapital uc = userService.getBalanceRowLockById(userid);
		if (uc == null) {
			desc[0] = "未查询到用户资金数据";
			logger.error(MessageFormat.format("改用户冻结金额错误，详情：{0}", desc[0]));
			return -401;
		}
		double freeze = uc.getFreezemoney();
		double balance = uc.getBalance();
		freeze = isAdd ? (freeze + money) : (freeze - money);
		if (freeze < 0) {
			desc[0] = "用户冻结金额不足";
			logger.error(MessageFormat.format("修改用户冻结金额错误，详情：{0}", desc[0]));
			return -402;
		}
		uc.setFreezemoney(freeze);
		userService.UsercapitalById(uc);

		Userfinance uf = new Userfinance();
		uf.setBalance(balance);
		uf.setDescription("订单余额支付买家扣款");
		uf.setUserid(userid);
		uf.setPaynum("");
		uf.setCreatetime(new Date());
		uf.setType(isAdd ? CapitalChange_Type.冻结金额增加.getValue()
				: CapitalChange_Type.冻结金额扣除.getValue());
		uf.setNumber(num);
		uf.setStatus(UserFinance_Type.已支付.getValue());
		uf.setUserip(userip);
		uf.setMoney(money);
		userService.addUserFinance(uf);

		Financerecords urs = new Financerecords();
		urs.setCreatetime(new Date());
		urs.setStatus(0);
		urs.setType(ChangeTableTypeEnum.修改.getValue());
		urs.setUsercapitalid(uc.getId());
		urs.setUserid(userid);
		urs.setUserip(userip);
		userService.addFinancerecords(urs);
		return 0;
	}

	/**
	 * 在线支付写入财务记录
	 * 
	 * @param userID
	 *            用户ID
	 * @param money
	 *            金额
	 * @param type
	 *            资金操作类型
	 * @param num
	 *            订单号或者交易号
	 * @param status
	 *            状态
	 * @param userip
	 *            客户端IP
	 * @param desc
	 *            备注
	 * @return
	 * @throws Exception
	 */
	int FinanceAdd(int userID, double money, CapitalChange_Type type,
			String num, int status, String userip, String desc)
			throws Exception {
		Userfinance last = userService.getLastUserFinance(userID);
		Userfinance finance = new Userfinance();
		finance.setBalance(last == null ? 0 : last.getBalance());
		finance.setUserid(userID);
		finance.setMoney(money);
		finance.setType(type.getValue());
		finance.setNumber(num);
		finance.setCreatetime(new Date());
		finance.setStatus(status);
		finance.setUserip(userip);
		finance.setDescription(desc);
		userService.addUserFinance(finance);
		return 0;
	}

	@Override
	public boolean updatePayForOrderSuccess(int orderID, PayTypeEnum payType,
			float money, int couponID, String userip, String[] desc)
			throws Exception {
		desc[0] = "";
		Orders order = getOrderByID(orderID);
		List<Orderdetail> details = getDetailsByOrderID(orderID);
		List<Integer> ids = new ArrayList<Integer>();
		Hashtable<Integer, Integer> Orderdetaildic = new Hashtable<Integer, Integer>();
		for (Orderdetail x : details) {
			ids.add(x.getSkuId());
			Orderdetaildic.put(x.getSkuId(), x.getProductcount());
		}
		List<Sku> skus = skuService.getListByIds(ids);
		order.setPaytype(payType.getValue());
		switch (payType) {
		case 在线支付:
			// 支付金额验证
			if (money != (order.getPrice().floatValue() - order.getDiscount()
					.floatValue())) {
				desc[0] = "支付金额错误";
				logger.error(MessageFormat.format("订单在线支付完成处理错误！错误详情:{0}",
						desc[0]));
				return false;
			}
			// 用户资金财务处理

			if (ChangeFreezeMoney(order.getSellerid(), money, order.getCode(),
					true, userip, desc) < 0) {
				return false;
			}
			if (FinanceAdd(order.getBuyerid(), money, CapitalChange_Type.支出,
					order.getCode(), 0, userip, "订单在线支付") < 0) {
				return false;
			}

			// 修改订单信息
			order.setActualpay(BigDecimal.valueOf(money));
			order.setStatus(OrderStatusEnum.待使用.getValue());
			order.setPaytype(PayTypeEnum.在线支付.getValue());
			order.setPaydate(new Date());
			orderactivityChildHistoryService.updatePayMoney(order.getCode(),
					order.getId(), order.getActualpay().floatValue());// 更新活动优惠记录表
			// 修改商品信息
			for (Sku sku : skus) {
				if (!Orderdetaildic.containsKey(sku.getId())) {
					continue;
				}
				sku.setSalescount(sku.getSalescount()
						+ Orderdetaildic.get(sku.getId()));
				skuService.updateSku(sku);
			}
			break;
		case 余额支付:
			break;
		case 优惠券支付:
			break;
		case 混合支付:
			break;
		case 货到付款:
			break;
		}
		return true;
	}

	// @Override
	// public boolean updateSendProForOrder(int orderID, String logisticsName,
	// String logisticsCode, String ip,
	// SessionUser user,Orders order ) throws Exception {
	//
	// order = getOrderByID(orderID);
	// if (order == null || (order.getStatus() != OrderStatusEnum.待发货.getValue()
	// && order.getStatus() != OrderStatusEnum.换货待发货.getValue()))
	// return false;
	// order.setStatus(order.getStatus() == OrderStatusEnum.待发货.getValue() ?
	// OrderStatusEnum.待收货.getValue()
	// : OrderStatusEnum.换货待收货.getValue());
	// order.setLogisticsname(logisticsName);
	// order.setLogisticscode(logisticsCode);
	// order.setDeliverdate(new Date());
	// if (ordersMapper.updateByPrimaryKey(order) > 0) {
	// // 订单状态修改添加记录 发货
	// if (order.getStatus() == OrderStatusEnum.待收货.getValue()) {
	// addOrderRecords("Status", String.valueOf(OrderStatusEnum.待发货.getValue()),
	// String.valueOf(OrderStatusEnum.待收货.getValue()), null, order, user, ip);
	// } else {
	// // 订单状态修改添加记录 换货待发货
	// addOrderRecords("Status",
	// String.valueOf(OrderStatusEnum.换货待发货.getValue()),
	// String.valueOf(OrderStatusEnum.换货待收货.getValue()), null, order, user, ip);
	// }
	// return true;
	// } else {
	// return false;
	// }
	// }
	//
	// @Override
	// public boolean updateCancelOrder(int orderID, String reason, String img,
	// String ip, SessionUser user, String[] desc)
	// throws Exception {
	// desc[0] = "";
	// Orders order = getOrderByID(orderID);
	// if (order == null) {
	// desc[0] = "订单不存在";
	// return false;
	// }
	// if (order.getStatus() == OrderStatusEnum.待付款.getValue()) {
	// order.setStatus(OrderStatusEnum.已取消.getValue());
	// List<Orderdetail> details = getDetailsByOrderID(orderID);
	// List<Integer> ids = new ArrayList<Integer>();
	// Hashtable<Integer, Integer> Orderdetaildic = new Hashtable<Integer,
	// Integer>();
	// for (Orderdetail x : details) {
	// ids.add(x.getSkuId());
	// Orderdetaildic.put(x.getSkuId(), x.getProductcount());
	// }
	// List<Sku> skus = skuService.getListByIds(ids);
	// for (Sku sku : skus) {
	// if (!Orderdetaildic.containsKey(sku.getId())) {
	// continue;
	// }
	// sku.setSalescount(sku.getSalescount() + Orderdetaildic.get(sku.getId()));
	// skuService.updateSku(sku);
	// }
	//
	// // TODO 订单取消活动
	// if (ordersMapper.updateByPrimaryKey(order) > 0) {
	//
	// // 订单状态修改添加记录 确认收货
	// addOrderRecords("Status", String.valueOf(OrderStatusEnum.待付款.getValue()),
	// String.valueOf(OrderStatusEnum.已取消.getValue()), null, order, user, ip);
	// return true;
	// } else {
	// return false;
	// }
	// } else if (order.getStatus() == OrderStatusEnum.待发货.getValue()
	// || order.getStatus() == OrderStatusEnum.待收货.getValue()) {
	// int oldStaus = order.getStatus();
	// order.setStatus(OrderStatusEnum.取消申请中.getValue());
	//
	// order.setCancelreason(reason);
	// Applyforcancelorder afco = new Applyforcancelorder();
	// afco.setOrderid(order.getId());
	// afco.setUserid(order.getBuyerid());
	// afco.setContent(reason);
	// afco.setCreatetime(new Date());
	// afco.setImgurl(img);
	// afco.setType(OrderApplyTypeEnum.订单取消.getValue());
	// afco.setOrderstatus(oldStaus); // 记录
	// // return Dal.UpdateAndAddAFCO(afco, db);
	// if (applyforcancelorderService.add(afco) > 0) {
	// // 订单状态修改添加记录 确认收货
	// addOrderRecords("Status", String.valueOf(oldStaus),
	// String.valueOf(OrderStatusEnum.取消申请中.getValue()),
	// null, order, user, ip);
	//
	// return true;
	// } else {
	// return false;
	// }
	// } else {
	// desc[0] = "订单不可以取消";
	// return false;
	// }
	// }
	//
	// @Override
	// public boolean updateConfirmReceivePro(int orderID, SessionUser user,
	// String ip, String[] desc) throws Exception {
	// desc[0] = "";
	// Orders order = getOrderByID(orderID);
	// if (order == null) {
	// desc[0] = "订单不存在";
	// return false;
	// }
	// if (order.getStatus() != OrderStatusEnum.待收货.getValue()
	// && order.getStatus() != OrderStatusEnum.换货待收货.getValue()) {
	// desc[0] = "订单状态错误";
	// return false;
	// }
	// if (order.getStatus() == OrderStatusEnum.待收货.getValue()) {
	// List<Userfinance> finances =
	// userFinanceService.getListByNumber(order.getCode());
	// Userfinance finance = null;
	// for (Userfinance w : finances) {
	// if (w.getUserid() == order.getSellerid() && w.getType() ==
	// CapitalChange_Type.冻结金额增加.getValue()) {
	// finance = w;
	// break;
	// }
	// }
	//
	// if (thawCapital(finance.getId(), order.getSellerid(), ip, desc) < 0) {
	// return false;
	// }
	//
	// // 给上级用户佣金返利处理
	// // Accounts acc = AccountsService.GetByUserID(order.BuyerID);
	// // if (acc != null && acc.FatherID > 0)
	// // {
	// // string val =
	// // ConfigDictionaryService.GetByType(ConfigSetTypeEnum.佣金返利).Value;
	// // decimal money = decimal.Parse(val) / 100;
	// // Task.Factory.StartNew(() =>
	// // client.AddCommissionForOrder(acc.FatherID.Value, money,
	// // order.GroupCode, PublicClass.GetUserIp));
	// // }
	//
	// order.setStatus(OrderStatusEnum.已确认收货.getValue());
	// } else {
	// order.setStatus(OrderStatusEnum.换货确认收货.getValue());
	// }
	//
	// order.setDeliverconfirmdate(new Date());
	// List<Orderdetail> details = getDetailsByOrderID(order.getId());
	// details.forEach(w -> w.setStatus(OrderDetailStatusEnum.订单完成.getValue()));
	// //// 添加订单换货完成时间
	// // details.ForEach(w => w.FinishTime = DateTime.Now);
	// // return Dal.Update(db);
	// ordersMapper.updateByPrimaryKey(order);
	// for (Orderdetail orderdetail : details) {
	// orderdetailMapper.updateByPrimaryKey(orderdetail);
	// }
	// if (order.getStatus() == OrderStatusEnum.已确认收货.getValue()) {
	// // 订单状态修改添加记录 确认收货
	// addOrderRecords("Status", String.valueOf(OrderStatusEnum.待收货.getValue()),
	// String.valueOf(OrderStatusEnum.已确认收货.getValue()), null, order, user, ip);
	// } else {
	// // 订单状态修改添加记录 换货确认收货
	// addOrderRecords("Status",
	// String.valueOf(OrderStatusEnum.换货待收货.getValue()),
	// String.valueOf(OrderStatusEnum.换货确认收货.getValue()), null, order, user,
	// ip);
	// }
	// return true;
	//
	// }

	int thawCapital(int financeID, int userID, String userip, String[] Desc)
			throws Exception {
		Usercapital uc = userService.getBalanceRowLockById(userID);
		if (uc == null) {
			Desc[0] = "未查询到用户资金数据";
			logger.error(MessageFormat.format("用户资金冻结错误，详情：{0}", Desc[0]));
			return -401;
		}
		double freeze = uc.getFreezemoney();
		double balance = uc.getBalance();
		double money = 0;
		if (financeID > 0) {
			Userfinance userfinance = userFinanceService.getUserFinance(
					UserFinance_Type.已支付.getValue(), financeID);

			if (userfinance == null) {
				Desc[0] = "financeID错误";
				logger.error(MessageFormat
						.format("financeID错误，详情：{0}", Desc[0]));
				return -402;
			}
			money = userfinance.getMoney();
		} else {
			money = freeze;
		}
		freeze -= money;
		balance += money;
		if (freeze < 0) {
			Desc[0] = "余额不足";
			logger.error(MessageFormat.format("用户资金冻结错误，详情：{0}", Desc[0]));
			return -403;
		}
		uc.setBalance(uc.getBalance() + balance);
		uc.setFreezemoney(uc.getFreezemoney() + freeze);
		userService.UsercapitalById(uc);
		Userfinance uf = new Userfinance();
		uf.setBalance(balance);
		uf.setDescription("订单余额支付买家扣款");
		uf.setUserid(userID);
		uf.setPaynum("");
		uf.setCreatetime(new Date());
		uf.setType(CapitalChange_Type.解冻.getValue());
		uf.setNumber("");
		uf.setStatus(UserFinance_Type.已支付.getValue());
		uf.setUserip(userip);
		uf.setMoney(money);
		userService.addUserFinance(uf);

		Financerecords urs = new Financerecords();
		urs.setCreatetime(new Date());
		urs.setStatus(0);
		urs.setType(ChangeTableTypeEnum.修改.getValue());
		urs.setUsercapitalid(uc.getId());
		urs.setUserid(userID);
		urs.setUserip(userip);
		userService.addFinancerecords(urs);
		return 0;
	}

	@Override
	public PageBean getP_OrdersList(int index, int pageRow,
			P_OrderListCriteria criteria) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, pageRow);// 还可以
																				// 设置其他的参数
																				// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<OrderDto> beanList = ordersMapper.getP_OrdersList(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public PageBean getUserListOrder(int page, int size,
			Api_OrderCriteria criteria) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Api_ListOrderDto> beanList = new ArrayList<Api_ListOrderDto>();
		if(criteria.getStatus()!=null && criteria.getStatus().equals(OrderStatusEnum.待付款.getValue())){
			beanList=ordersMapper.getUserListOrderNopayNoCountPage(pageBeanUtil);
		}else{
			beanList = ordersMapper.getUserListOrderNoCountPage(pageBeanUtil);
		}
		if(beanList.size()>0){
			List<String> sellerids = beanList.stream().map(Api_ListOrderDto::getShopname)
					.collect(Collectors.toList());// 内部遍历
			List<Integer> shopids= beanList.stream().map(Api_ListOrderDto::getShopid)
					.collect(Collectors.toList());// 内部遍历
			String[] b = sellerids.toArray(new String[sellerids.size()]);
			List<Shop> sList = shopMapper.getShopListByIds(String.join(",", b));
			List<Shop> sZooList = shopMapper.getZooScenicByIds(shopids);	
			List<String> ostrlist = new ArrayList<String>();
			for (Api_ListOrderDto order : beanList) {
				ostrlist.add(String.valueOf(order.getId()));
			}
			b = ostrlist.toArray(new String[ostrlist.size()]);
	    	List<Api_OrderDetaiBaselDto> cList=	ordersMapper.getUserListOrderDetailsByids(String.join(",", b));
			for (Api_ListOrderDto dto : beanList) {
				Integer sellerid=StringUtilsEX.ToInt(dto.getShopname());
				dto.setShopname(sList.stream().filter(x -> x.getUserid().equals(sellerid)).findFirst().get().getName());
				dto.setShoptype(sList.stream().filter(x -> x.getUserid().equals(sellerid)).findFirst().get().getShoptype());
				Optional<Shop> zoos=sZooList.stream().filter(x -> x.getId().equals(dto.getShopid())).findFirst();
				if(zoos.isPresent()){
					dto.setLatitude(zoos.get().getShopnum());
					dto.setLongitude(zoos.get().getName());
				}
				dto.setLsitdetais(cList.stream().filter(y->y.getOrderid()==dto.getId()).collect(Collectors.toList()));
			}
		}
		// 根据组编号分组
		List<Api_GroupOrderDto> grouplist = new ArrayList<Api_GroupOrderDto>();
		if (beanList == null || beanList.size() == 0) {
			pageBean.setBeanList(grouplist);
			return pageBean;
		}
		// 根据组编号分组
		Map<String, List<Api_ListOrderDto>> GroupcodeList = beanList.stream()
				.collect(Collectors.groupingBy(Api_ListOrderDto::getGroupcode));
		// 遍历
		for (Map.Entry<String, List<Api_ListOrderDto>> entry : GroupcodeList
				.entrySet()) {

			List<Api_ListOrderDto> entrylist = entry.getValue();
			// 查询同一组订单下各订单状态
			List<Integer> statuss = entrylist.stream().map((x) -> {
				return x.getStatus();
			}).distinct().collect(Collectors.toList());
			if (statuss.size() == 1 && statuss.get(0).equals(OrderStatusEnum.待付款.getValue())) {
//				for (Api_ListOrderDto dto : entrylist) {
//					for (Api_OrderDetaiBaselDto dto2 : dto.getLsitdetais()) {
//						Spu spu = spuMapper.selectByPrimaryKey(dto2.getSpuid());
//						if (spu != null) {
//							dto2.setProductimg(spu.getImgurl());
//						}
//					}
//				}
				Api_GroupOrderDto gDto = new Api_GroupOrderDto();
				gDto.setGcode(entry.getKey());
				gDto.setOrderdate(entrylist.get(0).getAddorderdate());
				gDto.setStatus(entrylist.get(0).getStatus());
				gDto.setOrderlist(entrylist);
				grouplist.add(gDto);
			} else {
				for (Api_ListOrderDto odto : entrylist) {
//					for (Api_OrderDetaiBaselDto dto2 : odto.getLsitdetais()) {
//						Spu spu = spuMapper.selectByPrimaryKey(dto2.getSpuid());
//						if (spu != null) {
//							dto2.setProductimg(spu.getImgurl());
//						}
//					}
					Api_GroupOrderDto gDto = new Api_GroupOrderDto();
					gDto.setGcode(entry.getKey());
					gDto.setOrderdate(odto.getAddorderdate());
					gDto.setStatus(odto.getStatus());
					List<Api_ListOrderDto> oList = new ArrayList<Api_ListOrderDto>();
					oList.add(odto);
					gDto.setOrderlist(oList);
					grouplist.add(gDto);
				}
			}
		}
		if (grouplist != null) {
			grouplist = grouplist.stream()
					.sorted((p1, p2) -> p2.getOrderdate().compareTo(
							p1.getOrderdate())).collect(Collectors.toList());
		}
		pageBean.setBeanList(grouplist);
		return pageBean;
	}

	@Override
	public PageBean getSHOrderList(Integer page1, Integer size11,
			Api_OrderCriteria aoc) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(aoc, page1, size11);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Api_ListOrderDto> beanList = new ArrayList<Api_ListOrderDto>();
		List<Api_GroupOrderDto> grouplist = new ArrayList<Api_GroupOrderDto>();
		beanList = ordersMapper.getSHOrderListNoCountPage(pageBeanUtil);
		if (beanList == null || beanList.size() == 0) {
			pageBean.setBeanList(grouplist);
			return pageBean;
		}
		List<String> ostrlist = new ArrayList<String>();
		for (Api_ListOrderDto order : beanList) {
			ostrlist.add(String.valueOf(order.getId()));
		}
		String[] b = ostrlist.toArray(new String[ostrlist.size()]);
    	List<Api_OrderDetaiBaselDto> cList=	ordersMapper.getUserListOrderDetailsByids(String.join(",", b));
		for (Api_ListOrderDto dto : beanList) {
			dto.setLsitdetais(cList.stream().filter(y->y.getOrderid()==dto.getId()).collect(Collectors.toList()));
		}
		// 根据组编号分组
		Map<String, List<Api_ListOrderDto>> GroupcodeList = beanList.stream()
				.collect(Collectors.groupingBy(Api_ListOrderDto::getGroupcode));
		// 遍历
		for (Map.Entry<String, List<Api_ListOrderDto>> entry : GroupcodeList
				.entrySet()) {
			List<Api_ListOrderDto> entrylist = entry.getValue();
			for (Api_ListOrderDto odto : entrylist) {
//				for (Api_OrderDetaiBaselDto dto2 : odto.getLsitdetais()) {
//					Spu spu = spuMapper.selectByPrimaryKey(dto2.getSpuid());
//					if (spu != null) {
//						dto2.setProductimg(spu.getImgurl());
//					}
//				}
				Api_GroupOrderDto gDto = new Api_GroupOrderDto();
				gDto.setGcode(entry.getKey());
				if (odto.getApplydate() == null) {
					gDto.setOrderdate(odto.getAddorderdate());
				} else {
					gDto.setOrderdate(odto.getApplydate());
				}
				gDto.setStatus(odto.getStatus());
				List<Api_ListOrderDto> oList = new ArrayList<Api_ListOrderDto>();
				oList.add(odto);
				gDto.setOrderlist(oList);
				grouplist.add(gDto);
			}
		}
		if (grouplist != null) {
			grouplist = grouplist
					.stream()
					.sorted((p1, p2) -> p2.getOrderdate().compareTo(
							p1.getOrderdate())).collect(Collectors.toList());
		}
		pageBean.setBeanList(grouplist);
		return pageBean;
	}

	public PageBean getOrdersByPage(CriteriaOrder criteria, int page, int size)
			throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pBeanUtil.getPage();
		List<OrderDto> list = new ArrayList<OrderDto>();
		if(criteria.getDetailstatus()!=null && criteria.getDetailstatus()>0){
			list=ordersMapper.getOrdersByDetailPage(pBeanUtil);
		}else{
			list=ordersMapper.getOrdersByPage(pBeanUtil);
		}
		if(list.size()==0){
			pageBean.setBeanList(list);
			return pageBean;
		}
		
		List<String> buyerids = list.stream().map(OrderDto::getBuyerName)
				.collect(Collectors.toList());// 内部遍历
		List<String> sellerids = list.stream().map(OrderDto::getShopName)
				.collect(Collectors.toList());// 内部遍历
		String[] b = buyerids.toArray(new String[buyerids.size()]);
		List<Users> uList = userService.getUsersListByIds(String.join(",", b));
		b = sellerids.toArray(new String[sellerids.size()]);
		List<Shop> sList = shopMapper.getShopListByIds(String.join(",", b));

		
		List<String> ostrlist = new ArrayList<String>();
		for (OrderDto order : list) {
			ostrlist.add(String.valueOf(order.getId()));
		}
		b = ostrlist.toArray(new String[ostrlist.size()]);
	List<Orderdetail> cList=	ordersMapper.selectDetailsByIds(String.join(",", b));
	list.forEach(x -> {
		x.setBuyerName(uList.stream()
				.filter(y -> y.getId().equals(x.getBuyerID())).findFirst()
				.get().getUsername());
		x.setShopName(sList.stream()
				.filter(y -> y.getUserid().equals(x.getSellerID()))
				.findFirst().get().getName());	
		x.setChildren(cList.stream().filter(y->y.getOrderid().equals(x.getId())).collect(Collectors.toList()));
	});
		pageBean.setBeanList(list);
		return pageBean;
	}

	public PageBean getAfterOrdersByPage(Criteria criteria, int page, int size)
			throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pBeanUtil.getPage();
		List<OrderDto> list = ordersMapper.getAfterOrdersByPage(pBeanUtil);
		pageBean.setBeanList(list);
		return pageBean;
	}

	/**
	 * @see com.yinlian.wssc.web.service.impl.OrderServiceBaseImpl#selectByStatus(java.lang.Integer)
	 */
	@Override
	public List<Orders> selectByStatus(Integer status) throws Exception {
		OrdersExample example = new OrdersExample();
		OrdersExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(status);
		return ordersMapper.selectByExample(example);
	}

	/**
	 * @see com.yinlian.wssc.web.service.impl.OrderServiceBaseImpl#updateByGroupCode(java.lang.String)
	 */
	@Override
	public void updateByGroupCode(String orderGroup, String money)
			throws Exception {
		super.updateByGroupCode(orderGroup, money);
	}

	@Override
	public OrderPayDto orderPay(String num) throws Exception {
		OrderPayDto dto = new OrderPayDto();
		List<Orders> orderslist = ordersMapper.getOrderByGroupCode(num);
		List<Orders> list = orderslist.stream()
				.filter(x -> x.getStatus() == OrderStatusEnum.待付款.getValue())
				.collect(Collectors.toList());
		if (list.size() <= 0)
			throw new Exception("订单编号不存在！");
		dto.setOrderNo(num);
		dto.getBeanNumber();
		double paytotal = 0.0;
		List<OderPayMerchantInfoDto> mList = new ArrayList<OderPayMerchantInfoDto>();
		int Beans = 0;
		DecimalFormat df = new DecimalFormat("######0");
		for (Orders orders : list) {
			if (StringUtils.isNotNull(orders.getVouchercode()))
				dto.setVoucherCode(orders.getVouchercode());
			if (orders.getBeans() != null)
				Beans += orders.getBeans();
			OderPayMerchantInfoDto dto2 = new OderPayMerchantInfoDto();
			Accounts accounts = accountsService.queryByuserid(orders
					.getSellerid());
			dto2.setMerchantName(accounts.getLoginname());
			double pay = orders.getActualpay().doubleValue() * 100;
			paytotal += pay;
			if (StringUtils.isNotNull(orders.getVouchercode())) {
				if (orders.getPcoupon() != null)
					pay += orders.getPcoupon();
				if (orders.getScoupon() != null)
					pay += orders.getScoupon();
			}
			if (orders.getBeans() != null)
				pay += orders.getBeans();
			dto2.setReceivable(df.format(pay));
			mList.add(dto2);
		}
		dto.setBeanNumber(String.valueOf(Beans));
		dto.setMerchantList(mList);
		dto.setOrderType("0");
		dto.setPayMoney(df.format(paytotal));

		return dto;
	}

}

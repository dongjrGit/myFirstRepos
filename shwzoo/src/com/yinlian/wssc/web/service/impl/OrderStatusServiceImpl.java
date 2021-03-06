package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.AfterServiceStatusEnum;
import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.ChangeTableTypeEnum;
import com.yinlian.Enums.CompleteStatus;
import com.yinlian.Enums.CouponTypeEnum;
import com.yinlian.Enums.FinanceType;
import com.yinlian.Enums.ImageTypeEnum;
import com.yinlian.Enums.MessagesTypeEnum;
import com.yinlian.Enums.Och_Type;
import com.yinlian.Enums.OrderApplyTypeEnum;
import com.yinlian.Enums.OrderDetailStatusEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Enums.PayTypeEnum;
import com.yinlian.Enums.PointRuleEnum;
import com.yinlian.Enums.PointsRecordsFromTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.Enums.TemplateTagEnum;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ApplyAfterDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CookieDto;
import com.yinlian.app.alipay.AlipayReturnUtil;
import com.yinlian.tenpay.PaymentUtil;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.mapper.AfterserviceMapper;
import com.yinlian.wssc.web.mapper.ApplyforcancelorderMapper;
import com.yinlian.wssc.web.mapper.CouponMapper;
import com.yinlian.wssc.web.mapper.FinancerecordsMapper;
import com.yinlian.wssc.web.mapper.IdcardinfoMapper;
import com.yinlian.wssc.web.mapper.ImagesMapper;
import com.yinlian.wssc.web.mapper.MessagesMapper;
import com.yinlian.wssc.web.mapper.OrderactivityChildHistoryMapper;
import com.yinlian.wssc.web.mapper.OrderactivityHistoryMapper;
import com.yinlian.wssc.web.mapper.OrderdetailMapper;
import com.yinlian.wssc.web.mapper.OrdersMapper;
import com.yinlian.wssc.web.mapper.ReceiveaddressMapper;
import com.yinlian.wssc.web.mapper.ReceiveinfoMapper;
import com.yinlian.wssc.web.mapper.SkuMapper;
import com.yinlian.wssc.web.mapper.SkuShowtimeMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.mapper.UsercapitalMapper;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.po.Afterservice;
import com.yinlian.wssc.web.po.Applyforcancelorder;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.Financerecords;
import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.po.OrderactivityChildHistory;
import com.yinlian.wssc.web.po.OrderactivityHistory;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Receiveaddress;
import com.yinlian.wssc.web.po.Receiveinfo;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.service.OrderUpdaterecordsService;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SmsUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import cryptix.jce.provider.cipher.Null;
import ticket.xml.utils.XmlFuncUtils;
import ticket.xml.utils.XmlUint;

@Component("orderStatusService")
public class OrderStatusServiceImpl implements OrderStatusService {

	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderdetailMapper orderdetailMapper;
	@Autowired
	private ApplyforcancelorderMapper applyfororderMapper;
	@Autowired
	private SkuMapper skuMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UsercapitalMapper usercapitalMapper;
	@Autowired
	private FinancerecordsMapper financerecordsMapper;
	@Autowired
	private UserfinanceMapper userfinanceMapper;
	@Autowired
	private SpuMapper spuMapper;

	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private PointsRecordService pointsRecordService;

	@Autowired
	private OrderactivityChildHistoryMapper orderactivityChildHistoryMapper;
	@Autowired
	private OrderactivityHistoryMapper orderactivityHistoryMapper;
	@Autowired
	private AfterserviceMapper afterserviceMapper;
	@Autowired
	private ImagesMapper imagesMapper;
	@Autowired
	private CouponService couponService;
	@Autowired
	private ReceiveinfoMapper receiveinfoMapper;
	@Autowired
	private ReceiveaddressMapper receiveaddressMapper;	
	
	@Autowired
	protected FreightService freightService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected MessagesMapper messagesMapper;
	@Autowired
	protected UserslevelService userslevelService;

	@Autowired
	private OrderUpdaterecordsService orderUpdaterecordsService;
	
	@Autowired
	private IdcardinfoMapper idcardinfoMapper;
	
	
	@Autowired
	private SkuShowtimeMapper   skuShowtimeMapper;

	@Override
	public int updateCancelOrder(String groupcode, int userid, String reason) throws Exception {
		List<Orders> ordersList = ordersMapper.getOrderByGroupCode(groupcode);
		if (ordersList == null || ordersList.isEmpty()) {
			LogHandle.error(LogType.Order, "根据组订单编号获取订单信息失败，groupcode:" + groupcode, "/order/updateCancelOrder");
			return 0;
		}
		for (Orders orders : ordersList) {
			if (orders.getStatus() != OrderStatusEnum.待付款.getValue()) {
				LogHandle.error(LogType.Order, "当前状态不允许取消，订单id:" + orders.getId() + ",状态：" + orders.getStatus(),
						"/order/updateCancelOrder");
				return 0;
			}
			int newstatus = 0;
			// 订单申请取消记录
			Applyforcancelorder apply = new Applyforcancelorder();
			apply.setCreatetime(new Date());
			apply.setContent(reason);
			apply.setOrderid(orders.getId());
			apply.setOrderstatus(orders.getStatus());
			apply.setUserid(userid);
			apply.setOrdernum(orders.getCode());
			apply.setType(OrderApplyTypeEnum.订单未付款取消.getValue());
			newstatus = OrderStatusEnum.已取消.getValue();
			/* Orders order = orderService.queryByID(orders.getId());
			if (order != null) {
				apply.setOrdernum(order.getCode());
			}
			if (orders.getStatus() == OrderStatusEnum.待付款.getValue()) {
				apply.setType(OrderApplyTypeEnum.订单未付款取消.getValue());
				newstatus = OrderStatusEnum.已取消.getValue();
			} 
				 * else if (status == OrderStatusEnum.待使用.getValue()) {
				 * apply.setType(OrderApplyTypeEnum.订单已付款取消.getValue());
				 * newstatus = OrderStatusEnum.审核中.getValue(); }
				 */
			applyfororderMapper.insert(apply);
			// 订单表信息修改
			orders.setStatus(newstatus);
			orders.setCancelreason(reason);
			ordersMapper.updateByPrimaryKey(orders);
			orderdetailMapper.cancelOrder(orders.getId(),newstatus);
		}
		int couponid=0;
		for (Orders ors : ordersList) {
			//如果使用优惠券则退回
			if(ors.getCouponid()!=null && ors.getCouponid()>0){
				if(couponid==ors.getCouponid())break;
				couponid=ors.getCouponid();
				List<Integer> couponids=new ArrayList<Integer>();
				couponids.add(ors.getCouponid());
				couponService.updateUCouponCancel(couponids, ors.getBuyerid());
			}
        }
		return 1;
	}
	
	@Override
	public int updateCheckCancel(int id, int status, int userid, int agreetype, String reason, Orders[] orders,
			String username, String imgUrl) throws Exception {
		orders[0] = ordersMapper.getOrderByID(id);
		// 订单申请审核记录
		Applyforcancelorder applyfor = new Applyforcancelorder();
		if (orders[0] == null) {
			LogHandle.error(LogType.Order, "根据订单ID获取订单信息失败，id:" + id, "/order/updateCheckCancel");
			return 0;
		}
		if (orders[0].getStatus() != status) {
			LogHandle.error(LogType.Order, "订单状态不一致，订单id:" + id + ",状态：" + orders[0].getStatus(),
					"/order/updateCheckCancel");
			return 0;
		}
		int newstatus = 0, applytype = 0;
		// 同意取消
		if (agreetype == 1) {
			if (status == OrderStatusEnum.审核中.getValue()) {
				newstatus = OrderStatusEnum.已取消.getValue();
				applytype = OrderApplyTypeEnum.订单未付款取消.getValue();

			} else {
				newstatus = OrderStatusEnum.已取消.getValue();
				applytype = OrderApplyTypeEnum.订单已付款取消.getValue();

				// 退款

				// 用户资金处理
				Userfinance finance = userfinanceMapper.getListByNumberAndType(orders[0].getCode(),
						CapitalChange_Type.冻结金额增加.getValue());
				if (finance == null) {
					LogHandle.error(LogType.Order, "根据订单获取用户资金失败，id:" + id, "/order/updateCheckCancel");
				}
				if (finance.getUserid() == orders[0].getSellerid()) {
					// 卖家资金处理
					changeFreezeMoney(orders[0].getSellerid(),
							orders[0].getActualpay().subtract(orders[0].getFreight()).doubleValue(),
							orders[0].getCode(), "订单取消，卖家冻结金额扣除", "", finance.getPaynum(), false);
					// 买家资金处理
					changeUserCapital(orders[0].getBuyerid(),
							orders[0].getActualpay().subtract(orders[0].getFreight()).doubleValue(),
							orders[0].getCode(), CapitalChange_Type.收入.getValue(), finance.getPaynum(), "订单取消，买家资金退回",
							"");
				}
			}
			// 退回优惠劵
			if (orders[0].getCouponid() != null && orders[0].getCouponid() > 0) {
				List<Integer> cidlist = new ArrayList<Integer>();
				cidlist.add(orders[0].getCouponid());
				couponService.updateUCouponCancel(cidlist, orders[0].getBuyerid());
			}
			// 库存还原
			List<Orderdetail> detailList = orderdetailMapper.getDetailsByOrderID(id);
			List<Integer> idlist = new ArrayList<Integer>();
			for (Orderdetail de : detailList) {
				idlist.add(de.getSkuId());
			}
			if (idlist.size() > 0) {
				List<Sku> skuList = skuMapper.getListByIds(idlist);
				for (Sku sku : skuList) {
					Optional<Orderdetail> detail = detailList.stream().filter(x -> x.getSkuId().equals(sku.getId()))
							.findFirst();
					if (detail.isPresent()) {
						sku.setStock(sku.getStock() + detail.get().getProductcount());
					}

				}
				skuMapper.updateStockList(skuList);
			}
			applyfor.setContent("同意");
			// 退还经豆
			if (orders[0].getBeans() != null) {
				// Users users =
				// usersMapper.getRowLockById(orders[0].getBuyerid());
				// Integer beans = users.getAvailablebeans();
				// users.setAvailablebeans(beans + orders[0].getBeans());
				// usersMapper.updateByPrimaryKey(users);
				// //经豆处理
				// //财务历史记录
				// Userfinance userfinance = new Userfinance();
				// userfinance.setUserid(orders[0].getBuyerid());
				// userfinance.setCreatetime(new Date());
				// userfinance.setBalance(beans.floatValue());
				// userfinance.setMoney(orders[0].getBeans().floatValue());
				// userfinance.setDescription("订单取消，经豆退还买家");
				// userfinance.setPaynum("");
				// userfinance.setUserip("");
				// userfinance.setType(CapitalChange_Type.收入.getValue());
				// userfinance.setNumber(orders[0].getCode());
				// userfinance.setStatus(UserFinance_Type.已支付.getValue());
				// userfinance.setFinancetype(FinanceType.经采豆.getValue());
				// userfinanceMapper.insert(userfinance);
				// //经豆历史记录
				// Historybeans historybeans = new Historybeans();
				// historybeans.setBeans(orders[0].getBeans());
				// historybeans.setCreattime(new Date());
				// historybeans.setUserid(users.getId());
				// historybeans.setUsername(users.getUsername());
				// historybeansMapper.insert(historybeans);
			}
		} else {
			// 不同意取消订单
			if (status == OrderStatusEnum.审核中.getValue()) {
				newstatus = OrderStatusEnum.待付款.getValue();
				applytype = OrderApplyTypeEnum.订单未付款取消.getValue();
			} else {
				newstatus = OrderStatusEnum.待使用.getValue();
				applytype = OrderApplyTypeEnum.订单已付款取消.getValue();
			}
			// Applyforcancelorder applycancel =
			// applyfororderMapper.getbyOrderAndType(id, applytype);
			// if (applycancel != null) {
			// newstatus = applycancel.getOrderstatus();
			// }
			applyfor.setContent(reason);
		}
		applyfor.setCreatetime(new Date());
		applyfor.setOrderid(orders[0].getId());
		applyfor.setOrdernum(orders[0].getCode());
		applyfor.setUserid(userid);
		applyfor.setOrderstatus(status);
		applyfor.setType(applytype);
		applyfor.setImgurl(imgUrl);
		applyfororderMapper.insert(applyfor);
		// 订单活动处理
		List<OrderactivityChildHistory> hislist = orderactivityChildHistoryMapper
				.getListByOrderCode(orders[0].getCode());
		int points = 0;
		// List<Integer> couids = new ArrayList<Integer>();
		for (OrderactivityChildHistory his : hislist) {
			switch (Och_Type.valueOf(his.getType())) {
			case 优惠券:
				float price = 0.0f;
				Coupon coupon = couponMapper.selectByPrimaryKey(his.getCouponid());
				if (coupon.getCoupontype() == CouponTypeEnum.金额限制.getValue())
					price = coupon.getFullreductionvalue();
				couponService.UseCoupon(his.getCouponid(), orders[0].getBuyerid(), price, "订单取消扣除优惠券");
				// couids.add(his.getCouponid());
				break;
			case 积分:
				points += his.getPoint();
				break;
			default:
				break;
			}
		}
		if (points > 0) {

			pointsRecordService.updateUserPoint(points, orders[0].getBuyerid(),
					PointsRecordsFromTypeEnum.活动增送.getValue(), PointsRecordsTypeEnum.消费.getValue());
		}
		return ordersMapper.updateStatus(newstatus, id);
	}

	/*
	 * @Override （中绿订单售后流程修改，产品如需要时请恢复注释） public int updateSendProForOrder(int
	 * orderID,String orderdetailid, String logisticsName, String logisticsCode,
	 * String ip, SessionUser user, Orders order) throws Exception { Orders
	 * orders=null; if(StringUtilsEX.ToInt(orderdetailid)>0){ Orderdetail ore =
	 * orderdetailService.queryByID(StringUtilsEX.ToInt(orderdetailid)); orders
	 * =ordersMapper.getOrderByID(ore.getOrderid());
	 * orderdetailMapper.updateStatus(OrderDetailStatusEnum.换货买家待收货.getValue(),
	 * ore.getId()); Afterservice
	 * after=afterServicemapper.getByOrderAndType(orders.getId(),2,
	 * AfterServiceStatusEnum.商家待发货.getValue()); if(after!=null){
	 * after.setbExpressname(logisticsName);
	 * after.setbExpressname(logisticsCode);
	 * afterServicemapper.updatebExpress(after); } }else{ orders
	 * =ordersMapper.getOrderByID(orderID); Afterservice
	 * after=afterServicemapper.getByOrderAndType(orderID,2,
	 * AfterServiceStatusEnum.商家待发货.getValue()); if(after!=null){
	 * after.setbExpressname(logisticsName);
	 * after.setbExpressname(logisticsCode);
	 * afterServicemapper.updatebExpress(after);
	 * 
	 * } }
	 * 
	 * orders.setStatus(OrderStatusEnum.待收货.getValue());
	 * orders.setLogisticsname(logisticsName);
	 * orders.setLogisticscode(logisticsCode); orders.setDeliverdate(new
	 * Date()); int isupdate=ordersMapper.updateByPrimaryKey(orders); Orders
	 * ors=orders; if ( isupdate> 0) { ExecutorService cachedThreadPool =
	 * Executors.newCachedThreadPool(); cachedThreadPool.execute(new Runnable()
	 * {
	 * 
	 * @Override public void run() { try { // 订单状态修改添加记录 发货
	 * orderService.addOrderRecords("Status",
	 * String.valueOf(OrderStatusEnum.待发货.getValue()),
	 * String.valueOf(OrderStatusEnum.待收货.getValue()), null, ors, user, ip);
	 * 
	 * 
	 * // 卖家发货发送推送消息 orderService.sendMessage(ors.getBuyerid(),
	 * ors.getCode(),new Date() , logisticsName,logisticsCode
	 * ,3,0,TemplateTagEnum.卖家发货,MessagesTypeEnum.订单提醒);
	 * 
	 * // String[] messStrings = new String[1]; //
	 * sendMessage(orders.getBuyerid(), // TemplateTagEnum.卖家发货.getValue(),
	 * "卖家发货", // orders.getCode(), logisticsCode, logisticsName, //
	 * orders.getDeliverdate(), messStrings); // // Messages messages = new
	 * Messages(); // messages.setContent(messStrings[0]); //
	 * messages.setUserid(user.getUserId()); //
	 * messages.setUsername(user.getName()); // messages.setTitle("发货成功"); //
	 * messages.setType(MessagesTypeEnum.订单提醒.getValue()); //
	 * messages.setSendtime(new Date()); // messagesMapper.insert(messages); }
	 * catch (Exception exception) { LogHandle.error(LogType.jpush,
	 * "订单发货 发送推送信息错误：{0}", exception,
	 * "OrderStatusService/updateSendProForOrder"); } } }); return 1; } else {
	 * return 0; } }
	 */

	/***
	 * 订单发货处理
	 * 
	 * @author Fjh
	 * 
	 * @param orderID
	 *            订单id
	 * @param logisticsName
	 *            物流名称
	 * @param logisticsCode
	 *            物流单号
	 * @param ip
	 *            操作用户ip
	 * @param user
	 *            当前登录用户
	 * @return 1 处理成功 0处理失败
	 */
	@Override
	public int updateSendProForOrder(int orderID, String logisticsName, String logisticsCode, String ip,
			SessionUser user) throws Exception {
		Orders orders = null;
		orders = ordersMapper.getOrderByID(orderID);
		orders.setStatus(OrderStatusEnum.待使用.getValue());
		orders.setLogisticsname(logisticsName);
		orders.setLogisticscode(logisticsCode);
		orders.setDeliverdate(new Date());
		int isupdate = ordersMapper.updateByPrimaryKey(orders);
		Orders ors = orders;
		if (isupdate > 0) {
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						// 订单状态修改添加记录 发货
						// orderService.addOrderRecords("Status",
						// String.valueOf(OrderStatusEnum.待发货.getValue()),
						// String.valueOf(OrderStatusEnum.待收货.getValue()),
						// null, ors, user, ip);

						// 卖家发货发送推送消息
						orderService.sendMessage(ors.getBuyerid(), ors.getCode(), new Date(), logisticsName,
								logisticsCode, 3, 0, TemplateTagEnum.卖家发货, MessagesTypeEnum.订单提醒);

					} catch (Exception exception) {
						LogHandle.error(LogType.jpush, "订单发货 发送推送信息错误：{0}", exception,
								"OrderStatusService/updateSendProForOrder");
					}
				}
			});
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @see com.yinlian.wssc.web.service.OrderStatusService#gotoPay(int, int,
	 *      int)
	 */
	@Override
	public int updatePayforBalanceCode(String groupNum, int userid, String userip, BaseResult item) throws Exception {
		Usercapital usercapital = usercapitalMapper.getBalanceRowLockById(userid);
		if (usercapital == null) {
			item.setDesc("用户余额不足");
			LogHandle.error(LogType.Order, MessageFormat.format("订单余额支付错误，错误详情：{0}", item.getDesc()));
			return -401;
		}
		List<Orders> orderlist = orderService.getOrderByGroupCode(groupNum);
		if (orderlist == null) {
			item.setDesc("组订单号错误");
			LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", groupNum));
			return -403;
		}
		Double ss = orderlist.stream().mapToDouble(x -> x.getActualpay().doubleValue()).sum();
		double totalMoney = Double.parseDouble(ss.toString());
		double buyer_balance = usercapital.getBalance();
		if (buyer_balance < totalMoney) {
			item.setDesc("用户余额不足");
			LogHandle.error(LogType.Order, MessageFormat.format("订单余额支付错误，错误详情：{0}", item.getDesc()));
			return -402;
		}
		// List<Orders> orderlist = orderService.getOrderByGroupCode(groupNum);

		buyer_balance -= totalMoney;
		usercapital.setBalance(buyer_balance);
		usercapitalMapper.updateByPrimaryKey(usercapital);

		String paynum = "";

		financeAdd(userid, buyer_balance, totalMoney, "订单余额支付买家扣款", paynum, userip, CapitalChange_Type.支出.getValue(),
				groupNum, UserFinance_Type.已支付.getValue());

		// 用户资金更改记录表
		recordsAdd(userid, userip, ChangeTableTypeEnum.修改.getValue(), usercapital.getId());
		// 买家付款发送推送消息
		Date timeDate = new Date();
		orderService.sendMessage(userid, groupNum, timeDate, "", "", 3, 0, TemplateTagEnum.支付成功, MessagesTypeEnum.订单提醒);

		for (Orders orders : orderlist) {
			// 以下是卖家操作
			int sellerid = orders.getSellerid();// 卖家id
			Usercapital sellerUsercapital = usercapitalMapper.getBalanceRowLockById(sellerid);
			if (sellerUsercapital == null) {
				item.setDesc("未查询到卖家用户资金数据");
				LogHandle.error(LogType.Order, MessageFormat.format("订单付款未查询到卖家资金数据，错误详情：{0}", item.getDesc()));
				return -402;
			}
			sellerUsercapital.setFreezemoney(sellerUsercapital.getFreezemoney() + totalMoney);
			usercapitalMapper.updateByPrimaryKeySelective(sellerUsercapital); // 增加卖家的冻结资金
			// 资金变动记录
			financeAdd(sellerid, sellerUsercapital.getBalance(), totalMoney, "订单余额支付卖家冻结金额增加", paynum, userip,
					CapitalChange_Type.冻结金额增加.getValue(), orders.getCode(), UserFinance_Type.已支付.getValue());

			// 用户资金更改记录表
			recordsAdd(sellerid, userip, ChangeTableTypeEnum.修改.getValue(), sellerUsercapital.getId());

			orders.setPaytype(PayTypeEnum.余额支付.getValue());
			orders.setPaydate(new Date());
			orders.setStatus(OrderStatusEnum.待使用.getValue());
			ordersMapper.updateByPrimaryKey(orders);

			// 异步操作 不影响正常流程
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			SessionUser user = SessionState.GetCurrentUser();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						int res = userService.changePoints(userid, PointRuleEnum.消费.getValue(),
								new BigDecimal(totalMoney));
						if (res < 0) {
							LogHandle.error(LogType.Order,
									MessageFormat.format("订单余额支付按积分规则赠送用户积分出错! errorCode:{0}:", res),
									"/order/updatePayforBalanceCode");
						}
						orderUpdaterecordsService.addOrderUpadateRecords("Status",
								OrderStatusEnum.待付款.getValue().toString(), OrderStatusEnum.待使用.getValue().toString(),
								orders.getId(), user.getUserId(), user.getLoginName(), userip);
					} catch (Exception e) {
						LogHandle.error(LogType.OperateRecords, "确认收货操作记录出错! 异常信息:", e, "/pc/order/comfirmreceive");
					}

				}
			});

			List<Orderdetail> details = orderdetailMapper.getDetailsByOrderID(orders.getId());
			List<Integer> ids = new ArrayList<Integer>();
			Hashtable<Integer, Integer> Orderdetaildic = new Hashtable<Integer, Integer>();
			for (Orderdetail x : details) {
				ids.add(x.getSkuId());
				Orderdetaildic.put(x.getSkuId(), x.getProductcount());
			}
			List<Sku> skus = skuMapper.getListByIds(ids);
			for (Sku sku : skus) {
				if (!Orderdetaildic.containsKey(sku.getId())) {
					continue;
				}
				Integer sale = sku.getSalescount();
				if (sale != null) {
					sku.setSalescount(sale + Orderdetaildic.get(sku.getId()));
				} else {
					sku.setSalescount(Orderdetaildic.get(sku.getId()));
				}
				skuMapper.updateByPrimaryKey(sku);
			}
			// 订单参与活动处理
			if (orders.getActivityid() != null
					|| details.stream().filter(x -> x.getMarketid() != null).findFirst().isPresent()) {
				activityProcess(orders.getCode(), orders.getBuyerid());

			}
			// 买家付款发送推送消息 （给卖家的）
			orderService.sendMessage(sellerid, orders.getCode(), new Date(), "", "", 3, 0, TemplateTagEnum.支付成功,
					MessagesTypeEnum.订单提醒);

		}
		LogHandle.info(LogType.Order, "订单余额支付买家卖家财务处理成功");
		return 1;
	}

	/**
	 * 单个订单支付
	 * 
	 * @param orderid
	 * @param userid
	 * @param userip
	 * @param item
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updatePayforBalanceID(int orderid, int userid, String userip, BaseResult item) throws Exception {
		Usercapital usercapital = usercapitalMapper.getBalanceRowLockById(userid);
		Orders orders = orderService.queryByID(orderid);
		if (orders == null) {
			item.setDesc("订单ID错误");
			LogHandle.error(LogType.Order, MessageFormat.format("订单ID错误，订单ID：{0}", orderid));
			return -403;
		}
		if (orders.getStatus() != OrderStatusEnum.待付款.getValue()) {
			item.setDesc("订单状态不是待付款，不能支付");
			LogHandle.error(LogType.Order, MessageFormat.format("订单状态不是待付款，不能支付。订单ID：{0}", orderid));
			return -404;
		}
		if (usercapital == null) {
			item.setDesc("用户余额不足");
			LogHandle.error(LogType.Order, MessageFormat.format("订单余额支付错误，错误详情：{0}", item.getDesc()));
			return -401;
		}
		double buyer_balance = usercapital.getBalance();
		double totalMoney = Double.parseDouble(orders.getActualpay().toString());
		if (buyer_balance < totalMoney) {
			item.setDesc("用户余额不足");
			LogHandle.error(LogType.Order, MessageFormat.format("订单余额支付错误，错误详情：{0}", item.getDesc()));
			return -402;
		}
		buyer_balance -= totalMoney;
		usercapital.setBalance(buyer_balance);
		usercapitalMapper.updateByPrimaryKey(usercapital);

		String paynum = "";

		financeAdd(userid, buyer_balance, totalMoney, "订单余额支付买家扣款", paynum, userip, CapitalChange_Type.支出.getValue(),
				orders.getCode(), UserFinance_Type.已支付.getValue());

		// 用户资金更改记录表
		recordsAdd(userid, userip, ChangeTableTypeEnum.修改.getValue(), usercapital.getId());
		// 以下是卖家操作
		int sellerid = orders.getSellerid();// 卖家id
		Usercapital sellerUsercapital = usercapitalMapper.getBalanceRowLockById(sellerid);
		if (sellerUsercapital == null) {
			item.setDesc("未查询到卖家用户资金数据");
			LogHandle.error(LogType.Order, MessageFormat.format("订单付款未查询到卖家资金数据，错误详情：{0}", item.getDesc()));
			return -402;
		}
		sellerUsercapital.setFreezemoney(sellerUsercapital.getFreezemoney() + totalMoney);
		usercapitalMapper.updateByPrimaryKeySelective(sellerUsercapital); // 增加卖家的冻结资金
		// 资金变动记录
		financeAdd(sellerid, sellerUsercapital.getBalance(), totalMoney, "订单余额支付卖家冻结金额增加", paynum, userip,
				CapitalChange_Type.冻结金额增加.getValue(), orders.getCode(), UserFinance_Type.已支付.getValue());

		orders.setPaytype(PayTypeEnum.余额支付.getValue());
		orders.setPaydate(new Date());
		orders.setStatus(OrderStatusEnum.待使用.getValue());
		ordersMapper.updateByPrimaryKey(orders);
		SessionUser user = SessionState.GetCurrentUser();
		// 异步操作 不影响正常流程
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		cachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					// 订单支付按积分规则异步赠送用户积分
					int res = userService.changePoints(userid, PointRuleEnum.消费.getValue(), new BigDecimal(totalMoney));
					if (res < 0) {
						LogHandle.error(LogType.Order, MessageFormat.format("订单余额支付按积分规则赠送用户积分出错! errorCode:{0}:", res),
								"/order/updatePayforBalanceCode");
					}
					orderUpdaterecordsService.addOrderUpadateRecords("Status",
							OrderStatusEnum.待付款.getValue().toString(), OrderStatusEnum.待使用.getValue().toString(),
							orders.getId(), user.getUserId(), user.getLoginName(), userip);
				} catch (Exception e) {
					LogHandle.error(LogType.OperateRecords, "支付操作记录出错! 异常信息:", e, "/order/pay");
				}

			}
		});

		List<Orderdetail> details = orderdetailMapper.getDetailsByOrderID(orders.getId());
		List<Integer> ids = new ArrayList<Integer>();
		Hashtable<Integer, Integer> Orderdetaildic = new Hashtable<Integer, Integer>();
		for (Orderdetail x : details) {
			ids.add(x.getSkuId());
			Orderdetaildic.put(x.getSkuId(), x.getProductcount());
		}
		List<Sku> skus = skuMapper.getListByIds(ids);
		// List<Spu> spulist=spuMapper.getBySkuIDs(ids);
		for (Sku sku : skus) {
			if (!Orderdetaildic.containsKey(sku.getId())) {
				continue;
			}
			sku.setSalescount(sku.getSalescount() + Orderdetaildic.get(sku.getId()));
			skuMapper.updateByPrimaryKey(sku);
		}
		List<Sku> skulist = skuMapper.getListByIds(ids);
		// 根据spuid分组
		Map<Integer, List<Sku>> spuGroupList = skulist.stream().collect(Collectors.groupingBy(Sku::getSpuId));
		// 遍历购物车下店铺
		List<Spu> spulist = new ArrayList<Spu>();
		Spu spu = null;
		for (Map.Entry<Integer, List<Sku>> entry : spuGroupList.entrySet()) {
			spu = new Spu();
			List<Sku> value = entry.getValue();
			Integer scount = 0;
			for (Sku sku : value) {
				scount += sku.getSalescount();
			}
			spu.setId(entry.getKey());
			spu.setSalescount(scount);
			spulist.add(spu);
		}
		if (spulist.size() > 0) {
			spuMapper.updateSalesCount(spulist);
		}
		// 订单参与活动处理
		if (orders.getActivityid() != null
				|| details.stream().filter(x -> x.getMarketid() != null).findFirst().isPresent()) {
			activityProcess(orders.getCode(), orders.getBuyerid());
		}
		LogHandle.info(LogType.Order, "订单余额支付买家卖家财务处理成功");
		return 1;
	}

	/**
	 * 确认收货 资金变动
	 * 
	 * @param orderid
	 * @param user
	 * @param userip
	 * @return
	 */
	@Override
	public int updatecomfirmReceive(int orderid, SessionUser user, String userip) throws Exception {
		Orders orders = ordersMapper.getOrderByID(orderid);
		if (orders == null) {
			LogHandle.error(LogType.Order, "根据订单ID获取订单信息失败，orderid:" + orderid, "/order/updatecomfirmReceive");
			return 0;
		}
		// if (orders.getStatus() != OrderStatusEnum.待收货.getValue()) {
		// LogHandle.error(LogType.Order, "订单不是待收货状态，不能确认收货不。订单id:" + orderid,
		// "/order/updatecomfirmReceive");
		// return 0;
		// }
		// // 本地用户资金处理
		Userfinance finance = userfinanceMapper.getListByNumberAndType(orders.getCode(),
				CapitalChange_Type.冻结金额增加.getValue());
		if (finance == null) {
			LogHandle.error(LogType.Order, "根据订单获取用户资金失败，id:" + orderid, "/order/updatecomfirmReceive");
			return 0;
		}
		if (finance.getUserid().equals(orders.getSellerid())) {
			// 卖家资金处理
			freezeMoneyToBalance(finance.getId(), "确认收货，卖家解冻订单金额", orders.getSellerid(), userip);
		} else {
			return 0;
		}
		List<Orderdetail> details = orderdetailMapper.getDetailsByOrderID(orders.getId());
		List<Integer> ids = new ArrayList<Integer>();
		Hashtable<Integer, Integer> Orderdetaildic = new Hashtable<Integer, Integer>();
		for (Orderdetail x : details) {
			ids.add(x.getSkuId());
			Orderdetaildic.put(x.getSkuId(), x.getProductcount());
		}
		List<Sku> skus = skuMapper.getListByIds(ids);
		// List<Spu> spulist=spuMapper.getBySkuIDs(ids);
		for (Sku sku : skus) {
			if (!Orderdetaildic.containsKey(sku.getId())) {
				continue;
			}
			sku.setSalescount(sku.getSalescount() + Orderdetaildic.get(sku.getId()));
			skuMapper.updateByPrimaryKey(sku);
		}

		// orders.setStatus(OrderStatusEnum.已确认收货.getValue());
		orders.setDeliverconfirmdate(new Date());
		ordersMapper.updateByPrimaryKey(orders);
		details.forEach(w -> w.setStatus(OrderDetailStatusEnum.订单完成.getValue()));
		for (Orderdetail orderdetail : details) {
			orderdetailMapper.updateByPrimaryKey(orderdetail);
		}
		LogHandle.info(LogType.Order, "订单已确认收货成功");

		int orderActualpay = orders.getActualpay().intValue();
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		cachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					// 获取用户等级标准
					List<Userslevel> levellist = userslevelService.getlevelList();
					// 获取用户信息
					Users user1 = userService.selectByPrimaryKey(user.getUserId());
					if (user1 != null) {
						Users upUser = new Users();
						upUser.setId(user1.getId());
						upUser.setLevel(user1.getLevel());
						upUser.setLevelid(user1.getLevelid());
						if (user1.getConsumeamount() != null && user1.getConsumeamount() >= 0) {
							final int amount = orderActualpay + user1.getConsumeamount();
							if (levellist != null && levellist.size() > 0) {
								Optional<Userslevel> ul = levellist.stream().filter(x -> x.getUpstandard() >= amount)
										.sorted((p1, p2) -> p1.getUpstandard().compareTo(p1.getUpstandard()))
										.findFirst();
								if (!ul.get().getLevel().equals(upUser.getLevel())) {
									upUser.setLevel(ul.get().getLevel());
									upUser.setLevelid(ul.get().getId());
									userService.updateuserlevel(upUser);
								}
							}
							// 更新消费金额
							userService.updateConsumeAmount(amount, user1.getId());
						}
					}
					if (user != null && user.getId() > 0) {
						// 买家收货发送推送消息
						orderService.sendMessage(user.getUserId(), orders.getCode(), new Date(), "", "", 3, 0,
								TemplateTagEnum.买家收货, MessagesTypeEnum.订单提醒);
					}

					// 生成对账单
					// businessBillService.generateBills();

				} catch (Exception exception) {
					LogHandle.error(LogType.User, "更新用户消费信息错误：{0}", exception, "order/updatecomfirmReceive");
				}
			}
		});
		return 1;
	}

	private int recordsAdd(int userid, String userip, int type, int usercapitalid) throws Exception {
		Financerecords financerecords = new Financerecords();
		financerecords.setUserid(userid);
		financerecords.setUserip(userip);
		financerecords.setType(type);
		financerecords.setCreatetime(new Date());
		financerecords.setStatus(0);
		financerecords.setUsercapitalid(usercapitalid);
		return financerecordsMapper.insert(financerecords);
	}

	/**
	 * 用户冻结资金处理
	 * 
	 * @param sellerid
	 * @param money
	 * @param ordercode
	 * @param desc
	 * @param userip
	 * @param paynum
	 * @param isadd
	 * @param item
	 * @return
	 * @throws Exception
	 */
	private int changeFreezeMoney(int sellerid, double money, String ordercode, String desc, String userip,
			String paynum, boolean isadd) throws Exception {
		Usercapital usercapital = usercapitalMapper.getBalanceRowLockById(sellerid);
		if (usercapital == null) {
			LogHandle.error(LogType.UserCapital, "资金表无对应用户数据，用户ID：" + sellerid, "order/changeFreezeMoney");
			return 0;
		}
		if (isadd == false && usercapital.getBalance() < money && usercapital.getFreezemoney() < money) {
			LogHandle.error(LogType.UserCapital, "用户资金不足，用户ID：" + sellerid, "order/changeFreezeMoney");
			return 0;
		}
		double totalMoney = 0.0f;
		int type = 0;
		if (isadd) {
			usercapital.setFreezemoney(usercapital.getFreezemoney() + money);
			type = CapitalChange_Type.冻结金额增加.getValue();
		} else {
			type = CapitalChange_Type.冻结金额扣除.getValue();
			if (usercapital.getFreezemoney() > money) {
				usercapital.setFreezemoney(usercapital.getFreezemoney() - money);
			} else {
				usercapital.setBalance(usercapital.getBalance() - money);
			}
		}
		totalMoney = usercapital.getFreezemoney();
		usercapitalMapper.updateByPrimaryKey(usercapital);
		// 资金变动记录
		financeAdd(sellerid, usercapital.getFreezemoney(), totalMoney, desc, paynum, userip, type, ordercode,
				UserFinance_Type.已支付.getValue());

		// 用户资金更改记录表
		return recordsAdd(sellerid, "", ChangeTableTypeEnum.修改.getValue(), usercapital.getId());
	}

	/**
	 * 添加用户资金变更
	 * 
	 * @param userid
	 *            用户id
	 * @param balance
	 *            用户余额
	 * @param money
	 *            变更金额
	 * @param desc
	 *            说明
	 * @param paynum
	 *            流水号
	 * @param userip
	 *            客户端ip
	 * @param type
	 *            类型
	 * @param ordercode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	private int financeAdd(int userid, double balance, double money, String desc, String paynum, String userip,
			int type, String ordercode, int status) throws Exception {
		Userfinance userfinance = new Userfinance();
		userfinance.setUserid(userid);
		userfinance.setCreatetime(new Date());
		userfinance.setBalance(balance);
		userfinance.setMoney(money);
		userfinance.setDescription(desc);
		userfinance.setPaynum(paynum);
		userfinance.setUserip(userip);
		userfinance.setType(type);
		userfinance.setNumber(ordercode);
		userfinance.setStatus(status);
		userfinance.setFinancetype(FinanceType.金额.getValue());
		return userfinanceMapper.insert(userfinance);
	}

	public int financeAddFK(int userid, double balance, double money, String desc, String paynum, String userip,
			int type, String ordercode, int status, Integer paytype) throws Exception {
		Userfinance userfinance = new Userfinance();
		userfinance.setUserid(userid);
		userfinance.setCreatetime(new Date());
		userfinance.setBalance(balance);
		userfinance.setMoney(money);
		userfinance.setDescription(desc);
		userfinance.setPaynum(paynum);
		userfinance.setUserip(userip);
		userfinance.setType(type);
		userfinance.setNumber(ordercode);
		userfinance.setStatus(status);
		userfinance.setFinancetype(FinanceType.金额.getValue());
		userfinance.setPaytype(paytype);
		return userfinanceMapper.insert(userfinance);
	}

	/**
	 * 用户资金处理
	 * 
	 * @param userid
	 * @param money
	 * @param ordercode
	 * @param type
	 * @param paynum
	 * @param desc
	 * @param userip
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public int changeUserCapital(int userid, double money, String ordercode, int type, String paynum, String desc,
			String userip) throws Exception {
		Usercapital usercapital = usercapitalMapper.getBalanceRowLockById(userid);
		if (usercapital == null) {
			LogHandle.error(LogType.UserCapital, "资金表无对应用户数据，用户ID：" + userid, "order/changeUserCapital");
			return 0;
		}

		if (type == CapitalChange_Type.支出.getValue() && usercapital.getBalance() < money) {
			LogHandle.error(LogType.UserCapital, "用户资金不足，用户ID：" + userid, "order/changeUserCapital");
			return 0;
		}
		if (type == CapitalChange_Type.支出.getValue()) {
			usercapital.setBalance(usercapital.getBalance() - money);
		}
		if (type == CapitalChange_Type.收入.getValue()) {
			usercapital.setBalance(usercapital.getBalance() + money);
		}
		usercapitalMapper.updateByPrimaryKey(usercapital);
		// 资金变动记录
		financeAdd(userid, usercapital.getBalance(), money, desc, paynum, userip, type, ordercode,
				UserFinance_Type.已支付.getValue());

		// 用户资金更改记录表
		return recordsAdd(userid, userip, ChangeTableTypeEnum.修改.getValue(), usercapital.getId());
	}

	/**
	 * 冻结金额解冻-确认收货
	 * 
	 * @param financeid
	 * @param desc
	 * @param userid
	 * @param userip
	 * @return
	 * @throws Exception
	 */
	private int freezeMoneyToBalance(int financeid, String desc, int userid, String userip) throws Exception {
		Userfinance userfinance = new Userfinance();
		userfinance = userfinanceMapper.selectByPrimaryKey(financeid);
		if (userfinance == null) {
			LogHandle.error(LogType.UserCapital, "查询不到用户资金数据，ID：" + financeid, "order/comfirmReceive");
			return 0;
		}
		Usercapital usercapital = usercapitalMapper.getBalanceRowLockById(userid);
		if (usercapital == null) {
			LogHandle.error(LogType.UserCapital, "资金表无对应用户数据，用户ID：" + userid, "order/comfirmReceive");
			return 0;
		}
		double money = userfinance.getMoney();
		if (money < 0 || usercapital.getFreezemoney() < money) {
			LogHandle.error(LogType.UserCapital, "用户冻结资金数据错误，用户ID：" + userid, "order/comfirmReceive");
			return 0;
		}
		usercapital.setBalance(usercapital.getBalance() + money);
		usercapital.setFreezemoney(usercapital.getFreezemoney() - money);
		usercapitalMapper.updateByPrimaryKey(usercapital);
		// 资金变动记录
		financeAdd(userid, usercapital.getBalance(), money, desc, userfinance.getPaynum(), userip,
				CapitalChange_Type.解冻.getValue(), userfinance.getNumber(), UserFinance_Type.已支付.getValue());

		// 用户资金更改记录表
		return recordsAdd(userid, userip, ChangeTableTypeEnum.修改.getValue(), usercapital.getId());
	}

	/**
	 * 用户冻结金额调整
	 * 
	 * @param desc
	 * @param type
	 * @param money
	 * @param userid
	 * @param userip
	 * @return
	 * @throws Exception
	 */
	private int UserfreezeToBalance(String desc, int type, float money, int userid, String userip) throws Exception {
		Usercapital usercapital = usercapitalMapper.getBalanceRowLockById(userid);
		if (usercapital == null) {
			LogHandle.error(LogType.UserCapital, "资金表无对应用户数据，用户ID：" + userid);
			return 0;
		}
		if (type == CapitalChange_Type.冻结金额扣除.getValue() && usercapital.getFreezemoney() < money) {
			LogHandle.error(LogType.UserCapital, "用户冻结金额不足，用户ID：" + userid, "order/UserfreezeToBalance");
			return 0;
		}
		if (type == CapitalChange_Type.冻结金额扣除.getValue()) {
			usercapital.setBalance(usercapital.getBalance() - money);
		}
		if (type == CapitalChange_Type.冻结金额增加.getValue()) {
			usercapital.setBalance(usercapital.getBalance() + money);
		}
		// float money = usercapital.getFreezemoney();
		// usercapital.setBalance(usercapital.getBalance() + money);
		usercapitalMapper.updateByPrimaryKey(usercapital);
		// 资金变动记录
		financeAdd(userid, usercapital.getBalance(), money, desc, "", userip, type, "",
				UserFinance_Type.已支付.getValue());

		// 用户资金更改记录表
		return recordsAdd(userid, userip, ChangeTableTypeEnum.修改.getValue(), usercapital.getId());
	}

	/**
	 * @see com.yinlian.wssc.web.service.OrderStatusService#applyAftermarketNoReceipt(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.String,
	 *      java.lang.String, com.yinlian.wssc.platform.vo.BaseResult)
	 */
	@Override
	public int updateAftermarketNoReceipt(Integer orderdetailid, Integer userid, Integer status, String reason,
			String[] images) throws Exception {

		Orderdetail orderdetail = orderdetailMapper.selectByPrimaryKey(orderdetailid);
		if (orderdetail == null) {
			LogHandle.error(LogType.Order, "根据订单详细信息ID获取订单详细信息失败，id:" + orderdetail,
					"/order/applyAftermarketNoReceipt");
			return -1;
		}
		// 订单申请记录
		Applyforcancelorder applyforcancelorder = new Applyforcancelorder();
		applyforcancelorder.setContent(reason);
		applyforcancelorder.setCreatetime(new Date());
		applyforcancelorder.setOrderid(orderdetailid);
		applyforcancelorder.setOrderstatus(status);
		applyforcancelorder.setUserid(userid);
		applyforcancelorder.setOrdernum(orderdetail.getOrdercode());
		applyforcancelorder.setType(OrderApplyTypeEnum.订单退款.getValue());
		applyfororderMapper.insertForPrimaryKey(applyforcancelorder);
		// 订单售后记录
		Afterservice afterservice = new Afterservice();
		afterservice.setReason(reason);
		afterservice.setCreatetime(new Date());
		afterservice.setOrderid(orderdetailid);
		afterservice.setStatus(AfterServiceStatusEnum.申请售后.getValue());
		afterservice.setUserid(userid);
		afterservice.setType(OrderApplyTypeEnum.订单退款.getValue());

		afterserviceMapper.insert(afterservice);
		Images record = new Images();
		record.setCreatetime(new Date());
		record.setStatus(0);
		record.setFkid(applyforcancelorder.getId());

		if (images != null) {
			for (String imageurl : images) {
				if (StringUtils.isNotNull(imageurl)) {
					record.setType(ImageTypeEnum.买家申请退款图片.getValue());
					record.setImgurl(imageurl);
					imagesMapper.insert(record);
				}
			}
		}
		orderdetail.setStatus(OrderDetailStatusEnum.申请退款.getValue());
		return orderdetailMapper.updateByPrimaryKey(orderdetail);
	}

	/**
	 * @see com.yinlian.wssc.web.service.OrderStatusService#chekApplyAftermarketNoReceipt(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.String,
	 *      java.lang.String[], com.yinlian.Enums.OrderApplyTypeEnum,
	 *      com.yinlian.wssc.platform.vo.BaseResult)
	 */
	@Override
	public int updateChekAftermarketNoReceipt(Integer orderid, Integer userid, Integer status, Integer agreetype,
			String reason, String image, Orders order) throws Exception {
		// Orders _orders = ordersMapper.getOrderByID(orderid);
		Orderdetail orderdetail = orderdetailMapper.selectByPrimaryKey(orderid);
		if (orderdetail == null) {
			LogHandle.error(LogType.Order, "根据订单ID获取订单信息失败，id:" + orderid, "/order/checkApplyAftermarket");
			return -1;
		}
		if (orderdetail.getStatus() != status) {
			LogHandle.error(LogType.Order, "订单状态不一致，订单id:" + orderid + ",状态：" + orderdetail.getStatus(),
					"/order/checkApplyAftermarket");
			return -1;
		}
		Applyforcancelorder applyforcancelorder = new Applyforcancelorder(); // 订单申请记录
		String code = orderdetail.getOrdercode();
		orderdetail.setOrdercode(code);
		Integer sellerid = orderdetail.getSellerid();
		Integer newstatus = 0;
		Integer applytype = 0;

		if (agreetype == 1) { // 同意
			newstatus = OrderDetailStatusEnum.退款成功.getValue();
			// 本地用户资金处理
			// Userfinance finance = userfinanceMapper.getListByNumberAndType(
			// code, CapitalChange_Type.冻结金额增加.getValue());
			// if (finance == null) {
			// LogHandle.error(LogType.Order, "根据订单获取用户资金失败，id:" + orderid,
			// "/order/checkApplyAftermarket");
			// }
			// if (finance.getUserid() == sellerid) {
			// // 卖家资金处理
			// changeFreezeMoney(sellerid, finance.getMoney(), code,
			// "售后申请退款，卖家冻结金额扣除", "", finance.getPaynum(), false);
			// // 买家资金处理
			// changeUserCapital(orderdetail.getBuyerid(), finance.getMoney(),
			// code, CapitalChange_Type.收入.getValue(),
			// finance.getPaynum(), "售后申请退款，买家资金退回", "");
			// }

			// 库存还原
			// List<Orderdetail> detailList = orderdetailMapper
			// .getDetailsByOrderID(orderid);
			// List<Integer> idlist = new ArrayList<Integer>();
			// for (Orderdetail de : detailList) {
			// idlist.add(de.getSkuId());
			// }
			// if (idlist.size() > 0) {
			// List<Sku> skuList = skuMapper.getListByIds(idlist);
			// for (Sku sku : skuList) {
			// Optional<Orderdetail> detail = detailList.stream()
			// .filter(x -> x.getSkuId() == sku.getId())
			// .findFirst();
			// sku.setStock(sku.getStock()
			// + detail.get().getProductcount());
			// }
			// skuMapper.updateStockList(skuList);
			// }
			applyforcancelorder.setContent(reason);
			applyforcancelorder.setContent("同意");
		} else { // 不同意
			Applyforcancelorder applycancel = applyfororderMapper.getbyOrderAndType(orderid, applytype);
			if (applycancel != null) {
				newstatus = applycancel.getOrderstatus();
			}
			applyforcancelorder.setContent(reason);
		}
		applyforcancelorder.setImgurl(image);
		applyforcancelorder.setCreatetime(new Date());
		applyforcancelorder.setOrderid(orderdetail.getId());
		applyforcancelorder.setUserid(sellerid);
		applyforcancelorder.setOrderstatus(status);
		applyforcancelorder.setType(OrderApplyTypeEnum.订单退款.getValue());
		applyfororderMapper.insert(applyforcancelorder);
		return orderdetailMapper.updateStatus(newstatus, orderid);
	}

	/**
	 * @see com.yinlian.wssc.web.service.OrderStatusService#checkApplyAftermarket(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      java.lang.String, com.yinlian.wssc.web.po.Orders)
	 */
	@Override
	public int checkApplyAftermarket(Integer orderid, Integer userid, Integer status, Integer agreetype, String reason,
			String[] images, OrderApplyTypeEnum applyTypeEnum, Orders orders) throws Exception {

		Orders _orders = ordersMapper.getOrderByID(orderid);
		if (orders == null) {
			LogHandle.error(LogType.Order, "根据订单ID获取订单信息失败，id:" + orderid, "/order/checkApplyAftermarket");
			return -1;
		}
		if (orders.getStatus() != status) {
			LogHandle.error(LogType.Order, "订单状态不一致，订单id:" + orderid + ",状态：" + _orders.getStatus(),
					"/order/checkApplyAftermarket");
			return -1;
		}
		Applyforcancelorder applyforcancelorder = new Applyforcancelorder(); // 订单申请记录
		Images record = new Images();
		String code = _orders.getCode();
		orders.setCode(code);
		Integer sellerid = _orders.getSellerid();
		Integer newstatus = 0;
		Integer applytype = 0;
		switch (applyTypeEnum) {
		case 订单退款:
			if (agreetype == 1) { // 同意
				newstatus = OrderStatusEnum.已完结.getValue();
				applytype = OrderApplyTypeEnum.订单已付款取消.getValue(); // TODO: 退款
																	// 调第三方接口

				// 本地用户资金处理
				Userfinance finance = userfinanceMapper.getListByNumberAndType(code,
						CapitalChange_Type.冻结金额增加.getValue());
				if (finance == null) {
					LogHandle.error(LogType.Order, "根据订单获取用户资金失败，id:" + orderid, "/order/checkApplyAftermarket");
				}
				if (finance.getUserid() == sellerid) {
					// 卖家资金处理
					changeFreezeMoney(sellerid, finance.getMoney(), code, "售后申请退款，卖家冻结金额扣除", "", finance.getPaynum(),
							false);
					// 买家资金处理
					changeUserCapital(_orders.getBuyerid(), finance.getMoney(), code, CapitalChange_Type.收入.getValue(),
							finance.getPaynum(), "售后申请退款，买家资金退回", "");
				}
				// 库存还原
				List<Orderdetail> detailList = orderdetailMapper.getDetailsByOrderID(orderid);
				List<Integer> idlist = new ArrayList<Integer>();
				for (Orderdetail de : detailList) {
					idlist.add(de.getSkuId());
				}
				if (idlist.size() > 0) {
					List<Sku> skuList = skuMapper.getListByIds(idlist);
					for (Sku sku : skuList) {
						Optional<Orderdetail> detail = detailList.stream().filter(x -> x.getSkuId() == sku.getId())
								.findFirst();
						sku.setStock(sku.getStock() + detail.get().getProductcount());
					}
					skuMapper.updateStockList(skuList);
				}
				applyforcancelorder.setContent("同意");
			} else { // 不同意
				applytype = OrderApplyTypeEnum.订单退款.getValue();
				Applyforcancelorder applycancel = applyfororderMapper.getbyOrderAndType(orderid, applytype);
				if (applycancel != null) {
					newstatus = applycancel.getOrderstatus();
				}
				applyforcancelorder.setContent(reason);
			}
			record.setType(ImageTypeEnum.卖家不同意退款图片.getValue());
			break;

		case 订单退货退款:

			if (agreetype == 1) { // 同意
				applytype = OrderApplyTypeEnum.订单已付款取消.getValue(); // TODO: 退款
																	// 调第三方接口
				newstatus = OrderDetailStatusEnum.退款成功.getValue();

				// 本地用户资金处理
				Userfinance finance = userfinanceMapper.getListByNumberAndType(code,
						CapitalChange_Type.冻结金额增加.getValue());
				if (finance == null) {
					LogHandle.error(LogType.Order, "根据订单获取用户资金失败，id:" + orderid, "/order/checkApplyAftermarket");
				}
				if (finance.getUserid() == sellerid) {
					// 卖家资金处理
					changeFreezeMoney(sellerid, finance.getMoney(), code, "售后申请退货退款，卖家冻结金额扣除", "", finance.getPaynum(),
							false);
					// 买家资金处理
					changeUserCapital(_orders.getBuyerid(), finance.getMoney(), code, CapitalChange_Type.收入.getValue(),
							finance.getPaynum(), "售后申请退货退款，买家资金退回", "");
				}
				applyforcancelorder.setContent("同意");
			} else { // 不同意
				applytype = OrderApplyTypeEnum.订单退货退款.getValue();
				Applyforcancelorder applycancel = applyfororderMapper.getbyOrderAndType(orderid, applytype);
				if (applycancel != null) {
					newstatus = applycancel.getOrderstatus();
				}
				applyforcancelorder.setContent(reason);
			}
			record.setType(ImageTypeEnum.卖家不同意退货图片.getValue());
			break;
		case 订单换货:

			if (agreetype == 1) { // 同意
				newstatus = OrderStatusEnum.已完结.getValue();
				applytype = OrderApplyTypeEnum.订单换货.getValue(); // TODO: 退款
																// 调第三方接口
				/*
				 * // 本地用户资金处理 Userfinance finance =
				 * userfinanceMapper.getListByNumberAndType( code,
				 * CapitalChange_Type.冻结金额增加.getValue()); if (finance == null) {
				 * LogHandle.error(LogType.Order, "根据订单获取用户资金失败，id:" + orderid,
				 * "/order/checkApplyAftermarket"); } if (finance.getUserid() ==
				 * sellerid) { // 卖家资金处理 changeFreezeMoney(sellerid,
				 * finance.getMoney(), code, "售后申请退货退款，卖家冻结金额扣除", "",
				 * finance.getPaynum(), false); // 买家资金处理
				 * changeUserCapital(_orders.getBuyerid(), finance.getMoney(),
				 * code, CapitalChange_Type.收入.getValue(), finance.getPaynum(),
				 * "售后申请退货退款，买家资金退回", ""); }
				 * 
				 * // 库存还原 换货的库存 处理 List<Orderdetail> detailList =
				 * orderdetailMapper .getDetailsByOrderID(orderid);
				 * List<Integer> idlist = new ArrayList<Integer>(); for
				 * (Orderdetail de : detailList) { idlist.add(de.getSkuId()); }
				 * if (idlist.size() > 0) { List<Sku> skuList =
				 * skuMapper.getListByIds(idlist); for (Sku sku : skuList) {
				 * Optional<Orderdetail> detail = detailList.stream() .filter(x
				 * -> x.getSkuId() == sku.getId()) .findFirst();
				 * sku.setStock(sku.getStock() +
				 * detail.get().getProductcount()); }
				 * skuMapper.updateStockList(skuList); }
				 */
				applyforcancelorder.setContent("同意");
			} else {
				// 不同意
				applytype = OrderApplyTypeEnum.订单换货.getValue();
				Applyforcancelorder applycancel = applyfororderMapper.getbyOrderAndType(orderid, applytype);
				if (applycancel != null) {
					newstatus = applycancel.getOrderstatus();
				}
				applyforcancelorder.setContent(reason);
			}
			record.setType(ImageTypeEnum.卖家不同意换货图片.getValue());
			break;
		default:
			break;
		}
		applyforcancelorder.setCreatetime(new Date());
		applyforcancelorder.setOrderid(_orders.getId());
		applyforcancelorder.setUserid(sellerid);
		applyforcancelorder.setOrderstatus(status);
		applyforcancelorder.setType(applytype);
		applyfororderMapper.insert(applyforcancelorder);
		if (images != null) {
			for (String imageurl : images) {
				record.setCreatetime(new Date());
				record.setFkid(orderid);
				record.setStatus(1);
				record.setImgurl(imageurl);
			}
		}

		return ordersMapper.updateStatus(newstatus, orderid);
	}

	/**
	 * 订单支付活动处理
	 * 
	 * @param ordercode
	 * @param userid
	 * @throws Exception
	 */
	private void activityProcess(String ordercode, int userid) throws Exception {
		// 订单活动处理
		List<OrderactivityChildHistory> hislist = orderactivityChildHistoryMapper.getListByOrderCode(ordercode);
		int points = 0;
		List<Integer> couids = new ArrayList<Integer>();
		if (hislist != null) {
			for (OrderactivityChildHistory his : hislist) {
				switch (Och_Type.valueOf(his.getType())) {
				case 优惠券:
					couids.add(his.getCouponid());
					break;
				case 积分:
					points += his.getPoint();
					break;
				default:
					break;
				}
			}
		}
		if (couids.size() > 0) {
			couponService.addUserCouponList(couids, userid);
		}
		if (points > 0) {
			pointsRecordService.updateUserPoint(points, userid, PointsRecordsFromTypeEnum.活动增送.getValue(),
					PointsRecordsTypeEnum.增加.getValue());
		}
		/**
		 * 订单支付 订单活动表状态 -已完成
		 */
		List<OrderactivityHistory> actHistory = orderactivityHistoryMapper.getbyOrdercode(ordercode);
		if (actHistory != null) {
			for (OrderactivityHistory his : actHistory) {
				his.setStatus(CompleteStatus.已完成.getValue());
				orderactivityHistoryMapper.updateByPrimaryKey(his);
			}
		}
	}

	/**
	 * 售后买家或者卖家发货(中绿售后流程调整，产品需要时请恢复注释)
	 */
	/*
	 * @Override public int updateSendProForOrderDetail(int orderdetailID, int
	 * applytype, int sender, String logisticsName, String logisticsCode) throws
	 * Exception { Orderdetail detail = orderdetailMapper
	 * .selectByPrimaryKey(orderdetailID); if (detail == null) {
	 * LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单ID：" + orderdetailID,
	 * "order/updateSendProForOrderDetail"); return 0; } if (detail.getStatus()
	 * != OrderDetailStatusEnum.换货买家待发货.getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.换货商家待发货 .getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.维修买家待发货 .getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.维修商家待发货 .getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.退货买家待发货 .getValue()) {
	 * LogHandle.error(LogType.Order, "子订单状态不是待发货，不能发货。子订单ID：" + orderdetailID,
	 * "order/updateSendProForOrderDetail"); return 0; }
	 * 
	 * OrderDetailStatusEnum detailStatus = OrderDetailStatusEnum
	 * .valueOf(detail.getStatus()); switch (detailStatus) { case 换货买家待发货:
	 * detail.setStatus(OrderDetailStatusEnum.换货商家待收货.getValue()); break; case
	 * 换货商家待发货: detail.setStatus(OrderDetailStatusEnum.换货买家待收货.getValue());
	 * break; case 维修买家待发货:
	 * detail.setStatus(OrderDetailStatusEnum.维修商家待收货.getValue()); break; case
	 * 维修商家待发货: detail.setStatus(OrderDetailStatusEnum.维修买家待收货.getValue());
	 * break; case 退货买家待发货:
	 * detail.setStatus(OrderDetailStatusEnum.退货商家待收货.getValue()); break;
	 * default: LogHandle.error(LogType.Order, "子订单状态错误。子订单状态：" + detailStatus,
	 * "order/updateSendProForOrderDetail"); return 0; } // 售后信息调整 Afterservice
	 * afs = new Afterservice(); if (sender == AfterServiceSender.买家.getValue())
	 * { afs = afterserviceMapper.getByOrderAndType(orderdetailID, applytype,
	 * AfterServiceStatusEnum.商家已确认.getValue()); if (afs != null) {
	 * afs.setbExpressname(logisticsName); afs.setbExpressnum(logisticsCode);
	 * afs.setStatus(AfterServiceStatusEnum.商家待收货.getValue()); }
	 * afterserviceMapper.updateByPrimaryKey(afs); } else { afs =
	 * afterserviceMapper.getByOrderAndType(orderdetailID, applytype,
	 * AfterServiceStatusEnum.商家待发货.getValue()); if (afs != null) {
	 * afs.setsExpressname(logisticsName); afs.setsExpressnum(logisticsCode);
	 * afs.setStatus(AfterServiceStatusEnum.买家待收货.getValue()); }
	 * afterserviceMapper.updatebExpress(afs); }
	 * 
	 * if (orderdetailMapper.updateByPrimaryKey(detail) > 0) { return 1; } else
	 * { return 0; } }
	 */
	/**
	 * 退换货维修申请
	 */
	@Override
	public int updateAfterServiceApply(int orderdetailID, int type, int userid, String reason, String imgs,
			BaseResult item) throws Exception {
		Orderdetail detail = orderdetailMapper.selectByPrimaryKey(orderdetailID);
		if (detail == null) {
			LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单ID：" + orderdetailID, "order/updateAfterServiceApply");
			item.setCode(-301);
			item.setDesc("根据子订单ID未能检索到数据。子订单ID：" + orderdetailID);
			return 0;
		}
		/*
		 * if (detail.getStatus() != OrderDetailStatusEnum.订单完成.getValue() &&
		 * detail.getStatus() != OrderDetailStatusEnum.换货成功.getValue() &&
		 * detail.getStatus() != OrderDetailStatusEnum.维修成功.getValue()) {
		 * LogHandle.error(LogType.Order, "子订单状态不对，不能申请售后。子订单ID：" +
		 * orderdetailID, "order/updateAfterServiceApply"); item.setCode(-302);
		 * item.setDesc("子订单状态不对，不能申请售后。状态" + detail.getStatus()); return 0; }
		 */
		int imgtype = 0;
		OrderApplyTypeEnum applytype = OrderApplyTypeEnum.valueOf(type);
		switch (applytype) {
		case 订单退款:
			detail.setStatus(OrderDetailStatusEnum.申请退款.getValue());
			imgtype = ImageTypeEnum.买家申请退款图片.getValue();
			break;
		/*
		 * case 订单退货退款:
		 * detail.setStatus(OrderDetailStatusEnum.申请退货退款.getValue()); imgtype =
		 * ImageTypeEnum.买家申请退货图片.getValue(); break; case 订单换货:
		 * detail.setStatus(OrderDetailStatusEnum.申请换货.getValue()); imgtype =
		 * ImageTypeEnum.买家申请换货图片.getValue(); break; case 订单维修:
		 * detail.setStatus(OrderDetailStatusEnum.申请维修.getValue()); imgtype =
		 * ImageTypeEnum.买家申请维修图片.getValue(); break;
		 */
		default:
			LogHandle.error(LogType.Order, "售后申请类型错误。申请类型：" + applytype, "order/updateAfterServiceApply");
			item.setCode(-302);
			item.setDesc("售后申请类型错误");
			return 0;
		}

		// 订单申请记录
		Applyforcancelorder apply = new Applyforcancelorder();
		apply.setContent(reason);
		apply.setCreatetime(new Date());
		apply.setOrderid(detail.getId());
		apply.setOrderstatus(detail.getStatus());
		apply.setType(type);
		apply.setUserid(userid);
		apply.setOrdernum(detail.getOrdercode());
		apply.setImgurl(imgs);
		apply.setDatatype(0);// 数据类型 0申请售后数据 1卖家审核数据 2平台处理数据
		applyfororderMapper.insert(apply);
		// 订单售后申请记录
		Afterservice afs = new Afterservice();
		afs.setCreatetime(new Date());
		afs.setOrderid(detail.getId());
		afs.setReason(reason);
		afs.setType(type);
		afs.setUserid(userid);
		afs.setImgurl(imgs);
		afs.setStatus(AfterServiceStatusEnum.申请售后.getValue());
		afterserviceMapper.insert(afs);
		if (imgs != null && !StringUtilsEX.IsNullOrWhiteSpace(imgs)) {
			List<Images> list = new ArrayList<Images>();
			Images img = null;
			for (String i : imgs.split(",")) {
				img = new Images();
				img.setCreatetime(new Date());
				img.setFkid(apply.getId());
				img.setStatus(0);
				img.setSort(0);
				img.setImgurl(i);
				img.setType(imgtype);
				list.add(img);
			}
			imagesMapper.insertList(list);
		}
		detail.setApplytime(new Date());
		return orderdetailMapper.updateByPrimaryKey(detail);
	}

	/**
	 * 售后商家同意处理
	 * 
	 * @author Fjh
	 * 
	 * @param orderdetailID
	 *            子订单id
	 * @param type
	 *            订单售后请求类型（订单未付款取消(0), 订单退货退款(1), 订单换货(2), 订单维修(3),
	 *            订单退款(4),订单已付款取消(5)）
	 * @param userip
	 *            用户ip
	 * @param isagree
	 *            是否同意 0否 1是
	 * @param reason
	 *            不同意原因
	 * @param imgurl
	 *            不同意图片说明
	 * @return >0 处理成功
	 */
	@Override
	public int updateCheckAfterApply(int orderdetailID, int type, int isagree, String reason, String imgurl,
			String userip) throws Exception {
		Orderdetail detail = orderdetailMapper.selectByPrimaryKey(orderdetailID);
		if (detail == null) {
			LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单ID：" + orderdetailID, "order/updateCheckAfterApply");
			return 0;
		}
		if (detail.getStatus() != OrderDetailStatusEnum.申请退款.getValue()) {
			LogHandle.error(LogType.Order, "子订单状态不对，不能售后审核。子订单ID：" + orderdetailID, "order/updateCheckAfterApply");
			return 0;
		}
		OrderApplyTypeEnum applytype = OrderApplyTypeEnum.valueOf(type);
		int newstatus = 0;
		int imgtype = 0;
		switch (applytype) {
		// case 订单退款:
		// newstatus = isagree == 0 ? OrderDetailStatusEnum.订单完成.getValue()
		// : OrderDetailStatusEnum.退款成功.getValue();
		// break;
		case 订单退款:
			newstatus = isagree == 0 ? OrderDetailStatusEnum.退款失败.getValue() : OrderDetailStatusEnum.退款成功.getValue();
			imgtype = ImageTypeEnum.卖家不同意退货图片.getValue();
			break;
		/*
		 * case 订单换货: newstatus = isagree == 0 ?
		 * OrderDetailStatusEnum.换货申请被拒绝.getValue() :
		 * OrderDetailStatusEnum.换货成功.getValue(); imgtype =
		 * ImageTypeEnum.卖家不同意换货图片.getValue(); break; case 订单维修: newstatus =
		 * isagree == 0 ? OrderDetailStatusEnum.维修申请被拒绝.getValue() :
		 * OrderDetailStatusEnum.维修成功.getValue(); break;
		 */
		default:
			LogHandle.error(LogType.Order, "售后申请类型错误。申请类型：" + applytype, "order/updateCheckAfterApply");
			return 0;
		}
		detail.setStatus(newstatus);
		if (isagree == 1) {
			Afterservice afs = afterserviceMapper.getByOrderAndType(orderdetailID, type,
					AfterServiceStatusEnum.申请售后.getValue());
			if (afs != null) {
				afs.setStatus(AfterServiceStatusEnum.售后完成.getValue());
				// 商家同意售后申请 短信通知买家
				Orders order = ordersMapper.getOrderByID(detail.getOrderid());
				Receiveinfo info = receiveinfoMapper.selectByOrderGroupId(order.getGroupcode());
				List<Receiveaddress> addressList = receiveaddressMapper.selectByUserId(order.getSellerid());
				Applyforcancelorder applyfor = new Applyforcancelorder();
				applyfor.setContent(reason);
				applyfor.setCreatetime(new Date());
				applyfor.setOrderid(detail.getId());
				applyfor.setOrderstatus(detail.getStatus());
				applyfor.setType(type);
				applyfor.setImgurl(imgurl);
				applyfor.setUserid(detail.getSellerid());
				applyfor.setOrdernum(detail.getOrdercode());
				applyfor.setDatatype(1);// 数据类型 0申请售后数据 1卖家审核数据 2平台处理数据
				applyfororderMapper.insert(applyfor);
				if (info != null && addressList != null && addressList.size() > 0) {
					String message = "尊敬的会员，您好！商家已同意您编号为" + detail.getOrdercode() + "的售后申请。退货地址为："
							+ addressList.get(0).getProvincename() + addressList.get(0).getCityname()
							+ addressList.get(0).getAreaname() + addressList.get(0).getAddress();
					SmsUtil.sendMessage(info.getTelphone(), message);
				}
				afs.setGetaddr(info.getAddress());
				afs.setContactsname(info.getConsignee());
				afs.setMobile(info.getTelphone());
				afs.setShopid(detail.getShopid());
				afs.setResult("订单售后申请审核通过");
				afterserviceMapper.updateByPrimaryKey(afs);

				if (applytype == OrderApplyTypeEnum.订单退货退款) {

					Orders orders = ordersMapper.selectByPrimaryKey(detail.getOrderid());
					BigDecimal decimal = orders.getActualpay(); // 实际支付金额
					BigDecimal price = orders.getPrice(); // 订单总金额
					BigDecimal skuprice = detail.getProductprice(); // 商品金额
					BigDecimal f = decimal.multiply(skuprice);
					float money = f.divide(price, 2, BigDecimal.ROUND_HALF_UP).floatValue(); // 返回的金额
					changeUserCapital(orders.getBuyerid(), money, orders.getCode(), CapitalChange_Type.收入.getValue(),
							"", "买家退货退款余额增加", userip);
					UserfreezeToBalance("买家退货退款冻结金额扣除", CapitalChange_Type.冻结金额扣除.getValue(), money,
							orders.getSellerid(), userip);
				}
			} else {
				LogHandle.error(LogType.Order, "根据子订单ID获取售后申请记录为空。申请类型：" + applytype, "order/updateCheckAfterApply");
				return 0;
			}
		} else {
			Afterservice afs = afterserviceMapper.getByOrderAndType(orderdetailID, type,
					AfterServiceStatusEnum.申请售后.getValue());
			if (afs != null) {
				afs.setStatus(AfterServiceStatusEnum.商家不同意.getValue());
				afs.setResult("订单售后申请驳回");
				afterserviceMapper.updateByPrimaryKey(afs);
			}
			Applyforcancelorder applyfor = new Applyforcancelorder();
			applyfor.setContent(reason);
			applyfor.setCreatetime(new Date());
			applyfor.setOrderid(detail.getId());
			applyfor.setOrderstatus(detail.getStatus());
			applyfor.setType(type);
			applyfor.setImgurl(imgurl);
			applyfor.setUserid(detail.getSellerid());
			applyfor.setOrdernum(detail.getOrdercode());
			applyfor.setDatatype(1);// 数据类型 0申请售后数据 1卖家审核数据 2平台处理数据
			applyfororderMapper.insert(applyfor);
			if (imgurl != null && !StringUtilsEX.IsNullOrWhiteSpace(imgurl)) {
				List<Images> imglist = new ArrayList<Images>();
				Images images = null;
				for (String img : imgurl.split(",")) {
					images = new Images();
					images.setCreatetime(new Date());
					images.setFkid(applyfor.getId());
					images.setStatus(0);
					images.setSort(0);
					images.setImgurl(img);
					images.setType(imgtype);
					imglist.add(images);
				}
				imagesMapper.insertList(imglist);
			}
		}
		detail.setFinishtime(new Date());
		detail.setDisposetime(new Date());
		return orderdetailMapper.updateByPrimaryKey(detail);
	}

	/**
	 * 售后商家纠纷同意处理
	 * 
	 * @param orderdetailID
	 *            子订单id
	 * @param type
	 *            订单售后请求类型（订单未付款取消(0), 订单退货退款(1), 订单换货(2), 订单维修(3),
	 *            订单退款(4),订单已付款取消(5)）
	 * @param userip
	 *            用户ip
	 * @param reason
	 *            原因
	 * @param imgurl
	 *            图片说明
	 * @return >0 处理成功
	 */
	@Override
	public int dealAfterApply(int orderdetailID, int type, String reason, String imgurl, String userip)
			throws Exception {
		int isagree = 1;// 同意处理
		Orderdetail detail = orderdetailMapper.selectByPrimaryKey(orderdetailID);
		if (detail == null) {
			LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单ID：" + orderdetailID, "order/updateCheckAfterApply");
			return 0;
		}
		/*
		 * if (detail.getStatus() != OrderDetailStatusEnum.换货申请被拒绝.getValue() &&
		 * detail.getStatus() != OrderDetailStatusEnum.维修申请被拒绝.getValue() &&
		 * detail.getStatus() != OrderDetailStatusEnum.退款申请被拒绝.getValue() &&
		 * detail.getStatus() != OrderDetailStatusEnum.退货申请被拒绝 .getValue()) {
		 * LogHandle.error(LogType.Order, "子订单状态不对，不能售后审核。子订单ID：" +
		 * orderdetailID, "order/updateCheckAfterApply"); return 0; }
		 */
		OrderApplyTypeEnum applytype = OrderApplyTypeEnum.valueOf(type);
		int newstatus = 0;
		switch (applytype) {
		case 订单退款:
			newstatus = OrderDetailStatusEnum.退款成功.getValue();
			break;
		/*
		 * case 订单退货退款: newstatus = OrderDetailStatusEnum.退货退款成功.getValue();
		 * break; case 订单换货: newstatus = OrderDetailStatusEnum.换货成功.getValue();
		 * break; case 订单维修: newstatus = OrderDetailStatusEnum.维修成功.getValue();
		 * break;
		 */
		default:
			LogHandle.error(LogType.Order, "售后申请类型错误。申请类型：" + applytype, "order/updateCheckAfterApply");
			return 0;
		}
		detail.setStatus(newstatus);
		if (isagree == 1) {
			Afterservice afs = afterserviceMapper.getByOrderAndType(orderdetailID, type,
					AfterServiceStatusEnum.商家不同意.getValue());
			if (afs != null) {
				afs.setStatus(AfterServiceStatusEnum.售后完成.getValue());
				// 商家同意售后申请 短信通知买家
				Orders order = ordersMapper.getOrderByID(detail.getOrderid());
				Receiveinfo info = receiveinfoMapper.selectByOrderGroupId(order.getGroupcode());
				List<Receiveaddress> addressList = receiveaddressMapper.selectByUserId(order.getSellerid());
				Applyforcancelorder applyfor = new Applyforcancelorder();
				applyfor.setContent(reason);
				applyfor.setCreatetime(new Date());
				applyfor.setOrderid(detail.getId());
				applyfor.setOrderstatus(detail.getStatus());
				applyfor.setType(type);
				applyfor.setImgurl(imgurl);
				applyfor.setUserid(detail.getSellerid());
				applyfor.setOrdernum(detail.getOrdercode());
				applyfor.setDatatype(2);// 数据类型 0申请售后数据 1卖家审核数据 2平台处理数据
				applyfororderMapper.insert(applyfor);
				if (info != null && addressList != null && addressList.size() > 0) {
					String message = "尊敬的会员，您好！商家已同意您编号为" + detail.getOrdercode() + "的售后申请。退货地址为："
							+ addressList.get(0).getProvincename() + addressList.get(0).getCityname()
							+ addressList.get(0).getAreaname() + addressList.get(0).getAddress();
					SmsUtil.sendMessage(info.getTelphone(), message);
				}
				afs.setGetaddr(info.getAddress());
				afs.setContactsname(info.getConsignee());
				afs.setMobile(info.getTelphone());
				afs.setShopid(detail.getShopid());
				afs.setResult("订单售后申请审核通过");
				afterserviceMapper.updateByPrimaryKey(afs);

				if (applytype == OrderApplyTypeEnum.订单退货退款) {

					Orders orders = ordersMapper.selectByPrimaryKey(detail.getOrderid());
					BigDecimal decimal = orders.getActualpay(); // 实际支付金额
					BigDecimal price = orders.getPrice(); // 订单总金额
					BigDecimal skuprice = detail.getProductprice(); // 商品金额
					BigDecimal f = decimal.multiply(skuprice);
					float money = f.divide(price, 2, BigDecimal.ROUND_HALF_UP).floatValue(); // 返回的金额
					changeUserCapital(orders.getBuyerid(), money, orders.getCode(), CapitalChange_Type.收入.getValue(),
							"", "买家退货退款余额增加", userip);
					UserfreezeToBalance("买家退货退款冻结金额扣除", CapitalChange_Type.冻结金额扣除.getValue(), money,
							orders.getSellerid(), userip);
				}
			} else {
				LogHandle.error(LogType.Order, "根据子订单ID获取售后申请记录为空。申请类型：" + applytype, "order/updateCheckAfterApply");
				return 0;
			}
		}
		detail.setFinishtime(new Date());
		detail.setDisposetime(new Date());
		return orderdetailMapper.updateByPrimaryKey(detail);
	}

	/**
	 * 售后退换货维修收货(中绿售后流程调整，产品需要时请恢复注释)
	 */
	/*
	 * public int updateAfterServiceReceive(int orderdetailID, int type, int
	 * sender, SessionUser user, String userip) throws Exception { Orderdetail
	 * detail = new Orderdetail(); detail =
	 * orderdetailMapper.selectByPrimaryKey(orderdetailID); if (detail == null)
	 * { LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单ID：" + orderdetailID,
	 * "order/updateAfterServiceReceive"); return 0; } if (detail.getStatus() !=
	 * OrderDetailStatusEnum.换货买家待收货.getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.换货商家待收货 .getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.维修买家待收货 .getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.维修商家待收货 .getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.退货商家待收货 .getValue()) {
	 * LogHandle.error(LogType.Order, "子订单状态不对，不能进行收货动作。子订单ID：" + orderdetailID,
	 * "order/updateAfterServiceReceive"); } OrderApplyTypeEnum applytype =
	 * OrderApplyTypeEnum.valueOf(type); int ret = 0; if (sender ==
	 * AfterServiceSender.买家.getValue()) { switch (applytype) { case 订单换货:
	 * detail.setStatus(OrderDetailStatusEnum.换货成功.getValue()); break; case
	 * 订单维修: detail.setStatus(OrderDetailStatusEnum.维修成功.getValue()); break;
	 * default: LogHandle.error(LogType.Order, "售后申请类型错误。申请类型：" + applytype,
	 * "order/updateAfterServiceReceive"); return 0; } detail.setFinishtime(new
	 * Date()); ret = orderdetailMapper.updateByPrimaryKey(detail);
	 * 
	 * Afterservice after = afterserviceMapper.getByOrderAndType( orderdetailID,
	 * type, AfterServiceStatusEnum.买家待收货.getValue()); if (after != null) {
	 * after.setStatus(AfterServiceStatusEnum.售后完成.getValue()); }
	 * afterserviceMapper.updatenoneExpress(after);
	 * 
	 * } else if (sender == AfterServiceSender.商家.getValue()) { Afterservice
	 * after = afterserviceMapper.getByOrderAndType( orderdetailID, type,
	 * AfterServiceStatusEnum.商家待收货.getValue()); if (after != null) { if
	 * (detail.getStatus() == OrderDetailStatusEnum.退货商家待收货 .getValue()) {
	 * after.setStatus(AfterServiceStatusEnum.售后完成.getValue()); } else {
	 * after.setStatus(AfterServiceStatusEnum.商家待发货.getValue()); }
	 * afterserviceMapper.updatenoneExpress(after); }
	 * 
	 * if (detail.getStatus() == OrderDetailStatusEnum.退货商家待收货.getValue()) { //
	 * 用户资金处理 买家余额增加 卖家冻结金额减少 // 本地资金处理 Orders orders =
	 * ordersMapper.selectByPrimaryKey(detail .getOrderid()); BigDecimal decimal
	 * = orders.getActualpay(); // 实际支付金额 BigDecimal price = orders.getPrice();
	 * // 订单总金额 BigDecimal skuprice = detail.getProductprice(); // 商品金额
	 * BigDecimal f = decimal.multiply(skuprice); float money = f.divide(price,
	 * 2, BigDecimal.ROUND_HALF_UP) .floatValue(); // 返回的金额
	 * changeUserCapital(orders.getBuyerid(), money, orders.getCode(),
	 * CapitalChange_Type.收入.getValue(), "", "买家退货退款余额增加", userip);
	 * UserfreezeToBalance("买家退货退款冻结金额扣除", CapitalChange_Type.冻结金额扣除.getValue(),
	 * money, orders.getSellerid(), userip);
	 * detail.setStatus(OrderDetailStatusEnum.退货退款成功.getValue());
	 * detail.setFinishtime(new Date()); } else if (detail.getStatus() ==
	 * OrderDetailStatusEnum.换货商家待收货 .getValue()) {
	 * detail.setStatus(OrderDetailStatusEnum.换货商家待发货.getValue()); } else if
	 * (detail.getStatus() == OrderDetailStatusEnum.维修商家待收货 .getValue()) {
	 * detail.setStatus(OrderDetailStatusEnum.维修商家待发货.getValue()); } ret =
	 * orderdetailMapper.updateByPrimaryKey(detail); } return ret; }
	 */

	/***
	 * 售后商家同意处理
	 * 
	 * @author Fjh
	 * 
	 * @param orderdetailID
	 *            子订单id
	 * @param type
	 *            订单售后请求类型（订单未付款取消(0), 订单退货退款(1), 订单换货(2), 订单维修(3),
	 *            订单退款(4),订单已付款取消(5)）
	 * @param userip
	 *            用户ip
	 * @return >0 处理成功
	 */
	/*
	 * @Override public int updateAfterServiceReceive(int orderdetailID, int
	 * type, String userip) throws Exception { Orderdetail detail = new
	 * Orderdetail(); detail =
	 * orderdetailMapper.selectByPrimaryKey(orderdetailID); if (detail == null)
	 * { LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单ID：" + orderdetailID,
	 * "order/updateAfterServiceReceive"); return 0; } if (detail.getStatus() !=
	 * OrderDetailStatusEnum.申请换货.getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.申请维修.getValue() && detail.getStatus() !=
	 * OrderDetailStatusEnum.申请退货退款.getValue()) { LogHandle.error(LogType.Order,
	 * "子订单状态不对，不能进行收货动作。子订单ID：" + orderdetailID,
	 * "order/updateAfterServiceReceive"); } OrderApplyTypeEnum applytype =
	 * OrderApplyTypeEnum.valueOf(type); int ret = 0; switch (applytype) { case
	 * 订单换货: detail.setStatus(OrderDetailStatusEnum.换货成功.getValue()); break;
	 * case 订单维修: detail.setStatus(OrderDetailStatusEnum.维修成功.getValue());
	 * break; case 订单退货退款: Orders orders =
	 * ordersMapper.selectByPrimaryKey(detail .getOrderid()); BigDecimal decimal
	 * = orders.getActualpay(); // 实际支付金额 BigDecimal price = orders.getPrice();
	 * // 订单总金额 BigDecimal skuprice = detail.getProductprice(); // 商品金额
	 * BigDecimal f = decimal.multiply(skuprice); float money = f.divide(price,
	 * 2, BigDecimal.ROUND_HALF_UP) .floatValue(); // 返回的金额
	 * changeUserCapital(orders.getBuyerid(), money, orders.getCode(),
	 * CapitalChange_Type.收入.getValue(), "", "买家退货退款余额增加", userip);
	 * UserfreezeToBalance("买家退货退款冻结金额扣除", CapitalChange_Type.冻结金额扣除.getValue(),
	 * money, orders.getSellerid(), userip);
	 * detail.setStatus(OrderDetailStatusEnum.退货退款成功.getValue()); break;
	 * default: LogHandle.error(LogType.Order, "售后申请类型错误。申请类型：" + applytype,
	 * "order/updateAfterServiceReceive"); return 0; } detail.setFinishtime(new
	 * Date()); ret = orderdetailMapper.updateByPrimaryKey(detail);
	 * 
	 * Afterservice after = afterserviceMapper.getByOrderAndType( orderdetailID,
	 * type, AfterServiceStatusEnum.申请售后.getValue()); if (after != null) {
	 * after.setStatus(AfterServiceStatusEnum.售后完成.getValue()); }
	 * afterserviceMapper.updatenoneExpress(after);
	 * 
	 * return ret; }
	 */

	/**
	 * 下单成功发送推送消息
	 * 
	 * @param userid
	 * @param ordercode
	 * @param ordertime
	 * @throws Exception
	 */
	/*
	 * private void sendMessage(int userid, Integer templatetag, String tagname,
	 * String ordercode, String logisname, String logiscode, Date ordertime,
	 * String[] content1) throws Exception { Users users =
	 * userService.queryById(userid); PushService pushService = new
	 * PushService(); Sendtemplate template = freightService.getbyType(3, 0,
	 * templatetag); String[] content = new String[1]; String logisticscompany =
	 * logisticsMapper.getByCode(logisname); if (templatetag ==
	 * TemplateTagEnum.卖家发货.getValue()) { if (template == null) { content[0] =
	 * "【因联商城】尊敬的用户" + users.getUsername() + ",单号" + ordercode + "的订单已发货。物流公司" +
	 * logisticscompany + ",物流编号" + logiscode;
	 * 
	 * } else { content[0] = template.getContent();
	 * freightService.exchangeTemplate(users.getUsername(), ordercode,
	 * logisticscompany, logiscode, ordertime.toString(), content);
	 * 
	 * } } else { if (template == null) { content[0] = "【因联商城】尊敬的用户" +
	 * users.getUsername() + ",单号" + ordercode + "的订单买家已确认收货。";
	 * 
	 * } else { content[0] = template.getContent();
	 * freightService.exchangeTemplate(users.getUsername(), ordercode, "", "",
	 * ordertime.toString(), content); } } content1[0] = content[0]; pushService
	 * .sendPushAlias(tagname, content[0], users.getId().toString());
	 * pushService.sendPushAliasIOS(tagname, content[0], users.getId()
	 * .toString()); }
	 */

	@Override
	public int refundReturn(Integer orderid, String reason) throws Exception {
		Orders orders = orderService.getOrderByID(orderid);
		switch (orders.getStatus()) {
		case 0:
		case 9:
		case 10:
			return -1;
		default:
			Applyforcancelorder record = new Applyforcancelorder();
			record.setContent(reason);
			record.setCreatetime(new Date());
			record.setOrderid(orderid);
			record.setOrderstatus(orders.getStatus());
			record.setType(OrderApplyTypeEnum.订单退款.getValue());
			record.setUserid(orders.getBuyerid());
			record.setOrdernum(orders.getCode());
			record.setStatus(OrderStatusEnum.审核中.getValue());
			applyfororderMapper.insert(record);
			orders.setStatus(OrderStatusEnum.审核中.getValue());
			orderService.update(orders);
			break;
		}
		return 1;
	}

	@Override
	public int updatePayforAlipayCode(String groupNum, String userip, String paynum, String price, Integer paytype,
			BaseResult item) throws Exception {
		List<Orders> orderlist = orderService.getOrderByGroupCode(groupNum);
		if (orderlist == null) {
			item.setCode(-401);
			item.setDesc("组订单号错误");
			LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", groupNum));
			return -1;
		}
		Orders order = new Orders();
		if (orderlist.size() > 0) {
			order = orderlist.get(0);
		}
		int userid = order.getBuyerid();
		if (!order.getStatus().equals(OrderStatusEnum.待付款.getValue())) {
			item.setCode(-402);
			item.setDesc("订单状态不是待付款");
			LogHandle.error(LogType.Order,
					MessageFormat.format("订单状态不匹配，组订单号：{0},订单状态：{1}", groupNum, orderlist.get(0).getStatus()));
			return -1;
		}
//		if (order.getStatus().equals(OrderStatusEnum.出票中.getValue()) && order.getReqcount() < 3) {
//			LogHandle.info(LogType.Order,
//					MessageFormat.format("出票中组订单号：{0},订单状态：{1}", groupNum, orderlist.get(0).getStatus()),
//					"notify/updatePayforAlipayCode");
//			StringBuffer stringBuffer3 = new StringBuffer();
//			for (Orders orders2 : orderlist) {
//				List<Orderdetail> details = orderdetailMapper.getDetailsByOrderID(orders2.getId());
//				List<Integer> ids = new ArrayList<Integer>();
//				Hashtable<Integer, Integer> Orderdetaildic = new Hashtable<Integer, Integer>();
//				for (Orderdetail x : details) {
//					ids.add(x.getSkuId());
//					Orderdetaildic.put(x.getSkuId(), x.getProductcount());
//					Sku sku = skuMapper.selectByPrimaryKey(x.getSkuId());
//					// 对接票务
//					stringBuffer3.append("<ticketOrder>");
//					stringBuffer3.append("<orderCode>"+x.getProcode()+"</orderCode>");// B
//					stringBuffer3.append("<price>"+x.getProductprice()+"</price>");
//					stringBuffer3.append("<quantity>"+x.getProductcount()+"</quantity>");
//					stringBuffer3.append("<totalPrice>"+x.getProductprice().multiply(new BigDecimal(x.getProductcount()))+"</totalPrice>");
//					stringBuffer3.append("<occDate>"+DateUtil.datePattren(x.getUsetime())+"</occDate>");
//					stringBuffer3.append("<goodsCode>"+sku.getTicketnum()+"</goodsCode>");//PST20160918013085 "+sku.getTicketnum()+"
//					stringBuffer3.append("<goodsName>"+x.getProductname()+"</goodsName>");
//					stringBuffer3.append("<remark>测试环境用上述参数</remark>");//测试环境用上述参数  "+sku.getRemark()+"
//					stringBuffer3.append("</ticketOrder>");
//				}
//			}
//			// 对接票务
//			Idcardinfo idcardinfo1 = idcardinfoMapper.getByGroupCode(groupNum);
//			String req=XmlFuncUtils.sendCodeReq(groupNum, price, idcardinfo1, stringBuffer3.toString());
//			String retmsg = "";
//			String[] temp = XmlUint.sendPost("SEND_CODE_REQ", req);
//			Map<String, Object> map = new HashMap<>();
//			if (temp[1].equals("1")) {
//				//updateStatus(groupNum, OrderStatusEnum.审核中.getValue());
//				map.put("code", "-1");
//			} else {
//				retmsg = temp[0];
//				map = XmlUint.parserValue(retmsg);
//			}
//			// retmsg=temp[0];
//
//			LogHandle.info(LogType.Order, "出票中票务对接下单返回：" + retmsg + ",gnum:" + groupNum,
//					"notify/updatePayforAlipayCode");
//			// String retmsg=
//			// XmlUint.sendPost("SEND_CODE_REQ",stringBuffer.toString());
//			// 如果成功code=0 订单状态 主订单 :代付款-待使用 子订单:待使用
//			// 如果不成功code!=0 订单状态 主订单 :代付款-审核中
//			List<Orders> orderlist1 = orderService.getOrderByGroupCode(groupNum);
//			// Map<String ,Object> map = XmlUint.parserValue(retmsg);
//			// 订单二维码地址
//			String codeimg = "";
//			if ("0".equals(map.get("code"))) {
//				// 下单成功发送信息
//				XmlUint.sendPost("SEND_SM_REQ", XmlFuncUtils.sendSmReq(groupNum));
//				// 获取二维码
//				// String retImg ="";
//				temp = XmlUint.sendPost("QUERY_IMG_URL_REQ", XmlFuncUtils.queryImgUrlReq(groupNum));
//				map = new HashMap<>();
//				if (temp[1].equals("1")) {
//					map.put("code", "-1");
//				} else {
//					retmsg = temp[0];
//					map = XmlUint.parserValue(retmsg);
//				}
//				Map<String, Object> mapImg = map;
//				if ("0".equals(mapImg.get("code"))) {
//					codeimg = mapImg.get("img").toString();
//				}
//				for (Orders orders1 : orderlist1) {
//					orders1.setStatus(OrderStatusEnum.待使用.getValue());
//					orders1.setQrcode(codeimg);
//					ordersMapper.updateByPrimaryKey(orders1);
//					List<Orderdetail> details1 = orderdetailMapper.getDetailsByOrderID(orders1.getId());
//					for (Orderdetail y : details1) {
//						orderdetailMapper.updateStatus(OrderDetailStatusEnum.待使用.getValue(), y.getId());
//						// 分商品1种商品一条
//						financeAdd(y.getBuyerid(), StringUtilsEX.ToDouble(y.getProductprice().toString()),
//								StringUtilsEX.ToDouble(y.getProductprice().toString()), "订单在线支付商品", paynum, userip,
//								CapitalChange_Type.收入.getValue(), y.getProcode(), UserFinance_Type.已支付.getValue());
//					}
//				}
//			} else if ("6".equals(map.get("code")) && orderlist1.get(0).getStatus().equals(OrderStatusEnum.出票中.getValue())) {
//				if(map.get("description").toString().contains("要保存的订单对象已存在，请检查")){
//					// 下单重复
//					LogHandle.info(LogType.Order, "出票中下单重复票务对接下单返回：" + retmsg + ",gnum:" + groupNum,
//							"notify/updatePayforAlipayCode");
//					temp = XmlUint.sendPost("QUERY_ORDER_REQ", XmlFuncUtils.queryCodeReq(groupNum));
//					map = new HashMap<>();
//					if (temp[1].equals("1")) {
//						map.put("code", "-1");
//					} else {
//						retmsg = temp[0];
//						map = XmlUint.parserValue(retmsg);
//					}
//					Map<String, Object> mapre = map;
//					if ("0".equals(mapre.get("code"))) {
//						for (Orders orders1 : orderlist1) {
//							orders1.setStatus(OrderStatusEnum.待使用.getValue());
//							ordersMapper.updateByPrimaryKey(orders1);
//							List<Orderdetail> details1 = orderdetailMapper.getDetailsByOrderID(orders1.getId());
//							for (Orderdetail y : details1) {
//								orderdetailMapper.updateStatus(OrderDetailStatusEnum.待使用.getValue(), y.getId());
//								// 分商品1种商品一条
//								financeAdd(y.getBuyerid(), StringUtilsEX.ToDouble(y.getProductprice().toString()),
//										StringUtilsEX.ToDouble(y.getProductprice().toString()), "订单在线支付商品", paynum, userip,
//										CapitalChange_Type.收入.getValue(), y.getProcode(), UserFinance_Type.已支付.getValue());
//							}
//						}
//						XmlUint.sendPost("SEND_SM_REQ",  XmlFuncUtils.sendSmReq(groupNum));
//						// 获取二维码
//						temp = XmlUint.sendPost("QUERY_IMG_URL_REQ", XmlFuncUtils.queryImgUrlReq(groupNum));
//						map = new HashMap<>();
//						if (temp[1].equals("1")) {
//							map.put("code", "-1");
//						} else {
//							retmsg = temp[0];
//							map = XmlUint.parserValue(retmsg);
//						}
//						Map<String, Object> mapImg = map;
//						
//						if ("0".equals(mapImg.get("code"))) {
//							codeimg = mapImg.get("img").toString();
//						}
//						List<Orders> orderlist2 = orderService.getOrderByGroupCode(groupNum);
//						for (Orders orders3 : orderlist2) {
//							orders3.setQrcode(codeimg);
//							ordersMapper.updateByPrimaryKey(orders3);
//						}
//					}
//				}else{
//					updateStatus(groupNum, OrderStatusEnum.审核中.getValue());
//				}
//			}
//		} else if (order.getStatus().equals(OrderStatusEnum.出票中.getValue()) && order.getReqcount() >= 3) {
//			// 下单失败
//			for (Orders orders1 : orderlist) {
//				orders1.setStatus(OrderStatusEnum.审核中.getValue());
//				List<Orderdetail> details1 = orderdetailMapper.getDetailsByOrderID(orders1.getId());
//				for (Orderdetail y : details1) {
//					orderdetailMapper.updateStatus(OrderStatusEnum.出票中.getValue(), y.getId());
//				}
//				ordersMapper.updateByPrimaryKey(orders1);
//			}
//		} else 
			if (order.getStatus().equals(OrderStatusEnum.待付款.getValue())) {
			Double ss = orderlist.stream().mapToDouble(x -> x.getActualpay().doubleValue()).sum();			
			float sstotal=new BigDecimal(ss).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			float totalMoney = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			if (sstotal!=totalMoney) {
				item.setCode(-403);
				item.setDesc("订单金额不匹配");
				LogHandle.error(LogType.Order,
						MessageFormat.format("订单金额不匹配，组订单号：{0},支付返回价格：{1},订单价格：{2}", groupNum, price, sstotal));
				return -1;
			}
			financeAddFK(order.getBuyerid(), totalMoney, totalMoney, "订单买家在线支付", paynum, userip,
					CapitalChange_Type.支出.getValue(), groupNum, UserFinance_Type.已支付.getValue(), paytype);
			StringBuffer stringBuffer1 = new StringBuffer();
			for (Orders orders : orderlist) {
				// 以下是卖家操作
				int sellerid = orders.getSellerid();// 卖家id
				Usercapital sellerUsercapital = usercapitalMapper.getBalanceRowLockById(sellerid);
				if (sellerUsercapital == null) {
					item.setCode(-404);
					item.setDesc("未查询到卖家用户资金数据");
					LogHandle.error(LogType.Order, MessageFormat.format("订单付款未查询到卖家资金数据，错误详情：{0}", item.getDesc()));
					return -1;
				}
				sellerUsercapital.setFreezemoney(sellerUsercapital.getFreezemoney() + totalMoney);
				usercapitalMapper.updateByPrimaryKeySelective(sellerUsercapital); // 增加卖家的冻结资金
				// 资金变动记录 分店铺 1个店铺 1条
				financeAddFK(sellerid, sellerUsercapital.getBalance(),
						StringUtilsEX.ToDouble(orders.getActualpay().toString()), "订单在线支付卖家冻结金额增加", paynum, userip,
						CapitalChange_Type.冻结金额增加.getValue(), orders.getCode(), UserFinance_Type.已支付.getValue(),
						paytype);

				// 用户资金更改记录表
				recordsAdd(sellerid, userip, ChangeTableTypeEnum.修改.getValue(), sellerUsercapital.getId());

				orders.setPaytype(paytype);
				orders.setPaydate(new Date());
				orders.setStatus(OrderStatusEnum.出票中.getValue());
				ordersMapper.updateByPrimaryKey(orders);

				List<Orderdetail> details = orderdetailMapper.getDetailsByOrderID(orders.getId());
				List<Integer> ids = new ArrayList<Integer>();
				Hashtable<Integer, Integer> Orderdetaildic = new Hashtable<Integer, Integer>();
				for (Orderdetail x : details) {
					orderdetailMapper.updateStatus(OrderStatusEnum.出票中.getValue(), x.getId());
					ids.add(x.getSkuId());
					Orderdetaildic.put(x.getSkuId(), x.getProductcount());
					Sku sku1 = skuMapper.selectByPrimaryKey(x.getSkuId());
					// 对接票务
					stringBuffer1.append("<ticketOrder>");
					stringBuffer1.append("<orderCode>"+x.getProcode()+"</orderCode>");// B
					stringBuffer1.append("<price>"+x.getProductprice()+"</price>");
					stringBuffer1.append("<quantity>"+x.getProductcount()+"</quantity>");
					stringBuffer1.append("<totalPrice>"+x.getProductprice().multiply(new BigDecimal(x.getProductcount()))+"</totalPrice>");
					stringBuffer1.append("<occDate>"+DateUtil.datePattren(x.getUsetime())+"</occDate>"); 
					stringBuffer1.append("<goodsCode>"+sku1.getTicketnum()+"</goodsCode>");//PST20160918013085 "+sku.getTicketnum()+"
					stringBuffer1.append("<goodsName>"+x.getProductname()+"</goodsName>");
					stringBuffer1.append("<remark>测试环境用上述参数</remark>");//测试环境用上述参数  "+sku.getRemark()+"
					stringBuffer1.append("</ticketOrder>");
				}
				List<Sku> skus = skuMapper.getListByIds(ids);
				for (Sku sku : skus) {
					if (!Orderdetaildic.containsKey(sku.getId())) {
						continue;
					}
					Integer sale = sku.getSalescount();
					if (sale != null) {
						sku.setSalescount(sale + Orderdetaildic.get(sku.getId()));
					} else {
						sku.setSalescount(Orderdetaildic.get(sku.getId()));
					}
					skuMapper.updateByPrimaryKey(sku);
				}
				// 订单参与活动处理
				if ((orders.getActivityid() != null && orders.getActivityid() > 0)
						|| details.stream().filter(x -> x.getMarketid() != null).findFirst().isPresent()) {
					activityProcess(orders.getCode(), orders.getBuyerid());

				}
			}
			// 订单支付按积分规则赠送用户积分
			int res = userService.changePoints(userid, PointRuleEnum.消费.getValue(), new BigDecimal(totalMoney));
			if (res < 0) {
				LogHandle.error(LogType.Order, MessageFormat.format("订单余额支付按积分规则赠送用户积分出错! errorCode:{0}:", res),
						"/order/updatePayforBalanceCode");
			}
			// 买家付款发送站内信消息
			orderService.sendMessage(userid, paynum, new Date(), "", "", 3, 0, TemplateTagEnum.支付成功,
					MessagesTypeEnum.订单提醒);
			LogHandle.info(LogType.Order, "订单在线支付买家卖家财务处理成功");
			// 订单状态 代付款-已付款(待出票)
			// 资金记录 financeAdd 会员1条 组编号groupcode 分店铺 1个店铺 1条 分商品1种商品一条
			// 对接票务
			Idcardinfo idcardinfo = idcardinfoMapper.getByGroupCode(groupNum);
			String retmsg = "";
			String reqstr=XmlFuncUtils.sendCodeReq(groupNum, price, idcardinfo, stringBuffer1.toString());
			LogHandle.info(LogType.Order, "待付款票务对接下单参数：" + reqstr,
					"notify/updatePayforAlipayCode");
			String[] temp = XmlUint.sendPost("SEND_CODE_REQ", reqstr);		
			Map<String, Object> map = new HashMap<>();
			if (temp[1].equals("1")) {
				//updateStatus(groupNum, OrderStatusEnum.审核中.getValue());
				map.put("code", "-1");
				// return -1;
			} else {
				retmsg = temp[0];
				map = XmlUint.parserValue(retmsg);
			}

			LogHandle.info(LogType.Order, "待付款票务对接下单返回：" + retmsg + ",gnum:" + groupNum,
					"notify/updatePayforAlipayCode");
			// 如果成功code=0 订单状态 主订单 :代付款-待使用 子订单:待使用
			// 如果不成功code!=0 订单状态 主订单 :代付款-审核中
			List<Orders> orderlist1 = orderService.getOrderByGroupCode(groupNum);
			String codeimg = "";
			if ("0".equals(map.get("code"))) {
				// 下单成功发送信息
				XmlUint.sendPost("SEND_SM_REQ", XmlFuncUtils.sendSmReq(groupNum));
				// 获取二维码
				temp = XmlUint.sendPost("QUERY_IMG_URL_REQ", XmlFuncUtils.queryImgUrlReq(groupNum));
				map = new HashMap<>();
				if (temp[1].equals("1")) {
					map.put("code", "-1");
				} else {
					retmsg = temp[0];
					map = XmlUint.parserValue(retmsg);
				}
				Map<String, Object> mapImg = map;
				
				if ("0".equals(mapImg.get("code"))) {
					codeimg = mapImg.get("img").toString();
				}
				for (Orders orders1 : orderlist1) {
					orders1.setStatus(OrderStatusEnum.待使用.getValue());
					orders1.setQrcode(codeimg);
					ordersMapper.updateByPrimaryKey(orders1);
					List<Orderdetail> details1 = orderdetailMapper.getDetailsByOrderID(orders1.getId());
					for (Orderdetail y : details1) {
						//y.setStatus(OrderDetailStatusEnum.待使用.getValue());
						orderdetailMapper.updateStatus(OrderDetailStatusEnum.待使用.getValue(), y.getId());
						// 分商品1种商品一条
						financeAdd(y.getBuyerid(), StringUtilsEX.ToDouble(y.getProductprice().toString()),
								StringUtilsEX.ToDouble(y.getProductprice().toString()), "订单在线支付商品", paynum, userip,
								CapitalChange_Type.收入.getValue(), y.getProcode(), UserFinance_Type.已支付.getValue());
					}
				}
			} else if ("6".equals(map.get("code"))) {
				if(map.get("description").toString().contains("要保存的订单对象已存在，请检查")){
					// 下单重复
					temp = XmlUint.sendPost("QUERY_ORDER_REQ", XmlFuncUtils.queryCodeReq(groupNum));
					map = new HashMap<>();
					if (temp[1].equals("1")) {
						map.put("code", "-1");
					} else {
						retmsg = temp[0];
						map = XmlUint.parserValue(retmsg);
					}
					Map<String, Object> mapre = map;
					
					if ("0".equals(mapre.get("code"))) {
						for (Orders orders1 : orderlist1) {
							orders1.setStatus(OrderStatusEnum.待使用.getValue());
							ordersMapper.updateByPrimaryKey(orders1);
							List<Orderdetail> details1 = orderdetailMapper.getDetailsByOrderID(orders1.getId());
							for (Orderdetail y : details1) {
								orderdetailMapper.updateStatus(OrderDetailStatusEnum.待使用.getValue(), y.getId());
								// 分商品1种商品一条
								financeAdd(y.getBuyerid(), StringUtilsEX.ToDouble(y.getProductprice().toString()),
										StringUtilsEX.ToDouble(y.getProductprice().toString()), "订单在线支付商品", paynum, userip,
										CapitalChange_Type.收入.getValue(), y.getProcode(), UserFinance_Type.已支付.getValue());
							}
						}
						//发送短信
						XmlUint.sendPost("SEND_SM_REQ", XmlFuncUtils.sendSmReq(groupNum));
						// 获取二维码
						temp = XmlUint.sendPost("QUERY_IMG_URL_REQ", XmlFuncUtils.queryImgUrlReq(groupNum));
						map = new HashMap<>();
						if (temp[1].equals("1")) {
							map.put("code", "-1");
						} else {
							retmsg = temp[0];
							map = XmlUint.parserValue(retmsg);
						}
						Map<String, Object> mapImg = map;
						
						if ("0".equals(mapImg.get("code"))) {
							codeimg = mapImg.get("img").toString();
						}
						List<Orders> orderlist2 = orderService.getOrderByGroupCode(groupNum);
						for (Orders orders3 : orderlist2) {
							orders3.setQrcode(codeimg);
							ordersMapper.updateByPrimaryKey(orders3);
						}
					}
				}else{
					updateStatus(groupNum, OrderStatusEnum.审核中.getValue());
				}
			}
		}
//		List<Orders> orderlist2 = orderService.getOrderByGroupCode(groupNum);
//		for (Orders orders2 : orderlist2) {
//			orders2.setReqcount(orders2.getReqcount() + 1);
//			ordersMapper.updateByPrimaryKey(orders2);
//		}
		return 1;
	}

	@Override
	public int updateStatus(Integer orderid, Integer status, int userid) throws Exception {
		return ordersMapper.updateStatus(status, orderid);
	}

	@Override
	public int addBalance(String out_trade_no, String trade_no, String total_fee, ReusltItem item) throws Exception {
		double totalmoney = Double.valueOf(total_fee);
		// 本地用户资金处理
		Userfinance finance = userfinanceMapper.getListByNumberAndType(out_trade_no, CapitalChange_Type.充值.getValue());
		if (finance == null) {
			LogHandle.error(LogType.Order, "为查找到充值记录", "/order/addBalance");
			return 0;
		}
		Usercapital usercapital = usercapitalMapper.getBalanceRowLockById(finance.getUserid());
		if (usercapital == null) {
			item.setCode(-404);
			item.setDesc("未查询到用户资金数据");
			LogHandle.error(LogType.Order, "订单付款未查询到资金数据", "/order/addBalance");
			return -1;
		}
		usercapital.setBalance(usercapital.getBalance() + totalmoney);
		usercapitalMapper.updateByPrimaryKey(usercapital);

		if (finance != null && finance.getStatus().equals(UserFinance_Type.未支付.getValue())) {
			finance.setBalance(usercapital.getBalance());
			finance.setStatus(UserFinance_Type.已支付.getValue());
			finance.setPaynum(trade_no);
			userfinanceMapper.updateByPrimaryKey(finance);
		} else {
			LogHandle.error(LogType.Order, "已支付过，不能重新支付", "/order/addBalance");
			return 0;
		}
		return 1;
	}

	@Override
	public int addBalancetoBond(double total_fee, Integer userid, ReusltItem item) throws Exception {
		Usercapital usercapital = usercapitalMapper.getBalanceRowLockById(userid);
		if (usercapital == null) {
			LogHandle.error(LogType.UserCapital, "资金表无对应用户数据，用户ID：" + userid, "order/addBalancetoBond");
			return 0;
		}
		if (total_fee > usercapital.getBalance()) {
			LogHandle.error(LogType.UserCapital, "用户余额不足，用户ID：" + userid, "order/addBalancetoBond");
			return 0;
		}
		BigDecimal b1 = new BigDecimal(total_fee);
		BigDecimal b2 = new BigDecimal(usercapital.getBalance());
		BigDecimal b3 = new BigDecimal(usercapital.getBond());
		usercapital.setBalance(b3.add(b1).doubleValue());
		usercapital.setBond(b2.subtract(b1).doubleValue());
		usercapitalMapper.updateByPrimaryKey(usercapital);
		financeAdd(userid, usercapital.getBalance(), total_fee, "订单余额转入保证金", "", "",
				CapitalChange_Type.余额转入保证金.getValue(), "", UserFinance_Type.已支付.getValue());

		return 1;
	}

	/**
	 * 申请退款
	 * 
	 * @param orderdetailid
	 * @param userid
	 * @param reason
	 * @param item
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateApplyTK(Integer orderdetailid, Integer userid, String reason, BaseResult item) throws Exception {

		Orderdetail detail = orderdetailMapper.selectByPrimaryKey(orderdetailid);
		if (detail == null) {
			LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单ID：" + orderdetailid, "order/updateApplyTK");
			item.setCode(-401);
			return 0;
		}
		if (!detail.getStatus().equals(OrderDetailStatusEnum.待使用.getValue())
				&& !detail.getStatus().equals(OrderDetailStatusEnum.退款失败.getValue())) {
			LogHandle.error(LogType.Order, "订单状态不能进行退款申请。子订单ID：" + orderdetailid + ",状态：" + detail.getStatus(),
					"order/updateApplyTK");
			item.setCode(-401);
			return 0;
		}
		// 申请退款接口调起
		StringBuffer stringMsg = new StringBuffer();
		stringMsg.append("<returnTicket>");
		stringMsg.append("<orderCode>" + detail.getProcode() + "</orderCode>"); // 子订单号
		stringMsg.append("<returnNum>" + detail.getProductcount() + "</returnNum>"); // 退票数量
		stringMsg.append("<thirdReturnCode></thirdReturnCode>"); // 第三方退单号
		stringMsg.append("<idCards></idCards>"); // 如果是实名制订单退票，请带上身份证号码多个身份证号中间用
													// “,”分割
		stringMsg.append("</returnTicket>"); //
		String retmsg;

		String[] temp = XmlUint.sendPost("RETURN_TICKET_NUM_NEW_REQ", stringMsg.toString());
		if (temp[1].equals("1")) {
			return -1;
		}
		retmsg = temp[0];
		Map<String, Object> map = XmlUint.parserValue(retmsg);
		if ("0".equals(map.get("code"))) {
			// 订单申请记录
			Applyforcancelorder apply = new Applyforcancelorder();
			apply.setContent(reason);
			apply.setCreatetime(new Date());
			apply.setOrderid(detail.getId());
			apply.setOrderstatus(detail.getStatus());
			apply.setType(OrderApplyTypeEnum.订单退款.getValue());
			apply.setUserid(userid);
			apply.setOrdernum(detail.getProcode());
			apply.setImgurl("");
			apply.setDatatype(0);// 数据类型 0申请售后数据 1卖家审核数据 2平台处理数据
			applyfororderMapper.insert(apply);
			// 修改订单状态
			orderdetailMapper.updateStatus(OrderDetailStatusEnum.申请退款.getValue(), detail.getId());
		} else {
			LogHandle.error(LogType.Order,
					"向票务发起退款申请失败。子订单ID：" + orderdetailid + ",状态：" + detail.getStatus() + ",错误码：" + map.get("code"),
					"order/updateApplyTK");
			item.setDesc("向票务发起退款申请失败");
			item.setCode(-401);
			return 0;
		}
		return 1;
	}

	/**
	 * 核销通知
	 * 
	 * @param orderdetailid
	 * @param reason
	 * @param item
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateDetailUse(String detailcode, BaseResult item) throws Exception {

		Orderdetail detail = orderdetailMapper.getDetailsByProcode(detailcode);
		if (detail == null) {
			LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单编号：" + detailcode, "order/updateDetailUse");
			item.setCode(-401);
			return 0;
		}
		if (!detail.getStatus().equals(OrderDetailStatusEnum.待使用.getValue())) {
			LogHandle.error(LogType.Order, "订单状态不能进行核销操作。子订单编号：" + detailcode + ",状态：" + detail.getStatus(),
					"order/updateDetailUse");
			item.setCode(-401);
			return 0;
		}
		// 以下是卖家操作
		int sellerid = detail.getSellerid();// 卖家id
		Usercapital sellerUsercapital = usercapitalMapper.getBalanceRowLockById(sellerid);
		if (sellerUsercapital == null) {
			item.setCode(-404);
			item.setDesc("未查询到卖家用户资金数据");
			LogHandle.error(LogType.Order, MessageFormat.format("订单付款未查询到卖家资金数据，错误详情：{0}", item.getDesc()));
			return -1;
		}
		Double rs = detail.getProductprice().multiply(BigDecimal.valueOf(detail.getProductcount())).doubleValue();
		sellerUsercapital.setFreezemoney(sellerUsercapital.getFreezemoney() - rs);
		sellerUsercapital.setBalance(sellerUsercapital.getBalance() + rs);
		usercapitalMapper.updateByPrimaryKeySelective(sellerUsercapital); // 增加卖家的冻结资金
		// 资金变动记录 分店铺 1个店铺 1条
		financeAdd(sellerid, sellerUsercapital.getBalance(), rs, "订单核销卖家余额增加", "", "", CapitalChange_Type.收入.getValue(),
				detail.getProcode(), UserFinance_Type.已支付.getValue());
		// 修改订单状态
		orderdetailMapper.updateStatus(OrderDetailStatusEnum.已使用.getValue(), detail.getId());
		return 1;
	}

	/**
	 * 退款通知
	 * 
	 * @param orderdetailid
	 * @param userid
	 * @param reason
	 * @param item
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateDetailTK(String detailcode, String status, BaseResult item) throws Exception {

		Orderdetail detail = orderdetailMapper.getDetailsByProcode(detailcode);
		if (detail == null) {
			LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单编号：" + detailcode, "order/updateDetailTK");
			item.setCode(-401);
			item.setDesc("子订单编号错误");
			return 0;
		}
		if (!detail.getStatus().equals(OrderDetailStatusEnum.待使用.getValue())
				&& !detail.getStatus().equals(OrderDetailStatusEnum.申请退款.getValue())) {
			LogHandle.error(LogType.Order, "订单状态不能进行退款操作。子订单编号：" + detailcode + ",状态：" + detail.getStatus(),
					"order/updateDetailTK");
			item.setCode(-401);
			item.setDesc("订单状态不能进行退款操作");
			return 0;
		}
		// 修改订单状态
		if (status.equals("success")) {
			orderdetailMapper.updateStatus(OrderDetailStatusEnum.审核中.getValue(), detail.getId());
		} else {
			orderdetailMapper.updateStatus(OrderDetailStatusEnum.退款失败.getValue(), detail.getId());
		}

		return 1;
	}

	/**
	 * 主订单完结通知
	 * 
	 * @param groupcode
	 * @param item
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateFinishOrder(String groupcode, BaseResult item) throws Exception {

		List<Orders> orderlist = orderService.getOrderByGroupCode(groupcode);
		if (orderlist == null) {
			item.setCode(-401);
			item.setDesc("组订单号错误");
			LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", groupcode));
			return -1;
		}
		for (Orders orders : orderlist) {
			List<Orderdetail> orderdetails = orderdetailMapper.getDetailsByOrderID(orders.getId());
			for (Orderdetail orderdetail : orderdetails) {
				if (!orderdetail.getStatus().equals(OrderDetailStatusEnum.已使用.getValue())
						&& !orderdetail.getStatus().equals(OrderDetailStatusEnum.退款成功.getValue())) {
					LogHandle.error(LogType.Order,
							"订单状态不能进行完结操作。子订单ID：" + orderdetail.getId() + ",状态：" + orderdetail.getStatus(),
							"order/orderFinish");
					item.setCode(-401);
					item.setDesc("订单状态不能进行完结操作");
					return 0;
				}
			}
			ordersMapper.updateStatus(OrderStatusEnum.已完结.getValue(), orders.getId());
		}
		return 1;
	}

	@Override
	public int updatePartTK(Integer orderdetailid, Integer userid, ReusltItem item) throws Exception {
		Orderdetail detail = orderdetailMapper.selectByPrimaryKey(orderdetailid);
		if (detail == null) {
			LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单ID：" + orderdetailid, "order/updateDetailTK");
			item.setCode(-401);
			item.setDesc("子订单编号错误");
			return 0;
		}
		if (!detail.getStatus().equals(OrderDetailStatusEnum.审核中.getValue())) {
			LogHandle.error(LogType.Order, "订单状态不能进行退款操作。子订单ID：" + orderdetailid + ",状态：" + detail.getStatus(),
					"order/updatePartTK");
			item.setCode(-401);
			item.setDesc("订单状态不能进行退款操作");
			return 0;
		}
		Orders orders = ordersMapper.getOrderByID(detail.getOrderid());
		Userfinance userfinance = userfinanceMapper.getListByNumberAndType(orders.getGroupcode(),
				CapitalChange_Type.支出.getValue());
		if (!orders.getPaytype().equals(PayTypeEnum.优惠券支付.getValue()) 
				&& (userfinance == null || !userfinance.getUserid().equals(orders.getBuyerid()))) {
			LogHandle.error(LogType.Order, "订单资金记录异常，不能进行退款操作。子订单ID：" + orderdetailid + ",组编号：" + orders.getGroupcode(),
					"order/updatePartTK");
			item.setCode(-401);
			item.setDesc("订单资金记录异常，不能进行退款操作");
			return 0;
		}
		Userfinance userfinancepro = userfinanceMapper.getListByNumberAndType(detail.getProcode(),
				CapitalChange_Type.收入.getValue());
		if (!orders.getPaytype().equals(PayTypeEnum.优惠券支付.getValue()) 
				&& (userfinancepro == null || !userfinancepro.getUserid().equals(detail.getBuyerid()))) {
			LogHandle.error(LogType.Order, "订单资金记录异常，不能进行退款操作。子订单ID：" + orderdetailid + ",商品订单号：" + detail.getProcode(),
					"order/updatePartTK");
			item.setCode(-401);
			item.setDesc("订单资金记录异常，不能进行退款操作");
			return 0;
		}
		List<Orders> orderlist = orderService.getOrderByGroupCode(orders.getGroupcode());
		if (orderlist == null) {
			item.setCode(-403);
			item.setDesc("组订单号错误");
			LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", orders.getGroupcode()));
			return 0;
		}
		Double ss = orderlist.stream().mapToDouble(x -> x.getActualpay().doubleValue()).sum();
		Double rs = detail.getProductprice().multiply(BigDecimal.valueOf(detail.getProductcount())).doubleValue();
		ss=BigDecimal.valueOf(ss).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		rs=BigDecimal.valueOf(rs).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		int couponid=0;
		if(orders.getCouponid()!=null && orders.getCouponid()>0){
			Coupon coupon=couponService.getByID(orders.getCouponid());
			if(coupon.getCouponusetype()==0){
				//针对商品
				Spu spu=spuMapper.selectByPrimaryKey(skuMapper.selectByPrimaryKey(detail.getSkuId()).getSpuId());
				if(spu.getId().equals(coupon.getUsetypeid())){
					if(coupon.getFacevalue()>rs){
						//付款为0
						rs=0.0d;
					}else{
						rs=rs-coupon.getFacevalue();
					}
					couponid=coupon.getId();
				}
			}else if(coupon.getCouponusetype()==2){
				//针对店铺
				rs=BigDecimal.valueOf(orders.getActualpay().doubleValue()*rs).divide(orders.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}else if(coupon.getCouponusetype()==3){
			    //全场通用
				rs=BigDecimal.valueOf(orders.getActualpay().doubleValue()*rs).divide(orders.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    }
		}
		rs=BigDecimal.valueOf(rs).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if(rs==0){
			List<Integer> couponids=new ArrayList<Integer>();
			couponids.add(couponid);
			couponService.updateUCouponCancel(couponids, orders.getBuyerid());
		}else{
			if (orders.getPaytype().equals(PayTypeEnum.支付宝支付.getValue())) {
				// 支付宝原路返回
				String retmsg = AlipayReturnUtil.alipayRefundRequest(orders.getGroupcode(), userfinance.getPaynum(), rs);
				if ("10000".equals(retmsg)) {
					// 以下是卖家操作
					int sellerid = detail.getSellerid();// 卖家id
					Usercapital sellerUsercapital = usercapitalMapper.getBalanceRowLockById(sellerid);
					if (sellerUsercapital == null) {
						item.setCode(-404);
						item.setDesc("未查询到卖家用户资金数据");
						LogHandle.error(LogType.Order, MessageFormat.format("订单付款未查询到卖家资金数据，错误详情：{0}", item.getDesc()));
						return -1;
					}
					sellerUsercapital.setFreezemoney(sellerUsercapital.getFreezemoney() - rs);
					usercapitalMapper.updateByPrimaryKeySelective(sellerUsercapital);
					// 退款成功 处理订单状态 添加资金记录
					financeAdd(orders.getBuyerid(), rs, rs, "会员支付宝退款", userfinance.getPaynum(), "",
							CapitalChange_Type.退款返还.getValue(), orders.getGroupcode(), UserFinance_Type.已支付.getValue());
					// 店铺
					financeAdd(orders.getSellerid(), rs, rs, "商家扣款", userfinance.getPaynum(), "",
							CapitalChange_Type.退款扣除.getValue(), detail.getProcode(), UserFinance_Type.已支付.getValue());

					orderdetailMapper.updateStatus(OrderDetailStatusEnum.退款成功.getValue(), detail.getId());
					//库存恢复
					updproStock(detail.getId());
				} else {
					item.setCode(-404);
					item.setDesc(retmsg);
					LogHandle.error(LogType.Order, "支付宝退款请求失败:" + retmsg, "order/updatePartTK");
					return 0;
				}
			} else if (orders.getPaytype().equals(PayTypeEnum.微信支付.getValue())) {
				// 微信原路返回

				String[] errordesc = new String[1];
				int refund = PaymentUtil.refund(orders.getGroupcode(), ss.toString(), rs.toString(),
						userfinance.getPaynum(), errordesc);
				if (refund > 0) {
					// 以下是卖家操作
					int sellerid = detail.getSellerid();// 卖家id
					Usercapital sellerUsercapital = usercapitalMapper.getBalanceRowLockById(sellerid);
					if (sellerUsercapital == null) {
						item.setCode(-404);
						item.setDesc("未查询到卖家用户资金数据");
						LogHandle.error(LogType.Order, MessageFormat.format("订单付款未查询到卖家资金数据，错误详情：{0}", item.getDesc()));
						return -1;
					}
					sellerUsercapital.setFreezemoney(sellerUsercapital.getFreezemoney() - rs);
					usercapitalMapper.updateByPrimaryKeySelective(sellerUsercapital);
					// 退款成功 处理订单状态 添加资金记录
					financeAdd(orders.getBuyerid(), rs, rs, "会员微信退款", userfinance.getPaynum(), "",
							CapitalChange_Type.退款返还.getValue(), orders.getGroupcode(), UserFinance_Type.已支付.getValue());
					// 店铺
					financeAdd(orders.getSellerid(), rs, rs, "商家扣款", userfinance.getPaynum(), "",
							CapitalChange_Type.退款扣除.getValue(), detail.getProcode(), UserFinance_Type.已支付.getValue());

					orderdetailMapper.updateStatus(OrderDetailStatusEnum.退款成功.getValue(), detail.getId());
					
					//库存恢复
					updproStock(detail.getId());
					// ordersMapper.updateStatus(OrderStatusEnum.已完结.getValue(),
					// detail.getOrderid());
				} else {
					item.setCode(-405);
					item.setDesc("微信退款请求失败");
					LogHandle.error(LogType.Order, "微信退款请求失败:" + errordesc[0], "order/updatePartTK");
					return 0;
				}
			}
			if(couponid>0){
				List<Integer> couponids=new ArrayList<Integer>();
				couponids.add(couponid);
				couponService.updateUCouponCancel(couponids, orders.getBuyerid());
			}
		}
		
		List<Orderdetail> odList=orderdetailMapper.getDetailsByOrderID(orders.getId());
		List<Orderdetail> odListtk=odList.stream().filter(x->x.getStatus()!=null 
				&& x.getStatus().equals(OrderDetailStatusEnum.退款成功.getValue())).collect(Collectors.toList());
		if(odList.size()==odListtk.size()){
			ordersMapper.updateStatus(OrderStatusEnum.已完结.getValue(), orders.getId());
		}
		return 1;
	}

	@Override
	public int updateOrderTK(String groupnum, Integer userid, ReusltItem item) throws Exception {
		List<Orders> orderlist = orderService.getOrderByGroupCode(groupnum);
		if (orderlist == null) {
			item.setCode(-403);
			item.setDesc("组订单号错误");
			LogHandle.error(LogType.Order, MessageFormat.format("组订单号错误，组订单号：{0}", groupnum));
			return 0;
		}
		Orders orders = orderlist.get(0);
		if (!orders.getStatus().equals(OrderStatusEnum.审核中.getValue())) {
			LogHandle.error(LogType.Order, "订单状态不能进行退款操作。订单号：" + groupnum + ",状态：" + orders.getStatus(),
					"order/updateOrderTK");
			item.setCode(-401);
			item.setDesc("订单状态不能进行退款操作");
			return 0;
		}
		Double ss = orderlist.stream().mapToDouble(x -> x.getActualpay().doubleValue()).sum();
		Userfinance userfinance = userfinanceMapper.getListByNumberAndType(orders.getGroupcode(),
				CapitalChange_Type.支出.getValue());
		if (ss>0 && (userfinance == null || !userfinance.getUserid().equals(orders.getBuyerid()))) {
			LogHandle.error(LogType.Order, "订单资金记录异常，不能进行退款操作。组编号：" + "" + orders.getGroupcode(),
					"order/updateOrderTK");
			item.setCode(-401);
			item.setDesc("订单资金记录异常，不能进行退款操作");
			return 0;
		}
		ss=BigDecimal.valueOf(ss).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if (orders.getPaytype().equals(PayTypeEnum.支付宝支付.getValue())) {
			String retmsg = AlipayReturnUtil.alipayRefundRequest(orders.getGroupcode(), userfinance.getPaynum(), ss);
			if ("10000".equals(retmsg)) {
				// 退款成功 处理订单状态 添加资金记录
				financeAdd(orders.getBuyerid(), ss, ss, "会员退款", userfinance.getPaynum(), "",
						CapitalChange_Type.退款返还.getValue(), orders.getGroupcode(), UserFinance_Type.已支付.getValue());
				for (Orders ord : orderlist) {
					// 以下是卖家操作
					int sellerid = ord.getSellerid();// 卖家id
					Usercapital sellerUsercapital = usercapitalMapper.getBalanceRowLockById(sellerid);
					if (sellerUsercapital == null) {
						item.setCode(-404);
						item.setDesc("未查询到卖家用户资金数据");
						LogHandle.error(LogType.Order, MessageFormat.format("订单付款未查询到卖家资金数据，错误详情：{0}", item.getDesc()));
						return -1;
					}
					sellerUsercapital
							.setFreezemoney(sellerUsercapital.getFreezemoney() - ord.getActualpay().doubleValue());
					usercapitalMapper.updateByPrimaryKeySelective(sellerUsercapital);
					// 店铺
					financeAdd(orders.getSellerid(), ord.getActualpay().doubleValue(), ord.getActualpay().doubleValue(),
							"商家扣款", userfinance.getPaynum(), "", CapitalChange_Type.退款扣除.getValue(), ord.getCode(),
							UserFinance_Type.已支付.getValue());
					ordersMapper.updateStatus(OrderStatusEnum.已完结.getValue(), ord.getId());
					
					List<Orderdetail> odList=orderdetailMapper.getDetailsByOrderID(ord.getId());
					for (Orderdetail orderdetail : odList) {
						orderdetailMapper.updateStatus(OrderDetailStatusEnum.退款成功.getValue(), orderdetail.getId());
						updproStock(orderdetail.getId());
					}
				}
			} else {
				item.setCode(-405);
				item.setDesc("支付宝退款请求失败");
				LogHandle.error(LogType.Order, "支付宝退款请求失败:" + retmsg, "order/updateOrderTK");
				return 0;
			}
		} else if (orders.getPaytype().equals(PayTypeEnum.微信支付.getValue())) {
			// 微信原路返回
			String[] errordesc = new String[1];
			int refund = PaymentUtil.refund(orders.getGroupcode(), ss.toString(), ss.toString(),
					userfinance.getPaynum(), errordesc);
			if (refund > 0) {
				// 退款成功 处理订单状态 添加资金记录
				financeAdd(orders.getBuyerid(), ss, ss, "会员退款", userfinance.getPaynum(), "",
						CapitalChange_Type.退款返还.getValue(), orders.getGroupcode(), UserFinance_Type.已支付.getValue());
				for (Orders ord : orderlist) {
					// 以下是卖家操作
					int sellerid = ord.getSellerid();// 卖家id
					Usercapital sellerUsercapital = usercapitalMapper.getBalanceRowLockById(sellerid);
					if (sellerUsercapital == null) {
						item.setCode(-404);
						item.setDesc("未查询到卖家用户资金数据");
						LogHandle.error(LogType.Order, MessageFormat.format("订单付款未查询到卖家资金数据，错误详情：{0}", item.getDesc()));
						return -1;
					}
					sellerUsercapital
							.setFreezemoney(sellerUsercapital.getFreezemoney() - ord.getActualpay().doubleValue());
					usercapitalMapper.updateByPrimaryKeySelective(sellerUsercapital);
					// 店铺
					financeAdd(orders.getSellerid(), ord.getActualpay().doubleValue(), ord.getActualpay().doubleValue(),
							"商家扣款", userfinance.getPaynum(), "", CapitalChange_Type.退款扣除.getValue(), ord.getCode(),
							UserFinance_Type.已支付.getValue());
					ordersMapper.updateStatus(OrderStatusEnum.已完结.getValue(), ord.getId());
					
					List<Orderdetail> odList=orderdetailMapper.getDetailsByOrderID(ord.getId());
					for (Orderdetail orderdetail : odList) {
						orderdetailMapper.updateStatus(OrderDetailStatusEnum.退款成功.getValue(), orderdetail.getId());
						//库存返回
						updproStock(orderdetail.getId());
					}
				}

			} else {
				item.setCode(-405);
				item.setDesc("微信退款请求失败");
				LogHandle.error(LogType.Order, "微信退款请求失败:" + errordesc[0], "order/updateOrderTK");
				return 0;
			}
		}else if (orders.getPaytype().equals(PayTypeEnum.优惠券支付.getValue())) {
			for (Orders ord : orderlist) {
				ordersMapper.updateStatus(OrderStatusEnum.已完结.getValue(), ord.getId());
				
				List<Orderdetail> odList=orderdetailMapper.getDetailsByOrderID(ord.getId());
				for (Orderdetail orderdetail : odList) {
					orderdetailMapper.updateStatus(OrderDetailStatusEnum.退款成功.getValue(), orderdetail.getId());
					//库存返回
					updproStock(orderdetail.getId());
				}
			}
		}
		int couponid=0;
		for (Orders ors : orderlist) {
			//如果使用优惠券则退回
			if(ors.getCouponid()!=null && ors.getCouponid()>0){
				if(couponid==ors.getCouponid())break;
				couponid=ors.getCouponid();
				List<Integer> couponids=new ArrayList<Integer>();
				couponids.add(ors.getCouponid());
				couponService.updateUCouponCancel(couponids, ors.getBuyerid());
			}
        }
		return 1;
	}

	@Override
	public ApplyAfterDto getApplyDetail(Integer orderdetailid) throws Exception {
		return ordersMapper.getApplyDetail(orderdetailid);
	}

	public int updateStatus(String gnum, Integer status) throws Exception {
		List<Orders> orderlist = orderService.getOrderByGroupCode(gnum);
		for (Orders orders : orderlist) {
			List<Orderdetail> details1 = orderdetailMapper.getDetailsByOrderID(orders.getId());
			for (Orderdetail y : details1) {
//				y.setStatus(status);
//				orderdetailMapper.updateByPrimaryKey(y);
				orderdetailMapper.updateStatus(status, y.getId());
			}
		}
		return ordersMapper.updateStatusByGroupcode(status, gnum);
	}

	@Override
	public int UntickOrdersStatus() throws Exception {
		List<Orders> olist=ordersMapper.getUntickOrders();
		String couponids=",";
		if(olist!=null && olist.size()>0){
			for (Orders ord : olist) {
				ordersMapper.updateStatus(OrderStatusEnum.审核中.getValue(), ord.getId());
				List<Orderdetail> details1 = orderdetailMapper.getDetailsByOrderID(ord.getId());
				for (Orderdetail y : details1) {
//					y.setStatus(OrderStatusEnum.审核中.getValue());
//					orderdetailMapper.updateByPrimaryKey(y);
					orderdetailMapper.updateStatus(OrderStatusEnum.审核中.getValue(), y.getId());
				}
				//如果使用优惠券则退回
				if(ord.getCouponid()!=null && ord.getCouponid()>0){
					if(couponids==","){
						couponids+=ord.getCouponid()+",";
					}else{
						if(couponids.contains(","+ord.getCouponid()+","))break;
					}
					List<Integer> couponidlist=new ArrayList<Integer>();
					couponidlist.add(ord.getCouponid());
					couponService.updateUCouponCancel(couponidlist, ord.getBuyerid());
				}
				
			}
		}
		return 1;
	}

	@Override
	public void updateInvalidOrder(List<Orders> orders) throws Exception {
		String couponids=",";
		for(Orders ord : orders){
			ordersMapper.updateStatus(ord.getId(), OrderStatusEnum.已取消.getValue());
	    	orderdetailMapper.cancelOrder(ord.getId(),OrderStatusEnum.已取消.getValue());
			//如果使用优惠券则退回
			if(ord.getCouponid()!=null && ord.getCouponid()>0){
				if(couponids==","){
					couponids+=ord.getCouponid()+",";
				}else{
					if(couponids.contains(","+ord.getCouponid()+","))break;
				}
				List<Integer> couponidlist=new ArrayList<Integer>();
				couponidlist.add(ord.getCouponid());
				couponService.updateUCouponCancel(couponidlist, ord.getBuyerid());
			}
	     }
	}

	@Override
	public int updproStock(Integer orderdetailid) throws Exception {
		Orderdetail detail = orderdetailMapper.selectByPrimaryKey(orderdetailid);
		if (detail == null) {
			LogHandle.error(LogType.Order, "根据子订单ID未能检索到数据。子订单ID：" + orderdetailid, "order/updproStock");
			return 0;
		}
		
		Integer year=DateUtil.getYear(detail.getUsetime());
		Integer month=DateUtil.getMonth(detail.getUsetime());
		Integer day=DateUtil.getDay(detail.getUsetime());
		
		SkuShowtime  showtime=skuShowtimeMapper.getSkuTimeBySkuid(detail.getSkuId(), year, month, day);
		if(showtime!=null){
			showtime.setStock(showtime.getStock()+detail.getProductcount());
			skuShowtimeMapper.updateByPrimaryKey(showtime);
		}
		return 1;
	}

	@Override
	public void sendOrderReq() throws Exception {
		List<Orders> olist=ordersMapper.getUntickOrdersReq();
        //循环下单通知
		if(olist!=null && olist.size()>0){
			// 根据组订单号分组
			Map<String, List<Orders>> groupList = olist.stream().collect(
					Collectors.groupingBy(Orders::getGroupcode));
			// 遍历
			for (Map.Entry<String, List<Orders>> entry : groupList
					.entrySet()) {
				String key = entry.getKey();
				String groupNum= entry.getKey();
				List<Orders> po=entry.getValue();
				StringBuffer stringBuffer3 = new StringBuffer();
				Double totalprice=po.stream().mapToDouble(x->x.getActualpay().doubleValue()).sum();
				for (Orders orders2 : po) {
					List<Orderdetail> details = orderdetailMapper.getDetailsByOrderID(orders2.getId());
					List<Integer> ids = new ArrayList<Integer>();
					Hashtable<Integer, Integer> Orderdetaildic = new Hashtable<Integer, Integer>();
					for (Orderdetail x : details) {
						ids.add(x.getSkuId());
						Orderdetaildic.put(x.getSkuId(), x.getProductcount());
						Sku sku = skuMapper.selectByPrimaryKey(x.getSkuId());
						// 对接票务
						stringBuffer3.append("<ticketOrder>");
						stringBuffer3.append("<orderCode>"+x.getProcode()+"</orderCode>");// B
						stringBuffer3.append("<price>"+x.getProductprice()+"</price>");
						stringBuffer3.append("<quantity>"+x.getProductcount()+"</quantity>");
						stringBuffer3.append("<totalPrice>"+x.getProductprice().multiply(new BigDecimal(x.getProductcount()))+"</totalPrice>");
						stringBuffer3.append("<occDate>"+DateUtil.datePattren(x.getUsetime())+"</occDate>");
						stringBuffer3.append("<goodsCode>"+sku.getTicketnum()+"</goodsCode>");//PST20160918013085 "+sku.getTicketnum()+"
						stringBuffer3.append("<goodsName>"+x.getProductname()+"</goodsName>");
						stringBuffer3.append("<remark>测试环境用上述参数</remark>");//测试环境用上述参数  "+sku.getRemark()+"
						stringBuffer3.append("</ticketOrder>");
					}
				}
				String price=new BigDecimal(totalprice).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				// 对接票务
				Idcardinfo idcardinfo1 = idcardinfoMapper.getByGroupCode(groupNum);
				String req=XmlFuncUtils.sendCodeReq(groupNum, price, idcardinfo1, stringBuffer3.toString());
				String retmsg = "";
				LogHandle.info(LogType.Order, "票务对接下单参数：" + req + ",gnum:" + groupNum,
						"/sendOrderReq");
				String[] temp = XmlUint.sendPost("SEND_CODE_REQ", req);
				Map<String, Object> map = new HashMap<>();
				if (temp[1].equals("1")) {
					//updateStatus(groupNum, OrderStatusEnum.审核中.getValue());
					map.put("code", "-1");
				} else {
					retmsg = temp[0];
					map = XmlUint.parserValue(retmsg);
				}
				// retmsg=temp[0];

				LogHandle.info(LogType.Order, "出票中票务对接下单返回：" + retmsg + ",gnum:" + groupNum,
						"/sendOrderReq");
				// String retmsg=
				// XmlUint.sendPost("SEND_CODE_REQ",stringBuffer.toString());
				// 如果成功code=0 订单状态 主订单 :代付款-待使用 子订单:待使用
				// 如果不成功code!=0 订单状态 主订单 :代付款-审核中
				List<Orders> orderlist1 = orderService.getOrderByGroupCode(groupNum);
				// Map<String ,Object> map = XmlUint.parserValue(retmsg);
				// 订单二维码地址
				String codeimg = "";
				if ("0".equals(map.get("code"))) {
					// 下单成功发送信息
					XmlUint.sendPost("SEND_SM_REQ", XmlFuncUtils.sendSmReq(groupNum));
					// 获取二维码
					// String retImg ="";
					temp = XmlUint.sendPost("QUERY_IMG_URL_REQ", XmlFuncUtils.queryImgUrlReq(groupNum));
					map = new HashMap<>();
					if (temp[1].equals("1")) {
						map.put("code", "-1");
					} else {
						retmsg = temp[0];
						map = XmlUint.parserValue(retmsg);
					}
					Map<String, Object> mapImg = map;
					if ("0".equals(mapImg.get("code"))) {
						codeimg = mapImg.get("img").toString();
					}
					for (Orders orders1 : orderlist1) {
						orders1.setStatus(OrderStatusEnum.待使用.getValue());
						orders1.setQrcode(codeimg);
						ordersMapper.updateByPrimaryKey(orders1);
						List<Orderdetail> details1 = orderdetailMapper.getDetailsByOrderID(orders1.getId());
						for (Orderdetail y : details1) {
							orderdetailMapper.updateStatus(OrderDetailStatusEnum.待使用.getValue(), y.getId());
						}
					}
				} else if ("6".equals(map.get("code")) && orderlist1.get(0).getStatus().equals(OrderStatusEnum.出票中.getValue())) {
					if(map.get("description").toString().contains("要保存的订单对象已存在，请检查")){
						// 下单重复
						LogHandle.info(LogType.Order, "出票中下单重复票务对接下单返回：" + retmsg + ",gnum:" + groupNum,
								"/sendOrderReq");
						temp = XmlUint.sendPost("QUERY_ORDER_REQ", XmlFuncUtils.queryCodeReq(groupNum));
						map = new HashMap<>();
						if (temp[1].equals("1")) {
							map.put("code", "-1");
						} else {
							retmsg = temp[0];
							map = XmlUint.parserValue(retmsg);
						}
						Map<String, Object> mapre = map;
						if ("0".equals(mapre.get("code"))) {
							for (Orders orders1 : orderlist1) {
								orders1.setStatus(OrderStatusEnum.待使用.getValue());
								ordersMapper.updateByPrimaryKey(orders1);
								List<Orderdetail> details1 = orderdetailMapper.getDetailsByOrderID(orders1.getId());
								for (Orderdetail y : details1) {
									orderdetailMapper.updateStatus(OrderDetailStatusEnum.待使用.getValue(), y.getId());
								}
							}
							XmlUint.sendPost("SEND_SM_REQ",  XmlFuncUtils.sendSmReq(groupNum));
							// 获取二维码
							temp = XmlUint.sendPost("QUERY_IMG_URL_REQ", XmlFuncUtils.queryImgUrlReq(groupNum));
							map = new HashMap<>();
							if (temp[1].equals("1")) {
								map.put("code", "-1");
							} else {
								retmsg = temp[0];
								map = XmlUint.parserValue(retmsg);
							}
							Map<String, Object> mapImg = map;
							
							if ("0".equals(mapImg.get("code"))) {
								codeimg = mapImg.get("img").toString();
							}
							List<Orders> orderlist2 = orderService.getOrderByGroupCode(groupNum);
							for (Orders orders3 : orderlist2) {
								orders3.setQrcode(codeimg);
								ordersMapper.updateByPrimaryKey(orders3);
							}
						}
					}else{
						updateStatus(groupNum, OrderStatusEnum.审核中.getValue());
					}
				}
			}
		}
	}
	 
}

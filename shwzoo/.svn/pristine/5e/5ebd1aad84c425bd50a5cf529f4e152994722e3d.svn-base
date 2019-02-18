package com.yinlian.wssc.web.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.yinlian.Enums.PointsRecordsFromTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.po.OrderBackup;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.CartService;
import com.yinlian.wssc.web.service.OrderBackupService;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yl.soft.log.LogHandle;

public class OrderUtils {

	static ConcurrentLinkedQueue<QData> queue = new ConcurrentLinkedQueue<QData>();
	private static OrderUtils _BackOrder;
	private CartService cartService;

	public void set(CartService cartService) {
		if (this.cartService == null)
			this.cartService = cartService;
	}

	private PointsRecordService pointsRecordService;

	public void set(PointsRecordService pointsRecordService) {
		if (this.pointsRecordService == null)
			this.pointsRecordService = pointsRecordService;
	}

	private OrderBackupService orderBackupService;

	public void set(OrderBackupService orderBackupService) {
		if (this.orderBackupService == null)
			this.orderBackupService = orderBackupService;
	}

	public void add(List<Orders> orders, String scids, int points, int userId) {
		QData qData = new QData();
		qData.orders = orders;
		qData.scids = scids;
		qData.points = points;
		qData.userId = userId;
		while (true) {
			if (queue.offer(qData))
				break;
		}
		if (myThread == null) {
			try {
				myThread = new MyThread();
				if(!myThread.isAlive())	{	
					myThread.start();
					}
			} catch (Exception e) {
			}
		
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					if (myThread != null) {
						myThread.istop = true;
					}
				}
			});
		}
	}

	MyThread myThread = null;

	class QData {
		public List<Orders> orders;
		public String scids;
		public int points;
		public int userId;
	}

	class MyThread extends Thread {

		public boolean istop = false;

		public void run() {
			while (true) {
				if (!queue.isEmpty()) {
					QData qData = queue.poll();
					if(qData==null)
						continue;
					try { // 订单备份信息
						addOrderBackup(qData.orders);
						// 订单删除购物车信息
						if (StringUtils.isNotNull(qData.scids)) {
							String[] scidss = qData.scids.split(",");
							for (String scid : scidss) {
								cartService.deleteByID(StringUtilsEX.ToInt(scid));
							}
							// shoppingCartService.deleteByBuyerID(userId);
						}
						if (qData.points > 0) {
							pointsRecordService.updateUserPoint(qData.points, qData.userId,
									PointsRecordsFromTypeEnum.订单消费.getValue(), PointsRecordsTypeEnum.消费.getValue());
						}
					} catch (Exception exception) {
						LogHandle.error(LogType.Api, "创建订单（订单备份信息和删除购物车信息错误：{0}", exception,
								"OrderServiceBaseImpl/add");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					}
				} else {
					try {
						if (istop) {
							break;
						}
						Thread.sleep(10);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	void addOrderBackup(List<Orders> orders) {
		try {
			List<OrderBackup> ordersbc = new ArrayList<OrderBackup>();
			for (Orders order : orders) {
				OrderBackup orderbc = new OrderBackup();
				orderbc.setCode(order.getCode());
				orderbc.setGroupcode(order.getGroupcode());
				orderbc.setStatus(order.getStatus());
				orderbc.setBuyerid(order.getBuyerid());
				orderbc.setSellerid(order.getSellerid());
				if (order.getPaytype() == null)
					orderbc.setPaytype(1);
				else
					orderbc.setPaytype(order.getPaytype());
				orderbc.setPrice(order.getPrice());
				orderbc.setFreight(order.getFreight());
				orderbc.setCouponid(order.getCouponid());
				orderbc.setLogisticscode(order.getLogisticscode());
				orderbc.setLogisticsname(order.getLogisticsname());
				orderbc.setAddorderdate(order.getAddorderdate());
				orderbc.setPaydate(order.getPaydate());
				orderbc.setAdddate(new Date());
				ordersbc.add(orderbc);
			}
			orderBackupService.add(ordersbc);
		} catch (Exception ex) {
			LogHandle.error(LogType.Api, "订单信息备份失败，错误详情：{0}", ex, "OrderServiceBaseImpl/addOrderBackup");
		}
	}

	public static OrderUtils getBackOrder() {

		if (_BackOrder == null) {
			_BackOrder = new OrderUtils();
		}
		return _BackOrder;
	}
	
	
	public static void main(String []args) {
		for(int i=0;i<10000;i++)
		{
			OrderUtils.getBackOrder().add(null, null, 0, 0);
		}
	}
}

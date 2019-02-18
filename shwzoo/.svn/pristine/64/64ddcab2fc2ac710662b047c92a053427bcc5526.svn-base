/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.task;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.GroupOrderEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.BusinessbillsService;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.PropertiesUtil;

/**
 * ScheduleTask.java
 * @author Liang.ma.s
 * @version $Id: ScheduleTask.java, v 0.1 2016年4月11日 下午3:19:58 Administrator Exp $
 */
public class ScheduleTask extends TimerTask {
	private static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
    
    private OrderStatusService orderStatusService;
    
    private BusinessbillsService businessbillsService;
    
    private OrderService       orderService;
    
    private ConfigSetService   configSetService;
    
    private GroupBuyOrderService groupBuyOrderService;
    
    private WebApplicationContext     context = null;

    //private final String       pattern = "yyyy-MM-dd HH:mm:ss";

    private final String       userip  = GetIpAddresss.getIp();

    public ScheduleTask(WebApplicationContext context) {
        this.context = context;
    }

    /**
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
        try {
        	orderService=(OrderService)context.getBean("orderService");
        	orderStatusService=(OrderStatusService)context.getBean("orderStatusService");
            //订单生成24小时没有付款自动取消
            List<Orders> unpaidList = orderService.getUnpaidOrders();
            orderStatusService.updateInvalidOrder(unpaidList);
            //20分钟处于出票中订单批量处理
            orderStatusService.UntickOrdersStatus();
            //查询出票中的订单进行票务下单通知
            orderStatusService.sendOrderReq();
            
        } catch (Exception e) {
        	logger.error("order-task error",e);
            e.printStackTrace();
            System.out.println(e.toString());
        }

    }
}

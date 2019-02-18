package com.yinlian.wssc.web.task;

import java.util.List;
import java.util.TimerTask;

import org.springframework.web.context.WebApplicationContext;

import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.BusinessbillsService;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

public class OrderBillsTask  extends TimerTask {
	
	private BusinessbillsService businessbillsService;
	
    private OrderStatusService orderStatusService;
	    
	private ConfigSetService   configSetService;
	
	private OrderService orderService;
	
	private WebApplicationContext     context = null;
	  
	public OrderBillsTask(WebApplicationContext context) {
        this.context = context;
	}
	@Override
	public void run() {

		try{
			
			//生成对账单
			businessbillsService=(BusinessbillsService)context.getBean("businessbillsService"); 
			businessbillsService.addBusBill();
			
			//清除待付款使用时间失效的订单
			orderService=(OrderService)context.getBean("orderService");
			orderStatusService=(OrderStatusService)context.getBean("orderStatusService");
			List<Orders> orders = orderService.getInvalidOrder();
			orderStatusService.updateInvalidOrder(orders);

			
		}catch(Exception e){
			e.printStackTrace();
			LogHandle.error(LogType.Businessbills, "生成对账单信息错误：",e,"/task/addBusBill");
		}
	
	}

}

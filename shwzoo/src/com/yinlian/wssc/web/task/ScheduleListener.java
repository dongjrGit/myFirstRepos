/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.task;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * ScheduleListener.java
 * @author Liang.ma.s
 * @version $Id: ScheduleListener.java, v 0.1 2016年4月11日 下午3:21:41 Administrator Exp $
 */
public class ScheduleListener implements ServletContextListener {
	private static Logger logger = LoggerFactory.getLogger(ScheduleListener.class);

    private Timer        timer      = null;

    private final String start_time = "";
    
    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
      //  timer.cancel(); //销毁定时器
    }

    /**
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					Thread.sleep(10*1000);
				
					//一天的毫秒数
			        long daySpan = 24*60*60 * 1000;
			        //得到时间类
			        Calendar date = Calendar.getInstance();
			        //设置时间为 00:00:00
			        date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE), 0, 0,
			            0);
			        timer = new Timer("zoo-task-timer",true);
			        event.getServletContext().log("定时器已启动");
			        
			        int period = 5 * 60 * 1000; //每隔5分钟执行一次
			        timer.schedule(new ScheduleTask(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext())),  0, period); 
			        //timer.schedule(new ScheduleTask(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext())), date.getTime(), daySpan); //每天00：00：00执行
			        
			        timer.schedule(new OrderBillsTask(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext())), date.getTime(), daySpan); //每天00：00：00执行
//			        //全文检索数据同步
//			        int period = 60 * 60 * 1000; //每隔5分钟执行一次
//			        timer.schedule(new ProductSearchTask(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext())), 0, period);
			        //批量添加会员数据
			        //timer.schedule(new AddMemberTask(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext())), period); 
			        event.getServletContext().log("已经添加任务");
				} catch (InterruptedException e) {
					logger.error("zoo-task error",e);
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		},"zoo-task-Thread").start();
    	
    }

}

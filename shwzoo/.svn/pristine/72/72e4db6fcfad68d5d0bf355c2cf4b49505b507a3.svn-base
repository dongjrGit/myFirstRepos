/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.TrackQueryDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.KdniaoTrackQueryAPI;
import com.yinlian.wssc.web.convert.TrackQueryConvert;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.TrackQuery;
import com.yinlian.wssc.web.service.LogisticsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月28日 下午4:42:16 Exp $
 */
@Controller
@RequestMapping("/seller/track")
public class SellerTrackQueryController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private LogisticsService logisticsService;
    /**
     * 查询物流信息
     *@param expcode
     *@param expno
     *@return
     */
    @RequestMapping(value = "/querytrack")
    public @ResponseBody ReusltItem querytrack(String orderid) {
        ReusltItem item = new ReusltItem();
        Gson gson = new Gson();
        try {
            if (StringUtils.isBlanck(orderid)) {
                item.setCode(-102);
                item.setDesc("订单错误{id}:" + orderid);
                return item;
            }
            Orders orders = orderService.getOrderByID(StringUtilsEX.ToInt(orderid));
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            String expcode = orders.getLogisticsname(); //物流的公司编码 请填写对应的编码 例如：顺丰：SF
            String expno = orders.getLogisticscode(); // 物流单号
            String result = api.getOrderTracesByJson(orders.getCode(), expcode, expno); // 得到物流结果
            gson = new Gson();
            TrackQueryDto dto = gson.fromJson(result, TrackQueryDto.class);
            item.setCode(0);
            item.setData(dto);
        } catch (Exception e) {
            if (DebugConfig.BLUETOOTH_DEBUG) {
                item.setDesc(e.getMessage());
            } else {
                item.setDesc("系统错误！");
            }
            item.setCode(-900);
            LogHandle.error(LogType.Other,"获取物流信息错误：{0}", e,"/seller/track/querytrack");
        }
        return item;
    }

    /**
     * 显示物流的信息
     *@return
     */
    @RequestMapping("/showTrackquery")
    public String showTrackquery(String orderid, Integer isowned, Model model) {
        Gson gson = new Gson();
        try {
            Orders orders = orderService.getOrderByID(StringUtilsEX.ToInt(orderid));
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            String expcode = orders.getLogisticsname(); //物流的公司编码 请填写对应的编码 例如：顺丰：SF
            String expno = orders.getLogisticscode(); // 物流单号
            String result = api.getOrderTracesByJson(orders.getCode(), expcode, expno); // 得到物流结果
            gson = new Gson();
            TrackQueryDto dto = gson.fromJson(result, TrackQueryDto.class);
            TrackQuery record = new TrackQuery();
            TrackQueryConvert.convertDto(dto, record);
            //获取快递公司名称
            String Logisticsname=logisticsService.getByCode(dto.getShipperCode());
            record.setName(Logisticsname);    
            model.addAttribute("data", record);
            model.addAttribute("isowned", isowned);
        } catch (Exception e) {
        	
            LogHandle.error(LogType.Other, MessageFormat.format("获取物流信息错误：{0}", e.getMessage()),
                orderid);
        }
        return "seller/ddgl/track";
    }
}

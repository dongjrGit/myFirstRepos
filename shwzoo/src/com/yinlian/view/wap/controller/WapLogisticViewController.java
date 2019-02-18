package com.yinlian.view.wap.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.TrackQueryDto;
import com.yinlian.wssc.search.KdniaoTrackQueryAPI;
import com.yinlian.wssc.web.convert.TrackQueryConvert;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.TrackQuery;
import com.yinlian.wssc.web.service.LogisticsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/logistic")
public class WapLogisticViewController {

	@Autowired
    private OrderService orderService;
    @Autowired
    private LogisticsService logisticsService;
    /**
     * 查询物流情况
     *@param orderid 订单id
     *@return
     */
    @RequestMapping(value = "/getlogistic", produces = "text/html;charset=UTF-8")
    public String querytrack(String orderid, HttpServletRequest request) throws Exception{

    	  Gson gson = new Gson();    	
        try {
            if (StringUtilsEX.ToInt(orderid)<=0) {
            	return ErrorRedirect.getInstance().wapRedirect("订单id错误");                  
            }
            String token = CookieUtils.getTokenFromCookie(request);
            SessionUser user = SessionState.GetCurrentUser(token);
            if (user.getCode() != 0) {       
            	//return "redirect:/wap/404.html";
            	return  "/template/wap/userinfo/login";
            }else{
            Orders orders = orderService.getOrderByID(StringUtilsEX.ToInt(orderid));
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            String expcode = orders.getLogisticsname(); //物流的公司编码 请填写对应的编码 例如：顺丰：SF
            String expno = orders.getLogisticscode(); // 物流单号
            String result = api.getOrderTracesByJson(orders.getCode(), expcode, expno);
            gson = new Gson();
            TrackQueryDto dto = gson.fromJson(result, TrackQueryDto.class);
            TrackQuery record = new TrackQuery();
            TrackQueryConvert.convertDto(dto, record);
            //获取快递公司名称
            String Logisticsname=logisticsService.getByCode(dto.getShipperCode());
            record.setName(Logisticsname);             
            request.setAttribute("logistic",record);
            String href=request.getParameter("href");
            if(StringUtilsEX.IsNullOrWhiteSpace(href))
            {
            	href="/wap/order/getorder";
            }
            request.setAttribute("href",href);
            //return "redirect:/wap/404.html";
            return "/template/wap/logisticinfo/LogisticInfo";
            }
            
        } catch (Exception e) {           
            LogHandle.error(LogType.wap, MessageFormat.format("获取物流信息错误：{0}", e.toString()),
                orderid);           
            return "/template/wap/index";
        }
       
    }
}

package com.yinlian.api.wap.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.TrackQueryDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.search.KdniaoTrackQueryAPI;
import com.yinlian.wssc.web.convert.TrackQueryConvert;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.TrackQuery;
import com.yinlian.wssc.web.service.LogisticsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/logistic")
public class WapLogisticController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private LogisticsService logisticsService;

	/**
	 * 查询物流情况
	 *
	 * @param ch
	 * @param token
	 * @param expcode
	 * @param expno
	 * @return
	 */
	@RequestMapping(value = "/getlogistic", produces = "text/html;charset=UTF-8")
	public String querytrack(String orderid, String ch,
			HttpServletRequest request) {
		ch = "3";
		BaseResult item = new BaseResult();
		Gson gson = new Gson();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtils.isBlanck(orderid)) {
				item.setCode(-102);
				item.setDesc("订单错误{id}:" + orderid);
				return item.toJson();
			}
			String token = CookieUtils.getTokenFromCookie(request);
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录！");
				return item.toJson();
			}
			Orders orders = orderService.getOrderByID(StringUtilsEX
					.ToInt(orderid));
			KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
			String expcode = orders.getLogisticsname(); // 物流的公司编码 请填写对应的编码
														// 例如：顺丰：SF
			String expno = orders.getLogisticscode(); // 物流单号
			String result = api.getOrderTracesByJson(orders.getCode(), expcode,
					expno);
			gson = new Gson();
			TrackQueryDto dto = gson.fromJson(result, TrackQueryDto.class);
			TrackQuery record = new TrackQuery();
			TrackQueryConvert.convertDto(dto, record);
			// 获取快递公司名称
			String Logisticsname = logisticsService.getByCode(dto
					.getShipperCode());
			record.setName(Logisticsname);
			item.setCode(0);
			item.setData(record);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"获取物流信息错误：{0}", e,
							"/logistic/getlogistic");			
		}
		return item.toJson();
	}
}

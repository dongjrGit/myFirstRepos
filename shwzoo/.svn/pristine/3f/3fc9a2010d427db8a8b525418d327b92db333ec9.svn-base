package com.yinlian.wssc.seller.view.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.OrderDetailStatusEnum;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CookieDto;
import com.yinlian.wssc.web.dto.AfterServiceOrderDto;
import com.yinlian.wssc.web.dto.SaleOrder;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.mapper.OrdersMapper;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.util.CriteriaDdtj;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 订单管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/seller/ddtj")
public class DdTjViewController {

	@Autowired
	private OrderService ordersService;
	@Autowired
	private OrderdetailService orderdetailService;

	/**
	 * 全部订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddtj_Total")
	public String ddtj_Total(HttpServletRequest request) {

		// 正常流程订单统计
		SaleOrder so = new SaleOrder();
		// 统计售后订单
		//AfterServiceOrderDto dto = new AfterServiceOrderDto();
		Integer userid = 0;
		try {
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser != null && sessionUser.getCode() == 0) {
				userid = sessionUser.getUserId();
			}
			List<SaleOrder> list = ordersService.selectAllOrder(userid,sessionUser.getShopid());
			if (list != null && list.size() > 0) {
				so = list.get(0);
			}
//			CriteriaDdtj criteria = new CriteriaDdtj();
//			criteria.setId(userid);
//			dto = orderdetailService.getDetailsAnalysisByShop(criteria);
//			if (dto == null) {
//				dto = new AfterServiceOrderDto();
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("order", so);
		request.setAttribute("userid", userid);
//		request.setAttribute("asorder", dto);
		return "seller/tj/ddtj_Total";
	}

	/** 
	 * 订单日统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddtj_Day")
	public String ddtj_Day(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer userid = sessionUser.getUserId();
		request.setAttribute("userid", userid);
		return "seller/tj/ddtj_Day";
	}

	/**
	 * 订单周统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddtj_Week")
	public String ddtj_Week(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer userid = sessionUser.getUserId();
		request.setAttribute("userid", userid);
		return "seller/tj/ddtj_Week";
	}

	/**
	 * 订单月统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddtj_Month")
	public String ddtj_Month(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer userid = sessionUser.getUserId();
		request.setAttribute("userid", userid);
		return "seller/tj/ddtj_Month";
	}

	/**
	 * 订单季度统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddtj_Quarter")
	public String ddtj_Quarter(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer userid = sessionUser.getUserId();
		request.setAttribute("userid", userid);
		return "seller/tj/ddtj_Quarter";
	}

	/**
	 * 订单年统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddtj_Year")
	public String ddtj_Year(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer userid = sessionUser.getUserId();
		request.setAttribute("userid", userid);
		return "seller/tj/ddtj_Year";
	}

	/**
	 * 销售概况
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/salesProfile")
	public String salesProfile(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer shopid = sessionUser.getShopid();
		request.setAttribute("shopid", shopid);
		return "seller/tj/salesProfile";
	}

	/**
	 * 销售明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/salesDetail")
	public String salesDetail(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer shopid = sessionUser.getShopid();
		request.setAttribute("shopid", shopid);
		return "seller/tj/SalesDetail";
	}

	/**
	 * 客户区域分析
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/buyerArea")
	public String buyerArea(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer shopid = sessionUser.getShopid();
		request.setAttribute("shopid", shopid);
		return "seller/tj/buyerArea";
	}

	/**
	 * 客户明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/buyerDetail")
	public String buyerDetail(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer shopid = sessionUser.getShopid();
		request.setAttribute("shopid", shopid);
		return "seller/tj/buyerDetail";
	}

	/**
	 * 优惠卷数据分析
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/couponUse")
	public String couponUse(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer shopid = sessionUser.getShopid();
		request.setAttribute("shopid", shopid);
		return "seller/tj/couponUse";
	}

	/**
	 * 组合商品数据分析
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/packageTJ")
	public String packageTJ(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer shopid = sessionUser.getShopid();
		request.setAttribute("shopid", shopid);
		return "seller/tj/PackageTJ";
	}

	/**
	 * 闪购数据分析
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/spike_SG")
	public String spike_SG(HttpServletRequest request) {
		SessionUser sessionUser = SessionState.GetCurrentUser();
		Integer shopid = sessionUser.getShopid();
		request.setAttribute("shopid", shopid);
		return "seller/tj/spike_SG";
	}
	
	/**
	 * 商品销量统计 -所有站点
	 * @param request
	 * @return
	 */
	@RequestMapping("/productssale")
	public String productssale(HttpServletRequest request) {
		return "seller/tj/spxltj_list";
	}
	@RequestMapping("/pcproductssale")
	public String productssale_pc(HttpServletRequest request) {
		return "seller/tj/spxltj_pc";
	}
	@RequestMapping("/appproductssale")
	public String productssale_app(HttpServletRequest request) {
		return "seller/tj/spxltj_app";
	}
	@RequestMapping("/wapproductssale")
	public String productssale_wap(HttpServletRequest request) {
		return "seller/tj/spxltj_wap";
	}
	
	@RequestMapping("/billlist")
	public String billlist(Integer status){
		return "seller/ddgl/businessbills";
	}
	
	@RequestMapping("/billedit")
	public String billedit(Integer id,Integer status,Model model){
		model.addAttribute("id", id);
		model.addAttribute("status", status);
		return "seller/ddgl/businessbillsedit";
	}
}

package com.yinlian.wssc.seller.view.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.OrderApplyTypeEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.mapper.LogisticsMapper;
import com.yinlian.wssc.web.po.Applyforcancelorder;
import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.po.Invoice;
import com.yinlian.wssc.web.po.Logistics;
import com.yinlian.wssc.web.po.LogisticsExample;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Receiveinfo;
import com.yinlian.wssc.web.service.ApplyforcancelorderService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.IdCardinfoService;
import com.yinlian.wssc.web.service.ImagesService;
import com.yinlian.wssc.web.service.InvoiceService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ReceiveInfoService;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/seller/shopdd")
public class ShopDdController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderdetailService orderdetailService;
	@Autowired
	private ReceiveInfoService receiveInfoService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private ApplyforcancelorderService applyforcancelorderService;
	@Autowired
	private ImagesService imagesService;
	@Autowired
	private LogisticsMapper logisticsMapper;
	@Autowired
	private ProvinceServcice  provinceServcice ;
	@Autowired
	private CityServcie cityServcie;
	@Autowired
	private AreaService  areaService ;
	@Autowired
	private IdCardinfoService idCardinfoService;
	/**
	 * 全部订单列表
	 * @param select_begin 开始时间
	 * @param select_end 结束时间
	 * @param pagetype 页标识 1对账
	 * @return
	 */
	@RequestMapping("/ddgl_OrderList")
	public String ddgl_OrderList(String select_begin,String select_end,Integer pagetype,Model model){
		model.addAttribute("begin", select_begin);
		model.addAttribute("end", select_end);
		model.addAttribute("pagetype", pagetype);
		//对账页跳转到订单列表的话默认展示已确认收货订单
		if (pagetype!=null&&pagetype==1) {
			model.addAttribute("status", OrderStatusEnum.已完结.getValue());
		}
		return "seller/ddgl/ddgl_OrderList";
	}
	
	/**
	 * 代付款订单列表
	 */
	@RequestMapping("/ddgl_DfkOrderList")
	public String ddgl_DfkOrderList() {
		return "seller/ddgl/ddgl_DfkOrderList";
	}
	
	/**
	 * 待使用订单列表
	 */
	@RequestMapping("/ddgl_DsyOrderList")
	public String ddgl_DsyOrderList() {
		return "seller/ddgl/ddgl_DsyOrderList";
	}
	
	/**
	 * 已使用订单列表
	 * @return
	 */
	@RequestMapping("/ddgl_YsyOrderList")
	public String ddgl_YsyOrderList() {
		return "seller/ddgl/ddgl_YsyOrderList";
	}
	
	/**
	 * 审核中订单列表
	 */
	@RequestMapping("/ddgl_ShzOrderList")
	public String ddgl_ShzOrderList() {
		return "seller/ddgl/ddgl_ShzOrderList";
	}
	
	/**
	 * 已取消订单列表
	 */
	@RequestMapping("/ddgl_YqxOrderList")
	public String ddgl_YqxOrderList() {
		return "seller/ddgl/ddgl_YqxOrderList";
	}
	
	/**
	 * 已完结订单列表
	 */
	@RequestMapping("/ddgl_YwjOrderList")
	public String ddgl_YwjOrderList() {
		return "seller/ddgl/ddgl_YwjOrderList";
	}
	
	/**
	 * 待发货订单列表
	 * @return
	 */
	@RequestMapping("/ddgl_sendOrderList")
	public String ddgl_sendOrderList(){
		return "seller/ddgl/ddgl_sendOrderList";
	}
	/**
	 * 订单详情
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddgl_OrderDetail")
	public String ddgl_OrderDetail(String id,String ch, HttpServletRequest request) {
		Orders orders = new Orders();
		List<Orderdetail> detaillist = new ArrayList<Orderdetail>();
		Idcardinfo idcardinfo = new Idcardinfo();
		Invoice invoice = new Invoice();
		String isowned = "0", reason = "0";
		float totalmoney = 0.0f;
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				orders = orderService.getOrderByID(StringUtilsEX.ToInt(id));
				if (orders != null) {
					detaillist = orderService.getDetailsByOrderID(orders
							.getId());
					idcardinfo = idCardinfoService.getByGroupCode(orders.getGroupcode());
					if (orders.getIsinvoice() == 1) {
						invoice = invoiceService
								.selectByOrderId(orders.getId());
					}
					if (orders.getCancelreason() != null) {
						reason = orders.getCancelreason();
					}
					if (orders.getIsowned() == true) {
						isowned = "1";
					}
					BigDecimal money = orders.getPrice().add(
							orders.getFreight());
					if(orders.getBeans()!=null && orders.getBeans()>0){
						Double disc=orders.getBeans()*orders.getBeansratio();
						totalmoney = Float.parseFloat(money.toString())
								- orders.getDiscount()-disc.floatValue();
					}else{
						totalmoney = Float.parseFloat(money.toString())
								- orders.getDiscount();
					}
					orders.setDiscount(money.subtract(new BigDecimal(totalmoney)).floatValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("order", orders);
		request.setAttribute("details", detaillist);
		request.setAttribute("idcardinfo", idcardinfo);
		request.setAttribute("invoice", invoice);
		request.setAttribute("ch", ch);
		request.setAttribute("reason", reason);
		request.setAttribute("totalmoney", totalmoney);
		return "seller/ddgl/ddgl_OrderDetail";
	}
	
	/**
	 * 订单详情
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddgl_OrderDetailTh")
	public String ddgl_OrderDetailTh(String id, HttpServletRequest request) {
		Orders orders = new Orders();
		List<Orderdetail> detaillist = new ArrayList<Orderdetail>();
		Receiveinfo receiveinfo = new Receiveinfo();
		Invoice invoice = new Invoice();
		String isowned = "0", reason = "0";
		float totalmoney = 0.0f;
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				orders = orderService.getOrderByID(StringUtilsEX.ToInt(id));
				if (orders != null) {
					detaillist = orderService.getDetailsByOrderID(orders
							.getId());
					receiveinfo = receiveInfoService.queryByOrderGroupId(orders
							.getGroupcode());
					if(receiveinfo!=null){
						receiveinfo.setProvincename(provinceServcice.queryByCode(receiveinfo.getProvince()).getName());
						receiveinfo.setCityname(cityServcie.queryByCode(receiveinfo.getCity()).getName());
						receiveinfo.setAreaname(areaService.queryByCode(receiveinfo.getArea()).getName());
					}
					if (orders.getIsinvoice() == 1) {
						invoice = invoiceService
								.selectByOrderId(orders.getId());
					}
					if (orders.getCancelreason() != null) {
						reason = orders.getCancelreason();
					}
					if (orders.getIsowned() == true) {
						isowned = "1";
					}
					BigDecimal money = orders.getPrice().add(
							orders.getFreight());
					totalmoney = Float.parseFloat(money.toString())
							- orders.getDiscount();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("order", orders);
		request.setAttribute("details", detaillist);
		request.setAttribute("receive", receiveinfo);
		request.setAttribute("invoice", invoice);
		request.setAttribute("isowned", isowned);
		request.setAttribute("reason", reason);
		request.setAttribute("totalmoney", totalmoney);
		return "seller/ddgl/ddgl_OrderDetailTh";
	}

	/**
	 * 售后申请查看原因
	 * 
	 * @param id
	 * @param ss
	 * @param aftertype
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddgl_thh")
	public String ddgl_thh(String id, String ss, String aftertype,
			HttpServletRequest request) {
		Applyforcancelorder apply = new Applyforcancelorder();
		List<Images> imglist = new ArrayList<Images>();
		Orderdetail de=new Orderdetail();
		Orders orders=new Orders();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				int type = StringUtilsEX.ToInt(aftertype);
//				if(type==2){
//					orders=orderService.queryByID(StringUtilsEX.ToInt(id));
//					request.setAttribute("detail", orders);
//				}else{
					de = orderdetailService.queryByID(StringUtilsEX
							.ToInt(id));
					request.setAttribute("detail", de);
//				}
				if (de != null || orders != null) {
					switch (type) {
					case 1:
						apply = applyforcancelorderService.getbyOrderAndType(
								de.getId(),
								OrderApplyTypeEnum.订单退货退款.getValue());
						break;
					case 2:
						apply = applyforcancelorderService.getbyOrderAndType(
								de.getId(), OrderApplyTypeEnum.订单退款.getValue());
						break;
					case 3:
						apply = applyforcancelorderService.getbyOrderAndType(
								de.getId(), OrderApplyTypeEnum.订单换货.getValue());
						break;
					case 4:
						apply = applyforcancelorderService.getbyOrderAndType(
								de.getId(), OrderApplyTypeEnum.订单维修.getValue());
						break;
					default:
						break;
					}
					if (apply != null) {
						imglist=imagesService.getByFKid(apply.getId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		request.setAttribute("detail", de);
		request.setAttribute("apply", apply);
		request.setAttribute("imglist", imglist);
		return "seller/ddgl/ddgl_thh";
	}
	
	/**退货订单
	 * 
	 * @return
	 */
	@RequestMapping("/ddgl_ReturnList")
	public String ddgl_ReturnList(){
		return "seller/ddgl/ddgl_ReturnList";
	}
	
	/**
	 * 换货订单
	 * @return
	 */
	@RequestMapping("/ddgl_ExchangeList")
	public String ddgl_ExchangeList(){
		return "seller/ddgl/ddgl_ExchangeList";
	}
	
	/**
	 * 退货退款订单
	 * @return
	 */
	@RequestMapping("/ddgl_OrderThTk")
	public String ddgl_OrderThTk(HttpServletRequest request){
		SessionUser sessionUser = SessionState.GetCurrentUser();
        Integer shopid = sessionUser.getShopid();
        request.setAttribute("shopid", shopid);
		return "seller/ddgl/ddgl_OrderThTk";
	}
	
	/**
	 * 显示备注页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editNote")
    public String editNote(String id,Model model) {
    	model.addAttribute("id",id);
        return "seller/ddgl/AddNote";
    }
	
	@RequestMapping("/ddgl_InvoiceList")
	public String ddgl_InvoiceList() {
		return "seller/ddgl/ddgl_InvoiceList";
	}
	
	/**
	 * 团购订单
	 * @return
	 */
	@RequestMapping("/ddgl_groupBuyList")
	public String ddgl_groupBuyList() {
		return "seller/ddgl/ddgl_groupBuyList";
	}
	
	@RequestMapping("/wlcompany")
	public String wlcompany(String id, HttpServletRequest request){
		//物流公司列表
		List<Logistics> wllist=new ArrayList<Logistics>();
		try{
			LogisticsExample example=new LogisticsExample();
			wllist=logisticsMapper.selectByExample(example);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("orderid", id);
		request.setAttribute("wllist", wllist);
		return "seller/ddgl/wlcompany";
	}
	
	@RequestMapping("/cdorderList")
	public String cdorderList(String id, HttpServletRequest request){
		return "seller/ddgl/cd_OrderList";
	}
	
	@RequestMapping("/billlist")
	public String billlist(Integer status,Model model){
		model.addAttribute("status", status);
		SessionUser user=SessionState.GetCurrentUser();
		model.addAttribute("shopid", user.getShopid());
		return "seller/ddgl/businessbills";
	}	
	
	/**
	 * 对账订单列表
	 * @param model
	 * @param shopid
	 * @param orderdate
	 * @return
	 */
	@RequestMapping("/bbillsOrderList")
	public String bbillsOrderList(Model model,String shopid,String orderdate,String status){
		model.addAttribute("shopid", shopid);
		model.addAttribute("orderdate", orderdate);
		model.addAttribute("status", status);
		return "/seller/ddgl/bbillsOrderList";
	}
	
}

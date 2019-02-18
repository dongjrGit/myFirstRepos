package com.yinlian.wssc.platform.view.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.OrderApplyTypeEnum;
import com.yinlian.Enums.OrderStatusEnum;
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
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 订单管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/platform/ddgl/")
public class DdglController {
	

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
	 * 直营订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/ddgl_zyOrderList")
	public void ddgl_zyOrderList() {
	}


	/**
	 * 店铺订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/ddgl_dpOrderList")
	public void ddgl_dpOrderList() {
	}
	
	/**
	 * 代付款订单列表
	 */
	@RequestMapping("/ddgl_dpDfkOrderList")
	public void ddgl_dpDfkOrderList() {
	}
	
	/**
	 * 待使用订单列表
	 */
	@RequestMapping("/ddgl_dpDsyOrderList")
	public void ddgl_dpDsyOrderList() {
	}
	
	/**
	 * 已使用订单列表
	 */
	@RequestMapping("/ddgl_dpYsyOrderList")
	public void ddgl_dpYsyOrderList() {
	}
	
	/**
	 * 审核中订单列表
	 */
	@RequestMapping("/ddgl_dpShzOrderList")
	public void ddgl_dpShzOrderList() {
	}
	
	/**
	 * 已取消订单列表
	 */
	@RequestMapping("/ddgl_dpYqxOrderList")
	public void ddgl_dpYqxOrderList() {
	}
	
	/**
	 * 已完结订单列表
	 */
	@RequestMapping("/ddgl_dpYwjOrderList")
	public void ddgl_dpYwjOrderList() {
	}

	/**
	 * 订单详情
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddgl_OrderDetail")
	public void ddgl_OrderDetail(String id,String ch,String type, HttpServletRequest request) {
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
					if(orders.getStatus()==OrderStatusEnum.审核中.getValue()){
						Applyforcancelorder applyfor= 
								applyforcancelorderService.getbyOrderAndUser(orders.getId(), orders.getBuyerid());
						if(applyfor!=null){
							reason=applyfor.getContent();
						}
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
		if(StringUtilsEX.ToInt(ch)<0){
			ch="0";
		}
		request.setAttribute("ch", ch);
		request.setAttribute("order", orders);
		request.setAttribute("details", detaillist);
		request.setAttribute("idcardinfo", idcardinfo);
		request.setAttribute("invoice", invoice);
		request.setAttribute("isowned", isowned);
		request.setAttribute("reason", reason);
		request.setAttribute("totalmoney", totalmoney);
		request.setAttribute("type", type);
	}

	
	@RequestMapping("/ddgl_OrderDetailTh")
	public void ddgl_OrderDetailTh(String id, HttpServletRequest request) {
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
	public void ddgl_thh(String id, String ss, String aftertype,
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
		
		request.setAttribute("apply", apply);
		request.setAttribute("imglist", imglist);
	}
	
	@RequestMapping("/ddgl_zyInvoiceList")
	public void ddgl_zyInvoiceList() {
	}
	@RequestMapping("/ddgl_dpInvoiceList")
	public void ddgl_dpInvoiceList() {
	}
	@RequestMapping("/wlcompany")
	public void wlcompany(String id, HttpServletRequest request){
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
	}
	
	/**
	 * 售后后台拒绝纠纷处理
	 * 
	 * @param id 订单id
	 * @param ss 订单状态 （OrderDetailStatusEnum）
	 * @param aftertype  1:"退货退款",2:"退款",3:"换货",4:"维修"
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddgl_shcl")
	public void ddgl_shcl(String id, String ss, String aftertype,
			HttpServletRequest request) {
		
		List<Applyforcancelorder> applys = new ArrayList<Applyforcancelorder>();		
		Orderdetail de=new Orderdetail();
		Orders orders=new Orders();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				int type = StringUtilsEX.ToInt(aftertype);
				if(type==2){
					orders=orderService.queryByID(StringUtilsEX.ToInt(id));
					request.setAttribute("detail", orders);
				}else{
					de = orderdetailService.queryByID(StringUtilsEX.ToInt(id));
					request.setAttribute("detail", de);
				}
				if (de != null || orders != null) {					
					applys = applyforcancelorderService.getApplyListByOid(de.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		request.setAttribute("applys", applys);
	}
	/**
	 * 售后详情
	 * 
	 * @param id 订单id	
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddgl_shxq")
	public void ddgl_shxq(String id,HttpServletRequest request) {
		List<Applyforcancelorder> applys = new ArrayList<Applyforcancelorder>();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {		
				 applys = applyforcancelorderService.getApplyListByOid(StringUtilsEX.ToInt(id));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("applys", applys);
	}
}

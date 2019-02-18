package com.yinlian.view.pc.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.wssc.web.po.Afterservice;
import com.yinlian.wssc.web.po.Applyforcancelorder;
import com.yinlian.wssc.web.po.Feedback;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.service.AfterService;
import com.yinlian.wssc.web.service.ApplyforcancelorderService;
import com.yinlian.wssc.web.service.FeedBackService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.StringUtilsEX;


@Controller
@RequestMapping("/member/khFwmemb")
public class PCKhFwmembViewController {
	
	@Autowired
	private OrderdetailService   orderdetailService;
	@Autowired
	private TopicService topservice;
	@Autowired
	private  OrderService  orderService;
	@Autowired
	private  ShopService  shopService;
	@Autowired
	private  AfterService  afterservice;
	@Autowired
	private  ApplyforcancelorderService  ApplyforcancelorderService;
	@Autowired
	private FeedBackService feedbackService;
	/**
	 * 申请返修/退换货 
	 * @return
	 */
    @RequestMapping("/afterServiceOrderList.html")
    public String afterServiceOrderList(Model model){
    	
    	List<Api_TopicBySpuDto> topspuTM=null;
		try {
			topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
		
		
    	return "/template/pc/memberCenter/KhFwmemb/AfterServiceOrderList";
    }
    
    
    /**
	 * 返修/退换货记录
	 * @return
	 */
    @RequestMapping("/AfterServiceCompleteList.html")
    public String AfterServiceCompleteList(Model model){
    	
    	List<Api_TopicBySpuDto> topspuTM=null;
		try {
			topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/KhFwmemb/AfterServiceCompleteList";
    }
    
    
    /**
	 * 返修/退换货
	 * @return
	 */
    @RequestMapping("/RepairOrReturn.html")
    public String RepairOrReturn(String orderdetailID,String backUrl,Model model){
    	
    	Orderdetail orderdetail=null;
		try {
			orderdetail = orderdetailService.queryByID(StringUtilsEX.ToInt(orderdetailID));
			model.addAttribute("orderdetail", orderdetail);
			model.addAttribute("backUrl", backUrl);
			model.addAttribute("orderdetailid", orderdetailID);
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/KhFwmemb/RepairOrReturn";
    }
    
    
    /**
     * 退换货成功页
     * @return
     */
    @RequestMapping("/repairOrReturnComplete.html")
    public String repairOrReturnComplete(Model model){
    	
    	List<Api_TopicBySpuDto> topspuTM=null;
		try {
			topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/KhFwmemb/repairOrReturnComplete";
    }
    
    /**
     * 退换货详细页
     * @param detailID 订单详情id
     * @param id 售后服务主键id
     * @return
     */
    @RequestMapping("/AfterServiceDetail.html")
    public String AfterServiceDetail(String detailID,String id,Model model,HttpServletRequest request){
    	
    	try {
    		
			Orderdetail  orderdetail=orderdetailService.queryByID(StringUtilsEX.ToInt(detailID));
			model.addAttribute("orderdetail", orderdetail);
			Integer sellerid=orderdetail.getSellerid();
			List<Shop>  shops=shopService.selectListByUserId(sellerid);
			String shopname=null;
			if(shops!=null && shops.size()>0){
				shopname=shops.get(0).getName();
			}
			model.addAttribute("shopname", shopname);
			Applyforcancelorder list =ApplyforcancelorderService.getbyOrderAndUser(StringUtilsEX.ToInt(detailID), orderdetail.getBuyerid());
			request.setAttribute("apply", list);//买家反馈
			List<Applyforcancelorder> sellerapply =ApplyforcancelorderService.getApplyList(StringUtilsEX.ToInt(detailID), sellerid);
			request.setAttribute("sellerapply", sellerapply);//卖家反馈
			Afterservice  after=afterservice.getAfterserviceByid(StringUtilsEX.ToInt(id));
			model.addAttribute("after", after);//售后申请
			if(after.getImgurl()!=null){
				String[] imgs=after.getImgurl().split(",");
			    String img1=null;
			    String img2=null;
			    String img3=null;
			    for (int i = 0; i < imgs.length; i++) {
					if(i==0){
						img1=imgs[0];
					}else if(i==1){
						img2=imgs[1];
					}else if(i==2){
						img3=imgs[2];
					}
				}
			    model.addAttribute("img1", img1);
			    model.addAttribute("img2", img2);
			    model.addAttribute("img3", img3);
			}
		    request.setAttribute("status", after.getStatus());
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
			

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/KhFwmemb/AfterServiceDetail";
    }
    
    /**
     *购买咨询列表
     * @return
     */
    @RequestMapping("/GoodsConsult.html")
    public String goodsConsult(Model model){
    	
    	List<Api_TopicBySpuDto> topspuTM=new ArrayList<Api_TopicBySpuDto>();
		try {
			topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/KhFwmemb/GoodsConsult";
    }
    
    
    /**
     *售后服务规则
     * @return
     */
    @RequestMapping("/afterSellRule.html")
    public String afterSellRule(Model model){

    	return "/template/pc/memberCenter/KhFwmemb/AfterSellRule";
    }
    @RequestMapping("/feedBack")
	public String queryMemberComment(){
		 
		return "/template/pc/memberCenter/KhFwmemb/feedbackList";
	}
    @RequestMapping("/feedBackdetail.html")
	public String feedBackdetail(String id,Model model) throws Exception{
    	Feedback feed=feedbackService.queryById(StringUtilsEX.ToInt(id));
    	model.addAttribute("feed", feed);
		return "/template/pc/memberCenter/KhFwmemb/feedbackDetail";
	}
    
    @RequestMapping("/feedBackadd.html")
	public String feedBackadd(String id,Model model) throws Exception{
    	Feedback feed=feedbackService.queryById(StringUtilsEX.ToInt(id));
    	model.addAttribute("feed", feed);
		return "/template/pc/memberCenter/KhFwmemb/feedbackDetail";
	}
    
}

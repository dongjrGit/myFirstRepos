package com.yinlian.view.pc.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.service.users_newsService;


@Controller
@RequestMapping("/member/pcconcern")
public class PCConcernViewController {

	
	@Autowired
	private   UsercollectService        usercollectService;
	
	@Autowired
	private   ShopService               shopService;
	
	@Autowired
	private   SpuService                spuService;
	
	@Autowired
	private   users_newsService  usernewsservice;
	
	@Autowired
	private TopicService topservice;
	
	 /**
    * 关注的商品
    * @param request
    * @return
    */
   @RequestMapping("/selectSpucollect.html")
   public String spucollect(Model model){
	   List<Api_TopicBySpuDto> topspuTM=null;
		try {
			topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/404.html";
		}
		
   	 return "/template/pc/memberCenter/Member/ConcernList";
   }
   /**
    * 菜谱收藏列表
    * @param model
    * @return
    */
   @RequestMapping("/cpsc.html")
   public String cpsc(Model model){
	   List<Api_TopicBySpuDto> topspuTM=null;
		try {
			topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/404.html";
		}
		return "/template/pc/memberCenter/Member/cpscList";
   }
    
   @RequestMapping("/selectShopcollect.html")
   public String shopcollect(Model model){
	   List<Api_TopicBySpuDto> topspuTM=null;
		try {
			topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/404.html";
		}
		
   	 return "/template/pc/memberCenter/Member/ConcernSList";
   }
 
}

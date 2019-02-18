package com.yinlian.view.wap.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/proclass")
public class WapClassViewController {
	@Autowired
	private CategoryService caService;
	@Autowired
	private MessageService   messageService;
	
	@RequestMapping("/classList.html")
	public ModelAndView classList(HttpServletRequest request,Model models) {
		ModelAndView model = new ModelAndView("/template/wap/products/classList");
		try {
			List<CategoryDTO> cd = caService.selectAll(0);
			model.addObject("classList", cd);
			int messagecount=0;
			Integer userid=SessionUtil.getSessionUserId(request);
			
			if(userid!=null){
				messagecount=messageService.getCount(userid);
				
			}
			models.addAttribute("messagecount", messagecount);
			
		} catch (Exception e) {
			LogHandle.error(LogType.wap, "访问商品分类错误：{0}", e, "/wap/proclassWap/classList");
			return ErrorRedirect.getInstance().wapRedirectModel("购物车错误");
		}
		//return ErrorRedirect.getInstance().wapRedirectErrorModel();
		return model;
	}
	
	
}

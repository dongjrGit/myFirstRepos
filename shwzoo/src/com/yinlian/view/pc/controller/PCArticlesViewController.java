package com.yinlian.view.pc.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.IndexFootEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/member/pcarticles")
public class PCArticlesViewController {

	@Autowired
	private   ArticlesService     articlesService;
	
	@Autowired
	private NewsService newsService;
	/**
	 * 通过导航分类查询文章信息
	 * @param classfyid
	 * @return
	 */
	@RequestMapping(value = "/showarticles", produces = "text/html;charset=UTF-8")
	public ModelAndView showarticles(String classfy,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(classfy)) {
				view.setViewName("/template/reeor/index.html");
				return view;
			}
			String title="";
			 switch (StringUtilsEX.ToInt(classfy)) {
             case 1: title = "售后政策";
                // atag = 3;
                 break;
             case 222: title = "积分规则";
                // atag = 1;
                 break;
             case 220: title = "什么是积分";
                // atag = 4;
                 break;
             case 221: title = "积分常见问题";
                // atag = 5;
                 break;
             case 5: title = "优惠券使用规则";
                // atag = 2;
                 break;
         }
			 
			List<SnewsWithBLOBs> newslist=newsService.findByCType(StringUtilsEX.ToInt(classfy));
			request.setAttribute("title", title);
			if (newslist.size()>0) {
				request.setAttribute("article", newslist.get(0));
			}
			view.setViewName("/template/pc/memberCenter/KhFwmemb/AfterSellRule");
			return view;
			
		} catch (Exception e) {
			LogHandle.error(LogType.pc, "查询文章信息异常! ",e, "news/showarticles");
			view.setViewName("redirect:/404.html");
			return view;
		}
	}
}

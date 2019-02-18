package com.yinlian.view.wap.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.GroupBuyDetailDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.GroupBuyService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 活动
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年7月5日
 */
@Controller
@RequestMapping("/wap/activityView")
public class WapActivityViewController {


	@Autowired
	private AdverisingService adverisingService;
	@Autowired
	private GroupBuyService groupBuyService;
	@Autowired
	private GroupBuyOrderService groupBuyOrderService;
	@Autowired
	private UsercapitalService usercapitalService;

	
	/**
	 * 团购活动
	 * 
	 * @author kh.wang
	 * @since 2016年7月5日
	 * @return
	 */
	@RequestMapping("/tuan.html")
	public String tuan(Model model){
		try {
			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.团购活动.getValue(), 1,WebSetEnum.wap.getValue()));
		} catch (Exception e) {
			LogHandle.error(LogType.wap,MessageFormat.format("团购活动数据异常! 异常信息:{0}", e),"/wap/activityView/tuan");
			return ErrorRedirect.getInstance().wapRedirect("团购活动错误");
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/activity/tuan";
	}
	
	/**
	 * 抢购活动
	 * 
	 * @author kh.wang
	 * @since 2016年7月6日
	 * @param model
	 * @return
	 */
	@RequestMapping("/qiang.html")
	public String qiang(Model model){
		try {
			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.抢购活动.getValue(), 1,WebSetEnum.wap.getValue()));
		} catch (Exception e) {
			LogHandle.error(LogType.wap,MessageFormat.format("抢购活动数据异常! 异常信息:{0}", e),"/wap/activityView/qiang");
			return ErrorRedirect.getInstance().wapRedirect("抢购活动错误");
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/activity/qiang";
	}
	
	@RequestMapping("/miao")
	public String miao(){
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/activity/miao";
	}
	
	@RequestMapping("/app/miao")
	public String appmiao(Model model,Integer ch){
		try {
			model.addAttribute("ch", ch);
			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.秒杀页.getValue(), 1,WebSetEnum.app.getValue()));
		} catch (Exception e) {
			LogHandle.error(LogType.wap,MessageFormat.format("秒杀数据异常! 异常信息:{0}", e),"/wap/activityView/miao");
			return ErrorRedirect.getInstance().wapRedirect("秒杀活动错误");
		}
		return "/template/app/activity/app_miao";
	}
	
	@RequestMapping("/shan")
	public String shan(){
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/activity/shan";
	}
	@RequestMapping("/app/shan")
	public String appshan(Model model,Integer ch){
		try {
			model.addAttribute("ch", ch);
			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.闪购页.getValue(), 1,WebSetEnum.app.getValue()));
		} catch (Exception e) {
			LogHandle.error(LogType.wap,MessageFormat.format("闪购数据异常! 异常信息:{0}", e),"/wap/activityView/shan");
			return ErrorRedirect.getInstance().wapRedirect("闪购活动错误");
		}
		return "/template/app/activity/app_shan";
	}
	
	/**
	 * 广告
	 * @return
	 */
	@RequestMapping("/gg")
	public @ResponseBody BaseResult gg(String ch,int type,int display){
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item;
			}
			item.setData(adverisingService.getListByTypeAndDisplay(type, display,WebSetEnum.wap.getValue()));
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("广告异常：" + e.getMessage());
			LogHandle.error(LogType.Api,MessageFormat.format("精彩专题异常! 异常信息:{0}", e.toString()),"wap/gg");
		}
		return item;
	}
	
	/**
	 * 获取团购详情
	 * 
	 * @param ch
	 * @param id
	 * @return
	 */
	@RequestMapping("/getDetail")
	public String getDetail(String ch, String id,HttpServletRequest request) {
		String logpath = "activity/" + 3 + "/" + "groupbuyDetail";
		try {
			Integer groupid = StringUtilsEX.ToIntNull(id);
			if (groupid == null || groupid <= 0) {
				//groupid = 65;
			}
			GroupBuyDetailDto dto=groupBuyService.getDetail(StringUtilsEX.ToInt(id));
			request.setAttribute("GroupBuyDetail", dto);

		} catch (Exception e) {
			LogHandle.error(LogType.wap, "获取团购详细错误：{0}", e,logpath);
			return "/wap/error/index";
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/activity/groupbuyDetail";
	}

	
	@RequestMapping("/getOrderList.html")
	public String guangchang(String status,Model model){
		model.addAttribute("status", status);
		SessionUser user = SessionState.GetCurrentUser();
		if(user==null||user.getId()<=0){
			return "/template/wap/userinfo/login";
		}
		return "/template/wap/activity/OrderAll";
	}
	
	
	@RequestMapping("/topay.html")
	public String  topay(String ogc,String price,Model model){
	
		try {
			SessionUser user = SessionState.GetCurrentUser();
			model.addAttribute("ogc", ogc);
			model.addAttribute("price", price);
			Usercapital usercapital = usercapitalService.queryByUserId(user.getUserId());
			model.addAttribute("blance", usercapital.getBalance());
		} catch (Exception e) {
			LogHandle.error(LogType.wap, "访问订单支付页错误：{0}", e, "/wap/activityView/topay");
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/activity/orderPay";
	}
}

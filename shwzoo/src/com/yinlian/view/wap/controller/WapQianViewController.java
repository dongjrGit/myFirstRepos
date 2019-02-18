package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.po.Pointsrecords;
import com.yinlian.wssc.web.service.PointRecordService;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/qianview")
public class WapQianViewController {
	@Autowired
	private PointsRecordService pointsRecordService;
	@Autowired
	private UserslevelService     userslevelService;
	@Autowired
	private PointRecordService    pointRecordService;
    @Autowired
    private UserService    userService;
	 
		@RequestMapping("/qian")
		public String Concerns(HttpServletRequest request,Model model){
			try {
				Integer isqian=0;
				Integer getPoint=0;
				int continousCount=0;
				Integer count=0;
				List<Pointsrecords> list=null;
				Integer userid=SessionUtil.getSessionUserId(request);
				if(userid!=null){
					Integer a=pointsRecordService.getPoints(userid);
					if(a!=null){
						getPoint=a;
					}
				
					Integer b=pointsRecordService.getContinousCount(userid);
					if(b!=null){
						continousCount=b;
					}
					list=pointsRecordService.selectByuserid(userid);
					
					if(list!=null&&list.size()>0){
						 count=list.size();
					}
					List<Pointsrecords> hasdata=pointsRecordService.selectByuser(userid);
					if(hasdata!=null&&hasdata.size()>0){
						isqian=1;
					}
				}
				model.addAttribute("getPoint", getPoint);
				model.addAttribute("continousCount",continousCount);
				model.addAttribute("Pointsrecords", list);
				model.addAttribute("count", count);
				model.addAttribute("isqian", isqian);
				//System.out.println(continousCount);
			} catch (Exception e) {
	            LogHandle.error(LogType.wap, MessageFormat.format("异常! 异常信息:{0}", e.getMessage())
	                .toString(), "/wap/qianview/Concerns");
	            return ErrorRedirect.getInstance().wapRedirect("异常");
			}
			//return "redirect:/wap/404.html";
			return "/template/wap/qiandao/qian";
		}
		
}

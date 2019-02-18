package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Receiveaddress;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ReceiveAddressService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/receiveraddress")
public class WapReceiverAddressViewController {

	@Autowired
	private ReceiveAddressService receiveAddressService;

	@Autowired
	private AreaService areaservice;

	@Autowired
	private CityServcie cityServcie;

	@Autowired
	private ProvinceServcice provinceServcice;

	/*
	 * 添加新的收货地址页面
	 */
	@RequestMapping("/WritAddress")
	public ModelAndView WritAddress(HttpServletRequest request) {
		String href = request.getParameter("href");
		if (!StringUtilsEX.IsNullOrWhiteSpace(href)) {
			request.setAttribute("href", href);
		}
		/*ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/wap/404.html");
		return model;*/
		return new ModelAndView("/template/wap/address/WritAddress");
	}

	/**
	 * 查询收货人地址
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping("/selectAddrByUserId")
	public String selectAddrByUserId(String ch, HttpServletRequest request) {
		try {
			String href = request.getParameter("href");
			if (!StringUtilsEX.IsNullOrWhiteSpace(href)) {
				request.setAttribute("href", href);
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				return "/template/wap/userinfo/login";
			} else {
				String userid = sessionUser.getUserId().toString();
				List<Receiveaddress> list = receiveAddressService
						.queryAllByUserId(userid);
				request.setAttribute("addresss", list);
			}
		} catch (Exception e) {
			LogHandle.error(
					LogType.wap,
					MessageFormat.format("查询每个顾客的收货人地址异常! 异常信息:{0}",
							e.toString()),
					"/wap/receiveraddress/selectAddrByUserId");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/address/Address";
	}

	/**
	 * 编辑地址
	 */
	@RequestMapping(value = "/editAddrById", produces = "text/html;charset=UTF-8")
	public ModelAndView editAddrById(String addressid, String ch,
			HttpServletRequest request) {
		ch = "3";
		ModelAndView view = new ModelAndView();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {

			}
			if (StringUtilsEX.IsNullOrWhiteSpace(addressid)) {
				view.setViewName("/template/error/index");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				view.setViewName("/template/wap/userinfo/login");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			} else {
				int addresid = Integer.parseInt(addressid);
				Receiveaddress address = receiveAddressService
						.selectByPrimaryKey(addresid);
				List<Province> plist = provinceServcice.selectAll();
				List<City> clist = cityServcie.selectByProvinceCode(address
						.getProvincecode());
				List<Area> alist = areaservice.selectByCityCode(address
						.getCitycode());
				request.setAttribute("addresss", address);
				request.setAttribute("plist", plist);
				request.setAttribute("clist", clist);
				request.setAttribute("alist", alist);
				view.setViewName("/template/wap/address/Editaddress");
				String href = request.getParameter("href");
				if (!StringUtilsEX.IsNullOrWhiteSpace(href)) {
					request.setAttribute("href", href);
				}
				return view;
			}
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("编辑收货人地址异常! 异常信息:{0}", e.toString()),
					"/wap/receiveraddress/editAddrById");
			view.setViewName("/template/error/index");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;
		}

		// return "/template/wap/address/Address";
	}
}

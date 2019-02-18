package com.yinlian.wssc.platform.view.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.po.ZL_BaseInfo;
import com.yinlian.wssc.web.service.ZL_BaseInfoService;
import com.yinlian.wssc.web.util.StringUtilsEX;


@Controller
@RequestMapping("/platform/zlbase")
public class zlBaseinfoViewController {
	@Autowired
    private ZL_BaseInfoService baseinfo ;
	@RequestMapping("/Listpage")
    public String showAdvertImg(String id, HttpServletRequest request) {

        return "platform/zl_base/zl_baseList";
    }
	
	@RequestMapping("/edit")
    public String edit(String id,String type, HttpServletRequest request, String ctype) {
		ZL_BaseInfo zl=baseinfo.selectByPrimaryKey(StringUtilsEX.ToInt(id));
		request.setAttribute("info", zl);
		request.setAttribute("id", id);
		return "platform/zl_base/zl_baseEdit";
	}
}

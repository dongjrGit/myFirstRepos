package com.yinlian.wssc.platform.view.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Menus;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.MenuService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import data.ParseUtil;

@Controller
public class Login_PlatformController {
	
	
    @Autowired
    private MenuService        menuService;
    
    @Autowired
    private AccountsService    accountsService;
    
    @Autowired
    private  OrderService      orderService;

	/**
     * 显示平台主页面
     * 
     * @param model
     * @return
     */
    @RequestMapping("/platform/index")
    public String index(Model model, HttpServletRequest request) {
		try {
			List<Menus> menus = new ArrayList<Menus>();
			SessionUser sessionUser = SessionState.GetCurrentUser();
			Integer userid = sessionUser.getUserId();
			if (userid != null) {
				String loginname = sessionUser.getLoginName();
				if (loginname != null && sessionUser.getUtype() == 3) {
					menus = menuService.queryAllPlatformMenus(0);
				} else {
					List<Integer> rigths = sessionUser.getRights();
					menus = menuService.queryByRrigth(rigths);
				}
				request.setAttribute("name", loginname);
			}
			model.addAttribute("list", menus);

		} catch (Exception e) {
			LogHandle.error(LogType.Login, "查询登录用户所有菜单异常：", e,
					"/platform/index");
		}
		return "platform/index";
	}

    /**
     * 显示右边的页面
     * 
     * @return
     */
    @RequestMapping("/platform/right")
    public String right(HttpServletRequest request,Model model) {
    	try {
	    	Calendar calendar = Calendar.getInstance();
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String end = formatter.format(calendar.getTime());
	    	calendar.add(Calendar.DAY_OF_MONTH, -6);
	    	String start = formatter.format(calendar.getTime());
	    	
	    	Platfrom_SYCriteria criteria=new Platfrom_SYCriteria();
	    	criteria.setBegintime(start);
	    	criteria.setEndtime(end);
    	
			//List<Accounts> accoutslist=accountsService.queryAccounts(criteria);
			Integer accountCount=0;
			Integer ordercount=0;
			Double orderPrice=0.0d;
			
			accountCount=accountsService.queryAccountsCount(criteria);
			model.addAttribute("accounts", accountCount);
			
			
			Map<String, Object>  map=orderService.queryOrders(criteria);
			if(map!=null){
			if(map.containsKey("orderCount")&&map.get("orderCount")!=null){
				ordercount=StringUtilsEX.ToInt(map.get("orderCount").toString());
			}
			if(map.containsKey("orderprice")&&map.get("orderprice")!=null){
				orderPrice=StringUtilsEX.ToDoubleNull(map.get("orderprice").toString());
			}
			}
			model.addAttribute("orderCount", ordercount);
			model.addAttribute("orderprice", orderPrice);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
        return "platform/salesStatistics";
    }
    
}

package com.yinlian.wssc.seller.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Role;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.RoleService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 卖家综合管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/seller/zhglshop")
public class ZhglShopViewController {

    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(ZhglShopViewController.class);

    @Autowired
    private RoleService         roleService;
    @Autowired
    private ShopService         shopService;
    @Autowired
    private UsercapitalService  usercapitalService;
    
    @Autowired
    private ConfigSetService    configsetService;

    /**
     * 角色列表
     * @return
     */
    @RequestMapping("/zhgl_RoleList")
    public String zhgl_RoleList() {
        return "seller/zhgl/zhgl_RoleList";
    }

    /**
     * 角色编辑
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/zhgl_RoleEdit")
    public String zhgl_RoleEdit(String id, HttpServletRequest request) {
        Role role = new Role();
        String actionString = "addRole";
        try {
            if (StringUtilsEX.ToInt(id) > 0) {
                role = roleService.getByID(StringUtilsEX.ToInt(id));
                actionString = "updateRole";
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("data", role);
        request.setAttribute("roleaction", actionString);
        return "seller/zhgl/zhgl_RoleEdit";
    }

    @RequestMapping("/zhgl_RolePerm")
    public String zhgl_RolePerm(String action, String id, HttpServletRequest request) {

        String actionString = "", roleid = "";
        try {
            if (StringUtilsEX.ToInt(id) > 0) {
                Role role = roleService.getByID(StringUtilsEX.ToInt(id));
                roleid = id;
                actionString = action;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("roleid", roleid);
        request.setAttribute("act", actionString);
        return "seller/zhgl/zhgl_RolePerm";
    }

    /**
     * 显示个人资料的页面
     * 
     * @return
     */
    @RequestMapping("/showBasicInfo")
    public String showBasicInfo(HttpServletRequest request, Integer userid) {
        try {
            userid = SessionUtil.getSessionUserId(request);
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("userid", userid);
        return "seller/zhgl/basicInfo";
    }

    /**
     * 卖家显示修改密码页面
     * 
     * @return
     */
    @RequestMapping("/showUpdatePassword")
    public String showUpdatePassword(HttpServletRequest request, Integer userid) {
        try {
            userid = SessionUtil.getSessionUserId(request);
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("userid", userid);
        return "seller/zhgl/changeLoginPwd";
    }

    /**
     * 显示子账号的页面
     * showEmployeeManagement?userid=9
     * @return
     */
    @RequestMapping("/showEmployeeManagement")
    public String showEmployeeManagement(HttpServletRequest request, Integer shopid) {
        try {
            shopid = SessionUtil.getSessionUserShopId(request);
            Shop shop = shopService.queryById(shopid);
            request.setAttribute("shop", shop);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "seller/zhgl/employeeManagement";
    }

    /**
     * 显示子账号添加的页面
     * 
     * @return
     */
    @RequestMapping("/showEmployeeManagementAdd")
    public String showEmployeeManagementAdd(Integer shopid, Model model) {
        try {
            Shop shop = shopService.queryById(shopid);
            model.addAttribute("shop", shop);
            List<Role> roles = roleService.selectShopRole(shop.getId());
            model.addAttribute("roles", roles);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "seller/zhgl/employeeManagementAdd";
    }

    /**
     * 显示子账号编辑的页面
     * 
     * @return
     */
    @RequestMapping("/showEmployeeManagementEdit")
    public String showEmployeeManagementEdit() {

        return "seller/zhgl/employeeManagementEdit";
    }

    /**
     * 显示财务记录的页面
     * 
     * @return
     */
    @RequestMapping("/showFinanceList")
    public String showFinanceList(HttpServletRequest request, Integer userid) {
        try {
            userid = SessionUtil.getSessionUserId(request);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "seller/zhgl/financeList";
    }

    /**
     * 显示充值页面
     * 
     * @param model
     * @param userid
     * @return
     */
    @RequestMapping("/showAccountVoucher")
    public String showAccountVoucher(HttpServletRequest request, Integer userid) {
        try {
            userid = SessionUtil.getSessionUserId(request);
            List<Shop> list = shopService.selectListByUserId(userid);
            Usercapital usercapital = usercapitalService.queryByUserId(userid);
            request.setAttribute("usercapital", usercapital);
            request.setAttribute("shop", list.get(0));
            Configdictionary   configdictionary=configsetService.showConfigSetByType(ConfigSetTypeEnum.店铺保证金.getValue());
            if(configdictionary!=null){
            	request.setAttribute("lowband", configdictionary.getValue());
            }
            request.setAttribute("userid", userid);
        } catch (Exception e) {
            logger.error("异常：", e.getMessage());
        }
        return "seller/zhgl/accountVoucher";
    }
}

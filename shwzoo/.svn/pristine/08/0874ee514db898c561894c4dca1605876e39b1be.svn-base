package com.yinlian.wssc.platform.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.LotteryParamTypeEnum;
import com.yinlian.api.app.dto.CouponShopDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.AccountsDTO;
import com.yinlian.wssc.web.dto.DepartmentDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.po.LotteryDictionary;
import com.yinlian.wssc.web.po.LotteryParam;
import com.yinlian.wssc.web.po.Menus;
import com.yinlian.wssc.web.po.Payconfig;
import com.yinlian.wssc.web.po.Role;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.DepartmentService;
import com.yinlian.wssc.web.service.FreightAttrService;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.LotteryDictionaryService;
import com.yinlian.wssc.web.service.LotteryParamService;
import com.yinlian.wssc.web.service.MenuService;
import com.yinlian.wssc.web.service.PayconfigService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.RoleService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/platform/controlpanel")
public class ControlPanelController {

    @Autowired
    private AreaService              areaService;

    /**
     * 日志输出的类
     */
    private static final Logger      logger = LoggerFactory.getLogger(ControlPanelController.class);

    @Autowired
    private MenuService              menuService;

    @Autowired
    private ProvinceServcice         provinceServcice;

    @Autowired
    private DepartmentService        departmentService;

    @Autowired
    private RoleService              roleService;

    @Autowired
    private ShopService              shopService;

    @Autowired
    private AccountsService          accountsService;
    @Autowired
    private LotteryDictionaryService lotteryDictionaryService;
    @Autowired
    private LotteryParamService      lotteryParamService;

    @Autowired
    private CouponService            couponService;
    @Autowired
    private FreightService           freightSerivice;
    @Autowired
    private FreightAttrService       reightAttrService;
    
    @Autowired
    private PayconfigService         payconfigService;
    /**
     * 显示表单
     * 
     * @param stype
     * @param model
     * @return
     */
    @RequestMapping("/showConfigSet")
    public String showConfigSet(Integer stype, Model model) {

        return "platform/controlpanel/Config_Set";

    }

    @RequestMapping("/Control_menuList")
    public String Control_menuList() {
        return "platform/controlpanel/Control_Menulist";
    }

    /**
     * 显示地区管理
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showAraeManagement")
    public String showAraeManagement(Integer id, Model model) {

        return "platform/controlpanel/AreaManagement_list";

    }

    @RequestMapping("/Control_menuAdd")
    public String Control_menuAdd() {
        return "platform/controlpanel/Control_Menuadd";
    }

    @RequestMapping("/Control_menuEdit")
    public String Control_menuEdit(String id, Model model) {
        Menus menus = new Menus();
        String fullpath, fid = "", sid = "", tid = "";
        try {
            if (StringUtilsEX.ToInt(id) > 0) {
                menus = menuService.getByID(StringUtilsEX.ToInt(id));
                if (menus != null) {
                    fullpath = menus.getFullpath();

                    if (fullpath.split(",").length > 0) {

                        switch (fullpath.split(",").length) {
                            case 1:
                                break;
                            case 2:
                                fid = fullpath.split(",")[0];
                                break;
                            case 3:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                break;
                            case 4:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                tid = fullpath.split(",")[2];
                                break;
                            default:
                                break;
                        }
                    } else {
                        fid = menus.getId().toString();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        model.addAttribute("data", menus);
        model.addAttribute("fid", fid);
        model.addAttribute("sid", sid);
        model.addAttribute("tid", tid);

        return "platform/controlpanel/Control_Menuedit";
    }

    /**
     * 显示支付方式管理页面
     * 
     * @param model
     * @return
     */

    @RequestMapping("/paySet_list")
    public String paySet_list(Model model) {

        return "platform/controlpanel/PaySet_list";
    }

    /**
     * 显示添加支付方式页面
     * 
     * @param model
     * @return
     */
    @RequestMapping("/paySet_add")
    public String paySet_add(Model model) {

        return "platform/controlpanel/PaySet_add";
    }

    /**
     * 显示运费模板页面
     * 
     * @return
     */
    @RequestMapping("/Freight_Manager_list")
    public String Freight_Manager_list(HttpServletRequest request) {
        try {
            SessionUser sessionUser = SessionUtil.getSessionUser(request);
            Integer userid = sessionUser.getUserId();
            Integer shopid = sessionUser.getShopid();
            Accounts accouts = accountsService.queryByuserid(userid);
            Shop shop = shopService.queryById(shopid);
            request.setAttribute("accouts", accouts);
            request.setAttribute("shop", shop);
        } catch (Exception e) {
            logger.error("", e);
        }

        return "platform/controlpanel/Freight_Manager_list";
    }
    
    /**
     * 显示运费子模板页面
     * 
     * @return
     */
    @RequestMapping("/subFreight_Manager_list")
    public String subFreight_Manager_list(String ftid,HttpServletRequest request) {
        try {
        	Freight freight=freightSerivice.selectById(StringUtilsEX.ToInt(ftid));
        	if(freight!=null){
        		Integer isby=freight.getIsexemptionpostage();
        		/* List<FreightAttr> freightAttr=reightAttrService.selectByFreightId(StringUtilsEX.ToInt(ftid));*/
        		request.setAttribute("ftid", ftid);
        		if(isby==1){
        			return "platform/controlpanel/bysubFreight_Manager_list";
        		}else{
        			return "platform/controlpanel/nobysubFreight_Manager_list";
        		}
        	}
        } catch (Exception e) {
            logger.error("", e);
        }
        return "";
    }
    
    
    /**
     * 平台角色列表
     * 
     * @return
     */
    @RequestMapping("/control_RoleList")
    public String control_RoleList() {
        return "platform/controlpanel/Control_RoleList";
    }

    /**
     * 平台角色编辑
     * 
     * @return
     */
    @RequestMapping("/control_RoleEdit")
    public String control_RoleEdit(String id, HttpServletRequest request) {
        Role role = new Role();
        String roleaction = "addRole";
        try {
            if (StringUtilsEX.ToInt(id) > 0) {
                role = roleService.getByID(StringUtilsEX.ToInt(id));
                if (role != null) {
                    roleaction = "updateRole";
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        request.setAttribute("data", role);
        request.setAttribute("roleaction", roleaction);
        return "platform/controlpanel/Control_RoleEdit";
    }

    /**
     * 店铺角色列表
     * 
     * @return
     */
    @RequestMapping("/control_dpRoleList")
    public String control_dpRoleList() {
        return "platform/controlpanel/Control_dpRoleList";
    }

    /**
     * 店铺角色编辑
     * 
     * @return
     */
    @RequestMapping("/control_dpRoleEdit")
    public String control_dpRoleEdit(String id, HttpServletRequest request) {
        Role role = new Role();
        String roleaction = "addShopRole", shopname = "";
        try {
            if (StringUtilsEX.ToInt(id) > 0) {
                role = roleService.getByID(StringUtilsEX.ToInt(id));
                if (role != null) {
                    shopname = shopService.queryById(role.getShopid()).getName();
                    roleaction = "updateShopRole";
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        request.setAttribute("data", role);
        request.setAttribute("roleaction", roleaction);
        request.setAttribute("shopname", shopname);
        return "platform/controlpanel/Control_dpRoleEdit";
    }

    /**
     * 角色编辑权限
     * 
     * @param action
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/control_RolePerm")
    public String control_RolePerm(String action, String id, HttpServletRequest request) {
        String actionString = "", roleid = "", roletype = "";
        try {
            if (StringUtilsEX.ToInt(id) > 0) {
                Role role = roleService.getByID(StringUtilsEX.ToInt(id));
                if (role.getShopid() == 0) {
                    roletype = "0";
                } else {
                    roletype = "1";
                }
                roleid = id;
                actionString = action;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        request.setAttribute("roleid", roleid);
        request.setAttribute("roletype", roletype);
        request.setAttribute("act", actionString);
        return "platform/controlpanel/Control_RolePerm";
    }

    /**
     * 显示部门管理页面
     * 
     * @return
     */
    @RequestMapping("/department_list")
    public String department_list(Model model) {

        return "platform/controlpanel/Department_list";
    }

    /**
     * 显示添加部门页面
     * 
     * @param model
     * @return
     */
    @RequestMapping("/department_Save")
    public String department_Save(Integer id, Model model) {
        DepartmentDto department = null;
        try {
            department = departmentService.queryDepartById(id);
            if (department != null) {
                department.setTiptitle("编辑部门");
                department.setAction("updateDepartById");
            } else {
                department = new DepartmentDto();
                department.setTiptitle("添加部门");
                department.setAction("addDepart");
            }
        } catch (Exception e) {
            logger.error("显示添加或修改部门页面的信息出错" + e.getMessage(), e);

        }
        model.addAttribute("department", department);
        return "platform/controlpanel/Department_Save";
    }

    /**
     * 操作员列表
     * 
     * @return
     */
    @RequestMapping("/operator_list")
    public String operator_list() {
        return "platform/controlpanel/operator_list";
    }

    /**
     * 操作员编辑
     * 
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/operator_edit")
    public String operator_edit(String id, HttpServletRequest request) {
        AccountsDTO dto = new AccountsDTO();
        String actionString = "addOperator";
        String fid = "", sid = "", tid = "";
        try {
            if (StringUtilsEX.ToInt(id) > 0) {
                dto = accountsService.selectByID(StringUtilsEX.ToInt(id));
                actionString = "updateOperator";
                if (dto.getDepartmentid() > 0) {
                    String fullpath = departmentService.getByID(dto.getDepartmentid())
                        .getFullpath();

                    if (fullpath.split(",").length > 0) {

                        switch (fullpath.split(",").length) {
                            case 1:
                                fid = fullpath.split(",")[0];
                                break;
                            case 2:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                break;
                            case 3:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                tid = fullpath.split(",")[2];
                                break;
                            default:
                                break;
                        }
                    } else {
                        fid = dto.getId().toString();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        request.setAttribute("data", dto);
        request.setAttribute("action", actionString);
        request.setAttribute("fid", fid);
        request.setAttribute("sid", sid);
        request.setAttribute("tid", tid);
        return "platform/controlpanel/operator_edit";
    }

    /**
     * 显示积分规则
     * 
     * @return
     */
    @RequestMapping("/showPointsRule")
    public String showPointsRule() {

        return "platform/controlpanel/pointsRules";
    }

    /**
     * 显示发送模板页面
     * 
     * @return
     */
    @RequestMapping("/showSendTemplate")
    public String showSendTemplate() {

        return "platform/controlpanel/sendTemplate";
    }

    /**
     * 显示添加和编辑页面
     * 
     * @return
     */
    @RequestMapping("/showSendTemplateEdit")
    public String showSendTemplateEdit() {

        return "platform/controlpanel/sendTemplateEdit";
    }

    /**
     * 显示抽奖规则页面
     * 
     * @return
     */
    @RequestMapping("/showlottery_rule")
    public String showlottery_rule() {

        return "platform/controlpanel/lottery_rule";
    }

    /**
     * 显示抽奖规则添加和编辑页面
     * 
     * @return
     */
    @RequestMapping("/showlottery_rule_add")
    public String showlottery_rule_add(Integer id, Model model) {
        try {
            LotteryDictionary lotteryDictionary = lotteryDictionaryService.queryById(id);
            model.addAttribute("lotteryDictionary", lotteryDictionary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "platform/controlpanel/lottery_rule_add";
    }

    /**
     * 显示中奖列表页面
     * 
     * @return
     */
    @RequestMapping("/showlottery")
    public String showlottery() {

        return "platform/controlpanel/lottery";
    }

    /**
     * 显示奖项信息的列表
     *@return
     */
    @RequestMapping("/showlotteryparam")
    public String showlotteryparam() {

        return "platform/controlpanel/lottery_param";
    }

    @RequestMapping("/lottery_param_edit")
    public String lottery_param_edit(Integer id, Model model) {
        try {
            List<LotteryDictionary> list = lotteryDictionaryService.selectAll();
            model.addAttribute("dictionarys", list);
            LotteryParam record = lotteryParamService.queryById(id);
            model.addAttribute("record", record);
        } catch (Exception e) {
            logger.error("", e);
            e.printStackTrace();
        }

        return "platform/controlpanel/lottery_param_edit";
    }
    
    
    @RequestMapping("/lottery_param_edit1")
    public String lottery_param_edit1(Integer id,Integer mark,Model model) {
        try {
        	model.addAttribute("ruleid",id);
            List<LotteryDictionary> list = lotteryDictionaryService.selectAll();
            model.addAttribute("dictionarys", list);
            LotteryParam record = lotteryParamService.getByLId(id);
            model.addAttribute("record", record);
            model.addAttribute("mark",mark);
            if(mark==LotteryParamTypeEnum.代金卷.getValue()){
            	List<CouponShopDto> couponlist=couponService.getLotteryCouponList();
            	model.addAttribute("couponlist", couponlist);
            }
        } catch (Exception e) {
            logger.error("", e);
            e.printStackTrace();
        }

        return "platform/controlpanel/lottery_param_edit1";
    }
    
    //支付配置
    @RequestMapping("/payConfig_list")
    public String payConfig_list(){
    	return "platform/controlpanel/payConfig_list";
    }
    
    @RequestMapping("/payConfig_edit")
    public String payConfig_edit(String id, HttpServletRequest request){
    	Payconfig payc=new Payconfig();
    	String actionString="add";
    	try{
    		if(StringUtilsEX.ToInt(id)>0){
    			payc=payconfigService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
    			actionString="edit";
    		}
    	}catch (Exception e) {
            e.printStackTrace();
        }
    	request.setAttribute("data", payc);
    	request.setAttribute("action", actionString);
    	return "platform/controlpanel/payConfig_edit";
    }
    
    @RequestMapping("/UserOperater")
    public String UserOperater(){
    	return "platform/controlpanel/UserOperater";
    }
    
    @RequestMapping("/deleteParamById")
    @ResponseBody
    public ReusltItem deleteParamById(String id){
    	ReusltItem item=new ReusltItem();
    	try {
    		if(lotteryParamService.deleteById(StringUtilsEX.ToInt(id))>0){
    			item.setCode(0);
    			item.setDesc("删除成功");
    			return item;
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return item;
    }
    
}



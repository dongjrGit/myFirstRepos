package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ChannelTypeEnum;
import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.ShopStatusEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.ShopClass;
import com.yinlian.wssc.web.po.Smsrecord;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.EmployeeService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.RoleMenusService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SmsService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.BasicConvert;
import com.yinlian.wssc.web.util.CommonUtils;
import com.yinlian.wssc.web.util.ConfigUtil;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.JsonUtil;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SmsUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.UserXml;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/seller")
public class SellerLoginController {

    /**
     * 输出日志的控制类
     */
    private static final Logger logger = LoggerFactory.getLogger(SellerLoginController.class);

    @Autowired
    private AccountsService     accountsService;
    @Autowired
    private UserService         userService;
    @Autowired
    private EmployeeService     employeeService;
    @Autowired
    private ShopService         shopService;
    @Autowired
    private RoleMenusService    roleMenusService;
    @Autowired
    private CategoryService     categoryService;
    @Autowired
    private SmsService          smsService;
    @Autowired
    private AreaService         areaService;
    @Autowired
    private CityServcie         cityServcie;
    @Autowired
    private ProvinceServcice    provinceServcice;
    @Autowired
    private ConfigSetService    configSetService;

    @Autowired
    private UserslevelService   userslevelService;
    @Autowired
    private OperaterecordsService  operaterecordsService;

    @RequestMapping("/user/toLogin")
    public @ResponseBody ReusltItem toLogin(String ChannelType, String UserName, String Pwd,
                                            String LoginType) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.IsNullOrWhiteSpace(UserName)) {
            item.setCode(-102);
            item.setDesc("登录名(name)不能为空！");
            return item;
        }
        if (StringUtilsEX.IsNullOrWhiteSpace(Pwd)) {
            item.setCode(-103);
            item.setDesc("密码(pwd)不能为空！");
            return item;
        }
        String token = UUID.randomUUID().toString();
        //session.setAttribute(ConstanValue.TOKEN_KEY, token);
        //Accounts accounts = null;
        //SessionUser sessionUser = new SessionUser();

        try {
            /*if(accountsService.queryOperator(UserName, Pwd, UserTypeEnum.Seller.getValue())!=null){
            	
            	accounts=accountsService.queryOperator(UserName, Pwd, UserTypeEnum.Seller.getValue());
            	
            }else if(accountsService.queryByPhone(UserName, Pwd, UserTypeEnum.Seller.getValue())!=null){
            	
            	accounts=accountsService.queryByPhone(UserName, Pwd, UserTypeEnum.Seller.getValue());
            	
            }else{
            	
            	accounts=accountsService.queryByEmail(UserName, Pwd, UserTypeEnum.Seller.getValue());
            }*/

            Integer[] array = { UserTypeEnum.Seller.getValue(), UserTypeEnum.Employee.getValue() };
            String userInfo = "";
            Object[] rls = accountsService.queryLogin(UserName, Pwd, array);
            switch (Integer.parseInt(rls[0].toString())) {
                case 0:
                    userInfo = JsonUtil.getJsonStringFromObject(rls[1]);
                    break;
                case -1:
                    item.setCode(-104);
                    item.setDesc("登录失败,用户名或密码错误！");
                    return item;
                case -2:
                    item.setCode(-105);
                    item.setDesc("登录失败,账户被锁定！");
                    return item;
                case -3:
                    item.setCode(-106);
                    item.setDesc("登录失败,账户异常！");
                    return item;
                default:
                    item.setCode(-104);
                    item.setDesc("登录失败,用户名或密码错误！");
                    return item;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            SessionUser sessionUser = (SessionUser) rls[1];
            SessionState.SetSessionUser(token, sessionUser);
            // Users users=userService.queryById(sessionUser.getUserId());
            item.setToken(token);
            item.setCode(0);
            Integer shopStatus = shopService.queryById(sessionUser.getShopid()).getStatus();

            map.put("Token", token);
            map.put("Channel", ChannelType);
            map.put("sessionUser", sessionUser);
            map.put("UserID", sessionUser.getUserId());
            map.put("UserName", sessionUser.getLoginName());
            /* map.put("Mobile",users.getMobile());
             map.put("Email",users.getEmail());*/
            map.put("ShopStatus", shopStatus);
            //System.out.println("+++++++++++++++++"+shopService.queryById(shopId.get(0)).getStatus());
            item.setData(JsonUtil.getJsonStringFromMap(map));
            /*if (accounts == null) {
                item.setCode(-200);
                item.setDesc("用户名或密码错误");
            } else {
                String realName = "";
                Integer shopId = null;
                Integer userId = accounts.getUserid();
                Integer usertype = accounts.getUsertype();
                switch (usertype) {
                    case 1:
                        Employee employee = employeeService.queryByNameAndPassword(UserName, Pwd);
                        shopId = employee.getShopid();
                        realName = employee.getRealname();
                        break;

                    case 5:
                        List<Shop> list = null;
                        if (userId != null) {
                            list = shopService.selectListByUserId(userId);
                        }

                        if (list != null && list.size() > 0) {
                            shopId = list.get(0).getId();
                        }
                        realName = userService.queryById(userId).getRealname();
                        break;
                }

                List<Integer> shopId=new ArrayList<Integer>();
                for (int i = 0; i < list.size(); i++) {
                	shopId.add(list.get(i).getId());
                }

                //				Redis_UserInfo.Set("channel"+userId, ChannelType);
                //				Redis_UserInfo.Set("token"+userId, token);
                String LoginName = accounts.getLoginname();
                List<Integer> rights = new ArrayList<Integer>();
                List<RoleMenus> roleMenus = roleMenusService.selectByRoleId(accounts.getRoleid());
                if (roleMenus != null && roleMenus.size() > 0) {
                    for (int i = 0; i < roleMenus.size(); i++) {
                        rights.add(roleMenus.get(i).getMenusid());
                    }
                } else {
                    rights = null;
                }

                sessionUser.setUserId(userId);
                sessionUser.setName(realName);
                sessionUser.setLoginName(LoginName);
                sessionUser.setRights(rights);
                if (shopId != null) {
                    sessionUser.setShopid(shopId);
                }
                SessionState.SetSessionUser(token, sessionUser);
                item.setToken(token);
                item.setCode(0);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("Token", token);
                map.put("Channel", ChannelType);
                map.put("sessionUser", sessionUser);
                map.put("UserID", accounts.getUserid());
                map.put("UserName", accounts.getLoginname());
                map.put("Mobile", accounts.getPhone());
                map.put("Email", accounts.getEmail());
                map.put("ShopStatus", shopService.queryById(shopId).getStatus());
                //System.out.println("+++++++++++++++++"+shopService.queryById(shopId.get(0)).getStatus());
                item.setData(JsonUtil.getJsonStringFromMap(map));
            }*/

        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("登录异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
					"登录异常:{0}", e,
					"/seller/user/toLogin");
        }
        return item;
    }

    @RequestMapping("/user/toUserRegister")
    public @ResponseBody ReusltItem toUserRegister(String typevalue, String username, String pwd,
                                                   String rpwd, String pcode) {
        ReusltItem item = new ReusltItem();
        try {
            if (!StringUtils.isNotNull(username)) {
                item.setCode(-101);
                item.setDesc("用户名不能为空");
            }
            if (!StringUtils.isNotNull(pwd)) {
                item.setCode(-102);
                item.setDesc("密码不能为空");
            }
            if (!StringUtils.isNotNull(rpwd)) {
                item.setCode(-103);
                item.setDesc("确认密码不能为空");
            }
            if (!pwd.equalsIgnoreCase(rpwd)) {
                item.setCode(-104);
                item.setDesc("两次密码不一致");
            }
            if (!StringUtils.isNotNull(typevalue)) {
                item.setCode(-105);
                item.setDesc("电话不能为空");
            }
            if (!StringUtils.isNotNull(pcode)) {
                item.setCode(-105);
                item.setDesc("短信验证码不能为空");
            }

            // 验证短信验证码
            String smss = RedisUserInfo.Get("I" + typevalue);
            if (!pcode.equals(smss)) {
                item.setCode(-106);
                item.setDesc("短信验证码错误");
            }

            Users users = new Users();
            users.setMobile(typevalue);
            users.setUsername(username);
            users.setPassword(pwd);
            users.setStatus(0);
            Userslevel userlevel = userslevelService.getlowerLevel();
            users.setLevelid(userlevel.getId());
            users.setLevel(userlevel.getLevel());
            users.setTotalpoints(0);
            users.setPoints(0);
            users.setAvailablebeans(0);
            users.setTotalbeans(0);

            int temp = userService.insert(users, typevalue, username, pwd, rpwd,ChannelTypeEnum.PC.getValue());
            if (temp < 0) {
                return item;

            }
            item.setCode(0);
            item.setDesc("用户注册成功");
            UserXml userXml = new UserXml();
            userXml.setUserName(users.getUsername());
            userXml.setMobile(users.getMobile());
            userXml.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(pwd));

            try {//TODO： 原支撑系统 要修改
              /*  Map<String, Object> map = WebserviceClient.register(
                    WebServiceTypeEnum.用户注册.getValue(), userXml);
                if (map != null && "1".equals(map.get("ResultCode"))) {
                    LogHandle.error(LogType.Api, MessageFormat.format(
                        "Webservice账户注册! 提交参数信息:{0},Webservice账户注册返回信息:{0},{1}",
                        userXml.toString(), map.get("ResultCode"), map.get("ResultMsg")),
                        "userinfo/register");
                } else {
                    LogHandle.error(LogType.Api, MessageFormat.format(
                        "Webservice账户注册错误! 提交参数信息:{0},{1},Webservice账户注册返回信息:{0},{1}",
                        userXml.toString(), map.get("ResultCode"), map.get("ResultMsg")),
                        "userinfo/register");
                }*/
            } catch (Exception e) {
                LogHandle.error(LogType.Api,
                    MessageFormat.format("Webservice账户注册异常! 异常信息:{0}", e.toString()),
                    "userinfo/register");
            }
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("注册异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
					"注册异常:{0}", e,
					"/seller/user/toUserRegister");
        }
        return item;
    }

    /**
     * 注册卖家shop
     * 
     * @param users
     * @param resultUser
     * @param shop
     * @param resultShop
     * @param authentication
     * @param result
     * @param fc_select
     * @param sc_select
     * @param tc_select
     * @return
     */
    @RequestMapping("/user/toRegister")
    public @ResponseBody ReusltItem toRegister(String loginname, String passwrd_pwd,
                                               String confirmpassword, String principalmobile,
                                               String imgverification, String mobilecode,
                                               String principalemail, String shopname,
                                               String principalname, String companytel,
                                               String companyname, String license,
                                               String organization, String tax, String certificate,
                                               String bank, String companyprovince,
                                               String companycity, String companyarea,
                                               String companyfox, String companyadress,
                                               String postcode, String companytype,
                                               String companyweb, String thirdclass,
                                               String thirdfullpath, String companysales,
                                               String longitude, String latitude, String imageurl,
                                               String description, String supporttel,
                                               String provincename, String cityname,
                                               String areaname, String banktype, String lineno,
                                               String bankname, String bankcardno,
                                               String hodername, String accounttype,
                                               String shopcircleid, String isep, String issupport,
                                               String classid, String isfree, String isjck,
                                               String popularity, String consumption,
                                               String discount,String licenseNum,String settlementType, HttpServletRequest request,String jcbg,String scxkz,String spy) {
        ReusltItem item = new ReusltItem();

        try {
            checkParameters(loginname, passwrd_pwd, confirmpassword, principalmobile,
                imgverification, mobilecode, principalemail, shopname, principalname, companytel,
                companyname, license, organization, tax, certificate, bank, companyprovince,
                companycity, companyarea, companyfox, companyadress, postcode, companytype,
                companyweb, thirdclass, thirdfullpath, companysales, longitude, latitude, imageurl,
                description, supporttel,jcbg,scxkz,spy,settlementType, item);

            if (item.getCode() < 0) {
                return item;
            }
//            if (StringUtilsEX.ToInt(classid)<= 0) {
//				item.setCode(-130);
//				item.setDesc("请选择店铺分类"); 
//				return item;
//			}
            if (!StringUtilsEX.IsNullOrWhiteSpace(popularity)) {
            	if(StringUtilsEX.ToInt(popularity)<0){
            		item.setCode(-307);
                    item.setDesc("人气请输入大于0的整数");
                    return item;
            	}            	
			}
            List<String> classidList = new ArrayList<String>();
            List<String> fullpathList = new ArrayList<String>();
            //String classid = thirdclass.substring(0, thirdclass.indexOf("|"));
            classidList = subs(thirdclass, classidList);
            //String fullpath = thirdfullpath.substring(0, thirdfullpath.indexOf("|"));
            fullpathList = subs(thirdfullpath, fullpathList);
            classidList = CommonUtils.removeDuplicateWithOrder(classidList);
            fullpathList = CommonUtils.removeDuplicateWithOrder(fullpathList);
            // 向 
           // Integer actype = StringUtilsEX.ToInt(accounttype);
            ShopClass shopClass = new ShopClass();
            Shop shop = new Shop();
            shop.setDiscount(discount);
            shop.setBankcardno(bankcardno);
            shop.setBankname(bankname);
            shop.setBanktype(banktype);
            shop.setLineno(lineno);
            shop.setHodername(hodername);
           /* if (!"-1".equals(accounttype)) {
                shop.setAccounttype(actype);
            }*/

            shop.setIsep(StringUtilsEX.ToBoolean(isep));
//            shop.setIsjck(StringUtilsEX.ToBoolean(isjck));
            shop.setIsjck(false);
           // shop.setIssupport(StringUtilsEX.ToBoolean(issupport));
           // shop.setIsfree(StringUtilsEX.ToBoolean(isfree));
            /*if(StringUtilsEX.ToIntNull(classid)>0){
            	shop.setClassid(StringUtilsEX.ToIntNull(classid));
            }else{*/
            	shop.setClassid(0);
            //}
            if(StringUtilsEX.ToInt(shopcircleid)>0){
            shop.setCircleid(StringUtilsEX.ToInt(shopcircleid));
            }
            shopClass.setCreatetime(new Date());
            shop.setSupporttel(supporttel);
            shop.setMargin(StringUtilsEX.ToFloat("0.0"));
            shop.setName(shopname);
            shop.setImgurl(imageurl);
            shop.setStatus(ShopStatusEnum.Checking.getValue());
            shop.setDescription(description);
            shop.setShopnum(ProductNumUtil.getShopNum());
            shop.setCompanyname(companyname);
            shop.setIsdel(false);
            shop.setIsflagship(false);
            shop.setIsowned(false);
            shop.setShopaddress(provincename + cityname + areaname + companyadress);
            shop.setPopularity(StringUtilsEX.ToInt(popularity));
            shop.setSettlementtype(StringUtilsEX.ToInt(settlementType));
            float consum = StringUtilsEX.ToFloat(consumption);
            if (consum <= 0) {
                consum = 0;
            }
            shop.setConsumption(consum);
            Users users = new Users();
            Accounts accounts = new Accounts();
            users.setPassword(passwrd_pwd);
            users.setEmail(principalemail);
            users.setMobile(principalmobile);
            users.setUsername(loginname);

            accounts.setLoginname(loginname);
            accounts.setPassword(passwrd_pwd);

            ShopAuthentication authentication = new ShopAuthentication();
            // 数据库中不为空的
            authentication.setCompanycontactname(principalname);
            authentication.setIseshop(0);

            authentication.setLatitude(latitude);
            authentication.setLongitude(longitude);

            authentication.setBank(bank);
            authentication.setCertificate(certificate);
            authentication.setLicense(license);
            authentication.setOrganization(organization);
            authentication.setTax(tax);

            authentication.setCompanyadress(companyadress);
            authentication.setCompanyname(companyname);
            authentication.setCompanycity(companycity);
            authentication.setCompanyfox(companyfox);
            authentication.setCompanyarea(companyarea);
            authentication.setCompanyprovince(companyprovince);
            authentication.setCompanytel(companytel);
            authentication.setPostcode(postcode);
            authentication.setCompanytype(StringUtilsEX.ToInt(companytype));
            authentication.setCompanysales(BasicConvert.string2BigDecimal(companysales)); //公司年销售额
            authentication.setCompanyweb(companyweb);
            authentication.setPrincipalemail(principalemail);
            authentication.setPrincipalname(principalname);
            authentication.setPrincipalmobile(principalmobile);
            authentication.setCompanyemail(principalemail);
            authentication.setLicenseNum(licenseNum);
            authentication.setExaminereport(jcbg);
            authentication.setProducelicence(scxkz);
            authentication.setAdoctrinecredential(spy);
            int code = shopService.insertShop(shop, shopClass, users, authentication, accounts);
            if (code > 0) {
                item.setCode(0);
                item.setDesc("新增成功");

            } else {
                item.setCode(-200);
                item.setDesc("新增失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("注册卖家shop异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
					"注册卖家shop异常:{0}", e,
					"/seller/user/toRegister");
        }
        return item;
    }

    /**
     * 校验参数
     * @param loginname
     * @param passwrd_pwd
     * @param confirmpassword
     * @param principalmobile
     * @param imgverification
     * @param mobilecode
     * @param principalemail
     * @param shopname
     * @param item 
     * @param latitude 
     * @param longitude 
     * @param companysales 
     * @param thirdfullpath 
     * @param thirdclass 
     * @param companyweb 
     * @param companytype 
     * @param margin 
     * @param postcode 
     * @param companyadress 
     * @param companyfox 
     * @param companyarea 
     * @param companycity 
     * @param companyprovince 
     * @param bank 
     * @param certificate 
     * @param tax 
     * @param organization 
     * @param license 
     * @param companyname 
     * @param companytel 
     * @param principalname 
     * @param spy 
     * @param scxkz 
     * @param jcbg 
     * @param settlementType 
     */
    private void checkParameters(String loginname, String passwrd_pwd, String confirmpassword,
                                 String principalmobile, String imgverification, String mobilecode,
                                 String principalemail, String shopname, String principalname,
                                 String companytel, String companyname, String license,
                                 String organization, String tax, String certificate, String bank,
                                 String companyprovince, String companycity, String companyarea,
                                 String companyfox, String companyadress, String postcode,
                                 String companytype, String companyweb, String thirdclass,
                                 String thirdfullpath, String companysales, String longitude,
                                 String latitude, String imageurl, String description,
                                 String supporttel, String jcbg, String scxkz, String spy, String settlementType, ReusltItem item) {
        if (!StringUtils.isNotNull(loginname)) {
            item.setCode(-101);
            item.setDesc("用户名不能为空");
        }
        if (!StringUtilsEX.checkUserName(loginname)) {
            item.setCode(-105);
            item.setDesc("用户名不正确，请输入3~16个字符，26个大小写字、数字或“_-”（不能以数字开头）");
            return;
        }
        if (!StringUtils.isNotNull(passwrd_pwd)) {
            item.setCode(-102);
            item.setDesc("密码不能为空");
        }
        if (!StringUtils.isNotNull(confirmpassword)) {
            item.setCode(-103);
            item.setDesc("确认密码不能为空");
        }
        if (!passwrd_pwd.equalsIgnoreCase(confirmpassword)) {
            item.setCode(-104);
            item.setDesc("两次密码不一致");
        }
        if (!StringUtils.isNotNull(principalmobile)) {
            item.setCode(-105);
            item.setDesc("联系人电话不能为空");
        }
        if (!StringUtils.isNotNull(mobilecode)) {
            item.setCode(-105);
            item.setDesc("短信验证码不能为空");
        }
        // 验证短信验证码
        String smss = RedisUserInfo.Get("I" + principalmobile);
        if (!mobilecode.equals(smss)) {
            item.setCode(-106);
            item.setDesc("短信验证码错误");
        }
      /*  if (!StringUtils.isNotNull(principalemail)) {
            item.setCode(-107);
            item.setDesc("请填写联系人邮箱");
        }*/

        if (!StringUtils.isNotNull(shopname)) {
            item.setCode(-108);
            item.setDesc("请填写店铺名称");
        }
        if (StringUtils.isBlanck(supporttel)) {
            item.setCode(-109);
            item.setDesc("请填写店铺客服电话");
        }
        if (!StringUtils.isNotNull(thirdclass)) {
            item.setCode(-110);
            item.setDesc("请填写经营范围");
        }

        if (!StringUtils.isNotNull(principalname)) {
            item.setCode(-111);
            item.setDesc("请填写联系人姓名");
        }
        if (!StringUtils.isNotNull(companyname)) {
            item.setCode(-112);
            item.setDesc("请填写公司名");
        }

     /*   if (!StringUtils.isNotNull(license)) {
            item.setCode(-113);
            item.setDesc("请上传营业执照");
        }
        if (!StringUtils.isNotNull(organization)) {
            item.setCode(-114);
            item.setDesc("请上传组织机构代码扫描文件");
        }
        if (!StringUtils.isNotNull(tax)) {
            item.setCode(-115);
            item.setDesc("请上传税务登记证");
        }
        if (!StringUtils.isNotNull(certificate)) {
            item.setCode(-116);
            item.setDesc("请上传公司资质");
        }
        if (!StringUtils.isNotNull(bank)) {
            item.setCode(-117);
            item.setDesc("请上传开户银行许可证");
        }*/

        if (!StringUtils.isNotNull(companyprovince)) {
            item.setCode(-118);
            item.setDesc("请选择省");
        }
        if (!StringUtils.isNotNull(companycity)) {
            item.setCode(-119);
            item.setDesc("请选择市");
        }
        if (!StringUtils.isNotNull(companyarea)) {
            item.setCode(-120);
            item.setDesc("请选择区");
        }
        if (!StringUtils.isNotNull(companyadress)) {
            item.setCode(-121);
            item.setDesc("请填写公司详细地址");
        }
        /*if (!StringUtils.isNotNull(companyadress)) {
            item.setCode(-122);
            item.setDesc("请填写公司邮编");
        }*/

        /* if (!StringUtils.isNotNull(companytel)) {
             item.setCode(-121);
             item.setDesc("请填写公司电话");
         }*/
        /*if (!StringUtils.isNotNull(companytype)) {
            item.setCode(-122);
            item.setDesc("请选择公司类型");
        }*/

       /* if (!StringUtils.isNotNull(companysales)) {
            item.setCode(-123);
            item.setDesc("请填写公司年销售额");
        }*/

        if (!StringUtils.isNotNull(imgverification)) {
            item.setCode(-124);
            item.setDesc("请填写验证码");
        }
        if (StringUtils.isBlanck(imageurl)) {
            item.setCode(-125);
            item.setDesc("请选择店铺头像");
        }
        if (StringUtils.isBlanck(description)) {
            item.setCode(-126);
            item.setDesc("请完善店铺简介");
        }
//        if (StringUtils.isBlanck(latitude) || StringUtils.isBlanck(longitude)) {
//            item.setCode(-127);
//            item.setDesc("请选择经纬度");
//        }String jcbg, String scxkz, String spy
        if (StringUtilsEX.toArray(jcbg).length<3) {
        	 item.setCode(-127);
             item.setDesc("请上传三张近期产品检测报告");
		}
        if (StringUtilsEX.toArray(scxkz).length<1) {
			item.setCode(-128);
			item.setDesc("请上传生产许可证或流通许可证");
		}
        if (StringUtilsEX.IsNullOrWhiteSpace(spy)) {
			item.setCode(-129);
			item.setDesc("请上传三品一标证书");
		}
        if (StringUtilsEX.IsNullOrWhiteSpace(settlementType)||StringUtilsEX.ToIntNull(settlementType)==-1) {
        	item.setCode(-130);
			item.setDesc("请选择结算类型");
		}
    }

    private List<String> subs(String s, List<String> list) {

        if (StringUtils.isNotNull(s) && s.indexOf("|") >= 0) {
            int index = s.indexOf("|");
            System.out.println(s.substring(0, index));
            list.add(s.substring(0, index));
            subs(s.substring(index + 1), list);
        } else if (StringUtils.isNotNull(s)) {
            list.add(s);
            System.out.println(s);
        }
        return list;
    }

    /**
     * 根据父ID查询子分类信息
     * 
     * @param fatherid
     * @return
     */
    @RequestMapping("/user/getClassByFatherID")
    public @ResponseBody ReusltItem getClassByFatherID(String fatherid) {
        ReusltItem item = new ReusltItem();
        List<CategoryDTO> list = new ArrayList<CategoryDTO>();
        try {
            if (StringUtilsEX.ToInt(fatherid) < 0) {
                item.setCode(-101);
                item.setDesc("参数错误，fatherid：" + fatherid);
                return item;
            }
            list = categoryService.getByFatherID(Integer.parseInt(fatherid));
            item.setDesc("查询子分类信息成功");
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("根据父ID查询子分类信息异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
					"根据父ID查询子分类信息异常:{0}", e,
					"/seller/user/getClassByFatherID");
        }

        return item;
    }

    /**
     * 查询经营范围
     * 
     * @return
     */
    @RequestMapping("/user/qeuryBusinessScop")
    public @ResponseBody ReusltItem qeuryBusinessScop() {
        ReusltItem item = new ReusltItem();
        try {
            Configdictionary configdictionary = configSetService
                .showConfigSetByType(ConfigSetTypeEnum.经营范围.getValue());
            item.setCode(0);
            item.setData(configdictionary.getValue());
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询经营范围异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
					"查询经营范围异常:{0}", e,
					"/seller/user/qeuryBusinessScop");
        }
        return item;
    }

    /**
     * 发送短信验证码
     * 
     * @param phone
     * @param type
     * @return
     */
    @RequestMapping(value = "/user/send", produces = "text/html;charset=UTF-8")
    public @ResponseBody String send(String phone, Integer type, String imgcode) {
        BaseResult item = new BaseResult();
        if (StringUtilsEX.IsNullOrWhiteSpace(phone)) {
            item.setCode(-101);
            item.setDesc("电话号码(ph)不能为空！");
            return item.toJson();
        }
        try {
        	String vCode = RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
            if (!vCode.equalsIgnoreCase(imgcode)) {
                item.setCode(-102);
                item.setDesc("图片验证码错误！");
                return item.toJson();
            }
            String val = ProductNumUtil.getRandNum();
            String message="【中绿生活】您好,您的本次验证码为"+val;
            SmsUtil.sendMessage(phone, message);
            RedisUserInfo.Set("I" + phone, val, ConfigUtil.get_instances().getSmsCodeTmeOut());
            item.setData(val);
            //保存到数据库
            Smsrecord smsrecord = new Smsrecord();
            smsrecord.setPhone(phone);
            smsrecord.setContent(val);
            smsrecord.setCreateDate(new Date());
            smsrecord.setType(type);
            smsService.addSms(smsrecord);
            item.setCode(0);
            item.setData(smsrecord);
            item.setDesc("获取验证码成功");
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("发送短信验证码异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SendMessage,
					"发送短信验证码异常:{0}", e,
					"/seller/user/send");
        }

        return item.toJson();
    }

    /**
     * 验证验证码
     * 
     * @param imgverification
     * @param request
     * @return
     */
    @RequestMapping("/user/checkVerification")
    public @ResponseBody ReusltItem checkVerification(String imgverification) {
        ReusltItem item = new ReusltItem();
        //验证 页面验证码
        String vCode = RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
        if (vCode.equalsIgnoreCase(imgverification)) {
            item.setCode(0);
            item.setDesc("验证成功");
        } else {
            item.setCode(-200);
            item.setDesc("验证码错误");
        }
        return item;
    }

    /**
     * 验证账号是否存在
     * 
     * @param userkey
     * @return
     */
    @RequestMapping("/user/checkuserrepeat")
    public @ResponseBody ReusltItem checkuserrepeat(String userkey, String usertype) {
        ReusltItem item = new ReusltItem();
        try {
            List<Accounts> list = accountsService.selectByName(userkey,
                StringUtilsEX.ToInt(usertype));
            if (list != null && list.size() == 0) {
                item.setCode(0);
            } else {
                item.setCode(-200);
            }
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("验证账号是否存在异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
					"验证账号是否存在异常:{0}", e,
					"/seller/user/checkuserrepeat");
        }
        return item;
    }

    /**
     * 校验手机号是否存在
     * 
     * @param userkey
     * @param usertype
     * @return
     */
    @RequestMapping("/user/checkMobileRepeat")
    public @ResponseBody ReusltItem checkMobileRepeat(String userkey, String usertype) {
        ReusltItem item = new ReusltItem();
        try {
            List<Accounts> list = accountsService.selectByMobile(userkey,
                StringUtilsEX.ToInt(usertype));
            if (list != null && list.size() == 0) {
                item.setCode(0);
            } else {
                item.setCode(-200);
            }
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("校验手机号是否存在异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
					"校验手机号是否存在异常:{0}", e,
					"/seller/user/checkMobileRepeat");
        }
        return item;
    }

    /**
     * 查询地区
     * 
     * @param type
     * @param code
     * @return
     */
    @RequestMapping("/user/queryRegion")
    public @ResponseBody ReusltItem queryRegion(String type, String code) {
        ReusltItem item = new ReusltItem();
        try {
            switch (StringUtilsEX.ToInt(type)) {
                case 0:
                    //查询 省
                    List<Province> list = provinceServcice.selectAll();
                    item.setCode(0);
                    item.setData(list);
                    break;

                case 1:
                    // 查询市
                    List<City> cities = cityServcie.selectByProvinceCode(code);
                    item.setCode(0);
                    item.setData(cities);
                    break;
                case 2:
                    //查询区
                    List<Area> areas = areaService.selectByCityCode(code);
                    item.setCode(0);
                    item.setData(areas);
                    break;
            }
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询地区异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
					"查询地区异常:{0}", e,
					"/seller/user/queryRegion");
        }
        return item;
    }
    
}

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.io.File;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.spring.jxl.core.ExcleReadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yinlian.Enums.AdvertImgTypeEnum;
import com.yinlian.Enums.ClassStatusEnum;
import com.yinlian.Enums.ClassTypeEnum;
import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.ShopHistoryStatus;
import com.yinlian.Enums.ShopSettlementTypeEnum;
import com.yinlian.Enums.ShopStatusEnum;
import com.yinlian.Enums.ShopViolationTypeEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.VillationDTo;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Circle;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Role;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.ShopClass;
import com.yinlian.wssc.web.po.Shopcategory;
import com.yinlian.wssc.web.po.Shopormemberstatushistory;
import com.yinlian.wssc.web.po.Shopviolation;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.giftcard;
import com.yinlian.wssc.web.po.giftcardExample;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.ApplyshopService;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.CircleService;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.RoleService;
import com.yinlian.wssc.web.service.ShopAuthenticationService;
import com.yinlian.wssc.web.service.ShopClassService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.ShopcategoryService;
import com.yinlian.wssc.web.service.ShopstatushistoryService;
import com.yinlian.wssc.web.service.ShopviolationService;
import com.yinlian.wssc.web.service.SmsService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.service.giftcardService;
import com.yinlian.wssc.web.util.BasicConvert;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.CriteriaApplyShop;
import com.yinlian.wssc.web.util.CriteriaCommodity;
import com.yinlian.wssc.web.util.CriteriaGroupBuy;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.CriteriaShopCategory;
import com.yinlian.wssc.web.util.CriteriaVoilationShop;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.MapDistance;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 店铺的控制层
 * 
 * @author Administrator
 * @version $Id: ShopController.java, v 0.1 2016年3月9日 下午8:25:55 Administrator
 *          Exp $
 */
@RestController
@RequestMapping("/platform/shop")
public class ShopController {

	@Autowired
	private UserService userService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ShopAuthenticationService shopAuthenticationService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private ConfigSetService configSetService;
	@Autowired
	private AccountsService accountsService;
	@Autowired
	private AdvertImgService advertImgService;

	@Autowired
	private ShopviolationService shopviolationService;
	@Autowired
	private ShopstatushistoryService shopstatushistoryService;
	@Autowired
	private ShopClassService shopClassService;
	@Autowired
	private UsercapitalService usercapitalService;
	@Autowired
	private ShopcategoryService shopcategoryService;
	@Autowired
	private CircleService circleService;
	@Autowired
	private ApplyshopService applyshopService;
	@Autowired
	private giftcardService cardservice;

	SessionUser user = null;
	@Autowired
	private OperaterecordsService operaterecordsService;

	@RequestMapping("/queryShopListCriteria")
	public ReusltItem queryShopListCriteria() {
		ReusltItem item = new ReusltItem();

		return item;
	}

	/**
	 * 根据用户名模糊查询
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/queryUserByLikeName")
	public ReusltItem queryUserByLikeName(String name, Integer usertype) {
		ReusltItem item = new ReusltItem();
		try {
			List<Users> list = userService.selectByLikeName(name, usertype);
			item.setCode(0);
			item.setData(list);
			item.setDesc("获取成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("用户名模糊查询信息异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "用户名模糊查询信息异常! 异常信息:{0}", e,
					"/platform/shop/queryUserByLikeName");
		}
		return item;
	}

	/**
	 * 根据店铺的名字模糊查询
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/queryShopByLikeName")
	public ReusltItem queryShopByLikeName(String name) {
		ReusltItem item = new ReusltItem();
		try {
			List<Shop> list = shopService.seletcByLikeName(name);
			item.setCode(0);
			item.setData(list);
			item.setDesc("获取成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("店铺的名字模糊查询信息异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "店铺的名字模糊查询信息异常! 异常信息:{0}", e, "shop/queryShopByLikeName");
		}
		return item;
	}

	/**
	 * 查询店铺的信息
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	@RequestMapping("/queryByCriteria")
	public ReusltItem queryByCriteria(String userid, String name, String shopnum, String shoptype, String status,
			@RequestParam("page") String pc, @RequestParam("size") String ps) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(pc) < 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex：" + pc);
				return item;
			}
			if (StringUtilsEX.ToInt(ps) < 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex：" + ps);
				return item;
			}
			CriteriaShop criteria = new CriteriaShop();
			criteria.setName(name);
			criteria.setShopnum(shopnum);
			criteria.setStatus(StringUtilsEX.ToInt(status));
			Integer _userid = StringUtilsEX.ToInt(userid);
			if (_userid > 0) {
				criteria.setUserid(_userid);
			}
			if (StringUtilsEX.ToInt(shoptype) >= 0) {
				criteria.setShoptype(StringUtilsEX.ToInt(shoptype));
			}
			PageBean pageBean = shopService.selectShopPage(criteria, StringUtilsEX.ToInt(pc), StringUtilsEX.ToInt(ps));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询店铺的信息异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询店铺的信息异常! 异常信息:{0}", e, "shop/queryByCriteria");
		}

		return item;
	}

	/**
	 * 添加店铺
	 * 
	 * @param loginname
	 * @param passwrd_pwd
	 * @param confirmpassword
	 * @param principalmobile
	 * @param imgverification
	 * @param mobilecode
	 * @param principalemail
	 * @param shopname
	 * @param principalname
	 * @param companytel
	 * @param companyname
	 * @param license
	 * @param organization
	 * @param tax
	 * @param certificate
	 * @param bank
	 * @param companyprovince
	 * @param companycity
	 * @param companyarea
	 * @param companyfox
	 * @param companyadress
	 * @param postcode
	 * @param margin
	 * @param companytype
	 * @param companyweb
	 * @param thirdclass
	 * @param thirdfullpath
	 * @param companysales
	 * @param longitude
	 * @param latitude
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public ReusltItem addShop(String loginname, String passwrd_pwd, String confirmpassword, String principalmobile,
			String pay_password_pwd, String pay_password_pwdagian, String imgverification, String mobilecode,
			String principalemail, String shopname, String principalname, String companytel, String companyname,
			String license, String organization, String tax, String certificate, String bank, String companyprovince,
			String companycity, String companyarea, String companyfox, String companyadress, String postcode,
			String companytype, String companyweb, String thirdclass, String thirdfullpath, String companysales,
			String longitude, String latitude, String imageurl, String description, String supporttel,
			String provincename, String cityname, String areaname, String phone, String shopcircleid, String isep,
			String issupport, String isfree, String isjck, String banktype, String lineno, String bankname,
			String bankcardno, String hodername, String classid, String isowned, String status, String accounttype,
			String popularity, String consumption, String settlementType, String discount, String licenseNum,
			String examinereport, String producelicence, String adoctrinecredential, String shoptype,
			String marketingscope, String bewrite, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			// String usertype = UserTypeEnum.Seller.getValue().toString();
			List<Accounts> list = accountsService.selectByName(loginname, UserTypeEnum.Seller.getValue());
			if (list.size() > 0) {
				item.setCode(-301);
				item.setDesc("该用户已存在");
				return item;
			}
			AccountsExample example = new AccountsExample();
			AccountsExample.Criteria criteria = example.createCriteria();
			criteria.andUsertypeEqualTo(UserTypeEnum.Seller.getValue());
			criteria.andPhoneEqualTo(phone);
			criteria.andIsdelEqualTo(false);
			List<Accounts> result = accountsService.queryByMobile(example);
			if (result.size() > 0) {
				item.setCode(-302);
				item.setDesc("该验证手机号已注册");
				return item;
			}
			if (!StringUtils.isNotNull(phone)) {
				item.setCode(-303);
				item.setDesc("验证手机号不能为空");
				return item;
			}
			if (StringUtilsEX.ToInt(shoptype) < 0) {
				item.setCode(-304);
				item.setDesc("店铺类型参数错误");
				return item;
			}
			checkParameters(loginname, principalmobile, principalemail, shopname, principalname, companytel,
					companyname, license, organization, tax, certificate, bank, companyprovince, companycity,
					companyarea, companyfox, companyadress, postcode, companytype, companyweb, thirdclass, companysales,
					imageurl, description, longitude, latitude, supporttel, isowned, examinereport, producelicence,
					adoctrinecredential, item);
			if (item.getCode() < 0) {
				return item;
			}

			Integer actype = StringUtilsEX.ToInt(accounttype);
			ShopClass shopClass = new ShopClass();
			Shop shop = new Shop();
			shop.setShoptype(StringUtilsEX.ToInt(shoptype));
			shop.setDiscount(discount);
			shop.setBankcardno(bankcardno);
			shop.setBankname(bankname);
			shop.setBanktype(banktype);
			shop.setLineno(lineno);
			shop.setHodername(hodername);
			shop.setSettlementtype(StringUtilsEX.ToInt(settlementType));
			if (!"-1".equals(accounttype)) {
				shop.setAccounttype(actype);
			}
			shop.setIsep(StringUtilsEX.ToBoolean(isep));
			shop.setIsjck(false);
			shop.setIssupport(StringUtilsEX.ToBoolean(issupport));
			shop.setIsfree(StringUtilsEX.ToBoolean(isfree));
			shop.setClassid(StringUtilsEX.ToIntNull(classid));
			shop.setCircleid(StringUtilsEX.ToInt(shopcircleid));
			// shopClass.setCreatetime(new Date());
			shop.setSupporttel(supporttel);
			shop.setName(shopname);
			shop.setMargin(StringUtilsEX.ToFloat("0.0"));
			shop.setImgurl(imageurl);
			shop.setDescription(description);
			shop.setAccounttype(StringUtilsEX.ToInt(accounttype));
			shop.setStatus(ShopStatusEnum.Open.getValue());
			shop.setIsowned(StringUtilsEX.ToBoolean(isowned));
			Integer popular = StringUtilsEX.ToInt(popularity);
			if (popular <= 0) {
				popular = 0;
			}
			shop.setPopularity(popular);
			float consum = StringUtilsEX.ToFloat(consumption);
			if (consum <= 0) {
				consum = 0;
			}
			shop.setConsumption(consum);
			// if (shop.getIsowned()) {
			// shop.setStatus(ShopStatusEnum.Open.getValue());
			// }
			shop.setCreattime(new Date());
			shop.setIsdel(false);
			shop.setIsflagship(false);
			if (provincename.indexOf("请选择") != -1) {
				provincename = "";
			}
			if (cityname.indexOf("请选择") != -1) {
				cityname = "";
			}
			if (areaname.indexOf("请选择") != -1) {
				areaname = "";
			}
			shop.setShopaddress(provincename + cityname + areaname + companyadress);

			ShopAuthentication authentication = new ShopAuthentication();
			// 数据库中不为空的
			authentication.setMarketingscope(marketingscope);
			authentication.setPrincipalmobile(phone);
			authentication.setPrincipalemail(principalemail);
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
			authentication.setCompanysales(BasicConvert.string2BigDecimal(companysales)); // 公司年销售额
			authentication.setCompanyweb(companyweb);
			authentication.setPrincipalemail(principalemail);
			authentication.setPrincipalname(principalname);
			authentication.setPrincipalmobile(principalmobile);
			authentication.setCompanyemail(principalemail);
			authentication.setLicenseNum(licenseNum);
			authentication.setExaminereport(examinereport);
			authentication.setProducelicence(producelicence);
			authentication.setAdoctrinecredential(adoctrinecredential);
			authentication.setBewrite(bewrite);
			int code = 0;
			String pwd = "888888";
			if (shop.getIsowned()) {
				Accounts accounts = accountsService.getSuperadmin();
				Users users = userService.queryById(accounts.getUserid());
				users.setPassword(pwd);
				users.setEmail(principalemail);
				users.setMobile(principalmobile);
				users.setUsername(loginname);
				users.setPaypass(pwd);

				accounts.setPhone(phone);
				accounts.setLoginname(loginname);
				accounts.setPassword(pwd);
				code = shopService.insertOwned(shop, authentication, accounts, users);
			} else {
				Users users = new Users();
				Accounts accounts = new Accounts();
				users.setPassword(pwd);
				users.setEmail(principalemail);
				users.setMobile(principalmobile);
				users.setUsername(loginname);
				users.setPaypass(pwd);

				accounts.setPhone(phone);
				accounts.setLoginname(loginname);
				accounts.setPassword(pwd);
				code = shopService.insertShop(shop, shopClass, users, authentication, accounts);
			}
			// int code = shopService.insertShop(shop, shopClass, users,
			// authentication, accounts, classidList,
			// fullpathList);
			if (code > 0) {
				item.setCode(0);
				item.setDesc("新增成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"spgl_dpgl_add.jsp", "/platform/shop/add", "添加店铺");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加店铺操作记录出错! 异常信息:", e, "/platform/shop/add");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("新增失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(MessageFormat.format("添加店铺的信息异常：{0}", e.getMessage()));
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "添加店铺的信息异常! 异常信息:{0}", e, "shop/add");
		}
		return item;
	}

	/**
	 * 校验参数
	 * 
	 * @param loginname
	 * @param passwrd_pwd
	 * @param confirmpassword
	 * @param principalmobile
	 * @param imgverification
	 * @param mobilecode
	 * @param principalemail
	 * @param shopname
	 * @param item
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
	 * @param latitude
	 * @param longitude
	 * @param adoctrinecredential
	 * @param producelicence
	 * @param examinereport
	 */
	private void checkParameters(String loginname, String principalmobile, String principalemail, String shopname,
			String principalname, String companytel, String companyname, String license, String organization,
			String tax, String certificate, String bank, String companyprovince, String companycity, String companyarea,
			String companyfox, String companyadress, String postcode, String companytype, String companyweb,
			String thirdclass, String companysales, String imageurl, String description, String longitude,
			String latitude, String supporttel, String isowned, String examinereport, String producelicence,
			String adoctrinecredential, ReusltItem item) {
		if (!StringUtils.isNotNull(loginname)) {
			item.setCode(-101);
			item.setDesc("用户名不能为空");
		}
		if (!StringUtilsEX.checkUserName(loginname)) {
			item.setCode(-105);
			item.setDesc("用户名不正确，请输入3~16个字符，26个大小写字、数字或“_-”（不能以数字开头）");
			return;
		}

		if (!StringUtils.isNotNull(principalmobile)) {
			item.setCode(-105);
			item.setDesc("联系人电话不能为空");
		}

		/*
		 * if (!StringUtils.isNotNull(principalemail)) { item.setCode(-107);
		 * item.setDesc("请填写联系人邮箱"); }
		 */

		if (!StringUtils.isNotNull(shopname)) {
			item.setCode(-108);
			item.setDesc("请填写店铺名称");
		}

		// if (!StringUtilsEX.ToBoolean(isowned)) {
		// if (!StringUtils.isNotNull(thirdclass)) {
		// item.setCode(-110);
		// item.setDesc("请填写经营范围");
		// }
		// }

		if (!StringUtils.isNotNull(principalname)) {
			item.setCode(-111);
			item.setDesc("请填写联系人姓名");
		}
		/*
		 * if (!StringUtils.isNotNull(companyname)) { item.setCode(-112);
		 * item.setDesc("请填写公司名"); }
		 */

		/*
		 * if (!StringUtils.isNotNull(license)) { item.setCode(-113);
		 * item.setDesc("请填写营业执照号码"); } if
		 * (!StringUtils.isNotNull(organization)) { item.setCode(-114);
		 * item.setDesc("请上传组织机构代码扫描文件"); } if (!StringUtils.isNotNull(tax)) {
		 * item.setCode(-115); item.setDesc("请上传税务登记证"); } if
		 * (!StringUtils.isNotNull(certificate)) { item.setCode(-116);
		 * item.setDesc("请上传公司资质"); } if (!StringUtils.isNotNull(bank)) {
		 * item.setCode(-117); item.setDesc("请上传开户银行许可证"); }
		 */

		if (!StringUtils.isNotNull(companyprovince) || "-1".equals(companyprovince)) {
			item.setCode(-118);
			item.setDesc("请选择省");
		}
		if (!StringUtils.isNotNull(companycity) || "-1".equals(companycity)) {
			item.setCode(-119);
			item.setDesc("请选择市");
		}
		if (!StringUtils.isNotNull(companyarea) || "-1".equals(companyarea)) {
			item.setCode(-120);
			item.setDesc("请选择区");
		}
		if (!StringUtils.isNotNull(companyadress)) {
			item.setCode(-121);
			item.setDesc("请填写公司详细地址");
		}
		/*
		 * if (!StringUtils.isNotNull(postcode)) { item.setCode(-122);
		 * item.setDesc("请填写公司邮编"); }
		 * 
		 * if (!StringUtils.isNotNull(companytel)) { item.setCode(-121);
		 * item.setDesc("请填写公司电话"); }
		 */
		/*
		 * if (!StringUtils.isNotNull(companytype)) { item.setCode(-122);
		 * item.setDesc("请选择公司类型"); }
		 * 
		 * if (!StringUtils.isNotNull(companysales)) { item.setCode(-123);
		 * item.setDesc("请填写公司年销售额"); }
		 */
		if (StringUtils.isBlanck(imageurl)) {
			item.setCode(-124);
			item.setDesc("请选择店铺头像");
		}

		if (StringUtils.isBlanck(description)) {
			item.setCode(-125);
			item.setDesc("请完善店铺简介信息");
		}
		// if (StringUtils.isBlanck(latitude) ||
		// StringUtils.isBlanck(longitude)) {
		// item.setCode(-126);
		// item.setDesc("请选择经纬度");
		// }
		// if (StringUtils.isBlanck(supporttel)) {
		// item.setCode(-127);
		// item.setDesc("请填写客服电话");
		// }
	}

	/**
	 * 更新
	 * 
	 * @param users
	 *            用户基本信息表
	 * @param resultUser
	 * @param shop
	 *            店铺表
	 * @param resultShop
	 * @param authentication
	 *            店铺的信息表
	 * @param resultAuth
	 * @param accounts
	 *            用户的总表
	 * @param result
	 * @param thirdclass
	 *            第三级分类的id 连接字符串 eg: "3|4|5|"
	 * @param thirdfullpath
	 *            总的全路径的字符串 eg: "1,2,3|4,5,6|7,8,9|"
	 * @param accountsid
	 *            用户表的id
	 * @param shopAuthenticationid
	 *            店铺的信息表的id
	 * @return
	 */
	@RequestMapping("/update")
	public ReusltItem update(String loginname, String principalmobile, String imgverification, String mobilecode,
			String principalemail, String shopname, String principalname, String companytel, String companyname,
			String license, String organization, String tax, String certificate, String bank, String companyprovince,
			String companycity, String companyarea, String companyfox, String companyadress, String postcode,
			String companytype, String companyweb, String thirdclass, String thirdfullpath, String companysales,
			String longitude, String latitude, String accountsid, String shopAuthenticationid, String shopid,
			String userid, String imageurl, String supporttel, String description, String provincename, String cityname,
			String areaname, String phone, String shopcircleid, String isep, String issupport, String isfree,
			String isjck, String banktype, String lineno, String bankname, String bankcardno, String hodername,
			String classid, String discount, String settlementType,String popularity,
			String consumption, String examinereport, String producelicence, String adoctrinecredential,
			String shoptype, String marketingscope, String licenseNum, String bewrite) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(shopid) <= 0) {
				item.setCode(-101);
				item.setDesc("店铺ID参数错误");
				return item;
			}
			if (!StringUtils.isNotNull(phone)) {
				item.setCode(-303);
				item.setDesc("验证手机号不能为空");
				return item;
			}

			Shop shop = shopService.queryById(StringUtilsEX.ToInt(shopid));
			// if(shop.getIsowned()==false){
			// if (!StringUtils.isDigit(popularity)) {
			// item.setCode(-307);
			// item.setDesc("人气请输入整数类型的数字");
			// return item;
			// }
			// }
			checkParameters(loginname, principalmobile, principalemail, shopname, principalname, companytel,
					companyname, license, organization, tax, certificate, bank, companyprovince, companycity,
					companyarea, companyfox, companyadress, postcode, companytype, companyweb, thirdclass, companysales,
					imageurl, description, longitude, latitude, supporttel, shop.getIsowned().toString(), examinereport,
					producelicence, adoctrinecredential, item);
			if (item.getCode() < 0) {
				return item;
			}
			shop.setBankcardno(bankcardno);
			shop.setBankname(bankname);
			shop.setBanktype(banktype);
			shop.setLineno(lineno);
			shop.setHodername(hodername);
			shop.setCircleid(StringUtilsEX.ToInt(shopcircleid));
			shop.setDiscount(discount);
			shop.setIsep(StringUtilsEX.ToBoolean(isep));
			shop.setName(shopname);
			/**
			 * 结算类型为空设置默认为日结算
			 */
			if (settlementType == null) {
				shop.setSettlementtype(ShopSettlementTypeEnum.日.getValue());
			} else {
				shop.setSettlementtype(StringUtilsEX.ToInt(settlementType));
			}
			shop.setIsjck(false);
			shop.setIssupport(StringUtilsEX.ToBoolean(issupport));
			shop.setIsfree(StringUtilsEX.ToBoolean(isfree));
			shop.setClassid(StringUtilsEX.ToIntNull(classid));
			shop.setId(StringUtilsEX.ToInt(shopid));
			shop.setUserid(StringUtilsEX.ToInt(userid));
			shop.setImgurl(imageurl);
			shop.setDescription(description);
			shop.setSupporttel(supporttel);
			shop.setShoptype(StringUtilsEX.ToInt(shoptype));
			Integer popular = StringUtilsEX.ToInt(popularity);
			if (popular <= 0) {
				popular = 0;
			}
			shop.setPopularity(popular);
			float consum = StringUtilsEX.ToFloat(consumption);
			if (consum <= 0) {
				consum = 0;
			}
			shop.setConsumption(consum);
			if (provincename.indexOf("请选择") != -1) {
				provincename = "";
			}
			if (cityname.indexOf("请选择") != -1) {
				cityname = "";
			}
			if (areaname.indexOf("请选择") != -1) {
				areaname = "";
			}
			shop.setShopaddress(provincename + cityname + areaname + companyadress);
			
			Users users = new Users();
			Accounts accounts = new Accounts();
			users.setEmail(principalemail);
			users.setMobile(phone);
			users.setUsername(loginname);
			users.setId(StringUtilsEX.ToInt(userid));
			users.setRealname(principalname);
			accounts.setPhone(phone);
			accounts.setLoginname(loginname);
			accounts.setId(StringUtilsEX.ToInt(accountsid));
			// String origloginname =
			// accountsService.selectByID(StringUtilsEX.ToInt(accountsid)).getLoginname();
			ShopAuthentication authentication = new ShopAuthentication();
			authentication.setBewrite(bewrite);
			authentication.setMarketingscope(marketingscope);
			authentication.setPrincipalmobile(principalmobile);
			authentication.setPrincipalemail(principalemail);
			// 数据库中不为空的
			authentication.setCompanycontactname(loginname);
			authentication.setPrincipalname(principalname);
			authentication.setIseshop(0);

			authentication.setLatitude(latitude);
			authentication.setLongitude(longitude);

			authentication.setBank(bank);
			authentication.setCertificate(certificate);
			authentication.setLicense(license);
			authentication.setOrganization(organization);
			authentication.setTax(tax);

			authentication.setCompanyadress(companyadress);
			authentication.setCompanycity(companycity);
			authentication.setCompanyfox(companyfox);
			authentication.setCompanyarea(companyarea);
			authentication.setCompanyprovince(companyprovince);
			authentication.setCompanytel(companytel);
			authentication.setPostcode(postcode);
			authentication.setCompanytype(StringUtilsEX.ToInt(companytype));
			authentication.setCompanysales(BasicConvert.string2BigDecimal(companysales)); // 公司年销售额
			authentication.setCompanyweb(companyweb);
			authentication.setId(StringUtilsEX.ToInt(shopAuthenticationid));
			authentication.setLicenseNum(licenseNum);
			// ,String examinereport,String producelicence,String
			// adoctrinecredential
			authentication.setExaminereport(examinereport);
			authentication.setProducelicence(producelicence);
			authentication.setAdoctrinecredential(adoctrinecredential);
			int code = 0;
			if (shop.getIsowned()) {
				code = shopService.updateOwned(shop, authentication, accounts, users);
			} else {
				code = shopService.update(users, shop, authentication, accounts);
			}
			// int code = shopService.update(users, shop, authentication,
			// accounts, classidList, fullpathList);
			if (code > 0) {
				item.setCode(0);
				item.setDesc("修改成功");

				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"spgl_dpgl_add.jsp", "/platform/shop/update", "更新店铺");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "更新店铺操作记录出错! 异常信息:", e, "/platform/shop/update");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("修改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(MessageFormat.format("更新店铺的信息异常：{0}", e.getMessage()));
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "更新店铺的信息异常! 异常信息:{0}", e, "shop/update");
		}
		return item;
	}

	/**
	 * 获取经营范围
	 * 
	 * @return
	 */
	@RequestMapping("/queryBusinessScope")
	public ReusltItem queryBusinessScope() {
		ReusltItem item = new ReusltItem();
		try {
			List<CategoryDTO> list = categoryService.selectAll(0);
			item.setCode(0);
			item.setData(list);
			item.setDesc("获取列表");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("经营范围信息异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "经营范围信息异常! 异常信息:{0}", e, "shop/queryBusinessScope");
		}
		return item;
	}

	/**
	 * 上传图片
	 * 
	 * @param pic
	 * @return
	 */
	@RequestMapping("/uploadfile")
	public String uploadfile(MultipartFile pic) {
		ReusltItem item = new ReusltItem();
		String imgUrl = "";
		String image_server = "";
		try {
			if (pic != null) {
				Properties properties = new Properties();
				InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
				properties.load(inStream);
				String path = properties.getProperty("image_url");
				image_server = properties.getProperty("image_src");
				inStream.close();
				String originalname = pic.getOriginalFilename();
				String newname = UUID.randomUUID().toString() + originalname.substring(originalname.lastIndexOf("."));
				File file = new File(path + originalname);//
				if (!file.exists()) {
					file.mkdirs();
				}
				pic.transferTo(file);
				imgUrl = "/images/" + originalname;// 保存新的 图片名称
			}

			item.setCode(0);
			item.setData(imgUrl);
			item.setDesc("文件上传成功");

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("上传本地图片出现异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "上传本地图片出现的异常信息:", e, "shop/uploadfile");
		}
		return item.toJson();
	}

	/**
	 * 修改密码
	 * 
	 * @param shopid
	 * @param newpwd
	 * @return
	 */
	@RequestMapping("/updatePwd")
	public ReusltItem updatePwd(String shopid, String newpwd, String repwd) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("店铺的id不能为空,id" + shopid);
				return item;
			}
			if (!StringUtils.isNotNull(newpwd)) {
				item.setCode(-102);
				item.setDesc("新密码不能为空");
				return item;
			}
			int result = shopService.updatePwd(StringUtilsEX.ToInt(shopid), newpwd, repwd);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改密码成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"spgl_dpgl_pwd_update.jsp", "/platform/shop/updatePwd", "修改密码");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改密码操作记录出错! 异常信息:", e, "/platform/shop/updatePwd");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("修改密码失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改密码异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "修改密码异常! 异常信息:{0}", e, "shop/updatePwd");
		}

		return item;
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
	 * 修改参数
	 * 
	 * @param shopid
	 * @param maxEmployee
	 * @param maxRole
	 * @param margin
	 * @return
	 */
	@RequestMapping("/updateParam")
	public ReusltItem updateParam(String shopid, String maxEmployee, String maxRole, String margin) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("店铺的id不能为空,id" + shopid);
			}
			if (StringUtilsEX.ToInt(maxEmployee) < 0) {
				item.setCode(-102);
				item.setDesc("店铺的最大员工数不能为空,id" + maxEmployee);
			}
			if (StringUtilsEX.ToInt(maxRole) < 0) {
				item.setCode(-103);
				item.setDesc("店铺的最大角色数不能为空,id" + maxRole);
			}
			if (StringUtilsEX.ToInt(margin) < 0) {
				item.setCode(-104);
				item.setDesc("店铺的保证金不能为空,id" + margin);
			}

			int result = shopService.updateParam(StringUtilsEX.ToInt(shopid), StringUtilsEX.ToInt(maxEmployee),
					StringUtilsEX.ToInt(maxRole), BasicConvert.toFloat(margin));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改参数成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_setting.jsp", "/platform/shop/updateParam", "修改参数");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改参数操作记录出错! 异常信息:", e,
									"/platform/shop/updateParam");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("修改参数失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改店铺参数异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "修改店铺参数异常! 异常信息:{0}", e, "shop/updateParam");
		}
		return item;
	}

	/**
	 * 店铺充值
	 * 
	 * @param shopid
	 * @param money
	 * @param type
	 * @return
	 */
	@RequestMapping("/recharge")
	public ReusltItem recharge(String shopid, String money, String type) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("店铺的id不能为空,id" + shopid);
			}
			if (StringUtilsEX.ToInt(money) < 0) {
				item.setCode(-102);
				item.setDesc("充值金额不能为空,id" + money);
			}
			if (StringUtilsEX.ToInt(type) < 0) {
				item.setCode(-103);
				item.setDesc("充值类型不能为空,id" + type);
			}

			int result = shopService.recharge(StringUtilsEX.ToInt(shopid), BasicConvert.toFloat(money),
					StringUtilsEX.ToInt(type));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("充值成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_recharge.jsp", "/platform/shop/recharge", "店铺充值");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "店铺充值操作记录出错! 异常信息:", e, "/platform/shop/recharge");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("充值失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("店铺充值异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "店铺充值异常! 异常信息:{0}", e, "shop/recharge");
		}
		return item;
	}

	/**
	 * 删除审核
	 * 
	 * @param shopid
	 * @param status
	 * @return
	 */
	@RequestMapping("/deleteCheck")
	public ReusltItem deleteCheck(String shopid, String status) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("店铺的id不能为空,id" + shopid);
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-102);
				item.setDesc("店铺的状态不能为空,id" + status);
			}

			int result = shopService.deleteCheck(StringUtilsEX.ToInt(shopid), StringUtilsEX.ToInt(status));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除审核成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_check.jsp", "/platform/shop/deleteCheck", "删除审核");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除审核操作记录出错! 异常信息:", e,
									"/platform/shop/deleteCheck");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("删除审核失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("店铺删除审核异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "店铺删除审核异常! 异常信息:{0}", e, "shop/deleteCheck");
		}
		return item;
	}

	/**
	 * 查询店铺的关联的信息
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	@RequestMapping("/queryAuthenticationByCriteria")
	public ReusltItem queryAuthenticationByCriteria(String shopid, String companyname, String status,
			@RequestParam("page") String pc, @RequestParam("size") String ps) {
		ReusltItem item = new ReusltItem();
		try {

			if (StringUtilsEX.ToInt(pc) < 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex：" + pc);
				return item;
			}
			if (StringUtilsEX.ToInt(ps) < 0) {
				item.setCode(-103);
				item.setDesc("分页参数错误，pageindex：" + ps);
				return item;
			}
			CriteriaShop criteria = new CriteriaShop();
			criteria.setCompanyname(companyname);
			criteria.setStatus(StringUtilsEX.ToInt(status));
			if (StringUtilsEX.ToInt(shopid) > 0) {
				criteria.setShopid(StringUtilsEX.ToInt(shopid));
			}
			PageBean pageBean = shopAuthenticationService.selectAuthenticationPage(criteria, StringUtilsEX.ToInt(pc),
					StringUtilsEX.ToInt(ps));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询店铺的关联的信息异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询店铺的关联的信息异常! 异常信息:{0}", e,
					"shop/queryAuthenticationByCriteria");
		}

		return item;
	}

	/**
	 * 根据shopid查询信息
	 * 
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/queryShopDetail")
	public ReusltItem queryShopDetail(String shopid) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("店铺的id不能为空,id" + shopid);
				return item;
			}
			ShopAuthentication authentication = shopAuthenticationService
					.queryDetailByShopId(StringUtilsEX.ToInt(shopid));
			item.setCode(0);
			item.setData(authentication);
			item.setDesc("查询详细信息成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询店铺的详细信息异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询店铺的详细信息异常! 异常信息:{0}", e, "shop/queryShopDetail");
		}
		return item;
	}

	/**
	 * 审核店铺的状态
	 * 
	 * @param shopid
	 * @param status
	 * @param lastStatus
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public ReusltItem updateStatus(String shopid, String status, String lastStatus) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("店铺的id不能为空,id" + shopid);
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-102);
				item.setDesc("店铺的状态不能为空,status" + status);
				return item;
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			int result = shopService.updateStatus(StringUtilsEX.ToInt(shopid), StringUtilsEX.ToInt(status),
					StringUtilsEX.ToInt(lastStatus), sessionUser.getUserId(), sessionUser.getLoginName());
			if (result > 0) {
				item.setCode(0);
				item.setDesc("保存成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), sessionUser.getUserId(),
									sessionUser.getLoginName(), "shop_check.jsp", "/platform/shop/updateStatus",
									"更新店铺的状态");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "更新店铺的状态操作记录出错! 异常信息:", e,
									"/platform/shop/updateStatus");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("保存失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("更新店铺的状态异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "更新店铺的状态异常! 异常信息:{0}", e, "shop/updateStatus");
		}
		return item;
	}

	/**
	 * 得到违规店铺的 分页数据
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	@RequestMapping("/queryShopViolationByCriteria")
	public ReusltItem queryShopViolationByCriteria(String type, @RequestParam("page") String pc,
			@RequestParam("size") String ps) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(pc) < 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex：" + pc);
				return item;
			}
			if (StringUtilsEX.ToInt(ps) < 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex：" + ps);
				return item;
			}
			CriteriaShop criteria = new CriteriaShop();
			criteria.setType(StringUtilsEX.ToInt(type));
			PageBean pageBean = shopService.selectShopViolationPage(criteria, StringUtilsEX.ToInt(pc),
					StringUtilsEX.ToInt(ps));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询违规店铺异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询违规店铺异常! 异常信息:{0}", e,
					"shop/queryShopViolationByCriteria");
		}
		return item;
	}

	/**
	 * 查询店铺角色
	 * 
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/queryShopRole")
	public ReusltItem queryShopRole(String shopid) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("店铺的id不能为空,id" + shopid);
				return item;
			}
			List<Role> list = roleService.selectShopRole(StringUtilsEX.ToInt(shopid));
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询店铺角色异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询店铺角色异常! 异常信息:{0}", e, "shop/queryShopRole");
		}
		return item;
	}

	/**
	 * 添加直营店铺
	 * 
	 * @param id
	 * @param name
	 * @param shopaddress
	 * @param status
	 * @param companyname
	 * @param supporttel
	 * @param imgurl
	 * @param laststatus
	 * @return
	 */
	@RequestMapping("/saveOwned")
	public ReusltItem saveOwned(String id, String name, String shopaddress, String status, String companyname,
			String supporttel, String imgurl, String laststatus) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			checkParam(name, shopaddress, status, companyname, supporttel, imgurl, item);
			if (item.getCode() < 0) {
				return item;
			}

			if (StringUtils.isNotNull(id)) {
				// 更新
				Shop shop = shopService.queryById(StringUtilsEX.ToInt(id));
				shop.setName(name);
				shop.setCompanyname(companyname);
				shop.setShopaddress(shopaddress);
				shop.setStatus(StringUtilsEX.ToInt(status));
				shop.setSupporttel(supporttel);
				shop.setImgurl(imgurl);
				int result = shopService.updateOwned(shop);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("保存成功");
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
										"shop_owned.jsp", "/platform/shop/saveOwned", "修改直营店铺");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "修改直营店铺操作记录出错! 异常信息:", e,
										"/platform/shop/saveOwned");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("保存失败");
				}
			} else {
				// 添加
				Shop shop = new Shop();
				shop.setName(name);
				shop.setCompanyname(companyname);
				shop.setShopaddress(shopaddress);
				shop.setStatus(StringUtilsEX.ToInt(status));
				shop.setSupporttel(supporttel);
				shop.setImgurl(imgurl);
				int result = shopService.insertOwned(shop);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("保存成功");
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {

						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
										"shop_owned.jsp", "/platform/shop/saveOwned", "添加直营店铺");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "添加直营店铺操作记录出错! 异常信息:", e,
										"/platform/shop/saveOwned");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("保存失败");
				}
			}
		} catch (

		Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加直营店铺异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "添加直营店铺异常! 异常信息:{0}", e, "shop/saveOwned");
		}
		return item;
	}

	/**
	 * 校验参数
	 * 
	 * @param name
	 * @param shopaddress
	 * @param status
	 * @param companyname
	 * @param supporttel
	 * @param imgurl
	 * @param item
	 */
	private void checkParam(String name, String shopaddress, String status, String companyname, String supporttel,
			String imgurl, ReusltItem item) {
		if (!StringUtils.isNotNull(name)) {
			item.setCode(-101);
			item.setDesc("店铺的名字不能为空");
		}
		if (!StringUtils.isNotNull(shopaddress)) {
			item.setCode(-102);
			item.setDesc("店铺的地址不能为空");
		}
		if (!StringUtils.isNotNull(companyname)) {
			item.setCode(-103);
			item.setDesc("店铺的公司名不能为空");
		}
		if (!StringUtils.isNotNull(supporttel)) {
			item.setCode(-104);
			item.setDesc("店铺的客户电话不能为空");
		}

		if (!StringUtils.isNotNull(imgurl)) {
			item.setCode(-105);
			item.setDesc("店铺的图片不能为空");
		}
	}

	/**
	 * 根据店铺的名字模糊查询
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/getShopStartWithName")
	public ReusltItem getShopStartWithName(String name) {
		ReusltItem item = new ReusltItem();
		try {
			List<Shop> list = shopService.getShopStartWithName(name);
			item.setCode(0);
			item.setData(list);
			item.setDesc("获取成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("店铺的名字模糊查询异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "店铺的名字模糊查询异常! 异常信息:{0}", e, "shop/getShopStartWithName");
		}
		return item;
	}

	/**
	 * 设置旗舰店 品牌下只有一个店是旗舰店
	 * 
	 * @param brandid
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/setFlag")
	public ReusltItem setFlag(String brandid, String shopid) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(brandid) < 0) {
				item.setCode(-101);
				item.setDesc("品牌的id不能为空,id" + brandid);
				return item;
			}
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-102);
				item.setDesc("店铺的id不能为空,id" + shopid);
				return item;
			}

			int result = shopService.updateFlag(StringUtilsEX.ToInt(brandid), StringUtilsEX.ToInt(shopid));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("设置成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"设置旗舰店页面", "/platform/shop/setFlag", "设置旗舰店");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "设置旗舰店操作记录出错! 异常信息:", e, "/platform/shop/setFlag");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("设置失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("设置旗舰店异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "设置旗舰店异常! 异常信息:{0}", e, "shop/setFlag");
		}
		return item;
	}

	/**
	 * 改变店铺的状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/changeShopStatus")
	public ReusltItem changeShopStatus(String id, String status) {
		ReusltItem item = new ReusltItem();
		try {
			Integer _status = StringUtilsEX.ToInt(status);
			Integer _id = StringUtilsEX.ToInt(id);
			if (_id < 0) {
				item.setCode(-101);
				item.setDesc("店铺id错误");
				return item;
			}
			if (_status < 0) {
				item.setCode(-102);
				item.setDesc("店铺要改变状态错误");
				return item;
			}

/*			if (_status == ShopStatusEnum.Open.getValue()) {
				Configdictionary config = configSetService.showConfigSetByType(ConfigSetTypeEnum.店铺保证金.getValue());
				float value = StringUtilsEX.ToFloat(config.getValue());
				Shop shop = shopService.queryById(_id);
				Usercapital usercapital = usercapitalService.queryByUserId(shop.getUserid());
				if (usercapital.getBond() < value) {
					item.setCode(-104);
					item.setDesc("店铺保证金不足,请充值");
					return item;
				}

			}
*/
			SessionUser sessionUser = SessionState.GetCurrentUser();
			int result = shopService.updateShopStatus(_id, _status, sessionUser.getUserId(),
					sessionUser.getLoginName());
			if (result > 0) {
				item.setCode(0);
				item.setDesc("设置成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), sessionUser.getUserId(),
									sessionUser.getLoginName(), "spgl_dpgl_list.jsp", "/platform/shop/changeShopStatus",
									"改变店铺的状态");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "改变店铺的状态操作记录出错! 异常信息:", e,
									"/platform/shop/changeShopStatus");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("设置失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("改变店铺的状态异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "改变店铺的状态异常! 异常信息:{0}", e, "shop/changeShopStatus");
		}
		return item;
	}

	/**
	 * 查询经营范围
	 * 
	 * @return
	 */
	@RequestMapping("/qeuryBusinessScop")
	public ReusltItem qeuryBusinessScop() {
		ReusltItem item = new ReusltItem();
		try {
			Configdictionary configdictionary = configSetService.showConfigSetByType(ConfigSetTypeEnum.经营范围.getValue());
			item.setCode(0);
			item.setData(configdictionary.getValue());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("经营范围信息异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "经营范围信息异常! 异常信息:{0}", e, "shop/qeuryBusinessScop");
		}
		return item;
	}

	/**
	 * 是否存在账户验证
	 * 
	 * @return
	 */
	@RequestMapping("/checkAccountRepeat")
	public ReusltItem checkAccountRepeat(String userkey, String usertype) {
		ReusltItem item = new ReusltItem();
		try {
			List<Accounts> list = accountsService.selectByName(userkey, StringUtilsEX.ToInt(usertype));
			if (list.size() == 0) {
				item.setCode(0);
			} else {
				item.setCode(-200);
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("验证是否存在账户异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "验证是否存在账户异常信息:", e, "shop/checkAccountRepeat");
		}
		return item;
	}

	/**
	 * 查询一定范围内的店铺集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@RequestMapping("/queryScopeBylatAndlogit")
	public ReusltItem queryScopeBylatAndlogit(String longitude, String latitude, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtils.isBlanck(longitude) || StringUtils.isBlanck(latitude)) {
				item.setCode(-102);
				item.setDesc("经纬度不能为空");
				return item;
			}
			if (StringUtilsEX.ToInt(page) < 0) {
				item.setCode(-103);
				item.setDesc("分页参数page错误:" + page);
				return item;
			}
			if (StringUtilsEX.ToInt(size) < 0) {
				item.setCode(-104);
				item.setDesc("分页参数size错误:" + size);
				return item;
			}
			Map<String, String> map = MapDistance.getSquarePoint(longitude, latitude, ConstanValue.DISTANCE);
			CriteriaShop criteria = new CriteriaShop();
			criteria.setLongmax(map.get("longmax"));
			criteria.setLongmin(map.get("longmin"));
			criteria.setLatmax(map.get("latmax"));
			criteria.setLatmin(map.get("latmin"));
			PageBean pageBean = shopService.queryBylatAndlogitCriteria(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(StringUtilsEX.ToInt(page));
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询经营范围异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询经营范围异常! 异常信息:{0}", e, "shop/queryScopeBylatAndlogit");
		}
		return item;
	}

	/**
	 * 查询店铺的广告列表
	 * 
	 * @param page
	 * @param size
	 * @param id
	 * @return
	 */
	@RequestMapping("/getImgListByCriteria")
	public ReusltItem getImgListByCriteria(String page, String size, String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数店铺id错误");
				return item;
			}
			if (StringUtilsEX.ToInt(page) < 0) {
				item.setCode(-103);
				item.setDesc("分页参数page错误:" + page);
				return item;
			}
			if (StringUtilsEX.ToInt(size) < 0) {
				item.setCode(-104);
				item.setDesc("分页参数size错误:" + size);
				return item;
			}
			CriteriaGroupBuy criteria = new CriteriaGroupBuy();
			criteria.setShopid(StringUtilsEX.ToInt(id));
			criteria.setType(AdvertImgTypeEnum.店铺.getValue());
			PageBean pageBean = advertImgService.getListByPage(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(StringUtilsEX.ToInt(page));
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询店铺的广告列表异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询店铺的广告列表异常! 异常信息:{0}", e, "shop/getImgListByCriteria");
		}
		return item;
	}

	/**
	 * 删除店铺广告
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delimg")
	public ReusltItem delimg(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数图片id错误");
				return item;
			}
			int result = advertImgService.deleteAdvert(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_imgList.jsp", "/platform/shop/delimg", "删除店铺广告");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除店铺广告操作记录出错! 异常信息:", e, "/platform/shop/delimg");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("删除失败");
				LogHandle.error(LogType.Api, MessageFormat.format("删除店铺广告", item.getDesc()), "shop/delimg");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除店铺广告异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "删除店铺广告异常! 异常信息:{0}", e, "shop/delimg");
		}
		return item;
	}

	/**
	 * 查询广告
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryImg")
	public ReusltItem queryImg(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数图片id错误");
				return item;
			}
			AdvertImg advertImg = advertImgService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(advertImg);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询广告异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					MessageFormat.format("查询广告异常! 异常信息:{0}", e.getMessage()).toString(), "shop/queryImg");
		}
		return item;
	}

	/**
	 * 保存广告
	 * 
	 * @param shopid
	 * @param img
	 * @param status
	 * @param sort
	 * @return
	 */
	@RequestMapping("/saveImg")
	public ReusltItem saveImg(String shopid, String img, String url, String status, String sort, String id) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("参数店铺id错误");
				return item;
			}
			if (StringUtils.isBlanck(img)) {
				item.setCode(-102);
				item.setDesc("请上传图片");
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-103);
				item.setDesc("请选择状态");
				return item;
			}
			if (StringUtilsEX.ToInt(sort) < 0) {
				item.setCode(-104);
				item.setDesc("请选择排序");
				return item;
			}
			// if (StringUtils.isBlanck(url)) {
			// item.setCode(-105);
			// item.setDesc("请填写链接地址");
			// return item;
			// }
			AdvertImg advertImg = new AdvertImg();
			advertImg.setCreattime(new Date());
			advertImg.setImgurl(img);
			advertImg.setUrl(url);
			advertImg.setIsdel(false);
			advertImg.setSort(StringUtilsEX.ToInt(sort));
			advertImg.setStatus(StringUtilsEX.ToInt(status));
			advertImg.setShopid(StringUtilsEX.ToInt(shopid));
			advertImg.setType(AdvertImgTypeEnum.店铺.getValue());
			if (StringUtils.isBlanck(id)) {
				int result = advertImgService.insert(advertImg);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("保存成功");
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
										"shop_imgEdit.jsp", "/platform/shop/saveImg", "保存广告");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "保存广告操作记录出错! 异常信息:", e,
										"/platform/shop/saveImg");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("保存失败");
				}

			} else {
				advertImg.setId(StringUtilsEX.ToInt(id));
				int result = advertImgService.updateByPrimaryKey(advertImg);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("保存成功");
				} else {
					item.setCode(-200);
					item.setDesc("保存失败");
				}
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("保存广告异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					MessageFormat.format("保存广告异常! 异常信息:{0}", e.getMessage()).toString(), "shop/saveImg");
		}
		return item;
	}

	/**
	 * 修改排序
	 * 
	 * @param id
	 * @param sort
	 * @return
	 */
	@RequestMapping("/updateImgSort")
	public ReusltItem updateImgSort(String id, String sort) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数id错误");
				return item;
			}
			if (StringUtilsEX.ToInt(sort) < 0) {
				item.setCode(-102);
				item.setDesc("请选择排序");
				return item;
			}
			int result = advertImgService.updateOrder(StringUtilsEX.ToInt(sort), StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"修改广告排序页面", "/platform/shop/updateImgSort", "修改广告排序");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改广告排序操作记录出错! 异常信息:", e,
									"/platform/shop/updateImgSort");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("修改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改广告排序异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					MessageFormat.format("修改广告排序异常! 异常信息:{0}", e.getMessage()).toString(), "shop/updateImgSort");
		}
		return item;
	}

	/**
	 * 修改排序
	 * 
	 * @param id
	 * @param sort
	 * @return
	 */
	@RequestMapping("/conditionSelect")
	public ReusltItem conditionSelect(String name) {
		ReusltItem item = new ReusltItem();
		try {
			List<Shop> shoplist = shopService.seletcByLikeName(name);
			item.setCode(0);
			item.setData(shoplist);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询店铺信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					MessageFormat.format("查询店铺信息异常! 异常信息:{0}", e.getMessage()).toString(), "shop/conditionSelect");
		}
		return item;

	}

	/**
	 * 插入一条违规店铺信息
	 * 
	 * @param shopid
	 * @param violationtype
	 * @param describe
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping("/addVoilationShop")
	public ReusltItem addVoilationShop(String shopid, String violationtype, String describe, String result,
			HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("请选择违规店铺");
				return item;
			}
			if (StringUtilsEX.ToInt(violationtype) < 0) {
				item.setCode(-102);
				item.setDesc("请选择违规类型");
				return item;
			}
			if (StringUtils.isBlanck(describe)) {
				item.setCode(-105);
				item.setDesc("请填写违规描述");
				return item;
			}
			if (StringUtils.isBlanck(result)) {
				item.setCode(-105);
				item.setDesc("请填写违规处理");
				return item;
			}

			Shop shop = shopService.queryById(StringUtilsEX.ToInt(shopid));
			Shopormemberstatushistory shopstatus = new Shopormemberstatushistory();
			shopstatus.setType(ShopHistoryStatus.店铺.getValue());
			shopstatus.setObjid(StringUtilsEX.ToInt(shopid));
			shopstatus.setCurrentid(ShopStatusEnum.Violation.getValue());
			shopstatus.setCurrentname(ShopStatusEnum.getName(3));
			shopstatus.setLastid(shop.getStatus());
			shopstatus.setLastname(ShopStatusEnum.getName(shop.getStatus()));
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			shopstatus.setOperatorid(sessionUser.getUserId());
			shopstatus.setOperatorname(sessionUser.getName());
			shopstatus.setCreatetime(new Date());

			shop.setStatus(ShopStatusEnum.Violation.getValue());

			Shopviolation shopviolation = new Shopviolation();
			shopviolation.setShopid(StringUtilsEX.ToInt(shopid));
			shopviolation.setType(StringUtilsEX.ToInt(violationtype));
			shopviolation.setDescription(describe);
			shopviolation.setResult(result);
			shopviolation.setIsdel(false);
			shopviolation.setCreattime(new Date());
			shopviolation.setIscurrent(true);

			int temp = shopviolationService.insertviolation(shop, shopstatus, shopviolation);
			if (temp > 0) {
				item.setCode(0);
				item.setDesc("插入违规店铺成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shopViolationAdd.jsp", "/platform/shop/addVoilationShop", "插入违规店铺");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "插入违规店铺操作记录出错! 异常信息:", e,
									"/platform/shop/addVoilationShop");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("插入违规店铺失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加违规店铺异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "添加违规店铺异常! 异常信息:{0}", e, "shop/addVoilationShop");
		}
		return item;
	}

	/**
	 * 删除店铺违规
	 * 
	 * @param violationId
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/delVoilationShop")
	public ReusltItem delVoilationShop(String violationId, String shopid, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(violationId) < 0) {
				item.setCode(-101);
				item.setDesc("违规店铺id不能为空");
				return item;
			}
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-102);
				item.setDesc("店铺id不能为空");
				return item;
			}
			Shopormemberstatushistory shopstatu = shopstatushistoryService.queryNewStatus(StringUtilsEX.ToInt(shopid));
			Shopormemberstatushistory shopstatus = new Shopormemberstatushistory();
			shopstatus.setType(ShopHistoryStatus.店铺.getValue());
			shopstatus.setObjid(StringUtilsEX.ToInt(shopid));
			shopstatus.setCurrentid(shopstatu.getLastid());
			shopstatus.setCurrentname(shopstatu.getLastname());
			shopstatus.setLastid(ShopStatusEnum.Violation.getValue());
			shopstatus.setLastname(ShopStatusEnum.getName(3));
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			shopstatus.setOperatorid(sessionUser.getUserId());
			shopstatus.setOperatorname(sessionUser.getName());
			shopstatus.setCreatetime(new Date());

			Shop shop = shopService.queryById(StringUtilsEX.ToInt(shopid));
			shop.setStatus(shopstatu.getLastid());

			Shopviolation shopviolation = shopviolationService.queryBykey(StringUtilsEX.ToInt(violationId));
			if (shopviolation != null) {
				shopviolation.setIsdel(true);
				shopviolation.setDeltime(new Date());
			}

			int temp = shopviolationService.delviolation(shopstatus, shop, shopviolation);
			if (temp > 0) {
				item.setCode(0);
				item.setData("删除成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_violation.jsp", "/platform/shop/delVoilationShop", "删除违规店铺");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除违规店铺操作记录出错! 异常信息:", e,
									"/platform/shop/delVoilationShop");
						}

					}
				});
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除店铺违规异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "删除店铺违规异常! 异常信息:{0}", e, "shop/delVoilationShop");
		}

		return item;
	}

	/**
	 * 解除违规店铺
	 * 
	 * @param violationId
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/removeVoilationShop")
	public ReusltItem removeVoilationShop(String violationId, String shopid, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(violationId) < 0) {
				item.setCode(-101);
				item.setDesc("违规店铺id不能为空");
				return item;
			}
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-102);
				item.setDesc("店铺id不能为空");
				return item;
			}
			Shopormemberstatushistory shopstatu = shopstatushistoryService.queryNewStatus(StringUtilsEX.ToInt(shopid));
			Shopormemberstatushistory shopstatus = new Shopormemberstatushistory();
			shopstatus.setType(ShopHistoryStatus.店铺.getValue());
			shopstatus.setObjid(StringUtilsEX.ToInt(shopid));
			shopstatus.setCurrentid(shopstatu.getLastid());
			shopstatus.setCurrentname(shopstatu.getLastname());
			shopstatus.setLastid(ShopStatusEnum.Violation.getValue());
			shopstatus.setLastname(ShopStatusEnum.getName(3));
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			shopstatus.setOperatorid(sessionUser.getUserId());
			shopstatus.setOperatorname(sessionUser.getName());
			shopstatus.setCreatetime(new Date());

			Shop shop = shopService.queryById(StringUtilsEX.ToInt(shopid));
			shop.setStatus(shopstatu.getLastid());

			Shopviolation shopviolation = shopviolationService.queryBykey(StringUtilsEX.ToInt(violationId));
			if (shopviolation != null) {
				shopviolation.setIscurrent(false);
			}

			int temp = shopviolationService.delviolation(shopstatus, shop, shopviolation);
			if (temp > 0) {
				item.setCode(0);
				item.setData("删除成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_violation.jsp", "/platform/shop/removeVoilationShop", "删除店铺违规");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除店铺违规操作记录出错! 异常信息:", e,
									"/platform/shop/removeVoilationShop");
						}

					}
				});
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除店铺违规异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "删除店铺违规异常! 异常信息:{0}", e, "shop/removeVoilationShop");
		}

		return item;
	}

	/**
	 * 查询违规店铺
	 * 
	 * @param page
	 * @param size
	 * @param type
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/queryVoilationShopByCriteria")
	public ReusltItem queryVoilationShopByCriteria(String page, String size, String type, String shopid) {

		ReusltItem item = new ReusltItem();
		try {
			CriteriaVoilationShop criteria = new CriteriaVoilationShop();
			criteria.setType(StringUtilsEX.ToInt(type));
			criteria.setShopid(StringUtilsEX.ToInt(shopid));
			PageBean pageBean = shopviolationService.queryShopVoilationListByCriteria(criteria,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询店铺违规异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询店铺违规异常! 异常信息:{0}", e,
					"shop/queryVoilationShopByCriteria");
		}
		return item;
	}

	/**
	 * 查询违规店铺的历史信息
	 * 
	 * @param page
	 * @param size
	 * @param type
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/queryHistoryVoilationShopByCriteria")
	public ReusltItem queryHistoryVoilationShopByCriteria(String page, String size, String type, String shopid) {

		ReusltItem item = new ReusltItem();
		try {
			CriteriaVoilationShop criteria = new CriteriaVoilationShop();
			criteria.setType(StringUtilsEX.ToInt(type));
			criteria.setShopid(StringUtilsEX.ToInt(shopid));
			PageBean pageBean = shopviolationService.queryShopHistoryVoilationListByCriteria(criteria,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询历史店铺违规异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询历史店铺违规异常! 异常信息:{0}", e,
					"shop/queryHistoryVoilationShopByCriteria");
		}
		return item;
	}

	/**
	 * 得到所以得违规类型
	 * 
	 * @return
	 */
	@RequestMapping("/getViolations")
	public ReusltItem getViolations() {
		ReusltItem item = new ReusltItem();
		try {
			ShopViolationTypeEnum[] array = ShopViolationTypeEnum.values();

			List<VillationDTo> list = new ArrayList<VillationDTo>();
			for (int i = 0; i < array.length; i++) {
				VillationDTo dTo = new VillationDTo();
				dTo.setValue(array[i].getName(array[i].lvl1.getValue()));
				dTo.setIndex(array[i].lvl1.getValue());
				list.add(dTo);
			}
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取所有的违规类型异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					MessageFormat.format("获取所有的违规类型异常! 异常信息:{0}", e.getMessage()), "shop/getViolations");
		}
		return item;
	}

	/**
	 * 同步业务支撑系统
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/syncup")
	public ReusltItem SyncUp(String id) {
		ReusltItem item = new ReusltItem();
		try {
			String[] desc = new String[1];
			// TODO： 原支撑系统 要修改
			/*
			 * if (!shopService.SyncUp(StringUtilsEX.ToInt(id), desc)) {
			 * item.setCode(-100); item.setDesc("支撑系统返回错误信息:" + desc[0]); } else
			 * { item.setDesc(desc[0]); }
			 */
		} catch (Exception ex) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("同步业务支撑系统异常：" + ex.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "同步业务支撑系统异常! 异常信息:{0}", ex, "shop/SyncUp");
		}
		return item;
	}

	/**
	 * 查询店铺分类的列表
	 * 
	 * @param page
	 * @param size
	 * @param type
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/queryShopCategoryList")
	public ReusltItem queryShopCategoryList(String page, String size, String name) {

		ReusltItem item = new ReusltItem();
		try {
			CriteriaShopCategory criteria = new CriteriaShopCategory();
			criteria.setName(name);
			PageBean pageBean = shopcategoryService.queryShopCategoryList(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询店铺分类的列表异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "查询店铺分类的列表异常! 异常信息:{0}", e, "shop/queryShopCategoryList");
		}
		return item;
	}

	/**
	 * 编辑店铺分类
	 * 
	 * @param id
	 * @param categoryName
	 * @return
	 */
	@RequestMapping("/updatCategoryById")
	public ReusltItem updatCategoryById(String id, String categoryName) {

		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("分类ID参数错误");
				return item;
			}
			if ("".equals(categoryName)) {
				item.setCode(-102);
				item.setDesc("分类不能为空");
				return item;
			}
			Shopcategory shopcategory = shopcategoryService.getByName(categoryName, StringUtilsEX.ToInt(id));
			if (shopcategory != null) {
				item.setCode(-110);
				item.setDesc("该分类已存在");
				return item;
			}
			int reslt = shopcategoryService.updatCategoryById(StringUtilsEX.ToInt(id), categoryName);
			if (reslt > 0) {
				item.setCode(0);
				item.setDesc("编辑成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_category_add.jsp", "/platform/shop/updatCategoryById", "修改店铺分类");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改店铺分类操作记录出错! 异常信息:", e,
									"/platform/shop/updatCategoryById");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("编辑失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑店铺分类异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "编辑店铺分类异常! 异常信息:{0}", e, "shop/updatCategoryById");
		}
		return item;
	}

	/**
	 * 删除店铺分类
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delShopCategory")
	public ReusltItem delShopCategory(String id) {

		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			int reslt = shopcategoryService.delShopCategory(StringUtilsEX.ToInt(id));
			if (reslt > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_ category_list.jsp", "/platform/shop/delShopCategory", "删除店铺分类");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除店铺分类操作记录出错! 异常信息:", e,
									"/platform/shop/delShopCategory");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除店铺分类异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "删除店铺分类异常! 异常信息:{0}", e, "shop/delShopCategory");
		}
		return item;
	}

	/**
	 * 添加店铺分类
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/addCategoryName")
	public ReusltItem addCategoryName(String name) {

		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if ("".equals(name)) {
				item.setCode(-101);
				item.setDesc("分类不能为空");
				return item;
			}
			Shopcategory shopcategory = shopcategoryService.queryByName(name);
			if (shopcategory != null) {
				item.setCode(-110);
				item.setDesc("该分类已存在");
				return item;
			}
			int reslt = shopcategoryService.addCategoryName(name);
			if (reslt > 0) {
				item.setCode(0);
				item.setDesc("添加成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_category_add.jsp", "/platform/shop/addCategoryName", "添加店铺分类");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加店铺分类操作记录出错! 异常信息:", e,
									"/platform/shop/addCategoryName");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加店铺分类异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "添加店铺分类异常! 异常信息:{0}", e, "shop/addCategoryName");
		}
		return item;
	}

	/**
	 * 添加商圈
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/addBusinessDistrict")
	public ReusltItem addBusinessDistrict(String name) {

		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if ("".equals(name)) {
				item.setCode(-101);
				item.setDesc("商圈不能为空");
				return item;
			}
			Circle circle = circleService.queryByName(name);
			if (circle != null) {
				item.setCode(-110);
				item.setDesc("该商圈已存在");
				return item;
			}
			int reslt = circleService.addBusinessDistrict(name);
			if (reslt > 0) {
				item.setCode(0);
				item.setDesc("添加成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"business_district_add.jsp", "/platform/shop/addBusinessDistrict", "添加商圈");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加商圈操作记录出错! 异常信息:", e,
									"/platform/shop/addBusinessDistrict");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加商圈异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "添加商圈异常! 异常信息:{0}", e, "shop/addBusinessDistrict");
		}
		return item;
	}

	/**
	 * 编辑商圈
	 * 
	 * @param id
	 * @param categoryName
	 * @return
	 */
	@RequestMapping("/updatBusinessDistrictById")
	public ReusltItem updatBusinessDistrictById(String id, String name) {

		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if ("".equals(name)) {
				item.setCode(-101);
				item.setDesc("商圈不能为空");
				return item;
			}
			int reslt = circleService.updatBusinessDistrictById(StringUtilsEX.ToInt(id), name);
			if (reslt > 0) {
				item.setCode(0);
				item.setDesc("编辑成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"business_district_add.jsp", "/platform/shop/updatBusinessDistrictById", "修改商圈");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改商圈操作记录出错! 异常信息:", e,
									"/platform/shop/updatBusinessDistrictById");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("编辑失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑商圈异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "编辑商圈异常! 异常信息:{0}", e, "shop/updatBusinessDistrictById");
		}
		return item;
	}

	/**
	 * 删除商圈
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delBusinessDistrict")
	public ReusltItem delBusinessDistrict(String id) {

		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			int reslt = circleService.delBusinessDistrict(StringUtilsEX.ToInt(id));
			if (reslt > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"business_district_list.jsp", "/platform/shop/delBusinessDistrict", "删除商圈");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除商圈操作记录出错! 异常信息:", e,
									"/platform/shop/delBusinessDistrict");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除商圈异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "删除商圈异常! 异常信息:{0}", e, "shop/delBusinessDistrict");
		}
		return item;
	}

	/**
	 * 分页查询商圈列表
	 * 
	 * @param page
	 * @param size
	 * @param type
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/queryBusinessDistrictList")
	public ReusltItem queryBusinessDistrictList(String page, String size, String name) {

		ReusltItem item = new ReusltItem();
		try {
			CriteriaShopCategory criteria = new CriteriaShopCategory();
			criteria.setName(name);
			PageBean pageBean = circleService.queryBusinessDistrictList(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询商圈列表异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "分页查询商圈列表异常! 异常信息:{0}", e,
					"shop/queryBusinessDistrictList");
		}
		return item;
	}

	/**
	 * 修改支付密码
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/updatePayPwd")
	public ReusltItem updatePayPwd(String shopid, String newpwd, String repwd) {

		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("店铺的id不能为空,id" + shopid);
				return item;
			}
			if (!StringUtils.isNotNull(newpwd)) {
				item.setCode(-102);
				item.setDesc("新密码不能为空");
				return item;
			}
			int result = shopService.updatePayPwd(StringUtilsEX.ToInt(shopid), newpwd, repwd);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改支付密码成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"spgl_dpgl_pay_pwd_update.jsp", "/platform/shop/updatePayPwd", "修改支付密码");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改支付密码操作记录出错! 异常信息:", e,
									"/platform/shop/updatePayPwd");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("修改支付密码失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加店铺分类异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "添加店铺分类异常! 异常信息:{0}", e, "shop/updatePayPwd");
		}
		return item;
	}

	/**
	 * 分页查询申请店铺列表
	 * 
	 * @param page
	 * @param size
	 * @param type
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/queryApplyShopList")
	public ReusltItem queryApplyShopList(String page, String size, String contactsname, String mobile,
			String shopname) {

		ReusltItem item = new ReusltItem();
		try {
			CriteriaApplyShop criteria = new CriteriaApplyShop();
			criteria.setContactsname(contactsname);
			criteria.setMobile(mobile);
			criteria.setShopname(shopname);
			PageBean pageBean = applyshopService.queryApplyShopList(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询申请店铺列表异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "分页查询申请店铺列表异常! 异常信息:{0}", e, "shop/queryApplyShopList");
		}
		return item;
	}

	/**
	 * 删除申请店铺
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delApplyShop")
	public ReusltItem delApplyShop(String id) {

		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			int result = applyshopService.delApplyShop(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_apply.jsp", "/platform/shop/delApplyShop", "删除申请店铺");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除申请店铺操作记录出错! 异常信息:", e,
									"/platform/shop/delApplyShop");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除申请店铺异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "删除申请店铺异常! 异常信息:{0}", e, "shop/delApplyShop");
		}
		return item;
	}

	/**
	 * 更改申请店铺的状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateApplyShop")
	public ReusltItem updateApplyShop(String id) {

		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			int result = applyshopService.updateApplyShop(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("更改成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"shop_apply.jsp", "/platform/shop/updateApplyShop", "更改申请店铺的状态");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "更改申请店铺的状态操作记录出错! 异常信息:", e,
									"/platform/shop/updateApplyShop");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("更改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("更改申请店铺的状态异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "更改申请店铺的状态异常! 异常信息:{0}", e, "shop/updateApplyShop");
		}
		return item;
	}

	/**
	 * 导入
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/importExcel")
	public ReusltItem importExcel(MultipartFile file) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			ExcleReadHelper helper = ExcleReadHelper.getInstance();
			List<String[]> arrayList = helper.generateSQL4POI(file);
			List<String> cicleList = helper.getCicle(arrayList);
			int code = shopService.insertShopInfoByExcle(arrayList, cicleList);
			if (code > 0) {
				item.setCode(0);
				item.setDesc("导入成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"添加商户信息页面", "/platform/shop/importExcel", "导入商户信息");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "导入商户信息操作记录出错! 异常信息:", e,
									"/platform/shop/importExcel");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("导入失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("导入商户信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "导入商户信息异常! 异常信息:{0}", e, "shop/importExcel");
		}
		return item;
	}

	/**
	 * 导入礼品卡
	 * 
	 * @param file
	 * @throws Exception
	 */
	@RequestMapping("/importcard")
	public void excel(MultipartFile file) throws Exception {
		try {
			ExcleReadHelper helper = ExcleReadHelper.getInstance();
			List<String[]> arrayList = helper.generateSQL4POI(file);
			giftcard g = new giftcard();
			for (int i = 0; i < arrayList.size(); i++) {
				String[] array = arrayList.get(i);
				giftcardExample example = new giftcardExample();
				giftcardExample.Criteria criteria = example.createCriteria();
				criteria.andCodeEqualTo(array[1]);
				List<giftcard> list = cardservice.selectByExample(example);
				if (list != null && list.size() > 0) {
					break;
				}
				g.setName(array[0]);
				g.setCode(array[1]);
				g.setPassword(array[2]);
				g.setRemark(array[3]);
				g.setType(array[4]);
				g.setBatchnum(array[5]);
				g.setFacevalue(StringUtilsEX.ToDouble(array[6]));
				g.setIsused(false);
				g.setIsdel(false);
				cardservice.insert(g);
			}
		} catch (Exception e) {

			LogHandle.error(LogType.PlatformShopManagement, MessageFormat.format("导入礼品卡异常! 异常信息:{0}", e.getMessage()),
					"shop/importcard");
		}
	}

	/**
	 * 查询店铺下商品分类
	 * 
	 * @return
	 */
	@RequestMapping("/queryCategorys")
	public ReusltItem queryCategorys(String fid, String name, String status, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			CriteriaCommodity criteriaCommodity = new CriteriaCommodity();
			if (StringUtilsEX.ToInt(fid) > 0) {
				criteriaCommodity.setFatherid(StringUtilsEX.ToInt(fid));
			}
			if (StringUtilsEX.ToInt(status) >= 0) {
				criteriaCommodity.setClassStatus(StringUtilsEX.ToInt(status));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
				criteriaCommodity.setName(name);
			}
			// 根据用户获取店铺ID
			criteriaCommodity.setShopid(user.getShopid());
			if (StringUtilsEX.ToInt(page) <= 0 || StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误。pageindex：" + page + ",pagesize:" + size);
			}
			PageBean pBean = categoryService.getClassByShopPage(criteriaCommodity, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);
			item.setDesc("success");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询店铺下商品分类出现的异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "查询店铺下商品分类出现的异常信息:" + e.toString(), "shop/queryCategorys");
		}

		return item;
	}

	/**
	 * 根据父ID查询子分类信息
	 * 
	 * @param fatherid
	 * @return
	 */
	@RequestMapping("/getClassByFatherID")
	public ReusltItem getClassByFatherID(String fatherid) {
		ReusltItem item = new ReusltItem();
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		try {
			if (StringUtilsEX.ToInt(fatherid) < 0) {
				item.setCode(-101);
				item.setDesc("请选择正确的父级分类!");
				return item;
			}
			list = categoryService.getByFatherID(Integer.parseInt(fatherid));
			item.setDesc("查询子分类信息成功");
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(" 根据父ID查询子分类信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "根据父ID查询子分类信息出错! 异常信息:", e, "/shop/getClassByFatherID");
		}
		return item;
	}

	/**
	 * 删除自定义分类
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCategory")
	public ReusltItem deleteCategory(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("分类ID参数错误");
				return item;
			} else {

				int result = categoryService.deleteCatetory(StringUtilsEX.ToInt(id), user.getUserId());
				if (result > 0) {
					item.setCode(0);
					item.setDesc("删除成功");
					LogHandle.info(LogType.Product, MessageFormat.format("删除分类信息成功! ID:{0}", id),
							"shopcategory/deleteCategory");
					SessionUser user = SessionState.GetCurrentUser();
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
										OperateRecordsFromEnum.卖家中心.getValue(), user.getUserId(), user.getLoginName(),
										"删除自定义分类页面", "/shop/deleteCategory", "删除自定义分类");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "添加删除自定义分类操作记录出错! 异常信息:", e,
										"/shop/deleteCategory");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("删除失败");
					LogHandle.info(LogType.Product, MessageFormat.format("删除分类信息失败! ID:{0}", id),
							"shop/deleteCategory");

				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除自定义分类出现的异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "删除自定义分类出现的异常:" + e.toString(), "shop/updateCategory");
		}
		return item;
	}

	/**
	 * 自定义分类提交审核
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/subReview")
	public ReusltItem subReview(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("自定义分类ID参数错误：" + id);
				return item;
			}
			Category category = new Category();
			category.setStatus(ClassStatusEnum.审核中.getValue());
			category.setId(StringUtilsEX.ToInt(id));
			if (categoryService.updateStatus(category) > 0) {
				item.setCode(0);
				item.setDesc("提交审核成功");
				LogHandle.info(LogType.Product, MessageFormat.format("提交审核分类信息成功! id:{0}", id), "shop/subReview");
				SessionUser user = SessionState.GetCurrentUser();
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.卖家中心.getValue(), user.getUserId(), user.getLoginName(),
									"修改自定义分类状态页面", "/platform/shop/subReview", "修改自定义分类状态");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加修改自定义分类状态操作记录出错! 异常信息:", e, "/shop/subReview");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("提交审核失败");
				LogHandle.info(LogType.Product, MessageFormat.format("提交审核分类信息失败! id:{0}", id), "shop/subReview");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("定义分自类提交审核异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "定义分自类提交审核异常:" + e.toString(), "shop/subReview");
		}
		return item;
	}

	/**
	 * 添加自定义分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/addCategory")
	public ReusltItem addCategory(String name, String firstID, String secondID, String sort) {
		ReusltItem item = new ReusltItem();
		try {
			Category category = checkCategoryParam("0", name, firstID, secondID, sort, item);
			if (item.getCode() < 0) {
				return item;
			}
			int result = categoryService.addCategory(category);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("添加分类信息成功");
				LogHandle.info(LogType.Product, MessageFormat.format("添加分类信息成功! 分类名称:{0}", name), "shop/addCategory");
				SessionUser user = SessionState.GetCurrentUser();
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.卖家中心.getValue(), user.getUserId(), user.getLoginName(),
									"添加自定义分类页面", "/platform/shop/addCategory", "添加自定义分类");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加添加自定义分类操作记录出错! 异常信息:", e, "/shop/addCategory");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("添加分类信息失败");
				LogHandle.info(LogType.Product, MessageFormat.format("添加分类信息失败! 分类名称:{0}", name), "shop/addCategory");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加自定义分类信息异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "添加自定义分类信息异常:", e, "shop/addCategory");
		}
		return item;
	}

	/**
	 * 添加分类
	 * 
	 * @param name
	 * @param fatherid
	 * @param sort
	 * @param warnnum
	 * @param iseditable
	 * @param isvirtual
	 * @param item
	 * @return
	 */
	private Category checkCategoryParam(String id, String name, String fatherid, String secondid, String sort,
			ReusltItem item) throws Exception {
		Category category = new Category();
		user = SessionState.GetCurrentUser();
		if (StringUtils.isBlanck(name)) {
			item.setCode(-101);
			item.setDesc("分类名称不能为空");
			return null;
		}
		Integer ID = StringUtilsEX.ToInt(id);
		if (ID < 0) {
			item.setCode(-102);
			item.setDesc("分类ID参数错误");
			return null;
		}
		if (StringUtilsEX.ToInt(sort) < 0) {
			item.setCode(-103);
			item.setDesc("分类排序参数错误，sort：" + sort);
			return null;
		}

		if (ID == 0) {
			category.setIsdel(false);
			category.setWarnnum(0);
			category.setIseditable(true);
			category.setIsvirtual(false);
			category.setCreattime(new Date());

			category.setShopid(user.getShopid());// 店铺ID，默认为1

			Integer fid = StringUtilsEX.ToInt(fatherid);
			Integer sid = StringUtilsEX.ToInt(secondid);
			if (fid < 0 || sid < 0) {
				item.setCode(-103);
				item.setDesc("所属类别错误");
			}

			category.setFatherid(sid);
			category.setClasslever(4);
			category.setFullpath(fid + "," + sid);
			category.setSearchpath("," + fid + "," + sid + ",");
		} else {
			category = categoryService.selectByPrimaryKey(ID);
		}
		category.setStatus(ClassStatusEnum.未提交.getValue()); // 审核通过
		category.setClasstype(ClassTypeEnum.商家自定义.getValue());
		category.setName(name);
		category.setSort(StringUtilsEX.ToInt(sort));

		return category;
	}

	/**
	 * 修改自定义分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/updateCategory")
	public ReusltItem updateCategory(String id, String name, String firstID, String secondID, String sort) {
		ReusltItem item = new ReusltItem();
		try {
			Category category = checkCategoryParam(id, name, firstID, secondID, sort, item);
			if (item.getCode() < 0) {
				return item;
			}
			int result = categoryService.updateCategory(category);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改分类信息成功");
				LogHandle.info(LogType.PlatformShopManagement,
						MessageFormat.format("修改分类信息成功! ID:{0},分类名称:{1}", id, name), "shop/updateCategory");
				SessionUser user = SessionState.GetCurrentUser();
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.卖家中心.getValue(), user.getUserId(), user.getLoginName(),
									"修改自定义分类页面", "/platform/shop/updateCategory", "修改自定义分类");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加修改自定义分类操作记录出错! 异常信息:", e,
									"/platform/shop/updateCategory");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("修改分类信息失败");
				LogHandle.info(LogType.PlatformShopManagement,
						MessageFormat.format("修改分类信息失败! ID:{0},分类名称:{1}", id, name), "shop/updateCategory");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改自定义分类出现的异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "修改自定义分类出现的异常:" + e.toString(), "shop/updateCategory");
		}
		return item;
	}
	
	/**
	 * 删除店铺
	 * 
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/deleteShop")
	public ReusltItem deleteShop(String shopid) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(shopid) < 0) {
				item.setCode(-101);
				item.setDesc("店铺的id不能为空,id" + shopid);
			}
			
			int result = shopService.deleteShop(StringUtilsEX.ToInt(shopid));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除店铺成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"spgl_dpgl_list.jsp", "/platform/shop/deleteShop", "删除店铺");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除店铺操作记录出错! 异常信息:", e,
									"/platform/shop/deleteShop");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("删除店铺失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("店铺删除异常：{0}" + e.getMessage().toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement, "店铺删除异常! 异常信息:{0}", e, "shop/deleteShop");
		}
		return item;
	}

}

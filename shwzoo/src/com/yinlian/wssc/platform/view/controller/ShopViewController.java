/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.view.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.PayTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.UserfinanceDto;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Circle;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.ShopBrand;
import com.yinlian.wssc.web.po.ShopClass;
import com.yinlian.wssc.web.po.Shopcategory;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.CircleService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ShopAuthenticationService;
import com.yinlian.wssc.web.service.ShopBrandService;
import com.yinlian.wssc.web.service.ShopClassService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.ShopcategoryService;
import com.yinlian.wssc.web.service.UserFinanceService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.CriteriaFinance;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 店铺的页面显示的控制类
 * 
 * @author Administrator
 * @version $Id: ShopViewController.java, v 0.1 2016年3月9日 下午8:01:40
 *          Administrator Exp $
 */
@Controller
@RequestMapping("/platform/shop")
public class ShopViewController {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory.getLogger(ShopViewController.class);
	@Autowired
	private ShopService shopService;
	@Autowired
	private UserService userService;
	@Autowired
	private ShopAuthenticationService shopAuthenticationService;
	@Autowired
	private AccountsService accountsService;
	@Autowired
	private ProvinceServcice provinceServcice;
	@Autowired
	private CityServcie cityServcie;
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopClassService shopClassService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ShopBrandService shopBrandService;
	@Autowired
	private UsercapitalService usercapitalService;
	@Autowired
	private AdvertImgService advertImgService;
	@Autowired
	private ConfigSetService configSetService;
	@Autowired
	private ShopcategoryService shopcategoryService;
	@Autowired
	private CircleService circleService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserFinanceService userFinanceService;

	/**
	 * 供应商资金记录列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showfinance_list")
	public String showfinance_list() {
		return "platform/shop/userfinance_list";
	}

	/**
	 * 显示店铺管理的列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {

		return "platform/shop/spgl_dpgl_list";
	}

	/**
	 * 催单列表
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/cdorderList")
	public String cdorderList(String id, HttpServletRequest request) {
		return "platform/shop/cd_OrderList";
	}

	/**
	 * 显示shop的添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/shopEdit")
	public String shopEdit(Model model, Integer shopid) {
		try {
			model.addAttribute("clist", shopcategoryService.getAllList());
			model.addAttribute("slist", circleService.getAllList());
			if (shopid != null) {

				Shop shop = shopService.queryById(shopid);
				model.addAttribute("shop", shop);
				Users users = userService.queryById(shop.getUserid());
				model.addAttribute("user", users);
				Accounts accounts = accountsService.queryByuserid(users.getId());
				model.addAttribute("accounts", accounts);
				ShopAuthentication shopAuthentication = shopAuthenticationService.queryByShopId(shop.getId());
				model.addAttribute("shopAuthentication", shopAuthentication);
				// // 获取分类
				// String shopclassfullpath = "";
				//
				// List<ShopClass> list = shopClassService.queryBy(shopid);
				// for (ShopClass shopClass : list) {
				// String path = shopClass.getClassfullpath();
				// shopclassfullpath = shopclassfullpath + path + "|";
				// }
				// model.addAttribute("shopclassfullpath", shopclassfullpath);

			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return "platform/shop/spgl_dpgl_add";
	}

	@RequestMapping("/queryRegion")
	public @ResponseBody ReusltItem queryRegion(String type, String code) {
		ReusltItem item = new ReusltItem();
		try {
			switch (StringUtilsEX.ToInt(type)) {
			case 0:
				// 查询 省
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
				// 查询区
				List<Area> areas = areaService.selectByCityCode(code);
				item.setCode(0);
				item.setData(areas);
				break;
			}
		} catch (Exception e) {
			logger.error("", e);
			item.setCode(-900);
			item.setDesc("异常信息：e" + e.getMessage());
		}
		return item;
	}

	/**
	 * 显示修改密码的页面
	 * 
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/showPwdUpdate")
	public String showPwdUpdate(String shopid, Model model) {
		model.addAttribute("shopid", shopid);
		return "platform/shop/spgl_dpgl_pwd_update";
	}

	/**
	 * 显示修改支付密码的页面
	 * 
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/showPayPwdUpdate")
	public String showPayPwdUpdate(String shopid, Model model) {
		model.addAttribute("shopid", shopid);
		return "platform/shop/spgl_dpgl_pay_pwd_update";
	}

	/**
	 * 显示店铺关联的品牌列表
	 * 
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/showShopBranList")
	public String showShopBranList(String shopid, Model model) {

		try {
			Shop shop = shopService.queryById(StringUtilsEX.ToInt(shopid));
			model.addAttribute("shop", shop);
		} catch (Exception e) {
			logger.error("", e);
		}
		return "platform/shop/spgl_spgl_pp_list";
	}

	/**
	 * 显示店铺品牌编辑的页面
	 * 
	 * @param shopid
	 * @param brandid
	 * @param model
	 * @return
	 */
	@RequestMapping("/showShopBrandEdit")
	public String showShopBrandEdit(Integer shopid, Integer id, Model model) {
		// 查询出店铺
		try {
			Shop shop = shopService.queryById(shopid);
			model.addAttribute("shop", shop);
			List<Brand> list = brandService.selectBrandList();
			model.addAttribute("list", list);
			// 根据shopid和brandid查询 shopbranid
			if (id != null) {
				ShopBrand shopBrand = shopBrandService.queryById(id);
				model.addAttribute("shopBrand", shopBrand);
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		return "platform/shop/spgl_dpgl_brandEdit";
	}

	/**
	 * 显示参数配置页面
	 * 
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/showParamSet")
	public String showParamSet(Integer shopid, Model model) {
		try {
			Shop shop = shopService.queryById(shopid);
			// Integer userid = shop.getUserid();
			// Usercapital record = usercapitalService.queryByUserId(userid);
			// TODO: 用户资产 余额 保证 显示 (要修改 原支撑系统)
			// Map<String, Object> map =
			// WebserviceClient.assetQueryInfo(userService.queryById(userid)
			// .getUsername(), WebServiceTypeEnum.账户资产查询);
			// String bond = (String) map.get("Advance");
			// shop.setMargin(Float.valueOf(bond));
			model.addAttribute("shop", shop);

		} catch (Exception e) {
			LogHandle.error(LogType.PlatformShopManagement,
					MessageFormat.format("查询参数配置异常! 异常信息:{0}", e.getMessage()).toString(), "shop/showParamSet");
		}
		return "platform/shop/shop_setting";
	}

	/**
	 * 显示店铺充值的页面
	 * 
	 * @param shopid
	 * @param model
	 * @return
	 */
	@RequestMapping("/showRecharge")
	public String showRecharge(Integer shopid, Model model) {
		try {
			Shop shop = shopService.queryById(shopid);
			model.addAttribute("shop", shop);
			int userid = shop.getUserid();
			// TODO: 用户资产 余额 保证 显示 原支撑系统 要修改
			Usercapital usercapital = usercapitalService.queryByUserId(userid);
			/*
			 * Map<String, Object> map =
			 * WebserviceClient.assetQueryInfo(userService.queryById(userid)
			 * .getUsername(), WebServiceTypeEnum.账户资产查询); String bond =
			 * (String) map.get("Advance"); String balance = (String)
			 * map.get("AccountBalance"); String freezemoney = (String)
			 * map.get("Hanging"); usercapital.setBond(Float.valueOf(bond));
			 * usercapital.setBalance(Float.valueOf(balance));
			 * usercapital.setFreezemoney(Float.valueOf(freezemoney));
			 * model.addAttribute("usercapital", usercapital);
			 * model.addAttribute("deposit", map.get("Deposit"));
			 */
			double bond = usercapital.getBond();
			double balance = usercapital.getBalance();
			double freezemoney = usercapital.getFreezemoney();
			usercapital.setBond(bond);
			usercapital.setBalance(balance);
			usercapital.setFreezemoney(freezemoney);
			model.addAttribute("usercapital", usercapital);
			Configdictionary config = configSetService.showConfigSetByType(ConfigSetTypeEnum.店铺保证金.getValue());
			model.addAttribute("config", config);
		} catch (Exception e) {
			LogHandle.error(LogType.PlatformShopManagement,
					MessageFormat.format("查询参数配置异常! 异常信息:{0}", e.getMessage()).toString(), "shop/showRecharge");
		}
		return "platform/shop/shop_recharge";
	}

	/**
	 * 显示审核中店铺的页面
	 * 
	 * @param checkStatus
	 * @return
	 */
	@RequestMapping("/showChecking")
	public String showChecking(String checkStatus) {

		return "platform/shop/shop_check";
	}

	/**
	 * 显示店铺详细页面
	 * 
	 * @return
	 */
	@RequestMapping("/showShopDetail")
	public String showShopDetail() {

		return "platform/shop/shop_check_detail";
	}

	/**
	 * 显示店铺违规页面
	 * 
	 * @return
	 */
	@RequestMapping("/showViolationShop")
	public String showViolationShop() {

		return "platform/shop/shop_violation";
	}

	/**
	 * 显示店铺违规页面
	 * 
	 * @return
	 */
	@RequestMapping("/ShopViolationAdd")
	public String shopViolationAdd() {

		return "platform/shop/shopViolationAdd";
	}

	/**
	 * 显示店铺违规历史页面
	 * 
	 * @return
	 */
	@RequestMapping("/showViolationHistoryShop")
	public String showViolationHistoryShop() {

		return "platform/shop/shop_violation_history";
	}

	/**
	 * 显示店员列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/showShopEmployeeList")
	public String showShopEmployeeList() {

		return "platform/shop/shop_employee";
	}

	/**
	 * 显示添加店员的页面
	 * 
	 * @return
	 */
	@RequestMapping("/showShopEmployeeAdd")
	public String showShopEmployeeAdd() {

		return "platform/shop/shop_employee_add";
	}

	/**
	 * 显示密码修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/showShopEmployeePwdUpdate")
	public String showShopEmployeePwdUpdate() {

		return "platform/shop/shop_employee_pwd_update";
	}

	/**
	 * 显示店员更新的页面
	 * 
	 * @return
	 */
	@RequestMapping("/showShopEmployeeUpdate")
	public String showShopEmployeeUpdate() {

		return "platform/shop/shop_employee_update";
	}

	/**
	 * 显示直营店铺编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/showOwned")
	public String showOwned(Model model) {
		try {
			try {
				model.addAttribute("clist", shopcategoryService.getAllList());
				model.addAttribute("slist", circleService.getAllList());
				Shop shop = shopService.getOwnedShop();
				if (shop != null) {

					model.addAttribute("shop", shop);
					Users users = userService.queryById(shop.getUserid());
					model.addAttribute("user", users);
					Accounts accounts = accountsService.queryByuserid(users.getId());
					model.addAttribute("accounts", accounts);
					ShopAuthentication shopAuthentication = shopAuthenticationService.queryByShopId(shop.getId());
					model.addAttribute("shopAuthentication", shopAuthentication);
					// 获取分类
					String shopclassfullpath = "";

					List<ShopClass> list = shopClassService.queryBy(shop.getId());
					for (ShopClass shopClass : list) {
						String path = shopClass.getClassfullpath();
						shopclassfullpath = shopclassfullpath + path + "|";
					}
					model.addAttribute("shopclassfullpath", shopclassfullpath);

				}
			} catch (Exception e) {
				logger.error("", e);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return "platform/shop/shop_owned";
	}

	@RequestMapping("/showBrandShop")
	public String showBrandShop(Model model) {
		try {
			List<Brand> list = brandService.selectBrandList();
			model.addAttribute("brands", list);
		} catch (Exception e) {
			logger.error("", e);
		}
		return "platform/product/goods_sppz_ppdpgl";
	}

	/**
	 * 显示店铺广告图片管理
	 * 
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/showImageList")
	public String showImageList(String shopid, Model model) {
		try {
			Shop shop = shopService.queryById(StringUtilsEX.ToInt(shopid));
			model.addAttribute("shop", shop);
		} catch (Exception e) {
			logger.error("", e);
		}
		return "platform/shop/shop_imgList";
	}

	/**
	 * 显示图片编辑页面
	 * 
	 * @param shopid
	 * @param model
	 * @return
	 */
	@RequestMapping("/shopImgEdit")
	public String shopImgEdit(String id, String shopid, Model model) {
		try {
			AdvertImg advertImg = advertImgService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			model.addAttribute("data", advertImg);
			model.addAttribute("shopid", shopid);
		} catch (Exception e) {
			logger.error("", e);
		}
		return "platform/shop/shop_imgEdit";
	}

	/**
	 * 显示店铺分类列表的页面
	 */
	@RequestMapping("/showShopCategoryList")
	public String showShopCategoryList() {

		return "platform/shop/shop_ category_list";
	}

	/**
	 * 显示添加/编辑店铺分类的页面
	 */
	@RequestMapping("/showShopCategoryAdd")
	public String showShopCategoryAdd(Integer id, Model model) {
		try {
			Shopcategory shopcategory = shopcategoryService.queryById(id);
			model.addAttribute("shopcategory", shopcategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "platform/shop/shop_category_add";
	}

	/**
	 * 显示商圈列表的页面
	 */
	@RequestMapping("/showBusinessDistrictList")
	public String showBusinessDistrictList() {

		return "platform/shop/business_district_list";
	}

	/**
	 * 显示添加/编辑店铺分类的页面
	 */
	@RequestMapping("/showBusinessDistrictAdd")
	public String showBusinessDistrictAdd(Integer id, Model model) {
		try {
			Circle circle = circleService.queryById(id);
			model.addAttribute("circle", circle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "platform/shop/business_district_add";
	}

	/**
	 * 显示申请店铺列表的页面
	 */
	@RequestMapping("/showApplyShopList")
	public String showApplyShopList() {

		return "platform/shop/shop_apply";
	}

	/**
	 * 导入商户信息页面
	 */
	@RequestMapping("/importshop")
	public String importshop() {

		return "platform/shop/importshop";
	}

	/**
	 * 自定义分类列表
	 * 
	 * @return
	 */
	@RequestMapping("/spgl_customlist")
	public String spgl_customlist() {
		return "platform/shop/spgl_customlist";
	}

	/**
	 * 自定义分类编辑
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_customEdit")
	public String spgl_customEdit(String id, Model model) {
		Category cate = new Category();
		String fid = "", sid = "", tid = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {

				cate = categoryService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
				if (cate != null) {
					if (cate.getFullpath().split(",").length > 0) {
						switch (cate.getFullpath().split(",").length) {
						case 1:
							break;
						case 2:
							fid = cate.getFullpath().split(",")[0];
							break;
						case 3:
							fid = cate.getFullpath().split(",")[0];
							sid = cate.getFullpath().split(",")[1];
							break;
						case 4:
							fid = cate.getFullpath().split(",")[0];
							sid = cate.getFullpath().split(",")[1];
							tid = cate.getFullpath().split(",")[2];
							break;
						default:
							break;
						}
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fid", fid);
		model.addAttribute("sid", sid);
		model.addAttribute("tid", tid);
		model.addAttribute("data", cate);
		return "platform/shop/spgl_customEdit";
	}

	/**
	 * excel导出供应商资金记录信息
	 * 
	 * @param request @param resp @throws
	 */
	@RequestMapping("/exportfinance")
	public void exportfinance(HttpServletRequest request, HttpServletResponse resp, String type, String shopcode,
			String starttime, String endtime, String shopname, String paytype, String number, String paynum) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			request.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/x-download");

			String fileName = "供应商资金记录列表.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			HSSFSheet sheet = wb.createSheet("供应商资金记录列表");
			sheet.setDefaultRowHeight((short) (2 * 256));
			sheet.setColumnWidth(0, 50 * 100);
			sheet.setColumnWidth(1, 50 * 160);
			sheet.setColumnWidth(2, 50 * 100);
			sheet.setColumnWidth(3, 50 * 100);
			sheet.setColumnWidth(4, 50 * 100);
			sheet.setColumnWidth(5, 50 * 100);
			sheet.setColumnWidth(6, 50 * 100);
			sheet.setColumnWidth(7, 50 * 100);
			sheet.setColumnWidth(8, 50 * 150);
			sheet.setColumnWidth(9, 50 * 100);
			sheet.setColumnWidth(10, 50 * 200);
			sheet.setColumnWidth(11, 50 * 200);
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);
			HSSFRow row = sheet.createRow((int) 0);
			sheet.createRow((int) 1);
			sheet.createRow((int) 2);
			sheet.createRow((int) 3);
			sheet.createRow((int) 4);
			sheet.createRow((int) 5);
			sheet.createRow((int) 6);
			sheet.createRow((int) 7);
			sheet.createRow((int) 8);
			sheet.createRow((int) 9);
			sheet.createRow((int) 10);
			sheet.createRow((int) 11);
			sheet.createRow((int) 12);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCell cell = row.createCell(0);
			cell.setCellValue("供应商编号 ");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("供应商名称 ");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("金额类型");
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("发生金额");
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue("支付类型");
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("商户编号 ");
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("商户名称 ");
			cell.setCellStyle(style);
			cell = row.createCell(7);
			cell.setCellValue("订单编号 ");
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue("订单流水号 ");
			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue("支付单号 ");
			cell = row.createCell(10);
			cell.setCellStyle(style);
			cell.setCellValue("日期 ");
			cell = row.createCell(11);
			cell.setCellStyle(style);
			cell.setCellValue("描述 ");
			cell = row.createCell(12);
			cell.setCellStyle(style);

			CriteriaFinance criteria = new CriteriaFinance();
			String typeslist = "";
			typeslist += CapitalChange_Type.解冻.getValue() + ",";
			typeslist += CapitalChange_Type.冻结金额增加.getValue() + ",";
			typeslist += CapitalChange_Type.冻结金额扣除.getValue() + ",";
			typeslist += CapitalChange_Type.退款扣除.getValue();
			criteria.setTypes(typeslist.trim());
			if (!StringUtilsEX.IsNullOrWhiteSpace(shopcode)) {
				criteria.setUsername(shopcode);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(shopname)) {
				criteria.setShopname(shopname);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(paynum)) {
				criteria.setPaynum(paynum);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(number)) {
				criteria.setNumber(number);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(paytype)) {
				paytype = paytype.substring(0, paytype.length() - 1);
				criteria.setPaytypes(paytype);
			}
			if (StringUtilsEX.ToInt(type) >= 0) {
				criteria.setType(StringUtilsEX.ToInt(type));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(starttime)) {
				criteria.setStart(StringUtilsEX.ToShortDate(starttime));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endtime)) {
				criteria.setEnd(StringUtilsEX.ToShortDate(endtime));
			}
			criteria.setSort("desc");
			criteria.setOrderByClause("createtime");

			List<UserfinanceDto> malist = userFinanceService.selectShopFinanceList(criteria);

			for (int i = 0; i < malist.size(); i++) {
				HSSFRow row1 = sheet.createRow((int) i + 1);
				UserfinanceDto ma = malist.get(i);
				row1.createCell(0).setCellValue(ma.getCodingnum()); //
				row1.createCell(1).setCellValue(ma.getUsername()); //

				if (ma.getType() == CapitalChange_Type.余额转入保证金.getValue()) {
					row1.createCell(2).setCellValue("余额转入保证金"); //
				}
				if (ma.getType() == CapitalChange_Type.保证金充值.getValue()) {
					row1.createCell(2).setCellValue("保证金充值"); //
				}
				if (ma.getType() == CapitalChange_Type.充值.getValue()) {
					row1.createCell(2).setCellValue("充值"); //
				}
				if (ma.getType() == CapitalChange_Type.冻结.getValue()) {
					row1.createCell(2).setCellValue("冻结"); //
				}
				if (ma.getType() == CapitalChange_Type.冻结金额增加.getValue()) {
					row1.createCell(2).setCellValue("冻结金额增加"); //
				}
				if (ma.getType() == CapitalChange_Type.冻结金额扣除.getValue()) {
					row1.createCell(2).setCellValue("冻结金额扣除"); //
				}
				if (ma.getType() == CapitalChange_Type.后台管理添加.getValue()) {
					row1.createCell(2).setCellValue("后台管理添加"); //
				}
				// if (ma.getType() == CapitalChange_Type.提现.getValue()) {
				// row1.createCell(2).setCellValue("提现"); //
				// }
				if (ma.getType() == CapitalChange_Type.支出.getValue()) {
					row1.createCell(2).setCellValue("支出"); //
				}
				if (ma.getType() == CapitalChange_Type.收入.getValue()) {
					row1.createCell(2).setCellValue("收入"); //
				}
				if (ma.getType() == CapitalChange_Type.解冻.getValue()) {
					row1.createCell(2).setCellValue("解冻"); //
				}
				if (ma.getType() == CapitalChange_Type.退款扣除.getValue()) {
					row1.createCell(2).setCellValue("退款扣除"); //
				}
				if (ma.getType() == CapitalChange_Type.退款返还.getValue()) {
					row1.createCell(2).setCellValue("退款返还"); //
				}

				row1.createCell(3).setCellValue(String.valueOf(ma.getMoney())); //
				if (ma.getPaytype() == null) {
					row1.createCell(4).setCellValue(""); //
				} else {
					if (ma.getPaytype() == PayTypeEnum.微信支付.getValue()) {
						row1.createCell(4).setCellValue("微信支付"); //
					}
					if (ma.getPaytype() == PayTypeEnum.支付宝支付.getValue()) {
						row1.createCell(4).setCellValue("支付宝支付"); //
					}
				}
				row1.createCell(5).setCellValue(ma.getBuyernum()); //
				row1.createCell(6).setCellValue(ma.getBuyername()); //
				row1.createCell(7).setCellValue(ma.getNumber()); //
				row1.createCell(8).setCellValue(ma.getGroupnum()); //
				row1.createCell(9).setCellValue(ma.getPaynum()); //
				row1.createCell(10).setCellValue(ma.getCreattimestr()); //
				row1.createCell(11).setCellValue(ma.getDescription()); //
			}

			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.PlatformShopManagement, "导出供应商资金记录信息异常! 异常信息:{0}", e, "shop/exportfinance");
		}
	}

}

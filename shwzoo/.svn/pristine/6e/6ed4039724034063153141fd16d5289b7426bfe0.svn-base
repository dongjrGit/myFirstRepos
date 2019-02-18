/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springfremarke.bean.prezo.BeanUtils;

import com.techown.wssc.web.mapper.ScenicMapper;
import com.techown.wssc.web.po.Scenic;
import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.FinanceTypeEnum;
import com.yinlian.Enums.PayPwdStatusEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.ShopHistoryStatus;
import com.yinlian.Enums.ShopStatusEnum;
import com.yinlian.Enums.SiteType;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.Enums.UserStatusEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ApiShopAboutInfoBaseDto;
import com.yinlian.api.app.dto.SatisfactionGoodCountDto;
import com.yinlian.api.app.dto.ShopAppDto;
import com.yinlian.api.app.dto.ShopScopeApiDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.TopicRelateInfo;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.AccountsMapper;
import com.yinlian.wssc.web.mapper.AdvertImgMapper;
import com.yinlian.wssc.web.mapper.AreaMapper;
import com.yinlian.wssc.web.mapper.BrandMapper;
import com.yinlian.wssc.web.mapper.CircleMapper;
import com.yinlian.wssc.web.mapper.CityMapper;
import com.yinlian.wssc.web.mapper.CouponMapper;
import com.yinlian.wssc.web.mapper.FinancerecordsMapper;
import com.yinlian.wssc.web.mapper.ProvinceMapper;
import com.yinlian.wssc.web.mapper.SatisfactionMapper;
import com.yinlian.wssc.web.mapper.ShopAuthenticationMapper;
import com.yinlian.wssc.web.mapper.ShopBrandMapper;
import com.yinlian.wssc.web.mapper.ShopClassMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.ShopormemberstatushistoryMapper;
import com.yinlian.wssc.web.mapper.SkuMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.mapper.UserAttrMapper;
import com.yinlian.wssc.web.mapper.UsercapitalMapper;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.Circle;
import com.yinlian.wssc.web.po.Financerecords;
import com.yinlian.wssc.web.po.Satisfaction;
import com.yinlian.wssc.web.po.SatisfactionExample;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.ShopAuthenticationExample;
import com.yinlian.wssc.web.po.ShopBrand;
import com.yinlian.wssc.web.po.ShopBrandExample;
import com.yinlian.wssc.web.po.ShopClass;
import com.yinlian.wssc.web.po.ShopExample;
import com.yinlian.wssc.web.po.Shopormemberstatushistory;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.SpuExample;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.UsercapitalExample;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.UsersExample;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.CommonUtils;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 店铺的业务了的实现了
 * 
 * @author Administrator
 * @version $Id: ShopServiceImpl.java, v 0.1 2016年3月9日 下午9:19:42 Administrator
 *          Exp $
 */
public class ShopServiceImpl implements ShopService {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ShopServiceImpl.class);

	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private AccountsMapper accountsMapper;
	@Autowired
	private ShopClassMapper shopClassMapper;
	@Autowired
	private ShopAuthenticationMapper shopAuthenticationMapper;
	@Autowired
	private UsercapitalMapper usercapitalMapper;
	@Autowired
	private ShopBrandMapper shopBrandMapper;
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private SpuMapper spuMapper;
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private SatisfactionMapper satisfactionMapper;
	@Autowired
	private UsercollectService usercollectService;
	@Autowired
	private AdvertImgMapper advertImgMapper;
	@Autowired
	private UserfinanceMapper userfinanceMapper;
	@Autowired
	private FinancerecordsMapper financerecordsMapper;
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private ShopormemberstatushistoryMapper statushistoryMapper;
	@Autowired
	private UserAttrMapper userAttrMapper;
	@Autowired
	private CircleMapper circleMapper;
	@Autowired
	private ScenicMapper scenicMapper;
	@Autowired
	private SkuMapper    skuMapper;
	/**
	 * @see com.yinlian.wssc.web.service.ShopService#seletcByLikeName(java.lang.String)
	 */
	@Override
	public List<Shop> seletcByLikeName(String name) throws Exception {
		ShopExample example = new ShopExample();
		ShopExample.Criteria criteria = example.createCriteria();
		criteria.andNameLike("%" + name + "%");
		criteria.andIsdelEqualTo(false);
		return shopMapper.selectByExample(example);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#selectShopPage(com.yinlian.wssc.web.util.CriteriaShop,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean selectShopPage(CriteriaShop criteria, Integer pc, Integer ps)
			throws Exception {
		if (criteria == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException(
						"The parameter Criteria is null!");
			}
		}
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Shop> beanList = shopMapper.selectShopPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#insertShop(com.yinlian.wssc.web.po.Shop,
	 *      com.yinlian.wssc.web.po.ShopClass, com.yinlian.wssc.web.po.Users,
	 *      com.yinlian.wssc.web.po.ShopAuthentication)
	 */
	@Override
	public int insertShop(Shop shop, ShopClass shopClass, Users users,
			ShopAuthentication authentication, Accounts accounts)
			throws Exception {
		CriteriaAccounts a = new CriteriaAccounts();
		if (!StringUtilsEX.IsNullOrWhiteSpace(accounts.getLoginname())) {
			a.setLoginname(accounts.getLoginname());
			if (accounts.getId() != null)
				a.setId(accounts.getId());
			if (accountsMapper.isExistAcc(a) > 0) {
				throw new Exception("登录名称重复！");
			}
		}
		if (!StringUtilsEX.IsNullOrWhiteSpace(accounts.getPhone())) {
			a = new CriteriaAccounts();
			a.setMobile(accounts.getPhone());
			if (accounts.getId() != null)
				a.setId(accounts.getId());
			if (accountsMapper.isExistAccByPhone(a) > 0) {
				throw new Exception("登录手机号重复！");
			}
		}
		// 先插入users数据
		String mobile = users.getMobile(); // 校验电话
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(mobile);
		List<Users> list1 = usersMapper.selectByExample(example);
		if (list1 != null && list1.size() > 0) {
			users.setIsmobilecheck(false);
		} else {
			users.setIsmobilecheck(true);
		}
		example = new UsersExample();
		criteria = example.createCriteria(); // 校验email
		criteria.andEmailEqualTo(users.getEmail());
		List<Users> list2 = usersMapper.selectByExample(example);
		if (list2 != null && list2.size() > 0) {
			users.setIsemailcheck(false);
		} else {
			users.setIsemailcheck(true);
		}
		users.setStatus(UserStatusEnum.正常.getValue());
		users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
				users.getPassword()));
		users.setPaypass(DEndecryptUtil.get_instances().passwordEncrypt(
				users.getPaypass()));
		users.setPaypassstatus(1);
		usersMapper.insertUsers(users);
		int userid = users.getId();
		// 插入usercapital
		Usercapital capital = new Usercapital();
		capital.setUserid(userid);
		capital.setBalance(0.0d);
		capital.setFreezemoney(0.0d);
		capital.setBond((double) shop.getMargin());
		capital.setStatus(UserFinance_Type.未支付.getValue());
		usercapitalMapper.insert(capital);
		// 插入 shop表
		shop.setUserid(userid);
		shop.setShopnum(ProductNumUtil.getShopNum());
		shopMapper.insertShop(shop);
		int shopid = shop.getId();
		// // 插入shopclass 有可能是多个
		// for (int i = 0; i < classidList.size(); i++) {
		// shopClass.setClassid(StringUtilsEX.ToInt(classidList.get(i)));
		// shopClass.setClassfullpath(fullpathList.get(i));
		// shopClass.setShopid(shopid);
		// shopClassMapper.insert(shopClass);
		// }

		// 插入authentication
		authentication.setShopid(shopid);
		authentication.setStatus(shop.getStatus());
		shopAuthenticationMapper.insert(authentication);

		// 插入Accounts表
		//
		if (users.getIsemailcheck()) {
			accounts.setEmail(users.getEmail());
		}
		if (users.getIsmobilecheck()) {
			accounts.setPhone(users.getMobile());
		}
		accounts.setUserid(userid);
		accounts.setUsertype(UserTypeEnum.Seller.getValue());
		accounts.setCreatetime(new Date());
		accounts.setIsdel(false);
		accounts.setStatus(ShopStatusEnum.Checking.getValue());
		accounts.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
				accounts.getPassword()));

		return accountsMapper.insert(accounts);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#queryById(java.lang.Integer)
	 */
	@Override
	public Shop queryById(Integer shopid) throws Exception {

		return shopMapper.selectByPrimaryKey(shopid);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#update(com.yinlian.wssc.web.po.Users,
	 *      com.yinlian.wssc.web.po.Shop,
	 *      com.yinlian.wssc.web.po.ShopAuthentication,
	 *      com.yinlian.wssc.web.po.Accounts, com.yinlian.wssc.web.po.ShopClass,
	 *      java.util.List, java.util.List)
	 */
	@Override
	public int update(Users users, Shop shop,
			ShopAuthentication authentication, Accounts accounts)
			throws Exception {

		// 更新users表
		int userid = shop.getUserid();
		Users uersRecord = usersMapper.selectByPrimaryKey(userid);
		if (uersRecord != null) {
			BeanUtils.copyProperties(users, uersRecord);
		}
		usersMapper.updateByPrimaryKey(uersRecord);
		CriteriaAccounts a = new CriteriaAccounts();
		if (!StringUtilsEX.IsNullOrWhiteSpace(accounts.getLoginname())) {
			a.setLoginname(accounts.getLoginname());
			a.setId(accounts.getId());
			if (accountsMapper.isExistAcc(a) > 0) {
				throw new Exception("登录名称重复！");
			}
		}
		if (!StringUtilsEX.IsNullOrWhiteSpace(accounts.getPhone())) {
			a = new CriteriaAccounts();
			a.setMobile(accounts.getPhone());
			a.setId(accounts.getId());
			if (accountsMapper.isExistAccByPhone(a) > 0) {
				throw new Exception("登录手机号重复！");
			}
		}
		// 更新accounts表
		Accounts accountsRecord = accountsMapper.selectByPrimaryKey(accounts
				.getId());
		accountsRecord.setLoginname(accounts.getLoginname());
		accountsRecord.setPhone(accounts.getPhone());
		accountsMapper.updateByPrimaryKey(accountsRecord);

		// 更新ShopAuthentication 表
		ShopAuthentication shopAuthenticationRecord = shopAuthenticationMapper
				.selectByPrimaryKey(authentication.getId());
		if (shopAuthenticationRecord != null) {
			BeanUtils.copyProperties(authentication, shopAuthenticationRecord);
		}
		shopAuthenticationRecord
				.setCompanycontactname(uersRecord.getUsername());
		shopAuthenticationRecord.setPrincipalmobile(authentication
				.getPrincipalmobile());
		shopAuthenticationRecord.setPrincipalemail(authentication
				.getPrincipalemail());

		shopAuthenticationMapper.updateByPrimaryKey(shopAuthenticationRecord);

		int shopid = shop.getId();
		// 更新shop

		Shop shopRecord = shopMapper.selectByPrimaryKey(shopid);
		if (shopRecord != null) {
			BeanUtils.copyProperties(shop, shopRecord);
			return shopMapper.updateByPrimaryKey(shopRecord);
		} else {
			return -1;
		}
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#updatePwd(java.lang.Integer,
	 *      java.lang.String)
	 */
	@Override
	public int updatePwd(Integer shopid, String newpwd, String repwd)
			throws Exception {
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		int userid = shop.getUserid();
		// 修改users 中的密码
		Users users = usersMapper.selectByPrimaryKey(userid);
		String oldpwd = users.getPassword();
		users.setPassword(DEndecryptUtil.get_instances()
				.passwordEncrypt(newpwd));
		usersMapper.updateByPrimaryKey(users);
		Accounts accounts = new Accounts();
		// 修改accounts表
		AccountsExample example = new AccountsExample();
		AccountsExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		List<Accounts> list = accountsMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			accounts = list.get(0);
		}
		accounts.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
				newpwd));
		// TODO： 原支撑系统 要修改
		/*
		 * Map<String, Object> map =
		 * WebserviceClient.changePassWordService(users.getUsername(), oldpwd,
		 * DEndecryptUtil.get_instances().passwordEncrypt(newpwd),
		 * WebServiceTypeEnum.登录密码修改); if (map != null &&
		 * "1".equals(map.get("ResultCode"))) {
		 * LogHandle.debug(LogType.PlatformShopManagement, MessageFormat.format(
		 * "WebService修改店铺的密码成功! 参数信息:{0},{1},返回信息:{3}", newpwd,
		 * map.get("ResultMsg")), "shop/updatePwd"); } else {
		 * LogHandle.debug(LogType.PlatformShopManagement, MessageFormat.format(
		 * "WebService修改店铺的密码失败! 参数信息:{0},{1},返回信息:{3}", newpwd,
		 * map.get("ResultMsg")), "shop/updatePwd"); }
		 */
		return accountsMapper.updateByPrimaryKey(accounts);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#updateParam(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, float)
	 */
	@Override
	public int updateParam(Integer shopid, Integer maxEmployee,
			Integer maxRole, float margin) throws Exception {
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		shop.setMargin(margin);
		shop.setMaxEmployee(maxEmployee);
		shop.setMaxRole(maxRole);
		UsercapitalExample example = new UsercapitalExample();
		UsercapitalExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(shop.getUserid());
		List<Usercapital> list = usercapitalMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			Usercapital usercapital = list.get(0);
			usercapital.setBond((double) margin);
			usercapitalMapper.updateByPrimaryKey(usercapital);
		}
		return shopMapper.updateByPrimaryKey(shop);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#recharge(java.lang.Integer,
	 *      float, java.lang.Integer)
	 */
	@Override
	public int recharge(Integer shopid, double money, Integer type)
			throws Exception {
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		if (shop == null) {
			LogHandle.error(LogType.SellerShopManagement, "查询不到相关店铺信息，店铺id："
					+ shopid, "shop/recharge");
			return -1;
		}
		int userid = shop.getUserid();
		UsercapitalExample example = new UsercapitalExample();
		UsercapitalExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		List<Usercapital> list = usercapitalMapper.selectByExample(example);
		Usercapital record = new Usercapital();
		if (list != null && list.size() > 0) {
			record = list.get(0);
		} else {
			record.setBalance(0.0d);
			record.setBond(0.0d);
			record.setFreezemoney(0.0d);
			record.setUserid(userid);
			record.setStatus(UserFinance_Type.未支付.getValue());
			usercapitalMapper.insertForPrinaryKey(record);
		}
		double balance = record.getBalance();
		double bond = record.getBond();
		Userfinance userfinance = new Userfinance();
		userfinance.setBalance(record.getBalance());
		userfinance.setCreatetime(new Date());
		userfinance.setDescription("");
		userfinance.setMoney(money);
		userfinance.setStatus(UserFinance_Type.已支付.getValue());
		userfinance.setUserid(userid);
		userfinance.setUserip("");
		userfinance.setNumber("");
		if (type == 1) { // 账户充值
			balance = balance + money;
			record.setBalance(balance);
			userfinance.setType(CapitalChange_Type.充值.getValue());
		} else { // 保证金充值
			/*
			 * if (balance < money) {
			 * LogHandle.error(LogType.PlatformShopManagement, "用户余额不足，店铺id：" +
			 * shopid, "shop/recharge"); return -1; } record.setBalance(balance
			 * - money);
			 */
			bond = bond + money;
			record.setBond(bond);
			userfinance.setType(CapitalChange_Type.充值.getValue());
		}
		// 商家资金变动表
		userfinanceMapper.updateByPrimaryKey(userfinance);
		// 商家资金记录更改表
		Financerecords financerecords = new Financerecords();
		financerecords.setCreatetime(new Date());
		financerecords.setStatus(0);
		financerecords.setType(FinanceTypeEnum.充值.getValue());
		financerecords.setUserid(userid);
		financerecords.setUsercapitalid(record.getId());
		financerecords.setUserip("");
		financerecordsMapper.insert(financerecords);
		return usercapitalMapper.updateByPrimaryKey(record);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#deleteCheck(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public int deleteCheck(Integer shopid, Integer status) throws Exception {
		Shop record = shopMapper.selectByPrimaryKey(shopid);
		record.setStatus(status);
		record.setDeltime(new Date());
		record.setDeltime(new Date());
		ShopAuthenticationExample example = new ShopAuthenticationExample();
		ShopAuthenticationExample.Criteria criteria = example.createCriteria();
		criteria.andShopidEqualTo(shopid);
		ShopAuthentication shopAuthentication = shopAuthenticationMapper
				.selectByExample(example).get(0);
		shopAuthentication.setStatus(status);
		shopAuthenticationMapper.updateByPrimaryKey(shopAuthentication);
		return shopMapper.updateByPrimaryKey(record);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#updateStatus(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int updateStatus(Integer shopid, Integer status, Integer lastStatus,
			Integer userid, String username) throws Exception {

		Shopormemberstatushistory history = new Shopormemberstatushistory();
		history.setCreatetime(new Date());
		history.setCurrentid(status);
		history.setCurrentname(ShopStatusEnum.getName(status));
		history.setLastid(lastStatus);
		history.setLastname(ShopStatusEnum.getName(lastStatus));
		history.setOperatorid(userid);
		history.setOperatorname(username);
		history.setType(ShopHistoryStatus.店铺.getValue());
		history.setObjid(shopid);
		statushistoryMapper.insert(history);

		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		shop.setStatus(status);
		shopMapper.updateByPrimaryKey(shop);
		// 修改店铺关联商品的表的状态
		ShopBrandExample brandExample = new ShopBrandExample();
		ShopBrandExample.Criteria criteria2 = brandExample.createCriteria();
		criteria2.andShopidEqualTo(shopid);
		List<ShopBrand> shopBrands = shopBrandMapper
				.selectByExample(brandExample);
		for (ShopBrand shopBrand : shopBrands) {
			shopBrand.setCheckstatus(status);
			shopBrand.setChecktime(new Date());
			shopBrandMapper.updateByPrimaryKey(shopBrand);
		}

		ShopAuthenticationExample example = new ShopAuthenticationExample();
		ShopAuthenticationExample.Criteria criteria = example.createCriteria();
		criteria.andShopidEqualTo(shopid);
		ShopAuthentication shopAuthentication = shopAuthenticationMapper
				.selectByExample(example).get(0);
		shopAuthentication.setStatus(status);

		return shopAuthenticationMapper.updateByPrimaryKey(shopAuthentication);
	}

	/**
	 * 根据店铺名称模糊检索
	 */
	public List<Shop> getShopStartWithName(String name) throws Exception {
		return shopMapper.getShopStartWithName(name);
	}

	/**
	 * @throws Exception
	 * @see com.yinlian.wssc.web.service.ShopService#selectShopViolationPage(com.yinlian.wssc.web.util.CriteriaShop,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean selectShopViolationPage(CriteriaShop criteria, Integer pc,
			Integer ps) throws Exception {
		if (criteria == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException(
						"The parameter Criteria is null!");
			}
		}
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Shop> beanList = shopMapper.selectShopPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#updateOwned(com.yinlian.wssc.web.po.Shop)
	 */
	@Override
	public int updateOwned(Shop shop) throws Exception {

		return shopMapper.updateByPrimaryKey(shop);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#insertOwned(com.yinlian.wssc.web.po.Shop)
	 */
	@Override
	public int insertOwned(Shop shop) throws Exception {

		return shopMapper.insert(shop);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#updateFlag(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public int updateFlag(Integer brandid, Integer shopid) throws Exception {
		Brand brand = brandMapper.selectByPrimaryKey(brandid); // 得到品牌
		Integer flagShipId = brand.getFlagshipid(); // 旗舰店铺的id
		if (flagShipId != null) {
			Shop _shop = shopMapper.selectByPrimaryKey(flagShipId);
			_shop.setIsflagship(false);
			shopMapper.updateByPrimaryKey(_shop);
		}
		Shop record = shopMapper.selectByPrimaryKey(shopid);
		record.setIsflagship(true);
		shopMapper.updateByPrimaryKey(record);
		brand.setFlagshipid(shopid);
		return brandMapper.updateByPrimaryKey(brand);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#updateSellerStatus(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String)
	 */
	@Override
	public int updateSellerStatus(Integer id, Integer status, String reason,
			Integer userid, String username) throws Exception {
		Shop shop = shopMapper.selectByPrimaryKey(id);
		int lastStatus = shop.getStatus();
		Shopormemberstatushistory history = new Shopormemberstatushistory();
		history.setCreatetime(new Date());
		history.setCurrentid(status);
		history.setCurrentname(ShopStatusEnum.getName(status));
		history.setLastid(lastStatus);
		history.setLastname(ShopStatusEnum.getName(lastStatus));
		history.setOperatorid(userid);
		history.setOperatorname(username);
		history.setType(ShopHistoryStatus.店铺.getValue());
		history.setObjid(id);
		statushistoryMapper.insert(history);

		shop.setStatus(status);
		shop.setReason(reason);
		shopMapper.updateByPrimaryKey(shop);
		ShopAuthenticationExample example = new ShopAuthenticationExample();
		ShopAuthenticationExample.Criteria criteria = example.createCriteria();
		criteria.andShopidEqualTo(id);
		ShopAuthentication shopAuthentication = shopAuthenticationMapper
				.selectByExample(example).get(0);
		shopAuthentication.setStatus(status);
		return shopAuthenticationMapper.updateByPrimaryKey(shopAuthentication);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#updateShopStatus(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public int updateShopStatus(Integer id, Integer status, Integer userid,
			String username) throws Exception {
		Shop record = shopMapper.selectByPrimaryKey(id);

		int lastStatus = record.getStatus();
		Shopormemberstatushistory history = new Shopormemberstatushistory();
		history.setCreatetime(new Date());
		history.setCurrentid(status);
		history.setCurrentname(ShopStatusEnum.getName(status));
		history.setLastid(lastStatus);
		history.setLastname(ShopStatusEnum.getName(lastStatus));
		history.setOperatorid(userid);
		history.setOperatorname(username);
		history.setType(ShopHistoryStatus.店铺.getValue());
		history.setObjid(id);
		statushistoryMapper.insert(history);

		record.setStatus(status);
		shopMapper.updateByPrimaryKey(record);
		ShopAuthenticationExample example = new ShopAuthenticationExample();
		ShopAuthenticationExample.Criteria criteria = example.createCriteria();
		criteria.andShopidEqualTo(id);
		ShopAuthentication shopAuthentication = shopAuthenticationMapper
				.selectByExample(example).get(0);
		shopAuthentication.setStatus(status);
		return shopAuthenticationMapper.updateByPrimaryKey(shopAuthentication);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#selectListByUserId(java.lang.Integer)
	 */
	@Override
	public List<Shop> selectListByUserId(Integer userid) throws Exception {

		ShopExample example = new ShopExample();
		ShopExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		return shopMapper.selectByExample(example);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#queryByIdForApp(java.lang.Integer)
	 */
	@Override
	public ShopAppDto queryByIdForApp(Integer id, SiteType siteType,ReusltItem item)
			throws Exception {
		Shop shop = shopMapper.selectByPrimaryKey(id);
		ShopAppDto dto = new ShopAppDto();
		if (shop == null) {
			item.setCode(-101);
			item.setDesc("店铺不存在");
			return null;
		}
		if (shop != null && shop.getIsdel()!=null && shop.getIsdel()==true) {
			item.setCode(-101);
			item.setDesc("店铺不存在");
			return null;
		}
		if (shop != null && shop.getStatus().equals(ShopStatusEnum.Open.getValue())==false) {
			item.setCode(-101);
			item.setDesc("店铺已打烊");
			return null;
		}
		if (shop != null) {
			BeanUtils.copyProperties(shop, dto);
		}
		SpuExample example = new SpuExample();
		SpuExample.Criteria criteria = example.createCriteria();
		criteria.andShopidEqualTo(id);
		criteria.andIsdelEqualTo(false);
		criteria.andStatusEqualTo(ProStatusEnum.上架.getValue());
		int count = spuMapper.countByExample(example);
		dto.setCount(count);
		ShopAuthenticationExample example2 = new ShopAuthenticationExample();
		ShopAuthenticationExample.Criteria criteria2 = example2
				.createCriteria();
		criteria2.andShopidEqualTo(id);
		List<ShopAuthentication> authentications = shopAuthenticationMapper
				.selectByExample(example2);
		if (authentications != null && authentications.size() > 0) {
			dto.setMarketingscope(authentications.get(0).getMarketingscope());
			dto.setBewrite(authentications.get(0).getBewrite());
		}
		List<AdvertImg> advertImgs = advertImgMapper.getListByShopId(id);
		dto.setAdvertimgs(advertImgs);
		Scenic scenic = scenicMapper.getScenicByShopId(id);
		if (scenic != null) {
			dto.setLatitude(scenic.getLatitude());
			dto.setLongitude(scenic.getLongitude());
		}
		List<SatisfactionGoodCountDto> dtos = shopMapper
				.getSfGoodCoutByShopId(id);
		if (dtos != null && dtos.size() > 0) {
			int commentSize = dtos.size();
			int stars = 0;
			for (SatisfactionGoodCountDto comm : dtos) {
				stars += comm.getAttitude();
			}
			double starr = Math.ceil(stars / commentSize);
			dto.setGoodrate(starr + "");
		} else {
			dto.setGoodrate("5");
		}
		return dto;
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#insertShopApp(com.yinlian.wssc.web.po.Shop,
	 *      com.yinlian.wssc.web.po.ShopClass,
	 *      com.yinlian.wssc.web.po.ShopAuthentication, java.util.List,
	 *      java.util.List)
	 */
	@Override
	public int insertShopApp(String mobile, String shopname, String shopnum,
			String username, String provincecode, String citycode,
			String areacode, String address, String password,
			String paypassword, String principalname) throws Exception {

		String provicnename = provinceMapper.selectNameByCode(provincecode);
		String cityname = cityMapper.selectNameByCode(citycode);
		String areaname = areaMapper.selectNameByCode(areacode);
		address = provicnename + cityname + areaname + address;

		Shop shop = new Shop();
		shop.setCreattime(new Date());
		shop.setIsdel(false);
		shop.setIsowned(false);
		shop.setName(shopname);
		shop.setShopnum(shopnum);
		shop.setStatus(ShopStatusEnum.Checking.getValue());
		shop.setIsflagship(false);

		ShopAuthentication authentication = new ShopAuthentication();
		authentication.setPrincipalmobile(mobile);
		authentication.setPrincipalname(principalname);
		authentication.setCompanyadress(address);
		authentication.setCompanyarea(areacode);
		authentication.setCompanycity(citycode);
		authentication.setCompanyprovince(provincecode);

		Users users = new Users();
		users.setUsername(username);
		users.setNickname(principalname);
		users.setRealname(principalname);
		users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
				password));
		users.setMobile(mobile);
		users.setPaypass(DEndecryptUtil.get_instances().passwordEncrypt(
				paypassword));
		users.setStatus(UserStatusEnum.正常.getValue());
		usersMapper.insertUsers(users);
		Integer userid = users.getId();

		Accounts accounts = new Accounts();
		accounts.setUserid(userid);
		accounts.setCreatetime(new Date());
		accounts.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
				password));
		accounts.setPhone(mobile);
		accounts.setIsdel(false);
		accounts.setUsertype(UserTypeEnum.Seller.getValue());
		accounts.setLoginname(username);
		accounts.setStatus(UserStatusEnum.正常.getValue());
		accountsMapper.insert(accounts);
		// 插入Usercapital
		Usercapital capital = new Usercapital();
		capital.setUserid(userid);
		capital.setBalance(0.0d);
		capital.setFreezemoney(0.0d);
		capital.setBond((double) shop.getMargin());
		capital.setStatus(UserFinance_Type.未支付.getValue());
		usercapitalMapper.insert(capital);

		UserAttr attr = new UserAttr();
		attr.setAddress(address);
		attr.setUserid(userid);
		attr.setAreacode(areacode);
		attr.setAreaname(areaname);
		attr.setCitycode(citycode);
		attr.setCityname(cityname);
		attr.setProvincecode(provincecode);
		attr.setProvincename(provicnename);
		attr.setPhone(mobile);
		attr.setSex(-1);
		userAttrMapper.insert(attr);
		// 插入 shop表

		shop.setShopaddress(address);
		shop.setUserid(userid);
		shopMapper.insertShop(shop);
		int shopid = shop.getId();

		// 插入authentication
		authentication.setShopid(shopid);
		authentication.setStatus(shop.getStatus());

		return shopAuthenticationMapper.insert(authentication);
	}

	@Override
	public ApiShopAboutInfoBaseDto getApiShopAboutInfo(Integer shopid)
			throws Exception {
		List<SatisfactionGoodCountDto> dtos = shopMapper
				.getSfGoodCoutByShopId(shopid);
		ApiShopAboutInfoBaseDto asa = new ApiShopAboutInfoBaseDto();
		if (dtos != null && dtos.size() > 0) {
			Integer count = (Integer) dtos.stream().collect(
					Collectors.summingInt(x -> x.getCount()));
			if (count <= 0)
				asa.setRatepraise("0.00%");
			asa.setRatepraise(Math.ceil(((Integer) dtos.stream()
					.filter(x -> x.getGood() >= 4)
					.collect(Collectors.summingInt(x -> x.getCount()))
					* 100.00 / count * 100.00))
					/ 100 + "%");
		} else {
			asa.setRatepraise("0.00%");
		}
		asa.setProcount(spuMapper.getCountByShopId(shopid));
		asa.setBookmark(usercollectService.getCountByShopId(shopid));
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		asa.setName(shop.getName());
		asa.setLogo(shop.getImgurl());
		return asa;
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#queryBylatAndlogit(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public PageBean queryBylatAndlogitCriteria(Criteria criteria, Integer pc,
			Integer ps) throws Exception {

		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Shop> list = shopMapper.selectBylatAndlogitPage(pageBeanUtil);
		List<ShopScopeApiDto> shopScopeApiDtos = new ArrayList<ShopScopeApiDto>();
		if (list != null && list.size() > 0) {
			for (Shop shop : list) {
				ShopScopeApiDto shopScopeApiDto = new ShopScopeApiDto();
				BeanUtils.copyProperties(shop, shopScopeApiDto);
				shopScopeApiDto.setDesc(shop.getDescription());
				shopScopeApiDto.setLatitude(shop.getLatitude());
				shopScopeApiDto.setLongitude(shop.getLongitude());
				shopScopeApiDto.setDistances(shop.getDistances());
				shopScopeApiDtos.add(shopScopeApiDto);
			}
		}
		pageBean.setBeanList(shopScopeApiDtos);
		return pageBean;
	}

	@Override
	public List<Shop> queryAll() throws Exception {

		return shopMapper.queryAll();
	}

	/**
	 * 分頁查詢店鋪主題
	 * 
	 * @see com.yinlian.wssc.web.service.ShopService#queryTopicRelateListByCriteria(com.yinlian.wssc.web.util.CriteriaTopic,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<TopicRelateInfo> beanList = shopMapper
				.selectTopicByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public Shop queryByUserId(Integer userid) throws Exception {

		return shopMapper.selectByUserid(userid);
	}

	// TODO： 原支撑系统 要修改
	/**
	 * 同步业务支撑系统
	 * 
	 * @throws ServiceException
	 */
	/*
	 * @Override public boolean SyncUp(Integer shopId, String[] desc) { try {
	 * Shop shop = shopMapper.selectByPrimaryKey(shopId); CompanyXml companyXml
	 * = new CompanyXml(); Users user =
	 * usersMapper.selectByPrimaryKey(shop.getUserid()); ShopAuthentication
	 * shopAuthentication =
	 * shopAuthenticationMapper.selectByPrimaryByShopId(shopId);
	 * java.util.Map<String, Object> rsl; if (shop.getIssyncup() != null &&
	 * shop.getIssyncup()) { companyXml.setUsername(user.getUsername());
	 * companyXml.setComaddress(shopAuthentication.getCompanyadress());
	 * companyXml.setComfax(shopAuthentication.getCompanyfox());
	 * companyXml.setIsep(shop.getIsep()); //
	 * companyXml.setComcontacts(shopAuthentication.getPrincipalname());
	 * companyXml.setContactphone(shopAuthentication.getPrincipalmobile());
	 * companyXml.setComproperty(getCompanytype(shopAuthentication.
	 * getCompanytype())); //
	 * companyXml.setComscale(shopAuthentication.getCompanypopulation().toString
	 * ()+"人"); rsl =
	 * WebserviceClient.updateCompany(WebServiceTypeEnum.企业修改.getValue(),
	 * companyXml); if (!rsl.get("ResultCode").equals("1")) { desc[0] =
	 * rsl.get("ResultMsg").toString(); return false; } desc[0] = "修改同步支撑系统成功！";
	 * return true; } else { BankXml bankXml = new BankXml();
	 * companyXml.setUsername(user.getUsername());
	 * companyXml.setPassword(user.getPassword());
	 * companyXml.setPayword(user.getPaypass());
	 * companyXml.setComtelephone(shop.getSupporttel());
	 * companyXml.setIsep(shop.getIsep());
	 * companyXml.setComname(shopAuthentication.getCompanyname());
	 * companyXml.setComaddress(shopAuthentication.getCompanyadress());
	 * companyXml.setComcontacts(shopAuthentication.getPrincipalname());
	 * companyXml.setContactphone(shopAuthentication.getPrincipalmobile());
	 * companyXml.setEmail(shopAuthentication.getPrincipalemail());
	 * companyXml.setComproperty(getCompanytype(shopAuthentication.
	 * getCompanytype())); companyXml.setItbustype("0001");
	 * companyXml.setItsystem("1"); if
	 * (StringUtils.isNotNull(shop.getBankname()) &&
	 * StringUtils.isNotNull(shop.getBankcardno())) {
	 * bankXml.setBankcardno(shop.getBankcardno());
	 * bankXml.setBankname(shop.getBankname());
	 * bankXml.setLineNo(shop.getBanktype());
	 * bankXml.setLineNo(shop.getLineno());
	 * bankXml.setHodername(shop.getHodername()); } rsl =
	 * WebserviceClient.comRegister(WebServiceTypeEnum.企业注册.getValue(),
	 * companyXml, bankXml); LogHandle.warn(LogType.Api, MessageFormat.format(
	 * "Webservice企业注册成功! 返回信息:{0}", JsonUtil.getJsonStringFromMap(rsl)),
	 * "ShopServiceImpl/SyncUp"); // shopMapper.updateByPrimaryKey(shop); if
	 * (!rsl.get("ResultCode").equals("1")) { desc[0] =
	 * rsl.get("ResultMsg").toString(); return false; } shop.setIssyncup(true);
	 * shopMapper.updateByPrimaryKey(shop); desc[0] = "注册同步支撑系统成功！"; return
	 * true; } } catch (Exception ex) { LogHandle.error(LogType.Api,
	 * "Webservice企业注册异常! 异常信息:{0}", ex, "ShopServiceImpl/SyncUp"); } return
	 * false; }
	 */
	private String getCompanytype(Integer type) {
		switch (type) {
		case 0:// 0：民营 1：国企 2：外企3：其它
			return "民营";
		case 1:
			return "国企";
		case 2:
			return "外企";
		default:
			return "其它";
		}
	}

	@Override
	public Shop getOwnedShop() throws Exception {

		return shopMapper.getOwnedShop();
	}

	/**
	 * 修改支付密码
	 * 
	 * @see com.yinlian.wssc.web.service.ShopService#updatePayPwd(java.lang.Integer,
	 *      java.lang.String)
	 */
	@Override
	public int updatePayPwd(Integer shopid, String newpwd, String repwd)
			throws Exception {
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		int userid = shop.getUserid();
		Users users = usersMapper.selectByPrimaryKey(userid);
		String oldpwd = users.getPaypass();
		users.setPaypass(DEndecryptUtil.get_instances().passwordEncrypt(newpwd));
		try {
			// TODO： 原支撑系统 要修改
			/*
			 * Map<String, Object> map =
			 * WebserviceClient.changePayWordService(users.getUsername(),
			 * oldpwd, DEndecryptUtil.get_instances().passwordEncrypt(newpwd),
			 * WebServiceTypeEnum.支付密码修改); if (map != null &&
			 * "1".equals(map.get("ResultCode"))) { LogHandle.debug(
			 * LogType.PlatformShopManagement, MessageFormat .format(
			 * "WebService修改店铺的支付密码成功! 参数信息:{0},{1},返回信息:{3}", newpwd,
			 * map.get("ResultMsg")), "shopServiceImpl/updatePayPwd"); } else {
			 * LogHandle.debug( LogType.PlatformShopManagement, MessageFormat
			 * .format("WebService修改店铺的支付密码失败! 参数信息:{0},{1},返回信息:{3}", newpwd,
			 * map.get("ResultMsg")), "shopServiceImpl/updatePayPwd"); }
			 */
		} catch (Exception e) {
			LogHandle.debug(LogType.PlatformShopManagement, MessageFormat
					.format("WebService修改店铺的支付密码失败! 参数信息:{0},{1},返回信息:{3}",
							newpwd, e.toString()),
					"shopServiceImpl/updatePayPwd");
		}
		return usersMapper.updateByPrimaryKey(users);
	}

	/**
	 * 直营店铺添加
	 * 
	 * @param shop
	 * @param authentication
	 * @param accounts
	 * @return
	 * @throws Exception
	 */
	public int insertOwned(Shop shop, ShopAuthentication authentication,
			Accounts accounts, Users users) throws Exception {
		CriteriaAccounts a = new CriteriaAccounts();
		if (!StringUtilsEX.IsNullOrWhiteSpace(accounts.getLoginname())) {
			a.setLoginname(accounts.getLoginname());
			if (accounts.getId() != null)
				a.setId(accounts.getId());
			if (accountsMapper.isExistAcc(a) > 0) {
				throw new Exception("登录名称重复！");
			}
		}
		if (!StringUtilsEX.IsNullOrWhiteSpace(accounts.getPhone())) {
			a = new CriteriaAccounts();
			a.setMobile(accounts.getPhone());
			if (accounts.getId() != null)
				a.setId(accounts.getId());
			if (accountsMapper.isExistAccByPhone(a) > 0) {
				throw new Exception("登录手机号重复！");
			}
		}
		users.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
				users.getPassword()));
		users.setPaypass(DEndecryptUtil.get_instances().passwordEncrypt(
				users.getPaypass()));
		users.setPaypassstatus(1);
		usersMapper.updateByPrimaryKey(users);
		accounts.setPassword(DEndecryptUtil.get_instances().passwordEncrypt(
				accounts.getPassword()));
		accountsMapper.updateByPrimaryKey(accounts);
		int userid = accounts.getUserid();
		// 插入usercapital
		Usercapital capital = new Usercapital();
		capital.setUserid(userid);
		capital.setBalance(0.0d);
		capital.setFreezemoney(0.0d);
		capital.setBond((double) shop.getMargin());
		capital.setStatus(UserFinance_Type.未支付.getValue());
		usercapitalMapper.insert(capital);
		// 插入 shop表
		shop.setUserid(userid);
		shop.setShopnum(ProductNumUtil.getShopNum());
		shopMapper.insertShop(shop);
		int shopid = shop.getId();
		// 插入authentication
		authentication.setShopid(shopid);
		authentication.setStatus(shop.getStatus());
		return shopAuthenticationMapper.insert(authentication);

	}

	public int updateOwned(Shop shop, ShopAuthentication authentication,
			Accounts accounts, Users users) throws Exception {
		// 更新users表
		int userid = shop.getUserid();
		Users uersRecord = usersMapper.selectByPrimaryKey(userid);
		if (uersRecord != null) {
			BeanUtils.copyProperties(users, uersRecord);
		}
		usersMapper.updateByPrimaryKey(uersRecord);
		CriteriaAccounts a = new CriteriaAccounts();
		if (!StringUtilsEX.IsNullOrWhiteSpace(accounts.getLoginname())) {
			a.setLoginname(accounts.getLoginname());
			a.setId(accounts.getId());
			if (accountsMapper.isExistAcc(a) > 0) {
				throw new Exception("登录名称重复！");
			}
		}
		if (!StringUtilsEX.IsNullOrWhiteSpace(accounts.getPhone())) {
			a = new CriteriaAccounts();
			a.setMobile(accounts.getPhone());
			a.setId(accounts.getId());
			if (accountsMapper.isExistAccByPhone(a) > 0) {
				throw new Exception("登录手机号重复！");
			}
		}
		// 更新accounts表
		Accounts accountsRecord = accountsMapper.selectByPrimaryKey(accounts
				.getId());
		accountsRecord.setLoginname(accounts.getLoginname());
		accountsRecord.setPhone(accounts.getPhone());
		accountsMapper.updateByPrimaryKey(accountsRecord);
		// 更新 shop表
		shopMapper.updateByPrimaryKey(shop);
		// 更新ShopAuthentication 表
		ShopAuthentication shopAuthenticationRecord = shopAuthenticationMapper
				.selectByPrimaryKey(authentication.getId());
		if (shopAuthenticationRecord != null) {
			BeanUtils.copyProperties(authentication, shopAuthenticationRecord);
		}
		shopAuthenticationRecord.setCompanycontactname(users.getUsername());
		shopAuthenticationRecord.setPrincipalmobile(authentication
				.getPrincipalmobile());
		shopAuthenticationRecord.setPrincipalemail(authentication
				.getPrincipalemail());
		return shopAuthenticationMapper
				.updateByPrimaryKey(shopAuthenticationRecord);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ShopService#insertShopInfoByExcle(java.util.List,
	 *      java.util.List)
	 */
	@Override
	public int insertShopInfoByExcle(List<String[]> arrayList,
			List<String> cicleList) throws Exception {

		if (arrayList != null && arrayList.size() > 0) {
			String password = DEndecryptUtil.get_instances().passwordEncrypt(
					"123456");
			String paypass = DEndecryptUtil.get_instances().passwordEncrypt(
					"123456");
			Integer circleid = null;
			String shopname = "";
			String username = "";
			String companyname = "";
			String principalname = "";
			String mobile = "";
			String address = "";
			String supporttel = "";
			String license = "";
			Users users = null;
			Accounts accounts = null;
			Shop shop = null;
			ShopAuthentication authentication = null;
			for (int i = 0; i < arrayList.size(); i++) {
				String[] array = arrayList.get(i);
				username = "sp100" + (i + 1);
				shopname = array[1];
				companyname = array[2];
				principalname = array[3];

				mobile = array[4];
				address = array[5];
				supporttel = array[6];
				license = array[8];

				String circlename = array[array.length - 1];
				Circle circle = circleMapper.queryByName(circlename);
				if (circle != null) {
					circleid = circle.getId();
				} else {
					Circle record = new Circle();
					record.setCreatetime(new Date());
					record.setName(circlename);
					circleMapper.insertForId(record);
					circleid = record.getId();
				}
				users = new Users();
				users.setUsername(username);
				users.setPassword(password);
				users.setPaypass(paypass);
				users.setPaypassstatus(PayPwdStatusEnum.未设置.getValue());
				users.setStatus(UserStatusEnum.正常.getValue());
				usersMapper.insertUsers(users);
				accounts = new Accounts();
				accounts.setLoginname(username);
				accounts.setPassword(password);
				accounts.setUserid(users.getId());
				accounts.setIsdel(false);
				accounts.setCreatetime(new Date());
				accounts.setStatus(UserStatusEnum.正常.getValue());
				accounts.setUsertype(UserTypeEnum.Seller.getValue());
				accountsMapper.insert(accounts);
				shop = new Shop();
				shop.setName(shopname);
				shop.setUserid(users.getId());
				shop.setCircleid(circleid);
				shop.setCompanyname(companyname);
				shop.setCreattime(new Date());
				shop.setIsflagship(false);
				shop.setIsdel(false);
				shop.setIsowned(false);
				shop.setIsep(false);
				shop.setShopnum(ProductNumUtil.getShopNum());
				shop.setStatus(ShopStatusEnum.CheckPass.getValue());
				shop.setShopaddress(address);
				shop.setSupporttel(supporttel);
				shopMapper.insertShop(shop);
				authentication = new ShopAuthentication();
				authentication.setShopid(shop.getId());
				authentication.setCompanyadress(address);
				authentication.setPrincipalmobile(mobile);
				authentication.setPrincipalname(principalname);
				authentication.setCompanyname(companyname);
				authentication.setLicense(license);
				authentication.setStatus(ShopStatusEnum.CheckPass.getValue());
				shopAuthenticationMapper.insert(authentication);
			}
		}
		return 1;
	}

	@Override
	public Shop getShopBySpuID(Integer spuid) throws Exception {

		return shopMapper.getShopBySpuID(spuid);
	}

	@Override
	public int updateByPrimaryKey(Shop record) throws Exception {
		return shopMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Shop> getShopListByName(String name) throws Exception {
		return shopMapper.getShopListByName(name);
	}

	@Override
	public PageBean getRestaurantShopList(CriteriaShop criteria, Integer page,
			Integer size) throws Exception {
		PageBeanUtil beanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = beanUtil.getPage();
		List<ShopAppDto> shopAppDtoList = shopMapper
				.getResShopListByPage(beanUtil);
		for (ShopAppDto shopAppDto : shopAppDtoList) {
			SpuExample example = new SpuExample();
			SpuExample.Criteria criteria1 = example.createCriteria();
			criteria1.andShopidEqualTo(shopAppDto.getId());
			criteria1.andIsdelEqualTo(false);
			criteria1.andStatusEqualTo(ProStatusEnum.上架.getValue());
			int count = spuMapper.countByExample(example);
			shopAppDto.setCount(count);
			List<SatisfactionGoodCountDto> dtos = shopMapper
					.getSfGoodCoutByShopId(shopAppDto.getId());
			if (dtos != null && dtos.size() > 0) {
				int commentSize = dtos.size();
				int stars = 0;
				for (SatisfactionGoodCountDto comm : dtos) {
					stars += comm.getAttitude();
				}
				double starr = Math.ceil(stars / commentSize);
				shopAppDto.setStar(starr + "");
			} else {
				shopAppDto.setStar("5");
			}
			// List<SatisfactionGoodCountDto> satisfactiondtos =
			// shopMapper.getSfGoodCoutByShopId(shopAppDto.getId());
			// double goodrate =
			// satisfactiondtos.stream().filter(x->x.getAttitude()>3).mapToInt(y->y.getCount()).sum();
			// double all =
			// satisfactiondtos.stream().mapToInt(x->x.getCount()).sum();
			// if (goodrate > 0 && all > 0) {
			// shopAppDto.setGoodrate(new BigDecimal(goodrate/all *
			// 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "%");
			// }else {
			// shopAppDto.setGoodrate("0.00%");
			// }
			shopAppDto.setGoodrate(getrate() + "%");
		}
		pageBean.setBeanList(shopAppDtoList);
		return pageBean;
	}

	@Override
	public List<Shop> getTicketCenter() throws Exception {
		List<Shop> shops = shopMapper.getTicketCenter();
		return shops;
	}

	@Override
	public PageBean getShopByName(CriteriaShop criteria, Integer pageindex,
			Integer pagesize) throws Exception {
		PageBeanUtil beanUtil = new PageBeanUtil(criteria, pageindex, pagesize);
		PageBean pageBean = beanUtil.getPage();
		List<ShopAppDto> shopAppDtoList = shopMapper
				.getShopByNameByPage(beanUtil);
		for (ShopAppDto shopAppDto : shopAppDtoList) {
			Integer count = spuMapper.getCountByShopId(shopAppDto.getId());
			shopAppDto.setCount(count);
			List<SatisfactionGoodCountDto> dtos = shopMapper
					.getSfGoodCoutByShopId(shopAppDto.getId());
			if (dtos != null && dtos.size() > 0) {
				int commentSize = dtos.size();
				int stars = 0;
				for (SatisfactionGoodCountDto comm : dtos) {
					stars += comm.getAttitude();
				}
				double starr = Math.ceil(stars / commentSize);
				shopAppDto.setStar(starr + "");
			} else {
				shopAppDto.setStar("5");
			}
			// List<SatisfactionGoodCountDto> satisfactiondtos =
			// shopMapper.getSfGoodCoutByShopId(shopAppDto.getId());
			// double goodrate =
			// satisfactiondtos.stream().filter(x->x.getAttitude()>3).mapToInt(y->y.getCount()).sum();
			// double all =
			// satisfactiondtos.stream().mapToInt(x->x.getCount()).sum();
			// if (goodrate > 0 && all > 0) {
			// shopAppDto.setGoodrate(new BigDecimal(goodrate/all *
			// 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "%");
			// }else {
			// shopAppDto.setGoodrate("0.00%");
			// }
			shopAppDto.setGoodrate(getrate() + "%");
		}
		pageBean.setBeanList(shopAppDtoList);
		return pageBean;
	}

	@Override
	public int deleteShop(Integer shopid) throws Exception {
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		if (shop == null)
			return 0;
		shop.setIsdel(true);
		shop.setDeltime(new Date());
		shop.setStatus(ShopStatusEnum.Delete.getValue());
		spuMapper.deleteSpuByShop(shopid);
		skuMapper.deleteSkuByShop(shopid);
		//
		shopMapper.updateByPrimaryKey(shop);
		accountsMapper.deleteByUserid(shop.getUserid());
		return 1;
	}

	private int getrate() {
		int max = 100;
		int min = 90;
		java.util.Random random = new java.util.Random();

		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	@Override
	public Shop gettopicshop(int value) throws Exception {
		return shopMapper.getTopicshop(value);
	}
}

package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.CouponIssueTypeEnum;
import com.yinlian.Enums.CouponTypeEnum;
import com.yinlian.Enums.CouponUseTypeEnum;
import com.yinlian.Enums.ShopStatusEnum;
import com.yinlian.Enums.VoucherStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.AvailableCouponDto;
import com.yinlian.api.app.dto.CouponDto;
import com.yinlian.api.app.dto.CouponShopDto;
import com.yinlian.api.app.dto.SearchCouponDto;
import com.yinlian.api.app.dto.SearchCouponDto.Prolist;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CouponShopCartDto;
import com.yinlian.api.app.dto.UserCouponDto;
import com.yinlian.api.app.dto.UserInfoDto;
import com.yinlian.pc.dto.CouponGetDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.V_CouponDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.CouponMapper;
import com.yinlian.wssc.web.mapper.CouponMapperCustom;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.mapper.UserCouponMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.mapper.VoucherMapper;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.CouponExample;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopExample;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.UserCoupon;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.Voucher;
import com.yinlian.wssc.web.po.VoucherExample;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Component("couponService")
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private UserCouponMapper userCouponMapper;
	@Autowired
	private CouponMapperCustom couponMapperCustom;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private ShopMapper shopMapper;

	@Autowired
	private VoucherMapper voucherMapper;

	@Autowired
	private SpuMapper spuMapper;
	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(CouponServiceImpl.class);

	/**
	 * 添加优惠卷
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int insert(Coupon record) throws Exception {
		return couponMapper.insert(record);
	}

	/**
	 * 编辑优惠卷
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int updateByID(Coupon record) throws Exception {
		return couponMapper.updateByPrimaryKey(record);
	}

	/**
	 * 删除优惠卷
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteByID(Integer id) throws Exception {
		return couponMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据ID获取优惠卷
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Coupon getByID(Integer id) throws Exception {
		return couponMapper.selectByPrimaryKey(id);
	}

	/**
	 * 获取优惠卷列表 不分页
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Coupon> queryByCoupon(Criteria criteria) throws Exception {
		return couponMapper.queryCoupon(criteria);
	}

	/**
	 * 获取所有店铺优惠券
	 */
	public List<Coupon> getShopCoupon(int webset) throws Exception {
		return couponMapper.getShopCoupon(webset);
	}

	/**
	 * 获取优惠卷列表 分页
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public PageBean getList(Criteria criteria, Integer page, Integer size)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<Coupon> list = couponMapper.getCouponByPage(pageBeanUtil);
		Shop shop = new Shop();
		for (Coupon coupon : list) {
			if (coupon.getShopid() != null) {
				shop = shopMapper.selectByPrimaryKey(coupon.getShopid());
				if (shop != null) {
					coupon.setShopname(shop.getName());
				}
			}
		}
		pBean.setBeanList(list);

		return pBean;
	}

	/**
	 * 修改优惠卷状态 公开/不公开
	 * 
	 * @param status
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateStatus(Integer status, Integer id) throws Exception {
		Coupon coupon = new Coupon();
		coupon.setId(id);
		coupon.setStatus(status);

		return couponMapper.updateStatus(coupon);
	}

	/**
	 * 删除优惠卷 标记删除
	 */
	public int deleteCoupon(Coupon coupon) throws Exception {
		return couponMapper.deleteCoupon(coupon);
	}

	/**
	 * 领用优惠卷修改数量
	 */
	public int updateCount(Coupon record) throws Exception {
		return couponMapper.updateCount(record);
	}

	/**
	 * 前台获取所有可领取的优惠卷
	 */
	public List<Coupon> getAvailableCoupon(Integer shopid) throws Exception {
		return couponMapper.getAvailableCoupon(shopid);
	}

	public Coupon getAvailableFirst(Integer shopid) throws Exception {
		return couponMapper.getAvailableFirst(shopid);
	}

	/**
	 * 获取满返类型优惠卷列表
	 */
	public List<Coupon> getCouponStartwithName(Integer shopid, String name)
			throws Exception {
		return couponMapper.getCouponStartwithName(shopid, name);
	}

	/**
	 * 会员获取优惠卷列表
	 */
	public PageBean getByUserID(Criteria criteria, Integer page, Integer size)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<UserCouponDto> list = couponMapperCustom
				.getByUserIDPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	/**
	 * 领取优惠劵
	 */
	public int addUserCoupon(Integer couponid, Integer userid, String userName,
			BaseResult item) throws Exception {

		Coupon coupon = couponMapper.selectByPrimaryKey(couponid);
		if (coupon == null) {
			item.setCode(-107);
			item.setDesc("根据优惠劵ID未能检索到数据");
			LogHandle.error(LogType.Coupon, "根据优惠劵ID未能检索到数据。优惠劵ID：" + couponid);
			return 0;
		}
		if (coupon.getConponcount() >= 1) {
			coupon.setConponcount(coupon.getConponcount() - 1);
		} else {
			item.setCode(-104);
			item.setDesc("优惠劵剩余张数不足，不能领取");
			LogHandle.error(LogType.Coupon, "优惠劵剩余张数不足，不能领取。优惠劵ID：" + couponid);
			return 0;
		}
		List<UserCoupon> ucliList = userCouponMapper.selectCount(couponid,
				userid);
		if (ucliList != null && ucliList.size() > 0) {
			if (ucliList.size() >= coupon.getGetcount()) {
				item.setCode(-105);
				item.setDesc("用户已领取该优惠劵");
				LogHandle.error(LogType.Coupon, "用户已领取该优惠劵。优惠劵ID：" + couponid);
				return 0;
			}
		}
		if (coupon.getGetuserlevel() != null && coupon.getGetuserlevel() >= 0) {
			Users users = usersMapper.selectByPrimaryKey(userid);
			if (users != null) {
				if (users.getLevel() != null) {
					if (users.getLevel() < coupon.getGetuserlevel()) {
						item.setCode(-106);
						item.setDesc("优惠劵领用等级限制，不能领取");
						LogHandle.error(LogType.Coupon, "优惠劵领用等级限制，不能领取。优惠劵ID："
								+ couponid);
						return 0;
					}
				}
			}
		}
		Date dt = coupon.getEndtime();
		UserCoupon uc = new UserCoupon();
		if (coupon.getEndday() > 0) {
			dt = DateUtil.addDays(coupon.getEndtime(), coupon.getEndday());
		}
		uc.setFromtype(coupon.getFromtype());
		uc.setCouponid(couponid);
		uc.setUserid(userid);
		uc.setIsuser(false);
		uc.setGettime(new Date());
		uc.setIsdel(false);
		uc.setOuttime(dt);
		// 添加用户关联优惠卷表信息
		userCouponMapper.insert(uc);
		// 减少优惠卷数量
		couponMapper.updateCount(coupon);

		return 1;
	}

	/**
	 * 用户使用优惠卷
	 */
	public Float UseCoupon(Integer couponid, Integer userid, Float price,
			String desc) throws Exception {
		Float ret = -1f;
		UserCoupon uc = userCouponMapper.getByUserAndCoupon(couponid, userid);
		Coupon coupon = new Coupon();
		if (uc == null) {
			logger.error(String.format("根据优惠卷ID和用户ID未检索到数据！优惠卷ID：{0}",
					coupon.getId()));
			return ret;
		} else {
			// 优惠卷信息
			coupon = couponMapper.selectByPrimaryKey(uc.getCouponid());
			// 判断优惠卷是否过期 如果有效天数不为空则判断天数，否则判断结束时间
			if (new Date().getTime() > uc.getOuttime().getTime()) {
				logger.error(String.format("该优惠卷已过使用期，不能使用！优惠卷ID：{0}",
						coupon.getId()));
				return ret;
			}
			if (coupon.getCoupontype() == CouponTypeEnum.金额限制.getValue()) {
				if (price < coupon.getFullreductionvalue()) {
					logger.error(String.format("订单金额不符合优惠券使用条件！优惠卷ID：{0}",
							coupon.getId()));
					return ret;
				}
			}

			uc.setIsuser(true);
			uc.setUserdesc(desc);
			uc.setUsetime(new Date());

			if (userCouponMapper.updateOrcancelUse(uc) > 0) {
				ret = coupon.getFacevalue();
			}
			return ret;
		}
	}

	/**
	 * 会员删除领用优惠卷
	 */
	public int deleteUCoupon(Integer id, Integer userid) throws Exception {
		UserCoupon uc = new UserCoupon();
		uc.setId(id);
		uc.setIsdel(true);
		uc.setDeluserid(userid);
		uc.setDeltime(new Date());
		return userCouponMapper.deleteOrcancelCoupon(uc);
	}

	/**
	 * 取消退货订单返回优惠卷
	 */
	public int updateUCouponCancel(List<Integer> couponids, Integer userid)
			throws Exception {

		List<UserCoupon> list = new ArrayList<UserCoupon>();
		UserCoupon uc = null;
		for (Integer couponid : couponids) {
			uc = new UserCoupon();
			uc.setIsuser(false);
			uc.setUsetime(null);
			uc.setUserdesc(null);
			uc.setCouponid(couponid);
			uc.setUserid(userid);
			list.add(uc);
		}
		return userCouponMapper.cancelUseList(list);
	}

	public UserCoupon getUCByID(Integer id) throws Exception {
		return userCouponMapper.selectByPrimaryKey(id);
	}

	/**
	 * 用户批量领用优惠卷
	 */
	public int addUserCouponList(List<Integer> couponids, Integer userid)
			throws Exception {
		Integer returns = 0;
		UserCoupon uc = null;
		for (Integer i : couponids) {
			Coupon coupon = couponMapper.selectByPrimaryKey(i);
			if (coupon.getConponcount() >= 1) {
				Date dt = coupon.getEndtime();
				if (coupon.getEndday() > 0) {
					dt = DateUtil.addDays(coupon.getEndtime(),
							coupon.getEndday());
				}
				uc = new UserCoupon();
				uc.setFromtype(coupon.getFromtype());

				uc.setCouponid(i);
				uc.setUserid(userid);
				uc.setIsuser(false);
				uc.setGettime(new Date());
				uc.setIsdel(false);
				uc.setOuttime(dt);
				// 添加用户关联优惠卷表信息
				returns = userCouponMapper.insert(uc);
				// 减少优惠卷数量
				coupon.setConponcount(coupon.getConponcount() - 1);
				returns = couponMapper.updateCount(coupon);

			} else {
				logger.error(String.format("优惠券领取张数超过剩余数量！"));
				return returns;
			}
		}
		return returns;
	}

	// / <summary>
	// / 根据店铺ID判断该店铺下是否有优惠卷
	// / </summary>
	// / <param name="shopid">店铺ID</param>
	// / <returns></returns>
	public Boolean IsExistByShopID(Integer shopid) throws Exception {
		Boolean has = true;
		List<Coupon> coupons = couponMapper.getAvailableCoupon(shopid);
		if (coupons == null || coupons.size() == 0) {
			has = false;
		}
		return has;
	}

	@Override
	public List<Coupon> getListByIds(List<Integer> ids) throws Exception {
		return couponMapper.getListByIds(ids);
	}

	/**
	 * 获取会员列表
	 */
	public PageBean getMenberListPage(Criteria criteria, Integer page,
			Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<UserInfoDto> list = couponMapperCustom
				.getMenberListPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	/**
	 * 系统优惠卷批量添加领取用户
	 */
	public int addUCouponList(List<Integer> userids, Integer couponid)
			throws Exception {
		Integer returns = 0;
		Coupon coupon = couponMapper.selectByPrimaryKey(couponid);
		Date dt = coupon.getEndtime();
		if (coupon.getConponcount() >= userids.size()) {
			if (coupon.getEndday() > 0) {
				dt = DateUtil.addDays(coupon.getEndtime(), coupon.getEndday());
			}
		} else {
			logger.error(String.format("优惠券领取张数超过剩余数量！"));
			return returns;
		}
		List<UserCoupon> ucList = new ArrayList<UserCoupon>();
		UserCoupon uc = null;
		for (Integer i : userids) {

			uc = new UserCoupon();
			uc.setFromtype(coupon.getFromtype());
			uc.setCouponid(couponid);
			uc.setUserid(i);
			uc.setIsuser(false);
			uc.setGettime(new Date());
			uc.setIsdel(false);
			uc.setOuttime(dt);
			ucList.add(uc);
		}
		// 添加用户关联优惠卷表信息
		returns = userCouponMapper.insertList(ucList);
		// 减少优惠卷数量
		coupon.setConponcount(coupon.getConponcount() - userids.size());
		returns = couponMapper.updateCount(coupon);
		return returns;
	}

	/**
	 * 前台商家首页获取可领取的优惠卷
	 */
	public List<Coupon> getShopAvailableCoupon(Integer shopid) throws Exception {
		List<Coupon> cList = couponMapper.getShopAvailableCoupon(shopid);
		if (cList != null) {
			return cList;
		} else {
			cList = new ArrayList<Coupon>();
		}
		return cList;
	}

	/**
	 * 根据优惠卷ID获取领取用户列表
	 */
	public PageBean getByCouponIDPage(Criteria criteria, Integer page,
			Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<V_CouponDto> list = couponMapperCustom
				.getByCouponIDPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	/**
	 * api获取可领取优惠卷列表
	 */
	public PageBean getUserCouponList(Criteria criteria, Integer page,
			Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<AvailableCouponDto> list = couponMapper
				.getAvailableCouponByPage(pageBeanUtil);
		pageBean.setBeanList(list);

		return pageBean;
	}

	/**
	 * 结算页获取优惠卷信息
	 */
	public List<CouponDto> getOrderCoupon(List<SearchCouponDto> dtos,
			Integer userid, Integer useplatform) throws Exception {
		List<CouponDto> list = new ArrayList<CouponDto>();
		List<CouponDto> couponlist = couponMapperCustom.getApiCoupon(userid,
				useplatform);

		// 获取总金额
		float totalm = 0.0f;
		if (dtos != null && dtos.size() > 0) {
			totalm = (float) dtos.stream()
					.mapToDouble(SearchCouponDto::getMoney).sum();
		}
		final float totalmoney = totalm;

		List<CouponDto> listcopy = new ArrayList<CouponDto>();
		if (couponlist != null && couponlist.size() > 0) {
			// 获取通用优惠劵
			listcopy = couponlist
					.stream()
					.filter(x -> x.getCouponissuetype().equals(CouponIssueTypeEnum.平台.getValue())
							&& x.getCouponusetype().equals(CouponUseTypeEnum.通用.getValue())
							&& (x.getCoupontype().equals(CouponTypeEnum.通用.getValue()) 
							|| (x.getCoupontype().equals(CouponTypeEnum.金额限制.getValue())
							&& (x.getFullreductionvalue()==null?0:x.getFullreductionvalue()) <= totalmoney)))
					.collect(Collectors.toList());

			for (CouponDto vc : listcopy) {
				if(vc.getFacevalue()>totalm){
					vc.setActface(Double.valueOf(totalm));
				}else{
					vc.setActface(vc.getFacevalue());
				}
				list.add(vc);
			}
			// 获取平台优惠劵
			for (SearchCouponDto sDto : dtos) {
				listcopy.clear();
				listcopy = couponlist
						.stream()
						.filter(x -> x.getCouponissuetype().equals(CouponIssueTypeEnum.平台.getValue())
								&& x.getShopid().equals(sDto.getShopid())
								&& (x.getCouponusetype().equals(CouponUseTypeEnum.店铺.getValue()) 
								&& (x.getCoupontype().equals(CouponTypeEnum.通用.getValue())
								|| (x.getCoupontype().equals(CouponTypeEnum.金额限制.getValue())
								&& (x.getFullreductionvalue()==null?0:x.getFullreductionvalue()) <= sDto.getMoney()))))
						.collect(Collectors.toList());
				for (CouponDto vc : listcopy) {
					if(vc.getFacevalue()>sDto.getMoney()){
						vc.setActface(Double.valueOf(sDto.getMoney()));
					}else{
						vc.setActface(vc.getFacevalue());
					}
					list.add(vc);
				}
				List<Prolist> listprostr = sDto.getProlist();
				JSONArray json = JSONArray.fromObject(listprostr);
				List<Prolist> listpro = (List<Prolist>) JSONArray.toCollection(
						json, Prolist.class);

				for (Prolist pro : listpro) {
					listcopy.clear();
					listcopy = couponlist
							.stream()
							.filter(x -> x.getCouponissuetype().equals(CouponIssueTypeEnum.平台.getValue())
									&&pro.getProid().equals(x.getUsetypeid()==null?0: x.getUsetypeid())
									&& x.getCouponusetype().equals(CouponUseTypeEnum.商品.getValue())
									&& (x.getCoupontype().equals(CouponTypeEnum.通用.getValue())
									|| (x.getCoupontype().equals(CouponTypeEnum.金额限制.getValue())
									&& (x.getFullreductionvalue()==null?0:x.getFullreductionvalue()) <= pro.getPromoney())))
							.collect(Collectors.toList());
					for (CouponDto vc : listcopy) {
						if(vc.getFacevalue()>pro.getPromoney()){
							vc.setActface(Double.valueOf(pro.getPromoney()));
						}else{
							vc.setActface(vc.getFacevalue());
						}
						list.add(vc);
					}
				}
			}

			// 获取店铺优惠劵
			for (SearchCouponDto sDto : dtos) {
				listcopy.clear();
				listcopy = couponlist
						.stream()
						.filter(x -> x.getCouponissuetype() == CouponIssueTypeEnum.店铺
								.getValue()
								&& x.getShopid().equals(sDto.getShopid())
								&& (x.getCouponusetype() == CouponUseTypeEnum.店铺
										.getValue() && (x.getCoupontype() == CouponTypeEnum.通用
										.getValue() || (x.getCoupontype() == CouponTypeEnum.金额限制
										.getValue() && (x.getFullreductionvalue()==null?0:x.getFullreductionvalue()) <= sDto
										.getMoney()))))
						.collect(Collectors.toList());
				for (CouponDto vc : listcopy) {
					if(vc.getFacevalue()>sDto.getMoney()){
						vc.setActface(Double.valueOf(sDto.getMoney()));
					}else{
						vc.setActface(vc.getFacevalue());
					}
					list.add(vc);
				}
				List<Prolist> listprostr = sDto.getProlist();
				JSONArray json = JSONArray.fromObject(listprostr);
				List<Prolist> listpro = (List<Prolist>) JSONArray.toCollection(
						json, Prolist.class);

				for (Prolist pro : listpro) {
					listcopy.clear();
					listcopy = couponlist
							.stream()
							.filter(x -> ((Integer)CouponIssueTypeEnum.店铺.getValue()).equals(x.getCouponissuetype())
									&& pro.getProid().equals(x.getUsetypeid()==null?0:x.getUsetypeid())
									&& ((Integer)CouponUseTypeEnum.商品.getValue()).equals(x.getCouponusetype())
									&& (((Integer)CouponTypeEnum.通用.getValue()).equals(x.getCoupontype())
									|| (((Integer)CouponTypeEnum.金额限制.getValue()).equals(x.getCoupontype())
									&&(x.getFullreductionvalue()==null?0:x.getFullreductionvalue()) <= pro.getPromoney())))
							.collect(Collectors.toList());
					
					for (CouponDto vc : listcopy) {
						if(vc.getFacevalue()>pro.getPromoney()){
							vc.setActface(Double.valueOf(pro.getPromoney()));
						}else{
							vc.setActface(vc.getFacevalue());
						}
						list.add(vc);
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<Shop> getShopStartWithName(String name) {
		ShopExample example = new ShopExample();
		ShopExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(ShopStatusEnum.Open.getValue());
		if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
			criteria.andNameLike(name);
		}
		List<Shop> list = shopMapper.selectByExample(example);
		if (list != null) {
			if (list.size() > 0) {
				list = list.stream().limit(10).collect(Collectors.toList());
			}
		} else {
			list = new ArrayList<Shop>();
		}

		return list;
	}

	public int insertAssign(Coupon record) throws Exception {
		String code = record.getGroupcode();
		VoucherExample example = new VoucherExample();
		VoucherExample.Criteria criteria = example.createCriteria();
		criteria.andGroupcodeEqualTo(code);
		criteria.andStatusEqualTo(VoucherStatusEnum.未分配.getValue());
		List<Voucher> list = voucherMapper.selectByExample(example);
		List<Integer> idList = new ArrayList<Integer>();
		if (list == null || list.size() < record.getConponcount()) {
			return 0;
		} else {
			list = list.stream().limit(record.getConponcount())
					.collect(Collectors.toList());
			for (Voucher voucher : list) {
				voucher.setStatus(VoucherStatusEnum.已分配.getValue());
				idList.add(voucher.getId());
			}
		}
		CouponExample examplec = new CouponExample();
		CouponExample.Criteria criteriac = examplec.createCriteria();
		criteriac.andCouponusetypeEqualTo(record.getCouponusetype());
		criteriac.andGroupcodeEqualTo(record.getGroupcode());
		criteriac.andGettypeEqualTo(record.getGettype());
		criteriac.andIsdelEqualTo(false);
		if (record.getUsetypeid() != null) {
			criteriac.andUsetypeidEqualTo(record.getUsetypeid());
		}
		List<Coupon> couponList = couponMapper.selectByExample(examplec);
		if (couponList != null && couponList.size() > 0) {
			Coupon couponSel = couponList.get(0);
			couponSel.setConponcount(couponSel.getConponcount()
					+ record.getConponcount());
			couponMapper.updateByPrimaryKey(couponSel);
		} else {
			couponMapper.insert(record);
		}
		if (idList.size() > 0) {
			voucherMapper.updateStatusList(idList);
		}
		return 1;
	}

	@Override
	public List<Spu> getSpuStartWithName(String name, Integer classid)
			throws Exception {
		Spu spu = new Spu();
		spu.setName(name);
		spu.setClassid(classid);
		return spuMapper.getSpuStartWithName(spu);
	}

	@Override
	public List<Coupon> getbyGroupcode(String code) throws Exception {
		return couponMapper.getbyGroupcode(code);
	}

	@Override
	public CouponShopDto getLotteryCoupon() throws Exception {
		return couponMapperCustom.getLotteryCoupon();
	}

	@Override
	public List<CouponShopDto> getLotteryCouponList() throws Exception {
		return couponMapperCustom.getLotteryCouponList();
	}

	@Override
	public int getCount(int userid) throws Exception {

		return couponMapper.selectCount(userid);
	}

	@Override
	public Coupon getByIDandUsesite(int id, int usesite) throws Exception {

		return couponMapper.getByIDandUsesite(id, usesite);
	}

	@Override
	public List<CouponShopCartDto> getShopCouponApi(int shopid, int usesite,
			int userid) throws Exception {
		List<CouponShopCartDto> dtoList = new ArrayList<CouponShopCartDto>();
		List<Coupon> list = couponMapper.getShopCouponApi(shopid, usesite);
		List<UserCoupon> uclist = new ArrayList<UserCoupon>();
		CouponShopCartDto dto = null;
		if (list == null || list.size() == 0) {
			return dtoList;
		}
		for (Coupon coupon : list) {
			dto = new CouponShopCartDto();
			uclist = userCouponMapper.selectCount(coupon.getId(), userid);
			if (uclist == null || uclist.size() == 0) {
				dto.setIsget(false);
			} else {
				if (uclist.size() == coupon.getGetcount()) {
					dto.setIsget(true);
				} else {
					dto.setIsget(false);
				}
			}
			dto.setFacevalue(Double.valueOf(coupon.getFacevalue()));
			dto.setId(coupon.getId());
			dto.setName(coupon.getCouponname());
			dtoList.add(dto);
		}
		return dtoList;
	}

	public PageBean getByUserIDorderby(Criteria criteria, Integer page,
			Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<UserCouponDto> list = couponMapperCustom
				.getByUserIDorderbyPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	/**
	 * pc获取可领取优惠卷列表
	 */
	public PageBean getUserCouponListPC(Criteria criteria, Integer page,
			Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<CouponGetDto> list = couponMapperCustom
				.getAvailableCouponPage(pageBeanUtil);
		CouponGetDto dto = couponMapperCustom.getAllUseCoupon();
		if (list != null && list.size() > 0) {
			if (dto != null) {
				dto.setShopid(9999);
				dto.setShopname("全场通用");
				list.add(dto);
			}
		} else {
			if (dto != null) {
				list = new ArrayList<CouponGetDto>();
				dto.setShopid(9999);
				dto.setShopname("全场通用");
				list.add(dto);
			}
		}

		pageBean.setBeanList(list);
		return pageBean;
	}

	@Override
	public List<Coupon> findByShopId(int shopId) throws Exception {

		return couponMapper.queryByShopId();
	}

	@Override
	public int getWapCouponCount(int userId, int value) throws Exception {
		return couponMapper.queryCouponCount(userId, value);
	}

}

package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.ActivityTypeEnum;
import com.yinlian.Enums.ActivityUseTypeEnum;
import com.yinlian.Enums.ManZengTypeEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.ShopCartProType;
import com.yinlian.Enums.ShopTypeEnum;
import com.yinlian.api.app.dto.ShoppingNewCartDto.ActivityShopCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CookieDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CouponShopCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.GiffullShopCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.PackShopCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.ShopShoppingCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.SkuShopCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.SpuShopCartDto;
import com.yinlian.api.app.dto.V_ShopCart;
import com.yinlian.wssc.web.dto.ActivityDto;
import com.yinlian.wssc.web.dto.FullGiftDto;
import com.yinlian.wssc.web.dto.PackageDto;
import com.yinlian.wssc.web.dto.PackageSkuDto;
import com.yinlian.wssc.web.dto.SpSpuDto;
import com.yinlian.wssc.web.mapper.ActivityMarketMapper;
import com.yinlian.wssc.web.mapper.CommentMapper;
import com.yinlian.wssc.web.mapper.CouponMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.ShopcartMapper;
import com.yinlian.wssc.web.mapper.ShopcartprosMapper;
import com.yinlian.wssc.web.mapper.SkuMapper;
import com.yinlian.wssc.web.mapper.SpikeactivityMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.mapper.VSpecsinfoMapper;
import com.yinlian.wssc.web.po.ActivityMarket;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Shopcart;
import com.yinlian.wssc.web.po.Shopcartpros;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.VSpecsinfo;
import com.yinlian.wssc.web.service.ActivityService;
import com.yinlian.wssc.web.service.CartService;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.PackageService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Component("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private ShopcartMapper shopcartMapper;
	@Autowired
	private ShopcartprosMapper shopcartprosMapper;
	@Autowired
	private SkuMapper skuMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private ActivityMarketMapper activityMarketMapper;
	@Autowired
	private ActivityService activityService;

	@Autowired
	private CouponService couponService;
	@Autowired
	private SpikeactivityMapper spikeactivityMapper;
	@Autowired
	private PackageService packageService;

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private VSpecsinfoMapper vSpecsinfoMapper;
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private SpuMapper spuMapper;
	
	@Autowired
	private SpuService   spuService;

	/**
	 * 添加购物车
	 * 
	 * @param vc
	 * @return
	 * @throws Exception
	 */
	public int addCart(V_ShopCart vc) throws Exception {
		Shopcart shopcart = new Shopcart();
		Shopcartpros cartpros = new Shopcartpros();
		Shop shop = new Shop();
		shop = shopMapper.selectByPrimaryKey(vc.getShopID());
		int cartid = 0;
		cartpros = shopcartprosMapper.getByuserIDAndProID(vc.getUserID(),
				vc.getProID(), vc.getType(), vc.getUseTime());
		double cartprice=0.0d;
		double addprice=0.0d;
		if (cartpros != null){
			cartprice=cartpros.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			//获取商品价格
			Spu spu=spuService.queryById(vc.getSpuID());
			addprice=0.0d;
			Integer year=DateUtil.getYear(vc.getUseTime());
			Integer month=DateUtil.getMonth(vc.getUseTime());
			Integer day=DateUtil.getDay(vc.getUseTime());
			
			SkuShowtime skutime=spuService.getSkuTime(vc.getSpuID(), year, month, day);
			if(skutime!=null){
				 addprice=skutime.getPrice().doubleValue();
			}else{
				addprice=spu.getPrice().doubleValue();
			}
			if(spu.getIstoday()!=null && spu.getIstoday()==1){
			     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 long days=(sdf.parse(sdf.format(new Date())).getTime()-vc.getUseTime().getTime())/(1000*3600*24);
				 if(days==0){
						addprice=spu.getPrice().doubleValue();
				 }
			}
			addprice=new BigDecimal(addprice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} 
		if (cartpros != null && cartpros.getId() > 0 && addprice==cartprice) {
			//cartpros.setProcount(cartpros.getProcount() + vc.getProCount());
			//cartpros.setSpikeid(vc.getSpikeID());
			//return shopcartprosMapper.updateByPrimaryKey(cartpros);
			Integer count=cartpros.getProcount() + vc.getProCount();
			return  shopcartprosMapper.updateCount(count, cartpros.getId());
		} else {
			shopcart = shopcartMapper.getByUserAndShop(vc.getUserID(),
					vc.getShopID());
			if (shopcart != null && shopcart.getId() > 0) {
				cartid = shopcart.getId();
			} else {
				if (shopcart == null)
					shopcart = new Shopcart();
				shopcart.setShopid(vc.getShopID());
				shopcart.setUserid(vc.getUserID());
				shopcart.setShopname(shop.getName());
				shopcart.setActivityid(vc.getActivityID());
				// 添加购物车主表 用户信息
				shopcartMapper.insertCart(shopcart);
				cartid = shopcart.getId();
			}
			if (cartpros != null)
				cartpros = new Shopcartpros();
			if (cartpros == null)
				cartpros = new Shopcartpros();
			cartpros.setIsselected(1);
			cartpros.setProcount(vc.getProCount());
			cartpros.setProid(vc.getProID());
			cartpros.setProname(vc.getProName());
			cartpros.setShopcartid(cartid);
			cartpros.setSpuid(vc.getSpuID());
			//cartpros.setType(vc.getType());
			cartpros.setUserid(vc.getUserID());
			cartpros.setShopid(vc.getShopID());
			cartpros.setUsetime(vc.getUseTime());
			//cartpros.setSpikeid(vc.getSpikeID());
			//获取商品价格
			Spu spu=spuService.queryById(vc.getSpuID());
			addprice=0.0d;
			Integer year=DateUtil.getYear(vc.getUseTime());
			Integer month=DateUtil.getMonth(vc.getUseTime());
			Integer day=DateUtil.getDay(vc.getUseTime());
			
			SkuShowtime skutime=spuService.getSkuTime(vc.getSpuID(), year, month, day);
			if(skutime!=null){
				 addprice=skutime.getPrice().doubleValue();
			}else{
				addprice=spu.getPrice().doubleValue();
			}
			if(spu.getIstoday()!=null && spu.getIstoday()==1){
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 long days=(sdf.parse(sdf.format(new Date())).getTime()-vc.getUseTime().getTime())/(1000*3600*24);
				 if(days==0){
						addprice=spu.getPrice().doubleValue();
				 }
			}
			//addprice=new BigDecimal(addprice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			cartpros.setPrice(new BigDecimal(addprice));
			// 添加购物车子表 商品信息
			return shopcartprosMapper.insert(cartpros);
		}

	}

	/**
	 * 清空购物车
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int deleteByUser(int userid) throws Exception {
		shopcartprosMapper.deleteByUser(userid);
		return shopcartMapper.deleteByUser(userid);
	}
	
	/**
	 * 删除购物车某个商品
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteByID(int id) throws Exception {
		return shopcartprosMapper.deleteByPrimaryKey(id);
	}

	public int deleteByIds(List<Integer> idlist) throws Exception {
		return shopcartprosMapper.deleteByIds(idlist);
	}

	public int updateCount(int count, int id) throws Exception {
		return shopcartprosMapper.updateCount(count, id);
	}

	public int getCountByUser(int userid) throws Exception {
		Integer count = shopcartprosMapper.countByuser(userid);
		if (count == null)
			count = 0;
		return count;
	}

	/**
	 * 获取购物车商品数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public CartDto getByUser(int userid, int sites) throws Exception {
		CartDto dto = new CartDto();
		List<V_ShopCart> cartList = new ArrayList<V_ShopCart>();
		cartList = shopcartMapper.getV_ShopCartByUser(userid);
		if (cartList == null || cartList.size() == 0)
			return dto;
		List<Integer> spuidlList = new ArrayList<Integer>();
		List<Integer> shopidlList = new ArrayList<Integer>();
		List<Integer> shopids = cartList.stream().map(V_ShopCart::getShopID).collect(Collectors.toList());
		List<Shop> sList = shopMapper.getByIds(shopids);
		for (V_ShopCart cp : cartList) {
			spuidlList.add(cp.getSpuID());
			shopidlList.add(cp.getShopID());
			if(sList != null && !sList.isEmpty()){
				for(Shop shop : sList){
					if((cp.getShopID()+"").equals(shop.getId()+"")){
						cp.setShoptype(shop.getShoptype());
					}
				}
			}
		}
		// 获取可参与的活动信息
		List<ActivityMarket> actList = new ArrayList<ActivityMarket>();
		List<ActivityMarket> actspuList = activityMarketMapper
				.getAvailableActivityBySpu(spuidlList, sites);
		if (actspuList != null) {
			for (ActivityMarket spuact : actspuList) {
				actList.add(spuact);
			}
		}

		List<ActivityMarket> actshopList = activityMarketMapper
				.getAvailableActivityByShop(shopidlList, sites);
		if (actshopList != null) {
			for (ActivityMarket shopact : actshopList) {
				actList.add(shopact);
			}
		}

		return getDto(dto, cartList, actList, userid, sites);
	}

	/**
	 * 获取购物车结算商品数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public CartDto getBuyCart(int userid, int sites) throws Exception {
		CartDto dto = new CartDto();
		List<V_ShopCart> cartList = new ArrayList<V_ShopCart>();
		cartList = shopcartMapper.getBuyCartByUser(userid);
		if (cartList == null || cartList.size() == 0)
			return dto;
		List<Integer> spuidlList = new ArrayList<Integer>();
		List<Integer> shopidlList = new ArrayList<Integer>();
		for (V_ShopCart cp : cartList) {
			spuidlList.add(cp.getSpuID());
			shopidlList.add(cp.getShopID());
		}
		
		// 获取可参与的活动信息
		List<ActivityMarket> actList = new ArrayList<ActivityMarket>();
		List<ActivityMarket> actspuList = activityMarketMapper
				.getAvailableActivityBySpu(spuidlList, sites);
		if (actspuList != null) {
			for (ActivityMarket spuact : actspuList) {
				actList.add(spuact);
			}
		}

		List<ActivityMarket> actshopList = activityMarketMapper
				.getAvailableActivityByShop(shopidlList, sites);
		if (actshopList != null) {
			for (ActivityMarket shopact : actshopList) {
				actList.add(shopact);
			}
		}

		return getDto(dto, cartList, actList, userid, sites);
	}

	public CartDto getByCookie(List<V_ShopCart> cartlist, int sites, int userid)
			throws Exception {

		CartDto dto = new CartDto();
		// 获取可参与的活动信息
		List<ActivityMarket> actList = new ArrayList<ActivityMarket>();

		if (cartlist != null && cartlist.size() > 0) {
			List<Integer> spuidlList = new ArrayList<Integer>();
			List<Integer> shopidlList = new ArrayList<Integer>();
			for (V_ShopCart cp : cartlist) {
				spuidlList.add(cp.getSpuID());
				shopidlList.add(cp.getShopID());
			}
			List<ActivityMarket> actspuList = activityMarketMapper
					.getAvailableActivityBySpu(spuidlList, sites);
			if (actspuList != null) {
				for (ActivityMarket spuact : actspuList) {
					actList.add(spuact);
				}
			}

			List<ActivityMarket> actshopList = activityMarketMapper
					.getAvailableActivityByShop(shopidlList, sites);
			if (actshopList != null) {
				for (ActivityMarket shopact : actshopList) {
					actList.add(shopact);
				}
			}
			return getDto(dto, cartlist, actList, userid, sites);
		} else {
			return dto;
		}
	}

	/**
	 * 获取购物车数据
	 * 
	 * @param dto
	 * @param cartList
	 * @param actList
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	private CartDto getDto(CartDto dto, List<V_ShopCart> cartList,
			List<ActivityMarket> actList, int userid, int sites)
			throws Exception {

		// 将购物车数据加载到CookieDto中
		List<CookieDto> list = new ArrayList<CookieDto>();
		CookieDto cDto;
		for (V_ShopCart vcs : cartList) {
			cDto = new CookieDto();
			cDto.setShopcartproid(vcs.getID());
			cDto.setGoodsID(vcs.getProID());
			cDto.setGoodsCount(vcs.getProCount());
			cDto.setShopId(vcs.getShopID());
			cDto.setShopActId(vcs.getActivityID() == null ? 0 : vcs
					.getActivityID());
			cDto.setSpuId(vcs.getSpuID() == null ? 0 : vcs.getSpuID());
			cDto.setCartType(vcs.getType());
			cDto.setSpikeID(vcs.getSpikeID() == null ? 0 : vcs.getSpikeID());
			cDto.setSpuActId(vcs.getMarketID() == null ? 0 : vcs.getMarketID());
			cDto.setIsSelected(vcs.getIsSelected() == null || vcs.getIsSelected() == 0 ? false : true);
			cDto.setUsetime(vcs.getUseTime());
			cDto.setShoptype(vcs.getShoptype());
			cDto.setPrice(vcs.getPrice());
			list.add(cDto);
		}
		// 加载购物车店铺信息
		ShopShoppingCartDto shopscd = null;
		// 加载购物车店铺下商品信息
		SpuShopCartDto spuscd = null;
		CouponShopCartDto cscd = null;
		List<Integer> skuidlist = new ArrayList<Integer>();
		List<Integer> shopidList = new ArrayList<Integer>();
//		Integer first = 16;
//		Integer second = 17;
//		Integer third = 15;
//		Integer third_second = 30;
//		Integer hours = 0;
//		Integer minute = 0;

		for (CookieDto cookDto : list) {
			// 获取购物车中非组合商品的skuid集合
			if (cookDto.getCartType() != ShopCartProType.组合商品.getValue())
				skuidlist.add(cookDto.getGoodsID());
			// 获取购物车店铺ID集合
			if (!shopidList.contains(cookDto.getShopId())) {
				shopidList.add(cookDto.getShopId());
			}
		}
		// 获取库存商品和店铺信息
		List<Sku> skuList = new ArrayList<Sku>();
		if (skuidlist.size() > 0) {
			skuList = skuMapper.getListByIds(skuidlist);
		}
		List<Shop> shopList = shopMapper.getByIds(shopidList);
		// 获取购物车页的规格信息
		List<VSpecsinfo> specslist = new ArrayList<VSpecsinfo>();
		if (skuidlist.size() > 0)
			specslist = vSpecsinfoMapper.getcartspecslist(skuidlist);
		if (specslist == null)
			specslist = new ArrayList<VSpecsinfo>();
		// 获取满赠活动集合
		List<Integer> giftAct = new ArrayList<Integer>();
		for (ActivityMarket aMarket : actList.stream()
				.filter(x -> x.getActtype() == ActivityTypeEnum.满赠.getValue())
				.collect(Collectors.toList())) {
			giftAct.add(aMarket.getId());
		}
		List<ActivityDto> fullgift = new ArrayList<ActivityDto>();
		if (giftAct.size() > 0) {
			fullgift = activityService.getActivityDtoByIDs(giftAct);
		}

		dto.setMoney(0.0d);
		dto.setDelmoney(0.0d);
		dto.setCount(0);
		dto.setSelectedcount(0);
		dto.setIsselected(true);
		// 根据shopid分组
		Map<Integer, List<CookieDto>> shopGroupList = list.stream().collect(
				Collectors.groupingBy(CookieDto::getShopId));
		// 遍历购物车下店铺
		for (Map.Entry<Integer, List<CookieDto>> entry : shopGroupList
				.entrySet()) {
			Integer key = entry.getKey();
			List<CookieDto> value = entry.getValue();
			shopscd = new ShopShoppingCartDto();
			shopscd.setIsselected(true);
			shopscd.setTotalmoney(0.0d);
			shopscd.setDelmoney(0.0d);
			if (key <= 0)
				continue;
			List<ActivityMarket> marketlist = null;
			shopscd.setShopid(key);
			Optional<CookieDto> shopOp = value.stream()
					.filter(x -> x.getShopId().equals(key)).findFirst();
			Optional<Shop> shop = shopList.stream()
					.filter(x -> x.getId().equals(key)).findFirst();
			if (!shop.isPresent())
				continue;
			shopscd.setShopname(shop.get().getName());

			 shopscd.setIscoupon(couponService.IsExistByShopID(key));
			// 优惠卷处理 暂时不做
			if (userid > 0) {
				// 处理优惠卷信息
				List<CouponShopCartDto> conpons = couponService
						.getShopCouponApi(shop.get().getId(), sites, userid);
				for (CouponShopCartDto con : conpons) {
					cscd = new CouponShopCartDto();
					BeanUtils.copyProperties(cscd, con);
					shopscd.csd.add(cscd);
				}
			}
			double shoptmoney = 0.0f, DelMoney = 0.0f;
			int shopCount = 0;
			// 根据spuid分组
			Map<Integer, List<CookieDto>> spuGroupList = value.stream()
					.collect(Collectors.groupingBy(CookieDto::getSpuId));
			// 处理spu信息
			for (Map.Entry<Integer, List<CookieDto>> entryspu : spuGroupList
					.entrySet()) {
				double price = 0.0d;
				int Stock = 0;
				Optional<CookieDto> spu = entryspu.getValue().stream()
						.filter(x -> x.getSpuId().equals(entryspu.getKey()))
						.findFirst();

				ShopCartProType cType = ShopCartProType.valueOf(spu.get()
						.getCartType());
				switch (cType) {
				case 组合商品:
					if (!spu.get().isIsSelected()) {
						dto.setIsselected(false);
						shopscd.setIsselected(false);
					}
					// 加载组合商品信息
					GetPackageInfo(spu.get().getShopcartproid(), spu.get()
							.getGoodsID(), dto, spu.get().getGoodsCount(), spu
							.get().isIsSelected(), shopscd, specslist,spu.get().getSpuId());
					if (shopscd.getTotalmoney() != null) {
						shoptmoney += shopscd.getTotalmoney();
					}
					continue;
				default:
					break;
				}
				spuscd = new SpuShopCartDto();
				spuscd.setId(spu.get().getSpuId());
				Spu spupo = spuMapper.selectByPrimaryKey(spu.get().getSpuId());
				spuscd.setIsPostage(spupo.getIspostage());
				double sputmoney = 0.0d;
				int spuCount = 0;
				for (CookieDto skuId : entryspu.getValue()) {
					Optional<Sku> skuOp = skuList.stream()
							.filter(x -> x.getId().equals(skuId.getGoodsID()))
							.findFirst();
					if (!skuOp.isPresent())
						continue;
					SkuShopCartDto skscd = new SkuShopCartDto();
					skscd.setShopcartproid(skuId.getShopcartproid());
					skscd.setIsselected(skuId.isIsSelected());
					skscd.setName(skuOp.get().getName());
					skscd.setImg(StringUtilsEX.IsNullOrWhiteSpace(skuOp.get().getImgurl()) ? spupo.getImgurl() : skuOp.get().getImgurl());
					skscd.setImgurlApp(StringUtilsEX.IsNullOrWhiteSpace(skuOp.get().getImgurlApp()) ? spupo.getImgurlApp() : skuOp.get().getImgurlApp());
					//门票使用时间
					skscd.setUsetime(DateUtil.datePattren(skuId.getUsetime()));
					//门票是否有效
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if(skuId.getUsetime().before(sdf.parse(sdf.format(date)))){
						skscd.setIsvalid(1);
						skscd.setIsselected(false);
						skscd.setValiddesc("商品使用时间已过期，请重新购买");
					}
					if(spupo.getIstoday()==null || spupo.getIstoday()!=1){
						String newdate=DateUtil.dateConvert(new Date());
						Date  date2=StringUtilsEX.ToShortDate(newdate);
						long days=(date2.getTime()-skuId.getUsetime().getTime())/(1000*3600*24);
						 if(days==0){
							 skscd.setIsvalid(1);
							 skscd.setIsselected(false);
							 skscd.setValiddesc("此商品暂无法售卖当天票");
						 }
					}
					 
					Integer year=DateUtil.getYear(skuId.getUsetime());
					Integer month=DateUtil.getMonth(skuId.getUsetime());
					Integer day=DateUtil.getDay(skuId.getUsetime());
					BigDecimal newprice=new BigDecimal(0);
					
					SkuShowtime skutime=spuService.getSkuTime(spu.get().getSpuId(), year, month, day);
					if(skutime!=null){
						newprice=skutime.getPrice();
					}else{
						newprice=spupo.getPrice();
					}
					
					//当天票价
					if(spupo.getIstoday()!=null && spupo.getIstoday()==1){
						//当天票过期
						if(DateUtil.IsOutDate(skuId.getUsetime())){
							skscd.setIsvalid(1);
							skscd.setIsselected(false);
							skscd.setValiddesc("当天票购买时间已过，暂时无法售卖");
						}
						 long days=(sdf.parse(sdf.format(new Date())).getTime()-skuId.getUsetime().getTime())/(1000*3600*24);
						 if(days==0){
							 newprice=spupo.getPrice();
							 skutime.setPrice(spupo.getPrice());
						 }
					}
					
					if(skuId.getPrice()==null){
						skscd.setIsvalid(1);
						skscd.setIsselected(false);
						skscd.setValiddesc("商品价格已失效，请重新购买");
					}else {
						if(skuId.getPrice().compareTo(newprice)==1 || skuId.getPrice().compareTo(newprice)==-1){
							skscd.setIsvalid(1);
							skscd.setIsselected(false);
							skscd.setValiddesc("商品价格已失效，请重新购买");
						}
					}
					
					if(skuId.getUsetime().before(sdf.parse(sdf.format(date)))){
						skscd.setIsvalid(1);
						skscd.setIsselected(false);
						skscd.setValiddesc("商品使用时间已过期，请重新购买");
					}
					/*if(shopOp.get().getShoptype() == ShopTypeEnum.门票.getValue()){
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(sdf.format(date).equals(sdf.format(skuId.getUsetime()))){//当天票
							hours = Integer.parseInt(sdf1.format(date).substring(11, 13));
							minute = Integer.parseInt(sdf1.format(date).substring(14, 16));
							Integer month = DateUtil.getMonth(date);
							switch (month) {
							case 3:
							case 4:
							case 5:
							case 6:
							case 9:
							case 10:
							case 11:
								if(hours > first){
									skscd.setIsvalid(1);
									skscd.setIsselected(false);
								}
								break;
							case 7:
							case 8:
								if(hours > second){
									skscd.setIsvalid(1);
									skscd.setIsselected(false);
								}
								break;
							case 1:
							case 2:
							case 12:
								if(hours > third){
									skscd.setIsvalid(1);
									skscd.setIsselected(false);
								}else if(hours == third){
									if(minute > third_second){
										skscd.setIsvalid(1);
										skscd.setIsselected(false);
									}
								}
								break;
							default:
								break;
							}
						}
					}*/
					ShopCartProType cSkuType = ShopCartProType.valueOf(skuId
							.getCartType());
					switch (cSkuType) {
					case 秒杀:
					case 闪购:
						// 加载秒杀活动商品信息
						if (skuId.getSpikeID() <= 0) {
							break;
						}
						SpSpuDto ssDto = spikeactivityMapper
								.getSpikeBySpikeIDAndSpuID(skuId.getSpikeID(),
										skuId.getSpuId());
						if (ssDto != null && ssDto.getId() > 0) {
							price = (float) ssDto.getDiscountPrice();
							Stock = ssDto.getSpuCount();
							skscd.setProtype(skuId.getCartType());
							skscd.setSpikeid(skuId.getSpikeID());
						} else {
							skscd.setProtype(ShopCartProType.普通商品.getValue());
						}
						break;
					default:
						Shop shop2 = shopMapper.selectByPrimaryKey(key);
						skscd.setProtype(shop2.getShoptype());
						break;
					}
					// 判断商品是否有优惠
					// if (price > 0) {
					// skuOp.get().setAppprice(price);
					// skuOp.get().setStock(Stock);
					// }

					if (price > 0) {
						skuOp.get().setPrice(BigDecimal.valueOf(price));
						skuOp.get().setStock(Stock);
					}
				//	skscd.setPrice(skuOp.get().getPrice().doubleValue());
					if(skutime!=null){
						skscd.setPrice(skutime.getPrice().doubleValue());
					}else{
						newprice=spupo.getPrice();
					}

					skscd.setCount(skuId.getGoodsCount());
					// 处理商品选中信息
					dto.setCount(dto.getCount() + skscd.getCount());
					if (skscd.getIsselected() == true) {
						shoptmoney += skscd.getPrice() * skscd.getCount();
						shopCount += skscd.getCount();
						sputmoney += skscd.getPrice() * skscd.getCount();
						spuCount += skscd.getCount();

						dto.setSelectedcount(dto.getSelectedcount()
								+ skscd.getCount());
						dto.setMoney(dto.getMoney() + skscd.getPrice()
								* skscd.getCount());
//						dto.setIsselected(true);
//						shopscd.setIsselected(true);
					} else {
						dto.setIsselected(false);
						shopscd.setIsselected(false);
					}
					/*skscd.setStock(skuOp.get().getStock() != null ? skuOp.get()
							.getStock() : 0);*/
					
					if(skutime!=null){
						skscd.setPrice(skutime.getPrice().doubleValue());
						skscd.setStock(skutime.getStock());
					}else{
						skscd.setStock(0);
					}
					if(skscd.getIsvalid()==1){
						skscd.setPrice(skuId.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
					skscd.setId(skuOp.get().getId());
					// 获取规格信息
					skscd.setSpecsinfo(getSpecsInfo(specslist, skuOp.get()
							.getId()));
					spuscd.skuscd.add(skscd);
				}
				// 筛选针对商品的活动信息
				marketlist = actList
						.stream()
						.filter(x -> x.getSpuid() !=null && x.getSpuid().equals(entryspu.getKey())
								&& x.getUsetype() == ActivityUseTypeEnum.针对商品
										.getValue()).distinct()
						.collect(Collectors.toList());
				DelMoney = CartMarket(marketlist, fullgift, spuscd.asd, spu
						.get().getSpuActId(), false, dto, sputmoney, spuCount,
						specslist);
				spuscd.setTotalmoney(new BigDecimal(sputmoney).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue());
				spuscd.setDelmoney(new BigDecimal(DelMoney).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue());
				shopscd.spuscd.add(spuscd);
			}
			// 筛选针对店铺的活动信息
			marketlist = actList.stream()
					.filter(x -> x.getShopid() !=null && x.getShopid().equals(key)
							&& x.getUsetype() == ActivityUseTypeEnum.针对金额
									.getValue()).distinct()
					.collect(Collectors.toList());
			DelMoney = CartMarket(marketlist, fullgift, shopscd.asd, shopOp
					.get().getShopActId(), true, dto, shoptmoney, shopCount,
					specslist);
			shopscd.setTotalmoney(new BigDecimal(shoptmoney).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
			shopscd.setDelmoney(new BigDecimal(DelMoney).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
			dto.shops.add(shopscd);
		}
		dto.setAmountpayable(new BigDecimal(dto.getMoney() - dto.getDelmoney()).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue());
		return dto;
	}

	/**
	 * 获取购物车商品数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public CartDto getByCookieCart(List<CookieDto> cartlist, int sites)throws Exception {

		CartDto dto = new CartDto();
		if (cartlist != null && cartlist.size() > 0) {
			// 获取可参与的活动信息
			List<ActivityMarket> actList = new ArrayList<ActivityMarket>();
			List<Integer> spuidlList = new ArrayList<Integer>();
			List<Integer> shopidlList = new ArrayList<Integer>();
			
			for (CookieDto cdto : cartlist) {
				shopidlList.add(cdto.getShopId());
				spuidlList.add(cdto.getSpuId());
			}
			
			
			List<ActivityMarket> actspuList=new ArrayList<ActivityMarket>();
			if(spuidlList.size()>0){
				 actspuList = activityMarketMapper
							.getAvailableActivityBySpu(spuidlList, sites);
			}			
			if (actspuList != null) {
				for (ActivityMarket spuact : actspuList) {
					actList.add(spuact);
				}
			}

			List<ActivityMarket> actshopList = activityMarketMapper
					.getAvailableActivityByShop(shopidlList, sites);
			if (actshopList != null) {
				for (ActivityMarket shopact : actshopList) {
					actList.add(shopact);
				}
			}

			return getByCookieDto(dto, cartlist, actList);
		} else {
			return dto;
		}
	}

	/**
	 * 根据cookie获取购物车数据
	 * 
	 * @param dto
	 * @param cookieList
	 * @param actList
	 * @return CartDto
	 * @throws Exception
	 */
	private CartDto getByCookieDto(CartDto dto, List<CookieDto> cookieList,
			List<ActivityMarket> actList) throws Exception {

		// 加载购物车店铺信息
		ShopShoppingCartDto shopscd = null;
		// 加载购物车店铺下商品信息
		SpuShopCartDto spuscd = null;
		List<Integer> skuidlist = new ArrayList<Integer>();
		List<Integer> shopidList = new ArrayList<Integer>();

		for (CookieDto cookDto : cookieList) {
			// 获取购物车中非组合商品的skuid集合
			if (cookDto.getCartType() != ShopCartProType.组合商品.getValue())
				skuidlist.add(cookDto.getGoodsID());
			// 获取购物车店铺ID集合
			if (!shopidList.contains(cookDto.getShopId())) {
				shopidList.add(cookDto.getShopId());
			}
		}
		// 获取库存商品和店铺信息
		List<Sku> skuList = new ArrayList<Sku>();
		if (skuidlist.size() > 0) {
			skuList = skuMapper.getListByIds(skuidlist);
		}
		List<Shop> shopList = shopMapper.getByIds(shopidList);
		// 获取购物车页的规格信息
		List<VSpecsinfo> specslist = new ArrayList<VSpecsinfo>();
		if (skuidlist.size() > 0)
			specslist = vSpecsinfoMapper.getcartspecslist(skuidlist);
		if (specslist == null)
			specslist = new ArrayList<VSpecsinfo>();
		// 获取满赠活动集合
		List<Integer> giftAct = new ArrayList<Integer>();
		for (ActivityMarket aMarket : actList.stream()
				.filter(x -> x.getActtype() == ActivityTypeEnum.满赠.getValue())
				.collect(Collectors.toList())) {
			giftAct.add(aMarket.getId());
		}
		List<ActivityDto> fullgift = new ArrayList<ActivityDto>();
		if (giftAct.size() > 0) {
			fullgift = activityService.getActivityDtoByIDs(giftAct);
		}

		dto.setMoney(0.0d);
		dto.setDelmoney(0.0d);
		dto.setCount(0);
		dto.setSelectedcount(0);
		dto.setIsselected(true);

		int shopCount = 0;
		// 根据shopid分组
		Map<Integer, List<CookieDto>> shopGroupList = cookieList.stream()
				.collect(Collectors.groupingBy(CookieDto::getShopId));
		// 遍历购物车下店铺
		for (Map.Entry<Integer, List<CookieDto>> entry : shopGroupList
				.entrySet()) {
			Integer key = entry.getKey();
			List<CookieDto> value = entry.getValue();
			shopscd = new ShopShoppingCartDto();
			shopscd.setIsselected(true);
			shopscd.setTotalmoney(0.0d);
			shopscd.setDelmoney(0.0d);
			if (key <= 0)
				continue;
			List<ActivityMarket> marketlist = null;
			shopscd.setShopid(key);
			Optional<CookieDto> shopOp = value.stream()
					.filter(x -> x.getShopId().equals(key)).findFirst();
			Optional<Shop> shop = shopList.stream()
					.filter(x -> x.getId().equals(key)).findFirst();
			if (!shop.isPresent())
				continue;
			shopscd.setShopname(shop.get().getName());

			double shoptmoney = 0.0d, DelMoney = 0.0d;

			// 根据spuid分组
			Map<Integer, List<CookieDto>> spuGroupList = value.stream()
					.collect(Collectors.groupingBy(CookieDto::getSpuId));

			int selectCount=0;
			double selecttotal=0.0d;
			// 处理spu信息
			for (Map.Entry<Integer, List<CookieDto>> entryspu : spuGroupList
					.entrySet()) {
				double price = 0.0d;
				int Stock = 0;
				Optional<CookieDto> spu = entryspu.getValue().stream()
						.filter(x -> x.getSpuId().equals(entryspu.getKey()))
						.findFirst();

				ShopCartProType cType = ShopCartProType.valueOf(spu.get()
						.getCartType());
				switch (cType) {
				case 组合商品:
					if (!spu.get().isIsSelected()) {
						dto.setIsselected(false);
						shopscd.setIsselected(false);
					}
					// 加载组合商品信息
					GetPackageInfo(spu.get().getShopcartproid(), spu.get()
							.getGoodsID(), dto, spu.get().getGoodsCount(), spu
							.get().isIsSelected(), shopscd, specslist,spu.get().getSpuId());
					if (shopscd.getTotalmoney() != null) {
						shoptmoney += shopscd.getTotalmoney();
					}
					continue;
				default:
					break;
				}
				spuscd = new SpuShopCartDto();
				spuscd.setId(spu.get().getSpuId());
				double sputmoney = 0.0d;
				int spuCount = 0;
				for (CookieDto skuId : entryspu.getValue()) {
					Optional<Sku> skuOp = skuList.stream()
							.filter(x -> x.getId().equals(skuId.getGoodsID()))
							.findFirst();
					if (!skuOp.isPresent())
						continue;
					SkuShopCartDto skscd = new SkuShopCartDto();
					skscd.setShopcartproid(skuId.getShopcartproid());
					skscd.setIsselected(skuId.isIsSelected());
					if (skuId.isIsSelected()) {
						selectCount++;
					}
					skscd.setImg(skuOp.get().getImgurlWap());
					skscd.setName(skuOp.get().getName());
					ShopCartProType cSkuType = ShopCartProType.valueOf(skuId
							.getCartType());
					switch (cSkuType) {
					case 秒杀:
					case 闪购:
						// 加载秒杀活动商品信息
						if (skuId.getSpikeID() <= 0)
							break;
						SpSpuDto ssDto = spikeactivityMapper
								.getSpikeBySpikeIDAndSpuID(skuId.getSpikeID(),
										skuId.getSpuId());
						if (ssDto != null && ssDto.getId() > 0) {
							price = (float) ssDto.getDiscountPrice();
							Stock = ssDto.getSpuCount();
							skscd.setProtype(skuId.getCartType());
							skscd.setSpikeid(skuId.getSpikeID());
						} else {
							skscd.setProtype(ShopCartProType.普通商品.getValue());
						}
						break;
					default:
						Shop shop2 = shopMapper.selectByPrimaryKey(key);
						skscd.setProtype(shop2.getShoptype());
						break;
					}
					// 判断商品是否有优惠
					if (price > 0) {
						skuOp.get().setWapprice(BigDecimal.valueOf(price));
						skuOp.get().setStock(Stock);
					}

					skscd.setPrice(skuOp.get().getWapprice() == null ? skuOp
							.get().getPrice().doubleValue() : skuOp.get().getWapprice().doubleValue());
					skscd.setCount(skuId.getGoodsCount());
					// 处理商品选中信息
					dto.setCount(dto.getCount() + skscd.getCount());
					if (skuId.isIsSelected()) {

						shoptmoney += skscd.getPrice() * skscd.getCount();
						shopCount += skscd.getCount();
						sputmoney += skscd.getPrice() * skscd.getCount();
						if(skscd.getIsselected()){
							selecttotal+=skscd.getPrice() * skscd.getCount();
						}
						spuCount += skscd.getCount();

						dto.setSelectedcount(dto.getSelectedcount()
								+ skscd.getCount());
						dto.setMoney(dto.getMoney() + skscd.getPrice()
								* skscd.getCount());

					} else {
						dto.setIsselected(false);
						shopscd.setIsselected(false);
					}
					skscd.setStock(skuOp.get().getStock() != null ? skuOp.get()
							.getStock() : 0);
					skscd.setId(skuOp.get().getId());
					// 获取规格信息
					skscd.setSpecsinfo(getSpecsInfo(specslist, skuOp.get()
							.getId()));
					spuscd.skuscd.add(skscd);
				}
				// 筛选针对商品的活动信息
				marketlist = actList
						.stream()
						.filter(x -> x.getSpuid() !=null && x.getSpuid().equals(entryspu.getKey())
								&& x.getUsetype() == ActivityUseTypeEnum.针对商品
										.getValue()).distinct()
						.collect(Collectors.toList());
				
				DelMoney = CartMarket(marketlist, fullgift, spuscd.asd, spu
						.get().getSpuActId(), false, dto, sputmoney, spuCount,
						specslist);
				spuscd.setTotalmoney(sputmoney);
				spuscd.setDelmoney(DelMoney);
				shopscd.spuscd.add(spuscd);
			}
			// 筛选针对店铺的活动信息
			marketlist = actList
					.stream()
					.filter(x -> x.getShopid() !=null && x.getShopid().equals(key)
							&& x.getUsetype() == ActivityUseTypeEnum.针对金额
									.getValue()).distinct()
					.collect(Collectors.toList());
			DelMoney = CartMarket(marketlist, fullgift, shopscd.asd, shopOp
					.get().getShopActId(), true, dto, shoptmoney, shopCount,
					specslist);
			shopscd.setTotalmoney(shoptmoney);
			shopscd.setDelmoney(DelMoney);
			shopscd.setSelectCount(selectCount);
			shopscd.setSelectTotalMoney(selecttotal);
			dto.shops.add(shopscd);
		}
		dto.setAmountpayable(dto.getMoney() - dto.getDelmoney());
		return dto;
	}

	/**
	 * 获取购物车规格信息
	 * 
	 * @param specslist
	 * @param skuid
	 */
	private String getSpecsInfo(List<VSpecsinfo> specslist, int skuid) {
		String specsinfo = "";
		List<VSpecsinfo> skuspecs = specslist.stream()
				.filter(x -> x.getSkuid() == skuid)
				.collect(Collectors.toList());
		if (skuspecs != null && skuspecs.size() > 0) {
			if (skuspecs.size() > 2)
				skuspecs = specslist.stream().limit(2)
						.collect(Collectors.toList());
			for (VSpecsinfo info : skuspecs) {
				specsinfo += info.getName() + ":" + info.getValue() + ";";
			}
			specsinfo = specsinfo.substring(0, specsinfo.length() - 1);
		} else {
			return specsinfo;
		}

		return specsinfo;
	}

	/**
	 * 获取组合商品信息
	 * 
	 * @param Shopcartproid
	 *            购物车子表ID
	 * @param packId
	 *            组合商品编号
	 * @param dto
	 *            购物车数据
	 * @param GoodsCount
	 *            商品数量
	 * @param IsSelected
	 *            是否选择
	 * @param cartdto
	 *            购物车店铺数据
	 * @param spuid 
	 * @throws Exception
	 */
	private void GetPackageInfo(int Shopcartproid, int packId, CartDto dto,
			int GoodsCount, Boolean IsSelected, ShopShoppingCartDto cartdto,
			List<VSpecsinfo> specslist, Integer spuid) throws Exception {

		PackageDto pack = packageService.GetPackageDtoByID(packId);
		if (pack == null)
			return;
		PackShopCartDto pscd = new PackShopCartDto();
		pscd.setShopcartproid(Shopcartproid);
		pscd.setName(pack.getPackageName());
		pscd.setCount(GoodsCount);
		pscd.setId(packId);
		pscd.setIsselected(IsSelected);
		pscd.setStock(pack.getCount());
		pscd.setPrice(Double.valueOf(pack.getSkuPackPrice()));
		if (pscd.getIsselected() == true) {
			double mon = pscd.getPrice() * pscd.getCount();
			dto.setMoney(dto.getMoney() + mon);
			cartdto.setTotalmoney(cartdto.getTotalmoney() + mon);
		}
		SkuShopCartDto sscd = null;
		int skucount = 0;
		for (PackageSkuDto psDto : pack.getSkus()) {
			sscd = new SkuShopCartDto();
			sscd.setCount(GoodsCount);
			sscd.setId(psDto.getSkuID());
			sscd.setImg(psDto.getImgUrl());
			if (IsSelected)
				dto.setSelectedcount(dto.getSelectedcount() + GoodsCount);
			sscd.setName(psDto.getSkuName());
			sscd.setPrice(Double.valueOf(psDto.getSkuPackPrice()));
			sscd.setSpecsinfo(getSpecsInfo(specslist, psDto.getSkuID())); // 规格信息
			sscd.setIsselected(true);			
			sscd.setProtype(1);
			sscd.setSpuid(psDto.getSpuid());
			pscd.skuscd.add(sscd);
			skucount += GoodsCount;
		}
		dto.setCount(dto.getCount() + skucount);
		cartdto.setCount(cartdto.getCount() + skucount);
		cartdto.packscd.add(pscd);
	}

	/**
	 * 获取购物车中商品或店铺的活动
	 * 
	 * @param actList
	 *            活动集合
	 * @param giftList
	 *            满赠活动
	 * @param spuActList
	 *            购物车商品/店铺对应活动
	 * @param SpuActId
	 *            活动ID
	 * @param isShopAct
	 *            是否店铺活动 true-是
	 * @param dto
	 *            购物车dto
	 * @param totalMoney
	 *            商品/店铺商品总金额
	 * @param totalCount
	 *            商品/店铺商品数量
	 * @return
	 */
	private double CartMarket(List<ActivityMarket> actList,
			List<ActivityDto> giftList, List<ActivityShopCartDto> spuActList,
			int SpuActId, boolean isShopAct, CartDto dto, double sputmoney,
			int spuCount, List<VSpecsinfo> specslist) {
		double delMoney = 0.0,firstdel=0.0,firstgif=0.0;
		for (ActivityMarket market : actList) {
			ActivityShopCartDto ascdto = new ActivityShopCartDto();
			
			// 如果同时有多个活动，只取一条
			if (spuActList != null && spuActList.size() >= 1) {
				Optional<ActivityShopCartDto> result = spuActList.stream()
						.filter(x -> x.getIsselect()==true).findFirst();
				if (result.isPresent()) {
					if(result.get().getType()== ActivityTypeEnum.满减.getValue()){
						firstdel= result.get().getDelmoney();
					}
					else{
						firstgif=result.get().getGifscd().size();
					}
//					spuActList.clear();
//					spuActList.add(result.get());
//					break;
				}
			}
			ascdto.setIsselect(true);
			ascdto.setName(market.getActname());
			ascdto.setId(market.getId());
			ascdto.setType(market.getActtype());
			ascdto.setTypename(market.getActtype() == ActivityTypeEnum.满减
					.getValue() ? "满减" : "满赠");
			switch (ActivityTypeEnum.valueOf(market.getActtype())) {
			case 满赠:
				if(ascdto.getIsselect()){
					if (market.getUsetype() == ActivityUseTypeEnum.针对商品
							.getValue()) {
						if (market.getCount() <= spuCount) {
							ascdto.setIsselect(true);
							Optional<ActivityDto> gift = giftList.stream()
									.filter(x -> x.getId() == market.getId())
									.findFirst();
							if (!gift.isPresent())
								break;
							if(firstgif>=gift.get().getGiftList().size()){
								ascdto.setIsselect(false);
							}else{
								spuActList.clear();
								GetFullgift(ascdto, gift.get(), specslist);
							}							
						} else {
							ascdto.setIsselect(false);
						}
					} else if (market.getUsetype() == ActivityUseTypeEnum.针对金额
							.getValue()) {
						if (market.getFullvalue() <= sputmoney) {
							ascdto.setIsselect(true);
							Optional<ActivityDto> gift = giftList.stream()
									.filter(x -> x.getId() == market.getId())
									.findFirst();
							if (!gift.isPresent())
								break;
							if(firstgif>=gift.get().getGiftList().size()){
								ascdto.setIsselect(false);
							}else{
								spuActList.clear();
								GetFullgift(ascdto, gift.get(), specslist);
							}	
						} else
							ascdto.setIsselect(false);
					}
				}
				break;
			case 满减:
				if(ascdto.getIsselect()){
					if (market.getUsetype() == ActivityUseTypeEnum.针对商品
							.getValue()) {
						if (market.getCount() <= spuCount) {
							if(firstdel>market.getSubvalue()){
								ascdto.setIsselect(false);
							}else{
								spuActList.clear();
								ascdto.setIsselect(true);
							}
							
						} else
							ascdto.setIsselect(false);

					} else if (market.getUsetype() == ActivityUseTypeEnum.针对金额
							.getValue()) {
						if (market.getFullvalue() <= sputmoney) {
							if(firstdel>market.getSubvalue()){
								ascdto.setIsselect(false);
							}else{
							spuActList.clear();
							ascdto.setIsselect(true);
							
							}
						} else
							ascdto.setIsselect(false);
					}
					ascdto.setDelmoney(Double.valueOf(market.getSubvalue()));
				}
				break;
			default:
				break;
			}
			spuActList.add(ascdto);
		}
		// if (isShopAct)
		// {
		// 如果同时有多个活动，只取一条
		if (spuActList != null && spuActList.size() >=1) {
			Optional<ActivityShopCartDto> result = spuActList.stream()
					.filter(x -> x.getIsselect()).findFirst();
			if (!result.isPresent())
				result = spuActList.stream().findFirst();
			else{
				if(result.get().getType()==ActivityTypeEnum.满减.getValue()){
					delMoney+=result.get().getDelmoney();
					dto.setDelmoney(dto.getDelmoney() + result.get().getDelmoney());					
				}
				
			}
			spuActList.clear();
			spuActList.add(result.get());
		}
		// }
		return delMoney;
	}

	/**
	 * 满赠活动获取赠品信息
	 * 
	 * @param ascdto
	 * @param giftList
	 */
	private void GetFullgift(ActivityShopCartDto ascdto, ActivityDto giftList,
			List<VSpecsinfo> specslist) {
		for (FullGiftDto fgdto : giftList.getGiftList()) {
			ManZengTypeEnum mzTypeEnum = ManZengTypeEnum.valueOf(fgdto
					.getGiftType());
			switch (mzTypeEnum) {
			case 商品:
				SkuShopCartDto skug = new SkuShopCartDto();
				skug.setCount(fgdto.getCount() == null ? 0 : fgdto.getCount());
				skug.setImg(fgdto.getImgUrl());
				skug.setPrice(0.0d);
				skug.setSpecsinfo(getSpecsInfo(specslist, fgdto.getGiftID())); // 规格信息
				skug.setId(fgdto.getGiftID());
				skug.setName(fgdto.getName());
				if(skug.getCount()>0){
				skug.setIsselected(true);
				}else{
					skug.setIsselected(false);
				}
				skug.setProtype(ShopCartProType.赠品.getValue());
				ascdto.skuscd.add(skug);
				break;
			default:
				GiffullShopCartDto gscd = new GiffullShopCartDto();
				if (fgdto.getGiftType() == ManZengTypeEnum.积分.getValue()){
					gscd.setFacevalue((double) fgdto.getPoints());
					gscd.setId(null);
					gscd.setCount(1);
					gscd.setIsselected(true);
				}				
				else{
					gscd.setFacevalue(fgdto.getPrice());
					gscd.setId(fgdto.getGiftID());
					gscd.setCount(fgdto.getCount() == null ? 0 : fgdto.getCount());
					if(gscd.getCount()>0){
						gscd.setIsselected(true);
					}else{
						gscd.setIsselected(false);
					}
				}
				gscd.setType(fgdto.getGiftType());
				
				
				ascdto.gifscd.add(gscd);
				break;
			}
		}
	}

	/**
	 * 选中或取消选中购物车商品
	 * 
	 * @param id
	 * @param sel
	 * @return
	 * @throws Exception
	 */
	public int updateSelect(int id, int sel) throws Exception {
		Shopcartpros record = new Shopcartpros();
		record.setIsselected(sel);
		record.setId(id);
		return shopcartprosMapper.updateSelect(record);
	}

	/**
	 * 批量更新购物车
	 */
	public int addCarts(List<V_ShopCart> vclist) throws Exception {
		Shopcart shopcart = null;
		Shopcartpros cartpros = null;
		Shop shop = null;
		int ret = 0;
		for (V_ShopCart vc : vclist) {
			shopcart = new Shopcart();
			cartpros = new Shopcartpros();
			shop = new Shop();
			shop = shopMapper.selectByPrimaryKey(vc.getShopID());
			int cartid = 0;
			cartpros = shopcartprosMapper.getByuserIDAndProID(vc.getUserID(),
					vc.getProID(), vc.getType(), vc.getUseTime());
			if (cartpros != null && cartpros.getId() > 0) {
				cartpros.setProcount(cartpros.getProcount() + vc.getProCount());
				//cartpros.setSpikeid(vc.getSpikeID());
				return shopcartprosMapper.updateByPrimaryKey(cartpros);
			} else {
				shopcart = shopcartMapper.getByUserAndShop(vc.getUserID(),
						vc.getShopID());
				if (shopcart != null && shopcart.getId() > 0) {
					cartid = shopcart.getId();
				} else {
					if (shopcart == null)
						shopcart = new Shopcart();
					shopcart.setShopid(vc.getShopID());
					shopcart.setUserid(vc.getUserID());
					shopcart.setShopname(shop.getName());
					shopcart.setActivityid(vc.getActivityID());
					// 添加购物车主表 用户信息
					shopcartMapper.insertCart(shopcart);
					cartid = shopcart.getId();
				}
				if (cartpros == null)
					cartpros = new Shopcartpros();
				cartpros.setIsselected(vc.getIsSelected());
				cartpros.setProcount(vc.getProCount());
				cartpros.setProid(vc.getProID());
				cartpros.setProname(vc.getProName());
				cartpros.setShopcartid(cartid);
				cartpros.setSpuid(vc.getSpuID());
				//cartpros.setType(vc.getType());
				cartpros.setUserid(vc.getUserID());
				cartpros.setShopid(vc.getShopID());
				//cartpros.setSpikeid(vc.getSpikeID());
				cartpros.setUsetime(vc.getUseTime());
				// 添加购物车子表 商品信息
				ret = shopcartprosMapper.insert(cartpros);
			}
		}
		return ret;
	}

	/**
	 * 批量更新购物车商品选中状态
	 */
	public int updateSelectList(List<Integer> idlist, List<Integer> issel)
			throws Exception {
		List<Shopcartpros> cartproslist = new ArrayList<Shopcartpros>();
		Shopcartpros cartpros = null;
		for (int i = 0; i < idlist.size(); i++) {
			cartpros = new Shopcartpros();
			cartpros.setId(idlist.get(i));
			cartpros.setIsselected(issel.get(i));
			cartproslist.add(cartpros);
		}
		return shopcartprosMapper.updateSelectList(cartproslist);

	}

	@Override
	public int updateSelectShop(List<Integer> shopidList, Integer userid,
			Integer sel) {

		for (Integer shopid : shopidList) {
			shopcartprosMapper.updateSelectShop(shopid, userid, sel);
		}
		return 1;
	}

	/**
	 * 清空购物车中失效的数据
	 */
	@Override
	public int deleteByUserTime(Shopcartpros shopcartpros) throws Exception {
		return shopcartprosMapper.deleteByUserTime(shopcartpros);
	}

	@Override
	public String getProStatusByID(Integer id) throws Exception {
		Shopcartpros scp= shopcartprosMapper.selectByPrimaryKey(id);
		Spu spu=spuMapper.selectByPrimaryKey(scp.getSpuid());
		if(spu.getIsdel()==true || spu.getStatus().equals(ProStatusEnum.上架.getValue())==false){
			return "商品"+spu.getName()+"已下架";
		}
		if(differentDaysByMillisecond(scp.getUsetime(),new Date())>=1){
			return "商品"+spu.getName()+"已过期";
		}
		return "";
	}
	
	/**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
	
}

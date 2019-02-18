package com.yinlian.view.pc.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.AddCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.ActivityShopCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.PackShopCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.ShopShoppingCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.SpuShopCartDto;
import com.yinlian.api.app.dto.V_ShopCart;
import com.yinlian.pc.dto.ConcernSpuDto;
import com.yinlian.pc.dto.GuessLikeDto;
import com.yinlian.pc.dto.OrdercartDto;
import com.yinlian.pc.dto.PCTopicDto;
import com.yinlian.pc.dto.ReceiveaddrDto;
import com.yinlian.wssc.web.dto.BrowseHistoryDto;
import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.mapper.ShopcartMapper;
import com.yinlian.wssc.web.mapper.SnewsClassMapper;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.service.CartService;
import com.yinlian.wssc.web.service.NavclassfyService;
import com.yinlian.wssc.web.service.PackageService;
import com.yinlian.wssc.web.service.ReceiveAddressService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/web/")
public class PcCartViewController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private PackageService packageService;
	
	@Autowired
	private ShopcartMapper shopcartMapper;
	
	@Autowired
	private ShopService shopService;

	@Autowired
	private SkuService skuService;

	@Autowired
	private SpuService spuService;

	@Autowired
	private ReceiveAddressService receiveAddressService;
	
	@Autowired
	private TopicService topservice;
	
	@Autowired
	private NavclassfyService navservice;
	
	
	@Autowired
	private     BrowsehistoryService   browsehistoryServise;
	
	@Autowired
	private UsercollectService usercollectService;
	
	@Autowired
	private ArticlesService articlesService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private SnewsClassMapper snewsclassMapper;
	@SuppressWarnings("unchecked")
	@RequestMapping("/addcartsuccess.html")
	public String addCartok(HttpServletRequest request) {
	
		CartDto dto = new CartDto();
		String goodsid = "", ispack = "0";
		try {
			SessionUser user = SessionState.GetCurrentUser();
			String cartid = request.getParameter("cartid");
			if (user != null && user.getUserId() != null) {
				dto = cartService.getByUser(user.getUserId(),
						ActivityUsePlatformEnum.pc.getValue());
			} else {
				String cartstr =getcookies("cartstr");
				List<AddCartDto> cartDtos = new ArrayList<AddCartDto>();
				if (!StringUtilsEX.IsNullOrWhiteSpace(cartstr)) {
					JSONArray json = JSONArray.fromObject(cartstr);
					cartDtos = (List<AddCartDto>) JSONArray.toCollection(json,
							AddCartDto.class);
				} else {
					cartDtos = new ArrayList<AddCartDto>();
				}
				List<V_ShopCart> vscList = new ArrayList<V_ShopCart>();
				if (cartDtos != null && cartDtos.size() > 0) {
					V_ShopCart vsCart = null;
					for (AddCartDto adddto : cartDtos) {
						vsCart = new V_ShopCart();
						vsCart = checkParam(adddto);
						vscList.add(vsCart);
					}
				}
				if (vscList != null || vscList.size() > 0) {
					dto = cartService.getByCookie(vscList,
							ActivityUsePlatformEnum.pc.getValue(),0);
				}
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(cartid)) {
				goodsid = cartid.split("_")[0];
				ispack = cartid.split("_")[1];
			}
			List<PCTopicDto> topiclist=topservice.getTopicSpuByPagetag(PageMarkType.购物车成功页.getValue(),
					Integer.toString(WebSetEnum.pc.getValue()));
			if(topiclist!=null && topiclist.size()>0){
				request.setAttribute("choosespecs", topiclist.get(0).getSpus());
			}else {
				request.setAttribute("choosespecs", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.pc, "加入购物车异常! 异常信息:{0}", e,
					"cart/add");
			return "redirect:/404.html";
		}
		request.setAttribute("cart", dto);
		request.setAttribute("goodsid", goodsid);
		request.setAttribute("ispack", ispack);
		return "/template/pc/cart/cartaddok";

	}

	@RequestMapping("/cart.html")
	public String cart(HttpServletRequest request) {
		CartDto dto = new CartDto();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			request.setAttribute("user", user);
			if (user != null && user.getUserId() != null) {
				dto = cartService.getByUser(user.getUserId(),
						ActivityUsePlatformEnum.pc.getValue());
				List<BrowseHistoryDto> browsehistory=browsehistoryServise.queryDetailByUserId(user.getUserId());
				if(browsehistory!=null && browsehistory.size()>10){
					browsehistory=browsehistory.stream().limit(10).collect(Collectors.toList());
				}
				request.setAttribute("browsehistory",browsehistory);
				List<ConcernSpuDto> conlist=usercollectService.getConcernSpuByUser(user.getUserId());
				if(conlist!=null && conlist.size()>10){
					conlist=conlist.stream().limit(10).collect(Collectors.toList());
				}
				request.setAttribute("conlist",conlist);
			} else {
				request.setAttribute("browsehistory",null);
				request.setAttribute("conlist",null);
				String cartstr = getcookies("cartstr");
				List<AddCartDto> cartDtos = new ArrayList<AddCartDto>();
				if (!StringUtilsEX.IsNullOrWhiteSpace(cartstr)) {
					cartDtos=JSON.parseArray(cartstr, AddCartDto.class);
				} else {
					cartDtos = new ArrayList<AddCartDto>();
				}
				List<V_ShopCart> vscList = new ArrayList<V_ShopCart>();
				if (cartDtos != null && cartDtos.size() > 0) {
					V_ShopCart vsCart = null;
					for (AddCartDto adddto : cartDtos) {
						vsCart = new V_ShopCart();
						vsCart = checkParam(adddto);
						vscList.add(vsCart);
					}
				}
				if (vscList != null || vscList.size() > 0) {
					dto = cartService.getByCookie(vscList,
							ActivityUsePlatformEnum.pc.getValue(),0);
				}
			}
			List<GuessLikeDto> guessLikeDto=spuService.findGuessLikeByShopCartID(user.getUserId(), 5);
			if(guessLikeDto!=null && guessLikeDto.size()>0){
				request.setAttribute("likespecs", guessLikeDto);
			}else {
				request.setAttribute("likespecs", null);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.pc, "获取购物车列表异常! 异常信息:{0}", e,
					"cart/getlist");
			return "redirect:/404.html";
		}
		request.setAttribute("cart", dto);
		return "/template/pc/cart/cartlist";
	}

	/**
	 * 验证购物车参数
	 * 
	 * @param dto
	 * @param user
	 * @param item
	 * @return
	 * @throws Exception
	 */
	private V_ShopCart checkParam(AddCartDto dto) throws Exception {
		Integer shopid = StringUtilsEX.ToInt(dto.getSid());
		Integer ProID = StringUtilsEX.ToInt(dto.getProid());
		Integer Procount = StringUtilsEX.ToInt(dto.getProcount());
		Integer Protype = StringUtilsEX.ToInt(dto.getType());
		Integer SpikeID = StringUtilsEX.ToIntNull(dto.getSpikeid());
		Integer isSelect = StringUtilsEX.ToInt(dto.getIssel());
		Integer SpuID = null;
		String goodsName = "";
		if (shopid <= 0) {
			return null;
		}
		if (ProID <= 0) {
			return null;
		}
		if (Procount <= 0) {
			return null;
		}
		if (Protype < 0) {
			return null;
		}
		switch (Protype) {
		case 1:
			// 组合商品
			Package pkage = packageService.getByID(ProID);
			if (pkage == null) {
				return null;
			}
			goodsName = pkage.getName();
			break;
		default:
			// 普通商品
			Sku sku = skuService.selectByID(ProID);
			if (sku == null) {
				return null;
			}
			goodsName = sku.getName();
			SpuWithBLOBs spu = spuService.queryById(sku.getSpuId());
			if (spu == null) {
				return null;
			} else {
				if (spu.getStatus() != ProStatusEnum.上架.getValue()) {
					return null;
				}
			}
			SpuID = spu.getId();
			break;
		}
		V_ShopCart vc = new V_ShopCart();
		vc.setUserID(0);
		vc.setIsSelected(isSelect);
		vc.setProCount(Procount);
		vc.setProName(goodsName);
		vc.setProID(ProID);
		vc.setShopID(shopid);
		vc.setType(Protype);
		vc.setSpuID(SpuID);
		vc.setSpikeID(SpikeID);
		return vc;
	}

	@RequestMapping("/orderpay.html")
	public String orderpay(HttpServletRequest request) {
		List<ReceiveaddrDto> reList = new ArrayList<ReceiveaddrDto>();
		ReceiveaddrDto addrDto =null;
		// 购物车传过来的数据解析接收类
		OrdercartDto.CartDto cartDto = new OrdercartDto.CartDto();

		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
				return "redirect:/member/user/showlogin";
			} else {
				// 收货地址
				reList = receiveAddressService.getByUser(user.getUserId());
				if(reList.size()>0){
					Optional<ReceiveaddrDto> addr=reList.stream().filter(x->x.getIsdefault()==1).findFirst();
					if(addr.isPresent()){
						addrDto =new ReceiveaddrDto();
						BeanUtils.copyProperties(addrDto, addr.get());
					}
				}
				// 拼接商品信息
				String cartparamstr = request.getParameter("cart_paramstr");
				if (!StringUtilsEX.IsNullOrWhiteSpace(cartparamstr)) {					
					 cartDto=JSON.parseObject(cartparamstr, OrdercartDto.CartDto.class);
				}
				
				int points=0;//用户积分
				Users users = userService.queryById(user.getUserId());
				if (users != null && users.getPoints() != null) {
					points = users.getPoints();
				}
				int usepoints=0;//用户可用积分
				double pointMoney=0.00;
				if(points>100){//可用积分要大于1000 且等值金额小于订单金额的1/2
					pointMoney=(points/100);
				}
				if(pointMoney>0){
					double apay = cartDto.getPaymoney()!=null?cartDto.getPaymoney():0;
					if (apay > 2) {
						double ap = (apay / 2);
					if(ap>pointMoney){
						BigDecimal b=new BigDecimal(pointMoney);  
						double f=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
						usepoints=StringUtilsEX.ToInt(Double.toString(f*100).split("\\.")[0]);					
					}else{						
						BigDecimal b=new BigDecimal(ap);  
						double f1=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
						usepoints=StringUtilsEX.ToInt(Double.toString(f1*100).split("\\.")[0]);
						pointMoney=ap;
					}
					if(usepoints<100){//可用积分要大于100 且等值金额小于订单金额的1/2
						usepoints=0;
					}
				  }
				}
				double paymoney=cartDto.getPaymoney();
				request.setAttribute("points",points);//总共积分
				request.setAttribute("usepoints",usepoints);//可用积分
				request.setAttribute("paymoney",paymoney);//订单支付的钱				
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.pc, "结算异常! 异常信息:{0}", e,
					"cart/orderpay");
			return "redirect:/404.html";
		}
		initHead(request);
		initFoot(request);
		request.setAttribute("defaultaddr", addrDto);
		request.setAttribute("address", reList);
		request.setAttribute("cartdto", cartDto);	
		
		return "/template/pc/Orders/order_jiesuan";
	}
	
	@RequestMapping("/orderbuynow.html")
	public String buyNow(String cartid,HttpServletRequest request){
		List<ReceiveaddrDto> reList = new ArrayList<ReceiveaddrDto>();
		CartDto dto = new CartDto();
		ReceiveaddrDto addrDto =null;
		// 购物车传过来的数据解析接收类
		OrdercartDto.CartDto cartDto = new OrdercartDto.CartDto();
		OrdercartDto.ActivityCartDto asd=null;
		OrdercartDto.ShopCartDto shopscd=null;
		OrdercartDto.SpuShopCartDto spusscd=null;
		
		String goodsid="",type="";
		try{
			SessionUser user = SessionState.GetCurrentUser();
			if (user !=null && user.getCode() == 0) {
				// 收货地址
				reList = receiveAddressService.getByUser(user.getUserId());
				if(reList.size()>0){
					Optional<ReceiveaddrDto> addr=reList.stream().filter(x->x.getIsdefault()==1).findFirst();
					if(addr.isPresent()){
						addrDto =new ReceiveaddrDto();
						BeanUtils.copyProperties(addrDto, addr.get());
					}
				}
				
				if(!StringUtilsEX.IsNullOrWhiteSpace(cartid)){
					String cartstr =getcookies("buynowstr");
					List<AddCartDto> cartDtos = JSON.parseArray(cartstr,AddCartDto.class);
					List<V_ShopCart> vscList = new ArrayList<V_ShopCart>();
					if (cartDtos != null && cartDtos.size() > 0) {
						V_ShopCart vsCart = null;
						for (AddCartDto adddto : cartDtos) {
							goodsid = cartid.split("_")[0];
							type = cartid.split("_")[1];
							if(StringUtilsEX.ToInt(goodsid).equals(StringUtilsEX.ToInt(adddto.getProid())) &&
									StringUtilsEX.ToInt(type).equals(StringUtilsEX.ToInt(adddto.getType()))){
								vsCart = new V_ShopCart();
								vsCart = checkParam(adddto);
								vscList.add(vsCart);
							}
						}						
					}
					if (vscList != null || vscList.size() > 0) {
						dto = cartService.getByCookie(vscList,
								ActivityUsePlatformEnum.pc.getValue(),user.getUserId());
						if(dto!=null){
							cartDto.setDelmoney(dto.getDelmoney());
							cartDto.setPaymoney(dto.getAmountpayable());
							cartDto.setTotalmoney(dto.getMoney());
							cartDto.setTotalcount(dto.getCount());
							for (ShopShoppingCartDto sscd : dto.getShops()) {
								asd=new OrdercartDto.ActivityCartDto();
								shopscd=new OrdercartDto.ShopCartDto();
							
								if(sscd.getAsd().size()>0){
									Optional<ActivityShopCartDto> result = sscd.getAsd().stream()
											.filter(x -> x.getIsselect()).findFirst();
									if (result.isPresent()){
										int c=0;
										if(result.get().skuscd.size()>0){											
											for (ShoppingNewCartDto.SkuShopCartDto skugif : result.get().skuscd) {												
												c+=skugif.getCount();
											}
										}
										if(c>0){
											BeanUtils.copyProperties(asd, result.get());
										}
										
									}									
								}
								shopscd.setSpuscd(new ArrayList<OrdercartDto.SpuShopCartDto>());
								shopscd.setShopasd(asd);
								shopscd.setShopid(sscd.getShopid());
								shopscd.setDelmoney(sscd.getDelmoney());
								shopscd.setShopname(sscd.getShopname());
								shopscd.setTotalmoney(sscd.getTotalmoney());
								for (PackShopCartDto pscd : sscd.getPackscd()) {
									OrdercartDto.PackShopCartDto packscd=new OrdercartDto.PackShopCartDto();
									BeanUtils.copyProperties(packscd, pscd);
									shopscd.packscd.add(packscd);
								}
								for (SpuShopCartDto spuscd : sscd.getSpuscd()) {
									spusscd=new OrdercartDto.SpuShopCartDto();
									OrdercartDto.ActivityCartDto spuasd=new OrdercartDto.ActivityCartDto();
									if(spuscd.getAsd().size()>0){
										Optional<ActivityShopCartDto> resultspu = spuscd.getAsd().stream()
												.filter(x -> x.getIsselect()).findFirst();
										if (resultspu.isPresent()){
											int c=0;
											if(resultspu.get().skuscd.size()>0){											
												for (ShoppingNewCartDto.SkuShopCartDto skugif : resultspu.get().skuscd) {												
													c+=skugif.getCount();
												}
											}
											if(c>0){
												BeanUtils.copyProperties(spuasd, resultspu.get());
											}
											
										}	
									}									
									BeanUtils.copyProperties(spusscd, spuscd);
									spusscd.setDelmoney(spuscd.getDelmoney());
									spusscd.setTotalmoney(spuscd.getTotalmoney());
									spusscd.setSpuasd(spuasd);
									shopscd.spuscd.add(spusscd);
								}
								cartDto.shops.add(shopscd);
							}
						}
						int points=0;//用户积分
						Users users = userService.queryById(user.getUserId());
						if (users != null && users.getPoints() != null) {
							points = users.getPoints();
						}
						int usepoints=0;//用户可用积分
						double pointMoney=0.00;
						if(points>100){//可用积分要大于100 且等值金额小于订单金额的1/2
							pointMoney=Double.valueOf(points/100.0);
						}
						if(pointMoney>0){
							double apay = cartDto.getPaymoney();
							if (apay > 2) {
								double ap = (apay / 2);
							if(ap>pointMoney){
								BigDecimal b=new BigDecimal(pointMoney);  
								double f=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
								usepoints=StringUtilsEX.ToInt(Double.toString(f*100).split("\\.")[0]);					
							}else{						
								BigDecimal b=new BigDecimal(ap);  
								double f1=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
								usepoints=StringUtilsEX.ToInt(Double.toString(f1*100).split("\\.")[0]);
								pointMoney=ap;
							}
							if(usepoints<100){//可用积分要大于100 且等值金额小于订单金额的1/2
								usepoints=0;
							}
						  }
						}
						double paymoney=cartDto.getPaymoney();
						request.setAttribute("points",points);//总共积分
						request.setAttribute("usepoints",usepoints);//可用积分
						request.setAttribute("paymoney",paymoney);//订单支付的钱
						request.setAttribute("pointMoney",pointMoney);//积分兑换的钱

					}
				}
			}
			else{
				return "redirect:/member/user/showlogin";
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.pc, "立即购买异常! 异常信息:{0}", e,
					"cart/paynow");
			return "redirect:/404.html";
		}
		initHead(request);
		initFoot(request);
		request.setAttribute("defaultaddr", addrDto);
		request.setAttribute("address", reList);
		request.setAttribute("cartdto", cartDto);
		return "/template/pc/Orders/order_jiesuan";
	}
	
	
	private void initHead(HttpServletRequest model){
		List<NavclassfyDto> listkh=new ArrayList<NavclassfyDto>();
		try{
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser==null||sessionUser.getCode() == -1)
				sessionUser = null;
			model.setAttribute("userinfo", sessionUser);
			listkh=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "客户服务"); //客户服务
		}
		catch(Exception e){
			listkh=new ArrayList<NavclassfyDto>();
			LogHandle.error(LogType.pc, "获取首页内容错误：{0}", e, "/head");
		}
		model.setAttribute("navskhfw",listkh);
	}
	private String getcookies(String cookiename )throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Cookie[] cookies = request.getCookies();
		String cookiesvalue = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookiename)) {
					cookiesvalue=URLDecoder.decode(cookie.getValue(), "UTF-8");  
					break;
				}
			}
		}
		return cookiesvalue;
	}
	private void initFoot(HttpServletRequest request){		
		List<SnewsClass> newscone =new ArrayList<SnewsClass>();
		List<SnewsClass> newsctwo =new ArrayList<SnewsClass>();
		List<SnewsClass> newscwthree =new ArrayList<SnewsClass>();
		List<SnewsClass> newscfour =new ArrayList<SnewsClass>();
		List<SnewsClass> newscfive =new ArrayList<SnewsClass>();
		try {
			List<SnewsClass> clist=snewsclassMapper.selectByParentId(0,2);
				for (int i = 0; i < clist.size(); i++) {
					if(clist.get(i).getLevel()==1){
						List<SnewsClass> newscone1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newscone.add(clist.get(i));
						for (int j = 0; j < newscone1.size(); j++) {
							newscone.add(newscone1.get(j));	
						}
					}
					if(clist.get(i).getLevel()==2){
						List<SnewsClass> newsctwo1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newsctwo.add(clist.get(i));
						for (int j = 0; j < newsctwo1.size(); j++) {
							newsctwo.add(newsctwo1.get(j));	
						}
					}
					if(clist.get(i).getLevel()==3){
						List<SnewsClass> newscwthree1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newscwthree.add(clist.get(i));
						for (int j = 0; j < newscwthree1.size(); j++) {
							newscwthree.add(newscwthree1.get(j));	
						}
					}
					if(clist.get(i).getLevel()==4){
						List<SnewsClass> newscfour1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newscfour.add(clist.get(i));
						for (int j = 0; j < newscfour1.size(); j++) {
							newscfour.add(newscfour1.get(j));	
						}
					}
					if(clist.get(i).getLevel()==5){
						List<SnewsClass> newscfive1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newscfive.add(clist.get(i));
						for (int j = 0; j < newscfive1.size(); j++) {
							newscfive.add(newscfive1.get(j));	
						}
					}
				}
		} catch (Exception e) {
			LogHandle.error(LogType.pc, "获取首页内容错误：{0}", e, "/foot");
		}
		request.setAttribute("newscone", newscone==null?new ArrayList<SnewsClass>():newscone);
		request.setAttribute("newsctwo", newsctwo==null?new ArrayList<SnewsClass>():newsctwo);
		request.setAttribute("newscwthree",newscwthree==null?new ArrayList<SnewsClass>():newscwthree);
		request.setAttribute("newscfour",newscfour==null?new ArrayList<SnewsClass>():newscfour);
		request.setAttribute("newscfive",newscfive==null?new ArrayList<SnewsClass>():newscfive);
	}
}

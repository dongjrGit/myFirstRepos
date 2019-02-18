package com.yinlian.view.pc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.UserCollectTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.OrderCountDto;
import com.yinlian.wap.dto.UserInfoDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_OrderCriteria;
import com.yinlian.wssc.web.dto.BrowseHistoryDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SnewsClassMapper;
import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.BankCardService;
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.ReceiveInfoService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/member/homepage")
public class MomberHomeViewController {
	@Autowired
	private     OrderService orderService;
	@Autowired
	private     ReceiveInfoService receiveInfoService;
	@Autowired
	private     UserService  userService;
	@Autowired
	private     MessageService messageService;
	@Autowired
	private     CouponService couponService;
	@Autowired
	private     BankCardService bankcardservice;
	@Autowired
	private     OrderdetailService orderdetailService;
	
	@Autowired
	private     UsercollectService usercollectService;
	@Autowired
	private     SpuService       spuService;
	@Autowired
	private     BrowsehistoryService   browsehistoryServise;
	@Autowired
	private SnewsClassMapper snewsclassMapper;
	
	@RequestMapping(value = "/leftpage", produces = "text/html;charset=UTF-8")
	public ModelAndView leftpage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/template/pc/memberCenter/Member/_PartialLeftside");
		return view;
	}
	@RequestMapping(value = "/homepages.html", produces = "text/html;charset=UTF-8")
	public String homepages(HttpServletRequest request) {
		String page="1",size="10",status="",iscomment="0";
		ReusltItem item = new ReusltItem();
		try {
			initFoot(request);
			SessionUser sessionUser =SessionState.GetCurrentUser();
			if(sessionUser==null){
				return "redirect:/member/user/showlogin";
			}
			UserInfoDto userInfo = new UserInfoDto();
			if (sessionUser.getCode() != 0) {
				
				return "redirect:/member/user/showlogin";
			} else {
				int userId = sessionUser.getUserId();
                  //int userId=492;
				OrderCountDto dto = orderService.selectCount(userId);
				userInfo.setChildren(dto);
				userInfo.setUserId(String.valueOf(userId));
				
				Users users = new Users();
				users = userService.queryById(userId);
				if (users == null) {
					return "redirect:/member/user/showlogin";

				} else {
					int currentPoints = users.getPoints();
					userInfo.setPoints(currentPoints);
					int messagecount=messageService.getCount(userId);
					userInfo.setMessagecount(messagecount);
					int couponcount = couponService.getCount(userId);
					userInfo.setCouponcount(couponcount);
					int bankcard = bankcardservice.selectCount(userId);
					userInfo.setBankcard(bankcard);
					userInfo.setImgUrl(users.getImgurl());
					userInfo.setRealName(users.getRealname());
					
					request.setAttribute("memberhome",userInfo);

					} 
				}
			
			if (sessionUser.getCode() != 0) {
				return "redirect:/member/user/showlogin";
			} else {
				Integer page1 = StringUtilsEX.ToIntNull(page);
				Integer size11 = StringUtilsEX.ToIntNull(size);
				if (page1 == null || page1 <= 0) {
         				page1 = 1;
				}
				if (size11 == null || size11 <= 0) {
					size11 = 10;
				}
				// item.setCode(0);
				Api_OrderCriteria aoc = new Api_OrderCriteria();
				aoc.setOrderByClause("AddOrderDate");
				aoc.setSort("desc");
				// aoc.setUserid(492);
				aoc.setUserid(sessionUser.getUserId());
				aoc.setStatus(StringUtilsEX.ToIntNull(status));
				PageBean list = orderService.getUserListOrder(page1, size11,aoc);
				request.setAttribute("orders", list.getBeanList());
				
				String type="0";
				
				int collectType=StringUtilsEX.ToInt(type);
				int buyerId=sessionUser.getUserId();
				//int buyerId=492;
			    List<Usercollect> listconcern=usercollectService.select(buyerId, collectType);
				if(listconcern!=null&&listconcern.size()<1){
					item.setCode(0);
						
				}else{
						
					if(collectType==UserCollectTypeEnum.商品.getValue()){
							List<Spu> spulist=new ArrayList<Spu>();
							for (int i = 0; i < listconcern.size(); i++) {
								Spu spu=spuService.queryById(listconcern.get(i).getSpuid());
								if(spu!=null){
									spulist.add(spu);
								}
							}
							item.setCode(0);
							item.setDesc("查询成功");
							item.setData(spulist);
							request.setAttribute("spu",spulist);
			}
					}
				List<BrowseHistoryDto> browsehistory=	browsehistoryServise.queryDetailByUserId(sessionUser.getUserId());
				request.setAttribute("browsehistory",browsehistory);
			}
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("会员中心异常：" + e.getMessage());
			LogHandle.error(LogType.pc, "会员中心异常! 异常信息:{0}", e,
					"order/getorder");
			return "redirect:/member/user/showlogin";
		}
		return "/template/pc/memberCenter/Member/MemberSY";
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

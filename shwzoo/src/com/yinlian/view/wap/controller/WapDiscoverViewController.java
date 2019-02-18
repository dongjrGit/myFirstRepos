/*
 * @(#) WapDiscoverViewController.java 2016年7月7日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.FindTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.search.Api_FindCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.showuserMapper;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Findrecord;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.showarticle;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.FindRecordService;
import com.yinlian.wssc.web.service.FindRelationService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.showarticleService;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 发现
 * 
 * @Description
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年7月7日
 */
@Controller
@RequestMapping("/wap/discover")
public class WapDiscoverViewController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private showarticleService showarticleService;
	@Autowired
	private UserService userService;
	@Autowired
	private showuserMapper showuserMapper;
	@Autowired
	private FindRecordService findrecordservice;

	@Autowired
	private FindRelationService findRelationService;

	/**
	 * 发现
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index(HttpServletRequest request) {
		try {
			List<Findrecord> list = new ArrayList<Findrecord>();
			list = findrecordservice.queryFind();
			Api_FindCriteria criteria = new Api_FindCriteria();
			int dtype = 0;
			if (list != null && list.size() > 0) {
				for (Findrecord fr : list) {
					if (fr.getType() == FindTypeEnum.专题.getValue()) {
						dtype = FindTypeEnum.专题.getValue();
						criteria.setFindid(fr.getId());
						PageBean listBean = findRelationService
								.selectFindTopicList(criteria, 1, 10);
						request.setAttribute("fsps", listBean.getBeanList());
						break;
					} else {
						if (fr.getType() == FindTypeEnum.资讯文章.getValue()) {
							dtype = FindTypeEnum.资讯文章.getValue();
							criteria.setFindid(fr.getId());
							PageBean listBean = findRelationService
									.selectFindArtList(criteria, 1, 10);
							request.setAttribute("fsps", listBean.getBeanList());
							break;
						}
					}

				}
			}
			request.setAttribute("finds", list);
			request.setAttribute("dtype", dtype);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("查询发现异常! 异常信息:{0}", e.getMessage())
							.toString(), "/wap/discover/index");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/index";
	}

	/**
	 * 百宝箱
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/box.html")
	public String box() {
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/box";
	}

	/**
	 * 精选推荐
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/recommend.html")
	public String recommend(Model model, String id) {
		model.addAttribute("fl", querycategory("3").getData());
		model.addAttribute("id", id);
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/recommend";
	}

	/**
	 * 查询商品分类
	 *
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/querycategory", produces = "text/html;charset=UTF-8")
	public BaseResult querycategory(String ch) {
		BaseResult item = new BaseResult();
		try {
			List<Category> list = categoryService.querySan();
			item.setData(list);
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询商盟分类异常：" + e.getMessage());
			LogHandle.error(LogType.Api,
					MessageFormat.format("查询商盟分类异常! 异常信息:{0}", e.getMessage())
							.toString(), "shop/queryscopshop");
		}
		return item;
	}

	/**
	 * 店铺动态
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/dynamic.html")
	public String shopdynamic(String findid, HttpServletRequest request) {
		try {
			if(StringUtilsEX.ToInt(findid)>0){
			Api_FindCriteria criteria = new Api_FindCriteria();						
			criteria.setFindid(StringUtilsEX.ToInt(findid));
			PageBean listBean = findRelationService.selectFindspuList(
					criteria, 1, 10);
			request.setAttribute("dynamic", listBean.getBeanList());
			request.setAttribute("findid", findid);
			}
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("店铺动态异常 异常信息:{0}", e.getMessage())
							.toString(), "/wap/discover/dynamic");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/dynamic";
	}

	/**
	 * 单个店铺
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/dynamicSimple.html")
	public String dynamicSimple() {
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/dynamicSimple";
	}

	/**
	 * 更多商品
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/dynamicMore.html")
	public String dynamicSimpleMore(String shopid, Model model) {
		model.addAttribute("shopid", shopid);
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/dynamicMore";
	}

	/**
	 * 晒出范 ---广场
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/guangchang.html")
	public String guangchang() {
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/guangchang";
	}

	/**
	 * 范详情
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/fandetail.html")
	public String fandetail(String showid, Model model) {
		try {
			showarticle showarticle = showarticleService.selectInfo(showid);
			if (showarticle != null) {
				Users users = userService.selectByPrimaryKey(showarticle
						.getUserid());
				if (users != null) {
					model.addAttribute("imgurl", users.getImgurl());
				}
			}
			int count = showuserMapper.selectCount(StringUtilsEX.ToInt(showid));
			model.addAttribute("count", count);
			model.addAttribute("showarticle", showarticle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/fandetail";
	}

	/**
	 * 范详情---分享
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/share.html")
	public String detailShare(String showid, Model model) {
		try {
			showarticle showarticle = showarticleService.selectInfo(showid);
			if (showarticle != null) {
				Users users = userService.selectByPrimaryKey(showarticle
						.getUserid());
				if (users != null) {
					model.addAttribute("imgurl", users.getImgurl());
				}
			}
			int count = showuserMapper.selectCount(StringUtilsEX.ToInt(showid));
			model.addAttribute("count", count);
			model.addAttribute("showarticle", showarticle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/share";
	}

	/**
	 * 晒范
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/shaifan.html")
	public String shaifan(String skuid, Model model) {

		model.addAttribute("skuid", skuid);
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/shaifan";
	}

	/**
	 * 晒范--添加图片
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/addImg.html")
	public String addImg() {
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/addImg";
	}

	/**
	 * 晒范---选择商品
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/selectSpu.html")
	public String selectSpu() {
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/selectSpu";
	}

	/**
	 * 我的---我发布的
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/mypublish.html")
	public String mypublish(HttpServletRequest request, Model model) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			if (sessionUser != null) {
				Users user = userService.selectByPrimaryKey(sessionUser
						.getUserId());
				if (user != null) {
					model.addAttribute("imgurl", user.getImgurl());
					model.addAttribute("username", user.getUsername());
					model.addAttribute("userid", user.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/mypublish";
	}

	/**
	 * 我的---我喜欢的
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/mylike.html")
	public String mylike(HttpServletRequest request, Model model) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			if (sessionUser != null) {
				Users user = userService.selectByPrimaryKey(sessionUser
						.getUserId());
				if (user != null) {
					model.addAttribute("imgurl", user.getImgurl());
					model.addAttribute("username", user.getUsername());
					model.addAttribute("userid", user.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/mylike";
	}

	/**
	 * 我的---更改头像
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/updphoto.html")
	public String updphoto(HttpServletRequest request, Model model) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			if (sessionUser != null) {
				Users user = userService.selectByPrimaryKey(sessionUser
						.getUserId());
				if (user != null) {
					model.addAttribute("userid", user.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/updphoto";
	}

	/**
	 * 我的---更改头像
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/updname.html")
	public String updname(HttpServletRequest request, Model model) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			if (sessionUser != null) {
				Users user = userService.selectByPrimaryKey(sessionUser
						.getUserId());
				if (user != null) {
					model.addAttribute("userid", user.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/updname";
	}

	/**
	 * 我的---消息
	 * 
	 * @author kh.wang
	 * @since 2016年7月7日
	 * @return
	 */
	@RequestMapping("/message.html")
	public String message() {
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/message";
	}

	/**
	 * 评论
	 * 
	 * @author kh.wang
	 * @since 2016年7月12日
	 * @return
	 */
	@RequestMapping("/comment.html")
	public String comment() {
		//return "redirect:/wap/404.html";
		return "/template/wap/discover/comment";
	}
}

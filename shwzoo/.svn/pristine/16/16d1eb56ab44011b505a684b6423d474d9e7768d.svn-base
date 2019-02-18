package com.yinlian.view.pc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.wssc.search.Pc_TopicCriteria;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.CriteriaBaner;
import com.yinlian.wssc.web.po.CriteriaSnews;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.BanerService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/zlzx")
public class ZLZXViewController {
	@Autowired 
	private TopicService topservice;
	@Autowired private NewsService newsService;
	@Autowired private TopicService tpService;
	@Autowired
	private BanerService banerService;
	@Autowired
	private AdverisingService adverusingService;
	@Autowired
	private CategoryService categoryService;
	// 中绿咨询页面
	@RequestMapping("/zlzx_index.html")
	public String zlzx(Model model) {
		try {
			CriteriaSnews criteria = new CriteriaSnews();
			
		    criteria.setState(0);//新闻0为发布，1为未发布

			criteria.setCtype(30);//菜谱大全
			criteria.setOrderByClause("sort");
			criteria.setSort("asc");				
			//今日热门
			model.addAttribute("jrrm", newsService.querySlist(criteria, 1, 8).getBeanList());
			criteria.setCtype(31);
			//精选推荐
			model.addAttribute("jxtj", newsService.querySlist(criteria, 1, 8).getBeanList());
			criteria.setCtype(32);
			//视频专区
			model.addAttribute("spzq", newsService.querySlist(criteria, 1, 13).getBeanList());
			
			return "/template/pc/zlzx/zlzx_new_index";

		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}
// 中绿咨询页面详细
	@RequestMapping("/zlzx_detail.html")
	public String zlzx_detail() {
		try {
			return "/template/pc/zlzx/zlzx_detail";
		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}
	// 中绿味道首页
	@RequestMapping("/zlwd.html")
	public String zlwd(HttpServletRequest request) {
		try {
			CriteriaSnews criteria = new CriteriaSnews();
			
		    criteria.setState(0);//新闻0为发布，1为未发布

			criteria.setCtype(14);//菜谱大全
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");						
			PageBean pageBean = newsService.querySlist(criteria, 1, 16);
			request.setAttribute("list", pageBean.getBeanList());
			
			return "/template/pc/zlzx/zlwd_index";
		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}
	// 中绿味道菜譜大全首页
	@RequestMapping("/zlwd_cpdq.html")
	public String cpdq() {
		try {
			return "/template/pc/zlzx/zlwd_cpdq";
		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}
	// 中绿味道菜普大全詳細
		@RequestMapping("/zlwd_cpdqdetail.html")
		public String cpdqdetail() {
			try {
				return "/template/pc/zlzx/zlwd_cpdqdetail";
			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		// 中绿味道廚房百科
		@RequestMapping("/zlwd_cfbk.html")
		public String cpdqcfbk() {
			try {
				return "/template/pc/zlzx/zlwd_cfbk";
			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		// 中绿味道廚房百科詳細
		@RequestMapping("/zlwd_cfbkdetail.html")
		public String cpdqcfbkdetail() {
			try {
				return "/template/pc/zlzx/zlwd_cfbkdetail";
			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		// 中绿味道专家讲堂
		@RequestMapping("/zlwd_zjjt.html")
		public String zlwd_zjjt() {
			try {
				return "/template/pc/zlzx/zlwd_zjjt";

			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		/**
		 * 中绿咨询更多
		 * @return
		 */
		@RequestMapping("/zlzx_gd.html")
		public String zlzx_gd(String title,String ctype,Model model){
			model.addAttribute("title", title);
			model.addAttribute("ctype", ctype);
			return "/template/pc/zlzx/zlzx_gd";
		}
		// 中绿味道专家讲堂详细
		@RequestMapping("/zlwd_zjjtdetail.html")
		public String zlwd_zjjtdetail() {
			try {
				return "/template/pc/zlzx/zlwd_zjjtdetail";
			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		// 中绿专区首页
		@RequestMapping("/zlzq_index.html")
		public String zlzq_index() {
			try {
				return "/template/pc/zlzx/zlzq_index";

			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		// 中绿专区地图s
		@RequestMapping("/zlzq_map.html")
		public String zlzq_map() {
			try {
				return "/template/pc/zlzx/zlzq_map";

			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		// 中绿专区基地详情
		@RequestMapping("/newsinfo.html")
		public String zlzq_basedetail(HttpServletRequest req,String ctype) {
			try {
				String title=req.getParameter("title");
				String url=req.getParameter("url");
				//额外参数拼接
				if (!StringUtilsEX.IsNullOrWhiteSpace(ctype)) {
					url+="&ctype="+ctype;
				}
				req.setAttribute("title", title);
				req.setAttribute("url", url);
				return "/template/pc/zlzx/zlzq_basedetail";

			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		
		@RequestMapping("/zlwdinfo.html")
		public String zlwdinfo(HttpServletRequest req,String cid) {
			try {
				String title=req.getParameter("title");
				String url=req.getParameter("url");
				req.setAttribute("title", title);
				req.setAttribute("url", url);
				req.setAttribute("prolist", newsService.queryProAll(StringUtilsEX.ToInt(cid)));
				return "/template/pc/zlzx/zlwd_info";
			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		//每日鲜
		@RequestMapping("/zlwd_mrx.html")
		public String zlwd_mrx(HttpServletRequest req,String mark,Model model){
			try {
				
				Pc_TopicCriteria cri=new Pc_TopicCriteria();//商品推荐
				cri.setPagetag(1);
				cri.setType(3);
				cri.setWebset("2");
				
				
				CriteriaBaner criteria = new CriteriaBaner();//轮播图
				switch (StringUtilsEX.ToInt(mark)) {
				case 15://每日鲜
					cri.setMark(TopicMarkEnum.每日鲜商品推荐.getValue());//商品推荐
					cri.setPagetag(PageMarkType.每日鲜.getValue());
					criteria.setType(2);
					break;
				case 16:
					cri.setMark(TopicMarkEnum.包邮直送商品推荐.getValue());
					cri.setPagetag(PageMarkType.包邮直送.getValue());
					criteria.setType(3);//包邮直送
					cri.setMark(21);
					break;
				default:
					criteria.setType(2);//每日鲜
					cri.setMark(21);
					break;
				}
				cri.setType(TopicTypeEnum.商品.getValue());
				cri.setWebset( WebSetEnum.pc.getValue() + "");
				PageBean list=tpService.getPcTopByPage(cri, 0, 4);
				req.setAttribute("sptj", list.getBeanList());
				//商品推荐↑
				
				
				criteria.setOrderByClause("sort");
				PageBean pageBean = banerService.selBaner(criteria, 0, 5);
				req.setAttribute("ad1list",pageBean.getBeanList());
				//轮播图↑
				List<Advertising> li=new ArrayList<Advertising>();
				switch (StringUtilsEX.ToInt(mark)) {
				case 15:
					li=adverusingService.getListByType(PageMarkType.每日鲜PC.getValue(), WebSetEnum.pc.getValue());
					break;
				case 16:
					li=adverusingService.getListByType(PageMarkType.包邮直送PC.getValue(), WebSetEnum.pc.getValue());//包邮直送
					break;
				default:
					li=adverusingService.getListByType(PageMarkType.每日鲜PC.getValue(), WebSetEnum.pc.getValue());//每日鲜
					break;
				}
				//广告↑
				
   				req.setAttribute("ad2list",li);
				List<Api_TopicBySpuDto> likedpros = topservice.getIndexTopic(PageMarkType.商品列表页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
						TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
				req.setAttribute("mark", mark);
				req.setAttribute("likedpros", likedpros);
				intitNav(model);
				return "/template/pc/zlzx/zlwd_mrx";
			} catch (Exception e) {
				return "redirect:/404.html";
			}
		}
		private void intitNav(Model model) {
			intitNav(model, null);
		}
		
		private void intitNav(Model model, List<CategoryDTO> list) {
			try {
				if (list == null) {
					list = categoryService.selectAll(0);
				}
				model.addAttribute("classlist", list);

			} catch (Exception e) {
				LogHandle.error(LogType.pc, "获取首页内容错误：{0}", e, "/index");
			}
		}
}

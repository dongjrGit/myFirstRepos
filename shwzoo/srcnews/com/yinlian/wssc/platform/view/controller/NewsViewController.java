package com.yinlian.wssc.platform.view.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.SnewsImgWithBLOBs;
import com.yinlian.wssc.web.po.SnewsRImgs;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/platform/news")
public class NewsViewController {

	@Autowired
	private NewsService newsService;

	/**
	 * 新闻类型
	 * 
	 * @param type
	 */
	@RequestMapping("/list")
	public String list(String type, String ctype, HttpServletRequest request) {
		request.setAttribute("type", type);
		request.setAttribute("ctype", ctype);
		return "/platform/news/list";
	}
	
	
	/**
	 * 相关食材
	 * @param newsid
	 * @param model
	 * @return
	 */
	@RequestMapping("/xgsc")
	public String xgsc(String newsid,Model model){
		model.addAttribute("newsid", newsid);
		return "/platform/news/xgsc_list";
	}
	
	@RequestMapping("/xgscedit")
	public String xgscedit(String newsid,Model model){
		model.addAttribute("newsid", newsid);
		return "/platform/news/xgsc_edit";
	}

	/**
	 * 公告类型
	 * 
	 * @param type
	 */
	@RequestMapping("/slist")
	public String singlelist(String type, String ctype, HttpServletRequest request) {
		request.setAttribute("type", type);
		request.setAttribute("ctype", ctype);
		return "/platform/news/slist";
	}

	/**
	 * 关于我们类型
	 */
	@RequestMapping("/single")
	public String single(String type,String name, HttpServletRequest request) {
		request.setAttribute("type", type);
		request.setAttribute("name", name);
		SnewsWithBLOBs swb = newsService.selSingle(StringUtilsEX.ToInt(type));
		request.setAttribute("vo", swb);
		return "/platform/news/single";
	}

	/**
	 * 新闻公告分类
	 * 
	 * @param type
	 */
	@RequestMapping("/clist")
	public String clist(String type, HttpServletRequest request) {
		request.setAttribute("type", type);
		return "/platform/news/clist";
	}

	/**
	 * 新闻分类编辑
	 * 
	 * @param type
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/showAddNewsClass")
	public String showAddNewsClass(String type, String id, Model model) {
		model.addAttribute("ctype", type);
		model.addAttribute("id", id);
		model.addAttribute("fl", newsService.queryStairByPid(StringUtilsEX.ToInt(type)));
		if (StringUtilsEX.ToInt(id) != null)
			model.addAttribute("vo", newsService.queryClassById(StringUtilsEX.ToInt(id)));
		return "/platform/news/clistedit";
	}

	/**
	 * 公告列表编辑
	 * 
	 * @param type
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/slistedit")
	public String clistedit(String type, HttpServletRequest request, String id, String ctype) {
		SnewsWithBLOBs swb = new SnewsWithBLOBs();
		String fid = "", sid = "", tid = "", fullpath = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				swb = newsService.selSlistById(StringUtilsEX.ToInt(id));
				if (swb != null) {
					fullpath = swb.getEx3();

					if (fullpath.split(",").length > 0) {

						switch (fullpath.split(",").length) {
						case 1:
							fid = fullpath.split(",")[0];
							break;
						case 2:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							break;
						case 3:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							tid = fullpath.split(",")[2];
							break;
						default:
							break;
						}
					} else {
						fid = swb.getId().toString();
					}
				}

			}
			final int ffid = StringUtilsEX.ToInt(fid), ssid = StringUtilsEX.ToInt(sid);
			List<SnewsClass> slist = newsService.queryStair(StringUtilsEX.ToInt(ctype));
			request.setAttribute("fslist", slist.stream().filter(x -> x.getPid() == 0).collect(Collectors.toList()));
			request.setAttribute("sslist", slist.stream().filter(x -> x.getPid() == ffid).collect(Collectors.toList()));
			request.setAttribute("tslist", slist.stream().filter(x -> x.getPid() == ssid).collect(Collectors.toList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", swb);
		request.setAttribute("ctype", ctype);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("tid", tid);
		request.setAttribute("type", type);
		return "/platform/news/slistedit";
	}

	/**
	 * 新闻列表编辑
	 * 
	 * @param type
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/listedit")
	public String listedit(String type, HttpServletRequest request, String id, String ctype) {
		SnewsWithBLOBs swb = new SnewsWithBLOBs();
		String fid = "", sid = "", tid = "", fullpath = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				swb = newsService.selSlistById(StringUtilsEX.ToInt(id));
				if (swb != null) {
					fullpath = swb.getEx3();

					if (fullpath.split(",").length > 0) {

						switch (fullpath.split(",").length) {
						case 1:
							fid = fullpath.split(",")[0];
							break;
						case 2:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							break;
						case 3:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							tid = fullpath.split(",")[2];
							break;
						default:
							break;
						}
					} else {
						fid = swb.getId().toString();
					}
				}

			}
			final int ffid = StringUtilsEX.ToInt(fid), ssid = StringUtilsEX.ToInt(sid);
			List<SnewsClass> slist = newsService.queryStair(StringUtilsEX.ToInt(ctype));
			request.setAttribute("fslist", slist.stream().filter(x -> x.getPid() == 0).collect(Collectors.toList()));
			request.setAttribute("sslist", slist.stream().filter(x -> x.getPid() == ffid).collect(Collectors.toList()));
			request.setAttribute("tslist", slist.stream().filter(x -> x.getPid() == ssid).collect(Collectors.toList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", swb);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("tid", tid);
		request.setAttribute("ctype", ctype);
		request.setAttribute("type", type);
		return "/platform/news/listedit";
	}

	@RequestMapping("/test")
	public void test() {

	}

	/**
	 * 图片新闻列表
	 * 
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/newsimg")
	public String newsimg(String type, String ctype, HttpServletRequest request) {
		request.setAttribute("type", type);
		request.setAttribute("ctype", ctype);
		return "/platform/news/newsimg";
	}

	/**
	 * 图片新闻编辑
	 * 
	 * @param type
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/newsimgedit")
	public String newsimgedit(String type, HttpServletRequest request, String id, String ctype) {
		SnewsImgWithBLOBs swb = new SnewsImgWithBLOBs();
		List<SnewsRImgs> list = new ArrayList<SnewsRImgs>();
		String fid = "", sid = "", tid = "", fullpath = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				swb = newsService.selNewsImgById(StringUtilsEX.ToInt(id));
				list = newsService.selRNewsImgBySid(StringUtilsEX.ToInt(id));
				if (swb != null) {
					fullpath = swb.getEx3();

					if (fullpath.split(",").length > 0) {

						switch (fullpath.split(",").length) {
						case 1:
							fid = fullpath.split(",")[0];
							break;
						case 2:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							break;
						case 3:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							tid = fullpath.split(",")[2];
							break;
						default:
							break;
						}
					} else {
						fid = swb.getId().toString();
					}
				}

			}
			final int ffid = StringUtilsEX.ToInt(fid), ssid = StringUtilsEX.ToInt(sid);
			List<SnewsClass> slist = newsService.queryStair(StringUtilsEX.ToInt(ctype));
			request.setAttribute("fslist", slist.stream().filter(x -> x.getPid() == 0).collect(Collectors.toList()));
			request.setAttribute("sslist", slist.stream().filter(x -> x.getPid() == ffid).collect(Collectors.toList()));
			request.setAttribute("tslist", slist.stream().filter(x -> x.getPid() == ssid).collect(Collectors.toList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", swb);
		request.setAttribute("imglist", list);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("tid", tid);
		request.setAttribute("type", type);
		request.setAttribute("ctype", ctype);
		return "/platform/news/newsimgedit";
	}

	/**
	 * 地图新闻
	 * 
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/maplist")
	public String maplist(String type, String ctype, HttpServletRequest request) {
		request.setAttribute("type", type);
		request.setAttribute("ctype", ctype);
		return "/platform/news/maplist";
	}

	/**
	 * 编辑地图新闻
	 * 
	 * @param type
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/maplistedit")
	public String maplistedit(String type, HttpServletRequest request, String id, String ctype) {
		SnewsWithBLOBs swb = new SnewsWithBLOBs();
		String fid = "", sid = "", tid = "", fullpath = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				swb = newsService.selSlistById(StringUtilsEX.ToInt(id));
				if (swb != null) {
					fullpath = swb.getEx3();

					if (fullpath.split(",").length > 0) {

						switch (fullpath.split(",").length) {
						case 1:
							fid = fullpath.split(",")[0];
							break;
						case 2:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							break;
						case 3:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							tid = fullpath.split(",")[2];
							break;
						default:
							break;
						}
					} else {
						fid = swb.getId().toString();
					}
				}

			}
			final int ffid = StringUtilsEX.ToInt(fid), ssid = StringUtilsEX.ToInt(sid);
			List<SnewsClass> slist = newsService.queryStair(StringUtilsEX.ToInt(ctype));
			request.setAttribute("fslist", slist.stream().filter(x -> x.getPid() == 0).collect(Collectors.toList()));
			request.setAttribute("sslist", slist.stream().filter(x -> x.getPid() == ffid).collect(Collectors.toList()));
			request.setAttribute("tslist", slist.stream().filter(x -> x.getPid() == ssid).collect(Collectors.toList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", swb);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("tid", tid);
		request.setAttribute("type", type);
		request.setAttribute("ctype", ctype);
		return "/platform/news/maplistedit";
	}

	/**
	 * 地图图片新闻
	 * 
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/mapnewsimg")
	public String mapnewsimg(String type, String ctype, HttpServletRequest request) {
		request.setAttribute("type", type);
		request.setAttribute("ctype", ctype);
		return "/platform/news/mapnewsimg";
	}

	/**
	 * 地图图片新闻编辑
	 * 
	 * @param type
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/mapimgedit")
	public String mapimgedit(String type, HttpServletRequest request, String id, String ctype) {
		SnewsImgWithBLOBs swb = new SnewsImgWithBLOBs();
		List<SnewsRImgs> list = new ArrayList<SnewsRImgs>();
		String fid = "", sid = "", tid = "", fullpath = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				swb = newsService.selNewsImgById(StringUtilsEX.ToInt(id));
				list = newsService.selRNewsImgBySid(StringUtilsEX.ToInt(id));
				if (swb != null) {
					fullpath = swb.getEx3();

					if (fullpath.split(",").length > 0) {

						switch (fullpath.split(",").length) {
						case 1:
							fid = fullpath.split(",")[0];
							break;
						case 2:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							break;
						case 3:
							fid = fullpath.split(",")[0];
							sid = fullpath.split(",")[1];
							tid = fullpath.split(",")[2];
							break;
						default:
							break;
						}
					} else {
						fid = swb.getId().toString();
					}
				}

			}
			final int ffid = StringUtilsEX.ToInt(fid), ssid = StringUtilsEX.ToInt(sid);
			List<SnewsClass> slist = newsService.queryStair(StringUtilsEX.ToInt(ctype));
			request.setAttribute("fslist", slist.stream().filter(x -> x.getPid() == 0).collect(Collectors.toList()));
			request.setAttribute("sslist", slist.stream().filter(x -> x.getPid() == ffid).collect(Collectors.toList()));
			request.setAttribute("tslist", slist.stream().filter(x -> x.getPid() == ssid).collect(Collectors.toList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", swb);
		request.setAttribute("imglist", list);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("tid", tid);
		request.setAttribute("type", type);
		request.setAttribute("ctype", ctype);
		return "/platform/news/mapimgedit";
	}

}

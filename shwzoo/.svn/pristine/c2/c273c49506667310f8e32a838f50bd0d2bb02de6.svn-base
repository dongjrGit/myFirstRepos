package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.NewsProCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.CriteriaSnews;
import com.yinlian.wssc.web.po.NewsProduct;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.SnewsClassExample;
import com.yinlian.wssc.web.po.SnewsImgWithBLOBs;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.po.users_newsExample;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.users_newsService;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private ProvinceServcice provinceServcice;

	@Autowired
	private CityServcie cityServcie;

	@Autowired
	private AreaService areaService;
	
	@Autowired
	private SpuService spuService;
	
	@Autowired
	private users_newsService usernewsService;
	
	SessionUser user = null;

	/**
	 * 获取新闻分类
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/queryClassTree")
	@ResponseBody
	public ReusltItem queryClassTree(String type) {
		ReusltItem item = new ReusltItem();
		try {
			List<SnewsClassExample> list = newsService.queryClassTree(0, StringUtilsEX.ToInt(type));
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询分类信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("查询分类信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/queryClassTree");
		}
		return item;
	}

	/**
	 * 删除分类信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delNewsClassById")
	@ResponseBody
	public ReusltItem delNewsClassById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.delNewsClassById(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setDesc("删除成功!");
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除分类信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("删除分类信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/delNewsClassById");
		}
		return item;
	}

	/**
	 * 查询新闻分类子集信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryClassSub")
	@ResponseBody
	public ReusltItem queryClassSub(Integer pid, Integer ctype) {
		ReusltItem item = new ReusltItem();
		try {
			if (pid == null) {
				item.setCode(101);
				item.setDesc("父id不能为空");
				return item;
			}
			List<SnewsClass> list = newsService.querySon(pid, ctype);
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询子集信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("查询子集信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/queryClassSub");
		}
		return item;
	}

	/**
	 * 添加分类信息
	 * 
	 * @param id
	 * @param name
	 * @param parentID
	 * @param sort
	 * @param ctype
	 * @return
	 */
	@RequestMapping("/addNewsClass")
	@ResponseBody
	public ReusltItem addNewsClass(String id, String name, @RequestParam("parentID") Integer[] parentID, String sort,
			String ctype, String img) {
		ReusltItem item = new ReusltItem();
		try {
			SnewsClass snewsClass = new SnewsClass();
			snewsClass.setName(name);
			List<Integer> par = new ArrayList<Integer>();
			for (int i = 0; i < parentID.length; i++) {
				if (parentID[i] != 0) {
					par.add(parentID[i]);
				}
			}
			if (par.size() == 0) {
				snewsClass.setFpath("0");
				snewsClass.setPid(0);
			} else {
				snewsClass.setFpath(org.apache.commons.lang.StringUtils.join(par, ","));
				snewsClass.setPid(par.get(par.size() - 1));
			}
			if (sort == "" || sort == null) {
				snewsClass.setSort(0);
			} else {
				snewsClass.setSort(StringUtilsEX.ToInt(sort));
			}
			if (img != null || img != "") {
				snewsClass.setImgurl(img);
			}
			snewsClass.setCtype(StringUtilsEX.ToInt(ctype));
			newsService.addNewsClass(snewsClass);
			item.setCode(0);
			item.setDesc("保存成功");
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加分类信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("添加分类信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/queryClassSub");
		}
		return item;
	}

	/**
	 * 编辑分类信息
	 * 
	 * @param name
	 * @param parentID
	 * @param sort
	 * @param id
	 * @return
	 */
	@RequestMapping("/editNewsClass")
	@ResponseBody
	public ReusltItem editNewsClass(String name, @RequestParam("parentID") Integer[] parentID, Integer sort, String id,
			String img) {
		ReusltItem item = new ReusltItem();
		try {
			SnewsClass snewsClass = newsService.queryClassById(StringUtilsEX.ToInt(id));
			snewsClass.setName(name);
			List<Integer> par = new ArrayList<Integer>();
			for (int i = 0; i < parentID.length; i++) {
				if (parentID[i] != 0) {
					par.add(parentID[i]);
				}
			}
			if (par.size() == 0) {
				snewsClass.setFpath("0");
				snewsClass.setPid(0);
			} else {
				snewsClass.setFpath(org.apache.commons.lang.StringUtils.join(par, ","));
				snewsClass.setPid(par.get(par.size() - 1));
			}
			if (img != null || img != "") {
				snewsClass.setImgurl(img);
			}
			snewsClass.setSort(sort);
			newsService.editNewsClass(snewsClass);
			item.setCode(0);
			item.setDesc("保存成功");
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("编辑分类信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("编辑分类信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/editNewsClass");
		}
		return item;
	}

	/**
	 * 编辑关于我们
	 * 
	 * @param title
	 * @param content
	 * @param ctype
	 * @return
	 */
	@RequestMapping("/editAboutUs")
	@ResponseBody
	public ReusltItem editAboutUs(String id, String title, String content, String ctype) {
		ReusltItem item = new ReusltItem();
		try {
			SnewsWithBLOBs swb = new SnewsWithBLOBs();
		
			swb.setTitle(title);
			swb.setContent(content);
			swb.setCtype(StringUtilsEX.ToInt(ctype));
			if (newsService.selSingle(StringUtilsEX.ToInt(ctype)) == null) {
				newsService.addSingle(swb);
			} else {	
				swb.setId(StringUtilsEX.ToInt(id));
				newsService.editSingle(swb);
			}
			item.setCode(0);
			item.setDesc("保存成功");
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加关于我们信息异常：" + e.getMessage());
			LogHandle.error(LogType.AboutUs, MessageFormat.format("添加关于我们信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/editAboutUs");
		}
		return item;
	}

	/**
	 * 获取公告列表
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param title
	 * @param classid1
	 * @param classid2
	 * @param classid3
	 * @param starttime
	 * @param endtime
	 * @param ctype
	 * @return
	 */
	@RequestMapping("/querySlist")
	@ResponseBody
	public ReusltItem querySList(String pageindex, String pagesize, String title, String classid1, String classid2,
			String classid3, String starttime, String endtime, String ctype, String state) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			CriteriaSnews criteria = new CriteriaSnews();
			Integer cid1 = StringUtilsEX.ToInt(classid1);
			Integer cid2 = StringUtilsEX.ToInt(classid2);
			Integer cid3 = StringUtilsEX.ToInt(classid3);
			String fpath = "";
			if (cid1 != null && cid1 > 0) {
				fpath = classid1;
			}
			if (cid2 != null && cid2 > 0) {
				fpath = classid1 + "," + classid2;
			}
			if (cid3 != null && cid3 > 0) {
				fpath = classid1 + "," + classid2 + "," + classid3;
			}
			criteria.setState(StringUtilsEX.ToInt(state));
			criteria.setFpath(fpath);
			criteria.setTitle(title);
			criteria.setStartTime(starttime);
			criteria.setEndTime(endtime);
			criteria.setCtype(StringUtilsEX.ToInt(ctype));
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
			PageBean pageBean = newsService.querySlist(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取公告列表信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("获取公告列表信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/querySList");
		}
		return item;
	}

	/**
	 * 编辑公告列表
	 * 
	 * @param id
	 * @param name
	 * @param classid
	 * @param img
	 * @param content
	 * @param createtime
	 * @param type
	 * @return
	 */
	@RequestMapping("/editslist")
	@ResponseBody
	public ReusltItem editslist(String id, String name, String classid, String img, String content, String updatetime,
			String type, String state,String useplatform) {
		ReusltItem item = new ReusltItem();
		try {
			SnewsWithBLOBs swb = new SnewsWithBLOBs();
			if (!classid.equals("0")) {
				if (newsService.queryClassById(StringUtilsEX.ToInt(classid)).getFpath().equals("0")) {
					swb.setEx3(classid);
				} else {
					swb.setEx3(newsService.queryClassById(StringUtilsEX.ToInt(classid)).getFpath() + ","
							+ StringUtilsEX.ToInt(classid));
				}
			} else {
				swb.setEx3("");
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(updatetime)) {
				swb.setUpdatetime(new Date());
			} else {
				swb.setUpdatetime(StringUtilsEX.ToDate(updatetime));
			}
			swb.setEx7(useplatform);
			swb.setTitle(name);
			swb.setCreatetime(new Date());
			swb.setCid(StringUtilsEX.ToInt(classid));
			swb.setImgurl(img);
			swb.setContent(content);
			swb.setCtype(StringUtilsEX.ToInt(type));
			swb.setState(StringUtilsEX.ToInt(state));
			if (StringUtilsEX.ToInt(id) <= 0) {
				newsService.addslist(swb);
				item.setCode(0);
			} else {
				swb.setId(StringUtilsEX.ToInt(id));
				newsService.updateSlistById(swb);
				item.setCode(0);
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加公告信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("添加公告信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/editslist");
		}
		return item;
	}

	/**
	 * 删除公告列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delSlist")
	@ResponseBody
	public ReusltItem delSlist(String id) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.delSlist(StringUtilsEX.ToInt(id));
			users_newsExample news=new users_newsExample();
			news.setnewsid(StringUtilsEX.ToInt(id));
			usernewsService.deleteBynewsid(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setDesc("删除成功");
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除公告信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("删除公告信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/delSlist");
		}
		return item;
	}

	/**
	 * 新闻编辑
	 * 
	 * @param id
	 * @param name
	 * @param subtitle
	 * @param tabloid
	 * @param classid
	 * @param updatetime
	 * @param url
	 * @param img
	 * @param content
	 * @param tag
	 * @param keyword
	 * @param author
	 * @param sort
	 * @param isbloid
	 * @param ishead
	 * @param isindex
	 * @param isrecommend
	 * @param state
	 * @return
	 */
	@RequestMapping("/editlist")
	@ResponseBody
	public ReusltItem editlist(String id, String name, String subtitle, String tabloid, String classid,
			String updatetime, String url, String img, String content, String tag, String keyword, String author,
			String sort, String ishead, String isindex, String isrecommend, String istop, String state, String type,
			String select_province, String select_city, String select_area, String text_companyadress, String longitude,
			String latitude, String appcontent) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			SnewsWithBLOBs swb = new SnewsWithBLOBs();
			if (!classid.equals("0")) {
				if (newsService.queryClassById(StringUtilsEX.ToInt(classid)).getFpath().equals("0")) {
					swb.setEx3(classid);
				} else {
					swb.setEx3(newsService.queryClassById(StringUtilsEX.ToInt(classid)).getFpath() + ","
							+ StringUtilsEX.ToInt(classid));
				}
			} else {
				swb.setEx3("");
			}
			swb.setTitle(name);
			swb.setCid(StringUtilsEX.ToInt(classid));
			swb.setImgurl(img);
			swb.setContent(content);
			swb.setCtype(StringUtilsEX.ToInt(type));
			swb.setSubtitle(subtitle);
			swb.setTabloid(tabloid);
			swb.setUrl(url);
			swb.setTag(tag);
			swb.setKeyword(keyword);
			swb.setProvince(select_province);
			swb.setCity(select_city);
			swb.setArea(select_area);
			swb.setEx2(appcontent);
			swb.setEx4(text_companyadress);
			swb.setEx5(longitude);
			swb.setEx6(latitude);
			if (StringUtilsEX.IsNullOrWhiteSpace(author)) {
				swb.setAuthor(user.getLoginName());
			} else {
				swb.setAuthor(author);
			}
			swb.setSort(StringUtilsEX.ToInt(sort));
			swb.setTabloid(tabloid);
			if (StringUtilsEX.IsNullOrWhiteSpace(updatetime)) {
				swb.setUpdatetime(new Date());
			} else {
				swb.setUpdatetime(StringUtilsEX.ToDate(updatetime));
			}
			swb.setCreatetime(new Date());
			if (ishead == null || ishead == "") {
				swb.setIshead(1);
			} else {
				swb.setIshead(StringUtilsEX.ToInt(ishead));
			}
			if (isindex == null || isindex == "") {
				swb.setIsindex(1);
			} else {
				swb.setIsindex(StringUtilsEX.ToInt(isindex));
			}
			if (isrecommend == null || isrecommend == "") {
				swb.setIsrecommend(1);
			} else {
				swb.setIsrecommend(StringUtilsEX.ToInt(isrecommend));
			}
			if (istop == null || istop == "") {
				swb.setIstop(1);
			} else {
				swb.setIstop(StringUtilsEX.ToInt(istop));
			}
			swb.setState(StringUtilsEX.ToInt(state));
			swb.setCtype(StringUtilsEX.ToInt(type));
			if (StringUtilsEX.ToInt(id) <= 0) {
				newsService.addList(swb);
				item.setCode(0);
			} else {
				swb.setId(StringUtilsEX.ToInt(id));
				newsService.updateListById(swb);
				item.setCode(0);
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加新闻信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("添加新闻信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/editlist");
		}
		return item;
	}

	/**
	 * 更改发布状态
	 * 
	 * @param id
	 * @param isstate
	 * @return
	 */
	@RequestMapping("/setstate")
	@ResponseBody
	public ReusltItem setstate(String id, String isstate) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.setstate(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(isstate));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/setstate");
		}
		return item;
	}

	/**
	 * 更改头条状态
	 * 
	 * @param id
	 * @param ishead
	 * @return
	 */
	@RequestMapping("/sethead")
	@ResponseBody
	public ReusltItem sethead(String id, String ishead) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.sethead(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(ishead));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/sethead");
		}
		return item;
	}

	/**
	 * 更改首页状态
	 * 
	 * @param id
	 * @param isindex
	 * @return
	 */
	@RequestMapping("/setindex")
	@ResponseBody
	public ReusltItem setindex(String id, String isindex) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.setindex(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(isindex));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/sethead");
		}
		return item;
	}

	/**
	 * 更改推荐状态
	 * 
	 * @param id
	 * @param isrecommend
	 * @return
	 */
	@RequestMapping("/setrecommend")
	@ResponseBody
	public ReusltItem setrecommend(String id, String isrecommend) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.setrecommend(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(isrecommend));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/sethead");
		}
		return item;
	}

	/**
	 * 更改置顶状态
	 * 
	 * @param id
	 * @param istop
	 * @return
	 */
	@RequestMapping("/settop")
	@ResponseBody
	public ReusltItem settop(String id, String istop) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.settop(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(istop));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/sethead");
		}
		return item;
	}

	/**
	 * 获取新闻列表
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param title
	 * @param classid1
	 * @param classid2
	 * @param classid3
	 * @param starttime
	 * @param endtime
	 * @param ctype
	 * @param state
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public ReusltItem querylist(String pageindex, String pagesize, String title, String classid1, String classid2,
			String classid3, String starttime, String endtime, String ctype, String state, String province, String city,
			String area) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			CriteriaSnews criteria = new CriteriaSnews();
			Integer cid1 = StringUtilsEX.ToInt(classid1);
			Integer cid2 = StringUtilsEX.ToInt(classid2);
			Integer cid3 = StringUtilsEX.ToInt(classid3);
			String fpath = "";
			if (cid1 != null && cid1 > 0) {
				fpath = classid1;
			}
			if (cid2 != null && cid2 > 0) {
				fpath = classid1 + "," + classid2;
			}
			if (cid3 != null && cid3 > 0) {
				fpath = classid1 + "," + classid2 + "," + classid3;
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(state)) {
				criteria.setState(StringUtilsEX.ToInt(state));
			}
			criteria.setFpath(fpath);
			criteria.setTitle(title);
			criteria.setStartTime(starttime);
			criteria.setEndTime(endtime);
			criteria.setCtype(StringUtilsEX.ToInt(ctype));
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
			criteria.setProvince(province);
			if (province.equals("-1")) {
				criteria.setProvince("");
			}
			criteria.setCity(city);
			if (city.equals("-1")) {
				criteria.setCity("");
			}
			criteria.setArea(area);
			if (area.equals("-1")) {
				criteria.setArea("");
			}
			PageBean pageBean = newsService.querySlist(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取新闻列表信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("获取新闻列表信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/querylist");
		}
		return item;
	}

	/**
	 * 获取图片新闻列表
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param title
	 * @param classid1
	 * @param classid2
	 * @param classid3
	 * @param starttime
	 * @param endtime
	 * @param ctype
	 * @param state
	 * @return
	 */
	@RequestMapping("/querynewsimg")
	@ResponseBody
	public ReusltItem queryNewsImg(String pageindex, String pagesize, String title, String classid1, String classid2,
			String classid3, String starttime, String endtime, String ctype, String state, String province, String city,
			String area) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			CriteriaSnews criteria = new CriteriaSnews();
			Integer cid1 = StringUtilsEX.ToInt(classid1);
			Integer cid2 = StringUtilsEX.ToInt(classid2);
			Integer cid3 = StringUtilsEX.ToInt(classid3);
			String fpath = "";
			if (cid1 != null && cid1 > 0) {
				fpath = classid1;
			}
			if (cid2 != null && cid2 > 0) {
				fpath = classid1 + "," + classid2;
			}
			if (cid3 != null && cid3 > 0) {
				fpath = classid1 + "," + classid2 + "," + classid3;
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(state)) {
				criteria.setState(StringUtilsEX.ToInt(state));
			}
			criteria.setFpath(fpath);
			criteria.setTitle(title);
			criteria.setStartTime(starttime);
			criteria.setEndTime(endtime);
			criteria.setCtype(StringUtilsEX.ToInt(ctype));
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
			criteria.setProvince(province);
			if (province.equals("-1")) {
				criteria.setProvince("");
			}
			criteria.setCity(city);
			if (city.equals("-1")) {
				criteria.setCity("");
			}
			criteria.setArea(area);
			if (area.equals("-1")) {
				criteria.setArea("");
			}
			PageBean pageBean = newsService.queryNewsImg(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取图片新闻列表信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("获取图片新闻列表信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/querylist");
		}
		return item;
	}

	/**
	 * 更改图片新闻发布状态
	 * 
	 * @param id
	 * @param isstate
	 * @return
	 */
	@RequestMapping("/setimgstate")
	@ResponseBody
	public ReusltItem setimgstate(String id, String isstate) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.setimgstate(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(isstate));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改图片发布状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改图片发布状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/setstate");
		}
		return item;
	}

	/**
	 * 更改图片新闻头条状态
	 * 
	 * @param id
	 * @param ishead
	 * @return
	 */
	@RequestMapping("/setimghead")
	@ResponseBody
	public ReusltItem setimghead(String id, String ishead) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.setimghead(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(ishead));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改图片头条状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改图片头条状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/sethead");
		}
		return item;
	}

	/**
	 * 更改图片新闻首页状态
	 * 
	 * @param id
	 * @param isindex
	 * @return
	 */
	@RequestMapping("/setimgindex")
	@ResponseBody
	public ReusltItem setimgindex(String id, String isindex) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.setimgindex(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(isindex));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改图片首页状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改图片首页状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/sethead");
		}
		return item;
	}

	/**
	 * 更改图片新闻推荐状态
	 * 
	 * @param id
	 * @param isrecommend
	 * @return
	 */
	@RequestMapping("/setimgrecommend")
	@ResponseBody
	public ReusltItem setimgrecommend(String id, String isrecommend) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.setimgrecommend(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(isrecommend));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改图片推荐状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改图片推荐状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/sethead");
		}
		return item;
	}

	/**
	 * 更改图片新闻置顶状态
	 * 
	 * @param id
	 * @param istop
	 * @return
	 */
	@RequestMapping("/setimgtop")
	@ResponseBody
	public ReusltItem setimgtop(String id, String istop) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.setimgtop(StringUtilsEX.ToInt(id), StringUtilsEX.ToInt(istop));
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("修改图片置顶状态异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("修改图片置顶状态异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/sethead");
		}
		return item;
	}

	/**
	 * 删除图片新闻
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delnewsimg")
	@ResponseBody
	public ReusltItem delNewsImg(String id) {
		ReusltItem item = new ReusltItem();
		try {
			newsService.delNewsImg(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setDesc("删除成功");
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除图片新闻信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("删除图片新闻信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/delSlist");
		}
		return item;
	}

	/**
	 * 添加图片新闻
	 * 
	 * @param id
	 * @param name
	 * @param subtitle
	 * @param tabloid
	 * @param classid
	 * @param updatetime
	 * @param url
	 * @param img
	 * @param content
	 * @param tag
	 * @param keyword
	 * @param author
	 * @param sort
	 * @param ishead
	 * @param isindex
	 * @param isrecommend
	 * @param istop
	 * @param state
	 * @param type
	 * @return
	 */
	@RequestMapping("/editnewsimg")
	@ResponseBody
	public ReusltItem editNewsImg(String id, String name, String subtitle, String tabloid, String classid, String imgs,
			String updatetime, String url, String img, String content, String tag, String keyword, String author,
			String sort, String ishead, String isindex, String isrecommend, String istop, String state, String type,
			String select_province, String select_city, String select_area, String text_companyadress, String longitude,
			String latitude) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			SnewsImgWithBLOBs swb = new SnewsImgWithBLOBs();
			if (!classid.equals("0")) {
				if (newsService.queryClassById(StringUtilsEX.ToInt(classid)).getFpath().equals("0")) {
					swb.setEx3(classid);
				} else {
					swb.setEx3(newsService.queryClassById(StringUtilsEX.ToInt(classid)).getFpath() + ","
							+ StringUtilsEX.ToInt(classid));
				}
			} else {
				swb.setEx3("");
			}
			swb.setTitle(name);
			swb.setCid(StringUtilsEX.ToInt(classid));
			swb.setImgurl(img);
			swb.setContent(content);
			swb.setCtype(StringUtilsEX.ToInt(type));
			swb.setSubtitle(subtitle);
			swb.setTabloid(tabloid);
			swb.setUrl(url);
			swb.setTag(tag);
			swb.setKeyword(keyword);
			swb.setProvince(select_province);
			swb.setCity(select_city);
			swb.setArea(select_area);
			swb.setEx4(text_companyadress);
			swb.setEx5(longitude);
			swb.setEx6(latitude);
			if (StringUtilsEX.IsNullOrWhiteSpace(author)) {
				swb.setAuthor(user.getLoginName());
			} else {
				swb.setAuthor(author);
			}
			swb.setSort(StringUtilsEX.ToInt(sort));
			swb.setTabloid(tabloid);
			if (StringUtilsEX.IsNullOrWhiteSpace(updatetime)) {
				swb.setUpdatetime(new Date());
			} else {
				swb.setUpdatetime(StringUtilsEX.ToDate(updatetime));
			}
			swb.setCreatetime(new Date());
			if (ishead == null || ishead == "") {
				swb.setIshead(1);
			} else {
				swb.setIshead(StringUtilsEX.ToInt(ishead));
			}
			if (isindex == null || isindex == "") {
				swb.setIsindex(1);
			} else {
				swb.setIsindex(StringUtilsEX.ToInt(isindex));
			}
			if (isrecommend == null || isrecommend == "") {
				swb.setIsrecommend(1);
			} else {
				swb.setIsrecommend(StringUtilsEX.ToInt(isrecommend));
			}
			if (istop == null || istop == "") {
				swb.setIstop(1);
			} else {
				swb.setIstop(StringUtilsEX.ToInt(istop));
			}
			swb.setState(StringUtilsEX.ToInt(state));
			swb.setCtype(StringUtilsEX.ToInt(type));
			if (StringUtilsEX.ToInt(id) <= 0) {
				newsService.addNewsImg(swb, imgs);
				item.setCode(0);
			} else {
				swb.setId(StringUtilsEX.ToInt(id));
				newsService.updateNewsImgById(swb, imgs);
				item.setCode(0);
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加新闻信息异常：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("添加新闻信息异常! 异常信息:{0}", e.getMessage()),
					"/platform/news/editlist");
		}
		return item;
	}

	/**
	 * 获取城市列表
	 * 
	 * @param type
	 * @param code
	 * @return
	 */
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
			item.setCode(-900);
			item.setDesc("异常信息：e" + e.getMessage());
		}
		return item;
	}
	
	/**
	 * 获取商品
	 * @param name
	 * @return
	 */
	@RequestMapping("/getSpuName")
	public @ResponseBody ReusltItem getSpuName(String name) {
		ReusltItem item = new ReusltItem();
		try {
			item.setData(spuService.getSpuStartWithNames(name));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("异常信息：e" + e.getMessage());
		}
		return item;
	} 
	/**
	 * 添加新闻商品
	 * @param spuname
	 * @param spuid
	 * @param newsid
	 * @param orderby
	 * @return
	 */
	@RequestMapping("/saveNewsSpu")
	public @ResponseBody ReusltItem getSpuName(String spuname,String spuid,String newsid,Integer orderby) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(spuid)<0) {
				item.setCode(-101);
				item.setDesc("商品ID错误");
				return item;
			}
			if (StringUtilsEX.ToInt(newsid)<0) {
				item.setCode(-102);
				item.setDesc("新闻ID错误");
				return item;
			}
			
			SpuWithBLOBs spu=spuService.queryById(StringUtilsEX.ToInt(spuid));
			NewsProduct news=new NewsProduct();
			news.setNewsid(StringUtilsEX.ToInt(newsid));
			news.setSpuid(StringUtilsEX.ToInt(spuid));
			news.setSpuimg(spu.getImgurl());
			news.setSpuname(spuname);
			news.setSpuprice(spu.getPrice().floatValue());
			news.setType(0);
			news.setOrderby(orderby);
			if (newsService.insertNewsPro(news)>0) {
				item.setCode(0);
				item.setDesc("添加商品成功");
			}else{
				item.setCode(-200);
				item.setDesc("添加失败");
			}
			return item;
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("异常信息：e" + e.getMessage());
			LogHandle.error(LogType.news,"添加新闻信息异常! 异常信息:", e,"/platform/news/saveNewsSpu");
		}
		return item;
	} 
	/**
	 * 查看新闻商品
	 * @param spuname
	 * @param spuid
	 * @param newsid
	 * @param orderby
	 * @return
	 */
	@RequestMapping("/listnewsspu")
	public @ResponseBody ReusltItem listnewsspu(Integer page,Integer size,Integer newsid) {
		ReusltItem item = new ReusltItem();
		try {
			NewsProCriteria criteria=new NewsProCriteria();
			criteria.setNewsid(newsid);
			PageBean pageBean=newsService.queryProPage(criteria, page, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			return item;
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("异常信息：e" + e.getMessage());
			LogHandle.error(LogType.news,"查询新闻商品异常! 异常信息:", e,"/platform/news/listnewsspu");
		}
		return item;
	} 
	/**
	 * 删除新闻商品
	 * @param page
	 * @param size
	 * @param newsid
	 * @return
	 */
	@RequestMapping("/delnewsspu")
	public @ResponseBody ReusltItem delnewsspu(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id)<0) {
				item.setCode(-101);
				item.setDesc("新闻商品ID错误");
				return item;
			}
			
			if (newsService.delNewsSpu(StringUtilsEX.ToInt(id))>0) {
				item.setDesc("删除成功");
				item.setCode(0);
			}else{
				item.setDesc("删除失败");
				item.setCode(-200);
			}
			return item;
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("异常信息：e" + e.getMessage());
			LogHandle.error(LogType.news,"删除新闻商品异常! 异常信息:", e,"/platform/news/delnewsspu");
		}
		return item;
	} 
	
}

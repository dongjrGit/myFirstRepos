/*
 * @(#) GreenChinaVoewController.java 2016年11月7日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.view.pc.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.pc.dto.ProvinceDto;
import com.yinlian.pc.dto.TopicBig;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.CriteriaSnews;
import com.yinlian.wssc.web.po.CriteriaVHpSku;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.SnewsImgWithBLOBs;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.po.SpecialProtype;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.po.V_Hp_Sku;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.HighSpecialtyService;
import com.yinlian.wssc.web.service.HotCityService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.SpecialProTypeService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.TopicCriteria;


@Controller
@RequestMapping("/lszg")
@SuppressWarnings({"unused","unchecked"})
public class GreenChinaViewController {
	
	@Autowired
	private AdverisingService advertImgService;
	
	@Autowired
	private HotCityService hotCityService;
	

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private HighSpecialtyService highSpecialtyService;
	
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private ProvinceServcice provinceServcice;
	
	@Autowired
	private SpecialProTypeService specialprotypeService;
	
	
	/**
	 * 绿色中国
	 * 
	 * @author kh.wang
	 * @since 2016年11月7日
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index(Model model){
		try{
			//广告1
			List<Advertising> ad1List=advertImgService.getListByTypeAndDisplay(PageMarkType.绿色中国首页.getValue(), 1, WebSetEnum.pc.getValue());
			
			CriteriaTopic topicCriteria=new CriteriaTopic();
			
			//最新开馆
			topicCriteria.setSpmark(TopicMarkEnum.地方馆.getValue());
			topicCriteria.setType(TopicTypeEnum.商品.getValue());
			topicCriteria.setWebset(WebSetEnum.pc.getValue()+"");
			topicCriteria.setOrderByClause("id");
			topicCriteria.setSort("desc");
			List<Topic> zxkglist=(List<Topic>) topicService.queryTopicListByCriteria(topicCriteria, 1, 5).getBeanList();
			
			model.addAttribute("zxkglist", zxkglist);
			//热门城市
			topicCriteria.setHotis(1);
			List<Topic> rmcslist=(List<Topic>) topicService.queryTopicListByCriteria(topicCriteria, 1, 15).getBeanList();
			model.addAttribute("rmcslist", rmcslist);
			
			CriteriaSnews newscriteria = new CriteriaSnews();
			newscriteria.setOrderByClause("createtime");
			newscriteria.setSort("desc");
			newscriteria.setCtype(5);
			List<SnewsWithBLOBs> newslist=(List<SnewsWithBLOBs>) newsService.querySlist(newscriteria, 1, 4).getBeanList();
			
			//实时推荐
			CriteriaVHpSku vhpskucriteria = new CriteriaVHpSku();
			vhpskucriteria.setOrderByClause("sort");
			vhpskucriteria.setType(1);
			List<V_Hp_Sku> vhpskulist = (List<V_Hp_Sku>) highSpecialtyService.getList(vhpskucriteria, 1, 5).getBeanList();
			model.addAttribute("ad1list", ad1List);
			model.addAttribute("newslist", newslist);
			model.addAttribute("vhpskulist", vhpskulist);
			return "/template/pc/greenchina/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
	}
	
	/**
	 * 地方官
	 * @param model
	 * @param provinceid
	 * @return
	 */
	@RequestMapping("/dfg.html")
	public String dfg(Model model,Integer provinceid){
		try{
			TopicCriteria criteria=new TopicCriteria();
			criteria.setTopicmark(TopicMarkEnum.地方馆.getValue());
			criteria.setTopictype(TopicTypeEnum.商品.getValue());
			criteria.setWebset(WebSetEnum.pc.getValue()+"");
			criteria.setProvinceid(provinceid);
			//获取所有大区
			List<TopicBig> biglist=provinceServcice.findBigAll();
			//获取所有地方官专题
			List<Topic> topiclist=topicService.findTopicCriteria(criteria);
			//获取所有大区
			List<Province> provinces=provinceServcice.selectAll();
			/**
			 * 添加到指定大区专题中
			 */
			for (Topic topic : topiclist) {
				for (TopicBig big : biglist) {
					if (topic.getProvinceid()!= null&&big.getName().equals(provinces.stream().filter(x->x.getId().equals(topic.getProvinceid())).collect(Collectors.toList()).get(0).getBigarea())) {
						big.getList().add(topic);
					}
				}
			}
			Map<String, List<Province>> maplist=provinces.stream().collect(Collectors.groupingBy(Province::getBigarea));
			/**
			 * 过滤掉不存在专题内容的大区
			 */
			model.addAttribute("biglist",biglist);
			model.addAttribute("dqlist", maplist);
			/**
			 * 过滤掉不存在专题内容的大区
			 */
			model.addAttribute("biglist", biglist.stream().filter(t->t.getList().size()>0).collect(Collectors.toList()));
			
			/**
			 * 区域
			 */
			List<ProvinceDto> prolist=provinceServcice.findProBigAll();
			model.addAttribute("prolist", prolist);
			
			
			//广告1
			List<Advertising> ad1List=advertImgService.getListByTypeAndDisplay(PageMarkType.绿色中国地方官.getValue(), 1, WebSetEnum.pc.getValue());
			model.addAttribute("adverlist", ad1List);
			
			return "/template/pc/greenchina/dfg";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
	}
	/**
	 * 地方官详情
	 * @param topicid
	 * @param model
	 * @return
	 */
	@RequestMapping("/dfginfo.html")
	private String dfginfo(Integer topicid,Model model) {
		try {
			//广告1
			List<Advertising> ad1List=advertImgService.getListByTypeAndDisplay(PageMarkType.绿色中国地方官.getValue(), 1, WebSetEnum.pc.getValue());
			model.addAttribute("adverlist", ad1List);
			Topic topic=topicService.queryById(topicid);
			model.addAttribute("vo", topic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/template/pc/greenchina/dfginfo";
	}
	/**
	 * 特色大全
	 * @param model
	 * @param fl
	 * @param jg
	 * @param min
	 * @param max
	 * @return
	 */
	@RequestMapping("/tsdq.html")
	public String tsdq(Model model,Integer fl,String jg,String min,String max){
		try{
			TopicCriteria criteria=new TopicCriteria();
			criteria.setTopicmark(TopicMarkEnum.特色大全.getValue());
			criteria.setTopictype(TopicTypeEnum.专题分类.getValue());
			criteria.setWebset(WebSetEnum.pc.getValue()+"");
			criteria.setPagemark(PageMarkType.专题页.getValue());
			List<SpecialProtype> spelist=specialprotypeService.findByTopicCruteria(criteria);
			
			model.addAttribute("spelist", spelist);
			model.addAttribute("fl", fl);
			model.addAttribute("jg", jg);
			model.addAttribute("min", min);
			model.addAttribute("max", max);
			return "/template/pc/greenchina/tsdq";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
	}
	/**
	 * 绿色故事详情
	 * @return
	 */
	@RequestMapping("/lsgs.html")
	public String lsgs(){
		try{
			return "/template/pc/greenchina/lsgs";
			
		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}

	/**
	 * 绿色故事详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/lsgsinfo.html")
	public String lsgsinfo(Integer id,Model model){
		try{
			SnewsWithBLOBs news=newsService.getById(id);
			
			model.addAttribute("vo", news);
			return "/template/pc/greenchina/lsgsinfo";
		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}
	
	/**
	 * 绿色品牌详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/lsppinfo.html")
	public String lsppinfo(Integer id,Model model){
		try{
			SnewsImgWithBLOBs news=newsService.selNewsImgById(id);
			
			model.addAttribute("vo", news);
			return "/template/pc/greenchina/lsppinfo";
		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}
}

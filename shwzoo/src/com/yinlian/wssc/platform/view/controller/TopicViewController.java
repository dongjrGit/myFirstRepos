/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.TopicClassifyEnum;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.wssc.platform.vo.MemberVo;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.SpecialProtype;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.po.TopicRelate;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpecialProTypeService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicRelateService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 专题的后台页面控制类
 * TopicViewController.java
 * @author Liang.ma.s
 * @version $Id: TopicViewController.java, v 0.1 2016年4月7日 下午4:03:22 Administrator Exp $
 */
@Controller
@RequestMapping("/platform/topic")
public class TopicViewController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(TopicViewController.class);

    @Autowired
    private TopicService        topicService;

    @Autowired
    private TopicRelateService  topicRelateService;
    @Autowired
    private ShopService         shopService;

    @Autowired
    private SpuService          spuService;
    
    @Autowired
    private BrandService         brandService;
    
    @Autowired
    private CategoryService     categoryService;
    
    @Autowired
    private SpecialProTypeService specialProTypeService;
    
    @Autowired
    private ProvinceServcice provinceServcice;
    
    

    /**
     * 显示专题列表页面
     * 
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<MemberVo> list = new ArrayList<MemberVo>();
        for (int i = 0; i < TopicMarkEnum.values().length; i++) {
            MemberVo markVo = new MemberVo();
            markVo.setCode(TopicMarkEnum.values()[i].getValue());
            markVo.setName(TopicMarkEnum.values()[i].name());
            list.add(markVo);
        }
        model.addAttribute("marklist", list);
        return "platform/net/topic_list";
    }

    /**
     * 显示子专题列表页面
     * @param topicid
     * @param model
     * @return
     */
    @RequestMapping("/sublist")
    public String sublist(String topicid, Model model) {
        model.addAttribute("topicid", topicid);

        return "platform/net/topicClass_list";
    }

    /**
     * 显示添加页面
     * 
     * @return
     */
    @RequestMapping("/showadd")
    public String showadd() {
        return "platform/net/topic_edit";
    }

    /**
     * 显示子专题添加页面
     * @return
     */
    @RequestMapping("/showsubAdd")
    public String shwosubAdd(String topicid,String href, Model model) {
        model.addAttribute("topicid", topicid);
        return "platform/net/subtopic_edit";
    }
    
    /**
     * 子专题编辑添加
     * @param topicid 专题ID
     * @param href 返回路径
     * @param model
     * @return
     */
    @RequestMapping("/showSonAdd")
    public String showSonAdd(String topicid,String href, Model model){
    	 model.addAttribute("topicid", topicid);
    	 model.addAttribute("href", href);
         return "platform/net/topicClass_new_edit";
    }

    /**
     * 显示修改页面
     * 
     * @return
     */
    @RequestMapping("/showEdit")
    public String showEdit(Integer id, HttpServletRequest request) {
        try {
            Topic topic = topicService.queryById(id);
            request.setAttribute("topic", topic);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "platform/net/topic_edit";
    }

    /**
     * 显示子专题关联商品的修改页面
     * 
     * @return
     */
    @RequestMapping("/showSubEdit")
    public String showSubEdit(Integer id, Integer fid, Model model, HttpServletRequest request) {
        try {
            Topic topic = topicService.queryById(id);
            model.addAttribute("fid", fid);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "platform/net/subtopic_edit";
    }

    /**
     * 显示修改页面
     * 
     * @return
     */
    @RequestMapping("/showsubEdit")
    public String showsubEdit(Integer topicid, Model model) {

        model.addAttribute("topicid", topicid);
        return "platform/net/subtopic_edit";
    }

    /**
     * 显示商品专题管理列表的页面
     * 
     * @return
     */
    @RequestMapping("/showProList")
    public String showProList(String topicid, String spmark, String fatherid,String type, Model model) {

        try {
//			
//        	
//        	if (StringUtilsEX.ToInt(spmark) == TopicMarkEnum.精彩专题.getValue()) {
//                /*	model.addAttribute("topicid", topicid);
//                	model.addAttribute("spmark",spmark);*/
//                model.addAttribute("topicid", topicid);
//                model.addAttribute("type",type);
//
//                return "platform/net/topicClass_list";
//            }else 
//            if (StringUtilsEX.ToInt(spmark)==TopicMarkEnum.品牌街.getValue()) {
//            	List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.首页.getValue(), TopicMarkEnum.品牌街.getValue(),TopicTypeEnum.品牌.getValue(),WebSetEnum.pc.getValue()+","+WebSetEnum.app.getValue());
//				if (dtolist!=null&&dtolist.size()>0) {
//			        model.addAttribute("topicid", dtolist.get(0).getId());
//			        model.addAttribute("type",TopicTypeEnum.品牌.getValue());
//				}else{
//					Topic topic=new Topic();
//					topic.setType(TopicTypeEnum.品牌.getValue());
//					topic.setMark(TopicMarkEnum.品牌街.getValue());
//					topic.setWebset(WebSetEnum.pc.getValue()+","+WebSetEnum.app.getValue());
//					topic.setPagetag(PageMarkType.首页.getValue());
//					topic.setIsdelete(false);
//					topic.setStatus(0);
//					topic.setName("品牌街");
//					topic.setTitle("品牌街");
//					topic.setIssys(true);
//					topicService.insert(topic);
//	    			model.addAttribute("topicid", topic.getId());
//	    			model.addAttribute("type",TopicTypeEnum.品牌.getValue());
//				}
//    		}else{
//                model.addAttribute("topicid", topicid);
//                model.addAttribute("type",TopicTypeEnum.品牌.getValue());
//    		}
//            
        	model.addAttribute("topicid", topicid);
            model.addAttribute("type",type);
        	model.addAttribute("fatherid", fatherid);
		} catch (Exception e) {
		}
        return "platform/net/topic_proSpecialList";
    }
    
    
    
   /**
    * 子专题
    * @param topicid 专题ID
    * @param type 专题类型
    * @param href 返回连接
    * @return
    */
    @RequestMapping("/showSonTopic")
    public String showSonTopic(String topicid,String type,String href,Model model){
    	model.addAttribute("topicid", topicid);
    	model.addAttribute("type", type);
    	model.addAttribute("href", href);
    	return "platform/net/topicClass_new_list";
    }
    
    
    @RequestMapping("/showProListCustom")
    public String showProListCustom(String topicid, String spmark, String fatherid,String type, Model model) {

        try {
            if (StringUtilsEX.ToInt(spmark)==TopicMarkEnum.品牌街.getValue()) {
            	List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.首页.getValue(), TopicMarkEnum.品牌街.getValue(),TopicTypeEnum.品牌.getValue(),WebSetEnum.pc.getValue()+","+WebSetEnum.app.getValue());
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
			        model.addAttribute("type",TopicTypeEnum.品牌.getValue());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.品牌.getValue());
					topic.setMark(TopicMarkEnum.品牌街.getValue());
					topic.setWebset(WebSetEnum.pc.getValue()+","+WebSetEnum.app.getValue());
					topic.setPagetag(PageMarkType.首页.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("品牌街");
					topic.setTitle("品牌街");
					topic.setIssys(true);
					topicService.insert(topic);
	    			model.addAttribute("topicid", topic.getId());
	    			model.addAttribute("type",TopicTypeEnum.品牌.getValue());
				}
    		}else{
                model.addAttribute("topicid", topicid);
                model.addAttribute("type",TopicTypeEnum.品牌.getValue());
    		}
            
            model.addAttribute("fatherid", fatherid);
		} catch (Exception e) {
		}
        return "platform/net/topic_custom_proSpecialList";
    }
    

    /**
     * 显示商品专题管理编辑页面
     * 
     * @return
     */
    @RequestMapping("/showProEdit")
    public String showProEdit(String topicid, String spmark,String fatherid, String id,String type, Model model) {
        try {
            model.addAttribute("topicid", topicid);
            model.addAttribute("spmark", spmark);
            model.addAttribute("type", type);
            model.addAttribute("fatherid",fatherid);
            if(id!=null){
            	 TopicRelate topicRelate = topicRelateService.queryById(StringUtilsEX.ToInt(id));
            	 model.addAttribute("data", topicRelate);
                 int relateid = topicRelate.getRelatedid();
                 int ty = StringUtilsEX.ToInt(type);
                 if (TopicTypeEnum.分类.getValue()==ty ) {
     				Category category = categoryService.selectByPrimaryKey(relateid);
     				String name = category.getName();
     				model.addAttribute("name", name);
     			} if (TopicTypeEnum.商品.getValue()==ty) {
     				Spu spu = spuService.queryById(relateid);
     				String name =spu.getName();
     				model.addAttribute("name", name);
     				model.addAttribute("relateid",relateid);
     			}if (TopicTypeEnum.店铺.getValue()==ty) {
     				Shop shop = shopService.queryById(relateid);
     				String name = shop.getName();
     				model.addAttribute("name", name);
     			}if (TopicTypeEnum.品牌.getValue()==ty) {
     				Brand brand = brandService.selectById(relateid);
     				String name = brand.getName();
     				model.addAttribute("relateid",relateid);
     				model.addAttribute("name", name);
     			}
            }
            Topic topic = topicService.queryById(StringUtilsEX.ToInt(topicid));
            if (topic != null) {
            	
            	int _type=topic.getType();
    			if(_type==TopicTypeEnum.商品.getValue()){
    				List<Spu> list=spuService.queryAll(
    						ProStatusEnum.上架.getValue());
    				model.addAttribute("list", list);
    			}else if(_type==TopicTypeEnum.店铺.getValue()){
    				List<Shop> list=shopService.queryAll();
    				model.addAttribute("list", list);
    			}else if(_type==TopicTypeEnum.品牌.getValue()){
    				List<Brand> list=brandService.selectAll();
    				model.addAttribute("list", list);
    			}else if(_type==TopicTypeEnum.分类.getValue()){
    				List<Category> list=categoryService.queryFirstClass();
    				model.addAttribute("list", list);
    			}
                /*int mark = topic.getMark();
                if (mark == TopicMarkEnum.发现好店.getValue()) {
                    List<Shop> list = shopService.queryAll();
                    model.addAttribute("list", list);
                } else if (mark == TopicMarkEnum.发现好货.getValue()
                           || mark == TopicMarkEnum.新品上市.getValue()) {
                    List<Spu> list = spuService.queryAll();
                    model.addAttribute("list", list);
                }*/
            }
        } catch (Exception e) {

            logger.error("", e);
        }
        return "platform/net/topic_proSpecialEdit";
    }
    
    
    @RequestMapping("/rmcslist")
    public String rmcslist(Model model){
    	 List<MemberVo> list = new ArrayList<MemberVo>();
         for (int i = 0; i < TopicMarkEnum.values().length; i++) {
             MemberVo markVo = new MemberVo();
             markVo.setCode(TopicMarkEnum.values()[i].getValue());
             markVo.setName(TopicMarkEnum.values()[i].name());
             list.add(markVo);
         }

         model.addAttribute("marklist", list);
    	model.addAttribute("topicmark", TopicMarkEnum.地方馆.getValue());
    	return "platform/net/topic_rmcs_list";
    }
    
    /**
     * 显示修改页面
     * 
     * @return
     */
    @RequestMapping("/rmcsEdit")
    public String rmcsEdit(Integer id, HttpServletRequest request) {
        try {
            Topic topic = topicService.queryById(id);
            List<Province> list= provinceServcice.selectAll();

            request.setAttribute("provincelist", list);
            request.setAttribute("topic", topic);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "platform/net/topic_rmcs_edit";
    }
    
    /**
     * 显示修改页面
     * 
     * @return
     */
    @RequestMapping("/rmcsAdd")
    public String rmcsEdit(Model model) {
       
		try {
			 List<Province> list = provinceServcice.selectAll();

	        model.addAttribute("provincelist", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "platform/net/topic_rmcs_edit";
    }
    
    /**
     * 显示商品专题管理列表的页面
     * 
     * @return
     */
    @RequestMapping("/rmcsProList")
    public String rmcsProList(String topicid, String spmark, String fatherid,String type, Model model) {

        if (StringUtilsEX.ToInt(spmark) == TopicMarkEnum.精彩专题.getValue()) {
            /*	model.addAttribute("topicid", topicid);
            	model.addAttribute("spmark",spmark);*/
            model.addAttribute("topicid", topicid);
            model.addAttribute("type",type);

            return "platform/net/topicClass_list";
        }
        model.addAttribute("fatherid", fatherid);
        model.addAttribute("topicid", topicid);
        model.addAttribute("type",type);
        return "platform/net/topic_rmcs_proSpecialList";
    }
    
    /**
     * 显示商品专题管理编辑页面
     * 
     * @return
     */
    @RequestMapping("/rmcsProEdit")
    public String rmcsProEdit(String topicid, String spmark,String fatherid, String id,String type, Model model) {
        try {
            model.addAttribute("topicid", topicid);
            model.addAttribute("spmark", spmark);
            model.addAttribute("type", type);
            model.addAttribute("fatherid",fatherid);
            if(id!=null){
            	 TopicRelate topicRelate = topicRelateService.queryById(StringUtilsEX.ToInt(id));
            	 model.addAttribute("data", topicRelate);
                 int relateid = topicRelate.getRelatedid();
                 int ty = StringUtilsEX.ToInt(type);
                 if (TopicTypeEnum.分类.getValue()==ty ) {
     				Category category = categoryService.selectByPrimaryKey(relateid);
     				String name = category.getName();
     				model.addAttribute("name", name);
     			} if (TopicTypeEnum.商品.getValue()==ty) {
     				Spu spu = spuService.queryById(relateid);
     				String name =spu.getName();
     				model.addAttribute("name", name);
     				model.addAttribute("relateid",relateid);
     			}if (TopicTypeEnum.店铺.getValue()==ty) {
     				Shop shop = shopService.queryById(relateid);
     				String name = shop.getName();
     				model.addAttribute("name", name);
     			}if (TopicTypeEnum.品牌.getValue()==ty) {
     				Brand brand = brandService.selectById(relateid);
     				String name = brand.getName();
     				model.addAttribute("name", name);
     			}
            }
            Topic topic = topicService.queryById(StringUtilsEX.ToInt(topicid));
            if (topic != null) {
            	
            	int _type=topic.getType();
    			if(_type==TopicTypeEnum.商品.getValue()){
    				List<Spu> list=spuService.queryAll(
    						ProStatusEnum.上架.getValue());
    				model.addAttribute("list", list);
    			}else if(_type==TopicTypeEnum.店铺.getValue()){
    				List<Shop> list=shopService.queryAll();
    				model.addAttribute("list", list);
    			}else if(_type==TopicTypeEnum.品牌.getValue()){
    				List<Brand> list=brandService.selectAll();
    				model.addAttribute("list", list);
    			}else if(_type==TopicTypeEnum.分类.getValue()){
    				List<Category> list=categoryService.queryFirstClass();
    				model.addAttribute("list", list);
    			}
                /*int mark = topic.getMark();
                if (mark == TopicMarkEnum.发现好店.getValue()) {
                    List<Shop> list = shopService.queryAll();
                    model.addAttribute("list", list);
                } else if (mark == TopicMarkEnum.发现好货.getValue()
                           || mark == TopicMarkEnum.新品上市.getValue()) {
                    List<Spu> list = spuService.queryAll();
                    model.addAttribute("list", list);
                }*/
            }
        } catch (Exception e) {

            logger.error("", e);
        }
        return "platform/net/topic_rmcs_proSpecialEdit";
    }
    
    
    /**
     * 特色大全商品管理列表的页面
     * 
     * @param pagetype 1.特色大全2.超值特卖3.爆款好货4.限时抢购5.新品上市6.每日鲜7.包邮直送pc8.包邮直送wap9.每日鲜10.独家品牌11.值得购
     * @return
     */
    @RequestMapping("/tsdqprolist")
    public String tsdqprolist(Model model,Integer pagetype) {
    	try {
    		if (pagetype==1) {
	    		List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.专题页.getValue(), TopicMarkEnum.特色大全.getValue(),TopicTypeEnum.专题分类.getValue(),WebSetEnum.pc.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.专题分类.getValue());
					topic.setMark(TopicMarkEnum.特色大全.getValue());
					topic.setWebset(WebSetEnum.pc.getValue()+"");
					topic.setPagetag(PageMarkType.专题页.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("特色大全");
					topic.setTitle("特色大全");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("type", TopicTypeEnum.专题分类.getValue());
			}else if (pagetype==2) {
				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.首页.getValue(), TopicMarkEnum.超值特卖.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.pc.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.超值特卖.getValue());
					topic.setWebset(WebSetEnum.pc.getValue()+"");
					topic.setPagetag(PageMarkType.首页.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("超值特卖");
					topic.setTitle("超值特卖");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 3) {
				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.首页.getValue(), TopicMarkEnum.爆款好货.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.pc.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.爆款好货.getValue());
					topic.setWebset(WebSetEnum.pc.getValue()+"");
					topic.setPagetag(PageMarkType.首页.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("爆款好货");
					topic.setTitle("爆款好货");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 4) {
				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.首页.getValue(), TopicMarkEnum.限时抢购.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.pc.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.限时抢购.getValue());
					topic.setWebset(WebSetEnum.pc.getValue()+"");
					topic.setPagetag(PageMarkType.首页.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("限时抢购");
					topic.setTitle("限时抢购");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 5) {
				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.首页.getValue(), TopicMarkEnum.新品尝鲜.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.pc.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.新品尝鲜.getValue());
					topic.setWebset(WebSetEnum.pc.getValue()+","+WebSetEnum.app.getValue());
					topic.setPagetag(PageMarkType.首页.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("新品上市");
					topic.setTitle("新品上市");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 6) {
				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.每日鲜.getValue(), TopicMarkEnum.每日鲜.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.app.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.每日鲜.getValue());
					topic.setWebset(WebSetEnum.app.getValue()+"");
					topic.setPagetag(PageMarkType.每日鲜.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("每日鲜");
					topic.setTitle("每日鲜");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 7) {
				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.包邮直送.getValue(), TopicMarkEnum.包邮直送.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.pc.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.包邮直送.getValue());
					topic.setWebset(WebSetEnum.pc.getValue()+"");
					topic.setPagetag(PageMarkType.包邮直送.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("包邮直送");
					topic.setTitle("包邮直送");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("isby", "true");//是否包邮字段
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 8) {
				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.包邮直送.getValue(), TopicMarkEnum.包邮直送.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.app.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.包邮直送.getValue());
					topic.setWebset(WebSetEnum.app.getValue()+"");
					topic.setPagetag(PageMarkType.包邮直送.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("包邮直送");
					topic.setTitle("包邮直送");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("isby", "true");//是否包邮字段
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 9) {
 				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.每日鲜.getValue(), TopicMarkEnum.每日鲜.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.pc.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.每日鲜.getValue());
					topic.setWebset(WebSetEnum.pc.getValue()+"");
					topic.setPagetag(PageMarkType.每日鲜.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("每日鲜");
					topic.setTitle("每日鲜");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("isby", "true");
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 10) {
 				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.专题页.getValue(), TopicMarkEnum.独家品牌.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.wap.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.独家品牌.getValue());
					topic.setWebset(WebSetEnum.wap.getValue()+","+WebSetEnum.app.getValue());
					topic.setPagetag(PageMarkType.专题页.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("独家品牌");
					topic.setTitle("独家品牌");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 11) {
 				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.专题页.getValue(), TopicMarkEnum.值得购.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.wap.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.值得购.getValue());
					topic.setWebset(WebSetEnum.wap.getValue()+","+WebSetEnum.app.getValue());
					topic.setPagetag(PageMarkType.专题页.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("值得购");
					topic.setTitle("值得购");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}else if (pagetype == 22) {
 				List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.商品列表页.getValue(), TopicMarkEnum.热卖推荐.getValue(),TopicTypeEnum.商品.getValue(),WebSetEnum.pc.getValue()+"");
				if (dtolist!=null&&dtolist.size()>0) {
			        model.addAttribute("topicid", dtolist.get(0).getId());
				}else{
					Topic topic=new Topic();
					topic.setType(TopicTypeEnum.商品.getValue());
					topic.setMark(TopicMarkEnum.热卖推荐.getValue());
					topic.setWebset(WebSetEnum.pc.getValue()+"");
					topic.setPagetag(PageMarkType.商品列表页.getValue());
					topic.setIsdelete(false);
					topic.setStatus(0);
					topic.setName("热卖推荐");
					topic.setTitle("热卖推荐");
					topic.setIssys(true);
					topicService.insert(topic);
					model.addAttribute("topicid", topic.getId());
				}
				model.addAttribute("type", TopicTypeEnum.商品.getValue());
			}
    		
    		model.addAttribute("pagetype", pagetype);
			
    	} catch (Exception e) {
			e.printStackTrace();
		}
        return "platform/net/topic_tsdq_proSpecialList";
    }
    
    /**
     * 显示商品专题管理编辑页面
     * 
     * @return
     */
    @RequestMapping("/tsdqProEdit")
    public String tsdqProEdit(String topicid, String spmark,String fatherid, String id,String type, Model model,String isby) {
        try {
            model.addAttribute("topicid", topicid);
			model.addAttribute("type", type);
            model.addAttribute("fatherid",fatherid);
            model.addAttribute("isby",isby);
            if(id!=null){
            	 TopicRelate topicRelate = topicRelateService.queryById(StringUtilsEX.ToInt(id));
            	 model.addAttribute("data", topicRelate);
            	 model.addAttribute("classifyName", topicRelate.getClassifyname());
                 int relateid = topicRelate.getRelatedid();
                 int ty = StringUtilsEX.ToInt(type);
                 if (TopicTypeEnum.分类.getValue()==ty ) {
                	 Spu spu = spuService.queryById(relateid);
      				String name =spu.getName();
      				model.addAttribute("name", name);
      				model.addAttribute("relateid",relateid);
     			} if (TopicTypeEnum.商品.getValue()==ty||TopicTypeEnum.专题分类.getValue()==ty) {
     				Spu spu = spuService.queryById(relateid);
     				String name =spu.getName();
     				model.addAttribute("name", name);
     				model.addAttribute("relateid",relateid);
     			}
            }
            Topic topic = topicService.queryById(StringUtilsEX.ToInt(topicid));
            if (topic != null) {

                model.addAttribute("spmark", topic.getMark());
            	int _type=topic.getType();
    			if(_type==TopicTypeEnum.商品.getValue()){
    				List<Spu> list=spuService.queryAll(
    						ProStatusEnum.上架.getValue());
    				model.addAttribute("list", list);
    			}else if(_type==TopicTypeEnum.店铺.getValue()){
    				List<Shop> list=shopService.queryAll();
    				model.addAttribute("list", list);
    			}else if(_type==TopicTypeEnum.品牌.getValue()){
    				List<Brand> list=brandService.selectAll();
    				model.addAttribute("list", list);
    			}else if(_type==TopicTypeEnum.分类.getValue()){
    				List<Category> list=categoryService.queryFirstClass();
    				model.addAttribute("list", list);
    			}
                /*int mark = topic.getMark();
                if (mark == TopicMarkEnum.发现好店.getValue()) {
                    List<Shop> list = shopService.queryAll();
                    model.addAttribute("list", list);
                } else if (mark == TopicMarkEnum.发现好货.getValue()
                           || mark == TopicMarkEnum.新品上市.getValue()) {
                    List<Spu> list = spuService.queryAll();
                    model.addAttribute("list", list);
                }*/
            }
        } catch (Exception e) {

            logger.error("", e);
        }
        return "platform/net/topic_tsdq_proSpecialEdit";
    }
    
    /**
     * 显示商品专题分类管理列表的页面
     * @param topicid
     * @param model
     * @return
     */
    @RequestMapping("/showSpeProList")
    public String showSpeProList(Model model) {
    	try {
    		List<Topic> dtolist=topicService.findTopicCriteria(PageMarkType.专题页.getValue(), TopicMarkEnum.特色大全.getValue(),TopicTypeEnum.专题分类.getValue(),WebSetEnum.pc.getValue()+"");
    		if (dtolist!=null&&dtolist.size()>0) {
    	        model.addAttribute("topicid", dtolist.get(0).getId());
    		}else{
    			Topic topic=new Topic();
    			topic.setType(TopicTypeEnum.专题分类.getValue());
    			topic.setMark(TopicMarkEnum.特色大全.getValue());
    			topic.setWebset(WebSetEnum.pc.getValue()+"");
    			topic.setPagetag(PageMarkType.专题页.getValue());
    			topic.setIsdelete(false);
    			topic.setStatus(0);
    			topic.setName("特色大全");
    			topic.setTitle("特色大全");
    			topicService.insert(topic);
    			model.addAttribute("topicid", topic.getId());
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	model.addAttribute("type", 5);
        return "platform/net/topic_classList";
    }
    
    /**
     * 显示专题商品分类的修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showSpeProEdit")
    public String showSpeProEdit(Integer id,Model model,Integer special) {
        try {
        	List<MemberVo> list = new ArrayList<MemberVo>();
            for (int i = 0; i < TopicClassifyEnum.values().length; i++) {
                MemberVo markVo = new MemberVo();
                markVo.setCode(TopicClassifyEnum.values()[i].getValue());
                markVo.setName(TopicClassifyEnum.values()[i].name());
                list.add(markVo);
            }
            model.addAttribute("marklist", list);
        	SpecialProtype specialProtype=specialProTypeService.getSpecialProtype(id);
        	model.addAttribute("specialProtype",specialProtype);
        	model.addAttribute("id",id);
        	model.addAttribute("special",special);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "platform/net/topic_classListEdit";
    }
    /* @RequestMapping("/showRelateEdit")
     public String showRelateEdit(Integer id, HttpServletRequest request) {
         try {
             TopicRelate topicRelate = topicRelateService.queryById(id);
             request.setAttribute("topicRelate", topicRelate);
         } catch (Exception e) {
             logger.error("", e);
         }
         return "platform/net/topic_proSpecialEdit";
     }*/
}

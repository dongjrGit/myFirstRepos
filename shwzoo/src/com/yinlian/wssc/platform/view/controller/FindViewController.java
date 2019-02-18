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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.FindTypeEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.FindRelation;
import com.yinlian.wssc.web.po.Findrecord;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.po.TopicRelate;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.FindRecordService;
import com.yinlian.wssc.web.service.FindRelationService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaFind;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 
 * @author wl
 *
 */
@Controller
@RequestMapping("/platform/find")
public class FindViewController {
    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(FindViewController.class);
    @Autowired
    private FindRecordService   findrecordservice;
    
    @Autowired
    private  SpuService    spuService;
    
    @Autowired
    private  FindRelationService   findRelationService;
    
    @Autowired
    private  TopicService    topicService;
    
    
    @Autowired
    private  ArticlesService   articlesService;
    
    

    /**
     * 图片
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRecord_list")
    public String findrecord_list(HttpServletRequest request) throws Exception {
        List<Findrecord> list = findrecordservice.selectimg();
        request.setAttribute("Findimg", list);
        return "platform/product/FindRecord_list";
    }

    /**
     * 显示发现图片列表页面
     * @return
     */
    @RequestMapping("/showFindRecord")
    public String showFindRecord() {

        return "platform/FindRecord/RecordImg";
    }

    /**
     * 显示图片添加页面
     * @return
     */
    @RequestMapping("/showFindRecordAdd")
    public String showFindRecordAdd(String id, Model model) {
        List<Findrecord> list = null;
        try {
            list = findrecordservice.queryAllrecord();
        } catch (Exception e) {
            logger.error("", e);
        }
        model.addAttribute("list", list);
        return "platform/FindRecord/FindrecordAdd";
    }

    


    /**
     * 编辑查看
     */
    @RequestMapping("/editFind")
    public String editFind(@RequestParam("id") Integer id, HttpServletRequest request) {
        Findrecord findrecord = new Findrecord();
        try {
            findrecord = findrecordservice.selectFind(id);
        } catch (Exception e) {
         
            e.printStackTrace();
        }
        request.setAttribute("findid", id);
        request.setAttribute("findrecord", findrecord);
        return "platform/FindRecord/FindrecordAdd";
    }

   
   
    /**
     * 显示发现关联信息
     * @param findid
     * @param type
     * @param model
     * @return
     */
    @RequestMapping("/showFindRelateList")
    public String showFindRelateList(String findid, String type,Model model) {

        model.addAttribute("findid", findid);
        model.addAttribute("type",type);
        return "platform/FindRecord/find_relateList";
    }
    
    /**
     * 显示发现管理编辑页面
     * 
     * @return
     */
    @RequestMapping("/showFindEdit")
    public String showProEdit(String findid,String id,String type, Model model) {
        try {
            model.addAttribute("findid", findid);
            model.addAttribute("type", type);
            if(id!=null){
            	 FindRelation  findRelation=findRelationService.queryInfoById(StringUtilsEX.ToInt(id));
            	 model.addAttribute("data", findRelation);
            	 
                 int relateid = findRelation.getRelationid();
                 model.addAttribute("relateid",relateid);
                 int ty = StringUtilsEX.ToInt(type);
                 
                 if (FindTypeEnum.专题.getValue()==ty ) {
                	 
     				Topic topic=topicService.queryById(relateid);
     				if(topic!=null){
     					String name = topic.getTitle();
     					model.addAttribute("name", name);
     				}
     				
     			} if (FindTypeEnum.店铺动态.getValue()==ty) {
     				
     				Spu spu = spuService.queryById(relateid);
     				String name =spu.getName();
     				model.addAttribute("name", name);
     				
     			}if (FindTypeEnum.资讯文章.getValue()==ty) {
     				
     				Articles articles = articlesService.selectByID(relateid);
     				String name = articles.getTitle();
     				model.addAttribute("name", name);
     				
     			}
            }
            Findrecord  find=findrecordservice.selectFind(StringUtilsEX.ToInt(findid));
          //  Topic topic = topicService.queryById(StringUtilsEX.ToInt(topicid));
            if (find != null) {
            	
            	int _type=find.getType();
    			if(_type==FindTypeEnum.店铺动态.getValue()){
    				
    				List<Spu> list=spuService.queryAll(
    						ProStatusEnum.上架.getValue());
    				model.addAttribute("list", list);
    				
    			}else if(_type==FindTypeEnum.专题.getValue()){
    				
    				List<Topic> list=topicService.queryAll();
    				model.addAttribute("list", list);
    				
    			}else if(_type==FindTypeEnum.资讯文章.getValue()){
    				
    				List<Articles> list=articlesService.findAll();
    				model.addAttribute("list", list);
    			}
            }
        } catch (Exception e) {

            logger.error("", e);
        }
        return "platform/FindRecord/find_relateEdit";
    }


}

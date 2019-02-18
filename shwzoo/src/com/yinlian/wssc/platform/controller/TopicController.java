/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.CriteriaSpecialProType;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.SpecialProtype;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.po.TopicRelate;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpecialProTypeService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicRelateService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 后台的专题的控制器
 * TopicController.java
 * @author Liang.ma.s
 * @version $Id: TopicController.java, v 0.1 2016年4月7日 下午4:04:35 Administrator Exp $
 */
@RestController
@RequestMapping("/platform/topic")
public class TopicController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService        topicService;
    @Autowired
    private TopicRelateService  topicRelateService;

    @Autowired
    private ShopService         shopService;

    @Autowired
    private SpuService          spuService;

    @Autowired
    private CategoryService     categoryService;

    @Autowired
    private BrandService        brandService;
    
    SessionUser user = null;
    @Autowired
    private OperaterecordsService operaterecordsService ;

    @Autowired
    private SpecialProTypeService specialProTypeService;
    /**
     * 添加子专题
     * @param page
     * @param size
     * @param type
     * @param title
     * @return
     */
    @RequestMapping("/addSubTopic")
    public ReusltItem addSubTopic(String name, String type, String mark, String img,
                                  String subspurl, String spfloor, String sort, String status,
                                  String desc, String fatherid) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
                item.setCode(-101);
                item.setDesc("标题标题(name)不能为空！");
                return item;
            }
            if (StringUtilsEX.ToInt(type) == 0) {
                item.setCode(-102);
                item.setDesc("请选择专题类型");
                return item;
            }
            /*if (StringUtilsEX.ToInt(mark)==0) {
            	item.setCode(-103);
            	item.setDesc("请选择专题标识");
            	return item;
            }*/
            if (StringUtilsEX.IsNullOrWhiteSpace(sort)) {
                item.setCode(-106);
                item.setDesc("子专题排序不能为空(sort)不能为空！");
                return item;
            }
            if (StringUtilsEX.IsNullOrWhiteSpace(img)) {
                item.setCode(-104);
                item.setDesc("请上传图片");
                return item;
            }

            if (StringUtilsEX.IsNullOrWhiteSpace(fatherid)) {
                item.setCode(-105);
                item.setDesc("fatherid不能为空");
                return item;
            }

            Topic topic1 = new Topic();
            topic1.setType(StringUtilsEX.ToInt(type));
            topic1.setMark(StringUtilsEX.ToInt(mark));
            topic1.setDescription(desc);
            topic1.setImgurl(img);
            topic1.setUrl(subspurl);
            topic1.setFloorappid(StringUtilsEX.ToInt(spfloor));
            topic1.setTitle(name);
            topic1.setSort(StringUtilsEX.ToInt(sort));
            //默认状态为禁用
            topic1.setStatus(StringUtilsEX.ToInt(status));
            topic1.setIsdelete(false);
            topic1.setFatherid(StringUtilsEX.ToInt(fatherid));

            if (topicService.insert(topic1) > 0) {
                logger.info(String.format("添加专题成功,标识:{0},名称:{1},", mark, name));
                item.setCode(0);
                item.setDesc("添加子主题成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "subtopic_edit.jsp", "/platform/topic/addSubTopic", "添加子专题");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加子专题操作记录出错! 异常信息:",
    								e, "/platform/topic/addSubTopic");
    					}
    					
    				}
    			});
            } else {
                logger.info(String.format("添加专题失败,标识:{0},名称:{1},", mark, name));
                item.setCode(-200);
                item.setDesc("添加子主题失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加子专题出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            item.setCode(-900);
            LogHandle.error(LogType.Topic,"添加子专题异常! 异常信息:{0}", e, "/platform/topic/addSubTopic");
        }

        return item;
    }

    /**
     * 添加主题
     * @param page
     * @param size
     * @param type
     * @param title
     * @return
     */
    @RequestMapping("/addTopic")
    public ReusltItem addTopic(String name, String type, String mark, String img, String status,
                               String desc,String webset,String pagetag,String hotis,String provinceid,String backcolour) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            List<Topic> tp = topicService.queryByName(name);
            if (tp.size()>0) {
                item.setCode(-119);
                item.setDesc("专题名称已存在！");
                return item;
            }
            if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
                item.setCode(-101);
                item.setDesc("专题名称不能为空！");
                return item;
            }
            /*if (StringUtilsEX.ToInt(type)==0) {
            	item.setCode(-102);
            	item.setDesc("请选择专题类型");
            	return item;
            }*/
            if (StringUtilsEX.ToInt(mark) == 0) {
                item.setCode(-103);
                item.setDesc("请选择专题标识");
                return item;
            }
            if (StringUtilsEX.IsNullOrWhiteSpace(img)) {
                item.setCode(-104);
                item.setDesc("请上传图片");
                return item;
            }
            if (StringUtilsEX.IsNullOrWhiteSpace(webset)) {
                item.setCode(-105);
                item.setDesc("使用平台不为空");
                return item;
            }
            if (StringUtilsEX.ToInt(pagetag)<0) {
                item.setCode(-106);
                item.setDesc("请选择页面标识");
                return item;
            }
            Topic topic1 = new Topic();
            topic1.setType(StringUtilsEX.ToInt(type));
            topic1.setMark(StringUtilsEX.ToInt(mark));
            topic1.setDescription(desc);
            topic1.setImgurl(img);
            //topic1.setUrl(subspurl);
            //topic1.setFloorappid(StringUtilsEX.ToInt(spfloor));
            topic1.setTitle(name);
            topic1.setIsdelete(false);
            topic1.setFatherid(0);
            topic1.setStatus(StringUtilsEX.ToInt(status));
            topic1.setWebset(webset);
            topic1.setPagetag(StringUtilsEX.ToInt(pagetag));
            topic1.setHotis(StringUtilsEX.ToInt(hotis));
            topic1.setProvinceid(StringUtilsEX.ToInt(provinceid));
//            if (StringUtilsEX.IsNullOrWhiteSpace(backcolour)) {
//            	topic1.setBackcolour("#FFFFFF");
//            }else{
//            	topic1.setBackcolour("#"+backcolour);
//            }
            //判断是否是地方官专题 是的话就是系统专题
            if (StringUtilsEX.ToInt(mark)==TopicMarkEnum.地方馆.getValue()) {
				topic1.setIssys(true);
			}else{
				topic1.setIssys(false);
			}
            if (topicService.insert(topic1) > 0) {
            	LogHandle.info(LogType.Topic,String.format("添加专题成功,标识:{0},名称:{1},", mark, name));
                item.setCode(0);
                item.setDesc("添加主题成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "topic_edit.jsp", "/platform/topic/addTopic", "添加主题");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加主题操作记录出错! 异常信息:",
    								e, "/platform/topic/addTopic");
    					}
    					
    				}
    			});
            } else {
            	LogHandle.info(LogType.Topic,String.format("添加专题失败,标识:{0},名称:{1},", mark, name));
                item.setCode(-200);
                item.setDesc("添加主题失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加专题出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,
                "添加专题异常! 异常信息:{0}", e, "/platform/topic/addTopic");
        }

        return item;
    }

    /**
     * 修改专题
     * @param id
     * @param name
     * @param type
     * @param mark
     * @param img
     * @param subspurl
     * @param spfloor
     * @param desc
     * @return
     */
    @RequestMapping("/updTopic")
    public ReusltItem updTopic(String id, String name, String type, String mark, String img,
                               String subspurl, String spfloor, String sort, String status,
                               String desc,String webset,String pagetag,String hotis,String provinceid,String backcolour) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
                item.setCode(-101);
                item.setDesc("专题id不能为空！");
                return item;
            }
            if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
                item.setCode(-102);
                item.setDesc("专题标题(name)不能为空！");
                return item;
            }
            if (StringUtilsEX.ToInt(type) == -1) {
                item.setCode(-103);
                item.setDesc("请选择专题类型");
                return item;
            }
//            if (StringUtilsEX.IsNullOrWhiteSpace(webset)) {
//                item.setCode(-104);
//                item.setDesc("使用平台不为空");
//                return item;
//            }
//            if (StringUtilsEX.ToInt(pagetag)<0) {
//            	item.setCode(-105);
//            	item.setDesc("请选择页面标识");
//            	return item;
//            }

            Topic topic = topicService.queryById(StringUtilsEX.ToInt(id));
            topic.setType(StringUtilsEX.ToInt(type));
            topic.setMark(StringUtilsEX.ToInt(mark));
            topic.setDescription(desc);
            topic.setImgurl(img);
            topic.setUrl(subspurl);
            topic.setFloorappid(StringUtilsEX.ToInt(spfloor));
            topic.setSort(StringUtilsEX.ToInt(sort));
            topic.setTitle(name);
            topic.setStatus(StringUtilsEX.ToInt(status));
            topic.setWebset(webset);
            topic.setPagetag(StringUtilsEX.ToInt(pagetag));
            topic.setHotis(StringUtilsEX.ToInt(hotis));
            topic.setProvinceid(StringUtilsEX.ToInt(provinceid));
            topic.setBackcolour(backcolour);
            //判断背景颜色是否填写 默认白色
            if (StringUtilsEX.IsNullOrWhiteSpace(backcolour)) {
            	topic.setBackcolour("FFFFFF");
            }else{
            	topic.setBackcolour(backcolour);
            }
            if (topicService.update(topic) > 0) {
                logger.info(String.format("修改专题成功,标识:{0},名称:{1},", mark, name));
                item.setCode(0);
                item.setDesc("修改主题成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "topic_edit.jsp", "/platform/topic/updTopic", "修改专题");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改专题操作记录出错! 异常信息:",
    								e, "/platform/topic/updTopic");
    					}
    					
    				}
    			});
            } else {
                logger.info(String.format("修改专题失败,标识:{0},名称:{1},", mark, name));
                item.setCode(-200);
                item.setDesc("修改主题失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改专题出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Topic,
	                "修改专题异常! 异常信息:{0}", e, "/platform/topic/updTopic");
        }
        return item;
    }

    /**
     * 查询主题
     * 
     * @param page
     * @param size
     * @param type
     * @param title
     * @return
     */
    @RequestMapping("/queryTopicListByCriteria")
    public ReusltItem queryTopicListByCriteria(String page, String size, String mark, String title,String issys) {

        ReusltItem item = new ReusltItem();
        try {
            CriteriaTopic criteria = new CriteriaTopic();
            criteria.setSpmark(StringUtilsEX.ToInt(mark));
            criteria.setTitle(title);
            criteria.setOrderByClause("id");
            criteria.setSort("Desc");
            criteria.setWebset("");
            if(StringUtilsEX.ToInt(issys)<0){
            	criteria.setIssys(0);
            }else{
            	criteria.setIssys(StringUtilsEX.ToInt(issys));
            }
            
            PageBean pageBean = topicService.queryTopicListByCriteria(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取查询主题的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,
                "获取查询主题的信息出错! 异常信息:{0}", e,
                "/platform/topic/queryTopicListByCriteria");
        }
        return item;
    }

    /**
     * 查询子专题
     * 
     * @param page
     * @param size
     * @param type
     * @param title
     * @return
     */
    @RequestMapping("/queryCtopicListByCriteria")
    public ReusltItem queryCtopicListByCriteria(String page, String size, String title, String fid) {

        ReusltItem item = new ReusltItem();
        try {
            CriteriaTopic criteria = new CriteriaTopic();
            criteria.setTitle(title);
            criteria.setFatherid(StringUtilsEX.ToInt(fid));
            PageBean pageBean = topicService.queryCtopicListByCriteria(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取查询查询子专题的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Error,
                "获取查询查询子专题的信息出错! 异常信息:{0}", e,
                "/platform/topic/queryClassTopicListByCriteria");
        }
        return item;
    }

    /**
     * 删除主题
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public ReusltItem deleteById(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("获取参数错误：id" + id);
            }
            Topic topic = topicService.queryById(StringUtilsEX.ToInt(id));
            topic.setIsdelete(true);
            int result = topicService.delTopicById(topic);

            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "topic_list.jsp", "/platform/topic/deleteById", "删除主题");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除主题操作记录出错! 异常信息:",
    								e, "/platform/topic/deleteById");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取删除主题的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,
                "获取删除主题的信息出错! 异常信息:{0}", e, "/platform/topic/deleteById");
        }
        return item;
    }

    /**
     * 通过id查询专题
     * @param id
     * @return
     */
    @RequestMapping("/queryById")
    public ReusltItem queryById(String id) {
        ReusltItem item = new ReusltItem();
        try {

            item.setData(topicService.queryById(StringUtilsEX.ToInt(id)));
            item.setCode(0);
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询专题的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,"获取主题的信息出错! 异常信息:{0}", e, "/platform/topic/queryById");

        }
        return item;
    }

    /**
     * 通过id查询对应的名称
     * @param id
     * @return
     */
    @RequestMapping("/getName")
    public ReusltItem getName(String id,String name,String isby) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("专题ID参数错误，id：" + id);
                return item;
            }
            Topic topic = topicService.queryById(StringUtilsEX.ToInt(id));
            if (topic != null) {
                int type = topic.getType();
                int mark = topic.getMark();
                if (type == TopicTypeEnum.商品.getValue()&& mark == TopicMarkEnum.今日特价.getValue()) {
                	List<Spu> zklist=spuService.getSpuStartWithNamesIszk(name);
                	item.setData(zklist);
                }else if (mark != TopicMarkEnum.今日特价.getValue() 
                		&& (type == TopicTypeEnum.商品.getValue()||type == TopicTypeEnum.专题分类.getValue())) {
                    List<Spu> list =new ArrayList<Spu>();
                   /* list=spuService.queryAll(
                    		ProStatusEnum.上架.getValue());*/
                    list=spuService.getSpuStartWithNames(name);
                    /*if(name!=""){
                    	list=spuService.getSpuStartWithNames(name);
                    }*/
                   
                    item.setData(list);
                }else if (type == TopicTypeEnum.店铺.getValue()) {
                    List<Shop> list =new ArrayList<Shop>();
                /*    list=shopService.queryAll();*/
                    list=shopService.getShopStartWithName(name);
                  /*  if(name!=""){
                    	
                    }*/
                    
                    item.setData(list);
                } else if (type == TopicTypeEnum.品牌.getValue()) {
                   List<Brand> list =new ArrayList<Brand>();
                    list=brandService.getBrandWithName(name);
                    /*list=brandService.selectAll();*/
                   /* if(name!=""){
                    	list=brandService.getBrandWithName(name);
                    }*/
                    
                    item.setData(list);
                } else if (type == TopicTypeEnum.分类.getValue()) {
                    List<Category> list =new ArrayList<Category>();
                    list=categoryService.queryFirstClass();
                    item.setData(list);
                }
                if(!StringUtilsEX.IsNullOrWhiteSpace(isby)){
                	List<Spu> l=spuService.getSpuStartWithNamesIsby(name);
                	item.setData(l);
                }
                /*int mark=topic.getMark();
                if(mark==TopicMarkEnum.发现好店.getValue()){
                	List<Shop> list=shopService.queryAll();
                	item.setData(list);
                }else if(mark==TopicMarkEnum.发现好货.getValue()||mark==TopicMarkEnum.新品上市.getValue()){
                	List<Spu> list=spuService.queryAll();
                	item.setData(list);
                }*/
                item.setCode(0);
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询名称的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Error,"获取名称信息出错! 异常信息:{0}", e, "/platform/topic/getName");
        }
        return item;
    }

    /**
     * 分页查询专题关联的数据
     * 
     * @param tid
     * @return
     */
    @RequestMapping("/queryByTid")
    public ReusltItem queryByTid(String index, String size, String tid, String spmark, String type) {
        ReusltItem item = new ReusltItem();
        try {
            PageBean pageBean = new PageBean();
            if (StringUtilsEX.ToInt(tid) <= 0) {
                item.setCode(-101);
                item.setDesc("参数错误，tid：" + tid);
                return item;
            }
            CriteriaTopic criteria = new CriteriaTopic();
            criteria.setTopicid(StringUtilsEX.ToInt(tid));
            Integer mark = StringUtilsEX.ToInt(spmark);

            Integer _type = StringUtilsEX.ToInt(type);
            if (_type == TopicTypeEnum.商品.getValue()||_type==TopicTypeEnum.专题分类.getValue()) {

                pageBean = spuService.queryTopicRelateListByCriteria(criteria,
                    StringUtilsEX.ToInt(index), StringUtilsEX.ToInt(size));

            } else if (_type == TopicTypeEnum.店铺.getValue()) {

                pageBean = shopService.queryTopicRelateListByCriteria(criteria,
                    StringUtilsEX.ToInt(index), StringUtilsEX.ToInt(size));

            } else if (_type == TopicTypeEnum.品牌.getValue()) {

                pageBean = brandService.queryTopicRelateListByCriteria(criteria,
                    StringUtilsEX.ToInt(index), StringUtilsEX.ToInt(size));

            } else if (_type == TopicTypeEnum.分类.getValue()) {

                pageBean = categoryService.queryTopicRelateListByCriteria(criteria,
                    StringUtilsEX.ToInt(index), StringUtilsEX.ToInt(size));

            } else if (_type == TopicTypeEnum.子专题.getValue()) {

                pageBean = topicRelateService.queryTopicRelateListByCriteria(criteria,
                    StringUtilsEX.ToInt(index), StringUtilsEX.ToInt(size));
            }

            //pageBean = topicRelateService.queryTopicRelateListByCriteria(criteria,StringUtilsEX.ToInt(index),StringUtilsEX.ToInt(size));

            /*if (mark == TopicMarkEnum.精彩专题.getValue()) {
            	//主題 
            	pageBean = topicRelateService.queryTopicRelateListByCriteria(criteria,StringUtilsEX.ToInt(index),StringUtilsEX.ToInt(size));
            }else if (mark == TopicMarkEnum.发现好店.getValue()) {
            	//店鋪
            	pageBean = shopService.queryTopicRelateListByCriteria(criteria,StringUtilsEX.ToInt(index),StringUtilsEX.ToInt(size));
            }else if (mark == TopicMarkEnum.新品上市.getValue()) {
            	//商品
            	pageBean = spuService.queryTopicRelateListByCriteria(criteria,StringUtilsEX.ToInt(index),StringUtilsEX.ToInt(size));
            }else if (mark == TopicMarkEnum.发现好货.getValue()) {
            	//發現好貨
            	pageBean = spuService.queryTopicRelateListByCriteria(criteria,StringUtilsEX.ToInt(index),StringUtilsEX.ToInt(size));
            }*/
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取查询专题关联的数据的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,
                "获取查询专题关联的数据的信息出错! 异常信息:{0}", e,
                "/platform/topic/deleteById");
        }
        return item;
    }

    /**
     * 添加主题
     * @param page
     * @param size
     * @param type
     * @param title
     * @return
     */
    @RequestMapping("/saveTopicRelate")
    public ReusltItem saveTopicRelate(String topicid, String relateid, String imgurl, String id,
                                      String orderby,String classifyid,String classifyName) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            if (StringUtilsEX.IsNullOrWhiteSpace(topicid)) {
                item.setCode(-101);
                item.setDesc("专题id不能为空！");
                return item;
            }
            if (StringUtilsEX.ToInt(relateid) <= 0) {
                item.setCode(-102);
                item.setDesc("请选择关联商品");
                return item;
            }
            /*if (StringUtilsEX.IsNullOrWhiteSpace(imgurl)) {
            	item.setCode(-103);
            	item.setDesc("请上传图片");
            	return item;
            }*/
            
            TopicRelate topicRelate = new TopicRelate();
            topicRelate.setTopicid(StringUtilsEX.ToInt(topicid));
            topicRelate.setRelatedid(StringUtilsEX.ToInt(relateid));
            topicRelate.setImgurl(imgurl);
            topicRelate.setClassifyid(StringUtilsEX.ToInt(classifyid));
            topicRelate.setClassifyname(classifyName);
            int a=StringUtilsEX.ToInt(orderby);
            if(a==-1){
            	 topicRelate.setSort(0);
            }else{
            	topicRelate.setSort(a);
            }
            
            
            //topicRelate.setSort(StringUtilsEX.ToInt(orderby));
            if (StringUtils.isBlanck(id)) {
            	List<TopicRelate> tplist=topicRelateService.getRelatedidAndreleteId(StringUtilsEX.ToInt(topicid), StringUtilsEX.ToInt(relateid));
                if(tplist!=null&&tplist.size()>0){
                	item.setCode(0);
                    item.setDesc("该商品已经存在");
                    return item;
                }
                int result = topicRelateService.insert(topicRelate);
                if (result > 0) {
                    logger.info(String.format("添加专题详情成功"));
                    item.setCode(0);
                    item.setDesc("添加主题详情成功");
                  //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "topic_edit.jsp.jsp", "/platform/topic/addTopicRelate", "添加主题详情");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加主题详情操作记录出错! 异常信息:",
        								e, "/platform/topic/addTopicRelate");
        					}
        					
        				}
        			});
                } else {
                    logger.info(String.format("添加专题详情失败"));
                    item.setCode(-200);
                    item.setDesc("添加主题详情失败");
                }
            } else {
                topicRelate.setId(StringUtilsEX.ToInt(id));
                TopicRelate record = topicRelateService.queryById(StringUtilsEX.ToInt(id));
                BeanUtils.copyProperties(topicRelate, record);
                int result = topicRelateService.update(record);
                if (result > 0) {
                    logger.info(String.format("修改专题详情成功"));
                    item.setCode(0);
                    item.setDesc("修改主题详情成功");
                  //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "topic_edit.jsp", "/platform/topic/addTopicRelate", "修改主题详情");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改主题详情操作记录出错! 异常信息:",
        								e, "/platform/topic/addTopicRelate");
        					}
        					
        				}
        			});
                } else {
                    logger.info(String.format("修改专题详情失败"));
                    item.setCode(-200);
                    item.setDesc("修改主题详情失败");
                }
            }

        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑专题管理出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,
                "编辑专题管理异常! 异常信息:{0}", e, "/platform/topic/addTopicRelate");
        }
        return item;
    }

    /**
     * 修改专题中间信息
     * @param id
     * @param relateid
     * @param imgurl
     * @return
     */
    @RequestMapping("/updTopicRelate")
    public ReusltItem updTopicRelate(String id, String relateid, String imgurl, String orderby) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
                item.setCode(-101);
                item.setDesc("id不能为空！");
                return item;
            }
            if (StringUtilsEX.ToInt(relateid) == 0) {
                item.setCode(-102);
                item.setDesc("请选择关联类型");
                return item;
            }
            if (StringUtilsEX.IsNullOrWhiteSpace(imgurl)) {
                item.setCode(-103);
                item.setDesc("请上传图片");
                return item;
            }
            TopicRelate topicRelate = topicRelateService.queryById(StringUtilsEX.ToInt(id));
            if (topicRelate != null) {
                topicRelate.setRelatedid(StringUtilsEX.ToInt(relateid));
                topicRelate.setImgurl(imgurl);
               // topicRelate.setSort(StringUtilsEX.ToInt(orderby));
                int a=StringUtilsEX.ToInt(orderby);
                if(a==-1){
                	 topicRelate.setSort(0);
                }else{
                	topicRelate.setSort(a);
                }
                
                if (topicRelateService.update(topicRelate) > 0) {
                    logger.info(String.format("修改专题信息成功"));
                    item.setCode(0);
                    item.setDesc("修改专题信息成功");
                  //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "修改专题信息页面", "/platform/topic/updTopicRelate", "修改专题中间信息");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改专题中间信息操作记录出错! 异常信息:",
        								e, "/platform/topic/updTopicRelate");
        					}
        					
        				}
        			});
                } else {
                    logger.info(String.format("修改专题信息失败"));
                    item.setCode(-200);
                    item.setDesc("修改专题信息失败");
                }
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改专题中间信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Error,"修改专题中间信息异常! 异常信息:{0}", e,
                "/platform/topic/updTopicRelate");
        }
        return item;
    }

    /**
     * 通过id查询专题的关联信息
     * @param id
     * @return
     */
    @RequestMapping("/selectById")
    public ReusltItem selectById(String id) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("专题ID 参数错误，id：" + id);
                return item;
            }
            item.setData(topicRelateService.queryById(StringUtilsEX.ToInt(id)));
            item.setCode(0);
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取主题的关联信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,"获取主题的关联信息出错! 异常信息:{0}", e, "/platform/topic/queryById");

        }
        return item;
    }

    /**
     * 根据id删除专题关联的一条信息
     * 
     * @param id
     * @return
     */
    @RequestMapping("delTopicR")
    public ReusltItem delTopicR(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("参数错误：id" + id);
            }
            int result = topicRelateService.deleteById(StringUtilsEX.ToInt(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "topic_proSpecialList.jsp", "/platform/topic/delTopicR", "根据id删除专题关联的一条信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除专题关联的一条信息操作记录出错! 异常信息:",
    								e, "/platform/topic/delTopicR");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取根据id删除专题关联的一条信息的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,"获取根据id删除专题关联的一条信息的信息出错! 异常信息:{0}", e,
                "/platform/topic/queryById");
        }
        return item;
    }

    /**
     * 根据spuid获取商品信息
     * @param id
     * @return
     */
    @RequestMapping("GetProductByID")
    public ReusltItem GetProductByID(String id) {
        ReusltItem item = new ReusltItem();
        try {
            Spu spu = spuService.queryById(StringUtilsEX.ToInt(id));
            item.setCode(0);
            item.setData(spu);
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取商品信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Product,"获取商品信息出错! 异常信息:{0}", e, "/platform/topic/GetProductByID");
        }
        return item;
    }

    /**
     * 修改排序
     * @param id
     * @param setValue
     * @param type
     * @return
     */
    @RequestMapping("SetSpecialProValue")
    public ReusltItem SetSpecialProValue(String id, String setValue, String type) {
        ReusltItem item = new ReusltItem();

        try {
        	user=SessionState.GetCurrentUser();
            TopicRelate topicRelate = topicRelateService.queryById(StringUtilsEX.ToInt(id));
            topicRelate.setSort(StringUtilsEX.ToInt(setValue));
            int temp = topicRelateService.update(topicRelate);
            if (temp > 0) {
                item.setCode(0);
                item.setDesc("保存成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "topic_proSpecialList.jsp", "/platform/topic/SetSpecialProValue", "修改排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改排序操作记录出错! 异常信息:",
    								e, "/platform/topic/SetSpecialProValue");
    					}
    					
    				}
    			});
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改排序出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,"修改排序出错! 异常信息:{0}", e,
                "/platform/topic/SetSpecialProValue");
        }

        return item;
    }

    /**
     * 修改排序
     * @param id
     * @param setValue
     * @param type
     * @return
     */
    @RequestMapping("SetSpecialValue")
    public ReusltItem SetSpecialValue(String id, String setValue, String type) {
        ReusltItem item = new ReusltItem();

        try {
        	user=SessionState.GetCurrentUser();
            Topic topic = topicService.queryById(StringUtilsEX.ToInt(id));
            topic.setSort(StringUtilsEX.ToInt(setValue));
            int temp = topicService.update(topic);

            if (temp > 0) {
                item.setCode(0);
                item.setDesc("保存成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "topic_proSpecialList.jsp", "/platform/topic/SetSpecialValue", "修改排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改排序操作记录出错! 异常信息:",
    								e, "/platform/topic/SetSpecialValue");
    					}
    					
    				}
    			});
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改排序出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,"修改排序出错! 异常信息:{0}", e, "/platform/topic/SetSpecialValue");
        }

        return item;
    }

    /**
     * 修改子专题状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("updateSubStatus")
    public ReusltItem updateSubStatus(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            Topic topic = topicService.queryById(StringUtilsEX.ToInt(id));
            Integer status = topic.getStatus();
            if (status == 0) {
                status = 1;
            } else if (status == 1) {
                status = 0;
            }
            topic.setStatus(status);
            int temp = topicService.update(topic);

            if (temp > 0) {
                item.setCode(0);
                item.setDesc("保存成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "topicClass_list.jsp", "/platform/topic/updateSubStatus", "修改子专题状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改子专题状态操作记录出错! 异常信息:",
    								e, "/platform/topic/updateSubStatus");
    					}
    					
    				}
    			});
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改状态：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,"修改状态出错! 异常信息:{0}", e, "/platform/topic/updateSubStatus");
        }

        return item;
    }

    /**
     * 修改子专题状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("updateStatus")
    public ReusltItem updateStatus(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            Topic topic = topicService.queryById(StringUtilsEX.ToInt(id));
            Integer status = topic.getStatus();
            Integer sta = 0;
            if (status == 0) {
                sta = 1;
            } else if (status == 1) {
                sta = 0;
            }
            Integer mark = topic.getMark();
            List<Topic> list = topicService.queryByMark(mark);
            for (int i = 0; i < list.size(); i++) {
                if (list != null && list.size() > 0) {
                    Topic topic1 = list.get(i);

                    if (topic1.getId() == StringUtilsEX.ToInt(id)) {
                        if (topic1.getStatus() == 0) {
                            topic1.setStatus(1);
                            topicService.update(topic1);
                        } else if (topic1.getStatus() == 1) {
                            topic1.setStatus(0);
                            topicService.update(topic1);
                        }
                    }
                    topic1.setStatus(sta);
                    topicService.update(topic1);
                }
            }
          //异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "topicClass_list.jsp", "/platform/topic/updateSubStatus", "修改子专题状态");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"修改子专题状态操作记录出错! 异常信息:",
								e, "/platform/topic/updateSubStatus");
					}
					
				}
			});
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改子专题状态错误：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Topic,"修改排序出错! 异常信息:{0}", e, "/platform/topic/updateSubStatus");
        }

        return item;
    }
    
    
    /**
     * 获取专题商品分类列表
     * @param specialid
     * @param name
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/getSpecialProtypeList")
    public ReusltItem getSpecialProtypeList(String specialid,String name,String page, String size){
    	ReusltItem item=new ReusltItem();
    	try {
    		CriteriaSpecialProType criteria = new CriteriaSpecialProType();
    		criteria.setSpecialid(StringUtilsEX.ToInt(specialid));
    		criteria.setName(name);
    		criteria.setOrderByClause("orderby");
            PageBean pageBean = specialProTypeService.getSpecialProtypeList(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取专题商品分类列表错误：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Topic,"获取专题商品分类列表出错!：", e,
					"/platform/topic/getSpecialProtypeList");
		}
    	return item;
    }
    
    /**
     * 根据id查询专题分类
     * @param id
     * @return
     */
    @RequestMapping("/getSpecialProtype")
    public ReusltItem getSpecialProtype(String id){
    	ReusltItem item=new ReusltItem();
    	try {
			item.setData(specialProTypeService.getSpecialProtype(StringUtilsEX.ToInt(id)));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取专题商品分类错误：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Topic,"获取专题商品分类出错!：", e,
					"/platform/topic/getSpecialProtype");
		}
    	return item;
    }
    
    /**
     * 添加专题商品分类
     * @param specialid
     * @param name
     * @param showname
     * @param img
     * @param orderby
     * @return
     */
    @RequestMapping("/insertSpecialProtype")
    public ReusltItem insertSpecialProtype(String specialid,String name,String showname,String img,
    		String orderby,String mark){
    	ReusltItem item=new ReusltItem();
    	try {
    		user=SessionState.GetCurrentUser();
    		SpecialProtype specialProtype=new SpecialProtype();
    		if(StringUtilsEX.IsNullOrWhiteSpace(name)){
    			item.setCode(-101);
    			item.setDesc("分类名称不能为空!");
    			return item;
    		}
    		if(StringUtilsEX.IsNullOrWhiteSpace(showname)){
    			item.setCode(-101);
    			item.setDesc("显示名称不能为空!");
    			return item;
    		}
    		specialProtype.setMark(StringUtilsEX.ToInt(mark));
    		specialProtype.setSpecialid(StringUtilsEX.ToInt(specialid));
    		specialProtype.setName(name);
    		specialProtype.setShowname(showname);
    		specialProtype.setImg(img);
    		if(StringUtilsEX.ToInt(orderby)>0){
    			specialProtype.setOrderby(StringUtilsEX.ToInt(orderby));
    		}else{
    			specialProtype.setOrderby(0);
    		}
    		if(specialProTypeService.insertSpecialProtype(specialProtype)>0){
    			item.setCode(0);
    			item.setDesc("保存成功!");
            } else {
                item.setCode(-200);
                item.setDesc("添加专题商品分类失败");
    		}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加专题商品分类错误：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Topic,"添加专题商品分类!：", e,
					"/platform/topic/insertSpecialProtype");
		}
    	return item;
    }
    
    /**
     * 修改专题商品分类
     * @param id
     * @param specialid
     * @param name
     * @param showname
     * @param img
     * @param orderby
     * @return
     */
    @RequestMapping("/updateSpecialProtype")
    public ReusltItem updateSpecialProtype(String id,String name,String showname,String mark,
    		String img,String orderby){
    	ReusltItem item=new ReusltItem();
    	try {
    		user=SessionState.GetCurrentUser();
    		SpecialProtype specialProtype=new SpecialProtype();
    		if(StringUtilsEX.IsNullOrWhiteSpace(name)){
    			item.setCode(-101);
    			item.setDesc("分类名称不能为空!");
    			return item;
    		}
    		if(StringUtilsEX.IsNullOrWhiteSpace(showname)){
    			item.setCode(-101);
    			item.setDesc("显示名称不能为空!");
    			return item;
    		}
    		specialProtype.setMark(StringUtilsEX.ToInt(mark));
    		specialProtype.setId(StringUtilsEX.ToInt(id));
    		specialProtype.setName(name);
    		specialProtype.setShowname(showname);
    		specialProtype.setImg(img);
    		if(StringUtilsEX.ToInt(orderby)>0){
    			specialProtype.setOrderby(StringUtilsEX.ToInt(orderby));
    		}else{
    			specialProtype.setOrderby(0);
    		}
    		if(specialProTypeService.updateSpecialProtype(specialProtype)>0){
    			item.setCode(0);
    			item.setDesc("保存成功!");
    			//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "topic_classListEdit.jsp", "/platform/topic/updateSpecialProtype", "修改专题商品分类");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改专题商品分类操作记录出错! 异常信息:",
    								e, "/platform/topic/updateSpecialProtype");
    					}
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改专题商品分类失败");
    		}
		} catch (Exception e) {
            item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Topic,"修改专题商品分类!：", e,
					"/platform/topic/updateSpecialProtype");
		}
    	return item;
    }
    
    /**
     * 删除专题商品分类
     * @param id
     * @return
     */
    @RequestMapping("/delSpecialProtype")
    public ReusltItem delSpecialProtype(String id){
    	ReusltItem item=new ReusltItem();
    	try {
    		user=SessionState.GetCurrentUser();
    		if(specialProTypeService.delSpecialProtype(StringUtilsEX.ToInt(id))>0){
    			item.setCode(0);
    			item.setDesc("删除成功!");
    			//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "topic_classList.jsp", "/platform/topic/delSpecialProtype", "删除专题商品分类");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除专题商品分类操作记录出错! 异常信息:",
    								e, "/platform/topic/delSpecialProtype");
    					}
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除专题商品分类失败");
    		}
		} catch (Exception e) {
            item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Topic,"删除专题商品分类出错!：", e,
					"/platform/topic/delSpecialProtype");
		}
    	return item;
    }
    
    /**
     * 通过id查询对应专题分类
     * @param id
     * @return
     */
    @RequestMapping("/getClassify")
    public ReusltItem getClassify(String id,String name) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("专题ID参数错误，id：" + id);
                return item;
            }
            Topic topic = topicService.queryById(StringUtilsEX.ToInt(id));
            if (topic != null) {
                int type = topic.getType();
               if (type == TopicTypeEnum.专题分类.getValue()) {
                	 List<SpecialProtype> list =new ArrayList<SpecialProtype>();
                     if(name!=""){
                     	list=specialProTypeService.queryAll(name,topic.getId());
                     }else{
                         list=specialProTypeService.queryAll(topic.getId());
                     }
                     item.setData(list);
                }
                item.setCode(0);
            }
        } catch (Exception e) {
            item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Topic,"通过id查询对应专题分类!：", e,
					"/platform/topic/getClassify");
        }
        return item;
    }
    
    /**
     * 模糊检索专题列表
     * @param name
     * @return
     */
    @RequestMapping("/getTopicStartWithName")
    public ReusltItem getSspuStartWithName(String name,String webset){
    	ReusltItem item = new ReusltItem();
		try {
			item.setData(topicService.getTopicStartWithName(name,StringUtilsEX.ToInt(webset)));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("模糊检索专题列表出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Topic,
					"模糊检索专题列表信息出错! 异常信息:", e,
					"/platform/topic/getSspuStartWithName");
		}
		return item;
    }
}

/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.FindTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.view.controller.FindViewController;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.FindTypeDto;
import com.yinlian.wssc.web.dto.SessionUser;
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
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.FindRecordService;
import com.yinlian.wssc.web.service.FindRelationService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicRelateService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaFind;
import com.yinlian.wssc.web.util.CriteriaFindRelate;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 后台的发现的控制器
 * TopicController.java
 * @author Liang.ma.s
 * @version $Id: TopicController.java, v 0.1 2016年4月7日 下午4:04:35 Administrator Exp $
 */
@RestController
@RequestMapping("/platform/find")
public class FindController {
	
	 private static final Logger logger = LoggerFactory.getLogger(FindController.class);
	 
	 @Autowired
	 private FindRecordService   findrecordservice;
	 
	 @Autowired
	 private FindRelationService  findRelationService;
	 
	 @Autowired
	 private  SpuService     spuService;
	 
	 @Autowired
	 private  TopicService     topicService;
	 
	 @Autowired
	 private  ArticlesService   articlesService;
	 
	 @Autowired
	 private  OperaterecordsService  operaterecordsService;
	 
	 SessionUser  user;

    /**
     * 分页查询发现
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryFindRecord")
    public @ResponseBody ReusltItem queryFindRecord(String title, String page, String size) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(page) <= 0) {
            item.setCode(-101);
            item.setDesc("分页参数错误，pageindex：" + page);
            return item;
        }
        if (StringUtilsEX.ToInt(size) <= 0) {
            item.setCode(-102);
            item.setDesc("分页参数错误，pageindex：" + size);
            return item;
        }
        try {
            CriteriaFind criteria = new CriteriaFind();
            criteria.setTitle(title);
            PageBean pageBean = findrecordservice.selectFindrecordListByPage(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("分页查询发现异常{0}：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
             LogHandle.error(LogType.Find,"分页查询发现异常! 异常信息:{0}", e, "/platform/find/queryFindRecord");
        }
        return item;
    }
	 
    
    /**
     * 分页查询发现关联信息
     * @param page
     * @param size
     * @param findid
     * @param type
     * @return
     */
    @RequestMapping("/queryFindRelate")
    public ReusltItem queryFindRelate(String page, String size, String findid, String type) {

        ReusltItem item = new ReusltItem();
        try {
        	
        	 PageBean pageBean = new PageBean();
             if (StringUtilsEX.ToInt(findid) <= 0) {
                 item.setCode(-101);
                 item.setDesc("参数错误，findid：" + findid);
                 return item;
             }
        	
        	CriteriaFindRelate criteria = new CriteriaFindRelate();
        	criteria.setFindid(StringUtilsEX.ToInt(findid));
            criteria.setType(StringUtilsEX.ToInt(type));
            
            Integer _type = StringUtilsEX.ToInt(type);
            
            if (_type == FindTypeEnum.店铺动态.getValue()) {

            	  pageBean = spuService.queryFindRelationByCriteria(criteria,
                         StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));

            } else if (_type == FindTypeEnum.专题.getValue()) {

            	  pageBean = topicService.queryFindRelationByCriteria(criteria,
                         StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));

            } else if (_type == FindTypeEnum.资讯文章.getValue()) {

            	  pageBean = articlesService.queryFindRelationByCriteria(criteria,
                         StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));

            }
           
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("获取查询发现关联的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Find,"获取查询发现关联信息出错! 异常信息:{0}", e,
                "/platform/find/queryFindRelate");
        }
        return item;
    }
    
   
    /**
     * 添加发现关联信息
     * @param findid
     * @param relationid
     * @param sort
     * @return
     */
    @RequestMapping("/addFindRelation")
    public ReusltItem addFindRelation(String findid,String relationid,String sort) {

        ReusltItem item = new ReusltItem();
        try {
        	FindRelation  findRelation=new FindRelation();
        	findRelation.setFindid(StringUtilsEX.ToInt(findid));
        	findRelation.setRelationid(StringUtilsEX.ToInt(relationid));
        	if(sort==""){
        		findRelation.setSort(0);
        	}
        	findRelation.setSort(StringUtilsEX.ToInt(sort));
        	int result=findRelationService.insertFindRelation(findRelation);
        	if (result > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
            } else {
                item.setCode(-200);
                item.setDesc("添加失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("添加发现关联信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Find,"添加发现关联信息出错! 异常信息:{0}", e,
                "/platform/find/addFindRelation");
        }
        return item;
    }
    
    
    
    /**
     * 通过id查询对应的名称
     * @param id
     * @return
     */
    @RequestMapping("/getName")
    public ReusltItem getName(String id,String name) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("专题ID参数错误，id：" + id);
                return item;
            }
            
           Findrecord  find=findrecordservice.selectFind(StringUtilsEX.ToInt(id));
           if (find != null) {
          	
          	int _type=find.getType();
  			if(_type==FindTypeEnum.店铺动态.getValue()){
  				
  				List<Spu> list =new ArrayList<Spu>();
                list=spuService.getSpuStartWithNames(name);
                item.setData(list);
  			}else if(_type==FindTypeEnum.专题.getValue()){
  				
  				List<Topic> list =new ArrayList<Topic>();
                list=topicService.getSpuStartWithNames(name);
                item.setData(list);
  			}else if(_type==FindTypeEnum.资讯文章.getValue()){
  				
  				List<Articles> list =new ArrayList<Articles>();
                list=articlesService.getArticleStartWithNames(name);
                item.setData(list);
  			}
  			item.setCode(0);
           }
            
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询名称的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
             LogHandle.error(LogType.Find,"获取名称信息出错! 异常信息:{0}", e, "/platform/find/getName");
        }
        return item;
    }

    
    /**
     * 编辑发现关联信息
     * @param findid
     * @param relateid
     * @param id
     * @param orderby
     * @return
     */
    @RequestMapping("/saveFindRelate")
    public ReusltItem saveFindRelate(String findid, String relateid, String id,
                                      String orderby) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
           
            if (StringUtilsEX.ToInt(relateid) == 0) {
                item.setCode(-102);
                item.setDesc("请选择关联类型");
                return item;
            }
        	
        	FindRelation  findRelation=new FindRelation();
         	findRelation.setFindid(StringUtilsEX.ToInt(findid));
         	findRelation.setRelationid(StringUtilsEX.ToInt(relateid));
            int a=StringUtilsEX.ToInt(orderby);
            if(a==-1){
            	findRelation.setSort(0);
            }else{
            	findRelation.setSort(a);
            }
            
            if (StringUtils.isBlanck(id)) {
                int result = findRelationService.insertFindRelation(findRelation);
                if (result > 0) {
                    logger.info(String.format("添加发现关联成功"));
                    item.setCode(0);
                    item.setDesc("添加发现关联成功");
                  //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "find_relateEdit.jsp", "/platform/find/saveFindRelate", "添加主题详情");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加主题详情操作记录出错! 异常信息:",
        								e, "/platform/topic/saveFindRelate");
        					}
        					
        				}
        			});
                } else {
                    logger.info(String.format("添加发现关联失败"));
                    item.setCode(-200);
                    item.setDesc("添加发现关联失败");
                }
            } else {
                FindRelation record = findRelationService.queryInfoById(StringUtilsEX.ToInt(id));
                record.setRelationid(StringUtilsEX.ToInt(relateid));
                if(a==-1){
                	record.setSort(0);
                }else{
                	record.setSort(a);
                }
                int result = findRelationService.updateFindRelation(record);
                if (result > 0) {
                    logger.info(String.format("修改发现关联成功"));
                    item.setCode(0);
                    item.setDesc("修改发现关联成功");
                  //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "find_relateEdit.jsp", "/platform/find/saveFindRelate", "修改发现关联信息");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改发现关联信息记录出错! 异常信息:",
        								e, "/platform/find/saveFindRelate");
        					}
        					
        				}
        			});
                } else {
                    logger.info(String.format("修改发现关联信息失败"));
                    item.setCode(-200);
                    item.setDesc("修改发现关联信息失败");
                }
            }

        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("编辑发现管理出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Find,"编辑发现管理异常! 异常信息:{0}", e, "/platform/find/saveFindRelate");
        }
        return item;
    }
    
    
    /**
     * 删除发现关联信息
     * @param id
     * @return
     */
    @RequestMapping("/delFindRelate")
    public ReusltItem delFindRelate(String id) {

        ReusltItem item = new ReusltItem();
        try {
        	 if (StringUtilsEX.ToInt(id) < 0) {
                 item.setCode(-101);
                 item.setDesc("参数id错误");
                 return item;
             }
        	int result=findRelationService.deleteFindRelation(StringUtilsEX.ToInt(id));
        	if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("删除发现关联信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Find,"删除发现关联信息出错! 异常信息:{0}", e,
                "/platform/find/delFindRelate");
        }
        return item;
    }
    
    
    /**
     * 修改发现关联信息状态
     * @param id
     * @param sort
     * @return
     */
    @RequestMapping("/updateFindRelationSort")
    public ReusltItem updateFindRelationSort(String id,String sort) {

        ReusltItem item = new ReusltItem();
        try {
        	 if (StringUtilsEX.ToInt(id) < 0) {
                 item.setCode(-101);
                 item.setDesc("参数id错误");
                 return item;
             }
        	FindRelation findRelation=findRelationService.queryInfoById(StringUtilsEX.ToInt(id));
        	findRelation.setSort(StringUtilsEX.ToInt(sort));
        	int result=findRelationService.updateFindRelation(findRelation);
        	if (result > 0) {
                item.setCode(0);
                item.setDesc("修改排序成功");
            } else {
                item.setCode(-200);
                item.setDesc("修改排序失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("删除发现关联信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Find,"修改排序出错! 异常信息:{0}", e,
                "/platform/find/updateFindRelationSort");
        }
        return item;
    }
    
	 /**
	  * 添加发现
	  * @param title  标题
	  * @param status  状态
	  * @param imgurl  图片
	  * @param url  链接地址
	  * @param type  类型
	  * @param sort  排序
	  * @return
	  */
    @RequestMapping("/findRecordAdd")
    public @ResponseBody ReusltItem findRecordAdd(String title, int status,
                                                  String imgurl, String url,String type, Integer sort) {
        ReusltItem item = new ReusltItem();
        int result = 0;
        try {
            Findrecord findrecord = new Findrecord();
            findrecord.setTitle(title);
            findrecord.setStatus(status);
            findrecord.setImgurl(imgurl);
            findrecord.setUrl(url);
            findrecord.setSort(sort);
            findrecord.setType(StringUtilsEX.ToInt(type));
            result = findrecordservice.add(findrecord);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
            } else {
                item.setCode(-200);
                item.setDesc("添加失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("添加发现出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Find,"添加发现异常! 异常信息:{0}", e, "/platform/find/findRecordAdd");
        }

        return item;
    }
    
    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteRecordById")
    public @ResponseBody ReusltItem deleteRecordById(String id) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("参数id错误");
            return item;
        }
        try {
            int result = findrecordservice.deleteById(StringUtilsEX.ToInt(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("添加子专题出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
             LogHandle.error(LogType.Find,"添加子专题异常! 异常信息:{0}", e, "/platform/find/deleteRecordById");
        }
        return item;
    }

    /**
     * 批量删除
     * @param idlist
     * @return
     */
    @RequestMapping("/delList")
    public @ResponseBody ReusltItem delList(String idlist) {
        ReusltItem item = new ReusltItem();
        String[] ids = idlist.split(",");
        int result = 0;
        if (!StringUtils.isNotNull(idlist)) {
            item.setCode(-101);
            item.setDesc("参数的id不能为空");
        }
        try {
            for (int i = 0; i < ids.length; i++) {

                result = findrecordservice.deleteById(StringUtilsEX.ToInt(ids[i]));
            }
            if (result > 0) {
                item.setCode(0);
                item.setDesc("批量删除成功");
            } else {
                item.setCode(-200);
                item.setDesc("批量删除失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("批量删除出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
             LogHandle.error(LogType.Find,"批量删除异常! 异常信息:{0}", e, "/platform/find/delList");
        }
        return item;

    }
    
    
    /**
     * 排序调整
     * 
     * @param sort
     * @param id
     * @return
     */
    @RequestMapping("/updateSort")
    public @ResponseBody ReusltItem updateSort(String sort, String id) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) <= 0) {
                item.setCode(-101);
                item.setDesc("发现ID参数错误,id:" + id);
                return item;
            }
            if (StringUtilsEX.ToInt(sort) < 0) {
                item.setCode(-102);
                item.setDesc("发现排序参数错误,sort:" + sort);
                return item;
            }
            if (findrecordservice.updateOrder(StringUtilsEX.ToInt(sort), StringUtilsEX.ToInt(id)) > 0) {
                logger.info(String.format("修改发现排序成功! ID:{0},排序:{1}", id, sort));
                item.setCode(0);
                item.setDesc("修改发现排序成功");
            } else {
                logger.warn(String.format("修改发现排序失败! ID:{0},排序:{1}", id, sort));
                item.setCode(-200);
                item.setDesc("修改发现排序失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("排序调整异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
             LogHandle.error(LogType.Find,"排序调整异常! 异常信息:{0}", e, "/platform/find/updateSort");
        }
        return item;
    }

    /**
     * 批量调整排序
     * 
     * @param orderbys
     * @param ids
     * @return
     */
    @RequestMapping("/updateSortList")
    public @ResponseBody ReusltItem updateSortList(String sorts, String ids) {
        ReusltItem item = new ReusltItem();
        try {
            List<Integer> orderlist = new ArrayList<Integer>();
            List<Integer> idlist = new ArrayList<Integer>();
            if (ids == null) {
                item.setCode(-101);
                item.setDesc("发现ID参数错误,id:" + ids);
                return item;
            }
            Integer ob = 0, ID = 0;
            String[] idStrings = ids.split(",");
            for (int i = 0; i < idStrings.length; i++) {
                ID = StringUtilsEX.ToInt(idStrings[i]);
                if (ID > 0)
                    idlist.add(ID);
                else {
                    item.setCode(-102);
                    item.setDesc("发现ID参数错误,id:" + idStrings[i]);
                    return item;
                }
            }
            if (sorts == null) {
                item.setCode(-102);
                item.setDesc("发现排序参数错误,sort:" + sorts);
                return item;
            }
            String[] orderStrings = sorts.split(",");
            for (int i = 0; i < orderStrings.length; i++) {
                ob = StringUtilsEX.ToInt(orderStrings[i]);
                if (ob >= 0)
                    orderlist.add(ob);
                else {
                    item.setCode(-102);
                    item.setDesc("发现排序参数错误,sort:" + orderStrings[i]);
                    return item;
                }
            }

            if (findrecordservice.updateOrderList(orderlist, idlist) > 0) {
                logger.info(String.format("批量修改发现排序成功! ID集合:{0},排序集合:{1}", ids, sorts));
                item.setCode(0);
                item.setDesc("批量修改发现排序成功");
            } else {
                logger.warn(String.format("批量修改发现排序失败! ID集合:{0},排序集合:{1}", ids, sorts));
                item.setCode(-200);
                item.setDesc("批量修改发现排序失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("批量调整排序异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
             LogHandle.error(LogType.Find,"批量调整排序异常! 异常信息:{0}", e, "/platform/find/updateSortList");
        }
        return item;
    }

    /**
     * 禁用/启用
     * 
     * @param status
     * @param id
     * @return
     */
    @RequestMapping("/updateStatus")
    public @ResponseBody ReusltItem updateStatus(String id) {
        ReusltItem item = new ReusltItem();
        try {
            Findrecord findrecord = findrecordservice.selectFind(StringUtilsEX.ToInt(id));
            int ss = 0;
            int _status = findrecord.getStatus();
            if (_status == 0) {
                ss = 1;
            } else if (_status == 1) {
                ss = 0;
            }

            if (findrecordservice.updateStatus(ss, StringUtilsEX.ToInt(id)) > 0) {
            	
                logger.info(String.format("修改发现状态成功! ID:{0},状态:{1}", id, ss));
                item.setCode(0);
                item.setDesc("修改发现状态成功");
            } else {
                logger.warn(String.format("修改发现状态失败! ID:{0},状态:{1}", id, ss));
                item.setCode(-200);
                item.setDesc("修改发现状态失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("批量调整排序异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
             LogHandle.error(LogType.Find,"禁用/启用异常! 异常信息:{0}", e, "/platform/find/updateStatus");
        }
        return item;
    }
    
    /**
     * 编辑查看
     */
    @RequestMapping("/editFindRecord")
    public @ResponseBody ReusltItem editFindRecord(String title, int status, int oldid,String type,
                                                   String imgurl, String url, Integer sort) {
        ReusltItem item = new ReusltItem();
        int result = 0;
        try {
            Findrecord record = findrecordservice.selectFind(oldid);
            Findrecord findrecord = new Findrecord();
            findrecord.setTitle(title);
            findrecord.setStatus(status);
            findrecord.setImgurl(imgurl);
            findrecord.setUrl(url);
            findrecord.setSort(sort);
            BeanUtils.copyProperties(findrecord, record);
            result = findrecordservice.updateById(record);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("编辑成功");
            } else {
                item.setCode(-200);
                item.setDesc("编辑失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("编辑查看异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
        	LogHandle.error(LogType.Find,"编辑查看异常! 异常信息:{0}", e, "/platform/find/editFindRecord");
        }
        return item;
    }

    
    @RequestMapping("/getFindType")
    public @ResponseBody ReusltItem getFindType() {
        ReusltItem item = new ReusltItem();
        try {
        	FindTypeEnum[] array = FindTypeEnum.values();

            List<FindTypeDto> list = new ArrayList<FindTypeDto>();
            for (int i = 0; i < array.length; i++) {
            	FindTypeDto dTo = new FindTypeDto();
            	dTo.setCode(FindTypeEnum.values()[i].getValue());
            	dTo.setName(FindTypeEnum.values()[i].name());
                list.add(dTo);
            }
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("获取所有的发现类型异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
           LogHandle
                .error(LogType.Find,"获取所有的发现类型异常! 异常信息:{0}", e,
                    "/platform/find/getFindType");
        }
        return item;
    }
    


}

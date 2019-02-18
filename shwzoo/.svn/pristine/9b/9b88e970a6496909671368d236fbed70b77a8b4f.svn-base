/*
 * @(#) WapDiscoverController.java 2016年7月8日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.api.wap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.FindTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_FindCriteria;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.search.Wap_CommentCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Findrecord;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.FindRecordService;
import com.yinlian.wssc.web.service.FindRelationService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import data.StringUtil;

@RestController
@RequestMapping("/api/wap/discover")
public class WapDiscoverController {


	@Autowired
	private TopicService topicService;
	
	@Autowired
	private CommentService commentService;
	
	
	@Autowired SkuService skuService;
	
    @Autowired
    private FindRecordService   findrecordservice;
    
    @Autowired
    private  FindRelationService   findRelationService;
	/**
	 * 精选推荐
	 * 
	 * @author kh.wang
	 * @since 2016年7月8日
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/jingxuan")
	public String jingxuan(String page,String size,String id,Model model){
		
		ReusltItem item = new ReusltItem();
		try {
			Integer ipage = StringUtilsEX.ToIntNull(page);
			Integer isize = StringUtilsEX.ToIntNull(size);
			if (ipage == null || ipage <= 0) {
				ipage = 1;
			}
			if (isize == null || isize <= 0) {
				isize = 10;
			}
			Api_TopicBySpuCriteria atc = new Api_TopicBySpuCriteria();
			atc.setMark(5);
			if(StringUtil.isNotNull(id))
			atc.setClassID(data.ParseUtil.toInteger(id));
			
			PageBean listBean = topicService.getTopicBySpu(atc, ipage, isize);
			item.setData(listBean.getBeanList());
			item.setPage(listBean.getTp());
			item.setMaxRow(listBean.getTr());
			item.setPageIndex(ipage);
			item.setPageSize(isize);
			model.addAttribute("jx",item);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "新品上市异常异常! 异常信息:{0}", e,
					"discover/findspu");
		}
		
		return "/template/wap/discover/recommend_list";
	}
	
	/**
     * 查询发现
     * @param ch
     * @return
     */
    @RequestMapping(value = "/queryfind", produces = "text/html;charset=UTF-8")
    public String queryfind(String ch) {
        ReusltItem item = new ReusltItem();
        try {
          
        	if (!StringUtilsEX.isChannelTypeExist(ch)) {
 				item.setCode(-108);
 				item.setDesc("登录通道参数错误");
 				return item.toJson();
 			}
        	
            List<Findrecord> list=findrecordservice.queryFind();
            item.setCode(0);
            item.setData(list);
            item.setDesc("查询成功");
        } catch (Exception e) {
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
            LogHandle.error(LogType.wap,"查询发现信息异常! 异常信息:{0}", e, "discover/queryfind");
        }
        return item.toJson();
    }
	
	/**
     * 查询发现关联信息
     * @param ch
     * @return
     */
    @RequestMapping(value = "/getFindRleation", produces = "text/html;charset=UTF-8")
    public String queryfind(String findid,String type,String page,String size,String ch) {
        ReusltItem item = new ReusltItem();
        try {
        	
 			if (!StringUtilsEX.isChannelTypeExist(ch)) {
 				item.setCode(-108);
 				item.setDesc("登录通道参数错误");
 				return item.toJson();
 			}
 			
 			if (StringUtilsEX.IsNullOrWhiteSpace(findid)) {
                item.setCode(-101);
                item.setDesc("发现id不能为空");
                return item.toJson();
            }
 			Integer ipage = StringUtilsEX.ToIntNull(page);
			Integer isize = StringUtilsEX.ToIntNull(size);
			if (ipage == null || ipage <= 0) {
				ipage = 1;
			}
			if (isize == null || isize <= 0) {
				isize = 10;
			}
			
			Api_FindCriteria criteria=new Api_FindCriteria();
			criteria.setFindid(StringUtilsEX.ToInt(findid));
			
 			Integer _type=StringUtilsEX.ToInt(type);
 			
 			if(_type==FindTypeEnum.店铺动态.getValue()){
 				
 				PageBean listBean = findRelationService.selectFindspuList(criteria, ipage, isize);
 				item.setData(listBean.getBeanList());
 				item.setPage(listBean.getTp());
 				item.setMaxRow(listBean.getTr());
 				item.setPageIndex(ipage);
 				item.setPageSize(isize);
  				
  			}else if(_type==FindTypeEnum.专题.getValue()){
  				
  				PageBean listBean=findRelationService.selectFindTopicList(criteria, ipage, isize);
  				item.setData(listBean.getBeanList());
 				item.setPage(listBean.getTp());
 				item.setMaxRow(listBean.getTr());
 				item.setPageIndex(ipage);
 				item.setPageSize(isize);
 				
  			}else if(_type==FindTypeEnum.资讯文章.getValue()){
  				
  				PageBean listBean=findRelationService.selectFindArtList(criteria, ipage, isize);
  				item.setData(listBean.getBeanList());
 				item.setPage(listBean.getTp());
 				item.setMaxRow(listBean.getTr());
 				item.setPageIndex(ipage);
 				item.setPageSize(isize);
                
  			}else if(_type==FindTypeEnum.外部链接.getValue()){
  				
  				Findrecord findrecord=findrecordservice.selectFind(StringUtilsEX.ToInt(findid));
  				item.setData(findrecord.getUrl());
  			}
            item.setDesc("查询成功");
        } catch (Exception e) {
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
            LogHandle.error(LogType.wap,"查询发现管理信息异常! 异常信息:{0}", e, "discover/queryFindRleation");
        }
        return item.toJson();
    }
	
	/**
	 * 评论
	 * 
	 * @author kh.wang
	 * @since 2016年8月18日
	 * @param page
	 * @param size
	 * @param squId
	 * @return
	 */
	@RequestMapping("/pl")
	public ReusltItem pl(Integer page,Integer size,Integer squId){
		ReusltItem item = new ReusltItem();
		try {
			if (page == null || page <= 0) {
				page = 1;
			}
			if (size == null || size <= 0) {
				size = 10;
			}
			
			Wap_CommentCriteria wap_CommentCriteria=new Wap_CommentCriteria();
			
			wap_CommentCriteria.setSpuid(skuService.selectByID(squId).getSpuId());
			
			PageBean listBean = commentService.getProductDetailedCommentList(wap_CommentCriteria, page,size);
			item.setData(listBean.getBeanList());
			item.setPage(listBean.getTp());
			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "评论列表异常! 异常信息:{0}", e,
					"discover/findspu");
		}
		return item;
	}



}

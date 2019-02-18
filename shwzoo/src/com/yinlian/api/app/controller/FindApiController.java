package com.yinlian.api.app.controller;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.FindTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_FindCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaBaner;
import com.yinlian.wssc.web.po.Findrecord;
import com.yinlian.wssc.web.service.BanerService;
import com.yinlian.wssc.web.service.FindRecordService;
import com.yinlian.wssc.web.service.FindRelationService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 发现api
 */
@RestController
@RequestMapping("/api/app/find")
public class FindApiController {
   // private static final Logger logger = LoggerFactory.getLogger(FindViewController.class);
    @Autowired
    private FindRecordService   findrecordservice;
    
    @Autowired
    private  FindRelationService   findRelationService;

    @Autowired
	private BanerService banerService;
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
            LogHandle.error(LogType.Api,"查询发现信息异常! 异常信息:{0}", e, "find/queryfind");
        }
        return item.toJson();
    }
    
    /**
     * 查询发现关联信息
     * @param ch
     * @return
     */
    @RequestMapping(value = "/queryFindRleation", produces = "text/html;charset=UTF-8")
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
            LogHandle.error(LogType.Api,"查询发现管理信息异常! 异常信息:{0}", e, "find/queryFindRleation");
        }
        return item.toJson();
    }
    
    /**
     * 获取APPBaner数据
     * @param type 0：发现首页的banner  1：中绿首页的banner
     * @param size 不传默认为5
     * @return
     */
    @RequestMapping(value = "/getBanner", produces = "text/html;charset=UTF-8")
    private String  getBanner(String type,String size,String ch) {
    	ReusltItem item = new ReusltItem();
    	if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
		}
    	if (!StringUtilsEX.isChannelTypeExist(type)) {
			item.setCode(-101);
			item.setDesc("类型不能为空");
			return item.toJson();
    	}
		try {
			if(StringUtilsEX.ToInt(size)<0) size="5";
			CriteriaBaner criteria = new CriteriaBaner();
			criteria.setType(StringUtilsEX.ToInt(type));
			criteria.setOrderByClause("sort");
			PageBean pageBean = banerService.selBaner(criteria, 0, StringUtilsEX.ToInt(size));
			item.setData(pageBean.getBeanList());
			item.setCode(0);
			item.setDesc("获取Baner数据成功");
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
            LogHandle.error(LogType.Api,
                MessageFormat.format("获取Baner数据息异常! 异常信息:{0}", e), "find/getBanner");
		}
    	return item.toJson();
	}
    
    
}

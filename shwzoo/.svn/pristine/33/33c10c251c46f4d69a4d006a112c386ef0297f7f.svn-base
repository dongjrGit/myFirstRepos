package com.yinlian.wssc.platform.view.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.Result;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.ArticlePart;
import com.yinlian.wssc.web.service.ArticlepartService;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/zd")
public class ModuleManagerController {
	/**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(ModuleManagerController.class);
    
    @Autowired
    private ArticlepartService articlepartService;
    
    @RequestMapping("/ModuleManager")
    public String ModuleManager(){
    	return "/platform/zd/ModuleManager";
    }
    
    @RequestMapping("/index")
    @ResponseBody
    public ReusltItem index(String page,String size){
    	ReusltItem item=new ReusltItem();
    	try {
			Integer ipage=StringUtilsEX.ToIntNull(page);
			Integer isize=StringUtilsEX.ToIntNull(size);
			if(StringUtilsEX.ToInt(page)<=0){
				item.setCode(-101);
				item.setDesc("分页参数错误,pageindex:"+page);
				return item;
			}
			if(StringUtilsEX.ToInt(size)<=0){
				item.setCode(-102);
				item.setDesc("分页参数错误,pageindex:"+size);
				return item;
			}
			PageBean listBean=articlepartService.getListByPage(ipage, isize);
			item.setData(listBean.getBeanList());
			item.setPage(listBean.getTp());
			item.setMaxRow(listBean.getTr());
			item.setPageIndex(ipage);
			item.setPageSize(isize);
		} catch (Exception e) {
			item.setCode(-900);
			LogHandle.error(LogType.Log, "模块管理错误",e.getMessage());
		}
    	return item;
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public ReusltItem delete(String id){
    	ReusltItem item=new ReusltItem();
    	try {
    		if(StringUtilsEX.IsNullOrWhiteSpace(id)){
        		item.setCode(-1);
        		item.setDesc("id不能为空");
        		return item;
        	}
        	if(articlepartService.deleteByID(StringUtilsEX.ToInt(id))){
        		item.setDesc("删除成功");
        	}
		} catch (Exception e) {
			item.setCode(-900);
			LogHandle.error(LogType.Log, "模块删除错误:"+e.getMessage());
		}
    	return item;
    }
    
/*    @RequestMapping("/save")
    @ResponseBody()
    public ReusltItem save(HttpServletRequest request,String name){
    	ReusltItem item=new ReusltItem();
    	try {
    		if(articlepartService.selectByName(name)!=null){
    			item.setCode(-1);
    			item.setDesc("该名称已存在");
    			return item;
    		}
			ArticlePart part=new ArticlePart();
			part.setName(name);
			part.setCreatetime(new Date());
			part.setCreateuserid(SessionUtil.getSessionUserId(request));
			if(articlepartService.insert(part)){
				item.setDesc("增加成功");
			}
		} catch (Exception e) {
			item.setCode(-900);
			LogHandle.error(LogType.Log, "模块增加错误:"+e.getMessage());
		}
    	return item;
    }
*/	
	
}





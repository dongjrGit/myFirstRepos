package com.yinlian.wssc.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.ZL_BaseInfo;
import com.yinlian.wssc.web.po.ZL_BaseInfoExample;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.ZL_BaseInfoService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.zl_baseinfoCriteria;
import com.yl.soft.log.LogHandle;
/**
 * 中绿基地
 * @author YongKang.Ni
 *
 */
@RestController
@RequestMapping("/platform/zlbaseinfo")
public class zlBaseinfoController {
	@Autowired
    private ZL_BaseInfoService baseinfo ;
	@Autowired
	private NewsService newsservice;
	 /**
	  * 分页查询
	  * @param page
	  * @param size
	  * @return
	  */
	@RequestMapping("/listpage")
	public @ResponseBody ReusltItem index(String page, String size){
		 ReusltItem item = new ReusltItem();
		try {
			Integer ipage = StringUtilsEX.ToIntNull(page);
			Integer isize = StringUtilsEX.ToIntNull(size);
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
		        zl_baseinfoCriteria criteria=new zl_baseinfoCriteria();
		        PageBean listBean = baseinfo.selectListPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
		        item.setData(listBean.getBeanList());
				item.setPage(listBean.getTp());
				item.setMaxRow(listBean.getTr());
				item.setPageIndex(ipage);
				item.setPageSize(isize);
			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
	    			item.setDesc("中绿基地列表异常：" + e.getMessage());
	           } else {
	               item.setDesc("系统错误！");
	           }
	           item.setCode(-900);
	           LogHandle.error(LogType.BaseInfo, "中绿基地列表错误",e,"/platform/zlbaseinfo/listpage");
			 }
		return item;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody ReusltItem delete(String id){
		 ReusltItem item = new ReusltItem();
		try {
			baseinfo.deleteByPrimaryKey(StringUtilsEX.ToInt(id)); 
			item.setDesc("删除成功");
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
    			item.setDesc("删除中绿基地异常：" + e.getMessage());
           } else {
               item.setDesc("系统错误！");
           }
           item.setCode(-900);
           LogHandle.error(LogType.BaseInfo, "删除中绿基地错误",e,"/platform/zlbaseinfo/delete");
		}
		return item;
	}
	
	/**
	 * 添加或修改基地信息
	 * @param shengcode 省编码
	 * @param shicodede 市编码
	 * @param qucode 	区编码
	 * @param name		基地名称
	 * @param shengname 省名称
	 * @param quname    区名称
	 * @param shiname   市名称
	 * @param tel		电话
	 * @param longitude	经度
	 * @param latitude  纬度
	 * @param address   详细地址
	 * @param id		ID
	 * @return
	 */
	@RequestMapping("/Edit")
	public String Edit(String shengcode,String shicode,String qucode ,String name,String shengname,String quname,String
			shiname,String tel,String longitude,String latitude,String address,String id){
		ReusltItem item = new ReusltItem();
		try{
			ZL_BaseInfo info=new ZL_BaseInfo();
			if(id.equals("")||id==null){
				info.setAddress(address);
				info.setAreacode(qucode);
				info.setAreaname(quname);
				info.setCitycode(shicode);
				info.setCityname(shiname);
				info.setLatitude(latitude);
				info.setLongitude(longitude);
				info.setName(name);
				info.setProvincecode(shengcode);
				info.setProvincename(shengname);
				info.setTel(tel);
				baseinfo.insert(info);
				item.setDesc("添加基地成功");
			}else{
				info.setAddress(address);
				info.setAreacode(qucode);
				info.setAreaname(quname);
				info.setCitycode(shicode);
				info.setCityname(shiname);
				info.setLatitude(latitude);
				info.setLongitude(longitude);
				info.setName(name);
				info.setProvincecode(shengcode);
				info.setProvincename(shengname);
				info.setTel(tel);
				info.setId(StringUtilsEX.ToInt(id));
				baseinfo.updateByPrimaryKey(info);
				item.setDesc("修改基地成功");
			}
		}catch(Exception e){
			if (DebugConfig.BLUETOOTH_DEBUG) {
    			item.setDesc("创建或修改中绿基地信息错误：" + e.getMessage());
           } else {
               item.setDesc("系统错误！");
           }
           item.setCode(-900);
           LogHandle.error(LogType.BaseInfo, "创建或修改中绿基地信息错误",e,"/platform/zlbaseinfo/delete");
		}
		return item.toJson();
	}
	/**
	 * 获取中绿基地的省编码
	 * @return
	 */
	@RequestMapping(value = "/getcityCodename", produces = "text/html;charset=UTF-8")
	public String getAllCityCodename(){
		ReusltItem item = new ReusltItem();
		List<ZL_BaseInfo>  list=baseinfo.selectGroupBy();
		item.setData(list);
		return item.toJson();
	} 
	/**
	 * 根据省编码查询基地信息
	 * @param code
	 * @return
	 */
	public String getzlDjxxbyCity(String code){
		ReusltItem item = new ReusltItem();
		ZL_BaseInfoExample exm=new ZL_BaseInfoExample();
		exm.setOrderByClause(" ProvinceCode="+code);
		List<ZL_BaseInfo>  list=baseinfo.selectByExample(exm );
		item.setData(list);
		return item.toJson();
	}
	
	
	
}

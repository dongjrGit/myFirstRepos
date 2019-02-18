package com.yinlian.wssc.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.GroupbuyClass;
import com.yinlian.wssc.web.service.GroupByClassService;
import com.yinlian.wssc.web.util.CriteriaPage;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/tgroup")
public class GroupByClassController {
	
	@Autowired
	private GroupByClassService groupByClassService;
	
	/**
	 * 查询团购类型列表
	 * @param name
	 * @param index
	 * @param size
	 * @return
	 */
	@RequestMapping("/getlist")
	@ResponseBody
	public ReusltItem getTglxList(String name,String pageindex,String pagesize){
		ReusltItem item=new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
	        CriteriaPage criteria = new CriteriaPage();
		    criteria.setTitle(name);
		    criteria.setOrderByClause("sort");
	        PageBean list=groupByClassService.selectByCriteria(criteria,index,size);
	        item.setCode(0);
            item.setData(list.getBeanList());
            if(list.getTr()==null){
            	item.setMaxRow(0);
            }else{
            	item.setMaxRow(list.getTr());
            }
            item.setPageIndex(list.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取团购类型列表异常：" + e.getMessage());
			LogHandle.error(LogType.group,"获取团购类型列表异常!",e, "/platfrom/tgroup/getlist");
		}
		return item;
	}
	
	/**
	 * 删除团购类型
	 * @param id
	 * @return
	 */
	@RequestMapping("/delbyid")
	@ResponseBody
	public ReusltItem delTglxById(String id){
		ReusltItem item=new ReusltItem();
		try {
			if(groupByClassService.delById(StringUtilsEX.ToInt(id))>0){
				item.setDesc("删除成功!");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除团购类型异常：" + e.getMessage());
			LogHandle.error(LogType.group,"删除团购类型异常!",e, "/platfrom/tgroup/delbyid");
		}
		return item;
	}
	
	/**
	 * 编辑团购类型
	 * @param id
	 * @param name
	 * @param sort
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ReusltItem addTglx(String id,String name,String sort){
		ReusltItem item=new ReusltItem();
		try {
			GroupbuyClass gbc=new GroupbuyClass();
			gbc.setName(name);
			if(StringUtilsEX.IsNullOrWhiteSpace(sort)){
				gbc.setSort(0);
			}else{
				gbc.setSort(StringUtilsEX.ToInt(sort));
			}
			gbc.setFid(0);
			gbc.setLevel(1);
			if(StringUtilsEX.IsNullOrWhiteSpace(id)){
				groupByClassService.addTglx(gbc);
				item.setCode(0);
				item.setDesc("添加成功");
			}else{
				gbc.setId(StringUtilsEX.ToInt(id));
				groupByClassService.updateById(gbc);
				item.setCode(0);
				item.setDesc("修改成功");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("保存团购类型异常：" + e.getMessage());
			LogHandle.error(LogType.group,"保存团购类型异常!",e, "/platfrom/tgroup/save");
		}
		return item;
	}
	
}

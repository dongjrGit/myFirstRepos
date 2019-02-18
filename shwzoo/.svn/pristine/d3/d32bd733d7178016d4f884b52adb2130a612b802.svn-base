/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.SearchAttrType_Enum;
import com.yinlian.Enums.SearchattrStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.ProductSpecsCustom;
import com.yinlian.wssc.web.po.Searchattr;
import com.yinlian.wssc.web.po.Searchvalue;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProductSpecsService;
import com.yinlian.wssc.web.service.SearchattrService;
import com.yinlian.wssc.web.service.SearchvalueService;
import com.yinlian.wssc.web.service.SpecsvaluesService;
import com.yinlian.wssc.web.util.BasicConvert;
import com.yinlian.wssc.web.util.CriteriaAttr;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 搜索属性的控制类
 * 
 * @author Administrator
 * @version $Id: SearchattrController.java, v 0.1 2016年2月29日 下午4:53:06
 *          Administrator Exp $
 */
@Controller
@RequestMapping("/platform/searchattr")
public class SearchattrController {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(SearchattrController.class);

	@Autowired
	private SearchattrService searchattrService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private SearchvalueService searchvalueService;
	@Autowired
	private ProductSpecsService productSpecsService;
	@Autowired
	private SpecsvaluesService specsvaluesService;
	
	SessionUser user=null;
    @Autowired
    private OperaterecordsService operaterecordsService ;

	/**
	 * 根据分类id查询搜索属性 得分页信息
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	@RequestMapping("/querySearchAttribute")
	public @ResponseBody ReusltItem querySearchAttribute(String classid,
			String name, @RequestParam("page") String pc,
			@RequestParam("size") String ps) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(pc) <= 0) {

				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex：" + pc);
			}
			if (StringUtilsEX.ToInt(ps) <= 0) {
				item.setCode(-103);
				item.setDesc("分页参数错误，pagesize：" + ps);
			}
			CriteriaAttr criteria = new CriteriaAttr();
			criteria.setClassid(StringUtilsEX.ToInt(classid));
			criteria.setSearchattrrname(name);
			criteria.setOrderByClause("sort");
			PageBean pageBean = searchattrService.selectSearchattrPage(
					criteria, StringUtilsEX.ToInt(pc), StringUtilsEX.ToInt(ps));
			item.setCode(0);
			item.setDesc("查询成功");
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取根据分类id查询搜索属性 得分页信息的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Product,
					"获取根据分类id查询搜索属性 得分页信息的信息出错! 异常信息:{0}", e,
					"/platform/searchattr/querySearchAttribute");
		}
		return item;
	}

	/**
	 * 根据搜索属性id 查询搜索属性值得分页信息
	 * 
	 * @param classID
	 *            分类id
	 * @param name
	 *            属性的名称
	 * @param pc
	 *            当前页
	 * @param ps
	 *            每页的大小
	 * @return
	 */
	@RequestMapping("/querySearchValue")
	public @ResponseBody ReusltItem querySearchValue(String attrid,
			@RequestParam(value = "page") String pc,
			@RequestParam(value = "size") String ps) {

		ReusltItem item = new ReusltItem();
		if (StringUtilsEX.ToInt(pc) <= 0) {
			item.setCode(-102);
			item.setDesc("分页参数错误，pageindex：" + pc);
			return item;
		}
		if (StringUtilsEX.ToInt(ps) <= 0) {
			item.setCode(-103);
			item.setDesc("分页参数错误，pagesize：" + ps);
			return item;
		}
		try {
			CriteriaAttr criteria = new CriteriaAttr();
			criteria.setAttrid(StringUtilsEX.ToInt(attrid));
			PageBean pageBean = searchattrService.selectSearchvaluePage(
					criteria, StringUtilsEX.ToInt(pc), StringUtilsEX.ToInt(ps));
			item.setCode(0);
			item.setDesc("查询成功");
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageSize(pageBean.getPs());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取根据搜索属性id 查询搜索属性值得分页信息的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product,
					"获取根据搜索属性id 查询搜索属性值得分页信息的信息出错! 异常信息:{0}", e,
					"/platform/searchattr/querySearchValue");
		}
		return item;
	}

	/**
	 * 根据搜索属性的id查询搜索属性的信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/querySearchAttributeById")
	public @ResponseBody ReusltItem querySearchAttributeById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("搜索属性的id参数错误,id:" + id);
				return item;
			}
			Searchattr searchattr = searchattrService
					.selectSearchAttrById(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(searchattr);
			item.setDesc("查询成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取根据搜索属性的id查询搜索属性的信息的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product,
					"获取根据搜索属性的id查询搜索属性的信息的信息出错! 异常信息:{0}", e,
					"/platform/searchattr/querySearchAttributeById");
		}
		return item;
	}

	/**
	 * 更新搜索属性的信息
	 * 
	 * @param id
	 * @param name
	 * @param sort
	 * @param status
	 * @param ischeckbox
	 * @return
	 */
	@RequestMapping("/updateSearchAttr")
	public @ResponseBody ReusltItem updateSearchAttr(String id, String name,
			String sort, String status, String ischeckbox,String userSites) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (BasicConvert.string2Integer(id) < 0) {
				item.setCode(-101);
				item.setDesc("商品属性的ID参数错误,id:" + id);
				return item;
			}
			Searchattr searchattr = checkSearchattrParam(name, sort, status,
					ischeckbox, item);
			if (item.getCode() < 0) {
				return item;
			}
			searchattr.setUsesite(userSites);
			searchattr.setId(StringUtilsEX.ToInt(id));
			int result = searchattrService.updateRecord(searchattr);
			if (result > 0) {
				item.setCode(0);
				item.setData(searchattr);
				item.setDesc("更新成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_search_list.jsp", "/platform/searchattr/updateSearchAttr", "更新搜索属性的信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"更新搜索属性的信息操作记录出错! 异常信息:",
    								e, "/platform/searchattr/updateSearchAttr");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setData(searchattr);
				item.setDesc("更新失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取更新搜索属性的信息的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Product,"获取更新搜索属性的信息的信息出错! 异常信息:{0}",
							e, "/platform/searchattr/updateSearchAttr");

		}
		return item;
	}

	/**
	 * 根据主键删除对象
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteSearchAttrById")
	public @ResponseBody ReusltItem deleteSearchAttrById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("搜索属性的id参数错误,id:" + id);
				return item;
			}
			int result = searchattrService.deleteSearchAttrById(BasicConvert
					.string2Integer(id));
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
                            		user.getUserId(), user.getLoginName(), "goods_spgl_search_list.jsp", "/platform/searchattr/deleteSearchAttrById", "根据主键删除对象");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据主键删除对象操作记录出错! 异常信息:",
    								e, "/platform/searchattr/deleteSearchAttrById");
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
				item.setDesc("获取根据主键删除对象的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Product,"获取根据主键删除对象的信息出错! 异常信息:{0}",
							e, "/platform/searchattr/deleteSearchAttrById");
		}
		return item;
	}

	/**
	 * 增加一个搜索属性
	 * 
	 * @param name
	 * @param sort
	 * @param status
	 * @param ischeckbox
	 * @return
	 */
	@RequestMapping("/insertSearchAttr")
	public @ResponseBody ReusltItem insertSearchAttr(String fullpath,
			String id, String classID, String name,
			@RequestParam(value = "order") String sort, String status,
			String isCheckBox, String typeID, String attrType,String userSites) {
		ReusltItem item = new ReusltItem();
		int result = 0;
		try {
			user=SessionState.GetCurrentUser();
			Searchattr searchattr = checkSearchattrParam(name, sort, status,
					isCheckBox, item);
			if (item.getCode() < 0) {
				return item;
			}
			if (!"0".equals(id)) {
				searchattr = searchattrService
						.selectSearchAttrById(StringUtilsEX.ToInt(id));
				searchattr.setIscheckbox(StringUtilsEX
						.toBooleanForOS(isCheckBox));
				searchattr.setSort(StringUtilsEX.ToInt(sort));
                searchattr.setUsesite(userSites);
				result = searchattrService.updateRecord(searchattr);
			} else {
				searchattr.setFullpath(fullpath);
				searchattr.setClassid(StringUtilsEX.ToInt(classID));
				searchattr.setAttrtype(StringUtilsEX.ToInt(attrType));
                searchattr.setTypeid(StringUtilsEX.ToInt(typeID));
                searchattr.setUsesite(userSites);
				searchattr.setStatus(SearchattrStatusEnum.启用.getValue());
				result = searchattrService.insert(searchattr);
			}
			if (result > 0) {
				item.setCode(0);
				item.setData(searchattr);
				item.setDesc("保存成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_search_list.jsp", "/platform/searchattr/insertSearchAttr", "增加一个搜索属性");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"增加一个搜索属性操作记录出错! 异常信息:",
    								e, "/platform/searchattr/insertSearchAttr");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setData(searchattr);
				item.setDesc("保存失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取增加一个搜索属性的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Product,"获取增加一个搜索属性的信息出错! 异常信息:{0}",
							e, "/platform/searchattr/insertSearchAttr");
		}

		return item;
	}

	/**
	 * 新增一个搜索属性的值得对象
	 * 
	 * @param type
	 * @param value
	 * @param unit
	 * @param sort
	 * @return
	 */

	@RequestMapping("/insertSearchValue")
	public @ResponseBody ReusltItem insertSearchValue(String type,
			String value, String unit, String sort, String attrID) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			Searchvalue searchvalue = checksearchvalueParam(type, value, unit,
					sort, item);
			if (item.getCode() < 0) {
				return item;
			}
			int ishave = specsvaluesService.ishave(StringUtilsEX.ToInt(attrID),
					value);
			if (ishave > 0) {
				searchvalue.setValue(attrID + "-" + value);
				searchvalue.setAttrid(StringUtilsEX.ToInt(attrID));
				int result = searchvalueService.insert(searchvalue);
				if (result > 0) {
					item.setCode(0);

					item.setDesc("新增成功");
					//异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "goods_spgl_searchvalue_list.jsp", "/platform/searchattr/insertSearchValue", "新增一个搜索属性的值得对象");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"新增一个搜索属性的值得对象操作记录出错! 异常信息:",
	    								e, "/platform/searchattr/insertSearchValue");
	    					}
	    					
	    				}
	    			});
				} else {
					item.setCode(-200);

					item.setDesc("新增失败");
				}
			} else {
				item.setCode(-110);
				item.setDesc("无该属性值");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取新增一个搜索属性的值得对象的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Product,"获取新增一个搜索属性的值得对象的信息出错! 异常信息:{0}",
							e, "/platform/searchattr/insertSearchValue");
		}
		return item;
	}

	/**
	 * 修改一个搜索属性值的对象
	 * 
	 * @param id
	 * @param type
	 * @param value
	 * @param unit
	 * @param sort
	 * @return
	 */
	@RequestMapping("/updateSearchValue")
	public @ResponseBody ReusltItem updateSearchValue(String id, String type,
			String value, String unit, String sort, String attrID,
			String attrtype, String minval, String maxval, String specsid) {

		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("商品属性值的ID参数错误,id:" + id);
				return item;

			}
			Searchvalue searchvalue = checksearchvalueParam(type, value, unit,
					sort, item);
			if (item.getCode() < 0) {
				return item;
			}
			int ishave = 1;
			if ("2".equals(attrtype)) {
				Brand brand = brandService.getbyName(value);
				if (brand == null) {
					item.setCode(-102);
					item.setDesc("品牌不存在");
					return item;
				}
				searchvalue.setValue(brand.getId() + "-" + value);
			} else {
				if ("1".equals(type)) {
					searchvalue.setValue(minval + "-" + maxval);
				} else {
					searchvalue.setValue(value);
				}
			}

			searchvalue.setAttrid(StringUtilsEX.ToInt(attrID));
			int result = -1;
			if ("0".equals(id)) {
				if (ishave > 0) {
					result = searchvalueService.insert(searchvalue);
					if (result > 0) {
						item.setCode(0);

						item.setDesc("新增成功");
						//异步操作 不影响正常流程
		                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		    			cachedThreadPool.execute(new Runnable() {
		    				@Override
		    				public void run() {
		    					try{
		    						operaterecordsService.insertOperaterecords(
		                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
		                            		user.getUserId(), user.getLoginName(), "goods_spgl_searchvalue_list.jsp", "/platform/searchattr/updateSearchValue", "新增一个搜索属性的值得对象");
		    					}
		    					catch(Exception e){
		    						LogHandle.error(LogType.OperateRecords,"新增一个搜索属性的值得对象操作记录出错! 异常信息:",
		    								e, "/platform/searchattr/updateSearchValue");
		    					}
		    					
		    				}
		    			});
					} else {
						item.setCode(-200);
						item.setDesc("新增失败");
					}
				} else {
					item.setCode(-110);
					item.setDesc("无该属性值");
				}
			} else {
				if (ishave > 0) {
					searchvalue.setId(StringUtilsEX.ToInt(id));
						result = searchvalueService.update(searchvalue);
					if (result > 0) {
						item.setCode(0);
						item.setDesc("修改成功");
						//异步操作 不影响正常流程
		                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		    			cachedThreadPool.execute(new Runnable() {
		    				@Override
		    				public void run() {
		    					try{
		    						operaterecordsService.insertOperaterecords(
		                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
		                            		user.getUserId(), user.getLoginName(), "goods_spgl_searchvalue_list.jsp", "/platform/searchattr/updateSearchValue", "修改一个搜索属性的值得对象");
		    					}
		    					catch(Exception e){
		    						LogHandle.error(LogType.OperateRecords,"修改一个搜索属性的值得对象操作记录出错! 异常信息:",
		    								e, "/platform/searchattr/updateSearchValue");
		    					}
		    					
		    				}
		    			});
					} else {
						item.setCode(-200);

						item.setDesc("修改失败");
					}
				} else {
					item.setCode(-110);
					item.setDesc("无该属性值");
				}

			}

		} catch (Throwable e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取修改一个搜索属性值的对象的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Product,"获取修改一个搜索属性值的对象的信息出错! 异常信息:"+e.getMessage(), "/platform/searchattr/updateSearchValue");
		}
		return item;
	}

	/**
	 * 删除一个搜索属性值对象
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteSearchValue")
	public @ResponseBody ReusltItem deleteSearchValue(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			int result = searchvalueService.delete(StringUtilsEX.ToInt(id));
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
                            		user.getUserId(), user.getLoginName(), "goods_spgl_searchvalue_list.jsp", "/platform/searchattr/deleteSearchValue", "删除一个搜索属性值对象");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除一个搜索属性值对象操作记录出错! 异常信息:",
    								e, "/platform/searchattr/deleteSearchValue");
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
				item.setDesc("获取删除一个搜索属性值对象的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Product,"获取删除一个搜索属性值对象的信息出错! 异常信息:{0}",
							e, "/platform/searchattr/deleteSearchValue");
		}
		return item;
	}

	/**
	 * 查询一个搜索属性值对象
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/querySearchValueById")
	public @ResponseBody ReusltItem querySearchValueById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (BasicConvert.string2Integer(id) < 0) {
				item.setCode(-101);
				item.setDesc("搜索属性值的id参数错误,id:" + id);
				return item;
			}
			Searchvalue searchvalue = searchvalueService
					.selectById(BasicConvert.string2Integer(id));
			item.setCode(0);
			item.setData(searchvalue);
			item.setDesc("查询成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取查询一个搜索属性值对象的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Product,"获取查询一个搜索属性值对象的信息出错! 异常信息:{0}",
							e, "/platform/searchattr/querySearchValueById");
		}
		return item;
	}

	/**
	 * 根据商品类别查询规格
	 * 
	 * @param classID
	 * @return
	 */
	@RequestMapping("/querySpecsByClassId")
	public @ResponseBody ReusltItem querySpecsByClassId(String classID) {
		ReusltItem item = new ReusltItem();
		try {
			List<ProductSpecsCustom> list = productSpecsService
					.querySpecsByClassid(StringUtilsEX.ToInt(classID));
			if (list.size() > 0) {
				item.setCode(0);
				item.setData(list);
				item.setDesc("查询成功");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取查询一个搜索属性值对象的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Product,"获取查询一个搜索属性值对象的信息出错! 异常信息:{0}",
							e, "/platform/searchattr/querySpecsByClassId");
		}
		return item;
	}

	/**
	 * 修改状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public @ResponseBody ReusltItem updateStatus(String id, String status) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			Integer ids = StringUtilsEX.ToInt(id);
			if (ids <= 0) {
				item.setCode(-101);
				item.setDesc("参数错误：id" + id);
			}
			Searchattr searchattr = searchattrService.selectSearchAttrById(ids);
			searchattr.setStatus(StringUtilsEX.ToInt(status));
			int result = searchattrService.updateRecord(searchattr);
			if (result > 0) {
				item.setCode(0);
				item.setData("更改成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_search_list.jsp", "/platform/searchattr/updateStatus", "修改状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改状态操作记录出错! 异常信息:",
    								e, "/platform/searchattr/updateStatus");
    					}
    					
    				}
    			});
			} else {
				item.setCode(0);
				item.setData("更改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取修改的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product," 异常信息:{0}", e,
					"/platform/searchattr/updateStatus");
		}
		return item;
	}

	/**
	 * 验证
	 * 
	 * @param name
	 * @param sort
	 * @param status
	 * @param ischeckbox
	 * @param item
	 * @return
	 * @throws Exception
	 */

	private Searchattr checkSearchattrParam(String name, String sort,
			String status, String ischeckbox, ReusltItem item) throws Exception {
		Searchattr searchattr = new Searchattr();
		if (StringUtilsEX.isBlank(name)) {
			item.setCode(-102);
			item.setDesc("商品属性名称不能为空");
		}
		if (StringUtilsEX.ToInt(sort) < 0) {
			item.setCode(-103);
			item.setDesc("商品属性排序参数错误,sort:" + sort);
		}
		if (StringUtilsEX.ToInt(status) < 0) {
			item.setCode(-104);
			item.setDesc("商品属性状态参数错误,status:" + status);
		}
		if (StringUtilsEX.ToInt(ischeckbox) < 0) {
			item.setCode(-105);
			item.setDesc("商品属性是否多选参数错误,ischeckbox:" + ischeckbox);
		}
		searchattr.setName(name);
		searchattr.setSort(StringUtilsEX.ToInt(sort));
		searchattr.setStatus(StringUtilsEX.ToInt(status));

		searchattr.setIscheckbox(StringUtilsEX.toBooleanForOS(ischeckbox));

		return searchattr;
	}

	/**
	 * 验证
	 * 
	 * @param type
	 * @param value
	 * @param unit
	 * @param sort
	 * @param item
	 * @return
	 * @throws Exception
	 */
	private Searchvalue checksearchvalueParam(String type, String value,
			String unit, String sort, ReusltItem item) throws Exception {
		Searchvalue searchvalue = new Searchvalue();

		if (StringUtilsEX.ToInt(type) < 0) {
			item.setCode(-102);
			item.setDesc("商品属性值的类型参数错误，type:" + type);
		}
		if (value == null) {
			item.setCode(-103);
			item.setDesc("商品属性值不能为空");
		}
		if (unit == null) {
			item.setCode(-104);
			item.setDesc("商品属性值的单位不能为空");
		}
		if (StringUtilsEX.ToInt(sort) < 0) {
			item.setCode(-105);
			item.setDesc("商品属性值的排序参数错误sort:" + sort);
		}
		searchvalue.setType(StringUtilsEX.ToInt(type));
		searchvalue.setValue(value);
		searchvalue.setUnit(unit);
		searchvalue.setSort(StringUtilsEX.ToInt(sort));

		return searchvalue;
	}
}

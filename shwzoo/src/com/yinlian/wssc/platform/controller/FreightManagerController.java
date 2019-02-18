package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.po.FreightAttr;
import com.yinlian.wssc.web.po.Sendtemplate;
import com.yinlian.wssc.web.service.FreightAttrService;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaFreightAttr;
import com.yinlian.wssc.web.util.CriteriaSendTemplate;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 配送设置的控制类
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/platform/freightmanager")
public class FreightManagerController {

	@Autowired
	private FreightService freightService;
	@Autowired
	private FreightAttrService freightAttrService;
	
	SessionUser                 user   = null;
    @Autowired
    private OperaterecordsService operaterecordsService ;

	/**
	 * 分页查询所有的运费模板
	 */
	@RequestMapping("/queryFreightManagerList")
	public @ResponseBody ReusltItem queryFreightManagerList(String page, String size, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) < 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误,page" + page);
				return item;
			}
			if (StringUtilsEX.ToInt(size) < 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误,size" + size);
				return item;
			}

			Integer shopid = SessionUtil.getSessionUserShopId(request);
			Criteria criteria = new Criteria();
			criteria.setShopid(shopid);
			PageBean pageBean = freightService.queryFreightByShopId(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setPageIndex(pageBean.getPc());
			item.setMaxRow(pageBean.getTr());
			item.setPageSize(pageBean.getPs());
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询模板错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"查询模板异常! 异常信息:{0}", e,
					"/platform/freightmanager/queryFreightManagerList");
		}
		return item;
	}

	/**
	 * 根据id删除配送设置
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteFreightManagerById")
	public @ResponseBody ReusltItem deleteFreightManagerById(Integer id,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			int result = freightService.deleteFreightManagerById(id);

			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "Freight_Manager_list.jsp", "/platform/freightmanager/deleteFreightManagerById", "根据id删除配送设置");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"根据id删除配送设置操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/deleteFreightManagerById");
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
        		item.setDesc("删除模板错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"删除模板异常! 异常信息:{0}", e,
					"/platform/freightmanager/deleteFreightManagerById");
		}
		return item;
	}

	/**
	 * 设为默认的
	 * 
	 * @param id
	 * @param shopid
	 * @return
	 */
	@RequestMapping("/setDefault")
	public @ResponseBody ReusltItem setDefault(String id, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("模板id错误");
				return item;
			}

			Integer shopid = SessionUtil.getSessionUserShopId(request);
			int result = freightService.updateStatusDefault(StringUtilsEX.ToInt(id), shopid);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("设置成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "Freight_Manager_list.jsp", "/platform/freightmanager/setDefault", "设置默认模板");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"设置默认模板操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/setDefault");
        					}
        					
        				}
        			});
			} else {
				item.setCode(-200);
				item.setDesc("设置失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("设置默认模板错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching, "设置默认模板异常! 异常信息:{0}", e,
					"/platform/freightmanager/setDefault");
		}
		return item;
	}

	/**
	 * 根据id查询模板信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/qeuryFreightById")
	public @ResponseBody ReusltItem qeuryFreightById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("模板id错误");
				return item;
			}

			Freight freight = freightService.selectById(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(freight);
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询模板信息错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"查询模板信息异常! 异常信息:{0}", e,
					"/platform/freightmanager/qeuryFreightById");
		}
		return item;
	}

	/**
	 * 获取模板详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/qeuryFreightAttrById")
	public @ResponseBody ReusltItem qeuryFreightAttrById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("模板id错误");
				return item;
			}

			List<FreightAttr> list = freightAttrService.selectByFreightId(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询模板详细信息错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"查询模板详细信息异常! 异常信息:{0}", e,
					"/platform/freightmanager/qeuryFreightAttrById");
		}
		return item;
	}

	/**
	 * 添加模板
	 * 
	 * @param shopid
	 * @param name
	 * @param isExemptionPostage
	 * @param pricingModel
	 * @param transportModel
	 * @param setNum
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody ReusltItem add(String name, String isExemptionPostage, String pricingModel,
			String transportModel, String setNum,String iscondition, String description, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			checkParam(name, isExemptionPostage, pricingModel, transportModel, setNum,iscondition,item);
			if (item.getCode() < 0) {
				return item;
			}

			Integer shopid = SessionUtil.getSessionUserShopId(request);
			int result = freightService.insert(shopid, name, StringUtilsEX.ToInt(isExemptionPostage),
					StringUtilsEX.ToInt(pricingModel), StringUtilsEX.ToInt(transportModel), StringUtilsEX.ToInt(setNum),
					StringUtilsEX.ToInt(iscondition),description);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("添加成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "Freight_Manager_list.jsp", "/platform/freightmanager/add", "添加模板");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加模板操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/add");
        					}
        					
        				}
        			});
			} else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("添加模板信息错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching, "添加模板信息异常! 异常信息:{0}", e,
					"/platform/freightmanager/add");
		}
		return item;
	}

	/**
	 * 修改模板
	 * 
	 * @param id
	 * @param shopid
	 * @param name
	 * @param isExemptionPostage
	 * @param pricingModel
	 * @param transportModel
	 * @param setNum
	 * @param json_attrList
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody ReusltItem update(String id, String name, String isExemptionPostage, String pricingModel,
			String transportModel, String setNum,String iscondition, String json_attrList, String description, String areas,
			String firstcount, String firstprice, String elsecount, String elseprice, String attrid,
			HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("模板id错误");
				return item;
			}
			checkParam(name, isExemptionPostage, pricingModel, transportModel, setNum, iscondition,item);
			if (item.getCode() < 0) {
				return item;
			}
			List<FreightAttr> attrs = new ArrayList<FreightAttr>();
			if (StringUtils.isNotNull(json_attrList)) {
				String[] errey = json_attrList.split("\\|");
				for (String string : errey) {
					String[] array = string.split(";");
					String areasString = array[0];
					areas = areasString.substring(areasString.indexOf(":") + 2, areasString.length() - 1);
					String firstcountsString = array[1];
					firstcount = firstcountsString.substring(firstcountsString.indexOf(":") + 2,
							firstcountsString.length() - 1);
					String firstpriceString = array[2];
					firstprice = firstpriceString.substring(firstpriceString.indexOf(":") + 2,
							firstpriceString.length() - 1);
					String elsecountString = array[3];
					elsecount = elsecountString.substring(elsecountString.indexOf(":") + 2,
							elsecountString.length() - 1);
					String elsepriceString = array[4];
					elseprice = elsepriceString.substring(elsepriceString.indexOf(":") + 2,
							elsepriceString.length() - 1);
					String attridString = array[5];
					attrid = attridString.substring(attridString.indexOf(":") + 2, attridString.length() - 1);
					String statussString = array[5];
					String status = statussString.substring(statussString.indexOf(":") + 2, statussString.length() - 1);
					FreightAttr freightAttr = new FreightAttr();
					freightAttr.setId(StringUtilsEX.ToInt(attrid));
					freightAttr.setAreas(areas);
					freightAttr.setFirstcount(StringUtilsEX.ToInt(firstcount));
					freightAttr.setFirstprice(StringUtilsEX.ToFloat(firstprice));
					freightAttr.setElsecount(StringUtilsEX.ToInt(elsecount));
					freightAttr.setElseprice(StringUtilsEX.ToFloat(elseprice));
					freightAttr.setStatus(StringUtilsEX.ToInt(status));
					attrs.add(freightAttr);
				}
			}

			Integer shopid = SessionUtil.getSessionUserShopId(request);
			int result = freightService.update(StringUtilsEX.ToInt(id), shopid, name,
					StringUtilsEX.ToInt(isExemptionPostage), StringUtilsEX.ToInt(pricingModel),
					StringUtilsEX.ToInt(transportModel), StringUtilsEX.ToInt(setNum),StringUtilsEX.ToInt(iscondition), description, attrs);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "Freight_Manager_list.jsp", "/platform/freightmanager/update", "修改模板");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改模板操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/update");
        					}
        					
        				}
        			});
			} else {
				item.setCode(-200);
				item.setDesc("修改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("修改模板信息错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"修改模板信息异常! 异常信息:{0}", e,
					"/platform/freightmanager/update");
		}
		return item;
	}

	/**
	 * 校验参数
	 * 
	 * @param shopid
	 * @param name
	 * @param isExemptionPostage
	 * @param pricingModel
	 * @param transportModel
	 * @param setNum
	 * @param item
	 */
	private void checkParam(String name, String isExemptionPostage, String pricingModel, String transportModel,
			String setNum, String iscondition,ReusltItem item) {

		if (!StringUtils.isNotNull(name)) {
			item.setCode(-102);
			item.setDesc("请输入模板名称");
		}	
		if (StringUtilsEX.ToInt(isExemptionPostage) < 0) {
			item.setCode(-104);
			item.setDesc("请选择是否包邮");
		}
		if(isExemptionPostage.equals("1")){
			if (!StringUtils.isNotNull(setNum)) {
				item.setCode(-103);
				item.setDesc("请输入计价方式数值");
			}
			if (!StringUtils.isDigit(setNum)) {
				item.setCode(-107);
				item.setDesc("计价方式数值请填不小于的数字");
			}
			if (StringUtilsEX.ToInt(iscondition) < 0) {
				item.setCode(-107);
				item.setDesc("请选择是否满足条件");
			}
		}
		
		if (StringUtilsEX.ToInt(pricingModel) < 0) {
			item.setCode(-105);
			item.setDesc("请选择是计件方式");
		}
		if (StringUtilsEX.ToInt(transportModel) < 0) {
			item.setCode(-106);
			item.setDesc("请选择运送方式");
		}
	}

	/**
	 * 删除模板详细信息根据id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAttr")
	public @ResponseBody ReusltItem deleteAttr(String id,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("详细模板id错误");
				return item;
			}

			int result = freightService.deleteAttr(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "Freight_Manager_list.jsp", "/platform/freightmanager/deleteAttr", "删除模板详细信息根据id");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"删除模板详细信息根据id操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/deleteAttr");
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
        		item.setDesc("删除模板详细信息错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"删除模板详细信息异常! 异常信息:{0}", e,
					"/platform/freightmanager/deleteAttr");
		}
		return item;
	}

	/**
	 * 添加模板详细配置
	 * 
	 * @param freightid
	 * @param areas
	 * @param firstcount
	 * @param firstprice
	 * @param elsecount
	 * @param elseprice
	 * @param atInNum
	 * @return
	 */
	@RequestMapping("/addAttr")
	public @ResponseBody ReusltItem addAttr(String freightid, String areas, String firstcount, String firstprice,
			String elsecount, String elseprice, String atInNum,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			checkParamAttr(freightid, areas, firstcount, firstprice, elsecount, elseprice, atInNum, item);
			if (item.getCode() < 0) {
				return item;
			}
			
			int result = freightService.insertAttr(StringUtilsEX.ToInt(freightid), areas,
					StringUtilsEX.ToInt(firstcount), StringUtilsEX.ToFloat(firstprice), StringUtilsEX.ToInt(elsecount),
					StringUtilsEX.ToFloat(elseprice));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("添加成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "Freight_Manager_list.jsp", "/platform/freightmanager/addAttr", "添加模板详细配置");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加模板详细配置操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/addAttr");
        					}
        					
        				}
        			});
			} else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("添加模板详细信息错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"添加模板详细信息异常! 异常信息:{0}", e,
					"/platform/freightmanager/addAttr");
		}
		return item;
	}

	/**
	 * 校验参数attr
	 * 
	 * @param freightid
	 * @param areas
	 * @param firstcount
	 * @param firstprice
	 * @param elsecount
	 * @param elseprice
	 * @param atInNum
	 * @param item
	 */
	private void checkParamAttr(String freightid, String areas, String firstcount, String firstprice, String elsecount,
			String elseprice, String atInNum, ReusltItem item) {
		if (!StringUtils.isNotNull(firstcount)) {
			firstcount = "0";
		}
		if (!StringUtils.isNotNull(firstprice)) {
			firstprice = "0";
		}
		if (!StringUtils.isNotNull(elsecount)) {
			elsecount = "0";
		}
		if (!StringUtils.isNotNull(atInNum)) {
			atInNum = "0";
		}
		if (!StringUtils.isNotNull(elseprice)) {
			elseprice = "0";
		}
		if (StringUtilsEX.ToInt(freightid) < 0) {
			item.setCode(-101);
			item.setDesc("参数模板id错误");
		}
		if (!StringUtils.isNotNull(areas)) {
			item.setCode(-102);
			item.setDesc("请选择区域");
		}
		if (StringUtilsEX.ToInt(firstcount) < 0) {
			item.setCode(-103);
			item.setDesc("请选择首件数量");
		}
		if (StringUtilsEX.ToFloat(firstprice) < 0) {
			item.setCode(-104);
			item.setDesc("请填写首件价格");
		}
		if (StringUtilsEX.ToInt(elsecount) < 0) {
			item.setCode(-103);
			item.setDesc("请选择续件数量");
		}
		if (StringUtilsEX.ToFloat(elseprice) < 0) {
			item.setCode(-104);
			item.setDesc("请填写续件价格");
		}
	}

	/**
	 * 添加发送模板
	 * 
	 * @param type
	 * @param content
	 * @param ctype
	 * @param sort
	 * @return
	 */
	@RequestMapping("/addSendTemplate")
	public @ResponseBody ReusltItem addSendTemplate(String type, String content, String ctype, String tag,
			String sort,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtils.isBlanck(content)) {
				item.setCode(-102);
				item.setDesc("请填写内容");
				return item;
			}
			if (StringUtilsEX.ToInt(ctype) < 0) {
				item.setCode(-103);
				item.setDesc("请选择内容类型");
				return item;
			}
			// if (StringUtilsEX.ToInt(sort)<0) {
			// item.setCode(-106);
			// item.setDesc("排序必须是数字");
			// return item;
			// }
			if (StringUtilsEX.ToInt(sort) < 0) {
				item.setCode(-104);
				item.setDesc("请填写排序");
				return item;
			}

			if (StringUtilsEX.ToInt(type) < 0) {
				item.setCode(-105);
				item.setDesc("请选择类型");
				return item;
			}
			if (StringUtilsEX.ToInt(tag) < 0) {
				item.setCode(-106);
				item.setDesc("请选择模板标识");
				return item;
			}
			int result = freightService.insertSendTemplate(StringUtilsEX.ToInt(type), content,
					StringUtilsEX.ToInt(ctype), StringUtilsEX.ToInt(sort), StringUtilsEX.ToInt(tag));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("添加成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "sendTemplateEdit.jsp", "/platform/freightmanager/addSendTemplate", "添加发送模板");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加发送模板操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/addSendTemplate");
        					}
        					
        				}
        			});
			} else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("添加发送模板错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"添加发送模板异常! 异常信息:{0}", e,
					"/platfrom/freightmanager/addSendTemplate");
		}
		return item;
	}

	/**
	 * 查询分页信息
	 * 
	 * @param type
	 * @param ctype
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getSendTemplateList")
	public @ResponseBody ReusltItem getSendTemplateList(String type, String ctype, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) < 0 || StringUtilsEX.ToInt(size) < 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误,page" + page + ",size" + size);
				return item;
			}
			CriteriaSendTemplate criteria = new CriteriaSendTemplate();
			if (StringUtils.isNotNull(type)) {
				criteria.setType(StringUtilsEX.ToInt(type));
			}
			if (StringUtils.isNotNull(ctype)) {
				criteria.setCtype(StringUtilsEX.ToInt(ctype));
			}
			PageBean pageBean = freightService.querySendTemplateList(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setPageIndex(pageBean.getPc());
			item.setMaxRow(pageBean.getTr());
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询发送模板错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"查询发送模板异常! 异常信息:{0}", e,
					"/platform/freightmanager/getSendTemplateList");
		}
		return item;
	}

	/**
	 * 删除发送模板
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletSendTemplate")
	public @ResponseBody ReusltItem deletSendTemplate(String id,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			Integer userid = SessionState.GetCurrentUser().getUserId();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数发送模板错误,id" + id);
				return item;
			}
			int result = freightService.deletSendTemplate(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "sendTemplate.jsp", "/platform/freightmanager/deletSendTemplate", "删除发送模板");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"删除发送模板操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/deletSendTemplate");
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
        		item.setDesc("查询发送模板错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"删除发送模板异常! 异常信息:{0}", e,
					"/platfrorm/freightmanager/deletSendTemplate");
		}
		return item;
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	public @ResponseBody ReusltItem deletTemplateList(String ids,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			Integer userid = SessionState.GetCurrentUser().getUserId();
			if (StringUtils.isBlanck(ids)) {
				item.setCode(-101);
				item.setDesc("参数发送模板错误,ids" + ids);
				return item;
			}
			String[] array = ids.split(",");
			int result = freightService.deletSendTemplateList(array);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "sendTemplate.jsp", "/platform/freightmanager/deletTemplateList", "删除批量发送模板");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"删除批量发送模板操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/deletTemplateList");
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
        		item.setDesc("删除发送模板错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"删除发送模板异常! 异常信息:{0}", e,
					"/platform/freightmanager/deletTemplateList");
		}
		return item;
	}

	/**
	 * 根据id查询发送模板
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/querySendTemplateById")
	public @ResponseBody ReusltItem querySendTemplateById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数发送模板错误,id" + id);
				return item;
			}
			Sendtemplate sendtemplate = freightService.querySendTemplate(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(sendtemplate);
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询发送模板异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"查询发送模板异常! 异常信息:{0}", e,
					"/platform/freightmanager/querySendTemplateById");
		}
		return item;
	}

	/**
	 * 修改发送模板
	 * 
	 * @param id
	 * @param content
	 * @param ctype
	 * @param sort
	 * @param type
	 * @return
	 */
	@RequestMapping("/updateSendTemplate")
	public @ResponseBody ReusltItem updateSendTemplate(String id, String content, String ctype, String tag, String sort,
			String type,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数发送模板错误,id" + id);
				return item;
			}
			if (StringUtils.isBlanck(content)) {
				item.setCode(-102);
				item.setDesc("请填写内容");
				return item;
			}
			if (StringUtilsEX.ToInt(ctype) < 0) {
				item.setCode(-103);
				item.setDesc("请选择内容类型");
				return item;
			}
			if (StringUtilsEX.ToInt(sort) < 0) {
				item.setCode(-104);
				item.setDesc("请填写排序");
				return item;
			}
			// if (StringUtils.isInteger(sort)) {
			// item.setCode(-106);
			// item.setDesc("排序必须是数字");
			// return item;
			// }
			if (StringUtilsEX.ToInt(type) < 0) {
				item.setCode(-105);
				item.setDesc("请选择类型");
				return item;
			}
			if (StringUtilsEX.ToInt(tag) < 0) {
				item.setCode(-106);
				item.setDesc("请选择模板标识");
				return item;
			}
			int result = freightService.updateSendTemplate(StringUtilsEX.ToInt(id), content, StringUtilsEX.ToInt(ctype),
					StringUtilsEX.ToInt(sort), StringUtilsEX.ToInt(type), StringUtilsEX.ToInt(tag));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "sendTemplateEdit.jsp", "/platform/freightmanager/updateSendTemplate", "修改发送模板");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改发送模板操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/updateSendTemplate");
        					}
        					
        				}
        			});
			} else {
				item.setCode(-200);
				item.setDesc("修改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("修改发送模板异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"修改发送模板异常! 异常信息:{0}", e,
					"/platform/freightmanager/updateSendTemplate");
		}
		return item;
	}

	/**
	 * 设置默认
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/setDefaultSendTemplate")
	public @ResponseBody ReusltItem setDefaultSendTemplate(String id,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("参数id错误,id" + id);
				return item;
			}
			int result = freightService.setDefaultSendTemplate(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("设置成功");
				user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "sendTemplate.jsp", "/platform/freightmanager/setDefaultSendTemplate", "设置默认发送模板");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"设置默认发送模板操作记录出错! 异常信息:",
        								e, "/platform/freightmanager/setDefaultSendTemplate");
        					}
        					
        				}
        			});
			} else {
				item.setCode(-200);
				item.setDesc("设置失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("设置默认发送模板异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"设置默认发送模板异常! 异常信息:{0}", e,
					"/platform/freightmanager/setDefaultSendTemplate");
		}
		return item;
	}
	
	@RequestMapping("/queryFreightAttrList")
	public @ResponseBody ReusltItem queryFreightAttrList(String ftid,String page, String size, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(ftid) < 0) {
				item.setCode(-101);
				item.setDesc("参数id错误,id" + ftid);
				return item;
			
			}
			CriteriaFreightAttr criteria = new CriteriaFreightAttr();
			
			criteria.setFreightid(StringUtilsEX.ToInt(ftid));
			PageBean pageBean = freightAttrService.queryAttrByFrightId(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setPageIndex(pageBean.getPc());
			item.setMaxRow(pageBean.getTr());
			item.setPageSize(pageBean.getPs());

		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询子模板异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Dispatching,"查询子模板异常! 异常信息:{0}", e,
					"/platform/freightmanager/queryFreightAttrList");
		}
		return item;
	}
	
}

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Specstype;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.SpecstypeService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 商品规格类型管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/platform/Specstype")
public class SpecstypeController {
    
    @Autowired
    private SpecstypeService    specstypeService;
    @Autowired
    private CategoryService     categoryService;

    SessionUser user=null;
    @Autowired
    private OperaterecordsService operaterecordsService ;
    /**
     * 根据分类ID获取规格类型
     * 
     * @param classid
     * @return
     */
    @RequestMapping("/getByClassID")
    public @ResponseBody ReusltItem getByClassID(String classid) {
         ReusltItem item = new ReusltItem();
        List<Specstype> data = new ArrayList<Specstype>();
        try {
            Integer cid = StringUtilsEX.ToInt(classid);
            if (cid > 0) {
                data = specstypeService.getByClassID(cid);
                item.setCode(0);
                item.setDesc("查询成功");
            }else{
            	 item.setCode(-101);
                 item.setDesc("分类ID参数错误");
                 return item;
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("根据分类ID获取规格类型异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "根据分类ID获取规格类型异常信息:", e,"/platform/Specstype/getByClassID");
        }
        item.setData(data);
        return item;
    }
	/**
	 * 根据分类ID获取规格类型(分页)
	 * @param classid
	 * @return
	 */
	@RequestMapping("/getPageByClassID")
	public @ResponseBody ReusltItem getPageByClassID(String classid, String index,
			String size) {
		ReusltItem item = new ReusltItem();
		try {
			Integer cid = StringUtilsEX.ToInt(classid);
			
			if (cid<= 0) {
				item.setCode(-101);
				item.setDesc("商品分类ID参数错误，classid：" + classid);
				return item;
			}
			if (StringUtilsEX.ToInt(index) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex：" + index);
				return item;
			}
			if (StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-103);
				item.setDesc("分页参数错误，pagesize：" + size);
				return item;
			}
			
			PageBean pageBean = specstypeService.getList(cid,
					StringUtilsEX.ToInt(index), StringUtilsEX.ToInt(size));
			
			item.setCode(0);
			item.setDesc("查询成功");
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("根据分类ID获取规格类型异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Product, "根据分类ID获取规格类型异常信息:" , e,"/platform/Specstype/getPageByClassID");
		}
		return item;
	}
	
	
	/**
	 * 根据分类ID获取规格类型(分页)
	 * @param classid
	 * @return
	 */
	@RequestMapping("/getPageByClassSonID")
	public @ResponseBody ReusltItem getPageByClassSonID(String classid, String index,
			String size) {
		ReusltItem item = new ReusltItem();
		try {
			Integer cid = StringUtilsEX.ToInt(classid);
			
			if (cid<= 0) {
				item.setCode(-101);
				item.setDesc("商品分类ID参数错误，classid：" + classid);
				return item;
			}
			if (StringUtilsEX.ToInt(index) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex：" + index);
				return item;
			}
			if (StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-103);
				item.setDesc("分页参数错误，pagesize：" + size);
				return item;
			}
			
			PageBean pageBean = specstypeService.getList(cid,
					StringUtilsEX.ToInt(index), StringUtilsEX.ToInt(size));
			
			item.setCode(0);
			item.setDesc("查询成功");
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("根据分类ID获取规格类型异常信息：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Product, "根据分类ID获取规格类型异常信息:" , e,"/platform/Specstype/getPageByClassID");
		}
		return item;
	}
	
    /**
     * 获取规格类型列表（分页）
     * 
     * @param classid
     * @param index
     * @param size
     * @return
     */
    @RequestMapping("/getList")
    public @ResponseBody ReusltItem getList(String classid,String classid2,String classid3, String index, String size) {
         ReusltItem item = new ReusltItem();
        try {
            Integer cid = StringUtilsEX.ToInt(classid);
            Integer cid2 = StringUtilsEX.ToInt(classid2);
            Integer cid3 = StringUtilsEX.ToInt(classid3);
            if (StringUtilsEX.ToInt(index) <= 0) {
                item.setCode(-102);
                item.setDesc("分页参数错误，pageindex：" + index);
                return item;
            }
            if (StringUtilsEX.ToInt(size) <= 0) {
                item.setCode(-103);
                item.setDesc("分页参数错误，pagesize：" + size);
                return item;
            }
            PageBean pageBean = specstypeService.getSonList(cid,cid2,cid3, StringUtilsEX.ToInt(index),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setDesc("查询成功");
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取规格类型列表异常信息：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "获取规格类型列表异常信息:" , e,"/platform/Specstype/getList");
        }
        return item;
    }
    /**
     * 添加规格类型
     * 
     * @param stype
     * @return
     */
    @RequestMapping("/add")
    public @ResponseBody ReusltItem add(String name, String classid, String orderby, String status) {
    	ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            Specstype stype = new Specstype();
            stype = checkParam(name, classid, orderby, status, "0",item);
            if (item.getCode() < 0) {
                return item;
            }
            if (specstypeService.insert(stype) > 0) {
            	LogHandle.info(LogType.Role, MessageFormat.format("添加规格类型成功,名称:{0},所属分类ID:{1},状态:{2}", 
            			name, classid,status),"/Specstype/add");
                item.setCode(0);
                item.setDesc("添加规格类型成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specstype_add.jsp", "/platform/Specstype/add", "添加规格类型");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加规格类型操作记录出错! 异常信息:",
    								e, "/platform/Specstype/add");
    					}
    					
    				}
    			});
            } else {
             	LogHandle.info(LogType.Role, MessageFormat.format("添加规格类型失败,名称:{0},所属分类ID:{1},状态:{2}", 
            			name, classid,status),"/Specstype/add");
                item.setCode(-200);
                item.setDesc("添加规格类型失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("添加规格类型异常信息：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "添加规格类型异常信息:",e,"/platform/Specstype/add");
        }
        return item;
    }

    /**
     * 修改规格类型
     * 
     * @param stype
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ReusltItem update(String id, String name, String classid, String orderby,
                                           String status) {
    	ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            Specstype stype = new Specstype();
            stype = checkParam(name, classid, orderby, status, id,item);
            if (item.getCode() < 0) {
                return item;
            }
            if (specstypeService.update(stype) > 0) {
            	LogHandle.info(LogType.Role, MessageFormat.format("修改规格类型成功,ID:{0},名称:{1},所属分类ID:{2}", 
            			id, name,classid),"/Specstype/update");
                item.setCode(0);
                item.setDesc("修改规格类型成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specstype_edit.jsp", "/platform/Specstype/update", "修改规格类型");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改规格类型操作记录出错! 异常信息:",
    								e, "/platform/Specstype/update");
    					}
    					
    				}
    			});
            } else {
            	LogHandle.info(LogType.Role, MessageFormat.format("修改规格类型失败,ID:{0},名称:{1},所属分类ID:{2}", 
            			id, name,classid),"/Specstype/update");
                item.setCode(-200);
                item.setDesc("修改规格类型失败");
            }

        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("修改规格类型异常信息：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "修改规格类型异常信息:" ,e,"/platform/Specstype/update");
        }
        return item;
    }

    // 参数验证
    private Specstype checkParam(String name, String classid, String orderby, String status,
                                String id,ReusltItem item) throws Exception {
        Specstype stype = new Specstype();

        Integer ID = StringUtilsEX.ToInt(id);
        if (ID < 0) {
            item.setCode(-101);
            item.setDesc("规格类型ID参数错误,id:" + id);
            return null;
        }
        if (StringUtilsEX.isBlank(name)) {
            item.setCode(-102);
            item.setDesc("规格类型名称不能为空");
            return null;
        }
        if (StringUtilsEX.ToInt(classid) <= 0) {
            item.setCode(-103);
            item.setDesc("所属分类ID参数错误,cid:" + classid);
            return null;
        }
        if (categoryService.queryById(StringUtilsEX.ToInt(classid)) == null) {
            item.setCode(-201);
            item.setDesc("根据所属分类ID未能检索到分类数据,cid:" + classid);
            return null;
        }

        if (StringUtilsEX.ToInt(status) < 0) {
            item.setCode(-104);
            item.setDesc("规格类型状态参数错误,status:" + status);
            return null;
        }
        if (StringUtilsEX.ToInt(orderby) < 0) {
            item.setCode(-105);
            item.setDesc("规格类型排序参数错误,orderby:" + orderby);
            return null;
        }
        if (ID > 0) {
            stype = specstypeService.getByID(ID);
        }
        stype.setName(name);
        stype.setClassid(StringUtilsEX.ToInt(classid));
        stype.setStatus(StringUtilsEX.ToInt(status));
        stype.setOrderby(StringUtilsEX.ToInt(orderby));
        String fullpath = categoryService.queryById(StringUtilsEX.ToInt(classid)).getFullpath();
        stype.setFullpath(fullpath);
        return stype;
    }

    /**
     * 删除规格类型
     * 
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public @ResponseBody ReusltItem delete(String id) {
         ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            Integer ID = StringUtilsEX.ToInt(id);
            if (ID < 0) {
                item.setCode(-101);
                item.setDesc("规格类型ID参数错误,id:" + id);
                return item;
            }
            if (specstypeService.del(ID) > 0) {
            	LogHandle.info(LogType.Role, MessageFormat.format("删除规格类型成功,ID:{0},操作人ID:{1}", 
            			id,user.getUserId()),"/Specstype/delete");
                item.setCode(0);
                item.setDesc("删除规格类型成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specstype_list.jsp", "/platform/Specstype/delete", "删除规格类型");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除规格类型操作记录出错! 异常信息:",
    								e, "/platform/Specstype/delete");
    					}
    					
    				}
    			});
            } else {
            	LogHandle.info(LogType.Role, MessageFormat.format("删除规格类型失败,ID:{0},操作人ID:{1}", 
            			id,user.getUserId()),"/Specstype/delete");
                item.setCode(-200);
                item.setDesc("删除规格类型失败");
            }

        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("删除规格类型异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "删除规格类型异常信息:" , e,"/platform/Specstype/delete");
        }
        return item;
    }

    /**
     * 批量删除规格类型
     * 
     * @param ids
     * @return
     */
    @RequestMapping("/deleteList")
    public @ResponseBody ReusltItem deleteList(String ids) {
         ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            List<Integer> idlist = new ArrayList<Integer>();
            String[] idStrings = ids.split(",");
            Integer ID = 0;
            for (int i = 0; i < idStrings.length; i++) {
                ID = StringUtilsEX.ToInt(idStrings[i]);
                if (ID > 0)
                    idlist.add(ID);
                else {
                    item.setCode(-102);
                    item.setDesc("规格类型ID参数错误,id:" + idStrings[i]);
                    return item;
                }
            }
            if (specstypeService.delList(idlist) > 0) {
            	LogHandle.info(LogType.Role, MessageFormat.format("批量删除规格类型成功,ID集合:{0},操作人ID:{1}", 
            			ids,user.getUserId()),"/Specstype/deleteList");
                item.setCode(0);
                item.setDesc("删除规格类型成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specstype_list.jsp", "/platform/Specstype/deleteList", "批量删除规格类型");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量删除规格类型操作记录出错! 异常信息:",
    								e, "/platform/Specstype/deleteList");
    					}
    					
    				}
    			});
            } else {
            	LogHandle.info(LogType.Role, MessageFormat.format("批量删除规格类型失败,ID集合:{0},操作人ID:{1}", 
            			ids,user.getUserId()),"/Specstype/deleteList");
                item.setCode(-200);
                item.setDesc("删除规格类型失败");
            }

        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("批量删除规格类型异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "批量删除规格类型异常信息:" , e,"/paltform/Specstype/deleteList");
        }
        return item;
    }

    /**
     * 排序调整
     * 
     * @param orderby
     * @param id
     * @return
     */
    @RequestMapping("/updateOrder")
    public @ResponseBody ReusltItem updateOrder(String orderby, String id) {
         ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(id) <= 0) {
                item.setCode(-101);
                item.setDesc("规格类型ID参数错误,id:" + id);
                return item;
            }
            if (StringUtilsEX.ToInt(orderby) < 0) {
                item.setCode(-102);
                item.setDesc("规格类型排序参数错误,orderby:" + orderby);
                return item;
            }
            if (specstypeService.updateOrder(StringUtilsEX.ToInt(orderby), StringUtilsEX.ToInt(id)) > 0) {
            	LogHandle.info(LogType.Role, MessageFormat.format("修改规格类型排序成功,ID:{0},排序:{1},操作人ID:{2}", 
            			id,orderby,user.getUserId()),"/Specstype/updateOrder");
                item.setCode(0);
                item.setDesc("修改规格类型排序成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specstype_list.jsp", "/platform/Specstype/updateOrder", "修改规格类型排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改规格类型排序操作记录出错! 异常信息:",
    								e, "/platform/Specstype/updateOrder");
    					}
    					
    				}
    			});
            } else {
            	LogHandle.info(LogType.Role, MessageFormat.format("修改规格类型排序失败,ID:{0},排序:{1},操作人ID:{2}", 
            			id,orderby,user.getUserId()),"/Specstype/updateOrder");
                item.setCode(-200);
                item.setDesc("修改规格类型排序失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("修改规格类型排序异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "修改规格类型排序异常信息:" , e,"/platform/Specstype/updateOrder");
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
    @RequestMapping("/updateOrderList")
    public @ResponseBody ReusltItem updateOrderList(String orderbys, String ids) {
         ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            List<Integer> orderlist = new ArrayList<Integer>();
            List<Integer> idlist = new ArrayList<Integer>();
            if (ids == null) {
                item.setCode(-101);
                item.setDesc("规格类型ID参数错误,id:" + ids);
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
                    item.setDesc("规格类型ID参数错误,id:" + idStrings[i]);
                    return item;
                }
            }
            if (orderbys == null) {
                item.setCode(-102);
                item.setDesc("规格类型排序参数错误,orderby:" + orderbys);
                return item;
            }
            String[] orderStrings = orderbys.split(",");
            for (int i = 0; i < orderStrings.length; i++) {
                ob = StringUtilsEX.ToInt(orderStrings[i]);
                if (ob >= 0)
                    orderlist.add(ob);
                else {
                    item.setCode(-102);
                    item.setDesc("规格类型排序参数错误,orderby:" + orderStrings[i]);
                    return item;
                }
            }

            if (specstypeService.updateOrderList(orderlist, idlist) > 0) {
            	LogHandle.info(LogType.Role, MessageFormat.format("批量修改规格类型排序成功,ID集合:{0},排序集合:{1},操作人ID:{2}", 
            			ids,orderbys,user.getUserId()),"/Specstype/updateOrderList");
                item.setCode(0);
                item.setDesc("批量修改规格类型排序成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specstype_list.jsp", "/platform/Specstype/updateOrderList", "批量修改规格类型排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量修改规格类型排序操作记录出错! 异常信息:",
    								e, "/platform/Specstype/updateOrderList");
    					}
    					
    				}
    			});
            } else {
            	LogHandle.info(LogType.Role, MessageFormat.format("批量修改规格类型排序失败,ID集合:{0},排序集合:{1},操作人ID:{2}", 
            			ids,orderbys,user.getUserId()),"/Specstype/updateOrderList");
                item.setCode(-200);
                item.setDesc("批量修改规格类型排序失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("批量修改规格类型排序异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "批量修改规格类型排序异常信息:" ,e,"/platform/Specstype/updateOrderList");
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
    public @ResponseBody ReusltItem updateStatus(String status, String id) {
         ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            Integer ID = StringUtilsEX.ToInt(id);
            if (id == null || ID < 0) {
                item.setCode(-101);
                item.setDesc("规格类型ID参数错误,id:" + id);
                return item;
            }
            Integer ss = StringUtilsEX.ToInt(status);
            if (status == null || ss < 0) {
                item.setCode(-102);
                item.setDesc("规格类型排序参数错误,orderby:" + status);
                return item;
            }
            if (specstypeService.updateStatus(ss, ID) > 0) {
            	LogHandle.info(LogType.Role, MessageFormat.format("修改规格类型状态成功,ID:{0},状态:{1},操作人ID:{2}", 
            			id,status,user.getUserId()),"/Specstype/updateStatus");
                item.setCode(0);
                item.setDesc("修改规格类型状态成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specstype_list.jsp", "/platform/Specstype/updateStatus", "修改规格类型状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改规格类型状态操作记录出错! 异常信息:",
    								e, "/platform/Specstype/updateStatus");
    					}
    					
    				}
    			});
            } else {
            	LogHandle.info(LogType.Role, MessageFormat.format("修改规格类型状态成功,ID:{0},状态:{1},操作人ID:{2}", 
            			id,status,user.getUserId()),"/Specstype/updateStatus");
                item.setCode(-200);
                item.setDesc("修改规格类型状态失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("修改规格类型状态异常信息：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "修改规格类型状态异常信息:" ,e,"/platform/Specstype/updateStatus");
        }
        return item;
    }
    /**
     * 根据分类ID获取规格类型
     * 
     * @param classid
     * @return
     */
    @RequestMapping("/getTypeByClassID")
    public @ResponseBody ReusltItem getTypeByClassID(String classid,String classid2,String classid3) {
         ReusltItem item = new ReusltItem();
        List<Specstype> list = new ArrayList<Specstype>();
        try {
            Integer cid = StringUtilsEX.ToInt(classid);
            Integer cid2 = StringUtilsEX.ToInt(classid2);
            Integer cid3 = StringUtilsEX.ToInt(classid3);
            if (cid > 0) {
            	Criteria criteria = new Criteria();
            	criteria.setClassid2(cid2);
            	criteria.setClassid3(cid3);
            	criteria.setClassid(cid);
            	List<Specstype> data = specstypeService.getBySuperiorClassID(criteria);
                if(data.size()>0){
                	list=data.stream().filter(x->x.getStatus()==0).collect(Collectors.toList());
                }
                item.setData(list);
                item.setCode(0);
                item.setDesc("查询成功");
            }else{
            	 item.setCode(-101);
                 item.setDesc("分类ID参数错误");
                 return item;
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("根据分类ID获取规格类型异常信息：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
        	LogHandle.error(LogType.Product, "根据分类ID获取规格类型异常信息:",e,"/platform/Specstype/getByClassID");
        }
       
        return item;
    }
}

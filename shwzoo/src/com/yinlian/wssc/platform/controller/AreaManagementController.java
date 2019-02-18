package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 地区管理的控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/platform/areamanagement")
public class AreaManagementController {

    /**
     * 输出的控制类
     */

    @Autowired
    private AreaService         areaService;

    @Autowired
    private CityServcie         cityServcie;

    @Autowired
    private ProvinceServcice    provinceServcice;
    
    @Autowired
    private OperaterecordsService operaterecordsService ;

    /**
     * 查询所有的地区
     * @return
     */
    @RequestMapping("/queryAreaList")
    public @ResponseBody ReusltItem queryAreaList() {

        ReusltItem item = new ReusltItem();

        try {

            List<Province> list = provinceServcice.selectList();

            item.setCode(0);
            item.setData(list);
            item.setDesc("获取所有地区列表");
        } catch (Exception e) {
        	item.setCode(-900);
        	 if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("获取查询所有的地区的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Area,"获取查询所有的地区的信息出错! 异常信息:",
					e, "/platform/areamanagement/queryAreaList");

        }

        return item;
    }

    /**
     * 根据tag,id删除地区， tag:1为区，2为市，3为省
     * @param tag
     * @param code
     * @return
     */
    @RequestMapping("/deleteAreaByCode")
    public @ResponseBody ReusltItem deleteAreaByCode(String tag, String code) {
        ReusltItem item = new ReusltItem();
        try {
        	SessionUser user=SessionState.GetCurrentUser();
            switch (StringUtilsEX.ToInt(tag)) {
                case 1:
                    int area = areaService.deleteAreaByCode(code);
                    if (area > 0) {
                        item.setCode(0);
                        item.setDesc("删除成功");
                        LogHandle.info(LogType.Area, MessageFormat.format("删除地区成功! code:{0},操作人ID:{1}", 
                        		code, user.getUserId()),"/platform/areamanagement/deleteAreaByCode");
                        //异步操作 不影响正常流程
                        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            			cachedThreadPool.execute(new Runnable() {
            				@Override
            				public void run() {
            					try{
            						operaterecordsService.insertOperaterecords(
                                    		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                    		user.getUserId(), user.getLoginName(), "AreaManagement_list.jsp", "/platform/areamanagement/deleteAreaByCode", "删除地区");
            					}
            					catch(Exception e){
            						LogHandle.error(LogType.OperateRecords,"删除地区操作记录出错! 异常信息:",
            								e, "/platform/areamanagement/deleteAreaByCode");
            					}
            					
            				}
            			});
                    } else {
                        item.setCode(-200);
                        item.setDesc("删除失败");
                    }

                    break;
                case 2:
                    int city = cityServcie.deleteCityByCode(code);
                    if (city > 0) {
                        item.setCode(0);
                        item.setDesc("删除成功");
                        LogHandle.info(LogType.Area, MessageFormat.format("删除城市成功! code:{0},操作人ID:{1}",
                        		code, user.getUserId()),"/platform/areamanagement/deleteAreaByCode");
                        //异步操作 不影响正常流程
                        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            			cachedThreadPool.execute(new Runnable() {
            				@Override
            				public void run() {
            					try{
            						operaterecordsService.insertOperaterecords(
                                    		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                    		user.getUserId(), user.getLoginName(), "AreaManagement_list.jsp", "/platform/areamanagement/deleteAreaByCode", "删除城市");
            					}
            					catch(Exception e){
            						LogHandle.error(LogType.OperateRecords,"删除城市操作记录出错! 异常信息:",
            								e, "/platform/areamanagement/deleteAreaByCode");
            					}
            					
            				}
            			});
                    } else {
                        item.setCode(-200);
                        item.setDesc("删除失败");
                    }
                    break;
                case 3:
                    int province = provinceServcice.deleteProvinceByCode(code);
                    if (province > 0) {
                        item.setCode(0);
                        item.setDesc("删除成功");
                        LogHandle.info(LogType.Area, MessageFormat.format("删除省份成功! code:{0},操作人ID:{1}",
                        		code, user.getUserId()),"/platform/areamanagement/deleteAreaByCode");
                        //异步操作 不影响正常流程
                        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            			cachedThreadPool.execute(new Runnable() {
            				@Override
            				public void run() {
            					try{
            						operaterecordsService.insertOperaterecords(
                                    		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                    		user.getUserId(), user.getLoginName(), "AreaManagement_list.jsp", "/platform/areamanagement/deleteAreaByCode", "删除省份");
            					}
            					catch(Exception e){
            						LogHandle.error(LogType.OperateRecords,"删除省份操作记录出错! 异常信息:",
            								e, "/platform/areamanagement/deleteAreaByCode");
            					}
            					
            				}
            			});
                    } else {
                        item.setCode(-200);
                        item.setDesc("删除失败");
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	 if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("删除地区的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Area, "删除地区的信息出错! 异常信息:",
					e, "/platform/areamanagement/deleteAreaByCode");
        }
        return item;
    }

    /**
     * 根据code修改地区 ，tag:1为区，2为市，3为省
     * @param tag
     * @param name
     * @param code
     * @return
     */
    @RequestMapping("/updateByCode")
    public @ResponseBody ReusltItem updateByCode(String tag, String name, String code) {

        ReusltItem item = new ReusltItem();
        try {
        	SessionUser user=SessionState.GetCurrentUser();
        	if (StringUtils.isBlanck(name)) {
				item.setCode(-101);
				item.setDesc("请填写地名");
				return item;
			}
            switch (StringUtilsEX.ToInt(tag)) {
                case 1:
                    int area = areaService.updateAreaByCode(name, code);
                    if (area > 0) {
                        item.setCode(0);
                        item.setDesc("更改成功");
                        LogHandle.info(LogType.Area, MessageFormat.format("更改地区信息成功! "
                        		+ "code:{0},name:{1},操作人ID:{2}", 
                        		code,name, user.getUserId()),"/platform/areamanagement/updateByCode");
                        //异步操作 不影响正常流程
                        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            			cachedThreadPool.execute(new Runnable() {
            				@Override
            				public void run() {
            					try{
            						operaterecordsService.insertOperaterecords(
                                    		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                    		user.getUserId(), user.getLoginName(), "AreaManagement_list.jsp", "/platform/areamanagement/updateByCode", "更改地区信息");
            					}
            					catch(Exception e){
            						LogHandle.error(LogType.OperateRecords,"更改地区信息操作记录出错! 异常信息:",
            								e, "/platform/areamanagement/updateByCode");
            					}
            					
            				}
            			});
                    } else {
                        item.setCode(-200);
                        item.setDesc("更改失败");
                    }

                    break;
                case 2:
                    int city = cityServcie.updateCityByCode(name, code);
                    if (city > 0) {
                        item.setCode(0);
                        item.setDesc("更改成功");
                        LogHandle.info(LogType.Area, MessageFormat.format("更改城市信息成功! "
                        	    + "code:{0},name:{1},操作人ID:{2}", 
                        		code,name,  user.getUserId()),"/platform/areamanagement/updateByCode");
                        //异步操作 不影响正常流程
                        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            			cachedThreadPool.execute(new Runnable() {
            				@Override
            				public void run() {
            					try{
            						operaterecordsService.insertOperaterecords(
                                    		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                    		user.getUserId(), user.getLoginName(), "AreaManagement_list.jsp", "/platform/areamanagement/updateByCode", "更改城市信息");
            					}
            					catch(Exception e){
            						LogHandle.error(LogType.OperateRecords,"更改城市信息操作记录出错! 异常信息:",
            								e, "/platform/areamanagement/updateByCode");
            					}
            					
            				}
            			});
                    } else {
                        item.setCode(-200);
                        item.setDesc("更改失败");
                    }
                    break;
                case 3:
                    int province = provinceServcice.updateProvinceByCode(name, code);
                    if (province > 0) {
                        item.setCode(0);
                        item.setDesc("更改成功");
                        LogHandle.info(LogType.Area, MessageFormat.format("更改省份信息成功! "
                        		+ "code:{0},name:{1},操作人ID:{2}", 
                        		code,name, user.getUserId()),"/platform/areamanagement/updateByCode");
                        //异步操作 不影响正常流程
                        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            			cachedThreadPool.execute(new Runnable() {
            				@Override
            				public void run() {
            					try{
            						operaterecordsService.insertOperaterecords(
                                    		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                    		user.getUserId(), user.getLoginName(), "AreaManagement_list.jsp", "/platform/areamanagement/updateByCode", "更改省份信息");
            					}
            					catch(Exception e){
            						LogHandle.error(LogType.OperateRecords,"更改省份信息操作记录出错! 异常信息:",
            								e, "/platform/areamanagement/updateByCode");
            					}
            					
            				}
            			});
                    } else {
                        item.setCode(-200);
                        item.setDesc("更改失败");
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("修改地区信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			
			LogHandle.error(LogType.Area, "获取地区信息出错! 异常信息:",
					e, "/platform/areamanagement/updateByCode");
        }

        return item;

    }

    /**
     * 根据code查询区域信息
     * 
     * @param type
     * @param code
     * @return
     */
    @RequestMapping("/queryArearByCode")
    public @ResponseBody ReusltItem queryArearByCode(String type, String code) {
        ReusltItem item = new ReusltItem();
        try {
            switch (type) {
                case "3": //查询省
                    Province province = provinceServcice.queryByCode(code);
                    item.setCode(0);
                    item.setData(province);
                    break;

                case "2": //查询市
                    City city = cityServcie.queryByCode(code);
                    item.setCode(0);
                    item.setData(city);
                    break;
                case "1": //查询区
                    Area area = areaService.queryByCode(code);
                    item.setCode(0);
                    item.setData(area);
                    break;
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("根据code查询区域信息的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			
			LogHandle.error(LogType.Area, "根据code查询区域信息的信息出错! 异常信息:",
					e, "/platform/areamanagement/queryArearByCode");
        }
        return item;
    }
    /**
     * 添加地区 ，tag:2为区，3为市，0为省
     * 
     * @param tag
     * @param name
     * @param code
     * @param fcode
     * @return
     */
    @RequestMapping("/add")
    public @ResponseBody ReusltItem add(String tag, String name, String code, String fcode ){
    	ReusltItem item = new ReusltItem();
    	try {
    		SessionUser user=SessionState.GetCurrentUser();
    		if (tag == null) {
    			List<Province> list = provinceServcice.query();
    			for (Province province : list) {
					String ishave = province.getCode();
					if (code.equals(ishave)) {
						item.setCode(-110);
						item.setDesc("编号错误");
						return item;
					}
				}
    			if (code == null || "".equals(code)) {
					item.setCode(-111);
					item.setDesc("编码不能为空");
					return item;
				}
    			if (!StringUtils.isDigit(code)) {
    				item.setCode(-112);
					item.setDesc("编码请输入数字");
					return item;
				}
    			 int province = provinceServcice.add(name, code);
 			    if (province > 0) {
 			        item.setCode(0);
 			        item.setDesc("添加成功");
 			       LogHandle.info(LogType.Area, MessageFormat.format("添加省份信息成功! "
                   		+ "code:{0},name:{1},操作人ID:{2}", 
                   		code,name, user.getUserId()),"/platform/areamanagement/add");
 			       //异步操作 不影响正常流程
 			       ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
       				cachedThreadPool.execute(new Runnable() {
       				@Override
       				public void run() {
       					try{
       						operaterecordsService.insertOperaterecords(
                               		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                               		user.getUserId(), user.getLoginName(), "AreaManagement_list.jsp", "/platform/areamanagement/add", "添加省份信息");
       					}
       					catch(Exception e){
       						LogHandle.error(LogType.OperateRecords,"添加省份信息操作记录出错! 异常信息:",
       								e, "/platform/areamanagement/add");
       					}
       					
       				}
       			});
 			    } else {
 			        item.setCode(-200);
 			        item.setDesc("添加失败");
 			    }
			}else {
				switch (tag) {
					case "2":
						List<Area> list = areaService.query();
		    			for (Area area : list) {
							String ishave = area.getCode();
							if (code.equals(ishave)) {
								item.setCode(-110);
								item.setDesc("编号错误");
								return item;
							}
						}
		    			if (code == null || "".equals(code)) {
							item.setCode(-111);
							item.setDesc("编码不能为空");
							return item;
						}
		    			if (!StringUtils.isDigit(code)) {
		    				item.setCode(-112);
							item.setDesc("编码请输入数字");
							return item;
						}
					    int area = areaService.add(name, code,fcode);
					    if (area > 0) {
					        item.setCode(0);
					        item.setDesc("添加成功");
					        LogHandle.info(LogType.Area, MessageFormat.format("添加地区信息成功! "
			                   		+ "code:{0},name:{1},操作人ID:{2}", 
			                   		code,name, user.getUserId()),"/platform/areamanagement/add");
					        //异步操作 不影响正常流程
			 			       ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			       				cachedThreadPool.execute(new Runnable() {
			       				@Override
			       				public void run() {
			       					try{
			       						operaterecordsService.insertOperaterecords(
			                               		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
			                               		user.getUserId(), user.getLoginName(), "AreaManagement_list.jsp", "/platform/areamanagement/add", "添加地区信息");
			       					}
			       					catch(Exception e){
			       						LogHandle.error(LogType.OperateRecords,"添加地区信息操作记录出错! 异常信息:",
			       								e, "/platform/areamanagement/add");
			       					}
			       					
			       				}
			       			});
					    } else {
					        item.setCode(-200);
					        item.setDesc("添加失败");
					    }
		
					    break;
					case "3":
						List<City> list1 = cityServcie.query();
		    			for (City city : list1) {
							String ishave = city.getCode();
							if (code.equals(ishave)) {
								item.setCode(-110);
								item.setDesc("编号错误");
								return item;
							}
						}
		    			if (code == null || "".equals(code)) {
							item.setCode(-111);
							item.setDesc("编码不能为空");
							return item;
						}
		    			if (!StringUtils.isDigit(code)) {
		    				item.setCode(-112);
							item.setDesc("编码请输入数字");
							return item;
						}
					    int city = cityServcie.add(name, code,fcode);
					    if (city > 0) {
					        item.setCode(0);
					        item.setDesc("添加成功");
					        LogHandle.info(LogType.Area, MessageFormat.format("添加城市信息成功! "
			                   		+ "code:{0},name:{1},操作人ID:{2}", 
			                   		code,name, user.getUserId()),"/platform/areamanagement/add");
					        //异步操作 不影响正常流程
			 			       ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			       				cachedThreadPool.execute(new Runnable() {
			       				@Override
			       				public void run() {
			       					try{
			       						operaterecordsService.insertOperaterecords(
			                               		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
			                               		user.getUserId(), user.getLoginName(), "AreaManagement_list.jsp", "/platform/areamanagement/add", "添加城市信息");
			       					}
			       					catch(Exception e){
			       						LogHandle.error(LogType.OperateRecords,"添加城市信息操作记录出错! 异常信息:",
			       								e, "/platform/areamanagement/add");
			       					}
			       					
			       				}
			       			});
					    } else {
					        item.setCode(-200);
					        item.setDesc("添加失败");
					    }
					    break;
					default:
					    break;
					}
   }
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("添加地区信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Area, "添加地区信息出错! 异常信息:",
					e, "/platform/areamanagement/add");
		}
    	return item;
    }
}

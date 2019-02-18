package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Advertimgdictionary;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaAdvert;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 广告的
 * AdvertimgController.java
 * @author sssssssl.m
 * @version $Id: AdvertimgController.java, v 0.1 2016年4月13日 下午5:03:19 Administrator Exp $
 */
@Controller
@RequestMapping("/platform/advertimg")
public class AdvertimgController {

    @Autowired
    AdvertImgService            advertImgService;

    SessionUser                 user   = null;
    @Autowired
    private OperaterecordsService operaterecordsService ;
    /**
     * 广告添加
     * @return
     */
    @RequestMapping("/edit")
    public @ResponseBody ReusltItem edit(String imgurl, String url, String status, String type,
                                         String posi, String id,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            /* if (StringUtilsEX.ToInt(type) <= 0) {
                 item.setCode(-101);
                 item.setDesc("请选择类型");
                 return item;
             }
             if (StringUtilsEX.ToInt(posi) <= 0) {
                 item.setCode(-102);
                 item.setDesc("请选择显示位置");
                 return item;
             }
             if (StringUtilsEX.ToInt(status) < 0) {
                 item.setCode(-104);
                 item.setDesc("请选择状态");
                 return item;
             }*/
            if (StringUtils.isBlanck(imgurl)) {
                item.setCode(-105);
                item.setDesc("请上传图片");
                return item;
            }
            /* if (StringUtils.isBlanck(url)) {
                 item.setCode(-106);
                 item.setDesc("请填写链接地址");
                 return item;
             }*/
            AdvertImg advert = new AdvertImg();
            advert.setImgurl(imgurl);
            advert.setUrl(url);
            advert.setType(StringUtilsEX.ToInt(type));
            advert.setIsdel(false);
            advert.setSort(0);
            advert.setStatus(StringUtilsEX.ToInt(status));
            advert.setCreattime(new Date());
            advert.setPosition(StringUtilsEX.ToInt(posi));
            if (StringUtils.isBlanck(id)) { //添加
                int result = advertImgService.insert(advert);
                if (result > 0) {
                    item.setCode(0);
                    item.setDesc("添加成功");
                    LogHandle.info(LogType.advertimg, MessageFormat.format("添加广告成功! ID:{0},状态:{1}", id, imgurl),"/platform/advertimg/edit");
                    user=SessionUtil.getSessionUser(request);
                    //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "advertImg_add.jsp", "/platform/advertimg/edit", "添加广告图片");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加保存广告信息操作记录出错! 异常信息:",
        								e, "/platform/advertimg/edit");
        					}
        					
        				}
        			});
                    
                } else {
                    item.setCode(-200);
                    item.setDesc("添加失败");
                    LogHandle.info(LogType.advertimg, MessageFormat.format("添加广告失败! ID:{0},状态:{1}", id, status),"/platform/advertimg/edit");
                }
            } else {
                if (StringUtilsEX.ToInt(id) < 0) {
                    item.setCode(-101);
                    item.setDesc("参数id错误");
                    return item;
                }
                advert.setId(StringUtilsEX.ToInt(id));
                int result = advertImgService.updateByPrimaryKey(advert);
                if (result > 0) {
                    item.setCode(0);
                    item.setDesc("修改成功");
                    LogHandle.info(LogType.advertimg, MessageFormat.format("修改广告成功! ID:{0},状态:{1}", id, status),"/platform/advertimg/edit");
                    user=SessionUtil.getSessionUser(request);
                    //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "advertImg_edit.jsp", "/platform/advertimg/edit", "修改广告图片");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改广告信息操作记录出错! 异常信息:",
        								e, "/platform/advertimg/edit");
        					}
        					
        				}
        			});
                } else {
                    item.setCode(-200);
                    item.setDesc("修改失败");
                    LogHandle.info(LogType.advertimg, MessageFormat.format("修改广告失败! ID:{0},状态:{1}", id, status),"/platform/advertimg/edit");
                }
            }

        } catch (Exception e) {
            item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("保存广告信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
        	LogHandle.error(LogType.advertimg,"保存广告信息信息出错! 异常信息:",
					e, "/platform/advertimg/edit");
        }

        return item;
    }

    /**
     * 编辑查看
     */
    @RequestMapping("/editAdvertImg")
    public @ResponseBody ReusltItem editAdvertImg(String imgurl, String sort, String status,
                                                  String oldid, String annouid,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        int result = 0;
        try {
        	user=SessionUtil.getSessionUser(request);
            AdvertImg advertImg = advertImgService.selectByPrimaryKey(StringUtilsEX.ToInt(oldid));
            AdvertImg advert = new AdvertImg();
            advert.setImgurl(imgurl);
            advert.setSort(StringUtilsEX.ToInt(sort));
            advert.setStatus(StringUtilsEX.ToInt(status));
            advert.setAnnouid(StringUtilsEX.ToInt(annouid));
            BeanUtils.copyProperties(advert, advertImg);
            result = advertImgService.updateByPrimaryKey(advertImg);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("编辑成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "advertImg_edit.jsp", "/platform/advertimg/editAdvertImg", "修改广告图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"编辑广告信息操作记录出错! 异常信息:",
    								e, "/platform/advertimg/editAdvertImg");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("编辑失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("编辑广告出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.advertimg,
                "编辑广告出错! 异常信息:", e, "/platform/advertimg/editAdvertImg");
 
        }

        return item;
    }

    /**
     * 分页广告图片
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryAdvert")
    public @ResponseBody ReusltItem queryAdvert(String page, String size, String type,
                                                String status, String position) {
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
        /* if (StringUtilsEX.ToInt(type) <= 0) {
             item.setCode(-103);
             item.setDesc("参数type错误，type：" + type);
             return item;
         }
         if (!StringUtilsEX.IsNullOrWhiteSpace(position)&&StringUtilsEX.ToInt(position)<0) {
             item.setCode(-105);
             item.setDesc("参数position错误，position：" + position);
             return item;
         }
         if (StringUtilsEX.ToInt(status) < 0) {
             item.setCode(-105);
             item.setDesc("参数status错误，status：" + status);
             return item;
         }*/
        int position1 = StringUtilsEX.ToInt(position);
        if (position1 < 0) {
            position1 = 0;
        }
        try {
            CriteriaAdvert criteria = new CriteriaAdvert();
            criteria.setPosition(position1);
            criteria.setStatus(StringUtilsEX.ToInt(status));
            criteria.setType(StringUtilsEX.ToInt(type));
            criteria.setOrderByClause("sort");
            PageBean pageBean = advertImgService.getListByPage(criteria, StringUtilsEX.ToInt(page),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询分页广告图片出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.advertimg,
                "查询分页广告图片出错! 异常信息:", e, "/platform/advertimg/queryAdvert");
 
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
    public @ResponseBody ReusltItem updateStatus(String status, String id,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
            Integer ID = StringUtilsEX.ToInt(id);
            if (id == null || ID < 0) {
                item.setCode(-101);
                item.setDesc("广告ID参数错误,id:" + id);
                return item;
            }
            Integer ss = StringUtilsEX.ToInt(status);
            if (status == null || ss < 0) {
                item.setCode(-102);
                item.setDesc("广告状态参数错误,orderby:" + status);
                return item;
            }
            if (advertImgService.updateStatus(ss, ID) > 0) {
            	LogHandle.info(LogType.advertimg, MessageFormat.format("修改广告状态成功! ID:{0},状态:{1}", id, status));
                item.setCode(0);
                item.setDesc("修改广告状态成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "advertImg_edit.jsp", "/platform/advertimg/updateStatus", "修改广告状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改广告状态信息操作记录出错! 异常信息:",
    								e, "/platform/advertimg/updateStatus");
    					}
    					
    				}
    			});
            } else {
            	LogHandle.info(LogType.advertimg, MessageFormat.format("修改广告状态失败! ID:{0},状态:{1}", id, status));
                item.setCode(-200);
                item.setDesc("修改广告状态失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("修改广告状态出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.advertimg,
               "修改广告状态出错! 异常信息:", e, "/platform/advertimg/updateStatus");
 
        }
        return item;
    }

    /**
     * 根据id删除图片
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteAdvertById")
    public @ResponseBody ReusltItem deleteAdvertById(String id,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("参数id错误");
            return item;
        }
        try {
        	user=SessionUtil.getSessionUser(request);
            int result = advertImgService.deleteAdvert(StringUtilsEX.ToInt(id));
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
                            		user.getUserId(), user.getLoginName(), "advertImg_edit.jsp", "/platform/advertimg/deleteAdvertById", "根据id删除图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除图片操作记录出错! 异常信息:",
    								e, "/platform/advertimg/deleteAdvertById");
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
            	item.setDesc("根据id删除图片出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.advertimg,
                "根据id删除图片出错! 异常信息:", e, "/platform/advertimg/deleteAdvertById");
 
        }
        return item;
    }

    /**
     * 查询广告字典列表
     * 
     * @return
     */
    @RequestMapping("/getList")
    public @ResponseBody ReusltItem getList() {
        ReusltItem item = new ReusltItem();
        try {
            List<Advertimgdictionary> list = advertImgService.queryList();
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询广告字典列表出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.advertimg,
                "查询广告字典列表出错! 异常信息:", e, "/platform/advertimg/getList");
 
        }
        return item;
    }

    /**
     * 根据id查询字典
     * 
     * @param id
     * @return
     */
    @RequestMapping("/getDicByMark")
    public @ResponseBody ReusltItem getDicById(Integer mark) {
        ReusltItem item = new ReusltItem();
        try {
            Advertimgdictionary record = advertImgService.queryDicByMark(mark);
            Integer value = record.getValue();
            List<Integer> list = new ArrayList<Integer>();
            if (value != null) {
                for (int i = 0; i < value; i++) {
                    list.add(i + 1);
                }
            }
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("根据id查询广告字典表出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.advertimg,
                "根据id查询广告字典表出错! 异常信息:", e, "/platform/advertimg/getDicByMark");
 
        }
        return item;
    }

    /**
     * 修改排序
     * @param id
     * @param setValue
     * @param type
     * @return
     */
    @RequestMapping("/SetSort")
    public @ResponseBody ReusltItem SetSort(String id, String setValue,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();

        try {
        	user=SessionUtil.getSessionUser(request);
            AdvertImg advertimg = advertImgService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
            advertimg.setSort(StringUtilsEX.ToInt(setValue));
            int temp = advertImgService.updateByPrimaryKey(advertimg);

            if (temp > 0) {
                item.setCode(0);
                item.setDesc("保存成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "advertImg_edit.jsp", "/platform/advertimg/SetSort", "修改排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改排序操作记录出错! 异常信息:",
    								e, "/platform/advertimg/SetSort");
    					}
    					
    				}
    			});
            }
        } catch (Exception e) {
            item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("修改排序出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.advertimg,
                "修改排序出错! 异常信息:", e, "/platform/advertimg/SetSort");
        }

        return item;
    }
}

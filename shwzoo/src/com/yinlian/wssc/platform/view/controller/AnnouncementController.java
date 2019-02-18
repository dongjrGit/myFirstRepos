package com.yinlian.wssc.platform.view.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Announcement;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.AnnouncementService;
import com.yinlian.wssc.web.util.Criteriaannouncement;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 
 * @author wl
 *
 */
@Controller
@RequestMapping("/platform/announcement")
public class AnnouncementController {
    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(AnnouncementController.class);
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    AdvertImgService            advertImgService;

    /**
     * 显示广告列表页面
     * @return
     */
    @RequestMapping("/showannouncement")
    public String showannouncement() {

        return "platform/announcement/announcement";
    }

    /**
     * 显示广告添加页面
     * @return
     * @throws Exception 
     */
    @RequestMapping("/announcementAdd")
    public String announcementAdd(Integer userid, HttpServletRequest request) throws Exception {
        List<Announcement> list = null;
        try {
            list = announcementService.queryAllrecord();
        } catch (Exception e) {
            logger.error("", e);
        }
        //		model.addAttribute("list", list);
        request.setAttribute("list", list);
        //		request.setAttribute("userid", userid);
        return "platform/announcement/announcementAdd";
    }

    /**
     * 显示图片添加页面
     * @return
     */
    @SuppressWarnings("deprecation")
    @RequestMapping("/announcementAddp")
    public @ResponseBody ReusltItem announcementAddp(String title, String content,
                                                     HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        int result = 0;
        try {
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Announcement announcement = new Announcement();
            announcement.setTitle(title);
            announcement.setContent(content);
            announcement.setSort(0);
            announcement.setCreattime(sim.parse(item.getStartTime()));
            SessionUser user = SessionUtil.getSessionUser(request);
            announcement.setUserid(user.getUserId());
            result = announcementService.add(announcement);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
            } else {
                item.setCode(-200);
                item.setDesc("添加失败");
            }
        } catch (Exception e) {
            logger.error("", e);
            item.setCode(-900);
            item.setDesc("异常：" + e.getMessage());
        }

        return item;
    }

    /**
    * 根据id删除广告
    * 
    * @param id
    * @return
    */
    @RequestMapping("/deleteannounById")
    public @ResponseBody ReusltItem deleteannounById(String id) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("参数id错误");
            return item;
        }
        try {
            int result = announcementService.delete(StringUtilsEX.ToInt(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
            }
        } catch (Exception e) {
            logger.error("AnnouncementController出现的异常" + e.getMessage(), e);
            item.setCode(-900);
            item.setDesc("异常：" + e.getMessage());
        }
        return item;
    }

    /**
     * 批量删除
     * @param idlist
     * @return
     */
    @RequestMapping("/delList")
    public @ResponseBody ReusltItem delList(String idlist) {
        ReusltItem item = new ReusltItem();
        String[] ids = idlist.split(",");
        int result = 0;
        if (!StringUtils.isNotNull(idlist)) {
            item.setCode(-101);
            item.setDesc("参数的id不能为空");
        }
        try {
            for (int i = 0; i < ids.length; i++) {

                result = announcementService.delete(StringUtilsEX.ToInt(ids[i]));
            }
            if (result > 0) {
                item.setCode(0);
                item.setDesc("批量删除成功");
            } else {
                item.setCode(-200);
                item.setDesc("批量删除失败");
            }
        } catch (Exception e) {
            logger.error("批量设置已读出现的异常" + e.getMessage(), e);
            item.setCode(-900);
            item.setDesc("异常：" + e.getMessage());
        }
        return item;

    }

    /**
     * 编辑查看
     */
    @RequestMapping("/editannoun")
    public String editFind(@RequestParam("id") Integer id, HttpServletRequest request) {
        Announcement announcement = new Announcement();
        try {
            announcement = announcementService.selectByid(id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        request.setAttribute("announid", id);
        request.setAttribute("announcement", announcement);
        return "platform/announcement/announcementEdit";
    }

    /**
     * 编辑查看
     */
    @RequestMapping("/editannounce")
    public @ResponseBody ReusltItem editannounce(String title, String content, Integer sort,
                                                 Integer oldid, HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        int result = 0;
        try {
            SessionUser user = SessionUtil.getSessionUser(request);
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Announcement announcement = announcementService.selectByid(oldid);
            Announcement announce = new Announcement();
            announce.setTitle(title);
            announce.setContent(content);
            announce.setSort(sort);
            announce.setCreattime(sim.parse(sim.format(new Date())));
            announce.setUserid(user.getUserId());
            BeanUtils.copyProperties(announce, announcement);
            result = announcementService.updateAnnouncement(announcement);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("编辑成功");
            } else {
                item.setCode(-200);
                item.setDesc("编辑失败");
            }
        } catch (Exception e) {
            logger.error("", e);
            item.setCode(-900);
            item.setDesc("异常：" + e.getMessage());
        }

        return item;
    }

    /**
     * 分页查询广告
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryannouncement")
    public @ResponseBody ReusltItem queryannouncement(String userid, String begin, String end,
                                                      String page, String size, String title) {
        //SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
        try {
            Criteriaannouncement criteria = new Criteriaannouncement();
            criteria.setUserid(null);
            criteria.setBegin(StringUtilsEX.ToShortDate(begin));
            criteria.setEnd(StringUtilsEX.ToShortDate(end));
            criteria.setTitle(title);
            criteria.setOrderByClause("sort");
            PageBean pageBean = announcementService.selectAnnouncementByPage(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
            logger.error("分页查询广告出现的异常" + e.getMessage(), e);
            item.setCode(-900);
            item.setDesc("异常：" + e.getMessage());
        }
        return item;
    }

    /**
     * 排序调整
     * 
     * @param sort
     * @param id
     * @return
     */
    @RequestMapping("/updateSort")
    public @ResponseBody ReusltItem updateSort(String sort, String id) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) <= 0) {
                item.setCode(-101);
                item.setDesc("广告ID参数错误,id:" + id);
                return item;
            }
            if (StringUtilsEX.ToInt(sort) < 0) {
                item.setCode(-102);
                item.setDesc("广告排序参数错误,sort:" + sort);
                return item;
            }
            if (announcementService.updateOrder(StringUtilsEX.ToInt(sort), StringUtilsEX.ToInt(id)) > 0) {
                logger.info(String.format("修改广告排序成功! ID:{0},排序:{1}", id, sort));
                item.setCode(0);
                item.setDesc("修改广告排序成功");
            } else {
                logger.warn(String.format("修改广告排序失败! ID:{0},排序:{1}", id, sort));
                item.setCode(-200);
                item.setDesc("修改广告排序失败");
            }
        } catch (Exception e) {
            logger.error(AnnouncementController.class + "修改广告排序异常", e);
            item.setCode(-900);
            item.setDesc("异常：" + e.getMessage());
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
    @RequestMapping("/updateSortList")
    public @ResponseBody ReusltItem updateSortList(String sorts, String ids) {
        ReusltItem item = new ReusltItem();
        try {
            List<Integer> orderlist = new ArrayList<Integer>();
            List<Integer> idlist = new ArrayList<Integer>();
            if (ids == null) {
                item.setCode(-101);
                item.setDesc("广告ID参数错误,id:" + ids);
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
                    item.setDesc("广告ID参数错误,id:" + idStrings[i]);
                    return item;
                }
            }
            if (sorts == null) {
                item.setCode(-102);
                item.setDesc("广告排序参数错误,sort:" + sorts);
                return item;
            }
            String[] orderStrings = sorts.split(",");
            for (int i = 0; i < orderStrings.length; i++) {
                ob = StringUtilsEX.ToInt(orderStrings[i]);
                if (ob >= 0)
                    orderlist.add(ob);
                else {
                    item.setCode(-102);
                    item.setDesc("广告排序参数错误,sort:" + orderStrings[i]);
                    return item;
                }
            }

            if (announcementService.updateOrderList(orderlist, idlist) > 0) {
                logger.info(String.format("批量广告排序成功! ID集合:{0},排序集合:{1}", ids, sorts));
                item.setCode(0);
                item.setDesc("批量修改广告排序成功");
            } else {
                logger.warn(String.format("批量修改广告排序失败! ID集合:{0},排序集合:{1}", ids, sorts));
                item.setCode(-200);
                item.setDesc("批量修改广告排序失败");
            }
        } catch (Exception e) {
            logger.error(AnnouncementController.class + "批量修改广告排序异常", e);
            item.setCode(-900);
            item.setDesc("异常：" + e.getMessage());
        }
        return item;
    }

}

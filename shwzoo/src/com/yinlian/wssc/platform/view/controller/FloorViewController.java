package com.yinlian.wssc.platform.view.controller;





import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.po.Floor;
import com.yinlian.wssc.web.po.Floorproduct;
import com.yinlian.wssc.web.service.FloorService;
import com.yinlian.wssc.web.service.FloorproductService;
import com.yinlian.wssc.web.util.StringUtilsEX;


@Controller
@RequestMapping("/platform/floor")
public class FloorViewController {
	 /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(FloorViewController.class);
    
    @Autowired
    private FloorService      floorService;
    
    @Autowired
    private  FloorproductService   floorproductService;
    
    /**
     * 显示楼层列表页面
     * 
     * @return
     */
    @RequestMapping("/list")
    public String list() {
    	
        return "platform/floor/Floors";
    }
    
    /**
     * 显示添加页面
     * 
     * @return
     */
    @RequestMapping("/showadd")
    public String showadd() {

        return "platform/floor/FloorsEdit";
    }
    
    
    /**
     * 显示修改页面
     * 
     * @return
     */
    @RequestMapping("/showEdit")
    public String showEdit(Integer id, HttpServletRequest request) {
       
    	try {
            Floor floor=floorService.querybyId(id);
            request.setAttribute("floor", floor);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "platform/floor/FloorsEdit";
    }
    
    /**
     * 显示楼层管理列表页面
     * 
     * @return
     */
    @RequestMapping("/showProList")
    public String showProList(String flid,Model model) {

    	model.addAttribute("flid", flid);
        return "platform/floor/ProFloors";
    }
    
    /**
     * 显示商品专题管理编辑页面
     * 
     * @return
     */
    @RequestMapping("/showProEdit")
    public String showProEdit(String flid,String id, Model model) {
        try {
           model.addAttribute("flid", flid);
           model.addAttribute("id", id);
           Floorproduct floorproduct=floorproductService.queryById(StringUtilsEX.ToInt(id));
           model.addAttribute("data", floorproduct);   
        } catch (Exception e) {

            logger.error("", e);
        }
        return "platform/floor/ProFloorsEdit";
    }

}

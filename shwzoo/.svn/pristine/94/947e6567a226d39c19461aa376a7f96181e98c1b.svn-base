package com.techown.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techown.wssc.web.po.RcmdScenic;
import com.techown.wssc.web.service.RcmdScenicService;

@Controller
@RequestMapping("/zoo/rcmdScenic")
public class RcmdScenicViewController {
    private static Logger logger = LoggerFactory.getLogger(RcmdScenicViewController.class);

    @Autowired
    private RcmdScenicService rcmdScenicService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, String type) {
        request.setAttribute("type", type);
        return "platform/zoo/rcmdScenicList";
    }

    /**
     * 跳转添加和编辑页面
     * 
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/listedit")
    public String listedit(Integer id, String type, HttpServletRequest request) {
        logger.info("listedit request id=" + id + "!type=" + type);
        try {
            if (null != id) {
                RcmdScenic rcmdScenic = rcmdScenicService.selectById(id);
                request.setAttribute("bean", rcmdScenic);
                request.setAttribute("type", rcmdScenic.getType());
            } else {
                request.setAttribute("type", type);
            }
        } catch (Exception e) {
            logger.error("call listedit exception request id=" + id + "!type=" + type, e);
        }
        return "/platform/zoo/rcmdScenicedit";
    }
}

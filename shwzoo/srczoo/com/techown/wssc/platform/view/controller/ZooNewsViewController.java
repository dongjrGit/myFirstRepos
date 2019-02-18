package com.techown.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techown.wssc.web.po.ZooNews;
import com.techown.wssc.web.service.ZooNewsService;

@Controller
@RequestMapping("/zoo/news")
public class ZooNewsViewController {

	private static Logger logger = LoggerFactory.getLogger(ZooNewsViewController.class);
	@Autowired
	private ZooNewsService zooNewsService;

	/**
	 * 跳转列表页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		return "platform/zoo/newsList";
	}

	/**
	 * 跳转添加和编辑页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/listedit")
	public String listedit(Integer id, HttpServletRequest request) {
		logger.info("zooNews listedit id {}", id);
		try {
			if (null != id) {
				ZooNews bean = zooNewsService.selectById(id);
				request.setAttribute("bean", bean);
			}
		} catch (Exception e) {
			logger.error("zooNews listedit id {} !\r\n exception", id, e);
		}
		return "/platform/zoo/newsedit";
	}
}

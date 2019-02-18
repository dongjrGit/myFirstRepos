package com.techown.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zoo/zooMap")
public class ZooMapViewController {
	private static Logger logger = LoggerFactory.getLogger(ZooMapViewController.class);
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Integer type, Integer state) {
		logger.info("list type=" + type + "!state=" + state);
		request.setAttribute("type", type);
		request.setAttribute("state", state);
		return "platform/zoo/zooMapList";
	}

	/**
	 * 跳转添加和编辑页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/listedit")
	public String listedit(Integer id, Integer type, Integer state, HttpServletRequest request) {
		logger.info("listedit request id=" + id + "!type=" + type + "!state=" + state);
		try {
			if (null != id) {

			}
			request.setAttribute("type", type);
			request.setAttribute("state", state);
		} catch (Exception e) {
			logger.error("call listedit exception request id=" + id + "!type=" + type + "!state=" + state, e);
		}
		return "/platform/zoo/zooMapedit";
	}
	@RequestMapping("/editView")
	public String editView(Integer id, Integer type, Integer state,String msg ,HttpServletRequest request) {
		logger.info("editView request id=" + id + "!type=" + type + "!state=" + state);
		try {
			if (null != id) {

			}
			request.setAttribute("type", type);
			request.setAttribute("state", state);
			request.setAttribute("msg", msg);
		} catch (Exception e) {
			logger.error("call editView exception request id=" + id + "!type=" + type + "!state=" + state, e);
		}
		return "/platform/zoo/zooMapDetail";
	}
	
}

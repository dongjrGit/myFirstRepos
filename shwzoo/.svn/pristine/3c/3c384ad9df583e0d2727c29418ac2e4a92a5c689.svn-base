package com.techown.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techown.wssc.web.po.ZooEditor;
import com.techown.wssc.web.service.ZooEditorService;

@Controller
@RequestMapping("/zoo/editor")
public class ZooEditorViewController {

	private static Logger logger = LoggerFactory.getLogger(ZooEditorViewController.class);
	@Autowired
	private ZooEditorService zooEditorService;

	/**
	 * 跳转列表页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Integer type) {
		logger.info("list type {}", type);
		request.setAttribute("type", type);
		return "platform/zoo/editorList";
	}

	/**
	 * 跳转添加和编辑页面
	 * 
	 * @param id
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("/listedit")
	public String listedit(HttpServletRequest request, Integer id, Integer type) {
		logger.info("zooEditor listedit id {},type {}", id, type);
		try {
			if (null != id) {
				ZooEditor bean = zooEditorService.getById(id);
				request.setAttribute("bean", bean);
				request.setAttribute("type", bean.getType());
			} else {
				request.setAttribute("type", type);
			}
		} catch (Exception e) {
			logger.error("zooEditor listedit id {}, type {} !\r\n exception", id, type, e);
		}
		return "/platform/zoo/editorDetail";
	}
}

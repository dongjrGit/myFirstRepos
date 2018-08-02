package com.mobile.application.controller.knowledge;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.service.knowledge.IKnowledgeService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.product.ProductMenuVO;

@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {
	
	@Autowired
	private IKnowledgeService knowledgeService;
	
	/**
	 * 进入菜单管理页面
	 */
	@RequestMapping("/menu")
	public String initKnowledgeMenu(){
		return "knowledge/knowledge_menu";
	}
	
	/**
	 * 查询菜单
	 * @param root
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryMenu")
	@ResponseBody
	public List<ProductMenuVO> qryKnowledgeMenu(String root, HttpSession session, HttpServletRequest request) {
		List<ProductMenuVO> list = knowledgeService.qryKnowledgeMenu(root);
		return list;
	}
	
	/**
	 * 菜单新增
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
	@RequestMapping("/addMenu")
	@ResponseBody
	public CommonVO add(@RequestBody ProductMenuVO menuVO, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, BusinessException {
		knowledgeService.addMenu(menuVO);
		return new CommonVO(true, "添加成功", null, null);
	}
	/**
	 * 菜单编辑
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
	@RequestMapping("/editMenu")
	@ResponseBody
	public CommonVO edit(@RequestBody ProductMenuVO menuVO, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, BusinessException {
		knowledgeService.editMenu(menuVO);
		return new CommonVO(true, "保存成功", null, null);
	}
	/**
	 * 删除菜单
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping("/delMenu")
	@ResponseBody
	public CommonVO delete(@RequestBody ProductMenuVO menuVO, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		knowledgeService.delMenu(menuVO);
		return new CommonVO(true, "删除成功", null, null);
	}
}

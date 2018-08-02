package com.mobile.application.controller.menu;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.service.menu.IMenuService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.menu.MenuVO;


@Controller
@RequestMapping("/menu")
public class MenuController {
	

	protected final transient Log log = LogFactory
			.getLog(MenuController.class);

	@Autowired
	private IMenuService menuServiceImpl;	
	
	/**
	 * 跳转菜单编辑主页面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpSession session, HttpServletRequest request) {
		return "menu/menu";
	}
	
	/**
	 * 菜单查询
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public List<MenuVO> query(HttpSession session, HttpServletRequest request) {
		return menuServiceImpl.qryMenu();
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
	@RequestMapping("/edit")
	@ResponseBody
	public CommonVO edit(@RequestBody MenuVO menuVO, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, BusinessException {
		menuServiceImpl.editMenu(menuVO);
		return new CommonVO(true, "保存成功", null, null);
	}
	
	/**
	 * 菜单新增
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping("/add")
	@ResponseBody
	public CommonVO add(@RequestBody MenuVO menuVO, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		menuServiceImpl.addMenu(menuVO);
		return new CommonVO(true, "添加成功", null, null);
	}
	
	/**
	 * 删除菜单
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping("/del")
	@ResponseBody
	public CommonVO delete(@RequestBody MenuVO menuVO, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		menuServiceImpl.delMenu(menuVO);
		return new CommonVO(true, "删除成功", null, null);
	}
	
	/**
	 * 编辑菜单action
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping("/qryAction")
	@ResponseBody
	public CommonVO qryActions(String menuId, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		CommonVO CommonVO = menuServiceImpl.qryActions(menuId);
		return CommonVO;
	}
	
	/**
	 * 保存action
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping("/saveAction")
	@ResponseBody
	public CommonVO saveActions(String actionIds, String menuId, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		CommonVO CommonVO = menuServiceImpl.saveActions(actionIds, menuId);
		return CommonVO;
	}
}

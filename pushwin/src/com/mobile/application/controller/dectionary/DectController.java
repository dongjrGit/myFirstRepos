package com.mobile.application.controller.dectionary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.controller.login.LoginController;
import com.mobile.application.service.dectionary.IDectService;
import com.mobile.application.vo.CommonVO;

@Controller
@RequestMapping("/dect")
public class DectController {
	protected final transient Log log = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private IDectService dectService;
	@Autowired
	private ServletContext servletContext;
	
	/**
	 * 进入数据字典页面
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/init")
	public String init() throws IOException{
		return "dectionary/dectionary";
	}
	
	/**
	 * 查询数据字典大类型列表
	 * @param pageIndex
	 * @param pageSize
	 * @param businTypeName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/listType")
	@ResponseBody
	public CommonVO listType(String pageIndex, String pageSize, String businTypeName) throws IOException{
		return dectService.dictlist(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), businTypeName);
	}
	
	/**
	 * 查询数据字典小类型详细列表
	 * @param pageIndex
	 * @param pageSize
	 * @param dict_id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/listItem")
	@ResponseBody
	public CommonVO listItem(String pageIndex, String pageSize, String dict_id) throws IOException{
		return this.dectService.listItem(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), dict_id);
	}
	/**
	 * 保存数据字典大类型
	 * @param dictNum
	 * @param dictName
	 * @param dictDesc
	 * @return
	 */
	@RequestMapping("/saveType")
	@ResponseBody
	public CommonVO saveType(String dictNum, String dictName, String dictDesc){
		return dectService.saveType(dictNum, dictName, dictDesc);
//		return this.dectService.addItem(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), dict_id);
	}
	
	/**
	 * 根据id 查询字典大类型数据
	 * @param dictId
	 * @return
	 */
	@RequestMapping("/dialogType")
	@ResponseBody
	public List<?> dialogType (String dictId){
		return dectService.dialogType(dictId);
	}
	
	/**
	 * 更新字典大类型
	 * @param id
	 * @param dictName
	 * @param dictDesc
	 * @return
	 */
	@RequestMapping("/updateType")
	@ResponseBody
	public CommonVO updateType (String id, String dictName, String dictDesc) {
		return dectService.updateType(id, dictName, dictDesc);
	}
	
	/**
	 * 删除字典大类型
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/deleteType")
	@ResponseBody
	public CommonVO deleteType (String roleId){
		return dectService.deleteType(roleId);
	}
	
	/**
	 * 删除字典详细编号
	 * @param deleteRows
	 * @return
	 */
	@RequestMapping("/deleteItem")
	@ResponseBody
	public CommonVO deleteItem (String deleteRows) {
		return dectService.deleteItem(deleteRows);
	}
	
	/**
	 * 查询字典小类型编号
	 * @param typeId
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/dialogItem")
	@ResponseBody
	public CommonVO dialogItem (String typeId, String itemId) {
		return dectService.dialogItem(typeId, itemId);
	}
	
	/**
	 * 更新数据字典小类
	 * @param dictDesc
	 * @param dictName
	 * @param dictNum
	 * @param dictsonId
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateItem")
	@ResponseBody
	public CommonVO updateItem (String dictDesc, String dictName, String dictNum, String dictsonId, String id) {
		return dectService.updateItem(dictDesc, dictName, dictNum, dictsonId, id);
	}
	
	/**
	 * 保存数据字典小类
	 * @param session
	 * @param dictDesc
	 * @param dictName
	 * @param dictNum
	 * @param dictId
	 * @return
	 */
	@RequestMapping("/addItem")
	@ResponseBody
	public CommonVO addItem (HttpSession session,String dictDesc, String dictName, String dictNum, String dictId){
		return dectService.addItem(session,dictDesc,dictName,dictNum,dictId);
	}
	
	/**
	 * 立即生效
	 */
	@RequestMapping("/activeDict")
	@ResponseBody
	public CommonVO qryAllDict (){
		servletContext.removeAttribute("dictMap");
		servletContext.setAttribute("dictMap", dectService.qryAllDict());
		return new CommonVO(true, "立即生效成功");
	}
	
	/**
	 * 通过数据字典大类Id查找小类
	 * @param typeId
	 * @return
	 */
	@RequestMapping("/qryDectByTypeWithDesc")
	@ResponseBody
	public List<?> qryDectByTypeWithDesc(String typeId){
		return dectService.qryDectByTypeWithDesc(typeId);
	}
	
	/**
	 * 通过数据字典大类Id查找小类
	 * @param typeId
	 * @return
	 */
	@RequestMapping("/qryDectByType")
	@ResponseBody
	public List<?> qryDectByType(String typeId){
		return dectService.qryDectByType(typeId);
	}
}

package com.mobile.application.controller.shootmater;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.service.shootmater.IShootMaterService;
import com.mobile.application.vo.CommonVO;

/** 
 * Copy Right Information   :Techown, 2015
 * Project                  : pushwin
 * Author					: 徐雪萍
 * JDK version used         : jdk1.6
 * Comments                 : 拍摄材料配置
 * Version                  : 1.01
 * Modification history     : 2015.9
 * Sr Date   Modified By   Why & What is modified
 * 1. 2015.9.27 徐雪萍     新建 
 **/
@Controller
@RequestMapping("/shootmater")
public class ShootMaterController {

		@Autowired
		private IShootMaterService ShootMaterService;
		
		/**
		 * Description : 拍摄材料配置，初始化页面
		 * @author 徐雪萍
		 * @version 1.01
		 * @see N/A
		 * @param N/A
		 * @return 拍摄材料配置界面
		 * @exception 
		 */
		@RequestMapping("/shootmaterInit")
		public String shootMater(){
			return "shootmater/shootMater";
		}
		/**
		 * Description : 查询拍摄材料，页面
		 * @author 徐雪萍
		 * @version 1.01
		 * @see N/A
		 * @param N/A
		 * @return 查询拍摄材料
		 * @exception 
		 */
		@RequestMapping("/qryShootMater")
		@ResponseBody
		public List<?> qryShootMater(HttpSession session,String type){
			List<?> list = ShootMaterService.qryShootMater(session,type);
			return list;
		}
		/**
		 * Description : save拍摄材料
		 * @author 徐雪萍
		 * @version 1.01
		 * @see N/A
		 * @param N/A
		 * @return 
		 * @exception 
		 */
		@RequestMapping("/saveShootMater")
		@ResponseBody
		public  CommonVO saveShootMater(HttpSession session,String materId,String materPid,String materLevel,String materName,String shootRequire,String materType){
			return ShootMaterService.saveShootMater(session,materId,materPid,materLevel,materName,shootRequire,materType);
		}
		/**
		 * Description : delete拍摄材料
		 * @author 徐雪萍
		 * @version 1.01
		 * @see N/A
		 * @param N/A
		 * @return CommonVO
		 * @exception 
		 */
		@RequestMapping("/delShootMater")
		@ResponseBody
		public CommonVO delShootMater(HttpSession session,String materId,String materIdPid){
			return  ShootMaterService.delShootMater(session,materId,materIdPid);
		}
		
		
		
}

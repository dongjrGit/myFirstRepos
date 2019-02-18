package com.yinlian.api.wap.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.util.CriteriaCommodity;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/class")
public class WapClassController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/classList")
	public String classList(String fatherID, Model model,HttpServletRequest request){
		try {
			Integer fid = StringUtilsEX.ToIntNull(fatherID);
			CriteriaCommodity crit = new CriteriaCommodity();
			if (fid != null && fid > 0) {
				crit.setFatherid(fid);
			}
			List<CategoryDTO> bean = categoryService.selectAll(0);	
			model.addAttribute("classList", bean);
		} catch (Exception e) {
			LogHandle.error(LogType.wap, "获取商品分类列表错误：{0}", e, "/Class");
		}
		return "/template/wap/products/classList";
	}
	
}
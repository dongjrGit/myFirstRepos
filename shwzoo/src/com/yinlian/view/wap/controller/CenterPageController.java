package com.yinlian.view.wap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.WebSetEnum;
import com.yinlian.api.app.dto.ProductDto;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/wap/centerPage")
public class CenterPageController {
	
	@RequestMapping("/ztj")
	public ModelAndView ztj(String name,String ids){
		ModelAndView model = new ModelAndView("/template/wap/zhuye/ztj_list");
		try {
			model.addObject("title", name);
			List<ProductDto>  list=new ArrayList<ProductDto>();
			List<Integer> goodids=new ArrayList<Integer>();
			if(ids.contains(",")){
				String[] a=ids.split(",");
				for (int i = 0; i < a.length; i++) {
					goodids.add(StringUtilsEX.ToInt(a[i]));
				}
			}else{
				goodids.add(StringUtilsEX.ToInt(ids));
			}
			if(goodids!=null&&goodids.size()>0){
				for (Integer id : goodids) {
					ProductDto dto=productService.selectInfoById(id,WebSetEnum.wap.getValue());
					if(dto!=null){
						list.add(dto);
					}
				}
			}
			model.addObject("list",list);
			model.addObject("href","/wap/centerPage/ztj?name="+name+"&ids="+ids);
			
		} catch (Exception e) {
		}
		return model;
	}


	@Autowired
	private ProductService  productService;
	
}

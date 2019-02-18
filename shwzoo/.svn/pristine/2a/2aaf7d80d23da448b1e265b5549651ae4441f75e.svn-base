package com.techown.wssc.platform.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techown.wssc.web.po.ScenicImage;
import com.techown.wssc.web.po.ScenicWithBLOBs;
import com.techown.wssc.web.service.ScenicImageService;
import com.techown.wssc.web.service.ScenicService;

@Controller
@RequestMapping("/zoo/scenic")
public class ScenicViewController {
	
	@Autowired
	private ScenicService scenicService;
	
	@Autowired
	private ScenicImageService scenicImgeService;

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		return "platform/zoo/scenicList";
	}
	
	/**
	 * 跳转添加和编辑页面
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/listedit")
	public String listedit(Integer id,HttpServletRequest request) {
		try {
			if(null!=id){
				ScenicWithBLOBs scenic = scenicService.selectById(id);
				List<ScenicImage>  list =scenicImgeService.selectByImageId(scenic.getImageid());
				StringBuffer buffer = new StringBuffer("");
				for (ScenicImage scenicImage : list) {
					buffer.append(scenicImage.getPath()).append(",");
				}
				request.setAttribute("bean", scenic);
				request.setAttribute("imagePaths", buffer.toString().substring(0, buffer.toString().length()-1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/platform/zoo/scenicedit";
	}
}

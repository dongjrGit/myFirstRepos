package com.mobile.application.controller.endpoint;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.lf5.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.service.product.IProductService;
import com.mobile.application.vo.CommonVO;

@Controller
@RequestMapping("/endpoint")
public class EndpointProductController {

	@Autowired
	private IProductService productService;
	
	/**
	 * 查询产品目录
	 * @param root
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/productMenu")
	@ResponseBody
	public CommonVO qryProductMenu(String root, HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		List<?> productMenu = productService.qryProductMenuForPad(reqJson == null ? "" : reqJson.optString("orgCode"));
		return new CommonVO(true, productMenu, productMenu.size() + "");
	}
	/**
	 * Description :
	 * @param orgCode
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/checkUpdateProduct")
	@ResponseBody
	public CommonVO checkUpdateProduct(String orgCode, HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		List<?> product = productService.checkUpdateProduct(reqJson == null ? "" : reqJson.optString("orgCode"));
		return new CommonVO(true, product, product.size() + "");
	}
	/**
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/qryProductForDownload")
	@ResponseBody
	public CommonVO qryProductForDownload(HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		List<?> product = productService.qryProductForDownload(reqJson == null ? "" : reqJson.optString("productIds"));
		return new CommonVO(true, product, product.size() + "");
	}
}

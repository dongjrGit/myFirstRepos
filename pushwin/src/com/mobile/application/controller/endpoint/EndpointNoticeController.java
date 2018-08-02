package com.mobile.application.controller.endpoint;

import java.io.IOException;

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

import com.mobile.application.service.endpoint.IEndpointNoticeService;
import com.mobile.application.vo.CommonVO;

@Controller
@RequestMapping("/endpoint")
public class EndpointNoticeController {
	
	@Autowired
	private IEndpointNoticeService endpointNoticeService;
	
	@RequestMapping("/qryNoticeList")
	@ResponseBody
	public CommonVO qryNoticeList(HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		if(null == reqJson) {
			return new CommonVO(false, "请求参数为空。");
		}
		return endpointNoticeService.qryNoticeList(reqJson.optString("userId"), reqJson.optString("noticeType"), reqJson.optString("pageIndex"), reqJson.optString("indexSize"));
	}
	
	@RequestMapping("/qryNoticeCount")
	@ResponseBody
	public CommonVO qryNoticeCount(HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		if(null == reqJson) {
			return new CommonVO(false, "请求参数为空。");
		}
		return endpointNoticeService.qryNoticeCount(reqJson.optString("userId"), reqJson.optString("noticeType"));
	}
	
	@RequestMapping("/readNotice")
	@ResponseBody
	public CommonVO readNotice(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		if(null == reqJson) {
			return new CommonVO(false, "请求参数为空。");
		}
		return endpointNoticeService.readNotice(reqJson.optString("pushNoticeId"));
	}
	
	@RequestMapping("/qryNotRedNotice")
	@ResponseBody
	public CommonVO qryNotRedNotice(HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		if(null == reqJson) {
			return new CommonVO(false, "请求参数为空。");
		}
		return endpointNoticeService.qryNoRedNoticeList(reqJson.optString("userId"), reqJson.optString("noticeType"));
	}
}

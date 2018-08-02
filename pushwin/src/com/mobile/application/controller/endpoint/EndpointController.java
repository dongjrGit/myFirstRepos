package com.mobile.application.controller.endpoint;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cetelem.corp.ws.application.service.AppCustInfoQueryDto;
import com.cetelem.corp.ws.application.service.AppInfoDto;
import com.cetelem.corp.ws.application.service.AppProgressRequest;
import com.cetelem.corp.ws.application.service.AppProgressResponse;
import com.cetelem.corp.ws.application.service.CustCreditLimitRequest;
import com.cetelem.corp.ws.application.service.CustCreditLimitResponse;
import com.cetelem.corp.ws.application.service.CustomerInquiresRequest;
import com.cetelem.corp.ws.application.service.CustomerInquiresResponse;
import com.cetelem.corp.ws.application.service.LoadApplicationRequest;
import com.cetelem.corp.ws.application.service.LoadApplicationResponse;
import com.cetelem.corp.ws.application.service.RatingScalerRequest;
import com.cetelem.corp.ws.application.service.RatingScalerResponse;
import com.cetelem.corp.ws.application.service.ReturnOfGoodsRequest;
import com.cetelem.corp.ws.application.service.ReturnOfGoodsResponse;
import com.cetelem.corp.ws.application.service.SmsServiceRequest;
import com.cetelem.corp.ws.application.service.SmsServiceResponse;
import com.cetelem.corp.ws.application.service.SubmitApplicationRequest;
import com.cetelem.corp.ws.application.service.SubmitApplicationResponse;
import com.cetelem.corp.ws.application.service.TelesalesCustInfoQueryRequest;
import com.cetelem.corp.ws.application.service.TelesalesCustInfoQueryResponse;
import com.cetelem.corp.ws.application.service.UploadStatusResponse;
import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.tools.MD5Util;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.entity.Puwshlogin;
import com.mobile.application.service.dectionary.IDectService;
import com.mobile.application.service.endpoint.IEndpointService;
import com.mobile.application.service.shootmater.IShootMaterService;
import com.mobile.application.until.JsonUtil;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.activity.ActivityResponseVO;
import com.mobile.application.vo.endpoint.ReqAddressVO;
import com.mobile.application.vo.endpoint.ReqApkVO;
import com.mobile.application.vo.endpoint.ReqLoginVO;
import com.mobile.application.vo.endpoint.ReqVersionVO;
import com.pushwin.payment.PaymentCalForServiceRequest;
import com.pushwin.payment.PaymentCalResponse;
import com.yuchengtech.bcrm.mobile.webservice.ResponseToken;


/** 
 * Copy Right Information   :Techown, 2015
 * Project                  : pushwin
 * Author					: 姜瑞
 * JDK version used         : jdk1.6
 * Comments                 : 终端设备接入接口实现类 
 * Version                  : 1.01
 * Modification history     : 2005.9.27
 * Sr Date   Modified By   Why & What is modified
 * 1. 2005.9.27 姜瑞     新建 
 **/
@Controller

@RequestMapping("/endpoint")
public class EndpointController {
	/**
	 * 日志
	 */
	
	private static Logger logger4j = Logger.getLogger(EndpointController.class);
	
	@Autowired
	IEndpointService endpointService;
	@Autowired
	private IDectService dectService;
	@Autowired
	private IShootMaterService ShootMaterService;
	
	private String identifiy = (String) SpringProperty.props
			.get("nj.sms.identifiy");
	private String ks_identifiy = (String) SpringProperty.props
			.get("ks.sms.identifiy");
	private String rz_identifiy = (String) SpringProperty.props
			.get("rz.sms.identifiy");
	
	private String potantial = (String) SpringProperty.props
			.get("nj.sms.potantial");
	
	private String picurl = (String) SpringProperty.props
			.get("pic.url");
	/**
	 * Description : 终端设备登录验证
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param 员工号、员工密码、设备编号、终端设备时间、设备sim卡号
	 * @return 登录结果
	 * @exception BusinessException
	 */
	@RequestMapping("/login")
	@ResponseBody
	public CommonVO login(@RequestBody ReqLoginVO endpointLoginVO, HttpServletRequest request, HttpServletResponse response) throws BusinessException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		if(endpointLoginVO.getBankFlag()==null || endpointLoginVO.getBankFlag()==""){
			endpointLoginVO.setBankFlag("NJCB");
		}
		CommonVO commonVO = endpointService.login(response, endpointLoginVO);
		if(commonVO.isSuccess()){
			response.setHeader("wms-token", endpointLoginVO.getUserCode());
		}   
		return commonVO;
	}
	
	/**
	 * Description : crm登录验证
	 * @author ydc
	 * @version 1.00
	 * @see N/A
	 * @param 员工号
	 * @return 员工号 令牌 交易状态 交易返回消息
	 * @exception BusinessException
	 * @throws IOException 
	 */
	@RequestMapping("/crmLogin")
	@ResponseBody
	public CommonVO crmLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
//		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		String userId=reqJson.optString("userId");
		logger4j.info("crmLogin userId："+userId);
//		logger4j.info("crmLogin: " + JsonUtil.JSONTOString(request));
		ResponseToken responseToken = null;
		try {
			responseToken = endpointService.crmLogin(userId);
		} catch (Exception e) {
			logger4j.info("crmLogin Excepion："+e.getMessage());
			return new CommonVO(false, "copo服务异常");
		}
		return new CommonVO(true, "", responseToken);
	}
	
	/**
	 * Description : 跨行查询，根据pad输入的银行名称，查询银行行号
	 * @author ydc
	 * @version 1.01
	 * @see N/A
	 * @param 行名
	 * @return 行号
	 * @exception BusinessException
	 * @throws IOException 
	 */
	@RequestMapping("/redisService")
	@ResponseBody
	public CommonVO redisService(HttpServletRequest request, HttpServletResponse response) throws BusinessException, IOException{
//		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
//		return endpointService.qryBankAcc(reqRedisVo);
		
		
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		logger4j.info( "redisService query:"+reqJson.optString("orgFullname") );
		Map<String, String> dictMap = new HashMap<String, String>();
		String bankCode = "";
		CommonVO commonVO = new CommonVO();
		try {
			
			bankCode = endpointService.qryBankAcc(reqJson == null ? "" : reqJson.optString("orgFullname"));
			dictMap.put("bankCode", bankCode);
			commonVO = new CommonVO(true, dictMap, null);
		} catch (Exception e) {
			logger4j.info( "redisService query error:"+e.getMessage() );
			commonVO = new CommonVO(false, dictMap, null);
		}
		return commonVO;
	}
	/**
	 * Description : 终端设备获取apk列表
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param 登录员工工号、最新一次zip包下载时间
	 * @return 查询结果集、下载图片zip包路径
	 * @exception BusinessException
	 */
	@RequestMapping("/qryApks")
	@ResponseBody
	public CommonVO qryApks(@RequestBody ReqApkVO reqApkVO, HttpServletRequest request, HttpServletResponse response) throws BusinessException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		return endpointService.qryApks(reqApkVO);
	}
	 /**
	  * Description : 保存pad经纬度地址
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param addressVO 地址数据集
	  * @param request 
	  * @param response
	  * @return
	  * @throws BusinessException
	  * @exception BusinessException
	  */
	@RequestMapping("/savePadAddress")
	@ResponseBody
	public CommonVO savePadAddress(@RequestBody ReqAddressVO addressVO, HttpServletRequest request, HttpServletResponse response) throws BusinessException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		CommonVO commonVO = endpointService.savePadAddress(response, addressVO);
		return commonVO;
	}
	
	 /**
	  * Description : 查询数据字典接口
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param request 
	  * @param response
	  * @return map
	  * @throws BusinessException
	  * @exception BusinessException
	 * @throws IOException 
	  */
	@RequestMapping("/qryAllDict")
	@ResponseBody
	public CommonVO qryAllDict(String lastUpdateTime, HttpServletRequest request, HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		if(reqJson.optString("bankFlag")==null || reqJson.optString("bankFlag")==""){
			reqJson.put("bankFlag", "NJCB");
		}
		Map<String, Object> dictMap = new HashMap<String, Object>();
		Map<String,Object> dicts = dectService.qryDicts(reqJson);
		dictMap.put("dict", dicts);
		dictMap.put("lastUpdateTime", DateUtil.format(new Date()));
		CommonVO commonVO = new CommonVO(true, dictMap, null);
		return commonVO;
	}
	
	 /**
	  * Description : 查询省市区
	  * @author 姜瑞
	  * @version 1.01
	  * @see N/A
	  * @param request 
	  * @param response
	  * @return map
	  * @throws BusinessException
	  * @exception BusinessException
	 * @throws IOException 
	  */
	@RequestMapping("/qryCity")
	@ResponseBody
	public CommonVO qryCity(String lastUpdateTime,HttpServletRequest request,  HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		if(reqJson.optString("bankFlag")==null || reqJson.optString("bankFlag")==""){
			reqJson.put("bankFlag", "NJCB");
		}
		Map<String, Object> citys = dectService.qryCity(reqJson == null ? "" : reqJson.optString("lastUpdateTime"),
				reqJson == null ? "" : reqJson.optString("bankFlag"));
		CommonVO commonVO = new CommonVO(true, citys, null);
		return commonVO;
	}
	 /**
	  * Description : 查询拍摄字典接口
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param request 
	  * @param response
	  * @return map
	  * @throws BusinessException
	  * @exception BusinessException
	 * @throws IOException 
	  */
	@RequestMapping("/qryShootingDict")
	@ResponseBody
	public CommonVO qryShootingDict(String lastUpdateTime, HttpServletRequest request, HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		Map<String, Object> dictMap = new HashMap<String, Object>();
		List<?> shootingDict = ShootMaterService.qryShootingDict(reqJson == null ? "" :reqJson.optString("lastUpdateTime"));
		dictMap.put("shootingDict", shootingDict);
		dictMap.put("lastUpdateTime", DateUtil.format(new Date()));
		CommonVO commonVO = new CommonVO(true, dictMap, null);
		return commonVO;
	}
	
	 /**
	  * Description : pad根据身份证查询客户信用额度，可用额度，产品名称，额度起始日
	  * @author yangdc
	  * @version 1.01
	  * @see N/A
	  * @param request 
	  * @param response
	  * @return map
	  * @throws BusinessException
	  * @exception BusinessException
	 * @throws IOException 
	  */
	@RequestMapping("/queryCustCreditLimit")
	@ResponseBody
	public CommonVO queryCustCreditLimit(@RequestBody CustCreditLimitRequest custCreditLimitRequest, HttpServletRequest request,HttpServletResponse response){
//		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		logger4j.info("CueryCustCreditLimit " + JsonUtil.JSONTOString(custCreditLimitRequest));
		CustCreditLimitResponse custCreditLimitResponse = null;
		try {
			custCreditLimitResponse = this.endpointService.queryCustCreditLimit(custCreditLimitRequest);
			logger4j.info("queryCustCreditLimit resutlt" + JsonUtil.JSONTOString(custCreditLimitResponse));
		} catch (Exception e) {
			logger4j.info("queryCustCreditLimit Excepion："+e.getMessage());
			return new CommonVO(false, "copo服务异常");
		}
		return new CommonVO(true, "", custCreditLimitResponse);
	}
	
	 /**
	  * Description : pad评分 
	  * @author yangdc
	  * @version 1.01
	  * @see N/A
	  * @param request 
	  * @param response
	  * @return map
	  * @throws BusinessException
	  * @exception BusinessException
	 * @throws IOException 
	  */
	@RequestMapping("/ratingScaler")
	@ResponseBody
	public CommonVO ratingScaler(@RequestBody RatingScalerRequest ratingScalerRequest, HttpServletRequest request,HttpServletResponse response){
//		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		logger4j.info("RatingScaler " + JsonUtil.JSONTOString(ratingScalerRequest));
		RatingScalerResponse ratingScalerResponse = null;
		try {
			ratingScalerResponse = this.endpointService.ratingScaler(ratingScalerRequest);
			logger4j.info("RatingScaler resutlt" + JsonUtil.JSONTOString(ratingScalerResponse));
		} catch (Exception e) {
			logger4j.info("save PAD score Excepion："+e.getMessage());
			return new CommonVO(false, "copo服务异常");
		}
		return new CommonVO(true, "", ratingScalerResponse);
	}
	/**
	 * Description : 预审批客户查询接口(RP、老客户) 
	 * @author xu
	 * @version 1.01
	 * @see N/A
	 * @param request 
	 * @param response
	 * @return map
	 * @throws BusinessException
	 * @exception BusinessException
	 * @throws IOException 
	 */
	@RequestMapping("/CustQuery")
	@ResponseBody
	public CommonVO CustQuery(@RequestBody CustomerInquiresRequest customerInquiresRequest, HttpServletRequest request,HttpServletResponse response){
//		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		logger4j.info("CustQuery " + JsonUtil.JSONTOString(customerInquiresRequest));
		CustomerInquiresResponse CustQuery = null;
		try {
			CustQuery = this.endpointService.qryCustQuery(customerInquiresRequest);
			logger4j.info("CustQuery resutlt" + JsonUtil.JSONTOString(CustQuery));
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonVO(false, "copo服务异常");
		}
		return new CommonVO(true, "", CustQuery);
	}
	/**
	  * Description : 补录列表查询接口 
	  * @author xu
	  * @version 1.01
	  * @see N/A
	  * @param request 
	  * @param response
	  * @return map
	  * @throws BusinessException
	  * @exception BusinessException
	 * @throws IOException 
	  */
	@RequestMapping("/AppProgress")
	@ResponseBody
	public CommonVO AppProgress(@RequestBody AppProgressRequest appProgressRequest,  HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		logger4j.info("AppProgress " + JsonUtil.JSONTOString(appProgressRequest));
		AppProgressResponse appProgressResponse;
		try {
			appProgressResponse = this.endpointService.qryAppProgress(appProgressRequest);
			logger4j.info("AppProgress result" + JsonUtil.JSONTOString(appProgressResponse));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new CommonVO(false, "copo服务异常");
		}
		return 	 new CommonVO(true, "", appProgressResponse);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/updateUploadStatus")
	@ResponseBody
	public String UpdateUploadStatus( HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		try {
			byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
			String reqString = new String(reqByte);
			JSONObject reqJson = null;
			if(StringUtils.isNotBlank(reqString)){
				reqJson = JSONObject.fromObject(reqString);
			}
			logger4j.info("AuthNumber:"+reqJson.getString("AuthNumber")+",UploadStatus:"+reqJson.getString("UploadStatus"));
			UploadStatusResponse uploadStatusResponse = this.endpointService.updateUploadStatus(reqJson.getString("AuthNumber"),reqJson.getString("UploadStatus"));
			logger4j.info("uploadStatusResponse errorCode:"+uploadStatusResponse.getErrorCode());
		} catch (Exception e) {
			logger4j.info("get uploadStatusResponse exception:"+e);
			return "faild";
		}
		
		
		return "success";
	}
	
	/**
	  * Description : 补录提交接口 
	  * @author xu
	  * @version 1.01
	  * @see N/A
	  * @param request 
	  * @param response
	  * @return map
	  * @throws BusinessException
	  * @exception BusinessException
	 * @throws IOException 
	  */
	@RequestMapping("/SubmitApp")
	@ResponseBody
	public CommonVO SubmitApp(@RequestBody SubmitApplicationRequest submitApplicationRequest, HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		logger4j.info("PUWHWINLOGSUBMITAPP " + JsonUtil.JSONTOString(submitApplicationRequest) + "PUWHWINLOGSUBMITAPP 返回时间" +DateUtil.getDateYYYMMDD());
		logger4j.info("PUWHWINLOGSUBMITAPP" +"存款账户"+submitApplicationRequest.getAppInfoDto().getTrustAccountNumber() + "开户行" +submitApplicationRequest.getAppInfoDto().getTrustAccountBranchName()+ "户名" + submitApplicationRequest.getAppInfoDto().getTrustAccountName());
		
		SubmitApplicationResponse submitApplicationResponse;
		try {
			submitApplicationResponse = this.endpointService.qrySubmitApp(submitApplicationRequest);
			logger4j.info("PUWHWINLOGSUBMITAPP result" + JsonUtil.JSONTOString(submitApplicationResponse) + "PUWHWINLOGSUBMITAPP 返回时间" +DateUtil.getDateYYYMMDD() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new CommonVO(false, "copo服务异常");
		}
		
		return new CommonVO(true, "", submitApplicationResponse);
	}
	/**
	  * Description : SMS接口 
	  * @author xu
	  * @version 1.01
	  * @see N/A
	  * @param request 
	  * @param response
	  * @return map
	  * @throws BusinessException
	  * @exception BusinessException
	 * @throws IOException 
	  *///SmsServiceRequest smsServiceRequest
	@RequestMapping("/SMS")
	@ResponseBody
	public CommonVO Sms(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = null;
		try {
			reqByte = StreamUtils.getBytes(request.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonVO(false, "copo服务异常");
		}
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		SmsServiceRequest smsServiceRequest = new SmsServiceRequest();
		smsServiceRequest.setToken(reqJson.getString("token"));
		smsServiceRequest.setLanguageType(reqJson.getString("languageType"));
		smsServiceRequest.setOrgCode(reqJson.getString("orgCode"));
		smsServiceRequest.setSmsType(reqJson.getString("smsType"));
		smsServiceRequest.setUserId(reqJson.getString("userId"));
		smsServiceRequest.setApplicationId(reqJson.getString("applicationId"));
		//smsSendType 自加字段   1  诚意贷  2  购易贷  3  普通短信内容
		 int smsSendType = Integer.parseInt(reqJson.getString("smsSendType")) ;
	//	String msg = "尊敬的客户，感谢您申请我行的购易贷/诚易贷业务，请将动态密码" +reqJson.getString("msg")+ "输入智能终端设备提醒您关注信息安全，勿将本人资料他人.";
		String number = reqJson.getString("msg");
		String mgdformart = "";
		String mgdformartres = "";
		String sms_identifiy = "";
		if(reqJson.optString("bankFlag")==null || reqJson.optString("bankFlag")==""){
			reqJson.put("bankFlag", "NJCB");
		}
		if("NJCB".equals(reqJson.optString("bankFlag"))){
			sms_identifiy=identifiy;
		}else if("KSLC".equals(reqJson.optString("bankFlag"))){
			sms_identifiy=ks_identifiy;
		}else if("RZCB".equals(reqJson.optString("bankFlag"))){
			sms_identifiy=rz_identifiy;
		}
		
		switch (smsSendType) {
		case 1:
			mgdformart = sms_identifiy.replace("555555555",number);
//			 mgdformartres = mgdformart.replace("66666666","诚易贷/随鑫贷");
			 if("NJCB".equals(reqJson.optString("bankFlag"))){
				 mgdformartres = mgdformart.replace("66666666","诚易贷/随鑫贷");
				}else if("KSLC".equals(reqJson.optString("bankFlag"))){
					mgdformartres = mgdformart.replace("66666666","鹿城贷");
				}else if("RZCB".equals(reqJson.optString("bankFlag"))){
					mgdformartres = mgdformart.replace("66666666","阳光贷");
				}
			smsServiceRequest.setMsg(mgdformartres);
			break;
		case 2:  
			mgdformart = sms_identifiy.replace("555555555",number);
//			 mgdformartres = mgdformart.replace("66666666","购易贷");
			 if("NJCB".equals(reqJson.optString("bankFlag"))){
				 mgdformartres = mgdformart.replace("66666666","购易贷");
				}else if("KSLC".equals(reqJson.optString("bankFlag"))){
					mgdformartres = mgdformart.replace("66666666","鹿城通");
				}else if("RZCB".equals(reqJson.optString("bankFlag"))){
					mgdformartres = mgdformart.replace("66666666","阳光贷");
				}
			 smsServiceRequest.setMsg(mgdformartres);
			break;
		default:
			smsServiceRequest.setMsg(reqJson.getString("msgcontent"));
			break;
		} 
		smsServiceRequest.setMobile(reqJson.getString("mobile"));
		logger4j.info("send Sms resquest"+JsonUtil.JSONTOString(smsServiceRequest));
		SmsServiceResponse Sms;
		try {
			Sms = this.endpointService.Sms(smsServiceRequest);
		} catch (IOException e) {
			e.printStackTrace();
			return new CommonVO(false, "copo服务异常");
		}
		logger4j.info("send Sms result "+JsonUtil.JSONTOString(smsServiceRequest));
		return new CommonVO(true, "",Sms);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/padLogin")
	@ResponseBody
	public CommonVO PadLogin(@RequestBody Puwshlogin puwshlogin, HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		CommonVO commonVO = new CommonVO();
		Map<String, Object> Sms = this.endpointService.padLogin(puwshlogin);
		 commonVO = new CommonVO(true,Sms, null);
		return commonVO;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/parameter")
	@ResponseBody
	public CommonVO Parameter(HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		//response.setHeader("Access-Control-Allow-Origin,*", request.getHeader("Access-Control-Allow-Origin"));
		response.setHeader("Access-Control-Allow-Origin","*");
		//response.addHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS");
		//response.addHeader("Access-Control-Allow-Hearders","Orgin,No-Cache,X-Control,Expires,Content-Type,X-E4M-ith");
		//response.addHeader("Access-Control-Allow-Origin","*");
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		/*String usercode = reqJson.optString("usercode");
		String orgCode = reqJson.optString("orgCode");
		String bei = reqJson.optString("bei");
		Map<String, Object> dictMap = new HashMap<String, Object>();
		Map<String,Object> dicts = this.endpointService.Parameter(reqJson == null ? "" : reqJson.optString("lastUpdateTime"),usercode, orgCode, bei);*/
		Map<String, Object> dictMap = new HashMap<String, Object>();
		if(reqJson.optString("bankFlag")==null || reqJson.optString("bankFlag")==""){
			reqJson.put("bankFlag", "NJCB");
		}
		Map<String,Object> dicts = this.endpointService.Parameter(reqJson);
	
		dictMap.put("dict", dicts);
		dictMap.put("lastUpdateTime", DateUtil.format(new Date()));
		return 	new CommonVO(true, dicts,null);
	}
	
	/**
	 * 申请取消接口
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/returnofgoods")
	@ResponseBody
	public CommonVO ReturnOfGoods(@RequestBody ReturnOfGoodsRequest  returnOfGoodsRequest ,HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		logger4j.info("returnofgoods" + JsonUtil.JSONTOString(returnOfGoodsRequest) );
		 ReturnOfGoodsResponse returnOfGoodsResponse;
		try {
			returnOfGoodsResponse = this.endpointService.ReturnOfGoods(returnOfGoodsRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonVO(false, "copo服务异常");
		}
		 logger4j.info("returnofgoods result" + JsonUtil.JSONTOString(returnOfGoodsResponse) );
		return 	new CommonVO(true, "",returnOfGoodsResponse);
	}
	
	/**
	 *  补录查询用户资料
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/loadApp")
	@ResponseBody
	public CommonVO loadApp(@RequestBody LoadApplicationRequest  loadApplicationRequest ,HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		
		logger4j.info("loadApp  " + JsonUtil.JSONTOString(loadApplicationRequest) );
		LoadApplicationResponse loadApplicationResponse;
		try {
			loadApplicationResponse = this.endpointService.loadApp(loadApplicationRequest);
			/*chenji添加 2017/03/17   去除特殊字符"'"*/
			if("'".equals(loadApplicationResponse.getAppInfoDto().getTrustAccountNumber())){
				loadApplicationResponse.getAppInfoDto().setTrustAccountNumber("");
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new CommonVO(false, "copo服务异常");
		}
		
		logger4j.info("loadApp  result" + JsonUtil.JSONTOString(loadApplicationResponse) );
		
		return 	new CommonVO(true, "",loadApplicationResponse);
	}
	
	
	/**
	 * 營銷試算
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/paymentHandler")
	@ResponseBody
	//TODO
	public CommonVO PaymentHandlerService(@RequestBody PaymentCalForServiceRequest  paymentCalForServiceRequest ,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		logger4j.info("paymentHandler" + JsonUtil.JSONTOString(paymentCalForServiceRequest));

		PaymentCalResponse paymentCalResponse;
		try {
			paymentCalResponse = this.endpointService.paymentCal(paymentCalForServiceRequest);
			logger4j.info("paymentHandler" + JsonUtil.JSONTOString(paymentCalResponse));
		} catch (Exception e) {
			e.printStackTrace();
			return 	new CommonVO(true,"copo服务异常");
		}
		return 	new CommonVO(true,"",paymentCalResponse);
	}
	
	/**
	 * 团办单位
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/preScrSubmitApp")
	@ResponseBody
	public CommonVO preScrSubmitApp(@RequestBody com.pushwin.PreScrSubmitApp.SubmitApplicationRequest  submitApplicationRequestone ,HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		logger4j.info("preScrSubmitApp" + JsonUtil.JSONTOString(submitApplicationRequestone) + "preScrSubmitApp 请求时间" +DateUtil.getDateYYYMMDD());

		com.pushwin.PreScrSubmitApp.SubmitApplicationResponse paymentCalResponse = null;
		try {
			paymentCalResponse = this.endpointService.PreScrSubmitApp(submitApplicationRequestone);
			
			logger4j.info("preScrSubmitApp" + JsonUtil.JSONTOString(paymentCalResponse)+ "preScrSubmitApp 返回时间" +DateUtil.getDateYYYMMDD())  ;
		} catch (Exception e) {
			e.printStackTrace();
			return 	new CommonVO(true,"copo服务异常");
		}
		
		return 	new CommonVO(true,"",paymentCalResponse);
	}
	
	/**
	 * 預約客戶接口
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/telesalesCustInfoQuery")
	@ResponseBody
	public CommonVO telesalesCustInfoQuery(@RequestBody  TelesalesCustInfoQueryRequest telesalesCustInfoQueryRequest ,HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		logger4j.info("telesalesCustInfoQuery" + JsonUtil.JSONTOString(telesalesCustInfoQueryRequest));

		TelesalesCustInfoQueryResponse telesalesCustInfoQueryResponse;
		try {
			telesalesCustInfoQueryResponse = this.endpointService.telesalesCustInfoQuery(telesalesCustInfoQueryRequest);
			
			logger4j.info("telesalesCustInfoQuery Result" + JsonUtil.JSONTOString(telesalesCustInfoQueryResponse));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 	new CommonVO(true,"copo服务异常");
		}
		
		return 	new CommonVO(true, "",telesalesCustInfoQueryResponse);
	}
	
	/**
	 * 补件完成接口
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/cfcUpdateAppState")
	@ResponseBody
	public CommonVO cfcUpdateAppState(@RequestBody  com.pushwin.CfcUpdateAppState.UpdateApplicationRequest updateAppStateRequest ,HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		logger4j.info("cfcUpdateAppState" + JsonUtil.JSONTOString(updateAppStateRequest));

		com.pushwin.CfcUpdateAppState.UpdateApplicationResponse updateAppStateResponse;
		try {
			updateAppStateResponse = this.endpointService.cfcUpdateAppState(updateAppStateRequest);
			
			logger4j.info("cfcUpdateAppState Result" + JsonUtil.JSONTOString(updateAppStateResponse));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 	new CommonVO(true,"copo服务异常");
		}
		
		return 	new CommonVO(true, "",updateAppStateResponse);
	}
	
	/**
	 * 补件完成接口
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/loginCheckService")
	@ResponseBody
	public String LoginCheckService(@RequestBody  com.pushwin.newpassword.UpdatePasswordRequest loginRequest ,HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		logger4j.info("loginCheckService" + JsonUtil.JSONTOString(loginRequest));
		this.endpointService.loginCheckService(loginRequest);
		
		//logger4j.info("loginCheckService Result" + JsonUtil.JSONTOString(updatePasswordResponse));
		String ss = "OK" ;
		return 	ss;
	}
	
	//验证码修改密码
	@RequestMapping("/loginCheckService2")
	@ResponseBody
	public String LoginCheckService2(@RequestBody  com.pushwin.newpassword.UpdatePasswordRequest loginRequest ,HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		logger4j.info("loginCheckService2" + JsonUtil.JSONTOString(loginRequest));
		this.endpointService.loginCheckService2(loginRequest);
		
		//logger4j.info("loginCheckService Result" + JsonUtil.JSONTOString(updatePasswordResponse));
		String ss = "OK" ;
		return 	ss;
	}
	/**
	 * 短信修改密码
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/smsUpdatePw")
	@ResponseBody
	public CommonVO smsUpdatePw(HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		try {
			byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
			String reqString = new String(reqByte);
			JSONObject reqJson = null;
			if(StringUtils.isNotBlank(reqString)){
				reqJson = JSONObject.fromObject(reqString);
			}
			String pinId=reqJson.optString("pin");
			String mobile=reqJson.optString("mobile");
			String userCode=reqJson.optString("userId");
			//根据userCode 查询 userId
			String userId=this.endpointService.qryUserIdByUserCode(userCode);
			if("用户不存在".equals(userId)){
				return new CommonVO(false, "用户不存在");
			}
			
			//查询手机号和工号是否匹配
			String mobile2=this.endpointService.qryMobile(userCode);
			if(!mobile.equals(mobile2)){
				return new CommonVO(false, "请输入工号对应的手机号码");
			}
			
			//device 表查询pin是否绑定在userCode下
			boolean flag2=this.endpointService.qrydevice(userId,pinId);
			if(!flag2){
				return new CommonVO(false, "您没有权限在此设备上操作");
			}
			
			//发送短信验证码
			SmsServiceRequest smsServiceRequest = new SmsServiceRequest();
			smsServiceRequest.setToken(reqJson.getString("token"));
			smsServiceRequest.setLanguageType(reqJson.getString("languageType"));
			smsServiceRequest.setOrgCode(reqJson.getString("orgCode"));
			smsServiceRequest.setSmsType(reqJson.getString("smsType"));
			smsServiceRequest.setUserId(reqJson.getString("userId"));
			smsServiceRequest.setApplicationId(reqJson.getString("applicationId"));
			
			smsServiceRequest.setMsg(reqJson.getString("msgcontent"));
			smsServiceRequest.setMobile(reqJson.getString("mobile"));
			SmsServiceResponse Sms;
			logger4j.info("send upPwSms request: "+reqJson);
			try {
				Sms = this.endpointService.Sms(smsServiceRequest);
			} catch (IOException e) {
				logger4j.info("smsUpdatePw exception:"+e.getMessage());
				return new CommonVO(false, "copo服务异常");
			}
			
			logger4j.info("send upPwSms result: "+JsonUtil.JSONTOString(Sms));
			return new CommonVO(true, "",Sms);
			
		} catch (Exception e) {
			logger4j.info("pushwin smsUpdatePw exception:"+e.getMessage());
			return new CommonVO(false, "copo服务异常");
		}

				
		
	}
	
	/**
	 * 申请取消接口
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/documentPic")
	@ResponseBody
	public CommonVO PicInfo(HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}	
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		String urn = reqJson.optString("urn");
		String type = reqJson.optString("type");
		String curIndex = reqJson.optString("curIndex");
		OutputStream os = null;
		logger4j.info("picture" + JsonUtil.JSONTOString(reqJson));
		try {
			//http://192.168.1.10:8080/document/public/photo.do?urn=963852&&type= photo&&curIndex=0
			URL url = new URL(picurl+"/document/public/photo.do?urn="+urn+"&&type="+type+"&&curIndex="+curIndex);
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(30 * 1000);
			conn.setReadTimeout(30 * 1000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
/*			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("wms-url", "http://jerry-PC:9009/mobilebank/controller/app/sv");
			conn.setRequestProperty("method",method);
			conn.setRequestProperty("wms-cipher","3des;ffffffff-c472-80e6-ffff-ffffc47280e6");
			conn.setRequestProperty("wms-compress","gzip");*/
			os = conn.getOutputStream();			
			/*byte[] bs=submitcontent.getBytes("UTF-8");
			os.write(bs);*/
			if (conn.getResponseCode() != 200) {
				System.out.println(conn.getResponseCode());
			} else {
				System.out.println(conn.getResponseCode()); 
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (os!=null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		return 	new CommonVO(true,os, null);
	}
	
	
	/**
	 * 补件完成接口
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/activityMessage")
	@ResponseBody
	public CommonVO activityMessage(HttpServletRequest request,HttpServletResponse response) throws BusinessException, IOException{
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		String reqString = new String(reqByte);
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		logger4j.info("activityMessage" + reqJson.toString());
		String orgcode = reqJson.optString("orgcode");
		String upateTime  = reqJson.optString("upateTime");
		List<ActivityResponseVO> result = null;
		try {
			result = endpointService.qryActivitys(orgcode,upateTime);
		} catch (ParseException e) {
			return 	new CommonVO(false,"更新时间解析出错");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Map<String, String> qryTimeMap = new HashMap<String, String>();
//		qryTimeMap.put("qryTime", DateUtil.format(new Date()));
		CommonVO commonVO = new CommonVO(true, result, null);
		commonVO.setTotal(DateUtil.format(new Date()));
		return 	commonVO;
	}
	
	
	/**
	 * 补件完成接口
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/downipa")
	@ResponseBody
	public boolean downIosIpa(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		boolean flag = false;
		OutputStream outputStream = null;
		try {
			String filename   = SpringProperty.props.getProperty("RootPath") + "/ipa/"+"NanjingBank.ipa";
			 // response  = ServletActionContext.getResponse();
			String uploadName = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			byte[] data = FileUtils.readFileToByteArray(new File(uploadName));
		    String fileName = filename.substring(filename.lastIndexOf('\\') + 1);
		    response.reset();
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		    response.addHeader("Content-Length", "" + data.length);
		    response.setContentType("application/octet-stream;charset=UTF-8");
		    outputStream = new BufferedOutputStream(response.getOutputStream());
		    outputStream.write(data);
		    flag = true;
		} catch (Exception e) {
			flag = false;
		}finally{
			try {
				outputStream.flush();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    return flag;
	}
	
	/**
	 * 补件完成接口
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/downzip")
	@ResponseBody
	public boolean downIoszip(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		byte[] reqByte = null;
		try {
			reqByte = StreamUtils.getBytes(request.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String reqString = new String(reqByte);
		String filepath =request.getParameter("filepath");
		JSONObject reqJson = null;
		if(StringUtils.isNotBlank(reqString)){
			reqJson = JSONObject.fromObject(reqString);
		}
		boolean flag = false;
		OutputStream outputStream = null;
		try {
			//String filename   = SpringProperty.props.getProperty("RootPath") + "/" +reqJson.optString("filepath");
			String filename   = SpringProperty.props.getProperty("RootPath") + "/" +filepath;
			 // response  = ServletActionContext.getResponse();
			String uploadName = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			byte[] data = FileUtils.readFileToByteArray(new File(uploadName));
		    String fileName = filename.substring(filename.lastIndexOf('\\') + 1);
		    response.reset();
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		    response.addHeader("Content-Length", "" + data.length);
		    response.setContentType("application/octet-stream;charset=UTF-8");
		    outputStream = new BufferedOutputStream(response.getOutputStream());
		    outputStream.write(data);
		    flag = true;
		} catch (Exception e) {
			flag = false;
		}finally{
			try {
				outputStream.flush();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    return flag;
	}
	/**
	 * 补件完成接口
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/iosversion")
	@ResponseBody
	public String iosversion(@RequestBody ReqVersionVO reqVersionVO,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin:*", request.getHeader("Access-Control-Allow-Origin"));
		FileInputStream tempFileInputStream = null;
		String tempDesc = "";
		logger4j.info("JIAN CE  IOS VERSDION:"+reqVersionVO.getBankFlag());
		try {
//			String filename="";
			if(reqVersionVO.getBankFlag()==null || reqVersionVO.getBankFlag()==""){
				reqVersionVO.setBankFlag("NJCB");
			}
			/*if( "NJCB".equals(reqVersionVO.getBankFlag()) ){
				filename   = SpringProperty.props.getProperty("plistpath") + "/" +"manifest.plist";
			}else if( "KSLC".equals(reqVersionVO.getBankFlag()) ){
				filename   = SpringProperty.props.getProperty("kslc_plistpath") + "/" +"manifest.plist";
			}else if( "RZCB".equals(reqVersionVO.getBankFlag()) ){
				filename   = SpringProperty.props.getProperty("rzcb_plistpath") + "/" +"manifest.plist";
			}
			 // response  = ServletActionContext.getResponse();
			 //tempDesc = new String(StreamUtils.getBytes(tempFileInputStream), "gbk");
			 tempFileInputStream = new FileInputStream(filename);
			 tempDesc = new String(StreamUtils.getBytes(tempFileInputStream), "gbk");
			 int l = tempDesc.indexOf("<key>bundle-version</key>");
			 int k = tempDesc.lastIndexOf("<key>kind</key>");
			 String strversion = tempDesc.substring(l,k).trim();
			 String versions1 = strversion.replace("<key>bundle-version</key>", "").trim();
			 int ll = versions1.indexOf(">")+1;
			 int l2 = versions1.lastIndexOf("<");
			 String versions = versions1.substring(ll,l2).trim();
			logger4j.info("JIAN CE  IOS VERSDION  WANBI" + versions);*/
			
			String iosVersion=this.endpointService.iosVersion(reqVersionVO.getBankFlag());
			logger4j.info("JIAN CE  IOS VERSDION:" + iosVersion);
			 return iosVersion;
		} catch (Exception e) {
			logger4j.info("JIAN CE  IOS VERSDION ERROR:" + e.getMessage());
			return "版本比对出错";
		}/*finally{
			try {
				tempFileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	}
	public static void main(String[] args) {
		String msg = "请将动态密码尊敬的客户，感谢您申请我行的购易贷/诚易贷业务，请将动态密码555555555输入智能终端设备。提醒您关注信息安全，勿将本人资料泄露他人。输入智能终端设备。提醒您关注信息安全，勿将本人资料泄露他人。" ; 
		String mgdformart = msg.replace("555555555","sssss");
		ReturnOfGoodsRequest res = new ReturnOfGoodsRequest();
		JSONObject objec = JSONObject.fromObject(res);
		
		LoadApplicationRequest  loadApplicationRequest = new LoadApplicationRequest();//  loadApplicationRequest
		System.out.println(objec.toString());
		JSONObject loadApplicationRequests = JSONObject.fromObject(loadApplicationRequest);
		System.out.println(loadApplicationRequests.toString());
		
		SubmitApplicationRequest res2= new SubmitApplicationRequest();
		AppInfoDto dto  =new AppInfoDto();
		dto.setCust2CustFutureField8("ssssssssss");
		res2.setAppInfoDto(dto);
		JSONObject loadApplicationRequestss1 = JSONObject.fromObject(dto);
		JSONObject loadApplicationRequestss = JSONObject.fromObject(res);
		System.out.println("-----------"+loadApplicationRequestss1.toString());
		TelesalesCustInfoQueryRequest telesalesCustInfoQueryRequest = new TelesalesCustInfoQueryRequest();
		AppCustInfoQueryDto appCustInfoQueryDto = new AppCustInfoQueryDto();
		appCustInfoQueryDto.setQuerySize(1);
		telesalesCustInfoQueryRequest.setAppCustInfoQueryDto(appCustInfoQueryDto);
		JSONObject telesalesCustInfoQueryRequestjs = JSONObject.fromObject(telesalesCustInfoQueryRequest);
		System.out.println("-----------"+telesalesCustInfoQueryRequestjs.toString());
		PaymentCalForServiceRequest paymentCalForServiceRequest = new PaymentCalForServiceRequest();
		
		JSONObject  paymentCalForServiceRequestjs = JSONObject.fromObject(paymentCalForServiceRequest);
		System.out.println("----------- pay ment"+paymentCalForServiceRequestjs.toString());
		
		com.pushwin.PreScrSubmitApp.SubmitApplicationRequest SubmitApplicationRequest2 = new 		com.pushwin.PreScrSubmitApp.SubmitApplicationRequest();
		com.pushwin.PreScrSubmitApp.AppInfoDto  appto = new com.pushwin.PreScrSubmitApp.AppInfoDto();
		SubmitApplicationRequest2.setAppInfoDto(appto);
		JSONObject  SubmitApplicationRequest3 = JSONObject.fromObject(SubmitApplicationRequest2);
		System.out.println("----------- "+SubmitApplicationRequest3.toString());
		
		//com.pushwin.CfcUpdateAppState.UpdateApplicationRequest updateAppStateRequest = new 		com.pushwin.CfcUpdateAppState.UpdateAppStateRequest();
		com.pushwin.CfcUpdateAppState.UpdateApplicationRequest  updateApplicationRequest = new com.pushwin.CfcUpdateAppState.UpdateApplicationRequest();
		updateApplicationRequest.setBarcode("301");
		JSONObject  updateAppStateRequeststring = JSONObject.fromObject(updateApplicationRequest);
		System.out.println("-----------cccc"+updateAppStateRequeststring.toString());
		
		com.pushwin.newpassword.UpdatePasswordRequest updatePasswordRequest = new 		com.pushwin.newpassword.UpdatePasswordRequest();
		//com.pushwin.CfcUpdateAppState.UpdateApplicationRequest  updateApplicationRequest = new com.pushwin.CfcUpdateAppState.UpdateApplicationRequest();
		updatePasswordRequest.setNewPassword("abc123");
		JSONObject  updatePasswordjs = JSONObject.fromObject(updatePasswordRequest);
		System.out.println("-----------"+updatePasswordjs.toString());
		System.out.println(mgdformart);
		
		System.out.println(MD5Util.getMD5String("abc123"));
	}
	
	
}

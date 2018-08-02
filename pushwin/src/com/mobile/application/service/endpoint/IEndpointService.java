package com.mobile.application.service.endpoint;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.mobile.application.entity.Puwshlogin;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.activity.ActivityResponseVO;
import com.mobile.application.vo.endpoint.ReqAddressVO;
import com.mobile.application.vo.endpoint.ReqApkVO;
import com.mobile.application.vo.endpoint.ReqCrmLoginVO;
import com.mobile.application.vo.endpoint.ReqLoginVO;
import com.pushwin.payment.PaymentCalForServiceRequest;
import com.pushwin.payment.PaymentCalResponse;
import com.yuchengtech.bcrm.mobile.webservice.ResponseToken;

public interface IEndpointService {

	CommonVO login(HttpServletResponse response, ReqLoginVO endpointLoginVO) throws BusinessException;
	ResponseToken crmLogin(String userId) throws BusinessException, MalformedURLException;

	/**
	 * Description : 终端获取apk列表，下载更新图片
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param N/A
	 * @return 查询结果集
	 * @exception BusinessException
	 */
	CommonVO qryApks(ReqApkVO reqApkVO) throws BusinessException;
	
	/**
	 * Description : 跨行校验
	 * @author ydc
	 * @version 1.01
	 * @see N/A
	 * @param 行名
	 * @return 行号
	 * @exception BusinessException
	 */
	String qryBankAcc(String bankCode) throws BusinessException;

	 /**
	  * Description :  保存pad地址
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param response
	  * @param addressVO 
	  * @return
	  * @exception BusinessException
	  */
	CommonVO savePadAddress(HttpServletResponse response,ReqAddressVO addressVO);

	CustomerInquiresResponse qryCustQuery(CustomerInquiresRequest customerInquiresRequest) throws IOException;

	RatingScalerResponse ratingScaler(RatingScalerRequest ratingScalerRequest) throws IOException;

	CustCreditLimitResponse queryCustCreditLimit(CustCreditLimitRequest custCreditLimitRequest) throws IOException;

	AppProgressResponse qryAppProgress(AppProgressRequest appProgressRequest) throws IOException;

	SubmitApplicationResponse qrySubmitApp(SubmitApplicationRequest submitApplicationRequest)  throws IOException;
	UploadStatusResponse updateUploadStatus(String AuthNumber,String UploadStatus)  throws IOException;
	
	
	SmsServiceResponse Sms(SmsServiceRequest smsServiceRequest)  throws IOException;
	
	Map<String, Object> padLogin(Puwshlogin puwshlogin)  throws IOException;
	/**
	 * 
	 * @param parameter upddate
	 * @param usercode 
	 * @param orgCode
	 * @param bei
	 * @return
	 * @throws IOException
	 */
	Map<String,Object> ParameterNew(Object parameter,String usercode,String orgCode,String bei)  throws IOException;
	/**
	 * 
	 * @param parameter upddate
	 * @param bei
	 * @return
	 * @throws IOException
	 */
	String iosVersion(String bankFlag) throws IOException;
	String qryUserIdByUserCode(String userCode) throws IOException;
	String qryMobile(String userCode) throws IOException;
	boolean qrydevice(String userId,String pinId) throws IOException;
	Map<String,Object> Parameter(Object parameter) throws IOException;
	ReturnOfGoodsResponse ReturnOfGoods(ReturnOfGoodsRequest returnOfGoodsRequest)  throws IOException;
	
	LoadApplicationResponse loadApp(LoadApplicationRequest loadApplicationRequest)  throws IOException;

	PaymentCalResponse paymentCal(PaymentCalForServiceRequest paymentCalRequest);
	
	
	TelesalesCustInfoQueryResponse telesalesCustInfoQuery(TelesalesCustInfoQueryRequest  telesalesCustInfoQueryRequest);

	
	com.pushwin.PreScrSubmitApp.SubmitApplicationResponse  PreScrSubmitApp(com.pushwin.PreScrSubmitApp.SubmitApplicationRequest submitApplicationRequest);
	
	com.pushwin.CfcUpdateAppState.UpdateApplicationResponse cfcUpdateAppState(com.pushwin.CfcUpdateAppState.UpdateApplicationRequest updateAppStateRequest);
	
//	public com.pushwin.newpassword.UpdatePasswordResponse loginCheckService(com.pushwin.newpassword.UpdatePasswordRequest loginRequest) ;
	public void loginCheckService(com.pushwin.newpassword.UpdatePasswordRequest loginRequest) ;
	public void loginCheckService2(com.pushwin.newpassword.UpdatePasswordRequest loginRequest) ;
	/**
	 * queryActivityMessage by orgcode
	 * @param orgcode
	 * @param upateTime
	 * @return
	 * @throws ParseException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	List<ActivityResponseVO> qryActivitys(String orgcode, String upateTime)
			throws ParseException, IllegalAccessException, InvocationTargetException;
}

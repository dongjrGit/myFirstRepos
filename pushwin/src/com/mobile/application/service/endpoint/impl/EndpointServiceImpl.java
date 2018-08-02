package com.mobile.application.service.endpoint.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cetelem.corp.cache.request.HashRequest;
import com.cetelem.corp.cache.response.HashResponse;
import com.cetelem.corp.common.client.BasicBizServiceClient;
import com.cetelem.corp.common.client.BizServiceClient;
import com.cetelem.corp.ws.application.service.AppInfoDto;
import com.cetelem.corp.ws.application.service.AppProgressRequest;
import com.cetelem.corp.ws.application.service.AppProgressResponse;
import com.cetelem.corp.ws.application.service.ApplicationProgress;
import com.cetelem.corp.ws.application.service.ApplicationProgressService;
import com.cetelem.corp.ws.application.service.CustCreditLimitRequest;
import com.cetelem.corp.ws.application.service.CustCreditLimitResponse;
import com.cetelem.corp.ws.application.service.CustomerInquires;
import com.cetelem.corp.ws.application.service.CustomerInquiresRequest;
import com.cetelem.corp.ws.application.service.CustomerInquiresResponse;
import com.cetelem.corp.ws.application.service.CustomerInquiresService;
import com.cetelem.corp.ws.application.service.LoadApplication;
import com.cetelem.corp.ws.application.service.LoadApplicationRequest;
import com.cetelem.corp.ws.application.service.LoadApplicationResponse;
import com.cetelem.corp.ws.application.service.LoadApplicationService;
import com.cetelem.corp.ws.application.service.QueryCustCreditLimit;
import com.cetelem.corp.ws.application.service.QueryCustCreditLimitService;
import com.cetelem.corp.ws.application.service.RatingScalerRequest;
import com.cetelem.corp.ws.application.service.RatingScalerResponse;
import com.cetelem.corp.ws.application.service.RatingScalerService;
import com.cetelem.corp.ws.application.service.RatingScalerServiceService;
import com.cetelem.corp.ws.application.service.ReturnOfGoods;
import com.cetelem.corp.ws.application.service.ReturnOfGoodsRequest;
import com.cetelem.corp.ws.application.service.ReturnOfGoodsResponse;
import com.cetelem.corp.ws.application.service.ReturnOfGoodsService;
import com.cetelem.corp.ws.application.service.SmsService;
import com.cetelem.corp.ws.application.service.SmsServiceRequest;
import com.cetelem.corp.ws.application.service.SmsServiceResponse;
import com.cetelem.corp.ws.application.service.SmsServiceService;
import com.cetelem.corp.ws.application.service.SubmitApplication;
import com.cetelem.corp.ws.application.service.SubmitApplicationRequest;
import com.cetelem.corp.ws.application.service.SubmitApplicationResponse;
import com.cetelem.corp.ws.application.service.SubmitApplicationService;
import com.cetelem.corp.ws.application.service.TelesalesCustInfoQueryRequest;
import com.cetelem.corp.ws.application.service.TelesalesCustInfoQueryResponse;
import com.cetelem.corp.ws.application.service.TelesalesCustInfoQueryService;
import com.cetelem.corp.ws.application.service.TelesalesCustInfoQueryServiceService;
import com.cetelem.corp.ws.application.service.UpdateUploadStatusService;
import com.cetelem.corp.ws.application.service.UpdateUploadStatusServiceService;
import com.cetelem.corp.ws.application.service.UploadStatusRequest;
import com.cetelem.corp.ws.application.service.UploadStatusResponse;
import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.tools.MD5Util;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.controller.endpoint.EndpointController;
import com.mobile.application.dao.IEndpointDao;
import com.mobile.application.entity.Paduser;
import com.mobile.application.entity.Puwshlogin;
import com.mobile.application.entity.TBasisActivity;
import com.mobile.application.entity.TBasisApk;
import com.mobile.application.entity.TBasisDevice;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisPosition;
import com.mobile.application.entity.TBasisRole;
import com.mobile.application.entity.TBasisRoleApk;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisUserRole;
import com.mobile.application.service.endpoint.IEndpointService;
import com.mobile.application.until.JsonUtil;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.activity.ActivityResponseVO;
import com.mobile.application.vo.endpoint.ReqAddressVO;
import com.mobile.application.vo.endpoint.ReqApkVO;
import com.mobile.application.vo.endpoint.ReqCrmLoginVO;
import com.mobile.application.vo.endpoint.ReqLoginVO;
import com.mobile.application.vo.endpoint.ResApkVO;
import com.mobile.application.vo.endpoint.ResLoginVO;
import com.mobile.application.vo.endpoint.WrapResApkVO;
import com.pushwin.payment.AppException_Exception;
import com.pushwin.payment.PaymentCalForServiceRequest;
import com.pushwin.payment.PaymentCalResponse;
import com.pushwin.payment.PaymentHandlerService;
import com.pushwin.payment.PaymentHandlerServiceService;
import com.yuchengtech.bcrm.mobile.webservice.ResponseToken;
import com.yuchengtech.bcrm.mobile.webservice.VerifyUser;
import com.yuchengtech.bcrm.mobile.webservice.VerifyUserService;

@Service
public class EndpointServiceImpl implements IEndpointService{
	
	//private static Logger logger4j = Logger.getLogger(EndpointController.class);
	
	@Autowired
	IEndpointDao endpointDao;
	@Autowired
	ServletContext servletContext;
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String iosVersion(String bankFlag){
		String iosVersion=  this.endpointDao.iosVersion(bankFlag);
		return iosVersion;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String qryUserIdByUserCode(String userCode){
		//根据员工号查询用户信息
		List<TBasisUser> tBasisUsers =  (List<TBasisUser>) endpointDao.get(TBasisUser.class, "userCode", userCode);
		if(null == tBasisUsers || tBasisUsers.size() == 0){
			return "用户不存在";
		}
		
		String userId=  tBasisUsers.get(0).getUserId();
		return userId;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String qryMobile(String userCode){
		//根据userCode 查询 手机号
		List<TBasisUser> tBasisUsers =  (List<TBasisUser>) endpointDao.get(TBasisUser.class, "userCode", userCode);
		String mobile=  tBasisUsers.get(0).getUserPhone();
		return mobile;
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean qrydevice(String userId,String pinId){
		//根据userId 和 pin 查询设备信息
		boolean flag =  this.endpointDao.qrydevice(userId, pinId);
		return flag;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public CommonVO login(HttpServletResponse response, ReqLoginVO endpointLoginVO) throws BusinessException {
		
		//根据员工号查询用户信息
		List<TBasisUser> tBasisUsers =  (List<TBasisUser>) endpointDao.get(TBasisUser.class, "userCode", endpointLoginVO.getUserCode());
		if(null == tBasisUsers || tBasisUsers.size() == 0){
			return new CommonVO(false, "用户不存在");
		}
		TBasisUser tBasisUser = tBasisUsers.get(0);
		if(tBasisUser==null||"".equals(tBasisUser)){
			return new CommonVO(false, "用户不存在");
		}
		
		String bankFlagString=endpointLoginVO.getBankFlag();
		if(tBasisUser==null|| !bankFlagString.equals(tBasisUser.getUserbeizhu1())){
			return new CommonVO(false, "非本行用户，不能登陆！");
		}

		//配置文件中读取最大登陆失败次数
		int loginErrorTimes = Integer.parseInt(SpringProperty.props.getProperty("LoginErrorTimes"));
		//当前密码错误次数
		int nowErrorTimes = Integer.parseInt(null == tBasisUser.getNoLogin() ? "0" : tBasisUser.getNoLogin());
		
		if(nowErrorTimes >= loginErrorTimes){
			return new CommonVO(false, "账号已锁定，请联系管理员解除锁定。");
		}
		
		if(!endpointLoginVO.getUserPwd().equals(tBasisUser.getUserPwd())) {
			//当前错误次数+1
			nowErrorTimes ++;
			tBasisUser.setNoLogin(String.valueOf(nowErrorTimes));
			endpointDao.update(tBasisUser);
			if(nowErrorTimes >= loginErrorTimes){
				tBasisUser.setNoLogin(String.valueOf(nowErrorTimes));
				endpointDao.update(tBasisUser);
				return new CommonVO(false, "账号已锁定，请联系管理员解除锁定。");
			} else {
				return new CommonVO(false, "密码不正确，还有" + (loginErrorTimes - nowErrorTimes) + "次机会。");
			}
		}
		
		//查看是否被禁用
		if("true".equals(tBasisUser.getIsClose())){
			return new CommonVO(false, "用户已被禁用，无法登录终端设备");
		}
		
		if("".equals(tBasisUser.getUpPwdTime()) || tBasisUser.getUpPwdTime() == null){
			
//			return new CommonVO(false,"首次登陆请系统，请先修改密码。" );//pad首次登陆只能在pc上修改密码，去掉此限制验证
		}else{
			int updpwdTimes = Integer.parseInt(SpringProperty.props.getProperty("updpwdTimes"));
			Date date = new Date();
			int t = DateUtil.diffDate(date,DateUtil.getDateTime(tBasisUser.getUpPwdTime()));
			if(t > updpwdTimes){
				return new CommonVO(false,"密码超过有效期，请修改密码。" );
			}
		}
		
		//获取用户角色
		Set<TBasisUserRole> tBasisUserRoles = tBasisUser.getTBasisUserRoles();
		if(null == tBasisUserRoles || tBasisUserRoles.size() == 0){
			return new CommonVO(false, "用户没有被分配角色，无法登录终端设备");
		}
		
		//判断用户角色是否有关联apk
		for(TBasisUserRole tBasisUserRole : tBasisUserRoles){
			TBasisRole tBasisRole = tBasisUserRole.getTBasisRole();
			Set<TBasisRoleApk> tBasisRoleApks = tBasisRole.getTBasisRoleApks();
			if(null == tBasisRoleApks || tBasisRoleApks.size() == 0){
				continue;
			}if(tBasisRoleApks.size() > 0){
				break;
			}
			return new CommonVO(false,"用户角色没有关联apk，无法登录设备");
		}
		
		
		// 判断设备是否出库
		List<?> devices = this.endpointDao.IsDeviceOut(endpointLoginVO.getDevicePin());
		if (devices.size() == 0) {
			// if(this.endpointDao.IsDeviceOut(endpointLoginVO.getDevicePin()).size()== 0){
			return new CommonVO(false, "该设备未出库，无法登录设备");
		} else {
			TBasisDevice device = (TBasisDevice) devices.get(0);
			if (device.gettBasisUser() == null || !device.gettBasisUser().getUserId().equals(tBasisUser.getUserId())) {
				return new CommonVO(false, "用户无法登录该设备");
			}
		}
		ResLoginVO resLoginVO = new ResLoginVO();
		try {
			BeanUtils.copyProperties(resLoginVO, tBasisUser);
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("删除zip包原文件目录失败");
		}
		
		String role = "";
		String roleName = "";
		for(TBasisUserRole tBasisUserRole : tBasisUserRoles){
			TBasisRole tBasisRole = tBasisUserRole.getTBasisRole();
			role += tBasisRole.getRoleName();
			role += ",";
			roleName += tBasisRole.getRoleName();
			roleName += ",";
		}
		//角色ID“，”分割
        String roleId = role.substring(0, role.lastIndexOf(",")-1);
        if(!"".equals(roleName)){
         roleName = roleName.substring(0, roleName.length()-1);
        }
        Map<String, Map<String, String>> dictMap = (Map<String, Map<String, String>>) servletContext.getAttribute("dictMap");
		Map<String, String> deadline =  dictMap.get("BASIS_DEADLINE");
		String padDeadline = deadline.get("PAD");
		//数据字典中的pad端密码过期时间
		long standard = Long.parseLong(padDeadline);
		long days = DateUtil.getDisDays(tBasisUser.getUpPwdTime(),DateUtil.getCurDateTime());
		TBasisOrg tBasisOrg = tBasisUser.getTBasisOrg();
		
		resLoginVO.setOrgCode(tBasisOrg.getOrgCode());
		resLoginVO.setOrgName(tBasisOrg.getOrgName());
		resLoginVO.setSysTime(DateUtil.format(new Date()));
		resLoginVO.setRoleId(roleId);
		resLoginVO.setRoleName(roleName);
		//resLoginVO.setUserbeizhu1(userbeizhu1);
		if(days > standard || tBasisUser.getNoLogin() == null){
			resLoginVO.setModifyPassword("true");
		}
		else{
			resLoginVO.setModifyPassword("false");
		}
		tBasisUser.setNoLogin("0");
		this.endpointDao.update(tBasisUser);
		return new CommonVO(true, "登陆成功", resLoginVO);
	}

	public ResponseToken crmLogin(String userId) throws BusinessException, MalformedURLException{
		
//		String VerifyUserUrl = "http://159.156.1.175:30088/cfccrm/services/VerifyUser?wsdl";
		String VerifyUserUrl = SpringProperty.props.getProperty("VerifyUser");
		VerifyUserService verifyUserService;
		verifyUserService = new VerifyUserService(new URL(VerifyUserUrl),new QName(
				"http://webService.mobile.bcrm.yuchengtech.com",
				"VerifyUserService"));
		VerifyUser verifyUser=verifyUserService.getVerifyUser();
		ResponseToken responseToken=verifyUser.getToken(userId);
		return responseToken;
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.endpoint.IEndpointService#qryApks(com.mobile.application.vo.endpoint.ReqApkVO)
	 */
	@Override
	@Transactional
	public CommonVO qryApks(ReqApkVO reqApkVO) throws BusinessException {
		
		//pushwin磁盘根目录
		String rootPath = SpringProperty.props.getProperty("RootPath");
		//apk图标存放路径
		String apkIconPath = SpringProperty.props.getProperty("ApkIconPath");
//
//		//生成zip存放的文件夹
//		String zipDirtPath = rootPath + apkIconPath + "/zip";
//		//员工号生成文件夹
//		File userZipDirtPath = new File(zipDirtPath + "/" + reqApkVO.getUserCode());
//		if(!userZipDirtPath.exists())
//			userZipDirtPath.mkdirs();
		TBasisUser tBasisUser = this.endpointDao.getUserByCode(reqApkVO.getUserCode());
//		List<TBasisUser> tBasisUsers = (List<TBasisUser>) endpointDao.get(TBasisUser.class, "userCode", reqApkVO.getUserCode());
		//获取用户角色关系
//		Set<TBasisUserRole> tBasisUserRoles = tBasisUsers.get(0).getTBasisUserRoles();
		Set<TBasisUserRole> tBasisUserRoles = tBasisUser.getTBasisUserRoles();
		//获取角色
		WrapResApkVO wrapResApkVO = new WrapResApkVO();
		Set<ResApkVO> resApkVOs = new HashSet<ResApkVO>();
		boolean picUpdFlag = false;
		//获取业务字典
		Map<String, Map<String, String>> dictMap = (Map<String, Map<String, String>>) servletContext.getAttribute("dictMap");
		Map<String, String> apkType =  dictMap.get("BASIS_APK_TYPE");
		for(TBasisUserRole tBasisUserRole : tBasisUserRoles){
			TBasisRole tBasisRole = tBasisUserRole.getTBasisRole();
			Set<TBasisRoleApk> tBasisRoleApks = tBasisRole.getTBasisRoleApks();
			for(TBasisRoleApk tBasisRoleApk : tBasisRoleApks){
				ResApkVO resApkVO = new ResApkVO();
				try {
					TBasisApk userTBasisApk = tBasisRoleApk.getTBasisApk();
					
					if(StringUtils.isBlank(reqApkVO.getLastTime()) || DateUtil.getDateTime(reqApkVO.getLastTime()).getTime() < DateUtil.getDateTime(userTBasisApk.getImgUpdateTime()).getTime()){
						picUpdFlag = true;
					}
					//将实体类转换成VO
					BeanUtils.copyProperties(resApkVO, tBasisRoleApk.getTBasisApk());
					String picPath = rootPath + userTBasisApk.getImgPath();
					resApkVO.setImgName(FilenameUtils.getName(picPath));
					//resApkVO.setRelatedApk(resApkVO.getRelatedApk());
					//resApkVO.setPageApk(resApkVO.getRelatedApk() + ".atyapply.LoadingActivity");
					resApkVO.setDealType(apkType.get(resApkVO.getDealType()));
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取交易列表失败");
				}
				resApkVOs.add(resApkVO);
			}
		}
		
		//如果图片有更新
		if(picUpdFlag){
			String zipFilePath = rootPath + apkIconPath + "/Icons.zip";
			File zipFile = new File(zipFilePath);
			if(zipFile.exists()){
				wrapResApkVO.setZipPath(apkIconPath + "/Icons.zip");
				wrapResApkVO.setZipLength(String.valueOf(zipFile.length()));
				wrapResApkVO.setZipName(zipFile.getName());
			}
		}
		
		wrapResApkVO.setLastTime(DateUtil.format(new Date()));
		wrapResApkVO.setResApkVOs(resApkVOs);
		return new CommonVO(true, wrapResApkVO, null);
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.endpoint.IEndpointService#padAddress(javax.servlet.http.HttpServletResponse, com.mobile.application.vo.endpoint.ReqLoginVO, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public CommonVO savePadAddress(HttpServletResponse response,ReqAddressVO addressVO) {
		TBasisUser tBasisUsers = this.endpointDao.getUserByCode(addressVO.getUserCode());
		String currentTime = DateUtil.format(new Date()/*addressVO.getUpdTime()*/, "yyyy-MM-dd HH:mm:ss");
		TBasisPosition tBasisPosition = new TBasisPosition();
		tBasisPosition.setDevicePin(addressVO.getDevicePin());
		tBasisPosition.setTBasisUser(tBasisUsers);
		tBasisPosition.setUpdateTime(currentTime);
		tBasisPosition.setLatitude(addressVO.getLatitude());
		tBasisPosition.setLongitude(addressVO.getLongitude());
		this.endpointDao.save(TBasisPosition.class,tBasisPosition);
		return new CommonVO(true, "保存成功");
	}
	@Override
	public RatingScalerResponse ratingScaler(RatingScalerRequest ratingScalerRequest) throws IOException   {
		String ratingScalerUrl = SpringProperty.props.getProperty("RatingScaler");
		RatingScalerServiceService ratingScalerServiceService=new RatingScalerServiceService(new URL(ratingScalerUrl),new QName(
				"http://service.application.ws.corp.cetelem.com/",
		"RatingScalerServiceService"));
		RatingScalerService ratingScalerService = ratingScalerServiceService.getRatingScalerServicePort();
		RatingScalerResponse ratingScalerResponse = ratingScalerService.ratingScalerSever(ratingScalerRequest);
		return ratingScalerResponse;
	}
	
	@Override 
	public CustCreditLimitResponse queryCustCreditLimit(CustCreditLimitRequest custCreditLimitRequest) throws IOException   {
		String custCreditLimitUrl = SpringProperty.props.getProperty("QueryCustCreditLimit");
		QueryCustCreditLimitService queryCustCreditLimitService = 
				new QueryCustCreditLimitService(new URL(custCreditLimitUrl),new QName(
				"http://service.application.ws.corp.cetelem.com/",
		"QueryCustCreditLimitService"));
		QueryCustCreditLimit queryCustCreditLimit = queryCustCreditLimitService.getQueryCustCreditLimitPort();
		CustCreditLimitResponse custCreditLimitResponse = queryCustCreditLimit.queryCustCreditLimit(custCreditLimitRequest);
		return custCreditLimitResponse;
	}
		
		
	public CustomerInquiresResponse qryCustQuery(CustomerInquiresRequest customerInquiresRequest) throws IOException   {
		String CustQueryUrl = SpringProperty.props.getProperty("CustQuery");
		CustomerInquiresService CustomerInquiresService=new CustomerInquiresService(new URL(CustQueryUrl),new QName(
				"http://service.application.ws.corp.cetelem.com/",
		"CustomerInquiresService"));
		CustomerInquires CustomerInquires = CustomerInquiresService.getCustomerInquiresPort();

		CustomerInquiresResponse CustomerInquiresResponse = CustomerInquires.getCustomerById(customerInquiresRequest);
		AppInfoDto AppInfoDto = new AppInfoDto();
		AppInfoDto = CustomerInquiresResponse.getAppInfoDto();
		
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("appInfoDto", AppInfoDto);
		map.put("customerType", CustomerInquiresResponse.getCustomerType());
		map.put("errorCode", CustomerInquiresResponse.getErrorCode());
		map.put("errorMessage", CustomerInquiresResponse.getErrorMessage());
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("custName", "张三");
		map1.put("houseCertificateFlag", "Y");
		map1.put("companyBranch", "请参考<行业与子行业>");
		map1.put("teacherRank", "文件接口-教师级别");
		map1.put("houseLoanFlag", "Y");
		map1.put("spCustName", "李四");
		map1.put("relCustName", "王五");
		map1.put("refCustName", "赵六");
		map1.put("appLoanPurpose", "其他（请注明）");
		map1.put("accFutureField9", "Y");
		map1.put("accCreditLimit", "授信额度");
		map1.put("accOpenToBuy", "可用额度");
		map1.put("desiredCreditLimit", "期望额度");
		map1.put("groupLoanType", "优惠类型");
		map1.put("groupLoanType", "优惠类型");
		map1.put("groupLoanType", "优惠类型");
		map1.put("groupLoanType", "优惠类型");
		map.put("other", map1);*/
//////////////////////////
//		File file = new File("D://njpushwin/");
//		JSONObject p1 = new JSONObject();
//		FilenameFilter directoryFilter = new FilenameFilter() {
//
//			public boolean accept(File dir, String fileName) {
//				if (dir.isDirectory()) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//		};
//		List<Map<String, Object>> oneDirectoryList = new ArrayList<Map<String,Object>>();
//		File[] oneDirectorys =  file.listFiles(directoryFilter);
//		for (File oneDirectory : oneDirectorys) {
//			String[] s = oneDirectory.getName().split(identityNumber+"_"+productCategory);
//			if(s.length>1){
//				FileInputStream fis= null;
//				try{
//					fis = new FileInputStream(oneDirectory);
//					byte[] resByte = StreamUtils.getBytes(fis);
//					String desc = new String(resByte);
//					p1 = JSONObject.fromObject(desc);
//				} catch (IOException e) {
//					CustomerInquiresResponse.setErrorCode("1");
//					CustomerInquiresResponse.setErrorMessage("读取数据失败");
//					CustomerInquiresResponse.setSuccess(false);
//					e.printStackTrace();
//					throw e;
//				} finally {
//					if(null != fis)
//						fis.close();
//				}
//			}}
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("appInfoDto", p1);
//		map2.put("customerType", CustomerInquiresResponse.getCustomerType());
//		map2.put("errorCode", CustomerInquiresResponse.getErrorCode());
//		map2.put("errorMessage", CustomerInquiresResponse.getErrorMessage());
/////////////////////////////		
		return CustomerInquiresResponse;
	}

	@Override
	public AppProgressResponse qryAppProgress(AppProgressRequest appProgressRequest) throws IOException   {
		String AppProgressUrl = SpringProperty.props.getProperty("AppProgress");
		ApplicationProgressService ApplicationProgressService =new ApplicationProgressService(new URL(AppProgressUrl),
				new QName("http://service.application.ws.corp.cetelem.com/",
						"ApplicationProgressService"));
		ApplicationProgress ApplicationProgress =  ApplicationProgressService.getApplicationProgressPort();
		AppProgressResponse AppProgressResponse = ApplicationProgress.queryApplicationProgress(appProgressRequest);
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", AppProgressResponse);*/
		return AppProgressResponse;
	}
	
	@Override
	public UploadStatusResponse updateUploadStatus(String AuthNumber,String UploadStatus) throws IOException {
		UploadStatusRequest uploadStatusRequest = new UploadStatusRequest();
		String updateUploadStatus = SpringProperty.props.getProperty("updateUploadStatus");
//		String UploadStatusUrl ="http://159.191.36.56:9080/trad_backend/service/UpdateUploadStatus?wsdl";
		UpdateUploadStatusServiceService updateUploadStatusServiceService = new UpdateUploadStatusServiceService(new URL(updateUploadStatus),new QName(
				"http://service.application.ws.corp.cetelem.com/",
				"UpdateUploadStatusServiceService"));
		UpdateUploadStatusService updateUploadStatusService =   updateUploadStatusServiceService.getUpdateUploadStatusServicePort();
		
		uploadStatusRequest.setAuthNumber(AuthNumber);
		uploadStatusRequest.setUploadStatus(UploadStatus);
		UploadStatusResponse uploadStatusResponse = updateUploadStatusService.updateUploadStatus(uploadStatusRequest);
		
		return uploadStatusResponse;
		
	}

	@Override
	public SubmitApplicationResponse qrySubmitApp(SubmitApplicationRequest submitApplicationRequest) throws IOException {
		String SubmitAppUrl = SpringProperty.props.getProperty("SubmitApp");
		SubmitApplicationService SubmitApplicationService =new SubmitApplicationService(new URL(SubmitAppUrl),new QName(
				"http://service.application.ws.corp.cetelem.com/",
		"SubmitApplicationService"));
		SubmitApplication SubmitApplication =   SubmitApplicationService.getSubmitApplicationPort();
//		productCategory = "03";
//		appAction = "NEW_PT";
//		customerType = "NEW";
//		JSONObject json = JSONObject.fromObject(appInfoDto1);
//		AppInfoDto appInfoDto = new AppInfoDto();
//		if(StringUtils.isNotBlank(appInfoDto1)){
//		 appInfoDto = (AppInfoDto) JSONObject.toBean(json,AppInfoDto.class);}
//		else{
//		 appInfoDto = new AppInfoDto();
//		}
//		appInfoDto.setAuthNumber("1111a");//授权号
//		appInfoDto.setUrn("11112222a");//urn 授权号+编号
//		appInfoDto.setOrgCode("301a");//行机构号 301-南京银行，302，日照银行
//		appInfoDto.setProductId("123123a");//产品ID
//		appInfoDto.setStaffId("123123a");//员工号
//		appInfoDto.setSerialNumber("123123a");//流水号
//		appInfoDto.setStoreNumber("123123a");//商户编号
//		appInfoDto.setStoreName("商户中文名称a");//商户名称
//		appInfoDto.setBarcode("123123a"); //扫描条码
//		appInfoDto.setSignatureDate("2016-01-29");//申请表签字日期
//		appInfoDto.setApplyTime(new Date());//申请日期
//		appInfoDto.setIdentityNumber("123123123123123123123123a");//证件号码
		
//		SubmitApplicationRequest SubmitApplicationRequest = new SubmitApplicationRequest();
//		SubmitApplicationRequest.setAppInfoDto(appInfoDto);
//		if(StringUtils.isNotBlank(productCategory)){
//			SubmitApplicationRequest.setProductCategory(productCategory);
//		}else{
//			SubmitApplicationRequest.setProductCategory("123");
//		}
//		SubmitApplicationRequest.setAction(appAction);
//		SubmitApplicationRequest.setCustomerType(customerType);
		SubmitApplicationResponse submitApplicationResponse = SubmitApplication.submitApp(submitApplicationRequest);
		//Map<String, Object> map = new HashMap<String, Object>();
//////////////////////		
//		File file = new File("D://njpushwin/"+customerType +"_"+appInfoDto.getIdentityNumber()+"_"+productCategory+".txt");
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(file);
//			fos.write(appInfoDto1.toString().getBytes());
//			fos.flush();
//			fos.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			SubmitApplicationResponse.setErrorCode("500");
//			SubmitApplicationResponse.setErrorMessage("写入文件错误");
//		} finally {
//			fos.close();
//		}		
////////////////////////		
/*		if("500".equals(SubmitApplicationResponse.getErrorCode())){
			map.put("errorCode", SubmitApplicationResponse.getErrorCode());
			map.put("errorMessage", SubmitApplicationResponse.getErrorMessage());
		}else{
		map.put("authorNumber", SubmitApplicationResponse.getAuthorNumber());
		map.put("finalDecision", SubmitApplicationResponse.getFinalDecision());
		map.put("decReason", "拒绝原因");
		map.put("crLimitCount", "预审额度");
		map.put("custEducation", "客户学历");
		map.put("pbocHouseLoan", "PBOCF房贷");
		map.put("errorCode", SubmitApplicationResponse.getErrorCode());
		map.put("errorMessage", SubmitApplicationResponse.getErrorMessage());
		map.put("pbocHouseLoan", "PBOCF房贷");
		}*/
		return submitApplicationResponse;
	}
	
	@Override
	public SmsServiceResponse Sms(SmsServiceRequest smsServiceRequest) throws IOException {
		String Sms = SpringProperty.props.getProperty("Sms");
		SmsServiceService SmsServiceService = new SmsServiceService(new URL(Sms),new QName(
				"http://service.application.ws.corp.cetelem.com/",
		"SmsServiceService"));
		SmsService smsService =   SmsServiceService.getSmsServicePort();
		SmsServiceResponse smsServiceResponse = smsService.sendSms(smsServiceRequest);
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("sms",smsServiceResponse);*/
		return smsServiceResponse;
	}
	@Transactional(readOnly = true)
	@Override
	public Map<String, Object> padLogin(Puwshlogin puwshlogin) throws IOException {
		//SmsServiceResponse smsServiceResponse = smsService.sendSms(smsServiceRequest);
		Paduser user = this.endpointDao.puwshlogin(puwshlogin);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user",user);
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	@Override
	public 	Map<String,Object> ParameterNew(Object parameter,String usercode,String orgCode,String bei ) throws IOException {
		Map<String, Object> mapCompany=  this.endpointDao.ParameterNew(null,usercode,orgCode,bei);
	/*	JSONObject listTBasisDictBranchobject = new  JSONObject();
		listTBasisDictBranchobject.put("TBasisDictBranch", listTBasisDictBranch);*/
		return  mapCompany;
	}
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	@Override
	public 	Map<String,Object> Parameter(Object parameter) throws IOException {
		Map<String, Object> mapCompany=  this.endpointDao.Parameter(parameter);
	/*	JSONObject listTBasisDictBranchobject = new  JSONObject();
		listTBasisDictBranchobject.put("TBasisDictBranch", listTBasisDictBranch);*/
		return  mapCompany;
	}

	@Override
	public ReturnOfGoodsResponse ReturnOfGoods(
			ReturnOfGoodsRequest returnOfGoodsRequest) throws IOException {
		String Sms = SpringProperty.props.getProperty("returnofgoods");
		ReturnOfGoodsService returnOfGoodsService = new ReturnOfGoodsService(new URL(Sms),new QName(
				"http://service.application.ws.corp.cetelem.com/",
		"ReturnOfGoodsService"));
		ReturnOfGoods returnOfGoods =   returnOfGoodsService.getReturnOfGoodsPort();
		ReturnOfGoodsResponse  returnOfGoodsResponse =  returnOfGoods.returnOfGood(returnOfGoodsRequest);
		return returnOfGoodsResponse;
	}

	@Override
	public LoadApplicationResponse loadApp(
			LoadApplicationRequest loadApplicationRequest) throws IOException {
		String Sms = SpringProperty.props.getProperty("loadApp");
		LoadApplicationService loadApplicationService = new LoadApplicationService(new URL(Sms),new QName(
				"http://service.application.ws.corp.cetelem.com/",
		"LoadApplicationService"));
		LoadApplication loadApplication =   loadApplicationService.getLoadApplicationPort();
		LoadApplicationResponse  loadAppInstanceResponse =  loadApplication.loadAppInstance(loadApplicationRequest);
		return loadAppInstanceResponse;
	}
    public static void main(String[] args) {
		TBasisOrg org = new TBasisOrg();
		JSONObject onj =JSONObject.fromObject(org);
		System.out.println(onj.toString());
		
	}

	@Override
	public PaymentCalResponse paymentCal(PaymentCalForServiceRequest paymentCalRequest) {
		String paymentHandle = SpringProperty.props.getProperty("paymentHandle");
		PaymentHandlerServiceService paymentHandlerServiceService = null;
		PaymentCalResponse paymentCalResponse = null;
				try {
						paymentHandlerServiceService = new PaymentHandlerServiceService(new URL(paymentHandle),new QName(
						"http://service.application.ws.corp.cetelem.com/",
								"PaymentHandlerServiceService"));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			PaymentHandlerService paymentHandlerService =   paymentHandlerServiceService.getPaymentHandlerServicePort();
		  try {
			paymentCalResponse =  paymentHandlerService.handlerPlan(paymentCalRequest);
		} catch (AppException_Exception e) {
			e.printStackTrace();
		}
		
		return paymentCalResponse;
	}

	@Override
	public TelesalesCustInfoQueryResponse telesalesCustInfoQuery(
			TelesalesCustInfoQueryRequest telesalesCustInfoQueryRequest) {
		String telesalesCustInfo = SpringProperty.props.getProperty("telesalesCustInfo");
		TelesalesCustInfoQueryServiceService telesalesCustInfoQueryServiceService = null;
		try {
			telesalesCustInfoQueryServiceService = new TelesalesCustInfoQueryServiceService(new URL(telesalesCustInfo),new QName(
					"http://service.application.ws.corp.cetelem.com/",
			"TelesalesCustInfoQueryServiceService"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		TelesalesCustInfoQueryService telesalesCustInfoQueryService =   telesalesCustInfoQueryServiceService.getTelesalesCustInfoQueryServicePort();
		TelesalesCustInfoQueryResponse  telesalesCustInfoQueryResponse =  telesalesCustInfoQueryService.getCustInfoList(telesalesCustInfoQueryRequest);
		return telesalesCustInfoQueryResponse;
	}

	@Override
	public com.pushwin.PreScrSubmitApp.SubmitApplicationResponse PreScrSubmitApp(
			com.pushwin.PreScrSubmitApp.SubmitApplicationRequest submitApplicationRequest) {
		String preScrSubmitApp = SpringProperty.props.getProperty("preScrSubmitApp");
		com.pushwin.PreScrSubmitApp.BatchSubmitApplicationService batchSubmitApplicationService = null;
		try {
			batchSubmitApplicationService = new com.pushwin.PreScrSubmitApp.BatchSubmitApplicationService(new URL(preScrSubmitApp),new QName(
					"http://service.application.ws.corp.cetelem.com/",
			"BatchSubmitApplicationService"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		com.pushwin.PreScrSubmitApp.BatchSubmitApplication BatchSubmitApplication =   batchSubmitApplicationService.getBatchSubmitApplicationPort();
		com.pushwin.PreScrSubmitApp.SubmitApplicationResponse submitApplicationResponse =  BatchSubmitApplication.preScrSubmitApp(submitApplicationRequest);
		return submitApplicationResponse;

	}

	
	@Override
	public com.pushwin.CfcUpdateAppState.UpdateApplicationResponse cfcUpdateAppState(
			com.pushwin.CfcUpdateAppState.UpdateApplicationRequest updateAppStateRequest) {
		String updateAppState = SpringProperty.props.getProperty("UpdateAppState");
		com.pushwin.CfcUpdateAppState.UpdateAppStateService updateAppStateService = null;
		try {
			updateAppStateService = new com.pushwin.CfcUpdateAppState.UpdateAppStateService(new URL(updateAppState),new QName(
					"http://service.application.ws.corp.cetelem.com/",
			"UpdateAppStateService"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		com.pushwin.CfcUpdateAppState.UpdateAppState updateAppStateit =   updateAppStateService.getUpdateAppStatePort();
		com.pushwin.CfcUpdateAppState.UpdateApplicationResponse updateApplicationResponse =  updateAppStateit.updateAppliationState(updateAppStateRequest);
		return updateApplicationResponse;
	}

	@Transactional
	@Override
	/*public com.pushwin.newpassword.UpdatePasswordResponse loginCheckService(com.pushwin.newpassword.UpdatePasswordRequest loginRequest) {
		String loginCheckServiceurl = SpringProperty.props.getProperty("LoginCheckService");
		com.pushwin.newpassword.LoginCheckServiceService loginCheckServiceService = null;
		try {
			loginCheckServiceService = new com.pushwin.newpassword.LoginCheckServiceService(new URL(loginCheckServiceurl),new QName(
					"http://service.application.ws.corp.cetelem.com/",
			"LoginCheckServiceService"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		com.pushwin.newpassword.LoginCheckService loginCheckService=   loginCheckServiceService.getLoginCheckServicePort();
		com.pushwin.newpassword.UpdatePasswordResponse  updatePasswordResponse =  loginCheckService.updateUserPassword(loginRequest);
*/	public void loginCheckService(com.pushwin.newpassword.UpdatePasswordRequest loginRequest) {
		String password = MD5Util.md5(loginRequest.getNewPassword());
		endpointDao.updatePADUserPassword(password,loginRequest.getUserId());
	
	}
	
	@Transactional
	@Override
	public void loginCheckService2(com.pushwin.newpassword.UpdatePasswordRequest loginRequest) {
		String password = MD5Util.md5(loginRequest.getNewPassword());
		endpointDao.updatePADUserPassword2(password,loginRequest.getUserId());
	
	}

	@Override
	@Transactional(readOnly = true)
	public List<ActivityResponseVO> qryActivitys(String orgcode, String upateTime) throws ParseException, IllegalAccessException, InvocationTargetException {
		Map<String, Object>  map = new HashMap<String, Object>();
//		  List listactivityCode = endpointDao.queryactivityMessageOrgByCode(orgcode);
		List<ActivityResponseVO> activityMap = endpointDao.qryActivitys(orgcode,upateTime);
		return activityMap;
	}

	public String qryBankAcc(String bankCode) throws BusinessException {
		
		String redisUrlString = SpringProperty.props.getProperty("redisUrlString");
		BizServiceClient client=new BasicBizServiceClient(redisUrlString);
		HashRequest request=new HashRequest();
		HashResponse response;
		request.setAction("cashServer");
		request.setMethod("doService");
		request.setKey(bankCode);
		request.setOrganizationId("CHN");
		request.setApplicationId("0101");
		response=(HashResponse)client.callService(request);
		System.out.println("response--:"+response.getFieldMap().toString());
		Map<String, String> map=new HashMap<String, String>();
		if(response.getErrorCode() != null){
			map=response.getFieldMap();
			//log.info("[custNumber"+custNumber+";keycode:"+keycode+"][map"+map+"]");
		}
		if(map!=null && map.get("bankCode")!=null && 
				map.get("bankCode")!=""){
			return map.get("bankCode");
		}
		return "";
	}
	
}

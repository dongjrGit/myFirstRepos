package com.mobile.application.controller.apk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.entity.TBasisApk;
import com.mobile.application.service.apk.IAPKService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;


@Controller
@RequestMapping("/apk")
public class APKController {
	
	@Autowired
	private IAPKService apkService;
	
	/*
	 * 进入交易管理页面
	 * */
	@RequestMapping("init")
	public String init(){
		return "apk/apk";
	}
	/*
	 * 查询交易信息
	 * */
	@RequestMapping(method=RequestMethod.POST,value="/qryApks")
	@ResponseBody
	public CommonVO qryApks(String pageIndex, String pageSize, String dealName){
		CommonVO commonVO = apkService.qryApks(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), dealName);
		return commonVO;
	}
	/*
	 * 查询交易所属渠道
	 * */
	@RequestMapping(method=RequestMethod.GET,value="/qryChannel")
	@ResponseBody
	public CommonVO qryChannel(String busintypeid){
		CommonVO commonVO = apkService.qryChannel(busintypeid);
		return commonVO;
	}
	/*
	 * 查询交易可关联APK
	 * */
	@RequestMapping(method=RequestMethod.POST,value="/qryApkById")
	@ResponseBody
	public CommonVO qryApkById(String dealId){
		CommonVO commonVO = apkService.qryApkById(dealId);
		return commonVO;
	}
	/*
	 * 保存交易信息
	 * */
	@RequestMapping(method=RequestMethod.POST,value="/saveApkInfo")
	public String saveApkInfo(@RequestParam(value = "apkIconName", required = false)MultipartFile file, TBasisApk tBasisApk, HttpSession session) throws BusinessException, IOException {
		apkService.saveApkInfo(file, tBasisApk, (SessionVO)session.getAttribute("sessionVO"));
		return "apk/apk";
	}
	/*
	 * 删除交易信息
	 * */
	@RequestMapping("/remove")
	@ResponseBody
	public CommonVO removeApkInfo(String dealId) throws BusinessException, IOException {
		CommonVO commonVO = apkService.removeApkInfo(dealId);
		return commonVO;
	}
	
	/**
	 * Description : 同步APK列表
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param N/A
	 * @return 查询结果集
	 * @exception BusinessException
	 */
	@RequestMapping("synchronousApk")
	@ResponseBody
	public CommonVO synchronousApk() throws Exception {
		try {
			
			//String apkInfo = getEMMApkInfo();
			//apkInfo 暂时以固定形式展示,后续待emm配合以接口形式将apk列表
			String apkInfo = "{\"result\":" +
					"[" +
					"{\"name\":\"信用卡申请\",\"pakageName\":\"com.techown.creditcard\"}," +
					"{\"name\":\"借记卡申请\",\"pakageName\":\"com.techown.debitcard\"}," +
					"{\"name\":\"借记卡批量采集\",\"pakageName\":\"com.techown.acquiredcard\"}," +
					"{\"name\":\"借记卡批量开卡\",\"pakageName\":\"com.techown.acquiredcard\"}," +
					"{\"name\":\"基金\",\"pakageName\":\"com.techown.acquiredcard\"}," +
					"{\"name\":\"电子银行签约\",\"pakageName\":\"com.techown.debitcard\"}," +
					"{\"name\":\"理财产品签约\",\"pakageName\":\"com.techown.financing\"}," +
					"{\"name\":\"排队管理\",\"pakageName\":\"com.techown.smarthall\"}," +
					"{\"name\":\"自助填单\",\"pakageName\":\"com.techown.smarthall\"}," +
					"{\"name\":\"客户信息查询\",\"pakageName\":\"com.techown.assistant\"}," +
					"{\"name\":\"小微贷款预审\",\"pakageName\":\"com.techown.mobileloan\"}," +
					"{\"name\":\"个人贷款预审\",\"pakageName\":\"com.techown.mobileloan\"}," +
					"{\"name\":\"贷款申请\",\"pakageName\":\"com.techown.mobileloan\"}," +
					"{\"name\":\"贷款预审查询\",\"pakageName\":\"com.techown.mobileloan\"}," +
					"{\"name\":\"贷款申请查询\",\"pakageName\":\"com.techown.mobileloan\"}," +
					"{\"name\":\"贷前调查\",\"pakageName\":\"com.techown.mobileloan\"}," +
					"{\"name\":\"贷后管理\",\"pakageName\":\"com.techown.mobileloan\"}," +
					"{\"name\":\"商户管理\",\"pakageName\":\"com.example.mmactivity\"}," +
					"{\"name\":\"计算器\",\"pakageName\":\"com.techown.pushwin\"}," +
					"{\"name\":\"产品营销\",\"pakageName\":\"com.techown.marketing\"}," +
					"{\"name\":\"江南银行\",\"pakageName\":\"com.ykjd.jnbank\"}," +
					"{\"name\":\"电子资料\",\"pakageName\":\"com.techown.electron\"}," +
					"{\"name\":\"贷款审批\",\"pakageName\":\"com.techown.mobileloan\"}" +
					"]}";
			if(apkInfo.equals("error")){
			}else{
				JSONObject json = JSONObject.fromObject(apkInfo);
				String data = json.getString("result");
				JSONArray apkInfos = JSONArray.fromObject(data);
				JSONObject apkJson = null;
				List mapList = new ArrayList();
				for (int i = 0; i < apkInfos.size(); i++) {
					apkJson = apkInfos.getJSONObject(i);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", apkJson.getString("name"));
					map.put("pakageName", apkJson.getString("pakageName"));
					
					mapList.add(map);
				}
				return new CommonVO(true, mapList, mapList.size() + "");
			}
			/*String busintypeId="1003";
			if (user != null) {
				JSONObject json = this.basisTransactionService.getDealChannel(user
						.getUserCode(), busintypeId);
				DisposDataRetrun.ajaxReturn(json);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
/*
 * 检验交易名称是否存在	
 * */	
	@RequestMapping("/checkDealName")
	@ResponseBody
	public CommonVO checkDealName(@RequestParam(value = "myfilelogin", required = false)MultipartFile file, TBasisApk tBasisApk, HttpSession session) throws BusinessException, IOException {
		CommonVO commonVO = apkService.checkDealName(tBasisApk);
		return commonVO;
	}	
	
	
}

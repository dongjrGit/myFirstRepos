package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PayConfigTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Payconfig;
import com.yinlian.wssc.web.po.PayconfigExample;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.PayconfigService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 支付配置的控制类
 * 
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/platform/paycongif")
public class PayConfigController {

	@Autowired
	private PayconfigService payconfigService;

	SessionUser user = null;
	@Autowired
    private OperaterecordsService operaterecordsService ;

	/**
	 * 添加支付配置
	 * 
	 * @param paytype
	 *            支付方式
	 * @param partner
	 *            商户号
	 * @param sellerid
	 *            卖家商户ID
	 * @param charset
	 *            编码格式
	 * @param signtype
	 *            签名方式（支付宝）
	 * @param paymenttype
	 *            支付类型
	 * @param sellername
	 *            商户名称（环迅）
	 * @param currency
	 *            币种（环迅）
	 * @param version
	 *            版本号（环迅）
	 * @param actionUrl
	 *            支付请求地址（环迅）
	 * @param retencode
	 *            交易返回接口加密方式（环迅）
	 * @param orderencode
	 *            订单支付接口加密方式（环迅）
	 * @param billvalidity
	 *            订单有效期 以小时为单位，必须是整数（环迅）
	 * @param iscredit
	 *            是否直连（环迅）
	 * @param bankno
	 *            银行编号（环迅）
	 * @param producttype
	 *            产品类型 1-个人网银 2-企业网银（环迅）
	 * @param anti_phishing_key
	 *            防钓鱼时间戳（支付宝）
	 * @param exter_invoke_ip
	 *            客户端的IP地址（支付宝）
	 * @param status
	 *            状态 0-启用 1-禁用
	 * @param notify_url
	 *            服务器异步通知页面路径
	 * @param success_url
	 *            成功页面跳转同步通知页面路径
	 * @param fail_url
	 *            失败页面跳转同步通知页面路径
	 * @param privatekey
	 *            私钥
	 * @return
	 */
	@RequestMapping("/add")
	public ReusltItem addPay(String paytype, String partner,
			String sellerid, String charset, String signtype,
			String paymenttype, String sellername, String currency,
			String version, String actionUrl, String retencode,
			String orderencode, String billvalidity, String iscredit,
			String bankno, String producttype, String anti_phishing_key,
			String exter_invoke_ip, String status, String notify_url,
			String success_url, String fail_url, String privatekey,String sites,
			String publickey,String msgid,String rettype) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			Payconfig payconfig = new Payconfig();
			payconfig = checkParam(paytype, partner, sellerid,
					charset, signtype, paymenttype, sellername, currency,
					version, actionUrl, retencode, orderencode, billvalidity,
					iscredit, bankno, producttype, anti_phishing_key,
					exter_invoke_ip, status, notify_url, success_url, fail_url,
					privatekey,sites,publickey,msgid,rettype, "0", item);
			if(payconfig==null || item.getCode()<0){
				return item;
			}
			if(payconfigService.insert(payconfig)>0){
				item.setCode(0);
				item.setDesc("添加支付配置成功!");
				LogHandle.info(
						LogType.Data,
						MessageFormat.format("添加支付配置成功! 支付类型:{0},商户号:{1},用户ID:{2}",
								paytype,partner, user.getUserId()),
						"/platform/paycongif/addPay");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "payConfig_edit.jsp", "/platform/paycongif/addPay", "添加支付配置");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加支付配置操作记录出错! 异常信息:",
    								e, "/platform/paycongif/addPay");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("添加支付配置失败!");
				LogHandle.info(
						LogType.Data,
						MessageFormat.format("添加支付配置失败! 支付类型:{0},商户号:{1},用户ID:{2}",
								paytype,partner, user.getUserId()),
						"/platform/paycongif/addPay");
			}

		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("添加支付配置异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Data,"添加支付配置异常! 异常信息:", e,
					"/platform/paycongif/addPay");
		}
		return item;
	}

	/**
	 * 添加支付配置
	 * 
	 * @param id
	 *            支付配置ID
	 * @param paytype
	 *            支付方式
	 * @param partner
	 *            商户号
	 * @param sellerid
	 *            卖家商户ID
	 * @param charset
	 *            编码格式
	 * @param signtype
	 *            签名方式（支付宝）
	 * @param paymenttype
	 *            支付类型
	 * @param sellername
	 *            商户名称（环迅）
	 * @param currency
	 *            币种（环迅）
	 * @param version
	 *            版本号（环迅）
	 * @param actionUrl
	 *            支付请求地址（环迅）
	 * @param retencode
	 *            交易返回接口加密方式（环迅）
	 * @param orderencode
	 *            订单支付接口加密方式（环迅）
	 * @param billvalidity
	 *            订单有效期 以小时为单位，必须是整数（环迅）
	 * @param iscredit
	 *            是否直连（环迅）
	 * @param bankno
	 *            银行编号（环迅）
	 * @param producttype
	 *            产品类型 1-个人网银 2-企业网银（环迅）
	 * @param anti_phishing_key
	 *            防钓鱼时间戳（支付宝）
	 * @param exter_invoke_ip
	 *            客户端的IP地址（支付宝）
	 * @param status
	 *            状态 0-启用 1-禁用
	 * @param notify_url
	 *            服务器异步通知页面路径
	 * @param success_url
	 *            成功页面跳转同步通知页面路径
	 * @param fail_url
	 *            失败页面跳转同步通知页面路径
	 * @param privatekey
	 *            私钥
	 * @return
	 */
	@RequestMapping("/edit")
	public ReusltItem editPay(String id,String paytype, String partner,
			String sellerid, String charset, String signtype,
			String paymenttype, String sellername, String currency,
			String version, String actionUrl, String retencode,
			String orderencode, String billvalidity, String iscredit,
			String bankno, String producttype, String anti_phishing_key,
			String exter_invoke_ip, String status, String notify_url,
			String success_url, String fail_url, String privatekey,String sites,
			String publickey,String msgid,String rettype) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			Payconfig payconfig = new Payconfig();
			payconfig = checkParam(paytype, partner, sellerid,
					charset, signtype, paymenttype, sellername, currency,
					version, actionUrl, retencode, orderencode, billvalidity,
					iscredit, bankno, producttype, anti_phishing_key,
					exter_invoke_ip, status, notify_url, success_url, fail_url,
					privatekey,sites,publickey,msgid,rettype, id, item);
			if(payconfig==null || item.getCode()<0){
				return item;
			}
			if(payconfigService.updateByPrimaryKey(payconfig)>0){
				item.setCode(0);
				item.setDesc("编辑支付配置成功!");
				LogHandle.info(
						LogType.Data,
						MessageFormat.format("编辑支付配置成功! id:{0},支付类型:{1},商户号:{2},用户ID:{3}",
								id,paytype,partner, user.getUserId()),
						"/platform/paycongif/editPay");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "payConfig_edit.jsp", "/platform/paycongif/editPay", "修改支付配置");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改支付配置操作记录出错! 异常信息:",
    								e, "/platform/paycongif/editPay");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("编辑支付配置失败!");
				LogHandle.info(
						LogType.Data,
						MessageFormat.format("编辑支付配置失败! id:{0},支付类型:{1},商户号:{2},用户ID:{3}",
								id,paytype,partner, user.getUserId()),
						"/platform/paycongif/editPay");
			}

		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("编辑支付配置异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Data,"编辑支付配置异常! 异常信息:", e,
					"/platform/paycongif/editPay");
		}
		return item;
	}
	
	/**
	 * 删除支付配置
	 * @param id
	 * @return
	 */
	@RequestMapping("/del")
	public ReusltItem delPay(String id){
		ReusltItem item=new ReusltItem();
		try{
			user = SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("id参数错误");
				return item;
			}
			if(payconfigService.deleteByPrimaryKey(StringUtilsEX.ToInt(id))>0){
				item.setCode(0);
				item.setDesc("删除支付配置成功!");
				LogHandle.info(
						LogType.Data,
						MessageFormat.format("删除支付配置成功! id:{0},用户ID:{1}",
								id, user.getUserId()),
						"/platform/paycongif/delPay");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "payConfig_list.jsp", "/platform/paycongif/delPay", "删除支付配置");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除支付配置操作记录出错! 异常信息:",
    								e, "/platform/paycongif/delPay");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("删除支付配置失败!");
				LogHandle.info(
						LogType.Data,
						MessageFormat.format("删除支付配置失败! id:{0},用户ID:{1}",
								id, user.getUserId()),
						"/platform/paycongif/delPay");
			}
			
		}catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("删除支付配置异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Data,"删除支付配置异常! 异常信息:", e,
					"/platform/paycongif/delPay");
		}
		return item;
	}
	
	
	
	private Payconfig checkParam(String paytype, String partner,
			String sellerid, String charset, String signtype,
			String paymenttype, String sellername, String currency,
			String version, String actionUrl, String retencode,
			String orderencode, String billvalidity, String iscredit,
			String bankno, String producttype, String anti_phishing_key,
			String exter_invoke_ip, String status, String notify_url,
			String success_url, String fail_url, String privatekey,String sites,
			String publickey,String msgid,String rettype, String id,
			ReusltItem item) throws Exception {
		user = SessionState.GetCurrentUser();
		Payconfig payconfig = new Payconfig();
		if (StringUtilsEX.ToInt(paytype) < 0) {
			item.setCode(-101);
			item.setDesc("支付方式错误");
			return null;
		} else {
			if (StringUtilsEX.ToInt(paytype) != PayConfigTypeEnum.支付宝
					.getValue()
					&& StringUtilsEX.ToInt(paytype) != PayConfigTypeEnum.环迅支付
							.getValue()) {
				item.setCode(-101);
				item.setDesc("支付方式错误");
				return null;
			}
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(privatekey)) {
			item.setCode(-121);
			item.setDesc("商户私钥不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(sites)<0) {
			item.setCode(-122);
			item.setDesc("站点不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(paytype) == PayConfigTypeEnum.支付宝.getValue()) {
			if (StringUtilsEX.IsNullOrWhiteSpace(signtype)) {
				item.setCode(-102);
				item.setDesc("支付宝签名方式不能为空");
				return null;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(sellerid)) {
				item.setCode(-103);
				item.setDesc("卖家收款号不能为空");
				return null;
			}
			//app端
			if(StringUtilsEX.ToInt(sites)==2){
				if (StringUtilsEX.IsNullOrWhiteSpace(publickey)) {
					item.setCode(-123);
					item.setDesc("支付宝公钥不能为空");
					return null;
				}
			}
		}
		if (StringUtilsEX.ToInt(paytype) == PayConfigTypeEnum.环迅支付.getValue()) {
			if (StringUtilsEX.IsNullOrWhiteSpace(sellerid)) {
				item.setCode(-104);
				item.setDesc("商户账号不能为空");
				return null;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(sellername)) {
				item.setCode(-108);
				item.setDesc("商户名称不能为空");
				return null;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(version)) {
				item.setCode(-109);
				item.setDesc("版本号不能为空");
				return null;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(actionUrl)) {
				item.setCode(-110);
				item.setDesc("支付请求地址不能为空");
				return null;
			}
			if (StringUtilsEX.ToInt(currency) < 0) {
				item.setCode(-111);
				item.setDesc("支付币种不能为空");
				return null;
			}
			if (StringUtilsEX.ToInt(retencode) < 0) {
				item.setCode(-112);
				item.setDesc("交易返回接口加密方式");
				return null;
			}
			if (StringUtilsEX.ToInt(orderencode) < 0) {
				item.setCode(-113);
				item.setDesc("订单支付接口加密方式");
				return null;
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(iscredit)) {
				if (StringUtilsEX.IsNullOrWhiteSpace(bankno)) {
					item.setCode(-114);
					item.setDesc("直连支付银行编号不能为空");
					return null;
				}
				if (StringUtilsEX.ToInt(producttype) < 0) {
					item.setCode(-114);
					item.setDesc("直连支付产品类型不能为空");
					return null;
				}
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(billvalidity)) {
				if (StringUtilsEX.ToInt(billvalidity) < 0) {
					item.setCode(-115);
					item.setDesc("订单有效期 以小时为单位，必须是整数");
					return null;
				}

			}
			if (StringUtilsEX.ToInt(rettype) < 0) {
				item.setCode(-126);
				item.setDesc("返回方式不能为空");
				return null;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(msgid)) {
				item.setCode(-127);
				item.setDesc("消息编号不能为空");
				return null;
			}
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(partner)) {
			item.setCode(-105);
			item.setDesc("商户号不能为空");
			return null;
		}

		if (StringUtilsEX.IsNullOrWhiteSpace(charset)) {
			item.setCode(-106);
			item.setDesc("编码规格不能为空");
			return null;
		}

		if (StringUtilsEX.IsNullOrWhiteSpace(paymenttype)) {
			item.setCode(-107);
			item.setDesc("支付方式不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(status) < 0) {
			item.setCode(-116);
			item.setDesc("状态有误");
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(notify_url)) {
			item.setCode(-117);
			item.setDesc("服务器异步回调地址不能为空");
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(success_url)) {
			item.setCode(-118);
			item.setDesc("成功页面跳转同步通知页面路径");
			return null;
		}
		if (StringUtilsEX.ToInt(id) < 0) {
			item.setCode(-119);
			item.setDesc("支付配置ID参数错误");
			return null;
		}
		if (StringUtilsEX.ToInt(id) > 0) {
			payconfig = payconfigService.selectByPrimaryKey(StringUtilsEX
					.ToInt(id));
			if (payconfig == null) {
				item.setCode(-120);
				item.setDesc("支付配置ID参数错误");
				LogHandle.error(LogType.Data, "根据支付配置ID未能检索到数据" + id,
						"/platform/paycongif/editPay");
				return null;
			}
		}else{
			payconfig.setCreatetime(new Date());	
			payconfig.setCreateuserid(user.getUserId());
		}
		payconfig.setPaytype(StringUtilsEX.ToInt(paytype));
		
		if(payconfig.getPaytype()==PayConfigTypeEnum.支付宝.getValue()){
			payconfig.setSignType(signtype);
			payconfig.setAntiPhishingKey(anti_phishing_key);
			payconfig.setExterInvokeIp(exter_invoke_ip);
			payconfig.setPublicKey(publickey);
		}else{
			payconfig.setActionurl(actionUrl);			
			payconfig.setBankno(bankno);
			if (StringUtilsEX.ToInt(billvalidity) > 0)
				payconfig.setBillvalidity(StringUtilsEX.ToInt(billvalidity));
			payconfig.setCurrency(StringUtilsEX.ToInt(currency));
			if (StringUtilsEX.ToInt(iscredit) > 0)
				payconfig.setIscredit(StringUtilsEX.ToInt(iscredit));
			payconfig.setMername(sellername);
			payconfig.setOrderencode(StringUtilsEX.ToInt(orderencode));
			if (StringUtilsEX.ToInt(producttype) >= 0)
				payconfig.setProducttype(StringUtilsEX.ToInt(producttype));
			payconfig.setRetencode(StringUtilsEX.ToInt(retencode));
			payconfig.setVersion(version);
			payconfig.setMsgid(msgid);
			payconfig.setRettype(StringUtilsEX.ToInt(rettype));
		}
		
		payconfig.setFailUrl(fail_url);	
		payconfig.setLang(charset);		
		payconfig.setNotifyUrl(notify_url);
		payconfig.setPartner(partner);
		payconfig.setPaymentType(paymenttype);		
		payconfig.setPrivateKey(privatekey);	
		payconfig.setSellerid(sellerid);
		payconfig.setStatus(StringUtilsEX.ToInt(status));
		payconfig.setSuccessUrl(success_url);		
		payconfig.setSites(StringUtilsEX.ToInt(sites));		
	
		return payconfig;
	}
	
	@RequestMapping("/getlist")
	public ReusltItem getList(String paytype,String partner){
		ReusltItem item=new ReusltItem();
		try{			
			PayconfigExample example=new PayconfigExample();
			PayconfigExample.Criteria criteria=example.createCriteria();
			if(StringUtilsEX.ToInt(paytype)>0){
				criteria.andPaytypeEqualTo(StringUtilsEX.ToInt(paytype));
			}
			if(!StringUtilsEX.IsNullOrWhiteSpace(partner)){
				criteria.andPartnerEqualTo(partner.trim());
			}
		    List<Payconfig> list=payconfigService.selectByExample(example);
			item.setCode(0);
			item.setData(list);
			
		}catch(Exception e){
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("查询支付配置异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Data,"查询支付配置异常! 异常信息:", e,
					"/platform/paycongif/getList");
		}
		
		return item;
	}
}

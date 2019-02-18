<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">编辑支付配置信息</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post">
						<input name="id" type="hidden" value="${data.id }" />
						<input name="datapaytype" type="hidden" value="${data.paytype}" />
						<input name="datasites" type="hidden" value="${data.sites}" />
						<input name="dataiscredit" type="hidden" value="${data.iscredit}" />
						<input name="dataproducttype" type="hidden" value="${data.producttype}" />
						<input name="datasigntype" type="hidden" value="${data.signType}" />
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>支付类型：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<select id="paytype" name="paytype" class="the-form-select"
									onchange="TypeChange()">
									<option value="1">支付宝</option>
									<option value="2">环迅支付</option>
								</select>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>站点：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<select id="sites" name="sites" class="the-form-select"
								onchange="siteChange()">
									<option value="1">PC</option>
									<option value="2">APP</option>
									<option value="3">WAP</option>
								</select>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>合作身份者ID/商户号：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="partner" type="text"
										value="${data.partner }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>收款账号：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="sellerid" type="text"
										value="${data.sellerid }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>商户秘钥：</label>
								</div>
								<div class="tjcpxx-con-form1">
								<textarea class="tjcpxx-con-form1-text" name="privatekey" cols="" rows="">${data.privateKey }</textarea>
								</div>
							</div>
						<div id="divalipay">							
							<div id="divaliapp" style="display: none;">
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>支付宝公钥：</label>
								</div>
								<div class="tjcpxx-con-form1">
								<textarea class="tjcpxx-con-form1-text" name="publickey" cols="" rows="">${data.publicKey }</textarea>
								</div>
							</div>
							<%-- <div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>支付宝APPID：</label>
								</div>
								<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="appid" type="text"
										value="${data.appid }">
								</div>
							</div> --%>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>签名方式：</label>
								</div>
								<div class="tjcpxx-con-form1">
								 <select id="signtype" name="signtype" class="the-form-select">
										<option value="MD5">MD5(pc应用)</option>
										<option value="RSA">RSA(app应用)</option>
									</select>
								</div>
							</div>						
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label>防钓鱼时间戳：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="anti_phishing_key" type="text"
										value="${data.antiPhishingKey }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label>客户端IP地址：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="exter_invoke_ip" type="text"
										value="${data.exterInvokeIp }">
								</div>
							</div>
							
						</div>
				        <div id="divips" style="display: none;">
				           <div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>商户名称：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="sellername" type="text"
										value="${data.mername }">
								</div>
							</div>
				           <div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>版本号：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="version" type="text"
										value="${data.version }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>消息编号：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="msgid" type="text"
										value="${data.msgid }">
								</div>
							</div>
							 <div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>支付请求地址：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="actionUrl" type="text"
										value="${data.actionurl }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>支付接口加密方式：</label>
								</div>
								<div class="tjcpxx-con-form1">
								    <select id="orderencode" name="orderencode" class="the-form-select">
										<option value="5">MD5(默认)</option>
									</select>
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>返回接口加密方式：</label>
								</div>
								<div class="tjcpxx-con-form1">
								    <select id="retencode" name="retencode" class="the-form-select">
										<option value="17">MD5(默认)</option>
									</select>
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>返回方式：</label>
								</div>
								<div class="tjcpxx-con-form1">
								    <select id="rettype" name="rettype" class="the-form-select">
										<option value="1">S2S(默认)</option>
									</select>
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>币种：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<select id="currency" name="currency" class="the-form-select">
										<option value="156">人民币</option>
									</select>
								</div>
							</div>
							<!-- <div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>编码：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<select id="charset" name="charset" class="the-form-select">
										<option value="GB">GB(默认)</option>
									</select>
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>IPS支付类型：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<select id="paymenttype" name="paymenttype" class="the-form-select">
										<option value="01">借记卡(默认)</option>
										<option value="02">信用卡</option>
										<option value="03">IPS账号</option>
									</select>
								</div>
							</div> -->
							 <div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label>订单有效期 ：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="billvalidity" type="text"
										value="${data.billvalidity }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label>是否直连：</label>
								</div>
								<div class="tjcpxx-con-form1">
								    <select id="iscredit" name="iscredit" class="the-form-select" onchange="creditChange()">
										<option value="">默认</option>
										<option value="1">是</option>
									</select>
								</div>
							</div>
							<div id="creditdiv" style="display:none;">
							<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>银行号 ：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="bankno" type="text"
										value="${data.bankno }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>产品类型：</label>
								</div>
								<div class="tjcpxx-con-form1">
								    <select id="producttype" name="producttype" class="the-form-select">
										<option value=""></option>
										<option value="1">个人网银</option>
										<option value="2">企业网银</option>
									</select>
								</div>
							</div>
							</div>
				        </div>
				        <div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>编码：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<select id="charset" name="charset" class="the-form-select">
										<option value="utf-8">UTF-8(支付宝)</option>
										<option value="GB">GB(IPS)</option>
									</select>
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>支付宝或IPS支付类型：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<select id="paymenttype" name="paymenttype" class="the-form-select">
									    <option value="1">支付宝(默认)</option>
										<option value="01">IPS(默认)</option>
									</select>
								</div>
							</div>
                           <div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>异步回调地址：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="notify_url" type="text"
										value="${data.notifyUrl }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>同步通知页面地址(成功)：</label>
								</div>
								<div class="tjcpxx-con-form1">
										<input class="tjcpxx-fprm-inp" name="success_url" type="text"
										value="${data.successUrl }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label>同步通知页面地址(失败)：</label>
								</div>
								<div class="tjcpxx-con-form1">
										<input class="tjcpxx-fprm-inp" name="fail_url" type="text"
										value="${data.failUrl }">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title">状态：</div>
                            <div class="tjcpxx-con-form huise">
                            <c:choose>
                            <c:when test="${data.status==1}">
                             <input name='status' type='radio' value='0'>启用
                                <span class='marrig35'></span>
                                <input name='status' checked type='radio' value='1'>禁用
                            </c:when>
                            <c:otherwise>
                             <input name='status' checked type='radio' value='0'>启用
                                <span class='marrig35'></span>
                                <input name='status' type='radio' value='1'>禁用
                            </c:otherwise>
                            </c:choose>
                            </div>
                        </div>
				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title"></div>
					<div class="tjcpxx-con-form huise">
						<input class="preserve-inp" name="Save" type="button" value="保存">
						<input id="action" type="hidden" value="${action }"> <span
							class="marrig35"></span> 
							<input class="preserve-inp_hs" name=""
							type="button" value="取消" onclick="formCancel()">
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>
</div>

<script type="text/javascript" src="/resource/public/platform/js/ControlPanel/pay_config.js"></script>
<script>
function formCancel() {
	location.href = "payConfig_list";
}	
</script>
<%@page contentType="text/html; charset=utf-8" language="java"%>

<%
	//设置请求字符集 避免产生乱码	
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>B2B提交接口</title>
<style type="text/css">
input {
	background-color: #dcdcdc;
	height: 18px;
	font-size: 11pt
}

select {
	background-color: #dcdcdc;
	height: 25px
}
</style>
</head>

<body style="font-size: 11pt">
	<form name="inputForm" method="post" action="ipsb2c">


		<table width="450" cellspacing="0" cellpadding="5" border="1"
			align="center">

			<tr>
				<td colspan="2" align="center" style="background-color: #a9a9a9">B2B提交接口</td>
			</tr>
			<tr>
				<td style="width: 50%">商户号</td>
				<td style="width: 50%" align="center"><input type="text"
					name="pMerCode" id="pMerCode" maxlength="6" value="120101" /></td>
			</tr>
			<tr>
				<td>商户订单号</td>
				<td align="center"><input type="text" name="pBillNo"
					id="pBillNo" maxlength="30"
					value="<%=new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())%>" /></td>
			</tr>
			<tr>
				<td>订单金额</td>
				<td align="center"><input type="text" name="pAmount"
					id="pAmount" maxlength="10" value="30.00" /></td>
			</tr>
			<tr>
				<td>订单日期</td>
				<td align="center"><input type="text" name="pDate" id="pDate"
					maxlength="8"
					value="<%=new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date())%>" /></td>
			</tr>
			<tr>
				<td>币种</td>
				<td align="center"><input type="text" name="pCurrency"
					id="pCurrency" maxlength="3" value="RMB" /></td>
			</tr>
			<tr>
				<td>语言</td>
				<td align="center"><select name="pLang" id="pLang"
					style="width: 179px">
						<option value="GB">简体中文</option>
						<option value="EN">英文</option>
						<option value="BIG5">繁体中文</option>
				</select></td>
			</tr>


			<tr>
				<td>支付结果成功返回的商户URL</td>
				<td align="center"><input type="text" name="pSuccessUrl"
					id="pSuccessUrl"
					value="http://paynew.ips.net.cn/Result/DisPayResult.aspx" /></td>
			</tr>
			<tr>
				<td>支付结果失败返回的商户URL</td>
				<td align="center"><input type="text" name="pFailureUrl"
					id="pFailureUrl"
					value="http://paynew.ips.net.cn/Result/DisPayResult.aspx" /></td>
			</tr>
			<tr>
				<td>支付异常返回的商户URL</td>
				<td align="center"><input type="text" name="pErrorUrl"
					id="pErrorUrl" value="http://paynew.ips.net.cn/Error.aspx" /></td>
			</tr>
			<tr>
				<td>商户附加信息</td>
				<td align="center"><input type="text" name="pAttach"
					id="pAttach" maxlength="200" value="测试专用" /></td>
			</tr>
			<tr>
				<td>订单支付接口加密方式</td>
				<td align="center"><select name="pOrderEncodeType"
					id="pOrderEncodeType" style="width: 179px">
						<option value="2">Md5摘要认</option>
						<option value="0">无需加密</option>
				</select></td>
			</tr>
			<tr>
				<td>交易返回接口加密方式</td>
				<td align="center"><select name="pRetEncodeType"
					id="pRetEncodeType" style="width: 179px">
						<option value="11">MD5WITHRSA的签名认证</option>
						<option value="12">MD5的摘要认证</option>
				</select></td>
			</tr>

			<tr>
				<td>是否采用S2S返回</td>
				<td align="center"><select name="pRetType" id="pRetType"
					style="width: 179px">
						<option value="1">使用</option>
						<option value="0">不使用</option>
				</select></td>
			</tr>
			<tr>
				<td>S2S返回地址</td>
				<td align="center"><input type="text" name="pServerUrl"
					id="pServerUrl" /></td>
			</tr>

			<tr>
				<td>分账规则类型</td>
				<td align="center"><select name="pRuleType" id="pRuleType"
					style="width: 179px">
						<option value="ALT">可变规则</option>
						<option value="FIX">固定规则</option>
				</select></td>
			</tr>
			<tr>
				<td>分账金额的类型</td>
				<td align="center"><select name="pAmtType" id="pAmtType"
					style="width: 179px">
						<option value="0">百分比</option>
						<option value="1">固定金额</option>
				</select></td>
			</tr>
			<tr>
				<td>分账手续费类型</td>
				<td align="center"><select name="pFeeType" id="pFeeType"
					style="width: 179px">
						<option value="0">百分比</option>
						<option value="1">固定金额</option>
				</select></td>
			</tr>

			<tr>
				<td>分账手续费计算的基数</td>
				<td align="center"><input type="text" name="pFeeBase"
					id="pFeeBase" value="1" /></td>
			</tr>
			<tr>
				<td>分账指令</td>
				<td align="center"><select name="pInstruction"
					id="pInstruction" style="width: 179px">
						<option value="1">实时分账</option>
						<option value="2">延时分账</option>
				</select></td>
			</tr>

			<tr>
				<td>分账明细加密方式</td>
				<td align="center"><select name="pDisEncodeType"
					id="pDisEncodeType" style="width: 179px">
						<option value="1">base64(ASNI)</option>
				</select></td>
			</tr>
			<tr>
				<td>分账规则明细</td>
				<td align="center"><input type="text" name="pRuleDetails"
					id="pRuleDetails" value="120101,60,0,1|alashy@163.com,40,100,0" /></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;<input
					id="btnSubmit" type="submit" value="提 交"
					style="width: 60px; height: 30px; background-color: #a9a9a9" />&nbsp;&nbsp;&nbsp;
					<input id="btnCancle" type="reset" value="重 填"
					style="width: 60px; height: 30px; background-color: #a9a9a9" /> <input
					type="hidden" name="dosubmit" value="1" />
				</td>
			</tr>
		</table>

	</form>
</body>
</html>

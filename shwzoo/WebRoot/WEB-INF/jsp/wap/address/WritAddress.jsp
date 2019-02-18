<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="Content-Type" content="text/html">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<title>添加收货地址</title>
<link rel="stylesheet" type="text/css" href="/wap/css/Yform.css">
<link rel="stylesheet" type="text/css" href="/wap/css/css.css">
<script src="/wap/js/jquery-1.9.1.min.js"></script>
<script src="/wap/js/Yeffect16_0118.min.js"></script>
<script src="/wap/js/Yform.js"></script>
<script src="/wap/js/js.js"></script>
<script src="/wap/js/ReceiverAddres.js"></script>
</head>
<body>
<ul class="Eidvua-dizhi">
	<li><span>收货人姓名</span><input id="name" type="text" placeholder="请输入您的姓名"></li>
    <li><span>手机号码</span><input id="mobile" type="text" placeholder="请输入您的手机号码"></li>
    <li><span>收货地址</span>
    	<div>
        	<select class="yselect" id="province" yplaceholder=" " yplaceholder-opacity="0.6">
            	<option>请选择</option>
            </select>
        </div>
        <div>
        	<select class="yselect" yplaceholder=" " id="city"  yplaceholder-opacity="0.6">
            	<option>请选择</option>
            </select>
        </div>
        <div>
        	<select class="yselect" yplaceholder=" " id="area" yplaceholder-opacity="0.6">
            	<option>请选择</option>
            </select>
        </div>
    </li>
    <li><span>详细地址</span><textarea id="address" placeholder="请输入详细地址"></textarea></li>
</ul>
<div class="Eidvua-backdiv">
	<input id="moren" type="checkbox" checked="checked" class="ycheckbox" data-html="<nobr>设为默认</nobr>">
</div>
<input class="Eidvuafrbtn" type="button" onclick="Save();" value="保存">
<div class="clear"></div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	receiveraddresss.GetProvData();
})
function Save(){
	receiveraddresss.editaddress();
}
</script>
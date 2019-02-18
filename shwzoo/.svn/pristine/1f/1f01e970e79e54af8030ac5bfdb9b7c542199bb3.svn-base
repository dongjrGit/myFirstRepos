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
<title>收货地址</title>
<link rel="stylesheet" type="text/css" href="/wap/css/Yform.css">
<link rel="stylesheet" type="text/css" href="/wap/css/css.css">
<script src="/wap/js/jquery-1.9.1.min.js"></script>
<script src="/wap/js/Yeffect16_0118.min.js"></script>
<script src="/wap/js/Yform.js"></script>
<script src="/wap/js/js.js"></script>
<script src="/wap/js/address/ReceiverAddres.js"></script>
</head>
<body>
<ul class="souhuodz-gl" id="receiveraddress">
<script type="text/html" id="addreslist">
{{each list as address i}}
	<li class="current">
    	<a href="#">
        	<h2><b>{{address.name}}</b>158****4668</h2>
            <p>{{address.provincename}}{{address.cityname}}{{address.areaname}}{{address.address}}</p>
        </a>
        <div>
        	<input checked type="radio" class="yradio" name="deft" data-html="<nobr>设为默认</nobr>">
            <span><a id="updateaddres"><img src="/wap/images/index_33.png">编辑</a><a id="deleteaddres"><img src="/wap/images/index_34.png">删除</a></span>
        </div>
    </li>
{{/each}}
</script>
</ul>
<a href="/wap/receiveraddress/WritAddress" class="ImMain-tcbtn">新建地址</a>
</body>
</html>
<script tyep="text/javascript">
$(document).ready(function(){
	receiveraddresss.getlist();
})
</script>
    
    

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
<title>物流信息</title>
<link rel="stylesheet" type="text/css" href="/wap/css/Yform.css">
<link rel="stylesheet" type="text/css" href="/wap/css/css.css">
<script src="/wap/js/jquery-1.9.1.min.js"></script>
<script src="/wap/js/Yeffect16_0118.min.js"></script>
<script src="/wap/js/Yform.js"></script>
<script src="/wap/js/js.js"></script>
<script src="/js/public.js"></script>
<script src="/wap/js/LogisticInfo.js"></script>
</head>
<body>
<div id="logisticinfo">
<script type="text/html" id="logisticInfolist">
{{each list as logistic i}}
<div class="wuliu-ddh">
	<a href="#">
    	<img src="/wap/images/index16.jpg">
        <div class="R">
        	<b>物流状态<font>已签收</font></b>
        	<p>运单号：70185803777765<br>信息来源：中通快递</p>
        </div>
    </a>
</div>
<hr class="back"/>
<ul class="wuliu-listS">
	<li class="current"><i></i>【北京市】快件已签收，感谢您使用中通快递！期待再次为您服务<span>2016-02-01 13:36:32</span></li>
    <li><i></i>【北京市】东五环外派件员：何沙乐1511235689正在为您派件<span>2016-02-01 13:36:32</span></li>
    <li><i></i>【北京市】东五环外派件员：何沙乐1511235689正在为您派件<span>2016-02-01 13:36:32</span></li>
    <li><i></i>【北京市】北京市内部已发出<span>2016-02-01 13:36:32</span></li>
    <li><i></i>【北京市】北京市内部已发出<span>2016-02-01 13:36:32</span></li>
    <li><i></i>商家已发货<span>2016-02-01 13:36:32</span></li>
</ul>
{{/each}}
</script>
</div>
</body>
</html>
<script type="text/javascript">
      $(document).ready(function(){
    	  logisticInfo.getlogisticInfo();
      })
</script>




    
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
<title>优惠券</title>
<link rel="stylesheet" type="text/css" href="/wap/css/Yform.css">
<link rel="stylesheet" type="text/css" href="/wap/css/css.css">
<script src="/wap/js/jquery-1.9.1.min.js"></script>
<script src="/wap/js/Yeffect16_0118.min.js"></script>
<script src="/wap/js/Yform.js"></script>
<script src="/wap/js/Coupon.js"></script>
<script src="/wap/js/js.js"></script>
<script src="/js/artTemplate.js"></script>
</head>
<body class="paswrd_gai" id="ceshi">
<script type="text/html" id="couponlist">
{{each list as coupon i}}
<ul class="youhui-quan" id="coupon">
<li>
    	<a href="#">
            <h3><font>￥</font>{{coupon.facevalue}}<span>{{coupon.coupontype}}</span></h3>
            <div><b>{{coupon.shopname}}</b>有效期：{{coupon.providetime|toDateDay}}--{{coupon.outtime|toDateDay}}</div>
            {{if coupon.isuser==true}}
               <img src="images/index_27a.png">
             {{else if coupon.isuser=false}}
             {{else}}<img src="images/index_27.png">
             {{/if}}
        </a>
    </li>
</ul>
{{/each}}
</script>
</body>
</html>
<script type="text/javascript">
 $(document).ready(function(){
	 Couponlist.getList(1,"",0);
 })
</script>

    
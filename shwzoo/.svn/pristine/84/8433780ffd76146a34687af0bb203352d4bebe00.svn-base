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
<title>全部订单</title>
<link rel="stylesheet" type="text/css" href="/wap/css/Yform.css">
<link rel="stylesheet" type="text/css" href="/wap/css/css.css">
<script src="/wap/js/jquery-1.9.1.min.js"></script>
<script src="/wap/js/Yeffect16_0118.min.js"></script>
<script src="/wap/js/Yform.js"></script>
<script src="/wap/js/js.js"></script>
<script src="/wap/js/OrderAll.js"></script>
<script src="/js/artTemplate.js"></script>
</head>
<body>

<section class="dingdan-list">
<script type="text/html" id="orderlist">
<div id="order">
   {{each list as order i}}
	<div class="Hd"><b>订单编号:{{order.code}}</b>
        {{if order.status==0}}
         <span>待付款</span>
        {{else if order.status==1}}
         <span>待发货</span>
        {{else if order.status==2}}
         <span>待收货</span>
        {{else if order.status==3}}
         <span>已确认收货</span>
        {{else if order.status==4}}
         <span>未付款取消申请</span>
        {{else if order.status==5}}
         <span>未付款已取消</span>
        {{else if order.status==6}}
         <span>已付款已取申请</span>
        {{else if order.status==7}}
         <span>已付款已取消</span>
        {{else if order.status==8}}
         <span>已完成</span>
        {{else if order.status==9}}
         <span>申请退款退货中</span>
        {{else if order.status==10}}
         <span>已退款</span>
        {{/if}}
  <ul>
    <li>
        <a href="商场-3-商品详情页.html">
         <img src="{{order.children.productimg}}" class="L">
         <div class="R">
           <b>{{order.shopName}}</b>
           <p>手机号码：137***1166</p>
         </div>
        </a>
     </li>
  </ul>
 <div class="FT">共{{order.children.productcount}}件,实付款:<span>￥{{order.actualPay}}</span><a herf="#">再次购买</a></div>
{{/each}}
</div>
    </script>
</section>
<hr class="back"/>

</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	//var status = GetQueryStringByName("status");
	Order.getlist(1,status);
})
</script>
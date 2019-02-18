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
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="/wap/css/Yform.css">
<link rel="stylesheet" type="text/css" href="/wap/css/css.css">
<script src="/wap/js/jquery-1.9.1.min.js"></script>
<script src="/wap/js/Yeffect16_0118.min.js"></script>
<script src="/wap/js/Yform.js"></script>
<script src="/wap/js/js.js"></script>
<script src="/wap/js/OrderDetail.js"></script>
</head>
<body>
<script type="text/html" id="orderlist">
<div id="orderdetails">
{{each list as orderdetail i}}
<div class="pordct_links pordct_linksComt">
    {{if orderdetail.status==3}}
	<img src="/images/index_17.png" class="wcz">
    {{/if}}
	<a class="backnone" href="javascript:void(0)">订单号:{{orderdetail.code}}</a>
    <a href="javascript:void(0)">
    <span class="rd">
       {{if orderdetail.status==0}}待付款
       {{else if orderdetail.status==1}}待发货
       {{else if orderdetail.status==2}}待收货
       {{else if orderdetail.status==3}}已确认收货
       {{else if orderdetail.status==4}}未付款取消申请
       {{else if orderdetail.status==5}}未付款已取消
       {{else if orderdetail.status==6}}已付款已取申请
       {{else if orderdetail.status==7}}已付款已取消
       {{else if orderdetail.status==8}}已完成
       {{else if orderdetail.status==9}}申请退款退货中
       {{else if orderdetail.status==10}}已退款
       {{/if}}                               
    </span></a>
</div>	
   <hr class="back"/>
<div class="Shipping-dz">
<h2><img src="images/index_18.png">{{orderdetail.consignee}}<img src="/images/index_19.png">{{orderdetail.telPhone}}</h2>
    <p><i>默认</i>{{orderdetail.address}}</p>
</div>
<hr class="back"/>
<div class="Shipping-xqH">店铺名称</div>
<ul class="Shipping-xqul">
	<li>
    	<a href="#">
        	<img src="images/index16.jpg">
            <div class="R"><b>{{orderdetail.children.productname}}</b><span>x{{orderdetail.children.productcount}}  颜色：丝滑拿铁</span></div>
            <i>￥{{orderdetail.actualPay}}</i>
        </a>
    </li>
</ul>
<a class="Shipping-callbtn" href="#"><img src="images/index_22.png">联系客服</a>
<hr class="back"/>
<ul class="Shipping-xinxi">
	<li>
    	<div class="H"><span>支付方式</span>
          <font>
            {{if orderdetail.payType== 0}}在线支付
            {{else if orderdetail.payType== 0}}微信支付
            {{else if orderdetail.payType== 0}}余额支付
            {{else if orderdetail.payType== 0}}优惠券支付支付
            {{else if orderdetail.payType== 0}}混合支付支付
          </font></div>
    </li>
    <li>
    	<div class="H"><span>配送信息</span><font>{{if orderdetail.transportMode==0}}快递配送{{else if}}自取</font></div>
        <div><span>运费</span><font>￥{{orderdetail.freight}}</font></div>
        <div><span>送货日期</span><font>2015-11-08</font></div>
    </li>
    <li>
    	<div class="H"><span>发票信息</span></div>
        <div><span>发票抬头：{{orderdetail.invoiceTitle}}</span></div>
        <div><span>发票内容：{{orderdetail.invoiceContent}}</span></div>
    </li>
</ul>
<ul class="Shipping-xinxi">
    <li>
    	<div class="H"><span>卖家留言</span></div>
        <div>及时送达</div>
    </li>
</ul>
<!--评论-->
<hr class="back"/>
<ul class="Shipping-xinxi">
	<li>
    	<div class="H"><font>共{{orderdetail.count}}件商品</font></div>
    </li>
    <li class="red">
        <div><span>商品费用</span><font>￥{{orderdetail.price}}</font></div>
        <div><span>优惠券抵扣</span><font>￥{{orderdetail.CouponPay}}</font></div>
        <div><span>积分抵扣</span><font>￥{{orderdetail.pointsPay}}</font></div>
        <div><span>经彩豆抵扣</span><font>￥{{orderdetail.pulsePay}}</font></div>
    </li>
</ul>
<hr class="back"/>
<!--店铺-->
<div class="pordct_dp">
	<div>
    	<p><img src="images/index1.jpg">奇酷旗舰店</p>
        <a href="#">进店逛逛</a>
    </div>
    <ul>
    	<li><b>132</b>全部商品</li>
        <li><b>3122</b>收藏店铺</li>
        <li><b>88.09%</b>好评率</li>
    </ul>
</div>
{{/each}}
</div>
</script>
</body>
</html>
<script typt="text/javascript">
$(document).ready(function(){
	//var orderid = GetQueryStringByName("orderid");
	orderDetail.getlist(1);
})
</script>







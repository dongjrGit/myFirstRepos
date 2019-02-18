<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="Content-Type" content="text/html">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta name="keywords" content="">
<meta name="description" content="">
<title>绿色中国</title>
<link rel="stylesheet" href="/resource/app/css/greenLife.css" />
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/jquery.1.10.2.min.js"></script>
<script type="text/javascript"
	src="/resource/app/js/Yeffect16_0118.min.js"></script>
<script type="text/javascript" src="/resource/app/fx/js/greenLife.js"></script>
</head>
<body>
	<!--septop begin-->
	<div class="septop  bjwhite">
		<div class="bigsize">绿色中国</div>
	</div>
	<!--septop stop-->
	<img src="/resource/app/images/img7.jpg" class="img-responsive" />
	<div class="modi-fxul fix lszgbtul">
		<ul class="fix">
			<li><a href="/pub/address/list?path=/app/pl/hslist"><i><img
						src="/resource/app/images/lszg_ico1.png" class="img-responsive" /></i>
					<p>地方馆</p></a></li>
			<li><a href="url://proclass"><i><img
						src="/resource/app/images/lszg_ico2.png" class="img-responsive" /></i>
					<p>特产大全</p></a></li>
			<li><a href="#"><i><img
						src="/resource/app/images/lszg_ico3.png" class="img-responsive" /></i>
					<p>绿色追溯</p></a></li>
			<li><a href="lsgslist"><i><img
						src="/resource/app/images/lszg_ico4.png" class="img-responsive" /></i>
					<p>绿色故事</p></a></li>
		</ul>
	</div>
	<hr class="back" />
	<div class="lszg-title fix">
		<span class="bolder">实时推荐</span><span class="huise">地道推荐，猜你喜欢</span>
	</div>
	<ul class="sep-byzslist fix">
		<c:forEach var="item" items="${rmlist }">
			<li><a href="url://prodetailed|||${item.spuid}"> <img src="${item.himgurl }" />
					<h3>${item.name }</h3>
					<p class="orange">
						￥
						<fmt:formatNumber type="number" value="${item.appprice }"
							pattern="0.00" maxFractionDigits="2" />
					</p>
			</a></li>
		</c:forEach>

	</ul>
	<hr class="back" />
	<div class="lszg-title fix borderbot">
		<span class="bolder">热门城市</span><span class="huise">热门城市 一手掌握</span>
	</div>
	<ul class="sep-zlzx fix">
		<c:forEach var="item" items="${snewslist}">			
			<li class="fix"><a href="acdetails?id=${item.id}"> <img
					src="${item.imgurl}" />
				<div class="sep-zlzx-r">
						<h3>${item.title}</h3>
						<p>${item.tabloid}</p>
					</div>
			</a></li>
		</c:forEach>
	</ul>
	<script>
		$(function() {
			Yeffect.resizeHtWstr(".sep-byzslist li:visible",
					".sep-byzslist li img", 296, 296);
			Yeffect.resizeHtWstr(".sep-zlzx li:visible", ".sep-zlzx li img",
					199, 49);
		})
	</script>
</body>
</html>
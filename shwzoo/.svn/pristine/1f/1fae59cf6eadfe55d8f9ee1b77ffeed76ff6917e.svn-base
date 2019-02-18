<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
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
<title>区域列表</title>
<link rel="stylesheet" href="/resource/app/css/greenLife.css" />
<script src="/resource/jquery-1.9.1.min.js"></script>
<script src="/resource/app/js/js/Yform.js"></script>
</head>
<body style="background: #f0f0f0;">
	<script>
		$(function() {
			/////////点击字母控制位置///////////
			var htmlBody = $("html,body");
			var boxFootScroll = $(".boxFoot-scroll h2");
			var diquselectBoxFootnava = $(".diquselect-boxFoot nav a");
			var diquselectBoxFoot = $(".boxFoot-scroll");
			var windowObjs = $(window);
			diquselectBoxFootnava.click(function() {
				htmlBody.scrollTop(99999);
				var suotyin = diquselectBoxFootnava.index(this);
				var sctopzhi = boxFootScroll.eq(suotyin).offset().top
						- boxFootScroll.eq(0).offset().top;
				diquselectBoxFoot.scrollTop(sctopzhi);
			})
			tilieState();
			diquselectBoxFoot.scroll(tilieState);
			windowObjs.scroll(tilieState);
			windowObjs.resize(tilieState);
			function tilieState() {
				var scrollTops = diquselectBoxFoot.scrollTop();
				var sctopQE0 = boxFootScroll.eq(0).offset().top;
				var diquselectBoxFootoff = diquselectBoxFoot.offset().top;

				var windowObjssctop = windowObjs.scrollTop();
				boxFootScroll.each(function(index, element) {
					var sctopxx = $(this).offset().top - sctopQE0;
					if (scrollTops >= sctopxx - 1) {
						$(this).addClass("fixd").find("b").css("top",
								diquselectBoxFootoff - windowObjssctop);
					} else {
						$(this).removeClass("fixd");
					}
				});
			}

		})
	</script>
	<div class="qyxz-top fix">
		区域选择 <i></i>
	</div>
	<section class="diquselect-boxUp">
		<div class="diquselect-dingwei">
			<b><a href="${path}?code=-1&pname=全国">全国</a></b>
		</div>
		<!--<div class="diquselect-tile">
	当前所在城市
</div>
<ul class="diquselect-ul">
	<li><a href="会员主页-2-我的信息.html">北京</a></li>
</ul>-->
		<div class="diquselect-tile">热门城市</div>
		<ul class="diquselect-ul">
			<c:forEach var="item" items="${hlist }">
				<li><a href="${path}?code=${item.code}&pname=${item.name}">${item.name}</a></li>
			</c:forEach>
		</ul>
	</section>
	<section class="diquselect-boxFoot">
		<nav>
			<c:forEach var="item" items="${list }">
				<a>${itm.sp}</a>
			</c:forEach>
		</nav>
		<div class="boxFoot-scroll">
			<c:forEach var="item" items="${list }">
				<h2>
					<b>${item.sp}</b>
				</h2>
				<p>
					<c:forEach var="item1" items="${item.list }">
						<a href="${path}?code=${item1.code}&pname=${item1.name}">${item1.name}</a>
					</c:forEach>
				</p>
			</c:forEach>
		</div>
	</section>
</body>
</html>

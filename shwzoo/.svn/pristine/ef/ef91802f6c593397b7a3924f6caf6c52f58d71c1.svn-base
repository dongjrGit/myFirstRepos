<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>地方馆-优质特产</title>
<link rel="stylesheet" href="/resource/app/css/greenLife.css" />
<link rel="stylesheet"
	href="/resource/public/commonjs/pulltorefresh/iscoll5.css" />
<script type="text/javascript" src="/resource/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/iscroll-probe.js"></script>
<script type="text/javascript"
	src="/resource/app/js/Yeffect16_0118.min.js"></script>
<script type="text/javascript"
	src="/resource/app/js/fx/greenLife.js?v=1.w1"></script>
<script type="text/javascript" src="/resource/app/js/pub.js"></script>
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/iscroll.js"></script>
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/pulltorefresh.js"></script>
<link rel="stylesheet"
	href="/resource/public/commonjs/pulltorefresh/pulltorefresh.css?v=1.12" />
<script type="text/javascript" src="/resource/app/js/fx/greenLife.js"></script>
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/pulliscoll5.js"></script>
</head>
<body>
	<!--septop begin-->
	<div class="septop rowreturn  bjwhite"
		style="position: absolute; z-index: 2;">
		<div class="bigsize">
			<span><a href="/pub/address/list?path=/app/pl/hslist">${pname}</a>
			</span><i></i>
		</div>
		<div id="rowreturn" onclick="goback()"></div>
	</div>
	<div id="wrapper">
		<div id="scroller">
			<!--septop stop-->
			<!--banner-->
			<div class="pullDown">
				<span class="pullDownIcon">&nbsp;</span> <span class="pullDownLabel"></span>
			</div>
			<div class="fx-banner" id="bannertop">
				<ul>
					<li><img
						src="/resource/app/images/children_game_concept01.jpg"></li>
					<li><img
						src="/resource/app/images/children_game_concept01.jpg"></li>
					<li><img
						src="/resource/app/images/children_game_concept01.jpg"></li>
					<li><img
						src="/resource/app/images/children_game_concept01.jpg"></li>
					<li><img
						src="/resource/app/images/children_game_concept01.jpg"></li>
				</ul>
				<p>
					<i></i><i></i><i></i><i></i><i></i>
				</p>
			</div>

			<!--banner***-->
			<ul class="sep-byzsq fix ">
				<li class="active"><a >优质特产</a></li>
				<li><a href="cclist">文化特色</a></li>
				<li ><a href="sdlist">特产名录</a></li>
			</ul>
			<!-- <hr class="back" />	 -->
			<ul class="sep-byzslist fix data-list">
				<c:forEach var="item" items="${list}">
					<li><a href="url://prodetailed|||${item.spuid}"> <img src="${item.imgurl_app }" />
							<h3 style="height: 0.99rem">${item.name }</h3>
							<p class="orange">￥${item.appprice }</p>
					</a></li>
				</c:forEach>
			</ul>
			<div class="pullUp"></div>
		</div>
	</div>
	<input type="hidden" id="page" value="${page}" />
	<input type="hidden" id="code" value="${code}" />
	<div id="contentx" style="display: none;">
		<li><a href="#"> <img src="{imgurl_app}" />
				<h3 style="height: 0.99rem">{name}</h3>
				<p class="orange">￥{appprice}</p>
		</a></li>
	</div>
</body>
</html>
<script>
	var index = 1;
	var type = -1;
	function goback() {
		window.location.href = "/app/news/nav";
	}
	$(function() {
		bannertop.init(1);
		Yeffect.mobileBanner(".fx-banner ul", ".fx-banner ul li",
				".fx-banner p i", "", "", "current", 5000);
		var fx_banner = $(".fx-banner");
		Yeffect.resizeHtW(fx_banner, fx_banner, 640, 248);
		Yeffect.resizeHtWstr(".sep-zlzx li:visible", ".sep-zlzx li img", 199,
				49);

		function pullDownAction() {
			window.location.reload();
		}
		;
		function pullUpAction() {

			getlist(false);
		}
		;
		iscool5.init(pullDownAction, pullUpAction);

	});
	function getclassdata(id) {
		type = id;
		index = 1;
		getlist(true);
	}
	function getlist(bol) {
		if (!bol) {
			if (parseInt($("#page").val()) <= index) {
				return;
			}
		}
		index++;
		$.ajax({
			url : "/app/pl/hlist",
			type : "post",
			async : false,
			data : {
				ch : 1,
				type : 0,
				code : $("#code").val(),
				page : index,
				size : 10
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					$("#page").val(data.page);
					if (bol) {
						$(".data-list").html("");
					}
					for (var i = 0; i < data.data.length; i++) {
						var val = data.data[i];
						var shtml = $("#contentx").html();
						shtml = shtml.replace("{imgurl_app}", val.imgurl_app);
						shtml = shtml.replace("{name}", val.name);
						shtml = shtml.replace("{appprice}", val.appprice);
						$(".data-list").append(shtml);
					}
				}
			},
			error : function() {
			}
		});
	};
</script>

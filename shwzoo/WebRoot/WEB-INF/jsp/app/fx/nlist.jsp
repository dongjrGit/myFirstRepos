<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta name="keywords" content="">
<meta name="description" content="">
<title>中绿资讯</title>
<link rel="stylesheet" href="/resource/app/css/greenLife.css" />
<script type="text/javascript" src="/resource/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="/resource/app/js/Yeffect16_0118.min.js"></script>
<script type="text/javascript"
	src="/resource/app/js/fx/greenLife.js?v=1.w1"></script>
<script type="text/javascript" src="/resource/app/js/fx/pub.js"></script>
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/iscroll.js"></script>
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/pulltorefresh.js"></script>
<link rel="stylesheet"
	href="/resource/public/commonjs/pulltorefresh/pulltorefresh.css?v=1.12" />
</head>
<body>
	<div class="septop rowreturn fixed">
		<span class="bigsize">中绿资讯</span>
		<div id="rowreturn" onclick="goback()"></div>
	</div>

	<ul class="sep-dqxz rowreturn fix">
		<li class="septop_l"><p>
				<span>全部</span>
			</p></li>
		<li class="moods"><p>
				<span>人气</span><i></i>
			</p></li>
		<li class="active"><p>
				<span>推荐</span>
			</p></li>
	</ul>

	<div id="wrapa" class="areacon">
		<div id="scro">
			<div class="close">X</div>
			<ul ID="l_area">
			</ul>
		</div>
	</div>
	<div id="wrapper">
		<ul class="sep-zlzx fix  data_list">
			<c:forEach var="item" items="${list}">
				<li class="fix"><a href="#"> <img src="${item.imgurl}" />
						<div class="sep-zlzx-r">
							<h3>${item.title}</h3>
							<p>${item.tabloid}</p>
						</div>
				</a></li>
			</c:forEach>
		</ul>
	</div>
	<input type="hidden" id="page" value="${page}" />
	<script>
		var index = 1, size = 10;
		$(function() {
			fixed('.septop');
			Yeffect.resizeHtWstr(".sep-cyjdimg li:visible",
					".sep-cyjdimg li img", 640, 360);

		});
		var myScroll1, generatedCount = 0;
		function inita() {
			screenHeight('.areacon', 0);
			myScroll1 = new iScroll('wrapa', {});
			$('.areacon').hide();
		}
		document.addEventListener('DOMContentLoaded', function() {
			setTimeout(inita, 1);
		}, false);
		refresher.init({
			id : "wrapper",
			pullDownAction : Refresh,
			pullUpAction : Load
		});
		var generatedCount = 0;
		function Refresh() {
			setTimeout(function() {
				index=1;// <-- Simulate network congestion, remove setTimeout from production!
				getlist(true);
				wrapper.refresh();
				/****remember to refresh after  action completed！ ---yourId.refresh(); ----| ****/

			}, 1000);

		}

		function Load() {
			setTimeout(
					function() {

						if (parseInt($("#page").val()) <= index) {
							wrapper.refresh();
							return;
						}
						index++;
						getlist(false);
						
						wrapper.refresh();
					}, 1000)
		}
		function goback() {
			window.location.href = "/app/news/nav";
		}
		function getlist() {
			for (i = 0; i < 2; i++) {
				$(".data_list").append(
								'<li class="fix"><a href="#"> <img src="/resource/app/images/img1.jpg" /><h3>'
										+ (++generatedCount)
										+ '</h3><p>柴沟堡熏肉是有名的熟肉制品，是河北省怀安县柴沟堡镇传统的特色名吃。距今已有200多年的…</p></div></a></li> ');
			}
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>特产名录-地方馆</title>
<link rel="stylesheet" href="/resource/app/css/greenLife.css" />
<link rel="stylesheet"
	href="/resource/public/commonjs/pulltorefresh/iscoll5.css" />
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/jquery.1.10.2.min.js"></script>
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/iscroll-probe.js"></script>
<script type="text/javascript"
	src="/resource/app/js/Yeffect16_0118.min.js"></script>
<script type="text/javascript"
	src="/resource/app/js/pub.js"></script>
<script type="text/javascript" src="/resource/app/js/fx/greenLife.js"></script>
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/pulliscoll5.js"></script>
</head>
<body>
	<div class="septop rowreturn  bjwhite"
		style="position: absolute; z-index: 2;">
		<div class="bigsize">
			<span><a href="/pub/address/list?path=/app/pl/sdlist">${pname}</a></span><i></i>
		</div>
		<div id="rowreturn" onclick="goback()"></div>
	</div>
	<div id="wrapper">
		<div id="scroller">
			<div class="pullDown">
				<span class="pullDownIcon">&nbsp;</span> <span class="pullDownLabel"></span>
			</div>
			<div class="fx-banner"  id="bannertop">
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
			<ul class="sep-byzsq fix ">
				<li><a href="hslist">优质特产</a></li>
				<li><a href="cclist">文化特色</a></li>
				<li class="active"><a>特产名录</a></li>
			</ul>
			<!--sep-zulb begin-->
			<ul class="sep-zulb fix">
				<li class="active"><a href="javascript:void(0)"
					onclick="getclassdata(0)">热门</a></li>
				<c:forEach var="item" items="${clslist}">
					<li><a href="javascript:void(0)"
						onclick="getclassdata(${item.id})">${item.name}</a></li>
				</c:forEach>
			</ul>
			<!--sep-zulb stop-->
			<ul class="sep-zlzx fix data-list">
				<c:forEach var="item" items="${list}">
					<li class="fix"><a href="acdetails?id=${item.id}"> <img src="${item.imgurl}" />
							<div class="sep-zlzx-r">
								<h3>${item.title}</h3>
								<p>${item.tabloid}</p>
							</div>
					</a></li>
				</c:forEach>
			</ul>
			<div class="pullUp"></div>
		</div>
	</div>
	<input type="hidden" id="page" value="${page}" />
	<input type="hidden" id="code" value="${code}" />
	<div id="contentx" style="display: none;">
		<li class="fix"><a href="acdetails?id={id}"> <img src="{imgurl}" />
				<div class="sep-zlzx-r">
					<h3>{title}</h3>
					<p>{tabloid}</p>
				</div>
		</a></li>
	</div>
</body>
</html>
<script>
	var index = 1;
var type=-1; 
function goback() {
	window.location.href = "/app/news/nav";
}
	$(function() {	bannertop.init(1);
		Yeffect.mobileBanner(".fx-banner ul", ".fx-banner ul li",
				".fx-banner p i", "", "", "current", 5000);
		var fx_banner = $(".fx-banner");
		Yeffect.resizeHtW(fx_banner, fx_banner, 640, 248);
		Yeffect.resizeHtWstr(".sep-zlzx li:visible", ".sep-zlzx li img", 199,
				49);
	
		function pullDownAction() {
			window.location.reload();
		};
		function pullUpAction() {
			getlist(false);
		};
		iscool5.init(pullDownAction, pullUpAction);
	
		
	});
	function  getclassdata(id)
	{
		type=id;
	index = 1;
	getlist(true);
		}
	function getlist(bol) {
		if(!bol)
			{
		if (parseInt($("#page").val()) <= index) {
			return;
		}}
		index++;s
		$.ajax({
			url : "/app/pl/clist",
			type : "post",
			async : false,
			data : {
				ch : 1,
				type : 3,
				code : $("#code").val(),
				page : index,
				size : 10,
				stype:type
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					$("#page").val(data.page);
					if(bol)
						{
						$(".data-list").html("");
						}
					for (var i = 0; i < data.data.length; i++) {
						var val = data.data[i];
						var shtml = $("#contentx").html();
						shtml = shtml.replace("{imgurl}", val.imgurl);
						shtml = shtml.replace("{id}", val.id);
						shtml = shtml.replace("{title}", val.title);
						shtml = shtml.replace("{tabloid}", val.tabloid);
						$(".data-list").append(shtml);
					}

				}
			},
			error : function() {
			}
		});
	};
</script>
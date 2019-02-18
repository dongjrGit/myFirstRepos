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
<title>${title}-绿色中国</title>
<link rel="stylesheet" href="/resource/app/css/greenLife.css" />
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/jquery.1.10.2.min.js"></script>
<script type="text/javascript" src="/resource/app/js/fx/greenLife.js"></script>
</head>
<body>
	<!--septop begin-->
	<div class="septop rowreturn bjwhite bjhs">
		<div class="bigsize">${title}</div>
		<div id="rowreturn" onclick="goback('${backurl}')"></div>
	</div>
	${content}
	<script>
	function goback(url) {
		window.history.go(-1);
	}
	</script>
</body>
</html>
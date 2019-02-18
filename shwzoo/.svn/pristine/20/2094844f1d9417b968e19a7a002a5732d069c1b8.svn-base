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
<title>我的信息</title>
<link rel="stylesheet" type="text/css" href="/wap/css/Yform.css">
<link rel="stylesheet" type="text/css" href="/wap/css/css.css">
<script src="/wap/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="/wap/js/Yeffect16_0118.min.js"></script>
<script src="/wap/js/Yform.js"></script>
<script src="/wap/js/js.js"></script>
<script src="/wap/js/UserInfo.js"></script>
</head>
<body>
<script type="text/html" id="userinfo">
{{each list userinfo i}}
<div id="users">
<hr class="back"/>
<a class="Imxixi-touxiang" href="#"><span>头像</span><img src="{{userinfo.imgUrl}}"></a>
<hr class="back"/>
<!--信息-->
<ul class="Imxixi-ztxx">
	<li><span>姓名</span><font>{{userinfo.realName}}</font></li>
    <li class="nianl"><span>年龄</span><font>{{userinfo.age}}</font><select><option>25</option><option>27</option><option>24</option><option>23</option><option>22</option></select></li>
    <li class="riqi"><span>出生日期</span><font>{{userinfo.userinfo}}</font><input type="date" value="1993/12/10"></li>
</ul>

<hr class="back"/>
<!--设置-->
<div class="ImMain_gotoGp">
	<a href="会员主页-2-我的信息-选择城市.html" class="ImMain_goto"><span>故乡</span><font>{{userinfo.hometown}}</font></a>
    <a href="会员主页-2-我的信息-选择城市.html" class="ImMain_goto"><span>所在地</span><font>{{userinfo.location}}</font></a>
</div>
<hr class="back"/>
<a href="会员主页-2-我的信息-修改密码.html" class="ImMain_goto lan"><span>修改密码</span><font>修改</font></a>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</div>
{{/each}}
</script>
<script>
$(function(){
	$(".Imxixi-ztxx .nianl select")	.change(function(){
		$(this).parents("li").find("font").text($(this).val());
	});
	
	$(".Imxixi-ztxx .riqi input")	.change(function(){
		$(this).parents("li").find("font").text($(this).val());
	});
	
})
</script>
</body>
</html>
 <script type="text/javascript">
    $(document).ready(function(){
    	UserInfo.getList("","");
    })
 </script>
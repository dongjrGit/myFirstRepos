<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品管理-商品列表</title>
<meta name="title" content="商品管理——商品列表" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="refresh" content="1200" />
<link rel="shortcut icon" type="image/x-icon" href="images/favicon_shortcut.ico" />

</head>
<body>   	
<div class="mainright">
	<!--l_wzmb  开始 -->
	<div class="l_wzmb">
  	<div class="l_wzmbtop">
    	<ul>
        	<li><a href="mb_20151010.html">时间配置</a></li>
            <li class="l_wzmbtop_sx">|</li>
            <li class="sj_hover"><a href="mb_20151010_01.html">经营范围配置</a><span class="sj-img"></span></li>
        </ul>
    </div><!--l_wzmbtop   stop -->
    <div class="h_imgcon fix">
    	<ul class="fix">
    		<!--<li><img src="images/bj-all_02.jpg"/></li>
    		<li><img src="images/img3.png"/></li>
    		<li><img src="images/img3.png"/></li>
    		<li><img src="images/img3.png"/></li>
    		<li><img src="images/img3.png"/></li>
    		<li><img src="images/img3.png"/></li>
    		<li><img src="images/img3.png"/></li>
    		<li><img src="images/img3.png"/></li>-->
    	</ul>
    	<div class="h_scimg">
    		<input type="button" value="添加图片" class="h_scimgbut" />
    		<input type="file" id="filemhbut" name="" id="" value="上传图片" class="filemhbut" multiple="multiple"/>
    	</div>
    </div>
  </div>
  <!--l_wzmb  结束 -->
</div><!--mainright stop -->  
<script>	
$("#filemhbut").change(function(){
	var objUrl = getObjectURL(this.files[0]) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$(".h_imgcon ul").append('<li><img src="'+objUrl+'" /></li>');
	}
});
//建立一个可存取到该file的url
function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}
</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>上海野生动物园平台管理</title>
    <meta name="title" content="上海野生动物园平台管理" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="shortcut icon" type="image/x-icon" href="/resource/public/platform/images/favicon_shortcut.ico" />
    <link type="text/css" rel="stylesheet" href="/resource/public/platform/css/header.css">
    <link type="text/css" rel="stylesheet" href="/resource/public/platform/css/common.css">
    <link href="/resource/ui-dialog.css" rel="stylesheet" />
    <script src="/resource/jquery-1.9.1.min.js"></script>
    <script src="/resource/public/commonjs/Guid.js"></script>
    <script src="/resource/cookie.js"></script>
    <script src="/resource/public/commonjs/jquery.form.js"></script>
    
    <script type="text/javascript" src="/resource/dialog-min.js"></script>
    <script type="text/javascript" src="/resource/dialog-plus.js"></script><!---弹出框js-->
    <script type="text/javascript" src="/resource/artDialog/artDialog.js"></script><!---弹出框js-->
    <script type="text/javascript" src="/resource/dialogShow.js"></script><!---自定义弹出框 调用-->
   
    <script type="text/javascript" src="/resource/public/platform/js/shop/updatePwd.js"></script>
</head>
<body>
<!--头部菜单  begin-->
<div id="top">
	<div class="top-left">
		<img src="/resource/public/platform/images/上海logo.png"/>
	</div>
	<div class="top-right">
		<ul id="topul">
			<li class="active"><i class="indexico1"></i><span>首页</span></li>
			  <c:if test="${list != null and list.size()>0 }">
                       <c:forEach items="${list}" var="fmenu" varStatus="s">
                            <c:if test="${fmenu.id != 1 }">
                            <li id="one_${fmenu.id }" data-id="${fmenu.id }">
                            <i class="indexico${s.count }"></i><span>${fmenu.name }</span>
                            </li>
                            </c:if>
                       </c:forEach>
                       </c:if>
		</ul>
		<div class="top-welcome"><a href="javascript:void(0);" class="updatePwd"><i></i>欢迎，admin!</div>

		<div class="top-exit"><a href="javascript:void(0);" class="ulogin_click"><i></i><p>退出系统</p></a></div>
	</div>
</div>
<!--leftside  begin-->
<div id="leftside">
	<!--topulcon  begin-->
	<div class="topulcon show">
		<div class="topulcon-tit"><a target="test" href="right" style="color:#FFFFFF;">首页</a></div>
		<!--n_khfw   begin -->
    <div class="n_khfw">
    	<div class="n_khfwmk">
        	<p>客诉服务热线：</p>
            <p>021-69916995</p>
        </div><!--n_khfwmk  stop -->
        <div class="n_khfwmk">
        	<p>技术服务热线：</p>
            <p>40088-59188</p>
        </div><!--n_khfwmk  stop -->
        <div class="n_khfwmk">
        	<p>工作日：</p>
            <p>周一~周五</p>
        </div><!--n_khfwmk  stop -->
        <div class="n_khfwmk">
        	<p>工作时间：</p>
            <p>9:00~18:00</p>
        </div><!--n_khfwmk  stop -->
        <div class="n_khfwmk">
        	<p>邮箱：</p>
            <p class="lvs">service@techown.com</p>
        </div><!--n_khfwmk  stop -->
        <div class="n_khfwmk">
        	<p>系统问题建议可发送至：</p>
            <p class="lvs">service@techown.com</p>
        </div><!--n_khfwmk  stop -->
    </div>
    <!--n_khfw  stop -->
	</div>
	<!--topulcon stop-->
	<!--topulcon  begin-->
	  <c:if test="${list != null and list.size()>0 }">
            <c:forEach items="${list }" var="fmenu">
                        <c:if test="${fmenu.id != 1 }">
                        <div class="topulcon">
                          <div class="topulcon-tit">${fmenu.name }</div>                        
                           <c:forEach items="${fmenu.childrens }" var="smenu">
                           <div class="topulconmk">
                           <h3>${smenu.name }<i></i></h3>
                             <ul class="topulconmkul">
                              <c:forEach items="${smenu.childrens }" var="scmenu">
                              <li><a target="test" href="${scmenu.menuurl}">${scmenu.name }</a></li>
                              </c:forEach>
                             </ul>
                             </div>
                             </c:forEach>
                             </div>
                           
                        </c:if>
            </c:forEach>
      </c:if>
	<!--topulcon stop-->
</div>
<!--leftside  stop-->
<div id="content">    
	<iframe name="test" id="test" src="/platform/right" width="100%" height="100%" frameborder="0" style="z-index:-1;"></iframe>   
</div><!-- content -->
<div id="footer">Designed by SHANGHAI WILD ANIMAL PARK</div>
<script>
//leftside左边的高度固定
var total = document.documentElement.clientHeight,
    colHeight = total-88;
document.getElementById('leftside').style.height=colHeight + 'px';
//头部菜单点击切换
$('#topul li').click(function(){
	$(this).addClass("active").siblings().removeClass('active');
	var index=$('#topul li').index(this);
	$("#leftside .topulcon").eq(index).addClass("show").siblings().removeClass('show');
})
//左边一级菜单点击切换
$('body').on("click",'.topulconmk h3',function(){
	    $(this).siblings("ul").toggle();
		$(this).find('i').toggleClass('down');		
})
$('body').on("click",'.topulconmkul li',function(){
	$('.topulconmkul li').removeClass('active');
	$(this).addClass('active').stop().siblings().removeClass('active');
})
$(document).ready(function () {	
	  $(".ulogin_click").bind("click", function () {
          DelCookie("_u_token");
          DelCookie("_u_channel");
          SetCookie("login", false);
          window.location.href = '/platform/login';
      });
	  
	  $(".updatePwd").bind("click", function () { order.updPwd(); }); 
  });
</script>
</body>
</html>
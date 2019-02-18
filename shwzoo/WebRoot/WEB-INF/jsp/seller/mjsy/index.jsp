<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>会员管理</title>
    <meta name="title" content="商品管理——到货通知" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="shortcut icon" type="image/x-icon" href="${ctx }/resource/public/seller/images/favicon_shortcut.ico" />
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/common.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/header-banner.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/sv/css/style.css">
    <script type="text/javascript" src="${ctx }/resource/public/seller/js/common.js"></script>
    <script type="text/javascript" src="${ctx }/resource/artTemplate.js"></script><!---列表页面数据加载模板js-->
    <script src="${ctx }/resource/cookie.js"></script>
</head>
<body>
<div id="header">
        <div class="wrapper">
            <div class="logo"><img src="${ctx }/sv/images/logo_wz.png" width="396" height="90"></div><!--logo 结束 -->
            <div class="searh">
                <input name="" type="text" class="searh-inp">
                <input name="" type="button" class="searh-but">
            </div><!--搜索框结束 -->
            <div class="admin">
                <span class="admin-tp"></span>
                欢迎,@SessionState.GetCurrentUser().UserName
                <a href="#"><span style="background:url(${ctx }/sv/images/mess301.png); width:12px; height:8px; display:inline-block; margin:0px 0px 0px 10px;"></span><span style="font-size:12px; color:#000; font-weight:normal; margin:0 3px 0px 3px;">消息</span><span style="font-size:12px; color:#E61912; margin-right:10px;">10</span></a>
                <a class="ulogin_click" href="javascript:void(0);">退出</a>
            </div><!--欢迎admin  退出  结束 -->
        </div>
    </div>
    <!--header头部 结束-->
    <div class="clear"></div>
    <!--banner导航  开始-->
    <div id="banner">
        <div class="wrapper">
            <ul id="ul_firstmenus">
                <li id="title_0" class="ban-hover"><a href="index">首页</a></li>
               @foreach (MenusDto item in ViewBag.Menus)
                {
                    <li id="title_@item.ID" data="@item.Children.ToJson()"><a href="javascript:void(0);">@item.Name</a></li>
                }
            </ul>
        </div>
    </div>
    <!--banner导航  结束-->
    <div class="clear"></div>
    <!--主要内容开始 -->
    <div id="container">
        <div class="leftside">
            <div class="index-dh"><a target="ifm" id="home_page" href="HomePage">店铺销售量</a></div>
            <script id="menuslist" type="text/html">
                {{each list as smenu i}}
                <div class="leftside-title">{{smenu.Name}}</div>
                <div class="leftside-con">
                    <ul>
                        {{each smenu.Children as tmenu i}}
                        <li><a target="ifm" href="{{tmenu.MenuUrl}}">{{tmenu.Name}}</a></li>
                        {{/each}}
                    </ul>
                </div>
                {{/each}}
            </script>
        </div><!--主要内容 左边导航结束 -->
        <div class="rightside" id="rightside">
            <iframe src="HomePage" frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" id="ifm" name="ifm" width="990px;"></iframe>
        </div><!--主要内容 右边结束 -->
    </div>
    <!--主要内容结束 -->
    <script language="javascript" type="text/javascript">
        function reinitIframe() {
            var iframe = document.getElementById("ifm");
            try {
                var bHeight = iframe.contentWindow.document.body.scrollHeight;
                var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
                var height = Math.max(bHeight, dHeight);
                iframe.height = height;
            } catch (ex) { }
        }
        window.setInterval("reinitIframe()", 200);

        $(document).ready(function () {
            $("#ul_firstmenus").find("li").bind("click", function () {
                var $li = $(this);
                $("#ul_firstmenus").find("li").each(function () {
                    $(this).removeClass("ban-hover");
                })
                $li.addClass("ban-hover");
                var html = "";
                if ($li.attr("id") == "title_0") {
                    html = '<div class="index-dh"><a target="ifm" id="home_page" href="HomePage">店铺销售量</a></div>';
                } else {
                    var listdata = {
                        list: $.parseJSON($li.attr("data"))
                    }
                    html = template('menuslist', listdata);
                }
                $(".leftside").html(html);
                unit();
            });
            $(function () {
                $(".ulogin_click").bind("click", function () {
                    DelCookie("_u_token");
                    DelCookie("_u_channel");
                    DelCookie("_uid");
                    DelCookie("_un");
                    window.location.href = '/Seller/dlzc/Login';
                });
            });

            function unit() {
                $(".leftside").find("li").bind("click", function () {
                    var $li = $(this);
                    $(".leftside").find("li").each(function () {
                        $(this).removeClass("leftside-con-hover");
                    })
                    $li.addClass("leftside-con-hover");
                })
            }
        })
    </script>
</body>
</html>
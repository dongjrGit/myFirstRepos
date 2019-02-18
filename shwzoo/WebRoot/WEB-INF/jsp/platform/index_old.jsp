<!-- @{
    int UserType = SessionState.GetCurrentUser().UserType.Value;
    int SuperUserType = (int)UserTypeEnum.SupAdmin;
}
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>上海野生动物园管理</title>
    <meta name="title" content="上海野生动物园管理" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="shortcut icon" type="image/x-icon" href="${ctx }/resource/public/platform/images/favicon_shortcut.ico" />
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/platform/css/banner1.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/platform/css/ddgl.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/platform/css/header.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/platform/css/index.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/platform/css/common.css">

    <script src="${ctx }/resource/jquery-1.9.1.min.js"></script>
    <script src="${ctx }/resource/public/commonjs/Guid.js"></script>
    <script src="${ctx }/resource/cookie.js"></script>
    <script src="${ctx }/resource/public/commonjs/jquery.form.js"></script>

    <style type="text/css">
        .l_xz {
            display: block;
        }

        .l_xzone {
            display: none;
        }

        body {
        }

        .bac1 {
            background: #fff;
            width: 300px;
            -webkit-border-top-left-radius: 5px;
            -moz-border-top-left-radius: 5px;
            border-top-left-radius: 5px;
            -webkit-border-bottom-left-radius: 5px;
            -moz-border-bottom-left-radius: 5px;
            border-bottom-left-radius: 5px;
        }

        .row03 {
            background: url(${ctx }/resource/public/platform/images/row03.png) no-repeat;
            width: 8px;
            height: 7px;
            position: absolute;
            top: 45%;
            right: 40px;
            display: inline-block;
        }

        .row04 {
            background: url(${ctx }/resource/public/platform/images/row04.png) no-repeat;
            width: 8px;
            height: 7px;
            position: absolute;
            top: 45%;
            right: 30px;
            display: inline-block;
        }
    </style>
  
</head>

<body style="background:#fff;">
    <!--top  开始 -->
    <div id="top">
        <div class="top_1">
            <div class="greet">
                <span class="portrait"></span>
                <span class="welcome">欢迎${name}</span>
                <span class="exit"><a href="javascript:void(0);" class="ulogin_click">退出</a></span>
            </div><!--greet stop -->
           <!--  <div class="search">
                <input id="inp-sea" name="" type="text">
                <input id="inp-sous" name="" type="button">
            </div>search stop -->
            <%-- <div class="message-con">
                <span class="l_pos"><img src="${ctx }/pv/images/img_pos01.png" width="12" height="17">您所在位置：首页</span>

            </div> --%>
        </div><!--top_1  stop -->
        <div class="top_2"></div><!--top_2  stop -->
        <div class="lefttop"><img src="${ctx }/resource/public/platform/images/LOGO(1).png" ></div><!--lefttop  stop -->
        <div class="l_xzb"><img src="${ctx }/resource/public/platform/images/row_dou01.png" width="15" height="8"></div>
        <div class="l_xzb_2"><img src="${ctx }/resource/public/platform/images/row_dou02.png" width="15" height="8"></div>
    </div>
    <!--top  结束 -->
    <div class="clear"></div>
    <!-- container  begin-->
    <div id="container">
        <div id="l_left">
            <!--lib_Tab1_sx  选项卡  开始 -->
            <div id="lib_Tab1_sx" class="lib_tabborder_sx">
                <div class="lib_Menubox_sx" id="lib_Menubox_sx">
                    <ul>
                        <li id="one1" data-id="1" class="hover">首页</li>
                       <c:if test="${list != null and list.size()>0 }">
                       <c:forEach items="${list }" var="menu">
                            <c:if test="${menu.id != 1 }">
                            <li id="one_${menu.id }" data-id="${menu.id }">${menu.name }</li>
                            </c:if>
                       </c:forEach>
                       </c:if>
                    </ul>
                </div><!--lib_Menubox_sx  stop -->
                <div class="lib_Contentbox_sx" id="lib_Contentbox_sx">
                    <div id="con_one_1">
                        <div class="l_zdlb">
                            <span class="l_index"><a target="test" href="right">首页</a></span>
                        </div><!--l_zdlb  stop -->
                        <!--n_khfw   begin -->
                        <div class="n_khfw">
                            <div class="n_khfwmk">
                                <p>客诉服务热线：</p>
                                <p>010-8888888888</p>
                            </div><!--n_khfwmk  stop -->
                            <div class="n_khfwmk">
                                <p>技术服务热线：</p>
                                <p>010-8888888888</p>
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
                                <p class="lvs">1234567999@qq.com</p>
                            </div><!--n_khfwmk  stop -->
                            <div class="n_khfwmk">
                                <p>系统问题建议可发送至：</p>
                                <p class="lvs">123456799999@qq.com</p>
                            </div><!--n_khfwmk  stop -->
                        </div>
                        <!--n_khfw  stop -->
                    </div><!-- con_one_1  stop-->
                   <c:if test="${list != null and list.size()>0 }">
                       <c:forEach items="${list }" var="menu">
                        <c:if test="${menu.id != 1 }">
                         <div id="con_one_${menu.id }" style="display:none">
                            <div class="l_zdlb">
                                <ul>
                                   <c:forEach items="${menu.childrens }" var="smenu">
                                        <li>${smenu.name }<span class="row01"></span></li>
                                        <div class="l_zdlbone">
                                            <ul>
                                              <c:forEach items="${smenu.childrens }" var="tmenu">
                                                   <c:choose>
                                                    <c:when test="${tmenu.haschild == true }">
                                                     <li class="l_zdlbtwo ">${tmenu.name }<span class="row02"></span></li>
                                                        <div class="viscount ">
                                                            <ul>
                                                                  <c:forEach items="${tmenu.childrens }" var="fmenu">
                                                                    <li><a target="test" href="${fmenu.menuurl }">${fmenu.name }</a></li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise> 
                                                     <li><a target="test" href="${tmenu.menuurl }">${tmenu.name }</a></li>
                                                    </c:otherwise>
                                                   </c:choose>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                   </c:forEach>
                                </ul>
                            </div>
                        </div>
                        </c:if>
                        </c:forEach>
                    </c:if>
                </div><!-- lib_Contentbox_sx  stop-->
            </div>
            
            <!--lib_Tab1_sx  选项卡  结束 -->
        </div><!--l_left 结束 -->
        <div id="l_right">
            <div id="content">
                <iframe name="test" id="test" src="${ctx }/platform/right" width="100%" height="100%" frameborder="0" style="z-index:-1;"></iframe>
            </div><!-- content -->
            <div id="footer">Designed by SHANGHAI WILD ANIMAL PARK</div>
        </div><!--l_right 结束 -->
    </div>
    <!-- container  stop -->
    <script type="text/javascript">
        $(document).ready(function () {
            $(".l_zdlbtwo").click(function () {
                $(this).children("span.row02").toggleClass("row03");
                $(this).next().toggleClass("l_xz");
                $(this).toggleClass("bac1");

            });
            $(".l_zdlbone").prev().click(function () {
                $(this).children("span.row01").toggleClass("row04");
                $(this).next().toggleClass("l_xzone")
            });
            $(".l_xzb").click(function () {
                $(this).css("display", "none")
                $(".l_xzb_2").css("display", "block")
                $(".lib_Contentbox_sx").toggleClass("l_xzone");
                $("#l_left").css("width", "31px")
                $("#l_right").css("margin-left", "31px")
            });
            $(".l_xzb_2").click(function () {
                $(this).css("display", "none")
                $(".l_xzb").css("display", "block")
                $(".lib_Contentbox_sx").toggleClass("l_xzone");
                $("#l_left").css("width", "200px")
                $("#l_right").css("margin-left", "200px")
            });

            $(".lib_Contentbox_sx").find("li").each(function (index) {
                $(this).children(0).bind("click", function () {

                    $(".lib_Contentbox_sx").find("li").each(function (i) {
                        $(this).removeClass("li_hover");
                    });
                    if ($(this).is("a"))
                        $(this).parent().addClass("li_hover");
                });
            });
            $(".lib_Menubox_sx").children(0).children().each(function () {
                $(this).bind("click", function () {
                    $(".lib_Menubox_sx").find("li").each(function (i) {
                        $(this).removeClass("hover");
                    });
                    $(".lib_Contentbox_sx").children().each(function () {
                        $(this).hide();
                    });
                    $("#con_one_" + $(this).attr("data-id")).show();
                    $(this).addClass("hover");
                });
            });
            $(".ulogin_click").bind("click", function () {
                DelCookie("_u_token");
                DelCookie("_u_channel");
                SetCookie("login", false);
                window.location.href = '/platform/login';
            });
            $(".search input[type=text]").focus(function () {
                $(".search").css("background-image", "url(resource/public/platform/images/searh01.png)");
                $(this).css({ "color": "#009BCE" })
            });

        });
        total = $(window).height();
        var colHeight = total - 80;
        var colHeightone = total - 80 - 30;
        $("#l_left").css("height", colHeight + "px");
        $("#l_right").css("height", colHeight + "px");

        $("#content").css("height", colHeightone + "px");
        $("#container").css("height", colHeight + "px");
        var obj = $("#lib_Tab1_sx");// document.getElementById("lib_Tab1_sx");
        if (obj.scrollHeight > obj.clientHeight || obj.offsetHeight > obj.clientHeight) {
            $("#lib_Menubox_sx").css("width", "80px");// document.getElementById("lib_Menubox_sx").style.width = "80px";
        }
    </script>
</body>
</html>

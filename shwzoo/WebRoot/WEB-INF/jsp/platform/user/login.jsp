<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>上海野生动物园管理登陆</title>
    <meta name="title" content="上海野生动物园管理登陆" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="shortcut icon" type="image/x-icon" href="/resource/public/platform/images/favicon_shortcut.ico" />
    <link href="${ctx }/resource/ui-dialog.css" rel="stylesheet" />
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/platform/css/common.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/platform/css/login.css">
    <script type="text/javascript" src="${ctx }/resource/public/platform/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx }/resource/public/platform/js/common.js"></script>
    <script src="${ctx }/resource/jquery-1.9.1.min.js"></script>
    <script src="${ctx }/resource/public/commonjs/Guid.js"></script>
    <script src="${ctx }/resource/cookie.js"></script>
    <script src="${ctx }/resource/dialog-min.js"></script>
    <script src="${ctx }/resource/dialogShow.js"></script> <!--弹出框-->
    <script src="${ctx }/resource/public/platform/js/Login/Login.js"></script>
    <!--表单验证css&&js-->
    <link href="${ctx }/resource/public/commoncss/jQuery.Validate.css" rel="stylesheet" />
    <script src="${ctx }/resource/public/commonjs/jquery.validate.js"></script>
    <script src="${ctx }/resource/public/commonjs/messages_cn.js"></script>
    <script src="${ctx }/resource/public/commonjs/jquery.validate-methods.js"></script>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        body {
            height: 100%;
        }

        .login_wrapper {
            height: 100%;
        }

        .login_header {
            height: 25%;
        }

        .login_footer {
            height: 25%;
        }

        .login_content01 {
            height: 25%;
        }

        .login_content02 {
            height: 25%;
        }
    </style>
</head>

<body>
    <div class="login_wrapper">
       <div class="login_header"><img src="${ctx }/resource/public/platform/images/bj-all_01.jpg" width="100%" height="100%"></div>
        <div class="login_content01"><img src="${ctx }/resource/public/platform/images/bj-all_02.jpg" width="100%" height="100%"></div>
        <div class="login_content02"><img src="${ctx }/resource/public/platform/images/bj-all_03.jpg" width="100%" height="100%"></div>
        <div class="login_footer"><img src="${ctx }/resource/public/platform/images/bj-all_04.jpg" width="100%" height="100%"></div>
    </div>
    <div id="main">
        <div class="leftcon">
            <img src="${ctx }/resource/public/platform/images/bj_01.png" width="655" height="400">
            <div class="clear"></div>
        </div><!--leftcon  stop -->
        <div class="rightcon">
            <div class="rightcon_conall">
                <div class="rightcon-top">上海野生动物园后台管理系统</div>
                <div class="rightcon-conyhm"><input name="userName" id="userName" type="text" class="login-inp" placeholder="登录名" ></div>
                <div class="rightcon-conmm"><input name="userPwd" id="userPwd" type="password" class="login-inp" placeholder="密码" onkeydown="EnterPress();"></div>
                <div id="div_errormessage" class="cwtsxx"></div>
                <div class="jzmm-login">
                   <%--  <span class="fxklogin"><img src="${ctx }/pv/images/check01.png" width="20" height="20"></span>
                    <span style=" line-height:25px; display:inline-block; float:left;">记住密码</span> --%>
                </div><!-- jzmm-login  stop  onclick="Login()"  <div class="dl-login">立即登录</div>-->
                <a href="javascript:void(0);" id="a_submit" onclick="Login()" ><div class="dl-login">立即登录</div></a>
                <div class="clear"></div>
            </div>
            <!--rightcon_conall  stop -->
        </div><!--rightcon  stop -->
        <div class="clear"></div>
    </div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        // onfocus="if(this.value==defaultValue) {this.value=''}" onblur="if(!value) {value=defaultValue; this.type='text';}"
        //初始化
       
        Init.bind();

        //js加载样式
        $(".rightcon-conyhm input[type=text]").focus(function () {
            $(".rightcon-conyhm").css("background-image", "url(/resource/public/platform/images/inp_02.png)");
            $(this).css({ "color": "#009BCE", })
        });
        $(".rightcon-conyhm input[type=text]").blur(function () {
            $(this).css({ "color": "#000", })
        });
        $(".rightcon-conmm input[type=password]").focus(function () {
            $(".rightcon-conmm").css("background-image", "url(/resource/public/platform/images/inp_04.png)");
            $(this).css({ "color": "#009BCE", })
        });
        $(".rightcon-conmm input[type=password]").blur(function () {
            $(this).css({ "color": "#000", })
        });
    });
</script>
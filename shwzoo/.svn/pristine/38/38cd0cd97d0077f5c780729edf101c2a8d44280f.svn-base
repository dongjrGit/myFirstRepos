<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>卖家管理</title>
    <meta name="title" content="商品管理——到货通知" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    
    <link rel="shortcut icon" type="image/x-icon" href="${ctx }/resource/public/seller/images/favicon_shortcut.ico" />
    <link href="${ctx }/resource/ui-dialog.css" rel="stylesheet" />
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/common.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/login.css">
<script type="text/javascript" src="${ctx }/resource/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/resource/public/seller/js/common.js"></script>

    <script src="${ctx }/resource/public/seller/js/Guid.js"></script>
    <script src="${ctx }/resource/public/seller/js/cookie.js"></script>
    <script src="${ctx }/resource/public/seller/js/dialog-min.js"></script>
    <script src="${ctx }/resource/public/seller/js/dialogShow.js"></script> <!--弹出框-->

    <script src="${ctx }/resource/public/seller/js/LoginRegister/Login.js"></script>
    <!--表单验证css&&js-->
    <link href="${ctx }/resource/public/commoncss/jQuery.Validate.css" rel="stylesheet" />
    <script src="${ctx }/resource/public/commonjs/jquery.validate.js"></script>
    <script src="${ctx }/resource/public/commonjs/messages_cn.js"></script>
    <script src="${ctx }/resource/public/commonjs/jquery.validate-methods.js"></script>
</head>

<body>
    <!--top  begin -->
    <div id="top">
        <div class="wrapper">
            <div class="logo"></div>
            <div class="top-right">您好，欢迎光临中绿生活！<span class="lvse"><a href="javascript:void(0);">登录</a></span></div>
        </div><!--wrapper  stop -->
    </div>
    <!--top  stop -->
    <!--main  begin -->
    <div id="main">
        <div class="wrapper">
            <div class="mainleft"><img src="${ctx }/sv/images/img01.png" width="563" height="328"></div><!--mainleft  stop -->
            <div class="mainright">
                <div class="mainright-top">中绿生活商户登录</div>
                <div class="mainright-con">
                    <div class="mainright-con-sj"></div>
                    <div class="login">
                        <form>
                            <p><input name="userName" id="userName" type="text" class="login-dl" value="手机号/会员名/邮箱" onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"></p>
                            <p><input name="userPwd" id="userPwd" type="password" class="login-dl" value="" onkeydown="EnterPress();"></p>
                        </form>
                    </div><!--login  stop -->
                    <div class="tsmess" id="div_errormessage"></div><!--tsmess -->
                    <div class="wjmm">
                        <a href="javascript:void(0);">忘记登录密码？</a>
                    </div><!--wjmm  stop -->
                    <div class="login-dlaniu">
                        <a href="javascript:void(0);" id="a_submit"><img src="${ctx }/sv/images/login.png" width="352" height="40"></a>
                    </div>
                    <div class="elselogin">
                        <div class="qtdl">使用合作网站账号登录：</div>
                        <div class="qtdl-img">
                            <span class="qq_login"></span>
                            <span class="wx_login"></span>
                            <span class="wb_login"></span>
                        </div>
                    </div><!--elselogin  stop -->
                </div><!--mainright-con  stop -->
            </div><!--mainright  stop -->
        </div><!--wrapper  stop -->
    </div>
    <!--main  stop -->
</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        //样式加载
        $("input[type=text]").focus(function () {
            $(this).css("color", "#000")
        });
        $("input[type=password]").focus(function () {
            $(this).css("color", "#000")
        });
        //初始化
        Init.bind();
    })
</script>




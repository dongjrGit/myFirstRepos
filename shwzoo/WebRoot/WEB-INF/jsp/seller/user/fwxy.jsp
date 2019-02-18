<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>企业注册</title>
    <meta name="title" content="商品管理——到货通知" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="${ctx }/resource/ui-dialog.css" rel="stylesheet" />
     <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/common.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/login.css">
<style type="text/css">
        html, body {
            height: auto;
            width: 100%;
            overflow: auto;
            margin: 0;
            padding: 0;
        }

        #top {
            width: 100%;
            height: 99px;
            background: #ffffff;
            border-bottom: 1px solid #EAEAEA;
        }

        #main {
            background: #FEF9F5;
            width: 100%;
            margin: 0;
            padding: 0;
        }

            #main .wrapper {
                width: 1200px;
                margin: 0 auto;
                /* background: url(/images/bj-all.jpg) no-repeat; */
            }
    </style>
    
</head>


    <!--top  begin -->
    <div id="top">
        <div class="wrapper">
            <div class="logos" style="margin-top: 10px;">
				<a href="/index.html"><img src="/resource/pc/web/images/logo.png" width="202"
					height="80" /></a>
			</div>
            <div class="top-right">您好，欢迎光临中绿生活！<span class="lvse"><a href="${ctx }/seller/login">登录</a></span></div>
        </div><!--wrapper  stop -->
    </div>
    <!--top  stop -->
    <!--main  begin -->
    <div id="main">
        <div class="wrapper">
            <!--yxyzcon主要内容  开始 -->
            <div class="yxyzcon" style="padding:80px 0px 20px 0px; margin-top:1px;">
                <div style="font-size:20px;  padding:0px 537px;width: 300px">《服务协议》</div><!--hyzcqyyh  stop -->
                <!-- fwlbcon  begin-->
                <div class="fwlbcon">
                     ${fwxy }
                </div>
                    <div class="clear"></div>
                </div>
                <!--fwlbcon  stop -->
                <div class="clear"></div>
                 
            </div>
            <!--yxyzcon主要内容  结束 -->
            <div class="clear"></div>

            <div class="clear"></div>
        </div><!--wrapper  stop -->
    <!--main  stop -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>上海野生动物园</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <sitemesh:write property='head' />
    <c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/resource/public/platform/css/ddgl.css">
    <link type="text/css" rel="stylesheet" href="/resource/public/platform/css/common.css">
    <link type="text/css" href="/resource/public/platform/css/pager2.css" rel="stylesheet" /><!---平台分页样式-->
    <script type="text/javascript" src="/resource/public/platform/js/pager2.js"></script><!---平台分页js-->
    <link href="/resource/ui-dialog.css" rel="stylesheet" />
    <link href="/resource/public/commoncss/jQuery.Validate.css" rel="stylesheet" /><!---validate自定义验证样式-->
    <script type="text/javascript" src="/resource/jquery-1.9.1.min.js"></script>
       
    <script type="text/javascript" src="/resource/artTemplate.js"></script><!---列表页面数据加载模板js-->
    <script type="text/javascript" src="/resource/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/resource/dialog-min.js"></script>
    <script type="text/javascript" src="/resource/cookie.js"></script>
    <script type="text/javascript" src="/resource/public/commonjs/GetQueryString.js"></script>
    <script type="text/javascript" src="/resource/dialog-plus.js"></script><!---弹出框js-->
    <script type="text/javascript" src="/resource/artDialog/artDialog.js"></script><!---弹出框js-->
    <script type="text/javascript" src="/resource/dialogShow.js"></script><!---自定义弹出框 调用-->
    <script type="text/javascript" src="/resource/public/commonjs/jquery.validate.js"></script>          <!---validate验证--> 
    <script type="text/javascript" src="/resource/public/commonjs/messages_cn.js"></script>
    <script type="text/javascript" src="/resource/public/commonjs/jquery.validate-methods.js"></script>  <!---validate自定义验证方法-->
    <script type="text/javascript" src="/resource/public/commonjs/BasePublic.js"></script>
    <script type="text/javascript" src="/resource/public/platform/js/common.js"></script><!---列表页面加载js-->
    <script type="text/javascript" src="/resource/public/platform/js/PageSize.js"></script>
    <script type="text/javascript" src="/resource/public/commonjs/DateExtension.js"></script>
    <script type="text/javascript" src="/resource/public/commonjs/baseRoot.js"></script>
</head>
<body>
    <sitemesh:write property='body' />
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 引入jQuery -->	
<script type="text/javascript" src="<%=basePath%>script/easyui/jquery.min.js" charset="UTF-8"></script>
<!-- script type="text/javascript" src="<%=basePath%>script/easyui/jquery-migrate-1.1.1.js"></script -->
<script type="text/javascript" src="<%=basePath%>script/util/jquery.cookie.js" charset="UTF-8"></script>
<!-- 引入easyUI -->
<link rel="stylesheet" href="<%=basePath%>script/easyui/themes/icon.css" type="text/css"></link>

<link id="easyuiTheme" rel="stylesheet" href="<%=basePath%>script/easyui/themes/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css" type="text/css">

<script type="text/javascript" src="<%=basePath%>script/easyui/jquery.easyui.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=basePath%>script/easyui/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>


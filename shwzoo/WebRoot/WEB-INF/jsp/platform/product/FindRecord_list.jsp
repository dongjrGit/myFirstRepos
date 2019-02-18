<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>经彩商城</title>
</head>
<body>
<table>
<c:forEach items="${Findimg}" var="F" varStatus="s">
<td>
	<img src="${F.imgurl}" Style="width:300px;height:300px;"/>
</td>
<c:if test="${(s.index+1)%3==0 }">
<tr></tr>
</c:if>
</c:forEach>
</table>
</body>
</html>
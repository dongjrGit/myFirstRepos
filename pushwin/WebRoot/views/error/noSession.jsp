<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<script src="<%=basePath %>/scripts/boot.js" type="text/javascript"></script>
</head>
<body>
	<script type="text/javascript" charset="utf-8">
		 top.mini.showMessageBox({
            showModal: true,
            width: 400,
            height : 150,
            title: "提示",
            iconCls: "mini-messagebox-warning",
            message: "<%=request.getAttribute("msg") %>",
            x: 'center',
            y: 'middle',
            callback: function(action){
            	var url = "<%=path%>/controller/system/init";
            	window.top.location.href = url;
    		}
        });
	
		<%-- parent.$.messager.progress('close');
		parent.$.messager.alert("警告",'${msg}',"warning",function() {
				top.window.location.href = '<%=basePath%>';
		}); --%>
	
</script>
</body>
</html>

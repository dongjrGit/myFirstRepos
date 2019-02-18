<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPEhtml PUBLIC "-//W3C//DTDhtml 4.01transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/views/include/HtmlContext.jsp"%>
<!-- link rel="stylesheet" href="<%=basePath%>script/easyui/demo/demo.css" type="text/css"></link></head>
<script type="text/javascript" src="<%=basePath%>script/easyui/datagrid-detailview.js"></script -->
<title>跑批任务列表</title>

<body>
<script type="text/javascript">
	var hisurl = "${pageContext.request.contextPath}/controller/task/taskhis?taskID=" + $('#dg').datagrid('getSelections')[0].bantchworkId;
	$('#histable').datagrid({
		url : hisurl,
								loader:function(param,success,error){
								var that = $(this);
								var opts = that.datagrid("options");
								if (!opts.url) {
									return false;
								}
								var cache = that.data().datagrid.cache;
								if (!cache) {
									$.ajax({
										type : opts.method,
										url : opts.url,
										data : param,
										dataType : "json",
										success : function (data) {
											that.data().datagrid['cache'] = data;
											success(bulidData(data));
										},
										error : function () {
											error.apply(this, arguments);
										}
									});
								} else {
									success(bulidData(cache));
								}
								function bulidData(data) {
									var temp = $.extend({},data);
									var tempRows = [];
									var start = (param.page - 1) * parseInt(param.rows);
									var end = start + parseInt(param.rows);
									var rows = data.rows;
									for (var i = start; i < end; i++) {
										if(rows[i]){
											tempRows.push(rows[i]);
										}else{
											break;
										}
									}
									temp.rows = tempRows;
									return temp;
								}
							},
		columns : [ [ {
			field : 'taskID',
			title : '任务ID',
			hidden:true
		}, {
			field : 'taskName',
			title : '任务名称',
			width : 135
		}, {
			field : 'startTm',
			title : '开始执行时间',
			width : 175
		}, {
			field : 'endTm',
			title : '结束执行时间',
			width : 175
		}, {
			field : 'exeRslt',
			title : '执行状态',
			width : 80
		} ] ],
		pagination : true,
		inline : false
	});
</script>
    <div style="background:#FFFEE6;color:#8F5700;padding-left: 12px;padding-top: 6px;">
		<h2>任务历史记录</h2>
	</div>
	<table id="histable"></table>

</body>
</html>
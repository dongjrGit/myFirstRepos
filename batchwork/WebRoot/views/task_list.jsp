<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/views/include/HtmlContext.jsp"%>
<link rel="stylesheet" href="<%=basePath%>script/easyui/demo/demo.css" type="text/css"></link></head>
<script type="text/javascript" src="<%=basePath%>script/easyui/datagrid-detailview.js"></script>
<title>跑批任务列表</title>
<script type="text/javascript">
	//var rows = $('#dg').datagrid('getSelections');
	//关闭窗口
	
	function closeWin(win, reload){
		$('#' + win).window('close');
		if(reload){
			//window.location.reload(true);
			var dg = $('#dg');
			dg.datagrid("reload", {timestamp:new Date()});
		}
	}
	function userOpt(optType){
		var rows = $('#dg').datagrid('getSelections');
			if(rows == "" || rows == "undefined"){
				$.messager.alert('消息提示','请选择一条记录！','warning');
				return;
			}
			if(rows[0].configStatus == "已启动" && optType == "start")
			{
				$.messager.alert('消息提示','任务已经启动，请不要重复操作！','warning');
				return;
			} else if(rows[0].configStatus == "已停止" && optType == "stop"){
				$.messager.alert('消息提示','任务已经停止，请不要重复操作！','warning');
				return;
			} else {
				$.messager.confirm('确认对话框', (optType == "start" ? "启动" : "停止") + '该任务吗？', function(r){
					if (r){
						$.ajax({
							url:"${pageContext.request.contextPath}/controller/task/onoff",
							type:"GET",
							dataType:"json",
							cache:false,
							data:{taskID:rows[0].TRIGGER_NAME, optType:optType},
							success:function(data){
								var dg = $('#dg');
								dg.datagrid("reload", {timestamp:new Date()});
								$.messager.show({
									title : '提示消息',
									msg : data.msg,
									timeout : 3000,
									showType : 'slide'
								});
							}
						});
					} else {
						return;
					}
				});



				}
	}
	 var toolbar = [{
            text:'查看历史',
            iconCls:'icon-search',
            handler:function(){
            	var rows = $('#dg').datagrid('getSelections');
            	if(rows == "" || rows == "undefined"){
	            	 $.messager.alert('消息提示','请选择一条数据!','warning');
	            	 return;
            	}
            	$('#winhis').window('refresh');
				$('#winhis').window('open');
			}
        },'-',{
            text:'时间调度',
            iconCls:'icon-save',
            handler:function(){
            	var rows = $('#dg').datagrid('getSelections');
            	if(rows == "" || rows == "undefined"){
	            	 $.messager.alert('消息提示','请选择一条数据!','warning');
	            	 return;
            	}
				$('#winconf').window('open');
			}
        },'-',{
            text:'启动',
            iconCls:'icon-redo',
            handler:function(){
            	userOpt("start");
			}
        },{
            text:'停止',
            iconCls:'icon-undo',
            handler:function(){
            	userOpt("stop");
			}
        }];
        function formatDate(value){
        	var datevalue = new Date(value);
        	return datevalue.getFullYear()+"-"+zeroize(datevalue.getMonth() + 1)+"-"+zeroize(datevalue.getDay())+" "+zeroize(datevalue.getHours())+":"+zeroize(datevalue.getMinutes())+":"+zeroize(datevalue.getSeconds());
        }
        var zeroize = function (value, length) {        
	        if (!length) length = 2;        
	        value = String(value);        
	        for (var i = 0, zeros = ''; i < (length - value.length); i++) {        
	            zeros += '0';        
	        }        
	        return zeros + value;        
	    };  
</script>
<body>
    <h2>定时任务控制面板</h2>
    <div style="margin:10px 0;"></div>
    
    <table id="dg" class="easyui-datagrid" title="定时任务列表" style="width:900px;height:400px"
            data-options="rownumbers:true,singleSelect:true,selectOnCheck:true,url:'${pageContext.request.contextPath}/controller/task/tasklist',method:'get',toolbar:toolbar, cache:false">
        <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'JOB_NAME',width:160">任务名称</th>
                <th data-options="field:'TRIGGER_NAME',width:150">触发器名称</th>
                <th data-options="field:'TRIGGER_GROUP',width:80">触发器组名</th>
                <th data-options="field:'TRIGGER_STATE',width:80">当前状态</th>
                <th data-options="field:'PREV_FIRE_TIME',formatter:formatDate,width:120">上一次执行时间</th>
                <th data-options="field:'NEXT_FIRE_TIME',formatter:formatDate,width:120">下一次执行时间</th>
                <th data-options="field:'START_TIME',formatter:formatDate,width:120">任务初始化时间</th>
            </tr>
        </thead>
    </table>
    <div id="winconf" class="easyui-window" title=" " data-options="minimizable:false,maximizable:false,closable:false,modal:true,closed:true,collapsible:false,draggable:true,href:'task_config.jsp',tools:'#winconftool',inline:false" style="width:535px;height:400px;padding:10px;">
    </div>
    <div id="winhis" class="easyui-window" title=" " data-options="minimizable:false,maximizable:false,closable:false,modal:true,closed:true,collapsible:false,draggable:false,href:'task_history.jsp',tools:'#winhistool',inline:false" style="width:606px;height:410px;padding:10px;">
    </div>
	<div id="winconftool">
        <a href="javascript:void(0)" class="icon-cancel" onclick="javascript:closeWin('winconf', true)"></a>
    </div>
    <div id="winhistool">
        <a href="javascript:void(0)" class="icon-cancel" onclick="javascript:closeWin('winhis', true)"></a>
    </div>
	
    
</body>
</html>
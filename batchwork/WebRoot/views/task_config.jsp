<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPEhtml PUBLIC "-//W3C//DTDhtml 4.01transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/views/include/HtmlContext.jsp"%>
<title>跑批任务时间配置</title>
</head>
<body>
<script type="text/javascript">
	//提交表单
	function submitForm(formID){
		if(formID == "userDefineForm"){
			var inputStr = $('#defineTimeID').datebox('getValue');
			var inputDate = new Date(inputStr.replace(/-/g,"/"));
			var nowDate = new Date();
			if(inputDate < nowDate){
				$.messager.alert('错误提示','您输入的日期有误，任务将永远无法执行，请重新输入!','error');
				return;
			}
		}
		//var form = document.getElementById(formID);
		//var taskID = $('#dg').datagrid('getSelections')[0].taskID;
		//form.action = "${pageContext.request.contextPath}/controller/task/taskconf" + "?taskID=" + taskID;
		//form.submit();
		
		var taskID = $('#dg').datagrid('getSelections')[0].TRIGGER_NAME;
		var url = "${pageContext.request.contextPath}/controller/task/taskconf" + "?taskID=" + taskID;
		$.ajax({
			url : url,
			type : "POST",
			dataType : "json",
			cache : false,
			data : $("#" + formID).serialize(),
			success : function(data){
				$.messager.show({
					title : '提示消息',
					msg : data.msg,
					timeout : 3000,
					showType : 'slide'
				});
			}
		});
	}
	//添加<tr>
	function addDayTime(){
		var dayTime = 
					"<tr>" + 
						"<td>" + 
							"<input id=\"dayTimeID\" name=\"dayTime\" value=\"00:00:00\"" + 
							"class=\"easyui-timespinner\" style=\"width:80px;\"" + 
							"required=\"required\" data-options=\"min:'00:00',showSeconds:true\" />" + 
						"</td>" + 
						"<td width=\"12px\"></td>" + 
						"<td>" + 
							"<a href=\"#\" class=\"easyui-linkbutton\"" + 
							"data-options=\"iconCls:'icon-remove'\" onclick=\"removeDayTime(this)\">删除</a>" + 
						"</td>" +
					"</tr>";
		$('#dayTable').append(dayTime);
		$.parser.parse($('#dayTable').parent()); 
	}
	//删除<tr>
	function removeDayTime(param){
		if(window.ActiveXObject){
			param.parentNode.parentNode.parentNode.removeChild(param.parentNode.parentNode);
		} else {
			param.parentElement.parentElement.remove();
		}
	}
	//加载下拉框
	function reloadCycle(unit){
		if(unit == "M" || unit == "S"){
			$('#intervalID').combobox('reload', 'ms.json');
		} else if(unit == "H"){
			$('#intervalID').combobox('reload', 'hour.json');
		}
	}
</script>
	
	<div style="background:#FFFEE6;color:#8F5700;padding-left: 12px;padding-top: 6px;">
		<h2>时间调度</h2>
	</div>
	<div style="margin:10px 0;"></div>
	<div class="easyui-tabs" style="width:500px;height:280px">
		<div title="每月" style="padding:10px">
			<form id="monthForm" method="post" action="">
				<input name="month" type="hidden" value="true">
				<div style="padding:8px">
					<div class="demo-tip icon-tip">&nbsp;</div>
					<div>选择几号执行（可选多个），再选择时间点。</div>
				</div>
				<table>
					<tr>
						<td><input class="easyui-combobox" id="monthDaysID" name="monthDays"
							data-options="
			                    url:'days.json',
			                    method:'get',
			                    valueField:'id',
			                    textField:'text',
			                    multiple:true,
			                    panelHeight:'auto',
			                    panelHeight:200
			            	">
						</td>
						<td><input id="monthTimeID" name="monthTime" value="00:00:00"
							class="easyui-timespinner" style="width:80px;"
							required="required" data-options="min:'00:00',showSeconds:true" />
						</td>
						<td>
							<a id="monthsbtn" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="submitForm('monthForm')">设定</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div title="每周" style="padding:10px">
			<form id="weekForm" method="post" action="">
				<input name="week" type="hidden" value="true">
				<div style="padding:8px">
					<div class="demo-tip icon-tip">&nbsp;</div>
					<div>选择周几执行（可选多个），再选择时间点。</div>
				</div>
				<table>
					<tr>
						<td><input class="easyui-combobox" id="weekDaysID" name="weekDays"
							data-options="
		                    url:'weeks.json',
		                    method:'get',
		                    valueField:'id',
		                    textField:'text',
		                    multiple:true,
		                    panelHeight:'auto',
		                    panelHeight:142
            				">
						</td>
						<td><input id="weekTimeID" name="weekTime" value="00:00:00"
							class="easyui-timespinner" style="width:80px;"
							required="required" data-options="min:'00:00',showSeconds:true" />
						</td>
						<td>
							<a id="weeksbtn" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="submitForm('weekForm')">设定</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div title="每日" style="padding:10px">
			<form id="dayForm" method="post" action="">
				<input name="day" type="hidden" value="true">
				<div style="padding:8px">
					<div class="demo-tip icon-tip">&nbsp;</div>
					<div>选择时间点，可以添加多个。</div>
				</div>
				<table id="dayTable">
					<tr>
						<td>
							<input id="dayTimeID" name="dayTime" value="00:00:00"
							class="easyui-timespinner" style="width:80px;"
							required="required" data-options="min:'00:00',showSeconds:true" />
						</td>
						<td width="12px"></td>
						<td>
							<a id="daysadd" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-add'" onclick="addDayTime()">添加</a>
						</td>
					</tr>
				</table>
				<div style="margin-top: 6px"></div>
				<hr>
				<div style="margin-top: 6px"></div>
				<table>
					<tr>
						<td>
							<a id="daysadd" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="submitForm('dayForm')">提交</a>
						</td>
						<td width="12px"></td>
					</tr>
				</table>
			</form>
		</div>
		<div title="实时" style="padding:10px">
			<form id="cycleForm" method="post" action="">
			<input name="loop" type="hidden" value="true">
			<div style="padding:8px">
				<div class="demo-tip icon-tip">&nbsp;</div>
				<div>选择时间单位时、分、秒，再选择时间间隔。</div>
			</div>
				<table>
					<tr>
						<td style="color:#8F5700;">选择单位：</td>
						<td><input class="easyui-combobox" id="loopTypeID"
							name="loopType"
							data-options="
		                    url:'unit.json',
		                    method:'get',
		                    valueField:'id',
		                    textField:'text',
		                    panelHeight:'auto',
		                    panelHeight:142,
		                    onChange:function(newValue,oldValue){
		                    	reloadCycle(newValue);
					    	}
            				">
						</td>
					</tr>
					<tr>
						<td style="color:#8F5700;">选择周期：</td>
						<td><input class="easyui-combobox"
							id="intervalID" name="interval"
							data-options="valueField:'id',textField:'text'"></td>
					</tr>
				</table>
				<div style="margin-top: 6px"></div>
				<hr>
				<div style="margin-top: 6px"></div>
				<table>
					<tr>
						<td>
							<a id="daysadd" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="submitForm('cycleForm')">提交</a>
						</td>
						<td width="12px"></td>
					</tr>
				</table>
			</form>
		</div>

			
		<div title="自定义" style="padding:10px">
		<form id="userDefineForm" method="post" action="">
			<input name="userDefine" type="hidden" value="true">
			<div style="padding:8px">
				<div class="demo-tip icon-tip">&nbsp;</div>
				<div>自定义跑批时间：指定年、月、日、时、分、秒。</div>
			</div>
			<div style="margin:10px 0;"></div>
			<input id="defineTimeID" name="defineTime" class="easyui-datetimebox" value="10/11/2012 23:59:59"
				style="width:150px">
			<div style="margin-top: 6px"></div>
			<hr>
			<div style="margin-top: 6px"></div>
			<table>
				<tr>
					<td>
						<a id="daysadd" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'" onclick="submitForm('userDefineForm')">提交</a>
					</td>
					<td width="12px"></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>
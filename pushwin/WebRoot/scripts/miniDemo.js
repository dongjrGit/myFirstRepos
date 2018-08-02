//十六进制颜色值的正则表达式  
var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;  
/*RGB颜色转换为16进制*/  
String.prototype.colorHex = function(){  
    var that = this;  
    if(/^(rgb|RGB)/.test(that)){  
        var aColor = that.replace(/(?:\(|\)|rgb|RGB)*/g,"").split(",");  
        var strHex = "#";  
        for(var i=0; i<aColor.length; i++){  
            var hex = Number(aColor[i]).toString(16);  
            if(hex === "0"){  
                hex += hex;   
            }  
            strHex += hex;  
        }  
        if(strHex.length !== 7){  
            strHex = that;    
        }  
        return strHex;  
    }else if(reg.test(that)){  
        var aNum = that.replace(/#/,"").split("");  
        if(aNum.length === 6){  
            return that;      
        }else if(aNum.length === 3){  
            var numHex = "#";  
            for(var i=0; i<aNum.length; i+=1){  
                numHex += (aNum[i]+aNum[i]);  
            }  
            return numHex;  
        }  
    }else{  
        return that;      
    }  
};

$(function (){
	$('#t4ui_disJ').find('input[value="1"]').attr('checked', 'checked');
	
	 //全局按钮及其他颜色
	 var outCol = $('#t4ui_topBg', parent.document).css('background-color');
	 var allCol = outCol.colorHex();
	 var num = 0;
	 $('.icon-add,.icon-remove, .Edit_Button, .Delete_Button').live('click', function (){		 
		 var style = '<style rel="stylesheet" type="text/css" id="addSty">' 
			 + 'body a:hover.mini-button, .mini-messagebox-buttons .mini-button{ background:' + outCol + '}' 
			 + '</style>';
		 if (!$('style').is('#addSty')){
			 $('head').append(style);
		 }     
	 });
	 
	 //按钮颜色、打印文字颜色、表格小按钮颜色、弹出框颜色、启动按钮、提醒框
	 $('#t4ui_MR .icon-search,.t4ui_buttonImg, .mini-toolbar .mini-button, .t4ui_but a, .icon-ok, .t4ui_girdBtnL,'
	 //公告新增、发送按钮、重置按钮、重置按钮、license
	 + '.t4ui_selfSda, ,#t4ui_button1, #t4ui_button2, #t4ui_MR .icon-search, .t4ui_litB, body a:hover.mini-button').css('background',outCol);
	 /*
	 switch (allCol){
	 	case '#007f86':
	 		var num = 0,
	 		botLine = '#4ab3b9';
	 		break;
	 		
	 	case '#169de4':
	 		var num = 1,
	 		//头部底线
	 		botLine = '#73bce3';
	 		//选择条颜色	 	
	 		var selectBac = '#fef4e2';
	 		$('#t4ui_girdBtnR').css('background', '#f29700');
	 		break;
	 	
	 	case '#d00000':
	 		var num = 2,
	 		botLine = '#d64747';
	 		$('#t4ui_girdBtnR').css('background', '#e9c53d');
	 		break;
	 	
	 	case '#ffffff':
	 		var num = 3,
	 		botLine = '#b4cce8';
	 		break;
	 };
	  */
	 if(allCol == '#007f86'){
	 	var num = 0,
	 	botLine = '#4ab3b9';
	 } else if(allCol == '#169de4') {
		var num = 1,
 		//头部底线
 		botLine = '#73bce3';
 		//选择条颜色	 	
 		var selectBac = '#fef4e2';
 		$('#t4ui_girdBtnR').css('background', '#f29700');
	 } else if (allCol == '#d00000') {
		var num = 2,
 		botLine = '#d64747';
 		$('#t4ui_girdBtnR').css('background', '#e9c53d');
	 } else if (allCol == '#ffffff'){
		var num = 3,
	 	botLine = '#b4cce8';
	 }
	
	 $('#t4ui_girdM .mini-grid-columns, #t4ui_mang .mini-toolbar, #t4ui_org .mini-toolbar, #t4ui_treeM .mini-grid-columns, .t4ui_botLine').css('border-bottom', '2px solid ' + botLine);
	 $('.Edit_Button').css('color', botLine);
	 $('html body .mini-grid-row-selected').css('background', selectBac);
	 //左上角小图标
	 $('#t4ui_pageImg').css('backgroundImage','url(../../img/home_icon_0'+num+'.jpg)');
	 //$('#t4ui_pageImg').css('background','url(img/home_icon_0'+num+'.jpg)');
});
//日期禁用
function setValue() {
	var t = mini.get("date2");
	t.setValue(new Date());
}
function getValue() {
	var t = mini.get("date2");

	alert(t.getValue());
}
function enable() {
	var t = mini.get("date2");
	t.enable();
}
function disable() {
	var t = mini.get("date2");
	
	t.disable();
}

function onValueChanged(e) {
	this.getFormValue();
}

////////////////////////////

function onDrawDate(e) {
	var date = e.date;
	var d = new Date();

	if (date.getTime() < d.getTime()) {
		e.allowSelect = false;
	}
}




//修改、删除
var Genders = [{ id: 1, text: '男' }, { id: 2, text: '女'}];

	mini.parse();

	var editWindow = mini.get("editWindow");
   
	var grid = mini.get("datagrid1");
	grid.load();
	grid.sortBy("createtime", "desc");
	
	///////////////////////////////////////////////////////       
	
	function onGenderRenderer(e) {
		for (var i = 0, l = Genders.length; i < l; i++) {
			var g = Genders[i];
			if (g.id == e.value) return g.text;
		}
		return "";
	}

	function onActionRenderer(e) {
		var grid = e.sender;
		var record = e.record;
		var uid = record._uid;
		var rowIndex = e.rowIndex;

		var s = /*'<a class="New_Button" href="javascript:newRow()">New</a>' + */
				'<a class="Edit_Button" href="javascript:editRow(\'' + uid + '\')"></a>'
				+ '<a class="Delete_Button" href="javascript:delRow(\'' + uid + '\')"></a>';
				   
		return s;
	}

	function newRow() {            
		var row = {};
		grid.addRow(row, 0);

		editRow(row._uid);
	}
	function editRow(row_uid) {
		var row = grid.getRowByUID(row_uid);
		if (row) {

			editWindow.show();
			var form = new mini.Form("#editform");
			form.clear();

			form.loading();
			$.ajax({
				url: "../data/AjaxService.aspx?method=GetEmployee&id=" + row.id,
				success: function (text) {
					form.unmask();

					var o = mini.decode(text);
					form.setData(o);
					
					
				},
				error: function () {
					alert("表单加载错误");
					form.unmask();
				}
			});

		}
	}

	function cancelRow() {
		grid.reload();
		editWindow.hide();
	}
	function delRow(row_uid) {
		var row = grid.getRowByUID(row_uid);
		if (row) {
			if (confirm("确定删除此记录？")) {
				grid.loading("删除中，请稍后......");
				$.ajax({
					url: "../data/AjaxService.aspx?method=RemoveEmployees&id=" + row.id,
					success: function (text) {
						grid.reload();
					},
					error: function () {
					}
				});
			}
		}
	}
	
	function updateRow() {            
		var form = new mini.Form("#editform");
		var o = form.getData();

		grid.loading("保存中，请稍后......");
		var json = mini.encode([o]);
		$.ajax({
			url: "../data/AjaxService.aspx?method=SaveEmployees",
			data: { data: json },
			success: function (text) {
				
				grid.reload();
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText);
			}
		});

		editWindow.hide(); 
	}

							
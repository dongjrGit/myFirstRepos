var ZooMapEdit = {
	formCancel : function() {
		window.location.href = "/zoo/zooMap/list?type=" + type + "&state=" + state;
	},
	changeFile : function(object) {
		var agent = navigator.userAgent.toLowerCase();
		if (agent.indexOf("msie") > 0) {
			var version = agent.match(/msie [\d.]+;/gi)[0];
			if (version == 'msie 9.0;') {
				$("#loadimg").attr("src", "");
				object.select();
				$('#div_image').focus();
				var nfile = document.selection.createRange().text;
				document.selection.empty();
				document.getElementById("loadimg").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + nfile + "')";
			} else {
				var objUrl = getObjectURL(object.files.item(0));
				if (objUrl) {
					$("#loadimg").attr("src", objUrl);
				} else {
					$("#loadimg").attr("src", "");
				}
			}
		} else {
			var objUrl = getObjectURL(object.files.item(0));
			if (objUrl) {
				$("#loadimg").attr("src", objUrl);
			} else {
				$("#loadimg").attr("src", "");
			}
		}
	}
}

// 建立一个可存取到该file的url
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
$(document).ready(function(){
	$("input[name=Save]").bind("click", save);
	$(".h_scimgbut1").click(function() {
		if($("#singlefile").val()){
			$.ajaxFileUpload({
				url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				data : {
					relationtype : 50
				},
				type : 'POST',
				success : function(result) {
					if (parseInt(result.desc) >= 512000) {
						Dalert("图片大小不能超过500kb");
					} else {
						if (result.data != "") {
							if (result.code == 0) {
								$("#loadimg").attr("src", result.data);
								$("#ossObject").val(result.ossObject);
								Dalert("上传成功");
								$("input[name='img']").val(result.data);
							} else {
								$("#loadimg").attr("src", "");
								Dalert("上传图片失败");
							}
						}
					}
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				},
				complete:function(){
        			
        		}
			});
			$("#singlefile").remove();
			var input = '<input type="file" id="singlefile" name="pics" class="filemhbut"  onchange="ZooMapEdit.changeFile(this);"  style="top: 10px; left: 27px;" />';
			$("#buttonid").after(input);
		}
	});
});


function save() {
	if (formSubmit()) {
		var data = {};
		data.id =id;
		data.url=$("input[name='img']").val();
		data.name=$('#name').val();
		data.type=type;
		data.state=state;
		data.path=$("#ossObject").val();
		var jsonData = JSON.stringify(data);
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		$.ajax({
			url : "/zoo/zooMap/editlist",
			type : "Post",
			data : {
				"data" : jsonData
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", function() {
						window.location.href = "/zoo/zooMap/list?type=" + type + "&state=" + state;
					});
				} else {
					$("input[name='Save']").show();
					Dalert(data.desc);
				}
			},
			error : function(xhr,status,error) {
				alert(xhr.responseText);
			}
		});
	}
}

function formSubmit() {
	if($('#sort').val()){
		if(!(/^\d+$/.test( $('#sort').val() ))){
			Dalert("格式不对！");
			return false;
		}
	}
	
	if ($("#name").val() == "") {
		Dalert("名称不能为空！");
		return false;
	}
	// 判断景点图片是否上传
	if ($("input[name='img']").val() == '') {
		Dalert("请上传图片！");
		return false;
	}
	return true;
}

$(function() {
	$("input[name=Save]").bind("click", save);
});

var Init = {
	bind : function() {
		// 景点列表
		autoxl.bind("scenicid", getScenicStartwithName, true);
	}
}

/**
 * 查设施列表
 * 
 * @param callback
 * @param event
 */
function getScenicStartwithName(callback, event) {
	var name = $("#scenicid").val();
	var type = $("#type").val();
	if (event)
		name += String.fromCharCode(event.keyCode);
	$.ajax({
		url : "/zoo/scenic/queryScenicName",
		type : "post",
		data : {
			"name" : name,
			"type" : type,
			"state":1
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var listdata = {
					list : data.data
				}
				var html = template('select_sceniclist', listdata);
				if (data.data.length == 0) {
					$("#scenicid").attr("data", "");
				} else {
					$("#scenicid").attr("data", "");
					data.data.forEach(function(value, index) {
						if (value.scenicname == name) {
							$("#scenicid").attr("data", value.id);
							return;
						}
					});
				}
				if (callback) {
					callback(html);
				}
			} else {
				Dalert(data.data);
			}
		},
		error : function(xhr,status,error) {
			alert(xhr.responseText);
		}
	});
}

function save() {
	if (formSubmit()) {
		var data = {};
		data.id = $('#id').val();
		data.img=$("input[name='img']").val();
		data.scenicid=$('#scenicid').attr("data");
		data.remark=$('#remark').val();
		var type=$('#type').val();
		data.type=type;
		console.log(data);
		var jsonData = JSON.stringify(data);
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		$.ajax({
			url : "/zoo/rcmdScenic/editlist",
			type : "Post",
			data : {
				"data" : jsonData
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", function() {
						window.location.href = "/zoo/rcmdScenic/list?type="+type;
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
	var type = $("#type").val();
	var object = {
		"1" : "剧场",
		"2" : "景点",
		"3" : "设施",
		"4" : "动物互动"
	};
	if ($("#scenicid").val() == "") {
		Dalert(object[type] + "名称不能为空！");
		return false;
	}
	if ($("#scenicid").val() != "" && !$("#scenicid").attr("data")) {
		Dalert("您输入的" + object[type] + "名称不存在！");
		return false;
	}
	// 判断景点图片是否上传
	if ($("input[name='img']").val() == '') {
		Dalert("请上传图片！");
		return false;
	}
	return true;

}

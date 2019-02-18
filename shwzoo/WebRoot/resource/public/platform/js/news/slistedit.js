
$(function(){
	$("input[name=Save]").bind("click",save);
});

function save(){
	if (formSubmit()) {
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		var type=$("#type").val();
		var ctype = $("#ctype").val();
		editor.sync();
		$("input[name=content]").val(editor.html());
		$.ajax({
			url : "/platform/news/editslist",
			type : "Post",
			data : $("#form").serialize(),
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", function(){window.location.href="/platform/news/slist?type="+type+"&ctype="+ctype});
				} else {
					$("input[name='Save']").show();
					Dalert(data.desc);
				}
			},
			error : function() {
			}
		});
	}
}

//保存前参数验证
function check() {
	return $("#form").validate({
		rules : {
			name : {
				required : true,
				stringCheck : true,
				rangelength : [ 1, 100 ]
			},
		},
		message : {
			name : {
				required : "标题不可为空"
			}
		}
	});
}

function formSubmit() {
	if (check().form()) {
		if ($("#thirdID").val() != 0) {
			$("#ClassID").val($("#thirdID").val());
		}else if ($("#secondID").val()!= 0) {
			$("#ClassID").val($("#secondID").val());
		}else if ($("#firstID").val()!= 0) {
			$("#ClassID").val($("#firstID").val());
		}
		return true;
	}

}






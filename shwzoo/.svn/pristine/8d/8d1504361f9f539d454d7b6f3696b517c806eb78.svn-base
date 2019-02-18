// 按钮事件绑定
$(function() {
	$("input[name=Save]").bind("click", Save);
})
// 保存
function Save() {
	// var action = $("#action").val();
	var loadimg = $("#img").val();
	var sort = $("#sort").val();
	var title = $("#title").val();
	var url = $("#url").val();
	var statusVal = "";
	for (var i = 0; i < $("input[name=radio_status]").length; i++) {
		if ($("input[name=radio_status]")[i].checked) {
			statusVal = $("input[name=radio_status]")[i].value;
		}
	}

	var id = $("#hidden_navigationid").val();

	// 防止重复提交 点击保存后隐藏按钮
	$("input[name='Save']").hide();
	$.ajax({
		url : "/platform/navigation/edit",
		type : "Post",
		data : {
			img : loadimg,
			url : url,
			sort : sort,
			status : statusVal,
			title : title,
			id : id
		},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {
				$("input[name='Save']").show();
				Dalert(data.desc);
			} else {
				Dalert("编辑成功！", "", function() {
					window.location.href = 'list';
				});
			}
		},
		error : function() {
		}
	});

}

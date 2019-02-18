
$(function(){
	$("input[name=Save]").bind("click",save);
});

function save(){
	if (check().form()) {
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		var type=$("#type").val();
		$.ajax({
			url : "/platform/baner/editlist",
			type : "Post",
			data : $("#form").serialize(),
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", function(){window.location.href="/platform/baner/list?type="+type});
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
			title : {
				required : true,
				stringCheck : true,
				rangelength : [ 1, 100 ]
			},
			sort:{
				digits:true
			},
		},
		message : {
			title : {
				required : "标题不可为空"
			},
			sort:{
				digits:"请输入整数"
			},
		}
	});
}







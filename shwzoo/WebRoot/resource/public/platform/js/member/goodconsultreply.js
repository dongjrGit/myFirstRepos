//商品咨询ID
var goodconsultID;
// 初始化
var Init = {
	bind : function() {
		// 获取商品咨询ID
		goodconsultID = GetQueryStringByName("goodconsultID");
	}
}
// 表单验证
var Vaildate = {
	bind : function() {
		$("#replygoodconsultForm").validate({
			rules : {
				text_replygoodconsult : {
					required : true
				}
			},
			messages : {
				text_replygoodconsult : {
					required : "请输入回复内容"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			},
			debug : true,
			submitHandler : function(form) {
				$(form).ajaxSubmit(Submit.bind());
			}
		})
	}
}
// 表单提交
var Submit = {
	bind : function() {
		var replygoodconsult = $("#text_replygoodconsult").val();
		$.ajax(({
			type : "post",
			url : "/platform/goodconsult/replyGoodConsult",
			dataType : "json",
			data : {
				id : goodconsultID,
				replycontent : replygoodconsult
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert("回复成功");
					$("#text_replygoodconsult").attr("value", "");
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}
//店铺ID
var shopID;
// 初始化
var Init = {
	bind : function() {
		// 获取店铺ID
		shopID = GetQueryStringByName("shopid");
	}
}
// 表单验证
var Vaildate = {
	bind : function() {
		$("#updateshoppwdForm").validate({
			rules : {
				text_newpwd : {
					required : true,
					byteRangeLength : [ 6, 15 ]
				},
				text_againpwd : {
					required : true,
					equalTo : "#text_newpwd"
				}
			},
			messages : {
				text_newpwd : {
					required : "请输入新密码",
					byteRangeLength : "密码字符长度不正确（6-15）"
				},
				text_againpwd : {
					required : "请输入确认新密码",
					equalTo : "两次密码不一致"
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
		var newpwd = $("#text_newpwd").val();
		var repwd = $("#text_againpwd").val();
		$.ajax(({
			type : "post",
			url : "/platform/shop/updatePayPwd",
			dataType : "json",
			data : {
				shopid : shopID,
				newpwd : newpwd,
				repwd : repwd
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert("密码修改成功");
					$("#text_newpwd").attr("value", "");
					$("#text_againpwd").attr("value", "");
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}
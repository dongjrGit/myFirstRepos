//会员ID
var memberID;
// 初始化
var Init = {
	bind : function() {
		// 获取会员ID
		memberID = GetQueryStringByName("memberID");
	}
}

// 表单验证
var Vaildate = {
	bind : function() {
		$("#updatememberpwdForm").validate({
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
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/updateMemberPwd",
			dataType : "json",
			data : {
				usertype : 2,
				memberid : memberID,
				loginpwd : newpwd
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
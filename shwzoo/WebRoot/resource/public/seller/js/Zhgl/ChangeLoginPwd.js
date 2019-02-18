//初始化
var Init = {
	bind : function() {

	}
}
// 表单验证
var Vaildate = {
	bind : function() {
		$("#changeLoginPwdForm").validate({
			rules : {
				pwd_oldpwd : {
					required : true,
				},
				pwd_newpwd : {
					required : true,
					byteRangeLength : [ 6, 16 ]
				},
				pwd_surepwd : {
					required : true,
					equalTo : "#pwd_newpwd"
				}
			},
			messages : {
				pwd_oldpwd : {
					required : "请输入原密码",
					isoldpwd :"密码无效"
				},
				pwd_newpwd : {
					required : "请输入新密码",
					byteRangeLength : "密码长度不正确，请重新设置(6-16位)"
				},
				pwd_surepwd : {
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
		var stroldpwd = $("#pwd_oldpwd").val();
		var strnewpwd = $("#pwd_newpwd").val();
		var strnewpwdagain = $("#pwd_surepwd").val();
		var userid = $("#hidden_userid").val();

		$.ajax(({
			type : "post",
			url : "/seller/message/updateUserPassword",
			dataType : "json",
			data : {
				userid : userid,
				oldpwd : stroldpwd,
				newpwd : strnewpwd,
				newpwdAgain : strnewpwdagain
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					SetCookie("_u_token","");
					Dalert("密码修改成功",3000,function(){parent.location.reload()});

					//$("#pwd_oldpwd").attr("value", "");
					//$("#pwd_newpwd").attr("value", "");
					//$("#pwd_surepwd").attr("value", "");
				}else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}
